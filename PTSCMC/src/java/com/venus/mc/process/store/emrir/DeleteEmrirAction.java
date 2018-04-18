/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.venus.mc.process.store.emrir;

import com.venus.core.auth.OnlineUser;
import com.venus.core.util.LogUtil;
import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.EmrirBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.EmrirDAO;
import com.venus.mc.database.DBUtil;
import com.venus.mc.util.MCUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
/**
 *
 * @author kngo
 */
public class DeleteEmrirAction extends SpineAction {

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
        String[] arrEmrirId = request.getParameterValues("emrirId");
        try {
            OnlineUser user = MCUtil.getOnlineUser(session);
            LogUtil.info("Username : " + user.getName() + " - Form : " + mapping.getPath() + " - DELETE");
            int length = 0;
            EmrirDAO emrirDAO = new EmrirDAO();
            EmrirBean emrirBean = null;
            int emrirId = 0, ednId = 0;
            if (arrEmrirId != null) {
                length = arrEmrirId.length;
            }
            for (int i = 0; i < length; i++) {
                emrirId = NumberUtil.parseInt(arrEmrirId[i], 0);
                if (emrirId > 0) {
                    //if (DBUtil.checkExisted("Select * From emsv Where emrir_id = " + emrirId)) {
                    if (DBUtil.checkFieldExisted("emsv","emrir_id",emrirId)) {
                        ActionMessages errors = new ActionMessages();
                        errors.add("delExisted", new ActionMessage("errors.emrir.delExisted"));
                        saveErrors(request, errors);
                        return false;
                    } else {
                        emrirBean = emrirDAO.getEmrir(emrirId);
                        ednId = emrirBean.getEdnId();

                        emrirDAO.deleteEmrir(emrirId);
                        emrirDAO.updateEdeliveryNotice(ednId, 0);
                    }
                }
            }
        } catch (Exception ex) {
        }
        return true;
    }
}
