/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.invoice;

import com.venus.mc.bean.ContractBean;
import com.venus.mc.contract.ContractFormBean;
import com.venus.mc.contract.InvoiceContractFormBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.ContractDAO;
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
public class InvoiceFormAction extends SpineAction {

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
        InvoiceContractFormBean formBean = null;
        String invId = request.getParameter("invId");
        Integer id = (Integer) session.getAttribute("id");
        if (id != null) {
            invId = id + "";
        }
        session.removeAttribute("id");
        ContractDAO contractDAO = new ContractDAO();
        contractDAO.setInvolvedEmps(PermissionUtil.getEmployeesHasPermission(request));
        ArrayList contractList = null;
        try {
            contractDAO.setRequestEmp(MCUtil.getMemberID(request.getSession()));
            contractList = contractDAO.getContractsForInvoice(0, "");
        } catch (Exception ex) {
            contractList = new ArrayList();
        }
        if (contractList == null) {
            contractList = new ArrayList();
        }
        ContractFormBean contract = new ContractFormBean();
        contract.setConId(0);
        contract.setContractNumber("");
        contractList.add(0, contract);
        request.setAttribute(Constants.CONTRACT_LIST, contractList);

        if (!GenericValidator.isBlankOrNull(invId)) {
            try {
                formBean = contractDAO.getInvoice(invId);
            } catch (Exception ex) {
            }
        }

        if (formBean == null) {
            formBean = new InvoiceContractFormBean();
            formBean.setRates(1.0);

        } else {
            try {
                ContractDAO conDAO = new ContractDAO();
                ContractBean bean = conDAO.getContract(formBean.getConId());
                if (bean != null) {
                    if (bean.getKind() == ContractBean.KIND_APPENDIX) {
                        formBean.setContractKind(MCUtil.getBundleString("message.appendix.number"));
                    } else if (bean.getKind() == ContractBean.KIND_CONTRACT) {
                        formBean.setContractKind(MCUtil.getBundleString("message.asset.contractNumber"));
                    } else if (bean.getKind() == ContractBean.KIND_DELIVERY_REQUEST) {
                        formBean.setContractKind(MCUtil.getBundleString("message.deliveryrequest.number"));
                    } else if (bean.getKind() == ContractBean.KIND_ORDER) {
                        formBean.setContractKind(MCUtil.getBundleString("message.order.number"));
                    } else if (bean.getKind() == ContractBean.KIND_PRINCIPLE) {
                        formBean.setContractKind(MCUtil.getBundleString("message.contract.principle.number"));
                    }
                }
            } catch (Exception ex) {
            }

        }
        request.setAttribute(Constants.INVOICE, formBean);

        ArrayList detailList = null;
        try {
            detailList = contractDAO.getInvoiceDetails(formBean.getIcId());
        } catch (Exception ex) {
        }
        if (detailList == null) {
            detailList = new ArrayList();
        }
        request.setAttribute(Constants.CONTRACT_DETAIL_LIST, detailList);

        ArrayList arrStatus = new ArrayList();
        LabelValueBean value;
        value = new LabelValueBean();
        value.setLabel(MCUtil.getBundleString("message.contract.bill.status.notpayment"));
        value.setValue(InvoiceContractFormBean.STATUS_NOTPAYMENT + "");
        arrStatus.add(value);
        value = new LabelValueBean();
        value.setLabel(MCUtil.getBundleString("message.contract.bill.status.paymented"));
        value.setValue(InvoiceContractFormBean.STATUS_PAYMENTED + "");
        arrStatus.add(value);
        value = new LabelValueBean();
        value.setLabel(MCUtil.getBundleString("message.contract.bill.status.accountTransdered"));
        value.setValue(InvoiceContractFormBean.STATUS_ACCOUNT_TRANSFERED + "");
        arrStatus.add(value);
        request.setAttribute(Constants.STATUS_LIST, arrStatus);

        ArrayList currency = MCUtil.getArrayCurrency();
        request.setAttribute(Constants.CURRENCY_LIST, currency);

        if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_EDIT, PermissionUtil.PER_PURCHASING_INVOICE, MCUtil.getMemberID(request.getSession()))) {
            if (formBean.getIcId() == 0) {
                request.setAttribute(Constants.CAN_SAVE, "true");
            } else {
                try {
                    if (!contractDAO.isInvoiceHasPayment(formBean.getIcId())) {
                        request.setAttribute(Constants.CAN_SAVE, "true");
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
        return PermissionUtil.PER_PURCHASING_INTERMEMO;
    }
}
