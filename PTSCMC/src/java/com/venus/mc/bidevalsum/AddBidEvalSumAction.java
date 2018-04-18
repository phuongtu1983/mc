/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.bidevalsum;

import com.venus.core.auth.OnlineUser;
import com.venus.core.util.LogUtil;
import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.BidEvalSumBean;
import com.venus.mc.bean.BidEvalSumGroupBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.BidEvalSumDAO;
import com.venus.mc.dao.BidEvalSumVendorDAO;
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
 * @author mai vinh loc
 */
public class AddBidEvalSumAction extends SpineAction {

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
        BidEvalSumFormBean formBean = (BidEvalSumFormBean) form;
        int besId = formBean.getBesId();

        boolean bNew = false;
        if (besId == 0) {
            bNew = true;
        } else {
            bNew = false;
        }
        BidEvalSumDAO bidDAO = new BidEvalSumDAO();
        if (bNew) {
            try {
                BidEvalSumBean b = bidDAO.getBidEvalSum(formBean.getTenId());
                if (b != null) {
                    bNew = false;
                }
            } catch (Exception ex) {
            }
        }

        BidEvalSumBean bean = new BidEvalSumBean();
        bean.setBesId(formBean.getBesId());
        bean.setTenId(formBean.getTenId());
        bean.setBesNumber(formBean.getBesNumber());
        bean.setCreatedDate(formBean.getCreatedDate());


        BidEvalSumVendorDAO bidVendorDAO = new BidEvalSumVendorDAO();

        try {
            if (bNew) {
                bean.setEmpId(MCUtil.getMemberID(request.getSession()));
                bean.setBesId(bidDAO.insertBidEvalSumId(bean));
            } else {
                HttpSession session = request.getSession();
                OnlineUser user = MCUtil.getOnlineUser(session);
                LogUtil.info("Username : " + user.getName() + " - Form : " + mapping.getPath() + " - EDIT");
                bean.setEmpId(MCUtil.getMemberID(request.getSession()));
                bidDAO.updateBidEvalSum(bean);
            }
            addEvalGroup(bean.getBesId(), formBean);
            addMaterial(formBean);
            bidDAO.updateRequestDetail(bean);
            bidVendorDAO.insertBidEvalSumMaterialForBidEvalSum(bean.getBesId(), formBean.getTenId());
        } catch (Exception ex) {
            LogUtil.error("FAILED:Vender:add-" + ex.getMessage());
            ex.printStackTrace();
        }
        return true;
    }

    private BidEvalSumGroupBean evalGroupExisted(ArrayList arrEvalGroup, int besgId) {
        BidEvalSumGroupBean bean = null;
        for (int i = 0; i < arrEvalGroup.size(); i++) {
            bean = (BidEvalSumGroupBean) arrEvalGroup.get(i);
            if (bean.getBesgId() == besgId) {
                return bean;
            }
        }
        return null;
    }

    private void addEvalGroup(int besId, BidEvalSumFormBean formBean) {
        BidEvalSumDAO bidDAO = new BidEvalSumDAO();
        ArrayList arrDet = null;
        if (besId != 0) {
            try {
                arrDet = bidDAO.getBidEvalSumGroup(formBean.getTenId());
            } catch (Exception ex) {
                arrDet = new ArrayList();
            }
        }
        if (formBean.getEvalId() != null) {
            String[] detIds = formBean.getEvalId();
            BidEvalSumGroupBean detBean = null;
            boolean canUpdate = false;
            for (int i = 0; i < detIds.length; i++) {
                if (!detIds[i].equals("0")) {//old
                    try {
                        detBean = evalGroupExisted(arrDet, Integer.parseInt(detIds[i]));
                        if (detBean != null) {
                            if (!detBean.getEmployee().equals(formBean.getEvalEmployee()[i])) {
                                detBean.setEmployee(formBean.getEvalEmployee()[i]);
                                canUpdate = true;
                            }
                            if (!detBean.getPosition().equals(formBean.getEvalPosition()[i])) {
                                detBean.setPosition(formBean.getEvalPosition()[i]);
                                canUpdate = true;
                            }
                            if (!detBean.getNote().equals(formBean.getEvalNote()[i])) {
                                detBean.setNote(formBean.getEvalNote()[i]);
                                canUpdate = true;
                            }
                            if (canUpdate) {
                                bidDAO.updateBidEvalSumGroup(detBean);
                            }
                        }
                    } catch (Exception ex) {
                    }
                } else {//new
                    try {
                        detBean = new BidEvalSumGroupBean(0, formBean.getEvalEmployee()[i], formBean.getEvalPosition()[i], formBean.getEvalNote()[i]);
                        detBean.setBesId(besId);
                        bidDAO.insertBidEvalSumGroup(detBean);
                    } catch (Exception ex) {
                    }
                }
            }
        }
    }

    private void addMaterial(BidEvalSumFormBean formBean) {
        BidEvalSumDAO bidDAO = new BidEvalSumDAO();
        try {
//            if (formBean.getEvalId() != null) {
//                String[] detIds = formBean.getConfirm();
//                for (int i = 0; i < detIds.length; i++) {
//
//                    if (NumberUtil.parseInt(formBean.getConfirm()[i], 0) > 0) {
//                        bidDAO.updateTenderPlanDetail(NumberUtil.parseInt(formBean.getDetIdTp()[i], 0));
//                    }
//
//                }
//            }
            if (formBean.getConfirm() != null) {
                String[] detIds = formBean.getConfirm();
                for (int i = 0; i < detIds.length; i++) {

                    if (NumberUtil.parseInt(detIds[i], 0) > 0) {
                        bidDAO.updateTenderPlanDetail(NumberUtil.parseInt(formBean.getDetIdTp()[i], 0));
                    }

                }
            }
        } catch (Exception ex) {
        }
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
