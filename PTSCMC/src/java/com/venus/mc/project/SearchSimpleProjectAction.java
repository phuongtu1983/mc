/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.venus.mc.project;

import com.venus.core.util.LogUtil;
import com.venus.mc.bean.SearchFormBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.ProjectDAO;
import com.venus.mc.util.Constants;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.venus.core.util.StringUtil;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import java.util.ArrayList;

/**
 *
 * @author kngo
 */
public class SearchSimpleProjectAction extends SpineAction {

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
        SearchFormBean projectForm = (SearchFormBean) form;
        int fieldid = projectForm.getSearchid();
        String strFieldvalue = projectForm.getSearchvalue();
        ArrayList projectList = null;
        ProjectDAO projectDAO = new ProjectDAO();
        try {
            projectList = projectDAO.searchSimpleProject(fieldid, StringUtil.encodeHTML(strFieldvalue));
        } catch (Exception ex) {
            LogUtil.error("FAILED: ProjectBean:searchSimpleProject-" + ex.getMessage());
            ex.printStackTrace();
        }
        request.setAttribute(Constants.PROJECT_LIST, projectList);
        return true;
    }

}
