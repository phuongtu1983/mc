/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.payment;

import com.venus.mc.bean.PaymentBean;
import com.venus.mc.bean.VendorBean;
import com.venus.mc.contract.ContractFormBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.ContractDAO;
import com.venus.mc.dao.EmployeeDAO;
import com.venus.mc.dao.OrganizationDAO;
import com.venus.mc.dao.VendorDAO;
import com.venus.mc.util.Constants;
import com.venus.mc.util.MCUtil;
import com.venus.mc.util.PermissionUtil;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.LabelValueBean;

/**
 *
 * @author phuongtu
 */
public class PaymentFormAction extends SpineAction {

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
        PaymentFormBean formBean = null;
        String payId = request.getParameter("payId");
        Integer id = (Integer) session.getAttribute("id");
        if (id != null) {
            payId = id + "";
        }
        session.removeAttribute("id");
        ContractDAO contractDAO = new ContractDAO();
        contractDAO.setInvolvedEmps(PermissionUtil.getEmployeesHasPermission(request));
        contractDAO.setRequestEmp(MCUtil.getMemberID(request.getSession()));
        ArrayList vendorList = null;
        try {
            VendorDAO venDAO = new VendorDAO();
            if (GenericValidator.isBlankOrNull(payId)) {
                venDAO.setRequestEmp(MCUtil.getMemberID(request.getSession()));
//            contractList = contractDAO.getContractsNotPaymentYetAndHasInvoice(0);
                vendorList = venDAO.getVendorsNotPaymentYetAndHasInvoice(0, "");
            } else {
                try {
                    formBean = contractDAO.getPayment(payId);
                } catch (Exception ex) {
                }
                vendorList = new ArrayList();
                VendorBean venBean = venDAO.getVendor(formBean.getVenId());
                if (venBean != null) {
                    vendorList.add(venBean);
                }
            }
        } catch (Exception ex) {
            vendorList = new ArrayList();
        }


        int venId = 0;
        if (formBean == null) {
            formBean = new PaymentFormBean();
            formBean.setRates(1);
            VendorBean vendor = new VendorBean();
            vendor.setVenId(0);
            vendor.setName("");
            vendorList.add(0, vendor);
        } else {
            venId = formBean.getVenId();
        }
        ArrayList contractList = null;
        if (venId > 0) {
            try {
                contractList = contractDAO.getContractsNotPaymentYetAndHasInvoice(0, venId);
            } catch (Exception ex) {
            }
        }
        if (contractList == null) {
            contractList = new ArrayList();
        } else {
            ContractFormBean contract = new ContractFormBean();
            contract.setConId(0);
            contract.setContractNumber("");
            contractList.add(0, contract);
        }
        request.setAttribute(Constants.CONTRACT_LIST, contractList);
        ArrayList arrInvoice = null;
        try {
//            arrInvoice = contractDAO.getInvoiceContractsByVendor(venId);
        } catch (Exception ex) {
        }
        if (arrInvoice == null) {
            arrInvoice = new ArrayList();
        }
        request.setAttribute(Constants.INVOICE_LIST, arrInvoice);

        ArrayList arrDetail = null;
        try {
//            arrDetail = contractDAO.getInvoiceContracts(conId, formBean.getPayId());
            arrDetail = contractDAO.getInvoiceContracts(0, formBean.getPayId());
        } catch (Exception ex) {
        }
        if (arrDetail == null) {
            arrDetail = new ArrayList();
        }
        request.setAttribute(Constants.CONTRACT_BILL_LIST, arrDetail);

        boolean canDelete = false;
        if (arrDetail.isEmpty() && formBean.getPayId() != 0) {
            try {
                VendorDAO venDAO = new VendorDAO();
                venDAO.setRequestEmp(MCUtil.getMemberID(request.getSession()));
                vendorList = venDAO.getVendorsNotPaymentYetAndHasInvoice(0, "");
                VendorBean vendor = new VendorBean();
                vendor.setVenId(0);
                vendor.setName("");
                vendorList.add(0, vendor);
                canDelete = true;
            } catch (Exception ex) {
            }
        }
        request.setAttribute(Constants.VENDOR_LIST, vendorList);
        request.setAttribute(Constants.PAYMENT, formBean);

        ArrayList arrStatus = new ArrayList();
        LabelValueBean value;
        value = new LabelValueBean();
        value.setLabel(MCUtil.getBundleString("message.payment.status.notpaid"));
        value.setValue(PaymentBean.STATUS_REQUESTED + "");
        arrStatus.add(value);
        value = new LabelValueBean();
        value.setLabel(MCUtil.getBundleString("message.payment.status.paid"));
        value.setValue(PaymentBean.STATUS_PAID + "");
        arrStatus.add(value);
        request.setAttribute(Constants.STATUS_LIST, arrStatus);

        ArrayList arrEmp = new ArrayList();
        try {
            String orgId = "";
            orgId = MCUtil.getOrganizationID(request.getSession()) + "";
            String orgs = "";
            try {
                OrganizationDAO orgDAO = new OrganizationDAO();
                orgs = orgDAO.getnestedChildOfOrg(orgId + "");
            } catch (Exception ex) {
            }
            orgs += "," + orgId;
            EmployeeDAO empDAO = new EmployeeDAO();
            empDAO.setRequestOrg(orgs);
            arrEmp = empDAO.getEmployees();
        } catch (Exception ex) {
        }
        if (arrEmp == null) {
            arrEmp = new ArrayList();
        }
        request.setAttribute(Constants.EMPLOYEE_LIST, arrEmp);
        if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_DELETE, PermissionUtil.PER_PURCHASING_PAYMENT, MCUtil.getMemberID(request.getSession()))) {
            if (formBean.getPayId() > 0) {
                try {
                    if (GenericValidator.isBlankOrNull(formBean.getMoveAccountingDate())) {
                        canDelete = true;
                    }
                    if (canDelete) {
                        request.setAttribute(Constants.CAN_DELETE, "true");
                    }
                } catch (Exception ex) {
                }
            }
        }
        return true;
    }

    @Override
    protected String getFunction() {
        return PermissionUtil.FUNC_VIEW + ";" + PermissionUtil.FUNC_EDIT;
    }

    @Override
    protected int getPermit() {
        return PermissionUtil.PER_PURCHASING_PAYMENT;
    }
}
