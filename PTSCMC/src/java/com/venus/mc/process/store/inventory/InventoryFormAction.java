/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.process.store.inventory;

import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.InventoryBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.EmployeeDAO;
import com.venus.mc.dao.InventoryDAO;
import com.venus.mc.dao.StoreDAO;
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
public class InventoryFormAction extends SpineAction {

    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Inventory we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    @Override
    public boolean doAction(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {

//        HttpSession session = request.getSession();

        try {
            InventoryFormBean formBean = null;
            int invId = NumberUtil.parseInt(request.getParameter("invId"), 0);

            if (invId > 0) {
                InventoryDAO inventoryDAO = new InventoryDAO();

                InventoryBean bean = inventoryDAO.getInventory(invId);
                if (bean != null) {
                    formBean = new InventoryFormBean(bean);
                }

            }
            if (formBean == null) {
                formBean = new InventoryFormBean();
            }
            request.setAttribute(Constants.INVENTORY, formBean);

            StoreDAO storeDAO = new StoreDAO();
            ArrayList arrStore = new ArrayList();
            arrStore = storeDAO.getStores();
            if (arrStore == null) {
                arrStore = new ArrayList();
            }
            request.setAttribute(Constants.STORE_LIST, arrStore);

            EmployeeDAO empDAO = new EmployeeDAO();
            ArrayList arrEmp = new ArrayList();
            arrEmp = empDAO.getEmployees();
            if (arrEmp == null) {
                arrEmp = new ArrayList();
            }
            request.setAttribute(Constants.EMPLOYEE_LIST, arrEmp);
        } catch (Exception ex) {
        }
        return true;
    }
}
