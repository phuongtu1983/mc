/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.comclarification;

import com.venus.core.auth.OnlineUser;
import com.venus.core.util.LogUtil;
import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.ComClarificationBean;
import com.venus.mc.bean.ComClarificationListBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.ComClarificationDAO;
import com.venus.mc.dao.ComClarificationListDAO;
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
public class AddComClarificationAction extends SpineAction {

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
        ComClarificationFormBean formBean = (ComClarificationFormBean) form;
//        int ccId = NumberUtil.parseInt(session.getAttribute("ccId").toString(),0);
        int ccId = formBean.getCcId();
        boolean bNew = false;
        if (ccId==0) {
            bNew = true;
        } else {
            bNew = false;
        }

        ComClarificationBean bean = new ComClarificationBean();
        bean.setTenId(formBean.getTenId());
        bean.setCcId(ccId);
        bean.setCcNumber(formBean.getCcNumber());
        bean.setSubfix(formBean.getSubfix());
        bean.setCreatedDate(formBean.getCreatedDate());

        ComClarificationListBean beanList = new ComClarificationListBean();
        beanList.setReference(formBean.getReference());
        beanList.setClarification(formBean.getClarification());
        beanList.setSupplierReply(formBean.getSupplierReply());
        beanList.setStatus(formBean.getStatus());
        
        ComClarificationDAO comDAO = new ComClarificationDAO();
        ComClarificationListDAO comListDAO = new ComClarificationListDAO();

        try {
            if (bNew) {
                bean.setCcId(comDAO.insertComClarificationId(bean));
                session.setAttribute("ccId", bean.getCcId());
            } else {                
                OnlineUser user = MCUtil.getOnlineUser(session);
                LogUtil.info("Username : " + user.getName() + " - Form : " + mapping.getPath() + " - EDIT");
                comDAO.updateComClarification(bean);
            }
            if (formBean.getReference().length()>=1){
                beanList.setCcId(bean.getCcId());
                comListDAO.insertComClarificationList(beanList);
            }
        } catch (Exception ex) {
            LogUtil.error("FAILED:Vender:add-" + ex.getMessage());
            ex.printStackTrace();
        }
        return true;
    }
}
