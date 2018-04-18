package com.venus.mc.contract.order;

import com.venus.core.util.GenericValidator;
import com.venus.mc.bean.ContractBean;
import com.venus.mc.bean.TenderPlanBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.ContractDAO;
import com.venus.mc.dao.TenderPlanDAO;
import com.venus.mc.dao.VendorDAO;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class GetSourceAction
  extends SpineAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    String kind = request.getParameter("kind");
    ArrayList array = null;
    if (!GenericValidator.isBlankOrNull(kind)) {
      if (kind.equals("1"))
      {
        ContractDAO contract = new ContractDAO();
        try
        {
          array = contract.getContractFromTenderPlan(ContractBean.KIND_ORDER);
        }
        catch (Exception localException) {}
        if (array == null) {
          array = new ArrayList();
        }
        ContractBean bean = new ContractBean(0);
        bean.setContractNumber("");
        array.add(0, bean);
        request.setAttribute("contractList", array);
        this.actionForwardResult = "contract";
      }
      else if (kind.equals("2"))
      {
        ArrayList arrTenderPlan = null;
        try
        {
          TenderPlanDAO tenderDAO = new TenderPlanDAO();
          arrTenderPlan = tenderDAO.getTenderPlansForContract();
        }
        catch (Exception localException1) {}
        if (arrTenderPlan == null) {
          arrTenderPlan = new ArrayList();
        }
        TenderPlanBean tender = new TenderPlanBean(0);
        tender.setTenderNumber("");
        arrTenderPlan.add(0, tender);
        request.setAttribute("tenderPlanList", arrTenderPlan);
        this.actionForwardResult = "tenderplan";
      }
      else if (kind.equals("3"))
      {
        ArrayList arrVendor = null;
        try
        {
          VendorDAO vendorDAO = new VendorDAO();
          arrVendor = vendorDAO.getVendors();
        }
        catch (Exception localException2) {}
        if (arrVendor == null) {
          arrVendor = new ArrayList();
        }
        request.setAttribute("vendorList", arrVendor);
        this.actionForwardResult = "other";
      }
    }
    return true;
  }
}
