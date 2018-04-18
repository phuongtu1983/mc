/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.process.equipment.requirerepair;

import com.venus.core.util.DateUtil;
import com.venus.mc.bean.RequireRepairBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.RequireRepairDAO;
import com.venus.mc.util.Constants;
import com.venus.mc.util.MCUtil;
import com.venus.mc.util.PermissionUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author mai vinh loc
 */
public class RequireRepairFormAction extends SpineAction {

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
        HttpSession session = request.getSession();
        RequireRepairFormBean formBean = null;
        RequireRepairBean bean = null;
        String rrId = request.getParameter("rrId");
        Integer id = (Integer) session.getAttribute("id");
        session.removeAttribute("id");
        if (id != null) {
            rrId = id + "";
        }
        
        if (!GenericValidator.isBlankOrNull(rrId)) {
            RequireRepairDAO requestDAO = new RequireRepairDAO();
            try {
                bean = requestDAO.getRequireRepair(Integer.parseInt(rrId));
                if (bean != null) {
                    formBean = new RequireRepairFormBean(bean);
                }

            } catch (Exception ex) {
            }
        }
        if (formBean == null) {
            formBean = new RequireRepairFormBean();
            formBean.setCreatedEmp(MCUtil.getMemberID(session));
            formBean.setRequireOrg(MCUtil.getOrganizationName(session));
            formBean.setCreatedDate(DateUtil.today("dd/MM/yyyy"));
            formBean.setRequireDate(DateUtil.today("dd/MM/yyyy"));
        }

        request.setAttribute(Constants.REQUIREREPAIR, formBean);


        return true;
    }

    @Override
    protected String getFunction() {
        return PermissionUtil.FUNC_VIEW + ";" + PermissionUtil.FUNC_EDIT;
    }

    @Override
    protected int getPermit() {
        return PermissionUtil.PER_EQUIPMENT_REPAIRREQUEST;
    }
}
