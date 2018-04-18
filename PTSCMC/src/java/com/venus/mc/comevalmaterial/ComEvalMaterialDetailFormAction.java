/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.comevalmaterial;

import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.ComEvalMaterialBean;
import com.venus.mc.bean.TenderPlanBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.ComEvalMaterialDAO;
import com.venus.mc.dao.IncotermDAO;
import com.venus.mc.dao.TenderPlanDAO;
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
public class ComEvalMaterialDetailFormAction extends SpineAction {

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
        ComEvalMaterialBean bean = null;
        int cemId = NumberUtil.parseInt(request.getParameter("cemId"), 0);
        int venId = NumberUtil.parseInt(request.getParameter("venId"), 0);
        int forms = NumberUtil.parseInt(request.getParameter("form"), 0);
        //terId = "1";
        //vendorName = "123";
        //result="1";

        if (cemId > 0) {
            ComEvalMaterialDAO vendorDAO = new ComEvalMaterialDAO();
            try {
                bean = vendorDAO.getComEvalMaterialVendor(cemId, venId);

            } catch (Exception ex) {
            }
        }
        if (bean == null) {
            bean = new ComEvalMaterialBean();
        } else {
            try {
                TenderPlanDAO tenDAO = new TenderPlanDAO();
                TenderPlanBean tenBean = tenDAO.getTenderPlan(bean.getTenId());
                if (tenBean != null) {
                    forms = NumberUtil.parseInt(tenBean.getForm(), 0);
                }
            } catch (Exception ex) {
            }
        }

        bean.setForm(forms);

        request.setAttribute(Constants.COM_EVAL_DETAIL, bean);

        ArrayList arrIncoterm = null;
        try {
            IncotermDAO cerDAO = new IncotermDAO();
            arrIncoterm = cerDAO.getIncoterms();
        } catch (Exception ex) {
        }
        if (arrIncoterm == null) {
            arrIncoterm = new ArrayList();
        }
        request.setAttribute(Constants.TENDERPLAN_BIDOPENING_INCOTERM, arrIncoterm);

        ArrayList currency = MCUtil.getArrayCurrency();
        request.setAttribute(Constants.CURRENCY_LIST, currency);

        return true;
    }
}
