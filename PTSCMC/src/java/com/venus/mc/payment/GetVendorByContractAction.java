/*
 *
 * Created on April 13, 2007, 2:08 PM
 */
package com.venus.mc.payment;

import com.venus.core.util.GenericValidator;
import com.venus.core.util.LogUtil;
import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.ContractBean;
import com.venus.mc.bean.VendorBean;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import com.venus.mc.util.Constants;

import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.ContractDAO;
import com.venus.mc.dao.VendorDAO;
import javax.servlet.http.HttpSession;

/**
 *
 * @author phuontu
 * @version
 */
public class GetVendorByContractAction extends SpineAction {

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
        String conId = request.getParameter("conId");
        String contractNumber = request.getParameter("contractNumber");
        int ven = NumberUtil.parseInt(request.getParameter("venId"), 0);
        Integer id = (Integer) session.getAttribute("id");
        if (id != null) {
            conId = id + "";
        }
        session.removeAttribute("id");
        session.removeAttribute("ten_id");
        session.removeAttribute("ven_id");
        boolean t = true;
        int venId = 0;
        if (!GenericValidator.isBlankOrNull(conId)) {
            try {
                VendorDAO vendorDAO = new VendorDAO();
                VendorBean bean;
                if (ven > 0) {
                    bean = vendorDAO.getVendor(ven);
                } else {
                    bean = vendorDAO.getVendorByContract(conId);
                }
                VendorBean bean1 = null;
                venId = NumberUtil.parseInt(bean.getNote(), 0);
                if (GenericValidator.isBlankOrNull(contractNumber) && bean != null && NumberUtil.isInteger(bean.getNote())) {
                    while (t) {
                        bean1 = vendorDAO.getVendorByNote(venId);
                        if (bean1 != null) {
                            if (bean1.getStatus() == 1) {
                                t = false;
                            } else {
                                venId = NumberUtil.parseInt(bean1.getNote(), 0);
                            }
                        } else {
                            t = false;
                        }
                    }
                }
                if (bean == null) {
                    bean = new VendorBean();
                }
                ContractDAO conDAO = new ContractDAO();
                ContractBean conBean = conDAO.getContract(Integer.parseInt(conId));
                if (conBean == null) {
                    conBean = new ContractBean();
                }
                if (bean1 != null) {
                    request.setAttribute(Constants.VENDOR, bean1);
                } else {
                    request.setAttribute(Constants.VENDOR, bean);
                }
                request.setAttribute(Constants.CONTRACT, conBean);
            } catch (Exception ex) {
                LogUtil.error("FAILED:Payment:vendorDetail-" + ex.getMessage());
                ex.printStackTrace();
            }
        }
        if (!GenericValidator.isBlankOrNull(request.getParameter("name"))) {
            this.actionForwardResult = "vendorName";
        }
        return true;
    }
}
