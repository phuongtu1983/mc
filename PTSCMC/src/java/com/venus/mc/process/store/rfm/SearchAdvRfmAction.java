/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.process.store.rfm;

import com.venus.core.util.LogUtil;
import com.venus.mc.bean.RfmBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.RfmDAO;
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
public class SearchAdvRfmAction extends SpineAction {

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
        RfmFormBean formBean = (RfmFormBean) form;
        RfmBean bean = new RfmBean();

        bean.setRfmId(formBean.getRfmId());
        bean.setCreator(formBean.getCreator());
        bean.setRfmNumber(formBean.getRfmNumber());
        bean.setCreatedDate(formBean.getCreatedDate());
        bean.setDeliveryDate(formBean.getDeliveryDate());
        if (formBean.getReceiveId()==1) bean.setOrgId(formBean.getOrgId());
        if (formBean.getReceiveId()==2) bean.setProId(formBean.getProId());
        bean.setDeliveryPlace(formBean.getDeliveryPlace());
        bean.setStoId(formBean.getStoId());
        bean.setReqType(formBean.getReqType());
        bean.setKind(formBean.getKind());
        bean.setReqId(formBean.getReqId());

        ArrayList rfmList = null;
        RfmDAO rfmDAO = new RfmDAO();
        try {
            rfmList = rfmDAO.searchAdvRfm(bean);
        } catch (Exception ex) {
            LogUtil.error("FAILED: searchAdvRfm-" + ex.getMessage());
            ex.printStackTrace();
        }
        request.setAttribute(Constants.RFM_LIST, rfmList);
        return true;
    }
}
