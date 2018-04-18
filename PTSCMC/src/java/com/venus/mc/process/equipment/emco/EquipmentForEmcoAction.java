/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.venus.mc.process.equipment.emco;
import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.EquipmentBean;
import com.venus.mc.bean.EmcoDetailBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.EquipmentDAO;
import com.venus.mc.dao.EmcoDAO;
import com.venus.mc.util.Constants;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author kngo
 */
public class EquipmentForEmcoAction extends SpineAction {

    @Override
    public boolean doAction(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        int emcoId = NumberUtil.parseInt(request.getParameter("emcoId"), 0);
        int emcId = NumberUtil.parseInt(request.getParameter("emcId"), 0);
        String equipment = request.getParameter("equipment");

        try {
            EmcoDetailBean emcoDetailBean = new EmcoDetailBean();

            emcoDetailBean.setEmcoId(emcoId);
            emcoDetailBean.setEquipment(equipment);
            emcoDetailBean.setUnit("");
            emcoDetailBean.setQuantity(1);
            emcoDetailBean.setSpec("");

            EmcoDAO emcoDAO = new EmcoDAO();
            emcoDetailBean.setEmcDetailId(emcoDAO.getEmcDetailId(emcId, equipment));
            emcoDetailBean.setEmcId(emcId);
            request.setAttribute(Constants.EMCO_EQUIPMENT, emcoDetailBean);
        } catch (Exception ex) {
        }
        return true;
    }
}
