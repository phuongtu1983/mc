/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.comeval;

import com.venus.core.util.DateUtil;
import com.venus.mc.bean.BidOpeningReportBean;
import com.venus.mc.bean.ComEvalBean;
import com.venus.mc.bean.TechEvalBean;
import com.venus.mc.bean.TenderPlanBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.EmployeeDAO;
import com.venus.mc.dao.ComEvalDAO;
import com.venus.mc.dao.TechEvalDAO;
import com.venus.mc.dao.TenderPlanDAO;
import com.venus.mc.util.Constants;
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
public class ComEvalFormAction extends SpineAction {

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
        ComEvalBean bean = null;
        TenderPlanBean tenderBean = null;
        BidOpeningReportBean beanOpen = null;
        String tenId = request.getParameter("tenId");

        if (!GenericValidator.isBlankOrNull(tenId)) {
            ComEvalDAO comDAO = new ComEvalDAO();
            TechEvalDAO techDAO = new TechEvalDAO();
            TenderPlanDAO tenderDAO = new TenderPlanDAO();
            try {
                bean = comDAO.getComEvalForVendor(Integer.parseInt(tenId));
                beanOpen = tenderDAO.getBidOpeningReport(Integer.parseInt(tenId));
                tenderBean = tenderDAO.getTenderPlan(Integer.parseInt(tenId));
                
                if (bean == null) {
                    bean = new ComEvalBean();
                    bean.setCreatedDate(DateUtil.today("dd/MM/yyyy"));
                }
                if (tenderBean != null) {
                    bean.setTenNumber(tenderBean.getTenderNumber());
                }
                if (beanOpen != null) {
                    bean.setBorNumber(beanOpen.getReportNumber());
                }
                bean.setForm(techDAO.getForm(Integer.parseInt(tenId)));
            } catch (Exception ex) {
            }
        }

        bean.setTenId(Integer.parseInt(tenId));

        request.setAttribute(Constants.COM_EVAL, bean);

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
            TechEvalDAO techEvalDAO = new TechEvalDAO();
            TechEvalBean teBean = techEvalDAO.getTechEval(bean.getTenId());
            if (teBean != null) {
                request.setAttribute(Constants.CAN_SAVE, "true");
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
