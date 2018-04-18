
package com.venus.mc.library.materialnotcode;

/**
 * @author Mai Vinh Loc
 */

import com.venus.mc.material.*;
import com.venus.core.auth.OnlineUser;
import com.venus.core.util.LogUtil;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.MaterialDAO;
import com.venus.mc.util.MCUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class DeleteSpeAction extends SpineAction {

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
        String[] arrMaterialid = request.getParameterValues("matId");
        try {
            OnlineUser user = MCUtil.getOnlineUser(session);
            LogUtil.info("Username : " + user.getName() + " - Form : " + mapping.getPath() + " - DELETE");
            int length = 0;
            MaterialDAO materialDAO = new MaterialDAO();
            if (arrMaterialid != null) {
                length = arrMaterialid.length;
            }
            for (int i = 0; i < length; i++) {
                materialDAO.deleteMaterial(arrMaterialid[i]);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return true;
    }
   
}
