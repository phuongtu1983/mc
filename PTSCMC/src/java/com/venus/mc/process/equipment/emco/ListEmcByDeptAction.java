/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.process.equipment.emco;

import com.venus.core.util.GenericValidator;
import com.venus.mc.bean.EmcBean;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import com.venus.mc.util.Constants;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.EmcDAO;
import java.util.ArrayList;

/**
 *
 * @author kngo
 */
public class ListEmcByDeptAction extends SpineAction {

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
        String department = request.getParameter("department");

        try {
            ArrayList arrMc = new ArrayList();
            if (!GenericValidator.isBlankOrNull(department)) {
                EmcDAO emcDAO = new EmcDAO();
                arrMc = emcDAO.getEmcsByDept(department);

                EmcBean emc0 = new EmcBean();
                emc0.setEmcId(0);
                emc0.setEmcNumber("");
                arrMc.add(0, emc0);
            }

            request.setAttribute(Constants.EMC_LIST, arrMc);
            EmcoFormBean formBean = new EmcoFormBean();
            request.setAttribute(Constants.EMCO, formBean);
        } catch (Exception ex) {
        }
        return true;
    }
}
