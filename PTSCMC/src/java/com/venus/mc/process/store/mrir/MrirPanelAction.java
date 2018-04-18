/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.venus.mc.process.store.mrir;

import com.venus.core.util.LogUtil;
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
public class MrirPanelAction extends SpineAction {

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
        ArrayList arrPro = getProjectList();
        request.setAttribute(Constants.MRIR_PROJECT_LIST, arrPro);
        return true;
    } 
    
    protected ArrayList getProjectList() {
        OrganizationDAO orgDAO = new OrganizationDAO();
        ArrayList arrOrg = null;
        try {
            arrOrg = orgDAO.getProjectList();
        } catch (Exception ex) {
            LogUtil.error("MrirFormAction:" + ex.getMessage());
        }
        if (arrOrg != null){
        LabelValueBean label = new LabelValueBean();
        label.setValue("all");
        label.setLabel(MCUtil.getBundleString("message.all"));
        arrOrg.add(0, label);
        
        label = new LabelValueBean();
        label.setValue("company");
        label.setLabel(MCUtil.getBundleString("message.Company"));
        arrOrg.add(1, label);
        }
        return arrOrg;
    }

    @Override
    protected String getXmlMessage() {
        return "mrirPanel";
    }
}
