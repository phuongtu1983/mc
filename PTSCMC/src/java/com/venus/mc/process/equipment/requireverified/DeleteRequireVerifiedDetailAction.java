/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.process.equipment.requireverified;

import com.venus.core.auth.OnlineUser;
import com.venus.core.util.LogUtil;
import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.RequireVerifiedDetailBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.RequireVerifiedDAO;
import com.venus.mc.util.Constants;
import com.venus.mc.util.MCUtil;
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
public class DeleteRequireVerifiedDetailAction extends SpineAction {

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
        String[] arrDetId = request.getParameterValues("chkDetId");
        RequireVerifiedFormBean formBean = (RequireVerifiedFormBean) form;
        OnlineUser user = MCUtil.getOnlineUser(session);
        LogUtil.info("Username : " + user.getName() + " - Form : " + mapping.getPath() + " - DELETE");
        int length = 0;
        RequireVerifiedDAO requestDAO = new RequireVerifiedDAO();
        if (arrDetId != null) {
            length = arrDetId.length;
        }
        ArrayList arrDetails = new ArrayList();
        String[] detId = formBean.getDetId();
        if (detId != null) {
            for (int i = 0; i < detId.length; i++) {
                if (MCUtil.isInSet(detId[i], arrDetId)) {
                    int nId = NumberUtil.parseInt(detId[i], 0);
                    if (nId > 0) {
                        try {
                            requestDAO.deleteRequireVerifiedDetail(detId[i]);
                        } catch (Exception ex) {
                            LogUtil.error("DeleteRequireVerifiedDetailAction: " + ex.getMessage());
                        }
                    }
                } else {
                    RequireVerifiedDetailBean detail = new RequireVerifiedDetailBean();
                    detail.setDetId(detId[i]);
                    detail.setEquId(formBean.getEquId()[i]);
                    detail.setUsedCode(formBean.getUseCode()[i]);
                    detail.setEquipmentName(formBean.getEquipmentName()[i]);
                    detail.setComment(formBean.getComment()[i]);
                    detail.setTimeEstimate(formBean.getTimeEstimate()[i]);
                    arrDetails.add(detail);
                }
            }
        }
        request.setAttribute(Constants.REQUIREVERIFIED_DETAIL_LIST, arrDetails);
        //OutputUtil.sendStringToOutput(response, "deleted");
        return true;
    }
}

