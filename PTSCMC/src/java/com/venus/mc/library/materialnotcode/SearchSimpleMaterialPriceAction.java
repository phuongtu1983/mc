/*
 *
 * Created on April 13, 2007, 2:23 PM
 */
package com.venus.mc.library.materialnotcode;

import com.venus.core.util.LogUtil;
import com.venus.mc.bean.SearchFormBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.MaterialDAO;
import com.venus.mc.util.Constants;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.venus.core.util.StringUtil;
import java.util.ArrayList;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author phuongtu
 * @version
 */
public class SearchSimpleMaterialPriceAction extends SpineAction {

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
        SearchFormBean mpForm = (SearchFormBean) form;
        int fieldid = mpForm.getSearchid();
        String strFieldvalue = mpForm.getSearchvalue();
        ArrayList materialList = null;
        MaterialDAO materialDAO = new MaterialDAO();
        try {
            materialList = materialDAO.searchMaterialPrices(fieldid, StringUtil.encodeHTML(strFieldvalue));
        } catch (Exception ex) {
            LogUtil.error("FAILED:MaterialPrice:searchSimple-" + ex.getMessage());
            ex.printStackTrace();
        }
        request.setAttribute(Constants.MATERIAL_LIST, materialList);
        return true;
    }
}
