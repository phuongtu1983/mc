/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.techeval;

import com.venus.core.util.GenericValidator;
import com.venus.mc.bean.ComEvalMaterialDetailBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.TenderPlanDetailDAO;
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
 * @author mai vinh loc
 */
public class ListDetailAction extends SpineAction {

    @Override
    public boolean doAction(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        String terId = request.getParameter("terId");
        //terId="1";
        ArrayList arrEmployee = null;
        if (!GenericValidator.isBlankOrNull(terId)) {
            try {
                TenderPlanDetailDAO techDAO = new TenderPlanDetailDAO();
                arrEmployee = techDAO.getTechEvalDetail(Integer.parseInt(terId));
            } catch (Exception ex) {
            }
        }
        if (arrEmployee == null) {
            arrEmployee = new ArrayList();
        }
        request.setAttribute(Constants.TECH_EVAL_DETAIL_LIST, arrEmployee);

        //TBE        
        ArrayList arr = new ArrayList();
        LabelValueBean value;
        value = new LabelValueBean();
        value.setLabel(MCUtil.getBundleString("message.select"));
        value.setValue("0");
        arr.add(value);
        value = new LabelValueBean();
        value.setLabel(MCUtil.getBundleString("message.result1"));
        value.setValue(ComEvalMaterialDetailBean.RESULT1 + "");
        arr.add(value);
        value = new LabelValueBean();
        value.setLabel(MCUtil.getBundleString("message.result2"));
        value.setValue(ComEvalMaterialDetailBean.RESULT2 + "");
        arr.add(value);
        value = new LabelValueBean();
        value.setLabel(MCUtil.getBundleString("message.result3"));
        value.setValue(ComEvalMaterialDetailBean.RESULT3 + "");
        arr.add(value);

        request.setAttribute(Constants.EVAL_TBE_LIST, arr);
        return true;
    }
}
