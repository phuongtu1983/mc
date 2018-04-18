package com.venus.mc.process.store.equipment.repairplan;

/**
 * @author Mai Vinh Loc
 */
import com.venus.core.util.LogUtil;
import com.venus.mc.bean.RepairPlanBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.RepairPlanDAO;
import com.venus.mc.util.Constants;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import java.util.ArrayList;

public class SearchAdvRepairPlanAction extends SpineAction {

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
        SearchAdvRepairPlanFormBean formBean = (SearchAdvRepairPlanFormBean) form;
        RepairPlanBean bean = new RepairPlanBean();

        bean.setRpId(formBean.getRpId());
        bean.setEquId(formBean.getEquId());
        bean.setAssId(formBean.getAssId());
        bean.setOrgId(formBean.getOrgId());
        bean.setRepairPart(formBean.getRepairPart());
        bean.setCost(formBean.getCost());
        bean.setRepairKind(formBean.getRepairKind());
        bean.setStatus(formBean.getStatus());
        bean.setComment(formBean.getComment());
        bean.setPerformKind(formBean.getPerformKind());
        bean.setPerformPart(formBean.getPerformPart());
        bean.setTimeTrue(formBean.getTimeTrue());

        ArrayList repairplanList = null;
        RepairPlanDAO repairplanDAO = new RepairPlanDAO();
        try {
            repairplanList = repairplanDAO.searchAdvRepairPlan(bean);
        } catch (Exception ex) {
            LogUtil.error("FAILED: searchAdvRepairPlan-" + ex.getMessage());
            ex.printStackTrace();
        }
        request.setAttribute(Constants.REPAIRPLAN_LIST, repairplanList);
        return true;
    }
}
