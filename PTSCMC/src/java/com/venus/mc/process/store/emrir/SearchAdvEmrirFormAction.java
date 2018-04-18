/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.process.store.emrir;

import com.venus.mc.bean.EdeliveryNoticeBean;
import com.venus.mc.bean.EmrirBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.DeliveryNoticeDAO;
import com.venus.mc.util.Constants;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author kngo
 */
public class SearchAdvEmrirFormAction extends SpineAction {

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

//        HttpSession session = request.getSession();
        EmrirBean bean = new EmrirBean();
        request.setAttribute(Constants.EMRIR_SEARCH_ADV, bean);

        DeliveryNoticeDAO dnDAO = new DeliveryNoticeDAO();
        ArrayList arrDn = new ArrayList();
        try {
            arrDn = dnDAO.getEdeliveryNoticesForEmrir();
        } catch (Exception ex) {
        }
        EdeliveryNoticeBean dnBean = new EdeliveryNoticeBean(0);
        dnBean.setEdnNumber("");
        arrDn.add(0, dnBean);

        request.setAttribute(Constants.EMRIR_DELIVERY_NOTICE_LIST, arrDn);
        return true;
    }
}
