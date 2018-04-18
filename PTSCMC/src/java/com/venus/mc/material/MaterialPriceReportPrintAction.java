package com.venus.mc.material;

import com.venus.core.util.LogUtil;
import com.venus.core.util.NumberUtil;
import com.venus.core.util.StringUtil;
import com.venus.mc.core.ExcelExport;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.MaterialDAO;
import com.venus.mc.dao.OrganizationDAO;
import com.venus.mc.util.MCUtil;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class MaterialPriceReportPrintAction
  extends SpineAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    int searchid = NumberUtil.parseInt(request.getParameter("searchid"), 0);
    String searchvalue = request.getParameter("searchvalue");
    ArrayList materialList = null;
    MaterialDAO materialDAO = new MaterialDAO();
    try
    {
      int orgId = MCUtil.getOrganizationID(request.getSession());
      OrganizationDAO orgDAO = new OrganizationDAO();
      String orgs = orgDAO.getnestedChildOfOrg(orgId + "");
      orgs = orgs + "," + orgDAO.getnestedParentOfOrg(new StringBuilder().append(orgId).append("").toString());
      orgs = orgs + "," + orgId;
      materialDAO.setRequestOrg(orgs);
      String fromDate = request.getParameter("fromDate");
      String toDate = request.getParameter("toDate");
      materialList = materialDAO.searchMaterialPrices(searchid, StringUtil.encodeHTML(searchvalue), fromDate, toDate);
    }
    catch (Exception ex)
    {
      LogUtil.error("FAILED:MaterialPrice:searchSimple-" + ex.getMessage());
      ex.printStackTrace();
    }
    if (materialList == null) {
      materialList = new ArrayList();
    }
    String templateFileName = request.getSession().getServletContext().getRealPath("/templates/vattu_price_export_template.xls");
    try
    {
      Map beans = new HashMap();
      beans.put("material", materialList);
      ExcelExport exporter = new ExcelExport();
      exporter.setBeans(beans);
      long milis = System.currentTimeMillis();
      exporter.export(request, response, templateFileName, "material_price.xls");
      milis = System.currentTimeMillis() - milis;
      System.out.println("material_price.xls : time : " + (int)(milis / 1000L % 60L) + " phut " + (int)(milis / 1000L / 60L) + " mili giay");
    }
    catch (Exception ex)
    {
      LogUtil.error("FAILED:MaterialPriceReportPrint:print-" + ex.getMessage());
      ex.printStackTrace();
    }
    return true;
  }
  
  protected boolean isReturnStream()
  {
    return true;
  }
}
