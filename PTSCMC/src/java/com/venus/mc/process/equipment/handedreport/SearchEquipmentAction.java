/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.process.equipment.handedreport;

import com.venus.core.util.LogUtil;
import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.SearchFormBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.EquipmentDAO;
import com.venus.mc.util.Constants;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import com.venus.core.util.StringUtil;
/**
 *
 * @author mai vinh loc
 */
public class SearchEquipmentAction extends SpineAction {

    @Override
    public boolean doAction(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {

        int rtId = NumberUtil.parseInt(request.getParameter("rtId"), 0);

        SearchFormBean formBean = (SearchFormBean) form;
        int fieldid = formBean.getSearchid();
        String strFieldvalue = formBean.getSearchvalue();
        ArrayList equipmentList = null;
        try {
            EquipmentDAO equipmentDAO = new EquipmentDAO();
            if (rtId > 0) {
                equipmentList = equipmentDAO.searchSimpleEquipmentofHandedReport(fieldid, StringUtil.encodeHTML(strFieldvalue), rtId);
            } 
        } catch (Exception ex) {
            LogUtil.error("FAILED:Equipment:search-" + ex.getMessage());
            ex.printStackTrace();
        }
        request.setAttribute(Constants.EQUIPMENT_LIST, equipmentList);
        return true;
    }
}
