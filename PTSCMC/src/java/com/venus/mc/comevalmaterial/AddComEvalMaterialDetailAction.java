/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.comevalmaterial;

import com.venus.core.auth.OnlineUser;
import com.venus.core.util.LogUtil;
import com.venus.mc.bean.ComEvalMaterialBean;
import com.venus.mc.bean.ComEvalMaterialDetailBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.ComEvalMaterialDAO;
import com.venus.mc.dao.ComEvalMaterialDetailDAO;
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
public class AddComEvalMaterialDetailAction extends SpineAction {

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
        ComEvalMaterialDetailFormBean formBean = (ComEvalMaterialDetailFormBean) form;
        //       int detId = formBean.getDetId();
        //      int terId = formBean.getTerId();
        boolean bNew = false;
        //      if (detId==0) {
        //         bNew = true;
        //     } else {
        //         bNew = false;
        //     }

        ComEvalMaterialDetailBean bean;

        ComEvalMaterialDetailDAO techDAO = new ComEvalMaterialDetailDAO();
        ComEvalMaterialDAO materialDAO = new ComEvalMaterialDAO();
        ComEvalMaterialBean beanVendor;
        Double total = 0.0;


        try {
            if (bNew) {
                //        techDAO.insertComEvalDetail(bean);
            } else {
                HttpSession session = request.getSession();
                OnlineUser user = MCUtil.getOnlineUser(session);
                LogUtil.info("Username : " + user.getName() + " - Form : " + mapping.getPath() + " - EDIT");

                for (int i = 0; i < formBean.getPriceCertificate().length; i++) {
                    ArrayList mat = materialDAO.getComEvalMaterialDetailOk(formBean.getDetIdTp()[i], formBean.getCemId());
                    ComEvalMaterialDetailBean b = null;
                    for (int j = 0; j < mat.size(); j++) {
                        b = (ComEvalMaterialDetailBean) mat.get(j);
                        b.setTenId(formBean.getTenId());
                        b.setMatId(formBean.getMatId()[i]);
                        b.setPrice(formBean.getPrice()[i]);
                        b.setPriceCustom(formBean.getPriceCustom()[i]);
                        b.setPriceTransport(formBean.getPriceTransport()[i]);
                        b.setPriceTax(formBean.getPriceTax()[i]);
                        b.setPriceOtherCost(formBean.getPriceOtherCost()[i]);
                        b.setPriceCertificate(formBean.getPriceCertificate()[i]);
                        b.setPriceToPort(formBean.getPriceToPort()[i]);
                        b.setPriceAfter(formBean.getPriceAfter()[i]);
                        b.setPricePtscmc(formBean.getPricePtscmc()[i]);
                        double t = b.getQuantity() * b.getPriceAfter();
                        b.setTotal(String.valueOf(t));
                        techDAO.updateComEvalMaterialDetail(b);
                    }
//                    bean = new ComEvalMaterialDetailBean();
//                    bean.setDetId(formBean.getComDetId()[i]);
//                    bean.setDetIdTp(formBean.getDetIdTp()[i]);
//                    bean.setTenId(formBean.getTenId());
//                    bean.setCemId(formBean.getCemId());
//                    bean.setMatId(formBean.getMatId()[i]);
//                    bean.setPrice(formBean.getPrice()[i]);
//                    bean.setPriceCustom(formBean.getPriceCustom()[i]);
//                    bean.setPriceTransport(formBean.getPriceTransport()[i]);
//                    bean.setPriceTax(formBean.getPriceTax()[i]);
//                    bean.setPriceOtherCost(formBean.getPriceOtherCost()[i]);
//                    bean.setPriceAfter(formBean.getPriceAfter()[i]);
//                    bean.setPricePtscmc(formBean.getPricePtscmc()[i]);
//                    double t = formBean.getPriceAfter()[i] * formBean.getQuantity()[i];
//                    bean.setTotal(String.valueOf(t));
//                    bean.setTotal(String.valueOf(formBean.getTotal()[i]));
//                    bean.setUnit(formBean.getUnit()[i]);
//                    bean.setQuantity(formBean.getQuantity()[i]);
//                    techDAO.updateComEvalMaterialDetail(bean);
                    total = total + formBean.getTotal()[i];
                }
                

                beanVendor = new ComEvalMaterialBean();

                beanVendor.setCemId(formBean.getCemId());
                beanVendor.setCurrency(formBean.getCurrency());
                beanVendor.setPaymentMethod(formBean.getPaymentMethod());
                beanVendor.setGuaranteeContract(formBean.getGuaranteeContract());
                beanVendor.setRates(formBean.getRates());
                beanVendor.setRatesAfter(String.valueOf(total));

                materialDAO.updateComEvalMaterialVendorForComEval(beanVendor);
                materialDAO.updateTenderEvaluateVendorIncoterm(formBean.getTenId(), formBean.getVenId(), formBean.getIncoterm());

                TenderPlanDAO tenderDAO = new TenderPlanDAO();
                tenderDAO.deleteBidEvalSum(formBean.getTenId() + "");
                tenderDAO.deletePriceComparision(formBean.getTenId() + "");
                
                techDAO.updateComEvalMaterialDetailResult(formBean.getTenId());
            }

        } catch (Exception ex) {
            LogUtil.error("FAILED:Vender:add-" + ex.getMessage());
            ex.printStackTrace();
        }

        return true;
    }
}
