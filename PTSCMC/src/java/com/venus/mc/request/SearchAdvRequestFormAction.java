/*
 *
 * Created on April 14, 2007, 8:49 AM
 */
package com.venus.mc.request;

import com.venus.mc.bean.EmployeeBean;
import com.venus.mc.bean.OrganizationBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.EmployeeDAO;
import com.venus.mc.dao.OrganizationDAO;
import com.venus.mc.util.Constants;
import com.venus.mc.util.MCUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import java.util.ArrayList;
import org.apache.struts.util.LabelValueBean;

/**
 *
 * @author HOANG DIEU
 * @version
 */
public class SearchAdvRequestFormAction extends SpineAction {

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
        ArrayList arrWhichUse = new ArrayList();
        LabelValueBean value;
        value = new LabelValueBean();
        value.setLabel(MCUtil.getBundleString("message.select"));
        value.setValue("0");
        arrWhichUse.add(value);
        value = new LabelValueBean();
        value.setLabel(MCUtil.getBundleString("message.request.whichUse.project"));
        value.setValue(RequestFormBean.WHICHUSE_PROJECT + "");
        arrWhichUse.add(value);
        value = new LabelValueBean();
        value.setLabel(MCUtil.getBundleString("message.request.whichUse.organization"));
        value.setValue(RequestFormBean.WHICHUSE_ORGANIZATION + "");
        arrWhichUse.add(value);
        value = new LabelValueBean();
        value.setLabel(MCUtil.getBundleString("message.request.whichUse.workshop"));
        value.setValue(RequestFormBean.WHICHUSE_WORKSHOP + "");
        arrWhichUse.add(value);
        value = new LabelValueBean();
        value.setLabel(MCUtil.getBundleString("message.request.whichUse.team"));
        value.setValue(RequestFormBean.WHICHUSE_TEAM + "");
        arrWhichUse.add(value);
        request.setAttribute(Constants.REQUEST_WHICHUSE_LIST, arrWhichUse);
        request.setAttribute(Constants.REQUEST, new SearchAdvRequestFormBean());
        ArrayList empList = null;
        try {
            EmployeeDAO employeeDAO = new EmployeeDAO();
            empList = employeeDAO.getEmployees();
            EmployeeBean empBean = new EmployeeBean(0);
            empBean.setFullname("");
            empList.add(0, empBean);
        } catch (Exception ex) {
        }
        if (empList == null) {
            empList = new ArrayList();
        }
        request.setAttribute(Constants.EMPLOYEE_LIST, empList);

        ArrayList orgList = null;
        try {
            OrganizationDAO orgDAO = new OrganizationDAO();
            orgList = orgDAO.getOrganizations();
            orgList.add(0, new OrganizationBean());
        } catch (Exception ex) {
        }
        if (orgList == null) {
            orgList = new ArrayList();
        }
        request.setAttribute(Constants.ORG_LIST, orgList);
        return true;
    }
}
