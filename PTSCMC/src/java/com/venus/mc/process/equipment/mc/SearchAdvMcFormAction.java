/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.process.equipment.mc;

import com.venus.mc.bean.McBean;
import com.venus.mc.bean.OrganizationBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.OrganizationDAO;
import com.venus.mc.util.Constants;
import com.venus.mc.util.MCUtil;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.LabelValueBean;

/**
 *
 * @author kngo
 */
public class SearchAdvMcFormAction extends SpineAction {

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
//        HttpSession session = request.getSession();
        McBean bean = new McBean();
        request.setAttribute(Constants.MC_SEARCH_ADV, bean);

        try {
            OrganizationDAO orgDAO = new OrganizationDAO();
            ArrayList arrOrg = orgDAO.getOrganizations();
            OrganizationBean orgBean = new OrganizationBean();
            orgBean.setOrgId(0);
            orgBean.setName("");
            arrOrg.add(0, orgBean);
            request.setAttribute(Constants.ORGANIZATION_LIST, arrOrg);
        } catch (Exception ex) {
        }

        ArrayList arrKind = new ArrayList();
        LabelValueBean value;
        value = new LabelValueBean();
        value.setLabel("");
        value.setValue("0");
        arrKind.add(value);
        value = new LabelValueBean();
        value.setLabel(MCUtil.getBundleString("message.mc.kind1"));
        value.setValue("1");
        arrKind.add(value);
        value = new LabelValueBean();
        value.setLabel(MCUtil.getBundleString("message.mc.kind2"));
        value.setValue("2");
        arrKind.add(value);
        request.setAttribute(Constants.MC_KIND_LIST, arrKind);

        return true;
    }
}
