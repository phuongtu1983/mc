/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.tenderplan;

import com.venus.mc.bean.BidClosingReportBean;
import com.venus.mc.bean.TenderLetterBean;
import com.venus.mc.bean.TenderPlanBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.EmployeeDAO;
import com.venus.mc.dao.TenderLetterDAO;
import com.venus.mc.dao.TenderPlanDAO;
import com.venus.mc.util.Constants;
import com.venus.mc.util.PermissionUtil;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author phuongtu
 */
public class BidClosingFormAction extends SpineAction {

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
        BidClosingReportFormBean formBean = null;
        TenderPlanBean tenderBean = null;
        TenderPlanDAO tenderDAO = new TenderPlanDAO();
        String tenId = request.getParameter("tenId");
        if (!GenericValidator.isBlankOrNull(tenId)) {
            try {
                BidClosingReportBean bean = tenderDAO.getBidClosingReportByTenId(Integer.parseInt(tenId));
                if (bean != null) {
                    formBean = new BidClosingReportFormBean(bean);
                }
                tenderBean = tenderDAO.getTenderPlan(Integer.parseInt(tenId));
            } catch (Exception ex) {
            }
        }
        if (formBean == null) {
            formBean = new BidClosingReportFormBean();
        }
        formBean.setTenId(tenderBean.getTenId());
        formBean.setTenderNumber(tenderBean.getTenderNumber());
        request.setAttribute(Constants.TENDERPLAN_BIDCLOSING, formBean);
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

        ArrayList arrGroup = null;
        try {
            if (formBean.getBcrId() != 0) {
                arrGroup = tenderDAO.getBidClosingGroupByReport(formBean.getBcrId());
            } else {
                arrGroup = tenderDAO.getBidClosingGroupByTenderPlan(formBean.getTenId());
            }
        } catch (Exception ex) {
        }
        if (arrGroup == null) {
            arrGroup = new ArrayList();
        }
        request.setAttribute(Constants.TENDERPLAN_BIDCLOSING_EMPLOYEE, arrGroup);

        ArrayList arrVendor = null;
        try {
            arrVendor = tenderDAO.getTenderPlanVendorDetailBidded(Integer.parseInt(tenId));
        } catch (Exception ex) {
        }
        if (arrVendor == null) {
            arrVendor = new ArrayList();
        }
        request.setAttribute(Constants.TENDERPLAN_BIDCLOSING_VENDOR, arrVendor);
        try {
            TenderLetterDAO letterDAO = new TenderLetterDAO();
            TenderLetterBean bean = letterDAO.getTenderLetter(Integer.parseInt(tenId));
            if (bean != null && bean.getLetId() != 0) {
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
