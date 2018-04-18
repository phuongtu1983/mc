package com.venus.mc.request;

import com.venus.core.auth.OnlineUser;
import com.venus.core.util.DateUtil;
import com.venus.core.util.GenericValidator;
import com.venus.core.util.LogUtil;
import com.venus.core.util.NumberUtil;
import com.venus.core.util.StringUtil;
import com.venus.mc.bean.RequestBean;
import com.venus.mc.bean.RequestDetailBean;
import com.venus.mc.bean.RequestHandledBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.OrganizationDAO;
import com.venus.mc.dao.RequestDAO;
import com.venus.mc.util.MCUtil;
import com.venus.mc.util.PermissionUtil;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

public class AddRequestAction
  extends SpineAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    HttpSession session = request.getSession();
    RequestFormBean formBean = (RequestFormBean)form;
    RequestDAO requestDAO = new RequestDAO();
    RequestBean bean = null;
    boolean bNew = false;
    boolean isExist = false;
    try
    {
      bean = requestDAO.getRequestByNumber(formBean.getRequestNumber());
    }
    catch (Exception localException1) {}
    int reqId = formBean.getReqId();
    if (reqId == 0)
    {
      bNew = true;
      if (bean != null) {
        isExist = true;
      }
    }
    else
    {
      bNew = false;
      if ((bean != null) && (bean.getReqId() != reqId)) {
        isExist = true;
      }
    }
    if (isExist)
    {
      ActionMessages errors = new ActionMessages();
      errors.add("requestExisted", new ActionMessage("errors.request.existed"));
      saveErrors(request, errors);
      return false;
    }
    try
    {
      bean = requestDAO.getRequest(reqId);
    }
    catch (Exception localException2) {}
    if (bean == null)
    {
      bean = new RequestBean();
      bean.setReqId(reqId);
    }
    if (((PermissionUtil.getEmployeesHasPermission(request, PermissionUtil.PER_PURCHASING_REQUEST_VIEW)) && ((bean.getStatus() == RequestBean.STATUS_MANAGER) || (bean.getStatus() == RequestBean.STATUS_HANDLE))) || ((formBean.getIsShortCut() == 1) && (formBean.getReqId() == 0)))
    {
      bean.setBomApprove(formBean.getBomApprove());
      bean.setBomComment(formBean.getBomComment());
      if (formBean.getOrgHandle() != null)
      {
        int length = formBean.getOrgHandle().length;
        String orgHandle = formBean.getOrgHandle()[0];
        for (int i = 1; i < length; i++) {
          orgHandle = orgHandle + "," + formBean.getOrgHandle()[i];
        }
        bean.setOrgHandle(orgHandle);
        if (bean.getReqId() > 0) {
          updateRequestHandledOrg(bean.getReqId(), orgHandle);
        }
      }
      if (formBean.getOrgPaid() != null)
      {
        int length = formBean.getOrgPaid().length;
        String orgPaid = formBean.getOrgPaid()[0];
        for (int i = 1; i < length; i++) {
          orgPaid = orgPaid + "," + formBean.getOrgPaid()[i];
        }
        bean.setOrgPaid(orgPaid);
      }
      if (formBean.getOrgRefer() != null)
      {
        int length = formBean.getOrgRefer().length;
        String orgRefer = formBean.getOrgRefer()[0];
        for (int i = 1; i < length; i++) {
          orgRefer = orgRefer + "," + formBean.getOrgRefer()[i];
        }
        bean.setOrgRefer(orgRefer);
      }
      if ((bean.getBomApprove() == RequestFormBean.APPROVE) || (formBean.getIsShortCut() == 1)) {
        bean.setBomAgreeDate(DateUtil.today("dd/MM/yyyy"));
      }
    }
    if ((bNew) || (bean.getCreatedEmp() == MCUtil.getMemberID(request.getSession()))) {
      setInfo(bean, formBean);
    }
    if ((PermissionUtil.hasPermission(request, PermissionUtil.FUNC_LIST, PermissionUtil.PER_PURCHASING_REQUEST_STORE)) && ((bean.getStatus() == RequestBean.STATUS_STORE) || (bean.getStatus() == RequestBean.STATUS_STORE_ACCEPT)))
    {
      bean.setStoreApprove(formBean.getStoreApprove());
      bean.setStoreComment(formBean.getStoreComment());
    }
    if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_LIST, PermissionUtil.PER_PURCHASING_REQUEST_CHECK))
    {
      bean.setCheckApprove(formBean.getCheckApprove());
      bean.setCheckComment(formBean.getCheckComment());
    }
    if ((PermissionUtil.hasPermission(request, PermissionUtil.FUNC_LIST, PermissionUtil.PER_PURCHASING_REQUEST_PLANDEP)) && 
      (bean.getStatus() == RequestBean.STATUS_MANAGER))
    {
      bean.setPlandepApprove(formBean.getPlandepApprove());
      bean.setPlandepComment(formBean.getPlandepComment());
    }
    if (formBean.getAssignedEmp() != 0) {
      try
      {
        String orgs = "";
        try
        {
          OrganizationDAO orgDAO = new OrganizationDAO();
          int orgId = MCUtil.getOrganizationID(request.getSession());
          orgs = orgDAO.getnestedParentOfOrg(orgId + "");
          if (orgs.equals("0")) {
            orgs = orgId + "";
          }
        }
        catch (Exception localException3) {}
        requestDAO.updateRequestAssignedEmp(reqId, orgs, formBean.getAssignedEmp());
      }
      catch (Exception localException4) {}
    }
    try
    {
      if (bNew)
      {
        bean.setCreatedEmp(MCUtil.getMemberID(request.getSession()));
        bean.setStatus(RequestBean.STATUS_CREATE);
        if (formBean.getIsShortCut() == 1)
        {
          int orgId = MCUtil.getOrganizationID(request.getSession());
          
          bean.setOrgHandle(orgId + "");
          if (StringUtil.isBlankOrNull(bean.getOrgRefer())) {
            bean.setOrgRefer(orgId + "");
          }
          if (StringUtil.isBlankOrNull(bean.getOrgPaid())) {
            bean.setOrgPaid(orgId + "");
          }
          bean.setCheckApprove(1);
          bean.setStoreApprove(1);
          bean.setBomApprove(1);
          bean.setPlandepApprove(1);
          
          bean.setApproveEmp(0);
          bean.setStatus(RequestBean.STATUS_HANDLE);
        }
        else
        {
          bean.setStoreComment("");
          bean.setCheckComment("");
          bean.setPlandepComment("");
          bean.setBomComment("");
        }
        reqId = requestDAO.insertRequest(bean);
        if (formBean.getIsShortCut() == 1) {
          updateRequestHandledOrg(reqId, bean.getOrgHandle(), MCUtil.getMemberID(request.getSession()) + "");
        }
      }
      else
      {
        OnlineUser user = MCUtil.getOnlineUser(session);
        LogUtil.info("Username : " + user.getName() + " - Form : " + mapping.getPath() + " - EDIT");
        RequestBean oldBean = requestDAO.getRequest(reqId);
        if ((bean.getBomApprove() == RequestFormBean.APPROVE) && (oldBean.getBomApprove() != RequestFormBean.APPROVE)) {
          bean.setBomAgreeDate(DateUtil.today("dd/MM/yyyy"));
        }
        if ((bean.getStoreApprove() == RequestFormBean.NOTAPPROVE) && (oldBean.getStoreApprove() != RequestFormBean.NOTAPPROVE)) {
          bean.setStatus(RequestBean.STATUS_CREATE);
        }
        if ((bean.getCheckApprove() == RequestFormBean.NOTAPPROVE) && (oldBean.getCheckApprove() != RequestFormBean.NOTAPPROVE))
        {
          bean.setStoreApprove(RequestFormBean.NOTAPPROVE);
          bean.setStatus(RequestBean.STATUS_CREATE);
        }
        if ((bean.getPlandepApprove() == RequestFormBean.NOTAPPROVE) && (oldBean.getPlandepApprove() != RequestFormBean.NOTAPPROVE))
        {
          bean.setCheckApprove(RequestFormBean.NOTAPPROVE);
          bean.setStoreApprove(RequestFormBean.NOTAPPROVE);
          bean.setStatus(RequestBean.STATUS_CREATE);
        }
        if ((bean.getBomApprove() == RequestFormBean.NOTAPPROVE) && (oldBean.getBomApprove() != RequestFormBean.NOTAPPROVE))
        {
          bean.setPlandepApprove(RequestFormBean.NOTAPPROVE);
          bean.setStoreApprove(RequestFormBean.NOTAPPROVE);
          bean.setCheckApprove(RequestFormBean.NOTAPPROVE);
          bean.setStatus(RequestBean.STATUS_CREATE);
        }
        requestDAO.updateRequest(bean);
      }
      session.setAttribute("id", Integer.valueOf(reqId));
      if ((!PermissionUtil.hasOneOfPermission(request, PermissionUtil.FUNC_LIST, PermissionUtil.PER_PURCHASING_REQUEST_CHECK + "," + PermissionUtil.PER_PURCHASING_REQUEST_PLANDEP)) && (!PermissionUtil.getEmployeesHasPermission(request, PermissionUtil.PER_PURCHASING_REQUEST_VIEW))) {
        if (reqId != 0)
        {
          ArrayList arrDet = null;
          try
          {
            arrDet = requestDAO.getRequestDetails(reqId);
          }
          catch (Exception ex)
          {
            arrDet = new ArrayList();
          }
          if (formBean.getReqDetId() != null)
          {
            String[] detIds = formBean.getReqDetId();
            String[] matIds = formBean.getMatId();
            RequestDetailBean detBean = null;
            double additionQuantity = 0.0D;
            double requestQuantity = 0.0D;
            double presentQuantity = 0.0D;
            boolean canUpdate = false;
            
            double resize = 0.0D;
            for (int i = 0; i < detIds.length; i++)
            {
              canUpdate = false;
              if (!detIds[i].equals("0"))
              {
                try
                {
                  detBean = detExisted(arrDet, Integer.parseInt(detIds[i]));
                  if (detBean != null)
                  {
                    requestQuantity = Double.parseDouble(formBean.getRequestQuantity()[i]);
                    resize = detBean.getRequestQuantity() - requestQuantity;
                    if (resize != 0.0D)
                    {
                      detBean.setRequestQuantity(requestQuantity);
                      detBean.setRemainQuantity(detBean.getRemainQuantity() - resize);
                      canUpdate = true;
                    }
                    presentQuantity = Double.parseDouble(formBean.getPresentQuantity()[i]);
                    resize = detBean.getPresentQuantity() - presentQuantity;
                    if (resize != 0.0D)
                    {
                      detBean.setPresentQuantity(presentQuantity);
                      canUpdate = true;
                    }
                    if (!detBean.getProvideDate().equals(formBean.getDeliverDate()[i]))
                    {
                      detBean.setProvideDate(formBean.getDeliverDate()[i]);
                      canUpdate = true;
                    }
                    if (!detBean.getUnit().equals(formBean.getUnit()[i]))
                    {
                      detBean.setUnit(formBean.getUnit()[i]);
                      canUpdate = true;
                    }
                    if ((formBean.getEmpId() != null) && 
                      (detBean.getEmpId() != NumberUtil.parseInt(formBean.getEmpId()[i], 0)))
                    {
                      detBean.setEmpId(Integer.parseInt(formBean.getEmpId()[i]));
                      if (detBean.getEmpId() == 0) {
                        detBean.setEmpId(-1);
                      }
                      detBean.setOrgId(MCUtil.getOrganizationID(request.getSession()));
                      canUpdate = true;
                    }
                    if (canUpdate) {
                      requestDAO.updateRequestDetail(detBean);
                    }
                  }
                }
                catch (Exception localException5) {}
              }
              else
              {
                int checkAppro = 0;
                try
                {
                  if (getCheckAppro(formBean.getCheckAppro(), "0_" + matIds[i])) {
                    checkAppro = 1;
                  } else {
                    checkAppro = 0;
                  }
                  if (GenericValidator.isBlankOrNull(formBean.getAdditionalQuantity()[i])) {
                    additionQuantity = 0.0D;
                  } else {
                    additionQuantity = Double.parseDouble(formBean.getAdditionalQuantity()[i]);
                  }
                  if (GenericValidator.isBlankOrNull(formBean.getRequestQuantity()[i])) {
                    requestQuantity = 0.0D;
                  } else {
                    requestQuantity = Double.parseDouble(formBean.getRequestQuantity()[i]);
                  }
                  detBean = new RequestDetailBean(0, formBean.getUnit()[i], Double.parseDouble(formBean.getPresentQuantity()[i]), additionQuantity, requestQuantity, formBean.getDeliverDate()[i], checkAppro, 0, 0, 0, "");
                  
                  detBean.setReqId(reqId);
                  
                  detBean.setMaterialKind(RequestFormBean.KIND_SECONDARY);
                  detBean.setMatId(Integer.parseInt(matIds[i]));
                  detBean.setStepId(RequestBean.STEP_REQ);
                  detBean.setStep(MCUtil.getBundleString("message.request"));
                  if (formBean.getIsShortCut() == 1)
                  {
                    detBean.setOrgId(MCUtil.getOrganizationID(request.getSession()));
                    detBean.setEmpId(MCUtil.getMemberID(request.getSession()));
                  }
                  requestDAO.insertRequestDetail(detBean);
                }
                catch (Exception localException6) {}
              }
            }
          }
        }
      }
    }
    catch (Exception ex)
    {
      LogUtil.error("FAILED:Request:add-" + ex.getMessage());
      ex.printStackTrace();
    }
    return true;
  }
  
  private RequestDetailBean detExisted(ArrayList arrDet, int detId)
  {
    RequestDetailFormBean formBean = null;
    for (int i = 0; i < arrDet.size(); i++)
    {
      formBean = (RequestDetailFormBean)arrDet.get(i);
      if (formBean.getDetId() == detId)
      {
        arrDet.remove(formBean);
        RequestDetailBean bean = new RequestDetailBean(detId);
        bean.setAdditionalQuantity(formBean.getAdditionalQuantity());
        bean.setRequestQuantity(formBean.getRequestQuantity());
        bean.setRemainQuantity(formBean.getRemainQuantity());
        bean.setPresentQuantity(formBean.getPresentQuantity());
        bean.setProvideDate(formBean.getProvideDate());
        bean.setCheckAppro(formBean.getCheckAppro());
        bean.setStockAppro(formBean.getStockAppro());
        bean.setPlandepAppro(formBean.getPlandepAppro());
        bean.setBomAppro(formBean.getBomAppro());
        bean.setMaterialKind(formBean.getMaterialKind());
        bean.setUnit(formBean.getUnit());
        bean.setEmpId(formBean.getEmpId());
        return bean;
      }
    }
    return null;
  }
  
  private boolean getCheckAppro(String[] checkAppros, String checkAppro)
  {
    if (checkAppros == null) {
      return false;
    }
    for (int i = 0; i < checkAppros.length; i++) {
      if (checkAppros[i].equals(checkAppro)) {
        return true;
      }
    }
    return false;
  }
  
  private void setInfo(RequestBean bean, RequestFormBean formBean)
  {
    bean.setRequestNumber(formBean.getRequestNumber());
    bean.setStatusSuggest(formBean.getStatusSuggest());
    bean.setCertificateRequire(formBean.getCertificateRequire());
    bean.setDescription(formBean.getDescription());
    bean.setWhichUse(formBean.getWhichUse());
    bean.setCreatedOrg(formBean.getCreatedOrg());
    bean.setStoreOrg(formBean.getStoreOrg());
    if (formBean.getWhichUse() == RequestFormBean.WHICHUSE_PROJECT) {
      bean.setProId(formBean.getProId());
    } else {
      bean.setOrgId(formBean.getOrgId());
    }
    bean.setIntermemoSubject("");
    bean.setKind(RequestBean.REQUEST);
    if (formBean.getApproveSuggest() != null)
    {
      int length = formBean.getApproveSuggest().length;
      String appSuggest = formBean.getApproveSuggest()[0];
      for (int i = 1; i < length; i++) {
        appSuggest = appSuggest + "," + formBean.getApproveSuggest()[i];
      }
      bean.setApproveSuggest(appSuggest);
    }
  }
  
  private void updateRequestHandledOrg(int reqId, String orgHandle)
  {
    try
    {
      RequestDAO requestDAO = new RequestDAO();
      ArrayList arrDetail = requestDAO.getRequestOrgHandle(reqId);
      String[] orgs = orgHandle.split(",");
      int length = orgs.length;
      int id = 0;
      for (int i = 0; i < length; i++)
      {
        id = NumberUtil.parseInt(orgs[i], 0);
        boolean isNew = true;
        for (int j = 0; j < arrDetail.size(); j++)
        {
          RequestHandledBean bean = (RequestHandledBean)arrDetail.get(j);
          if (bean.getOrgId() == id)
          {
            isNew = false;
            arrDetail.remove(j);
            break;
          }
        }
        if (isNew)
        {
          RequestHandledBean detBean = new RequestHandledBean();
          detBean.setReqId(reqId);
          detBean.setOrgId(NumberUtil.parseInt(orgs[i], 0));
          requestDAO.insertRequestHandle(detBean);
        }
      }
      String ids = "0";
      RequestHandledBean oldBean = null;
      for (int i = 0; i < arrDetail.size(); i++)
      {
        oldBean = (RequestHandledBean)arrDetail.get(i);
        ids = ids + "," + oldBean.getId();
      }
      if (!ids.equals("0")) {
        requestDAO.deleteRequestHandled(reqId, ids.substring(2));
      }
    }
    catch (Exception localException) {}
  }
  
  private void updateRequestHandledOrg(int reqId, String orgHandle, String empHandle)
  {
    try
    {
      RequestDAO requestDAO = new RequestDAO();
      ArrayList arrDetail = requestDAO.getRequestOrgHandle(reqId);
      String[] orgs = orgHandle.split(",");
      String[] emps = empHandle.split(",");
      int length = orgs.length;
      int id = 0;
      for (int i = 0; i < length; i++)
      {
        id = NumberUtil.parseInt(orgs[i], 0);
        boolean isNew = true;
        for (int j = 0; j < arrDetail.size(); j++)
        {
          RequestHandledBean bean = (RequestHandledBean)arrDetail.get(j);
          if (bean.getOrgId() == id)
          {
            isNew = false;
            arrDetail.remove(j);
            break;
          }
        }
        if (isNew)
        {
          RequestHandledBean detBean = new RequestHandledBean();
          detBean.setReqId(reqId);
          detBean.setOrgId(NumberUtil.parseInt(orgs[i], 0));
          detBean.setAssignedEmp(NumberUtil.parseInt(emps[i], 0));
          requestDAO.insertRequestHandle(detBean);
        }
      }
      String ids = "0";
      RequestHandledBean oldBean = null;
      for (int i = 0; i < arrDetail.size(); i++)
      {
        oldBean = (RequestHandledBean)arrDetail.get(i);
        ids = ids + "," + oldBean.getId();
      }
      if (!ids.equals("0")) {
        requestDAO.deleteRequestHandled(reqId, ids.substring(2));
      }
    }
    catch (Exception localException) {}
  }
  
  protected String getFunction()
  {
    return PermissionUtil.FUNC_EDIT + "";
  }
  
  protected int getPermit()
  {
    return PermissionUtil.PER_PURCHASING_REQUEST;
  }
}
