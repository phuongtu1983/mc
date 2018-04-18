package com.venus.mc.process.equipment.requireverified;

import com.venus.core.util.LogUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import com.venus.mc.util.Constants;

import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.RequireVerifiedDAO;
import com.venus.mc.util.PermissionUtil;
import java.util.ArrayList;

/**
 *
 * @author thuhc
 */
public class ListRequireVerifiedAction extends SpineAction {

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
        RequireVerifiedDAO requestDAO = new RequireVerifiedDAO();
        ArrayList requireVerifiedList = null;
        try {
            requireVerifiedList = requestDAO.getRequireVerifiedList();
        } catch (Exception ex) {
            LogUtil.error("FAILED:ListRequireVerifiedAction:list-" + ex.getMessage());            
        }
        request.setAttribute(Constants.REQUIREVERIFIED_LIST, requireVerifiedList);
        return true;
    }
    
    @Override
    protected String getFunction() {
        return PermissionUtil.FUNC_LIST + "";
    }

    @Override
    protected int getPermit() {
        return PermissionUtil.PER_EQUIPMENT_REPAIRREQUEST;
    }
}
