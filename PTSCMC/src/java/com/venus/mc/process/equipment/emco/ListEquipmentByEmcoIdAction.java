/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.venus.mc.process.equipment.emco;
import com.venus.core.util.NumberUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import com.venus.mc.util.Constants;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.EmcDAO;
import java.util.ArrayList;
/**
 *
 * @author kngo
 */
public class ListEquipmentByEmcoIdAction extends SpineAction {

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
        int emcId = NumberUtil.parseInt(request.getParameter("emcId"), 0);
        try {
            if (emcId > 0) {
                ArrayList arrEqu = new ArrayList();
                EmcDAO emcDAO = new EmcDAO();
                arrEqu = emcDAO.getEmcDetailsByEmc(emcId);
                request.setAttribute(Constants.EQUIPMENT_LIST, arrEqu);
            }
        } catch (Exception ex) {
        }
        this.actionForwardResult = "equipments";
        return true;
    }
}
