/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.process.purchasing.deliverynotice;

import com.venus.core.auth.OnlineUser;
import com.venus.core.util.LogUtil;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.DnDAO;
import com.venus.mc.util.MCUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author mai vinh loc
 */
public class DeleteDnDetailAction extends SpineAction {

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
        String[] arrDetId = request.getParameterValues("detId");
        String[] reqDetailId = request.getParameterValues("reqDetailId");
        try {
            OnlineUser user = MCUtil.getOnlineUser(session);
            LogUtil.info("Username : " + user.getName() + " - Form : " + mapping.getPath() + " - DELETE");
            int length = 0;
            DnDAO dnDAO = new DnDAO();
            if (arrDetId != null) {
                length = arrDetId.length;
            }
            for (int i = 0; i < length; i++) {                
                dnDAO.updateContractDetailQtDnDelete(arrDetId[i], reqDetailId[i]);
                dnDAO.deleteDnDetail(arrDetId[i]);
            }
        } catch (Exception ex) {
            LogUtil.error("FAILED:DnDetail:delete-" + ex.getMessage());
            ex.printStackTrace();
        }
        return true;
    }
}
