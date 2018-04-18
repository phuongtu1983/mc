/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.techclarification;

import com.venus.core.auth.OnlineUser;
import com.venus.core.util.LogUtil;
import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.TechClarificationBean;
import com.venus.mc.bean.TechClarificationListBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.TechClarificationDAO;
import com.venus.mc.dao.TechClarificationListDAO;
import com.venus.mc.util.MCUtil;
import com.venus.mc.util.PermissionUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author mai vinh loc
 */
public class AddTechClarificationAction extends SpineAction {

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
        TechClarificationFormBean formBean = (TechClarificationFormBean) form;
        int tcId = NumberUtil.parseInt(session.getAttribute("tcId").toString(), 0);
        boolean bNew = false;
        if (tcId == 0) {
            bNew = true;
        } else {
            bNew = false;
        }

        TechClarificationBean bean = new TechClarificationBean();
        bean.setTerId(formBean.getTerId());
        bean.setTcId(tcId);
        bean.setTcNumber(formBean.getTcNumber());
        bean.setSubfix(formBean.getSubfix());
        bean.setCreatedDate(formBean.getCreatedDate());

        TechClarificationListBean beanList = new TechClarificationListBean();
        beanList.setDiscipline(formBean.getDiscipline());
        beanList.setNote(formBean.getNotes());

        TechClarificationDAO techDAO = new TechClarificationDAO();
        TechClarificationListDAO techListDAO = new TechClarificationListDAO();

        try {
            if (bNew) {
                bean.setTcId(techDAO.insertTechClarificationId(bean));
                session.setAttribute("tcId", bean.getTcId());
            } else {
                OnlineUser user = MCUtil.getOnlineUser(session);
                LogUtil.info("Username : " + user.getName() + " - Form : " + mapping.getPath() + " - EDIT");
                techDAO.updateTechClarification(bean);
            }
            if (formBean.getDiscipline().length() >= 1) {
                beanList.setCategory("");
                techListDAO.insertTechClarificationList(beanList, bean.getTcId());
            }
        } catch (Exception ex) {
            LogUtil.error("FAILED:Vender:add-" + ex.getMessage());
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
        return PermissionUtil.PER_PURCHASING_TENDERPLAN;
    }
}
