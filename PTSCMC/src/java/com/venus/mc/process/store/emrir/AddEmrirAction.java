/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.process.store.emrir;

import com.venus.core.auth.OnlineUser;
import com.venus.core.util.LogUtil;
import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.EmrirBean;
import com.venus.mc.bean.EmrirDetailBean;
import com.venus.mc.bean.MrirBean;
import com.venus.mc.bean.MrirDetailBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.DnDAO;
import com.venus.mc.dao.EdnDAO;
import com.venus.mc.dao.EmrirDAO;
import com.venus.mc.dao.MrirDAO;
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
public class AddEmrirAction extends SpineAction {

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
        //kiem tra da chon ds vat tu chua
//        String[] detId = formBean.getDetId();
//        if (detId==null) {
//            ActionMessages errors = new ActionMessages();
//            errors.add("materialNoExist", new ActionMessage("errors.material.noexist"));
//            saveErrors(request, errors);
//            return false;            
//        }

        EmrirDAO emrirDAO = new EmrirDAO();
        EmrirBean bean = null;
        boolean bNew = false;
        boolean isExist = false;

        try {
            bean = emrirDAO.getEmrirByNumber(formBean.getEmrirNumber());
        } catch (Exception ex) {
            LogUtil.error(AddEmrirAction.class.getName() + ": " + ex.getMessage());
        }

        int emrirId = formBean.getEmrirId();
        if (emrirId == 0) {
            bNew = true;
            if (bean != null) {
                isExist = true;
            }
        } else {
            bNew = false;
            if (bean != null && bean.getEmrirId() != emrirId) {
                isExist = true;
            }
        }

        if (isExist) {
            ActionMessages errors = new ActionMessages();
            errors.add("mrirExisted", new ActionMessage("errors.mrir.existed"));
            saveErrors(request, errors);
            return false;
        }

        bean = new EmrirBean();
        bean.setEmrirId(formBean.getEmrirId());
        bean.setCreatedDate(formBean.getCreatedDate());
        bean.setOrgId(formBean.getOrgId());
        bean.setProId(formBean.getProId());
        bean.setConNumber(formBean.getConNumber());
        bean.setEmrirNumber(formBean.getEmrirNumber());
        bean.setNote(formBean.getNote());
        bean.setPackingListNo(formBean.getPackingListNo());
        bean.setInvoiceNo(formBean.getInvoiceNo());
        bean.setEdnId(formBean.getEdnId());

        try {
            if (bNew) {
                emrirId = emrirDAO.insertEmrir(bean);
            } else {
                HttpSession session = request.getSession();
                OnlineUser user = MCUtil.getOnlineUser(session);
                LogUtil.info("Username : " + user.getName() + " - Form : " + mapping.getPath() + " - EDIT");
                emrirDAO.updateEmrir(bean);
            }
            addDetail(emrirId, formBean);
        } catch (Exception ex) {
            LogUtil.error(AddEmrirAction.class.getName() + ": " + ex.getMessage());
        }
        return true;
    }

    private MrirDetailBean detExisted(ArrayList arrDet, int matId) {
        MrirDetailBean bean = null;
        for (int i = 0; i < arrDet.size(); i++) {
            bean = (MrirDetailBean) arrDet.get(i);
            if (bean.getMatId() == matId) {
                return bean;
            }
        }
        return null;
    }

    private void updateDeliveryNotice(int dnId, int status) {
        MrirDAO mrirDAO = new MrirDAO();
        try {
            mrirDAO.updateDeliveryNotice(dnId, status);
        } catch (Exception ex) {
        }
    }

    private void addDetail(int emrirId, EmrirFormBean formBean) {
        if (emrirId > 0) {
            EmrirDAO emrirDAO = new EmrirDAO();
            EdnDAO ednDAO = new EdnDAO();
            String[] detId = formBean.getDetId();
            if (detId != null) {
                for (int i = 0; i < detId.length; i++) {
                    EmrirDetailBean matBean = new EmrirDetailBean();
                    matBean.setHeatSerial(formBean.getHeatSerial()[i]);
                    matBean.setPrice(NumberUtil.parseDouble(formBean.getPrice()[i].replaceAll(",", ""), 0));
                    matBean.setQuantity(NumberUtil.parseDouble(formBean.getQuantity()[i].replaceAll(",", ""), 0));
                    matBean.setUnit(formBean.getUnit()[i]);
                    matBean.setEmatId(formBean.getEmatId()[i]);
                    matBean.setEmrirId(emrirId);

                    matBean.setMaterialGrade(formBean.getMaterialGrade()[i]);
                    matBean.setMaterialType(formBean.getMaterialType()[i]);
                    matBean.setRating(formBean.getRating()[i]);
                    matBean.setSize1(formBean.getSize1()[i]);
                    matBean.setSize2(formBean.getSize2()[i]);
                    matBean.setSize3(formBean.getSize3()[i]);
                    matBean.setTraceNo(formBean.getTraceNo()[i]);
                    matBean.setCertNo(formBean.getCertNo()[i]);
                    matBean.setColourCode(formBean.getColourCode()[i]);
                    matBean.setLocation(formBean.getLocation()[i]);
                    matBean.setComment(formBean.getComment()[i]);
                    matBean.setSystemNo(formBean.getSystemNo()[i]);
                    matBean.setItemNo(formBean.getItemNo()[i]);
                    matBean.setMatKind(formBean.getMatKind()[i]);

                    if (detId[i].indexOf("dn_") == 0) { //vat tu moi     
                        int newId = 0;
                        try {
                            newId = emrirDAO.insertEmrirDetail(matBean);
                        } catch (Exception ex) {
                            LogUtil.error(AddEmrirAction.class.getName() + ": " + ex.getMessage());
                        }
                        if (newId > 0) { //da xu ly xong, update thong bao giao hang chi tiet
                            int dndId = NumberUtil.parseInt(detId[i].substring(3, detId[i].length()), 0);
                            try {
                                ednDAO.updateEdnDetailStatus(dndId, 1);
                            } catch (Exception ex) {
                                LogUtil.error(AddEmrirAction.class.getName() + ": " + ex.getMessage());
                            }
                        }
                    } else { //vat tu da ton tai
                        matBean.setDetId(detId[i]);
                        try {
                            emrirDAO.updateEmrirDetail(matBean);
                        } catch (Exception ex) {
                            LogUtil.error(AddEmrirAction.class.getName() + ": " + ex.getMessage());
                        }

                    }

                }
            }

        }
    }
}
