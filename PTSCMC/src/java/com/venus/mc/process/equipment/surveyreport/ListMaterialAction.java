/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.process.equipment.surveyreport;

import com.venus.core.util.GenericValidator;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.MaterialDAO;
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
        String matIds = request.getParameter("matIds");
        ArrayList arrMaterial = null;
        if (!GenericValidator.isBlankOrNull(matIds)) {
            try {
                MaterialDAO equipmentDAO = new MaterialDAO();
                arrMaterial = equipmentDAO.getMaterialsForSurveyReport(matIds);
            } catch (Exception ex) {
            }
        }
        if (arrMaterial == null) {
            arrMaterial = new ArrayList();
        }
        request.setAttribute(Constants.MATERIAL_LIST, arrMaterial);

        return true;
    }
}
