/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.invoice;

import com.venus.core.auth.OnlineUser;
import com.venus.core.util.GenericValidator;
import com.venus.core.util.LogUtil;
import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.InvoiceContractBean;
import com.venus.mc.bean.InvoiceContractDetailBean;
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
public class AddInvoiceAction extends SpineAction {

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
        InvoiceFormBean formBean = (InvoiceFormBean) form;
        ContractDAO contractDAO = new ContractDAO();
        InvoiceContractBean bean = null;
        boolean bNew = false;
        boolean isExist = false;
        try {
//            bean = contractDAO.getInvoiceByNumber(formBean.getInvoice());
        } catch (Exception ex) {
        }
        int icId = formBean.getIcId();
        if (icId == 0) {
            bNew = true;
            if (bean != null) {
                isExist = true;
            }
        } else {
            bNew = false;
            if (bean != null && bean.getIcId() != icId) {
                isExist = true;
            }
        }
        if (isExist) {
            ActionMessages errors = new ActionMessages();
            errors.add("invoiceExisted", new ActionMessage("errors.contract.existed"));
            saveErrors(request, errors);
            return false;
        }
        bean = new InvoiceContractBean();
        bean.setIcId(formBean.getIcId());
        bean.setConId(formBean.getConId());
        bean.setPayId(formBean.getPayId());
        bean.setInvoice(formBean.getInvoice());
        bean.setInvoiceDate(formBean.getInvoiceDate());
        bean.setPaymentDate(formBean.getPaymentDate());
        bean.setKind(formBean.getKind());
        bean.setNote(formBean.getNote());
        bean.setStatus(formBean.getStatus());
        bean.setContractPaymentDate(formBean.getContractPaymentDate());
        bean.setReceiveDate(formBean.getReceiveDate());
        bean.setAmount(formBean.getAmount());
        bean.setSumVAT(formBean.getSumVAT());
        bean.setOtherAmount(formBean.getOtherAmount());
        bean.setTransportAmount(formBean.getTransportAmount());
        bean.setRates(formBean.getRates());
        try {
            if (bNew) {
                bean.setCreatedEmp(MCUtil.getMemberID(request.getSession()));
                icId = contractDAO.insertInvoiceContract(bean);
            } else {
                OnlineUser user = MCUtil.getOnlineUser(session);
                LogUtil.info("Username : " + user.getName() + " - Form : " + mapping.getPath() + " - EDIT");
                contractDAO.updateInvoiceContract(bean);
            }
            session.setAttribute("id", icId);
            if (icId != 0) {
                formBean.setIcId(icId);
                addDetail(formBean);
            }
        } catch (Exception ex) {
            LogUtil.error("FAILED:Invoice:add-" + ex.getMessage());
            ex.printStackTrace();
        }
        return true;
    }

    private InvoiceContractDetailBean detExisted(ArrayList arrDet, int detId) {
        InvoiceContractDetailBean formBean = null;
        for (int i = 0; i < arrDet.size(); i++) {
            formBean = (InvoiceContractDetailBean) arrDet.get(i);
            if (formBean.getDetId() == detId) {
                arrDet.remove(formBean);
                InvoiceContractDetailBean bean = new InvoiceContractDetailBean(detId);
                bean.setQuantity(formBean.getQuantity());
                bean.setVat(formBean.getVat());
                bean.setPrice(formBean.getPrice());
                bean.setTotal(formBean.getTotal());
                return bean;
            }
        }
        return null;
    }

    private void addDetail(InvoiceFormBean formBean) {
        if (formBean.getConDetId() != null) {
            ContractDAO contractDAO = new ContractDAO();
            ArrayList arrDet = null;
            try {
                arrDet = contractDAO.getInvoiceDetails(formBean.getIcId());
            } catch (Exception ex) {
            }
            if (arrDet == null) {
                arrDet = new ArrayList();
            }
            String[] detIds = formBean.getConDetId();
            String[] matIds = formBean.getMatId();
            InvoiceContractDetailBean detBean = null;
            double number = 0;
            double dou = 0;
            boolean canUpdate = false;
            for (int i = 0; i < detIds.length; i++) {
                canUpdate = false;
                if (!detIds[i].equals("0")) {//old
                    try {
                        detBean = detExisted(arrDet, Integer.parseInt(detIds[i]));
                        if (detBean != null) {
                            number = Double.parseDouble(formBean.getQuantity()[i]);
                            if (number != detBean.getQuantity()) {
                                detBean.setQuantity(number);
                                canUpdate = true;
                            }
                            dou = Double.parseDouble(formBean.getDetTotal()[i]);
                            if (dou != detBean.getTotal()) {
                                detBean.setTotal(dou);
                                canUpdate = true;
                            }
                            if (canUpdate) {
                                contractDAO.updateInvoiceDetail(detBean);
                            }
                        }
                    } catch (Exception ex) {
                    }
                } else {//new
                    try {
                        double quantity = 0;
                        double price = 0;
                        double total = 0;
                        double vat = 0;
                        if (GenericValidator.isBlankOrNull(formBean.getQuantity()[i])) {
                            quantity = 0;
                        } else {
                            quantity = Double.parseDouble(formBean.getQuantity()[i]);
                        }
                        if (GenericValidator.isBlankOrNull(formBean.getDetTotal()[i])) {
                            total = 0;
                        } else {
                            total = Double.parseDouble(formBean.getDetTotal()[i]);
                        }
                        if (GenericValidator.isBlankOrNull(formBean.getPrice()[i])) {
                            price = 0;
                        } else {
                            price = Double.parseDouble(formBean.getPrice()[i]);
                        }

                        if (GenericValidator.isBlankOrNull(formBean.getVat()[i])) {
                            vat = 0;
                        } else {
                            vat = Double.parseDouble(formBean.getVat()[i]);
                        }
                        detBean = new InvoiceContractDetailBean(0, formBean.getIcId(), NumberUtil.parseInt(matIds[i], 0), formBean.getUnit()[i], quantity, price, total, "", vat, "", NumberUtil.parseInt(formBean.getReqDetId()[i], 0));
                        detBean.setMatId(Integer.parseInt(matIds[i]));
                        if (formBean.getReqDetId() != null) {
                            detBean.setReqDetailId(Integer.parseInt(formBean.getReqDetId()[i]));
                        }
                        contractDAO.insertInvoiceDetail(detBean);
                    } catch (Exception ex) {
                    }
                }
            }
        }
    }

    @Override
    protected String getFunction() {
        return PermissionUtil.FUNC_EDIT + "";
    }

    @Override
    protected int getPermit() {
        return PermissionUtil.PER_PURCHASING_INVOICE;
    }
}
