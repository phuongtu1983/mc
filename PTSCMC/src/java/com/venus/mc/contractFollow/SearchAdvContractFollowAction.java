
package com.venus.mc.contractFollow;

/**
 * @author Mai Vinh Loc
 */

import com.venus.core.util.LogUtil;
import com.venus.mc.bean.ContractFollowBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.ContractFollowDAO;
import com.venus.mc.util.Constants;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import java.util.ArrayList;

public class SearchAdvContractFollowAction extends SpineAction {

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
        SearchAdvContractFollowFormBean formBean = (SearchAdvContractFollowFormBean) form;
        ContractFollowBean bean = new ContractFollowBean();

        bean.setFolId(formBean.getFolId());
        bean.setConId(formBean.getConId());
        bean.setProId(formBean.getProId());
        bean.setOrgId(formBean.getOrgId());
        bean.setFolNumber(formBean.getFolNumber());
        bean.setCreatedDate(formBean.getCreatedDate());
        bean.setServiceType(formBean.getServiceType());
        bean.setStartTime(formBean.getStartTime());
        bean.setEndTime(formBean.getEndTime());
        bean.setServiceAbility(formBean.getServiceAbility());
        bean.setServiceLevel(formBean.getServiceLevel());
        bean.setServiceEquipment(formBean.getServiceEquipment());
        bean.setServiceProgress(formBean.getServiceProgress());
        bean.setServiceSafety(formBean.getServiceSafety());
        bean.setServiceOther(formBean.getServiceOther());
        bean.setServiceCooperate(formBean.getServiceCooperate());
        bean.setGoodAbility(formBean.getGoodAbility());
        bean.setGoodProgress(formBean.getGoodProgress());
        bean.setGoodCertificate(formBean.getGoodCertificate());
        bean.setGoodQuality(formBean.getGoodQuality());
        bean.setGoodOther(formBean.getGoodOther());
        bean.setGoodCooperate(formBean.getGoodCooperate());
        bean.setComments(formBean.getComments());

        ArrayList unitList = null;
        ContractFollowDAO unitDAO = new ContractFollowDAO();
        try {
            unitList = unitDAO.searchAdvContractFollow(bean);
        } catch (Exception ex) {
            LogUtil.error("FAILED: searchAdvContractFollow-" + ex.getMessage());
            ex.printStackTrace();
        }
        request.setAttribute(Constants.CONTRACT_FOLLOW_LIST, unitList);
        return true;
    }  
}
