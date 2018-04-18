/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.process.store.inventory;

import com.venus.core.util.NumberUtil;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.InventoryDAO;
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
public class ListMaterialsByStoIdAction extends SpineAction {

    @Override
    public boolean doAction(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        int stoId = NumberUtil.parseInt(request.getParameter("stoId"), 0);

        try {
            ArrayList arrMaterial = null;
            if (stoId > 0) {
                InventoryDAO inventoryDAO = new InventoryDAO();
                arrMaterial = inventoryDAO.getMaterialsByStoId(stoId);
            }
            if (arrMaterial == null) {
                arrMaterial = new ArrayList();
            }
            request.setAttribute(Constants.INVENTORY_MATERIAL_LIST, arrMaterial);
        } catch (Exception ex) {
        }
        return true;
    }
}
