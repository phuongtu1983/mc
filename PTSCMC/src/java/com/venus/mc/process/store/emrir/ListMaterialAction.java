/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.process.store.emrir;

import com.venus.core.util.GenericValidator;
import com.venus.core.util.NumberUtil;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.EmrirDAO;
import com.venus.mc.dao.MrirDAO;
import com.venus.mc.util.Constants;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author kngo
 */
public class ListMaterialAction extends SpineAction {

    @Override
    public boolean doAction(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        int emrirId = NumberUtil.parseInt(request.getParameter("emrirId"), 0);

        ArrayList arrMaterial = null;
        if (emrirId > 0) {
            try {
                EmrirDAO mrirDAO = new EmrirDAO();
                arrMaterial = mrirDAO.getEmrirDetailsByEmrir(emrirId);
            } catch (Exception ex) {
            }
        }
        if (arrMaterial == null) {
            arrMaterial = new ArrayList();
        }
        request.setAttribute(Constants.EMRIR_MATERIAL_LIST, arrMaterial);
        return true;
    }
}
