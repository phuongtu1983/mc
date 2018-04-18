package com.venus.mc.origin;

/**
 * @author Mai Vinh Loc
 */
import com.venus.mc.bean.OriginBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.OriginDAO;
import com.venus.mc.util.Constants;
import com.venus.mc.util.PermissionUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class OriginFormAction extends SpineAction {

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

        OriginBean bean = new OriginBean();
        String oriId = request.getParameter("oriId");
        if (!GenericValidator.isBlankOrNull(oriId)) {
            OriginDAO originDAO = new OriginDAO();
            try {
                bean = originDAO.getOrigin(oriId);
            } catch (Exception ex) {
            }
        }
        request.setAttribute(Constants.ORIGIN, bean);
        return true;
    }

    @Override
    protected String getFunction() {
        return PermissionUtil.FUNC_VIEW + ";" + PermissionUtil.FUNC_EDIT;
    }

    @Override
    protected int getPermit() {
        return PermissionUtil.PER_LIBRARY_MATERIAL_ORIGIN;
    }
}
