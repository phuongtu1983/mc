package com.venus.mc.contract;

import com.venus.core.util.GenericValidator;
import com.venus.core.util.LogUtil;
import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.ContractBean;
import com.venus.mc.core.BaseAction;
import com.venus.mc.core.ExcelExport;
import com.venus.mc.dao.ContractDAO;
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

public class ContractPaymentPrintAction
  extends BaseAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    if (!GenericValidator.isBlankOrNull(request.getParameter("conId")))
    {
      ArrayList paymentList = null;
      ContractDAO contractDAO = new ContractDAO();
      ContractBean conBean = null;
      int conId = NumberUtil.parseInt(request.getParameter("conId"), 0);
      int kind = NumberUtil.parseInt(request.getParameter("kind"), 0);
      try
      {
        conBean = contractDAO.getContract(conId);
        paymentList = contractDAO.getPaidPaymentsByContractForPrint(conId, kind);
      }
      catch (Exception ex)
      {
        LogUtil.error("FAILED:Contract:printPaymentSum-" + ex.getMessage());
        ex.printStackTrace();
      }
      if (conBean == null) {
        conBean = new ContractBean();
      }
      if (paymentList == null) {
        paymentList = new ArrayList();
      }
      try
      {
        String templateFileName = request.getSession().getServletContext().getRealPath("/templates/BANG TONG HOP GIA TRI THANH TOAN.xls");
        double sum = 0.0D;
        ContractPaymentFormBean bean = null;
        for (int i = 0; i < paymentList.size(); i++)
        {
          bean = (ContractPaymentFormBean)paymentList.get(i);
          sum += bean.getTotal();
        }
        bean = contractDAO.getPaymentByContractLasted(conId, kind);
        if (bean == null) {
          bean = new ContractPaymentFormBean();
        }
        Map beans = new HashMap();
        beans.put("thanhtoan", paymentList);
        beans.put("sum", Double.valueOf(sum));
        beans.put("sum2", Double.valueOf(sum + bean.getTotal()));
        beans.put("thanhtoan2", bean);
        beans.put("mcrp_contractNumber", conBean.getContractNumber());
        beans.put("mcrp_vendor", conBean.getVendorName());
        ExcelExport exporter = new ExcelExport();
        exporter.setBeans(beans);
        long milis = System.currentTimeMillis();
        exporter.export(request, response, templateFileName, "BANG TONG HOP GIA TRI THANH TOAN.xls");
        milis = System.currentTimeMillis() - milis;
        System.out.println("BANG TONG HOP GIA TRI THANH TOAN.xls : time : " + (int)(milis / 1000L % 60L) + " phut " + (int)(milis / 1000L / 60L) + " mili giay");
      }
      catch (Exception localException1) {}
    }
    return true;
  }
  
  protected boolean isReturnStream()
  {
    return true;
  }
}
