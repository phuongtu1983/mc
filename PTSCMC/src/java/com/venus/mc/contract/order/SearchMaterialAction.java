/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.contract.order;

import com.venus.core.util.StringUtil;
import com.venus.core.util.LogUtil;
import com.venus.mc.bean.SearchFormBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.MaterialDAO;
import com.venus.mc.util.Constants;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author Mai vinh loc
 */
public class SearchMaterialAction extends SpineAction {

    @Override
    public boolean doAction(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        SearchFormBean formBean = (SearchFormBean) form;
        int fieldid = formBean.getSearchid();
        String strFieldvalue = formBean.getSearchvalue();
        ArrayList materialList = null;

        try {
            MaterialDAO materialDAO = new MaterialDAO();
            materialList = materialDAO.searchMaterial(fieldid, StringUtil.encodeHTML(strFieldvalue));
        } catch (Exception ex) {
            LogUtil.error("FAILED:Material:search-" + ex.getMessage());
            ex.printStackTrace();
        }
        request.setAttribute(Constants.MATERIAL_LIST, materialList);
        return true;
    }
}
