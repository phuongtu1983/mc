/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.venus.mc.process.equipment.emc;
import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.EmcDetailBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.util.Constants;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
/**
 *
 * @author kngo
 */
public class EquipmentForEmcAction extends SpineAction {

    @Override
    public boolean doAction(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        int emcId = NumberUtil.parseInt(request.getParameter("emcId"), 0);
        String equipment = request.getParameter("equipment");

        try {
            EmcDetailBean emcDetailBean = new EmcDetailBean();
            emcDetailBean.setEmcId(emcId);
            emcDetailBean.setEquipment(equipment);
            emcDetailBean.setUnit("");
            emcDetailBean.setQuantity(1);
            emcDetailBean.setSpec("");
            request.setAttribute(Constants.EMC_EQUIPMENT, emcDetailBean);
        } catch (Exception ex) {
        }
        return true;
    }
}
