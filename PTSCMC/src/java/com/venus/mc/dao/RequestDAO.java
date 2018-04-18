package com.venus.mc.dao;

import com.venus.core.util.DateUtil;
import com.venus.core.util.GenericValidator;
import com.venus.core.util.NumberUtil;
import com.venus.core.util.StringUtil;
import com.venus.mc.bean.ContractBean;
import com.venus.mc.bean.FileAttachmentBean;
import com.venus.mc.bean.OrganizationBean;
import com.venus.mc.bean.RequestBean;
import com.venus.mc.bean.RequestDetailBean;
import com.venus.mc.bean.RequestHandledBean;
import com.venus.mc.bean.RequestOrganizationBean;
import com.venus.mc.database.DBUtil;
import com.venus.mc.intermemo.SearchAdvIntermemoFormBean;
import com.venus.mc.process.store.dmv.DmvFormBean;
import com.venus.mc.request.RequestDetailFormBean;
import com.venus.mc.request.RequestFormBean;
import com.venus.mc.request.SearchAdvRequestFormBean;
import com.venus.mc.util.MCUtil;
import com.venus.mc.util.SQLSearchExpressionUtil;
import java.io.PrintStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.apache.struts.util.LabelValueBean;

public class RequestDAO
  extends BasicDAO
{
  public ArrayList getRequests()
    throws Exception
  {
    ResultSet rs = null;
    String sql = "select req_id, req.created_date, request_number, which_use, o.name as organizationName, o.kind as orgKind, co.name as createdOrg, e.fullname as assignedEmployee, bom_agree_date, (select min(rdet.material_kind) from request_detail as rdet where rdet.req_id=req.req_id) as materialKind, (select min(rdet.provide_date) from request_detail as rdet where rdet.req_id=req.req_id) as ros from request as req  left join organization as o on req.org_id=o.org_id left join organization as co on req.created_org=co.org_id left join employee as e on req.assigned_emp=e.emp_id where req.kind=" + RequestBean.REQUEST + " order by req_id desc";
    
    ArrayList requestList = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      RequestBean request = null;
      
      int materialKind = 0;
      while (rs.next())
      {
        request = new RequestBean();
        request.setReqId(rs.getInt("req_id"));
        request.setCreatedDate(DateUtil.formatDate(rs.getDate("created_date"), "dd/MM/yyyy"));
        request.setBomAgreeDate(DateUtil.formatDate(rs.getDate("bom_agree_date"), "dd/MM/yyyy"));
        request.setRequestNumber(rs.getString("request_number"));
        request.setWhichUseName(rs.getString("organizationName"));
        request.setCreatedOrganizationName(rs.getString("createdOrg"));
        request.setEmployeeName(rs.getString("assignedEmployee"));
        request.setRos(DateUtil.formatDate(rs.getDate("ros"), "dd/MM/yyyy"));
        materialKind = rs.getInt("materialKind");
        if (materialKind == RequestFormBean.KIND_MAJOR) {
          request.setMaterialKind(MCUtil.getBundleString("message.request.materialKind.major"));
        } else if (materialKind == RequestFormBean.KIND_SECONDARY) {
          request.setMaterialKind(MCUtil.getBundleString("message.request.materialKind.secondary"));
        }
        requestList.add(request);
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
    return requestList;
  }
  
  public ArrayList getRequestsByDnId(int dnId)
    throws Exception
  {
    ResultSet rs = null;
    String sql = "Select request.req_id, request_number From (request inner join request_detail on request.req_id = request_detail.req_id) inner join delivery_notice_detail on request_detail.det_id = delivery_notice_detail.req_detail_id  Where delivery_notice_detail.dn_id=" + dnId + " Group by request.req_id, request_number";
    
    ArrayList requestList = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      RequestBean request = null;
      while (rs.next())
      {
        request = new RequestBean();
        request.setReqId(rs.getInt("req_id"));
        request.setRequestNumber(rs.getString("request_number"));
        requestList.add(request);
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
    return requestList;
  }
  
  public ArrayList getRequestsByDrId(int drId)
    throws Exception
  {
    ResultSet rs = null;
    String sql = "Select request.req_id, request_number From ((request inner join request_detail on request.req_id = request_detail.req_id) inner join contract_detail on request_detail.det_id = contract_detail.reqDetail_id) inner join delivery_request_detail on contract_detail.det_id = delivery_request_detail.conDetail_id Where delivery_request_detail.dr_id=" + drId + " Group by request.req_id, request_number";
    
    ArrayList requestList = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      RequestBean request = null;
      while (rs.next())
      {
        request = new RequestBean();
        request.setReqId(rs.getInt("req_id"));
        request.setRequestNumber(rs.getString("request_number"));
        requestList.add(request);
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
    return requestList;
  }
  
  public String getMaterialNotCode()
    throws Exception
  {
    ResultSet rs = null;
    String st = "";
    String sql = "SELECT req_id FROM request_detail AS rd LEFT JOIN material AS m ON m.mat_id = rd.mat_id WHERE LENGTH(m.CODE)<=0";
    try
    {
      rs = DBUtil.executeQuery(sql);
      while (rs.next()) {
        st = st + ";" + rs.getString("req_id");
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
    return st;
  }
  
  public RequestBean getRequest(int reqId)
    throws Exception
  {
    ResultSet rs = null;
    String sql = "Select req.*, e.fullname as employeeName, co.name as createdOrg, o.kind as orgKind, o.name as projectName, ass.fullname as assignedEmpName, o.name_en as projectNameEn From request as req  left join employee as e on req.created_emp=e.emp_id  left join organization as co on req.created_org=co.org_id left join organization as o on req.org_id=o.org_id left join employee as ass on req.assigned_emp=ass.emp_id Where req_id=" + reqId;
    try
    {
      int whichUse = 0;
      
      rs = DBUtil.executeQuery(sql);
      String str = "";
      if (rs.next())
      {
        RequestBean request = new RequestBean();
        request.setReqId(rs.getInt("req_id"));
        request.setKind(rs.getInt("kind"));
        
        request.setCreatedEmp(rs.getInt("created_emp"));
        request.setCreatedOrg(rs.getInt("created_org"));
        request.setRequestNumber(rs.getString("request_number"));
        request.setCreatedDate(DateUtil.formatDate(rs.getDate("created_date"), "dd/MM/yyyy"));
        request.setApproveSuggest(rs.getString("approve_suggest"));
        request.setStatusSuggest(rs.getInt("status_suggest"));
        request.setCertificateRequire(rs.getString("certificate_require"));
        request.setWhichUse(rs.getInt("which_use"));
        request.setDescription(rs.getString("description"));
        request.setCheckComment(rs.getString("check_comment"));
        request.setStoreComment(rs.getString("store_comment"));
        request.setBomComment(rs.getString("bom_comment"));
        request.setPlandepComment(rs.getString("plandep_comment"));
        request.setOrgHandle(rs.getString("org_handle"));
        request.setOrgRefer(rs.getString("org_refer"));
        request.setOrgPaid(rs.getString("org_paid"));
        request.setIntermemoSubject(rs.getString("intermemo_subject"));
        request.setCheckApprove(rs.getInt("check_approve"));
        request.setStoreApprove(rs.getInt("store_approve"));
        request.setBomApprove(rs.getInt("bom_approve"));
        request.setPlandepApprove(rs.getInt("plandep_approve"));
        request.setEmployeeName(rs.getString("employeeName"));
        request.setOrganizationName(rs.getString("createdOrg"));
        request.setAssignedEmp(rs.getInt("assigned_emp"));
        request.setAssignedEmpName(rs.getString("assignedEmpName"));
        request.setProjectNameEn(rs.getString("projectNameEn"));
        request.setStatus(rs.getInt("status"));
        request.setStoreOrg(rs.getInt("store_org"));
        if (request.getStatus() == RequestBean.STATUS_CREATE) {
          request.setStatusText(MCUtil.getBundleString("message.request.created"));
        } else if (request.getStatus() == RequestBean.STATUS_STORE) {
          request.setStatusText(MCUtil.getBundleString("message.request.changeToStore"));
        } else if (request.getStatus() == RequestBean.STATUS_CHECK) {
          request.setStatusText(MCUtil.getBundleString("message.request.changeToCheck"));
        } else if (request.getStatus() == RequestBean.STATUS_PLANDEP) {
          request.setStatusText(MCUtil.getBundleString("message.request.changeToPlanDep"));
        } else if (request.getStatus() == RequestBean.STATUS_MANAGER) {
          request.setStatusText(MCUtil.getBundleString("message.request.changeToManage"));
        }
        whichUse = rs.getInt("which_use");
        if (whichUse == RequestFormBean.WHICHUSE_PROJECT)
        {
          request.setWhichUseName(rs.getString("projectName"));
          request.setProId(rs.getInt("org_id"));
        }
        else
        {
          request.setWhichUseName(rs.getString("createdOrg"));
          request.setOrgId(rs.getInt("org_id"));
        }
        str = getMaterialNullCode(reqId);
        if (str.length() > 0) {
          request.setHighlight(1);
        } else {
          request.setHighlight(0);
        }
        return request;
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
  
  public RequestBean getRequestByNumber(String requestNumber)
    throws Exception
  {
    ResultSet rs = null;
    String sql = "Select req_id, status From request Where request_number='" + requestNumber + "'";
    try
    {
      rs = DBUtil.executeQuery(sql);
      if (rs.next())
      {
        RequestBean request = new RequestBean();
        request.setReqId(rs.getInt("req_id"));
        request.setStatus(rs.getInt("status"));
        
        return request;
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
  
  public int insertRequest(RequestBean bean)
    throws Exception
  {
    if (bean == null) {
      return 0;
    }
    int result = 0;
    try
    {
      String sql = "";
      String createdOrg = "null";
      String orgId = "null";
      String orgHandle = "";
      String orgRefer = "";
      String orgPaid = "";
      String emp = "";
      String bomAgreeDate = "null";
      String assEmp = "null";
      String check_comment = "";
      String store_comment = "";
      String bom_comment = "";
      String plandep_comment = "";
      if (bean.getCheckComment() != null) {
        check_comment = bean.getCheckComment();
      }
      if (bean.getStoreComment() != null) {
        store_comment = bean.getStoreComment();
      }
      if (bean.getBomComment() != null) {
        bom_comment = bean.getBomComment();
      }
      if (bean.getPlandepComment() != null) {
        plandep_comment = bean.getPlandepComment();
      }
      if (bean.getProId() != 0) {
        orgId = bean.getProId() + "";
      }
      if (bean.getOrgId() != 0) {
        orgId = bean.getOrgId() + "";
      }
      if (!GenericValidator.isBlankOrNull(bean.getOrgHandle())) {
        orgHandle = bean.getOrgHandle();
      }
      if (!GenericValidator.isBlankOrNull(bean.getOrgRefer())) {
        orgRefer = bean.getOrgRefer();
      }
      if (!GenericValidator.isBlankOrNull(bean.getOrgPaid())) {
        orgPaid = bean.getOrgPaid();
      }
      if (bean.getCreatedEmp() == 0) {
        emp = "null";
      } else {
        emp = bean.getCreatedEmp() + "";
      }
      if (bean.getAssignedEmp() != 0) {
        assEmp = bean.getAssignedEmp() + "";
      }
      if (bean.getCreatedOrg() != 0) {
        createdOrg = bean.getCreatedOrg() + "";
      }
      if (!GenericValidator.isBlankOrNull(bean.getBomAgreeDate())) {
        bomAgreeDate = "STR_TO_DATE('" + bean.getBomAgreeDate() + "','%d/%m/%Y')";
      }
      sql = "Insert Into request (org_id, created_emp, kind, request_number, created_date, approve_suggest, status_suggest, certificate_require, which_use, description, check_comment, store_comment, bom_comment, plandep_comment, org_handle, org_refer, org_paid, intermemo_subject, check_approve, store_approve, bom_approve, plandep_approve,created_org, assigned_emp, approve_emp, bom_agree_date, status, store_org, purchase_org) Values (" + orgId + "," + emp + "," + bean.getKind() + ",'" + bean.getRequestNumber() + "',sysdate(),'" + bean.getApproveSuggest() + "'," + bean.getStatusSuggest() + ",'" + bean.getCertificateRequire() + "'," + bean.getWhichUse() + ",'" + bean.getDescription() + "','" + check_comment + "','" + store_comment + "','" + bom_comment + "','" + plandep_comment + "','" + orgHandle + "','" + orgRefer + "','" + orgPaid + "','" + bean.getIntermemoSubject() + "'," + bean.getCheckApprove() + "," + bean.getStoreApprove() + "," + bean.getBomApprove() + "," + bean.getPlandepApprove() + "," + createdOrg + "," + assEmp + "," + bean.getApproveEmp() + "," + bomAgreeDate + "," + bean.getStatus() + "," + bean.getStoreOrg() + "," + bean.getPurchaseOrg() + ")";
      
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
  
  public void updateRequest(RequestBean bean)
    throws Exception
  {
    if (bean == null) {
      return;
    }
    try
    {
      String orgId = "null";
      String orgHandle = "";
      String orgRefer = "";
      String orgPaid = "";
      String createdOrg = "null";
      String assEmp = "null";
      if (bean.getProId() != 0) {
        orgId = bean.getProId() + "";
      }
      if (bean.getOrgId() != 0) {
        orgId = bean.getOrgId() + "";
      }
      if (!GenericValidator.isBlankOrNull(bean.getOrgHandle())) {
        orgHandle = bean.getOrgHandle();
      }
      if (!GenericValidator.isBlankOrNull(bean.getOrgRefer())) {
        orgRefer = bean.getOrgRefer();
      }
      if (!GenericValidator.isBlankOrNull(bean.getOrgPaid())) {
        orgPaid = bean.getOrgPaid();
      }
      if (bean.getCreatedOrg() != 0) {
        createdOrg = bean.getCreatedOrg() + "";
      }
      if (bean.getAssignedEmp() != 0) {
        assEmp = bean.getAssignedEmp() + "";
      }
      String sql = "Update request Set  org_id=" + orgId + ", request_number='" + bean.getRequestNumber() + "'" + ", approve_suggest='" + bean.getApproveSuggest() + "'" + ", status_suggest=" + bean.getStatusSuggest() + ", certificate_require='" + bean.getCertificateRequire() + "'" + ", which_use=" + bean.getWhichUse() + ", description='" + bean.getDescription() + "'" + ", check_comment='" + bean.getCheckComment() + "'" + ", store_comment='" + bean.getStoreComment() + "'" + ", bom_comment='" + bean.getBomComment() + "'" + ", plandep_comment='" + bean.getPlandepComment() + "'" + ", org_handle='" + orgHandle + "'" + ", org_refer='" + orgRefer + "'" + ", org_paid='" + orgPaid + "'" + ", intermemo_subject='" + bean.getIntermemoSubject() + "'" + ", check_approve=" + bean.getCheckApprove() + ", store_approve=" + bean.getStoreApprove() + ", bom_approve=" + bean.getBomApprove() + ", plandep_approve=" + bean.getPlandepApprove() + ", created_org=" + createdOrg + ", assigned_emp=" + assEmp + ", store_org=" + bean.getStoreOrg();
      if (!GenericValidator.isBlankOrNull(bean.getBomAgreeDate())) {
        sql = sql + ", bom_agree_date=STR_TO_DATE('" + bean.getBomAgreeDate() + "','%d/%m/%Y')";
      }
      sql = sql + ",status=" + bean.getStatus();
      sql = sql + " Where req_id=" + bean.getReqId();
      
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
  
  public void updateRequestCreatedEmp(RequestBean bean)
    throws Exception
  {
    if (bean == null) {
      return;
    }
    try
    {
      String sql = "Update request Set created_emp=" + bean.getCreatedEmp() + " Where req_id=" + bean.getReqId();
      
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
  
  public int deleteRequest(String reqId)
    throws Exception
  {
    int result = 0;
    try
    {
      String sql = "Delete From request_file_attachment Where p_id=" + reqId;
      result = DBUtil.executeUpdate(sql);
      String matIds = getMaterialIdNotCode(NumberUtil.parseInt(reqId, 0));
      if (matIds.startsWith(",")) {
        matIds = matIds.substring(1);
      }
      if (!GenericValidator.isBlankOrNull(matIds))
      {
        sql = "Delete From material Where mat_id in (" + matIds + ")";
        result = DBUtil.executeUpdate(sql);
      }
      sql = "Delete From request_detail Where req_id=" + reqId;
      result = DBUtil.executeUpdate(sql);
      sql = "Delete From request Where req_id=" + reqId;
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
  
  public ArrayList searchSimpleRequest(int fieldid, String strFieldvalue, int status)
    throws Exception
  {
    ResultSet rs = null;
    String sql = "select DISTINCT req.req_id, req.created_date, request_number, which_use, o.name as organizationName, o.kind as orgKind, co.name as createdOrg, GROUP_CONCAT(DISTINCT e.fullname) as assignedEmployee, bom_agree_date, req.status, req.created_emp, (select min(rdet.material_kind) from request_detail as rdet where rdet.req_id=req.req_id) as materialKind, (select min(rdet.provide_date) from request_detail as rdet where rdet.req_id=req.req_id) as ros, (SELECT COUNT(emp_id) FROM request_detail AS rdet WHERE rdet.req_id=req.req_id AND rdet.emp_id IS NOT NULL) AS handleEmpCount from request as req LEFT JOIN request_detail AS rdet ON rdet.req_id=req.req_id left join organization as o on req.org_id=o.org_id left join organization as co on req.created_org=co.org_id left join employee as e on rdet.emp_id=e.emp_id where req.created_emp<>1 and req.kind=" + RequestBean.REQUEST;
    
    sql = sql + " and (0 OR ( req.created_emp=" + getRequestEmp() + ")";
    if (!StringUtil.isBlankOrNull(getInvolvedOrgs())) {
      sql = sql + " OR req.created_org IN (SELECT b.org_id FROM employee AS b WHERE b.emp_id=" + getRequestEmp() + " and b.org_id IN (" + getInvolvedOrgs() + ") AND b.pos_id IN (4,5,9,10))";
    } else {
      sql = sql + " OR req.created_org IN (SELECT b.org_id FROM employee AS b WHERE b.emp_id=" + getRequestEmp() + " and b.org_id=req.created_org AND b.pos_id IN (4,5,9,10))";
    }
    if (status != RequestBean.STATUS_CREATE) {
      if (status == RequestBean.STATUS_STORE)
      {
        sql = sql + " OR (req.STATUS>=" + RequestBean.STATUS_STORE;
        if (!getRequestOrg().equals("")) {
          sql = sql + " and req.store_org in (" + getRequestOrg() + ")";
        }
        sql = sql + ")";
      }
      else if (status == RequestBean.STATUS_CHECK)
      {
        sql = sql + " OR (req.STATUS=" + RequestBean.STATUS_CHECK + " and " + getRequestEmp() + " in (select emp_id from employee where org_id=req.created_org and pos_id in (4,9)))";
      }
      else if (status == RequestBean.STATUS_PLANDEP)
      {
        sql = sql + " OR (req.STATUS=" + RequestBean.STATUS_PLANDEP + " and " + getRequestEmp() + " in (select emp_id from employee where org_id=req.purchase_org and pos_id in (4,9)))";
        
        sql = sql + " OR (req.STATUS=" + RequestBean.STATUS_PLANDEP + " and " + getRequestEmp() + " in (select emp_id from project_employee where pro_id=req.purchase_org))";
      }
      else if (status == RequestBean.STATUS_MANAGER)
      {
        sql = sql + " OR req.STATUS>=" + RequestBean.STATUS_MANAGER;
      }
    }
    sql = sql + " OR (req.STATUS=" + RequestBean.STATUS_HANDLE + " AND req.assigned_emp IS NULL AND " + getRequestEmp() + " IN" + " (SELECT e.emp_id FROM employee AS e, request_handled AS rh" + " WHERE e.org_id=rh.org_id AND rh.req_id=req.req_id AND e.pos_id IN (4,5,9,10)))";
    
    sql = sql + " OR (req.STATUS=" + RequestBean.STATUS_HANDLE + " and " + getRequestEmp() + " in ";
    
    sql = sql + "( select emp_id from employee where emp_id=req.assigned_emp";
    sql = sql + " union select emp_id from request_detail where req_id=req.req_id";
    
    sql = sql + "))";
    if (!StringUtil.isBlankOrNull(getInvolvedOrgs())) {
      sql = sql + " OR (req.req_id IN (SELECT rh.req_id FROM request_handled AS rh WHERE rh.org_id IN (" + getInvolvedOrgs() + ")))";
    }
    sql = sql + ")";
    if ((fieldid > 0) && (!StringUtil.isBlankOrNull(strFieldvalue)))
    {
      if (fieldid == 1)
      {
        sql = sql + " and (" + SQLSearchExpressionUtil.excuteExpression(strFieldvalue, "request_number") + ")";
      }
      else if (fieldid == 2)
      {
        sql = sql + " and req.created_org in (select org_id from organization where kind<>" + OrganizationBean.KIND_PROJECT;
        sql = sql + " and (" + SQLSearchExpressionUtil.excuteExpression(strFieldvalue, "name") + "))";
      }
      else if (fieldid == 3)
      {
        sql = sql + " and req.org_id in (select org_id from organization where kind=" + OrganizationBean.KIND_PROJECT;
        sql = sql + " and (" + SQLSearchExpressionUtil.excuteExpression(strFieldvalue, "name") + "))";
      }
      else if (fieldid == 4)
      {
        sql = sql + " AND (DATE_FORMAT(bom_agree_date,'%d/%m/%Y') LIKE '%" + strFieldvalue.replace("/", "-") + "%'";
        strFieldvalue = strFieldvalue.replace("/", "-");
        sql = sql + " OR DATE_FORMAT(bom_agree_date,'%d/%m/%Y') LIKE '%" + strFieldvalue.replace("-", "/") + "%')";
      }
      else if (fieldid == 5)
      {
        sql = sql + " and rdet.emp_id in (select emp_id from employee where " + SQLSearchExpressionUtil.excuteExpression(strFieldvalue, "fullname") + ")";
      }
      else if (fieldid == 6)
      {
        String a = strFieldvalue.toUpperCase();
        sql = sql + " and req.status =";
        if (MCUtil.getBundleString("message.request.created").toUpperCase().indexOf(a) > -1)
        {
          strFieldvalue = RequestBean.STATUS_CREATE + "";
        }
        else if (MCUtil.getBundleString("message.request.changeToStore").toUpperCase().indexOf(a) > -1)
        {
          strFieldvalue = RequestBean.STATUS_STORE + "";
        }
        else if (MCUtil.getBundleString("message.request.changeToStoreAccept").toUpperCase().indexOf(a) > -1)
        {
          strFieldvalue = RequestBean.STATUS_STORE_ACCEPT + "";
        }
        else if (MCUtil.getBundleString("message.request.changeToCheck").toUpperCase().indexOf(a) > -1)
        {
          strFieldvalue = RequestBean.STATUS_CHECK + "";
        }
        else if (MCUtil.getBundleString("message.request.changeToPlanDep").toUpperCase().indexOf(a) > -1)
        {
          strFieldvalue = RequestBean.STATUS_PLANDEP + "";
        }
        else if (MCUtil.getBundleString("message.request.changeToManage").toUpperCase().indexOf(a) > -1)
        {
          strFieldvalue = RequestBean.STATUS_MANAGER + "";
        }
        else
        {
          int ind = a.indexOf(MCUtil.getBundleString("message.request.changeToHandle").toUpperCase());
          if (ind > -1) {
            a = a.substring(0, ind - 1);
          }
          strFieldvalue = RequestBean.STATUS_HANDLE + " and (" + SQLSearchExpressionUtil.excuteExpression(a, "ho.name") + ")";
        }
        sql = sql + strFieldvalue;
      }
      if (fieldid == 7) {
        sql = sql + " and mat_id in (select m.mat_id from material as m where 1 and  (" + SQLSearchExpressionUtil.excuteExpression(strFieldvalue, "m.name_vn") + "))";
      }
    }
    sql = sql + " GROUP BY req.req_id order by req.req_id desc";
    ArrayList requestList = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      RequestBean request = null;
      
      int materialKind = 0;
      String str = "";
      while (rs.next())
      {
        request = new RequestBean();
        request.setReqId(rs.getInt("req_id"));
        request.setCreatedEmp(rs.getInt("created_emp"));
        if (request.getCreatedEmp() == getRequestEmp()) {
          request.setCanDelete(true);
        } else {
          request.setCanDelete(false);
        }
        request.setCreatedDate(DateUtil.formatDate(rs.getDate("created_date"), "dd/MM/yyyy"));
        request.setBomAgreeDate(DateUtil.formatDate(rs.getDate("bom_agree_date"), "dd/MM/yyyy"));
        request.setRequestNumber(rs.getString("request_number"));
        request.setWhichUseName(rs.getString("organizationName"));
        request.setCreatedOrganizationName(rs.getString("createdOrg"));
        request.setEmployeeName(rs.getString("assignedEmployee"));
        request.setRos(DateUtil.formatDate(rs.getDate("ros"), "dd/MM/yyyy"));
        materialKind = rs.getInt("materialKind");
        if (materialKind == RequestFormBean.KIND_MAJOR) {
          request.setMaterialKind(MCUtil.getBundleString("message.request.materialKind.major"));
        } else if (materialKind == RequestFormBean.KIND_SECONDARY) {
          request.setMaterialKind(MCUtil.getBundleString("message.request.materialKind.secondary"));
        }
        status = rs.getInt("status");
        if (status == RequestBean.STATUS_CREATE) {
          request.setStatusText(MCUtil.getBundleString("message.request.created"));
        } else if (status == RequestBean.STATUS_STORE) {
          request.setStatusText(MCUtil.getBundleString("message.request.changeToStore"));
        } else if (status == RequestBean.STATUS_STORE_ACCEPT) {
          request.setStatusText(MCUtil.getBundleString("message.request.changeToStoreAccept"));
        } else if (status == RequestBean.STATUS_CHECK) {
          request.setStatusText(MCUtil.getBundleString("message.request.changeToCheck"));
        } else if (status == RequestBean.STATUS_PLANDEP) {
          request.setStatusText(MCUtil.getBundleString("message.request.changeToPlanDep"));
        } else if (status == RequestBean.STATUS_MANAGER) {
          request.setStatusText(MCUtil.getBundleString("message.request.changeToManage"));
        } else if (status == RequestBean.STATUS_HANDLE) {
          request.setStatusText(MCUtil.getBundleString("message.request.changeToHandle.1"));
        }
        if (rs.getInt("handleEmpCount") == 0) {
          request.setHighlight(1);
        }
        requestList.add(request);
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
    return requestList;
  }
  
  public ArrayList searchSimpleRequestPermission(int fieldid, String strFieldvalue)
    throws Exception
  {
    ResultSet rs = null;
    String sql = "select DISTINCT req_id, req.created_date, request_number, which_use, o.name as organizationName, o.kind as orgKind, co.name as createdOrg, e.fullname as assignedEmployee, bom_agree_date, (select min(rdet.material_kind) from request_detail as rdet where rdet.req_id=req.req_id) as materialKind, (select min(rdet.provide_date) from request_detail as rdet where rdet.req_id=req.req_id) as ros from request as req left join  organization as o on req.org_id=o.org_id left join organization as co on req.created_org=co.org_id left join employee as e on req.assigned_emp=e.emp_id , employee as ohe where plandep_approve = 1 AND req.created_emp<>1 and req.kind=" + RequestBean.REQUEST;
    
    sql = sql + " and (( ohe.org_id in (select '(0,' + req.org_handle + ',0)')) or (req.created_emp=ohe.emp_id and req.created_emp=" + getRequestEmp() + "))";
    if ((fieldid > 0) && (!StringUtil.isBlankOrNull(strFieldvalue))) {
      if (fieldid == 1)
      {
        sql = sql + " AND request_number LIKE '%" + strFieldvalue + "%'";
      }
      else if (fieldid == 2)
      {
        sql = sql + " and req.created_org in (select org_id from organization where kind<>" + OrganizationBean.KIND_PROJECT + " and name LIKE '%" + strFieldvalue + "%')";
      }
      else if (fieldid == 3)
      {
        sql = sql + " and req.org_id in (select org_id from organization where kind=" + OrganizationBean.KIND_PROJECT + " and name LIKE '%" + strFieldvalue + "%')";
      }
      else if (fieldid == 4)
      {
        sql = sql + " AND (DATE_FORMAT(bom_agree_date,'%d/%m/%Y') LIKE '%" + strFieldvalue.replace("/", "-") + "%'";
        strFieldvalue = strFieldvalue.replace("/", "-");
        sql = sql + " OR DATE_FORMAT(bom_agree_date,'%d/%m/%Y') LIKE '%" + strFieldvalue.replace("-", "/") + "%')";
      }
    }
    sql = sql + " order by req_id desc";
    ArrayList requestList = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      RequestBean request = null;
      int materialKind = 0;
      while (rs.next())
      {
        request = new RequestBean();
        request.setReqId(rs.getInt("req_id"));
        request.setCreatedDate(DateUtil.formatDate(rs.getDate("created_date"), "dd/MM/yyyy"));
        request.setBomAgreeDate(DateUtil.formatDate(rs.getDate("bom_agree_date"), "dd/MM/yyyy"));
        request.setRequestNumber(rs.getString("request_number"));
        request.setWhichUseName(rs.getString("organizationName"));
        request.setCreatedOrganizationName(rs.getString("createdOrg"));
        request.setEmployeeName(rs.getString("assignedEmployee"));
        request.setRos(DateUtil.formatDate(rs.getDate("ros"), "dd/MM/yyyy"));
        materialKind = rs.getInt("materialKind");
        if (materialKind == RequestFormBean.KIND_MAJOR) {
          request.setMaterialKind(MCUtil.getBundleString("message.request.materialKind.major"));
        } else if (materialKind == RequestFormBean.KIND_SECONDARY) {
          request.setMaterialKind(MCUtil.getBundleString("message.request.materialKind.secondary"));
        }
        if (getMaterialNotCode().length() > getMaterialNotCode().replace(rs.getString("req_id"), "").length()) {
          request.setHighlight(1);
        } else {
          request.setHighlight(0);
        }
        requestList.add(request);
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
    return requestList;
  }
  
  public ArrayList searchAdvRequest(SearchAdvRequestFormBean formBean)
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "select req_id, req.created_date, request_number, which_use, o.name as organizationName, o.kind as orgKind , co.name as createdOrg, e.fullname as assignedEmployee, bom_agree_date , (select min(rdet.material_kind) from request_detail as rdet where rdet.req_id=req.req_id) as materialKind , (select min(rdet.provide_date) from request_detail as rdet where rdet.req_id=req.req_id) as ros from request as req  left join organization as o on req.org_id=o.org_id left join organization as co on req.created_org=co.org_id left join employee as e on req.assigned_emp=e.emp_id where req.kind=" + RequestBean.REQUEST;
    if (!StringUtil.isBlankOrNull(formBean.getRequestNumber())) {
      sql = sql + " AND request_number LIKE '%" + formBean.getRequestNumber() + "%'";
    }
    if (!StringUtil.isBlankOrNull(formBean.getFromDate()))
    {
      sql = sql + " AND DATE(req.created_date) >= STR_TO_DATE('" + formBean.getFromDate() + "','%d/%m/%Y')";
      if (!StringUtil.isBlankOrNull(formBean.getToDate())) {
        sql = sql + " AND STR_TO_DATE('" + formBean.getFromDate() + "','%d/%m/%Y') <= STR_TO_DATE('" + formBean.getToDate() + "','%d/%m/%Y')";
      }
    }
    if (!StringUtil.isBlankOrNull(formBean.getToDate()))
    {
      sql = sql + " AND DATE(req.created_date) <= STR_TO_DATE('" + formBean.getToDate() + "','%d/%m/%Y')";
      if (!StringUtil.isBlankOrNull(formBean.getFromDate())) {
        sql = sql + " AND STR_TO_DATE('" + formBean.getFromDate() + "','%d/%m/%Y') <= STR_TO_DATE('" + formBean.getToDate() + "','%d/%m/%Y')";
      }
    }
    if (formBean.getCreatedEmp() != 0) {
      sql = sql + " AND created_emp=" + formBean.getCreatedEmp();
    }
    if (formBean.getCreatedOrg() != 0) {
      sql = sql + " AND req.created_org=" + formBean.getCreatedOrg();
    }
    if (formBean.getWhichUse() != 0)
    {
      sql = sql + " AND req.which_use=" + formBean.getWhichUse();
      if (formBean.getOrgId() != 0) {
        sql = sql + " AND req.org_id=" + formBean.getOrgId();
      }
      if (formBean.getProId() != 0) {
        sql = sql + " AND req.org_id=" + formBean.getProId();
      }
    }
    sql = sql + " order by req_id desc";
    
    ArrayList requestList = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      RequestBean request = null;
      
      int materialKind = 0;
      while (rs.next())
      {
        request = new RequestBean();
        request.setReqId(rs.getInt("req_id"));
        request.setCreatedDate(DateUtil.formatDate(rs.getDate("created_date"), "dd/MM/yyyy"));
        request.setBomAgreeDate(DateUtil.formatDate(rs.getDate("bom_agree_date"), "dd/MM/yyyy"));
        request.setRequestNumber(rs.getString("request_number"));
        request.setWhichUseName(rs.getString("organizationName"));
        request.setCreatedOrganizationName(rs.getString("createdOrg"));
        request.setEmployeeName(rs.getString("assignedEmployee"));
        request.setRos(DateUtil.formatDate(rs.getDate("ros"), "dd/MM/yyyy"));
        materialKind = rs.getInt("materialKind");
        if (materialKind == RequestFormBean.KIND_MAJOR) {
          request.setMaterialKind(MCUtil.getBundleString("message.request.materialKind.major"));
        } else if (materialKind == RequestFormBean.KIND_SECONDARY) {
          request.setMaterialKind(MCUtil.getBundleString("message.request.materialKind.secondary"));
        }
        requestList.add(request);
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
    return requestList;
  }
  
  public ArrayList searchAdvRequestPermission(SearchAdvRequestFormBean formBean)
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "select req_id, req.created_date, request_number, which_use, o.name as organizationName, o.kind as orgKind , co.name as createdOrg, e.fullname as assignedEmployee, bom_agree_date , (select min(rdet.material_kind) from request_detail as rdet where rdet.req_id=req.req_id) as materialKind , (select min(rdet.provide_date) from request_detail as rdet where rdet.req_id=req.req_id) as ros from request as req  left join organization as o on req.org_id=o.org_id left join organization as co on req.created_org=co.org_id left join employee as e on req.assigned_emp=e.emp_id where plandep_approve = 1 AND req.kind=" + RequestBean.REQUEST;
    if (!StringUtil.isBlankOrNull(formBean.getRequestNumber())) {
      sql = sql + " AND request_number LIKE '%" + formBean.getRequestNumber() + "%'";
    }
    if (!StringUtil.isBlankOrNull(formBean.getFromDate()))
    {
      sql = sql + " AND DATE(req.created_date) >= STR_TO_DATE('" + formBean.getFromDate() + "','%d/%m/%Y')";
      if (!StringUtil.isBlankOrNull(formBean.getToDate())) {
        sql = sql + " AND STR_TO_DATE('" + formBean.getFromDate() + "','%d/%m/%Y') <= STR_TO_DATE('" + formBean.getToDate() + "','%d/%m/%Y')";
      }
    }
    if (!StringUtil.isBlankOrNull(formBean.getToDate()))
    {
      sql = sql + " AND DATE(req.created_date) <= STR_TO_DATE('" + formBean.getToDate() + "','%d/%m/%Y')";
      if (!StringUtil.isBlankOrNull(formBean.getFromDate())) {
        sql = sql + " AND STR_TO_DATE('" + formBean.getFromDate() + "','%d/%m/%Y') <= STR_TO_DATE('" + formBean.getToDate() + "','%d/%m/%Y')";
      }
    }
    if (formBean.getCreatedEmp() != 0) {
      sql = sql + " AND created_emp=" + formBean.getCreatedEmp();
    }
    if (formBean.getCreatedOrg() != 0) {
      sql = sql + " AND req.created_org=" + formBean.getCreatedOrg();
    }
    if (formBean.getWhichUse() != 0)
    {
      sql = sql + " AND req.which_use=" + formBean.getWhichUse();
      if (formBean.getOrgId() != 0) {
        sql = sql + " AND req.org_id=" + formBean.getOrgId();
      }
      if (formBean.getProId() != 0) {
        sql = sql + " AND req.org_id=" + formBean.getProId();
      }
    }
    sql = sql + " order by req_id desc";
    
    ArrayList requestList = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      RequestBean request = null;
      
      int materialKind = 0;
      while (rs.next())
      {
        request = new RequestBean();
        request.setReqId(rs.getInt("req_id"));
        request.setCreatedDate(DateUtil.formatDate(rs.getDate("created_date"), "dd/MM/yyyy"));
        request.setBomAgreeDate(DateUtil.formatDate(rs.getDate("bom_agree_date"), "dd/MM/yyyy"));
        request.setRequestNumber(rs.getString("request_number"));
        request.setWhichUseName(rs.getString("organizationName"));
        request.setCreatedOrganizationName(rs.getString("createdOrg"));
        request.setEmployeeName(rs.getString("assignedEmployee"));
        request.setRos(DateUtil.formatDate(rs.getDate("ros"), "dd/MM/yyyy"));
        materialKind = rs.getInt("materialKind");
        if (materialKind == RequestFormBean.KIND_MAJOR) {
          request.setMaterialKind(MCUtil.getBundleString("message.request.materialKind.major"));
        } else if (materialKind == RequestFormBean.KIND_SECONDARY) {
          request.setMaterialKind(MCUtil.getBundleString("message.request.materialKind.secondary"));
        }
        requestList.add(request);
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
    return requestList;
  }
  
  public ArrayList getRequestDetails(int reqId)
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "Select d.*, m.name_vn as MaterialName, m.code, e.fullname From request_detail as d left join material as m on d.original_mat_id=m.mat_id left join employee as e on e.emp_id=d.emp_id Where req_id=" + reqId + " order by d.det_id asc";
    
    ArrayList request_detailList = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      RequestDetailFormBean request_detail = null;
      int no = 1;
      while (rs.next())
      {
        request_detail = new RequestDetailFormBean();
        request_detail.setNo(no++);
        request_detail.setDetId(rs.getInt("det_id"));
        request_detail.setReqId(rs.getInt("req_id"));
        request_detail.setMatId(rs.getInt("mat_id"));
        if (rs.getString("MaterialName") != null) {
          request_detail.setMatName(StringUtil.decodeString(rs.getString("MaterialName")));
        }
        request_detail.setMatCode(rs.getString("code"));
        request_detail.setConId(rs.getInt("con_id"));
        request_detail.setUnit(rs.getString("unit"));
        request_detail.setPresentQuantity(rs.getDouble("present_quantity"));
        request_detail.setAdditionalQuantity(rs.getDouble("additional_quantity"));
        request_detail.setRequestQuantity(rs.getDouble("request_quantity"));
        request_detail.setRemainQuantity(rs.getDouble("remain_quantity"));
        request_detail.setProvideDate(DateUtil.formatDate(rs.getDate("provide_date"), "dd/MM/yyyy"));
        
        request_detail.setIntermemoNote(rs.getString("intermemo_note"));
        request_detail.setStep(rs.getString("step"));
        request_detail.setStepId(rs.getInt("step_id"));
        request_detail.setMaterialKind(rs.getInt("material_kind"));
        request_detail.setEmpId(rs.getInt("emp_id"));
        request_detail.setIsCancel(rs.getInt("is_cancel"));
        request_detailList.add(request_detail);
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
    return request_detailList;
  }
  
  public RequestDetailBean getRequestDetailForMrir(int reqId, int matId)
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "Select request_detail.*, name_vn, code From request_detail left join material on request_detail.mat_id = material.mat_id Where req_id = " + reqId + " and request_detail.mat_id = " + matId;
    try
    {
      rs = DBUtil.executeQuery(sql);
      RequestDetailBean request_detail = null;
      if (rs.next())
      {
        request_detail = new RequestDetailBean();
        request_detail.setDetId(rs.getInt("det_id"));
        request_detail.setReqId(rs.getInt("req_id"));
        request_detail.setMatId(rs.getInt("mat_id"));
        request_detail.setMatName(StringUtil.decodeString(rs.getString("name_vn")));
        request_detail.setUnit(rs.getString("unit"));
        request_detail.setPresentQuantity(rs.getDouble("present_quantity"));
        request_detail.setAdditionalQuantity(rs.getDouble("additional_quantity"));
        
        return request_detail;
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
  
  public int insertRequestDetail(RequestDetailBean bean)
    throws Exception
  {
    if (bean == null) {
      return 0;
    }
    int result = 0;
    try
    {
      String sql = "";
      String provideDate = "";
      if (GenericValidator.isBlankOrNull(bean.getProvideDate())) {
        provideDate = "null";
      } else {
        provideDate = "STR_TO_DATE('" + bean.getProvideDate() + "','%d/%m/%Y')";
      }
      sql = "Insert Into request_detail(req_id, mat_id, unit, present_quantity, additional_quantity, request_quantity, provide_date, intermemo_note, step, step_id, material_kind, remain_quantity, original_mat_id";
      if (bean.getEmpId() > 0) {
        sql = sql + ", emp_id";
      }
      if (bean.getOrgId() > 0) {
        sql = sql + ", org_id";
      }
      sql = sql + ") Values (" + bean.getReqId() + "," + bean.getMatId() + ",'" + bean.getUnit() + "'," + bean.getPresentQuantity() + "," + bean.getAdditionalQuantity() + "," + bean.getRequestQuantity() + "," + provideDate + ",'" + bean.getIntermemoNote() + "','" + bean.getStep() + "'," + bean.getStepId() + "," + bean.getMaterialKind() + "," + bean.getRemainQuantity() + "," + bean.getMatId();
      if (bean.getEmpId() > 0) {
        sql = sql + ", " + bean.getEmpId();
      }
      if (bean.getOrgId() > 0) {
        sql = sql + ", " + bean.getOrgId();
      }
      sql = sql + ")";
      
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
  
  public int insertRequestCopyDetail(RequestDetailBean bean)
    throws Exception
  {
    int id = 0;
    try
    {
      String sql = "";
      String provideDate = "";
      if (GenericValidator.isBlankOrNull(bean.getProvideDate())) {
        provideDate = "null";
      } else {
        provideDate = "STR_TO_DATE('" + bean.getProvideDate() + "','%d/%m/%Y')";
      }
      sql = "INSERT INTO request_detail(req_id, mat_id, unit, present_quantity, additional_quantity, request_quantity, provide_date, intermemo_note, step, step_id, material_kind, remain_quantity, original_mat_id, emp_id, is_cancel, org_id)";
      
      sql = sql + " SELECT " + bean.getReqId() + "," + bean.getMatId() + ",'" + bean.getUnit() + "'," + bean.getPresentQuantity() + "," + bean.getAdditionalQuantity() + "," + bean.getRequestQuantity() + "," + provideDate + ",'" + bean.getIntermemoNote() + "',  step_id, step_id, material_kind," + bean.getRemainQuantity() + ", original_mat_id, emp_id, is_cancel, org_id" + " FROM request_detail WHERE det_id=" + bean.getDetId();
      
      DBUtil.executeInsert(sql);
    }
    catch (SQLException sqle)
    {
      throw new Exception(sqle.getMessage());
    }
    catch (Exception ex)
    {
      throw new Exception(ex.getMessage());
    }
    return id;
  }
  
  public void updateRequestDetail(RequestDetailBean bean)
    throws Exception
  {
    if (bean == null) {
      return;
    }
    try
    {
      String provideDate = "";
      if (GenericValidator.isBlankOrNull(bean.getProvideDate())) {
        provideDate = "null";
      } else {
        provideDate = "STR_TO_DATE('" + bean.getProvideDate() + "','%d/%m/%Y')";
      }
      String sql = "Update request_detail Set additional_quantity=" + bean.getAdditionalQuantity() + ", request_quantity=" + bean.getRequestQuantity() + ", remain_quantity=" + bean.getRemainQuantity() + ", present_quantity=" + bean.getPresentQuantity() + ", provide_date=" + provideDate + ", material_kind=" + bean.getMaterialKind() + ", unit='" + bean.getUnit() + "'";
      if (bean.getEmpId() > 0)
      {
        sql = sql + ", emp_id=" + bean.getEmpId();
        sql = sql + ", org_id=" + bean.getOrgId();
      }
      else if (bean.getEmpId() == -1)
      {
        sql = sql + ", emp_id=null, org_id=null";
      }
      sql = sql + " Where det_id=" + bean.getDetId();
      
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
  
  public void updateUnitRequestDetail(int unit, int reqId, int matId)
    throws Exception
  {
    try
    {
      String sql = "Update request_detail Set  unit=(SELECT unit_vn FROM unit WHERE uni_id = " + unit + ")";
      
      sql = sql + " Where req_id=" + reqId + " and mat_id = " + matId;
      
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
  
  public void updateUnitRequestDetail(int unit, String reqDetIds)
    throws Exception
  {
    try
    {
      String sql = "Update request_detail Set  unit=(SELECT unit_vn FROM unit WHERE uni_id = " + unit + ")";
      
      sql = sql + " Where det_id in (" + reqDetIds + ")";
      
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
  
  public String getOrderRelationRequestDetail(String detId)
    throws Exception
  {
    ResultSet rs = null;
    String result = "";
    try
    {
      String sql = "select c.contract_number, c.kind, c.appendix_contract_number from contract as c, contract_detail as det Where det.con_id=c.con_id and det.reqDetail_id=" + detId;
      
      rs = DBUtil.executeQuery(sql);
      while (rs.next())
      {
        String a = "";
        if (rs.getInt("kind") == ContractBean.KIND_APPENDIX) {
          a = rs.getString("contract_number");
        } else {
          a = rs.getString("contract_number");
        }
        result = result + ", " + a;
      }
      if (!result.equals("")) {
        result = result.substring(2);
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
  
  public int deleteRequestDetail(String detId)
    throws Exception
  {
    int result = 0;
    try
    {
      String sql = "Delete From request_detail  Where det_id=" + detId;
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
  
  public ArrayList getIntermemos()
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "select req_id, req.created_date, request_number, status_suggest, e.fullname as employeeName, o.name as organizationName, intermemo_subject from request as req left join employee as e on req.created_emp=e.emp_id left join organization as o on e.org_id=o.org_id where req.kind=" + RequestBean.INTERMEMO;
    
    ArrayList requestList = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      RequestBean request = null;
      int statusSuggest = 0;
      while (rs.next())
      {
        request = new RequestBean();
        request.setReqId(rs.getInt("req_id"));
        request.setCreatedDate(DateUtil.formatDate(rs.getDate("created_date"), "dd/MM/yyyy"));
        request.setRequestNumber(rs.getString("request_number"));
        request.setEmployeeName(rs.getString("employeeName"));
        request.setOrganizationName(rs.getString("organizationName"));
        request.setIntermemoSubject(rs.getString("intermemo_subject"));
        statusSuggest = rs.getInt("status_suggest");
        if (statusSuggest == 1) {
          request.setStatusName(MCUtil.getBundleString("message.request.statusSuggest1"));
        } else if (statusSuggest == 2) {
          request.setStatusName(MCUtil.getBundleString("message.request.statusSuggest2"));
        } else if (statusSuggest == 3) {
          request.setStatusName(MCUtil.getBundleString("message.request.statusSuggest3"));
        }
        requestList.add(request);
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
    return requestList;
  }
  
  public void updateIntermemoDetail(RequestDetailBean bean)
    throws Exception
  {
    if (bean == null) {
      return;
    }
    try
    {
      String sql = "Update request_detail Set additional_quantity=" + bean.getAdditionalQuantity() + ", request_quantity=" + bean.getRequestQuantity() + ", intermemo_note='" + bean.getIntermemoNote() + "'" + ", step='" + bean.getStep() + "'" + ", step_id=" + bean.getStepId() + ", material_kind=" + bean.getMaterialKind() + " Where det_id=" + bean.getDetId();
      
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
  
  public ArrayList searchSimpleIntermemo(int fieldid, String strFieldvalue)
    throws Exception
  {
    String strFieldname = "";
    switch (fieldid)
    {
    case 1: 
      strFieldname = "request_number";
    }
    ResultSet rs = null;
    
    String sql = "select req_id, req.created_date, request_number, status_suggest, e.fullname as employeeName, o.name as organizationName, intermemo_subject from request as req left join employee as e on req.created_emp=e.emp_id left join organization as o on e.org_id=o.org_id where req.kind=" + RequestBean.INTERMEMO;
    if ((fieldid > 0) && (!StringUtil.isBlankOrNull(strFieldvalue))) {
      sql = sql + " AND " + strFieldname + " LIKE '%" + strFieldvalue + "%'";
    }
    sql = sql + " order by created_date DESC";
    
    ArrayList requestList = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      RequestBean request = null;
      int statusSuggest = 0;
      while (rs.next())
      {
        request = new RequestBean();
        request.setReqId(rs.getInt("req_id"));
        request.setCreatedDate(DateUtil.formatDate(rs.getDate("created_date"), "dd/MM/yyyy"));
        request.setRequestNumber(rs.getString("request_number"));
        request.setEmployeeName(rs.getString("employeeName"));
        request.setOrganizationName(rs.getString("organizationName"));
        request.setIntermemoSubject(rs.getString("intermemo_subject"));
        statusSuggest = rs.getInt("status_suggest");
        if (statusSuggest == 1) {
          request.setStatusName(MCUtil.getBundleString("message.request.statusSuggest1"));
        } else if (statusSuggest == 2) {
          request.setStatusName(MCUtil.getBundleString("message.request.statusSuggest2"));
        } else if (statusSuggest == 3) {
          request.setStatusName(MCUtil.getBundleString("message.request.statusSuggest3"));
        }
        requestList.add(request);
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
    return requestList;
  }
  
  public ArrayList searchAdvIntermemo(SearchAdvIntermemoFormBean formBean)
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "select req_id, req.created_date, request_number, status_suggest, e.fullname as employeeName, o.name as organizationName, intermemo_subject from request as req left join employee as e on req.created_emp=e.emp_id left join organization as o on e.org_id=o.org_id where req.kind=" + RequestBean.INTERMEMO;
    if (!StringUtil.isBlankOrNull(formBean.getRequestNumber())) {
      sql = sql + " AND request_number LIKE '%" + formBean.getRequestNumber() + "%'";
    }
    if (!StringUtil.isBlankOrNull(formBean.getFromDate()))
    {
      sql = sql + " AND DATE(req.created_date) >= STR_TO_DATE('" + formBean.getFromDate() + "','%d/%m/%Y')";
      if (!StringUtil.isBlankOrNull(formBean.getToDate())) {
        sql = sql + " AND STR_TO_DATE('" + formBean.getFromDate() + "','%d/%m/%Y') <= STR_TO_DATE('" + formBean.getToDate() + "','%d/%m/%Y')";
      }
    }
    if (!StringUtil.isBlankOrNull(formBean.getToDate()))
    {
      sql = sql + " AND DATE(req.created_date) <= STR_TO_DATE('" + formBean.getToDate() + "','%d/%m/%Y')";
      if (!StringUtil.isBlankOrNull(formBean.getFromDate())) {
        sql = sql + " AND STR_TO_DATE('" + formBean.getFromDate() + "','%d/%m/%Y') <= STR_TO_DATE('" + formBean.getToDate() + "','%d/%m/%Y')";
      }
    }
    if (formBean.getCreatedEmp() != 0) {
      sql = sql + " AND created_emp=" + formBean.getCreatedEmp();
    }
    if (formBean.getStatusSuggest() != 0) {
      sql = sql + " AND status_suggest=" + formBean.getStatusSuggest();
    }
    if (!StringUtil.isBlankOrNull(formBean.getSubject())) {
      sql = sql + " AND intermemo_subject LIKE '%" + formBean.getSubject() + "%'";
    }
    ArrayList requestList = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      RequestBean request = null;
      int statusSuggest = 0;
      while (rs.next())
      {
        request = new RequestBean();
        request.setReqId(rs.getInt("req_id"));
        request.setCreatedDate(DateUtil.formatDate(rs.getDate("created_date"), "dd/MM/yyyy"));
        request.setRequestNumber(rs.getString("request_number"));
        request.setEmployeeName(rs.getString("employeeName"));
        request.setOrganizationName(rs.getString("organizationName"));
        request.setIntermemoSubject(rs.getString("intermemo_subject"));
        statusSuggest = rs.getInt("status_suggest");
        if (statusSuggest == 1) {
          request.setStatusName(MCUtil.getBundleString("message.request.statusSuggest1"));
        } else if (statusSuggest == 2) {
          request.setStatusName(MCUtil.getBundleString("message.request.statusSuggest2"));
        } else if (statusSuggest == 3) {
          request.setStatusName(MCUtil.getBundleString("message.request.statusSuggest3"));
        }
        requestList.add(request);
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
    return requestList;
  }
  
  public int insertAttachmentFile(FileAttachmentBean bean)
    throws Exception
  {
    if (bean == null) {
      return 0;
    }
    try
    {
      String sql = "";
      if (GenericValidator.isBlankOrNull(bean.getAttName())) {
        bean.setAttName("");
      }
      if (GenericValidator.isBlankOrNull(bean.getComments())) {
        bean.setComments("");
      }
      if (GenericValidator.isBlankOrNull(bean.getHref())) {
        bean.setHref("");
      }
      if (GenericValidator.isBlankOrNull(bean.getSource())) {
        bean.setSource("");
      }
      sql = "insert into request_file_attachment (created_emp,p_id,att_name,source,href,created_date,file_size,comments) values (" + bean.getCreatedEmp() + "," + bean.getPid() + "," + "'" + bean.getAttName() + "'," + "'" + bean.getSource() + "'," + "'" + bean.getHref() + "'," + "sysdate()," + bean.getFileSize() + "," + "'" + bean.getComments() + "')";
      
      return DBUtil.executeInsert(sql);
    }
    catch (SQLException sqle)
    {
      throw new Exception(sqle.getMessage());
    }
  }
  
  public ArrayList getAttachmentFiles(int pid)
    throws Exception
  {
    ArrayList fileList = new ArrayList();
    if (pid == 0) {
      return fileList;
    }
    ResultSet rs = null;
    try
    {
      String sql = "select att_id,created_emp,p_id,att_name,source,href,created_date,file_size,comments from request_file_attachment where p_id=" + pid;
      
      rs = DBUtil.executeQuery(sql);
      FileAttachmentBean fileBean;
      while (rs.next())
      {
        fileBean = new FileAttachmentBean();
        fileBean.setFtype(1);
        fileBean.setAttId(rs.getInt("att_id"));
        fileBean.setPid(rs.getInt("p_id"));
        fileBean.setCreatedEmp(rs.getInt("created_emp"));
        fileBean.setAttName(rs.getString("att_name"));
        fileBean.setSource(rs.getString("source"));
        fileBean.setHref(rs.getString("href"));
        fileBean.setFileSize(rs.getInt("file_size"));
        fileBean.setCreatedDate(DateUtil.formatDate(rs.getDate("created_date"), "dd/MM/yyyy"));
        fileBean.setComments(StringUtil.getString(rs.getString("comments")));
        fileList.add(fileBean);
      }
      return fileList;
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
  
  public FileAttachmentBean getAttachmentFile(int fid)
    throws Exception
  {
    if (fid == 0) {
      return null;
    }
    ResultSet rs = null;
    try
    {
      String sql = "select att_id,created_emp,p_id,att_name,source,href,created_date,file_size,comments from request_file_attachment where att_id=" + fid;
      
      rs = DBUtil.executeQuery(sql);
      if (rs.next())
      {
        FileAttachmentBean fileBean = new FileAttachmentBean();
        fileBean.setFtype(1);
        fileBean.setAttId(rs.getInt("att_id"));
        fileBean.setPid(rs.getInt("p_id"));
        fileBean.setCreatedEmp(rs.getInt("created_emp"));
        fileBean.setAttName(rs.getString("att_name"));
        fileBean.setSource(rs.getString("source"));
        fileBean.setHref(rs.getString("href"));
        fileBean.setFileSize(rs.getInt("file_size"));
        fileBean.setCreatedDate(DateUtil.formatDate(rs.getDate("created_date"), "dd/MM/yyyy"));
        fileBean.setComments(StringUtil.getString(rs.getString("comments")));
        
        return fileBean;
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
  
  public int deleteAttachmentFile(int fid)
    throws Exception
  {
    if (fid == 0) {
      return 0;
    }
    try
    {
      String sql = "delete from request_file_attachment where att_id=" + fid;
      return DBUtil.executeUpdate(sql);
    }
    catch (SQLException sqle)
    {
      throw new Exception(sqle.getMessage());
    }
  }
  
  public ArrayList getReqOrgs(String kind)
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "Select o.* From request_organization as r left join organization as o on r.org_id=o.org_id where r.kind='" + kind + "' Order by r.number";
    
    ArrayList organizationList = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      OrganizationBean organization = null;
      while (rs.next())
      {
        organization = new OrganizationBean();
        organization.setOrgId(rs.getInt("org_id"));
        organization.setName(rs.getString("name"));
        organization.setAbbreviate(rs.getString("abbreviate"));
        organization.setParentId(rs.getInt("parent_id"));
        organization.setStatus(rs.getInt("status"));
        organization.setKind(rs.getInt("kind"));
        if (rs.getInt("status") == 1) {
          organization.setStatusDetail(MCUtil.getBundleString("message.organization.status1"));
        } else if (rs.getInt("status") == 2) {
          organization.setStatusDetail(MCUtil.getBundleString("message.organization.status2"));
        }
        if (rs.getInt("kind") == 1) {
          organization.setKindDetail(MCUtil.getBundleString("message.organization.kind1"));
        } else if (rs.getInt("kind") == 2) {
          organization.setKindDetail(MCUtil.getBundleString("message.organization.kind2"));
        } else if (rs.getInt("kind") == 3) {
          organization.setKindDetail(MCUtil.getBundleString("message.organization.kind3"));
        } else if (rs.getInt("kind") == 4) {
          organization.setKindDetail(MCUtil.getBundleString("message.organization.kind4"));
        }
        organizationList.add(organization);
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
    return organizationList;
  }
  
  public ArrayList getOrgReqs(String kind)
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "Select r.* From request_organization as r, organization as o where r.org_id=o.org_id and r.kind='" + kind + "' Order by r.number";
    
    ArrayList organizationList = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      RequestOrganizationBean bean = null;
      while (rs.next())
      {
        bean = new RequestOrganizationBean();
        bean.setRoId(rs.getInt("ro_id"));
        bean.setOrgId(rs.getInt("org_id"));
        bean.setNumber(rs.getInt("number"));
        bean.setMapChar(rs.getString("map_char"));
        bean.setKind(rs.getString("kind"));
        organizationList.add(bean);
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
    return organizationList;
  }
  
  public RequestDetailFormBean checkRequestDetailStep(int reqDetailId, int step)
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "Select m.name_vn, req.request_number  From request_detail as d, request as req, material as m  Where d.req_id=req.req_id  and d.mat_id=m.mat_id  and det_id=" + reqDetailId + " and step_id<>" + step + " and remain_quantity=0";
    
    RequestDetailFormBean bean = null;
    try
    {
      rs = DBUtil.executeQuery(sql);
      while (rs.next())
      {
        bean = new RequestDetailFormBean();
        bean.setMatName(StringUtil.decodeString(rs.getString("name_vn")));
        bean.setReqNumber(rs.getString("request_number"));
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
    return bean;
  }
  
  @Deprecated
  public ArrayList getRequestListByDnId(int dnId)
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "select r.req_id, r.request_number,r.which_use,o.name from delivery_notice_detail as dnd left join request_detail as rd on dnd.req_detail_id = rd.det_id left join request as r on r.req_id = rd.req_id left join organization as o on r.org_id = o.org_id Where dnd.dn_id=" + dnId + " and dnd.status=0 " + " group by r.req_id";
    
    System.out.println(sql);
    
    ArrayList requestList = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      LabelValueBean request = null;
      while (rs.next())
      {
        request = new LabelValueBean();
        request.setValue(rs.getString("r.req_id"));
        int whichUse = rs.getInt("r.which_use");
        if (whichUse == RequestFormBean.WHICHUSE_PROJECT) {
          request.setLabel(rs.getString("r.request_number") + " - " + MCUtil.getBundleString("message.project") + " " + rs.getString("o.name"));
        } else {
          request.setLabel(rs.getString("r.request_number"));
        }
        requestList.add(request);
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
    return requestList;
  }
  
  public int getRequestForDmv(int reqId, DmvFormBean dmvBean)
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "Select s.sto_id,s.name,req.org_id,req.created_org,o.name,co.name,o.kind From request as req  left join employee as e on req.created_emp=e.emp_id  left join organization as co on req.created_org=co.org_id left join organization as o on req.org_id=o.org_id left join store as s on s.org_id=req.org_id Where req_id=" + reqId;
    try
    {
      rs = DBUtil.executeQuery(sql);
      if (rs.next())
      {
        if (rs.getInt("o.kind") == OrganizationBean.KIND_PROJECT)
        {
          dmvBean.setProId(rs.getInt("org_id"));
          dmvBean.setProName(rs.getString("o.name"));
          dmvBean.setOrgId(rs.getInt("req.created_org"));
          dmvBean.setOrgName(rs.getString("co.name"));
          dmvBean.setStoId(rs.getInt("s.sto_id"));
          dmvBean.setStoName(rs.getString("s.name"));
        }
        else
        {
          dmvBean.setStoId(1);
          dmvBean.setStoName(MCUtil.getBundleString("message.store.comp"));
          dmvBean.setOrgId(rs.getInt("org_id"));
          dmvBean.setOrgName(rs.getString("o.name"));
        }
        return 1;
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
  
  public ArrayList getRequestsOfProject(int proId)
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "select req_id, created_date, request_number, approve_suggest, status_suggest, which_use, o.name as organizationName, o.kind as orgKind from request as req left join organization as o on req.org_id=o.org_id where req.created_emp<>1 and req.kind=" + RequestBean.REQUEST + " and req.which_use=" + RequestFormBean.WHICHUSE_PROJECT + " and req.org_id=" + proId;
    
    sql = sql + " and ( req.created_emp=" + getRequestEmp();
    sql = sql + " OR ( " + getRequestEmp() + " in ";
    
    sql = sql + "( select emp_id from employee where emp_id=req.assigned_emp";
    sql = sql + " union select emp_id from request_detail where req_id=req.req_id";
    if (!getInvolvedEmps().equals("")) {
      sql = sql + " union (select " + getInvolvedEmps() + ")";
    }
    sql = sql + ")))";
    sql = sql + " order by req_id desc";
    
    ArrayList requestList = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      RequestBean request = null;
      String approveSuggest = "";
      String[] arrString = null;
      int statusSuggest = 0;
      int whichUse = 0;
      while (rs.next())
      {
        request = new RequestBean();
        request.setReqId(rs.getInt("req_id"));
        request.setCreatedDate(DateUtil.formatDate(rs.getDate("created_date"), "dd/MM/yyyy"));
        request.setRequestNumber(rs.getString("request_number"));
        approveSuggest = rs.getString("approve_suggest");
        arrString = approveSuggest.split(",");
        approveSuggest = "";
        for (int i = 0; i < arrString.length; i++) {
          if (arrString[i].equals("1")) {
            approveSuggest = approveSuggest + ", " + MCUtil.getBundleString("message.request.approveSuggest1");
          } else if (arrString[i].equals("2")) {
            approveSuggest = approveSuggest + ", " + MCUtil.getBundleString("message.request.approveSuggest2");
          } else if (arrString[i].equals("4")) {
            approveSuggest = approveSuggest + ", " + MCUtil.getBundleString("message.request.approveSuggest3");
          } else if (arrString[i].equals("8")) {
            approveSuggest = approveSuggest + ", " + MCUtil.getBundleString("message.request.approveSuggest4");
          } else if (arrString[i].equals("16")) {
            approveSuggest = approveSuggest + ", " + MCUtil.getBundleString("message.request.approveSuggest5");
          }
        }
        if (!GenericValidator.isBlankOrNull(approveSuggest)) {
          request.setApproveSuggest(approveSuggest.substring(1));
        }
        statusSuggest = rs.getInt("status_suggest");
        if (statusSuggest == 1) {
          request.setStatusName(MCUtil.getBundleString("message.request.statusSuggest1"));
        } else if (statusSuggest == 2) {
          request.setStatusName(MCUtil.getBundleString("message.request.statusSuggest2"));
        } else if (statusSuggest == 3) {
          request.setStatusName(MCUtil.getBundleString("message.request.statusSuggest3"));
        }
        whichUse = rs.getInt("which_use");
        request.setWhichUseName(rs.getString("organizationName"));
        requestList.add(request);
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
    return requestList;
  }
  
  public RequestDetailBean getRequestDetail(int detId)
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "Select d.*, m.name_vn as MaterialName, m.code From request_detail as d left join material as m on d.mat_id=m.mat_id Where d.det_id=" + detId;
    try
    {
      rs = DBUtil.executeQuery(sql);
      RequestDetailBean request_detail = null;
      if (rs.next())
      {
        request_detail = new RequestDetailBean();
        request_detail.setDetId(rs.getInt("det_id"));
        request_detail.setReqId(rs.getInt("req_id"));
        request_detail.setMatId(rs.getInt("mat_id"));
        request_detail.setMatName(StringUtil.decodeString(rs.getString("MaterialName")));
        request_detail.setMatCode(StringUtil.decodeString(rs.getString("code")));
        request_detail.setConId(rs.getInt("con_id"));
        request_detail.setUnit(rs.getString("unit"));
        request_detail.setPresentQuantity(rs.getDouble("present_quantity"));
        request_detail.setAdditionalQuantity(rs.getDouble("additional_quantity"));
        request_detail.setRequestQuantity(rs.getDouble("request_quantity"));
        request_detail.setRemainQuantity(rs.getDouble("remain_quantity"));
        request_detail.setProvideDate(DateUtil.formatDate(rs.getDate("provide_date"), "dd/MM/yyyy"));
        
        request_detail.setIntermemoNote(rs.getString("intermemo_note"));
        request_detail.setStep(rs.getString("step"));
        request_detail.setStepId(rs.getInt("step_id"));
        request_detail.setMaterialKind(rs.getInt("material_kind"));
        
        return request_detail;
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
  
  public ArrayList getRequestDetailsByDetIds(String detIds)
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "Select d.*, m.name_vn as MaterialName, m.code, r.request_number, r.created_date as req_date, o.name as request_org, p.name as request_project From request_detail as d left join material as m on d.mat_id=m.mat_id left join request as r on r.req_id=d.req_id left join organization as o on o.org_id=r.created_org left join organization as p on p.org_id=r.org_id Where d.det_id in (" + detIds + ")";
    
    ArrayList request_detailList = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      RequestDetailFormBean request_detail = null;
      while (rs.next())
      {
        request_detail = new RequestDetailFormBean();
        request_detail.setDetId(rs.getInt("det_id"));
        request_detail.setReqId(rs.getInt("req_id"));
        request_detail.setMatId(rs.getInt("mat_id"));
        if (rs.getString("MaterialName") != null) {
          request_detail.setMatName(StringUtil.decodeString(rs.getString("MaterialName")));
        }
        request_detail.setMatCode(rs.getString("code"));
        request_detail.setConId(rs.getInt("con_id"));
        request_detail.setUnit(rs.getString("unit"));
        request_detail.setPresentQuantity(rs.getDouble("present_quantity"));
        request_detail.setAdditionalQuantity(rs.getDouble("additional_quantity"));
        request_detail.setRequestQuantity(rs.getDouble("request_quantity"));
        request_detail.setRemainQuantity(rs.getDouble("remain_quantity"));
        request_detail.setProvideDate(DateUtil.formatDate(rs.getDate("provide_date"), "dd/MM/yyyy"));
        
        request_detail.setIntermemoNote(rs.getString("intermemo_note"));
        request_detail.setStep(rs.getString("step"));
        request_detail.setStepId(rs.getInt("step_id"));
        request_detail.setMaterialKind(rs.getInt("material_kind"));
        request_detail.setReqNumber(rs.getString("request_number"));
        request_detail.setReqDate(DateUtil.formatDate(rs.getDate("req_date"), "dd/MM/yyyy"));
        request_detail.setReqOrg(rs.getString("request_org"));
        request_detail.setReqProject(rs.getString("request_project"));
        request_detailList.add(request_detail);
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
    return request_detailList;
  }
  
  public int getRequestAssignEmployee(String reqIds)
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "Select emp_id From request_detail Where req_id in (" + reqIds + ")";
    int result = 0;
    try
    {
      rs = DBUtil.executeQuery(sql);
      while (rs.next()) {
        result = rs.getInt("emp_id");
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
  
  public String getMaxRequestIdByNumber(String requestNumber)
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "Select max(request_number) as requestNumber From request Where request_number like '" + requestNumber + "%'";
    
    String result = "";
    try
    {
      rs = DBUtil.executeQuery(sql);
      while (rs.next())
      {
        String reqNum = rs.getString("requestNumber");
        if (!GenericValidator.isBlankOrNull(reqNum)) {
          result = requestNumber.substring(requestNumber.length());
        } else {
          result = "";
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
  
  public RequestDetailBean getRequestDetailByMaterial(int reqId, String matCode, double quantity, String mrirNumber)
    throws Exception
  {
    ResultSet rs = null;
    
    RequestDetailBean detBean = null;
    try
    {
      String sql = "Select reqdet.det_id From request_detail as reqdet  left join material as mat on reqdet.mat_id=mat.mat_id  left join mrir_detail as mrdet on mrdet.req_detail_id=reqdet.det_id left join mrir as mr on mrdet.mrir_id=mr.mrir_id Where reqdet.req_id=" + reqId + " and mat.code='" + matCode + "' and reqdet.additional_quantity=" + quantity + " and mr.mrir_number='" + mrirNumber + "'";
      
      rs = DBUtil.executeQuery(sql);
      while (rs.next()) {
        detBean = getRequestDetail(rs.getInt("det_id"));
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
    return detBean;
  }
  
  public int getRequestDetailByMaterial(int reqId, String matCode, int conId)
    throws Exception
  {
    ResultSet rs = null;
    
    int result = 0;
    try
    {
      String sql = "Select reqdet.det_id From request_detail as reqdet  left join contract_detail as cdet on cdet.reqDetail_id=reqdet.det_id left join material as mat on reqdet.mat_id=mat.mat_id  Where reqdet.req_id=" + reqId + " and mat.code='" + matCode + "' and cdet.con_id=" + conId;
      
      rs = DBUtil.executeQuery(sql);
      while (rs.next()) {
        result = rs.getInt("det_id");
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
  
  public RequestBean getRequestByContractAndMaterial(String contractNumber, int matId)
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "Select rdet.req_id From request_detail as rdet, contract_detail as cdet, contract as c Where c.contract_number='" + contractNumber + "' and c.con_id=cdet.con_id and cdet.reqDetail_id=rdet.det_id";
    try
    {
      rs = DBUtil.executeQuery(sql);
      if (rs.next())
      {
        RequestBean request = new RequestBean();
        request.setReqId(rs.getInt("req_id"));
        
        return request;
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
  
  public int getRequestDetailByMaterial(int reqId, int matId, double quantity)
    throws Exception
  {
    ResultSet rs = null;
    int result = 0;
    try
    {
      String sql = "Select reqdet.det_id From request_detail as reqdet  left join mrir_detail as mrdet on mrdet.req_detail_id=reqdet.det_id left join mrir as mr on mrdet.mrir_id=mr.mrir_id Where reqdet.req_id=" + reqId + " and reqdet.mat_id=" + matId + " and reqdet.additional_quantity=" + quantity;
      
      rs = DBUtil.executeQuery(sql);
      while (rs.next()) {
        result = rs.getInt("det_id");
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
  
  public boolean isAssignRequestPosition(int empId, int reqId)
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "select e.emp_id from request as req, employee as e where req.req_id=" + reqId + " and req.STATUS=" + RequestBean.STATUS_HANDLE + " and e.emp_id=" + empId + " and (" + " (e.emp_id = req.assigned_emp)";
    if (!getInvolvedEmps().equals("")) {
      sql = sql + " or " + empId + " in (" + getInvolvedEmps() + ")";
    }
    sql = sql + ")";
    boolean result = false;
    try
    {
      rs = DBUtil.executeQuery(sql);
      if (rs.next())
      {
        result = true;
        DBUtil.closeConnection(rs);
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
  
  public String getMaterialNotCode(int reqId)
    throws Exception
  {
    ResultSet rs = null;
    
    String st = "";
    String sql = "SELECT req_id FROM request_detail AS rd LEFT JOIN material AS m ON m.mat_id = rd.mat_id WHERE (LENGTH(m.CODE)<=0 or m.code is null) and rd.req_id=" + reqId;
    try
    {
      rs = DBUtil.executeQuery(sql);
      while (rs.next()) {
        st = st + ";" + rs.getString("req_id");
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
    return st;
  }
  
  public String getMaterialIdNotCode(int reqId)
    throws Exception
  {
    ResultSet rs = null;
    
    String st = "";
    String sql = "SELECT m.mat_id FROM request_detail AS rd LEFT JOIN material AS m ON m.mat_id = rd.mat_id WHERE (LENGTH(m.CODE)<=0 or m.code is null) and rd.req_id=" + reqId;
    try
    {
      rs = DBUtil.executeQuery(sql);
      while (rs.next()) {
        st = st + "," + rs.getString("mat_id");
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
    return st;
  }
  
  public String getMaterialNullCode(int reqId)
    throws Exception
  {
    ResultSet rs = null;
    
    String st = "";
    String sql = "SELECT req_id FROM request_detail AS rd, material AS m WHERE m.mat_id = rd.mat_id and m.code is null and rd.req_id=" + reqId;
    try
    {
      rs = DBUtil.executeQuery(sql);
      while (rs.next()) {
        st = st + ";" + rs.getString("req_id");
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
    return st;
  }
  
  public boolean isAssignRequestOfRequest(int empId, int reqId)
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "select emp_id from request_detail where req_id=" + reqId + " and emp_id=" + empId;
    boolean result = false;
    try
    {
      rs = DBUtil.executeQuery(sql);
      if (rs.next())
      {
        result = true;
        DBUtil.closeConnection(rs);
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
  
  public int getRequestDetail(int reqId, int matId)
    throws Exception
  {
    ResultSet rs = null;
    
    int result = 0;
    try
    {
      String sql = "Select reqdet.det_id From request_detail as reqdet Where reqdet.req_id=" + reqId + " and reqdet.original_mat_id='" + matId;
      
      rs = DBUtil.executeQuery(sql);
      while (rs.next()) {
        result = rs.getInt("det_id");
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
  
  public void cancalRequestRemainQuantity(int reqDetId)
    throws Exception
  {
    try
    {
      String sql = "Update request_detail Set remain_quantity=0, is_cancel=1 Where det_id=" + reqDetId;
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
  
  public String getRequestOfContract(int conId)
    throws Exception
  {
    ResultSet rs = null;
    String st = "";
    String sql = "SELECT group_concat(DISTINCT r.request_number) as request_number from contract as c left join contract_detail as cdet on c.con_id=cdet.con_id left join request_detail as rdet on cdet.reqDetail_id=rdet.det_id left join request as r on rdet.req_id=r.req_id where c.con_id=" + conId + " order by r.req_id";
    try
    {
      rs = DBUtil.executeQuery(sql);
      if (rs.next())
      {
        st = rs.getString("request_number");
        DBUtil.closeConnection(rs);
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
    return st;
  }
  
  public void rollbackRequestStatus(String tenDetIds)
    throws Exception
  {
    try
    {
      String sql = "Update request_detail Set step='" + MCUtil.getBundleString("message.request") + "'" + ", step_id=" + RequestBean.STEP_REQ + ", remain_quantity=remain_quantity + (SELECT quantity FROM tender_plan_detail WHERE det_id IN (" + tenDetIds + "))" + ", ten_id=null, con_id=null, ord_id=null, dr_id=null, dn_id=null, mrir_id=null, msv_id=null, rfm_id=null, miv_id=null, mrv_id=null" + " Where det_id in (select reqDetail_id from tender_plan_detail where det_id in (" + tenDetIds + "))";
      
      DBUtil.executeUpdate(sql);
      
      sql = "Update tender_plan_detail Set is_roll_back=1 where det_id in (" + tenDetIds + ")";
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
  
  public void updateRequestDetailForChangeMaterial(RequestDetailBean bean)
    throws Exception
  {
    if (bean == null) {
      return;
    }
    try
    {
      String sql = "Update request_detail Set  mat_id=" + bean.getMatId() + ", original_mat_id=" + bean.getMatId() + ", unit='" + bean.getUnit() + "'";
      
      sql = sql + " Where det_id=" + bean.getDetId();
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
  
  public String getRequestDetailIdsRelationMaterial(int matId)
    throws Exception
  {
    ResultSet rs = null;
    
    String st = "0";
    String sql = "SELECT det_id FROM request_detail AS rd WHERE rd.mat_id=" + matId;
    try
    {
      rs = DBUtil.executeQuery(sql);
      while (rs.next()) {
        st = st + "," + rs.getString("det_id");
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
    return st;
  }
  
  public ArrayList getRequestDetails(String matIds, String reqIds)
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "SELECT det.*, m.name_vn as MaterialName, m.code, e.fullname FROM request_detail AS det  left join material as m on det.original_mat_id=m.mat_id left join employee as e on e.emp_id=det.emp_id WHERE det.mat_id in (" + matIds + ") AND det.req_id in (" + reqIds + ")" + " order by det.req_id desc, det.det_id desc";
    
    ArrayList request_detailList = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      RequestDetailFormBean request_detail = null;
      int no = 1;
      while (rs.next())
      {
        request_detail = new RequestDetailFormBean();
        request_detail.setNo(no++);
        request_detail.setDetId(rs.getInt("det_id"));
        request_detail.setReqId(rs.getInt("req_id"));
        request_detail.setMatId(rs.getInt("mat_id"));
        if (rs.getString("MaterialName") != null) {
          request_detail.setMatName(StringUtil.decodeString(rs.getString("MaterialName")));
        }
        request_detail.setMatCode(rs.getString("code"));
        request_detail.setConId(rs.getInt("con_id"));
        request_detail.setUnit(rs.getString("unit"));
        request_detail.setPresentQuantity(rs.getDouble("present_quantity"));
        request_detail.setAdditionalQuantity(rs.getDouble("additional_quantity"));
        request_detail.setRequestQuantity(rs.getDouble("request_quantity"));
        request_detail.setRemainQuantity(rs.getDouble("remain_quantity"));
        request_detail.setProvideDate(DateUtil.formatDate(rs.getDate("provide_date"), "dd/MM/yyyy"));
        
        request_detail.setIntermemoNote(rs.getString("intermemo_note"));
        request_detail.setStep(rs.getString("step"));
        request_detail.setStepId(rs.getInt("step_id"));
        request_detail.setMaterialKind(rs.getInt("material_kind"));
        request_detail.setEmpId(rs.getInt("emp_id"));
        request_detail.setIsCancel(rs.getInt("is_cancel"));
        request_detailList.add(request_detail);
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
    return request_detailList;
  }
  
  public ArrayList getRequestOrgHandle(int reqId)
    throws Exception
  {
    ResultSet rs = null;
    String sql = "Select * from request_handled where req_id=" + reqId;
    ArrayList requestList = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      RequestHandledBean request = null;
      while (rs.next())
      {
        request = new RequestHandledBean();
        request.setId(rs.getInt("id"));
        request.setReqId(rs.getInt("req_id"));
        request.setOrgId(rs.getInt("org_id"));
        request.setAssignedEmp(rs.getInt("assigned_emp"));
        requestList.add(request);
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
    return requestList;
  }
  
  public int insertRequestHandle(RequestHandledBean bean)
    throws Exception
  {
    if (bean == null) {
      return 0;
    }
    int result = 0;
    try
    {
      String sql = "";
      sql = "Insert Into request_handled(req_id, org_id, assigned_emp) Values (" + bean.getReqId() + "," + bean.getOrgId() + "," + bean.getAssignedEmp() + ")";
      
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
  
  public int deleteRequestHandled(String ids)
    throws Exception
  {
    int result = 0;
    try
    {
      String sql = "Delete From request_handled Where id in (" + ids + ")";
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
  
  public int deleteRequestHandled(int reqId, String ids)
    throws Exception
  {
    int result = 0;
    try
    {
      String sql = "update request_detail set emp_id=null, org_id=null where req_id=" + reqId + " and org_id in ( SELECT org_id FROM request_handled WHERE id in (" + ids + "))";
      
      result = DBUtil.executeUpdate(sql);
      sql = "Delete From request_handled Where id in (" + ids + ")";
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
  
  public void updateRequestAssignedEmp(int reqId, String orgId, int empId)
    throws Exception
  {
    try
    {
      String sql = "Update request_handled Set assigned_emp=" + empId + " Where req_id=" + reqId + " and org_id in (" + orgId + ")";
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
  
  public int getRequestAssignedEmp(int reqId, String orgIds)
    throws Exception
  {
    ResultSet rs = null;
    int result = 0;
    try
    {
      String sql = "Select assigned_emp From request_handled Where req_id=" + reqId + " and org_id in (" + orgIds + ")";
      rs = DBUtil.executeQuery(sql);
      while (rs.next()) {
        result = rs.getInt("assigned_emp");
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
  
  public ArrayList getRequestDetailsForAssign(int reqId)
    throws Exception
  {
    ResultSet rs = null;
    String sql = "Select d.*, m.name_vn as MaterialName, m.code, e.fullname From request_detail as d left join material as m on d.original_mat_id=m.mat_id left join employee as e on e.emp_id=d.emp_id Where req_id=" + reqId + " order by d.det_id asc";
    
    ArrayList request_detailList = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      RequestDetailFormBean request_detail = null;
      int no = 1;
      while (rs.next())
      {
        request_detail = new RequestDetailFormBean();
        request_detail.setNo(no++);
        request_detail.setDetId(rs.getInt("det_id"));
        request_detail.setReqId(rs.getInt("req_id"));
        request_detail.setMatId(rs.getInt("mat_id"));
        if (rs.getString("MaterialName") != null) {
          request_detail.setMatName(StringUtil.decodeString(rs.getString("MaterialName")));
        }
        request_detail.setMatCode(rs.getString("code"));
        request_detail.setConId(rs.getInt("con_id"));
        request_detail.setUnit(rs.getString("unit"));
        request_detail.setPresentQuantity(rs.getDouble("present_quantity"));
        request_detail.setAdditionalQuantity(rs.getDouble("additional_quantity"));
        request_detail.setRequestQuantity(rs.getDouble("request_quantity"));
        request_detail.setRemainQuantity(rs.getDouble("remain_quantity"));
        request_detail.setProvideDate(DateUtil.formatDate(rs.getDate("provide_date"), "dd/MM/yyyy"));
        request_detail.setIntermemoNote(rs.getString("intermemo_note"));
        request_detail.setStep(rs.getString("step"));
        request_detail.setStepId(rs.getInt("step_id"));
        request_detail.setMaterialKind(rs.getInt("material_kind"));
        request_detail.setEmpId(rs.getInt("emp_id"));
        request_detail.setIsCancel(rs.getInt("is_cancel"));
        request_detail.setEmployee(rs.getString("fullname"));
        request_detailList.add(request_detail);
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
    return request_detailList;
  }
  
  public ArrayList getRequestDetailsForAssign(String detIds)
    throws Exception
  {
    ResultSet rs = null;
    String sql = "Select d.*, m.name_vn as MaterialName, m.code, e.fullname From request_detail as d left join material as m on d.original_mat_id=m.mat_id left join employee as e on e.emp_id=d.emp_id Where det_id in (" + detIds + ")" + " order by d.det_id asc";
    
    ArrayList request_detailList = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      RequestDetailFormBean request_detail = null;
      int no = 1;
      while (rs.next())
      {
        request_detail = new RequestDetailFormBean();
        request_detail.setNo(no++);
        request_detail.setDetId(rs.getInt("det_id"));
        request_detail.setReqId(rs.getInt("req_id"));
        request_detail.setMatId(rs.getInt("mat_id"));
        if (rs.getString("MaterialName") != null) {
          request_detail.setMatName(StringUtil.decodeString(rs.getString("MaterialName")));
        }
        request_detail.setMatCode(rs.getString("code"));
        request_detail.setConId(rs.getInt("con_id"));
        request_detail.setUnit(rs.getString("unit"));
        request_detail.setPresentQuantity(rs.getDouble("present_quantity"));
        request_detail.setAdditionalQuantity(rs.getDouble("additional_quantity"));
        request_detail.setRequestQuantity(rs.getDouble("request_quantity"));
        request_detail.setRemainQuantity(rs.getDouble("remain_quantity"));
        request_detail.setProvideDate(DateUtil.formatDate(rs.getDate("provide_date"), "dd/MM/yyyy"));
        request_detail.setIntermemoNote(rs.getString("intermemo_note"));
        request_detail.setStep(rs.getString("step"));
        request_detail.setStepId(rs.getInt("step_id"));
        request_detail.setMaterialKind(rs.getInt("material_kind"));
        request_detail.setEmpId(rs.getInt("emp_id"));
        request_detail.setIsCancel(rs.getInt("is_cancel"));
        request_detail.setEmployee(rs.getString("fullname"));
        request_detailList.add(request_detail);
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
    return request_detailList;
  }
  
  public int copyRequest(int reqId, String requestNumber, String detIds)
    throws Exception
  {
    int id = 0;
    try
    {
      String sql = "";
      sql = "INSERT INTO request (org_id, created_emp, kind, request_number, created_date, approve_suggest, status_suggest,  certificate_require, which_use, description, check_comment, store_comment, bom_comment, plandep_comment,  org_handle, org_refer, org_paid, intermemo_subject, check_approve, store_approve, bom_approve, plandep_approve, created_org, assigned_emp, approve_emp, bom_agree_date, STATUS, store_org) SELECT org_id, created_emp, kind, '" + requestNumber + "', SYSDATE(), approve_suggest, status_suggest" + " , certificate_require, which_use, description, check_comment, store_comment, bom_comment, plandep_comment" + " , org_handle, org_refer, org_paid, intermemo_subject, check_approve, store_approve, bom_approve, plandep_approve" + " , created_org, assigned_emp, approve_emp, bom_agree_date, STATUS, store_org" + " FROM request WHERE req_id=" + reqId;
      
      id = DBUtil.executeInsert(sql);
      if (id > 0)
      {
        sql = "INSERT INTO request_handled(req_id, org_id, assigned_emp) SELECT " + id + ", org_id, assigned_emp FROM request_handled WHERE req_id=" + reqId;
        
        DBUtil.executeInsert(sql);
        
        sql = "INSERT INTO request_detail(req_id, mat_id, unit, present_quantity, additional_quantity, request_quantity, provide_date, intermemo_note, step, step_id, material_kind, remain_quantity, original_mat_id, emp_id, is_cancel, org_id) SELECT " + id + ", mat_id, unit, present_quantity, additional_quantity, request_quantity, provide_date, intermemo_note" + ", step, step_id, material_kind, remain_quantity, original_mat_id, emp_id, is_cancel, org_id" + " FROM request_detail WHERE det_id IN (" + detIds + ")";
        
        DBUtil.executeInsert(sql);
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
    return id;
  }
  
  public int copyRequest(int reqId, String appSuggest, RequestFormBean formBean)
    throws Exception
  {
    int id = 0;
    try
    {
      String sql = "";
      String orgId = "";
      if (formBean.getProId() != 0) {
        orgId = formBean.getProId() + "";
      }
      if (formBean.getOrgId() != 0) {
        orgId = formBean.getOrgId() + "";
      }
      sql = "INSERT INTO request (org_id, created_emp, kind, request_number, created_date, approve_suggest, status_suggest,  certificate_require, which_use, description, check_comment, store_comment, bom_comment, plandep_comment,  org_handle, org_refer, org_paid, intermemo_subject, check_approve, store_approve, bom_approve, plandep_approve, created_org, assigned_emp, approve_emp, bom_agree_date, STATUS, store_org) SELECT " + orgId + ", created_emp, kind, '" + formBean.getRequestNumber() + "', SYSDATE(), '" + appSuggest + "', " + formBean.getStatusSuggest() + " , '" + formBean.getCertificateRequire() + "', " + formBean.getWhichUse() + ", '" + formBean.getDescription() + "', check_comment, store_comment, bom_comment, plandep_comment" + " , org_handle, org_refer, org_paid, intermemo_subject, check_approve, store_approve, bom_approve, plandep_approve" + " , created_org, assigned_emp, approve_emp, bom_agree_date, STATUS, store_org" + " FROM request WHERE req_id=" + reqId;
      
      id = DBUtil.executeInsert(sql);
      if (id > 0)
      {
        sql = "INSERT INTO request_handled(req_id, org_id, assigned_emp) SELECT " + id + ", org_id, assigned_emp FROM request_handled WHERE req_id=" + reqId + " and assigned_emp=" + formBean.getAssignedEmp();
        
        DBUtil.executeInsert(sql);
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
    return id;
  }
  
  public String getNextRequestNumber(String prefix, int length)
    throws Exception
  {
    String result = "";
    ResultSet rs = null;
    try
    {
      String t = "";
      String y = "-";
      for (int i = 0; i < length; i++) {
        t = t + "_";
      }
      for (int i = 1; i < length; i++) {
        y = y + "_";
      }
      String sql = "select max(request_number) as number from request where request_number like '" + prefix + t + "'" + " and request_number not like '" + prefix + y + "'";
      
      rs = DBUtil.executeQuery(sql);
      if (rs.next()) {
        result = rs.getString("number");
      }
      if ((result == null) || (result.equals("")))
      {
        result = "";
        for (int i = 0; i < length - 1; i++) {
          result = result + "0";
        }
        result = result + "1";
      }
      else
      {
        int num = Integer.parseInt(result.substring(prefix.length())) + 1;
        result = num + "";
        while (result.length() < length) {
          result = "0" + result;
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
}
