/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.dao;

import com.venus.core.util.DateUtil;
import com.venus.core.util.StringUtil;
import com.venus.mc.bean.TimeUsingBean;
import com.venus.mc.database.DBUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author thcao
 */
public class TimeUsingDAO extends BasicDAO {
    
    public TimeUsingDAO() {
    }
    
    public ArrayList getTimeUsingList() throws Exception {
        
        ResultSet rs = null;
        
        String sql = "select r.*,e.fullname from time_using as r"
                + " left join employee as e on e.emp_id = r.created_emp"
                + " group by update_date";
        
        ArrayList tuList = new ArrayList();
        try {
            
            
            
            rs = DBUtil.executeQuery(sql);
            TimeUsingBean tuBean = null;
            while (rs.next()) {
                tuBean = new TimeUsingBean();
                tuBean.setUpdateDate(DateUtil.formatDate(rs.getDate("update_date"), "dd/MM/yyyy"));
                tuBean.setCreatedEmpId(rs.getInt("created_emp"));
                tuBean.setCreatedEmpName(rs.getString("e.fullname"));
                tuList.add(tuBean);
            }
            
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        } finally {
            if (rs != null) {
                DBUtil.closeConnection(rs);
            }
            
        }
        return tuList;
    }
    
    public ArrayList getTimeUsing(String strDate) throws Exception {
        
        ResultSet rs = null;
        
        String sql = "select r.*,e.fullname,q.manage_org,q.used_code,m.name_vn,u.unit_vn,q.appeared_date, o.name "
                + " from time_using as r"
                + " left join employee as e on e.emp_id = r.created_emp"
                + " left join equipment as q on q.equ_id = r.equ_id"
                + " left join material as m on m.mat_id=q.mat_id"
                + " left join unit as u on u.uni_id=m.uni_id"
                + " left join organization as o on o.org_id=q.manage_org"
                + " where r.update_date=STR_TO_DATE('" + strDate + "','%d/%m/%Y')";
        
        ArrayList tuList = new ArrayList();
        try {
            
            
            
            rs = DBUtil.executeQuery(sql);
            int i = 1;
            while (rs.next()) {
                TimeUsingBean tuBean = new TimeUsingBean();
                tuBean.setNo(i++);
                tuBean.setTuId(rs.getInt("r.tu_id"));
                tuBean.setUpdateDate(DateUtil.formatDate(rs.getDate("update_date"), "dd/MM/yyyy"));
                tuBean.setCreatedEmpId(rs.getInt("created_emp"));
                tuBean.setCreatedEmpName(rs.getString("e.fullname"));
                tuBean.setManageOrgId(rs.getInt("q.manage_org"));
                tuBean.setUsedCode(rs.getString("q.used_code"));
                tuBean.setEquId(rs.getInt("r.equ_id"));
                tuBean.setEquipmentName(StringUtil.decodeString(rs.getString("m.name_vn")));
                tuBean.setUnit(rs.getString("u.unit_vn"));
                tuBean.setTimeUsed(rs.getLong("r.time_used"));
                tuBean.setAppearedDate(DateUtil.formatDate(rs.getDate("q.appeared_date"), "dd/MM/yyyy"));
                tuBean.setManageOrgName(rs.getString("o.name"));
                tuList.add(tuBean);
            }
            
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        } finally {
            if (rs != null) {
                DBUtil.closeConnection(rs);
            }
            
        }
        return tuList;
    }
    
    public ArrayList getTimeUsingFromEquipment(String strUpdateDate) throws Exception {
        
        ResultSet rs = null;
        
        String sql = "select e.*,m.name_vn,u.unit_vn, o.name "
                + " from equipment as e"
                + " left join material as m on m.mat_id=e.mat_id"
                + " left join unit as u on u.uni_id=m.uni_id"
                + " left join organization as o on o.org_id=e.manage_org"
                + " where e.time_update=1";
        
        ArrayList tuList = new ArrayList();
        try {
            
            
            
            rs = DBUtil.executeQuery(sql);
            int idx = 1;
            while (rs.next()) {
                TimeUsingBean tuBean = new TimeUsingBean();
                tuBean.setNo(idx++);
                tuBean.setManageOrgId(rs.getInt("e.manage_org"));
                tuBean.setUsedCode(rs.getString("e.used_code"));
                tuBean.setEquId(rs.getInt("e.equ_id"));
                tuBean.setEquipmentName(StringUtil.decodeString(rs.getString("m.name_vn")));
                tuBean.setUnit(rs.getString("u.unit_vn"));
                tuBean.setManageOrgName(rs.getString("o.name"));
                tuBean.setAppearedDate(DateUtil.formatDate(rs.getDate("e.appeared_date"), "dd/MM/yyyy"));
                tuBean.setUpdateDate(strUpdateDate);
                tuList.add(tuBean);
            }
            
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        } finally {
            if (rs != null) {
                DBUtil.closeConnection(rs);
            }
            
        }
        return tuList;
    }
    
