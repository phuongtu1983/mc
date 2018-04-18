package com.venus.mc.dao;

import com.venus.core.util.DateUtil;
import com.venus.core.util.GenericValidator;
import com.venus.core.util.StringUtil;
import com.venus.mc.bean.AssetBean;
import com.venus.mc.database.DBUtil;
import com.venus.mc.util.MCUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Mai Vinh Loc
 */
public class AssetDAO extends BasicDAO {

    public AssetDAO() {
    }

    public ArrayList getAssets() throws Exception {
        ResultSet rs = null;
        String sql = "SELECT e.* FROM asset AS e ORDER BY e.ass_id desc";

        ArrayList assetList = new ArrayList();
        try {
            rs = DBUtil.executeQuery(sql);
            AssetBean asset = null;
            while (rs.next()) {
                asset = new AssetBean();
                asset.setAssId(rs.getInt("ass_id"));
                asset.setAssetName(rs.getString("asset_name"));
                asset.setDecisionNumber(rs.getString("decision_number"));
                asset.setManageCode(rs.getString("manage_code"));
                asset.setRequestNumber(rs.getString("request_number"));
                asset.setContractNumber(rs.getString("contract_number"));
                asset.setTestNumber(rs.getString("test_number"));
                asset.setUnit(rs.getString("unit"));
                asset.setUsedCode(rs.getString("used_code"));
                asset.setColorCode(rs.getString("color_code"));
                asset.setSpecCerts(rs.getString("spec_certs"));
                asset.setFuelLevel(rs.getString("fuel_level"));
                asset.setStatus(rs.getInt("status"));
                asset.setAppearedDate(DateUtil.formatDate(rs.getDate("appeared_date"), "dd/MM/yyyy"));
                asset.setUsedDate(DateUtil.formatDate(rs.getDate("used_date"), "dd/MM/yyyy"));
                asset.setComment(rs.getString("comment"));
                if (rs.getInt("status") == AssetBean.S1) {
                    asset.setStatusName(MCUtil.getBundleString("message.asset.status1"));
                }
                if (rs.getInt("status") == AssetBean.S2) {
                    asset.setStatusName(MCUtil.getBundleString("message.asset.status2"));
                }
                if (rs.getInt("status") == AssetBean.S3) {
                    asset.setStatusName(MCUtil.getBundleString("message.asset.status3"));
                }
                if (rs.getInt("status") == AssetBean.S4) {
                    asset.setStatusName(MCUtil.getBundleString("message.asset.status4"));
                }

                assetList.add(asset);
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
        return assetList;
    }

    public AssetBean getAsset(String assetid) throws Exception {
        ResultSet rs = null;
        String sql = "SELECT e.* FROM asset AS e where e.ass_id=" + assetid;

        try {
            rs = DBUtil.executeQuery(sql);
            while (rs.next()) {
                AssetBean asset = new AssetBean();
                asset.setAssId(rs.getInt("ass_id"));
                asset.setAssetName(rs.getString("asset_name"));
                asset.setDecisionNumber(rs.getString("decision_number"));
                asset.setManageCode(rs.getString("manage_code"));
                asset.setRequestNumber(rs.getString("request_number"));
                asset.setContractNumber(rs.getString("contract_number"));
                asset.setTestNumber(rs.getString("test_number"));
                asset.setUnit(rs.getString("unit"));
                asset.setUsedCode(rs.getString("used_code"));
                asset.setColorCode(rs.getString("color_code"));
                asset.setSpecCerts(rs.getString("spec_certs"));
                asset.setFuelLevel(rs.getString("fuel_level"));
                asset.setStatus(rs.getInt("status"));
                asset.setAppearedDate(DateUtil.formatDate(rs.getDate("appeared_date"), "dd/MM/yyyy"));
                asset.setUsedDate(DateUtil.formatDate(rs.getDate("used_date"), "dd/MM/yyyy"));
                asset.setComment(rs.getString("comment"));
                if (rs.getInt("status") == AssetBean.S1) {
                    asset.setStatusName(MCUtil.getBundleString("message.asset.status1"));
                }
                if (rs.getInt("status") == AssetBean.S2) {
                    asset.setStatusName(MCUtil.getBundleString("message.asset.status2"));
                }
                if (rs.getInt("status") == AssetBean.S3) {
                    asset.setStatusName(MCUtil.getBundleString("message.asset.status3"));
                }
                if (rs.getInt("status") == AssetBean.S4) {
                    asset.setStatusName(MCUtil.getBundleString("message.asset.status4"));
                }
                return asset;
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

    public int insertAsset(AssetBean bean) throws Exception {
        if (bean == null) {
            return 0;
        }
        int result = 0;
        try {
            String sql = "";
            String appearedDate = "null";
            String usedDate = "null";
            if (!GenericValidator.isBlankOrNull(bean.getAppearedDate())) {
                appearedDate = "STR_TO_DATE('" + bean.getAppearedDate() + "','%d/%m/%Y')";
            }

            if (!GenericValidator.isBlankOrNull(bean.getUsedDate())) {
                usedDate = "STR_TO_DATE('" + bean.getUsedDate() + "','%d/%m/%Y')";
            }
            sql = "insert into asset(decision_number,manage_code,asset_name,unit,request_number,contract_number,test_number,used_code,color_code,spec_certs"
                    + ",fuel_level,status,appeared_date,used_date,comment)"
                    + " values ('" + bean.getDecisionNumber() + "','" + bean.getManageCode() + "','" + bean.getAssetName() + "','" + bean.getUnit() + "','" + bean.getRequestNumber() + "','"
                    + bean.getContractNumber() + "','" + bean.getTestNumber() + "','" + bean.getUsedCode() + "','" + bean.getColorCode() + "','"
                    + bean.getSpecCerts() + "','" + bean.getFuelLevel() + "'," + bean.getStatus() + "," + appearedDate + "," + usedDate + ",'" + bean.getComment() + "')";

            ////System.out.println("sql ====" + sql);
            result = DBUtil.executeInsert(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return result;
    }

    public void updateAsset(AssetBean bean) throws Exception {
        if (bean == null) {
            return;
        }
        int result = 0;
        try {
            String sql = "update asset set "
                    + " decision_number='" + bean.getDecisionNumber() + "'"
                    + ", manage_code='" + bean.getManageCode() + "'"
                    + ", asset_name='" + bean.getAssetName() + "'"
                    + ", request_number='" + bean.getRequestNumber() + "'"
                    + ", contract_number='" + bean.getContractNumber() + "'"
                    + ", test_number='" + bean.getTestNumber() + "'"
                    + ", unit='" + bean.getUnit() + "'"
                    + ", used_code='" + bean.getUsedCode() + "'"
                    + ", color_code='" + bean.getColorCode() + "'"
                    + ", spec_certs='" + bean.getSpecCerts() + "'"
                    + ", fuel_level='" + bean.getFuelLevel() + "'"
                    + ", status=" + bean.getStatus() + ""
                    + ", appeared_date=STR_TO_DATE('" + bean.getAppearedDate() + "','%d/%m/%Y')"
                    + ", used_date=STR_TO_DATE('" + bean.getUsedDate() + "','%d/%m/%Y')"
                    + ", comment='" + bean.getComment() + "'"
                    + " where ass_id=" + bean.getAssId();

            ////System.out.println("sql=" + sql);
            result = DBUtil.executeUpdate(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public int deleteAsset(String assetid) throws Exception {
        int result = 0;
        try {
            String sql = "delete from asset " + " where ass_id=" + assetid;
            result = DBUtil.executeUpdate(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return result;
    }

    public ArrayList searchSimpleAsset(int fieldid, String strFieldvalue) throws Exception {
        String strFieldname = "";
        switch (fieldid) {
            case 1:
                strFieldname = "decision_number";
                break;
            case 2:
                strFieldname = "asset_name";
                break;
            case 3:
                strFieldname = "request_number";
                break;
            case 4:
                strFieldname = "contract_number";
                break;
        }
        ResultSet rs = null;

        String sql = "SELECT e.* FROM asset AS e where 1 ";
        if ((fieldid > 0) && (!StringUtil.isBlankOrNull(strFieldvalue))) {
            sql = sql + " AND " + strFieldname + " LIKE '%" + strFieldvalue + "%'";
        }
        sql = sql + " order by ass_id desc";

        ArrayList assetList = new ArrayList();
        try {
            rs = DBUtil.executeQuery(sql);
            AssetBean asset = null;
            while (rs.next()) {
                asset = new AssetBean();
                asset.setAssId(rs.getInt("ass_id"));
                asset.setAssetName(rs.getString("asset_name"));
                asset.setDecisionNumber(rs.getString("decision_number"));
                asset.setManageCode(rs.getString("manage_code"));
                asset.setRequestNumber(rs.getString("request_number"));
                asset.setContractNumber(rs.getString("contract_number"));
                asset.setTestNumber(rs.getString("test_number"));
                asset.setUnit(rs.getString("unit"));
                asset.setUsedCode(rs.getString("used_code"));
                asset.setColorCode(rs.getString("color_code"));
                asset.setSpecCerts(rs.getString("spec_certs"));
                asset.setFuelLevel(rs.getString("fuel_level"));
                asset.setStatus(rs.getInt("status"));
                asset.setAppearedDate(DateUtil.formatDate(rs.getDate("appeared_date"), "dd/MM/yyyy"));
                asset.setUsedDate(DateUtil.formatDate(rs.getDate("used_date"), "dd/MM/yyyy"));
                asset.setComment(rs.getString("comment"));
                if (rs.getInt("status") == AssetBean.S1) {
                    asset.setStatusName(MCUtil.getBundleString("message.asset.status1"));
                }
                if (rs.getInt("status") == AssetBean.S2) {
                    asset.setStatusName(MCUtil.getBundleString("message.asset.status2"));
                }
                if (rs.getInt("status") == AssetBean.S3) {
                    asset.setStatusName(MCUtil.getBundleString("message.asset.status3"));
                }
                if (rs.getInt("status") == AssetBean.S4) {
                    asset.setStatusName(MCUtil.getBundleString("message.asset.status4"));
                }
                assetList.add(asset);
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
        return assetList;
    }

    public ArrayList searchAdvAsset(AssetBean bean) throws Exception {
        ResultSet rs = null;

        String sql = "SELECT e.* FROM asset AS e where 1 ";

        if (bean.getStatus() > 0) {
            sql = sql + " AND status = " + bean.getStatus() + "";
        }

        if (!StringUtil.isBlankOrNull(bean.getDecisionNumber())) {
            sql = sql + " AND decision_number LIKE '%" + bean.getDecisionNumber() + "%'";
        }

        if (!StringUtil.isBlankOrNull(bean.getManageCode())) {
            sql = sql + " AND manage_code LIKE '%" + bean.getManageCode() + "%'";
        }

        if (!StringUtil.isBlankOrNull(bean.getAssetName())) {
            sql = sql + " AND asset_name LIKE '%" + bean.getAssetName() + "%'";
        }

        if (!StringUtil.isBlankOrNull(bean.getRequestNumber())) {
            sql = sql + " AND request_number LIKE '%" + bean.getRequestNumber() + "%'";
        }

        if (!StringUtil.isBlankOrNull(bean.getContractNumber())) {
            sql = sql + " AND contract_number LIKE '%" + bean.getContractNumber() + "%'";
        }

        if (!StringUtil.isBlankOrNull(bean.getTestNumber())) {
            sql = sql + " AND test_number LIKE '%" + bean.getTestNumber() + "%'";
        }

        if (!StringUtil.isBlankOrNull(bean.getUnit())) {
            sql = sql + " AND unit LIKE '%" + bean.getUnit() + "%'";
        }

        if (!StringUtil.isBlankOrNull(bean.getUsedCode())) {
            sql = sql + " AND used_code LIKE '%" + bean.getUsedCode() + "%'";
        }

        if (!StringUtil.isBlankOrNull(bean.getColorCode())) {
            sql = sql + " AND color_code LIKE '%" + bean.getColorCode() + "%'";
        }

        if (!StringUtil.isBlankOrNull(bean.getSpecCerts())) {
            sql = sql + " AND spec_certs LIKE '%" + bean.getSpecCerts() + "%'";
        }

        if (!StringUtil.isBlankOrNull(bean.getFuelLevel())) {
            sql = sql + " AND fuel_level LIKE '%" + bean.getFuelLevel() + "%'";
        }

        if (!StringUtil.isBlankOrNull(bean.getAppearedDate())) {
            sql = sql + " AND appeared_date LIKE STR_TO_DATE('" + bean.getAppearedDate() + "','%d/%m/%Y %H')";
        }

        if (!StringUtil.isBlankOrNull(bean.getUsedDate())) {
            sql = sql + " AND used_date LIKE STR_TO_DATE('" + bean.getUsedDate() + "','%d/%m/%Y %H')";
        }

        if (!StringUtil.isBlankOrNull(bean.getComment())) {
            sql = sql + " AND comment LIKE '%" + bean.getComment() + "%'";
        }
        sql = sql + " order by ass_id desc";

        ArrayList assetList = new ArrayList();
        try {
            rs = DBUtil.executeQuery(sql);
            AssetBean asset = null;
            while (rs.next()) {
                asset = new AssetBean();
                asset.setAssId(rs.getInt("ass_id"));
                asset.setAssetName(rs.getString("asset_name"));
                asset.setDecisionNumber(rs.getString("decision_number"));
                asset.setManageCode(rs.getString("manage_code"));
                asset.setRequestNumber(rs.getString("request_number"));
                asset.setContractNumber(rs.getString("contract_number"));
                asset.setTestNumber(rs.getString("test_number"));
                asset.setUnit(rs.getString("unit"));
                asset.setUsedCode(rs.getString("used_code"));
                asset.setColorCode(rs.getString("color_code"));
                asset.setSpecCerts(rs.getString("spec_certs"));
                asset.setFuelLevel(rs.getString("fuel_level"));
                asset.setStatus(rs.getInt("status"));
                asset.setAppearedDate(DateUtil.formatDate(rs.getDate("appeared_date"), "dd/MM/yyyy"));
                asset.setUsedDate(DateUtil.formatDate(rs.getDate("used_date"), "dd/MM/yyyy"));
                asset.setComment(rs.getString("comment"));
                if (rs.getInt("status") == AssetBean.S1) {
                    asset.setStatusName(MCUtil.getBundleString("message.asset.status1"));
                }
                if (rs.getInt("status") == AssetBean.S2) {
                    asset.setStatusName(MCUtil.getBundleString("message.asset.status2"));
                }
                if (rs.getInt("status") == AssetBean.S3) {
                    asset.setStatusName(MCUtil.getBundleString("message.asset.status3"));
                }
                if (rs.getInt("status") == AssetBean.S4) {
                    asset.setStatusName(MCUtil.getBundleString("message.asset.status4"));
                }
                assetList.add(asset);
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
        return assetList;
    }

    public boolean checkDecisionNumber(int id, String value) throws SQLException {
        ResultSet rs = null;
        try {
            rs = DBUtil.executeQuery("SELECT * FROM asset WHERE ass_id <> " + id + " AND decision_number = '" + value + "'");
            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            throw new SQLException(ex.getMessage());
        } finally {
            if (rs != null) {
                DBUtil.closeConnection(rs);
            }
            
        }
    }

    public boolean checkUsedCode(int id, String value) throws SQLException {
        ResultSet rs = null;
        try {
            rs = DBUtil.executeQuery("SELECT * FROM asset WHERE ass_id <> " + id + " AND used_code = '" + value + "'");
            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            throw new SQLException(ex.getMessage());
        } finally {
            if (rs != null) {
                DBUtil.closeConnection(rs);
            }
            
        }
    }
}
