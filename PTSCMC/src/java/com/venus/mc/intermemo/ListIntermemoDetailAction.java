/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.intermemo;

import com.venus.core.util.GenericValidator;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.RequestDAO;
import com.venus.mc.request.RequestFormBean;
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
 * @author phuongtu
 */
public class ListIntermemoDetailAction extends SpineAction {

    @Override
    public boolean doAction(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String reqId = request.getParameter("reqId");
        Integer id = (Integer) session.getAttribute("id");
        if (id != null) {
            reqId = id + "";
        }
        session.removeAttribute("id");
        ArrayList arrRequestDetail = null;
        if (!GenericValidator.isBlankOrNull(reqId)) {
            try {
                RequestDAO requestDAO = new RequestDAO();
                arrRequestDetail = requestDAO.getRequestDetails(Integer.parseInt(reqId));
            } catch (Exception ex) {
            }
        }
        if (arrRequestDetail == null) {
            arrRequestDetail = new ArrayList();
        }
        request.setAttribute(Constants.REQUEST_DETAIL_LIST, arrRequestDetail);

        LabelValueBean value;
        ArrayList arrMaterialKind = new ArrayList();
        value = new LabelValueBean();
        value.setLabel(MCUtil.getBundleString("message.request.materialKind.major"));
        value.setValue(RequestFormBean.KIND_MAJOR + "");
        arrMaterialKind.add(value);
        value = new LabelValueBean();
        value.setLabel(MCUtil.getBundleString("message.request.materialKind.secondary"));
        value.setValue(RequestFormBean.KIND_SECONDARY + "");
        arrMaterialKind.add(value);
        request.setAttribute(Constants.REQUEST_MATERIAL_KIND_LIST, arrMaterialKind);
        return true;
    }
}
