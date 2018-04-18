/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.core.sax;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author phuongtu
 */
public class SAXRow {

    private List<SAXColumn> cols = new ArrayList<SAXColumn>();

    public void addCols(SAXColumn col) {
        this.cols.add(col);
    }

    public StringBuffer newRow() {
        StringBuffer strRow = new StringBuffer();
        strRow.append("<w:tr>");
        SAXColumn col = null;
        for (int i = 0; i < cols.size(); i++) {
            col = (SAXColumn) cols.get(i);
            strRow.append(col.newCol());
        }
        strRow.append("</w:tr>");
        return strRow;
    }
}
