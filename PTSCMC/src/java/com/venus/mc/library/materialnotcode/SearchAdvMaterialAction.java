
package com.venus.mc.library.materialnotcode;

/**
 * @author Mai Vinh Loc
 */
import com.venus.core.util.LogUtil;
import com.venus.core.util.StringUtil;
import com.venus.mc.bean.MaterialBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.MaterialDAO;
import com.venus.mc.util.Constants;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.venus.mc.material.SearchAdvMaterialFormBean;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import java.util.ArrayList;

public class SearchAdvMaterialAction extends SpineAction {

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
        SearchAdvMaterialFormBean formBean = (SearchAdvMaterialFormBean) form;
        MaterialBean bean = new MaterialBean();
        
        bean.setMatId(formBean.getMatId());
        bean.setOriId(formBean.getOriId());
        bean.setUniId(formBean.getUniId());
        bean.setCode(formBean.getCode());
        bean.setNameEn(StringUtil.encodeHTML(formBean.getNameEn()));
        bean.setNameVn(StringUtil.encodeHTML(formBean.getNameVn()));
        bean.setKind(formBean.getKind());
        bean.setNote(formBean.getNote());
        bean.setGroId(formBean.getGroId());

        ArrayList materialList = null;
        MaterialDAO materialDAO = new MaterialDAO();
        try {
            materialList = materialDAO.searchAdvMaterialNotCode(bean);
        } catch (Exception ex) {
            LogUtil.error("FAILED: searchAdvMaterial-" + ex.getMessage());
            ex.printStackTrace();
        }
        request.setAttribute(Constants.MATERIAL_LIST, materialList);
        return true;
    }   
}
