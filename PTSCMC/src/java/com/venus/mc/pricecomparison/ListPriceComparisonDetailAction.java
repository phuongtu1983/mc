/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.pricecomparison;

import com.venus.core.util.NumberUtil;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.PriceComparisonDetailDAO;
import com.venus.mc.util.Constants;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author mai vinh loc
 */
public class ListPriceComparisonDetailAction extends SpineAction {

    @Override
    public boolean doAction(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        int tenId = NumberUtil.parseInt(request.getParameter("tenId"), 0);
        int pcId = NumberUtil.parseInt(request.getParameter("pcId"), 0);
        ArrayList arrDetail = null;
        if (tenId > 0) {
            try {
                PriceComparisonDetailDAO pricecomparisonDAO = new PriceComparisonDetailDAO();
                arrDetail = pricecomparisonDAO.getPriceComparisonDetails(tenId, pricecomparisonDAO.getTenderPlanEvalKind(tenId), pcId);
            } catch (Exception ex) {
            }
        }
        if (arrDetail == null) {
            arrDetail = new ArrayList();
        }
        request.setAttribute(Constants.PRICECOMPARISON_LIST, arrDetail);
        return true;
    }
}
