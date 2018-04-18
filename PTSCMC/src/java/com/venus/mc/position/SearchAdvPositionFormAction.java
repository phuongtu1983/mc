/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.venus.mc.position;

import com.venus.mc.bean.PositionBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.util.Constants;
import com.venus.mc.util.MCUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;


import java.util.ArrayList;
import org.apache.struts.util.LabelValueBean;
import org.apache.struts.util.LabelValueBean;

/**
 *
 * @author kngo
 */
public class SearchAdvPositionFormAction extends SpineAction {
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
        request.setAttribute(Constants.POSITION, bean);
        return true;
    }
}
