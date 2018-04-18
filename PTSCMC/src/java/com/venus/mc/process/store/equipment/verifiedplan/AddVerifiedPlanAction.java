package com.venus.mc.process.store.equipment.verifiedplan;

/**
 * @author Mai Vinh Loc
 */
import com.venus.core.auth.OnlineUser;
import com.venus.core.util.LogUtil;
import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.VerifiedPlanBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.VerifiedPlanDAO;
import com.venus.mc.util.MCUtil;
import com.venus.mc.util.PermissionUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class AddVerifiedPlanAction extends SpineAction {

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
        VerifiedPlanFormBean formBean = (VerifiedPlanFormBean) form;
        int equId = NumberUtil.parseInt(request.getParameter("equId"), 0);
        int vpId = formBean.getVpId();
        boolean bNew = false;
        if (vpId == 0) {
            bNew = true;
        } else {
            bNew = false;
        }

        VerifiedPlanBean bean = new VerifiedPlanBean();
        bean.setVpId(formBean.getVpId());
        bean.setEquId(equId);
        bean.setOrgId(formBean.getOrgId());
        bean.setContent(formBean.getContent());
        bean.setCost(formBean.getCost());
        bean.setStatus(formBean.getStatus());
        bean.setComment(formBean.getComment());
        bean.setPerformKind(formBean.getPerformKind());
        bean.setPerformPart(formBean.getPerformPart());
        bean.setTimePlan(formBean.getTimePlan());
        bean.setTimeEffect(formBean.getTimeEffect());
        bean.setTimeNext(formBean.getTimeNext());

        VerifiedPlanDAO verifiedplanDAO = new VerifiedPlanDAO();

        try {
            boolean isExist = false;
            /*         isExist = verifiedplanDAO.checkDecisionNumber(formBean.getEquId(), formBean.getDecisionNumber());
            if (isExist) {
            ActionMessages errors = new ActionMessages();
            errors.add("decisionNumberExisted", new ActionMessage("errors.verifiedplan.existedDecisionNumber"));
            saveErrors(request, errors);
            return false;
            }
             */

            if (bNew) {
                verifiedplanDAO.insertVerifiedPlan(bean);
            } else {
                HttpSession session = request.getSession();
                OnlineUser user = MCUtil.getOnlineUser(session);
                LogUtil.info("Username : " + user.getName() + " - Form : " + mapping.getPath() + " - EDIT");
                verifiedplanDAO.updateVerifiedPlan(bean);
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
