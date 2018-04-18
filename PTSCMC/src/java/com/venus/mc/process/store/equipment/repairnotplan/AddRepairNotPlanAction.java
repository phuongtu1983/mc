package com.venus.mc.process.store.equipment.repairnotplan;

/**
 * @author Mai Vinh Loc
 */
import com.venus.core.auth.OnlineUser;
import com.venus.core.util.LogUtil;
import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.RepairNotPlanBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.RepairNotPlanDAO;
import com.venus.mc.util.MCUtil;
import com.venus.mc.util.PermissionUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class AddRepairNotPlanAction extends SpineAction {

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
        RepairNotPlanFormBean formBean = (RepairNotPlanFormBean) form;
        int equId = NumberUtil.parseInt(request.getParameter("equId"), 0);
        int rnpId = formBean.getRnpId();
        boolean bNew = false;
        if (rnpId == 0) {
            bNew = true;
        } else {
            bNew = false;
        }

        RepairNotPlanBean bean = new RepairNotPlanBean();
        bean.setRnpId(formBean.getRnpId());
        bean.setEquId(equId);
        bean.setRrId(formBean.getRrId());
        bean.setOrgUsed(formBean.getOrgUsed());
        bean.setCost(formBean.getCost());

        RepairNotPlanDAO repairnotplanDAO = new RepairNotPlanDAO();

        try {
            boolean isExist = false;
            /*         isExist = repairnotplanDAO.checkDecisionNumber(formBean.getEquId(), formBean.getDecisionNumber());
            if (isExist) {
            ActionMessages errors = new ActionMessages();
            errors.add("decisionNumberExisted", new ActionMessage("errors.repairnotplan.existedDecisionNumber"));
            saveErrors(request, errors);
            return false;
            }
             */

            if (bNew) {
                repairnotplanDAO.insertRepairNotPlan(bean);
            } else {
                HttpSession session = request.getSession();
                OnlineUser user = MCUtil.getOnlineUser(session);
                LogUtil.info("Username : " + user.getName() + " - Form : " + mapping.getPath() + " - EDIT");
                repairnotplanDAO.updateRepairNotPlan(bean);
            }
        } catch (Exception ex) {
            LogUtil.error("FAILED:Vender:add-" + ex.getMessage());
            ex.printStackTrace();
        }
        return true;
    }

    @Override
    protected String getFunction() {
        return PermissionUtil.FUNC_EDIT + "";
    }

    @Override
    protected int getPermit() {
        return PermissionUtil.PER_EQUIPMENT;
    }
}
