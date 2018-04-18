package com.venus.mc.process.store.mrir;

import com.venus.core.util.LogUtil;
import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.DnBean;
import com.venus.mc.bean.MrirBean;
import com.venus.mc.bean.MrirOsDBean;
import com.venus.mc.core.BaseAction;
import com.venus.mc.core.ExcelExport;
import com.venus.mc.dao.DnDAO;
import com.venus.mc.dao.MrirDAO;
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

public class MrirPrintAction
  extends BaseAction
{
  private String templateFile = "BM.02.02.KH_BBKiemTraTiepNhanVTTBTL.xls";
  
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    int mrirId = NumberUtil.parseInt(request.getParameter("mrirId"), 0);
    ArrayList mrirList = null;
    MrirDAO mrirDAO = new MrirDAO();
    MrirBean mrirBean = null;
    Map beans = new HashMap();
    int comment = NumberUtil.parseInt(request.getParameter("comment"), 0);
    try
    {
      mrirBean = mrirDAO.getMrir(mrirId);
    }
    catch (Exception ex)
    {
      LogUtil.error("MrirPrintAction: " + ex.getMessage());
    }
    if (mrirBean != null)
    {
      beans.put("mcrp_conNumber", mrirBean.getConNumber());
      beans.put("mcrp_venName", mrirBean.getVenName());
      DnBean dnBean = null;
      DnDAO dnDAO = new DnDAO();
      try
      {
        int kind = 1;
        dnBean = dnDAO.getDn(mrirBean.getDnId(), kind, mrirBean.getMrirId());
      }
      catch (Exception ex)
      {
        LogUtil.error("MrirPrintAction: " + ex.getMessage());
      }
      if (dnBean != null) {
        beans.put("mcrp_proName", dnBean.getProName());
      } else {
        beans.put("mcrp_proName", "");
      }
      beans.put("mcrp_blNo", mrirBean.getBlNo());
      beans.put("mcrp_invoiceNo", mrirBean.getInvoiceNo());
      beans.put("mcrp_plNo", mrirBean.getPlNo());
      beans.put("mcrp_mrirNumber", mrirBean.getMrirNumber());
      beans.put("mcrp_createdDate", mrirBean.getCreatedDate());
      try
      {
        mrirList = mrirDAO.getMrirOsDs(mrirId);
      }
      catch (Exception ex)
      {
        LogUtil.error("MrirPrintAction: " + ex.getMessage());
      }
      if (mrirList == null) {
        mrirList = new ArrayList();
      }
      String reqNumbers = ",";
      int reqId = 0;
      for (int i = 0; i < mrirList.size(); i++)
      {
        MrirOsDBean moBean = (MrirOsDBean)mrirList.get(i);
        moBean.setDetId(i + 1);
        if (!reqNumbers.contains("," + moBean.getReqNumber() + ",")) {
          reqNumbers = reqNumbers + moBean.getReqNumber() + ",";
        }
        if (reqId != moBean.getReqId()) {
          reqId = moBean.getReqId();
        }
        if (moBean.getOsdId() > 0) {
          try
          {
            mrirDAO.getOsDMaterial(moBean);
          }
          catch (Exception ex)
          {
            LogUtil.error("MrirPrintAction: " + ex.getMessage());
          }
        }
      }
      if (!reqNumbers.equals(","))
      {
        reqNumbers = reqNumbers.substring(1);
        reqNumbers = reqNumbers.substring(0, reqNumbers.length() - 1);
      }
      beans.put("mcrp_reqNumber", reqNumbers);
      beans.put("mrir", mrirList);
      if (mrirBean.getKind() == 1)
      {
        beans.put("mcrp_packingListNo", mrirBean.getPlNo());
        if (comment == 1) {
          this.templateFile = "BM.02.02.KH_BBKiemTraTiepNhanVTTBTL_DuAn_Comment.xls";
        } else {
          this.templateFile = "BM.02.02.KH_BBKiemTraTiepNhanVTTBTL_DuAn.xls";
        }
      }
      String templateFileName = request.getSession().getServletContext().getRealPath("/templates/" + this.templateFile);
      ExcelExport exporter = new ExcelExport();
      exporter.setBeans(beans);
      try
      {
        long milis = System.currentTimeMillis();
        exporter.export(request, response, templateFileName, this.templateFile);
        milis = System.currentTimeMillis() - milis;
        System.out.println(this.templateFile + "time : " + (int)(milis / 1000L % 60L) + " phut " + (int)(milis / 1000L / 60L) + " mili giay");
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
