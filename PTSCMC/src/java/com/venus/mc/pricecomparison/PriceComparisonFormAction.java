/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.pricecomparison;

import com.venus.core.util.DateUtil;
import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.PriceComparisonBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.PriceComparisonDAO;
import com.venus.mc.util.Constants;
import com.venus.mc.util.PermissionUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author mai vinh loc
 */
public class PriceComparisonFormAction extends SpineAction {

    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP PriceComparison we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    @Override
    public boolean doAction(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        PriceComparisonBean bean = new PriceComparisonBean();
        int tenId = NumberUtil.parseInt(request.getParameter("tenId"), 0);

        if (tenId > 0) {
            PriceComparisonDAO pricecomparisonDAO = new PriceComparisonDAO();
            try {
                bean = pricecomparisonDAO.getPriceComparison(tenId);
                if (bean == null) {
                    bean = new PriceComparisonBean();
                    bean.setCreatedDate(DateUtil.today("dd/MM/yyyy"));
                    bean.setTenId(tenId);
                }
            } catch (Exception ex) {
            }
        }

        request.setAttribute(Constants.PRICECOMPARISON, bean);

        return true;
    }

    @Override
    protected String getFunction() {
        return PermissionUtil.FUNC_VIEW + ";" + PermissionUtil.FUNC_EDIT;
    }

    @Override
    protected int getPermit() {
        return PermissionUtil.PER_PURCHASING_TENDERPLAN;
    }
}
