package com.venus.mc.process.store.equipment.transferprocess;

/**
 * @author Mai Vinh Loc
 */
import com.venus.core.util.NumberUtil;
import com.venus.core.util.StringUtil;
import com.venus.mc.bean.OrganizationBean;
import com.venus.mc.bean.TransferProcessBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.EmployeeDAO;
import com.venus.mc.dao.OrganizationDAO;
import com.venus.mc.dao.TransferProcessDAO;
import com.venus.mc.util.Constants;
import com.venus.mc.util.MCUtil;
import com.venus.mc.util.PermissionUtil;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.LabelValueBean;

public class TransferProcessFormAction extends SpineAction {

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
        int tpId = NumberUtil.parseInt(request.getParameter("tpId"), 0);
        int equId = NumberUtil.parseInt(request.getParameter("equId"), 0);
        int assId = NumberUtil.parseInt(request.getParameter("assId"), 0);
        if (tpId > 0) {
            TransferProcessDAO tpDAO = new TransferProcessDAO();
            try {
                bean = tpDAO.getTransferProcess(tpId, equId, assId);
            } catch (Exception ex) {
            }
        }
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
        EmployeeDAO empDAO = new EmployeeDAO();
        try {
            ArrayList empList = empDAO.getEmployeeListByOrg(bean.getReceiveOrg());
            empList.add(0, new LabelValueBean(MCUtil.getBundleString("message.employee.select"), "0"));
            request.setAttribute(Constants.EMP_LIST, empList);
        } catch (Exception ex) {
        }

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
