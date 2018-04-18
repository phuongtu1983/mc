/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.payment;

import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.OrganizationDAO;
import com.venus.mc.util.Constants;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.LabelValueBean;

/**
 *
 * @author phuongtu
 */
public class PaymentReportFormAction extends SpineAction {

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
//        try {
//            OrganizationDAO orgDAO = new OrganizationDAO();
//            ArrayList orgList = orgDAO.getProjectList();
//            LabelValueBean organization = new LabelValueBean();
//            organization.setValue("0");
//            organization.setLabel("");
//            orgList.add(0, organization);
//            request.setAttribute(Constants.PRO_LIST, orgList);
//        } catch (Exception ex) {
//            Logger.getLogger(PaymentReportFormAction.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        try {
//            RequestDAO reqDAO = new RequestDAO();
//            reqDAO.setInvolvedEmps(PermissionUtil.getEmployeesHasPermission(request));
//            ArrayList reqList = reqDAO.getRequests();
//            RequestBean req = new RequestBean(0);
//            reqList.add(0, req);
//            request.setAttribute(Constants.REQUEST_LIST, reqList);
//        } catch (Exception ex) {
//            Logger.getLogger(RequestReportFormAction.class.getName()).log(Level.SEVERE, null, ex);
//        }

//        ArrayList orgList = null;
//        try {
//            OrganizationDAO orgDAO = new OrganizationDAO();
//            orgList = orgDAO.getOrgExceptKind(OrganizationBean.KIND_TO + "," + OrganizationBean.KIND_PROJECT);
//            OrganizationBean organization = new OrganizationBean(0);
//            orgList.add(0, organization);
//        } catch (Exception ex) {
//            orgList = new ArrayList();
//        }
//        if (orgList == null) {
//            orgList = new ArrayList();
//        }
//        request.setAttribute(Constants.ORG_LIST, orgList);

//        try {
//            HttpSession session = request.getSession();
//            RequestReportFormBean formBean = new RequestReportFormBean();
//            formBean.setOrgId(MCUtil.getOrganizationID(session));
//            request.setAttribute(Constants.FORM_OBJ, formBean);
//        } catch (Exception ex) {
//        }

        return true;
    }
}
