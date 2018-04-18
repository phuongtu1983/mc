/*
 *
 * Created on April 13, 2007, 2:57 PM
 */
package com.venus.mc.tenderplan;

import com.venus.core.util.DateUtil;
import com.venus.core.util.LogUtil;
import com.venus.mc.bean.TenderPlanBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.TenderPlanDAO;
import com.venus.mc.util.Constants;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author phuongtu
 * @version
 */
public class SearchAdvTenderPlanAction extends SpineAction {

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
        SearchAdvTenderPlanFormBean formBean = (SearchAdvTenderPlanFormBean) form;
        ArrayList tenderPlanList = null;
        TenderPlanDAO tenderDAO = new TenderPlanDAO();
        try {
            tenderPlanList = tenderDAO.searchAdvTenderPlan(formBean);
        } catch (Exception ex) {
            LogUtil.error("FAILED:TenderPlan:searchAdv-" + ex.getMessage());
            ex.printStackTrace();
        }
        if (tenderPlanList == null) {
            tenderPlanList = new ArrayList();
        }
        TenderPlanBean tenderBean = null;
        long MILLSECS_PER_DAY = 1000 * 60 * 60 * 24;
        long delta = 0;
        Date today = DateUtil.transformerStringEnDate(DateUtil.today("dd/MM/yyyy"), "dd/MM/yyyy");
        for (int i = 0; i < tenderPlanList.size(); i++) {
            tenderBean = (TenderPlanBean) tenderPlanList.get(i);
            Date deadline = DateUtil.transformerStringEnDate(tenderBean.getBidDeadline(), "dd/MM/yyyy");
            if (deadline != null) {
                delta = (deadline.getTime() - today.getTime());
                if (delta >= 0) {
                    delta = delta / MILLSECS_PER_DAY;
                    if (delta <= 1) {
                        tenderBean.setIsNeedHighLight(1);
                    }
                }
            }
        }
        request.setAttribute(Constants.TENDERPLAN_LIST, tenderPlanList);
        return true;
    }
}
