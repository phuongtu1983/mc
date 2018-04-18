/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.process.equipment.handedreport;

import com.venus.core.auth.OnlineUser;
import com.venus.core.util.LogUtil;
import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.HandedReportBean;
import com.venus.mc.bean.HandedReportDetailBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.HandedReportDAO;
import com.venus.mc.util.MCUtil;
import com.venus.mc.util.PermissionUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

/**
 *
 * @author thuhc
 */
public class AddHandedReportAction extends SpineAction {

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
        HandedReportFormBean formBean = (HandedReportFormBean) form;
        HandedReportDAO hrDAO = new HandedReportDAO();
        int hrId = formBean.getHrId();
        boolean bNew = false;
        if (hrId == 0) {
            bNew = true;
        } else {
            bNew = false;
        }
        HandedReportBean bean = new HandedReportBean();
        bean.setHrId(hrId);
        bean.setHrNumber(formBean.getHrNumber());
        bean.setCreatedEmpId(MCUtil.getMemberID(session));
        bean.setCreatedDate(formBean.getCreatedDate());
        bean.setCreatedTime(formBean.getCreatedTime());
        bean.setCreatedLocation(formBean.getCreatedLocation());
        bean.setRtId(formBean.getRtId());
        bean.setOrgReceiveId(formBean.getOrgReceiveId());
        bean.setOrgId(MCUtil.getOrganizationID(session));
        bean.setEmpReceiveId(formBean.getEmpReceiveId());

        boolean isExist = false;
        try {
            isExist = hrDAO.checkHrNumber(formBean.getHrId(), formBean.getHrNumber());
        } catch (Exception ex) {
            LogUtil.error(getClass(), ex.getMessage());
            isExist = false;
        }
        if (isExist) {
            ActionMessages errors = new ActionMessages();
            errors.add("existedRrNumber", new ActionMessage("errors.requirerepair.existedRrNumber"));
            saveErrors(request, errors);
            return false;
        }
        if (bNew) {
            bean.setCreatedEmpId(MCUtil.getMemberID(request.getSession()));
            try {
                hrId = hrDAO.insertHandedReport(bean);
            } catch (Exception ex) {
                LogUtil.error(getClass(), ex.getMessage());
            }
        } else {
            OnlineUser user = MCUtil.getOnlineUser(session);
            LogUtil.info("Username : " + user.getName() + " - Form : " + mapping.getPath() + " - EDIT");
            try {
                hrDAO.updateHandedReport(bean);
            } catch (Exception ex) {
                LogUtil.error(getClass(), ex.getMessage());
            }
        }
        session.setAttribute("id", hrId);
        addDetails(hrId, formBean);
        return true;
    }

    private void addDetails(int hrId, HandedReportFormBean formBean) {
        if (hrId > 0) {
            String[] detId = formBean.getDetId();
            if (detId != null) {
                HandedReportDAO dao = new HandedReportDAO();
                for (int i = 0; i < detId.length; i++) {
                    HandedReportDetailBean detailBean = new HandedReportDetailBean();
                    detailBean.setHrId(hrId);
                    detailBean.setDetId(formBean.getDetId()[i]);
                    detailBean.setEquId(formBean.getEquId()[i]);
                    detailBean.setQuantity(NumberUtil.parseDouble(formBean.getQuantity()[i], 0));
                    detailBean.setComment(formBean.getComment()[i]);
                    detailBean.setSpecifications(formBean.getSpecifications()[i]);
                    if (detailBean.getDetId().indexOf("hr_") == 0) { //dong moi
                        try {
                            dao.insertHandedReportDetail(detailBean);
                        } catch (Exception ex) {
                            LogUtil.error(getClass(), ex.getMessage());
                        }
                    } else {
                        try {
                            dao.updateHandedReportDetail(detailBean);
                        } catch (Exception ex) {
                            LogUtil.error(getClass(), ex.getMessage());
                        }
                    }
                }
            }
        }
    }

    @Override
    protected String getFunction() {
        return PermissionUtil.FUNC_EDIT + "";
    }

    @Override
    protected int getPermit() {
        return PermissionUtil.PER_EQUIPMENT_REPAIRREQUEST;
    }
}
