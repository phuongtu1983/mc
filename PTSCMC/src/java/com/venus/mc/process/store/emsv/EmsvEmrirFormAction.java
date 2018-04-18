/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.process.store.emsv;

import com.venus.core.util.DateUtil;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.EmsvDAO;
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
public class EmsvEmrirFormAction extends SpineAction {

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
        String emsvId = request.getParameter("emsvId");
        if (GenericValidator.isBlankOrNull(emsvId)) {
            ArrayList arrMrir = null;
            try {
                EmsvDAO emsvDAO = new EmsvDAO();
                arrMrir = emsvDAO.getEmrir4Emsv();
            } catch (Exception ex) {
                Logger.getLogger(EmsvEmrirFormAction.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (arrMrir == null) {
                arrMrir = new ArrayList();
            }
            arrMrir.add(0,new LabelValueBean(MCUtil.getBundleString("message.msv.mrirsel"), "0"));
            EmsvFormBean formBean = new EmsvFormBean();
            formBean.setEmrirId(0);
            formBean.setCreatedDate(DateUtil.today("dd/MM/yyyy"));
            formBean.setCreatedEmpName(MCUtil.getMemberFullName(request.getSession()));
            formBean.setCreatedEmpId(MCUtil.getMemberID(request.getSession()));
            
            request.setAttribute(Constants.EMSV_OBJ,formBean);
            request.setAttribute(Constants.MRIR_LIST, arrMrir);
        }
        return true;
    }
}
