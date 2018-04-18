/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.position;

import com.venus.mc.bean.PositionBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.PositionDAO;
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
 * @author kngo
 */
public class PositionFormAction extends SpineAction {

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
        PositionBean bean = new PositionBean();
        String positionid = request.getParameter("posId");
        if (!GenericValidator.isBlankOrNull(positionid)) {
            PositionDAO positionDAO = new PositionDAO();
            try {
                bean = positionDAO.getPosition(Integer.parseInt(positionid));
            } catch (Exception ex) {
            }
        }
        request.setAttribute(Constants.POSITION, bean);
        return true;
    }

    @Override
    protected String getXmlMessage() {
        return "positionDetail";
    }

    @Override
    protected String getXmlParameters(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        if (!GenericValidator.isBlankOrNull(request.getParameter("posId"))) {
            return request.getParameter("posId");
        } else {
            return "";
        }
    }
    
    @Override
    protected String getFunction() {
        return PermissionUtil.FUNC_VIEW + ";" + PermissionUtil.FUNC_EDIT;
    }

    @Override
    protected int getPermit() {
        return PermissionUtil.PER_SYSTEM;
    }
}
