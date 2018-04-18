/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.core.sax;

import com.venus.mc.core.HierSpineReportParser;
import com.venus.mc.core.SpineReportParser;
import java.util.ArrayList;

/**
 *
 * @author PhuongTu
 */
public class HierTableBean extends HierBean {

    private ArrayList rowArray;
    private HierRowBean parentRow;
    private int tableId;
    private String tableName;

    public ArrayList getRowArray() {
        return rowArray;
    }

    public void setRowArray(ArrayList rowArray) {
        this.rowArray = rowArray;
    }

    public void addRow(HierRowBean rowBean) {
        if (this.rowArray == null) {
            this.rowArray = new ArrayList();
        }
        this.rowArray.add(rowBean);
    }

    @Override
    public StringBuffer convertToString() {
        StringBuffer result = new StringBuffer("");
        if (content != null) {
            if (this.rowArray != null) {
                HierRowBean bean = null;
                for (int i = 0; i < this.rowArray.size(); i++) {
                    bean = (HierRowBean) this.rowArray.get(0);
                    content.append(bean.convertToString());
                }
                this.rowArray = null;
            }
            result.append(content);
            content = null;
        }
        return result;
    }

    public HierRowBean getParentRow() {
        return parentRow;
    }

    public void setParentRow(HierRowBean parentRow) {
        this.parentRow = parentRow;
    }

    public int getTableId() {
        return tableId;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public void generateStructure(Object obj) {
        ArrayList arr = (ArrayList) obj;
        for (int i = 0; i < arr.size(); i++) {
            HierReportBean bean = (HierReportBean) arr.get(i);
            bean.generate(this);
        }
    }

    public void generateStructureFromSaxHandler(Object obj) {
        RowSAXHandler rowHandler = (RowSAXHandler) obj;
        HierRowBean row = null;
        HierRowBean rowTemp = null;
        int size = rowArray.size();
        for (int i = size - 1; i >= 0; i--) {
            row = (HierRowBean) rowArray.get(i);
            if (row.getRowName() != null) {
                int num = rowHandler.getRowCount();
                if (num > 0) {
                    for (int j = 0; j < num; j++) {
                        rowTemp = row.generate(null);
                        rowTemp.setRowId(j);
                        rowArray.add(i, rowTemp);
                    }
                    rowArray.remove(i + num);
                }
            }
        }
        setTextForTable(rowHandler.getReport());
        insertNewRowGenerated();
    }

    public HierTableBean clone(HierSpineReportParser report) {
        HierTableBean table = new HierTableBean();
        table.setContent(new StringBuffer(content));
        table.setTableId(tableId);
        table.setTableName(tableName);
        HierRowBean row = null;
        ArrayList arrRow = new ArrayList();
        table.setRowArray(arrRow);
        if (rowArray != null) {
            for (int i = 0; i < rowArray.size(); i++) {
                row = (HierRowBean) rowArray.get(i);
                arrRow.add(row.clone(report));
            }
        }
        return table;
    }

    public void generate(HierSpineReportParser report) {
        if (rowArray != null) {
            HierRowBean row = null;
            int size = rowArray.size();
            for (int i = size - 1; i >= 0; i--) {
                row = (HierRowBean) rowArray.get(i);
                if (row.getRowName() != null) {
                    int num = report.getNumberOfRowToDuplicate(row.getRowId(), row.getRowName());
                    if (num > 0) {
                        report.descreaseRowId();// boi vi se remove 1 dong, tranh lam tang them rowId
                        for (int j = 0; j < num; j++) {
                            rowArray.add(i, row.generate(report));
                        }
                        rowArray.remove(i + num);
                    }
                    if (num == 0) {
                        num = 1;
                        report.descreaseRowId();// boi vi se remove 1 dong, tranh lam tang them rowId
                        for (int j = 0; j < num; j++) {
                            rowArray.add(i, row.generate(report));
                        }
                        rowArray.remove(i + num);
                    }
//                    report.descreaseRowId();// boi vi se remove 1 dong, tranh lam tang them rowId
//                    for (int j = 0; j < num; j++) {
//                        rowArray.add(i, row.generate(report));
//                    }
//                    rowArray.remove(i + num);
                }
            }
            setTextForTable(report);
            insertNewRowGenerated();
        }
    }

    private void insertNewRowGenerated() {
        if (rowArray != null && rowArray.size() > 0) {
            String insertedMark = "mcrp_hierarchic";
            HierRowBean row = null;
            String str = "";
            int index = -1;
            int length = -1;
            for (int i = 0; i < rowArray.size(); i++) {
                row = (HierRowBean) rowArray.get(i);
                if (row.getRowName() != null) {
                    str = "{" + insertedMark + row.getRowName() + "}";
                    index = content.indexOf(str);
                    length = str.length();
                }
                if (index > -1) {
                    content.insert(index + length, row.convertToString());
                }
            }
            if (index > -1) {
                content.delete(index, index + length);
            }
        }
    }

    public void setTextForTable(SpineReportParser report) {
        if (rowArray != null) {
            HierRowBean row = null;
            for (int i = 0; i < rowArray.size(); i++) {
                row = (HierRowBean) rowArray.get(i);
                row.setTextForRow(report);
            }
        }
    }

    @Override
    protected void finalize() throws Throwable {
        this.parentRow = null;
        this.rowArray = null;
        this.content = null;
        super.finalize();
    }
}
