/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.process.equipment.requireverified;

import com.venus.core.auth.OnlineUser;
import com.venus.core.util.LogUtil;
import com.venus.mc.bean.RequireVerifiedBean;
import com.venus.mc.bean.RequireVerifiedDetailBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.RequireVerifiedDAO;
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
public class AddRequireVerifiedAction extends SpineAction {

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
        RequireVerifiedFormBean formBean = (RequireVerifiedFormBean) form;
        RequireVerifiedDAO requireVerifiedDAO = new RequireVerifiedDAO();
        int rvId = formBean.getRvId();
        boolean bNew = false;
        if (rvId == 0) {
            bNew = true;
        } else {
            bNew = false;
        }
        RequireVerifiedBean bean = new RequireVerifiedBean();
        bean.setRvId(rvId);
        bean.setRvNumber(formBean.getRvNumber());
        bean.setCreatedEmpId(MCUtil.getMemberID(session));
        bean.setCreatedDate(formBean.getCreatedDate());
        bean.setReason(formBean.getReason());

        boolean isExist = false;
        try {
            isExist = requireVerifiedDAO.checkRvNumber(formBean.getRvId(), formBean.getRvNumber());
        } catch (Exception ex) {
            LogUtil.error("AddRequireVerifiedAction: " + ex.getMessage());
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
                rvId = requireVerifiedDAO.insertRequireVerified(bean);
            } catch (Exception ex) {
                LogUtil.error("AddRequireVerifiedAction: " + ex.getMessage());
            }
        } else {
            OnlineUser user = MCUtil.getOnlineUser(session);
            LogUtil.info("Username : " + user.getName() + " - Form : " + mapping.getPath() + " - EDIT");
            try {
                requireVerifiedDAO.updateRequireVerified(bean);
            } catch (Exception ex) {
                LogUtil.error("AddRequireVerifiedAction: " + ex.getMessage());
            }
        }
        session.setAttribute("id", rvId);
        addDetails(rvId, formBean);
        return true;
    }

    private void addDetails(int rvId, RequireVerifiedFormBean formBean) {
        if (rvId > 0) {
            String[] detId = formBean.getDetId();
            if (detId != null) {
                RequireVerifiedDAO dao = new RequireVerifiedDAO();
                for (int i = 0; i < detId.length; i++) {
                    RequireVerifiedDetailBean detailBean = new RequireVerifiedDetailBean();
                    detailBean.setRvId(rvId);
                    detailBean.setComment(formBean.getComment()[i]);
                    detailBean.setDetId(formBean.getDetId()[i]);
                    detailBean.setEquId(formBean.getEquId()[i]);
                    detailBean.setTimeEstimate(formBean.getTimeEstimate()[i]);
                    if (detailBean.getDetId().indexOf("rvd_") == 0) { //dong moi
                        try {
                            dao.insertRequireVerifiedDetail(detailBean);
                        } catch (Exception ex) {
                            LogUtil.error("AddRequireVerifiedAction: " + ex.getMessage());
                        }
                    } else {
                        try {
                            dao.updateRequireVerifiedDetail(detailBean);
                        } catch (Exception ex) {
                            LogUtil.error("UpdateRequireVerifiedAction: " + ex.getMessage());
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
