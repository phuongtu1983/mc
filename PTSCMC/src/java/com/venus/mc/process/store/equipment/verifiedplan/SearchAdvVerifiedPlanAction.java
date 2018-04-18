package com.venus.mc.process.store.equipment.verifiedplan;

/**
 * @author Mai Vinh Loc
 */
import com.venus.core.util.LogUtil;
import com.venus.mc.bean.VerifiedPlanBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.VerifiedPlanDAO;
import com.venus.mc.util.Constants;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import java.util.ArrayList;

public class SearchAdvVerifiedPlanAction extends SpineAction {

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
        SearchAdvVerifiedPlanFormBean formBean = (SearchAdvVerifiedPlanFormBean) form;
        VerifiedPlanBean bean = new VerifiedPlanBean();

        bean.setVpId(formBean.getVpId());
        bean.setEquId(formBean.getEquId());
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

        ArrayList verifiedplanList = null;
        VerifiedPlanDAO verifiedplanDAO = new VerifiedPlanDAO();
        try {
            verifiedplanList = verifiedplanDAO.searchAdvVerifiedPlan(bean);
        } catch (Exception ex) {
            LogUtil.error("FAILED: searchAdvVerifiedPlan-" + ex.getMessage());
            ex.printStackTrace();
        }
        request.setAttribute(Constants.VERIFIEDPLAN_LIST, verifiedplanList);
        return true;
    }
}
