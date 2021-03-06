
package com.venus.mc.unit;

/**
 * @author Mai Vinh Loc
 */

import com.venus.core.util.LogUtil;
import com.venus.mc.bean.UnitBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.UnitDAO;
import com.venus.mc.util.Constants;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import java.util.ArrayList;

public class SearchAdvUnitAction extends SpineAction {

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
        SearchAdvUnitFormBean formBean = (SearchAdvUnitFormBean) form;
        UnitBean bean = new UnitBean();

        bean.setUnitEn(formBean.getUnitEn());
        bean.setUnitVn(formBean.getUnitVn());

        ArrayList unitList = null;
        UnitDAO unitDAO = new UnitDAO();
        try {
            unitList = unitDAO.searchAdvUnit(bean);
        } catch (Exception ex) {
            LogUtil.error("FAILED: searchAdvUnit-" + ex.getMessage());
            ex.printStackTrace();
        }
        request.setAttribute(Constants.UNIT_LIST, unitList);
        return true;
    }  
}
