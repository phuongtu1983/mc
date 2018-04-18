/*
 *
 * Created on April 14, 2007, 8:49 AM
 */
package com.venus.mc.tenderplan;

import com.venus.mc.core.SpineAction;
import com.venus.mc.util.Constants;
import com.venus.mc.util.MCUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import java.util.ArrayList;
import org.apache.struts.util.LabelValueBean;

/**
 *
 * @author HOANG DIEU
 * @version
 */
public class SearchAdvTenderPlanFormAction extends SpineAction {

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
        SearchAdvTenderPlanFormBean formBean = new SearchAdvTenderPlanFormBean();
        request.setAttribute(Constants.TENDERPLAN, formBean);
        ArrayList arrForm = new ArrayList();
        LabelValueBean value;
        value = new LabelValueBean();
        value.setLabel(MCUtil.getBundleString("message.select"));
        value.setValue("0");
        arrForm.add(value);
        value = new LabelValueBean();
        value.setLabel(MCUtil.getBundleString("message.tenderplan.form.private"));
        value.setValue(TenderPlanFormBean.FORM_PRIVATE + "");
        arrForm.add(value);
        value = new LabelValueBean();
        value.setLabel(MCUtil.getBundleString("message.tenderplan.form.fax"));
        value.setValue(TenderPlanFormBean.FORM_FAX + "");
        arrForm.add(value);
        request.setAttribute(Constants.TENDERPLAN_FORM, arrForm);
        return true;
    }
}
