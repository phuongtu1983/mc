/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.process.equipment.mc;

import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.EquipmentBean;
import com.venus.mc.bean.McDetailBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.EquipmentDAO;
import com.venus.mc.dao.McDAO;
import com.venus.mc.util.Constants;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author kngo
 */
public class EquipmentForMcAction extends SpineAction {

    @Override
    public boolean doAction(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        int mcId = NumberUtil.parseInt(request.getParameter("mcId"), 0);
        int mcoId = NumberUtil.parseInt(request.getParameter("mcoId"), 0);
        int equId = NumberUtil.parseInt(request.getParameter("equId"), 0);

        try {
            McDetailBean mcDetailBean = new McDetailBean();

            mcDetailBean.setMcId(mcId);
            mcDetailBean.setEquId(equId);

            EquipmentDAO equDAO = new EquipmentDAO();
            EquipmentBean equBean = equDAO.getEquipment(Integer.toString(equId));
            mcDetailBean.setUsedCode(equBean.getUsedCode());
            mcDetailBean.setNameVn(equBean.getEquipmentName());
            mcDetailBean.setUnit(equBean.getUnit());

            mcDetailBean.setQuantity(1);
            mcDetailBean.setSpec("");

            McDAO mcDAO = new McDAO();
            mcDetailBean.setMcoDetailId(mcDAO.getMcoDetailId(mcoId, equId));

            mcDetailBean.setMcoId(mcoId);
            request.setAttribute(Constants.MC_EQUIPMENT, mcDetailBean);
        } catch (Exception ex) {
        }
        return true;
    }
}
