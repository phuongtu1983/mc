/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.process.store.rfm;

import com.venus.core.util.NumberUtil;
import com.venus.core.util.StringUtil;
import com.venus.mc.bean.OrganizationBean;
import com.venus.mc.bean.RfmBean;
import com.venus.mc.bean.StoreBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.OrganizationDAO;
import com.venus.mc.dao.StoreDAO;
import com.venus.mc.util.Constants;
import com.venus.mc.util.MCUtil;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.LabelValueBean;

/**
 *
 * @author mai vinh loc
 */
public class SearchAdvRfmFormAction extends SpineAction {

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
        int kind = NumberUtil.parseInt(request.getParameter("kind"), 0);
        RfmBean bean = new RfmBean();
        bean.setKind(kind);
        request.setAttribute(Constants.RFM, bean);

        //ORG
        ArrayList orgList = null;
        try {
            OrganizationDAO orgDAO = new OrganizationDAO();
            orgList = orgDAO.getOrgByKind(0);
        } catch (Exception ex) {
        }
        ArrayList arrOrg = new ArrayList();
        LabelValueBean value;
        value = new LabelValueBean();
        value.setLabel(MCUtil.getBundleString("message.organization.select"));;
        value.setValue("0");
        arrOrg.add(value);
        for (int i=0;i<orgList.size();i++) {
            OrganizationBean org = (OrganizationBean)orgList.get(i);
            value = new LabelValueBean();
            value.setLabel(String.valueOf(StringUtil.decodeString(org.getName())));
            value.setValue(String.valueOf(org.getOrgId()));
            arrOrg.add(value);
        }
        request.setAttribute(Constants.ORG_LIST, arrOrg);

        ArrayList proList = null;
        try {
            OrganizationDAO orgDAO = new OrganizationDAO();
            proList = orgDAO.getOrgByKind(OrganizationBean.KIND_PROJECT);
        } catch (Exception ex) {
        }
        ArrayList arrPro = new ArrayList();
  //    LabelValueBean value;
        value = new LabelValueBean();
        value.setLabel(MCUtil.getBundleString("message.project.select"));
        value.setValue("0");
        arrPro.add(value);
        for (int i=0;i<proList.size();i++) {
            OrganizationBean pro = (OrganizationBean)proList.get(i);
            value = new LabelValueBean();
            value.setLabel(String.valueOf(StringUtil.decodeString(pro.getName())));
            value.setValue(String.valueOf(pro.getOrgId()));
            arrPro.add(value);
        }
        request.setAttribute(Constants.PROJECT_LIST, arrPro);

        ArrayList stoList = null;
        try {
            StoreDAO stoDAO = new StoreDAO();
            stoList = stoDAO.getStores(1);
        } catch (Exception ex) {
        }
        ArrayList arrSto = new ArrayList();
      //  LabelValueBean value;
        value = new LabelValueBean();
        value.setLabel(MCUtil.getBundleString("message.store.select"));
        value.setValue("0");
        arrSto.add(value);
        for (int i=0;i<stoList.size();i++) {
            StoreBean sto = (StoreBean)stoList.get(i);
            value = new LabelValueBean();
            value.setLabel(String.valueOf(StringUtil.decodeString(sto.getName())));
            value.setValue(String.valueOf(sto.getStoId()));
            arrSto.add(value);
        }
        request.setAttribute(Constants.STORE_LIST, arrSto);

        return true;
    }
}
