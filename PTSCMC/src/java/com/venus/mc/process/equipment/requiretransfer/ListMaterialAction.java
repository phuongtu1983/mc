/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.process.equipment.requiretransfer;

import com.venus.core.util.GenericValidator;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.EquipmentDAO;
import com.venus.mc.util.Constants;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author mai vinh loc
 */
public class ListMaterialAction extends SpineAction {

    @Override
    public boolean doAction(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        String equIds = request.getParameter("equIds");
        ArrayList arrMaterial = null;
        if (!GenericValidator.isBlankOrNull(equIds)) {
            try {
                EquipmentDAO equipmentDAO = new EquipmentDAO();
                arrMaterial = equipmentDAO.getEquipments(equIds);
            } catch (Exception ex) {
            }
        }
        if (arrMaterial == null) {
            arrMaterial = new ArrayList();
        }
        request.setAttribute(Constants.EQUIPMENT_LIST, arrMaterial);

        return true;
    }
}
