/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.process.store.emrir;

import com.venus.core.util.DateUtil;
import com.venus.core.util.LogUtil;
import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.EmrirBean;
import com.venus.mc.bean.EosDBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.EdnDAO;
import com.venus.mc.dao.EmployeeDAO;
import com.venus.mc.dao.EmrirDAO;
import com.venus.mc.util.Constants;
import com.venus.mc.util.MCUtil;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author kngo
 */
public class EosDFormAction extends SpineAction {

    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Mrir we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    @Override
    public boolean doAction(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();
        int emrirId = NumberUtil.parseInt(request.getParameter("emrirId"), 0);
        EosDFormBean formBean = null;
        EmrirBean emrirBean = null;
        EosDBean bean = null;
        EmrirDAO emrirDAO = new EmrirDAO();
        try {
            bean = emrirDAO.getEosDByEmrir(emrirId);
        } catch (Exception ex) {
            LogUtil.error(EosDFormAction.class.getName() + ": " + ex.getMessage());
        }
        if (bean == null) {
            formBean = new EosDFormBean();
            formBean.setCreatedDate(DateUtil.today("dd/MM/yyyy"));
            formBean.setEmrirId(emrirId);
            formBean.setCreatedEmpId(MCUtil.getMemberID(session));
            formBean.setCreatedEmpName(MCUtil.getMemberFullName(session));
            formBean.setDueDate(DateUtil.getDate(new java.util.Date(), 3, "dd/MM/yyyy"));
            formBean.setClosedDate(DateUtil.getDate(new java.util.Date(), 7, "dd/MM/yyyy"));
        } else {
            formBean = new EosDFormBean(bean);
            ArrayList arrMat = null;
            try {
                arrMat = emrirDAO.getEosDDetailsByEosD(bean.getEosdId());
            } catch (Exception ex) {
                LogUtil.error(EosDFormAction.class.getName() + ": " + ex.getMessage());
            }
            request.setAttribute(Constants.MATERIAL_LIST, arrMat);
        }        
        ArrayList arrEmp = null;
        EmployeeDAO empDAO = new EmployeeDAO();
        try {
            arrEmp = empDAO.getEmployees();
        } catch (Exception ex) {
            LogUtil.error(EosDFormAction.class.getName() + ": " + ex.getMessage());
        }
        if (arrEmp == null) {
            arrEmp = new ArrayList();
        }
        request.setAttribute(Constants.EOSD_EMPLOYEE_LIST, arrEmp);
        try {
            emrirBean = emrirDAO.getEmrir(emrirId);
        } catch (Exception ex) {
            LogUtil.error(EosDFormAction.class.getName() + ": " + ex.getMessage());
        }
        ArrayList arrMat = null;
        if (emrirBean != null) {
            formBean.setEdnId(emrirBean.getEdnId());
            formBean.setEconNumber(emrirBean.getConNumber());            
            EdnDAO ednDAO = new EdnDAO();
            try {                
                arrMat = ednDAO.getEdnMaterialList(emrirBean.getEdnId());
            } catch (Exception ex) {
                LogUtil.error(EosDFormAction.class.getName() + ": " + ex.getMessage());
            }
        }
        request.setAttribute(Constants.EOSD, formBean);
        if (arrMat == null) {
            arrMat = new ArrayList();
        }
        request.setAttribute(Constants.EOSD_MATERIAL_LIST, arrMat);
        return true;
    }
}
