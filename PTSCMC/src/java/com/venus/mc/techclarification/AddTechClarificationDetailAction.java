/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.techclarification;

import com.venus.core.auth.OnlineUser;
import com.venus.core.util.LogUtil;
import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.TechClarificationListBean;
import com.venus.mc.bean.TechClarificationListDetailBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.TechClarificationListDAO;
import com.venus.mc.dao.TechClarificationListDetailDAO;
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
public class AddTechClarificationDetailAction extends SpineAction {

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
        TechClarificationDetailFormBean formBean = (TechClarificationDetailFormBean) form;
        int tclId = NumberUtil.parseInt(session.getAttribute("tclId").toString(),0);
        int detId = formBean.getDetId();
        boolean bNew = false;

        if (formBean.getReference().length()>0) {
            bNew = true;
        } else {
            bNew = false;
        }

        TechClarificationListDetailBean bean = new TechClarificationListDetailBean();
        bean.setDetId(formBean.getDetId());
        bean.setTclId(tclId);
        bean.setClarification(formBean.getClarification());
        bean.setSupplierReply(formBean.getSupplierReply());
        bean.setReference(formBean.getReference());
        bean.setSubcategory(formBean.getSubcategory());
        bean.setStatus(formBean.getStatus());

        TechClarificationListBean beanList = new TechClarificationListBean();
        beanList.setDiscipline(formBean.getDiscipline());
        beanList.setCategory(formBean.getCategory());
        beanList.setNote(formBean.getNote());
        beanList.setTclId(tclId);
        
        TechClarificationListDetailDAO techDAO = new TechClarificationListDetailDAO();
        TechClarificationListDAO techListDAO = new TechClarificationListDAO();

        try {
            if (bNew) {
                techDAO.insertTechClarificationListDetail(bean);
                //session.setAttribute("tclId", bean.getTcId());
            } else {                
                OnlineUser user = MCUtil.getOnlineUser(session);
                LogUtil.info("Username : " + user.getName() + " - Form : " + mapping.getPath() + " - EDIT");
                //techDAO.updateTechClarificationListDetail(bean);
            }
                techListDAO.updateTechClarificationListForDetail(beanList);
            
        } catch (Exception ex) {
            LogUtil.error("FAILED:Vender:add-" + ex.getMessage());
            ex.printStackTrace();
        }
        return true;
    }
}
