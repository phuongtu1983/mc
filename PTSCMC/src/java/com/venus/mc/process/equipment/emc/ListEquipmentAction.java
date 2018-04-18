/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.process.equipment.emc;

import com.venus.core.util.NumberUtil;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.EmcDAO;
import com.venus.mc.util.Constants;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author kngo
 */
public class ListEquipmentAction extends SpineAction {

    @Override
    public boolean doAction(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        int emcId = NumberUtil.parseInt(request.getParameter("emcId"), 0);
        Integer id = (Integer) session.getAttribute("id");
        if (id != null) {
            emcId = id;
        }
        session.removeAttribute("id");

        ArrayList arrEquipment = null;
        if (emcId > 0) {
            try {
                EmcDAO emcDAO = new EmcDAO();
                arrEquipment = emcDAO.getEmcDetailsByEmc(emcId);
            } catch (Exception ex) {
            }
        }
        if (arrEquipment == null) {
            arrEquipment = new ArrayList();
        }
        request.setAttribute(Constants.EMC_EQUIPMENT_LIST, arrEquipment);
        return true;
    }
}
