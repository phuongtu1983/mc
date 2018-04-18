/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.process.equipment.requiretransfer;

import com.venus.core.auth.OnlineUser;
import com.venus.core.util.LogUtil;
import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.RequireTransferBean;
import com.venus.mc.bean.RequireTransferDetailBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.RequireTransferDAO;
import com.venus.mc.dao.EquipmentDAO;
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
public class AddRequireTransferAction extends SpineAction {

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
        RequireTransferFormBean formBean = (RequireTransferFormBean) form;
        RequireTransferDAO requireTransferDAO = new RequireTransferDAO();

        int rtId = formBean.getRtId();
        boolean bNew = false;
        if (rtId == 0) {
            bNew = true;
        } else {
            bNew = false;
        }
        RequireTransferBean bean = new RequireTransferBean();
        bean.setRtId(rtId);
        bean.setRtNumber(formBean.getRtNumber());
        bean.setCreatedEmpId(MCUtil.getMemberID(session));
        bean.setCreatedDate(formBean.getCreatedDate());
        bean.setReason(formBean.getReason());
        bean.setOrgId(formBean.getOrgId());
        bean.setOrgName(formBean.getOrgName());

        boolean isExist = false;
        try {
            isExist = requireTransferDAO.checkRtNumber(formBean.getRtId(), formBean.getRtNumber());
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
                rtId = requireTransferDAO.insertRequireTransfer(bean);
            } catch (Exception ex) {
                LogUtil.error(getClass(), ex.getMessage());
            }
        } else {
            OnlineUser user = MCUtil.getOnlineUser(session);
            LogUtil.info("Username : " + user.getName() + " - Form : " + mapping.getPath() + " - EDIT");
            try {
                requireTransferDAO.updateRequireTransfer(bean);
            } catch (Exception ex) {
                LogUtil.error(getClass(), ex.getMessage());
            }
        }
        session.setAttribute("id", rtId);
        addDetails(rtId, formBean);
        return true;
    }

    private void addDetails(int rtId, RequireTransferFormBean formBean) {
        if (rtId > 0) {
            String[] detId = formBean.getDetId();
            if (detId != null) {
                RequireTransferDAO dao = new RequireTransferDAO();
                EquipmentDAO equDAO = new EquipmentDAO();
                for (int i = 0; i < detId.length; i++) {
                    RequireTransferDetailBean detailBean = new RequireTransferDetailBean();
                    detailBean.setRtId(rtId);
                    detailBean.setStatus(formBean.getStatus()[i]);
                    detailBean.setDetId(formBean.getDetId()[i]);
                    detailBean.setEquId(formBean.getEquId()[i]);
                    detailBean.setTimeEstimate(formBean.getTimeEstimate()[i]);
                    //detailBean.setQuantity(NumberUtil.parseInt(formBean.getQuantity()[i], 0));
                    detailBean.setQuantity(1);
                    detailBean.setOrgId(formBean.getOrgId());
                    if (detailBean.getDetId().indexOf("rt_") == 0) { //dong moi
                        try {
                            dao.insertRequireTransferDetail(detailBean);
                            equDAO.updateOrgManager(formBean.getEquId()[i],formBean.getOrgId());
                        } catch (Exception ex) {
                            LogUtil.error(getClass(), ex.getMessage());
                        }
                    } else {
                        try {
                            dao.updateRequireTransferDetail(detailBean);
                            equDAO.updateOrgManager(formBean.getEquId()[i],formBean.getOrgId());
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