    public boolean checkTimeUsing(String updateDate) throws Exception {
        ResultSet rs = null;
        try {
            
            
            
            String sql = "select * from time_using where update_date = STR_TO_DATE('" + updateDate + "','%d/%m/%Y')";
            
            rs = DBUtil.executeQuery(sql);
            if (rs.next()) {
                
                return true;
            } else {
                
                return false;
            }
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        } finally {
            if (rs != null) {
                DBUtil.closeConnection(rs);
            }
            
        }
    }
    
    public int insertTimeUsing(TimeUsingBean bean) throws Exception {
        if (bean == null) {
            return 0;
        }
        int result = 0;
        try {
            String sql = "";
            sql = "insert into time_using(update_date,created_emp,equ_id,time_used)"
                    + " values (sysdate()"
                    + "," + bean.getCreatedEmpId()
                    + "," + bean.getEquId()
                    + "," + bean.getTimeUsed() + ")";
            ////System.out.println("sql ====" + sql);
            result = DBUtil.executeInsert(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return result;
    }
    
    public void updateTimeUsing(TimeUsingBean bean) throws Exception {
        if (bean == null) {
            return;
        }
        int result = 0;
        try {
            String sql = "update time_using"
                    + " set time_used=" + bean.getTimeUsed()
                    + ",update_date=STR_TO_DATE('" + bean.getUpdateDate() + "','%d/%m/%Y')" + " where tu_id=" + bean.getTuId();
            ////System.out.println("sql=" + sql);
            result = DBUtil.executeUpdate(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }
    
    public int deleteTimeUsing(int tuId) throws Exception {
        int result = 0;
        try {
            String sql = "delete from time_using " + " where tu_id=" + tuId;
            result = DBUtil.executeUpdate(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        }
        return result;
    }
    
    public ArrayList searchSimpleTimeUsing(int fieldid, String strFieldvalue) throws Exception {
        String strFieldname = "";
        switch (fieldid) {
            case 1:
                strFieldname = "update_date";
                break;
            
        }
        ResultSet rs = null;
        
        String sql = "select r.*,e.fullname from time_using as r"
                + " left join employee as e on e.emp_id = r.created_emp";
        sql += " where 1";
        if ((fieldid > 0) && (!StringUtil.isBlankOrNull(strFieldvalue))) {
            sql = sql + "and " + strFieldname + "= STR_TO_DATE('" + strFieldvalue + "','%d/%m/%Y')";
        }
        sql += " group by update_date";
        sql = sql + " order by tu_id desc";
        
        ArrayList tuList = new ArrayList();
        try {
            
            
            
            rs = DBUtil.executeQuery(sql);
            TimeUsingBean tuBean = null;
            while (rs.next()) {
                tuBean = new TimeUsingBean();
                tuBean.setUpdateDate(DateUtil.formatDate(rs.getDate("update_date"), "dd/MM/yyyy"));
                tuBean.setCreatedEmpId(rs.getInt("created_emp"));
                tuBean.setCreatedEmpName(rs.getString("e.fullname"));
                tuList.add(tuBean);
            }
            
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        } finally {
            if (rs != null) {
                DBUtil.closeConnection(rs);
            }
            
        }
        return tuList;
        
    }

    //Tong thoi gian thuc hien
    public long getTotalTimeUsed(int equId, String updateDate) throws Exception {
        ResultSet rs = null;
        
        String sql = "select sum(r.time_used) as total from time_using as r where r.equ_id=" + equId
                + " and r.update_date<STR_TO_DATE('" + updateDate + "','%d/%m/%Y')";
        //String sql = "Select con_id From contract_detail Where reqDetail_id=" + reqDetId;

        long result = 0;
        try {



            //System.out.println("sql=" + sql);

            rs = DBUtil.executeQuery(sql);
            while (rs.next()) {
                result = rs.getLong("total");
            }
            
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        } finally {
            DBUtil.closeConnection(rs);
        }
        return result;
    }
}
