/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.tenderplan;

import com.venus.mc.bean.BidClosingReportBean;
import com.venus.mc.bean.BidOpeningReportBean;
import com.venus.mc.bean.TenderPlanBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.EmployeeDAO;
import com.venus.mc.dao.IncotermDAO;
import com.venus.mc.dao.TenderPlanDAO;
import com.venus.mc.util.Constants;
import com.venus.mc.util.MCUtil;
import com.venus.mc.util.PermissionUtil;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.LabelValueBean;

/**
 *
 * @author phuongtu
 */
public class BidOpeningFormAction extends SpineAction {

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
        BidOpeningReportFormBean formBean = null;
        TenderPlanBean tenderBean = null;
        TenderPlanDAO tenderDAO = new TenderPlanDAO();
        String tenId = request.getParameter("tenId");
        if (!GenericValidator.isBlankOrNull(tenId)) {
            try {
                BidOpeningReportBean bean = tenderDAO.getBidOpeningReport(Integer.parseInt(tenId));
                if (bean != null) {
                    formBean = new BidOpeningReportFormBean(bean);
                }
                tenderBean = tenderDAO.getTenderPlan(Integer.parseInt(tenId));
            } catch (Exception ex) {
            }
        }
        if (formBean == null) {
            formBean = new BidOpeningReportFormBean();
        }
        formBean.setTenId(tenderBean.getTenId());
        formBean.setTenderNumber(tenderBean.getTenderNumber());
        request.setAttribute(Constants.TENDERPLAN_BIDOPENING, formBean);
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
            if (formBean.getBorId() != 0) {
                arrGroup = tenderDAO.getBidOpeningGroupByReport(formBean.getBorId());
            } else {
                arrGroup = tenderDAO.getBidOpeningGroupByTenderPlan(formBean.getTenId());
            }
        } catch (Exception ex) {
        }
        if (arrGroup == null) {
            arrGroup = new ArrayList();
        }
        request.setAttribute(Constants.TENDERPLAN_BIDOPENING_EMPLOYEE, arrGroup);

        ArrayList arrVendor = null;
        try {
//            arrVendor = tenderDAO.getTenderPlanVendorBiddiedDetail(Integer.parseInt(tenId));
            arrVendor = tenderDAO.getTenderPlanVendorDetailBidded(Integer.parseInt(tenId));
        } catch (Exception ex) {
        }
        if (arrVendor == null) {
            arrVendor = new ArrayList();
        }
        request.setAttribute(Constants.TENDERPLAN_BIDOPENING_VENDOR, arrVendor);

        ArrayList arrBiddedStatus = new ArrayList();
        LabelValueBean value;
        value = new LabelValueBean();
        value.setLabel(MCUtil.getBundleString("message.tenderplan.bidopening.biddedStatus.sealed"));
        value.setValue(BidOpeningReportFormBean.SEALED + "");
        arrBiddedStatus.add(value);
        value = new LabelValueBean();
        value.setLabel(MCUtil.getBundleString("message.tenderplan.bidopening.biddedStatus.notsealed"));
        value.setValue(BidOpeningReportFormBean.NOTSEALED + "");
        arrBiddedStatus.add(value);
        request.setAttribute(Constants.TENDERPLAN_BIDOPENING_BIDDEDSTATUS, arrBiddedStatus);

        ArrayList arrIncoterm = null;
        try {
            IncotermDAO cerDAO = new IncotermDAO();
            arrIncoterm = cerDAO.getIncoterms();
        } catch (Exception ex) {
        }
        if (arrIncoterm == null) {
            arrIncoterm = new ArrayList();
        }
        request.setAttribute(Constants.TENDERPLAN_BIDOPENING_INCOTERM, arrIncoterm);

        try {
            BidClosingReportBean bean = tenderDAO.getBidClosingReportByTenId(formBean.getTenId());
            if (bean != null) {
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
