/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.process.equipment.timeusing;

import com.venus.core.util.DateUtil;
import com.venus.core.util.LogUtil;
import com.venus.mc.bean.TimeUsingBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.RepairPlanDAO;
import com.venus.mc.dao.TimeUsingDAO;
import com.venus.mc.util.Constants;
import com.venus.mc.util.MCUtil;
import com.venus.mc.util.PermissionUtil;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author mai vinh loc
 */
public class TimeUsingFormAction extends SpineAction {

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
        TimeUsingFormBean formBean = null;
        //TimeUsingBean bean = null;
        String strDate = request.getParameter("updateDate");
        String id = (String) session.getAttribute("id");
        if (id != null) {
            strDate = id + "";
        }
        session.removeAttribute("id");
        ArrayList tuList = null;
        TimeUsingDAO tuDAO = new TimeUsingDAO();
        if (GenericValidator.isBlankOrNull(strDate)) {
            strDate = DateUtil.today("dd/MM/yyyy");
        }
        try {
            tuList = tuDAO.getTimeUsing(strDate);
            if (tuList != null &&
                    tuList.size() > 0) {
                TimeUsingBean bean = (TimeUsingBean)tuList.get(0);
                formBean = new TimeUsingFormBean();
                formBean.setCreatedEmpId(bean.getCreatedEmpId());
                formBean.setCreatedEmpName(bean.getCreatedEmpName());
                formBean.setUpdateDate(strDate);
            }
        } catch (Exception ex) {
            LogUtil.error(getClass(), ex.getMessage());
        }

        if (formBean == null) {
            formBean = new TimeUsingFormBean();
            formBean.setCreatedEmpId(MCUtil.getMemberID(session));
            formBean.setCreatedEmpName(MCUtil.getMemberFullName(session));
            formBean.setUpdateDate(DateUtil.today("dd/MM/yyyy"));
        }
        if (tuList == null ||
                tuList.size() == 0) {
            try {
                tuList = tuDAO.getTimeUsingFromEquipment(formBean.getUpdateDate());
            } catch (Exception ex) {
                LogUtil.error(getClass(), ex.getMessage());
            }
        }
        if (tuList != null) {
            RepairPlanDAO rpDAO = new RepairPlanDAO();
            for (int i = 0; i < tuList.size(); i++) {
                TimeUsingBean tuBean = (TimeUsingBean) tuList.get(i);
                try {
                    long totalTime = rpDAO.getTotalTimeRepairPlan(tuBean.getEquId(), tuBean.getUpdateDate());
                    long totalTimeUsed = tuDAO.getTotalTimeUsed(tuBean.getEquId(), tuBean.getUpdateDate());
                    tuBean.setTotalTimeUsed(totalTimeUsed);
                    tuBean.setTotalTimeRepair(totalTime);
                    tuBean.setTimeRemain(totalTime - totalTimeUsed - tuBean.getTimeUsed());
                } catch (Exception ex) {
                    LogUtil.error(getClass(), ex.getMessage());
                }
                try {
                    tuBean.setNextSchedule(rpDAO.getDateOfRepairNext(tuBean.getEquId(),tuBean.getUpdateDate()));
                } catch (Exception ex) {
                    LogUtil.error(getClass(), ex.getMessage());
                }

            }
        }
        request.setAttribute(Constants.TIMEUSING, formBean);
        request.setAttribute(Constants.TIMEUSING_DETAIL_LIST, tuList);
        return true;
    }

    @Override
    protected String getFunction() {
        return PermissionUtil.FUNC_VIEW + ";" + PermissionUtil.FUNC_EDIT;
    }

    @Override
    protected int getPermit() {
        return PermissionUtil.PER_EQUIPMENT_REPAIRREQUEST;
    }
}
