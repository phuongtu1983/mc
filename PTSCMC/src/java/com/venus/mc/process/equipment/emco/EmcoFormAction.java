/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.process.equipment.emco;

import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.EmcoBean;
import com.venus.mc.bean.EmcBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.EmcoDAO;
import com.venus.mc.dao.EmcDAO;
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
public class EmcoFormAction extends SpineAction {

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
            EmcoDAO emcoDAO = new EmcoDAO();
            EmcoFormBean formBean = null;
            int emcoId = NumberUtil.parseInt(request.getParameter("emcoId"), 0);
            Integer id = (Integer) session.getAttribute("id");
            //session.removeAttribute("id");
            if (id != null) {
                emcoId = id;
            }
            
            if (emcoId > 0) {
                EmcoBean bean = emcoDAO.getEmco(emcoId);
                if (bean != null) {
                    formBean = new EmcoFormBean(bean);
                }
            }

            if (formBean == null) {
                formBean = new EmcoFormBean();
            }

            formBean.setStatus(2);
            formBean.setResult(1);
            request.setAttribute(Constants.EMCO, formBean);

            LabelValueBean value;
            EmcDAO emcDAO = new EmcDAO();
            ArrayList arrDept = new ArrayList();
            if (emcoId == 0) {
                arrDept = emcDAO.getDepartments();
            } else {
                arrDept = emcDAO.getDepartmentsNoStatus();
            }
            value = new LabelValueBean();
            value.setLabel("");
            value.setValue("");
            arrDept.add(0, value);
            request.setAttribute(Constants.EMCO_DEPARMENT_LIST, arrDept);

            ArrayList arrStatus = new ArrayList();
            value = new LabelValueBean();
            value.setLabel(MCUtil.getBundleString("message.emco.status1"));
            value.setValue("1");
            arrStatus.add(value);
            value = new LabelValueBean();
            value.setLabel(MCUtil.getBundleString("message.emco.status2"));
            value.setValue("2");
            arrStatus.add(value);
            request.setAttribute(Constants.EMCO_STATUS_LIST, arrStatus);

            ArrayList arrEmc = new ArrayList();
            if (emcoId == 0) {
                arrEmc = emcDAO.getEmcsByDept(formBean.getDepartment());
                EmcBean emc0 = new EmcBean();
                emc0.setEmcId(0);
                emc0.setEmcNumber("");
                arrEmc.add(0, emc0);
            }
            request.setAttribute(Constants.EMC_LIST, arrEmc);
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
        return PermissionUtil.PER_EQUIPMENT_EMCO;
    }
}
