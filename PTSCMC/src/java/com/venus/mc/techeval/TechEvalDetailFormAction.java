/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.techeval;

import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.ComEvalMaterialDetailBean;
import com.venus.mc.bean.TechEvalDetailBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.TechEvalDetailDAO;
import com.venus.mc.util.Constants;
import com.venus.mc.util.MCUtil;
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
public class TechEvalDetailFormAction extends SpineAction {

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
        TechEvalDetailBean bean = null;
        int terId = NumberUtil.parseInt(request.getParameter("terId"), 0);
        int venId =  NumberUtil.parseInt(request.getParameter("venId"),0);

        //terId = "1";
        //vendorName = "123";
        //result="1";

        if (terId > 0) {
            TechEvalDetailDAO tenderDAO = new TechEvalDetailDAO();
            try {
                bean = tenderDAO.getTechEvalDetail(terId,venId);                
            } catch (Exception ex) {
            }
        }
        if (bean == null) {
            bean = new TechEvalDetailBean();
            bean.setEvalTbe(1);
        }
        bean.setTerId(terId);

        request.setAttribute(Constants.TECH_EVAL_DETAIL, bean);
        session.setAttribute("kind", bean.getKind());

        ArrayList arrTbe = new ArrayList();
        LabelValueBean value;
        value = new LabelValueBean();
        value.setLabel(MCUtil.getBundleString("message.select"));
        value.setValue("0");
        arrTbe.add(value);
        value = new LabelValueBean();
        value.setLabel(MCUtil.getBundleString("message.result1"));
        value.setValue(ComEvalMaterialDetailBean.RESULT1 + "");
        arrTbe.add(value);
        value = new LabelValueBean();
        value.setLabel(MCUtil.getBundleString("message.result2"));
        value.setValue(ComEvalMaterialDetailBean.RESULT2 + "");
        arrTbe.add(value);
        value = new LabelValueBean();
        value.setLabel(MCUtil.getBundleString("message.result3"));
        value.setValue(ComEvalMaterialDetailBean.RESULT3 + "");
        arrTbe.add(value);

        request.setAttribute(Constants.TBE_LIST, arrTbe);

        //ResultList
        ArrayList arrResult = new ArrayList();
        //LabelValueBean value;
        value = new LabelValueBean();
        value.setLabel(MCUtil.getBundleString("message.select"));
        value.setValue("0");
        arrResult.add(value);
        value = new LabelValueBean();
        value.setLabel(MCUtil.getBundleString("message.result1"));
        value.setValue(ComEvalMaterialDetailBean.RESULT1 + "");
        arrResult.add(value);
        value = new LabelValueBean();
        value.setLabel(MCUtil.getBundleString("message.result2"));
        value.setValue(ComEvalMaterialDetailBean.RESULT2 + "");
        arrResult.add(value);
        value = new LabelValueBean();
        value.setLabel(MCUtil.getBundleString("message.result3"));
        value.setValue(ComEvalMaterialDetailBean.RESULT3 + "");
        arrResult.add(value);
        request.setAttribute(Constants.RESULT_LIST, arrResult);

        return true;
    }
}
