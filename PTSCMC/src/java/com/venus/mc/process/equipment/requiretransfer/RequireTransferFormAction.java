/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.process.equipment.requiretransfer;

import com.venus.core.util.DateUtil;
import com.venus.mc.bean.RequireTransferBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.RequireTransferDAO;
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
public class RequireTransferFormAction extends SpineAction {

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
        RequireTransferFormBean formBean = null;
        RequireTransferBean bean = null;
        String rtId = request.getParameter("rtId");
        Integer id = (Integer) session.getAttribute("id");
        session.removeAttribute("id");
        if (id != null) {
            rtId = id + "";
        }
        
        if (!GenericValidator.isBlankOrNull(rtId)) {
            RequireTransferDAO requestDAO = new RequireTransferDAO();
            try {
                bean = requestDAO.getRequireTransfer(Integer.parseInt(rtId));
                if (bean != null) {
                    formBean = new RequireTransferFormBean(bean);
                }

            } catch (Exception ex) {
            }
        }
        if (formBean == null) {
            formBean = new RequireTransferFormBean();
            formBean.setCreatedEmpId(MCUtil.getMemberID(session));
            formBean.setOrgId(MCUtil.getOrganizationID(session));
            formBean.setOrgName(MCUtil.getOrganizationName(session));
            formBean.setCreatedDate(DateUtil.today("dd/MM/yyyy"));            
        }
        request.setAttribute(Constants.REQUIRETRANSFER, formBean);
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
