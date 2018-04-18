package com.venus.mc.tenderplan;

import com.venus.core.util.LogUtil;
import com.venus.core.util.NumberUtil;
import com.venus.mc.core.BaseAction;
import com.venus.mc.core.ExcelExport;
import com.venus.mc.dao.TenderPlanDAO;
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

public class TenderPlanMaterialPrintAction
  extends BaseAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    ArrayList list = null;
    String templateFileName = request.getSession().getServletContext().getRealPath("/templates/DonGiaSoSanh.xls");
    try
    {
      TenderPlanDAO tenderDAO = new TenderPlanDAO();
      list = tenderDAO.getTenderPlanDetail(NumberUtil.parseInt(request.getParameter("tenId"), 0));
    }
    catch (Exception localException1) {}
    if (list == null) {
      list = new ArrayList();
    }
    try
    {
      TenderPlanDetailFormBean bean = null;
      for (int i = 0; i < list.size(); i++)
      {
        bean = (TenderPlanDetailFormBean)list.get(i);
        bean.setMatId(i + 1);
      }
      Map beans = new HashMap();
      beans.put("dongia", list);
      ExcelExport exporter = new ExcelExport();
      exporter.setBeans(beans);
      long milis = System.currentTimeMillis();
      String exportFile = request.getSession().getServletContext().getRealPath("/templates/temp/Don Gia So Sanh.xls");
      exporter.export(request, response, templateFileName, exportFile);
      milis = System.currentTimeMillis() - milis;
      System.out.println("Don Gia So Sanh.xls : time : " + (int)(milis / 1000L % 60L) + " phut " + (int)(milis / 1000L / 60L) + " mili giay");
    }
    catch (Exception ex)
    {
      LogUtil.error("FAILED:TenderPlanMaterialPrintAction:print-" + ex.getMessage());
      ex.printStackTrace();
    }
    return true;
  }
  
  protected boolean isReturnStream()
  {
    return true;
  }
}
