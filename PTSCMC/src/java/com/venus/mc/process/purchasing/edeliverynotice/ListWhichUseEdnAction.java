/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.process.purchasing.edeliverynotice;

import com.venus.core.util.GenericValidator;
import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.EdnBean;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import com.venus.mc.util.Constants;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.EdnDAO;
import java.util.ArrayList;

/**
 *
 * @author mai vinh loc
 */
public class ListWhichUseEdnAction extends SpineAction {

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
        String whichUse = request.getParameter("whichUse");
        if (GenericValidator.isBlankOrNull(whichUse)) {
            return true;
        }

        String dnId = request.getParameter("dnId");
        EdnBean bean = null;
        if (!GenericValidator.isBlankOrNull(dnId)) {
            try {
                EdnDAO dnDAO = new EdnDAO();
                bean = dnDAO.getDn(NumberUtil.parseInt(dnId, 0));
            } catch (Exception ex) {
            }
        }
        if (bean == null) {
            bean = new EdnBean();
        }
        request.setAttribute(Constants.DN, bean);
        return true;
    }
}
