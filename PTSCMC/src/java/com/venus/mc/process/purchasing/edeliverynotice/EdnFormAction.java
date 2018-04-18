/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.process.purchasing.edeliverynotice;

import com.venus.core.util.DateUtil;
import com.venus.core.util.NumberUtil;
import com.venus.core.util.StringUtil;
import com.venus.mc.bean.EdnBean;
import com.venus.mc.bean.OrganizationBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.EdnDAO;
import com.venus.mc.dao.OrganizationDAO;
import com.venus.mc.util.Constants;
import com.venus.mc.util.MCUtil;
import com.venus.mc.util.PermissionUtil;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.LabelValueBean;

/**
 *
 * @author mai vinh loc
 */
public class EdnFormAction extends SpineAction {

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
        HttpSession session = request.getSession();
        EdnBean bean = null;
        String dnId = request.getParameter("dnId");
        Integer id = (Integer) session.getAttribute("id");
        if (id != null) {
            dnId = id + "";
        }
        //session.removeAttribute("id");
        //     int stoId = NumberUtil.parseInt(request.getParameter("stoId"),0);
        //     int kind = NumberUtil.parseInt(request.getParameter("kind"),0);

        if (!GenericValidator.isBlankOrNull(dnId)) {
            EdnDAO dnDAO = new EdnDAO();
            ArrayList arrRequestDetail = null;
            try {
                bean = dnDAO.getDn(NumberUtil.parseInt(dnId, 0));
                arrRequestDetail = dnDAO.getDnDetails(Integer.parseInt(dnId));
            } catch (Exception ex) {
            }
            if (arrRequestDetail == null) {
                arrRequestDetail = new ArrayList();
            }
            request.setAttribute(Constants.DN_DETAIL_LIST, arrRequestDetail);
        }
        if (bean == null) {
            bean = new EdnBean();
            bean.setCreatedDate(DateUtil.today("dd/MM/yyyy"));
            bean.setExpectedDate(DateUtil.today("dd/MM/yyyy"));
        }
        request.setAttribute(Constants.DN, bean);

        ArrayList arrWhichUse = new ArrayList();
        LabelValueBean value;
        value = new LabelValueBean();
        value.setLabel(MCUtil.getBundleString("message.select"));
        value.setValue("0");
        arrWhichUse.add(value);
        value = new LabelValueBean();
        value.setLabel(MCUtil.getBundleString("message.mrir.conId1"));
        value.setValue("1");
        arrWhichUse.add(value);
        value = new LabelValueBean();
        value.setLabel(MCUtil.getBundleString("message.mrir.conId2"));
        value.setValue("2");
        arrWhichUse.add(value);
        value = new LabelValueBean();
        value.setLabel(MCUtil.getBundleString("message.mrir.conId3"));
        value.setValue("3");
        arrWhichUse.add(value);
        value = new LabelValueBean();
        value.setLabel(MCUtil.getBundleString("message.dn.drId"));
        value.setValue("4");
        arrWhichUse.add(value);
        request.setAttribute(Constants.DN_WHICHUSE_LIST, arrWhichUse);

        //ORG
        ArrayList orgList = null;
        try {
            OrganizationDAO orgDAO = new OrganizationDAO();
            orgList = orgDAO.getOrgByKind(0);
        } catch (Exception ex) {
        }
        ArrayList arrOrg = new ArrayList();
        //LabelValueBean value;
        value = new LabelValueBean();
        value.setLabel(MCUtil.getBundleString("message.organization.select"));
        value.setValue("0");
        arrOrg.add(value);
        for (int i = 0; i < orgList.size(); i++) {
            OrganizationBean org = (OrganizationBean) orgList.get(i);
            value = new LabelValueBean();
            value.setLabel(String.valueOf(StringUtil.decodeString(org.getName())));
            value.setValue(String.valueOf(org.getOrgId()));
            arrOrg.add(value);
        }
        request.setAttribute(Constants.ORG_LIST, arrOrg);

        //Project
        ArrayList proList = null;
        try {
            OrganizationDAO proDAO = new OrganizationDAO();
            proList = proDAO.getOrgByKind(100);
        } catch (Exception ex) {
        }
        ArrayList arrPro = new ArrayList();
        //LabelValueBean value;
        value = new LabelValueBean();
        value.setLabel(MCUtil.getBundleString("message.project.select"));
        value.setValue("0");
        arrPro.add(value);
        for (int i = 0; i < proList.size(); i++) {
            OrganizationBean org = (OrganizationBean) proList.get(i);
            value = new LabelValueBean();
            value.setLabel(String.valueOf(StringUtil.decodeString(org.getName())));
            value.setValue(String.valueOf(org.getOrgId()));
            arrPro.add(value);
        }
        request.setAttribute(Constants.PRO_LIST, arrPro);

        return true;
    }

    @Override
    protected String getFunction() {
        return PermissionUtil.FUNC_VIEW + ";" + PermissionUtil.FUNC_EDIT;
    }

    @Override
    protected int getPermit() {
        return PermissionUtil.PER_PURCHASING_DELIVERYNOTICE_PROJECT;
    }
}
