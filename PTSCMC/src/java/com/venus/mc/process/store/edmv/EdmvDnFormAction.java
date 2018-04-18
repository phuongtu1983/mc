/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.process.store.edmv;

import com.venus.mc.process.store.dmv.*;
import com.venus.mc.process.store.mrv.*;
import com.venus.core.util.DateUtil;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.DeliveryNoticeDAO;
import com.venus.mc.dao.EdmvDAO;
import com.venus.mc.dao.MrirDAO;
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
public class EdmvDnFormAction extends SpineAction {

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
        String edmvId = request.getParameter("edmvId");
        if (GenericValidator.isBlankOrNull(edmvId)) {
            ArrayList arrDn = null;
            try {
                EdmvDAO dnDAO = new EdmvDAO();
                arrDn = dnDAO.getEdnList();
            } catch (Exception ex) {
                Logger.getLogger(EdmvDnFormAction.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (arrDn == null) {
                arrDn = new ArrayList();
            }
            arrDn.add(0, new LabelValueBean(MCUtil.getBundleString("message.select"), "0"));

            EdmvFormBean formBean = new EdmvFormBean();
            formBean.setCreatedDate(DateUtil.today("dd/MM/yyyy"));
            formBean.setCreatedEmpName(MCUtil.getMemberFullName(request.getSession()));
            formBean.setCreatedEmpId(MCUtil.getMemberID(request.getSession()));
            request.setAttribute(Constants.EDMV_OBJ, formBean);
            request.setAttribute(Constants.DELIVERY_NOTICE_LIST, arrDn);
        }
        return true;
    }
}
