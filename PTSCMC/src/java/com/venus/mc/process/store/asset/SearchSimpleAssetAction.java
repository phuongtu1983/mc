
package com.venus.mc.process.store.asset;

/**
 * @author Mai Vinh Loc
 */
import com.venus.core.util.StringUtil;
import com.venus.core.util.LogUtil;
import com.venus.mc.bean.SearchFormBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.AssetDAO;
import com.venus.mc.util.Constants;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class SearchSimpleAssetAction extends SpineAction {

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
        SearchFormBean assetForm = (SearchFormBean) form;
        int fieldid = assetForm.getSearchid();
        String strFieldvalue = assetForm.getSearchvalue();
        ArrayList assetList = null;
        AssetDAO assetDAO = new AssetDAO();
        try {
            assetList = assetDAO.searchSimpleAsset(fieldid, StringUtil.encodeHTML(strFieldvalue));
        } catch (Exception ex) {
            LogUtil.error("FAILED: AssetBean:searchSimpleAsset-" + ex.getMessage());
            ex.printStackTrace();
        }
        request.setAttribute(Constants.ASSET_LIST, assetList);
        return true;
    }   
}
