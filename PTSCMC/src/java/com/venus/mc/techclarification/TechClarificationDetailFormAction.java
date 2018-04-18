/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.techclarification;

import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.TechClarificationListBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.TechClarificationListDAO;
import com.venus.mc.util.Constants;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.LabelValueBean;


/**
 *
 * @author mai vinh loc
 */
public class TechClarificationDetailFormAction extends SpineAction {

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
        TechClarificationListBean bean = null;
        String note = request.getParameter("note");
        String tclId = request.getParameter("tclId");
        String discipline = request.getParameter("discipline");

        if (!GenericValidator.isBlankOrNull(tclId)) {
            TechClarificationListDAO techDAO = new TechClarificationListDAO();
            try {
                bean = techDAO.getTechClarificationList(NumberUtil.parseInt(tclId,0));
                if (bean == null) {
                    bean = new TechClarificationListBean();
                }
            } catch (Exception ex) {
            }
        }
               
        request.setAttribute(Constants.TECH_CLARIFICATION_DETAIL, bean);
        session.setAttribute("tclId", tclId);

        //Status
        ArrayList arr = new ArrayList();
        LabelValueBean value;
        value = new LabelValueBean();
        value.setLabel("Đóng");
        value.setValue("0");
        arr.add(value);        
        value = new LabelValueBean();
        value.setLabel("Mở");
        value.setValue("1");
        arr.add(value);

        request.setAttribute(Constants.STATUS_LIST,arr);

        return true;
    }
}
