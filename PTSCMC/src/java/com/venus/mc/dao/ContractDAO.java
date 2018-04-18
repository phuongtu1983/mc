package com.venus.mc.dao;

import com.venus.core.util.DateUtil;
import com.venus.core.util.GenericValidator;
import com.venus.core.util.StringUtil;
import com.venus.mc.bean.ContractBean;
import com.venus.mc.bean.ContractFollowBean;
import com.venus.mc.database.DBUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ContractFollowDAO
  extends BasicDAO
{
  public ArrayList getContractFollows()
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "SELECT c1.*,c2.contract_number,c2.kind,c2.appendix_contract_number,c2.created_date as created_time, c2.effected_date, c2.expire_date FROM contract_follow as c1 LEFT JOIN contract as c2 ON c1.con_id = c2.con_id order by fol_number asc";
    
    ArrayList contract_followList = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      ContractFollowBean contract_follow = null;
      while (rs.next())
      {
        contract_follow = new ContractFollowBean();
        contract_follow.setFolId(rs.getInt("fol_id"));
        contract_follow.setConId(rs.getInt("con_id"));
        contract_follow.setProId(rs.getInt("pro_id"));
        contract_follow.setOrgId(rs.getInt("org_id"));
        contract_follow.setFolNumber(rs.getString("fol_number"));
        contract_follow.setCreatedDate(DateUtil.formatDate(rs.getDate("created_date"), "dd/MM/yyyy"));
        contract_follow.setServiceType(rs.getString("service_type"));
        if (rs.getInt("kind") == ContractBean.KIND_APPENDIX) {
          contract_follow.setConNumber(rs.getString("contract_number"));
        } else {
          contract_follow.setConNumber(rs.getString("contract_number"));
        }
        contract_follow.setCreatedTime(DateUtil.formatDate(rs.getDate("created_time"), "dd/MM/yyyy"));
        contract_follow.setStartTime(DateUtil.formatDate(rs.getDate("effected_date"), "dd/MM/yyyy"));
        
        contract_follow.setEndTime(DateUtil.formatDate(rs.getDate("expire_date"), "dd/MM/yyyy"));
        contract_follow.setServiceAbility(rs.getString("service_ability"));
        contract_follow.setServiceLevel(rs.getString("service_level"));
        contract_follow.setServiceEquipment(rs.getString("service_equipment"));
        contract_follow.setServiceProgress(rs.getString("service_progress"));
        contract_follow.setServiceSafety(rs.getString("service_safety"));
        contract_follow.setServiceOther(rs.getString("service_other"));
        contract_follow.setServiceCooperate(rs.getString("service_cooperate"));
        contract_follow.setGoodAbility(rs.getString("good_ability"));
        contract_follow.setGoodProgress(rs.getString("good_progress"));
        contract_follow.setGoodCertificate(rs.getString("good_certificate"));
        contract_follow.setGoodQuality(rs.getString("good_quality"));
        contract_follow.setGoodOther(rs.getString("good_other"));
        contract_follow.setGoodCooperate(rs.getString("good_cooperate"));
        contract_follow.setComments(StringUtil.getString(rs.getString("comments")));
        
        contract_followList.add(contract_follow);
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
    return contract_followList;
  }
  
  public ContractFollowBean getContractFollow(String contract_followid)
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "SELECT c1.*,c2.created_date as created_time, c2.effected_date, c2.expire_date FROM contract_follow as c1 LEFT JOIN contract as c2 ON c1.con_id = c2.con_id where fol_id=" + contract_followid;
    try
    {
      rs = DBUtil.executeQuery(sql);
      if (rs.next())
      {
        ContractFollowBean contract_follow = new ContractFollowBean();
        contract_follow = new ContractFollowBean();
        contract_follow.setFolId(rs.getInt("fol_id"));
        contract_follow.setConId(rs.getInt("con_id"));
        contract_follow.setProId(rs.getInt("pro_id"));
        contract_follow.setOrgId(rs.getInt("org_id"));
        contract_follow.setFolNumber(rs.getString("fol_number"));
        contract_follow.setCreatedDate(DateUtil.formatDate(rs.getDate("created_date"), "dd/MM/yyyy"));
        contract_follow.setServiceType(rs.getString("service_type"));
        contract_follow.setCreatedTime(DateUtil.formatDate(rs.getDate("created_time"), "dd/MM/yyyy"));
        contract_follow.setStartTime(DateUtil.formatDate(rs.getDate("effected_date"), "dd/MM/yyyy"));
        contract_follow.setEndTime(DateUtil.formatDate(rs.getDate("expire_date"), "dd/MM/yyyy"));
        contract_follow.setServiceAbility(rs.getString("service_ability"));
        contract_follow.setServiceLevel(rs.getString("service_level"));
        contract_follow.setServiceEquipment(rs.getString("service_equipment"));
        contract_follow.setServiceProgress(rs.getString("service_progress"));
        contract_follow.setServiceSafety(rs.getString("service_safety"));
        contract_follow.setServiceOther(rs.getString("service_other"));
        contract_follow.setServiceCooperate(rs.getString("service_cooperate"));
        contract_follow.setGoodAbility(rs.getString("good_ability"));
        contract_follow.setGoodProgress(rs.getString("good_progress"));
        contract_follow.setGoodCertificate(rs.getString("good_certificate"));
        contract_follow.setGoodQuality(rs.getString("good_quality"));
        contract_follow.setGoodOther(rs.getString("good_other"));
        if (contract_follow.getGoodOther() != null) {
          contract_follow.getGoodOther().replace("null", "");
        }
        contract_follow.setGoodCooperate(rs.getString("good_cooperate"));
        contract_follow.setComments(StringUtil.getString(rs.getString("comments")));
        
        return contract_follow;
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
  
  public int insertContractFollow(ContractFollowBean bean)
    throws Exception
  {
    if (bean == null) {
      return 0;
    }
    int result = 0;
    try
    {
      String sql = "";
      String createdDate = "";
      String startTime = "";
      String endTime = "";
      if (GenericValidator.isBlankOrNull(bean.getCreatedDate())) {
        createdDate = "null";
      } else {
        createdDate = "STR_TO_DATE('" + bean.getCreatedDate() + "','%d/%m/%Y')";
      }
      if (GenericValidator.isBlankOrNull(bean.getStartTime())) {
        startTime = "null";
      } else {
        startTime = "STR_TO_DATE('" + bean.getStartTime() + "','%d/%m/%Y')";
      }
      if (GenericValidator.isBlankOrNull(bean.getEndTime())) {
        endTime = "null";
      } else {
        endTime = "STR_TO_DATE('" + bean.getEndTime() + "','%d/%m/%Y')";
      }
      sql = "insert into contract_follow(con_id,pro_id,org_id,fol_number,created_date,service_type,start_time,end_time,service_ability,service_level, service_equipment,service_progress,service_safety,service_other,service_cooperate,good_ability,good_progress,good_certificate,good_quality,good_other,good_cooperate,comments) values (" + bean.getConId() + "," + bean.getProId() + "," + bean.getOrgId() + ",'" + bean.getFolNumber() + "'," + createdDate + "" + ",'" + bean.getServiceType() + "'," + startTime + "," + endTime + "," + bean.getServiceAbility() + "," + bean.getServiceLevel() + "" + "," + bean.getServiceEquipment() + "," + bean.getServiceProgress() + "," + bean.getServiceSafety() + ",'" + bean.getServiceOther() + "'," + bean.getServiceCooperate() + "" + "," + bean.getGoodAbility() + "," + bean.getGoodProgress() + "," + bean.getGoodCertificate() + "," + bean.getGoodQuality() + "," + bean.getGoodOther() + "" + "," + bean.getGoodCooperate() + ",'" + bean.getComments() + "')";
      
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
    return result;
  }
  
  public void updateContractFollow(ContractFollowBean bean)
    throws Exception
  {
    if (bean == null) {
      return;
    }
    try
    {
      String sql = "update contract_follow set  con_id=" + bean.getConId() + "" + ", pro_id=" + bean.getProId() + "" + ", org_id=" + bean.getOrgId() + "" + ", fol_number='" + bean.getFolNumber() + "'" + ", created_date= STR_TO_DATE('" + bean.getCreatedDate() + "','%d/%m/%Y')" + ", service_type='" + bean.getServiceType() + "'" + ", start_time= STR_TO_DATE('" + bean.getStartTime() + "','%d/%m/%Y')" + ", end_time= STR_TO_DATE('" + bean.getEndTime() + "','%d/%m/%Y')" + ", service_ability=" + bean.getServiceAbility() + "" + ", service_level=" + bean.getServiceLevel() + "" + ", service_equipment=" + bean.getServiceEquipment() + "" + ", service_progress=" + bean.getServiceProgress() + "" + ", service_safety=" + bean.getServiceSafety() + "" + ", service_other='" + bean.getServiceOther() + "'" + ", service_cooperate=" + bean.getServiceCooperate() + "" + ", good_ability=" + bean.getGoodAbility() + "" + ", good_progress=" + bean.getGoodProgress() + "" + ", good_certificate=" + bean.getGoodCertificate() + "" + ", good_quality=" + bean.getGoodQuality() + "" + ", good_other='" + bean.getGoodOther() + "'" + ", good_cooperate=" + bean.getGoodCooperate() + "" + ", comments='" + bean.getComments() + "'" + " where fol_id=" + bean.getFolId();
      
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
  
  public int deleteContractFollow(String contract_followid)
    throws Exception
  {
    int result = 0;
    try
    {
      String sql = "delete from contract_follow  where fol_id=" + contract_followid;
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
    return result;
  }
  
  public ArrayList searchSimpleContractFollow(int fieldid, String strFieldvalue)
    throws Exception
  {
    String strFieldname = "";
    switch (fieldid)
    {
    case 1: 
      strFieldname = "c1.fol_number";
      break;
    case 2: 
      strFieldname = "c2.contract_number";
    case 3: 
      strFieldname = "c1.created_date";
    }
    ResultSet rs = null;
    
    String sql = "SELECT c1.*,c2.contract_number,c2.kind,c2.appendix_contract_number,c2.created_date as created_time, c2.effected_date, c2.expire_date FROM contract_follow as c1 LEFT JOIN contract as c2 ON c1.con_id = c2.con_id where 1 ";
    if ((fieldid > 0) && (!StringUtil.isBlankOrNull(strFieldvalue))) {
      sql = sql + "AND " + strFieldname + " LIKE '%" + strFieldvalue + "%'";
    }
    sql = sql + " order by fol_id desc";
    
    ArrayList contract_followList = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      ContractFollowBean contract_follow = null;
      while (rs.next())
      {
        contract_follow = new ContractFollowBean();
        contract_follow.setFolId(rs.getInt("fol_id"));
        contract_follow.setConId(rs.getInt("con_id"));
        contract_follow.setProId(rs.getInt("pro_id"));
        contract_follow.setOrgId(rs.getInt("org_id"));
        contract_follow.setFolNumber(rs.getString("fol_number"));
        contract_follow.setCreatedDate(rs.getString("created_date"));
        contract_follow.setServiceType(rs.getString("service_type"));
        if (rs.getInt("kind") == ContractBean.KIND_APPENDIX) {
          contract_follow.setConNumber(rs.getString("contract_number"));
        } else {
          contract_follow.setConNumber(rs.getString("contract_number"));
        }
        contract_follow.setCreatedTime(rs.getString("created_time"));
        contract_follow.setStartTime(rs.getString("effected_date"));
        contract_follow.setEndTime(rs.getString("expire_date"));
        contract_follow.setServiceAbility(rs.getString("service_ability"));
        contract_follow.setServiceLevel(rs.getString("service_level"));
        contract_follow.setServiceEquipment(rs.getString("service_equipment"));
        contract_follow.setServiceProgress(rs.getString("service_progress"));
        contract_follow.setServiceSafety(rs.getString("service_safety"));
        contract_follow.setServiceOther(rs.getString("service_other"));
        contract_follow.setServiceCooperate(rs.getString("service_cooperate"));
        contract_follow.setGoodAbility(rs.getString("good_ability"));
        contract_follow.setGoodProgress(rs.getString("good_progress"));
        contract_follow.setGoodCertificate(rs.getString("good_certificate"));
        contract_follow.setGoodQuality(rs.getString("good_quality"));
        contract_follow.setGoodOther(rs.getString("good_other"));
        contract_follow.setGoodCooperate(rs.getString("good_cooperate"));
        contract_follow.setComments(StringUtil.getString(rs.getString("comments")));
        contract_followList.add(contract_follow);
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
    return contract_followList;
  }
  
  public ArrayList searchAdvContractFollow(ContractFollowBean bean)
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "SELECT c1.*,c2.contract_number,c2.kind,c2.appendix_contract_number,c2.created_date as created_time, c2.effected_date, c2.expire_date FROM contract_follow as c1 LEFT JOIN contract as c2 ON c1.con_id = c2.con_id where 1 ";
    if (!StringUtil.isBlankOrNull(bean.getFolNumber())) {
      sql = sql + " AND fol_number LIKE '%" + bean.getFolNumber() + "%'";
    }
    if (!StringUtil.isBlankOrNull(bean.getCreatedDate())) {
      sql = sql + " AND c1.created_date LIKE '%" + bean.getCreatedDate() + "%'";
    }
    if (bean.getConId() > 0) {
      sql = sql + " AND c1.con_id LIKE '%" + bean.getConId() + "%'";
    }
    if (bean.getProId() > 0) {
      sql = sql + " AND pro_id LIKE '%" + bean.getProId() + "%'";
    }
    if (Integer.parseInt(bean.getServiceType()) > 0) {
      sql = sql + " AND service_type LIKE '%" + bean.getServiceType() + "%'";
    }
    if (bean.getOrgId() > 0) {
      sql = sql + " AND org_id LIKE '%" + bean.getOrgId() + "%'";
    }
    if (!StringUtil.isBlankOrNull(bean.getComments())) {
      sql = sql + " AND comments LIKE '%" + bean.getComments() + "%'";
    }
    sql = sql + " order by fol_id desc";
    
    ArrayList contract_followList = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      ContractFollowBean contract_follow = null;
      while (rs.next())
      {
        contract_follow = new ContractFollowBean();
        contract_follow.setFolId(rs.getInt("fol_id"));
        contract_follow.setConId(rs.getInt("con_id"));
        contract_follow.setProId(rs.getInt("pro_id"));
        contract_follow.setOrgId(rs.getInt("org_id"));
        contract_follow.setFolNumber(rs.getString("fol_number"));
        contract_follow.setCreatedDate(rs.getString("created_date"));
        contract_follow.setServiceType(rs.getString("service_type"));
        if (rs.getInt("kind") == ContractBean.KIND_APPENDIX) {
          contract_follow.setConNumber(rs.getString("contract_number"));
        } else {
          contract_follow.setConNumber(rs.getString("contract_number"));
        }
        contract_follow.setCreatedTime(rs.getString("created_time"));
        contract_follow.setStartTime(rs.getString("effected_date"));
        contract_follow.setEndTime(rs.getString("expire_date"));
        contract_follow.setServiceAbility(rs.getString("service_ability"));
        contract_follow.setServiceLevel(rs.getString("service_level"));
        contract_follow.setServiceEquipment(rs.getString("service_equipment"));
        contract_follow.setServiceProgress(rs.getString("service_progress"));
        contract_follow.setServiceSafety(rs.getString("service_safety"));
        contract_follow.setServiceOther(rs.getString("service_other"));
        contract_follow.setServiceCooperate(rs.getString("service_cooperate"));
        contract_follow.setGoodAbility(rs.getString("good_ability"));
        contract_follow.setGoodProgress(rs.getString("good_progress"));
        contract_follow.setGoodCertificate(rs.getString("good_certificate"));
        contract_follow.setGoodQuality(rs.getString("good_quality"));
        contract_follow.setGoodOther(rs.getString("good_other"));
        contract_follow.setGoodCooperate(rs.getString("good_cooperate"));
        contract_follow.setComments(StringUtil.getString(rs.getString("comments")));
        contract_followList.add(contract_follow);
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
    return contract_followList;
  }
  
  public boolean checkExisted(int id, String value)
    throws SQLException
  {
    ResultSet rs = null;
    try
    {
      rs = DBUtil.executeQuery("SELECT * FROM contract_follow WHERE fol_id <> " + id + " AND fol_number = '" + id + "'");
      boolean bool;
      if (rs.next()) {
        return true;
      }
      return false;
    }
    catch (SQLException ex)
    {
      throw new SQLException(ex.getMessage());
    }
    finally
    {
      if (rs != null) {
        DBUtil.closeConnection(rs);
      }
    }
  }
}
