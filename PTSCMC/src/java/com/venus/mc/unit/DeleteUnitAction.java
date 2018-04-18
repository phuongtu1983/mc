package com.venus.mc.unit;

/**
 * @author Mai Vinh Loc
 */
import com.venus.core.auth.OnlineUser;
import com.venus.core.util.LogUtil;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.UnitDAO;
import com.venus.mc.util.MCUtil;
import com.venus.mc.util.PermissionUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

public class DeleteUnitAction extends SpineAction {

    private String result = "";

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
        String[] arrUnitid = request.getParameterValues("uniId");
        try {
            OnlineUser user = MCUtil.getOnlineUser(session);
            LogUtil.info("Username : " + user.getName() + " - Form : " + mapping.getPath() + " - DELETE");
            int length = 0;
            boolean isExist = false;
            boolean isExist1 = false;
            UnitDAO unitDAO = new UnitDAO();
            if (arrUnitid != null) {
                length = arrUnitid.length;
            }
            for (int i = 0; i < length; i++) {
                isExist = unitDAO.checkDeleted(arrUnitid[i]);
                if (!isExist) {
                    unitDAO.deleteUnit(arrUnitid[i]);
                } else {
                    isExist1 = true;
                }
            }
            if (isExist1) {
                ActionMessages errors = new ActionMessages();
                errors.add("deleteUnit", new ActionMessage("errors.unit.deleteUnit"));
                saveErrors(request, errors);
                return false;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return true;
    }

    @Override
    protected String getErrorsString(HttpServletRequest request) {
        return result;
    }

    @Override
    protected String getFunction() {
        return PermissionUtil.FUNC_DELETE + "";
    }

    @Override
    protected int getPermit() {
        return PermissionUtil.PER_LIBRARY_MATERIAL_UNIT;
    }
}
