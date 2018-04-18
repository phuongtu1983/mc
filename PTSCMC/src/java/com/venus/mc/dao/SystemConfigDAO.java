package com.venus.mc.dao;

import com.venus.mc.bean.SystemConfigBean;
import com.venus.mc.database.DBUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Mai Vinh Loc
 */
public class SystemConfigDAO extends BasicDAO {

    public SystemConfigDAO() {
    }

    public ArrayList getConfigs() throws Exception {

        ResultSet rs = null;

        String sql = "select * from system_config";

        ArrayList configList = new ArrayList();
        try {



            rs = DBUtil.executeQuery(sql);
            SystemConfigBean config = null;
            while (rs.next()) {
                config = new SystemConfigBean();
                config.setId(rs.getInt("id"));
                config.setType(rs.getInt("type"));
                config.setValue(rs.getString("value"));
                configList.add(config);
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
        return configList;
    }

    public SystemConfigBean getConfig(int type) throws Exception {

        ResultSet rs = null;

        String sql = "select * from system_config where type=" + type;


        try {



            rs = DBUtil.executeQuery(sql);
            SystemConfigBean config = null;
            while (rs.next()) {
                config = new SystemConfigBean();
                config.setId(rs.getInt("id"));
                config.setType(rs.getInt("type"));
                config.setValue(rs.getString("value"));

                return config;
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

    public int insertConfig(SystemConfigBean bean) throws Exception {
        if (bean == null) {
            return 0;
        }
        int result = 0;
        try {
            String sql = "";
            sql = "insert into system_config(type,value) values (" + bean.getType() + ",'" + bean.getValue() + "')";
            result = DBUtil.executeInsert(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return result;
    }

    public void updateConfig(SystemConfigBean bean) throws Exception {
        if (bean == null) {
            return;
        }
        try {
            String sql = "update system_config set value='" + bean.getValue() + "' where type=" + bean.getType();
            DBUtil.executeUpdate(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }
}
