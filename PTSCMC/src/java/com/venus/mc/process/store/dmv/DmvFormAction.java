/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.process.store.dmv;

import com.venus.core.util.DateUtil;
import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.DmvBean;
import com.venus.mc.bean.DnBean;
import com.venus.mc.bean.OrganizationBean;
import com.venus.mc.bean.RequestBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.DmvDAO;
import com.venus.mc.dao.DnDAO;
import com.venus.mc.dao.EmployeeDAO;
import com.venus.mc.dao.RequestDAO;
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
public class DmvFormAction extends SpineAction {

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
        DmvFormBean formBean = null;
        String dmvId = request.getParameter("dmvId");
        DmvDAO dmvDAO = new DmvDAO();
        if (!GenericValidator.isBlankOrNull(dmvId)) {
            try {
                DmvBean bean = dmvDAO.getDmv(Integer.parseInt(dmvId));
                if (bean != null) {
                    formBean = new DmvFormBean(bean);
                }
            } catch (Exception ex) {
            }
            ArrayList arrMaterial = null;
            try {
                arrMaterial = dmvDAO.getMaterialsFromDmv(Integer.parseInt(dmvId));
            } catch (Exception ex) {
            }
            request.setAttribute(Constants.MATERIAL_LIST, arrMaterial);
        } else {
            int dnId = NumberUtil.parseInt(request.getParameter("dnId"), 0);
            int reqId = NumberUtil.parseInt(request.getParameter("reqId"), 0);
            if (dnId > 0 && reqId > 0) { //da co thong bao giao hang     
                DnDAO dnDAO = new DnDAO();
                try {
                    formBean = dnDAO.createNewDmvFromDn(dnId);
                } catch (Exception ex) {
                }
                if (formBean == null) {
                    formBean = new DmvFormBean();
                }
                formBean.setCreatedDate(DateUtil.today("dd/MM/yyyy"));
                formBean.setCreatedEmpName(MCUtil.getMemberFullName(request.getSession()));
                formBean.setCreatedEmpId(MCUtil.getMemberID(request.getSession()));
                formBean.setDnId(dnId);
                formBean.setReqId(reqId);

                RequestDAO reqDAO = new RequestDAO();
                try {
                    reqDAO.getRequestForDmv(reqId, formBean);
                } catch (Exception ex) {
                }

                ArrayList arrMat = null;
                try {
                    arrMat = dnDAO.getDnDetailForDmv(dnId,reqId);
                    request.setAttribute(Constants.MATERIAL_LIST, arrMat);
                } catch (Exception ex) {

                }

            } else {
                formBean = new DmvFormBean();
                formBean.setCreatedDate(DateUtil.today("dd/MM/yyyy"));
                formBean.setCreatedEmpName(MCUtil.getMemberFullName(request.getSession()));
                formBean.setCreatedEmpId(MCUtil.getMemberID(request.getSession()));
            }
        }
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
        request.setAttribute(Constants.DMV_OBJ, formBean);
        return true;
    }
    
    @Override
    protected String getXmlMessage() {
        return "dmvForm";
    }

    @Override
    protected String getXmlParameters(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        if (!GenericValidator.isBlankOrNull(request.getParameter("dmvId"))) {
            return request.getParameter("dmvId");
        } else {
            return "";
        }
    }
}
