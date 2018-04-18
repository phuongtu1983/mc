/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.process.purchasing.deliverynotice;

import com.venus.core.util.LogUtil;
import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.DnBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.DnDAO;
import com.venus.mc.util.Constants;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import java.util.ArrayList;

/**
 *
 * @author mai vinh loc
 */
public class SearchAdvDnAction extends SpineAction {

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
        int kind = NumberUtil.parseInt(request.getParameter("kind"), 1);
        DnFormBean formBean = (DnFormBean) form;
        DnBean bean = new DnBean();
        bean.setDnId(formBean.getDnId());
        bean.setConId(formBean.getConId());
        bean.setDnNumber(formBean.getDnNumber());
        bean.setCreatedDate(formBean.getCreatedDate());
        bean.setExpectedDate(formBean.getExpectedDate());
        bean.setDeliveryPlace(formBean.getDeliveryPlace());
        bean.setDeliveryPresenter(formBean.getDeliveryPresenter());
        //       bean.setMoveCreateMrir(formBean.getMoveCreateMrir());
        //      bean.setMoveCreateMsv(formBean.getMoveCreateMsv());
        //      bean.setReceiveMrir(formBean.getReceiveMrir());
        //     bean.setReceiveMsv(formBean.getReceiveMsv());
        bean.setWhichUse(formBean.getWhichUse());
        bean.setCreatedOrg(formBean.getCreatedOrg());
        bean.setProId(formBean.getProId());
        bean.setStatus(formBean.getStatus());

        ArrayList dnList = null;
        DnDAO dnDAO = new DnDAO();
        try {

            if (kind == DnBean.KIND1) {
                dnList = dnDAO.searchAdvDn(bean);
            }
            if (kind == DnBean.KIND2) {
                dnList = dnDAO.searchAdvDn2(bean);
            }
            if (kind == DnBean.KIND4) {
                dnList = dnDAO.searchAdvDn4(bean);
            }
            if (kind == DnBean.KIND5) {
                dnList = dnDAO.searchAdvDn5(bean);
            }
        } catch (Exception ex) {
            LogUtil.error("FAILED: searchAdvDn-" + ex.getMessage());
            ex.printStackTrace();
        }
        request.setAttribute(Constants.DN_LIST, dnList);
        return true;
    }
}
