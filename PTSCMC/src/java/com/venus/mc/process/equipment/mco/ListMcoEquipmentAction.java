/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.process.equipment.mco;

import com.venus.core.util.GenericValidator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import com.venus.mc.util.Constants;

import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.McoDAO;
import java.util.ArrayList;

/**
 *
 * @author kngo
 */
public class ListMcoEquipmentAction extends SpineAction {

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
        String equIds = request.getParameter("equIds");
        ArrayList equipmentList = null;
        if (!GenericValidator.isBlankOrNull(equIds)) {
            McoDAO mcoDAO = new McoDAO();
            try {
                equipmentList = mcoDAO.getEquipments(equIds);
            } catch (Exception ex) {
            }
        }
        if (equipmentList == null) {
            equipmentList = new ArrayList();
        }
        request.setAttribute(Constants.MCO_EQUIPMENT_LIST, equipmentList);
        return true;
    }
}
