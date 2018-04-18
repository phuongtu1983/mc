/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.comeval;

import com.venus.core.auth.OnlineUser;
import com.venus.core.util.LogUtil;
import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.ComEvalDetailBean;
import com.venus.mc.bean.ComEvalVendorBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.ComEvalDetailDAO;
import com.venus.mc.dao.ComEvalMaterialDAO;
import com.venus.mc.dao.ComEvalVendorDAO;
import com.venus.mc.dao.TenderPlanDAO;
import com.venus.mc.util.MCUtil;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author mai vinh loc
 */
public class AddComEvalDetailAction extends SpineAction {

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
        ComEvalDetailFormBean formBean = (ComEvalDetailFormBean) form;
        //       int detId = formBean.getDetId();
        int terId = formBean.getTerId();
        boolean bNew = false;
        //      if (detId==0) {
        //         bNew = true;
        //     } else {
        //         bNew = false;
        //     }

        ComEvalDetailBean bean;

        ComEvalDetailDAO comEvalDetailDAO = new ComEvalDetailDAO();
        ComEvalVendorDAO techVendorDAO = new ComEvalVendorDAO();
        ComEvalMaterialDAO materialDAO = new ComEvalMaterialDAO();
        ComEvalVendorBean beanVendor;
        Double total = 0.0;

        try {
            if (bNew) {
                //        techDAO.insertComEvalDetail(bean);
            } else {
                HttpSession session = request.getSession();
                OnlineUser user = MCUtil.getOnlineUser(session);
                LogUtil.info("Username : " + user.getName() + " - Form : " + mapping.getPath() + " - EDIT");

                for (int i = 0; i < formBean.getUnit().length; i++) {
                    ArrayList mat = comEvalDetailDAO.getComEvalDetailOk(formBean.getComDetId()[i]);
                    ComEvalDetailBean b = null;
                    for (int j = 0; j < mat.size(); j++) {
                        b = (ComEvalDetailBean) mat.get(j);
                        b.setTenId(formBean.getTenId());
                        b.setMatId(formBean.getMatId()[i]);
                        b.setPrice(String.valueOf(NumberUtil.reformatMoneyDefault(formBean.getPrice()[i], formBean.getCurrency())));
                        b.setPriceAfter(String.valueOf(NumberUtil.reformatMoneyDefault(formBean.getPriceAfter()[i], formBean.getCurrency())));
                        double t = b.getQuantity() * formBean.getPriceAfter()[i];
                        b.setTotal(String.valueOf(NumberUtil.reformatMoneyDefault(t, formBean.getCurrency())));
                        comEvalDetailDAO.updateComEvalDetail(b);
                    }
//                    bean = new ComEvalDetailBean();
//                    bean.setDetId(formBean.getComDetId()[i]);
//                    bean.setCevId(formBean.getCevId());
//                    bean.setMatId(formBean.getMatId()[i]);
//                    bean.setUnit(formBean.getUnit()[i]);
//                    bean.setQuantity(formBean.getQuantity()[i]);
//                    bean.setPrice(String.valueOf(formBean.getPrice()[i]));
//                    bean.setPriceAfter(String.valueOf(formBean.getPriceAfter()[i]));
//                    bean.setTotal(String.valueOf(formBean.getTotal()[i]));

//                    comEvalDetailDAO.updateComEvalDetail(bean);

                    total = total + formBean.getTotal()[i];

                }

                beanVendor = new ComEvalVendorBean();
                beanVendor.setCeId(formBean.getCeId());
                beanVendor.setCevId(formBean.getCevId());
                beanVendor.setCurrency(formBean.getCurrency());
                beanVendor.setCustomTax(formBean.getCustomTax());
                beanVendor.setCostTransport(Double.parseDouble(NumberUtil.reformatMoneyDefault(formBean.getCostTransport(), formBean.getCurrency())));
                beanVendor.setOtherTax(Double.parseDouble(NumberUtil.reformatMoneyDefault(formBean.getOtherTax(), formBean.getCurrency())));
                beanVendor.setOtherCost(Double.parseDouble(NumberUtil.reformatMoneyDefault(formBean.getOtherTax(), formBean.getCurrency())));
                beanVendor.setPriceCertificate(Double.parseDouble(NumberUtil.reformatMoneyDefault(formBean.getPriceCertificate(), formBean.getCurrency())));
                beanVendor.setPriceToPort(Double.parseDouble(NumberUtil.reformatMoneyDefault(formBean.getPriceToPort(), formBean.getCurrency())));
                beanVendor.setSum(NumberUtil.reformatMoneyDefault((formBean.getCustomTax() + formBean.getCostTransport() + formBean.getOtherTax() + formBean.getOtherCost() + formBean.getPriceCertificate() + formBean.getPriceToPort()) * formBean.getRates() + total, formBean.getCurrency()));
                beanVendor.setRand(formBean.getRand());
                beanVendor.setRates(formBean.getRates());
                beanVendor.setRatesAfter(total);
                beanVendor.setPaymentMethod(formBean.getPaymentMethod());
                techVendorDAO.updateComEvalVendorForComEval(beanVendor);

                materialDAO.updateTenderEvaluateVendorIncoterm(formBean.getTenId(), formBean.getVenId(), formBean.getIncoterm());
                
                TenderPlanDAO tenderDAO = new TenderPlanDAO();
                tenderDAO.deleteBidEvalSum(formBean.getTenId() + "");
                tenderDAO.deletePriceComparision(formBean.getTenId() + "");
            }

        } catch (Exception ex) {
            LogUtil.error("FAILED:Vender:add-" + ex.getMessage());
            ex.printStackTrace();
        }

        return true;
    }
}
