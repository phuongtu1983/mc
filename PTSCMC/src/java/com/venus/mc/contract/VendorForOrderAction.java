/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.contract;

import com.venus.core.util.GenericValidator;
import com.venus.mc.bean.VendorBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.ContractDAO;
import com.venus.mc.util.Constants;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author phuongtu
 */
public class VendorForOrderAction extends SpineAction {

    @Override
    public boolean doAction(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        VendorBean bean = null;
        if (!GenericValidator.isBlankOrNull(request.getParameter("conId"))) {
            try {
                ContractDAO contractDAO = new ContractDAO();
                bean = contractDAO.getVendorOfContract(Integer.parseInt(request.getParameter("conId")));
            } catch (Exception ex) {
            }
        }
        if (bean == null) {
            bean = new VendorBean();
        }
        request.setAttribute(Constants.VENDOR, bean);
        return true;
    }
}
