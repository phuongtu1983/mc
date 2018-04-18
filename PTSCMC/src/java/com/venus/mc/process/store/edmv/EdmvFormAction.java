/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.process.store.edmv;


import com.venus.core.util.DateUtil;
import com.venus.core.util.NumberUtil;

import com.venus.mc.bean.EdmvBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.EdmvDAO;
import com.venus.mc.dao.EmployeeDAO;
import com.venus.mc.dao.OrganizationDAO;
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
public class EdmvFormAction extends SpineAction {

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
        EdmvFormBean formBean = null;
        String edmvId = request.getParameter("edmvId");
        EdmvDAO edmvDAO = new EdmvDAO();
        if (!GenericValidator.isBlankOrNull(edmvId)) {
            try {
                EdmvBean bean = edmvDAO.getEdmv(Integer.parseInt(edmvId));
                if (bean != null) {
                    formBean = new EdmvFormBean(bean);
                }
            } catch (Exception ex) {
            }
            ArrayList arrMaterial = null;
            try {
                arrMaterial = edmvDAO.getMaterialsFromEdmv(Integer.parseInt(edmvId));
            } catch (Exception ex) {                
            }
            request.setAttribute(Constants.MATERIAL_LIST, arrMaterial);
        } else {
            int ednId = NumberUtil.parseInt(request.getParameter("ednId"), 0);            
            if (ednId > 0) { //da co thong bao giao hang                     
                try {
                    formBean = edmvDAO.createNewEdmvFromEdn(ednId);
                } catch (Exception ex) {
                }
                if (formBean == null) {
                    formBean = new EdmvFormBean();
                }
                formBean.setCreatedDate(DateUtil.today("dd/MM/yyyy"));
                formBean.setCreatedEmpName(MCUtil.getMemberFullName(request.getSession()));
                formBean.setCreatedEmpId(MCUtil.getMemberID(request.getSession()));
                formBean.setEdnId(ednId);               

                ArrayList arrMat = null;
                try {
                    arrMat = edmvDAO.getEdnDetailForEdmv(ednId);
                    request.setAttribute(Constants.MATERIAL_LIST, arrMat);
                } catch (Exception ex) {

                }

            } else {
                formBean = new EdmvFormBean();
                formBean.setCreatedDate(DateUtil.today("dd/MM/yyyy"));
                formBean.setCreatedEmpName(MCUtil.getMemberFullName(request.getSession()));
                formBean.setCreatedEmpId(MCUtil.getMemberID(request.getSession()));
            }
        }
        OrganizationDAO orgDAO = new OrganizationDAO();
        try {
            ArrayList orgList = orgDAO.getOrganizationList();
            orgList.add(0,new LabelValueBean(MCUtil.getBundleString("message.organization.select"),"0"));
            request.setAttribute(Constants.ORG_LIST, orgList);
        } catch (Exception ex) {            
        }        
//        try {
//            ArrayList proList = orgDAO.getProjectList();
//            proList.add(0,new LabelValueBean(MCUtil.getBundleString("message.project.select"),"0"));
//            request.setAttribute(Constants.PRO_LIST, proList);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
        EmployeeDAO empDAO = new EmployeeDAO();
        try {            
            ArrayList empList = empDAO.getEmployeeListByOrg(formBean.getOrgId());
            empList.add(0,new LabelValueBean(MCUtil.getBundleString("message.select"),"0"));
            request.setAttribute(Constants.EMP_LIST, empList);
        } catch (Exception ex) {
        }
        ArrayList arrType = new ArrayList();
        LabelValueBean value = new LabelValueBean();
        value.setLabel(MCUtil.getBundleString("message.dmv.ccdc"));
        value.setValue("1");
        arrType.add(value);
        value = new LabelValueBean();
        value.setLabel(MCUtil.getBundleString("message.dmv.khac"));
        value.setValue("0");
        arrType.add(value);
        value = new LabelValueBean();
        value.setLabel(MCUtil.getBundleString("message.dmv.tscdhh"));
        value.setValue("2");
        arrType.add(value);
        request.setAttribute(Constants.DMV_KIND_LIST, arrType);        
        request.setAttribute(Constants.EDMV_OBJ, formBean);
        return true;
    }
    
    @Override
    protected String getXmlMessage() {
        return "edmvForm";
    }

    @Override
    protected String getXmlParameters(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        if (!GenericValidator.isBlankOrNull(request.getParameter("edmvId"))) {
            return request.getParameter("edmvId");
        } else {
            return "";
        }
    }
}
