/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.bidevalsum;

import com.venus.core.util.DateUtil;
import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.BidOpeningReportBean;
import com.venus.mc.bean.BidEvalSumBean;
import com.venus.mc.bean.ComEvalBean;
import com.venus.mc.bean.TenderEvaluateVendorBean;
import com.venus.mc.bean.TenderPlanBean;
import com.venus.mc.bean.TenderPlanDetailBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.EmployeeDAO;
import com.venus.mc.dao.BidEvalSumDAO;
import com.venus.mc.dao.BidEvalSumVendorDAO;
import com.venus.mc.dao.ComEvalDAO;
import com.venus.mc.dao.TenderPlanDAO;
import com.venus.mc.dao.TenderPlanDetailDAO;
import com.venus.mc.util.Constants;
import com.venus.mc.util.PermissionUtil;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author mai vinh loc
 */
public class BidEvalSumFormAction extends SpineAction {

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
        BidEvalSumBean bean = null;
        TenderPlanBean tenderBean = null;
        BidOpeningReportBean beanOpen = null;
        int tenId = NumberUtil.parseInt(request.getParameter("tenId"), 0);
        BidEvalSumDAO bidDAO = new BidEvalSumDAO();
        if (tenId > 0) {
            TenderPlanDAO tenderDAO = new TenderPlanDAO();
            try {
                bean = bidDAO.getBidEvalSum(tenId);
                beanOpen = tenderDAO.getBidOpeningReport(tenId);
                tenderBean = tenderDAO.getTenderPlan(tenId);
            } catch (Exception ex) {
            }
        }

        if (bean == null) {
            bean = new BidEvalSumBean();
            bean.setCreatedDate(DateUtil.today("dd/MM/yyyy"));
        }
        if (tenderBean != null) {
            bean.setTenNumber(tenderBean.getTenderNumber());
        }
        if (beanOpen != null) {
            bean.setBorNumber(beanOpen.getReportNumber());
        }

        bean.setTenId(tenId);

        request.setAttribute(Constants.BID_EVAL_SUM, bean);

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
            ComEvalDAO comEvalDAO = new ComEvalDAO();
            ComEvalBean comBean = comEvalDAO.getComEval(bean.getTenId());
            if (comBean != null) {
                request.setAttribute(Constants.CAN_SAVE, "true");
            }
        } catch (Exception ex) {
        }

        if (bean.getBesId() > 0) {
            try {
                TenderPlanDetailDAO techDAO = new TenderPlanDetailDAO();
               // String matIds = bidDAO.getMaterialBidEvalSumForEmail(bean.getTenId(), techDAO.getEvalKindTenderPlan(bean.getTenId() + ""));
                String matIds = bidDAO.getMaterialBidEvalSumForEmailNon(bean.getTenId(), techDAO.getEvalKindTenderPlan(bean.getTenId() + ""));
                if (!matIds.equals("0")) {
                    request.setAttribute(Constants.CAN_EMAIL, "true");
                }
                               
                ArrayList arrDet = null;
                
                BidEvalSumVendorDAO bidVendorDAO = new BidEvalSumVendorDAO();
                arrDet = bidVendorDAO.getTenderPlanVendorDiffBid(bean.getTenId());
                TenderEvaluateVendorBean detail1 = null;
                //add Vendor Auto
                for (int i = 0; i < arrDet.size(); i++) {
                    detail1 = (TenderEvaluateVendorBean) arrDet.get(i);
                    bidVendorDAO.insertBidEvalForBidEvalSum(bean.getBesId(), detail1.getVenId());               
                }
                //add Detail auto                
                arrDet = bidVendorDAO.getTenderPlanDetailsforDiffBid(bean.getTenId());
                TenderPlanDetailBean detail = null;
                
                for (int i = 0; i < arrDet.size(); i++) {
                    detail = (TenderPlanDetailBean) arrDet.get(i);
                    bidVendorDAO.insertBidEvalForBidEvalSumDetail(bean.getBesId(), detail.getMatId(), detail.getQuantity(), detail.getPrice(), detail.getTotal(), detail.getUnit(), detail.getReqDetailId());                    
                }
             
            } catch (Exception ex) {
            }
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
