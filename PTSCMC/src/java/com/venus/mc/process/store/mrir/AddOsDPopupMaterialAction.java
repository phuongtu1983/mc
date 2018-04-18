/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.process.store.mrir;

import com.venus.core.util.LogUtil;
import com.venus.core.util.NumberUtil;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.MrirDAO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author mai vinh loc
 */
public class AddOsDPopupMaterialAction extends SpineAction {

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
        int matId = NumberUtil.parseInt(request.getParameter("matId"), 0);
        int detId = NumberUtil.parseInt(request.getParameter("detId"), 0);
        if (matId > 0) {
            MrirDAO mrirDAO = new MrirDAO();
            try {

                // Them moi Bao cao danh gia
                mrirDAO.updateOsDMatIdTemp(matId, detId);
                //
            } catch (Exception ex) {
                LogUtil.error("FAILED:TechEval:add-" + ex.getMessage());
                ex.printStackTrace();
            }

        }
        return true;
    }
}
