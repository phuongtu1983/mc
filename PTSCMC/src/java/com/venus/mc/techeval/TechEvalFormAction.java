/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.techeval;

import com.venus.core.util.DateUtil;
import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.BidOpeningReportBean;
import com.venus.mc.bean.TechEvalBean;
import com.venus.mc.bean.TenderPlanBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.EmployeeDAO;
import com.venus.mc.dao.TechEvalDAO;
import com.venus.mc.dao.TenderPlanDAO;
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
public class TechEvalFormAction extends SpineAction {

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
        TechEvalBean bean = null;
        TenderPlanBean tenderBean = null;
        BidOpeningReportBean beanOpen = null;
        String tenId = MCUtil.getParameter("tenId", request);
        if (!GenericValidator.isBlankOrNull(tenId)) {
            TechEvalDAO techDAO = new TechEvalDAO();
            try {
                bean = techDAO.getTechEval(NumberUtil.parseInt(tenId, 0));
                if (bean == null) {
                    bean = new TechEvalBean();
                    bean.setCreatedDate(DateUtil.today("dd/MM/yyyy"));
                }
                bean.setForm(techDAO.getForm(Integer.parseInt(tenId)));
            } catch (Exception ex) {
            }
            TenderPlanDAO tenderDAO = new TenderPlanDAO();
            try {
                beanOpen = tenderDAO.getBidOpeningReport(NumberUtil.parseInt(tenId, 0));
                tenderBean = tenderDAO.getTenderPlan(NumberUtil.parseInt(tenId, 0));
            } catch (Exception ex) {
            }
        }

        bean.setTenId(Integer.parseInt(tenId));
        if (tenderBean != null) {
            bean.setTenNumber(tenderBean.getTenderNumber());
        }
        if (beanOpen != null) {
            bean.setBorNumber(beanOpen.getReportNumber());
        }

        request.setAttribute(Constants.TECH_EVAL, bean);

        ArrayList arrEmployee = null;
        try {
            EmployeeDAO employeeDAO = new EmployeeDAO();
            arrEmployee = employeeDAO.getEmployees();
        } catch (Exception ex) {
        }
        if (arrEmployee == null) {
            arrEmployee = new ArrayList();
        }
        request.setAttribute(Constants.EMPLOYEE_LIST, arrEmployee);
        try {
            TenderPlanDAO tenderDAO = new TenderPlanDAO();
            BidOpeningReportBean openBean = tenderDAO.getBidOpeningReport(bean.getTenId());
            TenderPlanBean tenderPlanBean = tenderDAO.checkForm(bean.getTenId());
            TenderPlanBean tenderPlan2Bean = tenderDAO.checkSaveTechEval(bean.getTenId());
            if (NumberUtil.parseInt(tenderPlanBean.getForm(), 0) == 1) {
                if (openBean != null) {
                    request.setAttribute(Constants.CAN_SAVE, "true");
                }
            } else {
                if (tenderPlan2Bean != null) {
                    request.setAttribute(Constants.CAN_SAVE, "true");
                }
            }
        } catch (Exception ex) {
        }
        return true;
    }

    @Override
    protected String getFunction() {
        return PermissionUtil.FUNC_VIEW + ";" + PermissionUtil.FUNC_EDIT;
    }

    @Override
    protected int getPermit() {
        return PermissionUtil.PER_PURCHASING_TENDERPLAN;
    }
}
