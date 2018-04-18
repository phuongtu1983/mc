package com.venus.mc.dao;

import com.venus.mc.bean.ParameterBean;
import com.venus.mc.database.DBUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Mai Vinh Loc
 */
public class ParameterDAO extends BasicDAO {

    public ParameterDAO() {
    }

    public ArrayList getParameters() throws Exception {
        ResultSet rs = null;
        String sql = "SELECT * FROM parameter";

        ArrayList list = new ArrayList();
        try {
            rs = DBUtil.executeQuery(sql);
            ParameterBean param = null;
            while (rs.next()) {
                param = new ParameterBean();
                param.setName(rs.getString("name"));
                param.setValue(rs.getString("value"));
                list.add(param);
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
        return list;
    }

    public ParameterBean getParameter(String name) throws Exception {
        ResultSet rs = null;
        String sql = "SELECT * FROM parameter where name='" + name + "'";

        try {
            rs = DBUtil.executeQuery(sql);
            while (rs.next()) {
                ParameterBean param = new ParameterBean();
                param.setName(rs.getString("name"));
                param.setValue(rs.getString("value"));
                return param;
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
        return null;
    }

    public int insertParameter(ParameterBean bean) throws Exception {
        if (bean == null) {
            return 0;
        }
        int result = 0;
        try {
            String sql = "";

            sql = "insert into parameter(name,value)"
                    + " values ('" + bean.getName() + "','" + bean.getValue() + "')";

            ////System.out.println("sql ====" + sql);
            result = DBUtil.executeInsert(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return result;
    }

    public void updateParameter(ParameterBean bean) throws Exception {
        if (bean == null) {
            return;
        }
        try {
            String sql = "update parameter set "
                    + " value='" + bean.getValue() + "'"
                    + " where name='" + bean.getName() + "'";
            ////System.out.println("sql=" + sql);
            DBUtil.executeUpdate(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public int deleteParameter(String name) throws Exception {
        int result = 0;
        try {
            String sql = "delete from parameter where name='" + name + "'";
            result = DBUtil.executeUpdate(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return result;
    }
}
