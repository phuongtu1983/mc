/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.venus.mc.material;

import com.venus.core.auth.OnlineUser;
import com.venus.core.util.LogUtil;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.MaterialDAO;
import com.venus.mc.util.MCUtil;
import com.venus.mc.util.PermissionUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
/**
 *
 * @author kngo
 */
public class DeleteMaterialPriceAction  extends SpineAction {
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
        String[] arrAutoId = request.getParameterValues("autoId");
        try {
            OnlineUser user = MCUtil.getOnlineUser(session);
            LogUtil.info("Username : " + user.getName() + " - Form : " + mapping.getPath() + " - DELETE");
            int length = 0;
            MaterialDAO materialDAO = new MaterialDAO();
            if (arrAutoId != null) {
                length = arrAutoId.length;
            }
            String ids = "0";
            for (int i = 0; i < length; i++) {
                ids += "," + arrAutoId[i];
            }
            materialDAO.deleteMaterialPrice(ids);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return true;
    }
    
    @Override
    protected String getFunction() {
        return PermissionUtil.FUNC_DELETE + "";
    }

    @Override
    protected int getPermit() {
        return PermissionUtil.PER_LIBRARY_MATERIAL_PRICE;
    }
}
