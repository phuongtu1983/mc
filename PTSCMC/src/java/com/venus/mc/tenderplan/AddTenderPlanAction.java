package com.venus.mc.tenderplan;

import com.venus.core.auth.OnlineUser;
import com.venus.core.util.GenericValidator;
import com.venus.core.util.LogUtil;
import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.BidEvalSumBean;
import com.venus.mc.bean.RequestBean;
import com.venus.mc.bean.RequestDetailBean;
import com.venus.mc.bean.TenderEvaluateGroupBean;
import com.venus.mc.bean.TenderEvaluateVendorBean;
import com.venus.mc.bean.TenderPlanBean;
import com.venus.mc.bean.TenderPlanCertificateBean;
import com.venus.mc.bean.TenderPlanDetailBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.BidEvalSumDAO;
import com.venus.mc.dao.BidEvalSumVendorDAO;
import com.venus.mc.dao.RequestDAO;
import com.venus.mc.dao.TenderPlanDAO;
import com.venus.mc.request.RequestDetailFormBean;
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

public class AddTenderPlanAction
  extends SpineAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    HttpSession session = request.getSession();
    TenderPlanFormBean formBean = (TenderPlanFormBean)form;
    
    TenderPlanBean bean = null;
    boolean bNew = false;
    boolean isExist = false;
    try
    {
      TenderPlanDAO tenderDAO = new TenderPlanDAO();
      bean = tenderDAO.getTenderPlanByNumber(formBean.getTenderNumber());
    }
    catch (Exception localException1) {}
    int tenId = formBean.getTenId();
    if (tenId == 0)
    {
      bNew = true;
      if (bean != null) {
        isExist = true;
      }
    }
    else
    {
      bNew = false;
      if ((bean != null) && (bean.getTenId() != tenId)) {
        isExist = true;
      }
    }
    if (isExist)
    {
      ActionMessages errors = new ActionMessages();
      errors.add("tenderExisted", new ActionMessage("errors.tenderplan.existed"));
      saveErrors(request, errors);
      return false;
    }
    if (bNew) {
      try
      {
        String[] reqIds = formBean.getReqDetId();
        RequestDAO reqDAO = new RequestDAO();
        RequestDetailFormBean detailBean = null;
        boolean isMatAvailable = true;
        String strError = MCUtil.getBundleString("errors.tenderplan.material.notAvailable") + " : ";
        for (int i = 0; i < reqIds.length; i++)
        {
          detailBean = reqDAO.checkRequestDetailStep(NumberUtil.parseInt(reqIds[i], 0), RequestBean.STEP_REQ);
          if (detailBean != null)
          {
            isMatAvailable = false;
            strError = strError + " <br/> " + detailBean.getMatName() + "(" + detailBean.getReqNumber() + ")";
          }
        }
        if (!isMatAvailable)
        {
          this.strErrors = strError;
          return false;
        }
      }
      catch (Exception localException2) {}
    }
    bean = new TenderPlanBean();
    bean.setTenId(tenId);
    bean.setTenderNumber(formBean.getTenderNumber());
    bean.setCreatedEmp(MCUtil.getMemberID(request.getSession()));
    bean.setCreatedDate(formBean.getCreatedDate());
    bean.setPackName(formBean.getPackName());
    bean.setSubject(formBean.getSubject());
    bean.setFoundation(formBean.getFoundation());
    bean.setSupplyScope(formBean.getSupplyScope());
    bean.setForm(formBean.getForm());
    bean.setOfferType(formBean.getOfferType());
    bean.setFinancial(formBean.getFinancial());
    bean.setBidSendingDate(formBean.getBidSendingDate());
    bean.setBidDeadline(formBean.getBidDeadline());
    bean.setBidOpeningDate(formBean.getBidOpeningDate());
    bean.setEvaluatedDate(formBean.getEvaluatedDate());
    bean.setApprovedDate(formBean.getApprovedDate());
    bean.setContractDate(formBean.getContractDate());
    bean.setContractExecDate(formBean.getContractExecDate());
    bean.setBidSendingTime(formBean.getBidSendingTime());
    bean.setBidDeadlineTime(formBean.getBidDeadlineTime());
    bean.setBidOpeningTime(formBean.getBidOpeningTime());
    bean.setEvaluatedTime(formBean.getEvaluatedTime());
    bean.setApprovedTime(formBean.getApprovedTime());
    bean.setContractTime(formBean.getContractTime());
    bean.setContractExecTime(formBean.getContractExecTime());
    bean.setTechEvalStandard(formBean.getTechEvalStandard());
    bean.setComEvalStandard(formBean.getComEvalStandard());
    bean.setEvalKind(formBean.getEvalKind());
    bean.setHandleEmp(formBean.getHandleEmp());
    bean.setDeliveryTime(formBean.getDeliveryTime());
    
    bean.setFinancialPreTax(formBean.getFinancialPreTax());
    try
    {
      TenderPlanDAO tenderDAO = new TenderPlanDAO();
      int besId = 0;
      if (bNew)
      {
        bean.setCreatedEmp(MCUtil.getMemberID(request.getSession()));
        tenId = tenderDAO.insertTenderPlan(bean);
        
        BidEvalSumBean beanE = new BidEvalSumBean();
        
        beanE.setEmpId(bean.getCreatedEmp());
        beanE.setTenId(tenId);
        beanE.setBesNumber(formBean.getTenderNumber() + "-" + tenId);
        beanE.setCreatedDate(formBean.getCreatedDate());
        BidEvalSumDAO bidDAO = new BidEvalSumDAO();
        besId = bidDAO.insertBidEvalSumId(beanE);
      }
      else
      {
        OnlineUser user = MCUtil.getOnlineUser(session);
        LogUtil.info("Username : " + user.getName() + " - Form : " + mapping.getPath() + " - EDIT");
        tenderDAO.updateTenderPlan(bean);
        
        besId = tenderDAO.getBesId(tenId);
      }
      session.setAttribute("id", Integer.valueOf(tenId));
      addDetail(tenId, besId, formBean);
      addEvalGroup(tenId, besId, formBean);
      addCertificate(tenId, formBean);
      addVendor(tenId, besId, formBean);
    }
    catch (Exception ex)
    {
      LogUtil.error("FAILED:TenderPlan:add-" + ex.getMessage());
      ex.printStackTrace();
    }
    return true;
  }
  
  private TenderPlanDetailBean detExisted(ArrayList arrDet, int detId)
  {
    TenderPlanDetailBean bean = null;
    for (int i = 0; i < arrDet.size(); i++)
    {
      bean = (TenderPlanDetailBean)arrDet.get(i);
      if (bean.getDetId() == detId) {
        return bean;
      }
    }
    return null;
  }
  
  private TenderEvaluateGroupBean evalGroupExisted(ArrayList arrEvalGroup, int tegId)
  {
    TenderEvaluateGroupBean bean = null;
    for (int i = 0; i < arrEvalGroup.size(); i++)
    {
      bean = (TenderEvaluateGroupBean)arrEvalGroup.get(i);
      if (bean.getTegId() == tegId) {
        return bean;
      }
    }
    return null;
  }
  
  private TenderPlanCertificateBean certificateExisted(ArrayList arrCertificate, int tcId)
  {
    TenderPlanCertificateBean bean = null;
    for (int i = 0; i < arrCertificate.size(); i++)
    {
      bean = (TenderPlanCertificateBean)arrCertificate.get(i);
      if (bean.getTcId() == tcId) {
        return bean;
      }
    }
    return null;
  }
  
  private TenderEvaluateVendorBean evalVendorExisted(ArrayList arrVendor, int venId)
  {
    TenderEvaluateVendorBean bean = null;
    for (int i = 0; i < arrVendor.size(); i++)
    {
      bean = (TenderEvaluateVendorBean)arrVendor.get(i);
      if (bean.getVenId() == venId) {
        return bean;
      }
    }
    return null;
  }
  
  private void addDetail(int tenId, int besId, TenderPlanFormBean formBean)
  {
    if (tenId != 0)
    {
      TenderPlanDAO tenderDAO = new TenderPlanDAO();
      ArrayList arrDet = null;
      try
      {
        arrDet = tenderDAO.getTenderPlanDetails(tenId);
      }
      catch (Exception ex)
      {
        arrDet = new ArrayList();
      }
      if (formBean.getTenDetId() != null)
      {
        String[] detIds = formBean.getTenDetId();
        String[] matIds = formBean.getMatId();
        TenderPlanDetailBean detBean = null;
        double additionQuantity = 0.0D;
        boolean canUpdate = false;
        for (int i = 0; i < detIds.length; i++)
        {
          canUpdate = false;
          if (!detIds[i].equals("0")) {
            try
            {
              detBean = detExisted(arrDet, Integer.parseInt(detIds[i]));
              if (detBean != null)
              {
                double resize = 0.0D;
                additionQuantity = Double.parseDouble(formBean.getMatQuantity()[i]);
                resize = detBean.getQuantity() - additionQuantity;
                double oldQuantity = detBean.getQuantity();
                if (resize != 0.0D)
                {
                  detBean.setQuantity(additionQuantity);
                  canUpdate = true;
                }
                if (!detBean.getProvideDate().equals(formBean.getMatProvideDate()[i]))
                {
                  detBean.setProvideDate(formBean.getMatProvideDate()[i]);
                  canUpdate = true;
                }
                if (canUpdate)
                {
                  tenderDAO.updateTenderPlanDetail(detBean);
                  updateRemainQuantityOfRequestDetail(detBean.getReqDetailId(), oldQuantity, additionQuantity);
                  
                  tenderDAO.updateBidEvalSumDetailQt1(oldQuantity, Integer.parseInt(detIds[i]));
                  tenderDAO.updateBidEvalSumDetailQt2(additionQuantity, Integer.parseInt(detIds[i]));
                }
              }
            }
            catch (Exception localException1) {}
          } else {
            try
            {
              if (GenericValidator.isBlankOrNull(formBean.getMatQuantity()[i])) {
                additionQuantity = 0.0D;
              } else {
                additionQuantity = Double.parseDouble(formBean.getMatQuantity()[i]);
              }
              detBean = new TenderPlanDetailBean(0, formBean.getMatUnit()[i], additionQuantity, formBean.getMatProvideDate()[i], Integer.parseInt(formBean.getReqDetId()[i]));
              detBean.setTenId(tenId);
              detBean.setMatId(Integer.parseInt(matIds[i]));
              detBean.setPrice(Double.parseDouble(formBean.getMatPrice()[i]));
              detBean.setTotal(Double.parseDouble(formBean.getMatTotal()[i]));
              tenderDAO.insertTenderPlanDetail(detBean);
              updateRemainQuantityOfRequestDetail(detBean.getReqDetailId(), 0.0D, additionQuantity);
              
              BidEvalSumVendorDAO bidVendorDAO = new BidEvalSumVendorDAO();
              
              bidVendorDAO.insertBidEvalForBidEvalSumDetail(besId, detBean.getMatId(), additionQuantity, detBean.getPrice(), detBean.getTotal(), formBean.getMatUnit()[i], detBean.getReqDetailId());
            }
            catch (Exception localException2) {}
          }
        }
      }
    }
  }
  
  private void addEvalGroup(int tenId, int besId, TenderPlanFormBean formBean)
  {
    TenderPlanDAO tenderDAO = new TenderPlanDAO();
    ArrayList arrDet = null;
    if (tenId != 0) {
      try
      {
        arrDet = tenderDAO.getTenderPlanEvalGroup(tenId);
      }
      catch (Exception ex)
      {
        arrDet = new ArrayList();
      }
    }
    if (formBean.getEvalId() != null)
    {
      String[] detIds = formBean.getEvalId();
      TenderEvaluateGroupBean detBean = null;
      boolean canUpdate = false;
      for (int i = 0; i < detIds.length; i++) {
        if (!detIds[i].equals("0")) {
          try
          {
            detBean = evalGroupExisted(arrDet, Integer.parseInt(detIds[i]));
            if (detBean != null)
            {
              if (!detBean.getEmployee().equals(formBean.getEvalEmployee()[i]))
              {
                detBean.setEmployee(formBean.getEvalEmployee()[i]);
                canUpdate = true;
              }
              if (!detBean.getPosition().equals(formBean.getEvalPosition()[i]))
              {
                detBean.setPosition(formBean.getEvalPosition()[i]);
                canUpdate = true;
              }
              if (!detBean.getNote().equals(formBean.getEvalNote()[i]))
              {
                detBean.setNote(formBean.getEvalNote()[i]);
                canUpdate = true;
              }
              if (canUpdate) {
                tenderDAO.updateTenderEvaluateGroup(detBean);
              }
            }
          }
          catch (Exception localException1) {}
        } else {
          try
          {
            detBean = new TenderEvaluateGroupBean(0, formBean.getEvalEmployee()[i], formBean.getEvalPosition()[i], formBean.getEvalNote()[i]);
            detBean.setTenId(tenId);
            tenderDAO.insertTenderEvaluateGroup(detBean);
            
            BidEvalSumDAO bidDAO = new BidEvalSumDAO();
            bidDAO.insertBidEvalSumGroup(besId, formBean.getEvalEmployee()[i], formBean.getEvalPosition()[i], formBean.getEvalNote()[i]);
          }
          catch (Exception localException2) {}
        }
      }
    }
  }
  
  private void addCertificate(int tenId, TenderPlanFormBean formBean)
  {
    TenderPlanDAO tenderDAO = new TenderPlanDAO();
    ArrayList arrDet = null;
    if (tenId != 0) {
      try
      {
        arrDet = tenderDAO.getTenderPlanCertificate(tenId);
      }
      catch (Exception ex)
      {
        arrDet = new ArrayList();
      }
    }
    if (formBean.getTcId() != null)
    {
      String[] detIds = formBean.getTcId();
      TenderPlanCertificateBean detBean = null;
      boolean canUpdate = false;
      for (int i = 0; i < detIds.length; i++) {
        if (!detIds[i].equals("0")) {
          try
          {
            detBean = certificateExisted(arrDet, Integer.parseInt(detIds[i]));
            if (detBean != null)
            {
              if (detBean.getCerId() != Integer.parseInt(formBean.getCerId()[i]))
              {
                detBean.setCerId(Integer.parseInt(formBean.getCerId()[i]));
                canUpdate = true;
              }
              if (canUpdate) {
                tenderDAO.updateTenderCertificate(detBean);
              }
            }
          }
          catch (Exception localException1) {}
        } else {
          try
          {
            detBean = new TenderPlanCertificateBean(formBean.getTenId(), Integer.parseInt(formBean.getCerId()[i]));
            detBean.setTenId(tenId);
            tenderDAO.insertTenderCertificate(detBean);
          }
          catch (Exception localException2) {}
        }
      }
    }
  }
  
  private void addVendor(int tenId, int besId, TenderPlanFormBean formBean)
  {
    TenderPlanDAO tenderDAO = new TenderPlanDAO();
    ArrayList arrDet = null;
    if (tenId != 0) {
      try
      {
        arrDet = tenderDAO.getTenderPlanVendor(tenId);
      }
      catch (Exception ex)
      {
        arrDet = new ArrayList();
      }
    }
    if (formBean.getVendor() != null)
    {
      String[] venIds = formBean.getVendor();
      TenderEvaluateVendorBean venBean = null;
      for (int i = 0; i < venIds.length; i++)
      {
        venBean = evalVendorExisted(arrDet, Integer.parseInt(venIds[i]));
        if (venBean == null) {
          try
          {
            venBean = new TenderEvaluateVendorBean();
            venBean.setVenId(Integer.parseInt(venIds[i]));
            venBean.setTenId(tenId);
            venBean.setBiddedPage("");
            venBean.setQuoNo("");
            venBean.setBiddedFoundation("");
            venBean.setBidValidity("");
            tenderDAO.insertTenderEvaluateVendor(venBean);
            
            BidEvalSumVendorDAO bidVendorDAO = new BidEvalSumVendorDAO();
            bidVendorDAO.insertBidEvalForBidEvalSum(besId, Integer.parseInt(venIds[i]));
          }
          catch (Exception localException1) {}
        }
      }
    }
  }
  
  private void updateRemainQuantityOfRequestDetail(int reqDetId, double oldQuantity, double newQuantity)
  {
    RequestDAO reqDAO = new RequestDAO();
    if (reqDetId != 0) {
      try
      {
        RequestDetailBean bean = reqDAO.getRequestDetail(reqDetId);
        if (bean != null)
        {
          bean.setRemainQuantity(bean.getRemainQuantity() + oldQuantity - newQuantity);
          reqDAO.updateRequestDetail(bean);
        }
      }
      catch (Exception localException) {}
    }
  }
  
  protected String getFunction()
  {
    return PermissionUtil.FUNC_EDIT + "";
  }
  
  protected int getPermit()
  {
    return PermissionUtil.PER_PURCHASING_TENDERPLAN;
  }
}
