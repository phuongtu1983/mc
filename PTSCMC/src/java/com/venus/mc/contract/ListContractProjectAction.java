/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.contract;

import com.venus.core.util.GenericValidator;
import com.venus.core.util.NumberUtil;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.ContractDAO;
import com.venus.mc.util.Constants;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author phuongtu
 */
public class ListContractProjectAction extends SpineAction {

    @Override
    public boolean doAction(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        ArrayList arrProject = null;
        if (!GenericValidator.isBlankOrNull(request.getParameter("conId"))) {
            try {
                ContractDAO contractDAO = new ContractDAO();
                int conId = NumberUtil.parseInt(request.getParameter("conId"), 0);
                ArrayList arr = contractDAO.getContractProjectCosts(conId);
                if (arr == null) {
                    arrProject = contractDAO.getContractProjects(conId);
                }
            } catch (Exception ex) {
            }
        }
        if (arrProject == null) {
            arrProject = new ArrayList();
        }
        request.setAttribute(Constants.PROJECT_LIST, arrProject);
        return true;
    }
}
