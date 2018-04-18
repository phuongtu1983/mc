/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.techeval;

import com.venus.core.auth.OnlineUser;
import com.venus.core.util.LogUtil;
import com.venus.core.util.NumberUtil;
import com.venus.core.util.StringUtil;
import com.venus.mc.bean.TechEvalDetailBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.TechEvalDetailDAO;
import com.venus.mc.dao.TechEvalVendorDAO;
import com.venus.mc.dao.TenderPlanDAO;
import com.venus.mc.util.MCUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author mai vinh loc
 */
public class AddTechEvalDetailAction extends SpineAction {

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
        TechEvalDetailFormBean formBean = (TechEvalDetailFormBean) form;
        int matId = NumberUtil.parseInt(request.getParameter("matId"), 0);
        int detId = NumberUtil.parseInt(request.getParameter("detId"), 0);
        if (matId > 0) {
            TechEvalDetailDAO techDAO = new TechEvalDetailDAO();
            try {
                techDAO.updateTechEvalDetailMatId(matId, detId);
            } catch (Exception ex) {
                LogUtil.error("FAILED:TechEval:add-" + ex.getMessage());
                ex.printStackTrace();
            }
        } else {
            //       int detId = formBean.getDetId();
            int terId = formBean.getTerId();
            boolean bNew = false;
            //      if (detId==0) {
            //         bNew = true;
            //     } else {
            //         bNew = false;
            //     }

            TechEvalDetailBean bean;

            TechEvalDetailDAO techDAO = new TechEvalDetailDAO();
            TechEvalVendorDAO techVendorDAO = new TechEvalVendorDAO();
            TenderPlanDAO tenderDAO = new TenderPlanDAO();
            int result = 0;
            for (int i = 0; i < formBean.getUnit().length; i++) {
                try {
                    if (bNew) {
                        //        techDAO.insertTechEvalDetail(bean);
                    } else {
                        HttpSession session = request.getSession();
                        OnlineUser user = MCUtil.getOnlineUser(session);
                        LogUtil.info("Username : " + user.getName() + " - Form : " + mapping.getPath() + " - EDIT");

                        bean = new TechEvalDetailBean();
                        bean.setDetId(formBean.getDetId()[i]);
                        bean.setSpec(StringUtil.encodeHTML(formBean.getSpec()[i]));
                        bean.setQty(formBean.getQty()[i]);
                        bean.setUnit(formBean.getUnit()[i]);
                        if (formBean.getOtherCondition() != null) {
                            bean.setOtherCondition(formBean.getOtherCondition()[i]);
                        }
                        bean.setDeliveryTime(formBean.getDeliveryTime()[i]);
                        if (formBean.getEvalTbe() != null) {
                            bean.setEvalTbe(formBean.getEvalTbe()[i]);
                            //result = 3;
                        }
                        bean.setTerId(formBean.getTerId());
                        bean.setPropose("");
                        bean.setOffer("");
                        //bean.setResult(formBean.getResult());
                        //result = 5; //Da danh gia
                        if (formBean.getResult() == null) {
                            formBean.setResult("5");
                        }
                        techDAO.updateTechEvalDetail(bean);
                        
                        if (NumberUtil.parseInt(formBean.getResult(), 0) > 1) {
                            tenderDAO.deleteBidEvalSum(formBean.getTenId() + "");
                            tenderDAO.deletePriceComparision(formBean.getTenId() + "");
                            tenderDAO.deleteVendorDetail(formBean.getTenId() + "", formBean.getTerId() + "");
                            tenderDAO.deleteVendor(formBean.getTenId() + "", formBean.getTerId() + "");
                            tenderDAO.deleteVendorMaterialDetail(formBean.getTenId() + "", formBean.getTerId() + "");
                            tenderDAO.deleteVendorMaterial(formBean.getTenId() + "", formBean.getTerId() + "");
                        }
                    }

                } catch (Exception ex) {
                    LogUtil.error("FAILED:Vender:add-" + ex.getMessage());
                    ex.printStackTrace();
                }
            }
            try {
                if (formBean.getCertificateAttach() != null) {
                    techVendorDAO.updateTechEvalVendorForTechEval(formBean.getResult(), formBean.getCertificateAttach(), terId, formBean.getTenId());
                } else {
                    techVendorDAO.updateTechEvalVendorForTechEval(formBean.getResult(), "", terId, formBean.getTenId());
                }
            } catch (Exception ex) {
                LogUtil.error("FAILED:Vender:add-" + ex.getMessage());
                ex.printStackTrace();
            }
        }
        return true;
    }
}
