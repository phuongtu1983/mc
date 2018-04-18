/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.process.equipment.mc;

import com.venus.core.util.LogUtil;
import com.venus.mc.bean.McBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.McDAO;
import com.venus.mc.util.Constants;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import java.util.ArrayList;

/**
 *
 * @author kngo
 */
public class SearchAdvMcAction extends SpineAction {

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
        SearchAdvMcFormBean formBean = (SearchAdvMcFormBean) form;
        McBean bean = new McBean();

        bean.setMcNumber(formBean.getMcNumber());        
        bean.setOrgId(formBean.getOrgId());
        bean.setKind(formBean.getKind());
        bean.setCarryOnDate(formBean.getCarryOnDate());
        bean.setCarryOnHour(formBean.getCarryOnHour());
        bean.setCarryOnMinute(formBean.getCarryOnMinute());
        bean.setExplanation(formBean.getExplanation());        
        bean.setDescCarryOn(formBean.getDescCarryOn());
        bean.setDescNotCarryOn(formBean.getDescNotCarryOn());
        bean.setStatus(formBean.getStatus());
        bean.setResult(formBean.getResult());
        
        ArrayList mcList = null;
        McDAO mcDAO = new McDAO();
        try {
            mcList = mcDAO.searchAdvMc(bean);
        } catch (Exception ex) {
            LogUtil.error("FAILED: searchAdvMc-" + ex.getMessage());
            ex.printStackTrace();
        }
        request.setAttribute(Constants.MC_LIST, mcList);
        return true;
    }
}
