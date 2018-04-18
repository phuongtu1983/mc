package com.venus.mc.dao;

import com.venus.core.util.DateUtil;
import com.venus.core.util.GenericValidator;
import com.venus.core.util.StringUtil;
import com.venus.mc.bean.ContractBean;
import com.venus.mc.bean.MsvBean;
import com.venus.mc.bean.MsvMaterialBean;
import com.venus.mc.database.DBUtil;
import com.venus.mc.request.RequestFormBean;
import com.venus.mc.util.MCUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MsvDAO
  extends BasicDAO
{
  public ArrayList getMsvList(int type)
    throws Exception
  {
    ArrayList arrRes = new ArrayList();
    ResultSet rs = null;
    try
    {
      String sql = "select m.*,r.mrir_number,r.con_id,c.contract_number,c.kind,c.appendix_contract_number,s.name,e.fullname,o.name from msv as m left join mrir as r on r.mrir_id=m.mrir_id left join contract as c on r.con_id=c.con_id left join employee as e on m.delivery_emp=e.emp_id left join organization as o on o.org_id=m.org_id left join store as  s on m.sto_id=s.sto_id where m.type=" + type + " order by created_date desc";
      
      rs = DBUtil.executeQuery(sql);
      while (rs.next())
      {
        MsvBean bean = new MsvBean();
        bean.setMsvId(rs.getInt("m.msv_id"));
        bean.setCreatedDate(DateUtil.formatDate(rs.getDate("m.created_date"), "dd/MM/yyyy"));
        bean.setMsvNumber(rs.getString("m.msv_number"));
        bean.setMrirId(rs.getInt("m.mrir_id"));
        bean.setMrirNumber(rs.getString("r.mrir_number"));
        bean.setCreatedEmpId(rs.getInt("m.created_emp"));
        bean.setStoId(rs.getInt("m.sto_id"));
        bean.setStoName(rs.getString("s.name"));
        bean.setDescription(rs.getString("m.description"));
        bean.setKind(rs.getInt("m.kind"));
        bean.setTotal(rs.getDouble("m.total"));
        bean.setConId(rs.getInt("r.con_id"));
        if (rs.getInt("kind") == ContractBean.KIND_APPENDIX) {
          bean.setConNumber(rs.getString("contract_number"));
        } else {
          bean.setConNumber(rs.getString("contract_number"));
        }
        bean.setDeliverer(rs.getString("m.deliverer"));
        
        bean.setDeliveryEmpId(rs.getInt("m.delivery_emp"));
        bean.setDeliveryEmpName(rs.getString("e.fullname"));
        bean.setOrgId(rs.getInt("m.org_id"));
        bean.setOrgName(rs.getString("o.name"));
        bean.setMivNumbers(rs.getString("m.miv_numbers"));
        arrRes.add(bean);
      }
    }
    catch (SQLException ex)
    {
      throw new Exception(ex.getMessage());
    }
    finally
    {
      if (rs != null) {
        DBUtil.closeConnection(rs);
      }
    }
    return arrRes;
  }
  
  public MsvBean getMsv(int msvId)
    throws Exception
  {
    if (msvId == 0) {
      return null;
    }
    ResultSet rs = null;
    try
    {
      String sql = "select m.*,e.fullname,re.fullname,s.name,r.mrir_number,v.name, v.address,p.name,o.name,c.con_id,c.contract_number,c.kind,c.appendix_contract_number, c.currency  from msv as m left join employee as e on m.created_emp=e.emp_id left join employee as re on m.receive_emp=re.emp_id left join mrir as r on m.mrir_id=r.mrir_id left join store as s on m.sto_id=s.sto_id left join organization as o on o.org_id=m.org_id left join organization as p on p.org_id=m.pro_id left join contract as c on r.con_id=c.con_id left join vendor as v on v.ven_id=c.ven_id where msv_id=" + msvId;
      
      rs = DBUtil.executeQuery(sql);
      if (rs.next())
      {
        MsvBean bean = new MsvBean();
        
        bean.setMsvId(rs.getInt("m.msv_id"));
        bean.setCreatedDate(DateUtil.formatDate(rs.getDate("m.created_date"), "dd/MM/yyyy"));
        bean.setMsvNumber(rs.getString("m.msv_number"));
        bean.setMrirId(rs.getInt("m.mrir_id"));
        bean.setMrirNumber(rs.getString("r.mrir_number"));
        bean.setCreatedEmpId(rs.getInt("m.created_emp"));
        bean.setCreatedEmpName(rs.getString("e.fullname"));
        bean.setStoId(rs.getInt("m.sto_id"));
        bean.setStoName(rs.getString("s.name"));
        bean.setDescription(rs.getString("m.description"));
        bean.setKind(rs.getInt("m.kind"));
        bean.setTotal(rs.getDouble("m.total"));
        bean.setCurrency(rs.getString("currency"));
        bean.setDeliverer(rs.getString("m.deliverer"));
        bean.setType(rs.getInt("m.type"));
        
        bean.setOrgId(rs.getInt("m.org_id"));
        bean.setDeliveryEmpId(rs.getInt("m.delivery_emp"));
        
        bean.setDmvOrder(rs.getString("m.dmv_order"));
        bean.setConId(rs.getInt("c.con_id"));
        if (rs.getInt("kind") == ContractBean.KIND_APPENDIX) {
          bean.setConNumber(rs.getString("contract_number"));
        } else {
          bean.setConNumber(rs.getString("contract_number"));
        }
        bean.setVendorName(StringUtil.decodeString(rs.getString("v.name")));
        bean.setAddress(StringUtil.decodeString(rs.getString("v.address")));
        bean.setReceiveEmpId(rs.getInt("m.receive_emp"));
        bean.setReceiveEmpName(rs.getString("re.fullname"));
        bean.setProId(rs.getInt("m.pro_id"));
        bean.setProName(rs.getString("p.name"));
        bean.setOrgName(rs.getString("o.name"));
        
        return bean;
      }
    }
    catch (SQLException ex)
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
  
  public int getDnId(int mrirId)
    throws Exception
  {
    if (mrirId == 0) {
      return 0;
    }
    ResultSet rs = null;
    try
    {
      String sql = "SELECT dn_id FROM mrir WHERE mrir_id = " + mrirId;
      
      rs = DBUtil.executeQuery(sql);
      if (rs.next()) {
        return rs.getInt("dn_id");
      }
    }
    catch (SQLException ex)
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
  
  public MsvBean getMsvByNumber(String msvNumber)
    throws Exception
  {
    if (StringUtil.isBlankOrNull(msvNumber)) {
      return null;
    }
    ResultSet rs = null;
    try
    {
      String sql = "select m.*,e.fullname,re.fullname,s.name,r.mrir_number,v.name,p.name,o.name,c.con_id,c.contract_number,c.kind,c.appendix_contract_number from msv as m left join employee as e on m.created_emp=e.emp_id left join employee as re on m.receive_emp=re.emp_id left join mrir as r on m.mrir_id=r.mrir_id left join store as s on m.sto_id=s.sto_id left join organization as o on o.org_id=m.org_id left join organization as p on p.org_id=m.pro_id left join contract as c on r.con_id=c.con_id left join vendor as v on v.ven_id=c.ven_id where msv_number='" + msvNumber + "'";
      
      rs = DBUtil.executeQuery(sql);
      if (rs.next())
      {
        MsvBean bean = new MsvBean();
        
        bean.setMsvId(rs.getInt("m.msv_id"));
        bean.setCreatedDate(DateUtil.formatDate(rs.getDate("m.created_date"), "dd/MM/yyyy"));
        bean.setMsvNumber(rs.getString("m.msv_number"));
        bean.setMrirId(rs.getInt("m.mrir_id"));
        bean.setMrirNumber(rs.getString("r.mrir_number"));
        bean.setCreatedEmpId(rs.getInt("m.created_emp"));
        bean.setCreatedEmpName(rs.getString("e.fullname"));
        bean.setStoId(rs.getInt("m.sto_id"));
        bean.setStoName(rs.getString("s.name"));
        bean.setDescription(rs.getString("m.description"));
        bean.setKind(rs.getInt("m.kind"));
        bean.setTotal(rs.getDouble("m.total"));
        bean.setDeliverer(rs.getString("m.deliverer"));
        bean.setType(rs.getInt("m.type"));
        
        bean.setOrgId(rs.getInt("m.org_id"));
        bean.setDeliveryEmpId(rs.getInt("m.delivery_emp"));
        
        bean.setDmvOrder(rs.getString("m.dmv_order"));
        bean.setConId(rs.getInt("c.con_id"));
        bean.setConNumber(rs.getString("c.contract_number"));
        if (rs.getInt("kind") == ContractBean.KIND_APPENDIX) {
          bean.setConNumber(rs.getString("contract_number"));
        } else {
          bean.setConNumber(rs.getString("contract_number"));
        }
        bean.setVendorName(StringUtil.decodeString(rs.getString("v.name")));
        bean.setReceiveEmpId(rs.getInt("m.receive_emp"));
        bean.setReceiveEmpName(rs.getString("re.fullname"));
        bean.setProId(rs.getInt("m.pro_id"));
        bean.setProName(rs.getString("p.name"));
        bean.setOrgName(rs.getString("o.name"));
        
        return bean;
      }
    }
    catch (SQLException ex)
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
  
  public MsvBean getNewMsvFromMrir(int type, int mrirId)
    throws Exception
  {
    if (mrirId == 0) {
      return null;
    }
    ResultSet rs = null;
    try
    {
      if (type == 1)
      {
        String sql = "select dn.created_org,o1.name,dn.pro_id,o.name, MAX(s.sto_id) AS sto_id, s.NAME AS sto_name from mrir as m left join delivery_notice as dn on dn.dn_id=m.dn_id left join organization as o on o.org_id=dn.created_org left join organization as o1 on o1.org_id=dn.pro_id LEFT JOIN store AS s ON s.org_id=dn.pro_id AND s.kind=1 where m.mrir_id=" + mrirId;
        
        rs = DBUtil.executeQuery(sql);
        if (rs.next())
        {
          MsvBean bean = new MsvBean();
          bean.setStoId(rs.getInt("sto_id"));
          bean.setStoName(rs.getString("sto_name"));
          bean.setOrgId(rs.getInt("dn.created_org"));
          bean.setOrgName(rs.getString("o.name"));
          bean.setProId(rs.getInt("dn.pro_id"));
          bean.setProName(rs.getString("o1.name"));
          
          return bean;
        }
      }
      else
      {
        String sql = "select r.which_use,r.created_org,o1.name,s.name, s.sto_id,o.name, r.org_id,c.contract_number,v.name,c.kind,c.appendix_contract_number from mrir_detail as md left join request_detail as rd on md.req_detail_id=rd.det_id left join request as r on r.req_id=rd.req_id left join organization as o on o.org_id=r.org_id left join organization as o1 on o1.org_id=r.created_org left join store as s on s.org_id=r.org_id left join mrir as m on m.mrir_id=md.mrir_id left join contract as c on c.con_id=m.con_id left join vendor as v on c.ven_id=v.ven_id  where md.mrir_id=" + mrirId;
        
        rs = DBUtil.executeQuery(sql);
        if (rs.next())
        {
          MsvBean bean = new MsvBean();
          int whichUse = rs.getInt("r.which_use");
          if (whichUse == RequestFormBean.WHICHUSE_PROJECT)
          {
            bean.setStoId(rs.getInt("s.sto_id"));
            bean.setStoName(StringUtil.getString(rs.getString("s.name")));
            bean.setProId(rs.getInt("r.org_id"));
            bean.setProName(StringUtil.getString(rs.getString("o.name")));
            bean.setOrgId(rs.getInt("r.created_org"));
            bean.setOrgName(StringUtil.getString(rs.getString("o1.name")));
          }
          else
          {
            bean.setStoId(1);
            bean.setStoName(MCUtil.getBundleString("message.store.company"));
            bean.setOrgId(rs.getInt("r.org_id"));
            bean.setOrgName(StringUtil.getString(rs.getString("o.name")));
          }
          if (rs.getInt("kind") == ContractBean.KIND_APPENDIX) {
            bean.setConNumber(rs.getString("contract_number"));
          } else {
            bean.setConNumber(rs.getString("contract_number"));
          }
          bean.setVendorName(StringUtil.decodeString(rs.getString("v.name")));
          
          return bean;
        }
      }
    }
    catch (SQLException ex)
    {
      throw new Exception(ex.getMessage());
    }
    finally
    {
      if (rs != null) {
        DBUtil.closeConnection(rs);
      }
    }
    String sql;
    return null;
  }
  
  public ArrayList getMaterialsFromMrir(int mrirId, int stoId)
    throws Exception
  {
    ArrayList arrRes = new ArrayList();
    if (mrirId == 0) {
      return arrRes;
    }
    ResultSet rs = null;
    try
    {
      String sql = "select r.request_number, md.mat_id,md.quantity,md.price,md.unit,md.req_detail_id,m.name_vn,m.code from mrir_detail as md left join material as m on m.mat_id=md.mat_id";
      if (stoId == 0) {
        sql = sql + " left join request_detail as rd on rd.det_id=md.req_detail_id left join request as r on rd.req_id=r.req_id";
      }
      if (stoId > 1) {
        sql = sql + " left join request_detail as rd on rd.det_id=md.req_detail_id left join request as r on rd.req_id=r.req_id left join organization as o on o.org_id=r.org_id left join store as s on s.org_id=r.org_id";
      } else if (stoId == 1) {
        sql = sql + " left join request_detail as rd on rd.det_id=md.req_detail_id left join request as r on rd.req_id=r.req_id AND r.org_id=" + stoId + " left join organization as o on o.org_id=r.org_id" + " left join store as s on s.org_id=r.org_id";
      }
      sql = sql + " where md.mrir_id=" + mrirId;
      if (stoId > 1) {
        sql = sql + " and s.sto_id = " + stoId;
      } else if (stoId != 1) {}
      rs = DBUtil.executeQuery(sql);
      int id = 1;
      while (rs.next())
      {
        MsvMaterialBean bean = new MsvMaterialBean();
        bean.setDetId(id++);
        bean.setMatId(rs.getInt("md.mat_id"));
        bean.setMatName(StringUtil.decodeString(rs.getString("m.name_vn")));
        bean.setMatCode(rs.getString("m.code"));
        bean.setQuantity(rs.getDouble("md.quantity"));
        bean.setPrice(rs.getDouble("md.price"));
        bean.setTotal(bean.getQuantity() * bean.getPrice());
        bean.setUnit(rs.getString("md.unit"));
        bean.setReqDetailId(rs.getInt("md.req_detail_id"));
        bean.setReqNumber(rs.getString("r.request_number"));
        arrRes.add(bean);
      }
    }
    catch (SQLException ex)
    {
      throw new Exception(ex.getMessage());
    }
    finally
    {
      if (rs != null) {
        DBUtil.closeConnection(rs);
      }
    }
    return arrRes;
  }
  
  public int insertMsv(MsvBean bean)
    throws Exception
  {
    if (bean == null) {
      return 0;
    }
    if ((GenericValidator.isBlankOrNull(bean.getMsvNumber())) || (bean.getMrirId() == 0) || (bean.getCreatedEmpId() == 0) || (bean.getStoId() == 0)) {
      return 0;
    }
    if (GenericValidator.isBlankOrNull(bean.getDescription())) {
      bean.setDescription("");
    }
    if (GenericValidator.isBlankOrNull(bean.getDeliverer())) {
      bean.setDeliverer("");
    }
    int result = 0;
    try
    {
      String date = "sysdate()";
      if (!GenericValidator.isBlankOrNull(bean.getCreatedDate())) {
        date = "STR_TO_DATE('" + bean.getCreatedDate() + "','%d/%m/%Y')";
      }
      String sql = "";
      sql = "insert into msv( created_date, msv_number, mrir_id, created_emp, sto_id, description, total, type, dn_id, kind,";
      if (bean.getOrgId() > 0) {
        sql = sql + " org_id,";
      }
      if (bean.getProId() > 0) {
        sql = sql + " pro_id,";
      }
      if (bean.getDeliveryEmpId() > 0) {
        sql = sql + " delivery_emp,";
      }
      sql = sql + " miv_numbers, deliverer ) values (" + date + "," + "'" + bean.getMsvNumber() + "'," + bean.getMrirId() + "," + bean.getCreatedEmpId() + "," + bean.getStoId() + "," + "'" + bean.getDescription() + "'," + bean.getTotal() + "," + bean.getType() + "," + bean.getDnId() + "," + bean.getKind() + ",";
      if (bean.getOrgId() > 0) {
        sql = sql + bean.getOrgId() + ",";
      }
      if (bean.getProId() > 0) {
        sql = sql + bean.getProId() + ",";
      }
      if (bean.getDeliveryEmpId() > 0) {
        sql = sql + bean.getDeliveryEmpId() + ",";
      }
      sql = sql + "'" + bean.getMivNumbers() + "'," + "'" + bean.getDeliverer() + "'" + ")";
      
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
  
  public int insertMsvMaterial(MsvMaterialBean bean)
    throws Exception
  {
    if (bean == null) {
      return 0;
    }
    if ((bean.getMsvId() == 0) || (bean.getMatId() == 0) || (bean.getQuantity() == 0.0D)) {
      return 0;
    }
    int result = 0;
    try
    {
      String sql = "";
      sql = "insert into msv_detail(msv_id, mat_id, unit, quantity, price, total, req_detail_id,current_quantity, mrir_det_id, note) Values (" + bean.getMsvId() + "," + bean.getMatId() + "," + "'" + bean.getUnit() + "'," + bean.getQuantity() + "," + bean.getPrice() + "," + bean.getTotal() + "," + bean.getReqDetailId() + "," + bean.getQuantity() + "," + bean.getMrirDetId() + ",'" + bean.getNote() + "'" + ")";
      
      result = DBUtil.executeInsert(sql);
    }
    catch (SQLException sqle)
    {
      throw new Exception(sqle.getMessage());
    }
    return result;
  }
  
  public ArrayList getMaterialsFromMsv(int msvId)
    throws Exception
  {
    ArrayList arrRes = new ArrayList();
    if (msvId == 0) {
      return arrRes;
    }
    ResultSet rs = null;
    try
    {
      String sql = "select msv.sto_id, d.*,m.name_vn,m.code,o.name,r.request_number,rd.req_id,r.which_use from msv_detail as d left join material as m on m.mat_id=d.mat_id left join request_detail as rd on d.req_detail_id = rd.det_id left join request as r on r.req_id = rd.req_id left join organization as o on o.org_id=r.org_id LEFT JOIN msv ON msv.msv_id = d.msv_id  where d.msv_id=" + msvId;
      
      rs = DBUtil.executeQuery(sql);
      int id = 1;
      while (rs.next())
      {
        MsvMaterialBean bean = new MsvMaterialBean();
        bean.setStoId(rs.getInt("sto_id"));
        bean.setDetId(rs.getInt("d.det_id"));
        bean.setMatId(rs.getInt("d.mat_id"));
        bean.setMsvId(rs.getInt("d.msv_id"));
        bean.setMatName(StringUtil.decodeString(rs.getString("m.name_vn")));
        bean.setMatCode(rs.getString("m.code"));
        bean.setQuantity(rs.getDouble("d.quantity"));
        bean.setPrice(rs.getDouble("d.price"));
        bean.setTotal(bean.getQuantity() * bean.getPrice());
        bean.setUnit(rs.getString("d.unit"));
        bean.setReqDetailId(rs.getInt("d.req_detail_id"));
        bean.setReqNumber(rs.getString("r.request_number"));
        bean.setReqId(rs.getInt("rd.req_id"));
        if (rs.getInt("r.which_use") == RequestFormBean.WHICHUSE_PROJECT) {
          bean.setProName(rs.getString("o.name"));
        }
        arrRes.add(bean);
      }
    }
    catch (SQLException ex)
    {
      throw new Exception(ex.getMessage());
    }
    finally
    {
      if (rs != null) {
        DBUtil.closeConnection(rs);
      }
    }
    return arrRes;
  }
  
  public ArrayList searchSimple(int type, int fieldid, String strFieldvalue)
    throws Exception
  {
    String strFieldname = "";
    switch (fieldid)
    {
    case 1: 
      strFieldname = "m.msv_number";
      break;
    case 2: 
      if (type == 0) {
        strFieldname = "c.contract_number";
      } else if (type == 1) {
        strFieldname = "m.miv_numbers";
      }
      if (type == 2) {
        strFieldname = "m.dmv_order";
      }
      break;
    case 3: 
      strFieldname = "s.name";
    }
    ArrayList arrRes = new ArrayList();
    ResultSet rs = null;
    try
    {
      String sql = "select m.*,r.mrir_number,r.con_id,c.contract_number,s.name,e.fullname,o.name,c.kind,c.appendix_contract_number from msv as m left join mrir as r on r.mrir_id=m.mrir_id left join contract as c on r.con_id=c.con_id left join employee as e on m.delivery_emp=e.emp_id left join organization as o on o.org_id=m.org_id left join store as  s on m.sto_id=s.sto_id";
      
      sql = sql + " where type=" + type;
      if ((fieldid > 0) && (!StringUtil.isBlankOrNull(strFieldvalue))) {
        sql = sql + " AND " + strFieldname + " LIKE '%" + strFieldvalue + "%'";
      }
      sql = sql + " order by created_date desc";
      
      rs = DBUtil.executeQuery(sql);
      while (rs.next())
      {
        MsvBean bean = new MsvBean();
        bean.setMsvId(rs.getInt("m.msv_id"));
        bean.setCreatedDate(DateUtil.formatDate(rs.getDate("m.created_date"), "dd/MM/yyyy"));
        bean.setMsvNumber(rs.getString("m.msv_number"));
        bean.setMrirId(rs.getInt("m.mrir_id"));
        bean.setMrirNumber(rs.getString("r.mrir_number"));
        bean.setCreatedEmpId(rs.getInt("m.created_emp"));
        bean.setStoId(rs.getInt("m.sto_id"));
        bean.setStoName(rs.getString("s.name"));
        bean.setDescription(rs.getString("m.description"));
        bean.setKind(rs.getInt("m.kind"));
        bean.setTotal(rs.getDouble("m.total"));
        bean.setConId(rs.getInt("r.con_id"));
        if (rs.getInt("kind") == ContractBean.KIND_APPENDIX) {
          bean.setConNumber(rs.getString("contract_number"));
        } else {
          bean.setConNumber(rs.getString("contract_number"));
        }
        bean.setDeliverer(rs.getString("m.deliverer"));
        
        bean.setDeliveryEmpId(rs.getInt("m.delivery_emp"));
        bean.setDeliveryEmpName(rs.getString("e.fullname"));
        bean.setOrgId(rs.getInt("m.org_id"));
        bean.setOrgName(rs.getString("o.name"));
        bean.setMivNumbers(rs.getString("m.miv_numbers"));
        
        arrRes.add(bean);
      }
    }
    catch (SQLException ex)
    {
      throw new Exception(ex.getMessage());
    }
    finally
    {
      if (rs != null) {
        DBUtil.closeConnection(rs);
      }
    }
    return arrRes;
  }
  
  public ArrayList searchSimple(int type, int fieldid, String strFieldvalue, String storeId)
    throws Exception
  {
    String strFieldname = "";
    switch (fieldid)
    {
    case 1: 
      strFieldname = "m.msv_number";
      break;
    case 2: 
      if (type == 0) {
        strFieldname = "c.contract_number";
      } else if (type == 1) {
        strFieldname = "m.miv_numbers";
      }
      if (type == 2) {
        strFieldname = "m.dmv_order";
      }
      break;
    case 3: 
      strFieldname = "s.name";
      break;
    case 4: 
      strFieldname = "r.mrir_number";
    }
    ArrayList arrRes = new ArrayList();
    ResultSet rs = null;
    try
    {
      String sql = "select m.*,r.mrir_number,r.con_id,c.contract_number,s.name,e.fullname,o.name,c.kind,c.appendix_contract_number from msv as m left join mrir as r on r.mrir_id=m.mrir_id left join contract as c on r.con_id=c.con_id left join employee as e on m.delivery_emp=e.emp_id left join organization as o on o.org_id=m.org_id left join store as  s on m.sto_id=s.sto_id";
      
      sql = sql + " where type=" + type;
      if ((fieldid > 0) && (!StringUtil.isBlankOrNull(strFieldvalue))) {
        sql = sql + " AND " + strFieldname + " LIKE '%" + strFieldvalue + "%'";
      }
      if ((!GenericValidator.isBlankOrNull(storeId)) && 
        (!storeId.equals("all"))) {
        sql = sql + " and m.sto_id=" + storeId;
      }
      sql = sql + " order by created_date desc";
      
      rs = DBUtil.executeQuery(sql);
      while (rs.next())
      {
        MsvBean bean = new MsvBean();
        bean.setMsvId(rs.getInt("m.msv_id"));
        bean.setCreatedDate(DateUtil.formatDate(rs.getDate("m.created_date"), "dd/MM/yyyy"));
        bean.setMsvNumber(rs.getString("m.msv_number"));
        bean.setMrirId(rs.getInt("m.mrir_id"));
        bean.setMrirNumber(rs.getString("r.mrir_number"));
        bean.setCreatedEmpId(rs.getInt("m.created_emp"));
        bean.setStoId(rs.getInt("m.sto_id"));
        bean.setStoName(rs.getString("s.name"));
        bean.setDescription(rs.getString("m.description"));
        bean.setKind(rs.getInt("m.kind"));
        bean.setTotal(rs.getDouble("m.total"));
        bean.setConId(rs.getInt("r.con_id"));
        if (rs.getInt("kind") == ContractBean.KIND_APPENDIX) {
          bean.setConNumber(rs.getString("contract_number"));
        } else {
          bean.setConNumber(rs.getString("contract_number"));
        }
        bean.setDeliverer(rs.getString("m.deliverer"));
        
        bean.setDeliveryEmpId(rs.getInt("m.delivery_emp"));
        bean.setDeliveryEmpName(rs.getString("e.fullname"));
        bean.setOrgId(rs.getInt("m.org_id"));
        bean.setOrgName(rs.getString("o.name"));
        bean.setMivNumbers(rs.getString("m.miv_numbers"));
        
        arrRes.add(bean);
      }
    }
    catch (SQLException ex)
    {
      throw new Exception(ex.getMessage());
    }
    finally
    {
      if (rs != null) {
        DBUtil.closeConnection(rs);
      }
    }
    return arrRes;
  }
  
  public int updateMsv(MsvBean bean)
    throws Exception
  {
    if ((bean == null) || (bean.getMsvId() == 0)) {
      return 0;
    }
    if (GenericValidator.isBlankOrNull(bean.getDescription())) {
      bean.setDescription("");
    }
    if (GenericValidator.isBlankOrNull(bean.getDmvOrder())) {
      bean.setDmvOrder("");
    }
    if (GenericValidator.isBlankOrNull(bean.getDeliverer())) {
      bean.setDeliverer("");
    }
    if (GenericValidator.isBlankOrNull(bean.getMivNumbers())) {
      bean.setMivNumbers("");
    }
    int result = 0;
    try
    {
      String sql = "update msv set ";
      sql = sql + "msv_number='" + bean.getMsvNumber() + "'";
      sql = sql + ",description='" + bean.getDescription() + "'";
      sql = sql + ",deliverer='" + bean.getDeliverer() + "'";
      sql = sql + ",kind=" + bean.getKind();
      sql = sql + ",dn_id=" + bean.getDnId();
      sql = sql + ",dmv_order='" + bean.getDmvOrder() + "'";
      sql = sql + ",miv_numbers='" + bean.getMivNumbers() + "'";
      sql = sql + ",delivery_emp='" + bean.getDeliveryEmpId() + "'";
      sql = sql + " where msv_id=" + bean.getMsvId();
      result = DBUtil.executeUpdate(sql);
    }
    catch (SQLException sqle)
    {
      throw new Exception(sqle.getMessage());
    }
    return result;
  }
  
  public MsvMaterialBean getMsvDetail(int mivId, int reqDetailId)
    throws Exception
  {
    ResultSet rs = null;
    try
    {
      String sql = "select * from msv_detail where req_detail_id=" + reqDetailId + " and msv_id in" + "(select rdet.msv_id from miv as m, rfm_detail as rdet" + " where m.rfm_id=rdet.rfm_id and rdet.req_detail_id=" + reqDetailId + " and m.miv_id=" + mivId + ")";
      
      rs = DBUtil.executeQuery(sql);
      if (rs.next())
      {
        MsvMaterialBean bean = new MsvMaterialBean();
        bean.setDetId(rs.getInt("det_id"));
        bean.setQuantity(rs.getDouble("quantity"));
        bean.setCurrentQuantity(rs.getDouble("current_quantity"));
        bean.setPrice(rs.getDouble("price"));
        bean.setTotal(rs.getDouble("total"));
        
        return bean;
      }
    }
    catch (SQLException ex)
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
  
  public void updateMsvDetail(MsvMaterialBean bean)
    throws Exception
  {
    if (bean == null) {
      return;
    }
    try
    {
      String sql = "Update msv_detail Set  quantity=" + bean.getQuantity() + ", current_quantity=" + bean.getCurrentQuantity() + ", price=" + bean.getPrice() + ", total=" + bean.getTotal() + " Where det_id=" + bean.getDetId();
      
      DBUtil.executeUpdate(sql);
    }
    catch (SQLException sqle)
    {
      throw new Exception(sqle.getMessage());
    }
  }
  
  public String checkMsvDetailStatus(int matId, int msvId, int reqDetailId)
    throws Exception
  {
    ResultSet rs = null;
    String sql = "SELECT rfm_number FROM rfm_detail AS d LEFT JOIN rfm AS r ON r.rfm_id = d.rfm_id where msv_id=" + msvId + " and req_detail_id=" + reqDetailId + " and mat_id=" + matId;
    try
    {
      rs = DBUtil.executeQuery(sql);
      String str1;
      if (rs.next()) {
        return rs.getString("rfm_number");
      }
      return "";
    }
    catch (SQLException ex)
    {
      return "";
    }
    finally
    {
      if (rs != null) {
        DBUtil.closeConnection(rs);
      }
    }
  }
  
  public int deleteMsvDetail(int detId)
    throws Exception
  {
    if (detId == 0) {
      return 0;
    }
    try
    {
      String sql = "delete from msv_detail where det_id=" + detId;
      return DBUtil.executeUpdate(sql);
    }
    catch (SQLException sqle)
    {
      throw new Exception(sqle.getMessage());
    }
  }
  
  public int deleteAllMsvDetail(int msvId)
    throws Exception
  {
    if (msvId == 0) {
      return 0;
    }
    try
    {
      String sql = "delete from msv_detail where msv_id=" + msvId;
      return DBUtil.executeUpdate(sql);
    }
    catch (SQLException sqle)
    {
      throw new Exception(sqle.getMessage());
    }
  }
  
  public MsvMaterialBean getMsvDetail(int detId)
    throws Exception
  {
    ResultSet rs = null;
    try
    {
      String sql = "select d.*,m.name_vn,m.code,o.name,r.request_number,rd.req_id,r.which_use from msv_detail as d left join material as m on m.mat_id=d.mat_id left join request_detail as rd on d.req_detail_id = rd.det_id left join request as r on r.req_id = rd.req_id left join organization as o on o.org_id=r.org_id where d.det_id=" + detId;
      
      rs = DBUtil.executeQuery(sql);
      if (rs.next())
      {
        MsvMaterialBean bean = new MsvMaterialBean();
        bean.setDetId(rs.getInt("d.det_id"));
        bean.setMatId(rs.getInt("d.mat_id"));
        bean.setMsvId(rs.getInt("d.msv_id"));
        bean.setMatName(StringUtil.decodeString(rs.getString("m.name_vn")));
        bean.setMatCode(rs.getString("m.code"));
        bean.setQuantity(rs.getDouble("d.quantity"));
        bean.setPrice(rs.getDouble("d.price"));
        bean.setTotal(bean.getQuantity() * bean.getPrice());
        bean.setUnit(rs.getString("d.unit"));
        bean.setReqDetailId(rs.getInt("d.req_detail_id"));
        bean.setReqNumber(rs.getString("r.request_number"));
        bean.setReqId(rs.getInt("rd.req_id"));
        if (rs.getInt("r.which_use") == RequestFormBean.WHICHUSE_PROJECT) {
          bean.setProName(rs.getString("o.name"));
        }
        return bean;
      }
    }
    catch (SQLException ex)
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
  
  public int deleteMsv(int msvId)
    throws Exception
  {
    if (msvId == 0) {
      return 0;
    }
    try
    {
      String sql = "delete from msv where msv_id=" + msvId;
      return DBUtil.executeUpdate(sql);
    }
    catch (SQLException sqle)
    {
      throw new Exception(sqle.getMessage());
    }
  }
  
  public int getMsvDetailByRequestDetail(int msvId, int reqDetId)
    throws Exception
  {
    ResultSet rs = null;
    int result = 0;
    try
    {
      String sql = "select det_id from msv_detail as m where msv_id=" + msvId + " and req_detail_id=" + reqDetId;
      
      rs = DBUtil.executeQuery(sql);
      while (rs.next()) {
        result = rs.getInt("det_id");
      }
    }
    catch (SQLException ex)
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
  
  public String getFollowEmp(int mrirId)
    throws Exception
  {
    ResultSet rs = null;
    String result = "";
    try
    {
      String sql = "SELECT e.fullname FROM contract AS c LEFT JOIN mrir AS m ON m.con_id = c.con_id LEFT JOIN employee AS e ON e.emp_id = c.follow_emp WHERE m.mrir_id=" + mrirId;
      
      rs = DBUtil.executeQuery(sql);
      while (rs.next()) {
        result = rs.getString("fullname");
      }
    }
    catch (SQLException ex)
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
  
  public MsvMaterialBean getMsvDetailForImport(int reqDetailId)
    throws Exception
  {
    ResultSet rs = null;
    try
    {
      String sql = "select m.* from msv_detail m, rfm_detail r where m.req_detail_id=r.req_detail_id and m.req_detail_id=" + reqDetailId;
      
      rs = DBUtil.executeQuery(sql);
      if (rs.next())
      {
        MsvMaterialBean bean = new MsvMaterialBean();
        bean.setDetId(rs.getInt("det_id"));
        bean.setQuantity(rs.getDouble("quantity"));
        bean.setCurrentQuantity(rs.getDouble("current_quantity"));
        bean.setPrice(rs.getInt("price"));
        bean.setTotal(rs.getInt("total"));
        
        return bean;
      }
    }
    catch (SQLException ex)
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
  
  public String getNextMsvNumber(String prefix, int length)
    throws Exception
  {
    String result = "";
    try
    {
      result = getNextNumber(prefix, length, "msv_number", "msv");
    }
    catch (Exception ex)
    {
      throw new Exception(ex.getMessage());
    }
    return result;
  }
}
