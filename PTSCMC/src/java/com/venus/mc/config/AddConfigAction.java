/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.config;

import com.venus.core.util.LogUtil;
import com.venus.core.util.OutputUtil;
import com.venus.mc.bean.SystemConfigBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.SystemConfigDAO;
import com.venus.mc.util.PermissionUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author phuongtu
 */
public class AddConfigAction extends SpineAction {

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
        ConfigFormBean formBean = (ConfigFormBean) form;
        String result = "";
        try {
            insertConfig(formBean.getId(), SystemConfigBean.DNTO, formBean.getDnTO());
            insertConfig(formBean.getId(), SystemConfigBean.DNCC, formBean.getDnCC());
            insertConfig(formBean.getId(), SystemConfigBean.INVOICETO, formBean.getInvoiceTO());
            insertConfig(formBean.getId(), SystemConfigBean.INVOICECC, formBean.getInvoiceCC());
            insertConfig(formBean.getId(), SystemConfigBean.MRIRTO, formBean.getMrirTO());
            insertConfig(formBean.getId(), SystemConfigBean.MRIRCC, formBean.getMrirCC());
            insertConfig(formBean.getId(), SystemConfigBean.MSVTO, formBean.getMsvTO());
            insertConfig(formBean.getId(), SystemConfigBean.MSVCC, formBean.getMsvCC());
            insertConfig(formBean.getId(), SystemConfigBean.REPAIRTO, formBean.getRepairTO());
            insertConfig(formBean.getId(), SystemConfigBean.REPAIRCC, formBean.getRepairCC());
            insertConfig(formBean.getId(), SystemConfigBean.AUDITTO, formBean.getAuditTO());
            insertConfig(formBean.getId(), SystemConfigBean.AUDITCC, formBean.getAuditCC());
            insertConfig(formBean.getId(), SystemConfigBean.MATINTO, formBean.getMatInTO());
            insertConfig(formBean.getId(), SystemConfigBean.MATINCC, formBean.getMatInCC());
            insertConfig(formBean.getId(), SystemConfigBean.MATOUTTO, formBean.getMatOutTO());
            insertConfig(formBean.getId(), SystemConfigBean.MATOUTCC, formBean.getMatOutCC());
            result = "true";
        } catch (Exception ex) {
        }
        OutputUtil.sendStringToOutput(response, result);
        return true;
    }

    private void insertConfig(int id, int type, String value) {
        SystemConfigDAO configDAO = new SystemConfigDAO();
        SystemConfigBean bean = new SystemConfigBean(id, type, value);
        try {
            if (id == 0) {
                configDAO.insertConfig(bean);
            } else {
                configDAO.updateConfig(bean);
            }
        } catch (Exception ex) {
            LogUtil.error("FAILED:Config:add-" + ex.getMessage());
            ex.printStackTrace();
        }
    }

    @Override
    protected String getFunction() {
        return PermissionUtil.FUNC_EDIT + "";
    }

    @Override
    protected int getPermit() {
        return PermissionUtil.PER_SYSTEM;
    }
}
