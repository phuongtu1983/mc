package com.venus.mc.process.store.equipment;

/**
 * @author Mai Vinh Loc
 */
import com.venus.core.util.LogUtil;
import com.venus.mc.bean.EquipmentBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.EquipmentDAO;
import com.venus.mc.util.Constants;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import java.util.ArrayList;

public class SearchAdvEquipmentAction extends SpineAction {

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
        SearchAdvEquipmentFormBean formBean = (SearchAdvEquipmentFormBean) form;
        EquipmentBean bean = new EquipmentBean();

        bean.setEquId(formBean.getEquId());
        bean.setMivId(formBean.getMivId());
        bean.setMivNumber(formBean.getMivNumber());
        bean.setManageCode(formBean.getManageCode());
        bean.setEquipmentName(formBean.getEquipmentName());
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

        bean.setReqDetailId(formBean.getReqDetailId());
        bean.setMatId(formBean.getMatId());
        bean.setConId(formBean.getConId());
        bean.setKind(formBean.getKind());

        ArrayList equipmentList = null;
        EquipmentDAO equipmentDAO = new EquipmentDAO();
        try {
            equipmentList = equipmentDAO.searchAdvEquipment(bean);
        } catch (Exception ex) {
            LogUtil.error("FAILED: searchAdvEquipment-" + ex.getMessage());
            ex.printStackTrace();
        }
        request.setAttribute(Constants.EQUIPMENT_LIST, equipmentList);
        return true;
    }
}
