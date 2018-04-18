package com.venus.mc.process.store.equipment.verifiedplan;

/**
 * @author Mai Vinh Loc
 */
import com.venus.core.util.NumberUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import com.venus.mc.util.Constants;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.VerifiedPlanDAO;
import com.venus.mc.util.PermissionUtil;
import java.util.ArrayList;

public class ListVerifiedPlanAction extends SpineAction {

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
        VerifiedPlanDAO verifiedplanDAO = new VerifiedPlanDAO();
        int equId = NumberUtil.parseInt(request.getParameter("equId"), 0);
        ArrayList verifiedplanList = null;
        try {
            verifiedplanList = verifiedplanDAO.getVerifiedPlans(equId);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        request.setAttribute(Constants.VERIFIEDPLAN_LIST, verifiedplanList);
        return true;
    }

    @Override
    protected String getFunction() {
        return PermissionUtil.FUNC_LIST + "";
    }

    @Override
    protected int getPermit() {
        return PermissionUtil.PER_EQUIPMENT;
    }
}
