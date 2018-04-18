/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.process.equipment.timeusing;

import com.venus.core.auth.OnlineUser;
import com.venus.core.util.LogUtil;
import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.TimeUsingBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.TimeUsingDAO;
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
public class AddTimeUsingAction extends SpineAction {

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
        TimeUsingFormBean formBean = (TimeUsingFormBean) form;
        TimeUsingDAO hrDAO = new TimeUsingDAO();
        String strDate ;
        boolean bNew = true;
        try {
            bNew = !hrDAO.checkTimeUsing(formBean.getUpdateDate());
        } catch (Exception ex) {
            LogUtil.error(getClass(), ex.getMessage());
        }
        int[] equId = formBean.getEquId();
        if (equId != null) {
            if (!bNew) {
                OnlineUser user = MCUtil.getOnlineUser(session);
                LogUtil.info("Username : " + user.getName() + " - Form : " + mapping.getPath() + " - EDIT");
            }
            for (int i = 0; i < equId.length; i++) {
                TimeUsingBean bean = new TimeUsingBean();
                bean.setTuId(formBean.getTuId()[i]);
                bean.setUpdateDate(formBean.getUpdateDate());
                bean.setTimeUsed(formBean.getTimeUsed()[i]);
                bean.setEquId(equId[i]);
                strDate = formBean.getUpdateDate();
                if (bNew) {
                    bean.setCreatedEmpId(MCUtil.getMemberID(session));
                    try {
                        hrDAO.insertTimeUsing(bean);
                    } catch (Exception ex) {
                        LogUtil.error(getClass(), ex.getMessage());
                    }
                } else {
                    try {
                        hrDAO.updateTimeUsing(bean);
                    } catch (Exception ex) {
                        LogUtil.error(getClass(), ex.getMessage());
                    }
                }
                session.setAttribute("id", strDate);
            }

        }

        return true;
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
