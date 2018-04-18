package com.venus.mc.material;

import com.venus.core.auth.OnlineUser;
import com.venus.core.util.LogUtil;
import com.venus.mc.bean.SpeBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.SpeDAO;
import com.venus.mc.util.MCUtil;
import com.venus.mc.util.PermissionUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class AddSpeAction
  extends SpineAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    HttpSession session = request.getSession();
    
    SpeFormBean formBean = (SpeFormBean)form;
    String spe = session.getAttribute("spe").toString();
    boolean bNew = false;
    if (spe.equals("0")) {
      bNew = true;
    } else {
      bNew = false;
    }
    SpeBean bean = new SpeBean();
    
    bean.setSpe1Id(formBean.getSpe1Id());
    bean.setSpe2Id(formBean.getSpe2Id());
    bean.setSpe3Id(formBean.getSpe3Id());
    bean.setSpe4Id(formBean.getSpe4Id());
    bean.setSpe5Id(formBean.getSpe5Id());
    bean.setSpe6Id(formBean.getSpe6Id());
    bean.setSign(formBean.getSign());
    bean.setNote(formBean.getNote());
    bean.setSpe(formBean.getSpe());
    
    SpeDAO speDAO = new SpeDAO();
    try
    {
      if (!bNew)
      {
        OnlineUser user = MCUtil.getOnlineUser(session);
        LogUtil.info("Username : " + user.getName() + " - Form : " + mapping.getPath() + " - EDIT");
      }
    }
    catch (Exception ex)
    {
      LogUtil.error("FAILED:Vender:add-" + ex.getMessage());
      ex.printStackTrace();
    }
    return true;
  }
  
  protected String getFunction()
  {
    return PermissionUtil.FUNC_EDIT + "";
  }
  
  protected int getPermit()
  {
    return PermissionUtil.PER_LIBRARY_MATERIAL_CATALOG;
  }
}
