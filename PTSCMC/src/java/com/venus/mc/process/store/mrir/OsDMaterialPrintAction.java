package com.venus.mc.process.store.mrir;

import com.venus.core.util.GenericValidator;
import com.venus.core.util.LogUtil;
import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.OsDDetailBean;
import com.venus.mc.core.BaseAction;
import com.venus.mc.core.ExcelExport;
import com.venus.mc.dao.MrirDAO;
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

public class OsDMaterialPrintAction
  extends BaseAction
{
  private String templateFile = "Materials_of_OSD.xls";
  
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    int osdId = NumberUtil.parseInt(request.getParameter("osdId"), 0);
    if (osdId > 0)
    {
      ArrayList arrOsDMat = null;
      MrirDAO mrirDAO = new MrirDAO();
      Map beans = new HashMap();
      try
      {
        arrOsDMat = mrirDAO.getOsDDetailsByOsD(osdId);
      }
      catch (Exception ex)
      {
        LogUtil.error(getClass(), ex.getMessage());
      }
      if (arrOsDMat == null) {
        arrOsDMat = new ArrayList();
      }
      for (int i = 0; i < arrOsDMat.size(); i++)
      {
        OsDDetailBean matBean = (OsDDetailBean)arrOsDMat.get(i);
        matBean.setNo(i + 1);
        if (matBean.getStatus() == 1) {
          matBean.setStatusText("Close");
        } else {
          matBean.setStatusText("Open");
        }
        if (!GenericValidator.isBlankOrNull(matBean.getNonConform()))
        {
          String[] arrNonConform = matBean.getNonConform().split(",");
          String nonConformText = "";
          for (int j = 0; j < arrNonConform.length; j++)
          {
            nonConformText = nonConformText + MCUtil.getBundleString(new StringBuilder().append("message.osd.nonConform").append(arrNonConform[j]).toString());
            if (j != arrNonConform.length - 1) {
              nonConformText = nonConformText + "/";
            }
          }
          matBean.setNonConformText(nonConformText);
        }
      }
      beans.put("osd", arrOsDMat);
      String templateFileName = request.getSession().getServletContext().getRealPath("/templates/" + this.templateFile);
      ExcelExport exporter = new ExcelExport();
      exporter.setBeans(beans);
      try
      {
        long milis = System.currentTimeMillis();
        exporter.export(request, response, templateFileName, this.templateFile);
        milis = System.currentTimeMillis() - milis;
        System.out.println("Materials_of_OSD.xls : time : " + (int)(milis / 1000L % 60L) + " phut " + (int)(milis / 1000L / 60L) + " mili giay");
      }
      catch (Exception ex)
      {
        LogUtil.error(getClass(), ex.getMessage());
      }
    }
    return true;
  }
  
  protected boolean isReturnStream()
  {
    return true;
  }
}
