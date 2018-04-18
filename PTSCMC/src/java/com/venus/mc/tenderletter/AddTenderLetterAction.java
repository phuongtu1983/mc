/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.tenderletter;

import com.venus.core.auth.OnlineUser;
import com.venus.core.util.LogUtil;
import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.TenderLetterBean;
import com.venus.mc.bean.TenderLetterDetailBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.TenderLetterDAO;
import com.venus.mc.dao.TenderLetterDetailDAO;
import com.venus.mc.dao.TenderPlanDAO;
import com.venus.mc.util.MCUtil;
import com.venus.mc.util.PermissionUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author mai vinh loc
 */
public class AddTenderLetterAction extends SpineAction {

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
        TenderLetterFormBean formBean = (TenderLetterFormBean) form;
        int letId = formBean.getLetId();
        boolean bNew = false;
        if (letId == 0) {
            bNew = true;
        } else {
            bNew = false;
        }

        TenderLetterBean bean = new TenderLetterBean();
        bean.setLetId(formBean.getLetId());
        bean.setTenId(formBean.getTenId());
        bean.setCreatedDate(formBean.getCreatedDate());
        bean.setRecievedEmp1(formBean.getRecievedEmp1());
        bean.setRecievedEmp2(formBean.getRecievedEmp2());
        bean.setRegDetId(formBean.getRegDetId());

        TenderLetterDetailBean beanDetail = new TenderLetterDetailBean();

        TenderLetterDAO letterDAO = new TenderLetterDAO();
        TenderLetterDetailDAO tenderDetailDAO = new TenderLetterDetailDAO();

        String[] detIds = formBean.getRegDetId();

        try {
            addVendor(formBean);
            if (bNew) {
                formBean.setLetId(letterDAO.insertTenderLetter(bean));
                if (formBean.getLetId() > 0) {
                    for (int i = 0; i < detIds.length; i++) {
                        beanDetail.setLetId(formBean.getLetId());
                        beanDetail.setTevId(NumberUtil.parseInt(formBean.getTevId()[i], 0));
                        beanDetail.setSubfix(formBean.getSubfix()[i]);
                        tenderDetailDAO.insertTenderLetterDetail(beanDetail);
                    }
                }
            } else {
                HttpSession session = request.getSession();
                OnlineUser user = MCUtil.getOnlineUser(session);
                LogUtil.info("Username : " + user.getName() + " - Form : " + mapping.getPath() + " - EDIT");
                letterDAO.updateTenderLetter(bean);
                TenderPlanDAO tenderDAO = new TenderPlanDAO();
                int detId = 0;
                for (int i = 0; i < detIds.length; i++) {
                    detId = NumberUtil.parseInt(formBean.getRegDetId()[i], 0);
                    if (detId > 0) {
                        beanDetail.setDetId(detId);
                        beanDetail.setTenId(formBean.getTenId());
                        beanDetail.setLetId(formBean.getLetId());
                        beanDetail.setTevId(Integer.parseInt(formBean.getTevId()[i]));
                        beanDetail.setSubfix(formBean.getSubfix()[i]);
                        tenderDetailDAO.updateTenderLetterDetail(beanDetail);
                        /*if (tenderDAO.getBidded(formBean.getTenId(), formBean.getVenId()[i]) == 0) {
                            tenderDAO.deleteBidEvalSum(formBean.getTenId() + "");
                            tenderDAO.deletePriceComparision(formBean.getTenId() + "");
                            tenderDAO.deleteVendorTev(formBean.getTenId() + "", formBean.getTevId()[i] + "");
                            tenderDAO.deleteVendorMaterialTev(formBean.getTenId() + "", formBean.getTevId()[i] + "");
                        }*/

                    } else {
                        beanDetail.setLetId(formBean.getLetId());
                        beanDetail.setTevId(NumberUtil.parseInt(formBean.getTevId()[i], 0));
                        beanDetail.setSubfix(formBean.getSubfix()[i]);
                        tenderDetailDAO.insertTenderLetterDetail(beanDetail);
                    }
                }

            }


        } catch (Exception ex) {
            LogUtil.error("FAILED:Vender:add-" + ex.getMessage());
            ex.printStackTrace();
        }

        return true;
    }

    private void addVendor(TenderLetterFormBean formBean) {
        TenderPlanDAO tenderDAO = new TenderPlanDAO();
        if (formBean.getVenId() != null) {
            String[] venIds = formBean.getVenId();
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
