package com.venus.mc.process.purchasing.edeliverynotice;

import com.venus.core.util.LogUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import com.venus.mc.util.Constants;

import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.EdnDAO;
import com.venus.mc.util.PermissionUtil;
import java.util.ArrayList;

/**
 *
 * @author mai vinh loc
 * @version
 */
public class ListEdnAction extends SpineAction {

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
        //int kind = NumberUtil.parseInt(request.getParameter("kind"),0);
        EdnDAO dnDAO = new EdnDAO();
        ArrayList dnList = null;
        try {
            dnList = dnDAO.getDns();
        } catch (Exception ex) {
            LogUtil.error("FAILED:Dn:list-" + ex.getMessage());
            ex.printStackTrace();
        }
        request.setAttribute(Constants.DN_LIST, dnList);
        return true;
    }

    @Override
    protected String getFunction() {
        return PermissionUtil.FUNC_LIST + "";
    }

    @Override
    protected int getPermit() {
        return PermissionUtil.PER_PURCHASING_DELIVERYNOTICE_PROJECT;
    }
}
