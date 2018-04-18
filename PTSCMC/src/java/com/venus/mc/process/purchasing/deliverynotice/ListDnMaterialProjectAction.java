package com.venus.mc.process.purchasing.deliverynotice;

import com.venus.core.util.GenericValidator;
import com.venus.core.util.NumberUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import com.venus.mc.util.Constants;

import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.DnDAO;
import com.venus.mc.util.MCUtil;
import java.util.ArrayList;
import javax.servlet.http.HttpSession;

/**
 *
 * @author mai vinh loc
 * @version
 */
public class ListDnMaterialProjectAction extends SpineAction {

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
        int proId = NumberUtil.parseInt(request.getParameter("proId"), 0);
        int kind = NumberUtil.parseInt(request.getParameter("kind"), 0);
        String ids = request.getParameter("ids");
        HttpSession session = request.getSession();
        int orgId = (MCUtil.getOrganizationID(session));
        ArrayList materialList = null;
        if (proId >= 0) {
            DnDAO dnDAO = new DnDAO();
            try {
                if (kind < 4) {
                    materialList = dnDAO.getMaterialsOfProject(proId, orgId, ids);
                } else {
                    materialList = dnDAO.getMaterialsOfStoreRequest(proId);
                }

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
