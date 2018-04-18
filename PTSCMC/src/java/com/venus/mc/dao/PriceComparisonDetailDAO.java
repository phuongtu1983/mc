package com.venus.mc.dao;

import com.venus.core.util.DateUtil;
import com.venus.core.util.GenericValidator;
import com.venus.core.util.NumberUtil;
import com.venus.core.util.StringUtil;
import com.venus.mc.bean.PriceComparisonDetailBean;
import com.venus.mc.database.DBUtil;
import com.venus.mc.tenderplan.TenderPlanFormBean;
import java.io.PrintStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PriceComparisonDetailDAO
  extends BasicDAO
{
  public ArrayList getPriceComparisonDetails()
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "Select * From price_comparison_detail Order by det_id ASC";
    
    ArrayList price_comparison_detailList = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      PriceComparisonDetailBean price_comparison_detail = null;
      while (rs.next())
      {
        price_comparison_detail = new PriceComparisonDetailBean();
        price_comparison_detail.setDetId(rs.getString("det_id"));
        price_comparison_detail.setPcId(rs.getInt("pc_id"));
        price_comparison_detail.setMatId(rs.getInt("mat_id"));
        price_comparison_detail.setLatestPrice(rs.getString("latest_price"));
        price_comparison_detail.setInternetPrice(rs.getString("internet_price"));
        price_comparison_detail.setProposedPrice(rs.getString("proposed_price"));
        price_comparison_detailList.add(price_comparison_detail);
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
    return price_comparison_detailList;
  }
  
  public int getTenderPlanEvalKind(int tenId)
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "SELECT * FROM tender_plan WHERE ten_id = " + tenId;
    int result = 0;
    try
    {
      System.out.println("sql=" + sql);
      
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
  
  public int getTenderPlanEvalKinds(int pcId)
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "SELECT t.* FROM tender_plan AS t LEFT JOIN price_comparison AS p ON p.ten_id = t.ten_id WHERE p.pc_id = " + pcId;
    
    int result = 0;
    try
    {
      System.out.println("sql=" + sql);
      
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
  
  public ArrayList getPriceComparisonDetails(int tenId, int evalKind, int pcId)
    throws Exception
  {
    ResultSet rs = null;
    
    int i = 1;
    String sql = "";
    if (evalKind == TenderPlanFormBean.EVAL_KIND_ALL)
    {
      if (pcId == 0) {
        sql = "SELECT DISTINCT p.currency AS currency1, cv.currency as currency2,  p.*, rd.mat_id AS matId, m.name_vn, c.unit, MIN(c.price) AS proposedPrice, pd.det_id, pd.pc_id, pd.latest_price, pd.proposed_price, pd.internet_price FROM com_eval_detail AS c LEFT JOIN tender_plan_detail AS t ON t.det_id = c.det_id_tp LEFT JOIN request_detail AS rd ON rd.det_id = t.reqDetail_id LEFT JOIN com_eval_vendor AS cv ON cv.cev_id = c.cev_id LEFT JOIN material_price AS p ON p.mat_id = rd.mat_id AND p.con_id = (SELECT MAX(con_id) FROM  material_price AS m1 WHERE p.mat_id = m1.mat_id) LEFT JOIN material AS m ON rd.mat_id=m.mat_id LEFT JOIN price_comparison AS pc ON pc.ten_id = t.ten_id LEFT JOIN price_comparison_detail AS pd ON pd.pc_id = pc.pc_id WHERE cv.rand = 1  AND c.price > 0 AND t.ten_id = " + tenId + " GROUP BY det_id_tp ORDER BY det_id_tp ";
      } else {
        sql = "SELECT DISTINCT pd.currency_latest_price AS currency1, pd.currency_proposed_price AS currency2, m.mat_id AS matId, m.name_vn, u.unit_vn as unit, pd.* FROM price_comparison_detail AS pd LEFT JOIN price_comparison AS pc ON pc.pc_id = pd.pc_id LEFT JOIN material AS m ON pd.mat_id=m.mat_id LEFT JOIN unit AS u ON u.uni_id = m.uni_id WHERE pc.ten_id = " + tenId + " ORDER BY pd.det_id ASC ";
      }
    }
    else if (pcId == 0) {
      sql = "SELECT DISTINCT p.currency AS currency1, cv.currency as currency2, p.*, te.mat_id AS matId, m.name_vn, c.unit, MIN(c.price_ptscmc) AS proposedPrice, pd.det_id, pd.pc_id, pd.latest_price, pd.proposed_price, pd.internet_price FROM com_eval_material_detail AS c LEFT JOIN com_eval_material_vendor AS cv ON cv.cem_id = c.cem_id LEFT JOIN tech_eval_detail AS te ON te.det_id_tp = c.det_id_tp LEFT JOIN tech_eval_vendor AS r ON r.ter_id = te.ter_id LEFT JOIN tech_eval AS t ON t.te_id = r.te_id LEFT JOIN tender_plan_detail AS tp ON tp.det_id = te.det_id_tp LEFT JOIN request_detail AS rd ON rd.det_id = tp.reqDetail_id LEFT JOIN material_price AS p ON p.mat_id = rd.mat_id AND p.con_id = (SELECT MAX(con_id) FROM  material_price AS m1 WHERE p.mat_id = m1.mat_id) LEFT JOIN material AS m ON rd.mat_id=m.mat_id LEFT JOIN price_comparison AS pc ON pc.ten_id = t.ten_id LEFT JOIN price_comparison_detail AS pd ON pd.pc_id = pc.pc_id WHERE te.mat_id > 0 AND c.result = 1 AND r.ter_id = cv.ter_id AND t.ten_id = " + tenId + " GROUP BY te.det_id_tp ORDER BY te.det_id_tp ";
    } else {
      sql = "SELECT DISTINCT pd.currency_latest_price AS currency1, pd.currency_proposed_price AS currency2, m.mat_id AS matId, m.name_vn, u.unit_vn as unit, pd.* FROM price_comparison_detail AS pd LEFT JOIN price_comparison AS pc ON pc.pc_id = pd.pc_id LEFT JOIN material AS m ON pd.mat_id=m.mat_id LEFT JOIN unit AS u ON u.uni_id = m.uni_id WHERE pc.ten_id = " + tenId + " ORDER BY pd.det_id ASC ";
    }
    System.out.println("sql=" + sql);
    
    ArrayList price_comparison_detailList = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      PriceComparisonDetailBean price_comparison_detail = null;
      while (rs.next())
      {
        price_comparison_detail = new PriceComparisonDetailBean();
        price_comparison_detail.setStt(i);
        price_comparison_detail.setDetId(rs.getString("det_id"));
        price_comparison_detail.setPcId(rs.getInt("pc_id"));
        price_comparison_detail.setMatId(rs.getInt("matId"));
        price_comparison_detail.setMatName(StringUtil.decodeString(rs.getString("name_vn")));
        price_comparison_detail.setUnit(rs.getString("unit"));
        price_comparison_detail.setCurrency1(rs.getString("currency1"));
        price_comparison_detail.setCurrency2(rs.getString("currency2"));
        if (rs.getString("det_id") != null)
        {
          if (rs.getString("latest_price") != null) {
            price_comparison_detail.setLatestPrice(NumberUtil.formatMoneyDefault(Double.valueOf(rs.getDouble("latest_price"))));
          } else {
            price_comparison_detail.setLatestPrice("0");
          }
          if (rs.getString("proposed_price") != null) {
            price_comparison_detail.setProposedPrice(NumberUtil.formatMoneyDefault(Double.valueOf(rs.getDouble("proposed_price"))));
          } else {
            price_comparison_detail.setProposedPrice("0");
          }
          if (rs.getString("contract_number") != null) {
            price_comparison_detail.setContractNumber(rs.getString("contract_number"));
          } else {
            price_comparison_detail.setContractNumber("");
          }
          if (rs.getString("effected_date") != null) {
            price_comparison_detail.setEffectedDate(DateUtil.formatDate(rs.getDate("effected_date"), "dd/MM/yyyy"));
          } else {
            price_comparison_detail.setEffectedDate("");
          }
        }
        else
        {
          if (rs.getString("price") != null) {
            price_comparison_detail.setLatestPrice(NumberUtil.formatMoneyDefault(Double.valueOf(rs.getDouble("price"))));
          } else {
            price_comparison_detail.setLatestPrice("0");
          }
          if (rs.getString("proposedPrice") != null) {
            price_comparison_detail.setProposedPrice(NumberUtil.formatMoneyDefault(Double.valueOf(rs.getDouble("proposedPrice"))));
          } else {
            price_comparison_detail.setProposedPrice("0");
          }
          if (rs.getString("contract_number") != null) {
            price_comparison_detail.setContractNumber(rs.getString("contract_number"));
          } else {
            price_comparison_detail.setContractNumber("");
          }
          if (rs.getString("effected_date") != null) {
            price_comparison_detail.setEffectedDate(DateUtil.formatDate(rs.getDate("effected_date"), "dd/MM/yyyy"));
          } else {
            price_comparison_detail.setEffectedDate("");
          }
        }
        if (rs.getString("internet_price") != null) {
          price_comparison_detail.setInternetPrice(NumberUtil.formatMoneyDefault(Double.valueOf(rs.getDouble("internet_price"))));
        } else {
          price_comparison_detail.setInternetPrice("0");
        }
        price_comparison_detailList.add(price_comparison_detail);
        i++;
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
    return price_comparison_detailList;
  }
  
  public PriceComparisonDetailBean getPriceComparisonDetail(int detId)
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "Select * From price_comparison_detail Where det_id=" + detId;
    try
    {
      rs = DBUtil.executeQuery(sql);
      if (rs.next())
      {
        PriceComparisonDetailBean price_comparison_detail = new PriceComparisonDetailBean();
        price_comparison_detail.setDetId(rs.getString("det_id"));
        price_comparison_detail.setPcId(rs.getInt("pc_id"));
        price_comparison_detail.setMatId(rs.getInt("mat_id"));
        price_comparison_detail.setLatestPrice(NumberUtil.formatMoneyDefault(Double.valueOf(rs.getDouble("latest_price"))));
        price_comparison_detail.setInternetPrice(NumberUtil.formatMoneyDefault(Double.valueOf(rs.getDouble("internet_price"))));
        price_comparison_detail.setProposedPrice(NumberUtil.formatMoneyDefault(Double.valueOf(rs.getDouble("proposed_price"))));
        
        return price_comparison_detail;
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
  
  public void insertPriceComparisonDetail(PriceComparisonDetailBean bean)
    throws Exception
  {
    if (bean == null) {
      return;
    }
    try
    {
      String sql = "";
      String effectedDate = "";
      if (GenericValidator.isBlankOrNull(bean.getEffectedDate())) {
        effectedDate = "null";
      } else {
        effectedDate = "STR_TO_DATE('" + bean.getEffectedDate() + "','%d/%m/%Y')";
      }
      sql = "Insert Into price_comparison_detail(pc_id, mat_id,contract_number, effected_date, latest_price, internet_price, proposed_price, currency_latest_price, currency_proposed_price) Values (" + bean.getPcId() + "," + bean.getMatId() + ",'" + bean.getContractNumber() + "'," + effectedDate + "," + bean.getLatestPrice() + "," + bean.getInternetPrice() + "," + bean.getProposedPrice() + ",'" + bean.getCurrency1() + "','" + bean.getCurrency2() + "')";
      
      System.out.println("sql ====" + sql);
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
  
  public void updatePriceComparisonDetail(PriceComparisonDetailBean bean)
    throws Exception
  {
    if (bean == null) {
      return;
    }
    try
    {
      String effectedDate = "";
      if (GenericValidator.isBlankOrNull(bean.getEffectedDate())) {
        effectedDate = "null";
      } else {
        effectedDate = "STR_TO_DATE('" + bean.getEffectedDate() + "','%d/%m/%Y')";
      }
      String sql = "Update price_comparison_detail Set  pc_id=" + bean.getPcId() + ", mat_id=" + bean.getMatId() + ", latest_price=" + bean.getLatestPrice() + "" + ", internet_price=" + bean.getInternetPrice() + "" + ", proposed_price=" + bean.getProposedPrice() + "" + ", currency_latest_price='" + bean.getCurrency1() + "'" + ", currency_proposed_price='" + bean.getCurrency2() + "'" + ", effected_date=" + effectedDate + "" + ", contract_number='" + bean.getContractNumber() + "'" + " Where det_id=" + bean.getDetId();
      
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
  
  public int deletePriceComparisonDetail(int detId)
    throws Exception
  {
    int result = 0;
    try
    {
      String sql = "Delete From price_comparison_detail  Where det_id=" + detId;
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
}
