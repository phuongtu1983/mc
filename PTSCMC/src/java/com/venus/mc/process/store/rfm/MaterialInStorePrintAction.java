package com.venus.mc.process.store.rfm;

import com.venus.core.util.LogUtil;
import com.venus.core.util.NumberUtil;
import com.venus.core.util.StringUtil;
import com.venus.mc.bean.RfmDetailBean;
import com.venus.mc.core.BaseAction;
import com.venus.mc.core.ExcelExport;
import com.venus.mc.dao.RfmDAO;
import java.io.PrintStream;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class MaterialInStorePrintAction
  extends BaseAction
{
  private String templateFile = "VTTB Ton kho.xls";
  
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    int searchid = NumberUtil.parseInt(request.getParameter("searchid"), 0);
    String strFieldvalue = request.getParameter("value");
    try
    {
      strFieldvalue = URLDecoder.decode(strFieldvalue, "UTF8");
    }
    catch (Exception localException1) {}
    String strFieldvalues = request.getParameter("values");
    String extraValue = request.getParameter("extraValue");
    ArrayList arrrfm = null;
    Map beans = new HashMap();
    try
    {
      RfmDAO rfmDAO = new RfmDAO();
      arrrfm = rfmDAO.searchMaterialInStore(searchid, StringUtil.encodeHTML(strFieldvalue), strFieldvalues, 3, extraValue, 0);
    }
    catch (Exception ex)
    {
      LogUtil.error(getClass(), ex.getMessage());
    }
    if (arrrfm == null) {
      arrrfm = new ArrayList();
    }
    for (int i = 0; i < arrrfm.size(); i++)
    {
      RfmDetailBean matBean = (RfmDetailBean)arrrfm.get(i);
      matBean.setStt(i + 1);
    }
    beans.put("pc", arrrfm);
    
    String templateFileName = request.getSession().getServletContext().getRealPath("/templates/" + this.templateFile);
    ExcelExport exporter = new ExcelExport();
    exporter.setBeans(beans);
    try
    {
      long milis = System.currentTimeMillis();
      exporter.export(request, response, templateFileName, this.templateFile);
      milis = System.currentTimeMillis() - milis;
      System.out.println("VTTB Ton kho.xls : time : " + (int)(milis / 1000L % 60L) + " phut " + (int)(milis / 1000L / 60L) + " mili giay");
    }
    catch (Exception ex)
    {
      LogUtil.error(getClass(), ex.getMessage());
    }
    return true;
  }
  
  protected boolean isReturnStream()
  {
    return true;
  }
}
