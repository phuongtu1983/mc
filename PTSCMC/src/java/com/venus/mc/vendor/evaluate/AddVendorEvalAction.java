/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.vendor.evaluate;

import com.venus.core.auth.OnlineUser;
import com.venus.core.util.GenericValidator;
import com.venus.core.util.LogUtil;
import com.venus.mc.bean.VendorEvaluateBean;
import com.venus.mc.bean.VendorEvalDetailBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.VendorDAO;
import com.venus.mc.util.MCUtil;
import com.venus.mc.util.PermissionUtil;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author phuongtu
 */
public class AddVendorEvalAction extends SpineAction {

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
        String venId = request.getParameter("venId");
        if (GenericValidator.isBlankOrNull(venId)) {
            return false;
        }
        VendorEvalFormBean formBean = (VendorEvalFormBean) form;
        int evalId = formBean.getEvalId();
        boolean bNew = false;
        if (evalId == 0) {
            bNew = true;
        } else {
            bNew = false;
        }
        HttpSession session = request.getSession();
        VendorEvaluateBean bean = new VendorEvaluateBean();
        bean.setEvalId(formBean.getEvalId());
        bean.setEmpId(formBean.getEmpId());
        bean.setOrgId(formBean.getOrgId());
        bean.setVenId(Integer.parseInt(venId));
        bean.setLastResult(formBean.getLastResult());
        bean.setFromDate(formBean.getFromDate());
        bean.setToDate(formBean.getToDate());
        bean.setEvalNumber(formBean.getEvalNumber());
        VendorDAO vendorDAO = new VendorDAO();
        try {
            ArrayList arrDetEval = null;
            if (bNew) {
                evalId = vendorDAO.insertVendorEval(bean);
                arrDetEval = vendorDAO.getEvalCrisDefault();
            } else {
                OnlineUser user = MCUtil.getOnlineUser(session);
                LogUtil.info("Username : " + user.getName() + " - Form : " + mapping.getPath() + " - EDIT");
                vendorDAO.updateVendorEval(bean);
                evalId = bean.getEvalId();
                arrDetEval = vendorDAO.getEvaluteDetail(evalId);
            }
            if (evalId != 0) {
                VendorEvalDetailBean detEval = null;
                for (int i = 0; i < arrDetEval.size(); i++) {
                    detEval = (VendorEvalDetailBean) arrDetEval.get(i);
                    detEval.setNote(formBean.getDetNotes()[i]);
                    detEval.setEvalResult(getEvalDetResult(formBean.getDetResults(), i));
                    if (detEval.getDetid() == 0) {
                        detEval.setEvalid(evalId);
                        vendorDAO.insertEvalDetail(detEval);
                    } else {
                        vendorDAO.updateEvalDetail(detEval);
                    }
                }
            }
        } catch (Exception ex) {
            LogUtil.error("FAILED:VenderEval:add-" + ex.getMessage());
            ex.printStackTrace();
        }
        return true;
    }

//    private int getEvalDetResult(int[] results, int criId) {
//        for (int i = 0; i < results.length; i++) {
//            if ((int) results[i] == criId) {
//                return 1;
//            }
//        }
//        return 0;
//    }
    private int getEvalDetResult(int[] results, int ind) {
        return (int) results[ind];
//        for (int i = 0; i < results.length; i++) {
//            if ((int) results[i] == criId) {
//                return 1;
//            }
//        }
//        return 0;
    }
    
        @Override
    protected String getFunction() {
        return PermissionUtil.FUNC_EDIT + "";
    }

    @Override
    protected int getPermit() {
        return PermissionUtil.PER_LIBRARY_VENDOR_EVAL;
    }
}
