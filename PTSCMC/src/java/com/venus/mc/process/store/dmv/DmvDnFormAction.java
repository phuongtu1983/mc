/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.process.store.dmv;

import com.venus.mc.process.store.mrv.*;
import com.venus.core.util.DateUtil;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.DnDAO;
import com.venus.mc.util.Constants;
import com.venus.mc.util.MCUtil;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.LabelValueBean;

/**
 *
 * @author phuongtu
 */
public class DmvDnFormAction extends SpineAction {

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
        String dmvId = request.getParameter("dmvId");
        if (GenericValidator.isBlankOrNull(dmvId)) {
            ArrayList arrDn = null;
            try {
                DnDAO dnDAO = new DnDAO();
                arrDn = dnDAO.getDeliveryNoticeList();
            } catch (Exception ex) {
                Logger.getLogger(DmvDnFormAction.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (arrDn == null) {
                arrDn = new ArrayList();
            }
            arrDn.add(0, new LabelValueBean(MCUtil.getBundleString("message.select"), "0"));
            
            ArrayList arrReq = new ArrayList();
            arrReq.add(0, new LabelValueBean(MCUtil.getBundleString("message.select"), "0"));
            
            DmvFormBean formBean = new DmvFormBean();            
            formBean.setCreatedDate(DateUtil.today("dd/MM/yyyy"));
            formBean.setCreatedEmpName(MCUtil.getMemberFullName(request.getSession()));
            formBean.setCreatedEmpId(MCUtil.getMemberID(request.getSession()));
            
            

            request.setAttribute(Constants.DMV_OBJ, formBean);
            request.setAttribute(Constants.DELIVERY_NOTICE_LIST, arrDn);
            request.setAttribute(Constants.REQUEST_LIST, arrReq);
        }
        return true;
    }
}
