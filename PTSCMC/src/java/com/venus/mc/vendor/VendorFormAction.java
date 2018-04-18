/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.vendor;

import com.venus.core.util.StringUtil;
import com.venus.mc.bean.OrganizationBean;
import com.venus.mc.bean.VendorBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.OrganizationDAO;
import com.venus.mc.dao.VendorDAO;
import com.venus.mc.util.Constants;
import com.venus.mc.util.MCUtil;
import com.venus.mc.util.PermissionUtil;
import java.util.ArrayList;
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
public class VendorFormAction extends SpineAction {

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

        ArrayList arrStatus = new ArrayList();
        LabelValueBean value;
        value = new LabelValueBean();
        value.setLabel(MCUtil.getBundleString("message.vendor.status1"));
        value.setValue("1");
        arrStatus.add(value);
        value = new LabelValueBean();
        value.setLabel(MCUtil.getBundleString("message.vendor.status2"));
        value.setValue("2");
        arrStatus.add(value);
        request.setAttribute(Constants.VENDOR_STATUS_LIST, arrStatus);

        ArrayList arrKind = new ArrayList();
        value = new LabelValueBean();
        value.setLabel(MCUtil.getBundleString("message.vendor.kind.national"));
        value.setValue(VendorBean.KIND_NATIONAL + "");
        arrKind.add(value);
        value = new LabelValueBean();
        value.setLabel(MCUtil.getBundleString("message.vendor.kind.international"));
        value.setValue(VendorBean.KIND_INTERNATIONAL + "");
        arrKind.add(value);
        request.setAttribute(Constants.VENDOR_KIND_LIST, arrKind);
        
        //ORG
        ArrayList orgList = null;
        try {
            OrganizationDAO orgDAO = new OrganizationDAO();
            orgList = orgDAO.getOrgByDN();
        } catch (Exception ex) {
        }
        ArrayList arrOrg = new ArrayList();
        value = new LabelValueBean();
        value.setLabel(MCUtil.getBundleString("message.organization.select"));
        value.setValue("0");
        arrOrg.add(value);
        for (int i = 0; i < orgList.size(); i++) {
            OrganizationBean orgProcess = (OrganizationBean) orgList.get(i);
            value = new LabelValueBean();
            value.setLabel(String.valueOf(StringUtil.decodeString(orgProcess.getName())));
            value.setValue(String.valueOf(orgProcess.getOrgId()));
            arrOrg.add(value);
        }
        request.setAttribute(Constants.ORGANIZATION_LIST, arrOrg);

        VendorFormBean formBean = null;
        String venId = request.getParameter("venId");
        if (!GenericValidator.isBlankOrNull(venId)) {
            VendorDAO vendorDAO = new VendorDAO();
            try {
                VendorBean bean = vendorDAO.getVendor(Integer.parseInt(venId));
                if (bean != null) {
                    formBean = new VendorFormBean(bean);
                }
            } catch (Exception ex) {
            }
        }
        if (formBean == null) {
            formBean = new VendorFormBean();
        }
        request.setAttribute(Constants.VENDOR, formBean);
        return true;
    }

    @Override
    protected String getXmlMessage() {
        return "vendorDetail";
    }

    @Override
    protected String getXmlParameters(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        if (!GenericValidator.isBlankOrNull(request.getParameter("venId"))) {
            return request.getParameter("venId");
        } else {
            return "";
        }
    }

    @Override
    protected String getFunction() {
        return PermissionUtil.FUNC_VIEW + ";" + PermissionUtil.FUNC_EDIT;
    }

    @Override
    protected int getPermit() {
        return PermissionUtil.PER_LIBRARY_VENDOR;
    }
}
