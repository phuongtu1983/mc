package com.venus.mc.database;

import com.venus.core.database.DBInit;
import java.sql.SQLException;

/***
 *
 * @author Administrator
 *
 */
public class MCDBInit extends DBInit {
    public MCDBInit() {
    }
    
    @Override
    public void init(){
        //System.out.println("Begin get Connection here =====");
        try{
            this.getDataSource("DBMC");
        } catch(SQLException sqle){
            sqle.printStackTrace();            
        }
        //System.out.println("end get Connection here =====");
    }
}