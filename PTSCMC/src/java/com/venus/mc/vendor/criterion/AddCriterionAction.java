/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.vendor.criterion;

import com.venus.core.auth.OnlineUser;
import com.venus.core.util.LogUtil;
import com.venus.mc.bean.EvaluateCriterionBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.VendorDAO;
import com.venus.mc.util.MCUtil;
import com.venus.mc.util.PermissionUtil;
import com.venus.mc.vendor.evaluate.EvaluateCriterionFormBean;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author phuongtu
 */
public class AddCriterionAction extends SpineAction {

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
        EvaluateCriterionFormBean formBean = (EvaluateCriterionFormBean) form;
        int criId = formBean.getCriId();
        boolean bNew = false;
        if (criId == 0) {
            bNew = true;
        } else {
            bNew = false;
        }
        HttpSession session = request.getSession();
        EvaluateCriterionBean bean = new EvaluateCriterionBean();
        bean.setCriId(criId);
        bean.setName(formBean.getName());
        bean.setReach(formBean.getReach());
        bean.setNotReach(formBean.getNotReach());
        bean.setComments(formBean.getComments());
        VendorDAO vendorDAO = new VendorDAO();
        try {
            if (bNew) {
                vendorDAO.insertEvaluateCriterion(bean);
            } else {
                OnlineUser user = MCUtil.getOnlineUser(session);
                LogUtil.info("Username : " + user.getName() + " - Form : " + mapping.getPath() + " - EDIT");
                vendorDAO.updateEvaluateCriterion(bean);
            }
        } catch (Exception ex) {
            LogUtil.error("FAILED:EvalCriterion:add-" + ex.getMessage());
            ex.printStackTrace();
        }
        return true;
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
