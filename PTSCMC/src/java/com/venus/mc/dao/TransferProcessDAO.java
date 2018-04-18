package com.venus.mc.dao;

import com.venus.core.util.DateUtil;
import com.venus.core.util.StringUtil;
import com.venus.mc.bean.TransferProcessBean;
import com.venus.mc.database.DBUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Mai Vinh Loc
 */
public class TransferProcessDAO extends BasicDAO {

    public TransferProcessDAO() {
    }

    public ArrayList getTransferProcesss(int equId, int assId) throws Exception {

        ResultSet rs = null;

        String sql = "";
        if (assId == 0) {
            sql = "SELECT t.*, o.name AS orgName FROM transfer_process AS t LEFT JOIN organization AS o ON o.org_id = t.receive_org WHERE equ_id = " + equId + "  ORDER BY tp_id desc";
        } else {
            sql = "SELECT t.*, o.name AS orgName FROM transfer_process AS t LEFT JOIN organization AS o ON o.org_id = t.receive_org WHERE ass_id = " + assId + "  ORDER BY tp_id desc";
        }


        ArrayList transferprocessList = new ArrayList();
        try {



            rs = DBUtil.executeQuery(sql);
            TransferProcessBean transferprocess = null;
            while (rs.next()) {
                transferprocess = new TransferProcessBean();
                transferprocess.setTpId(rs.getInt("tp_id"));
                transferprocess.setEquId(rs.getInt("equ_id"));
                transferprocess.setAssId(rs.getInt("ass_id"));
                transferprocess.setReceiveOrg(rs.getInt("receive_org"));
                transferprocess.setReceiveEmp(rs.getInt("receive_emp"));
                transferprocess.setProject(rs.getInt("project"));
                transferprocess.setComment(rs.getString("comment"));
                transferprocess.setReceiveDate(DateUtil.formatDate(rs.getDate("receive_date"), "dd/MM/yyyy"));
                transferprocess.setReturnDate(DateUtil.formatDate(rs.getDate("return_date"), "dd/MM/yyyy"));
                transferprocess.setOrgName(rs.getString("orgName"));
                transferprocessList.add(transferprocess);
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
        return transferprocessList;
    }

    public TransferProcessBean getTransferProcess(int tpId, int equId, int assId) throws Exception {

        ResultSet rs = null;

        String sql = "";
        if (assId == 0) {
            sql = "SELECT * FROM transfer_process WHERE equ_id = " + equId + " AND tp_id=" + tpId;
        } else {
            sql = "SELECT * FROM transfer_process WHERE ass_id = " + assId + " AND tp_id=" + tpId;
        }



        try {


            ////System.out.println("sql=" + sql);

            rs = DBUtil.executeQuery(sql);
            while (rs.next()) {
                TransferProcessBean transferprocess = new TransferProcessBean();
                transferprocess.setTpId(rs.getInt("tp_id"));
                transferprocess.setEquId(rs.getInt("equ_id"));
                transferprocess.setAssId(rs.getInt("ass_id"));
                transferprocess.setReceiveOrg(rs.getInt("receive_org"));
                transferprocess.setReceiveEmp(rs.getInt("receive_emp"));
                transferprocess.setProject(rs.getInt("project"));
                transferprocess.setComment(rs.getString("comment"));
                transferprocess.setReceiveDate(DateUtil.formatDate(rs.getDate("receive_date"), "dd/MM/yyyy"));
                transferprocess.setReturnDate(DateUtil.formatDate(rs.getDate("return_date"), "dd/MM/yyyy"));

                return transferprocess;
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

    public void insertTransferProcess(TransferProcessBean bean) throws Exception {
        if (bean == null) {
            return;
        }
        int result = 0;
        String equId = null;
        String assId = null;
        String orgId = null;
        if (bean.getAssId() > 0) {
            assId = bean.getAssId() + "";
        }
        if (bean.getEquId() > 0) {
            equId = bean.getEquId() + "";
        }

        try {
            String sql = "";
            sql = "insert into transfer_process(equ_id,ass_id,receive_date,return_date,receive_org,receive_emp,project,comment)"
                    + " values (" + equId + "," + assId + ",STR_TO_DATE('" + bean.getReceiveDate() + "','%d/%m/%Y'),STR_TO_DATE('" + bean.getReturnDate() + "','%d/%m/%Y')," + bean.getReceiveOrg() + "," + bean.getReceiveEmp() + "," + bean.getProject() + ",'" + bean.getComment() + "')";

            ////System.out.println("sql ====" + sql);
            result = DBUtil.executeInsert(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public void updateTransferProcess(TransferProcessBean bean) throws Exception {
        if (bean == null) {
            return;
        }
        int result = 0;
        String equId = null;
        String assId = null;
        String orgId = null;

        if (bean.getAssId() > 0) {
            assId = bean.getAssId() + "";
        }
        if (bean.getEquId() > 0) {
            equId = bean.getEquId() + "";
        }

        try {
            String sql = "update transfer_process set "
                    + " equ_id=" + equId + ""
                    + ", ass_id=" + assId + ""
                    + ", receive_org='" + bean.getReceiveOrg() + "'"
                    + ", receive_emp=" + bean.getReceiveEmp() + ""
                    + ", project=" + bean.getProject() + ""
                    + ", comment='" + bean.getComment() + "'"
                    + ", receive_date=STR_TO_DATE('" + bean.getReceiveDate() + "','%d/%m/%Y')"
                    + ", return_date=STR_TO_DATE('" + bean.getReturnDate() + "','%d/%m/%Y')"
                    + " where tp_id=" + bean.getTpId();

            ////System.out.println("sql=" + sql);
            result = DBUtil.executeUpdate(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public int deleteTransferProcess(String tpId) throws Exception {
        int result = 0;
        try {
            String sql = "delete from transfer_process where tp_id=" + tpId;
            result = DBUtil.executeUpdate(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return result;
    }

    public ArrayList searchSimpleTransferProcess(int fieldid, String strFieldvalue, int equId, int assId) throws Exception {
        String strFieldname = "";
        switch (fieldid) {
            case 1:
                strFieldname = "comment";
                break;
        }
        ResultSet rs = null;

        String sql = "";
        if (assId == 0) {
            sql = "SELECT t.*, o.name AS orgName FROM transfer_process AS t LEFT JOIN organization AS o ON o.org_id = t.receive_org WHERE equ_id = " + equId + " ";
        } else {
            sql = "SELECT t.*, o.name AS orgName FROM transfer_process AS t LEFT JOIN organization AS o ON o.org_id = t.receive_org WHERE ass_id = " + assId + " ";
        }

        if ((fieldid > 0) && (!StringUtil.isBlankOrNull(strFieldvalue))) {
            sql = sql + " AND " + strFieldname + " LIKE '%" + strFieldvalue + "%'";
        }
        sql = sql + " order by tp_id desc";

        ArrayList transferprocessList = new ArrayList();
        try {


            ////System.out.println("sql=" + sql);

            rs = DBUtil.executeQuery(sql);
            TransferProcessBean transferprocess = null;
            while (rs.next()) {
                transferprocess = new TransferProcessBean();
                transferprocess.setTpId(rs.getInt("tp_id"));
                transferprocess.setEquId(rs.getInt("equ_id"));
                transferprocess.setAssId(rs.getInt("ass_id"));
                transferprocess.setReceiveOrg(rs.getInt("receive_org"));
                transferprocess.setReceiveEmp(rs.getInt("receive_emp"));
                transferprocess.setProject(rs.getInt("project"));
                transferprocess.setComment(rs.getString("comment"));
                transferprocess.setReceiveDate(DateUtil.formatDate(rs.getDate("receive_date"), "dd/MM/yyyy"));
                transferprocess.setReturnDate(DateUtil.formatDate(rs.getDate("return_date"), "dd/MM/yyyy"));
                transferprocess.setOrgName(rs.getString("orgName"));
                transferprocessList.add(transferprocess);
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
        return transferprocessList;

    }

    public ArrayList searchAdvTransferProcess(TransferProcessBean bean) throws Exception {
        ResultSet rs = null;

        String sql = "";
        if (bean.getAssId() == 0) {
            sql = "SELECT t.*, o.name AS orgName FROM transfer_process AS t LEFT JOIN organization AS o ON o.org_id = t.receive_org WHERE equ_id = " + bean.getEquId() + " ";
        } else {
            sql = "SELECT t.*, o.name AS orgName FROM transfer_process AS t LEFT JOIN organization AS o ON o.org_id = t.receive_org WHERE ass_id = " + bean.getAssId() + " ";
        }

        if (bean.getReceiveOrg() > 0) {
            sql = sql + " AND receive_org = " + bean.getReceiveOrg() + "";
        }

        if (bean.getReceiveEmp() > 0) {
            sql = sql + " AND receive_emp = " + bean.getReceiveEmp() + "";
        }

        if (bean.getProject() > 0) {
            sql = sql + " AND project = " + bean.getProject() + "";
        }

        if (!StringUtil.isBlankOrNull(bean.getComment())) {
            sql = sql + " AND comment LIKE '%" + bean.getComment() + "%'";
        }

        if (!StringUtil.isBlankOrNull(bean.getReceiveDate())) {
            sql = sql + " AND receive_date LIKE STR_TO_DATE('" + bean.getReceiveDate() + "','%d/%m/%Y %H')";
        }

        if (!StringUtil.isBlankOrNull(bean.getReturnDate())) {
            sql = sql + " AND return_date LIKE STR_TO_DATE('" + bean.getReturnDate() + "','%d/%m/%Y %H')";
        }
        sql = sql + " order by tp_id desc";

        ArrayList transferprocessList = new ArrayList();
        try {


            ////System.out.println("sql=" + sql);

            rs = DBUtil.executeQuery(sql);
            TransferProcessBean transferprocess = null;

            while (rs.next()) {
                transferprocess = new TransferProcessBean();
                transferprocess.setTpId(rs.getInt("tp_id"));
                transferprocess.setEquId(rs.getInt("equ_id"));
                transferprocess.setAssId(rs.getInt("ass_id"));
                transferprocess.setReceiveOrg(rs.getInt("receive_org"));
                transferprocess.setReceiveEmp(rs.getInt("receive_emp"));
                transferprocess.setProject(rs.getInt("project"));
                transferprocess.setComment(rs.getString("comment"));
                transferprocess.setReceiveDate(DateUtil.formatDate(rs.getDate("receive_date"), "dd/MM/yyyy"));
                transferprocess.setReturnDate(DateUtil.formatDate(rs.getDate("return_date"), "dd/MM/yyyy"));
                transferprocess.setOrgName(rs.getString("orgName"));
                transferprocessList.add(transferprocess);
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
        return transferprocessList;
    }
    /*
    public boolean checkDecisionNumber(int id, String value) throws SQLException {
    
    try {
    ResultSet rs = null;
    
    
    //     System.out.println("executeQuery: " + sql);
    rs = DBUtil.executeQuery("SELECT * FROM transfer_process WHERE tp_id <> " + id + " AND decision_number = '" + value + "'");
    if (rs.next()) {
    return true;
    } else {
    return false;
    }
    } catch (SQLException ex) {
    throw new SQLException(ex.getMessage());
    } finally {
    try {
    
    
    } catch (SQLException e) {
    throw new SQLException(e.getMessage());
    }
    }
    }
    
    public boolean checkUsedCode(int id, String value) throws SQLException {
    
    try {
    ResultSet rs = null;
    
    
    //     System.out.println("executeQuery: " + sql);
    rs = DBUtil.executeQuery("SELECT * FROM transfer_process WHERE tp_id <> " + id + " AND used_code = '" + value + "'");
    if (rs.next()) {
    return true;
    } else {
    return false;
    }
    } catch (SQLException ex) {
    throw new SQLException(ex.getMessage());
    } finally {
    try {
    
    
    } catch (SQLException e) {
    throw new SQLException(e.getMessage());
    }
    }
    }
     */
}
