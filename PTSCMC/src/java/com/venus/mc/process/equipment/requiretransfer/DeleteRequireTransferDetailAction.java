/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.process.equipment.requiretransfer;

import com.venus.core.auth.OnlineUser;
import com.venus.core.util.LogUtil;
import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.RequireTransferDetailBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.RequireTransferDAO;
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
public class DeleteRequireTransferDetailAction extends SpineAction {

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
        RequireTransferFormBean formBean = (RequireTransferFormBean) form;
        OnlineUser user = MCUtil.getOnlineUser(session);
        LogUtil.info("Username : " + user.getName() + " - Form : " + mapping.getPath() + " - DELETE");
        int length = 0;
        RequireTransferDAO requestDAO = new RequireTransferDAO();
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
                            requestDAO.deleteRequireTransferDetail(detId[i]);
                        } catch (Exception ex) {
                            LogUtil.error(getClass().getName() +": " + ex.getMessage());
                        }
                    }
                } else {
                    RequireTransferDetailBean detail = new RequireTransferDetailBean();
                    detail.setDetId(detId[i]);
                    detail.setEquId(formBean.getEquId()[i]);
                    detail.setUsedCode(formBean.getUseCode()[i]);
                    detail.setEquipmentName(formBean.getEquipmentName()[i]);
                    detail.setStatus(formBean.getStatus()[i]);
                    detail.setQuantity(NumberUtil.parseDouble(formBean.getQuantity()[i],0));                    
                    detail.setTimeEstimate(formBean.getTimeEstimate()[i]);
                    arrDetails.add(detail);
                }
            }
        }
        request.setAttribute(Constants.REQUIRETRANSFER_DETAIL_LIST, arrDetails);
        //OutputUtil.sendStringToOutput(response, "deleted");
        return true;
    }
}

