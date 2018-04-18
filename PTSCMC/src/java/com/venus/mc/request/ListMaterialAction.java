package com.venus.mc.request;

import com.venus.core.util.GenericValidator;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.MaterialDAO;
import com.venus.mc.util.MCUtil;
import com.venus.mc.util.PermissionUtil;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.LabelValueBean;

public class ListMaterialAction
  extends SpineAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    String matIds = request.getParameter("matIds");
    ArrayList arrMaterial = null;
    if (!GenericValidator.isBlankOrNull(matIds)) {
      try
      {
        MaterialDAO materialDAO = new MaterialDAO();
        arrMaterial = materialDAO.getMaterialsForRequest(matIds);
      }
      catch (Exception localException) {}
    }
    if (arrMaterial == null) {
      arrMaterial = new ArrayList();
    }
    request.setAttribute("materialList", arrMaterial);
    
    ArrayList arrMaterialKind = new ArrayList();
    LabelValueBean value = new LabelValueBean();
    value.setLabel(MCUtil.getBundleString("message.request.materialKind.major"));
    value.setValue(RequestFormBean.KIND_MAJOR + "");
    arrMaterialKind.add(value);
    value = new LabelValueBean();
    value.setLabel(MCUtil.getBundleString("message.request.materialKind.secondary"));
    value.setValue(RequestFormBean.KIND_SECONDARY + "");
    arrMaterialKind.add(value);
    request.setAttribute("requestMaterialKindList", arrMaterialKind);
    return true;
  }
  
  protected String getFunction()
  {
    return PermissionUtil.FUNC_LIST + "";
  }
  
  protected int getPermit()
  {
    return PermissionUtil.PER_PURCHASING_REQUEST;
  }
}
