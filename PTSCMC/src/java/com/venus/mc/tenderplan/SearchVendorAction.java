package com.venus.mc.tenderplan;

import com.venus.core.util.LogUtil;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.VendorDAO;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class SearchVendorAction
  extends SpineAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    SearchVendorFormBean formBean = (SearchVendorFormBean)form;
    ArrayList vendorList = null;
    try
    {
      VendorDAO vendorDAO = new VendorDAO();
      vendorList = vendorDAO.searchVendor(formBean);
    }
    catch (Exception ex)
    {
      LogUtil.error("FAILED:VendorForTenderPlan:search-" + ex.getMessage());
      ex.printStackTrace();
    }
    if (vendorList == null) {
      vendorList = new ArrayList();
    }
    request.setAttribute("vendorList", vendorList);
    return true;
  }
}
