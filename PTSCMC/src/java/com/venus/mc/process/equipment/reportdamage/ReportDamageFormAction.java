package com.venus.mc.process.equipment.reportdamage;

/**
 * @author Mai Vinh Loc
 */
import com.venus.core.util.DateUtil;
import com.venus.core.util.StringUtil;
import com.venus.mc.bean.ReportDamageBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.ReportDamageDAO;
import com.venus.mc.dao.EmployeeDAO;
import com.venus.mc.bean.EmployeeBean;
import com.venus.mc.bean.EquipmentBean;
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

public class ReportDamageFormAction extends SpineAction {

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
        ReportDamageBean bean = null;
        String rdId = request.getParameter("rdId");
        Integer id = (Integer) session.getAttribute("id");
        if (id != null) {
            rdId = id + "";
        }
        session.removeAttribute("id");
        if (!GenericValidator.isBlankOrNull(rdId)) {
            ReportDamageDAO materialDAO = new ReportDamageDAO();
            try {
                bean = materialDAO.getReportDamage(rdId);
            } catch (Exception ex) {
            }
        }
        if (bean == null) {
            bean = new ReportDamageBean();
            bean.setCreatedDate(DateUtil.today("dd/MM/yyyy"));
            bean.setStatus(ReportDamageBean.STATUS2);
        }
        request.setAttribute(Constants.REPORTDAMAGE, bean);

        //Equipment
        EquipmentDAO equipmentDAO = new EquipmentDAO();
        ArrayList equipmentList = null;
        try {
            equipmentList = equipmentDAO.getEquipmentsOrg(EquipmentBean.K1, MCUtil.getOrganizationID(session));
        } catch (Exception ex) {
        }
        ArrayList arrEquipment = new ArrayList();
        LabelValueBean value;
        value = new LabelValueBean();
        value.setLabel(MCUtil.getBundleString("message.equipment.select"));
        value.setValue("0");
        arrEquipment.add(value);
        for (int i = 0; i < equipmentList.size(); i++) {
            EquipmentBean employee = (EquipmentBean) equipmentList.get(i);
            value = new LabelValueBean();
            value.setLabel(String.valueOf(StringUtil.decodeString(employee.getEquipmentName())) + " - " + employee.getUsedCode());
            value.setValue(String.valueOf(employee.getEquId()));
            arrEquipment.add(value);
        }
        request.setAttribute(Constants.EQUIPMENT_LIST, arrEquipment);

        //Employee
        EmployeeDAO employeeDAO = new EmployeeDAO();
        ArrayList employeeList = null;
        try {
            employeeList = employeeDAO.getEmployeeOfOrg(MCUtil.getOrganizationID(session));
        } catch (Exception ex) {
        }
        ArrayList arrEmployee = new ArrayList();
        //LabelValueBean value;
        value = new LabelValueBean();
        value.setLabel(MCUtil.getBundleString("message.employee.select"));
        value.setValue("0");
        arrEmployee.add(value);
        for (int i = 0; i < employeeList.size(); i++) {
            EmployeeBean employee = (EmployeeBean) employeeList.get(i);
            value = new LabelValueBean();
            value.setLabel(String.valueOf(StringUtil.decodeString(employee.getFullname())));
            value.setValue(String.valueOf(employee.getEmpId()));
            arrEmployee.add(value);
        }
        request.setAttribute(Constants.EMPLOYEE_LIST, arrEmployee);

        ArrayList arrStatus = new ArrayList();
        value = new LabelValueBean();
        value.setLabel(MCUtil.getBundleString("message.reportdamage.status1"));
        value.setValue(ReportDamageBean.STATUS1 + "");
        arrStatus.add(value);
        value = new LabelValueBean();
        value.setLabel(MCUtil.getBundleString("message.reportdamage.status2"));
        value.setValue(ReportDamageBean.STATUS2 + "");
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
        return PermissionUtil.PER_EQUIPMENT_FACTREPORT;
    }
}
