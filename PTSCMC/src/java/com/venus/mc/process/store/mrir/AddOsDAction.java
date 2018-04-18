/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.process.store.mrir;

import com.venus.core.auth.OnlineUser;
import com.venus.core.util.LogUtil;
import com.venus.core.util.NumberUtil;
import com.venus.core.util.OutputUtil;
import com.venus.mc.bean.OsDBean;
import com.venus.mc.bean.OsDDetailBean;
import com.venus.mc.core.SpineAction;
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
public class AddOsDAction extends SpineAction {

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
        HttpSession session = request.getSession();
        OsDFormBean formBean = (OsDFormBean) form;
        //kiem tra da chon ds vat tu chua
//        String[] detId = formBean.getDetId();
//        if (detId==null) {
//            ActionMessages errors = new ActionMessages();
//            errors.add("materialNoExist", new ActionMessage("errors.material.noexist"));
//            saveErrors(request, errors);
//            return false;            
//        }

        MrirDAO osDDAO = new MrirDAO();
        OsDBean bean = null;
        boolean bNew = false;
        boolean isExist = false;
        try {
            bean = osDDAO.getOsDByNumber(formBean.getOsdNumber());
        } catch (Exception ex) {
        }

        int osdId = formBean.getOsdId();
        if (osdId == 0) {
            bNew = true;
            if (bean != null) {
                isExist = true;
            }
        } else {
            bNew = false;
            if (bean != null && bean.getOsdId() != osdId) {
                isExist = true;
            }
        }

        if (isExist) {
            ActionMessages errors = new ActionMessages();
            errors.add("osdExisted", new ActionMessage("errors.osd.existed"));
            saveErrors(request, errors);
            return false;
        }
        bean = new OsDBean();
        bean.setOsdId(formBean.getOsdId());
        bean.setOsdNumber(formBean.getOsdNumber());
        bean.setMrirId(formBean.getMrirId());
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
                bean.setCreatedEmpId(MCUtil.getMemberID(request.getSession()));
                osdId = osDDAO.insertOsD(bean);
            } else {
                OnlineUser user = MCUtil.getOnlineUser(session);
                LogUtil.info("Username : " + user.getName() + " - Form : " + mapping.getPath() + " - EDIT");
                osDDAO.updateOsD(bean);
            }
            session.setAttribute("id", osdId);

            addDetail(osdId, formBean);
            OutputUtil.sendStringToOutput(response, bean.getMrirId() + "");
        } catch (Exception ex) {
            LogUtil.error("FAILED:OsD:add-" + ex.getMessage());
            OutputUtil.sendStringToOutput(response, "");
        }
        return true;
    }

    private OsDDetailBean detExisted(ArrayList arrDet, int matId) {
        OsDDetailBean bean = null;
        for (int i = 0; i < arrDet.size(); i++) {
            bean = (OsDDetailBean) arrDet.get(i);
            if (bean.getMatId() == matId) {
                return bean;
            }
        }
        return null;
    }

    private void addDetail(int osdId, OsDFormBean formBean) {
        if (osdId > 0) {
            MrirDAO mrirDAO = new MrirDAO();
            String[] detId = formBean.getDetId();
            if (detId != null) {
                for (int i = 0; i < detId.length; i++) {
                    OsDDetailBean matBean = new OsDDetailBean();
                    matBean.setQuantity(NumberUtil.parseDouble(formBean.getQuantity()[i].replaceAll(",", ""), 0));
                    matBean.setReqDetailId(formBean.getReqDetailId()[i]);
                    matBean.setUnit(formBean.getUnit()[i]);
                    matBean.setMatId(formBean.getMatId()[i]);
                    matBean.setOsdId(osdId);
                    if (detId[i].indexOf("dn_") == 0) { //vat tu moi     
                        int newId = 0;
                        try {
                            newId = mrirDAO.insertOsDDetail(matBean);
                        } catch (Exception ex) {
                            LogUtil.error("AddOsDAction: " + ex.getMessage());
                        }
                    } else { //vat tu da ton tai
                        matBean.setDetId(detId[i]);
                        try {
                            mrirDAO.updateOsDDetail(matBean);
                        } catch (Exception ex) {
                            ex.printStackTrace();
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
