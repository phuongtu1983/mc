package com.venus.mc.contractFollow;

/**
 * @author Mai Vinh Loc
 */
import com.venus.core.util.DateUtil;
import com.venus.core.util.NumberUtil;
import com.venus.core.util.StringUtil;
import com.venus.mc.bean.ContractFollowBean;
import com.venus.mc.bean.OrganizationBean;
import com.venus.mc.bean.ProjectBean;
import com.venus.mc.contract.ContractFormBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.ContractFollowDAO;
import com.venus.mc.dao.ContractDAO;
import com.venus.mc.dao.OrganizationDAO;
import com.venus.mc.dao.ProjectDAO;
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

public class ContractFollowFormAction extends SpineAction {

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

        ContractFollowBean bean = new ContractFollowBean();
        String folId = request.getParameter("folId");
        String serviceType = request.getParameter("serviceType");
        Integer id = (Integer) session.getAttribute("id");
        if (id != null) {
            folId = id + "";
        }
        session.removeAttribute("id");
        if (!GenericValidator.isBlankOrNull(folId)) {
            ContractFollowDAO contractFollowDAO = new ContractFollowDAO();
            try {
                bean = contractFollowDAO.getContractFollow(folId);
            } catch (Exception ex) {
            }
        }
        if (bean == null) {
            bean = new ContractFollowBean();
        }

        if (folId == null) {
            bean.setServiceAbility("1");
            bean.setServiceCooperate("1");
            bean.setServiceEquipment("1");
            bean.setServiceLevel("1");
            bean.setServiceProgress("1");
            bean.setServiceSafety("1");
            bean.setGoodAbility("1");
            bean.setGoodCertificate("1");
            bean.setGoodCooperate("1");
            bean.setGoodProgress("1");
            bean.setGoodQuality("1");
            bean.setCreatedDate(DateUtil.today("dd/MM/yyyy"));
            bean.setServiceType("1");
        }
        if (bean != null) {
            if (NumberUtil.parseInt(serviceType, 0) > 0) {
                bean.setServiceType(serviceType);
            }
        }

        request.setAttribute(Constants.CONTRACT_FOLLOW, bean);

        //Contract List
        ContractDAO contractDAO = new ContractDAO();
        ArrayList contractList = null;
        try {
            OrganizationDAO orgDAO = new OrganizationDAO();
            int orgId = MCUtil.getOrganizationID(request.getSession());
            String orgs = orgDAO.getnestedChildOfOrg(orgId + "");
            orgs += "," + orgId;
            contractDAO.setRequestOrg(orgs);
            contractDAO.setRequestEmp(MCUtil.getMemberID(request.getSession()));
            contractList = contractDAO.getContracts(0);
        } catch (Exception ex) {
        }
        ArrayList arrContract = new ArrayList();
        LabelValueBean value;
        value = new LabelValueBean();
        value.setLabel(MCUtil.getBundleString("message.contract.select"));
        value.setValue("0");
        arrContract.add(value);
        for (int i = 0; i < contractList.size(); i++) {
            ContractFormBean contract = (ContractFormBean) contractList.get(i);
            value = new LabelValueBean();
            value.setLabel(String.valueOf(StringUtil.decodeString(contract.getContractNumber())));
            value.setValue(String.valueOf(contract.getConId()));
            arrContract.add(value);
        }
        request.setAttribute(Constants.CONTRACT_LIST, arrContract);

        //Project List
        ProjectDAO projectDAO = new ProjectDAO();
        ArrayList projectList = null;
        try {
            projectList = projectDAO.getProjects();
        } catch (Exception ex) {
        }
        ArrayList arrProject = new ArrayList();
        //LabelValueBean value;
        value = new LabelValueBean();
        value.setLabel(MCUtil.getBundleString("message.project.select"));
        value.setValue("0");
        arrProject.add(value);
        for (int i = 0; i < projectList.size(); i++) {
            ProjectBean project = (ProjectBean) projectList.get(i);
            value = new LabelValueBean();
            value.setLabel(String.valueOf(StringUtil.decodeString(project.getName())));
            value.setValue(String.valueOf(project.getProId()));
            arrProject.add(value);
        }
        request.setAttribute(Constants.PROJECT_LIST, arrProject);

        //SERVICE List
        ArrayList serviceList = null;
        try {
            projectList = projectDAO.getProjects();
        } catch (Exception ex) {
        }
        ArrayList arrService = new ArrayList();
        //LabelValueBean value;
        //value = new LabelValueBean();
        //value.setLabel("");
        //value.setValue("0");
        //arrService.add(value);
        //
        value = new LabelValueBean();
        value.setLabel(MCUtil.getBundleString("message.contractFollow.serviceType1"));
        value.setValue("1");
        arrService.add(value);
        value = new LabelValueBean();
        value.setLabel(MCUtil.getBundleString("message.contractFollow.serviceType2"));
        value.setValue("2");
        arrService.add(value);
        request.setAttribute(Constants.SERVICE_LIST, arrService);

        //Org List
        OrganizationDAO orgDAO = new OrganizationDAO();
        ArrayList orgList = null;
        try {
            orgList = orgDAO.getOrganizations();
        } catch (Exception ex) {
        }
        ArrayList arrOrg = new ArrayList();
        //LabelValueBean value;
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
        request.setAttribute(Constants.ORG_LIST, arrOrg);

        return true;
    }

    @Override
    protected String getFunction() {
        return PermissionUtil.FUNC_VIEW + ";" + PermissionUtil.FUNC_EDIT;
    }

    @Override
    protected int getPermit() {
        return PermissionUtil.PER_PURCHASING_CONTRACT_FOLLOW;
    }
}
