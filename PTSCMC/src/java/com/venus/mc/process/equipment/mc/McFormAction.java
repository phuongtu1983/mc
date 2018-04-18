/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.process.equipment.mc;

import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.McBean;
import com.venus.mc.bean.McoBean;
import com.venus.mc.bean.OrganizationBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.McDAO;
import com.venus.mc.dao.McoDAO;
import com.venus.mc.dao.OrganizationDAO;
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

/**
 *
 * @author kngo
 */
public class McFormAction extends SpineAction {

    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Mio we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    @Override
    public boolean doAction(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

        try {
            McDAO mcDAO = new McDAO();
            McFormBean formBean = null;
            int mcId = NumberUtil.parseInt(request.getParameter("mcId"), 0);
            Integer id = (Integer) session.getAttribute("id");
            //session.removeAttribute("id");
            if (id != null) {
                mcId = id;
            }
            
            if (mcId > 0) {
                McBean bean = mcDAO.getMc(mcId);
                if (bean != null) {
                    formBean = new McFormBean(bean);
                }
            }
            if (formBean == null) {
                formBean = new McFormBean();
            }
            formBean.setStatus(2);
            formBean.setResult(1);
            request.setAttribute(Constants.MC, formBean);

            McoDAO mcoDAO = new McoDAO();
            ArrayList arrOrg = null;
            if (mcId > 0) {
                OrganizationDAO orgDAO = new OrganizationDAO();
                arrOrg = orgDAO.getOrgByKind(99);
            } else {
                arrOrg = mcoDAO.getOrgs();
            }
            OrganizationBean orgBean = new OrganizationBean();
            orgBean.setOrgId(0);
            orgBean.setName("");
            arrOrg.add(0, orgBean);
            request.setAttribute(Constants.ORGANIZATION_LIST, arrOrg);

            ArrayList arrKind = new ArrayList();
            LabelValueBean value;
            value = new LabelValueBean();
            value.setLabel(MCUtil.getBundleString("message.mc.kind1"));
            value.setValue("1");
            arrKind.add(value);
            value = new LabelValueBean();
            value.setLabel(MCUtil.getBundleString("message.mc.kind2"));
            value.setValue("2");
            arrKind.add(value);
            request.setAttribute(Constants.MC_KIND_LIST, arrKind);

            ArrayList arrStatus = new ArrayList();
            value = new LabelValueBean();
            value.setLabel(MCUtil.getBundleString("message.mco.status1"));
            value.setValue("1");
            arrStatus.add(value);
            value = new LabelValueBean();
            value.setLabel(MCUtil.getBundleString("message.mco.status2"));
            value.setValue("2");
            arrStatus.add(value);
            request.setAttribute(Constants.MC_STATUS_LIST, arrStatus);

            ArrayList arrMc = new ArrayList();
            arrMc = mcoDAO.getMcosByOrgId(formBean.getOrgId());
            McoBean mc0 = new McoBean();
            mc0.setMcoId(0);
            mc0.setMcoNumber("");
            arrMc.add(0, mc0);
            request.setAttribute(Constants.MCO_LIST, arrMc);
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
        return PermissionUtil.PER_EQUIPMENT_MC;
    }
}
