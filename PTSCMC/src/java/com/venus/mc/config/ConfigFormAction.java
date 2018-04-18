/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.config;

import com.venus.mc.bean.SystemConfigBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.SystemConfigDAO;
import com.venus.mc.util.Constants;
import com.venus.mc.util.PermissionUtil;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author phuongtu
 */
public class ConfigFormAction extends SpineAction {

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

        SystemConfigDAO configDAO = new SystemConfigDAO();
        ArrayList configList = null;
        try {
            configList = configDAO.getConfigs();
        } catch (Exception ex) {
            configList = new ArrayList();
        }
        if (configList == null) {
            configList = new ArrayList();
        }

        ConfigFormBean formBean = new ConfigFormBean();
        SystemConfigBean bean = null;
        for (int i = 0; i < configList.size(); i++) {
            bean = (SystemConfigBean) configList.get(i);
            if (bean.getType() == SystemConfigBean.AUDITTO) {
                formBean.setAuditTO(bean.getValue());
            }
            if (bean.getType() == SystemConfigBean.AUDITCC) {
                formBean.setAuditCC(bean.getValue());
            }
            if (bean.getType() == SystemConfigBean.DNTO) {
                formBean.setDnTO(bean.getValue());
            }
            if (bean.getType() == SystemConfigBean.DNCC) {
                formBean.setDnCC(bean.getValue());
            }
            if (bean.getType() == SystemConfigBean.INVOICETO) {
                formBean.setInvoiceTO(bean.getValue());
            }
            if (bean.getType() == SystemConfigBean.INVOICECC) {
                formBean.setInvoiceCC(bean.getValue());
            }
            if (bean.getType() == SystemConfigBean.MATINTO) {
                formBean.setMatInTO(bean.getValue());
            }
            if (bean.getType() == SystemConfigBean.MATINCC) {
                formBean.setMatInCC(bean.getValue());
            }
            if (bean.getType() == SystemConfigBean.MATOUTTO) {
                formBean.setMatOutTO(bean.getValue());
            }
            if (bean.getType() == SystemConfigBean.MATOUTCC) {
                formBean.setMatOutCC(bean.getValue());
            }
            if (bean.getType() == SystemConfigBean.MRIRTO) {
                formBean.setMrirTO(bean.getValue());
            }
            if (bean.getType() == SystemConfigBean.MRIRCC) {
                formBean.setMrirCC(bean.getValue());
            }
            if (bean.getType() == SystemConfigBean.MSVTO) {
                formBean.setMsvTO(bean.getValue());
            }
            if (bean.getType() == SystemConfigBean.MSVCC) {
                formBean.setMsvCC(bean.getValue());
            }
            if (bean.getType() == SystemConfigBean.REPAIRTO) {
                formBean.setRepairTO(bean.getValue());
            }
            if (bean.getType() == SystemConfigBean.REPAIRCC) {
                formBean.setRepairCC(bean.getValue());
            }
        }
        if (configList.size() > 0) {
            formBean.setId(1);
        }
        request.setAttribute(Constants.CONFIG, formBean);

        return true;
    }

    @Override
    protected String getFunction() {
        return PermissionUtil.FUNC_VIEW + ";" + PermissionUtil.FUNC_EDIT;
    }

    @Override
    protected int getPermit() {
        return PermissionUtil.PER_SYSTEM;
    }
}
