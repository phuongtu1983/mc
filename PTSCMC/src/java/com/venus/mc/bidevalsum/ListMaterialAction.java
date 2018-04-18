/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.bidevalsum;

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
public class ListMaterialAction extends SpineAction {

    @Override
    public boolean doAction(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        String tenId = request.getParameter("tenId");
        //terId="1";
        ArrayList arrEmployee = null;
        if (!GenericValidator.isBlankOrNull(tenId)) {
            try {
                TenderPlanDetailDAO techDAO = new TenderPlanDetailDAO();
                //arrEmployee = techDAO.getMaterialBidEvalSum(Integer.parseInt(tenId),techDAO.getEvalKindTenderPlan(tenId));
                // Bo qua danh gia
                arrEmployee = techDAO.getMaterialBidEvalSumNon(Integer.parseInt(tenId));
                //
            } catch (Exception ex) {
            }
        }
        if (arrEmployee == null) {
            arrEmployee = new ArrayList();
        }
        request.setAttribute(Constants.MATERIAL_LIST, arrEmployee);

        //Confirm        
        ArrayList arr = new ArrayList();
        LabelValueBean value;
        value = new LabelValueBean();
        value.setLabel(MCUtil.getBundleString("message.select"));
        value.setValue("0");
        arr.add(value);
        value = new LabelValueBean();
        value.setLabel(MCUtil.getBundleString("message.confirm"));
        value.setValue(ComEvalMaterialDetailBean.RESULT1 + "");
        arr.add(value);

        request.setAttribute(Constants.EVAL_TBE_LIST, arr);
        return true;
    }
}
