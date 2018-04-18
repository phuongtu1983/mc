/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.process.purchasing.deliverynotice;

import com.venus.core.util.StringUtil;
import com.venus.mc.bean.OrganizationBean;
import com.venus.mc.bean.ProjectBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.OrganizationDAO;
import com.venus.mc.dao.StoreDAO;
import com.venus.mc.util.Constants;
import com.venus.mc.util.MCUtil;
import com.venus.mc.util.PermissionUtil;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.LabelValueBean;

/**
 *
 * @author mai vinh loc
 */
public class DnPanelAction extends SpineAction {

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

        OrganizationDAO orgDAO = new OrganizationDAO();
        try {
            ArrayList proList = orgDAO.getOrganizations(100); //DU AN
            ArrayList arrPro = new ArrayList();
        LabelValueBean value;
        value = new LabelValueBean();
        value.setLabel(MCUtil.getBundleString("message.project.select"));
        value.setValue("0");
        arrPro.add(value);

        for (int i = 0; i < proList.size(); i++) {
            OrganizationBean pro = (OrganizationBean) proList.get(i);
            value = new LabelValueBean();
            value.setLabel(String.valueOf(StringUtil.decodeString(pro.getName())));
            value.setValue(String.valueOf(pro.getOrgId()));
            arrPro.add(value);
        }
            request.setAttribute(Constants.PROJECT_LIST, arrPro);
        } catch (Exception ex) {
        }
        return true;
    }

    @Override
    protected String getXmlMessage() {
        return "dnPanel";
    }

    @Override
    protected String getFunction() {
        return PermissionUtil.FUNC_LIST + "";
    }

    /*
    @Override
    protected int getPermit() {
    return PermissionUtil.PER_PURCHASING;
    }
     */
}
