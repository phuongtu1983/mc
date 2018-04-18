/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.techclarification;

import com.venus.mc.techeval.*;
import com.venus.core.util.GenericValidator;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.TechClarificationListDAO;
import com.venus.mc.dao.TechClarificationListDetailDAO;
import com.venus.mc.dao.TenderPlanDetailDAO;
import com.venus.mc.util.Constants;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.LabelValueBean;

/**
 *
 * @author mai vinh loc
 */
public class ListDetailAction extends SpineAction {

    @Override
    public boolean doAction(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String tclId = session.getAttribute("tclId").toString();

        //detId="1";
        ArrayList arrEmployee = null;
        if (!GenericValidator.isBlankOrNull(tclId)) {
            try {
                TechClarificationListDetailDAO techDAO = new TechClarificationListDetailDAO();
                arrEmployee = techDAO.getTechClarificationListDetail(tclId);
            } catch (Exception ex) {
            }
        }
        if (arrEmployee == null) {
            arrEmployee = new ArrayList();
        }
        request.setAttribute(Constants.TCL, arrEmployee);

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

        request.setAttribute(Constants.TCL_STATUS_LIST,arr);
        return true;
    }
}
