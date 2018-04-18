/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.pricecomparison;

import com.venus.core.util.LogUtil;
import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.PriceComparisonDetailBean;
import com.venus.mc.bean.TenderPlanBean;
import com.venus.mc.core.BaseAction;
import com.venus.mc.core.ExcelExport;
import com.venus.mc.dao.PriceComparisonDetailDAO;
import com.venus.mc.dao.TenderPlanDAO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author mai vinh loc
 */
public class PriceComparisonPrintAction extends BaseAction {

    private String templateFile = "Bang so sanh gia.xls";

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
        int tenId = NumberUtil.parseInt(request.getParameter("tenId"), 0);
        int pcId = NumberUtil.parseInt(request.getParameter("pcId"), 0);
        if (tenId > 0) {

            ArrayList arrPriceComparison = null;
            PriceComparisonDetailDAO priceComparisonDetailDAO = new PriceComparisonDetailDAO();
            Map beans = new HashMap();
            try {
                PriceComparisonDetailDAO pricecomparisonDAO = new PriceComparisonDetailDAO();
                arrPriceComparison = priceComparisonDetailDAO.getPriceComparisonDetails(tenId, pricecomparisonDAO.getTenderPlanEvalKind(tenId), pcId);
            } catch (Exception ex) {
                LogUtil.error(this.getClass(), ex.getMessage());
            }
            if (arrPriceComparison == null) {
                arrPriceComparison = new ArrayList();
            }
            for (int i = 0; i < arrPriceComparison.size(); i++) {
                PriceComparisonDetailBean matBean = (PriceComparisonDetailBean) arrPriceComparison.get(i);
                matBean.setStt((i + 1));
            }
            beans.put("pc", arrPriceComparison);
            TenderPlanBean tender = null;

            try {
                TenderPlanDAO tenderDAO = new TenderPlanDAO();
                tender = tenderDAO.getTenderPlan(tenId);

            } catch (Exception ex) {
            }
            if (tender == null) {
                tender = new TenderPlanBean();
            }

            beans.put("packName", tender.getPackName());
            String templateFileName = request.getSession().getServletContext().getRealPath("/templates/" + templateFile);
            ExcelExport exporter = new ExcelExport();
            exporter.setBeans(beans);
            try {
                long milis = System.currentTimeMillis();
                exporter.export(request, response, templateFileName, templateFile);
                milis = System.currentTimeMillis() - milis;
                System.out.println("Bang so sanh gia.xls : " + "time : " + (int) ((milis / 1000) % 60) + " phut " + (int) ((milis / 1000) / 60) + " mili giay");
            } catch (Exception ex) {
                LogUtil.error(this.getClass(), ex.getMessage());
            }
        }
        return true;
    }

    @Override
    protected boolean isReturnStream() {
        return true;
    }
}
