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
import com.venus.mc.dao.ContractDAO;
import java.util.ArrayList;

/**
 *
 * @author mai vinh loc
 * @version
 */
public class ListDnAdjustmentMaterialAction extends SpineAction {

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
        String detIds = request.getParameter("detIds");
        int conId = NumberUtil.parseInt(request.getParameter("conId"), 0);
        int drId = NumberUtil.parseInt(request.getParameter("drId"), 0);
        if (drId > 0) {
            conId = drId;
        }
        ArrayList materialList = null;
        if (!GenericValidator.isBlankOrNull(detIds)) {
            DnDAO dnDAO = new DnDAO();
            try {
                ContractDAO conDAO = new ContractDAO();
                int id = 0;
                id = conDAO.getContractId(conId + "");
                if (drId > 0) {
                    materialList = dnDAO.getMaterialsOfContract(detIds, conId);
                } else if (conId > 0) {
                    if (id > 0) {
                        materialList = dnDAO.getMaterialsOfDnAdjustment(id, detIds);
                    } else {
                        materialList = dnDAO.getMaterialsOfDnAdjustment(conId, detIds);
                    }

                } else {
                    materialList = dnDAO.getMaterials(detIds);
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
