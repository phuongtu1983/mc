/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.position;

import com.venus.core.auth.OnlineUser;
import com.venus.core.util.LogUtil;
import com.venus.mc.bean.PositionBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.PositionDAO;
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
public class AddPositionAction extends SpineAction {

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
        PositionFormBean formBean = (PositionFormBean) form;
        PositionDAO positionDAO = new PositionDAO();
        PositionBean bean = null;
        boolean bNew = false;
        boolean isExist = false;

        try {
            bean = positionDAO.getPositionByName(formBean.getName());
        } catch (Exception ex) {
        }

        int posId = formBean.getPosId();
        if (posId == 0) {
            bNew = true;
            if (bean != null) {
                isExist = true;
            }
        } else {
            bNew = false;
            if (bean != null && bean.getPosId() != posId) {
                isExist = true;
            }
        }
        if (isExist) {
            ActionMessages errors = new ActionMessages();
            errors.add("positionExisted", new ActionMessage("errors.position.existed"));
            saveErrors(request, errors);
            return false;
        }

        bean = new PositionBean();
        bean.setPosId(formBean.getPosId());
        bean.setName(formBean.getName());

        try {
            if (bNew) {
                positionDAO.insertPosition(bean);
            } else {
                HttpSession session = request.getSession();
                OnlineUser user = MCUtil.getOnlineUser(session);
                LogUtil.info("Username : " + user.getName() + " - Form : " + mapping.getPath() + " - EDIT");
                positionDAO.updatePosition(bean);
            }
        } catch (Exception ex) {
            LogUtil.error("FAILED:Position:add-" + ex.getMessage());
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
        return PermissionUtil.PER_SYSTEM;
    }
}
