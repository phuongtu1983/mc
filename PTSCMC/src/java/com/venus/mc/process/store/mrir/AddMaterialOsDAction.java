/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.process.store.mrir;

import com.venus.core.util.LogUtil;
import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.OsDDetailBean;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import com.venus.mc.util.Constants;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.DnDAO;
import com.venus.mc.dao.MrirDAO;
import java.util.ArrayList;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

/**
 *
 * @author kngo
 */
public class AddMaterialOsDAction extends SpineAction {

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
        OsDFormBean formBean = (OsDFormBean) form;
        //int[] reqDetailId = formBean.getReqDetailId();
        String[] detId = formBean.getDetId();
        ArrayList arrMat = new ArrayList();
        DnDAO dnDAO = new DnDAO();
        MrirDAO mrirDAO = new MrirDAO();
        OsDDetailBean detail = null;
        int dndDetId = formBean.getCbxMaterialOfReq();
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
                        detail = dnDAO.getDnDetailForOsD(NumberUtil.parseInt(detId[i].substring(3, detId[i].length()), 0));
                    } catch (Exception ex) {
                        LogUtil.error("AddMaterialOsDAction: " + ex.getMessage());
                    }
                } else {
                    try {
                        detail = mrirDAO.getOsDMaterial(NumberUtil.parseInt(detId[i], 0));
                    } catch (Exception ex) {
                        LogUtil.error("AddMaterialOsDAction: " + ex.getMessage());
                    }
                }
                if (detail != null) {
                    detail.setQuantity(NumberUtil.parseDouble(formBean.getQuantity()[i].replaceAll(",", ""), 0));
                    arrMat.add(detail);
                }
            }
        }
        try {
            detail = dnDAO.getDnDetailForOsD(dndDetId);
            if (detail != null) {
                arrMat.add(detail);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        request.setAttribute(Constants.MATERIAL_LIST, arrMat);
        return true;
    }
}
