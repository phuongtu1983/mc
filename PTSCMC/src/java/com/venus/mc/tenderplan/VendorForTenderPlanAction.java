package com.venus.mc.tenderplan;

import com.venus.core.util.GenericValidator;
import com.venus.mc.bean.VendorBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.VendorDAO;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class VendorForTenderPlanAction
  extends SpineAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    String venIds = request.getParameter("venIds");
    int rowNum = 0;
    ArrayList vendorList = null;
    if (!GenericValidator.isBlankOrNull(venIds))
    {
      rowNum = Integer.parseInt(request.getParameter("rowNum"));
      try
      {
        VendorDAO vendorDAO = new VendorDAO();
        vendorList = vendorDAO.getVendors(venIds);
        rowNum += vendorList.size();
      }
      catch (Exception localException) {}
    }
    if (vendorList == null) {
      vendorList = new ArrayList();
    }
    VendorBean vendor = null;
    for (int i = 0; i < vendorList.size(); i++)
    {
      vendor = (VendorBean)vendorList.get(i);
      vendor.setNote(rowNum-- + "");
    }
    request.setAttribute("vendorList", vendorList);
    return true;
  }
}
