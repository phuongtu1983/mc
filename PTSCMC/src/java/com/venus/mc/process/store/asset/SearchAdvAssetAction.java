package com.venus.mc.process.store.asset;

/**
 * @author Mai Vinh Loc
 */
import com.venus.core.util.LogUtil;
import com.venus.mc.bean.AssetBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.AssetDAO;
import com.venus.mc.util.Constants;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import java.util.ArrayList;

public class SearchAdvAssetAction extends SpineAction {

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
        SearchAdvAssetFormBean formBean = (SearchAdvAssetFormBean) form;
        AssetBean bean = new AssetBean();

        bean.setAssId(formBean.getAssId());
        bean.setAssetName(formBean.getAssetName());
        bean.setDecisionNumber(formBean.getDecisionNumber());
        bean.setManageCode(formBean.getManageCode());
        bean.setAssetName(formBean.getAssetName());
        bean.setRequestNumber(formBean.getRequestNumber());
        bean.setContractNumber(formBean.getContractNumber());
        bean.setTestNumber(formBean.getTestNumber());
        bean.setUnit(formBean.getUnit());
        bean.setUsedCode(formBean.getUsedCode());
        bean.setColorCode(formBean.getColorCode());
        bean.setSpecCerts(formBean.getSpecCerts());
        bean.setFuelLevel(formBean.getFuelLevel());
        bean.setStatus(formBean.getStatus());
        bean.setAppearedDate(formBean.getAppearedDate());
        bean.setUsedDate(formBean.getUsedDate());
        bean.setComment(formBean.getComment());

        ArrayList assetList = null;
        AssetDAO assetDAO = new AssetDAO();
        try {
            assetList = assetDAO.searchAdvAsset(bean);
        } catch (Exception ex) {
            LogUtil.error("FAILED: searchAdvAsset-" + ex.getMessage());
            ex.printStackTrace();
        }
        request.setAttribute(Constants.ASSET_LIST, assetList);
        return true;
    }
}
