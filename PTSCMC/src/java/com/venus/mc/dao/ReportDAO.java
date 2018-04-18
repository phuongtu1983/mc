package com.venus.mc.dao;

import com.venus.core.util.DateUtil;
import com.venus.core.util.GenericValidator;
import com.venus.core.util.NumberUtil;
import com.venus.core.util.StringUtil;
import com.venus.mc.bean.ContractBean;
import com.venus.mc.bean.OsDBean;
import com.venus.mc.bean.RequestBean;
import com.venus.mc.bean.StoreBean;
import com.venus.mc.contract.ContractFormBean;
import com.venus.mc.database.DBUtil;
import com.venus.mc.process.store.report.MCReportFormBean;
import com.venus.mc.process.store.report.MCRequestReportFormBean;
import com.venus.mc.process.store.report.Store2Level2FormBean;
import com.venus.mc.process.store.report.Store2LevelFormBean;
import com.venus.mc.request.RequestFormBean;
import com.venus.mc.request.RequestReportBean;
import com.venus.mc.util.MCUtil;
import com.venus.mc.util.SQLSearchExpressionUtil;
import java.io.PrintStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReportDAO
  extends BasicDAO
{
  public ArrayList getMCReport(int proId, int stoId, int matKind, String msvFromDate, String msvEndDate, String rfmFromDate, String rfmEndDate, String mivFromDate, String mivEndDate)
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "select distinct m.code as matCode, m.name_vn as matName, u.unit_vn as matUnit, c.contract_number,c.kind,c.appendix_contract_number, r.request_number, cdet.quantity as contractQuantity, cdet.price, mr.mrir_number, mrdet.heat_serial, mr.created_date as mrirDate, msv.msv_number, msvdet.quantity msvQuantity, s.name as store, rfm.rfm_number, miv.miv_number, miv.created_date as mivDate, mrdet.quality, ro.name as usedOrg, rfmdet.quantity as usedQuantity, mivdet.pre_quantity, mrdet.comment, mrdet.location from mrir as mr left join mrir_detail as mrdet on mr.mrir_id=mrdet.mrir_id left join contract as c on mr.con_id=c.con_id left join contract_detail as cdet on cdet.con_id=c.con_id and cdet.reqDetail_id=mrdet.req_detail_id left join request as r on mr.req_id=r.req_id left join material as m on mrdet.mat_id=m.mat_id left join unit as u on u.uni_id=m.uni_id left join msv as msv on msv.mrir_id=mr.mrir_id left join msv_detail as msvdet on msvdet.msv_id=msv.msv_id and msvdet.req_detail_id=mrdet.req_detail_id left join store as s on s.sto_id=msv.sto_id left join rfm as rfm on rfm.sto_id=msv.sto_id left join rfm_detail as rfmdet on rfmdet.req_detail_id=msvdet.req_detail_id and rfmdet.rfm_id=rfm.rfm_id left join miv as miv on miv.rfm_id=rfm.rfm_id left join miv_detail as mivdet on mivdet.miv_id=miv.miv_id and mivdet.req_detail_id=mrdet.req_detail_id left join organization as ro on ro.org_id=rfm.request_org where mr.con_id is not null and rfmdet.req_detail_id is not null";
    if (proId != 0) {
      sql = sql + " and s.org_id=" + proId;
    }
    if (stoId != 0) {
      sql = sql + " and msv.sto_id=" + stoId;
    }
    if (matKind != 0) {
      sql = sql + " and mrdet.mk_id=" + matKind;
    }
    if (!GenericValidator.isBlankOrNull(msvFromDate)) {
      sql = sql + " and date(msv.created_date) >= STR_TO_DATE('" + msvFromDate + "','%d/%m/%Y')";
    }
    if (!GenericValidator.isBlankOrNull(msvEndDate)) {
      sql = sql + " and date(msv.created_date) <= STR_TO_DATE('" + msvEndDate + "','%d/%m/%Y')";
    }
    if (!GenericValidator.isBlankOrNull(rfmFromDate)) {
      sql = sql + " and date(rfm.created_date) >= STR_TO_DATE('" + rfmFromDate + "','%d/%m/%Y')";
    }
    if (!GenericValidator.isBlankOrNull(rfmEndDate)) {
      sql = sql + " and date(rfm.created_date) <= STR_TO_DATE('" + rfmEndDate + "','%d/%m/%Y')";
    }
    if (!GenericValidator.isBlankOrNull(mivFromDate)) {
      sql = sql + " and date(miv.created_date) >= STR_TO_DATE('" + mivFromDate + "','%d/%m/%Y')";
    }
    if (!GenericValidator.isBlankOrNull(mivEndDate)) {
      sql = sql + " and date(miv.created_date) <= STR_TO_DATE('" + mivEndDate + "','%d/%m/%Y')";
    }
    ArrayList list = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      MCReportFormBean bean = null;
      int i = 1;
      while (rs.next())
      {
        bean = new MCReportFormBean();
        bean.setOrder(i++);
        bean.setMatCode(rs.getString("matCode"));
        if (rs.getString("matName") != null) {
          bean.setMatName(StringUtil.decodeString(rs.getString("matName")));
        }
        bean.setMatUnit(rs.getString("matUnit"));
        if (rs.getInt("kind") == ContractBean.KIND_APPENDIX) {
          bean.setContractNumber(rs.getString("contract_number"));
        } else {
          bean.setContractNumber(rs.getString("contract_number"));
        }
        bean.setRequestNumber(rs.getString("request_number"));
        bean.setMatPrice(rs.getDouble("price"));
        bean.setContractQuantity(rs.getDouble("contractQuantity"));
        bean.setMrirNumber(rs.getString("mrir_number"));
        bean.setHeatNo(rs.getString("heat_serial"));
        bean.setMrirDate(DateUtil.formatDate(rs.getDate("mrirDate"), "dd/MM/yyyy"));
        bean.setMsvNumber(rs.getString("msv_number"));
        bean.setMsvQuantity(rs.getDouble("msvQuantity"));
        bean.setStore(rs.getString("store"));
        bean.setRfmNumber(rs.getString("rfm_number"));
        bean.setMivNumber(rs.getString("miv_number"));
        bean.setCertificate(rs.getString("quality"));
        bean.setUsedOrg(rs.getString("usedOrg"));
        bean.setUsedQuantity(rs.getDouble("usedQuantity"));
        bean.setMivDate(DateUtil.formatDate(rs.getDate("mivDate"), "dd/MM/yyyy"));
        if (!GenericValidator.isBlankOrNull(bean.getMivNumber())) {
          bean.setBalance(rs.getDouble("pre_quantity") - bean.getUsedQuantity());
        } else {
          bean.setBalance(bean.getMsvQuantity());
        }
        bean.setTotalReceived(bean.getMsvQuantity() * bean.getMatPrice());
        bean.setTotalIssued(bean.getMatPrice() * bean.getUsedQuantity());
        bean.setLocation(rs.getString("location"));
        bean.setMatNote(rs.getString("comment"));
        list.add(bean);
      }
    }
    catch (SQLException sqle)
    {
      throw new Exception(sqle.getMessage());
    }
    catch (Exception ex)
    {
      throw new Exception(ex.getMessage());
    }
    finally
    {
      if (rs != null) {
        DBUtil.closeConnection(rs);
      }
    }
    return list;
  }
  
  public ArrayList getMCRequestReport(int proId, int reqId, String deliveryFromDate, String deliveryEndDate, String contractFromDate, String contractEndDate, String mrirFromDate, String mrirEndDate, String msvFromDate, String msvEndDate, String rfmFromDate, String rfmEndDate, String mivFromDate, String mivEndDate, String deliveryDateAsContractFromDate, String deliveryDateAsContractToDate)
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "select distinct r.request_number, p.name as projectName, co.name as orgName, m.code as matCode, m.name_vn as matName, r.created_date as requestDate, u.unit_vn as matUnit, r.created_date as mrirDate, rdet.additional_quantity, t.tender_number, c.contract_number,c.kind,c.appendix_contract_number, c.created_date as contractDate, v.name as vendorName, mr.created_date as mrirDate, mr.mrir_number, ms.msv_number, mi.miv_number, r.bom_agree_date, mrdet.location, mrdet.comment from request as r left join organization as p on r.org_id=p.org_id left join organization as co on r.created_org=co.org_id left join request_detail as rdet on rdet.req_id=r.req_id left join material as m on m.mat_id=rdet.mat_id left join unit as u on u.uni_id=m.uni_id left join tender_plan_detail as tdet on tdet.reqDetail_id=rdet.det_id left join tender_plan as t on t.ten_id=tdet.ten_id left join contract_detail as cdet on cdet.reqDetail_id=rdet.det_id left join contract as c on c.con_id=cdet.con_id left join vendor as v on v.ven_id=c.ven_id left join mrir_detail as mrdet on mrdet.req_detail_id=rdet.det_id left join mrir as mr on mrdet.mrir_id=mr.mrir_id left join msv as ms on ms.mrir_id=mr.mrir_id left join rfm_detail as rfdet on rfdet.msv_id=ms.msv_id and rfdet.req_detail_id=mrdet.req_detail_id left join rfm as rf on rf.rfm_id=rfdet.rfm_id left join miv as mi on mi.rfm_id=rf.rfm_id where 1";
    if (proId > 0) {
      sql = sql + " and r.org_id=" + proId;
    }
    if (reqId > 0) {
      sql = sql + " and r.req_id=" + reqId;
    }
    if (!GenericValidator.isBlankOrNull(deliveryFromDate)) {
      sql = sql + " and date(r.created_date) >= STR_TO_DATE('" + deliveryFromDate + "','%d/%m/%Y')";
    }
    if (!GenericValidator.isBlankOrNull(deliveryEndDate)) {
      sql = sql + " and date(r.created_date) <= STR_TO_DATE('" + deliveryEndDate + "','%d/%m/%Y')";
    }
    if (!GenericValidator.isBlankOrNull(contractFromDate)) {
      sql = sql + " and date(c.created_date) >= STR_TO_DATE('" + contractFromDate + "','%d/%m/%Y')";
    }
    if (!GenericValidator.isBlankOrNull(contractEndDate)) {
      sql = sql + " and date(c.created_date) <= STR_TO_DATE('" + contractEndDate + "','%d/%m/%Y')";
    }
    if (!GenericValidator.isBlankOrNull(mrirFromDate)) {
      sql = sql + " and date(mr.created_date) >= STR_TO_DATE('" + mrirFromDate + "','%d/%m/%Y')";
    }
    if (!GenericValidator.isBlankOrNull(mrirEndDate)) {
      sql = sql + " and date(mr.created_date) <= STR_TO_DATE('" + mrirEndDate + "','%d/%m/%Y')";
    }
    if (!GenericValidator.isBlankOrNull(msvFromDate)) {
      sql = sql + " and date(ms.created_date) >= STR_TO_DATE('" + msvFromDate + "','%d/%m/%Y')";
    }
    if (!GenericValidator.isBlankOrNull(msvEndDate)) {
      sql = sql + " and date(ms.created_date) <= STR_TO_DATE('" + msvEndDate + "','%d/%m/%Y')";
    }
    if (!GenericValidator.isBlankOrNull(rfmFromDate)) {
      sql = sql + " and date(rf.created_date) >= STR_TO_DATE('" + rfmFromDate + "','%d/%m/%Y')";
    }
    if (!GenericValidator.isBlankOrNull(rfmEndDate)) {
      sql = sql + " and date(rf.created_date) <= STR_TO_DATE('" + rfmEndDate + "','%d/%m/%Y')";
    }
    if (!GenericValidator.isBlankOrNull(mivFromDate)) {
      sql = sql + " and date(mi.created_date) >= STR_TO_DATE('" + mivFromDate + "','%d/%m/%Y')";
    }
    if (!GenericValidator.isBlankOrNull(mivEndDate)) {
      sql = sql + " and date(mi.created_date) <= STR_TO_DATE('" + mivEndDate + "','%d/%m/%Y')";
    }
    if (!GenericValidator.isBlankOrNull(deliveryDateAsContractFromDate)) {
      sql = sql + " and date(c.delivery_date) >= STR_TO_DATE('" + deliveryDateAsContractFromDate + "','%d/%m/%Y')";
    }
    if (!GenericValidator.isBlankOrNull(deliveryDateAsContractToDate)) {
      sql = sql + " and date(c.delivery_date) <= STR_TO_DATE('" + deliveryDateAsContractToDate + "','%d/%m/%Y')";
    }
    ArrayList list = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      MCRequestReportFormBean bean = null;
      int i = 1;
      while (rs.next())
      {
        bean = new MCRequestReportFormBean();
        bean.setId(i++);
        bean.setRequestNumber(rs.getString("request_number"));
        bean.setProjectName(rs.getString("projectName"));
        bean.setOrganization(rs.getString("orgName"));
        bean.setMatCode(rs.getString("matCode"));
        if (rs.getString("matName") != null) {
          bean.setMatName(StringUtil.decodeString(rs.getString("matName")));
        }
        bean.setRequestDate(DateUtil.formatDate(rs.getDate("requestDate"), "dd/MM/yyyy"));
        bean.setRequestDeliveryDate(DateUtil.formatDate(rs.getDate("bom_agree_date"), "dd/MM/yyyy"));
        bean.setMatUnit(rs.getString("matUnit"));
        bean.setQuantity(rs.getDouble("additional_quantity"));
        bean.setTenderplanNumber(rs.getString("tender_number"));
        if (rs.getInt("kind") == ContractBean.KIND_APPENDIX) {
          bean.setContractNumber(rs.getString("contract_number"));
        } else {
          bean.setContractNumber(rs.getString("contract_number"));
        }
        bean.setContractDate(DateUtil.formatDate(rs.getDate("contractDate"), "dd/MM/yyyy"));
        bean.setVendor(StringUtil.decodeString(rs.getString("vendorName")));
        bean.setMrirDate(DateUtil.formatDate(rs.getDate("mrirDate"), "dd/MM/yyyy"));
        bean.setMrirNumber(rs.getString("mrir_number"));
        bean.setMsvNumber(rs.getString("msv_number"));
        bean.setMivNumber(rs.getString("miv_number"));
        bean.setMatNote(rs.getString("comment"));
        bean.setLocation(rs.getString("location"));
        list.add(bean);
      }
    }
    catch (SQLException sqle)
    {
      throw new Exception(sqle.getMessage());
    }
    catch (Exception ex)
    {
      throw new Exception(ex.getMessage());
    }
    finally
    {
      if (rs != null) {
        DBUtil.closeConnection(rs);
      }
    }
    return list;
  }
  
  public ArrayList getMCProjectStoreReport(int proId, int stoId, int orgList, String nameVn, String code, String msvFromDate, String msvEndDate, String rfmFromDate, String rfmEndDate, String mivFromDate, String mivEndDate)
    throws Exception
  {
    ResultSet rs = null;
    
    String sql1 = "SELECT mrdet.req_detail_id, ms.TYPE, m.CODE, m.name_vn, mrdet.unit, r.request_number, c.contract_number, ro.NAME AS orgReqName, fo.NAME AS procerement, fop.NAME AS procerement2,  cdet.quantity AS conQuantity, cdet.price, mr.mrir_number, mrdet.heat_serial, mrdet.cert_no, '' AS rfm_number, '' AS requester, ms.msv_number AS msvmiv, ms.created_date AS msvmivDate, mdet.quantity, 0 AS rfmQuantity, msr.actual_quantity AS balance, mrdet.location, c.kind, c.appendix_contract_number, DATEDIFF(SYSDATE(),mrdet.time_using) AS time_use FROM mrir AS mr LEFT JOIN mrir_detail AS mrdet ON mrdet.mrir_id=mr.mrir_id LEFT JOIN request_detail AS rdet ON rdet.det_id=mrdet.req_detail_id LEFT JOIN contract_detail AS cdet ON cdet.reqDetail_id=rdet.det_id LEFT JOIN material AS m ON cdet.mat_id_temp=m.mat_id LEFT JOIN request AS r ON rdet.req_id=r.req_id LEFT JOIN contract AS c ON c.con_id=cdet.con_id LEFT JOIN organization AS ro ON ro.org_id=r.org_id  LEFT JOIN employee AS fe ON fe.emp_id=c.follow_emp LEFT JOIN organization AS fo ON fo.org_id=fe.org_id LEFT JOIN organization AS fop ON fop.org_id=fo.parent_id LEFT JOIN msv_detail AS mdet ON mdet.mrir_det_id=mrdet.det_id LEFT JOIN msv AS ms ON ms.msv_id=mdet.msv_id LEFT JOIN material_store_request AS msr ON msr.req_detail_id=mrdet.req_detail_id  AND msr.msv_id=mdet.msv_id LEFT JOIN contract AS cc ON cdet.con_id=cc.parent_id AND cc.kind=" + ContractBean.KIND_ADJUSTMENT + " where cdet.STATUS=" + ContractFormBean.MATERIAL_STATUS_NORMAL + " AND cc.parent_id IS NULL";
    
    String sql2 = "SELECT mrdet.req_detail_id, ms.TYPE, m.CODE, m.name_vn, mrdet.unit, r.request_number, c.contract_number, ro.NAME AS orgReqName, fo.NAME AS procerement, fop.NAME AS procerement2, cdet.quantity AS conQuantity, cdet.price, mr.mrir_number, mrdet.heat_serial, mrdet.cert_no, rf.rfm_number, rfo.NAME AS requester, mi.miv_number AS msvmiv, mi.created_date AS msvmivDate, -midet.quantity, rfdet.quantity AS rfmQuantity, 0 AS balance, mrdet.location, c.kind, c.appendix_contract_number, DATEDIFF(SYSDATE(),mrdet.time_using) AS time_use FROM mrir AS mr LEFT JOIN mrir_detail AS mrdet ON mrdet.mrir_id=mr.mrir_id LEFT JOIN request_detail AS rdet ON rdet.det_id=mrdet.req_detail_id LEFT JOIN contract_detail AS cdet ON cdet.reqDetail_id=rdet.det_id LEFT JOIN material AS m ON cdet.mat_id_temp=m.mat_id LEFT JOIN request AS r ON rdet.req_id=r.req_id LEFT JOIN contract AS c ON c.con_id=cdet.con_id LEFT JOIN organization AS ro ON ro.org_id=r.org_id  LEFT JOIN employee AS fe ON fe.emp_id=c.follow_emp LEFT JOIN organization AS fo ON fo.org_id=fe.org_id LEFT JOIN organization AS fop ON fop.org_id=fo.parent_id LEFT JOIN msv_detail AS mdet ON mdet.mrir_det_id=mrdet.det_id LEFT JOIN msv AS ms ON ms.msv_id=mdet.msv_id LEFT JOIN rfm_detail AS rfdet ON rfdet.req_detail_id=mrdet.req_detail_id AND rfdet.msv_id=ms.msv_id LEFT JOIN rfm AS rf ON rf.rfm_id=rfdet.rfm_id LEFT JOIN miv_detail AS midet ON midet.req_detail_id=mrdet.req_detail_id AND midet.rfm_det_id=rfdet.det_id LEFT JOIN miv AS mi ON mi.miv_id=midet.miv_id LEFT JOIN contract AS cc ON cdet.con_id=cc.parent_id AND cc.kind=" + ContractBean.KIND_ADJUSTMENT + " , organization AS rfo" + " where rfo.org_id=rf.request_org and cdet.STATUS=" + ContractFormBean.MATERIAL_STATUS_NORMAL + " AND cc.parent_id IS NULL";
    if (!GenericValidator.isBlankOrNull(mivFromDate)) {
      sql2 = sql2 + " and date(mi.created_date) >= STR_TO_DATE('" + mivFromDate + "','%d/%m/%Y')";
    }
    if (!GenericValidator.isBlankOrNull(mivEndDate)) {
      sql2 = sql2 + " and date(mi.created_date) <= STR_TO_DATE('" + mivEndDate + "','%d/%m/%Y')";
    }
    sql1 = sql1 + " AND (";
    sql2 = sql2 + " AND (1 and (";
    if (proId > 0)
    {
      if (proId == 41)
      {
        sql1 = sql1 + " ( r.org_id=" + proId + " and r.which_use=" + RequestFormBean.WHICHUSE_PROJECT + ") or r.which_use<>" + RequestFormBean.WHICHUSE_PROJECT;
        
        sql2 = sql2 + " ( r.org_id=" + proId + " and r.which_use=" + RequestFormBean.WHICHUSE_PROJECT + ") or r.which_use<>" + RequestFormBean.WHICHUSE_PROJECT;
      }
      else
      {
        sql1 = sql1 + " ( r.org_id=" + proId + " and r.which_use=" + RequestFormBean.WHICHUSE_PROJECT + ")";
        if (stoId != 0) {
          sql1 = sql1 + " OR  ms.sto_id=" + stoId;
        }
        sql2 = sql2 + " ( r.org_id=" + proId + " and r.which_use=" + RequestFormBean.WHICHUSE_PROJECT + ")";
      }
    }
    else
    {
      sql1 = sql1 + " 1 ";
      sql2 = sql2 + " 1 ";
    }
    sql2 = sql2 + " or ";
    if (stoId != 0) {
      sql2 = sql2 + " mi.sto_id=" + stoId;
    } else {
      sql2 = sql2 + " 1 ";
    }
    sql1 = sql1 + ")";
    sql2 = sql2 + "))";
    if (!GenericValidator.isBlankOrNull(nameVn))
    {
      sql1 = sql1 + " and (" + SQLSearchExpressionUtil.excuteExpression(nameVn, "m.name_vn") + ")";
      sql2 = sql2 + " and (" + SQLSearchExpressionUtil.excuteExpression(nameVn, "m.name_vn") + ")";
    }
    if (!GenericValidator.isBlankOrNull(code))
    {
      sql1 = sql1 + " and (" + SQLSearchExpressionUtil.excuteExpression(code, "m.code") + ")";
      sql2 = sql2 + " and (" + SQLSearchExpressionUtil.excuteExpression(code, "m.code") + ")";
    }
    if (!GenericValidator.isBlankOrNull(msvFromDate))
    {
      sql1 = sql1 + " and date(ms.created_date) >= STR_TO_DATE('" + msvFromDate + "','%d/%m/%Y')";
      sql2 = sql2 + " and date(ms.created_date) >= STR_TO_DATE('" + msvFromDate + "','%d/%m/%Y')";
    }
    if (!GenericValidator.isBlankOrNull(msvEndDate))
    {
      sql1 = sql1 + " and date(ms.created_date) <= STR_TO_DATE('" + msvEndDate + "','%d/%m/%Y')";
      sql2 = sql2 + " and date(ms.created_date) <= STR_TO_DATE('" + msvEndDate + "','%d/%m/%Y')";
    }
    if (!GenericValidator.isBlankOrNull(rfmFromDate))
    {
      sql1 = sql1 + " and date(rf.created_date) >= STR_TO_DATE('" + rfmFromDate + "','%d/%m/%Y')";
      sql2 = sql2 + " and date(rf.created_date) >= STR_TO_DATE('" + rfmFromDate + "','%d/%m/%Y')";
    }
    if (!GenericValidator.isBlankOrNull(rfmEndDate))
    {
      sql1 = sql1 + " and date(rf.created_date) <= STR_TO_DATE('" + rfmEndDate + "','%d/%m/%Y')";
      sql2 = sql2 + " and date(rf.created_date) <= STR_TO_DATE('" + rfmEndDate + "','%d/%m/%Y')";
    }
    sql1 = sql1 + " union " + sql2 + " ORDER BY 1 ASC, 2 DESC";
    System.out.println("sql===" + sql1);
    
    ArrayList list = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql1);
      MCRequestReportFormBean bean = null;
      int i = 1;
      while (rs.next()) {
        if (rs.getInt("req_detail_id") != 0)
        {
          bean = new MCRequestReportFormBean();
          bean.setId(i++);
          bean.setMatCode(rs.getString("code"));
          if (rs.getString("name_vn") != null) {
            bean.setMatName(StringUtil.decodeString(rs.getString("name_vn")));
          }
          bean.setMatUnit(rs.getString("unit"));
          bean.setRequestNumber(rs.getString("request_number"));
          if (rs.getInt("kind") == ContractBean.KIND_APPENDIX) {
            bean.setContractNumber(rs.getString("contract_number"));
          } else {
            bean.setContractNumber(rs.getString("contract_number"));
          }
          bean.setProjectName(rs.getString("orgReqName"));
          if (!GenericValidator.isBlankOrNull(rs.getString("procerement2"))) {
            bean.setProcurement(rs.getString("procerement2"));
          } else if (!GenericValidator.isBlankOrNull(rs.getString("procerement"))) {
            bean.setProcurement(rs.getString("procerement"));
          }
          bean.setConQuantity(rs.getDouble("conQuantity"));
          bean.setUnitPrice(NumberUtil.formatMoneyDefault(Double.valueOf(rs.getDouble("price"))));
          bean.setMrirNumber(rs.getString("mrir_number"));
          bean.setHeatNo(rs.getString("heat_serial"));
          bean.setMillNo(rs.getString("cert_no"));
          bean.setRfmNumber(rs.getString("rfm_number"));
          bean.setRequester(rs.getString("requester"));
          bean.setMsvmiv(rs.getString("msvmiv"));
          bean.setMsvmivDate(DateUtil.formatDate(rs.getDate("msvmivDate"), "dd/MM/yyyy"));
          bean.setBalance(rs.getDouble("balance"));
          if (rs.getDouble("quantity") != 0.0D)
          {
            bean.setQuantity(rs.getDouble("quantity"));
            bean.setTotalPrice(NumberUtil.formatMoneyDefault(Double.valueOf(rs.getDouble("price") * rs.getDouble("quantity"))));
          }
          else
          {
            bean.setQuantity(rs.getDouble("rfmQuantity"));
            bean.setTotalPrice(NumberUtil.formatMoneyDefault(Double.valueOf(rs.getDouble("price") * rs.getDouble("rfmQuantity"))));
          }
          bean.setLocation(rs.getString("location"));
          if (rs.getInt("time_use") > 0) {
            bean.setTimeUse(MCUtil.getBundleString("message.mrirdetail.timeUse") + " " + rs.getInt("time_use") + " " + MCUtil.getBundleString("message.day"));
          } else {
            bean.setTimeUse("");
          }
          list.add(bean);
        }
      }
    }
    catch (SQLException sqle)
    {
      throw new Exception(sqle.getMessage());
    }
    catch (Exception ex)
    {
      throw new Exception(ex.getMessage());
    }
    finally
    {
      if (rs != null) {
        DBUtil.closeConnection(rs);
      }
    }
    return list;
  }
  
  public ArrayList getMCProjectStoreMrirReport(int proId, int stoId, int status, String osdFromDate, String osdEndDate)
    throws Exception
  {
    ResultSet rs = null;
    String sql1 = "SELECT c.contract_number,r1.request_number, o.created_date, m.mrir_number, o.osd_number, o.closed, o.closed_date,o.note FROM os_d AS o LEFT JOIN os_d_detail AS o1 ON o1.osd_id =o.osd_id  LEFT JOIN request_detail AS r ON r.det_id = o1.req_detail_id LEFT JOIN request AS r1 ON r1.req_id = r.req_id LEFT JOIN mrir AS m ON o.mrir_id = m.mrir_id LEFT JOIN contract AS c ON c.con_id = m.con_id WHERE 1 ";
    if (!GenericValidator.isBlankOrNull(osdFromDate)) {
      sql1 = sql1 + " and date(o.created_date) >= STR_TO_DATE('" + osdFromDate + "','%d/%m/%Y')";
    }
    if (!GenericValidator.isBlankOrNull(osdEndDate)) {
      sql1 = sql1 + " and date(mi.created_date) <= STR_TO_DATE('" + osdEndDate + "','%d/%m/%Y')";
    }
    sql1 = sql1 + " AND ";
    if (proId > 0) {
      sql1 = sql1 + " r1.org_id=" + proId;
    } else {
      sql1 = sql1 + " 1 ";
    }
    sql1 = sql1 + " GROUP BY request_number";
    ArrayList list = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql1);
      OsDBean bean = null;
      while (rs.next())
      {
        bean = new OsDBean();
        bean.setContractNumber(rs.getString("contract_number"));
        bean.setRequestNumber(rs.getString("request_number"));
        bean.setCreatedDate(DateUtil.formatDate(rs.getDate("created_date"), "dd/MM/yyyy"));
        bean.setMrirNumber(rs.getString("mrir_number"));
        bean.setOsdNumber(rs.getString("osd_number"));
        bean.setClosedDate(DateUtil.formatDate(rs.getDate("closed_date"), "dd/MM/yyyy"));
        bean.setNote(rs.getString("note"));
        if (rs.getInt("closed") == 1) {
          bean.setStringClosed("Close");
        } else {
          bean.setStringClosed("Open");
        }
        list.add(bean);
      }
    }
    catch (SQLException sqle)
    {
      throw new Exception(sqle.getMessage());
    }
    catch (Exception ex)
    {
      throw new Exception(ex.getMessage());
    }
    finally
    {
      if (rs != null) {
        DBUtil.closeConnection(rs);
      }
    }
    return list;
  }
  
  public ArrayList getRequestTimeReport(int proId, String requestNumber, String deliveryFromDate, String deliveryEndDate)
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "select distinct r.request_number, r.bom_agree_date, rdet.provide_date, p.name as projectName, co.name as organization, rdet.remain_quantity, rdet.request_quantity, r.description, GROUP_CONCAT(DISTINCT gm.note) AS matGroup, e.fullname as requester, GROUP_CONCAT(DISTINCT ae.fullname) AS responseEmp, (SELECT COUNT(cdet.det_id) FROM contract AS c, contract_detail AS cdet, request_detail AS rqdet WHERE c.con_id=cdet.con_id AND cdet.reqDetail_id=rqdet.det_id AND rqdet.req_id=r.req_id AND c.payment_status=1 and c.kind<>" + ContractBean.KIND_ADJUSTMENT + ") AS contract_approved_counter" + ", (SELECT COUNT(detail.det_id) FROM request_detail AS detail WHERE detail.req_id=rdet.req_id AND detail.is_cancel=1) AS cancel_counter" + ", COUNT(rdet.det_id) AS request_counter" + " , (SELECT MAX(c.created_date) FROM request_detail rdet, contract_detail AS cdet, contract AS c WHERE rdet.req_id=r.req_id AND rdet.det_id=cdet.reqDetail_id AND cdet.con_id=c.con_id AND rdet.is_cancel=0 AND c.payment_status=1 AND c.kind<>" + ContractBean.KIND_ADJUSTMENT + ") AS last_date" + ", (SELECT MAX(IF(tdet.det_id IS NOT NULL, ten.created_date, IF(cdet.det_id IS NOT NULL, con.created_date,'')))" + " FROM request_detail rdet" + " LEFT JOIN tender_plan_detail AS tdet ON tdet.reqDetail_id=rdet.det_id AND rdet.ten_id IS NOT NULL" + " LEFT JOIN tender_plan AS ten ON tdet.ten_id=ten.ten_id" + " LEFT JOIN contract_detail AS cdet ON cdet.reqDetail_id=rdet.det_id AND (rdet.con_id IS NOT NULL OR rdet.ord_id IS NOT NULL OR rdet.dr_id IS NOT NULL OR rdet.dn_id IS NOT NULL)" + " LEFT JOIN contract AS con ON con.con_id=cdet.con_id" + " WHERE rdet.req_id=r.req_id AND rdet.is_cancel=0 AND rdet.step_id<>1) AS last_process_date " + " from request as r" + " left join request_detail as rdet on rdet.req_id=r.req_id" + " left join organization as p on r.org_id=p.org_id" + " left join organization as co on r.created_org=co.org_id" + " left join material as m on m.mat_id=rdet.mat_id" + " left join specification2 as gm on gm.spe2_id=m.gro_id" + " left join employee as e on e.emp_id=r.created_emp" + " left join employee as ae on ae.emp_id=rdet.emp_id" + " where r.created_emp<>1 and r.kind=" + RequestBean.REQUEST;
    
    sql = sql + " and ( r.created_emp=" + getRequestEmp();
    sql = sql + " OR ( " + getRequestEmp() + " in ";
    
    sql = sql + "( SELECT emp_id FROM employee, request_handled AS rh WHERE rh.req_id=r.req_id AND emp_id=rh.assigned_emp union select emp_id from request_detail where req_id=r.req_id))";
    if (!getInvolvedEmps().equals(""))
    {
      String[] orgs = getInvolvedOrgs().split(",");
      sql = sql + " or (r.status=" + RequestBean.STATUS_HANDLE + " and (0";
      for (int i = 0; i < orgs.length; i++) {
        sql = sql + " or concat(',',r.org_handle,',') like '%," + orgs[i] + ",%'";
      }
      sql = sql + "))";
    }
    sql = sql + ")";
    sql = sql + " and (0 ";
    if (!StringUtil.isBlankOrNull(getInvolvedOrgs())) {
      sql = sql + " OR rdet.emp_id IN (SELECT b.emp_id FROM employee AS b WHERE b.org_id IN (0" + getInvolvedOrgs() + "))";
    } else {
      sql = sql + " OR 1 ";
    }
    sql = sql + ")";
    if (proId > 0) {
      sql = sql + " and r.org_id=" + proId;
    }
    if (!StringUtil.isBlankOrNull(requestNumber)) {
      sql = sql + " and (" + SQLSearchExpressionUtil.excuteExpression(requestNumber, "request_number") + ")";
    }
    if (!GenericValidator.isBlankOrNull(deliveryFromDate)) {
      sql = sql + " and date(r.bom_agree_date) >= STR_TO_DATE('" + deliveryFromDate + "','%d/%m/%Y')";
    }
    if (!GenericValidator.isBlankOrNull(deliveryEndDate)) {
      sql = sql + " and date(r.bom_agree_date) <= STR_TO_DATE('" + deliveryEndDate + "','%d/%m/%Y')";
    }
    sql = sql + " GROUP BY r.req_id ORDER BY r.req_id DESC";
    
    ArrayList list = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      RequestReportBean bean = null;
      int i = 1;
      while (rs.next())
      {
        bean = new RequestReportBean();
        bean.setId(i++);
        bean.setRequestNumber(rs.getString("request_number"));
        bean.setRequestDeliveryDate(DateUtil.formatDate(rs.getDate("bom_agree_date"), "dd/MM/yyyy"));
        
        bean.setProvideDate(DateUtil.formatDate(rs.getDate("provide_date"), "dd/MM/yyyy"));
        bean.setProjectName(rs.getString("projectName"));
        bean.setOrganization(rs.getString("organization"));
        bean.setDescription(rs.getString("description"));
        bean.setMatGroup(rs.getString("matGroup"));
        bean.setRequester(rs.getString("requester"));
        bean.setResponseEmp(rs.getString("responseEmp"));
        bean.setLastProcessDate(DateUtil.formatDate(rs.getDate("last_process_date"), "dd/MM/yyyy"));
        
        bean.setFinishOrderDate("");
        if (rs.getInt("remain_quantity") == rs.getInt("request_quantity"))
        {
          bean.setStatus("");
        }
        else if (rs.getInt("contract_approved_counter") + rs.getInt("cancel_counter") == rs.getInt("request_counter"))
        {
          bean.setStatus("Close");
          bean.setFinishOrderDate(DateUtil.formatDate(rs.getDate("last_date"), "dd/MM/yyyy"));
        }
        else
        {
          bean.setStatus("Ongoing");
        }
        list.add(bean);
      }
    }
    catch (SQLException sqle)
    {
      throw new Exception(sqle.getMessage());
    }
    catch (Exception ex)
    {
      throw new Exception(ex.getMessage());
    }
    finally
    {
      if (rs != null) {
        DBUtil.closeConnection(rs);
      }
    }
    return list;
  }
  
  public ArrayList getMCMaterialBalanceReport(int proId, int stoId, int matKind, String msvFromDate, String msvEndDate, String rfmFromDate, String rfmEndDate, String mivFromDate, String mivEndDate)
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "select distinct m.code as matCode, mrdet.item_no, m.name_vn as matName, mrdet.material_grade, mrdet.material_type, mrdet.size1, mrdet.size2, mrdet.size3, c.contract_number, c.kind, c.appendix_contract_number, cdet.manufacturer, mr.mrir_number, mrdet.heat_serial, mrdet.cert_no, mrdet.trace_no, mrdet.colour_code, ms.created_date as msvDate, ms.msv_number, u.unit_vn as matUnit, msdet.quantity as msvQuantity, rf.rfm_number, rfdet.quantity as rfmQuantity, re.fullname as requester, mi.miv_number, midet.quantity as mivQuantity, mi.created_date as mivDate, midet.pre_quantity, mrdet.location, mrdet.comment from request as r left join request_detail as rdet on rdet.req_id=r.req_id left join material as m on m.mat_id=rdet.mat_id left join unit as u on u.uni_id=m.uni_id left join contract_detail as cdet on cdet.reqDetail_id=rdet.det_id left join contract as c on c.con_id=cdet.con_id left join mrir_detail as mrdet on mrdet.req_detail_id=rdet.det_id left join mrir as mr on mrdet.mrir_id=mr.mrir_id left join msv as ms on ms.mrir_id=mr.mrir_id left join msv_detail as msdet on ms.msv_id=msdet.msv_id and msdet.req_detail_id=mrdet.req_detail_id left join rfm_detail as rfdet on rfdet.msv_id=ms.msv_id and rfdet.req_detail_id=mrdet.req_detail_id left join rfm as rf on rf.rfm_id=rfdet.rfm_id left join employee as re on re.emp_id=rf.created_emp left join miv as mi on mi.rfm_id=rf.rfm_id left join miv_detail as midet on mi.miv_id=midet.miv_id and midet.req_detail_id=mrdet.req_detail_id where 1";
    if (proId > 0) {
      sql = sql + " and r.org_id=" + proId;
    }
    if (stoId != 0) {
      sql = sql + " and ms.sto_id=" + stoId;
    }
    if (matKind != 0) {
      sql = sql + " and mrdet.mk_id=" + matKind;
    }
    if (!GenericValidator.isBlankOrNull(msvFromDate)) {
      sql = sql + " and date(ms.created_date) >= STR_TO_DATE('" + msvFromDate + "','%d/%m/%Y')";
    }
    if (!GenericValidator.isBlankOrNull(msvEndDate)) {
      sql = sql + " and date(ms.created_date) <= STR_TO_DATE('" + msvEndDate + "','%d/%m/%Y')";
    }
    if (!GenericValidator.isBlankOrNull(rfmFromDate)) {
      sql = sql + " and date(rf.created_date) >= STR_TO_DATE('" + rfmFromDate + "','%d/%m/%Y')";
    }
    if (!GenericValidator.isBlankOrNull(rfmEndDate)) {
      sql = sql + " and date(rf.created_date) <= STR_TO_DATE('" + rfmEndDate + "','%d/%m/%Y')";
    }
    if (!GenericValidator.isBlankOrNull(mivFromDate)) {
      sql = sql + " and date(mi.created_date) >= STR_TO_DATE('" + mivFromDate + "','%d/%m/%Y')";
    }
    if (!GenericValidator.isBlankOrNull(mivEndDate)) {
      sql = sql + " and date(mi.created_date) <= STR_TO_DATE('" + mivEndDate + "','%d/%m/%Y')";
    }
    ArrayList list = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      MCRequestReportFormBean bean = null;
      int i = 1;
      while (rs.next())
      {
        bean = new MCRequestReportFormBean();
        bean.setId(i++);
        bean.setMatCode(rs.getString("matCode"));
        bean.setItemNo(rs.getString("item_no"));
        if (rs.getString("matName") != null) {
          bean.setMatName(StringUtil.decodeString(rs.getString("matName")));
        }
        bean.setMatGrade(rs.getString("material_grade"));
        bean.setMatType(rs.getString("material_type"));
        bean.setMatSize1(rs.getString("size1"));
        bean.setMatSize2(rs.getString("size2"));
        bean.setMatSize3(rs.getString("size3"));
        if (rs.getInt("kind") == ContractBean.KIND_APPENDIX) {
          bean.setContractNumber(rs.getString("contract_number"));
        } else {
          bean.setContractNumber(rs.getString("contract_number"));
        }
        bean.setVendor(StringUtil.decodeString(rs.getString("manufacturer")));
        bean.setMrirNumber(rs.getString("mrir_number"));
        bean.setHeatNo(rs.getString("heat_serial"));
        bean.setMillNo(rs.getString("cert_no"));
        
        bean.setMsvDate(DateUtil.formatDate(rs.getDate("msvDate"), "dd/MM/yyyy"));
        bean.setMsvNumber(rs.getString("msv_number"));
        bean.setMatUnit(rs.getString("matUnit"));
        bean.setQuantity(rs.getDouble("msvQuantity"));
        bean.setRfmNumber(rs.getString("rfm_number"));
        bean.setRfmQuantity(rs.getDouble("rfmQuantity"));
        bean.setRequester(rs.getString("requester"));
        bean.setMivNumber(rs.getString("miv_number"));
        bean.setMivQuantity(rs.getDouble("mivQuantity"));
        bean.setMivDate(DateUtil.formatDate(rs.getDate("mivDate"), "dd/MM/yyyy"));
        if (!GenericValidator.isBlankOrNull(bean.getMivNumber())) {
          bean.setBalance(rs.getDouble("pre_quantity") - rs.getDouble("rfmQuantity"));
        } else {
          bean.setBalance(rs.getDouble("msvQuantity"));
        }
        bean.setReturnWorkShop(0.0D);
        bean.setMatNote(rs.getString("comment"));
        bean.setLocation(rs.getString("location"));
        list.add(bean);
      }
    }
    catch (SQLException sqle)
    {
      throw new Exception(sqle.getMessage());
    }
    catch (Exception ex)
    {
      throw new Exception(ex.getMessage());
    }
    finally
    {
      if (rs != null) {
        DBUtil.closeConnection(rs);
      }
    }
    return list;
  }
  
  public String getnestedParentOfOrg(int orgId)
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "select org_id from organization where org_id in (select parent_id from organization where org_id=" + orgId + ")";
    
    String result = "0";
    try
    {
      rs = DBUtil.executeQuery(sql);
      while (rs.next()) {
        result = result + "," + rs.getString("org_id");
      }
    }
    catch (SQLException sqle)
    {
      throw new Exception(sqle.getMessage());
    }
    catch (Exception ex)
    {
      throw new Exception(ex.getMessage());
    }
    finally
    {
      if (rs != null) {
        DBUtil.closeConnection(rs);
      }
    }
    return result;
  }
  
  public String getStatusForRequestReport(int reqDetailId)
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "Select distinct tdet.det_id, tl.let_id, bor.bor_id, bcr.bcr_id, te.te_id, ce.ce_id, bes.bes_id, tdet.ten_id as tenderId From tender_plan_detail as tdet left join tender_letter as tl on tdet.ten_id=tl.ten_id left join bid_closing_report as bcr on tdet.ten_id=bcr.ten_id left join bid_opening_report as bor on tdet.ten_id=bor.ten_id left join tech_eval as te on tdet.ten_id=te.ten_id left join com_eval as ce on tdet.ten_id=ce.ten_id left join bid_eval_sum as bes on tdet.ten_id=bes.ten_id where tdet.reqDetail_id=" + reqDetailId;
    
    String result = "";
    try
    {
      rs = DBUtil.executeQuery(sql);
      if ((rs.next()) && 
        (rs.getInt("det_id") > 0))
      {
        result = MCUtil.getBundleString("message.tenderplan.status.12");
        if (rs.getInt("let_id") != 0) {
          result = MCUtil.getBundleString("message.tenderplan.status.12");
        }
        if ((rs.getInt("bcr_id") != 0) || (rs.getInt("bor_id") != 0)) {
          result = MCUtil.getBundleString("message.tenderplan.status.34");
        }
        if (rs.getInt("te_id") != 0) {
          result = MCUtil.getBundleString("message.tenderplan.status.5");
        }
        if (rs.getInt("ce_id") != 0) {
          result = MCUtil.getBundleString("message.tenderplan.status.67");
        }
        if (rs.getInt("bes_id") != 0) {
          result = MCUtil.getBundleString("message.tenderplan.status.8");
        }
        if (rs.getInt("tenderId") != 0) {
          result = MCUtil.getBundleString("message.tenderplan.status.ordered");
        }
      }
    }
    catch (SQLException sqle)
    {
      throw new Exception(sqle.getMessage());
    }
    catch (Exception ex)
    {
      throw new Exception(ex.getMessage());
    }
    finally
    {
      if (rs != null) {
        DBUtil.closeConnection(rs);
      }
    }
    return result;
  }
  
  public ArrayList getStore2BalanceReport(int proId, int sto2Id, String nameVn, String code)
    throws Exception
  {
    ResultSet rs = null;
    
    String sql1 = "SELECT midet.req_detail_id, m.CODE, m.name_vn, co.NAME AS createdOrg, ro.NAME AS projectName, r.request_number, rf.rfm_number, miv.miv_number, midet.quantity AS mivQuantity, u.unit_vn AS matUnit, miv.created_date, '' AS workshop, '' AS usmNumber, 0 AS usmQuantity, '' AS usmMatUnit, '' AS usmDate, umi.current_quantity AS balance, '' AS note FROM miv AS miv LEFT JOIN miv_detail AS midet ON miv.miv_id=midet.miv_id LEFT JOIN rfm_detail AS rfdet ON midet.rfm_det_id=rfdet.det_id LEFT JOIN rfm AS rf ON rfdet.rfm_id=rf.rfm_id LEFT JOIN request_detail AS rdet ON midet.req_detail_id=rdet.det_id LEFT JOIN request AS r ON rdet.req_id=r.req_id LEFT JOIN organization AS ro ON ro.org_id=r.org_id LEFT JOIN organization AS co ON co.org_id=r.created_org LEFT JOIN material AS m ON midet.mat_id=m.mat_id LEFT JOIN unit AS u ON u.uni_id=m.uni_id LEFT JOIN used_material_import AS umi ON umi.req_detail_id=midet.req_detail_id AND umi.miv_id=midet.miv_id WHERE 1 ";
    
    String sql2 = "SELECT midet.req_detail_id, m.CODE, m.name_vn, co.NAME AS createdOrg, ro.NAME AS projectName, r.request_number, rf.rfm_number, miv.miv_number, midet.quantity AS mivQuantity, u.unit_vn AS matUnit, miv.created_date, wo.name AS workshop, d.usm_number AS usmNumber, -ddet.used_quantity AS usmQuantity, midet.unit AS usmMatUnit, d.update_date AS usmDate, 0 AS balance, '' AS note FROM used_material_diary AS d LEFT JOIN used_material_diary_detail AS ddet ON ddet.ums_id=d.ums_id LEFT JOIN miv AS miv ON miv.miv_id=ddet.miv_id LEFT JOIN miv_detail AS midet ON midet.miv_id=miv.miv_id AND midet.req_detail_id=ddet.req_detail_id LEFT JOIN rfm_detail AS rfdet ON midet.rfm_det_id=rfdet.det_id LEFT JOIN rfm AS rf ON rfdet.rfm_id=rf.rfm_id LEFT JOIN request_detail AS rdet ON midet.req_detail_id=rdet.det_id LEFT JOIN request AS r ON rdet.req_id=r.req_id LEFT JOIN organization AS ro ON ro.org_id=r.org_id LEFT JOIN organization AS co ON co.org_id=r.created_org LEFT JOIN material AS m ON midet.mat_id=m.mat_id LEFT JOIN unit AS u ON u.uni_id=m.uni_id LEFT JOIN organization AS wo ON wo.org_id=d.org_id WHERE 1 ";
    if (sto2Id > 0)
    {
      sql1 = sql1 + " and umi.sto_id=" + sto2Id;
      sql2 = sql2 + " and d.sto_id=" + sto2Id;
    }
    if (!GenericValidator.isBlankOrNull(nameVn))
    {
      sql1 = sql1 + " and (" + SQLSearchExpressionUtil.excuteExpression(nameVn, "m.name_vn") + ")";
      sql2 = sql2 + " and (" + SQLSearchExpressionUtil.excuteExpression(nameVn, "m.name_vn") + ")";
    }
    if (!GenericValidator.isBlankOrNull(code))
    {
      sql1 = sql1 + " and (" + SQLSearchExpressionUtil.excuteExpression(code, "m.code") + ")";
      sql2 = sql2 + " and (" + SQLSearchExpressionUtil.excuteExpression(code, "m.code") + ")";
    }
    sql1 = sql1 + " union " + sql2 + " ORDER BY 1";
    System.out.println("sql===" + sql1);
    
    ArrayList list = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql1);
      Store2Level2FormBean bean = null;
      int i = 1;
      while (rs.next()) {
        if (!GenericValidator.isBlankOrNull(rs.getString("req_detail_id")))
        {
          bean = new Store2Level2FormBean();
          bean.setId(i++);
          bean.setMatCode(rs.getString("code"));
          if (rs.getString("name_vn") != null) {
            bean.setMatName(StringUtil.decodeString(rs.getString("name_vn")));
          }
          bean.setOrganization(rs.getString("createdOrg"));
          bean.setProjectName(rs.getString("projectName"));
          bean.setRequestNumber(rs.getString("request_number"));
          bean.setRfmNumber(rs.getString("rfm_number"));
          bean.setMivNumber(rs.getString("miv_number"));
          bean.setMivQuantity(rs.getDouble("mivQuantity"));
          bean.setMatUnit(rs.getString("matUnit"));
          if (!GenericValidator.isBlankOrNull(rs.getString("created_date"))) {
            bean.setMivDate(DateUtil.formatDate(rs.getDate("created_date"), "dd/MM/yyyy"));
          }
          bean.setWorkshop(rs.getString("workshop"));
          bean.setUsmNumber(rs.getString("usmNumber"));
          bean.setUsmQuantity(rs.getDouble("usmQuantity"));
          bean.setUsmMatUnit(rs.getString("usmMatUnit"));
          bean.setMatNote(rs.getString("note"));
          if (!GenericValidator.isBlankOrNull(rs.getString("usmDate"))) {
            bean.setUsmDate(DateUtil.formatDate(rs.getDate("usmDate"), "dd/MM/yyyy"));
          }
          bean.setBalance(rs.getDouble("balance"));
          list.add(bean);
        }
      }
    }
    catch (SQLException sqle)
    {
      throw new Exception(sqle.getMessage());
    }
    catch (Exception ex)
    {
      throw new Exception(ex.getMessage());
    }
    finally
    {
      if (rs != null) {
        DBUtil.closeConnection(rs);
      }
    }
    return list;
  }
  
  public ArrayList getStore2BalanceReport2(int proId, int sto2Id, String nameVn, String code)
    throws Exception
  {
    ResultSet rs = null;
    
    String sql1 = "SELECT DISTINCT rfdet.req_detail_id, m.CODE, m.name_vn, u.unit_vn AS matUnit, rfdet.quantity AS quantity, r.request_number, rf.rfm_number, miv.miv_number, '' AS umsNumber, '' AS umsEmployee, ro.NAME AS projectName, '' AS rmsNumber, '' AS rmsEmployee, '' AS workshop, '' AS umsrmsDate, umi.current_quantity AS balance, '' AS note, '' as location  FROM used_material_import AS umi  LEFT JOIN miv_detail AS midet ON umi.req_detail_id=midet.req_detail_id AND umi.miv_id=midet.miv_id  LEFT JOIN miv AS miv ON miv.miv_id=midet.miv_id  LEFT JOIN rfm_detail AS rfdet ON midet.rfm_det_id=rfdet.det_id  LEFT JOIN rfm AS rf ON  rfdet.rfm_id=rf.rfm_id  LEFT JOIN request_detail AS rdet ON midet.req_detail_id=rdet.det_id  LEFT JOIN request AS r ON rdet.req_id=r.req_id  LEFT JOIN organization AS ro ON ro.org_id=r.org_id  LEFT JOIN material AS m ON midet.mat_id=m.mat_id  LEFT JOIN unit AS u ON u.uni_id=m.uni_id  WHERE 1 ";
    
    String sql2 = "SELECT DISTINCT ddet.req_detail_id, m.CODE, m.name_vn, u.unit_vn AS matUnit, ddet.used_quantity AS quantity, r.request_number, '' AS rfm_number, '' AS miv_number, d.usm_number AS umsNumber, e.fullname AS umsEmployee, ro.NAME AS projectName, '' AS rmsNumber, '' AS rmsEmployee, '' AS workshop, d.update_date AS umsrmsDate, 0 AS balance, '' AS note, d.location  FROM used_material_diary_detail AS ddet  LEFT JOIN used_material_diary AS d  ON ddet.ums_id=d.ums_id  LEFT JOIN request_detail AS rdet ON ddet.req_detail_id=rdet.det_id  LEFT JOIN request AS r ON rdet.req_id=r.req_id  LEFT JOIN organization AS ro ON ro.org_id=r.org_id  LEFT JOIN material AS m ON ddet.mat_id=m.mat_id  LEFT JOIN unit AS u ON u.uni_id=m.uni_id  LEFT JOIN employee AS e ON e.emp_id=d.created_emp  WHERE 1 ";
    
    String sql3 = "SELECT DISTINCT rddet.req_detail_id, m.CODE, m.name_vn, u.unit_vn AS matUnit, rddet.returned_quantity AS quantity, r.request_number, '' AS rfm_number, '' AS miv_number, '' AS umsNumber, '' AS umsEmployee, ro.NAME AS projectName, rd.rsm_number AS rmsNumber, e.fullname AS rmsEmployee, '' AS workshop, rd.update_date AS umsrmsDate, 0 AS balance, '' AS note, '' as location  FROM returned_material_diary_detail AS rddet  LEFT JOIN returned_material_diary AS rd  ON rddet.rms_id=rd.rms_id  LEFT JOIN request_detail AS rdet ON rddet.req_detail_id=rdet.det_id  LEFT JOIN request AS r ON rdet.req_id=r.req_id  LEFT JOIN organization AS ro ON ro.org_id=r.org_id  LEFT JOIN material AS m ON rddet.mat_id=m.mat_id  LEFT JOIN unit AS u ON u.uni_id=m.uni_id  LEFT JOIN employee AS e ON e.emp_id=rd.created_emp  WHERE 1 ";
    
    String sql4 = "SELECT DISTINCT msv_det.req_detail_id, m.CODE, m.name_vn, u.unit_vn AS matUnit, msv_det.quantity, r.request_number, '' AS rfm_number, dn.dn_number AS miv_number, msv.msv_number AS umsNumber, '' AS umsEmployee, ro.NAME AS projectName, '' AS rmsNumber, '' AS rmsEmployee, '' AS workshop, '' AS umsrmsDate, 0 AS balance, '' AS note, '' AS location   FROM msv AS msv LEFT JOIN msv_detail AS msv_det ON msv.msv_id=msv_det.msv_id  LEFT JOIN material AS m ON msv_det.mat_id=m.mat_id LEFT JOIN unit AS u ON m.uni_id=u.uni_id LEFT JOIN request_detail AS rdet ON msv_det.req_detail_id=rdet.det_id LEFT JOIN request AS r ON rdet.req_id=r.req_id LEFT JOIN delivery_notice AS dn ON msv.dn_id=dn.dn_id LEFT JOIN organization AS ro ON ro.org_id=r.org_id WHERE msv.TYPE=1";
    if (sto2Id > 0)
    {
      sql1 = sql1 + " and umi.sto_id=" + sto2Id;
      sql2 = sql2 + " and d.sto_id=" + sto2Id;
      sql3 = sql3 + " and rd.sto_id=" + sto2Id;
      sql4 = sql4 + " and msv.sto_id=" + sto2Id;
    }
    else if (proId > 0)
    {
      sql1 = sql1 + " and umi.sto_id IN (SELECT sto_id FROM store WHERE kind=" + StoreBean.LEVEL2 + " AND org_id=" + proId + ")";
      sql2 = sql2 + " and d.sto_id IN (SELECT sto_id FROM store WHERE kind=" + StoreBean.LEVEL2 + " AND org_id=" + proId + ")";
      sql3 = sql3 + " and rd.sto_id IN (SELECT sto_id FROM store WHERE kind=" + StoreBean.LEVEL2 + " AND org_id=" + proId + ")";
      sql4 = sql4 + " and msv.sto_id IN (SELECT sto_id FROM store WHERE kind=" + StoreBean.LEVEL1 + " AND org_id=" + proId + ")";
    }
    if (!GenericValidator.isBlankOrNull(nameVn))
    {
      sql1 = sql1 + " and (" + SQLSearchExpressionUtil.excuteExpression(nameVn, "m.name_vn") + ")";
      sql2 = sql2 + " and (" + SQLSearchExpressionUtil.excuteExpression(nameVn, "m.name_vn") + ")";
      sql3 = sql3 + " and (" + SQLSearchExpressionUtil.excuteExpression(nameVn, "m.name_vn") + ")";
      sql4 = sql4 + " and (" + SQLSearchExpressionUtil.excuteExpression(nameVn, "m.name_vn") + ")";
    }
    if (!GenericValidator.isBlankOrNull(code))
    {
      sql1 = sql1 + " and (" + SQLSearchExpressionUtil.excuteExpression(code, "m.code") + ")";
      sql2 = sql2 + " and (" + SQLSearchExpressionUtil.excuteExpression(code, "m.code") + ")";
      sql3 = sql3 + " and (" + SQLSearchExpressionUtil.excuteExpression(code, "m.code") + ")";
      sql4 = sql4 + " and (" + SQLSearchExpressionUtil.excuteExpression(code, "m.code") + ")";
    }
    sql1 = sql1 + " union " + sql2 + " union " + sql3 + " union " + sql4 + " ORDER BY 1";
    
    System.out.println("sql===" + sql1);
    
    ArrayList list = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql1);
      Store2LevelFormBean bean = null;
      int i = 1;
      while (rs.next()) {
        if (!GenericValidator.isBlankOrNull(rs.getString("req_detail_id")))
        {
          bean = new Store2LevelFormBean();
          bean.setId(i++);
          bean.setMatCode(rs.getString("code"));
          if (rs.getString("name_vn") != null) {
            bean.setMatName(StringUtil.decodeString(rs.getString("name_vn")));
          }
          bean.setMatUnit(rs.getString("matUnit"));
          bean.setQuantity(rs.getDouble("quantity"));
          bean.setRequestNumber(rs.getString("request_number"));
          if (!GenericValidator.isBlankOrNull(rs.getString("miv_number"))) {
            bean.setRfmmiv(rs.getString("miv_number"));
          } else {
            bean.setRfmmiv(rs.getString("rfm_number"));
          }
          bean.setUmsNumnber(rs.getString("umsNumber"));
          bean.setUmsEmployee(rs.getString("umsEmployee"));
          bean.setProjectName(rs.getString("projectName"));
          
          bean.setRmsNumber(rs.getString("rmsNumber"));
          bean.setRmsEmployee(rs.getString("rmsEmployee"));
          bean.setWorkshop(rs.getString("workshop"));
          if (!GenericValidator.isBlankOrNull(rs.getString("umsrmsDate"))) {
            bean.setUmsrmsDate(DateUtil.formatDate(rs.getDate("umsrmsDate"), "dd/MM/yyyy"));
          }
          bean.setMatNote(rs.getString("note"));
          bean.setLocation(rs.getString("location"));
          bean.setBalance(rs.getDouble("balance"));
          list.add(bean);
        }
      }
    }
    catch (SQLException sqle)
    {
      throw new Exception(sqle.getMessage());
    }
    catch (Exception ex)
    {
      throw new Exception(ex.getMessage());
    }
    finally
    {
      if (rs != null) {
        DBUtil.closeConnection(rs);
      }
    }
    return list;
  }
  
  public ArrayList getRequestReport(int proId, int reqId, String deliveryFromDate, String deliveryEndDate, String contractFromDate, String contractEndDate, String mrirFromDate, String mrirEndDate, String msvFromDate, String msvEndDate, int orgId)
  {
    throw new UnsupportedOperationException("Not yet implemented");
  }
  
  public ArrayList getRequestReport(int proId, String reqNumber, String deliveryFromDate, String deliveryEndDate, String contractFromDate, String contractEndDate, String mrirFromDate, String mrirEndDate, String msvFromDate, String msvEndDate, int orgId, String reqContract, String reqVendor, String deliveryDateAsContractFromDate, String deliveryDateAsContractToDate)
    throws Exception
  {
    ResultSet rs = null;
    String sql = "select distinct r.request_number, r.bom_agree_date, rdet.provide_date, p.name as projectName, co.name as organization, r.description, m.name_vn as matName, u.unit_vn as matUnit, rdet.additional_quantity, cdet.price, IF(cdet.det_id IS NULL, rdet.request_quantity, cdet.quantity) AS request_quantity, cdet.quantity*cdet.price as total, cdet.total as sum, cdet.vat, ic.invoice, c.contract_number, cdet.status as reqDetStatus, c.effected_date as contractDate, v.name as vendor, e.fullname as responseEmp, fe.fullname as followEmp, c.delivery, c.delivery_date, mpo.name_vn as poMatName, mpo.mat_id as poMat, m.mat_id as originalMat, rdet.det_id as reqDetId, c.currency, c.kind, c.appendix_contract_number, pc.contract_number as parentNumber, m.code, ic.invoice_date, mr.mrir_id, dndet.move_create_mrir, mr.mrir_number, mr.created_date as mrirDate, dndet.receive_mrir, dndet.move_create_msv, ms.msv_number, ms.created_date as msvDate, dndet.receive_msv, pay.move_accounting_date, pay.location, pay.pay_date, dn.actual_date as actualDate, mrdet.quantity as mrDetQuantity, tl.let_id, bor.bor_id, bcr.bcr_id, te.te_id, ce.ce_id, bes.bes_id, t.ten_id AS tenderId, t.created_emp as tenderCreatedEmp, cdet.det_id as conDetId, rdet.is_cancel, c.payment_status from request as r left join request_detail as rdet on rdet.req_id=r.req_id left join organization as p on r.org_id=p.org_id left join organization as co on r.created_org=co.org_id left join material as m on m.mat_id=rdet.original_mat_id left join unit as u on u.uni_id=m.uni_id left join contract_detail as cdet on cdet.reqDetail_id=rdet.det_id LEFT JOIN material AS mpo ON mpo.mat_id=cdet.mat_id_temp left join contract as c on c.con_id=cdet.con_id left join contract as pc on c.parent_id=pc.con_id left join vendor as v on v.ven_id=c.ven_id left join delivery_notice_detail as dndet on dndet.req_detail_id=rdet.det_id left join delivery_notice as dn on dn.con_id=c.con_id and dndet.dn_id=dn.dn_id left join mrir_detail as mrdet on mrdet.dnd_id=dndet.det_id  left join mrir as mr on mrdet.mrir_id=mr.mrir_id left join msv as ms on ms.mrir_id=mr.mrir_id left join payment_detail as pdet on pdet.con_id=c.con_id left join invoice_contract as ic on ic.ic_id=pdet.inv_id left join payment as pay on pay.pay_id=pdet.pay_id left join employee as e on e.emp_id=rdet.emp_id left join employee as fe on fe.emp_id=c.follow_emp left join tender_plan_detail as tdet on tdet.reqDetail_id=rdet.det_id AND tdet.mat_id=m.mat_id LEFT JOIN tender_plan AS t ON t.ten_id=tdet.ten_id left join tech_eval_detail as ted on ted.det_id_tp=tdet.det_id left join tender_letter as tl on tl.ten_id=t.ten_id LEFT JOIN bid_closing_report AS bcr ON t.ten_id=bcr.ten_id LEFT JOIN bid_opening_report AS bor ON t.ten_id=bor.ten_id LEFT JOIN tech_eval AS te ON t.ten_id=te.ten_id LEFT JOIN com_eval AS ce ON t.ten_id=ce.ten_id LEFT JOIN bid_eval_sum AS bes ON tdet.ten_id=bes.ten_id where (dn.dn_id IS NULL OR dn.kind=1 ) and r.created_emp<>1 AND (c.kind not in (" + ContractBean.KIND_ADJUSTMENT + "," + ContractBean.KIND_PRINCIPLE + ") OR c.kind IS NULL)";
    
    sql = sql + " and (r.STATUS=" + RequestBean.STATUS_HANDLE + " and (0 ";
    if (!GenericValidator.isBlankOrNull(getInvolvedOrgs()))
    {
      sql = sql + " or r.assigned_emp in (select emp_id from employee where org_id in(" + getInvolvedOrgs() + "))";
      sql = sql + " or ( r.created_emp in (select emp_id from employee where org_id in (" + getRequestOrg() + ")))";
      sql = sql + " or ( rdet.emp_id IN ( SELECT emp_id FROM employee WHERE org_id in(" + getInvolvedOrgs() + ")))";
    }
    if (getRequestEmp() > 0) {
      sql = sql + " or r.created_emp=" + getRequestEmp();
    }
    sql = sql + "))";
    if (proId > 0) {
      sql = sql + " and r.org_id=" + proId;
    }
    if (!GenericValidator.isBlankOrNull(reqNumber)) {
      sql = sql + " and (" + SQLSearchExpressionUtil.excuteExpression(reqNumber, "r.request_number") + ")";
    }
    if (!GenericValidator.isBlankOrNull(reqContract)) {
      sql = sql + " and (" + SQLSearchExpressionUtil.excuteExpression(reqContract, "c.contract_number") + ")";
    }
    if (!GenericValidator.isBlankOrNull(reqVendor)) {
      sql = sql + " and (" + SQLSearchExpressionUtil.excuteExpression(reqVendor, "v.name") + ")";
    }
    if (!GenericValidator.isBlankOrNull(deliveryFromDate)) {
      sql = sql + " and date(r.bom_agree_date) >= STR_TO_DATE('" + deliveryFromDate + "','%d/%m/%Y')";
    }
    if (!GenericValidator.isBlankOrNull(deliveryEndDate)) {
      sql = sql + " and date(r.bom_agree_date) <= STR_TO_DATE('" + deliveryEndDate + "','%d/%m/%Y')";
    }
    if (!GenericValidator.isBlankOrNull(contractFromDate)) {
      sql = sql + " and date(c.effected_date) >= STR_TO_DATE('" + contractFromDate + "','%d/%m/%Y')";
    }
    if (!GenericValidator.isBlankOrNull(contractEndDate)) {
      sql = sql + " and date(c.effected_date) <= STR_TO_DATE('" + contractEndDate + "','%d/%m/%Y')";
    }
    if (!GenericValidator.isBlankOrNull(mrirFromDate)) {
      sql = sql + " and date(mr.created_date) >= STR_TO_DATE('" + mrirFromDate + "','%d/%m/%Y')";
    }
    if (!GenericValidator.isBlankOrNull(mrirEndDate)) {
      sql = sql + " and date(mr.created_date) <= STR_TO_DATE('" + mrirEndDate + "','%d/%m/%Y')";
    }
    if (!GenericValidator.isBlankOrNull(msvFromDate)) {
      sql = sql + " and date(ms.created_date) >= STR_TO_DATE('" + msvFromDate + "','%d/%m/%Y')";
    }
    if (!GenericValidator.isBlankOrNull(msvEndDate)) {
      sql = sql + " and date(ms.created_date) <= STR_TO_DATE('" + msvEndDate + "','%d/%m/%Y')";
    }
    if (!StringUtil.isBlankOrNull(deliveryDateAsContractFromDate))
    {
      sql = sql + " AND DATE(c.delivery_date) >= STR_TO_DATE('" + deliveryDateAsContractFromDate + "','%d/%m/%Y')";
      if (!StringUtil.isBlankOrNull(deliveryDateAsContractToDate)) {
        sql = sql + " AND STR_TO_DATE('" + deliveryDateAsContractFromDate + "','%d/%m/%Y') <= STR_TO_DATE('" + deliveryDateAsContractToDate + "','%d/%m/%Y')";
      }
    }
    if (!StringUtil.isBlankOrNull(deliveryDateAsContractToDate))
    {
      sql = sql + " AND DATE(c.delivery_date) <= STR_TO_DATE('" + deliveryDateAsContractToDate + "','%d/%m/%Y')";
      if (!StringUtil.isBlankOrNull(deliveryDateAsContractFromDate)) {
        sql = sql + " AND STR_TO_DATE('" + deliveryDateAsContractFromDate + "','%d/%m/%Y') <= STR_TO_DATE('" + deliveryDateAsContractToDate + "','%d/%m/%Y')";
      }
    }
    ArrayList list = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      RequestReportBean bean = null;
      int i = 1;
      String req = "";
      int no = 0;
      while (rs.next()) {
        if ((rs.getInt("tenderCreatedEmp") != 1) || (rs.getInt("conDetId") != 0))
        {
          bean = new RequestReportBean();
          bean.setId(i++);
          bean.setRequestNumber(rs.getString("request_number"));
          if (!req.equals(bean.getRequestNumber()))
          {
            req = bean.getRequestNumber();
            no = 1;
          }
          else
          {
            no++;
          }
          bean.setNo(no + "");
          bean.setRequestDeliveryDate(DateUtil.formatDate(rs.getDate("bom_agree_date"), "dd/MM/yyyy"));
          bean.setProvideDate(DateUtil.formatDate(rs.getDate("provide_date"), "dd/MM/yyyy"));
          bean.setProjectName(rs.getString("projectName"));
          bean.setOrganization(rs.getString("organization"));
          bean.setDescription(rs.getString("description"));
          bean.setMatCode(rs.getString("code"));
          bean.setContractDate(DateUtil.formatDate(rs.getDate("contractDate"), "dd/MM/yyyy"));
          bean.setDeliveryDate(DateUtil.formatDate(rs.getDate("actualDate"), "dd/MM/yyyy"));
          bean.setMoveCreateMrir(DateUtil.formatDate(rs.getDate("move_create_mrir"), "dd/MM/yyyy"));
          bean.setMrirNumber(rs.getString("mrir_number"));
          bean.setMrirDate(DateUtil.formatDate(rs.getDate("mrirDate"), "dd/MM/yyyy"));
          bean.setReceiveMrir(DateUtil.formatDate(rs.getDate("receive_mrir"), "dd/MM/yyyy"));
          bean.setMoveCreateMsv(DateUtil.formatDate(rs.getDate("move_create_msv"), "dd/MM/yyyy"));
          bean.setMsvNumber(rs.getString("msv_number"));
          bean.setMsvDate(DateUtil.formatDate(rs.getDate("msvDate"), "dd/MM/yyyy"));
          bean.setReceiveMsv(DateUtil.formatDate(rs.getDate("receive_msv"), "dd/MM/yyyy"));
          bean.setMoveAccountingDate(DateUtil.formatDate(rs.getDate("move_accounting_date"), "dd/MM/yyyy"));
          bean.setVolume(rs.getString("location"));
          bean.setResponseEmp(rs.getString("responseEmp"));
          bean.setFollowEmp(rs.getString("followEmp"));
          bean.setPaymentDate(DateUtil.formatDate(rs.getDate("pay_date"), "dd/MM/yyyy"));
          bean.setContractDeliveryDate(DateUtil.formatDate(rs.getDate("delivery_date"), "dd/MM/yyyy"));
          if (rs.getString("matName") != null) {
            bean.setMatName(StringUtil.decodeString(rs.getString("matName")));
          }
          if ((rs.getInt("originalMat") != rs.getInt("poMat")) && 
            (rs.getString("poMatName") != null)) {
            bean.setPoMatName(StringUtil.decodeString(rs.getString("poMatName")));
          }
          bean.setMatUnit(rs.getString("matUnit"));
          bean.setQuantity(rs.getInt("request_quantity"));
          if (rs.getDouble("mrDetQuantity") > 0.0D)
          {
            bean.setMrirQuantity(rs.getDouble("mrDetQuantity"));
            bean.setTotal(rs.getDouble("price") * rs.getDouble("mrDetQuantity"));
            double vat = rs.getDouble("vat") / 100.0D;
            bean.setSum(rs.getDouble("price") * rs.getDouble("mrDetQuantity") * (vat + 1.0D));
          }
          else
          {
            bean.setTotal(rs.getDouble("total"));
            bean.setSum(rs.getDouble("sum"));
          }
          bean.setInvoice(rs.getString("invoice"));
          bean.setInvoiceDate(DateUtil.formatDate(rs.getDate("invoice_date"), "dd/MM/yyyy"));
          if (rs.getInt("payment_status") == ContractFormBean.STATUS_APPROVED)
          {
            bean.setCurrency(rs.getString("currency"));
            bean.setPrice(rs.getDouble("price"));
            bean.setVat(rs.getDouble("vat"));
            bean.setVendor(StringUtil.decodeString(rs.getString("vendor")));
          }
          else
          {
            bean.setTotal(0.0D);
            bean.setSum(0.0D);
          }
          if (rs.getInt("kind") == ContractBean.KIND_APPENDIX) {
            bean.setContractNumber(rs.getString("contract_number"));
          } else {
            bean.setContractNumber(rs.getString("contract_number"));
          }
          if (rs.getInt("mrir_id") > 0)
          {
            bean.setStatus(MCUtil.getBundleString("message.contract.process.status.deliveried"));
          }
          else if (!GenericValidator.isBlankOrNull(bean.getContractNumber()))
          {
            if (rs.getInt("payment_status") == 1)
            {
              bean.setStatus("dkgh " + rs.getString("delivery_date"));
            }
            else
            {
              bean.setStatus(MCUtil.getBundleString("message.tenderplan.status.8"));
              bean.setContractDate("");
              bean.setContractDeliveryDate("");
            }
          }
          else
          {
            if (rs.getInt("let_id") != 0) {
              bean.setStatus(MCUtil.getBundleString("message.tenderplan.status.12"));
            }
            if (rs.getInt("bes_id") != 0) {
              bean.setStatus(MCUtil.getBundleString("message.tenderplan.status.8"));
            }
          }
          if ((rs.getInt("reqDetStatus") == ContractFormBean.MATERIAL_STATUS_CANCEL) || (rs.getInt("is_cancel") == 1))
          {
            bean.setStatus(MCUtil.getBundleString("message.cancel"));
            bean.setMoveAccountingDate("");
            bean.setVolume("");
          }
          list.add(bean);
        }
      }
    }
    catch (SQLException sqle)
    {
      throw new Exception(sqle.getMessage());
    }
    catch (Exception ex)
    {
      throw new Exception(ex.getMessage());
    }
    finally
    {
      if (rs != null) {
        DBUtil.closeConnection(rs);
      }
    }
    return list;
  }
}
