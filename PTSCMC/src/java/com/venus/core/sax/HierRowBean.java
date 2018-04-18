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
public class HierRowBean extends HierBean {

    private ArrayList tableArray;
    private boolean needDuplicate = false;
    private HierTableBean parentTable;
    private int rowId;
    private String rowName;

    public ArrayList getTableArray() {
        return tableArray;
    }

    public void setTableArray(ArrayList tableArray) {
        this.tableArray = tableArray;
    }

    public void setNeedDuplicate(boolean needDuplicate) {
        this.needDuplicate = needDuplicate;
    }

    public boolean getNeedDuplicate() {
        return this.needDuplicate;
    }

    @Override
    public StringBuffer convertToString() {
        StringBuffer result = new StringBuffer("");
        if (content != null) {
            if (this.tableArray != null) {
                HierTableBean bean = null;
                for (int i = 0; i < this.tableArray.size(); i++) {
                    bean = (HierTableBean) this.tableArray.get(0);
                    content.append(bean.convertToString());
                }
            }
            this.tableArray = null;
            result.append(content);
            content = null;
        }
        return result;
    }

    public void addTable(HierTableBean tableBean) {
        if (this.tableArray == null) {
            this.tableArray = new ArrayList();
        }
        this.tableArray.add(tableBean);
    }

    public HierTableBean getParentTable() {
        return parentTable;
    }

    public void setParentTable(HierTableBean parentTable) {
        this.parentTable = parentTable;
    }

    @Override
    protected void finalize() throws Throwable {
        this.tableArray = null;
        this.content = null;
        this.parentTable = null;
        super.finalize();
    }

    public int getRowId() {
        return rowId;
    }

    public void setRowId(int rowId) {
        this.rowId = rowId;
    }

    public String getRowName() {
        return rowName;
    }

    public void setRowName(String rowName) {
        this.rowName = rowName;
    }

    public HierRowBean clone(HierSpineReportParser report) {
        HierRowBean result = new HierRowBean();
        result.setContent(content);
        result.setNeedDuplicate(false);
        if (rowName != null) {
            report.increaseRowId();
        }
        result.setRowId(report.getRowId());
        result.setRowName(rowName);
        ArrayList arrTable = new ArrayList();
        result.setTableArray(arrTable);
        if (tableArray != null) {
            HierTableBean table = null;
            HierTableBean tableTemp = null;
            for (int i = 0; i < tableArray.size(); i++) {
                table = (HierTableBean) tableArray.get(i);
                tableTemp = table.clone(report);
                arrTable.add(tableTemp);
            }
        }
        return result;
    }

    public HierRowBean generate(HierSpineReportParser report) {
        HierRowBean result = new HierRowBean();
        result.setContent(new StringBuffer(content));
        result.setNeedDuplicate(false);
        if (report != null) {
            if (rowName != null) {
                report.increaseRowId();
            }
            result.setRowId(report.getRowId());
        }
        result.setRowName(rowName);
        ArrayList arrTable = new ArrayList();
        result.setTableArray(arrTable);
        if (tableArray != null) {
            HierTableBean table = null;
            HierTableBean tableTemp = null;
            for (int i = 0; i < tableArray.size(); i++) {
                table = (HierTableBean) tableArray.get(i);
                if (report != null) {
                    tableTemp = table.clone(report);
                    arrTable.add(tableTemp);
                    tableTemp.generate(report);
                }
            }
            insertNewTableGenerated(result.getContent(), arrTable);
        }
        return result;
    }

    private void insertNewTableGenerated(StringBuffer content, ArrayList arrTable) {
        if (arrTable != null && arrTable.size() > 0) {
            String insertedMark = "mcrp_hierarchic";
            HierTableBean table = null;
            String str = "";
            int index = -1;
            int length = -1;
            for (int i = arrTable.size() - 1; i >= 0; i--) {
                table = (HierTableBean) arrTable.get(i);
                if (table.getTableName() != null) {
                    str = "{" + insertedMark + table.getTableName() + "}";
                    index = content.indexOf(str);
                    length = str.length();
                }
                if (index > -1) {
                    content.insert(index + length, table.convertToString());
                }
            }
            if (index > -1) {
                content.delete(index, index + length);
            }
        }
    }

    public void setTextForRow(SpineReportParser report) {
        if (content != null) {
            int startIndex = content.indexOf("{");
            String str = "";
            while (startIndex != -1) {
                int endIndex = content.indexOf("}");
                if (endIndex != -1) {
                    String tab = content.substring(startIndex + 1, endIndex);
                    str = report.getTabText(rowName, rowId, tab);
                    str = this.normalize(str);
                    content.replace(startIndex, endIndex + 1, str);
                }
                startIndex = content.indexOf("{", endIndex);
            }
            if (tableArray != null) {
                HierTableBean table = null;
                for (int i = 0; i < tableArray.size(); i++) {
                    table = (HierTableBean) tableArray.get(i);
                    table.setTextForTable(report);
                }
            }
        }
    }
}
