/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.process.equipment.surveyreport;

import com.venus.core.util.GenericValidator;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.SurveyReportDAO;
import com.venus.mc.util.Constants;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author mai vinh loc
 */
public class ListSurveyReportDetailsAction extends SpineAction {

    @Override
    public boolean doAction(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String srId = request.getParameter("srId");
        Integer id = (Integer) session.getAttribute("id");
        if (id != null) {
            srId = id + "";
        }
        session.removeAttribute("id");
        ArrayList arrSurveyReportDetail = null;
        SurveyReportDAO surveyreport = new SurveyReportDAO();
        if (!GenericValidator.isBlankOrNull(srId)) {
            try {                
                arrSurveyReportDetail = surveyreport.getSurveyReportDetails(Integer.parseInt(srId));
            } catch (Exception ex) {
            }
        }
        if (arrSurveyReportDetail == null) {
            arrSurveyReportDetail = new ArrayList();
        }
        request.setAttribute(Constants.SURVEYREPORT_DETAIL_LIST, arrSurveyReportDetail);

        ArrayList arrSurveyReportMaterial = null;
        if (!GenericValidator.isBlankOrNull(srId)) {
            try {
                //SurveyReportDAO surveyreport = new SurveyReportDAO();
                arrSurveyReportMaterial = surveyreport.getSurveyReportMaterials(Integer.parseInt(srId));
            } catch (Exception ex) {
            }
        }
        if (arrSurveyReportMaterial == null) {
            arrSurveyReportMaterial = new ArrayList();
        }
        request.setAttribute(Constants.SURVEYREPORT_MATERIAL_LIST, arrSurveyReportMaterial);

        
        return true;
    }
}
