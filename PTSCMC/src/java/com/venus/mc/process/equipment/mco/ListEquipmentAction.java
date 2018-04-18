/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.process.equipment.mco;

import com.venus.core.util.GenericValidator;
import com.venus.core.util.NumberUtil;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.McoDAO;
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
        String mcoId = request.getParameter("mcoId");
        Integer id = (Integer) session.getAttribute("id");
        if (id != null) {
            mcoId = id + "";
        }
        session.removeAttribute("id");
        ArrayList arrEquipment = null;
        if (!GenericValidator.isBlankOrNull(mcoId)) {
            try {
                McoDAO mcoDAO = new McoDAO();
                arrEquipment = mcoDAO.getMcoDetailsByMco(NumberUtil.parseInt(mcoId, 0));
            } catch (Exception ex) {
            }
        }
        if (arrEquipment == null) {
            arrEquipment = new ArrayList();
        }
        request.setAttribute(Constants.MCO_EQUIPMENT_LIST, arrEquipment);
        return true;
    }
}
