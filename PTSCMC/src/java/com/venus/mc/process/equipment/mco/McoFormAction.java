/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.process.equipment.mco;

import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.McoBean;
import com.venus.mc.core.SpineAction;
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
public class McoFormAction extends SpineAction {

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
            McoFormBean formBean = null;
            int mcoId = NumberUtil.parseInt(request.getParameter("mcoId"), 0);
            Integer id = (Integer) session.getAttribute("id");
            //session.removeAttribute("id");
            if (id != null) {
                mcoId = id;
            }
            
            if (mcoId > 0) {
                McoDAO mcoDAO = new McoDAO();
                McoBean bean = mcoDAO.getMco(mcoId);
                if (bean != null) {
                    formBean = new McoFormBean(bean);
                }
            }
            if (formBean == null) {
                formBean = new McoFormBean();
            }
            formBean.setStatus(2);
            formBean.setResult(1);
            request.setAttribute(Constants.MCO, formBean);

            OrganizationDAO orgDAO = new OrganizationDAO();
            ArrayList arrOrg = orgDAO.getOrgByKind(99);
            request.setAttribute(Constants.ORGANIZATION_LIST, arrOrg);

            ArrayList arrKind = new ArrayList();
            LabelValueBean value;
            value = new LabelValueBean();
            value.setLabel(MCUtil.getBundleString("message.mco.kind1"));
            value.setValue("1");
            arrKind.add(value);
            value = new LabelValueBean();
            value.setLabel(MCUtil.getBundleString("message.mco.kind2"));
            value.setValue("2");
            arrKind.add(value);
            request.setAttribute(Constants.MCO_KIND_LIST, arrKind);

            ArrayList arrStatus = new ArrayList();
            value = new LabelValueBean();
            value.setLabel(MCUtil.getBundleString("message.mco.status1"));
            value.setValue("1");
            arrStatus.add(value);
            value = new LabelValueBean();
            value.setLabel(MCUtil.getBundleString("message.mco.status2"));
            value.setValue("2");
            arrStatus.add(value);
            request.setAttribute(Constants.MCO_STATUS_LIST, arrStatus);
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
        return PermissionUtil.PER_EQUIPMENT_MCO;
    }
}
