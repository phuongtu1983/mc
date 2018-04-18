/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.tenderletter;

import com.venus.core.util.DateUtil;
import com.venus.mc.bean.EmployeeBean;
import com.venus.mc.bean.TenderLetterBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.ContractDAO;
import com.venus.mc.dao.EmployeeDAO;
import com.venus.mc.dao.TenderLetterDAO;
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

/**
 *
 * @author mai vinh loc
 */
public class TenderLetterFormAction extends SpineAction {

    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP TenderLetter we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    @Override
    public boolean doAction(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        TenderLetterBean bean = new TenderLetterBean();
        String tenId = request.getParameter("tenId");

        if (!GenericValidator.isBlankOrNull(tenId)) {
            TenderLetterDAO tenderDAO = new TenderLetterDAO();
            try {
                bean = tenderDAO.getTenderLetter(Integer.parseInt(tenId));
                if (bean == null) {
                    bean = new TenderLetterBean();
                    bean.setCreatedDate(DateUtil.today("dd/MM/yyyy"));
                } else {
                    ContractDAO contractDAO = new ContractDAO();
                    boolean hasContract = contractDAO.hasContractFromTenId(bean.getTenId(), 0);
                    if (!hasContract) {
                        request.setAttribute(Constants.CAN_SAVE, "true");
                    }
                }
            } catch (Exception ex) {
            }
        }

        //Employee List
        EmployeeDAO employeeDAO = new EmployeeDAO();
        ArrayList contractList = null;
        try {
            contractList = employeeDAO.getEmployees();
        } catch (Exception ex) {
        }
        ArrayList arrEmp = new ArrayList();
        LabelValueBean value;
        value = new LabelValueBean();
        value.setLabel(MCUtil.getBundleString("message.employee.select"));
        value.setValue("0");
        arrEmp.add(value);
        for (int i = 0; i < contractList.size(); i++) {
            EmployeeBean empList = (EmployeeBean) contractList.get(i);
            value = new LabelValueBean();
            value.setLabel(String.valueOf(empList.getFullname()));
            value.setValue(String.valueOf(empList.getEmpId()));
            arrEmp.add(value);
        }
        request.setAttribute(Constants.EMPLOYEE_LIST, arrEmp);

        request.setAttribute(Constants.TENDER_LETTER, bean);

        return true;
    }

    @Override
    protected String getFunction() {
        return PermissionUtil.FUNC_VIEW + ";" + PermissionUtil.FUNC_EDIT;
    }

    @Override
    protected int getPermit() {
        return PermissionUtil.PER_PURCHASING_TENDERPLAN;
    }
}
