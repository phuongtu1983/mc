/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.tenderplan;

import com.venus.core.auth.OnlineUser;
import com.venus.core.util.LogUtil;
import com.venus.mc.bean.BidClosingGroupBean;
import com.venus.mc.bean.BidClosingReportBean;
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
public class AddBidClosingAction extends SpineAction {

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
        BidClosingReportFormBean formBean = (BidClosingReportFormBean) form;
        BidClosingReportBean bean = null;
        try {
            TenderPlanDAO tenderDAO = new TenderPlanDAO();
            bean = tenderDAO.getBidClosingReportByTenId(formBean.getTenId());
        } catch (Exception ex) {
        }
        int bcrId = formBean.getBcrId();

        bean = new BidClosingReportBean();
        bean.setTenId(formBean.getTenId());
        bean.setReportNumber(formBean.getReportNumber());
        bean.setClosingDate(formBean.getClosingDate());
        bean.setClosingTime(formBean.getClosingTime());
        bean.setEndClosingDate(formBean.getEndClosingDate());
        bean.setEndClosingTime(formBean.getEndClosingTime());
        bean.setComments(formBean.getComments());

        try {
            TenderPlanDAO tenderDAO = new TenderPlanDAO();
            if (formBean.getBcrId() == 0) {
                bcrId = tenderDAO.insertBidClosingReport(bean);
            } else {
                HttpSession session = request.getSession();
                OnlineUser user = MCUtil.getOnlineUser(session);
                LogUtil.info("Username : " + user.getName() + " - Form : " + mapping.getPath() + " - EDIT");
                tenderDAO.updateBidClosingReport(bean);
            }
            addBidClosingGroup(bcrId, formBean);
        //addVendor(formBean); khong dung nua, di chuyen qua Thu Gui NCC roi
        } catch (Exception ex) {
            LogUtil.error("FAILED:BidClosing:add-" + ex.getMessage());
            ex.printStackTrace();
        }
        return true;
    }

    private BidClosingGroupBean closingGroupExisted(ArrayList arrGroup, int bcgId) {
        BidClosingGroupBean bean = null;
        for (int i = 0; i < arrGroup.size(); i++) {
            bean = (BidClosingGroupBean) arrGroup.get(i);
            if (bean.getBcgId() == bcgId) {
                return bean;
            }
        }
        return null;
    }

    private void addBidClosingGroup(int bcrId, BidClosingReportFormBean formBean) {
        TenderPlanDAO tenderDAO = new TenderPlanDAO();
        ArrayList arrDet = null;
        if (bcrId != 0) {
            try {
                arrDet = tenderDAO.getBidClosingGroup(bcrId);
            } catch (Exception ex) {
                arrDet = new ArrayList();
            }
        }
        if (formBean.getBcgId() != null) {
            String[] detIds = formBean.getBcgId();
            BidClosingGroupBean detBean = null;
            boolean canUpdate = false;
            for (int i = 0; i < detIds.length; i++) {
                canUpdate = false;
                if (!detIds[i].equals("0")) {//old
                    try {
                        detBean = closingGroupExisted(arrDet, Integer.parseInt(detIds[i]));
                        if (detBean != null) {
                            if (!detBean.getEmployee().equals(formBean.getBidClosingEmployee()[i])) {
                                detBean.setEmployee(formBean.getBidClosingEmployee()[i]);
                                canUpdate = true;
                            }
                            if (!detBean.getPosition().equals(formBean.getBidClosingPosition()[i])) {
                                detBean.setPosition(formBean.getBidClosingPosition()[i]);
                                canUpdate = true;
                            }
                            if (!detBean.getNote().equals(formBean.getBidClosingNote()[i])) {
                                detBean.setNote(formBean.getBidClosingNote()[i]);
                                canUpdate = true;
                            }
                            if (canUpdate) {
                                tenderDAO.updateBidClosingGroup(detBean);
                            }
                        }
                    } catch (Exception ex) {
                    }
                } else {//new
                    try {
                        detBean = new BidClosingGroupBean(0, formBean.getBidClosingEmployee()[i], formBean.getBidClosingPosition()[i], formBean.getBidClosingNote()[i]);
                        detBean.setBcrId(bcrId);
                        tenderDAO.insertBidClosingGroup(detBean);
                    } catch (Exception ex) {
                    }
                }
            }
        }
    }

    private void addVendor(BidClosingReportFormBean formBean) {
        TenderPlanDAO tenderDAO = new TenderPlanDAO();
        if (formBean.getVendorId() != null) {
            String[] venIds = formBean.getVendorId();
            String[] checkes = formBean.getVendorBidded();
            int bidded = 0;
            for (int i = 0; i < venIds.length; i++) {
                if (getCheckVendor(checkes, venIds[i])) {
                    bidded = 1;
                } else {
                    bidded = 0;
                }
                try {
                    tenderDAO.updateBidClosingReportVendorBidded(formBean.getTenId(), venIds[i], bidded);
                } catch (Exception ex) {
                }
            }
        }
    }

    private boolean getCheckVendor(String[] checks, String venId) {
        if (checks == null) {
            return false;
        }
        for (int i = 0; i < checks.length; i++) {
            if (checks[i].equals(venId)) {
                return true;
            }
        }
        return false;
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
