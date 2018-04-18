package com.venus.mc.process.equipment.requiresettling;

/**
 * @author Mai Vinh Loc
 */
import com.venus.core.util.DateUtil;
import com.venus.core.util.StringUtil;
import com.venus.mc.bean.RequireSettlingBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.RequireSettlingDAO;
import com.venus.mc.bean.OrganizationBean;
import com.venus.mc.bean.ProjectBean;
import com.venus.mc.bean.SurveyReportBean;
import com.venus.mc.dao.OrganizationDAO;
import com.venus.mc.dao.ProjectDAO;
import com.venus.mc.dao.SurveyReportDAO;
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

public class RequireSettlingFormAction extends SpineAction {

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
        RequireSettlingFormBean formBean = null;
        RequireSettlingBean bean = null;
        String rsId = request.getParameter("rsId");
        Integer id = (Integer) session.getAttribute("id");
        session.removeAttribute("id");
        if (id != null) {
            rsId = id + "";
        }
        
        if (!GenericValidator.isBlankOrNull(rsId)) {
            RequireSettlingDAO requestDAO = new RequireSettlingDAO();
            try {
                bean = requestDAO.getRequireSettling(Integer.parseInt(rsId));
                if (bean != null) {
                    formBean = new RequireSettlingFormBean(bean);
                }

            } catch (Exception ex) {
            }
        }
        if (formBean == null) {
            formBean = new RequireSettlingFormBean();
            formBean.setCreatedEmp(MCUtil.getMemberID(session));
            formBean.setStatus(RequireSettlingBean.STATUS1);
            formBean.setCreatedDate(DateUtil.today("dd/MM/yyyy"));
            formBean.setRequireDate(DateUtil.today("dd/MM/yyyy"));
        }

        request.setAttribute(Constants.REQUIRESETTLING, formBean);

        //Project
        ProjectDAO projectDAO = new ProjectDAO();
        ArrayList projectList = null;
        try {
            projectList = projectDAO.getProjects();
        } catch (Exception ex) {
        }
        ArrayList arrProject = new ArrayList();
        LabelValueBean value;
        value = new LabelValueBean();
        value.setLabel(MCUtil.getBundleString("message.project.select"));
        value.setValue("0");
        arrProject.add(value);
        for (int i = 0; i < projectList.size(); i++) {
            ProjectBean project = (ProjectBean) projectList.get(i);
            value = new LabelValueBean();
            value.setLabel(String.valueOf(StringUtil.decodeString(project.getName())));
            value.setValue(String.valueOf(project.getProId()));
            arrProject.add(value);
        }
        request.setAttribute(Constants.PROJECT_LIST, arrProject);

        //SurveyReport
        SurveyReportDAO surveyreportDAO = new SurveyReportDAO();
        ArrayList surveyreportList = null;
        try {
            surveyreportList = surveyreportDAO.getSurveyReports();
        } catch (Exception ex) {
        }
        ArrayList arrSurveyReport = new ArrayList();
        //LabelValueBean value;
        value = new LabelValueBean();
        value.setLabel(MCUtil.getBundleString("message.surveyreport.select"));
        value.setValue("0");
        arrSurveyReport.add(value);
        for (int i = 0; i < surveyreportList.size(); i++) {
            SurveyReportBean surveyreport = (SurveyReportBean) surveyreportList.get(i);
            value = new LabelValueBean();
            value.setLabel(String.valueOf(StringUtil.decodeString(surveyreport.getSrNumber())));
            value.setValue(String.valueOf(surveyreport.getSrId()));
            arrSurveyReport.add(value);
        }
        request.setAttribute(Constants.SURVEYREPORT_LIST, arrSurveyReport);

        //Org
        OrganizationDAO orgDAO = new OrganizationDAO();
        ArrayList orgList = null;
        try {
            orgList = orgDAO.getOrganizations();
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

        return true;
    }

    @Override
    protected String getFunction() {
        return PermissionUtil.FUNC_VIEW + ";" + PermissionUtil.FUNC_EDIT;
    }

    @Override
    protected int getPermit() {
        return PermissionUtil.PER_EQUIPMENT;
    }
}
