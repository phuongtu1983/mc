/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.process.store.emrir;

import com.venus.core.auth.OnlineUser;
import com.venus.core.util.LogUtil;
import com.venus.core.util.NumberUtil;
import com.venus.core.util.OutputUtil;
import com.venus.mc.bean.EosDBean;
import com.venus.mc.bean.EosDDetailBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.EmrirDAO;
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
public class AddEosDAction extends SpineAction {

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
        EmrirDAO osDDAO = new EmrirDAO();
        EosDBean bean = null;

        boolean bNew = false;
        boolean isExist = false;

        try {
            bean = osDDAO.getEosDByNumber(formBean.getEosdNumber());
        } catch (Exception ex) {
        }

        int eosdId = formBean.getEosdId();
        if (eosdId == 0) {
            bNew = true;
            if (bean != null) {
                isExist = true;
            }
        } else {
            bNew = false;
            if (bean != null && bean.getEosdId() != eosdId) {
                isExist = true;
            }
        }

        if (isExist) {
            ActionMessages errors = new ActionMessages();
            errors.add("osdExisted", new ActionMessage("errors.osd.existed"));
            saveErrors(request, errors);
            return false;
        }
        bean = new EosDBean();
        bean.setEosdId(formBean.getEosdId());
        bean.setCreatedEmpId(MCUtil.getMemberID(request.getSession()));
        bean.setEosdNumber(formBean.getEosdNumber());
        bean.setEmrirId(formBean.getEmrirId());
        bean.setDescription(formBean.getDescription());
        bean.setCorrectAct(formBean.getCorrectAct());
        bean.setActionBy(formBean.getActionBy());
        bean.setDueDate(formBean.getDueDate());
        bean.setClosed(formBean.getClosed());
        bean.setClosedDate(formBean.getClosedDate());
        bean.setNote(formBean.getNote());

        if (formBean.getNonConform() != null) {
            int length = formBean.getNonConform().length;
            String appConform = formBean.getNonConform()[0];
            for (int i = 1; i < length; i++) {
                appConform += "," + formBean.getNonConform()[i];
            }
            bean.setNonConform(appConform);
        }

        try {
            if (bNew) {
                eosdId = osDDAO.insertEosD(bean);
            } else {
                HttpSession session = request.getSession();
                OnlineUser user = MCUtil.getOnlineUser(session);
                LogUtil.info("Username : " + user.getName() + " - Form : " + mapping.getPath() + " - EDIT");
                osDDAO.updateEosD(bean);
            }
            addDetail(eosdId, formBean);
            OutputUtil.sendStringToOutput(response, bean.getEmrirId() + "");
        } catch (Exception ex) {
            LogUtil.error(AddEosDAction.class.getName() + ": " + ex.getMessage());
            OutputUtil.sendStringToOutput(response, "");
        }
        return true;
    }

    private EosDDetailBean detExisted(ArrayList arrDet, int ematId) {
        EosDDetailBean bean = null;
        for (int i = 0; i < arrDet.size(); i++) {
            bean = (EosDDetailBean) arrDet.get(i);
            if (bean.getEmatId() == ematId) {
                return bean;
            }
        }
        return null;
    }

    private void addDetail(int eosdId, EosDFormBean formBean) {
        if (eosdId > 0) {
            EmrirDAO emrirDAO = new EmrirDAO();
            String[] detId = formBean.getDetId();
            if (detId != null) {
                for (int i = 0; i < detId.length; i++) {
                    EosDDetailBean matBean = new EosDDetailBean();
                    matBean.setQuantity(NumberUtil.parseDouble(formBean.getQuantity()[i].replaceAll(",", ""), 0));
                    matBean.setUnit(formBean.getUnit()[i]);
                    matBean.setEmatId(formBean.getEmatId()[i]);
                    matBean.setEosdId(eosdId);
                    if (detId[i].indexOf("dn_") == 0) { //vat tu moi     
                        int newId = 0;
                        try {
                            newId = emrirDAO.insertEosDDetail(matBean);
                        } catch (Exception ex) {
                            LogUtil.error(AddEosDAction.class.getName() + ": " + ex.getMessage());
                        }
                    } else { //vat tu da ton tai
                        matBean.setDetId(detId[i]);
                        try {
                            emrirDAO.updateEosDDetail(matBean);
                        } catch (Exception ex) {
                            LogUtil.error(AddEosDAction.class.getName() + ": " + ex.getMessage());
                        }

                    }

                }
            }

        }
    }

    @Override
    protected boolean isReturnStream() {
        return true;
    }
}
