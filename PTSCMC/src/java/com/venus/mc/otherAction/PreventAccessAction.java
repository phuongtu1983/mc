/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.otherAction;

import com.venus.core.util.GenericValidator;
import com.venus.mc.bean.ParameterBean;
import com.venus.mc.dao.ParameterDAO;
import com.venus.mc.util.Constants;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author phuongtu
 */
public class PreventAccessAction extends org.apache.struts.action.Action {

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
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        String status = request.getParameter("status");
        if (!GenericValidator.isBlankOrNull(status)) {
            try {
                String name = "prevent_access";
                String value = "";
                if (status.equals("on")) {
                    value = "on";
                } else {
                    value = "off";
                }
                ParameterDAO paramDAO = new ParameterDAO();
                ParameterBean bean = paramDAO.getParameter(name);
                if (bean != null) {
                    bean.setValue(value);
                    paramDAO.updateParameter(bean);
                } else {
                    bean = new ParameterBean();
                    bean.setName(name);
                    bean.setValue(value);
                    paramDAO.insertParameter(bean);
                }
            } catch (Exception ex) {
            }
        }
        return mapping.findForward(Constants.FORWARD_ACT_SUCCESS);
    }
}
