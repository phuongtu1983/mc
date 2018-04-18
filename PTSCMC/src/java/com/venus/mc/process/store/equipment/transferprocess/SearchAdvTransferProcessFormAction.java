package com.venus.mc.process.store.equipment.transferprocess;

/**
 * @author Mai Vinh Loc
 */
import com.venus.core.util.StringUtil;
import com.venus.mc.bean.EmployeeBean;
import com.venus.mc.bean.OrganizationBean;
import com.venus.mc.bean.TransferProcessBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.EmployeeDAO;
import com.venus.mc.dao.OrganizationDAO;
import com.venus.mc.util.Constants;
import com.venus.mc.util.MCUtil;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.LabelValueBean;

public class SearchAdvTransferProcessFormAction extends SpineAction {

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

        TransferProcessBean bean = new TransferProcessBean();
        request.setAttribute(Constants.TRANSFERPROCESS, bean);

        //ORG
        ArrayList orgList = null;
        try {
            OrganizationDAO orgDAO = new OrganizationDAO();
            orgList = orgDAO.getOrgByKind(0);
        } catch (Exception ex) {
        }
        ArrayList arrOrg = new ArrayList();
        LabelValueBean value;
        value = new LabelValueBean();
        value.setLabel(MCUtil.getBundleString("message.organization.select"));
        value.setValue("0");
        arrOrg.add(value);
        for (int i = 0; i < orgList.size(); i++) {
            OrganizationBean org = (OrganizationBean) orgList.get(i);
            value = new LabelValueBean();
            value.setLabel(String.valueOf(StringUtil.decodeString(org.getName())));
            value.setValue(String.valueOf(org.getOrgId()));
            arrOrg.add(value);
        }
        request.setAttribute(Constants.TRANSFERPROCESS_ORG_LIST, arrOrg);

        //PROJECT
        ArrayList proList = null;
        try {
            OrganizationDAO orgDAO = new OrganizationDAO();
            proList = orgDAO.getOrgByKind(OrganizationBean.KIND_PROJECT);
        } catch (Exception ex) {
        }
        ArrayList arrPro = new ArrayList();
        //    LabelValueBean value;
        value = new LabelValueBean();
        value.setLabel(MCUtil.getBundleString("message.project.select"));
        value.setValue("0");
        arrPro.add(value);
        for (int i = 0; i < proList.size(); i++) {
            OrganizationBean pro = (OrganizationBean) proList.get(i);
            value = new LabelValueBean();
            value.setLabel(String.valueOf(StringUtil.decodeString(pro.getName())));
            value.setValue(String.valueOf(pro.getOrgId()));
            arrPro.add(value);
        }
        request.setAttribute(Constants.TRANSFERPROCESS_PROJECT_LIST, arrPro);

        //EMP
        ArrayList empList = null;
        try {
            EmployeeDAO empDAO = new EmployeeDAO();
            empList = empDAO.getEmployees();
        } catch (Exception ex) {
        }
        ArrayList arrEmp = new ArrayList();
        //    LabelValueBean value;
        value = new LabelValueBean();
        value.setLabel(MCUtil.getBundleString("message.employee.select"));
        value.setValue("0");
        arrEmp.add(value);
        for (int i = 0; i < empList.size(); i++) {
            EmployeeBean emp = (EmployeeBean) empList.get(i);
            value = new LabelValueBean();
            value.setLabel(String.valueOf(StringUtil.decodeString(emp.getFullname())));
            value.setValue(String.valueOf(emp.getEmpId()));
            arrEmp.add(value);
        }
        request.setAttribute(Constants.TRANSFERPROCESS_EMP_LIST, arrEmp);

        return true;
    }
}
