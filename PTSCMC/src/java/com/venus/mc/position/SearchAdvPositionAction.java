/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.venus.mc.position;

import com.venus.core.util.LogUtil;
import com.venus.mc.bean.PositionBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.PositionDAO;
import com.venus.mc.util.Constants;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import java.util.ArrayList;
/**
 *
 * @author kngo
 */
public class SearchAdvPositionAction extends SpineAction {

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
        SearchAdvPositionFormBean formBean = (SearchAdvPositionFormBean) form;
        PositionBean bean = new PositionBean();

        bean.setName(formBean.getName());

        ArrayList positionList = null;
        PositionDAO positionDAO = new PositionDAO();
        try {
            positionList = positionDAO.searchAdvPosition(bean);
        } catch (Exception ex) {
            LogUtil.error("FAILED: searchAdvPosition-" + ex.getMessage());
            ex.printStackTrace();
        }
        request.setAttribute(Constants.POSITION_LIST, positionList);
        return true;
    }
}
