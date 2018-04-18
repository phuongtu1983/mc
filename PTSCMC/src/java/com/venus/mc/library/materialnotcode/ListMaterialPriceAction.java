package com.venus.mc.library.materialnotcode;

/**
 * @author Nguyen Phuong Tu
 */
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import com.venus.mc.util.Constants;

import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.MaterialDAO;
import java.util.ArrayList;

public class ListMaterialPriceAction extends SpineAction {

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
        MaterialDAO materialDAO = new MaterialDAO();
        ArrayList materialList = null;
        try {
            materialList = materialDAO.getMaterialPrices();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (materialList == null) {
            materialList = new ArrayList();
        }
        request.setAttribute(Constants.MATERIAL_LIST, materialList);
        return true;
    }
}
