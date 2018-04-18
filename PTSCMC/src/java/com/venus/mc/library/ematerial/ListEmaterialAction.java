package com.venus.mc.library.ematerial;

/**
 * @author Mai Vinh Loc
 */
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import com.venus.mc.util.Constants;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.EmaterialDAO;
import com.venus.mc.util.PermissionUtil;
import java.util.ArrayList;

public class ListEmaterialAction extends SpineAction {

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
        EmaterialDAO ematerialDAO = new EmaterialDAO();
        ArrayList ematerialList = null;
        try {
            ematerialList = ematerialDAO.getEmaterials();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        request.setAttribute(Constants.MATERIAL_LIST, ematerialList);
        return true;
    }

    @Override
    protected String getFunction() {
        return PermissionUtil.FUNC_LIST + "";
    }

    @Override
    protected int getPermit() {
        return PermissionUtil.PER_LIBRARY_MATERIAL_OUT;
    }
}
