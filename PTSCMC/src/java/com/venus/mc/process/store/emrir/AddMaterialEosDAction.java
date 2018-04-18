/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.process.store.emrir;

import com.venus.core.util.LogUtil;
import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.EosDDetailBean;
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
public class AddMaterialEosDAction extends SpineAction {

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
        EosDFormBean formBean = (EosDFormBean) form;
        //int[] reqDetailId = formBean.getReqDetailId();
        String[] detId = formBean.getDetId();
        ArrayList arrMat = new ArrayList();
        EdnDAO ednDAO = new EdnDAO();
        EmrirDAO emrirDAO = new EmrirDAO();
        EosDDetailBean detail = null;
        int dndDetId = formBean.getCbxMaterialOfDn();
        if (detId != null) {
            for (int i = 0; i < detId.length; i++) {
                if (detId[i].equals("dn_" + dndDetId)) {
                    ActionMessages errors = new ActionMessages();
                    errors.add("mrirMaterialExisted", new ActionMessage("errors.mrir.materialexisted"));
                    saveErrors(request, errors);
                    return false;
                }
                if (detId[i].indexOf("dn_") == 0) { //vat tu moi     
                    try {
                        detail = ednDAO.getEdnDetailForOsD(NumberUtil.parseInt(detId[i].substring(3, detId[i].length()), 0));
                    } catch (Exception ex) {
                        LogUtil.error(AddMaterialEosDAction.class.getName() + ": " + ex.getMessage());
                    }
                } else {
                    try {
                        detail = emrirDAO.getEosDMaterial(NumberUtil.parseInt(detId[i], 0));
                    } catch (Exception ex) {
                        LogUtil.error(AddMaterialEosDAction.class.getName() + ": " + ex.getMessage());
                    }
                }
                if (detail != null) {
                    detail.setQuantity(NumberUtil.parseDouble(formBean.getQuantity()[i].replaceAll(",", ""), 0));
                    arrMat.add(detail);
                }
            }
        }
        try {
            detail = ednDAO.getEdnDetailForOsD(dndDetId);
        } catch (Exception ex) {
            LogUtil.error(AddMaterialEosDAction.class.getName() + ": " + ex.getMessage());
        }
        if (detail != null) {
            arrMat.add(detail);
        }
        request.setAttribute(Constants.MATERIAL_LIST, arrMat);
        return true;
    }
}
