/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.payment;

import com.venus.core.auth.OnlineUser;
import com.venus.core.util.GenericValidator;
import com.venus.core.util.LogUtil;
import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.PaymentBean;
import com.venus.mc.bean.PaymentDetailBean;
import com.venus.mc.contract.InvoiceContractFormBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.ContractDAO;
import com.venus.mc.util.MCUtil;
import com.venus.mc.util.PermissionUtil;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

/**
 *
 * @author phuongtu
 */
public class AddPaymentAction extends SpineAction {

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
        PaymentFormBean formBean = (PaymentFormBean) form;
        ContractDAO contractDAO = new ContractDAO();
        PaymentBean bean = null;
        boolean bNew = false;
        boolean isExist = false;
        try {
            bean = contractDAO.getPaymentByNumber(formBean.getPayNumber());
        } catch (Exception ex) {
        }
        int payId = formBean.getPayId();
        if (payId == 0) {
            bNew = true;
            if (bean != null) {
                isExist = true;
            }
        } else {
            bNew = false;
            if (bean != null && bean.getPayId() != payId) {
                isExist = true;
            }
        }
        if (isExist) {
            ActionMessages errors = new ActionMessages();
            errors.add("paymentExisted", new ActionMessage("errors.payment.existed"));
            saveErrors(request, errors);
            return false;
        }
        bean = new PaymentBean();
        bean.setPayId(formBean.getPayId());
        bean.setPayNumber(formBean.getPayNumber());
        bean.setPayDate(formBean.getPayDate());
        bean.setTotal(formBean.getTotal());
        bean.setDocument(formBean.getDocument());
        bean.setNote(formBean.getNote());
        bean.setMoveAccountingDate(formBean.getMoveAccountingDate());
        bean.setStatus(formBean.getStatus());
        bean.setVolume(formBean.getVolume());
        bean.setLocation(formBean.getLocation());
        bean.setHandleEmp(formBean.getHandleEmp());
        bean.setTotalPay(formBean.getTotalPay());
        bean.setPunish(formBean.getPunish());
        bean.setRates(formBean.getRates());
        try {
            if (bNew) {
                bean.setCreatedEmp(MCUtil.getMemberID(request.getSession()));
                payId = contractDAO.insertPayment(bean);
            } else {
                OnlineUser user = MCUtil.getOnlineUser(session);
                LogUtil.info("Username : " + user.getName() + " - Form : " + mapping.getPath() + " - EDIT");
                contractDAO.updatePayment(bean);
            }
            session.setAttribute("id", payId);
            if (payId != 0) {
                formBean.setPayId(payId);
//                updateContractInvoice(formBean);
                addDetail(formBean);
            }
        } catch (Exception ex) {
            LogUtil.error("FAILED:Payment:add-" + ex.getMessage());
            ex.printStackTrace();
        }
        return true;
    }

    private void updateContractInvoice(PaymentFormBean formBean) {
        if (formBean.getPayBillId() != null) {
            ContractDAO contractDAO = new ContractDAO();
            String[] detIds = formBean.getPayBillId();
            int icId = 0;
            for (int i = 0; i < detIds.length; i++) {
                try {
                    icId = NumberUtil.parseInt(formBean.getPayBillId()[i], 0);
                    contractDAO.updateInvoiceContractForPayment(icId, formBean.getPayId());
//                    if (formBean.getStatus() == PaymentBean.STATUS_PAID) {
//                        contractDAO.updateInvoiceContractStatusForPayment(icId, InvoiceContractFormBean.STATUS_PAYMENTED);
                    InvoiceContractFormBean b = contractDAO.getInvoice(icId + "");
                    if (b != null && b.getStatus() == InvoiceContractFormBean.STATUS_NOTPAYMENT && !GenericValidator.isBlankOrNull(formBean.getMoveAccountingDate())) {
                        contractDAO.updateInvoiceContractStatusForPayment(icId, InvoiceContractFormBean.STATUS_ACCOUNT_TRANSFERED);
                    }
//                    }
                } catch (Exception ex) {
                }
            }
        }
    }

    private void addDetail(PaymentFormBean formBean) {
        ContractDAO contractDAO = new ContractDAO();
        ArrayList arrDet = null;
        try {
            arrDet = contractDAO.getPaymentDetails(formBean.getPayId());
        } catch (Exception ex) {
        }
        if (arrDet == null) {
            arrDet = new ArrayList();
        }
        String[] detIds = formBean.getPayBillId();
        PaymentDetailBean detBean = null;
        double dou = 0;
        boolean canUpdate = false;
        if (detIds != null) {
            for (int i = 0; i < detIds.length; i++) {
                canUpdate = false;
                if (!detIds[i].equals("0")) {//old
                    try {
                        detBean = detExisted(arrDet, Integer.parseInt(detIds[i]));
                        if (detBean != null) {
                            if (formBean.getAmount() != null) {
                                dou = Double.parseDouble(formBean.getAmount()[i]);
                                if (dou != detBean.getAmount()) {
                                    detBean.setAmount(dou);
                                    canUpdate = true;
                                }
                            }
                            if (canUpdate) {
                                contractDAO.updatePaymentDetail(detBean);
                            }
                        }
                    } catch (Exception ex) {
                    }
                } else {//new
                    try {
                        double amount = 0;
                        if (formBean.getAmount() != null) {
                            if (GenericValidator.isBlankOrNull(formBean.getAmount()[i])) {
                                amount = 0;
                            } else {
                                amount = NumberUtil.parseDouble(formBean.getAmount()[i], 0);
                            }
                        }
                        detBean = new PaymentDetailBean();
                        detBean.setPayId(formBean.getPayId());
                        detBean.setConId(NumberUtil.parseInt(formBean.getPayConId()[i], 0));
                        detBean.setInvId(NumberUtil.parseInt(formBean.getInvId()[i], 0));
                        detBean.setAmount(amount);
                        contractDAO.insertPaymentDetail(detBean);
                    } catch (Exception ex) {
                    }
                }
            }
        }

    }

    private PaymentDetailBean detExisted(ArrayList arrDet, int detId) {
        PaymentDetailBean oldBean = null;
        for (int i = 0; i < arrDet.size(); i++) {
            oldBean = (PaymentDetailBean) arrDet.get(i);
            if (oldBean.getDetId() == detId) {
                arrDet.remove(oldBean);
                PaymentDetailBean bean = new PaymentDetailBean();
                bean.setDetId(oldBean.getDetId());
                bean.setPayId(oldBean.getPayId());
                bean.setConId(oldBean.getConId());
                bean.setInvId(oldBean.getInvId());
                bean.setAmount(oldBean.getAmount());
                return bean;
            }
        }
        return null;
    }

    @Override
    protected String getFunction() {
        return PermissionUtil.FUNC_EDIT + "";
    }

    @Override
    protected int getPermit() {
        return PermissionUtil.PER_PURCHASING_PAYMENT;
    }
}
