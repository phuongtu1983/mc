/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.process.equipment.mc;

import com.venus.core.util.NumberUtil;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.McDAO;
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
        int mcId = NumberUtil.parseInt(request.getParameter("mcId"), 0);
        Integer id = (Integer) session.getAttribute("id");
            if (id != null) {
                mcId = id;
            }
            session.removeAttribute("id");
        ArrayList arrEquipment = null;
        if (mcId > 0) {
            try {
                McDAO mcDAO = new McDAO();
                arrEquipment = mcDAO.getMcDetailsByMc(mcId);
            } catch (Exception ex) {
            }
        }
        if (arrEquipment == null) {
            arrEquipment = new ArrayList();
        }
        request.setAttribute(Constants.MC_EQUIPMENT_LIST, arrEquipment);
        return true;
    }
}
