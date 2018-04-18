package com.venus.mc.dao;

import com.venus.core.util.DateUtil;
import com.venus.core.util.StringUtil;
import com.venus.mc.bean.MaterialBean;
import com.venus.mc.bean.RequestBean;
import com.venus.mc.bean.EdnBean;
import com.venus.mc.bean.EdnDetailBean;
import com.venus.mc.bean.EmrirDetailBean;
import com.venus.mc.bean.EosDDetailBean;
import com.venus.mc.database.DBUtil;
import com.venus.mc.process.purchasing.edeliverynotice.EdnFormBean;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.apache.struts.util.LabelValueBean;

/**
 * @author Mai Vinh Loc
 */
public class EdnDAO extends BasicDAO {

    public EdnDAO() {
    }

    public ArrayList getDns()
            throws Exception {

        ResultSet rs = null;

        String sql = "SELECT n.* FROM edelivery_notice AS n WHERE 1 ORDER BY edn_number asc";

        ArrayList dnList = new ArrayList();
        try {



            rs = DBUtil.executeQuery(sql);
            EdnFormBean dn = null;
            while (rs.next()) {
                dn = new EdnFormBean();
                dn.setCreatedDate(DateUtil.formatDate(rs.getDate("created_date"), "dd/MM/yyyy"));
                dn.setDnId(rs.getInt("edn_id"));
                dn.setExpectedDate(DateUtil.formatDate(rs.getDate("expected_date"), "dd/MM/yyyy"));
                dn.setDnNumber(rs.getString("edn_number"));
                dn.setContractNumber(rs.getString("econ_number"));
                dn.setDeliveryPlace(rs.getString("delivery_place"));
                dn.setDeliveryPresenter(rs.getString("delivery_presenter"));
                dn.setCreatedOrg(rs.getInt("created_org"));
                dn.setProId(rs.getInt("pro_id"));
                dnList.add(dn);
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
        return dnList;
    }

    public ArrayList getRequestMsv(int stoId)
            throws Exception {

        ResultSet rs = null;

        String sql = "SELECT r1.request_number,r1.req_id FROM msv_detail AS m LEFT JOIN msv AS m1 ON m.msv_id = m1.msv_id LEFT JOIN request_detail AS r ON m.req_detail_id = r.det_id LEFT JOIN request AS r1 ON r.req_id = r1.req_id WHERE m1.sto_id = " + stoId;

        ArrayList dnList = new ArrayList();
        try {



            rs = DBUtil.executeQuery(sql);
            RequestBean dn = null;
            while (rs.next()) {
                dn = new RequestBean();
                dn.setReqId(rs.getInt("req_id"));
                dn.setRequestNumber(rs.getString("request_number"));

                dnList.add(dn);
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
        return dnList;
    }

    public ArrayList getMaterialContract(int ids)
            throws Exception {

        ResultSet rs = null;

        String sql = "SELECT m.emat_id, m.name_vn AS materialName, u.* FROM ematerial AS m LEFT JOIN unit AS u ON u.uni_id=m.uni_id WHERE m.emat_id in(" + ids + ")";

        ArrayList dnList = new ArrayList();
        try {



            rs = DBUtil.executeQuery(sql);
            MaterialBean dn = null;
            while (rs.next()) {
                dn = new MaterialBean();
                dn.setMatId(rs.getInt("emat_id"));
                dn.setNameVn(StringUtil.decodeString(rs.getString("materialName")));

                dnList.add(dn);
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
        return dnList;
    }

    public ArrayList getMaterialsOfContract(String ids) throws Exception {
        ResultSet rs = null;

        String sql = "SELECT m.*, u.* FROM ematerial AS m LEFT JOIN unit AS u ON u.uni_id=m.uni_id WHERE m.emat_id in(" + ids + ")";


        ArrayList detailList = new ArrayList();
        try {



            rs = DBUtil.executeQuery(sql);
            EdnDetailBean detail = null;
            while (rs.next()) {
                detail = new EdnDetailBean();
                detail.setMatId(rs.getInt("emat_id"));
                //       detail.setContractNumber(rs.getString("econ_number"));
                detail.setMatCode(rs.getString("code"));
                detail.setMatName(StringUtil.decodeString(rs.getString("name_vn")));
                //             detail.setQuantity(rs.getInt("quantity"));
                detail.setUnit(rs.getString("unit_vn"));
                //              detail.setPrice(rs.getDouble("price"));
                detailList.add(detail);
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
        return detailList;
    }

    public ArrayList searchDnMaterial(int fieldid, String strFieldvalue) throws Exception {
        String strFieldname = "";
        switch (fieldid) {
            case 1:
                strFieldname = "m.name_vn";
                break;
            case 2:
                strFieldname = "m.code";
                break;
        }
        String sql = "";

        sql = "SELECT m.code, m.emat_id, m.name_vn AS materialName, u.* FROM ematerial AS m LEFT JOIN unit AS u ON u.uni_id=m.uni_id WHERE 1 ";

        ResultSet rs = null;

        if ((fieldid > 0) && (!StringUtil.isBlankOrNull(strFieldvalue))) {
            sql = sql + " AND " + strFieldname + " LIKE '%" + strFieldvalue + "%'";
        }
        sql = sql + " order by m.emat_id desc";

        ArrayList detailList = new ArrayList();
        try {



            rs = DBUtil.executeQuery(sql);
            EdnDetailBean detail = null;
            while (rs.next()) {
                detail = new EdnDetailBean();
                detail.setMatId(rs.getInt("emat_id"));
                //       detail.setContractNumber(rs.getString("econ_number"));
                detail.setMatCode(rs.getString("code"));
                detail.setMatName(StringUtil.decodeString(rs.getString("materialName")));
                //       detail.setQuantity(rs.getInt("quantity"));
                detail.setUnit(rs.getString("unit_vn"));
                //       detail.setPrice(rs.getDouble("price"));

                detailList.add(detail);
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
        return detailList;

    }
    /*
    public ArrayList searchMaterialInStore(int fieldid, String strFieldvalue, int kind) throws Exception {
    String strFieldname = "";
    switch (fieldid) {
    case 1:
    strFieldname = "m2.code";
    break;
    case 2:
    strFieldname = "m2.name_vn";
    break;
    case 3:
    strFieldname = "t.request_number";
    break;
    case 4:
    strFieldname = "s.name";
    break;
    }
    ResultSet rs = null;
    
    String sql = "";
    if (kind == 0) {
    sql = "SELECT m2.name_vn as materialName, m2.code, u.*,r.econ_number,m.price,m.mat_id,m.quantity  FROM contract_detail AS m LEFT JOIN material AS m2 ON m2.mat_id = m.mat_id LEFT JOIN unit AS u ON u.uni_id=m2.uni_id LEFT JOIN contract AS r ON r.con_id=m.con_id where 1 ";
    } else {
    sql = "SELECT m.*, m2.name_vn as materialName,m2.code, u.*,v.msv_number, s.name,s.sto_id  FROM material_store_request_outside AS m LEFT JOIN material AS m2 ON m2.mat_id = m.mat_id LEFT JOIN unit AS u ON u.uni_id=m2.uni_id LEFT JOIN msv AS v ON v.sto_id=m.sto_id LEFT JOIN store AS s ON s.sto_id=m.sto_id where 1 ";
    }
    if ((fieldid > 0) && (!StringUtil.isBlankOrNull(strFieldvalue))) {
    sql = sql + "AND " + strFieldname + " LIKE '%" + strFieldvalue + "%'";
    }
    sql = sql + " order by m2.mat_id desc";
    
    ArrayList detailList = new ArrayList();
    try {
    
    
    rs = DBUtil.executeQuery(sql);
    EdnDetailBean detail = null;
    while (rs.next()) {
    detail = new EdnDetailBean();
    detail.setMatId(rs.getInt("emat_id"));
    //       detail.setContractNumber(rs.getString("econ_number"));
    detail.setMatCode(rs.getString("code"));
    detail.setMatName(rs.getString("materialName"));
    detail.setQuantity(rs.getInt("quantity"));
    detail.setUnit(rs.getString("unit_vn"));
    detail.setPrice(rs.getDouble("price"));
    detailList.add(detail);
    }
    } catch (SQLException sqle) {
    throw new Exception(sqle.getMessage());
    } catch (Exception ex) {
    throw new Exception(ex.getMessage());
    } finally {
    try {
    
    
    } catch (Exception e) {
    throw new Exception(e.getMessage());
    }
    }
    return detailList;
    }
     */

    public EdnBean getDnByNumber(String edn_number) throws Exception {
        ResultSet rs = null;

        String sql = "Select edn_id From edelivery_notice Where edn_number='" + edn_number + "'";

        try {



            rs = DBUtil.executeQuery(sql);
            while (rs.next()) {
                EdnBean bean = new EdnBean();
                bean.setDnId(rs.getInt("edn_id"));

                return bean;
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

    public EdnBean getDn(int dnId) throws Exception {

        ResultSet rs = null;

        String sql = "SELECT d.*,o.name AS orgName, o1.name AS proName FROM edelivery_notice AS d LEFT JOIN organization AS o ON o.org_id = d.created_org LEFT JOIN organization AS o1 ON o1.org_id = d.pro_id "
                + " where d.edn_id=" + dnId;


        try {


            ////System.out.println("sql=" + sql);

            rs = DBUtil.executeQuery(sql);
            while (rs.next()) {
                EdnBean dn = new EdnBean();
                dn.setCreatedDate(DateUtil.formatDate(rs.getDate("created_date"), "dd/MM/yyyy"));
                dn.setDnId(rs.getInt("edn_id"));
                dn.setExpectedDate(DateUtil.formatDate(rs.getDate("expected_date"), "dd/MM/yyyy"));
                dn.setDnNumber(rs.getString("edn_number"));
                dn.setContractNumber(rs.getString("econ_number"));
                dn.setDeliveryPlace(rs.getString("delivery_place"));
                dn.setDeliveryPresenter(rs.getString("delivery_presenter"));
                dn.setCreatedOrg(rs.getInt("created_org"));
                dn.setOrgName(rs.getString("orgName"));
                dn.setProName(rs.getString("proName"));
                dn.setProId(rs.getInt("pro_id"));

                return dn;
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

    public ArrayList getDnDetails(int edn_id) throws Exception {
        ResultSet rs = null;

        String sql = "SELECT d.*, m.name_vn AS materialName, m.code,u.unit_vn,u.unit_en, r1.econ_number FROM edelivery_notice_detail AS d LEFT JOIN ematerial AS m ON d.emat_id=m.emat_id LEFT JOIN edelivery_notice AS r1 ON r1.edn_id = d.edn_id LEFT JOIN unit AS u ON u.uni_id = m.uni_id "
                + " Where d.edn_id=" + edn_id;

        ArrayList detailList = new ArrayList();
        try {



            rs = DBUtil.executeQuery(sql);
            EdnDetailBean detail = null;
            while (rs.next()) {
                detail = new EdnDetailBean();
                detail.setDetId(rs.getInt("det_id"));
                detail.setMatId(rs.getInt("emat_id"));
                //       detail.setContractNumber(rs.getString("econ_number"));
                detail.setMatCode(rs.getString("code"));
                detail.setMatName(StringUtil.decodeString(rs.getString("materialName")));
                detail.setQuantity(rs.getInt("quantity"));
                detail.setUnit(rs.getString("u.unit_vn"));
                detail.setNote(StringUtil.getString(rs.getString("note")));
                detail.setPrice(rs.getDouble("price"));
                detailList.add(detail);
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
        return detailList;
    }

    public int insertDn(EdnBean bean) throws Exception {
        if (bean == null) {
            return 0;
        }

        int result = 0;

        try {

            String sql = "";

            sql = "insert into edelivery_notice(created_date,delivery_presenter,expected_date,delivery_place,edn_number,econ_number,created_org,pro_id)"
                    + " values (STR_TO_DATE('" + bean.getCreatedDate() + "','%d/%m/%Y'),'" + bean.getDeliveryPresenter() + "',STR_TO_DATE('" + bean.getExpectedDate() + "','%d/%m/%Y'),'" + bean.getDeliveryPlace() + "','" + bean.getDnNumber() + "','" + bean.getContractNumber() + "','" + bean.getCreatedOrg() + "','" + bean.getProId() + "')";

            ////System.out.println("sql ====" + sql);
            result = DBUtil.executeInsert(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return result;
    }

    public void insertDnDetail(EdnDetailBean bean) throws Exception {
        if (bean == null) {
            return;
        }

        int result = 0;

        try {

            String sql = "";

            sql = "insert into edelivery_notice_detail(edn_id,emat_id,quantity,note,price)"
                    + " values ('" + bean.getDnId() + "','" + bean.getMatId() + "','" + bean.getQuantity() + "','" + bean.getNote() + "','" + bean.getPrice() + "')";

            ////System.out.println("sql ====" + sql);
            result = DBUtil.executeInsert(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return;
    }

    public void updateDn(EdnBean bean) throws Exception {
        if (bean == null) {
            return;
        }



        try {



            String sql = "update edelivery_notice set "
                    + " created_date=STR_TO_DATE('" + bean.getCreatedDate() + "','%d/%m/%Y')"
                    + ", delivery_presenter='" + bean.getDeliveryPresenter() + "'"
                    + ", expected_date=STR_TO_DATE('" + bean.getExpectedDate() + "','%d/%m/%Y')"
                    + ", delivery_place='" + bean.getDeliveryPlace() + "'"
                    + ", edn_number='" + bean.getDnNumber() + "'"
                    + ", econ_number='" + bean.getContractNumber() + "'"
                    + ", created_org='" + bean.getCreatedOrg() + "'"
                    + ", pro_id='" + bean.getProId() + "'"
                    + " where edn_id=" + bean.getDnId();
            ////System.out.println("sql=" + sql);
            DBUtil.executeUpdate(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public void updateDnDetail(EdnDetailBean bean) throws Exception {
        if (bean == null) {
            return;
        }



        try {



            String sql = "update edelivery_notice_detail set "
                    + " edn_id='" + bean.getDnId() + "'"
                    + ", emat_id='" + bean.getMatId() + "'"
                    + ", quantity='" + bean.getQuantity() + "'"
                    + //          ", unit='" + bean.getUnit() + "'" +
                    ", note='" + bean.getNote() + "'"
                    + ", price='" + bean.getPrice() + "'"
                    + " where det_id=" + bean.getDetId();

            ////System.out.println("sql=" + sql);
            DBUtil.executeUpdate(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public int deleteDn(String edn_id) throws Exception {


        int result = 0;
        try {


            String sql = "delete from edelivery_notice " + " where edn_id=" + edn_id;
            result = DBUtil.executeUpdate(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return result;
    }

    public int deleteEdnDetails(String ednId) throws Exception {
        int result = 0;
        try {
            String sql = "Delete From edelivery_notice_detail Where edn_id=" + ednId;
            result = DBUtil.executeUpdate(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return result;
    }

    public int deleteDnDetail(String detId) throws Exception {
        int result = 0;
        try {
            String sql = "Delete From edelivery_notice_detail Where det_id=" + detId;
            result = DBUtil.executeUpdate(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return result;
    }

    public ArrayList searchSimpleDn(int fieldid, String strFieldvalue) throws Exception {
        String strFieldname = "";
        switch (fieldid) {
            case 1:
                strFieldname = "edn_number";
                break;
            case 2:
                strFieldname = "delivery_place";
                break;
        }
        ResultSet rs = null;

        String sql = "SELECT * FROM edelivery_notice WHERE 1 ";
        if ((fieldid > 0) && (!StringUtil.isBlankOrNull(strFieldvalue))) {
            sql = sql + " AND " + strFieldname + " LIKE '%" + strFieldvalue + "%'";
        }
        sql = sql + " order by edn_id DESC";

        ArrayList dnList = new ArrayList();
        try {


            ////System.out.println("sql=" + sql);

            rs = DBUtil.executeQuery(sql);
            EdnFormBean dn = null;
            while (rs.next()) {
                dn = new EdnFormBean();
                dn.setCreatedDate(DateUtil.formatDate(rs.getDate("created_date"), "dd/MM/yyyy"));
                dn.setDnId(rs.getInt("edn_id"));
                dn.setExpectedDate(DateUtil.formatDate(rs.getDate("expected_date"), "dd/MM/yyyy"));
                dn.setDnNumber(rs.getString("edn_number"));
                dn.setContractNumber(rs.getString("econ_number"));
                dn.setDeliveryPlace(rs.getString("delivery_place"));
                dn.setDeliveryPresenter(rs.getString("delivery_presenter"));
                dn.setCreatedOrg(rs.getInt("created_org"));
                dn.setProId(rs.getInt("pro_id"));
                dnList.add(dn);
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
        return dnList;

    }

    public ArrayList searchAdvDn(EdnBean bean) throws Exception {
        ResultSet rs = null;

        String sql = "SELECT * FROM edelivery_notice WHERE 1 ";

        if (!StringUtil.isBlankOrNull(bean.getDnNumber())) {
            sql = sql + " AND edn_number LIKE '%" + bean.getDnNumber() + "%'";
        }
        if (!StringUtil.isBlankOrNull(bean.getContractNumber())) {
            sql = sql + " AND econ_number LIKE '%" + bean.getContractNumber() + "%'";
        }
        if (!StringUtil.isBlankOrNull(bean.getCreatedDate())) {
            sql = sql + " AND 0 = DATEDIFF(created_date, STR_TO_DATE('" + bean.getCreatedDate() + "','%d/%m/%Y %H'))";
        }
        if (!StringUtil.isBlankOrNull(bean.getExpectedDate())) {
            sql = sql + " AND 0 = DATEDIFF(expected_date, STR_TO_DATE('" + bean.getExpectedDate() + "','%d/%m/%Y %H'))";
        }
        if (!StringUtil.isBlankOrNull(bean.getDeliveryPresenter())) {
            sql = sql + " AND delivery_presenter LIKE '%" + bean.getDeliveryPresenter() + "%'";
        }
        if (!StringUtil.isBlankOrNull(bean.getDeliveryPlace())) {
            sql = sql + " AND delivery_place LIKE '%" + bean.getDeliveryPlace() + "%'";
        }
        if (bean.getCreatedOrg() > 0) {
            sql = sql + " AND created_org = " + bean.getCreatedOrg() + "";
        }

        sql = sql + " order by edn_id desc";

        ArrayList dnList = new ArrayList();
        try {


            ////System.out.println("sql=" + sql);

            rs = DBUtil.executeQuery(sql);
            EdnFormBean dn = null;

            while (rs.next()) {
                dn = new EdnFormBean();
                dn.setCreatedDate(DateUtil.formatDate(rs.getDate("created_date"), "dd/MM/yyyy"));
                dn.setDnId(rs.getInt("edn_id"));
                dn.setExpectedDate(DateUtil.formatDate(rs.getDate("expected_date"), "dd/MM/yyyy"));
                dn.setDnNumber(rs.getString("edn_number"));
                dn.setContractNumber(rs.getString("econ_number"));
                dn.setDeliveryPlace(rs.getString("delivery_place"));
                dn.setDeliveryPresenter(rs.getString("delivery_presenter"));
                dn.setCreatedOrg(rs.getInt("created_org"));
                dn.setProId(rs.getInt("pro_id"));
                dnList.add(dn);
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
        return dnList;
    }

    public ArrayList getDnMaterial(int edn_id, int matId) throws Exception {
        ResultSet rs = null;

        String sql = "Select d.*, m.name_vn as materialName, m.code"
                + " From edelivery_notice_detail as d left join ematerial as m on d.emat_id=m.emat_id"
                + " Where edn_id=" + edn_id + " and d.emat_id=" + matId;

        ArrayList detailList = new ArrayList();
        try {



            rs = DBUtil.executeQuery(sql);
            EdnDetailBean detail = null;
            while (rs.next()) {
                detail = new EdnDetailBean();
                detail.setDetId(rs.getInt("det_id"));
                detail.setMatId(rs.getInt("emat_id"));
                //       detail.setContractNumber(rs.getString("econ_number"));
                detail.setMatCode(rs.getString("code"));
                detail.setMatName(StringUtil.decodeString(rs.getString("materialName")));
                detail.setQuantity(rs.getInt("quantity"));
                detail.setUnit(rs.getString("unit_vn"));
                detail.setPrice(rs.getDouble("price"));
                detailList.add(detail);
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
        return detailList;
    }

    public ArrayList getEdnMaterialList(int ednId) throws Exception {
        ResultSet rs = null;

        String sql = "select d.det_id, m.name_vn"
                + " from edelivery_notice_detail AS d"
                + " left join ematerial as m on d.emat_id=m.emat_id"
                + " where d.edn_id=" + ednId;

        ArrayList detailList = new ArrayList();
        try {



            rs = DBUtil.executeQuery(sql);
            while (rs.next()) {
                LabelValueBean detail = new LabelValueBean();
                detail.setValue(rs.getString("d.det_id"));
                detail.setLabel(StringUtil.decodeString(rs.getString("m.name_vn")));
                detailList.add(detail);
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
        return detailList;
    }

    public ArrayList getEdnForEmrir(int status)
            throws Exception {
        ResultSet rs = null;

        String sql = "Select edelivery_notice.* From edelivery_notice Where status = " + status;

        ArrayList dnList = new ArrayList();
        try {



            rs = DBUtil.executeQuery(sql);
            LabelValueBean dn = null;
            while (rs.next()) {
                dn = new LabelValueBean();
                dn.setValue(rs.getString("edn_id"));
                dn.setLabel(rs.getString("edn_number"));
                dnList.add(dn);
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
        return dnList;
    }

    public int getEdnDetailStatus(int detId) throws Exception {
        if (detId == 0) {
            return 0;
        }
        int status = 0;
        ResultSet rs = null;
        try {
            String sql = "select status from edelivery_notice_detail"
                    + " where det_id=" + detId;

            rs = DBUtil.executeQuery(sql);
            while (rs.next()) {
                status = rs.getInt("status");
            }

        } catch (SQLException sqle) {
        } finally {
            if (rs != null) {
                DBUtil.closeConnection(rs);
            }
            
        }

        return status;
    }

    public EmrirDetailBean getEdnDetailForMrir(int detId) throws Exception {
        ResultSet rs = null;

        String sql = "select d.*, m.name_vn, m.code,u.unit_vn"
                + " from edelivery_notice_detail as d"
                + " left join ematerial as m on d.emat_id=m.emat_id"
                + " left join unit as u on u.uni_id=m.uni_id"
                + //                " left join request_detail as rd on d.req_detail_id=rd.det_id" +
                //                " left join request as r on r.req_id=rd.req_id" +
                //                " left join organization as o on o.org_id=r.org_id" +
                " Where d.det_id=" + detId;

        try {



            rs = DBUtil.executeQuery(sql);
            int i = 0;
            while (rs.next()) {
                EmrirDetailBean detail = new EmrirDetailBean();
                detail.setDetId("dn_" + rs.getString("det_id"));
                detail.setEmatId(rs.getInt("emat_id"));
                detail.setMatCode(rs.getString("m.code"));
                detail.setMatName(StringUtil.decodeString(rs.getString("m.name_vn")));
                detail.setQuantity(rs.getDouble("quantity"));
                detail.setUnit(rs.getString("u.unit_vn"));
                detail.setPrice(rs.getDouble("price"));
                //lay tu phieu y/c nhap tra kho vat tu 
                detail.setMaterialGrade("");
                detail.setMaterialType("");
                detail.setSize1("");
                detail.setSize2("");
                detail.setSize3("");
                detail.setHeatSerial("");
                detail.setTraceNo("");
                detail.setCertNo("");
                detail.setColourCode("");
                detail.setRating("");
                detail.setSystemNo("");
                detail.setItemNo("");
                detail.setLocation("");
                detail.setComment("");

                return detail;
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

    public EosDDetailBean getEdnDetailForOsD(int detId) throws Exception {
        ResultSet rs = null;

        String sql = "select d.*, m.name_vn, m.code,u.unit_vn"
                + " from edelivery_notice_detail as d"
                + " left join ematerial as m on d.emat_id=m.emat_id"
                + " left join unit as u on u.uni_id=m.uni_id"
                + //                " left join request_detail as rd on d.req_detail_id=rd.det_id" +
                //                " left join request as r on r.req_id=rd.req_id" +
                //                " left join organization as o on o.org_id=r.org_id" +
                " Where d.det_id=" + detId;

        try {



            rs = DBUtil.executeQuery(sql);
            int i = 0;
            while (rs.next()) {
                EosDDetailBean detail = new EosDDetailBean();
                detail.setDetId("dn_" + rs.getString("det_id"));
                detail.setEmatId(rs.getInt("emat_id"));
                detail.setMatCode(rs.getString("m.code"));
                detail.setMatName(StringUtil.decodeString(rs.getString("m.name_vn")));
                detail.setQuantity(rs.getDouble("quantity"));
                detail.setUnit(rs.getString("u.unit_vn"));

                return detail;
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

    public void updateEdnDetailStatus(int detId, int status) throws Exception {
        if (detId == 0) {
            return;
        }
        try {
            String sql = "update edelivery_notice_detail set "
                    + " status=" + status
                    + " where det_id=" + detId;
            DBUtil.executeUpdate(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        }
    }

    public ArrayList getEdnDetailsForMrir(int ednId, int status) throws Exception {
        ResultSet rs = null;

        String sql = "select d.*, m.name_vn, m.code,u.unit_vn"
                + " from edelivery_notice_detail as d"
                + " left join ematerial as m on d.emat_id=m.emat_id"
                + " left join unit as u on u.uni_id=m.uni_id"
                + " Where d.edn_id=" + ednId + " and d.status=" + status;
        ArrayList arrRes = new ArrayList();

        try {



            rs = DBUtil.executeQuery(sql);
            while (rs.next()) {
                EmrirDetailBean detail = new EmrirDetailBean();
                detail.setDetId("dn_" + rs.getString("det_id"));
                detail.setEmatId(rs.getInt("emat_id"));
                detail.setMatCode(rs.getString("m.code"));
                detail.setMatName(StringUtil.decodeString(rs.getString("m.name_vn")));
                detail.setQuantity(rs.getDouble("quantity"));
                detail.setUnit(rs.getString("u.unit_vn"));
                detail.setPrice(rs.getDouble("price"));
                //lay tu phieu y/c nhap tra kho vat tu 
                detail.setMaterialGrade("");
                detail.setMaterialType("");
                detail.setSize1("");
                detail.setSize2("");
                detail.setSize3("");
                detail.setHeatSerial("");
                detail.setTraceNo("");
                detail.setRating("");
                detail.setCertNo("");
                detail.setColourCode("");
                detail.setStatus(rs.getInt("status"));
                arrRes.add(detail);
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
        return arrRes;
    }
}
