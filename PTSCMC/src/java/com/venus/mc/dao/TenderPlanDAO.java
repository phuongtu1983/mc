package com.venus.mc.dao;

import com.venus.core.util.DateUtil;
import com.venus.core.util.NumberUtil;
import com.venus.core.util.StringUtil;
import com.venus.mc.bean.BidEvalSumDetailBean;
import com.venus.mc.bean.ComEvalDetailBean;
import com.venus.mc.bean.TechEvalDetailBean;
import com.venus.mc.bean.TenderPlanDetailBean;
import com.venus.mc.database.DBUtil;
import com.venus.mc.tenderplan.TenderPlanFormBean;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TenderPlanDetailDAO
  extends BasicDAO
{
  public TenderPlanDetailBean getTenderPlanDetail(int detId)
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "Select * From tender_plan_detail Where det_id=" + detId;
    try
    {
      rs = DBUtil.executeQuery(sql);
      if (rs.next())
      {
        TenderPlanDetailBean tender_plan_detail = new TenderPlanDetailBean();
        tender_plan_detail.setDetId(rs.getInt("det_id"));
        tender_plan_detail.setTenId(rs.getInt("ten_id"));
        tender_plan_detail.setMatId(rs.getInt("mat_id"));
        
        tender_plan_detail.setUnit(rs.getString("unit"));
        tender_plan_detail.setQuantity(rs.getDouble("quantity"));
        
        return tender_plan_detail;
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
  
  public TechEvalDetailBean getTenderPlanDetailForTechEvalDetail(int terId)
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "SELECT * FROM tech_eval_detail WHERE ter_id = " + terId;
    try
    {
      rs = DBUtil.executeQuery(sql);
      if (rs.next())
      {
        TechEvalDetailBean tender_plan_detail = new TechEvalDetailBean();
        tender_plan_detail.setTenId(rs.getInt("t.ten_id"));
        tender_plan_detail.setMatId(rs.getInt("t.mat_id"));
        tender_plan_detail.setUnit(rs.getString("t.unit"));
        tender_plan_detail.setNameVn(StringUtil.decodeString(rs.getString("m.name_vn")));
        tender_plan_detail.setQty(rs.getString("t.quantity"));
        tender_plan_detail.setProvideDate(DateUtil.formatDate(rs.getDate("t.provide_date"), "dd/MM/yyyy"));
        tender_plan_detail.setResult(rs.getString("t2.result"));
        tender_plan_detail.setSpec(StringUtil.decodeString(rs.getString("spec")));
        tender_plan_detail.setDeliveryTime(rs.getString("delivery_time"));
        tender_plan_detail.setOtherCondition(rs.getString("other_condition"));
        tender_plan_detail.setEvalTbe(rs.getInt("eval_tbe"));
        tender_plan_detail.setCertificateAttach(rs.getString("certificate_attach"));
        
        return tender_plan_detail;
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
  
  public ArrayList getTechEvalDetail(int terId)
    throws Exception
  {
    ResultSet rs = null;
    
    int stt = 1;
    
    String sql = "SELECT time1,SUM(qt) AS qt,name_vn,mat_id, det_id, unit, spec, delivery_time, other_condition, eval_tbe  FROM (SELECT t.det_id AS tenDetId, tp.delivery_time AS time1,d.qty AS qt, t.ten_id,m.name_vn,m.mat_id AS mat_id, d.det_id, d.unit, d.spec, d.delivery_time, d.other_condition, d.eval_tbe FROM tech_eval_detail AS d LEFT JOIN tender_plan_detail AS t ON d.det_id_tp = t.det_id LEFT JOIN request_detail AS rdet ON t.reqDetail_id = rdet.det_id LEFT JOIN material AS m ON t.mat_id = m.mat_id LEFT JOIN tender_plan AS tp ON tp.ten_id = t.ten_id WHERE d.ter_id= " + terId + "  ORDER BY t.det_id ) AS tbl  GROUP BY name_vn ORDER BY tbl.tenDetId asc";
    
    ArrayList list = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      TechEvalDetailBean tender_plan_detail = null;
      while (rs.next())
      {
        tender_plan_detail = new TechEvalDetailBean();
        tender_plan_detail.setDetId(rs.getInt("det_id"));
        tender_plan_detail.setMatId(rs.getInt("mat_id"));
        tender_plan_detail.setUnit(rs.getString("unit"));
        tender_plan_detail.setNameVn(StringUtil.decodeString(rs.getString("name_vn")));
        tender_plan_detail.setQty(rs.getString("qt"));
        tender_plan_detail.setProvideDate(rs.getString("time1"));
        
        tender_plan_detail.setSpec(StringUtil.decodeString(rs.getString("spec")));
        tender_plan_detail.setDeliveryTime(rs.getString("delivery_time"));
        tender_plan_detail.setOtherCondition(rs.getString("other_condition"));
        tender_plan_detail.setEvalTbe(rs.getInt("eval_tbe"));
        tender_plan_detail.setStt(stt + "");
        list.add(tender_plan_detail);
        stt++;
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
  
  public ArrayList getMaterialBidEvalSum(int tenId, int evalKind)
    throws Exception
  {
    ResultSet rs = null;
    
    int stt = 1;
    String sql = "";
    if (evalKind == TenderPlanFormBean.EVAL_KIND_PART) {
      sql = "SELECT u.unit_vn, ced.det_id_tp, ced.unit, tpd.mat_id, m.name_vn, ted.spec, ted.delivery_time, tpd.confirm, ted.det_id  FROM com_eval AS ce  LEFT JOIN com_eval_material_vendor AS cmv ON cmv.ce_id = ce.ce_id  LEFT JOIN com_eval_material_detail AS ced ON ced.cem_id = cmv.cem_id  LEFT JOIN tender_plan_detail AS tpd ON tpd.det_id = ced.det_id_tp  LEFT JOIN material AS m ON m.mat_id = tpd.mat_id  LEFT JOIN tech_eval_detail AS ted ON ted.ter_id = cmv.ter_id LEFT JOIN material AS m1 ON m1.mat_id = ted.mat_id LEFT JOIN unit AS u ON u.uni_id = m1.uni_id WHERE ce.ten_id= " + tenId + " AND ced.result = 1 AND ted.det_id_tp = ced.det_id_tp AND m.name_vn <> ted.spec ";
    } else {
      sql = "SELECT u.unit_vn, ced.det_id_tp, ced.unit, tpd.mat_id, m.name_vn, ted.spec, ted.delivery_time, tpd.confirm, ted.det_id  FROM com_eval AS ce  LEFT JOIN com_eval_vendor AS cmv ON cmv.ce_id = ce.ce_id  LEFT JOIN com_eval_detail AS ced ON ced.cev_id = cmv.cev_id  LEFT JOIN tender_plan_detail AS tpd ON tpd.det_id = ced.det_id_tp  LEFT JOIN material AS m ON m.mat_id = tpd.mat_id  LEFT JOIN tech_eval_detail AS ted ON ted.ter_id = cmv.ter_id LEFT JOIN material AS m1 ON m1.mat_id = ted.mat_id LEFT JOIN unit AS u ON u.uni_id = m1.uni_id  WHERE ce.ten_id= " + tenId + " AND cmv.RAND = 1 AND ted.det_id_tp = ced.det_id_tp AND m.name_vn <> ted.spec";
    }
    sql = sql + "  GROUP BY tpd.mat_id ORDER BY ced.det_id_tp ";
    
    ArrayList list = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      TechEvalDetailBean tender_plan_detail = null;
      while (rs.next())
      {
        tender_plan_detail = new TechEvalDetailBean();
        tender_plan_detail.setDetId(rs.getInt("det_id"));
        tender_plan_detail.setDetIdTp(rs.getInt("det_id_tp"));
        tender_plan_detail.setMatId(rs.getInt("mat_id"));
        tender_plan_detail.setUnit(rs.getString("unit"));
        tender_plan_detail.setUnitTemp(rs.getString("unit_vn"));
        tender_plan_detail.setNameVn(StringUtil.decodeString(rs.getString("name_vn")));
        
        tender_plan_detail.setSpec(StringUtil.decodeString(rs.getString("spec")));
        tender_plan_detail.setNameTemp(rs.getString("spec").replace("&#10;", "^"));
        tender_plan_detail.setDeliveryTime(rs.getString("delivery_time"));
        
        tender_plan_detail.setConfirm(rs.getInt("confirm"));
        tender_plan_detail.setStt(stt + "");
        list.add(tender_plan_detail);
        stt++;
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
  
  public ArrayList getMaterialBidEvalSumNon(int tenId)
    throws Exception
  {
    ResultSet rs = null;
    
    int stt = 1;
    String sql = "";
    
    sql = "SELECT cd.mat_id as cb, b.det_id, u.unit_vn, tpd.det_id AS det_id_tp, b.unit, b.mat_id, m1.name_vn,m.name_vn AS name_temp, tpd.confirm FROM bid_eval_sum_detail AS b LEFT JOIN tender_plan_detail AS tpd ON tpd.reqDetail_id = b.req_detail_id  AND b.mat_id_temp = tpd.mat_id_temp LEFT JOIN material AS m ON m.mat_id = b.mat_id_temp LEFT JOIN material AS m1 ON m1.mat_id = b.mat_id LEFT JOIN unit AS u ON u.uni_id = m1.uni_id LEFT JOIN contract AS c ON c.ten_id = tpd.ten_id LEFT JOIN contract_detail AS cd ON cd.mat_id_temp = tpd.mat_id_temp AND cd.reqDetail_id = b.req_detail_id WHERE tpd.ten_id= " + tenId;
    
    sql = sql + "  GROUP BY tpd.mat_id ORDER BY tpd.det_id ";
    
    ArrayList list = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      TechEvalDetailBean tender_plan_detail = null;
      while (rs.next())
      {
        tender_plan_detail = new TechEvalDetailBean();
        tender_plan_detail.setDetId(rs.getInt("det_id"));
        tender_plan_detail.setDetIdTp(rs.getInt("det_id_tp"));
        tender_plan_detail.setMatId(rs.getInt("mat_id"));
        tender_plan_detail.setUnit(rs.getString("unit"));
        tender_plan_detail.setUnitTemp(rs.getString("unit_vn"));
        tender_plan_detail.setNameVn(StringUtil.decodeString(rs.getString("name_vn")));
        
        tender_plan_detail.setSpec(StringUtil.decodeString(rs.getString("name_temp")));
        tender_plan_detail.setNameTemp(rs.getString("name_temp"));
        tender_plan_detail.setDeliveryTime("");
        
        tender_plan_detail.setConfirm(rs.getInt("confirm"));
        tender_plan_detail.setStt(stt + "");
        if (rs.getString("cb") == null) {
          tender_plan_detail.setCb(0);
        } else {
          tender_plan_detail.setCb(rs.getInt("cb"));
        }
        list.add(tender_plan_detail);
        stt++;
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
  
  public int getTerId(String besvId, String tenId)
    throws Exception
  {
    ResultSet rs = null;
    
    int result = 0;
    try
    {
      String sql = "SELECT ter.ter_id FROM tech_eval_vendor AS ter  LEFT JOIN tender_evaluate_vendor AS tev ON ter.tev_id = tev.tev_id WHERE tev.ven_id = (SELECT ven_id FROM bid_eval_sum_vendor WHERE besv_id = " + besvId + ") AND tev.ten_id =" + tenId;
      
      rs = DBUtil.executeQuery(sql);
      while (rs.next()) {
        result = rs.getInt("ter_id");
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
  
  public ArrayList getBidEvalSumDetail(int besvId, int terId, int tenId, int evalKind)
    throws Exception
  {
    ResultSet rs = null;
    
    int stt = 1;
    String sql = "";
    if (evalKind == TenderPlanFormBean.EVAL_KIND_PART) {
      sql = sql + "SELECT delivery_time, det_id, besv_id, mat_id, unit, name_vn, SUM(tbl.quantity) AS qt, price, price_after, SUM(tbl.total) as total FROM (SELECT  ted.delivery_time,c.price_after, c.det_id_tp, d.besv_id, d.det_id,d.mat_id, d.price, d.quantity, d.total, d.unit, m.name_vn  FROM bid_eval_sum_detail AS d  LEFT JOIN bid_eval_sum_vendor AS bsv ON bsv.besv_id = d.besv_id  LEFT JOIN tender_evaluate_vendor AS tv ON tv.ven_id = bsv.ven_id  LEFT JOIN tech_eval_vendor AS tev ON tev.tev_id = tv.tev_id  LEFT JOIN tech_eval_detail AS ted ON ted.ter_id = tev.ter_id AND ted.mat_id = d.mat_id  LEFT JOIN com_eval_material_detail AS c ON c.det_id_tp = ted.det_id_tp AND c.result=1  LEFT JOIN com_eval_material_vendor AS cev ON cev.cem_id = c.cem_id AND cev.ter_id = tev.ter_id  LEFT JOIN material AS m ON d.mat_id = m.mat_id  WHERE d.besv_id = " + besvId + " AND tv.ten_id = " + tenId + " GROUP BY d.req_detail_id) AS tbl GROUP BY tbl.mat_id ORDER BY tbl.det_id_tp ";
    } else {
      sql = "SELECT ted.delivery_time, c.price_after, SUM(d.quantity) AS qt, SUM(d.total) AS total, d.det_id, d.besv_id, d.mat_id, d.unit,m.name_vn , d.price  FROM bid_eval_sum_detail AS d  LEFT JOIN request_detail AS rd ON rd.det_id = d.req_detail_id  LEFT JOIN material AS m ON rd.mat_id = m.mat_id  LEFT JOIN tender_plan_detail AS tp ON tp.reqDetail_id = d.req_detail_id  LEFT JOIN com_eval_detail AS c ON c.det_id_tp = tp.det_id   LEFT JOIN tech_eval_detail AS ted ON ted.`det_id_tp` = tp.`det_id`    INNER JOIN com_eval_vendor AS cv ON cv.cev_id = c.cev_id AND cv.RAND = 1  WHERE d.besv_id =  " + besvId + "  AND ted.ter_id = " + terId + "  GROUP BY m.name_vn  ORDER BY rd.req_id DESC, rd.det_id ASC ";
    }
    ArrayList list = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      BidEvalSumDetailBean tender_plan_detail = null;
      while (rs.next())
      {
        tender_plan_detail = new BidEvalSumDetailBean();
        
        tender_plan_detail.setBesvId(rs.getInt("besv_id"));
        tender_plan_detail.setMatId(rs.getInt("mat_id"));
        tender_plan_detail.setUnit(rs.getString("unit"));
        tender_plan_detail.setNameVn(StringUtil.decodeString(rs.getString("name_vn")));
        tender_plan_detail.setQuantity(rs.getDouble("qt"));
        tender_plan_detail.setPrice(NumberUtil.formatMoneyDefault(Double.valueOf(rs.getDouble("price"))));
        tender_plan_detail.setPriceAfter(NumberUtil.formatMoneyDefault(Double.valueOf(rs.getDouble("price_after"))));
        tender_plan_detail.setTotal(NumberUtil.formatMoneyDefault(Double.valueOf(rs.getDouble("total"))));
        tender_plan_detail.setDeliveryTime(rs.getString("delivery_time"));
        tender_plan_detail.setStt(stt + "");
        list.add(tender_plan_detail);
        stt++;
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
  
  public int getEvalKindTenderPlan(String tenId)
    throws Exception
  {
    ResultSet rs = null;
    
    int result = 0;
    try
    {
      String sql = "select * from tender_plan Where ten_id=" + tenId;
      
      rs = DBUtil.executeQuery(sql);
      while (rs.next()) {
        result = rs.getInt("eval_kind");
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
  
  public ComEvalDetailBean getTenderPlanDetailForComEvalDetail(int cevId)
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "Select d.*,m.name_vn  from com_eval_detail as d left join material as m on d.mat_id = m.mat_id where d.cev_id = " + cevId;
    try
    {
      rs = DBUtil.executeQuery(sql);
      if (rs.next())
      {
        ComEvalDetailBean tender_plan_detail = new ComEvalDetailBean();
        tender_plan_detail.setDetId(rs.getInt("det_id"));
        tender_plan_detail.setCevId(rs.getInt("cev_id"));
        tender_plan_detail.setMatId(rs.getInt("mat_id"));
        tender_plan_detail.setUnit(rs.getString("unit"));
        tender_plan_detail.setNameVn(StringUtil.decodeString(rs.getString("name_vn")));
        tender_plan_detail.setQuantity(rs.getDouble("quantity"));
        tender_plan_detail.setPrice(NumberUtil.formatMoneyDefault(Double.valueOf(rs.getDouble("price"))));
        tender_plan_detail.setTotal(NumberUtil.formatMoneyDefault(Double.valueOf(rs.getDouble("total"))));
        
        return tender_plan_detail;
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
  
  public ArrayList getComEvalDetail(int cevId)
    throws Exception
  {
    ResultSet rs = null;
    
    int stt = 1;
    
    String sql = "SELECT tbl.*, SUM(tbl.quantity) AS qt FROM ( SELECT m.name_vn,m.mat_id, d.det_id, d.cev_id, d.det_id_tp, d.manufacturer, d.unit, d.quantity, d.price, d.total, d.price_after FROM com_eval_detail AS d  LEFT JOIN tender_plan_detail AS t ON d.det_id_tp = t.det_id  LEFT JOIN request_detail AS rdet ON t.reqDetail_id = rdet.det_id  LEFT JOIN material AS m ON d.mat_id = m.mat_id  WHERE d.cev_id = " + cevId + " ORDER BY t.det_id) AS tbl " + " GROUP BY tbl.name_vn ORDER BY tbl.det_id_tp";
    
    ArrayList list = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      ComEvalDetailBean tender_plan_detail = null;
      while (rs.next())
      {
        tender_plan_detail = new ComEvalDetailBean();
        tender_plan_detail.setDetId(rs.getInt("det_id"));
        tender_plan_detail.setCevId(rs.getInt("cev_id"));
        tender_plan_detail.setMatId(rs.getInt("mat_id"));
        tender_plan_detail.setUnit(rs.getString("unit"));
        tender_plan_detail.setNameVn(StringUtil.decodeString(rs.getString("name_vn")));
        tender_plan_detail.setQuantity(rs.getDouble("qt"));
        tender_plan_detail.setPrice(NumberUtil.formatMoneyDefault(Double.valueOf(rs.getDouble("price"))));
        tender_plan_detail.setPriceAfter(NumberUtil.formatMoneyDefault(Double.valueOf(rs.getDouble("price_after"))));
        tender_plan_detail.setTotal(NumberUtil.formatMoneyDefault(Double.valueOf(rs.getDouble("total"))));
        tender_plan_detail.setStt(stt + "");
        list.add(tender_plan_detail);
        stt++;
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
  
  public ArrayList searchSimpleTenderPlanDetail(int fieldid, String strFieldvalue)
    throws Exception
  {
    String strFieldname = "";
    switch (fieldid)
    {
    case 1: 
      strFieldname = "description";
    }
    ResultSet rs = null;
    
    String sql = "Select * From tender_plan_detail Where 1 ";
    if ((fieldid > 0) && (!StringUtil.isBlankOrNull(strFieldvalue))) {
      sql = sql + "AND " + strFieldname + " LIKE '%" + strFieldvalue + "%'";
    }
    sql = sql + " Order by det_id DESC";
    
    ArrayList tender_plan_detailList = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      TenderPlanDetailBean tender_plan_detail = null;
      while (rs.next())
      {
        tender_plan_detail = new TenderPlanDetailBean();
        tender_plan_detail.setDetId(rs.getInt("det_id"));
        tender_plan_detail.setTenId(rs.getInt("ten_id"));
        tender_plan_detail.setMatId(rs.getInt("mat_id"));
        
        tender_plan_detail.setUnit(rs.getString("unit"));
        tender_plan_detail.setQuantity(rs.getDouble("quantity"));
        
        tender_plan_detailList.add(tender_plan_detail);
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
    return tender_plan_detailList;
  }
  
  public ArrayList searchAdvTenderPlanDetail(TenderPlanDetailBean bean)
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "Select * From tender_plan_detail Where 1 ";
    if (bean.getDetId() != 0) {
      sql = sql + " AND det_id =" + bean.getDetId();
    }
    if (bean.getTenId() != 0) {
      sql = sql + " AND ten_id =" + bean.getTenId();
    }
    if (bean.getMatId() != 0) {
      sql = sql + " AND mat_id =" + bean.getMatId();
    }
    if (!StringUtil.isBlankOrNull(bean.getUnit())) {
      sql = sql + " AND unit LIKE '%" + bean.getUnit() + "%'";
    }
    if (bean.getQuantity() != 0.0D) {
      sql = sql + " AND quantity =" + bean.getQuantity();
    }
    if (bean.getProvideDate() != null) {
      sql = sql + " AND provide_date = '" + bean.getProvideDate() + "'";
    }
    sql = sql + " Order by det_id DESC";
    
    ArrayList tender_plan_detailList = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      TenderPlanDetailBean tender_plan_detail = null;
      while (rs.next())
      {
        tender_plan_detail = new TenderPlanDetailBean();
        tender_plan_detail.setDetId(rs.getInt("det_id"));
        tender_plan_detail.setTenId(rs.getInt("ten_id"));
        tender_plan_detail.setMatId(rs.getInt("mat_id"));
        
        tender_plan_detail.setUnit(rs.getString("unit"));
        tender_plan_detail.setQuantity(rs.getDouble("quantity"));
        
        tender_plan_detailList.add(tender_plan_detail);
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
    return tender_plan_detailList;
  }
}
