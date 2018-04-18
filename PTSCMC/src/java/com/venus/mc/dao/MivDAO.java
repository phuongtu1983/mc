package com.venus.mc.dao;

import com.venus.core.util.DateUtil;
import com.venus.core.util.GenericValidator;
import com.venus.core.util.StringUtil;
import com.venus.mc.bean.MaterialStoreRequestBean;
import com.venus.mc.bean.MivBean;
import com.venus.mc.bean.MivDetailBean;
import com.venus.mc.bean.MsvMaterialBean;
import com.venus.mc.bean.OrganizationBean;
import com.venus.mc.bean.RfmBean;
import com.venus.mc.database.DBUtil;
import com.venus.mc.process.store.miv.MivFormBean;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MivDAO
  extends BasicDAO
{
  public ArrayList getMivs()
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "Select * From miv where 1 order by miv_id desc";
    
    ArrayList mivList = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      MivBean bean = null;
      while (rs.next())
      {
        bean = new MivBean();
        bean.setMivId(rs.getInt("miv_id"));
        bean.setCreator(rs.getInt("created_emp"));
        bean.setMivNumber(rs.getString("miv_number"));
        bean.setCreatedDate(DateUtil.formatDate(rs.getDate("created_date"), "dd/MM/yyyy"));
        bean.setReceiver(rs.getInt("receive_emp"));
        bean.setRfmId(rs.getInt("rfm_id"));
        bean.setOrgId(rs.getInt("org_id"));
        bean.setStoId(rs.getInt("sto_id"));
        bean.setDescription(rs.getString("description"));
        bean.setTotal(rs.getDouble("total"));
        mivList.add(bean);
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
    return mivList;
  }
  
  public ArrayList getEMivs()
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "Select * From emiv where 1 order by emiv_id desc";
    
    ArrayList mivList = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      MivBean bean = null;
      while (rs.next())
      {
        bean = new MivBean();
        bean.setMivId(rs.getInt("emiv_id"));
        bean.setCreator(rs.getInt("created_emp"));
        bean.setMivNumber(rs.getString("emiv_number"));
        bean.setCreatedDate(DateUtil.formatDate(rs.getDate("created_date"), "dd/MM/yyyy"));
        bean.setReceiver(rs.getInt("receive_emp"));
        bean.setRfmId(rs.getInt("erfm_id"));
        bean.setOrgId(rs.getInt("org_id"));
        bean.setStoId(rs.getInt("sto_id"));
        bean.setDescription(rs.getString("description"));
        bean.setTotal(rs.getDouble("total"));
        mivList.add(bean);
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
    return mivList;
  }
  
  public int getRfmId(int mivId)
    throws Exception
  {
    ResultSet rs = null;
    String sql = "Select rfm_id From miv Where miv_id=" + mivId;
    try
    {
      rs = DBUtil.executeQuery(sql);
      if (rs.next()) {
        return rs.getInt("rfm_id");
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
    return 0;
  }
  
  public MivBean getMiv(int mivId)
    throws Exception
  {
    if (mivId == 0) {
      return null;
    }
    ResultSet rs = null;
    
    String sql = "Select m.*, r.request_org,r.org_id as proId, r.rfm_number, o.name as orgName, s.name as stoName, o.kind as orgKind, ro.name requestOrg, e.fullname as creatorName From miv as m left join rfm as r on m.rfm_id=r.rfm_id  left join organization as o on o.org_id=m.org_id  left join organization as ro on ro.org_id=r.request_org  left join store as s on s.sto_id=m.sto_id  left join employee as e on e.emp_id=m.created_emp  Where m.miv_id=" + mivId;
    try
    {
      rs = DBUtil.executeQuery(sql);
      if (rs.next())
      {
        MivBean bean = new MivBean();
        bean.setMivId(rs.getInt("miv_id"));
        bean.setCreator(rs.getInt("created_emp"));
        bean.setCreatorName(rs.getString("creatorName"));
        bean.setMivNumber(rs.getString("miv_number"));
        bean.setCreatedDate(DateUtil.formatDate(rs.getDate("created_date"), "dd/MM/yyyy"));
        bean.setReceiver(rs.getInt("receive_emp"));
        bean.setRfmId(rs.getInt("rfm_id"));
        bean.setRequestOrg(rs.getInt("request_org"));
        bean.setProId(rs.getInt("proId"));
        bean.setOrgId(rs.getInt("org_id"));
        if (rs.getInt("orgKind") == OrganizationBean.KIND_PROJECT) {
          bean.setWhichUseName(rs.getString("orgName") + "-" + rs.getString("requestOrg"));
        } else {
          bean.setWhichUseName(rs.getString("orgName"));
        }
        bean.setStoId(rs.getInt("sto_id"));
        bean.setStoreName(rs.getString("stoName"));
        bean.setDescription(rs.getString("description"));
        bean.setTotal(rs.getDouble("total"));
        bean.setRfmNumber(rs.getString("rfm_number"));
        bean.setType(rs.getInt("kind"));
        
        return bean;
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
  
  public MivBean getEMiv(int mivId)
    throws Exception
  {
    if (mivId == 0) {
      return null;
    }
    ResultSet rs = null;
    
    String sql = "Select m.*, r.request_org,r.org_id as proId, r.erfm_number, o.name as orgName, s.name as stoName, o.kind as orgKind, ro.name requestOrg, e.fullname as creatorName From emiv as m left join erfm as r on m.erfm_id=r.erfm_id  left join organization as o on o.org_id=m.org_id  left join organization as ro on ro.org_id=r.request_org  left join store as s on s.sto_id=m.sto_id  left join employee as e on e.emp_id=m.created_emp  Where m.emiv_id=" + mivId;
    try
    {
      rs = DBUtil.executeQuery(sql);
      if (rs.next())
      {
        MivBean bean = new MivBean();
        bean.setMivId(rs.getInt("emiv_id"));
        bean.setCreator(rs.getInt("created_emp"));
        bean.setCreatorName(rs.getString("creatorName"));
        bean.setMivNumber(rs.getString("emiv_number"));
        bean.setCreatedDate(DateUtil.formatDate(rs.getDate("created_date"), "dd/MM/yyyy"));
        bean.setReceiver(rs.getInt("receive_emp"));
        bean.setRfmId(rs.getInt("erfm_id"));
        bean.setRequestOrg(rs.getInt("request_org"));
        bean.setProId(rs.getInt("proId"));
        bean.setOrgId(rs.getInt("org_id"));
        if (rs.getInt("orgKind") == OrganizationBean.KIND_PROJECT) {
          bean.setWhichUseName(rs.getString("orgName") + "-" + rs.getString("requestOrg"));
        } else {
          bean.setWhichUseName(rs.getString("orgName"));
        }
        bean.setStoId(rs.getInt("sto_id"));
        bean.setStoreName(rs.getString("stoName"));
        bean.setDescription(rs.getString("description"));
        bean.setTotal(rs.getDouble("total"));
        bean.setRfmNumber(rs.getString("erfm_number"));
        bean.setType(rs.getInt("kind"));
        
        return bean;
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
  
  public int insertMiv(MivBean bean)
    throws Exception
  {
    if (bean == null) {
      return 0;
    }
    int result = 0;
    try
    {
      String sql = "";
      String orgId = "null";
      String rfmId = "null";
      String stoId = "null";
      String receiver = "null";
      String date = "sysdate()";
      if (!GenericValidator.isBlankOrNull(bean.getCreatedDate())) {
        date = "STR_TO_DATE('" + bean.getCreatedDate() + "','%d/%m/%Y')";
      } else {
        date = "sysdate()";
      }
      if (bean.getProId() != 0) {
        orgId = bean.getProId() + "";
      } else {
        orgId = bean.getOrgId() + "";
      }
      if (bean.getRfmId() != 0) {
        rfmId = bean.getRfmId() + "";
      }
      if (bean.getStoId() != 0) {
        stoId = bean.getStoId() + "";
      }
      if (bean.getReceiver() != 0) {
        receiver = bean.getReceiver() + "";
      }
      if (GenericValidator.isBlankOrNull(bean.getDescription())) {
        bean.setDescription("");
      }
      sql = "Insert Into miv(miv_number, created_date, created_emp, receive_emp, rfm_id, org_id, sto_id, description, total, kind) Values ('" + bean.getMivNumber() + "'," + date + "," + bean.getCreator() + "," + receiver + "," + rfmId + "," + orgId + "," + stoId + ",'" + bean.getDescription() + "'," + bean.getTotal() + "," + bean.getType() + ")";
      
      result = DBUtil.executeInsert(sql);
      if (result > 0)
      {
        sql = "update rfm set status=" + RfmBean.MIV_STATUS + " where rfm_id=" + rfmId;
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
    return result;
  }
  
  public int insertEMiv(MivBean bean)
    throws Exception
  {
    if (bean == null) {
      return 0;
    }
    int result = 0;
    try
    {
      String sql = "";
      String orgId = "null";
      String rfmId = "null";
      String stoId = "null";
      String receiver = "null";
      if (bean.getOrgId() != 0) {
        orgId = bean.getOrgId() + "";
      }
      if (bean.getRfmId() != 0) {
        rfmId = bean.getRfmId() + "";
      }
      if (bean.getStoId() != 0) {
        stoId = bean.getStoId() + "";
      }
      if (bean.getReceiver() != 0) {
        receiver = bean.getReceiver() + "";
      }
      sql = "Insert Into emiv(emiv_number, created_date, created_emp, receive_emp, erfm_id, org_id, sto_id, description, total, kind) Values ('" + bean.getMivNumber() + "',sysdate()," + bean.getCreator() + "," + receiver + "," + rfmId + "," + orgId + "," + stoId + ",'" + bean.getDescription() + "'," + bean.getTotal() + "," + bean.getType() + ")";
      
      result = DBUtil.executeInsert(sql);
      if (result > 0)
      {
        sql = "update erfm set status=" + RfmBean.MIV_STATUS + " where erfm_id=" + rfmId;
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
    return result;
  }
  
  public void updateRfm(int rfmId)
    throws Exception
  {
    try
    {
      String sql = "Update rfm Set  status= 1  Where rfm_id=" + rfmId;
      
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
  
  public void updateMiv(MivBean bean)
    throws Exception
  {
    if (bean == null) {
      return;
    }
    try
    {
      String orgId = "null";
      String rfmId = "null";
      String stoId = "null";
      String receiver = "null";
      if (bean.getProId() != 0) {
        orgId = bean.getProId() + "";
      } else {
        orgId = bean.getOrgId() + "";
      }
      if (bean.getRfmId() != 0) {
        rfmId = bean.getRfmId() + "";
      }
      if (bean.getStoId() != 0) {
        stoId = bean.getStoId() + "";
      }
      if (bean.getReceiver() != 0) {
        receiver = bean.getReceiver() + "";
      }
      String sql = "Update miv Set  miv_number='" + bean.getMivNumber() + "'" + ", created_emp=" + bean.getCreator() + ", receive_emp=" + receiver + ", rfm_id=" + rfmId + ", org_id=" + orgId + ", sto_id=" + stoId + ", description='" + bean.getDescription() + "'" + ", total=" + bean.getTotal() + ", kind=" + bean.getType() + " Where miv_id=" + bean.getMivId();
      
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
  
  public void updateEMiv(MivBean bean)
    throws Exception
  {
    if (bean == null) {
      return;
    }
    try
    {
      String orgId = "null";
      String rfmId = "null";
      String stoId = "null";
      String receiver = "null";
      if (bean.getProId() != 0) {
        orgId = bean.getProId() + "";
      }
      if (bean.getOrgId() != 0) {
        orgId = bean.getOrgId() + "";
      }
      if (bean.getRfmId() != 0) {
        rfmId = bean.getRfmId() + "";
      }
      if (bean.getStoId() != 0) {
        stoId = bean.getStoId() + "";
      }
      if (bean.getReceiver() != 0) {
        receiver = bean.getReceiver() + "";
      }
      String sql = "Update emiv Set  emiv_number='" + bean.getMivNumber() + "'" + ", created_date=STR_TO_DATE('" + bean.getCreatedDate() + "','%d/%m/%Y')" + ", created_emp=" + bean.getCreator() + ", receive_emp=" + receiver + ", erfm_id=" + rfmId + ", org_id=" + orgId + ", sto_id=" + stoId + ", description='" + bean.getDescription() + "'" + ", total=" + bean.getTotal() + ", kind=" + bean.getType() + " Where emiv_id=" + bean.getMivId();
      
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
  
  public int deleteMiv(int mivId)
    throws Exception
  {
    int result = 0;
    try
    {
      String sql = "";
      ArrayList arrDetail = getMivDetails(mivId);
      MivDetailBean det = null;
      for (int i = 0; i < arrDetail.size(); i++)
      {
        det = (MivDetailBean)arrDetail.get(i);
        deleteMivDetail(det.getDetId());
      }
      sql = "Delete From miv Where miv_id=" + mivId;
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
  
  public ArrayList searchSimpleMiv(int fieldid, String strFieldvalue, String storeId)
    throws Exception
  {
    String strFieldname = "";
    String strFrom = " miv as m";
    String strWhere = " Where 1";
    switch (fieldid)
    {
    case 1: 
      strFieldname = "m.miv_number";
      break;
    case 2: 
      strFrom = strFrom + ",rfm as r";
      strWhere = strWhere + " and m.rfm_id=r.rfm_id";
      strFieldname = "r.rfm_number";
    }
    ResultSet rs = null;
    
    String sql = "Select m.* From " + strFrom + strWhere;
    if ((fieldid > 0) && (!StringUtil.isBlankOrNull(strFieldvalue))) {
      sql = sql + " AND " + strFieldname + " LIKE '%" + strFieldvalue + "%'";
    }
    if ((!GenericValidator.isBlankOrNull(storeId)) && 
      (!storeId.equals("all"))) {
      sql = sql + " and m.sto_id=" + storeId;
    }
    sql = sql + " order by created_date DESC";
    
    ArrayList mivList = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      MivBean bean = null;
      while (rs.next())
      {
        bean = new MivBean();
        bean.setMivId(rs.getInt("miv_id"));
        bean.setCreator(rs.getInt("created_emp"));
        bean.setMivNumber(rs.getString("miv_number"));
        bean.setCreatedDate(DateUtil.formatDate(rs.getDate("created_date"), "dd/MM/yyyy"));
        bean.setReceiver(rs.getInt("receive_emp"));
        bean.setRfmId(rs.getInt("rfm_id"));
        bean.setOrgId(rs.getInt("org_id"));
        bean.setStoId(rs.getInt("sto_id"));
        bean.setDescription(rs.getString("description"));
        bean.setTotal(rs.getDouble("total"));
        mivList.add(bean);
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
    return mivList;
  }
  
  public ArrayList searchAdvMiv(MivBean bean)
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "Select * From tender_plan Where 1 ";
    
    sql = sql + " Order by ten_id DESC";
    
    ArrayList tender_planList = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
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
    return tender_planList;
  }
  
  public MivBean getMivByNumber(String mivNumber)
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "Select miv_id From miv Where miv_number='" + mivNumber + "'";
    try
    {
      rs = DBUtil.executeQuery(sql);
      if (rs.next())
      {
        int mivId = rs.getInt("miv_id");
        
        return getMiv(mivId);
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
  
  public MivBean getEMivByNumber(String mivNumber)
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "Select emiv_id From emiv Where emiv_number='" + mivNumber + "'";
    try
    {
      rs = DBUtil.executeQuery(sql);
      if (rs.next())
      {
        MivBean bean = new MivBean();
        bean.setMivId(rs.getInt("emiv_id"));
        
        return bean;
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
  
  public ArrayList getMivDetails(int mivId)
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "Select d.*,m.name_vn, m.code From miv_detail as d left join material as m on d.mat_id=m.mat_id  Where d.miv_id=" + mivId;
    
    ArrayList detailList = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      MivDetailBean details = null;
      while (rs.next())
      {
        details = new MivDetailBean();
        details.setDetId(rs.getInt("det_id"));
        details.setMivId(rs.getInt("miv_id"));
        details.setMatId(rs.getInt("mat_id"));
        details.setUnit(rs.getString("unit"));
        details.setQuantity(rs.getDouble("quantity"));
        details.setPrice(rs.getDouble("price"));
        details.setTotal(rs.getDouble("total"));
        details.setReqDetailId(rs.getInt("req_detail_id"));
        details.setMatName(StringUtil.decodeString(rs.getString("name_vn")));
        details.setMatCode(rs.getString("code"));
        detailList.add(details);
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
    return detailList;
  }
  
  public ArrayList getEMivDetails(int mivId)
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "Select d.*,m.name_vn, m.code From emiv_detail as d left join ematerial as m on d.emat_id=m.emat_id  Where d.emiv_id=" + mivId;
    
    ArrayList detailList = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      MivDetailBean details = null;
      while (rs.next())
      {
        details = new MivDetailBean();
        details.setDetId(rs.getInt("det_id"));
        details.setMivId(rs.getInt("emiv_id"));
        details.setMatId(rs.getInt("emat_id"));
        details.setUnit(rs.getString("unit"));
        details.setQuantity(rs.getDouble("quantity"));
        details.setPrice(rs.getDouble("price"));
        details.setTotal(rs.getDouble("total"));
        details.setMatName(StringUtil.decodeString(rs.getString("name_vn")));
        details.setMatCode(rs.getString("code"));
        detailList.add(details);
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
    return detailList;
  }
  
  public int insertMivDetail(MivDetailBean bean)
    throws Exception
  {
    if (bean == null) {
      return 0;
    }
    int result = 0;
    try
    {
      String sql = "";
      MsvDAO msvDAO = new MsvDAO();
      MsvMaterialBean detailBean = msvDAO.getMsvDetail(bean.getMivId(), bean.getReqDetailId());
      if (detailBean == null) {
        detailBean = new MsvMaterialBean();
      }
      sql = "Insert Into miv_detail(miv_id, mat_id, unit, quantity, price, total, req_detail_id, pre_quantity, rfm_det_id) Values (" + bean.getMivId() + "," + bean.getMatId() + ",'" + bean.getUnit() + "'," + bean.getQuantity() + "," + bean.getPrice() + "," + bean.getTotal() + "," + bean.getReqDetailId() + "," + detailBean.getCurrentQuantity() + "," + bean.getRfmDetId() + ")";
      
      result = DBUtil.executeInsert(sql);
      
      double temp = detailBean.getCurrentQuantity() - bean.getQuantity();
      detailBean.setCurrentQuantity(temp);
      msvDAO.updateMsvDetail(detailBean);
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
  
  public int insertEMivDetail(MivDetailBean bean)
    throws Exception
  {
    if (bean == null) {
      return 0;
    }
    int result = 0;
    try
    {
      String sql = "";
      sql = "Insert Into emiv_detail(emiv_id, emat_id, unit, quantity, price, total) Values (" + bean.getMivId() + "," + bean.getMatId() + ",'" + bean.getUnit() + "'," + bean.getQuantity() + "," + bean.getPrice() + "," + bean.getTotal() + ")";
      
      result = DBUtil.executeInsert(sql);
      sql = "update ematerial_store set  reserve_quantity=reserve_quantity-" + bean.getQuantity() + ", actual_quantity=actual_quantity-" + bean.getQuantity() + " where emat_id=" + bean.getMatId() + " and sto_id=" + bean.getStoId();
      
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
  
  public void updateMivDetail(MivDetailBean bean)
    throws Exception
  {
    if (bean == null) {
      return;
    }
    try
    {
      MivDetailBean detail = getMivDetail(bean.getDetId());
      MaterialStoreRequestBean msrBean = getMaterialStoreRequest(bean.getStoId(), bean.getReqDetailId());
      if (msrBean == null) {
        msrBean = new MaterialStoreRequestBean();
      }
      String sql = "Update miv_detail Set  quantity=" + bean.getQuantity() + ", pre_quantity=" + msrBean.getActualQuantity() + detail.getQuantity() + ", total=" + bean.getTotal() + " Where det_id=" + bean.getDetId();
      
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
  
  public void updateEMivDetail(MivDetailBean bean)
    throws Exception
  {
    if (bean == null) {
      return;
    }
    try
    {
      String sql = "Update emiv_detail Set  quantity=" + bean.getQuantity() + ", total=" + bean.getTotal() + " Where det_id=" + bean.getDetId();
      
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
  
  public int deleteMivDetail(int detId)
    throws Exception
  {
    int result = 0;
    try
    {
      MivDetailBean bean = getMivDetail(detId);
      if (bean != null)
      {
        MivBean miv = getMiv(bean.getMivId());
        String sql = "Delete From miv_detail Where det_id=" + detId;
        result = DBUtil.executeUpdate(sql);
        miv.setTotal(miv.getTotal() - bean.getTotal());
        updateMiv(miv);
        sql = "update material_store_request set  reserve_quantity=reserve_quantity+" + bean.getQuantity() + ", actual_quantity=actual_quantity+" + bean.getQuantity() + " where req_detail_id=" + bean.getReqDetailId() + " and msv_id = (SELECT msv_id FROM rfm_detail WHERE det_id = " + bean.getRfmDetId() + ")";
        
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
    return result;
  }
  
  public MivFormBean getMivToPrint(int mivId)
    throws Exception
  {
    if (mivId == 0) {
      return null;
    }
    ResultSet rs = null;
    
    String sql = "Select m.*, r.rfm_number, e.fullname, s.name, rec.fullname as receiverName, o.name as organizationName From miv as m left join rfm as r on m.rfm_id=r.rfm_id  left join employee as e on m.created_emp=e.emp_id  left join employee as rec on m.receive_emp=rec.emp_id  left join store as s on m.sto_id=s.sto_id  left join organization as o on rec.org_id=o.org_id  Where m.miv_id=" + mivId;
    try
    {
      rs = DBUtil.executeQuery(sql);
      if (rs.next())
      {
        MivFormBean bean = new MivFormBean();
        bean.setMivId(rs.getInt("miv_id"));
        bean.setCreator(rs.getInt("created_emp"));
        bean.setCreatorName(rs.getString("fullname"));
        bean.setMivNumber(rs.getString("miv_number"));
        bean.setCreatedDate(DateUtil.formatDate(rs.getDate("created_date"), "dd/MM/yyyy"));
        bean.setReceiver(rs.getInt("receive_emp"));
        bean.setReceiverName(rs.getString("receiverName"));
        bean.setRfmId(rs.getInt("rfm_id"));
        bean.setOrgId(rs.getInt("org_id"));
        bean.setWhichUseName(rs.getString("organizationName"));
        bean.setStoId(rs.getInt("sto_id"));
        bean.setStoreName(rs.getString("name"));
        bean.setDescription(rs.getString("description"));
        bean.setTotal(rs.getDouble("total"));
        bean.setRfmNumber(rs.getString("rfm_number"));
        bean.setType(rs.getInt("kind"));
        
        return bean;
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
  
  public MivFormBean getEMivToPrint(int mivId)
    throws Exception
  {
    if (mivId == 0) {
      return null;
    }
    ResultSet rs = null;
    
    String sql = "Select m.*, r.erfm_number, e.fullname, s.name, rec.fullname as receiverName, o.name as organizationName From emiv as m left join erfm as r on m.erfm_id=r.erfm_id  left join employee as e on m.created_emp=e.emp_id  left join employee as rec on m.receive_emp=rec.emp_id  left join store as s on m.sto_id=s.sto_id  left join organization as o on m.org_id=m.org_id  Where m.emiv_id=" + mivId;
    try
    {
      rs = DBUtil.executeQuery(sql);
      if (rs.next())
      {
        MivFormBean bean = new MivFormBean();
        bean.setMivId(rs.getInt("emiv_id"));
        bean.setCreator(rs.getInt("created_emp"));
        bean.setCreatorName(rs.getString("fullname"));
        bean.setMivNumber(rs.getString("emiv_number"));
        bean.setCreatedDate(DateUtil.formatDate(rs.getDate("created_date"), "dd/MM/yyyy"));
        bean.setReceiver(rs.getInt("receive_emp"));
        bean.setReceiverName(rs.getString("receiverName"));
        bean.setRfmId(rs.getInt("erfm_id"));
        bean.setOrgId(rs.getInt("org_id"));
        bean.setWhichUseName(rs.getString("organizationName"));
        bean.setStoId(rs.getInt("sto_id"));
        bean.setStoreName(rs.getString("name"));
        bean.setDescription(rs.getString("description"));
        bean.setTotal(rs.getDouble("total"));
        bean.setRfmNumber(rs.getString("erfm_number"));
        bean.setType(rs.getInt("kind"));
        
        return bean;
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
  
  public MivDetailBean getMivDetail(int detId)
    throws Exception
  {
    if (detId == 0) {
      return null;
    }
    ResultSet rs = null;
    
    String sql = "Select * From miv_detail Where det_id=" + detId;
    try
    {
      rs = DBUtil.executeQuery(sql);
      if (rs.next())
      {
        MivDetailBean bean = new MivDetailBean();
        bean.setDetId(detId);
        bean.setMivId(rs.getInt("miv_id"));
        bean.setMatId(rs.getInt("mat_id"));
        bean.setUnit(rs.getString("unit"));
        bean.setQuantity(rs.getDouble("quantity"));
        bean.setPrice(rs.getDouble("price"));
        bean.setTotal(rs.getDouble("total"));
        bean.setReqDetailId(rs.getInt("req_detail_id"));
        bean.setRfmDetId(rs.getInt("rfm_det_id"));
        return bean;
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
  
  public MaterialStoreRequestBean getMaterialStoreRequest(int stoId, int reqDetailId)
    throws Exception
  {
    if (reqDetailId == 0) {
      return null;
    }
    ResultSet rs = null;
    
    String sql = "Select * From material_store_request Where req_detail_id=" + reqDetailId + " and sto_id=" + stoId;
    try
    {
      rs = DBUtil.executeQuery(sql);
      if (rs.next())
      {
        MaterialStoreRequestBean bean = new MaterialStoreRequestBean();
        bean.setMsrId(rs.getInt("msr_id"));
        bean.setStoId(rs.getInt("sto_id"));
        bean.setMatId(rs.getInt("mat_id"));
        bean.setPrice(rs.getDouble("price"));
        bean.setReserveQuantity(rs.getDouble("reserve_quantity"));
        bean.setAvailableQuantity(rs.getDouble("available_quantity"));
        bean.setActualQuantity(rs.getDouble("actual_quantity"));
        bean.setReqDetailId(rs.getInt("req_detail_id"));
        
        return bean;
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
  
  public int deleteUsedMaterialMiv(int mivId)
    throws Exception
  {
    int result = 0;
    try
    {
      String sql = "";
      ArrayList arrDetail = getMivDetails(mivId);
      MivDetailBean det = null;
      for (int i = 0; i < arrDetail.size(); i++)
      {
        det = (MivDetailBean)arrDetail.get(i);
        deleteMivDetail(det.getDetId());
      }
      sql = "Delete From used_material_import Where miv_id=" + mivId;
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
  
  public int insertMivDetailForImport(MivDetailBean bean)
    throws Exception
  {
    if (bean == null) {
      return 0;
    }
    int result = 0;
    try
    {
      String sql = "";
      MsvDAO msvDAO = new MsvDAO();
      MsvMaterialBean detailBean = msvDAO.getMsvDetailForImport(bean.getReqDetailId());
      if (detailBean == null) {
        detailBean = new MsvMaterialBean();
      }
      sql = "Insert Into miv_detail(miv_id, mat_id, unit, quantity, price, total, req_detail_id, pre_quantity, rfm_det_id, note) Values (" + bean.getMivId() + "," + bean.getMatId() + ",'" + bean.getUnit() + "'," + bean.getQuantity() + "," + bean.getPrice() + "," + bean.getTotal() + "," + bean.getReqDetailId() + "," + bean.getPreQuantity() + "," + bean.getRfmDetId() + ",'" + bean.getNote() + "')";
      
      result = DBUtil.executeInsert(sql);
      
      detailBean.setCurrentQuantity(bean.getPreQuantity() - bean.getQuantity());
      msvDAO.updateMsvDetail(detailBean);
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
  
  public int updateMivDetailForImport(MivDetailBean bean)
    throws Exception
  {
    if (bean == null) {
      return 0;
    }
    int result = 0;
    try
    {
      String sql = "";
      MsvDAO msvDAO = new MsvDAO();
      MsvMaterialBean detailBean = msvDAO.getMsvDetailForImport(bean.getReqDetailId());
      if (detailBean == null) {
        detailBean = new MsvMaterialBean();
      }
      MivDetailBean oldBean = getMivDetail(bean.getMivId(), bean.getReqDetailId(), bean.getRfmDetId());
      updateMivDetail(bean);
      
      detailBean.setCurrentQuantity(bean.getPreQuantity() - bean.getQuantity());
      msvDAO.updateMsvDetail(detailBean);
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
  
  public MivDetailBean getMivDetail(int mivId, int reqDetId, int rfmDetId)
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "Select * From miv_detail Where miv_id=" + mivId + " and req_detail_id=" + reqDetId + " and rfm_det_id=" + rfmDetId;
    try
    {
      rs = DBUtil.executeQuery(sql);
      if (rs.next())
      {
        MivDetailBean bean = new MivDetailBean();
        bean.setDetId(rs.getInt("det_id"));
        bean.setMivId(rs.getInt("miv_id"));
        bean.setMatId(rs.getInt("mat_id"));
        bean.setUnit(rs.getString("unit"));
        bean.setQuantity(rs.getDouble("quantity"));
        bean.setPrice(rs.getDouble("price"));
        bean.setTotal(rs.getDouble("total"));
        bean.setReqDetailId(rs.getInt("req_detail_id"));
        
        return bean;
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
  
  public MivDetailBean getMivDetail(String mivNumber, int reqDetId, int rfmDetId)
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "Select midet.* From miv_detail as midet, miv as mi Where midet.miv_id=mi.miv_id and  mi.miv_number='" + mivNumber + "' and midet.req_detail_id=" + reqDetId + " and midet.rfm_det_id=" + rfmDetId;
    try
    {
      rs = DBUtil.executeQuery(sql);
      if (rs.next())
      {
        MivDetailBean bean = new MivDetailBean();
        bean.setDetId(rs.getInt("det_id"));
        bean.setMivId(rs.getInt("miv_id"));
        bean.setMatId(rs.getInt("mat_id"));
        bean.setUnit(rs.getString("unit"));
        bean.setQuantity(rs.getDouble("quantity"));
        bean.setPrice(rs.getDouble("price"));
        bean.setTotal(rs.getDouble("total"));
        bean.setReqDetailId(rs.getInt("req_detail_id"));
        
        return bean;
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
  
  public String getNextMivNumber(String prefix, int length)
    throws Exception
  {
    String result = "";
    try
    {
      result = getNextNumber(prefix, length, "miv_number", "miv");
    }
    catch (Exception ex)
    {
      throw new Exception(ex.getMessage());
    }
    return result;
  }
}
