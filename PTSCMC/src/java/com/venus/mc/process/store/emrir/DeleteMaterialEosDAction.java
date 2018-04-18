/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.process.store.emrir;

import com.venus.core.util.LogUtil;
import com.venus.core.util.NumberUtil;
import com.venus.core.util.OutputUtil;
import com.venus.mc.bean.EosDDetailBean;
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
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

/**
 *
 * @author kngo
 */
public class DeleteMaterialEosDAction extends SpineAction {

    private boolean isStream = false;

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
        String[] chkDetId = formBean.getChkDetId();
        if (chkDetId == null || chkDetId.length == 0) {
            isStream = true;
            OutputUtil.sendStringToOutput(response, "noselect");
            return true;
        }
        isStream = false;        
        String[] detId = formBean.getDetId();
        ArrayList arrMat = new ArrayList();
        EdnDAO ednDAO = new EdnDAO();
        EmrirDAO emrirDAO = new EmrirDAO();
        EosDDetailBean detail = null;
        if (detId != null) {
            for (int i = 0; i < detId.length; i++) {
                //kiem tra danh sach vat tu trong table co nam trong danh sach can xoa hay ko
                if (MCUtil.isInSet(detId[i], chkDetId)) {
                    //neu nam trong danh sach can xoa, kiem tra co ton tai chua, neu ton tai ma chua xu ly thi se xoa
                    int dndId = NumberUtil.parseInt(detId[i], 0);
                    boolean bContinue = true;
                    if (dndId > 0) {
                        try {
                            if (emrirDAO.deleteEosDDetail(dndId) == 0) {
                                //khong the xoa dc vi vat tu da xu ly roi
                                bContinue = false;
                            }
                        } catch (Exception ex) {
                            LogUtil.error("DeleteMaterialOsDAction: " + ex.getMessage());
                        }
                    }
                    if (bContinue) {
                        continue;
                    }
                }
                //neu ko nam trong danh sach bi xoa thi lay lai thong 
                if (detId[i].indexOf("dn_") == 0) { //vat tu moi
                    try {
                        detail = ednDAO.getEdnDetailForOsD(NumberUtil.parseInt(detId[i].substring(3, detId[i].length()), 0));
                    } catch (Exception ex) {
                        LogUtil.error("DeleteMaterialOsDAction: " + ex.getMessage());
                    }
                } else {
                    try {
                        detail = emrirDAO.getEosDMaterial(NumberUtil.parseInt(detId[i], 0));
                    } catch (Exception ex) {
                        LogUtil.error("DeleteMaterialOsDAction: " + ex.getMessage());
                    }
                }
                if (detail != null) {
                    detail.setQuantity(NumberUtil.parseDouble(formBean.getQuantity()[i].replaceAll(",", ""), 0));
                    arrMat.add(detail);
                }
            }
        }
        request.setAttribute(Constants.MATERIAL_LIST, arrMat);
        return true;
    }

    @Override
    protected boolean isReturnStream() {
        return isStream;
    }
}
