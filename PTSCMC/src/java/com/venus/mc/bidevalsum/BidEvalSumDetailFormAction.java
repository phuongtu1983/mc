/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.bidevalsum;

import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.BidEvalSumVendorBean;
import com.venus.mc.contract.ContractFormBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.BidEvalSumVendorDAO;
import com.venus.mc.dao.TenderPlanDAO;
import com.venus.mc.util.Constants;
import com.venus.mc.util.MCUtil;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.LabelValueBean;

/**
 *
 * @author mai vinh loc
 */
public class BidEvalSumDetailFormAction extends SpineAction {

    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    @Override
    public boolean doAction(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();
        BidEvalSumVendorBean bean = null;
        int besvId = NumberUtil.parseInt(request.getParameter("besvId"), 0);
        int tenId = NumberUtil.parseInt(request.getParameter("tenId"), 0);
        int evalKind = 0;
        //terId = "1";
        //vendorName = "123";
        //result="1";

        if (besvId > 0) {
            BidEvalSumVendorDAO vendorDAO = new BidEvalSumVendorDAO();
            try {
                bean = vendorDAO.getBidEvalSumVendor(besvId, tenId);
                TenderPlanDAO tenderDAO = new TenderPlanDAO();
                evalKind = tenderDAO.getEvalKindTenderPlan(tenId + "");
            } catch (Exception ex) {
            }
        }
        if (bean == null) {
            bean = new BidEvalSumVendorBean();
        }

        ArrayList arrPayment = new ArrayList();
        LabelValueBean value;
        value = new LabelValueBean();
        value.setLabel(MCUtil.getBundleString("message.contract.paymentMode.cash"));
        value.setValue(ContractFormBean.PAYMENT_CASH + "");
        arrPayment.add(value);
        value = new LabelValueBean();
        value.setLabel(MCUtil.getBundleString("message.contract.paymentMode.transfer"));
        value.setValue(ContractFormBean.PAYMENT_TRANSFER + "");
        arrPayment.add(value);
        request.setAttribute(Constants.PAYMENT_MODE_LIST, arrPayment);

        request.setAttribute(Constants.BID_EVAL_SUM_DETAIL, bean);
        request.setAttribute(Constants.TENDERPLAN_EVALKIND, evalKind);

        return true;
    }
}
