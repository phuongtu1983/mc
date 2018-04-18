package com.venus.mc.process.purchasing.edeliverynotice;

import com.venus.core.util.GenericValidator;
import com.venus.core.util.NumberUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import com.venus.mc.util.Constants;

import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.EdnDAO;
import java.util.ArrayList;

/**
 *
 * @author mai vinh loc
 * @version
 */
public class ListEdnMaterialAction extends SpineAction {

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
        String matIds = request.getParameter("matIds");

        ArrayList materialList = null;
        if (!GenericValidator.isBlankOrNull(matIds)) {
            EdnDAO dnDAO = new EdnDAO();
            try {
                materialList = dnDAO.getMaterialsOfContract(matIds);
            } catch (Exception ex) {
            }
        }
        if (materialList == null) {
            materialList = new ArrayList();
        }
        request.setAttribute(Constants.MATERIAL_LIST, materialList);
        return true;
    }
}
