/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.process.store.emrir;

import com.venus.core.util.NumberUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import com.venus.mc.util.Constants;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.DeliveryNoticeDAO;
import java.util.ArrayList;

/**
 *
 * @author kngo
 */
public class ListMaterialsByDnIdAction extends SpineAction {

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
        int ednId = NumberUtil.parseInt(request.getParameter("ednId"), 0);

        try {
            ArrayList arrMat = new ArrayList();
            if (ednId > 0) {
                DeliveryNoticeDAO dnDAO = new DeliveryNoticeDAO();
                arrMat = dnDAO.getEdeliveryNoticeDetails(ednId);
            }
            request.setAttribute(Constants.EMRIR_MATERIAL_LIST, arrMat);
        } catch (Exception ex) {
        }

        this.actionForwardResult = "materials";
        return true;
    }
}
