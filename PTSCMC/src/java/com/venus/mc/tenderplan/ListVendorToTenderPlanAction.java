package com.venus.mc.tenderplan;

import com.venus.core.util.LogUtil;
import com.venus.mc.bean.GroupMaterialBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.VendorDAO;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class ListVendorToTenderPlanAction
  extends SpineAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    VendorDAO vendorDAO = new VendorDAO();
    ArrayList vendorGroupMaterialList = null;
    try
    {
      vendorGroupMaterialList = vendorDAO.getGroupMaterials("");
    }
    catch (Exception ex)
    {
      LogUtil.error("FAILED:VenderGroupMaterial:list-" + ex.getMessage());
      ex.printStackTrace();
    }
    if (vendorGroupMaterialList == null) {
      vendorGroupMaterialList = new ArrayList();
    }
    GroupMaterialBean bean = new GroupMaterialBean();
    bean.setGroId(0);
    bean.setName("");
    vendorGroupMaterialList.add(0, bean);
    request.setAttribute("vendorGroupMaterialList", vendorGroupMaterialList);
    return true;
  }
}
