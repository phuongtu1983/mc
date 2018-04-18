/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.venus.mc.incoterm;

import com.venus.core.auth.OnlineUser;
import com.venus.core.util.LogUtil;
import com.venus.mc.bean.IncotermBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.IncotermDAO;
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
 * @author Mai Vinh Loc
 */
public class AddIncotermAction extends SpineAction {

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
        IncotermFormBean formBean = (IncotermFormBean) form;
        IncotermDAO incDAO = new IncotermDAO();
        IncotermBean bean = null;
        boolean bNew = false;
        boolean isExist = false;

        try {
            bean = incDAO.getIncotermByName(formBean.getIncName());
        } catch (Exception ex) {
        }

        int incId = formBean.getIncId();
        if (incId == 0) {
            bNew = true;
            if (bean != null) {
                isExist = true;
            }
        } else {
            bNew = false;
            if (bean != null && bean.getIncId() != incId) {
                isExist = true;
            }
       }
        if (isExist) {
            ActionMessages errors = new ActionMessages();
            errors.add("webExisted", new ActionMessage("errors.web.existed"));
            saveErrors(request, errors);
            return false;
        }

        bean = new IncotermBean();
        bean.setIncId(formBean.getIncId());
        bean.setIncName(formBean.getIncName());
        bean.setComment(formBean.getComment());

        try {
            if (bNew) {
                incDAO.insertIncoterm(bean);
            } else {
                HttpSession session = request.getSession();
                OnlineUser user = MCUtil.getOnlineUser(session);
                LogUtil.info("Username : " + user.getName() + " - Form : " + mapping.getPath() + " - EDIT");
                incDAO.updateIncoterm(bean);
            }
        } catch (Exception ex) {
            LogUtil.error("FAILED:Incoterm:add-" + ex.getMessage());
            ex.printStackTrace();
        }
        return true;
    }
}
