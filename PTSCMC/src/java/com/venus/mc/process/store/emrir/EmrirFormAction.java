/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.process.store.emrir;

import com.venus.core.util.DateUtil;
import com.venus.core.util.GenericValidator;
import com.venus.core.util.LogUtil;
import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.EdnBean;
import com.venus.mc.bean.EmrirBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.EdnDAO;
import com.venus.mc.dao.EmrirDAO;
import com.venus.mc.util.Constants;
import com.venus.mc.util.MCUtil;
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
public class EmrirFormAction extends SpineAction {

    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Emrir we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    @Override
    public boolean doAction(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();
        int emrirId = NumberUtil.parseInt(request.getParameter("emrirId"), 0);
        EmrirFormBean formBean = null;
        EmrirBean emrirBean = null;
        EmrirDAO emrirDAO = new EmrirDAO();
        EdnDAO ednDAO = new EdnDAO();
        ArrayList arrMat = null;
        if (emrirId > 0) {
            try {
                emrirBean = emrirDAO.getEmrir(emrirId);
            } catch (Exception ex) {
                LogUtil.error(EmrirFormAction.class.getName() + ": " + ex.getMessage());
            }
            if (emrirBean != null) {
                formBean = new EmrirFormBean(emrirBean);
            }
            //danh sach vat tu hien tai cua Mrir            
            try {
                arrMat = emrirDAO.getEmrirDetailsByEmrir(formBean.getEmrirId());
            } catch (Exception ex) {
                LogUtil.error(EmrirFormAction.class.getName() + ": " + ex.getMessage());
            }
            if (arrMat != null && arrMat.size() > 0) {
                request.setAttribute(Constants.MATERIAL_LIST, arrMat);
            }

            //thong bao giao hang
            ArrayList arrDn = new ArrayList();
            LabelValueBean label = new LabelValueBean();
            label.setValue("" + formBean.getEdnId());
            label.setLabel(formBean.getEdnNumber());
            arrDn.add(0, label);
            request.setAttribute(Constants.EMRIR_DELIVERY_NOTICE_LIST, arrDn);
            //danh sach vat tu cua thong bao giao hang            
            try {
                arrMat = ednDAO.getEdnMaterialList(formBean.getEdnId());
            } catch (Exception ex) {
                LogUtil.error(EmrirFormAction.class.getName() + ": " + ex.getMessage());
            }
            if (arrMat != null && arrMat.size() > 0) {
                request.setAttribute(Constants.EMRIR_MATERIAL_LIST, arrMat);
            }
        } else {
            formBean = new EmrirFormBean();
            formBean.setCreatedDate(DateUtil.today("dd/MM/yyyy"));
            formBean.setCreatedEmpId(MCUtil.getMemberID(session));
            
            int ednId = NumberUtil.parseInt(request.getParameter("ednId"), 0);
            if (ednId > 0) {
                //lay thong tin tu dnd     
                EdnBean ednBean = null;
                try {
                    ednBean = ednDAO.getDn(ednId);
                } catch (Exception ex) {
                    LogUtil.error(EmrirFormAction.class.getName() + ": " + ex.getMessage());
                }
                if (ednBean!=null) {
                    formBean.setProId(ednBean.getProId());
                    formBean.setProName(ednBean.getProName());
                    formBean.setOrgId(ednBean.getCreatedOrg());
                    formBean.setOrgName(ednBean.getOrgName());
                    formBean.setConNumber(ednBean.getContractNumber());
                }
                //danh sach vat tu cua thong bao giao hang            
                formBean.setEdnId(ednId);
                try {
                    arrMat = ednDAO.getEdnMaterialList(ednId);
                } catch (Exception ex) {
                    arrMat = null;
                    LogUtil.error(EmrirFormAction.class.getName() + ": " + ex.getMessage());
                }
                if (arrMat != null && arrMat.size() > 0) {
                    request.setAttribute(Constants.EMRIR_MATERIAL_LIST, arrMat);
                }                
                //tu dong add het vao danh sach vat tu hien co
                try {
                    arrMat = ednDAO.getEdnDetailsForMrir(ednId, 0);
                } catch (Exception ex) {
                    arrMat = null;
                    LogUtil.error(EmrirFormAction.class.getName() + ": " + ex.getMessage());
                }
                if (arrMat != null && arrMat.size() > 0) {
                    request.setAttribute(Constants.MATERIAL_LIST, arrMat);
                }  
                
            }             
            // lay danh sach thong bao giao hang chua xu ly
            ArrayList arrDn = null;
            try {
                arrDn = ednDAO.getEdnForEmrir(0);
            } catch (Exception ex) {
                LogUtil.error(EmrirFormAction.class.getName() + ": " + ex.getMessage());
            }
            LabelValueBean label = new LabelValueBean();
            label.setValue("0");
            label.setLabel(MCUtil.getBundleString("message.emrir.selectDnId"));
            arrDn.add(0, label);
            request.setAttribute(Constants.EMRIR_DELIVERY_NOTICE_LIST, arrDn);
        }
        request.setAttribute(Constants.EMRIR, formBean);
        return true;
    }

    @Override
    protected String getXmlMessage() {
        return "emrirForm";
    }

    @Override
    protected String getXmlParameters(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        if (!GenericValidator.isBlankOrNull(request.getParameter("emrirId"))) {
            return request.getParameter("emrirId");
        } else {
            return "";
        }
    }
}
