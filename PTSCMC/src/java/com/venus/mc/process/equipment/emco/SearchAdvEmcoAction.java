/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.process.equipment.emco;

import com.venus.core.util.LogUtil;
import com.venus.mc.bean.EmcoBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.EmcoDAO;
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
public class SearchAdvEmcoAction extends SpineAction {

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
        SearchAdvEmcoFormBean formBean = (SearchAdvEmcoFormBean) form;
        EmcoBean bean = new EmcoBean();

        bean.setEmcoNumber(formBean.getEmcoNumber());
        bean.setDepartment(formBean.getDepartment());
        bean.setCarryOutDate(formBean.getCarryOutDate());
        bean.setCarryOutHour(formBean.getCarryOutHour());
        bean.setCarryOutMinute(formBean.getCarryOutMinute());
        bean.setExplanation(formBean.getExplanation());
        bean.setDescCarryOut(formBean.getDescCarryOut());
        bean.setDescNotCarryOut(formBean.getDescNotCarryOut());
        bean.setStatus(formBean.getStatus());
        bean.setResult(formBean.getResult());

        ArrayList emcoList = null;
        EmcoDAO emcoDAO = new EmcoDAO();
        try {
            emcoList = emcoDAO.searchAdvEmco(bean);
        } catch (Exception ex) {
            LogUtil.error("FAILED: searchAdvEmco-" + ex.getMessage());
            ex.printStackTrace();
        }
        request.setAttribute(Constants.EMCO_LIST, emcoList);
        return true;
    }
}
