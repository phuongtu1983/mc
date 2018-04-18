package com.venus.mc.request;

import com.venus.core.util.LogUtil;
import com.venus.core.util.OutputUtil;
import com.venus.core.util.StringUtil;
import com.venus.mc.bean.MaterialBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.MaterialDAO;
import com.venus.mc.material.MaterialFormBean;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    MaterialBean bean = new MaterialBean();
    bean.setMatId(formBean.getMatId());
    bean.setCode("");
    bean.setUniId(formBean.getUniId());
    bean.setNameEn(StringUtil.encodeHTML(formBean.getNameEn()));
    bean.setNameVn(StringUtil.encodeHTML(formBean.getNameVn()));
    bean.setQc(StringUtil.encodeHTML(formBean.getQc()));
    
    bean.setNote(StringUtil.encodeHTML(formBean.getNote()));
    MaterialDAO materialDAO = new MaterialDAO();
    int matId = bean.getMatId();
    try
    {
      if (matId == 0)
      {
        matId = materialDAO.checkName(bean.getMatId(), bean.getNameVn(), bean.getNameEn());
        if (matId > 0)
        {
          ActionMessages errors = new ActionMessages();
          errors.add("matNameExisted", new ActionMessage("errors.material.existedName"));
          saveErrors(request, errors);
          return false;
        }
        matId = materialDAO.insertMaterial(bean);
      }
      else
      {
        materialDAO.updateRequestMaterial(bean);
      }
      OutputUtil.sendStringToOutput(response, "success:" + matId);
    }
    catch (Exception ex)
    {
      LogUtil.error("FAILED:RequestMaterial:add-" + ex.getMessage());
      ex.printStackTrace();
    }
    return true;
  }
  
  protected boolean isReturnStream()
  {
    return true;
  }
}
