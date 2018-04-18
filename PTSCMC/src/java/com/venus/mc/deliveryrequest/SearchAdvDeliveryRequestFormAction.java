/*
 *
 * Created on April 14, 2007, 8:49 AM
 */
package com.venus.mc.deliveryrequest;

import com.venus.mc.bean.ContractBean;
import com.venus.mc.bean.VendorBean;
import com.venus.mc.contract.SearchAdvContractFormBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.VendorDAO;
import com.venus.mc.util.Constants;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import java.util.ArrayList;

/**
 *
 * @author phuongtu
 * @version
 */
public class SearchAdvDeliveryRequestFormAction extends SpineAction {

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
        SearchAdvContractFormBean formBean = new SearchAdvContractFormBean();
        formBean.setKind(ContractBean.KIND_DELIVERY_REQUEST);

        request.setAttribute(Constants.CONTRACT, formBean);
        ArrayList vendorList = null;
        try {
            VendorDAO vendorDAO = new VendorDAO();
            vendorList = vendorDAO.getVendors();
        } catch (Exception ex) {
        }
        if (vendorList == null) {
            vendorList = new ArrayList();
        }
        vendorList.add(0, new VendorBean(0));
        request.setAttribute(Constants.VENDOR_LIST, vendorList);
//        ArrayList arrStatus = new ArrayList();
//        LabelValueBean value;
//        value = new LabelValueBean();
//        value.setLabel(MCUtil.getBundleString("message.select"));
//        value.setValue("0");
//        arrStatus.add(value);
//        value = new LabelValueBean();
//        value.setLabel(MCUtil.getBundleString("message.contract.status.approved"));
//        value.setValue(ContractFormBean.STATUS_APPROVED + "");
//        arrStatus.add(value);
//        value = new LabelValueBean();
//        value.setLabel(MCUtil.getBundleString("message.contract.status.approving"));
//        value.setValue(ContractFormBean.STATUS_APPROVING + "");
//        arrStatus.add(value);
//        request.setAttribute(Constants.STATUS_LIST, arrStatus);
        return true;
    }
}
