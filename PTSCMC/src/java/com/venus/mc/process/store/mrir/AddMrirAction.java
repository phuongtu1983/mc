package com.venus.mc.process.store.mrir;

import com.venus.core.util.LogUtil;
import com.venus.core.util.NumberUtil;
import com.venus.core.util.OutputUtil;
import com.venus.core.util.StringUtil;
import com.venus.mc.bean.MaterialBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.MaterialDAO;
import com.venus.mc.dao.MrirDAO;
import com.venus.mc.material.MaterialFormBean;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class AddMaterialAction
  extends SpineAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    int detId = NumberUtil.parseInt(request.getParameter("detId"), 0);
    MaterialFormBean formBean = (MaterialFormBean)form;
    MaterialBean bean = new MaterialBean();
    bean.setMatId(formBean.getMatId());
    
    bean.setCode(null);
    bean.setUniId(formBean.getUniId());
    bean.setNameEn(StringUtil.encodeHTML(formBean.getNameEn()));
    bean.setNameVn(StringUtil.encodeHTML(formBean.getNameVn()));
    
    bean.setNote(StringUtil.encodeHTML(formBean.getNote()));
    MaterialDAO materialDAO = new MaterialDAO();
    int matId = 0;
    try
    {
      matId = materialDAO.checkName(bean.getMatId(), bean.getNameVn(), bean.getNameEn());
      if (matId == 0) {
        matId = materialDAO.insertMaterialOsD(bean);
      }
      if (matId > 0)
      {
        MrirDAO mrirDAO = new MrirDAO();
        try
        {
          mrirDAO.updateOsDMatIdTemp(matId, detId);
        }
        catch (Exception ex)
        {
          LogUtil.error("FAILED:TechEval:add-" + ex.getMessage());
          ex.printStackTrace();
        }
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
