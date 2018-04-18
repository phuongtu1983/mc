/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.comevalmaterial;

import com.venus.core.util.GenericValidator;
import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.ComEvalMaterialDetailBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.ComEvalMaterialDetailDAO;
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
        String cemId = request.getParameter("cemId");
        String terId = request.getParameter("terId");

        ArrayList arrEmployee = null;
        if (!GenericValidator.isBlankOrNull(cemId)) {
            try {
                ComEvalMaterialDetailDAO techDAO = new ComEvalMaterialDetailDAO();
                arrEmployee = techDAO.getComEvalMaterialDetails(NumberUtil.parseInt(cemId,0),NumberUtil.parseInt(terId,0));
            } catch (Exception ex) {
            }
        }
        if (arrEmployee == null) {
            arrEmployee = new ArrayList();
        }
        request.setAttribute(Constants.COM_EVAL_DETAIL_LIST, arrEmployee);
        
        //ResultList
        ArrayList arrResult = new ArrayList();
        LabelValueBean value;
        value = new LabelValueBean();
        value.setLabel(MCUtil.getBundleString("message.select"));
        value.setValue("0");
        arrResult.add(value);
        value = new LabelValueBean();
        value.setLabel(MCUtil.getBundleString("message.result1"));
        value.setValue(ComEvalMaterialDetailBean.RESULT1+"");
        arrResult.add(value);
        value = new LabelValueBean();
        value.setLabel(MCUtil.getBundleString("message.result2"));
        value.setValue(ComEvalMaterialDetailBean.RESULT2+"");
        arrResult.add(value);
        request.setAttribute(Constants.RESULT_LIST, arrResult);


        return true;
    }
}
