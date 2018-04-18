package com.venus.mc.contract;

import com.venus.core.auth.OnlineUser;
import com.venus.core.util.GenericValidator;
import com.venus.core.util.LogUtil;
import com.venus.core.util.NumberUtil;
import com.venus.core.util.StringUtil;
import com.venus.mc.bean.ContractBean;
import com.venus.mc.bean.ContractDetailBean;
import com.venus.mc.bean.InvoiceContractBean;
import com.venus.mc.bean.MaterialBean;
import com.venus.mc.bean.ProjectCostBean;
import com.venus.mc.bean.RequestDetailBean;
import com.venus.mc.bean.VendorMaterialBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.ContractDAO;
import com.venus.mc.dao.MaterialDAO;
import com.venus.mc.dao.RequestDAO;
import com.venus.mc.dao.TenderPlanDAO;
import com.venus.mc.dao.VendorDAO;
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

public class AddContractAction
  extends SpineAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    HttpSession session = request.getSession();
    ContractFormBean formBean = (ContractFormBean)form;
    ContractDAO contractDAO = new ContractDAO();
    ContractBean bean = null;
    int kind = NumberUtil.parseInt(request.getParameter("kind"), 0);
    boolean bNew = false;
    boolean isExist = false;
    int conId = 0;
    try
    {
      if (kind == ContractBean.KIND_APPENDIX)
      {
        String conNumber = "";
        ContractBean pC = contractDAO.getContract(formBean.getParentId());
        if (pC != null)
        {
          conNumber = pC.getContractNumber();
          int ind = conNumber.indexOf("-");
          if (ind > -1) {
            conNumber = conNumber.substring(0, ind);
          }
          conNumber = " H?? " + conNumber;
        }
        formBean.setContractNumber("PL " + formBean.getContractNumber() + " H? " + pC.getContractNumber());
      }
      bean = contractDAO.getContractByNumber(formBean.getContractNumber(), formBean.getKind());
      
      conId = formBean.getConId();
      if (conId == 0)
      {
        bNew = true;
        if (bean != null) {
          isExist = true;
        }
      }
      else
      {
        bNew = false;
        if ((bean != null) && (bean.getConId() != conId)) {
          isExist = true;
        }
      }
      if (formBean.getKind() == ContractBean.KIND_ADJUSTMENT) {
        isExist = contractDAO.getContractByNumberAdjustment(formBean.getContractNumber(), formBean.getKind(), formBean.getParentId(), conId);
      }
    }
    catch (Exception localException1) {}
    if (isExist)
    {
      ActionMessages errors = new ActionMessages();
      if (formBean.getKind() == ContractBean.KIND_CONTRACT) {
        errors.add("contractExisted", new ActionMessage("errors.contract.existed"));
      } else if (formBean.getKind() == ContractBean.KIND_APPENDIX) {
        errors.add("appendixErrorMessageDiv", new ActionMessage("errors.appendix.existed"));
      } else if (formBean.getKind() == ContractBean.KIND_ADJUSTMENT) {
        errors.add("adjustmentErrorMessageDiv", new ActionMessage("errors.adjustment.existed"));
      }
      saveErrors(request, errors);
      return false;
    }
    bean = new ContractBean();
    bean.setConId(formBean.getConId());
    bean.setTenId(formBean.getTenId());
    bean.setVenId(formBean.getVenId());
    bean.setContractNumber(formBean.getContractNumber());
    bean.setEffectedDate(formBean.getEffectedDate());
    bean.setExpireDate(formBean.getExpireDate());
    bean.setTransport(formBean.getTransport());
    bean.setOtherFee(formBean.getOtherFee());
    bean.setTotal(formBean.getTotal());
    bean.setDeliveryDate(formBean.getDeliveryDate());
    bean.setDeliveryPlace(formBean.getDeliveryPlace());
    bean.setPaymentMode(formBean.getPaymentMode());
    bean.setPaymentStatus(formBean.getPaymentStatus());
    bean.setKind(formBean.getKind());
    bean.setParentId(formBean.getParentId());
    bean.setNote(formBean.getNote());
    bean.setTotalNotVAT(formBean.getTotalNotVAT());
    bean.setSumVAT(formBean.getSumVAT());
    bean.setSignDate(formBean.getSignDate());
    bean.setVolume(formBean.getVolume());
    bean.setPayment(formBean.getPayment());
    bean.setCurrency(formBean.getCurrency());
    bean.setFollowEmp(formBean.getFollowEmp());
    bean.setProcessStatus(formBean.getProcessStatus());
    bean.setProcessStatusText(formBean.getProcessStatusText());
    bean.setCertificate(formBean.getCertificate());
    bean.setNote(formBean.getNote());
    bean.setDelivery(formBean.getDelivery());
    bean.setAppendixContractNumber(formBean.getAppendixContractNumber());
    bean.setCertificate(formBean.getCertificate());
    bean.setSoftDocument(formBean.getSoftDocument());
    try
    {
      ContractBean oldBean = null;
      if (bNew)
      {
        bean.setResponseEmp(MCUtil.getMemberID(request.getSession()));
        bean.setCreatedEmp(bean.getResponseEmp());
        int pId = formBean.getParentId();
        int parentId = 0;
        parentId = contractDAO.getContractId(pId + "");
        conId = contractDAO.insertContract(bean);
        bean.setConId(conId);
        if (formBean.getKind() == ContractBean.KIND_ADJUSTMENT)
        {
          ArrayList contractDetailList = new ArrayList();
          ContractDetailBean detBean = null;
          if (parentId > 0) {
            pId = parentId;
          }
          contractDetailList = contractDAO.getContractDetailsForAdjustment(pId);
          for (int i = 0; i < contractDetailList.size(); i++)
          {
            detBean = (ContractDetailBean)contractDetailList.get(i);
            detBean.setConId(conId);
            String[] matIds = formBean.getMatId();
            if (matIds != null) {
              for (int j = 0; j < matIds.length; j++)
              {
                detBean.setNote("0");
                detBean.setPrice(detBean.getPrice());
                detBean.setQuantity(detBean.getQuantity());
                detBean.setMaterialName(StringUtil.encodeHTML(detBean.getMaterialName()));
                detBean.setMatId(detBean.getMatId());
                detBean.setMatIdTemp(detBean.getMatId());
                detBean.setStatus(1);
                if ((Integer.parseInt(matIds[j]) == detBean.getMatId()) && (detBean.getReqDetailId() == Integer.parseInt(formBean.getReqDetId()[j])))
                {
                  detBean.setNote("1");
                  detBean.setPrice(NumberUtil.parseDouble(formBean.getPrice()[j], 0.0D));
                  detBean.setQuantity(NumberUtil.parseDouble(formBean.getQuantity()[j], 0.0D));
                  detBean.setMaterialName(StringUtil.encodeHTML(formBean.getMaterialName()[j]));
                  detBean.setMatId(NumberUtil.parseInt(formBean.getMatId()[j], 0));
                  detBean.setMatIdTemp(NumberUtil.parseInt(formBean.getMatId()[j], 0));
                  detBean.setStatus(1);
                  break;
                }
              }
            }
            contractDAO.insertContractDetailForAdjustment(formBean.getKind(), detBean);
          }
        }
      }
      else
      {
        OnlineUser user = MCUtil.getOnlineUser(session);
        LogUtil.info("Username : " + user.getName() + " - Form : " + mapping.getPath() + " - EDIT");
        oldBean = contractDAO.getContract(bean.getConId());
        contractDAO.updateContract(bean);
      }
      session.setAttribute("id", Integer.valueOf(conId));
      session.setAttribute("ten_id", Integer.valueOf(bean.getTenId()));
      session.setAttribute("ven_id", Integer.valueOf(bean.getVenId()));
      if (conId != 0)
      {
        formBean.setConId(conId);
        if ((bNew) && (formBean.getKind() != ContractBean.KIND_ADJUSTMENT)) {
          addDetail(formBean);
        } else {
          addDetail(formBean);
        }
        updateTenderPlan(formBean);
        addContractCostProject(formBean);
        addContractInvoice(formBean);
      }
      if ((formBean.getKind() != ContractBean.KIND_DELIVERY_REQUEST) && (formBean.getKind() != ContractBean.KIND_APPENDIX) && 
        ((oldBean == null) || ((oldBean != null) && (oldBean.getPaymentStatus() != ContractFormBean.STATUS_APPROVED))) && 
        (bean.getPaymentStatus() == ContractFormBean.STATUS_APPROVED))
      {
        MaterialDAO matDAO = new MaterialDAO();
        ArrayList matPrice = matDAO.getMaterialPricesOfContract(bean.getConId());
        if ((matPrice == null) || (matPrice.isEmpty())) {
          contractDAO.saveContractMaterialPrice(bean.getConId());
        }
      }
    }
    catch (Exception ex)
    {
      LogUtil.error("FAILED:Contract:add-" + ex.getMessage());
      ex.printStackTrace();
    }
    if (formBean.getKind() == ContractBean.KIND_CONTRACT) {
      this.actionForwardResult = "contract";
    } else if (formBean.getKind() == ContractBean.KIND_PRINCIPLE) {
      this.actionForwardResult = "contractPrinciple";
    } else if (formBean.getKind() == ContractBean.KIND_ORDER) {
      this.actionForwardResult = "order";
    } else if (formBean.getKind() == ContractBean.KIND_APPENDIX) {
      this.actionForwardResult = "appendix";
    }
    return true;
  }
  
  private ContractDetailBean detExisted(ArrayList arrDet, int detId)
  {
    ContractDetailFormBean formBean = null;
    for (int i = 0; i < arrDet.size(); i++)
    {
      formBean = (ContractDetailFormBean)arrDet.get(i);
      if (formBean.getDetId() == detId)
      {
        arrDet.remove(formBean);
        ContractDetailBean bean = new ContractDetailBean(detId);
        bean.setQuantity(formBean.getQuantity());
        bean.setVat(formBean.getVat());
        bean.setPrice(formBean.getPrice());
        bean.setTotal(formBean.getTotal());
        bean.setStatus(formBean.getMatStatus());
        bean.setReqDetailId(formBean.getReqDetailId());
        
        return bean;
      }
    }
    return null;
  }
  
  private void addDetail(ContractFormBean formBean)
  {
    if (formBean.getConDetId() != null)
    {
      ContractDAO contractDAO = new ContractDAO();
      ArrayList arrDet = null;
      try
      {
        arrDet = contractDAO.getContractDetails(formBean.getConId());
        if ((arrDet.isEmpty()) && 
          (formBean.getKind() == ContractBean.KIND_ADJUSTMENT))
        {
          ArrayList contractDetailList = new ArrayList();
          ContractDetailBean detBean = null;
          int pId = formBean.getParentId();
          int parentId = 0;
          parentId = contractDAO.getContractId(pId + "");
          if (parentId > 0) {
            pId = parentId;
          }
          contractDetailList = contractDAO.getContractDetailsForAdjustment(pId);
          for (int i = 0; i < contractDetailList.size(); i++)
          {
            detBean = (ContractDetailBean)contractDetailList.get(i);
            detBean.setConId(formBean.getConId());
            String[] matIds = formBean.getMatId();
            if (matIds != null) {
              for (int j = 0; j < matIds.length; j++)
              {
                detBean.setNote("0");
                detBean.setPrice(detBean.getPrice());
                detBean.setQuantity(detBean.getQuantity());
                detBean.setMaterialName(StringUtil.encodeHTML(detBean.getMaterialName()));
                detBean.setMatId(detBean.getMatId());
                detBean.setMatIdTemp(detBean.getMatId());
                detBean.setStatus(1);
                if ((Integer.parseInt(matIds[j]) == detBean.getMatId()) && (detBean.getReqDetailId() == Integer.parseInt(formBean.getReqDetId()[j])))
                {
                  detBean.setNote("1");
                  detBean.setPrice(NumberUtil.parseDouble(formBean.getPrice()[j], 0.0D));
                  detBean.setQuantity(NumberUtil.parseDouble(formBean.getQuantity()[j], 0.0D));
                  detBean.setMaterialName(StringUtil.encodeHTML(formBean.getMaterialName()[j]));
                  detBean.setMatId(NumberUtil.parseInt(formBean.getMatId()[j], 0));
                  detBean.setMatIdTemp(NumberUtil.parseInt(formBean.getMatId()[j], 0));
                  detBean.setStatus(1);
                  break;
                }
              }
            }
            contractDAO.insertContractDetailForAdjustment(formBean.getKind(), detBean);
          }
        }
        arrDet = contractDAO.getContractDetails(formBean.getConId());
      }
      catch (Exception localException) {}
      if (arrDet == null) {
        arrDet = new ArrayList();
      }
      String[] detIds = formBean.getConDetId();
      String[] matIds = formBean.getMatId();
      String[] expires = formBean.getExpire();
      
      ContractDetailBean detBean = null;
      double number = 0.0D;
      double dou = 0.0D;
      String s = "";
      int inter = 0;
      boolean canUpdate = false;
      if (formBean.getKind() == ContractBean.KIND_ADJUSTMENT)
      {
        ArrayList contractDetailList = new ArrayList();
        try
        {
          int pId = formBean.getParentId();
          int parentId = 0;
          parentId = contractDAO.getContractId(pId + "");
          if (parentId > 0) {
            pId = parentId;
          }
          contractDetailList = contractDAO.getContractDetailsForAdjustment(pId);
          for (int i = 0; i < contractDetailList.size(); i++)
          {
            detBean = (ContractDetailBean)contractDetailList.get(i);
            if (matIds != null) {
              for (int j = 0; j < matIds.length; j++)
              {
                detBean.setNote("0");
                detBean.setPrice(detBean.getPrice());
                detBean.setQuantity(detBean.getQuantity());
                detBean.setMaterialName(detBean.getMaterialName());
                detBean.setMatId(detBean.getMatId());
                detBean.setMatIdTemp(detBean.getMatId());
                detBean.setStatus(1);
                if ((Integer.parseInt(matIds[j]) == detBean.getMatId()) && (detBean.getReqDetailId() == Integer.parseInt(formBean.getReqDetId()[j])))
                {
                  detBean.setNote("1");
                  detBean.setPrice(NumberUtil.parseDouble(formBean.getPrice()[j], 0.0D));
                  detBean.setQuantity(NumberUtil.parseDouble(formBean.getQuantity()[j], 0.0D));
                  detBean.setMaterialName(formBean.getMaterialName()[j]);
                  detBean.setMatId(NumberUtil.parseInt(formBean.getMatId()[j], 0));
                  detBean.setMatIdTemp(NumberUtil.parseInt(formBean.getMatId()[j], 0));
                  detBean.setStatus(1);
                  break;
                }
              }
            }
            contractDAO.updateContractDetail(detBean);
          }
        }
        catch (Exception localException1) {}
      }
      else
      {
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
                int oldStatus = 0;
                if (formBean.getKind() != ContractBean.KIND_PRINCIPLE)
                {
                  number = Double.parseDouble(formBean.getQuantity()[i]);
                  resize = detBean.getQuantity() - number;
                  if (resize != 0.0D)
                  {
                    detBean.setQuantity(number);
                    canUpdate = true;
                  }
                  if (formBean.getDetTotal() != null)
                  {
                    dou = Double.parseDouble(formBean.getDetTotal()[i]);
                    if (dou != detBean.getTotal())
                    {
                      detBean.setTotal(dou);
                      canUpdate = true;
                    }
                  }
                  if (expires != null) {
                    detBean.setExpired(Integer.parseInt(expires[i]));
                  }
                }
                if (formBean.getKind() == ContractBean.KIND_ADJUSTMENT) {
                  detBean.setNote("1");
                }
                if ((formBean.getMatStatus() != null) && 
                  (detBean.getStatus() != Integer.parseInt(formBean.getMatStatus()[i])))
                {
                  oldStatus = detBean.getStatus();
                  detBean.setStatus(Integer.parseInt(formBean.getMatStatus()[i]));
                  canUpdate = true;
                }
                if (formBean.getVat() != null)
                {
                  dou = Double.parseDouble(formBean.getVat()[i]);
                  if (dou != detBean.getVat())
                  {
                    detBean.setVat(dou);
                    canUpdate = true;
                  }
                }
                if (formBean.getPrice() != null)
                {
                  dou = Double.parseDouble(formBean.getPrice()[i]);
                  if (dou != detBean.getPrice())
                  {
                    detBean.setPrice(dou);
                    canUpdate = true;
                  }
                }
                if (formBean.getMatIdTemp() != null)
                {
                  inter = Integer.parseInt(formBean.getMatIdTemp()[i]);
                  if (inter != detBean.getMatIdTemp())
                  {
                    detBean.setMatIdTemp(inter);
                    canUpdate = true;
                  }
                }
                if (formBean.getConfirm() != null)
                {
                  inter = Integer.parseInt(formBean.getConfirm()[i]);
                  if (inter != detBean.getConfirm())
                  {
                    detBean.setConfirm(inter);
                    canUpdate = true;
                  }
                }
                if (formBean.getMaterialName() != null)
                {
                  s = formBean.getMaterialName()[i];
                  s = StringUtil.encodeHTML(s);
                  if (!s.equals(detBean.getMaterialName()))
                  {
                    detBean.setMaterialName(s);
                    canUpdate = true;
                  }
                }
                if (canUpdate)
                {
                  if (detBean.getStatus() == 0) {
                    detBean.setStatus(ContractFormBean.MATERIAL_STATUS_NORMAL);
                  }
                  contractDAO.updateContractDetail(detBean);
                  contractDAO.updateRequestDetail(formBean.getConId());
                  contractDAO.updateContractDetailConfirm(detBean);
                  if ((formBean.getKind() == ContractBean.KIND_ORDER) && (
                    (oldStatus == ContractFormBean.MATERIAL_STATUS_NORMAL) || (detBean.getStatus() == ContractFormBean.MATERIAL_STATUS_CANCEL))) {
                    updateRemainQuantityOfRequestDetail(detBean.getReqDetailId(), detBean.getQuantity(), 1);
                  }
                }
              }
            }
            catch (Exception localException2) {}
          } else {
            try
            {
              double quantity = 0.0D;
              double price = 0.0D;
              double total = 0.0D;
              double vat = 0.0D;
              int expired = 0;
              int confirm1 = 0;
              int matTemp = 0;
              String matName = "";
              if (formBean.getKind() == ContractBean.KIND_ADJUSTMENT) {
                detBean.setNote("1");
              }
              if (formBean.getKind() != ContractBean.KIND_PRINCIPLE)
              {
                if (GenericValidator.isBlankOrNull(formBean.getQuantity()[i])) {
                  quantity = 0.0D;
                } else {
                  quantity = Double.parseDouble(formBean.getQuantity()[i]);
                }
                if (formBean.getDetTotal() != null) {
                  if (GenericValidator.isBlankOrNull(formBean.getDetTotal()[i])) {
                    total = 0.0D;
                  } else {
                    total = Double.parseDouble(formBean.getDetTotal()[i]);
                  }
                }
                if (expires != null) {
                  expired = Integer.parseInt(expires[i]);
                }
              }
              if (formBean.getPrice() != null) {
                if (GenericValidator.isBlankOrNull(formBean.getPrice()[i])) {
                  price = 0.0D;
                } else {
                  price = Double.parseDouble(formBean.getPrice()[i]);
                }
              }
              if (formBean.getVat() != null) {
                if (GenericValidator.isBlankOrNull(formBean.getVat()[i])) {
                  vat = 0.0D;
                } else {
                  vat = Double.parseDouble(formBean.getVat()[i]);
                }
              }
              if (formBean.getConfirm() == null) {
                confirm1 = 0;
              } else {
                confirm1 = Integer.parseInt(formBean.getConfirm()[i]);
              }
              if (formBean.getMatIdTemp() == null) {
                matTemp = 0;
              } else {
                matTemp = NumberUtil.parseInt(formBean.getMatIdTemp()[i], 0);
              }
              if (formBean.getMaterialName() == null)
              {
                matName = "";
              }
              else
              {
                matName = formBean.getMaterialName()[i];
                matName = StringUtil.encodeHTML(matName);
              }
              detBean = new ContractDetailBean(0, formBean.getUnit()[i], quantity, price, total, "", vat, 0, matTemp, matName, confirm1);
              detBean.setConId(formBean.getConId());
              detBean.setMatId(Integer.parseInt(matIds[i]));
              if (formBean.getMatStatus() != null) {
                detBean.setStatus(Integer.parseInt(formBean.getMatStatus()[i]));
              }
              detBean.setExpired(expired);
              if (formBean.getReqDetId() != null) {
                detBean.setReqDetailId(Integer.parseInt(formBean.getReqDetId()[i]));
              }
              if (detBean.getStatus() == 0) {
                detBean.setStatus(ContractFormBean.MATERIAL_STATUS_NORMAL);
              }
              contractDAO.insertContractDetail(formBean.getKind(), detBean);
              insertMaterial(formBean.getVenId(), detBean);
              contractDAO.updateRequestDetail(formBean.getConId());
              if ((formBean.getKind() == ContractBean.KIND_ORDER) && (formBean.getTenId() == 0) && 
                (detBean.getStatus() == ContractFormBean.MATERIAL_STATUS_NORMAL)) {
                if (detBean.getStatus() == ContractFormBean.MATERIAL_STATUS_CANCEL) {
                  updateRemainQuantityOfRequestDetail(detBean.getReqDetailId(), quantity, 1);
                } else if (detBean.getStatus() == ContractFormBean.MATERIAL_STATUS_NORMAL) {
                  updateRemainQuantityOfRequestDetail(detBean.getReqDetailId(), quantity, -1);
                }
              }
            }
            catch (Exception localException3) {}
          }
        }
      }
    }
  }
  
  private void addContractCostProject(ContractFormBean formBean)
  {
    if (formBean.getConProId() != null)
    {
      ContractDAO contractDAO = new ContractDAO();
      try
      {
        contractDAO.deleteProjectCost(formBean.getConId());
      }
      catch (Exception localException) {}
      String[] detIds = formBean.getConProId();
      ProjectCostBean detBean = null;
      for (int i = 0; i < detIds.length; i++) {
        try
        {
          double cost = 0.0D;
          if (GenericValidator.isBlankOrNull(formBean.getCost()[i])) {
            cost = 0.0D;
          } else {
            cost = Double.parseDouble(formBean.getCost()[i]);
          }
          detBean = new ProjectCostBean(0, cost);
          detBean.setProId(NumberUtil.parseInt(formBean.getProId()[i], 0));
          detBean.setConId(formBean.getConId());
          contractDAO.insertProjectCost(detBean);
        }
        catch (Exception localException1) {}
      }
    }
  }
  
  private InvoiceContractBean projectBillExisted(ArrayList arrDet, int detId)
  {
    InvoiceContractFormBean formBean = null;
    for (int i = 0; i < arrDet.size(); i++)
    {
      formBean = (InvoiceContractFormBean)arrDet.get(i);
      if (formBean.getIcId() == detId)
      {
        arrDet.remove(formBean);
        InvoiceContractBean bean = new InvoiceContractBean(detId);
        bean.setInvoice(formBean.getInvoice());
        bean.setPaymentDate(formBean.getPaymentDate());
        bean.setAmount(formBean.getAmount());
        bean.setInvoiceDate(formBean.getInvoiceDate());
        bean.setNote(formBean.getNote());
        return bean;
      }
    }
    return null;
  }
  
  private void addContractInvoice(ContractFormBean formBean)
  {
    if (formBean.getConInvId() != null)
    {
      ContractDAO contractDAO = new ContractDAO();
      ArrayList arrDet = null;
      try
      {
        arrDet = contractDAO.getContractInvoices(formBean.getConId());
      }
      catch (Exception localException) {}
      if (arrDet == null) {
        arrDet = new ArrayList();
      }
      String[] detIds = formBean.getConInvId();
      InvoiceContractBean detBean = null;
      boolean canUpdate = false;
      for (int i = 0; i < detIds.length; i++) {
        if (!detIds[i].equals("0")) {
          try
          {
            detBean = projectBillExisted(arrDet, Integer.parseInt(detIds[i]));
            if (detBean != null)
            {
              if (!detBean.getInvoice().equals(formBean.getInvoice()[i]))
              {
                detBean.setInvoice(formBean.getInvoice()[i]);
                canUpdate = true;
              }
              if (!detBean.getPaymentDate().equals(formBean.getPaymentDate()[i]))
              {
                detBean.setPaymentDate(formBean.getPaymentDate()[i]);
                canUpdate = true;
              }
              if (!detBean.getInvoiceDate().equals(formBean.getInvoiceDate()[i]))
              {
                detBean.setInvoiceDate(formBean.getInvoiceDate()[i]);
                canUpdate = true;
              }
              if (!detBean.getNote().equals(formBean.getInvoiceNote()[i]))
              {
                detBean.setNote(formBean.getInvoiceNote()[i]);
                canUpdate = true;
              }
              if (detBean.getAmount() != Double.parseDouble(formBean.getAmount()[i]))
              {
                detBean.setAmount(Double.parseDouble(formBean.getAmount()[i]));
                canUpdate = true;
              }
              if (canUpdate) {
                contractDAO.updateInvoiceContract(detBean);
              }
            }
          }
          catch (Exception localException1) {}
        } else {
          try
          {
            detBean = new InvoiceContractBean(0, formBean.getInvoice()[i], formBean.getPaymentDate()[i], formBean.getInvoiceDate()[i], formBean.getInvoiceNote()[i], formBean.getKind());
            detBean.setConId(formBean.getConId());
            detBean.setAmount(Double.parseDouble(formBean.getAmount()[i]));
            contractDAO.insertInvoiceContract(detBean);
          }
          catch (Exception localException2) {}
        }
      }
    }
  }
  
  private void updateTenderPlan(ContractFormBean formBean)
  {
    if (formBean.getConId() != 0)
    {
      TenderPlanDAO tenderDAO = new TenderPlanDAO();
      try
      {
        tenderDAO.updateTenderPlanStatus(formBean.getTenId());
      }
      catch (Exception localException) {}
    }
  }
  
  private void insertMaterial(int venId, ContractDetailBean conDetBean)
  {
    try
    {
      VendorDAO vendorDAO = new VendorDAO();
      MaterialDAO matDAO = new MaterialDAO();
      MaterialBean matBean = matDAO.getMaterial(conDetBean.getMatId() + "");
      VendorMaterialBean bean = vendorDAO.getVendorMaterialByName(venId, matBean.getNameVn(), matBean.getNameEn());
      if (bean == null)
      {
        bean = new VendorMaterialBean();
        bean.setVenId(venId);
        bean.setNameVn(matBean.getNameVn());
        bean.setNameEn(matBean.getNameEn());
        bean.setOriId(matBean.getOriId());
        bean.setGroId(matBean.getGroId());
        vendorDAO.insertVendorMaterial(bean);
      }
    }
    catch (Exception ex)
    {
      LogUtil.error("FAILED:AddContract-VenderMaterial:add-" + ex.getMessage());
      ex.printStackTrace();
    }
  }
  
  private void updateRemainQuantityOfRequestDetail(int reqDetId, double changedQuantity, int sign)
  {
    RequestDAO reqDAO = new RequestDAO();
    if (reqDetId != 0) {
      try
      {
        RequestDetailBean bean = reqDAO.getRequestDetail(reqDetId);
        if (bean != null)
        {
          if (sign < 0) {
            bean.setRemainQuantity(bean.getRemainQuantity() - changedQuantity);
          } else {
            bean.setRemainQuantity(bean.getRemainQuantity() + changedQuantity);
          }
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
    return PermissionUtil.PER_PURCHASING_CONTRACT;
  }
}
