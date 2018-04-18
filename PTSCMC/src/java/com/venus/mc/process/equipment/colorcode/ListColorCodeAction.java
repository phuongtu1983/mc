package com.venus.mc.process.equipment.colorcode;

/**
 * @author Mai Vinh Loc
 */
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import com.venus.mc.util.Constants;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.ColorCodeDAO;
import com.venus.mc.util.PermissionUtil;
import java.util.ArrayList;

public class ListColorCodeAction extends SpineAction {

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
        ColorCodeDAO colorCodeDAO = new ColorCodeDAO();
        ArrayList colorCodeList = null;
        try {
            colorCodeList = colorCodeDAO.getColorCodes();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        request.setAttribute(Constants.COLORCODE_LIST, colorCodeList);
        return true;
    }

    @Override
    protected String getFunction() {
        return PermissionUtil.FUNC_LIST + "";
    }

    @Override
    protected int getPermit() {
        return PermissionUtil.PER_EQUIPMENT_COLOR;
    }
}
