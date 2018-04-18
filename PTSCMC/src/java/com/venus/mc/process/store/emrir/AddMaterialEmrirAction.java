/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.process.store.emrir;

import com.venus.core.util.LogUtil;
import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.EmrirDetailBean;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import com.venus.mc.util.Constants;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.EdnDAO;
import com.venus.mc.dao.EmrirDAO;
import java.util.ArrayList;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

/**
 *
 * @author kngo
 */
public class AddMaterialEmrirAction extends SpineAction {

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
        EmrirFormBean formBean = (EmrirFormBean) form;
        String[] detId = formBean.getDetId();
        ArrayList arrMat = new ArrayList();
        EdnDAO ednDAO = new EdnDAO();
        EmrirDAO emrirDAO = new EmrirDAO();
        EmrirDetailBean detail = null;
        int dnId = formBean.getCbxMaterialOfDn();
        try {
            if (detId != null) {
                if (ednDAO.getEdnDetailStatus(dnId) == 1) { //vat tu da tao MRIR roi
                    ActionMessages errors = new ActionMessages();
                    errors.add("mrirMaterialExisted", new ActionMessage("errors.mrir.materialexisted"));
                    saveErrors(request, errors);
                    return false;
                }
                for (int i = 0; i < detId.length; i++) {
                    if (detId[i].equals("dn_" + dnId)) {
                        ActionMessages errors = new ActionMessages();
                        errors.add("mrirMaterialExisted", new ActionMessage("errors.mrir.materialexisted"));
                        saveErrors(request, errors);
                        return false;
                    }
                    if (detId[i].indexOf("dn_") == 0) { //vat tu moi     
                        try {
                            detail = ednDAO.getEdnDetailForMrir(NumberUtil.parseInt(detId[i].substring(3, detId[i].length()), 0));
                        } catch (Exception ex) {
                            LogUtil.error(AddMaterialEmrirAction.class.getName() + ": " + ex.getMessage());
                        }
                    } else {
                        try {
                            detail = emrirDAO.getEmrirMaterial(NumberUtil.parseInt(detId[i], 0));
                        } catch (Exception ex) {
                            LogUtil.error(AddMaterialEmrirAction.class.getName() + ": " + ex.getMessage());
                        }
                    }

                    if (detail != null) {
                        detail.setQuantity(NumberUtil.parseDouble(formBean.getQuantity()[i].replaceAll(",", ""), 0));
                        detail.setHeatSerial(formBean.getHeatSerial()[i]);
                        detail.setMaterialGrade(formBean.getMaterialGrade()[i]);
                        detail.setMaterialType(formBean.getMaterialType()[i]);
                        detail.setRating(formBean.getRating()[i]);
                        detail.setSize1(formBean.getSize1()[i]);
                        detail.setSize2(formBean.getSize2()[i]);
                        detail.setSize3(formBean.getSize3()[i]);
                        detail.setTraceNo(formBean.getTraceNo()[i]);
                        detail.setCertNo(formBean.getCertNo()[i]);
                        detail.setColourCode(formBean.getColourCode()[i]);
                        arrMat.add(detail);
                    }
                }
            }
        } catch (Exception ex) {
            LogUtil.error(AddMaterialEmrirAction.class.getName() + ": " + ex.getMessage());
        }
        try {
            detail = ednDAO.getEdnDetailForMrir(dnId);
        } catch (Exception ex) {
            LogUtil.error(AddMaterialEmrirAction.class.getName() + ": " + ex.getMessage());
        }
        if (detail != null) {
            arrMat.add(detail);
        }
        request.setAttribute(Constants.MATERIAL_LIST, arrMat);
        return true;
    }
}
