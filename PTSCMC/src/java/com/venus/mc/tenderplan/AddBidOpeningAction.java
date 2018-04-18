/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.tenderplan;

import com.venus.core.auth.OnlineUser;
import com.venus.core.util.LogUtil;
import com.venus.mc.bean.BidOpeningGroupBean;
import com.venus.mc.bean.BidOpeningReportBean;
import com.venus.mc.bean.TenderEvaluateVendorBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.TenderPlanDAO;
import com.venus.mc.util.MCUtil;
import com.venus.mc.util.PermissionUtil;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author phuongtu
 */
public class AddBidOpeningAction extends SpineAction {

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
        BidOpeningReportFormBean formBean = (BidOpeningReportFormBean) form;
        BidOpeningReportBean bean = null;
        try {
            TenderPlanDAO tenderDAO = new TenderPlanDAO();
            bean = tenderDAO.getBidOpeningReport(formBean.getTenId());
        } catch (Exception ex) {
        }
        int borId = formBean.getBorId();

        bean = new BidOpeningReportBean();
        bean.setTenId(formBean.getTenId());
        bean.setReportNumber(formBean.getReportNumber());
        bean.setStartDate(formBean.getStartDate());
        bean.setStartTime(formBean.getStartTime());
        bean.setEndDate(formBean.getEndDate());
        bean.setEndTime(formBean.getEndTime());
        bean.setComments(formBean.getComments());

        try {
            TenderPlanDAO tenderDAO = new TenderPlanDAO();
            if (formBean.getBorId() == 0) {
                borId = tenderDAO.insertBidOpeningReport(bean);
            } else {
                HttpSession session = request.getSession();
                OnlineUser user = MCUtil.getOnlineUser(session);
                LogUtil.info("Username : " + user.getName() + " - Form : " + mapping.getPath() + " - EDIT");
                tenderDAO.updateBidOpeningReport(bean);
            }
            addBidOpeningGroup(borId, formBean);
            addVendor(formBean);
        } catch (Exception ex) {
            LogUtil.error("FAILED:BidClosing:add-" + ex.getMessage());
            ex.printStackTrace();
        }
        return true;
    }

    private BidOpeningGroupBean openingGroupExisted(ArrayList arrGroup, int bogId) {
        BidOpeningGroupBean bean = null;
        for (int i = 0; i < arrGroup.size(); i++) {
            bean = (BidOpeningGroupBean) arrGroup.get(i);
            if (bean.getBogId() == bogId) {
                return bean;
            }
        }
        return null;
    }

    private void addBidOpeningGroup(int borId, BidOpeningReportFormBean formBean) {
        TenderPlanDAO tenderDAO = new TenderPlanDAO();
        ArrayList arrDet = null;
        if (borId != 0) {
            try {
                arrDet = tenderDAO.getBidOpeningGroup(borId);
            } catch (Exception ex) {
                arrDet = new ArrayList();
            }
        }
        if (formBean.getBogId() != null) {
            String[] detIds = formBean.getBogId();
            BidOpeningGroupBean detBean = null;
            boolean canUpdate = false;
            for (int i = 0; i < detIds.length; i++) {
                canUpdate = false;
                if (!detIds[i].equals("0")) {//old
                    try {
                        detBean = openingGroupExisted(arrDet, Integer.parseInt(detIds[i]));
                        if (detBean != null) {
                            if (!detBean.getEmployee().equals(formBean.getBidOpeningEmployee()[i])) {
                                detBean.setEmployee(formBean.getBidOpeningEmployee()[i]);
                                canUpdate = true;
                            }
                            if (!detBean.getPosition().equals(formBean.getBidOpeningPosition()[i])) {
                                detBean.setPosition(formBean.getBidOpeningPosition()[i]);
                                canUpdate = true;
                            }
                            if (!detBean.getNote().equals(formBean.getBidOpeningNote()[i])) {
                                detBean.setNote(formBean.getBidOpeningNote()[i]);
                                canUpdate = true;
                            }
                            if (canUpdate) {
                                tenderDAO.updateBidOpeningGroup(detBean);
                            }
                        }
                    } catch (Exception ex) {
                    }
                } else {//new
                    try {
                        detBean = new BidOpeningGroupBean(0, formBean.getBidOpeningEmployee()[i], formBean.getBidOpeningPosition()[i], "", "");
                        detBean.setBorId(borId);
                        tenderDAO.insertBidOpeningGroup(detBean);
                    } catch (Exception ex) {
                    }
                }
            }
        }
    }

    private void addVendor(BidOpeningReportFormBean formBean) {
        TenderPlanDAO tenderDAO = new TenderPlanDAO();
        if (formBean.getVendorId() != null) {
            String[] venIds = formBean.getVendorId();
            String[] foundations = formBean.getVendorBiddedFoundation();
            String[] pages = formBean.getVendorBiddedPage();
            String[] statuss = formBean.getVendorBiddedStatus();
            String[] quono = formBean.getVendorQuoNo();
            String[] quodate = formBean.getVendorQuoDate();
            String[] validity = formBean.getVendorBidValidity();
            String[] incoterm = formBean.getVendorIncoterm();
            ArrayList vendors = null;
            try {
                vendors = tenderDAO.getTenderPlanVendor(formBean.getTenId());
            } catch (Exception ex) {
            }
            if (vendors == null) {
                vendors = new ArrayList();
            }
            TenderEvaluateVendorBean bean = null;
            boolean canUpdate = false;
            for (int i = 0; i < venIds.length; i++) {
                canUpdate = false;
                bean = getVendor(vendors, venIds[i]);
                if (bean != null) {
                    if (!bean.getBiddedFoundation().equals(foundations[i])) {
                        bean.setBiddedFoundation(foundations[i]);
                        canUpdate = true;
                    }
                    if (!bean.getBiddedPage().equals(pages[i])) {
                        bean.setBiddedPage(pages[i]);
                        canUpdate = true;
                    }
                    if (bean.getBiddedStatus() != Integer.parseInt(statuss[i])) {
                        bean.setBiddedStatus(Integer.parseInt(statuss[i]));
                        canUpdate = true;
                    }
                    if (!bean.getQuoNo().equals((quono[i]))) {
                        bean.setQuoNo(quono[i]);
                        canUpdate = true;
                    }
                    if (!bean.getQuoDate().equals((quodate[i]))) {
                        bean.setQuoDate(quodate[i]);
                        canUpdate = true;
                    }
                    if (!bean.getBidValidity().equals((validity[i]))) {
                        bean.setBidValidity(validity[i]);
                        canUpdate = true;
                    }
                    if (bean.getIncoterm() != Integer.parseInt(incoterm[i])) {
                        bean.setIncoterm(Integer.parseInt(incoterm[i]));
                        canUpdate = true;
                    }
                    if (canUpdate) {
                        try {
                            tenderDAO.updateBidClosingReportVendorAfterOpeningReport(bean);
                        } catch (Exception ex) {
                        }
                    }
                }

            }
        }
    }

    private TenderEvaluateVendorBean getVendor(ArrayList vendors, String venId) {
        TenderEvaluateVendorBean bean = null;
        int vendor = Integer.parseInt(venId);
        for (int i = 0; i < vendors.size(); i++) {
            bean = (TenderEvaluateVendorBean) vendors.get(i);
            if (bean.getVenId() == vendor) {
                vendors.remove(bean);
                return bean;
            }
        }
        return null;
    }

    @Override
    protected String getFunction() {
        return PermissionUtil.FUNC_EDIT + "";
    }

    @Override
    protected int getPermit() {
        return PermissionUtil.PER_PURCHASING_TENDERPLAN;
    }
}
