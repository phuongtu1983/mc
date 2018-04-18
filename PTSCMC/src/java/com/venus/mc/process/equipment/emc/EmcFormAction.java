/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.process.equipment.emc;

import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.EmcBean;
import com.venus.mc.core.SpineAction;
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
public class EmcFormAction extends SpineAction {

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
            EmcFormBean formBean = null;
            int emcId = NumberUtil.parseInt(request.getParameter("emcId"), 0);
            Integer id = (Integer) session.getAttribute("id");
            //session.removeAttribute("id");
            if (id != null) {
                emcId = id;
            }
            
            if (emcId > 0) {
                EmcDAO emcDAO = new EmcDAO();
                EmcBean bean = emcDAO.getEmc(emcId);
                if (bean != null) {
                    formBean = new EmcFormBean(bean);
                }
            }
            if (formBean == null) {
                formBean = new EmcFormBean();
            }

            formBean.setStatus(2);
            formBean.setResult(1);
            request.setAttribute(Constants.EMC, formBean);

            LabelValueBean value;
            ArrayList arrStatus = new ArrayList();
            value = new LabelValueBean();
            value.setLabel(MCUtil.getBundleString("message.emc.status1"));
            value.setValue("1");
            arrStatus.add(value);
            value = new LabelValueBean();
            value.setLabel(MCUtil.getBundleString("message.emc.status2"));
            value.setValue("2");
            arrStatus.add(value);
            request.setAttribute(Constants.EMC_STATUS_LIST, arrStatus);
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
        return PermissionUtil.PER_EQUIPMENT_EMC;
    }
}
