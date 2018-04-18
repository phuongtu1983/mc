/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.process.purchasing.edeliverynotice;

import com.venus.core.util.LogUtil;
import com.venus.mc.bean.EdnBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.EdnDAO;
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
public class SearchAdvEdnAction extends SpineAction {

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
        EdnFormBean formBean = (EdnFormBean) form;
        EdnBean bean = new EdnBean();
        bean.setDnId(formBean.getDnId());
        bean.setDnNumber(formBean.getDnNumber());
        bean.setContractNumber(formBean.getContractNumber());
        bean.setCreatedDate(formBean.getCreatedDate());
        bean.setExpectedDate(formBean.getExpectedDate());
        bean.setDeliveryPlace(formBean.getDeliveryPlace());
        bean.setDeliveryPresenter(formBean.getDeliveryPresenter());
        bean.setCreatedOrg(formBean.getCreatedOrg());
        bean.setProId(formBean.getProId());

        ArrayList dnList = null;
        EdnDAO dnDAO = new EdnDAO();
        try {
            dnList = dnDAO.searchAdvDn(bean);
        } catch (Exception ex) {
            LogUtil.error("FAILED: searchAdvEdn-" + ex.getMessage());
            ex.printStackTrace();
        }
        request.setAttribute(Constants.DN_LIST, dnList);
        return true;
    }
}
