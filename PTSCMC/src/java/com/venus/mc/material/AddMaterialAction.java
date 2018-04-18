package com.venus.mc.material;

import com.venus.core.auth.OnlineUser;
import com.venus.core.util.LogUtil;
import com.venus.core.util.NumberUtil;
import com.venus.core.util.OutputUtil;
import com.venus.core.util.StringUtil;
import com.venus.mc.bean.ContractBean;
import com.venus.mc.bean.MaterialBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.MaterialDAO;
import com.venus.mc.dao.RequestDAO;
import com.venus.mc.util.MCUtil;
import com.venus.mc.util.PermissionUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

public class AddMaterialAction
  extends SpineAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    MaterialFormBean formBean = (MaterialFormBean)form;
    int matId = formBean.getMatId();
    int detId = NumberUtil.parseInt(request.getParameter("detId"), 0);
    int kind = NumberUtil.parseInt(request.getParameter("kind"), 0);
    boolean bNew = false;
    if (matId == 0) {
      bNew = true;
    } else {
      bNew = false;
    }
    MaterialBean bean = new MaterialBean();
    bean.setMatId(formBean.getMatId());
    bean.setOriId(formBean.getOriId());
    bean.setUniId(formBean.getUniId());
    bean.setCode(formBean.getCode());
    bean.setSpe1Id(formBean.getSpe1Id());
    bean.setSpe2Id(formBean.getSpe2Id());
    bean.setSpe3Id(formBean.getSpe3Id());
    bean.setSpe4Id(formBean.getSpe4Id());
    bean.setSpe5Id(formBean.getSpe5Id());
    bean.setSpe6Id(formBean.getSpe6Id());
    bean.setSpe7(formBean.getSpe7());
    bean.setNameEn(StringUtil.encodeHTML(formBean.getNameEn()));
    bean.setNameVn(StringUtil.encodeHTML(formBean.getNameVn()));
    
    bean.setType(formBean.getType());
    bean.setKind(formBean.getKind());
    bean.setNote(StringUtil.encodeHTML(formBean.getNote()));
    bean.setGroId(formBean.getGroId());
    bean.setQc(StringUtil.encodeHTML(formBean.getQc()));
    bean.setSpe(formBean.getSpe());
    bean.setDeliveryTime(formBean.getDeliveryTime());
    MaterialDAO materialDAO = new MaterialDAO();
    try
    {
      boolean isExist = false;
      isExist = materialDAO.checkCode(formBean.getMatId(), formBean.getCode());
      if (isExist)
      {
        ActionMessages errors = new ActionMessages();
        errors.add("matCodeExisted", new ActionMessage("errors.material.existedCode"));
        saveErrors(request, errors);
        return false;
      }
      int m = 0;
      m = materialDAO.checkName(bean.getMatId(), bean.getNameVn(), bean.getNameEn());
      if (m > 0)
      {
        ActionMessages errors = new ActionMessages();
        errors.add("matNameExisted", new ActionMessage("errors.material.existedName"));
        saveErrors(request, errors);
        return false;
      }
      if (bNew)
      {
        materialDAO.deleteMaterialAA(detId);
        matId = materialDAO.insertMaterialBid(bean);
        if (kind == ContractBean.KIND_ADJUSTMENT) {
          materialDAO.updateContractDetailMatIdNewAdjustment(matId, detId);
        } else if (kind == ContractBean.KIND_ORDER) {
          materialDAO.updateContractDetailMatIdTempNull(matId, detId);
        } else {
          materialDAO.updateContractDetailMatIdTemp(matId, detId);
        }
      }
      else
      {
        HttpSession session = request.getSession();
        OnlineUser user = MCUtil.getOnlineUser(session);
        LogUtil.info("Username : " + user.getName() + " - Form : " + mapping.getPath() + " - EDIT");
        
        MaterialBean oldBean = materialDAO.getMaterial(bean.getMatId() + "");
        int isUnitChangesd = 0;
        if (oldBean.getUniId() != bean.getUniId()) {
          isUnitChangesd = 1;
        }
        materialDAO.updateMaterial(bean);
        if (isUnitChangesd == 1)
        {
          RequestDAO requestDAO = new RequestDAO();
          String str = requestDAO.getRequestDetailIdsRelationMaterial(bean.getMatId());
          if (!str.equals("0")) {
            requestDAO.updateUnitRequestDetail(formBean.getUniId(), str);
          }
        }
      }
    }
    catch (Exception ex)
    {
      LogUtil.error("FAILED:Vender:add-" + ex.getMessage());
      ex.printStackTrace();
    }
    String json = "{\"id\":\"" + matId + "\"}";
    OutputUtil.sendStringToOutput(response, json);
    
    return true;
  }
  
  protected String getFunction()
  {
    return PermissionUtil.FUNC_EDIT + "";
  }
  
  protected int getPermit()
  {
    return PermissionUtil.PER_LIBRARY_MATERIAL_COMPANY;
  }
  
  protected boolean isReturnStream()
  {
    return true;
  }
}
