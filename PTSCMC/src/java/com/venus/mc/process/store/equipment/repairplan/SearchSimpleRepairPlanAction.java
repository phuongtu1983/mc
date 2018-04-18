package com.venus.mc.process.store.equipment.repairplan;

/**
 * @author Mai Vinh Loc
 */
import com.venus.core.util.LogUtil;
import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.SearchFormBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.RepairPlanDAO;
import com.venus.mc.util.Constants;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import com.venus.core.util.StringUtil;
public class SearchSimpleRepairPlanAction extends SpineAction {

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
        SearchFormBean repairplanForm = (SearchFormBean) form;
        int equId = NumberUtil.parseInt(request.getParameter("equId"), 0);
        int assId = NumberUtil.parseInt(request.getParameter("assId"), 0);
        int fieldid = repairplanForm.getSearchid();
        String strFieldvalue = repairplanForm.getSearchvalue();
        ArrayList repairplanList = null;
        RepairPlanDAO repairplanDAO = new RepairPlanDAO();
        try {
            repairplanList = repairplanDAO.searchSimpleRepairPlan(fieldid, StringUtil.encodeHTML(strFieldvalue), equId, assId);
        } catch (Exception ex) {
            LogUtil.error("FAILED: RepairPlanBean:searchSimpleRepairPlan-" + ex.getMessage());
            ex.printStackTrace();
        }
        request.setAttribute(Constants.REPAIRPLAN_LIST, repairplanList);
        return true;
    }
}
