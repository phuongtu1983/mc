/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.venus.mc.groupmaterial;

import com.venus.core.auth.OnlineUser;
import com.venus.core.util.LogUtil;
import com.venus.mc.bean.GroupMaterialBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.GroupMaterialDAO;
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
 * @author kngo
 */
public class AddGroupMaterialAction extends SpineAction {

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
        GroupMaterialFormBean formBean = (GroupMaterialFormBean) form;
        GroupMaterialDAO groupMaterialDAO = new GroupMaterialDAO();
        GroupMaterialBean bean = null;
        boolean bNew = false;
        boolean isExist = false;

        try {
            bean = groupMaterialDAO.getGroupMaterialByName(formBean.getName());
        } catch (Exception ex) {
        }

        int groId = formBean.getGroId();
        if (groId == 0) {
            bNew = true;
            if (bean != null) {
                isExist = true;
            }
        } else {
            bNew = false;
            if (bean != null && bean.getGroId() != groId) {
                isExist = true;
            }
       }
        if (isExist) {
            ActionMessages errors = new ActionMessages();
            errors.add("groupMaterialExisted", new ActionMessage("errors.groupmaterial.existed"));
            saveErrors(request, errors);
            return false;
        }

        bean = new GroupMaterialBean();
        bean.setGroId(formBean.getGroId());
        bean.setName(formBean.getName());
        bean.setNote(formBean.getNote());

        try {
            if (bNew) {
                groupMaterialDAO.insertGroupMaterial(bean);
            } else {
                HttpSession session = request.getSession();
                OnlineUser user = MCUtil.getOnlineUser(session);
                LogUtil.info("Username : " + user.getName() + " - Form : " + mapping.getPath() + " - EDIT");
                groupMaterialDAO.updateGroupMaterial(bean);
            }
        } catch (Exception ex) {
            LogUtil.error("FAILED:GroupMaterial:add-" + ex.getMessage());
            ex.printStackTrace();
        }
        return true;
    }
    
    
    @Override
    protected String getFunction() {
        return PermissionUtil.FUNC_EDIT + "";
    }

    @Override
    protected int getPermit() {
        return PermissionUtil.PER_LIBRARY_VENDOR;
    }
}
