/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.tenderletter;

import com.venus.core.util.GenericValidator;
import com.venus.mc.core.BaseAction;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import com.venus.mc.workReport.TenderLetterReport;
import com.venus.mc.bean.TenderLetterDetailBean;
import com.venus.mc.bean.TenderPlanBean;
import com.venus.mc.dao.TenderLetterDetailDAO;
import com.venus.mc.dao.TenderPlanDAO;
import com.venus.mc.tenderplan.TenderPlanFormBean;

/**
 *
 * @author kngo
 */
public class TenderLetterPrintAction1 extends BaseAction {

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
        if (!GenericValidator.isBlankOrNull(request.getParameter("detId"))) {
            try {
                TenderLetterDetailBean bean = null;
                TenderPlanBean tenBean=null;
                TenderLetterDetailDAO tenDAO = new TenderLetterDetailDAO();
                TenderPlanDAO
                        tenderDAO=new TenderPlanDAO();
                try {
                    bean = tenDAO.getTenderLetterDetail(Integer.parseInt(request.getParameter("detId")));
                    tenBean=tenderDAO.getTenderPlan(bean.getTenId());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                if(tenBean==null) tenBean=new TenderPlanBean();
                printTenderLetter(request, response, bean, tenBean);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        return true;
    }

    private void printTenderLetter(HttpServletRequest request, HttpServletResponse response, TenderLetterDetailBean bean,TenderPlanBean tenBean) {
        if (bean != null) {
            try {
                String xmlTemplate = request.getSession().getServletContext().getRealPath("/templates/Cong van moi chao gia_xml.xml");
                String wordTemplate = "";
                
                if (bean.getForm() == 1) {
                    if(tenBean.getOfferType()==TenderPlanFormBean.OFFER_IN){
                        wordTemplate = request.getSession().getServletContext().getRealPath("/templates/Cong van moi chao gia_PBK.xml");
                    }else{
                        wordTemplate = request.getSession().getServletContext().getRealPath("/templates/Cong van moi chao gia_PBK_nn.xml");
                    }
                    
                } else {
                    if(tenBean.getOfferType()==TenderPlanFormBean.OFFER_IN){
                        wordTemplate = request.getSession().getServletContext().getRealPath("/templates/Cong van moi chao gia_Fax.xml");
                    } else
                        wordTemplate = request.getSession().getServletContext().getRealPath("/templates/Cong van moi chao gia_Fax_nn.xml");
                }
                TenderLetterReport report = new TenderLetterReport(xmlTemplate, wordTemplate, "Cong van moi chao gia");
                report.executeParse(request, response, bean);
            } catch (Exception ex) {
            }
        }
    }

    @Override
    protected boolean isReturnStream() {
        return true;
    }
}
