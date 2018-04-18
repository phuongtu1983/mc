package com.venus.mc.dao;

import com.venus.core.util.StringUtil;
import com.venus.mc.bean.GroupMaterialBean;
import com.venus.mc.database.DBUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Mai Vinh Loc
 */
public class GroupMaterialDAO extends BasicDAO {

    public GroupMaterialDAO() {
    }

    public ArrayList getGroupMaterials()
            throws Exception {

        ResultSet rs = null;

        String sql = "select * from specification2 order by note asc";
//        String sql = "select * from group_material order by name asc";

        ArrayList unitList = new ArrayList();
        try {



            rs = DBUtil.executeQuery(sql);
            GroupMaterialBean unit = null;
            while (rs.next()) {
                unit = new GroupMaterialBean();
                unit.setGroId(rs.getInt("spe2_id"));
//                unit.setName(rs.getString("name"));
                unit.setName(StringUtil.getString(rs.getString("note")));

                unitList.add(unit);
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
        return unitList;
    }

    public GroupMaterialBean getGroupMaterial(int groId) throws Exception {

        ResultSet rs = null;

        String sql = "Select * From specification2 Where spe2_id = " + groId;
//        String sql = "Select * From group_material Where gro_id = " + groId;


        try {


            ////System.out.println("sql=" + sql);

            rs = DBUtil.executeQuery(sql);
            while (rs.next()) {
                GroupMaterialBean unit = new GroupMaterialBean();
                unit.setGroId(rs.getInt("spe2_id"));
//                unit.setName(rs.getString("name"));
                unit.setName(StringUtil.getString(rs.getString("note")));

                return unit;
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

    public GroupMaterialBean getGroupMaterialByName(String name) throws Exception {
        ResultSet rs = null;

        String sql = "Select spe2_id From specification2 Where note = '" + name + "'";
//        String sql = "Select gro_id From group_material Where name = '" + name + "'";


        try {


            ////System.out.println("sql=" + sql);

            rs = DBUtil.executeQuery(sql);
            while (rs.next()) {
                GroupMaterialBean unit = new GroupMaterialBean();
                unit.setGroId(rs.getInt("spe2_id"));

                return unit;
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

    //phuongtu sua
    public int insertGroupMaterial(GroupMaterialBean bean) throws Exception {
        if (bean == null) {
            return 0;
        }
        int result = 0;
        try {
            String sql = "";
            sql = "insert into group_material(note,name)"
                    + " values ('" + bean.getNote() + "','" + bean.getName() + "')";

            result = DBUtil.executeInsert(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return result;
    }

    public void updateGroupMaterial(GroupMaterialBean bean) throws Exception {
        if (bean == null) {
            return;
        }
        try {
            String sql = "update group_material set "
                    + " note='" + bean.getNote() + "'"
                    + ", name='" + bean.getName() + "'"
                    + " where gro_id=" + bean.getGroId();
            ////System.out.println("sql=" + sql);
            DBUtil.executeUpdate(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public int deleteGroupMaterial(int groId) throws Exception {
        int result = 0;
        try {
            String sql = "Delete From group_material Where gro_id=" + groId;
            result = DBUtil.executeUpdate(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return result;
    }

    public ArrayList searchSimpleGroupMaterial(int fieldid, String strFieldvalue) throws Exception {
        String strFieldname = "";
        switch (fieldid) {
            case 1:
                strFieldname = "note";
                break;
        }
        ResultSet rs = null;

        String sql = "Select spe2_id, note From specification2 Where 1 ";
//        String sql = "Select gro_id, note, name From group_material Where 1 ";
        if ((fieldid > 0) && (!StringUtil.isBlankOrNull(strFieldvalue))) {
            sql = sql + "AND " + strFieldname + " LIKE '%" + strFieldvalue + "%'";
        }
        sql = sql + " Order by spe2_id desc";

        ArrayList unitList = new ArrayList();
        try {


            ////System.out.println("sql=" + sql);

            rs = DBUtil.executeQuery(sql);
            GroupMaterialBean unit = null;
            while (rs.next()) {
                unit = new GroupMaterialBean();
                unit.setGroId(rs.getInt("spe2_id"));
//                unit.setName(rs.getString("name"));
                unit.setName(StringUtil.getString(rs.getString("note")));
                unitList.add(unit);
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
        return unitList;

    }

    public ArrayList searchAdvGroupMaterial(GroupMaterialBean bean) throws Exception {
        ResultSet rs = null;

        String sql = "select spe2_id, note from specification2 where 1 ";
//        String sql = "select gro_id, note, name from group_material where 1 ";

        if (!StringUtil.isBlankOrNull(bean.getNote())) {
            sql = sql + " AND note LIKE '%" + bean.getNote() + "%'";
        }

        if (!StringUtil.isBlankOrNull(bean.getName())) {
            sql = sql + " AND note LIKE '%" + bean.getName() + "%'";
        }

        sql = sql + " order by spe2_id desc";

        ArrayList unitList = new ArrayList();
        try {


            ////System.out.println("sql=" + sql);

            rs = DBUtil.executeQuery(sql);
            GroupMaterialBean unit = null;

            while (rs.next()) {
                unit = new GroupMaterialBean();
                unit.setGroId(rs.getInt("spe2_id"));
//                unit.setName(rs.getString("name"));
                unit.setName(StringUtil.getString(rs.getString("note")));
                unitList.add(unit);
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
        return unitList;
    }
}
