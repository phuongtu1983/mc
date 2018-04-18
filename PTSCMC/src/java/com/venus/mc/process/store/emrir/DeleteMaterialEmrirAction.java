/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.process.store.emrir;

import com.venus.core.util.LogUtil;
import com.venus.core.util.NumberUtil;
import com.venus.core.util.OutputUtil;
import com.venus.mc.bean.EmrirDetailBean;

import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.EdnDAO;
import com.venus.mc.dao.EmrirDAO;
import com.venus.mc.util.Constants;
import com.venus.mc.util.MCUtil;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author kngo
 */
public class DeleteMaterialEmrirAction extends SpineAction {

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
        EmrirFormBean formBean = (EmrirFormBean) form;
        String[] chkDetId = formBean.getChkDetId();
        if (chkDetId == null || chkDetId.length == 0) {
            isStream = true;
            OutputUtil.sendStringToOutput(response, "noselect");
            return true;
        }
        isStream = false;
        //int[] reqDetailId = formBean.getReqDetailId();
        String[] detId = formBean.getDetId();
        ArrayList arrMat = new ArrayList();
        EdnDAO ednDAO = new EdnDAO();
        EmrirDAO emrirDAO = new EmrirDAO();
        EmrirDetailBean detail = null;
        if (detId != null) {
            for (int i = 0; i < detId.length; i++) {
                //kiem tra danh sach vat tu trong table co nam trong danh sach can xoa hay ko
                if (MCUtil.isInSet(detId[i], chkDetId)) {
                    //neu nam trong danh sach can xoa, kiem tra co ton tai chua, neu ton tai ma chua xu ly thi se xoa
                    int dndId = NumberUtil.parseInt(detId[i], 0);
                    boolean bContinue = true;
                    if (dndId > 0) {
                        try {
                            if (emrirDAO.deleteEmrirDetail(dndId) == 0) {
                                //khong the xoa dc vi vat tu da xu ly roi
                                bContinue = false;
                            } else {
                                try {
                                    ednDAO.updateEdnDetailStatus(formBean.getEdnId(), 0);
                                } catch (Exception ex) {
                                    LogUtil.error(DeleteMaterialEmrirAction.class.getName() + ": " + ex.getMessage());
                                }
                            }
                        } catch (Exception ex) {
                            LogUtil.error(DeleteMaterialEmrirAction.class.getName() + ": " + ex.getMessage());
                        }

                    }
                    if (bContinue) {
                        continue;
                    }
                }
                //neu ko nam trong danh sach bi xoa thi lay lai thong 
                if (detId[i].indexOf("dn_") == 0) { //vat tu moi
                    try {
                        detail = ednDAO.getEdnDetailForMrir(NumberUtil.parseInt(detId[i].substring(3, detId[i].length()), 0));
                    } catch (Exception ex) {
                        LogUtil.error(DeleteMaterialEmrirAction.class.getName() + ": " + ex.getMessage());
                    }
                } else {
                    try {
                        detail = emrirDAO.getEmrirMaterial(NumberUtil.parseInt(detId[i], 0));
                    } catch (Exception ex) {
                        LogUtil.error(DeleteMaterialEmrirAction.class.getName() + ": " + ex.getMessage());
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
        request.setAttribute(Constants.MATERIAL_LIST, arrMat);
        if (!isStream) {
            actionForwardResult = "success";
        }
        return true;
    }

    @Override
    protected boolean isReturnStream() {
        return isStream;
    }
}
