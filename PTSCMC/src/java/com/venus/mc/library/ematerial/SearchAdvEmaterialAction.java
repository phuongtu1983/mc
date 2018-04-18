
package com.venus.mc.library.ematerial;

/**
 * @author Mai Vinh Loc
 */
import com.venus.core.util.LogUtil;
import com.venus.core.util.StringUtil;
import com.venus.mc.bean.EmaterialBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.EmaterialDAO;
import com.venus.mc.util.Constants;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import java.util.ArrayList;

public class SearchAdvEmaterialAction extends SpineAction {

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
        SearchAdvEmaterialFormBean formBean = (SearchAdvEmaterialFormBean) form;
        EmaterialBean bean = new EmaterialBean();
        
        bean.setEmatId(formBean.getEmatId());
        bean.setOriId(formBean.getOriId());
        bean.setUniId(formBean.getUniId());
        bean.setCode(formBean.getCode());
        bean.setNameEn(StringUtil.encodeHTML(formBean.getNameEn()));
        bean.setNameVn(StringUtil.encodeHTML(formBean.getNameVn()));
        bean.setKind(formBean.getKind());
        bean.setNote(formBean.getNote());

        ArrayList ematerialList = null;
        EmaterialDAO ematerialDAO = new EmaterialDAO();
        try {
            ematerialList = ematerialDAO.searchAdvEmaterial(bean);
        } catch (Exception ex) {
            LogUtil.error("FAILED: searchAdvEmaterial-" + ex.getMessage());
            ex.printStackTrace();
        }
        request.setAttribute(Constants.MATERIAL_LIST, ematerialList);
        return true;
    }   
}
