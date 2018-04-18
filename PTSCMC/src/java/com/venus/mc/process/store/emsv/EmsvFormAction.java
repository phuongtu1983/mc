/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.process.store.emsv;

import com.venus.core.util.DateUtil;
import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.EmsvBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.EmsvDAO;
import com.venus.mc.dao.MrirDAO;
import com.venus.mc.dao.StoreDAO;
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

/**
 *
 * @author phuongtu
 */
public class EmsvFormAction extends SpineAction {

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
        EmsvFormBean formBean = null;
        String emsvId = request.getParameter("emsvId");
        if (!GenericValidator.isBlankOrNull(emsvId)) {
            EmsvDAO emsvDAO = new EmsvDAO();
            try {
                EmsvBean bean = emsvDAO.getEmsv(Integer.parseInt(emsvId));
                if (bean != null) {
                    formBean = new EmsvFormBean(bean);
                }
            } catch (Exception ex) {
            }
            ArrayList arrMaterial = null;
            try {
                arrMaterial = emsvDAO.getMaterialsFromEmsv(Integer.parseInt(emsvId));
            } catch (Exception ex) {
            }
            request.setAttribute(Constants.MATERIAL_LIST, arrMaterial);
        } else {
            EmsvDAO emsvDAO = new EmsvDAO();
            int emrirId = NumberUtil.parseInt(request.getParameter("emrirId"), 0);
//            EmsvBean bean = null;
            if (emrirId>0) {                
//                try {
//                    bean = emsvDAO.getNewEmsvFromEmrir(emrirId);
//                } catch (Exception ex) {
//                }
                ArrayList arrMaterial = null;
                try {
                    arrMaterial = emsvDAO.getMaterialsFromEmrir(emrirId);
                } catch (Exception ex) {
                }
                request.setAttribute(Constants.MATERIAL_LIST, arrMaterial);
            }
            formBean = new EmsvFormBean();
            formBean.setEmrirId(emrirId);            
            formBean.setCreatedDate(DateUtil.today("dd/MM/yyyy"));
            formBean.setCreatedEmpName(MCUtil.getMemberFullName(request.getSession()));
            formBean.setCreatedEmpId(MCUtil.getMemberID(request.getSession()));
//            if (bean != null) {
//                formBean.setStoId(bean.getStoId());
//                formBean.setStoName(bean.getStoName());
//            }
            ArrayList arrMrir = null;
            try {              
                arrMrir = emsvDAO.getEmrir4Emsv();
            } catch (Exception ex) {
                Logger.getLogger(EmsvFormAction.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (arrMrir == null) {
                arrMrir = new ArrayList();
            }
            request.setAttribute(Constants.MRIR_LIST, arrMrir);
        }
        if (formBean == null) {
            formBean = new EmsvFormBean();
            formBean.setCreatedDate(DateUtil.today("dd/MM/yyyy"));
            formBean.setCreatedEmpName(MCUtil.getMemberFullName(request.getSession()));
            formBean.setCreatedEmpId(MCUtil.getMemberID(request.getSession()));//                   
        }
        StoreDAO storeDAO = new StoreDAO();
        ArrayList storeList = null;
        try {
            storeList = storeDAO.getStoresLevel1();
        } catch (Exception ex) {            
        }
        request.setAttribute(Constants.STORE_LIST, storeList);
        
        request.setAttribute(Constants.EMSV_OBJ, formBean);
        return true;
    }
    
    @Override
    protected String getXmlMessage() {
        return "emsvForm";
    }

    @Override
    protected String getXmlParameters(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        if (!GenericValidator.isBlankOrNull(request.getParameter("emsvId"))) {
            return request.getParameter("emsvId");
        } else {
            return "";
        }
    }
}
