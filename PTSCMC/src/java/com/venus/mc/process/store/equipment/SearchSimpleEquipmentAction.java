
package com.venus.mc.process.store.equipment;

/**
 * @author Mai Vinh Loc
 */

import com.venus.core.util.LogUtil;
import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.SearchFormBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.EquipmentDAO;
import com.venus.mc.util.Constants;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.venus.core.util.StringUtil;
import java.util.ArrayList;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class SearchSimpleEquipmentAction extends SpineAction {

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
        SearchFormBean equipmentForm = (SearchFormBean) form;
        int fieldid = equipmentForm.getSearchid();
        int kind = NumberUtil.parseInt(request.getParameter("kind"),1);
        String strFieldvalue = equipmentForm.getSearchvalue();
        ArrayList equipmentList = null;
        EquipmentDAO equipmentDAO = new EquipmentDAO();
        try {
            equipmentList = equipmentDAO.searchSimpleEquipment(fieldid, StringUtil.encodeHTML(strFieldvalue), kind);
        } catch (Exception ex) {
            LogUtil.error("FAILED: EquipmentBean:searchSimpleEquipment-" + ex.getMessage());
            ex.printStackTrace();
        }
        request.setAttribute(Constants.EQUIPMENT_LIST, equipmentList);
        return true;
    }   
}
