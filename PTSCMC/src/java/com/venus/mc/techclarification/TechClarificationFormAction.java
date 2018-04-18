/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.techclarification;

import com.venus.core.util.DateUtil;
import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.TechClarificationBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.TechClarificationDAO;
import com.venus.mc.util.Constants;
import com.venus.mc.util.PermissionUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author mai vinh loc
 */
public class TechClarificationFormAction extends SpineAction {

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
        TechClarificationBean bean = null;
        String venId = request.getParameter("venId");
        String terId = request.getParameter("terId");
        //String venId = request.getParameter("venId");

        if (!GenericValidator.isBlankOrNull(terId)) {
            TechClarificationDAO techDAO = new TechClarificationDAO();
            try {
                bean = techDAO.getTechClarification(NumberUtil.parseInt(terId, 0), NumberUtil.parseInt(venId, 0));
                if (bean == null) {
                    bean = new TechClarificationBean();
                    bean.setCreatedDate(DateUtil.today("dd/MM/yyyy"));
                }
            } catch (Exception ex) {
            }
        }

        request.setAttribute(Constants.TECH_CLARIFICATION, bean);
        session.setAttribute("tcId", bean.getTcId());

        return true;
    }

    @Override
    protected String getFunction() {
        return PermissionUtil.FUNC_VIEW + ";" + PermissionUtil.FUNC_EDIT;
    }

    @Override
    protected int getPermit() {
        return PermissionUtil.PER_PURCHASING_TENDERPLAN;
    }
}
