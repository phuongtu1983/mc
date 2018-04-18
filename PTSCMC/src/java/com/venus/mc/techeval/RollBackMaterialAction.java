/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.techeval;

import com.venus.core.util.GenericValidator;
import com.venus.core.util.NumberUtil;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.RequestDAO;
import com.venus.mc.dao.TechEvalDAO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author phuongtu
 */
public class RollBackMaterialAction extends SpineAction {

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
        int tenId = NumberUtil.parseInt(request.getParameter("tenId"), 0);
        try {
            TechEvalDAO tDAO = new TechEvalDAO();
            String detIdTps = tDAO.getTechMaterialNotReach(tenId);
            if (!detIdTps.equals("0")) {
                RequestDAO reqDAO = new RequestDAO();
                String[] detIds = detIdTps.split(",");
                for (int i = 0; i < detIds.length; i++) {
                    if (!GenericValidator.isBlankOrNull(detIds[i]) && !detIds[i].equals("0")) {
                        reqDAO.rollbackRequestStatus(detIds[i]);
                    }
                }

            }
        } catch (Exception ex) {
        }
        return true;
    }
}
