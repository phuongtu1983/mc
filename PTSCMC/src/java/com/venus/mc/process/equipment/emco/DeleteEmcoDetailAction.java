/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.venus.mc.process.equipment.emco;
import com.venus.core.auth.OnlineUser;
import com.venus.core.util.LogUtil;
import com.venus.core.util.NumberUtil;
import com.venus.core.util.OutputUtil;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.EmcoDAO;
import com.venus.mc.util.MCUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
/**
 *
 * @author kngo
 */
public class DeleteEmcoDetailAction extends SpineAction {

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
        String[] arrDetId = request.getParameterValues("emcoEquipmentChk");
        try {
            OnlineUser user = MCUtil.getOnlineUser(session);
            LogUtil.info("Username : " + user.getName() + " - Form : " + mapping.getPath() + " - DELETE");
            int length = 0;
            EmcoDAO emcoDAO = new EmcoDAO();
            if (arrDetId != null) {
                length = arrDetId.length;
            }
            for (int i = 0; i < length; i++) {
                if (!arrDetId[i].equals("0")) {
                    emcoDAO.deleteEmcoDetail(NumberUtil.parseInt(arrDetId[i], 0));
                }
            }
            OutputUtil.sendStringToOutput(response, "deleted");
        } catch (Exception ex) {
        }
        return true;
    }

    @Override
    protected boolean isReturnStream() {
        return true;
    }
}
