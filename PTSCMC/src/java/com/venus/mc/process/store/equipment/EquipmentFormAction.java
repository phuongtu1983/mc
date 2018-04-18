package com.venus.mc.process.store.equipment;

/**
 * @author Mai Vinh Loc
 */
import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.EquipmentBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.EquipmentDAO;
import com.venus.mc.util.Constants;
import com.venus.mc.util.MCUtil;
import com.venus.mc.util.PermissionUtil;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.LabelValueBean;

public class EquipmentFormAction extends SpineAction {

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

        HttpSession session = request.getSession();
        int kind = NumberUtil.parseInt(request.getParameter("kind"), 1);
        EquipmentBean bean = new EquipmentBean();
        String equId = request.getParameter("equId");
        if (!GenericValidator.isBlankOrNull(equId)) {
            EquipmentDAO materialDAO = new EquipmentDAO();
            try {
                bean = materialDAO.getEquipment(equId, kind);
            } catch (Exception ex) {
            }
        }
        request.setAttribute(Constants.EQUIPMENT, bean);

        //STATUS
        ArrayList arrStatus = new ArrayList();
        LabelValueBean value;
        value = new LabelValueBean();
        value.setLabel(MCUtil.getBundleString("message.equipment.status1"));
        value.setValue(EquipmentBean.S1 + "");
        arrStatus.add(value);
        value = new LabelValueBean();
        value.setLabel(MCUtil.getBundleString("message.equipment.status2"));
        value.setValue(EquipmentBean.S2 + "");
        arrStatus.add(value);
        value = new LabelValueBean();
        value.setLabel(MCUtil.getBundleString("message.equipment.status3"));
        value.setValue(EquipmentBean.S3 + "");
        arrStatus.add(value);
        value = new LabelValueBean();
        value.setLabel(MCUtil.getBundleString("message.equipment.status4"));
        value.setValue(EquipmentBean.S4 + "");
        arrStatus.add(value);
        value = new LabelValueBean();
        value.setLabel(MCUtil.getBundleString("message.equipment.status5"));
        value.setValue(EquipmentBean.S5 + "");
        arrStatus.add(value);
        request.setAttribute(Constants.STATUS_LIST, arrStatus);

        return true;
    }

    @Override
    protected String getFunction() {
        return PermissionUtil.FUNC_VIEW + ";" + PermissionUtil.FUNC_EDIT;
    }

    @Override
    protected int getPermit() {
        return PermissionUtil.PER_EQUIPMENT;
    }
}
