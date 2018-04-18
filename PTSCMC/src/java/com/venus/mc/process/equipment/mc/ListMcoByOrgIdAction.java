/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.process.equipment.mc;

import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.McoBean;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import com.venus.mc.util.Constants;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.McoDAO;
import java.util.ArrayList;

/**
 *
 * @author kngo
 */
public class ListMcoByOrgIdAction extends SpineAction {

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
        int orgId = NumberUtil.parseInt(request.getParameter("orgId"), 0);

        try {
            if (orgId > 0) {
                McoDAO mcoDAO = new McoDAO();
                ArrayList arrMc = new ArrayList();
                arrMc = mcoDAO.getMcosByOrgId(orgId);
                McoBean mc0 = new McoBean();
                mc0.setMcoId(0);
                mc0.setMcoNumber("");
                arrMc.add(0, mc0);
                request.setAttribute(Constants.MCO_LIST, arrMc);

                McFormBean formBean = new McFormBean();
                request.setAttribute(Constants.MC, formBean);
            }
        } catch (Exception ex) {
        }
        return true;
    }
}
