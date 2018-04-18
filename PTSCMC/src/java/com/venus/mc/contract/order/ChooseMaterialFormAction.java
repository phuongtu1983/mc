/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.contract.order;

import com.venus.core.util.NumberUtil;
import com.venus.mc.core.SpineAction;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author Mai vinh loc
 */
public class ChooseMaterialFormAction extends SpineAction {

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
        int matId = NumberUtil.parseInt(request.getParameter("matId"), 0);
        String detIds = request.getParameter("detIds");
        String detId = request.getParameter("detId");
        String matIds = request.getParameter("matIds");
        String reqIds = request.getParameter("reqIds");
        String kind = request.getParameter("kind");
        request.setAttribute("kind",kind);
        request.setAttribute("matId",matId);
        request.setAttribute("detIds",detIds);
        request.setAttribute("detId",detId);
        request.setAttribute("matIds",matIds);
        request.setAttribute("reqIds",reqIds);
        return true;
    }
}
