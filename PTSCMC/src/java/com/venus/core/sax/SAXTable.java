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
public class SAXTable {
    private List<SAXRow> rows = new ArrayList<SAXRow>();
    public void addRows(SAXRow row){
        this.rows.add(row);
    }
    
    public StringBuffer createRows(){
        StringBuffer strRows = new StringBuffer();
        SAXRow row = null;
        for(int i = 0; i < rows.size(); i++){
            row = (SAXRow)rows.get(i);            
            strRows.append(row.newRow());
        }
        return strRows;
    }       
}



