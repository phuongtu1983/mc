/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.tenderletter;

import com.venus.core.util.DateUtil;
import com.venus.core.util.GenericValidator;
import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.TenderLetterBean;
import com.venus.mc.bean.TenderPlanBean;
import com.venus.mc.bean.VendorBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.TenderLetterDAO;
import com.venus.mc.dao.TenderPlanDAO;
import com.venus.mc.dao.VendorDAO;
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
public class ListTenderLetterDetailAction extends SpineAction {

    @Override
    public boolean doAction(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        String tenId = request.getParameter("tenId");

        ArrayList arrDetail = null;
        TenderPlanBean tb = new TenderPlanBean();
        int evalKind = 0;
        if (!GenericValidator.isBlankOrNull(tenId)) {
            try {
                VendorDAO tenderDAO = new VendorDAO();
                TenderPlanDAO tenDAO = new TenderPlanDAO();
                arrDetail = tenderDAO.getVendorsForTenderLetter(tenId);
                evalKind = tenderDAO.getEvalKind(tenId);
                tb = tenDAO.getOrgNameEmpCreatedTenderPlan(NumberUtil.parseInt(tenId, 0));
            } catch (Exception ex) {
            }
        }
        if (arrDetail == null) {
            arrDetail = new ArrayList();
        }

        try {
            TenderLetterDAO letterDAO = new TenderLetterDAO();
//            OrganizationDAO orgDAO = new OrganizationDAO();
            TenderLetterBean letBean = letterDAO.getTenderLetter(NumberUtil.parseInt(tenId, 0));
//            if (letBean != null && letBean.getLetId() == 0) {
            if (letBean != null) {
//                OrganizationBean orgBean = orgDAO.getOrganization(MCUtil.getOrganizationID(request.getSession()));
//                if (orgBean != null && orgBean.getParentId() > 0) {
//                    orgBean = orgDAO.getOrganization(orgBean.getParentId());
//                }
                VendorBean bean = null;
                String year = DateUtil.today("yyyy");

                for (int i = 0; i < arrDetail.size(); i++) {
                    bean = (VendorBean) arrDetail.get(i);
                    if (bean.getDetId() == 0) {
                    bean.setSubfix("/" + year + "/CKHH-" + tb.getAbbreviate());
                   //     bean.setSubfix("/" + year + "/CKHH-KH");
                    }
                }
            }
        } catch (Exception ex) {
        }
        request.setAttribute(Constants.TENDER_LETTER_LIST, arrDetail);
        request.setAttribute(Constants.TENDERPLAN_EVALKIND, evalKind);
        return true;
    }
}
