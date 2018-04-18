package com.venus.mc.dao;

import com.venus.core.util.DateUtil;
import com.venus.core.util.GenericValidator;
import com.venus.core.util.NumberUtil;
import com.venus.core.util.StringUtil;
import com.venus.mc.bean.BidEvalSumBean;
import com.venus.mc.bean.BidEvalSumDetailBean;
import com.venus.mc.bean.BidEvalSumGroupBean;
import com.venus.mc.bean.BidEvalSumVendorBean;
import com.venus.mc.bean.TechEvalDetailBean;
import com.venus.mc.bean.TenderPlanBean;
import com.venus.mc.database.DBUtil;
import com.venus.mc.tenderplan.TenderPlanFormBean;
import com.venus.mc.util.MCUtil;
import com.venus.mc.workReportBean.BidEvalSumReportVendorBean;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class BidEvalSumDAO
{
  public ArrayList getBidEvalSums()
    throws Exception
  {
    ResultSet rs = null;
    String sql = "Select * From bid_eval_sum Order by bes_id ASC";
    
    ArrayList bid_eval_sumList = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      BidEvalSumBean bid_eval_sum = null;
      while (rs.next())
      {
        bid_eval_sum = new BidEvalSumBean();
        bid_eval_sum.setBesId(rs.getInt("bes_id"));
        bid_eval_sum.setTenId(rs.getInt("ten_id"));
        bid_eval_sum.setEmpId(rs.getInt("emp_id"));
        bid_eval_sum.setBesNumber(rs.getString("bes_number"));
        bid_eval_sum.setCreatedDate(DateUtil.formatDate(rs.getDate("created_date"), "dd/MM/yyyy"));
        bid_eval_sumList.add(bid_eval_sum);
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
    return bid_eval_sumList;
  }
  
  public BidEvalSumBean getBidEvalSum(int tenId)
    throws Exception
  {
    ResultSet rs = null;
    String sql = "Select * From bid_eval_sum Where ten_id=" + tenId;
    try
    {
      rs = DBUtil.executeQuery(sql);
      if (rs.next())
      {
        BidEvalSumBean bid_eval_sum = new BidEvalSumBean();
        bid_eval_sum.setBesId(rs.getInt("bes_id"));
        bid_eval_sum.setTenId(rs.getInt("ten_id"));
        bid_eval_sum.setEmpId(rs.getInt("emp_id"));
        bid_eval_sum.setBesNumber(rs.getString("bes_number"));
        bid_eval_sum.setCreatedDate(DateUtil.formatDate(rs.getDate("created_date"), "dd/MM/yyyy"));
        return bid_eval_sum;
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
    return null;
  }
  
  public BidEvalSumBean getBidEvalSumForReport(int besId)
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "SELECT t.created_date AS tenderPlanDate, t1.created_date AS tenderLetterDate,b.* FROM bid_eval_sum AS b LEFT JOIN tender_plan AS t ON t.ten_id = b.ten_id LEFT JOIN tender_letter AS t1 ON t1.ten_id = b.ten_id WHERE b.bes_id= " + besId;
    try
    {
      rs = DBUtil.executeQuery(sql);
      if (rs.next())
      {
        BidEvalSumBean bid_eval_sum = new BidEvalSumBean();
        bid_eval_sum.setBesId(rs.getInt("bes_id"));
        bid_eval_sum.setTenId(rs.getInt("ten_id"));
        bid_eval_sum.setEmpId(rs.getInt("emp_id"));
        bid_eval_sum.setBesNumber(rs.getString("bes_number"));
        bid_eval_sum.setCreatedDate(DateUtil.formatDate(rs.getDate("created_date"), "dd/MM/yyyy"));
        bid_eval_sum.setTenderPlanDate(DateUtil.formatDate(rs.getDate("tenderPlanDate"), "dd/MM/yyyy"));
        bid_eval_sum.setTenderLetterDate(DateUtil.formatDate(rs.getDate("tenderLetterDate"), "dd/MM/yyyy"));
        return bid_eval_sum;
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
    return null;
  }
  
  public double getVendorsBeforeForReport(int besId)
    throws Exception
  {
    ResultSet rs = null;
    String sql = "SELECT COUNT(t.ten_id) AS total FROM tender_evaluate_vendor AS t LEFT JOIN bid_eval_sum AS b ON b.ten_id = t.ten_id WHERE b.bes_id = " + besId + " GROUP BY t.ten_id ";
    double result = 0.0D;
    try
    {
      rs = DBUtil.executeQuery(sql);
      while (rs.next()) {
        result = rs.getDouble("total");
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
  
  public double getVendorsAfterForReport(int besId)
    throws Exception
  {
    ResultSet rs = null;
    String sql = "SELECT COUNT(t.ten_id) AS total FROM tender_evaluate_vendor AS t LEFT JOIN bid_eval_sum AS b ON b.ten_id = t.ten_id WHERE t.bidded=1 AND b.bes_id = " + besId + " GROUP BY t.ten_id ";
    double result = 0.0D;
    try
    {
      rs = DBUtil.executeQuery(sql);
      while (rs.next()) {
        result = rs.getDouble("total");
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
  
  public TenderPlanBean getTenderPlanReport(int besId)
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "SELECT t.ten_id, t.tender_number, t.form, t.eval_kind, t.bid_deadline, t.created_date as tenderDate , t1.created_date as tenderLetterDate  , t.bid_opening_date, t.bid_sending_date, t.evaluated_date, t.approved_date, t.contract_date, t.contract_exec_date, t.contract_exec_time, t.bid_sending_time, t.bid_deadline_time, t.bid_opening_time, t.evaluated_time, t.approved_time, t.contract_time, t.pack_name FROM tender_plan AS t left join tender_letter as t1 on t1.ten_id = t.ten_id LEFT JOIN bid_opening_report AS b ON b.ten_id = t.ten_id LEFT JOIN bid_eval_sum AS s ON s.ten_id = t.ten_id WHERE  s.bes_id = " + besId;
    try
    {
      rs = DBUtil.executeQuery(sql);
      if (rs.next())
      {
        TenderPlanBean bid_eval_sum = new TenderPlanBean();
        bid_eval_sum.setTenderNumber(rs.getString("tender_number"));
        bid_eval_sum.setForm(rs.getString("form"));
        bid_eval_sum.setEvalKind(rs.getInt("eval_kind"));
        bid_eval_sum.setTenId(rs.getInt("ten_id"));
        bid_eval_sum.setBidDeadline(DateUtil.formatDate(rs.getDate("bid_deadline"), "dd/MM/yyyy"));
        bid_eval_sum.setBidOpeningDate(DateUtil.formatDate(rs.getDate("bid_opening_date"), "dd/MM/yyyy"));
        bid_eval_sum.setCreatedDate(DateUtil.formatDate(rs.getDate("tenderDate"), "dd/MM/yyyy"));
        bid_eval_sum.setTenderLetterDate(DateUtil.formatDate(rs.getDate("tenderLetterDate"), "dd/MM/yyyy"));
        bid_eval_sum.setBidSendingDate(DateUtil.formatDate(rs.getDate("bid_sending_date"), "dd/MM/yyyy"));
        bid_eval_sum.setEvaluatedDate(DateUtil.formatDate(rs.getDate("evaluated_date"), "dd/MM/yyyy"));
        bid_eval_sum.setApprovedDate(DateUtil.formatDate(rs.getDate("approved_date"), "dd/MM/yyyy"));
        bid_eval_sum.setContractDate(DateUtil.formatDate(rs.getDate("contract_date"), "dd/MM/yyyy"));
        bid_eval_sum.setContractExecDate(DateUtil.formatDate(rs.getDate("contract_exec_date"), "dd/MM/yyyy"));
        bid_eval_sum.setBidSendingTime(rs.getString("bid_sending_time"));
        bid_eval_sum.setBidDeadlineTime(rs.getString("bid_deadline_time"));
        bid_eval_sum.setBidOpeningTime(rs.getString("bid_opening_time"));
        bid_eval_sum.setEvaluatedTime(rs.getString("evaluated_time"));
        bid_eval_sum.setApprovedTime(rs.getString("approved_time"));
        bid_eval_sum.setContractTime(rs.getString("contract_time"));
        bid_eval_sum.setContractExecTime(rs.getString("contract_exec_time"));
        bid_eval_sum.setPackName(rs.getString("pack_name"));
        if (rs.getInt("form") == TenderPlanFormBean.FORM_PRIVATE) {
          bid_eval_sum.setForm(MCUtil.getBundleString("message.tenderplan.form.private"));
        }
        if (rs.getInt("form") == TenderPlanFormBean.FORM_FAX) {
          bid_eval_sum.setForm(MCUtil.getBundleString("message.tenderplan.form.fax"));
        }
        return bid_eval_sum;
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
    return null;
  }
  
  public TechEvalDetailBean getTechEvalDetailReport(int tenId)
    throws Exception
  {
    ResultSet rs = null;
    
    TechEvalDetailBean detail = new TechEvalDetailBean();
    String sql = "SELECT ted.delivery_time, ted.other_condition FROM tech_eval_detail AS ted LEFT JOIN tech_eval_vendor AS tev ON tev.ter_id = ted.ter_id LEFT JOIN tech_eval AS te ON te.te_id = tev.te_id WHERE te.ten_id = " + tenId;
    try
    {
      rs = DBUtil.executeQuery(sql);
      while (rs.next())
      {
        if (rs.getString("delivery_time") != null) {
          detail.setDeliveryTime(StringUtil.decodeString(rs.getString("delivery_time")));
        } else {
          detail.setDeliveryTime("");
        }
        if (rs.getString("other_condition") != null) {
          detail.setOtherCondition(StringUtil.decodeString(rs.getString("other_condition")));
        } else {
          detail.setOtherCondition("");
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
    return detail;
  }
  
  public String getCurrencyForReport(int tenId, int evalKind)
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "";
    if (evalKind == 0) {
      sql = "select c.currency from com_eval_vendor as c left join com_eval as e on e.ce_id = c.ce_id where c.currency IS NOT NULL AND LENGTH(c.currency)>0 and e.ten_id = " + tenId;
    } else {
      sql = "select c.currency from com_eval_material_vendor as c left join com_eval as e on e.ce_id = c.ce_id where c.currency IS NOT NULL AND LENGTH(c.currency)>0 and e.ten_id = " + tenId;
    }
    String result = "";
    try
    {
      rs = DBUtil.executeQuery(sql);
      while (rs.next()) {
        result = rs.getString("currency");
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
  
  public void insertBidEvalSum(BidEvalSumBean bean)
    throws Exception
  {
    if (bean == null) {
      return;
    }
    try
    {
      String sql = "";
      
      sql = "Insert Into bid_eval_sum(ten_id, emp_id, bes_number, created_date) Values (" + bean.getTenId() + "," + bean.getEmpId() + ",'" + bean.getBesNumber() + "','" + bean.getCreatedDate() + "')";
      
      DBUtil.executeUpdate(sql);
    }
    catch (SQLException sqle)
    {
      throw new Exception(sqle.getMessage());
    }
    catch (Exception ex)
    {
      throw new Exception(ex.getMessage());
    }
    finally {}
  }
  
  public int insertBidEvalSumId(BidEvalSumBean bean)
    throws Exception
  {
    if (bean == null) {
      return 0;
    }
    int result = 0;
    try
    {
      String sql = "";
      
      sql = "Insert Into bid_eval_sum(ten_id, emp_id, bes_number, created_date) Values (" + bean.getTenId() + "," + bean.getEmpId() + ",'" + bean.getBesNumber() + "',now())";
      
      result = DBUtil.executeInsert(sql);
    }
    catch (SQLException sqle)
    {
      throw new Exception(sqle.getMessage());
    }
    catch (Exception ex)
    {
      throw new Exception(ex.getMessage());
    }
    finally {}
    return result;
  }
  
  public void updateTenderPlanDetail(int detId)
    throws Exception
  {
    ResultSet rs = null;
    try
    {
      String sql1 = "select ten_id, mat_id from tender_plan_detail as ted where ted.det_id=" + detId;
      rs = DBUtil.executeQuery(sql1);
      int tenId = 0;
      int matIdTemp = 0;
      while (rs.next())
      {
        tenId = rs.getInt("ten_id");
        matIdTemp = rs.getInt("mat_id");
      }
      String sql = "Update tender_plan_detail AS t Set  confirm = 1 Where ten_id=" + tenId + " AND mat_id = " + matIdTemp;
      
      DBUtil.executeUpdate(sql);
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
  }
  
  public void insertBidEvalSumGroup(BidEvalSumGroupBean bean)
    throws Exception
  {
    if (bean == null) {
      return;
    }
    try
    {
      String sql = "";
      sql = "Insert Into bid_eval_sum_group(bes_id, employee, position, note) Values (" + bean.getBesId() + ",'" + bean.getEmployee() + "','" + bean.getPosition() + "','" + bean.getNote() + "')";
      DBUtil.executeUpdate(sql);
    }
    catch (SQLException sqle)
    {
      throw new Exception(sqle.getMessage());
    }
    catch (Exception ex)
    {
      throw new Exception(ex.getMessage());
    }
  }
  
  public void insertBidEvalSumGroup(int besId, String employee, String position, String note)
    throws Exception
  {
    try
    {
      String sql = "";
      sql = "Insert Into bid_eval_sum_group(bes_id, employee, position, note) Values (" + besId + ",'" + employee + "','" + position + "','" + note + "')";
      DBUtil.executeUpdate(sql);
    }
    catch (SQLException sqle)
    {
      throw new Exception(sqle.getMessage());
    }
    catch (Exception ex)
    {
      throw new Exception(ex.getMessage());
    }
  }
  
  public void updateBidEvalSum(BidEvalSumBean bean)
    throws Exception
  {
    if (bean == null) {
      return;
    }
    try
    {
      String sql = "Update bid_eval_sum Set  ten_id=" + bean.getTenId() + ", emp_id=" + bean.getEmpId() + ", bes_number='" + bean.getBesNumber() + "'" + ", created_date=STR_TO_DATE('" + bean.getCreatedDate() + "','%d/%m/%Y')" + " Where bes_id=" + bean.getBesId();
      
      DBUtil.executeUpdate(sql);
    }
    catch (SQLException sqle)
    {
      throw new Exception(sqle.getMessage());
    }
    catch (Exception ex)
    {
      throw new Exception(ex.getMessage());
    }
  }
  
  public void updateRequestDetail(BidEvalSumBean bean)
    throws Exception
  {
    if (bean == null) {
      return;
    }
    ResultSet rs = null;
    try
    {
      String sql = "SELECT mat_id, mat_id_temp,reqDetail_id FROM tender_plan_detail WHERE ten_id = " + bean.getTenId() + " AND confirm = 1 AND mat_id_temp>0";
      rs = DBUtil.executeQuery(sql);
      while (rs.next())
      {
        sql = "UPDATE request_detail SET mat_id = " + rs.getString("mat_id_temp") + " WHERE ten_id = " + bean.getTenId() + " AND original_mat_id = " + rs.getString("mat_id");
        DBUtil.executeUpdate(sql);
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
  }
  
  public void updateBidEvalSumGroup(BidEvalSumGroupBean bean)
    throws Exception
  {
    if (bean == null) {
      return;
    }
    try
    {
      String sql = "";
      sql = "Update bid_eval_sum_group Set  bes_id='" + bean.getBesId() + "'" + ", employee='" + bean.getEmployee() + "'" + ", position='" + bean.getPosition() + "'" + ", note='" + bean.getNote() + "'" + " where besg_id=" + bean.getBesgId();
      DBUtil.executeUpdate(sql);
    }
    catch (SQLException sqle)
    {
      throw new Exception(sqle.getMessage());
    }
    catch (Exception ex)
    {
      throw new Exception(ex.getMessage());
    }
  }
  
  public ArrayList getBidEvalSumGroup(int tenId)
    throws Exception
  {
    ResultSet rs = null;
    String sql = "SELECT g.* FROM bid_eval_sum_group AS g LEFT JOIN bid_eval_sum AS s ON s.bes_id=g.bes_id WHERE s.ten_id = " + tenId;
    
    ArrayList list = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      BidEvalSumGroupBean bean = null;
      while (rs.next())
      {
        bean = new BidEvalSumGroupBean();
        bean.setBesgId(rs.getInt("besg_id"));
        bean.setBesId(rs.getInt("bes_id"));
        bean.setEmployee(rs.getString("employee"));
        bean.setPosition(rs.getString("position"));
        bean.setNote(StringUtil.getString(rs.getString("note")));
        list.add(bean);
      }
      if (rs != null) {
        DBUtil.closeConnection(rs);
      }
      if (bean == null)
      {
        String sql1 = "SELECT g.* FROM com_eval_group AS g LEFT JOIN com_eval AS v ON g.ce_id=v.ce_id WHERE v.ten_id = " + tenId;
        rs = DBUtil.executeQuery(sql1);
        while (rs.next())
        {
          bean = new BidEvalSumGroupBean();
          
          bean.setEmployee(rs.getString("employee"));
          bean.setPosition(rs.getString("position"));
          bean.setNote(rs.getString("note"));
          list.add(bean);
        }
        if (rs != null) {
          DBUtil.closeConnection(rs);
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
  
  public ArrayList getBidEvalSumGroupReport(int besId)
    throws Exception
  {
    ResultSet rs = null;
    String sql = "SELECT * FROM bid_eval_sum_group WHERE bes_id = " + besId + "  ORDER BY besg_id";
    ArrayList list = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      BidEvalSumGroupBean bean = null;
      while (rs.next())
      {
        bean = new BidEvalSumGroupBean();
        bean.setBesgId(rs.getInt("besg_id"));
        bean.setBesId(rs.getInt("bes_id"));
        bean.setEmployee(rs.getString("employee"));
        bean.setPosition(rs.getString("position"));
        bean.setNote(StringUtil.getString(rs.getString("note")));
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
  
  public ArrayList getTenderEvaluateVendorReport(int besId)
    throws Exception
  {
    ResultSet rs = null;
    String sql = "SELECT  v.name  FROM tender_evaluate_vendor AS t  LEFT JOIN vendor AS v ON t.ven_id = v.ven_id LEFT JOIN bid_eval_sum AS s ON s.ten_id = t.ten_id WHERE s.bes_id = " + besId;
    
    ArrayList list = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      BidEvalSumVendorBean bean = null;
      while (rs.next())
      {
        bean = new BidEvalSumVendorBean();
        bean.setVendorName(StringUtil.decodeString(rs.getString("name")));
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
  
  public ArrayList getTenderEvaluateVendorBiddedReport(int besId)
    throws Exception
  {
    ResultSet rs = null;
    String sql = "SELECT  v.name FROM tender_evaluate_vendor AS t LEFT JOIN vendor AS v ON t.ven_id = v.ven_id LEFT JOIN bid_eval_sum AS s ON s.ten_id = t.ten_id WHERE t.bidded= 1 AND bes_id = " + besId;
    
    ArrayList list = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      BidEvalSumVendorBean bean = null;
      while (rs.next())
      {
        bean = new BidEvalSumVendorBean();
        bean.setVendorName(StringUtil.decodeString(rs.getString("name")));
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
  
  public ArrayList getBidEvalSumVendorReport(int besId)
    throws Exception
  {
    ResultSet rs = null;
    String sql = "SELECT v.ven_id,v.name,b.delivery_time, b.payment_mode FROM bid_eval_sum_vendor AS b LEFT JOIN vendor AS v ON v.ven_id = b.ven_id WHERE b.bes_id = " + besId;
    
    ArrayList list = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      BidEvalSumVendorBean bean = null;
      while (rs.next())
      {
        bean = new BidEvalSumVendorBean();
        bean.setVendorName(StringUtil.decodeString(rs.getString("name")));
        bean.setDeliveryTime(rs.getString("delivery_time"));
        bean.setPaymentMode(rs.getString("payment_mode"));
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
  
  public ArrayList getBidEvalSumVendorIdReport(int besId)
    throws Exception
  {
    ResultSet rs = null;
    String sql = "SELECT besv_id FROM bid_eval_sum_vendor WHERE bes_id = " + besId;
    
    ArrayList list = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      BidEvalSumVendorBean bean = null;
      while (rs.next())
      {
        bean = new BidEvalSumVendorBean();
        bean.setBesvId(rs.getInt("besv_id"));
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
  
  public ArrayList getBidEvalSumDetailsReport(int besId, int besvId, String currency)
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "SELECT name_vn, unit, SUM(quantity) AS quantity , price, besv_id ,det_id, GROUP_CONCAT(DISTINCT request_number) request_number, SUM(quantity*price) AS total FROM (SELECT  t.det_id AS tenDetId, m.name_vn, b1.unit, b1.quantity, b1.price, b1.besv_id ,b1.det_id, r.request_number, b1.quantity*b1.price AS total FROM bid_eval_sum_detail AS b1 LEFT JOIN  bid_eval_sum_vendor AS b ON b.besv_id = b1.besv_id LEFT JOIN request_detail AS rdet ON rdet.det_id=b1.req_detail_id LEFT JOIN request AS r ON r.req_id=rdet.req_id LEFT JOIN tender_plan_detail AS t ON t.reqDetail_id=rdet.det_id and t.mat_id=rdet.original_mat_id LEFT JOIN material AS m ON m.mat_id = t.mat_id_temp WHERE b.bes_id = " + besId + " AND b1.besv_id = " + besvId + " ORDER BY t.det_id ASC) AS tbl" + " GROUP BY (name_vn)  ORDER BY tbl.tenDetId";
    
    ArrayList bid_eval_sum_detailList = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      BidEvalSumDetailBean bid_eval_sum_detail = null;
      while (rs.next())
      {
        bid_eval_sum_detail = new BidEvalSumDetailBean();
        bid_eval_sum_detail.setDetId(rs.getInt("det_id"));
        bid_eval_sum_detail.setBesvId(rs.getInt("besv_id"));
        bid_eval_sum_detail.setMatName(StringUtil.decodeString(rs.getString("name_vn")));
        bid_eval_sum_detail.setUnit(rs.getString("unit"));
        bid_eval_sum_detail.setQuantity(rs.getDouble("quantity"));
        bid_eval_sum_detail.setPrice(NumberUtil.formatMoneyDefault(rs.getDouble("price"), currency));
        bid_eval_sum_detail.setTotal(NumberUtil.formatMoneyDefault(rs.getDouble("total"), currency));
        bid_eval_sum_detail.setRequest(rs.getString("request_number"));
        bid_eval_sum_detailList.add(bid_eval_sum_detail);
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
    return bid_eval_sum_detailList;
  }
  
  public float getBidEvalSumDetailsReportVAT(int besId)
    throws Exception
  {
    ResultSet rs = null;
    String sql = "SELECT SUM(b1.total)*0.1 AS vat FROM bid_eval_sum_detail AS b1 LEFT JOIN  bid_eval_sum_vendor AS b ON b.besv_id = b1.besv_id LEFT JOIN material AS m ON m.mat_id = b1.mat_id WHERE b.bes_id = " + besId;
    
    float result = 0.0F;
    try
    {
      rs = DBUtil.executeQuery(sql);
      while (rs.next()) {
        result = rs.getFloat("vat");
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
  
  public int deleteBidEvalSumGroup(String besgId)
    throws Exception
  {
    int result = 0;
    try
    {
      String sql = "Delete From bid_eval_sum_group Where besg_id=" + besgId;
      result = DBUtil.executeUpdate(sql);
    }
    catch (SQLException sqle)
    {
      throw new Exception(sqle.getMessage());
    }
    catch (Exception ex)
    {
      throw new Exception(ex.getMessage());
    }
    finally {}
    return result;
  }
  
  public int deleteBidEvalSum(int besId)
    throws Exception
  {
    int result = 0;
    try
    {
      String sql = "Delete From bid_eval_sum  Where bes_id=" + besId;
      result = DBUtil.executeUpdate(sql);
    }
    catch (SQLException sqle)
    {
      throw new Exception(sqle.getMessage());
    }
    catch (Exception ex)
    {
      throw new Exception(ex.getMessage());
    }
    finally {}
    return result;
  }
  
  public ArrayList searchSimpleBidEvalSum(int fieldid, String strFieldvalue)
    throws Exception
  {
    String strFieldname = "";
    switch (fieldid)
    {
    case 1: 
      strFieldname = "ten_id";
    }
    ResultSet rs = null;
    
    String sql = "Select * From bid_eval_sum Where 1 ";
    if ((fieldid > 0) && (!StringUtil.isBlankOrNull(strFieldvalue))) {
      sql = sql + "AND " + strFieldname + " = " + strFieldvalue;
    }
    sql = sql + " Order by bes_id DESC";
    
    ArrayList bid_eval_sumList = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      BidEvalSumBean bid_eval_sum = null;
      while (rs.next())
      {
        bid_eval_sum = new BidEvalSumBean();
        bid_eval_sum.setBesId(rs.getInt("bes_id"));
        bid_eval_sum.setTenId(rs.getInt("ten_id"));
        bid_eval_sum.setEmpId(rs.getInt("emp_id"));
        bid_eval_sum.setBesNumber(rs.getString("bes_number"));
        bid_eval_sum.setCreatedDate(DateUtil.formatDate(rs.getDate("created_date"), "dd/MM/yyyy"));
        bid_eval_sumList.add(bid_eval_sum);
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
    return bid_eval_sumList;
  }
  
  public ArrayList searchAdvBidEvalSum(BidEvalSumBean bean)
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "Select bes_id, pro_id, name, physical_add, kind, comments From bid_eval_sum Where 1 ";
    if (bean.getBesId() != 0) {
      sql = sql + " AND bes_id =" + bean.getBesId();
    }
    if (bean.getTenId() != 0) {
      sql = sql + " AND ten_id =" + bean.getTenId();
    }
    if (bean.getEmpId() != 0) {
      sql = sql + " AND emp_id =" + bean.getEmpId();
    }
    if (!StringUtil.isBlankOrNull(bean.getBesNumber())) {
      sql = sql + " AND bes_number LIKE '%" + bean.getBesNumber() + "%'";
    }
    if (bean.getCreatedDate() != null) {
      sql = sql + " AND created_date = '" + bean.getCreatedDate() + "'";
    }
    sql = sql + " Order by bes_id DESC";
    
    ArrayList bid_eval_sumList = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      BidEvalSumBean bid_eval_sum = null;
      while (rs.next())
      {
        bid_eval_sum = new BidEvalSumBean();
        bid_eval_sum.setBesId(rs.getInt("bes_id"));
        bid_eval_sum.setTenId(rs.getInt("ten_id"));
        bid_eval_sum.setEmpId(rs.getInt("emp_id"));
        bid_eval_sum.setBesNumber(rs.getString("bes_number"));
        bid_eval_sum.setCreatedDate(DateUtil.formatDate(rs.getDate("created_date"), "dd/MM/yyyy"));
        bid_eval_sumList.add(bid_eval_sum);
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
    return bid_eval_sumList;
  }
  
  public ArrayList getBidEvalSumVendor(int besId, int evalKind, int tenId)
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "";
    if (evalKind == TenderPlanFormBean.EVAL_KIND_PART) {
      sql = "SELECT DISTINCT v.NAME, tev.certificate_attach, cemv.currency, cemv.rates, v.kind as venKind,(SELECT bv.delivery_time FROM bid_eval_sum_vendor AS bv WHERE bv.bes_id = " + besId + " AND bv.ven_id = v.ven_id) AS delivery_time" + ",(SELECT bv.besv_id FROM bid_eval_sum_vendor AS bv WHERE bv.bes_id = " + besId + " AND bv.ven_id = v.ven_id) as besv_id" + ",(select sum(besd.total) from bid_eval_sum_detail as besd, bid_eval_sum_vendor as besv where besd.besv_id=besv.besv_id and besv.ven_id=v.ven_id and besv.bes_id=" + besId + " group by besv.ven_id) as rates_after" + ", (select sum(cemd.price_certificate) from com_eval_material_detail as cemd, com_eval_material_vendor as cemv where cemd.cem_id=cemv.cem_id and cemv.ce_id=ce.ce_id ) as priceCertificate" + " FROM com_eval AS ce" + " LEFT JOIN com_eval_material_vendor AS cemv ON ce.ce_id=cemv.ce_id" + " LEFT JOIN tech_eval_vendor AS tev ON tev.ter_id = cemv.ter_id" + " LEFT JOIN tender_evaluate_vendor AS ter ON ter.tev_id = tev.tev_id" + " LEFT JOIN vendor AS v ON ter.ven_id=v.ven_id" + " WHERE ce.ten_id = " + tenId + " AND tev.ter_id IN (SELECT tv.ter_id FROM bid_eval_sum_vendor AS besv LEFT JOIN tender_evaluate_vendor AS ter ON ter.ven_id = besv.ven_id" + " LEFT JOIN tech_eval_vendor AS tv ON tv.tev_id = ter.tev_id)";
    } else if (evalKind == TenderPlanFormBean.EVAL_KIND_ALL) {
      sql = "select v.name, besv.delivery_time,tev.certificate_attach, cev.rates_after, cev.currency, besv.besv_id, cev.rates, v.kind as venKind, 0 as priceCertificate FROM bid_eval_sum AS bes  LEFT JOIN tech_eval AS te ON te.ten_id=bes.ten_id  LEFT JOIN tech_eval_vendor AS tev ON tev.te_id=te.te_id LEFT JOIN com_eval AS ce ON bes.ten_id=ce.ten_id LEFT JOIN com_eval_vendor AS cev ON ce.ce_id=cev.ce_id AND cev.ter_id=tev.ter_id LEFT JOIN bid_eval_sum_vendor AS besv ON besv.bes_id=bes.bes_id  LEFT JOIN vendor AS v ON besv.ven_id=v.ven_id where cev.rand = 1 AND bes.bes_id=" + besId + " GROUP BY besv.ven_id";
    }
    ArrayList bid_eval_sumList = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      BidEvalSumReportVendorBean bean = null;
      int i = 1;
      double amount = 0.0D;
      double rates = 0.0D;
      while (rs.next()) {
        if (rs.getInt("besv_id") != 0)
        {
          bean = new BidEvalSumReportVendorBean();
          bean.setN1("" + i);
          bean.setVendor(StringUtil.decodeString(rs.getString("name")));
          bean.setDeliveryDate(rs.getString("delivery_time"));
          bean.setCertificate(rs.getString("certificate_attach"));
          rates = rs.getDouble("rates");
          if (!GenericValidator.isBlankOrNull(rs.getString("rates_after")))
          {
            amount = rs.getDouble("rates_after") / rates;
            DecimalFormat twoDForm = new DecimalFormat("#.##");
            double a = Double.valueOf(twoDForm.format(amount)).doubleValue();
            double b = Double.valueOf(twoDForm.format(amount * 0.1D)).doubleValue();
            bean.setAmount3(a + "");
            bean.setAmount4(b + "");
            bean.setAmount5(Double.valueOf(twoDForm.format(a + b)) + "");
          }
          else
          {
            bean.setAmount3("0");
            bean.setAmount4("0");
            bean.setAmount5("0");
          }
          bean.setCurrency(rs.getString("currency"));
          bean.setBesvId(rs.getInt("besv_id"));
          bean.setVendorKind(rs.getInt("venKind"));
          bean.setPriceCertificate(rs.getDouble("priceCertificate"));
          
          bid_eval_sumList.add(bean);
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
    return bid_eval_sumList;
  }
  
  public String getMaterialBidEvalSumForEmail(int tenId, int evalKind)
    throws Exception
  {
    ResultSet rs = null;
    String results = "0";
    String sql = "";
    if (evalKind == TenderPlanFormBean.EVAL_KIND_PART) {
      sql = "SELECT DISTINCT tpd.mat_id_temp FROM com_eval AS ce  LEFT JOIN com_eval_material_vendor AS cmv ON cmv.ce_id = ce.ce_id  LEFT JOIN com_eval_material_detail AS ced ON ced.cem_id = cmv.cem_id  LEFT JOIN tender_plan_detail AS tpd ON tpd.det_id = ced.det_id_tp  LEFT JOIN material AS m ON m.mat_id = tpd.mat_id_temp  WHERE tpd.mat_id_temp<>tpd.mat_id AND tpd.mat_id_temp<>0 AND tpd.confirm = 1 AND ced.result = 1 AND m.CODE IS NULL  AND ce.ten_id= " + tenId;
    } else {
      sql = "SELECT DISTINCT tpd.mat_id_temp  FROM com_eval AS ce  LEFT JOIN com_eval_vendor AS cmv ON cmv.ce_id = ce.ce_id  LEFT JOIN com_eval_detail AS ced ON ced.cev_id = cmv.cev_id  LEFT JOIN tender_plan_detail AS tpd ON tpd.det_id = ced.det_id_tp  LEFT JOIN material AS m ON m.mat_id = tpd.mat_id_temp  WHERE tpd.mat_id_temp<>tpd.mat_id AND tpd.mat_id_temp<>0 AND tpd.confirm = 1 AND m.CODE IS NULL AND cmv.RAND = 1 AND ce.ten_id=" + tenId;
    }
    try
    {
      rs = DBUtil.executeQuery(sql);
      while (rs.next()) {
        results = results + "," + rs.getInt("mat_id_temp");
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
    return results;
  }
  
  public String getMaterialBidEvalSumForEmailNon(int tenId, int evalKind)
    throws Exception
  {
    ResultSet rs = null;
    String results = "0";
    String sql = "";
    if (evalKind == TenderPlanFormBean.EVAL_KIND_PART) {
      sql = "SELECT DISTINCT tpd.mat_id_temp FROM tender_plan_detail AS tpd LEFT JOIN material AS m ON m.mat_id = tpd.mat_id_temp WHERE tpd.mat_id_temp<>tpd.mat_id AND tpd.mat_id_temp<>0 AND tpd.confirm = 1 AND m.CODE IS NULL  AND tpd.ten_id= " + tenId;
    } else {
      sql = "SELECT DISTINCT tpd.mat_id_temp FROM tender_plan_detail AS tpd LEFT JOIN material AS m ON m.mat_id = tpd.mat_id_temp WHERE tpd.mat_id_temp<>tpd.mat_id AND tpd.mat_id_temp<>0 AND tpd.confirm = 1 AND m.CODE IS NULL AND tpd.ten_id= " + tenId;
    }
    try
    {
      rs = DBUtil.executeQuery(sql);
      while (rs.next()) {
        results = results + "," + rs.getInt("mat_id_temp");
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
    return results;
  }
}
