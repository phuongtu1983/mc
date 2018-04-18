/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.process.equipment.emc;

import com.venus.core.auth.OnlineUser;
import com.venus.core.util.LogUtil;
import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.EmcBean;
import com.venus.mc.bean.EmcDetailBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.EmcDAO;
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
public class AddEmcAction extends SpineAction {

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
        EmcFormBean formBean = (EmcFormBean) form;
        EmcDAO emcDAO = new EmcDAO();
        EmcBean bean = null;

        boolean bNew = false;
        boolean isExist = false;

        try {
            bean = emcDAO.getEmcByNumber(formBean.getEmcNumber());
        } catch (Exception ex) {
        }

        int emcId = formBean.getEmcId();
        if (emcId == 0) {
            bNew = true;
            if (bean != null) {
                isExist = true;
            }
        } else {
            bNew = false;
            if (bean != null && bean.getEmcId() != emcId) {
                isExist = true;
            }
        }

        if (isExist) {
            ActionMessages errors = new ActionMessages();
            errors.add("emcExisted", new ActionMessage("errors.emc.existed"));
            saveErrors(request, errors);
            return false;
        }

        bean = new EmcBean();
        bean.setEmcId(formBean.getEmcId());
        bean.setEmcNumber(formBean.getEmcNumber());
        bean.setDepartment(formBean.getDepartment());
        bean.setCarryOutDatePlan(formBean.getCarryOutDatePlan());
        bean.setCarryOnDate(formBean.getCarryOnDate());
        bean.setCarryOnHour(formBean.getCarryOnHour());
        bean.setCarryOnMinute(formBean.getCarryOnMinute());
        bean.setExplanation(formBean.getExplanation());
        bean.setDescCarryOn(formBean.getDescCarryOn());
        bean.setDescNotCarryOn(formBean.getDescNotCarryOn());
        bean.setStatus(formBean.getStatus());
        bean.setResult(formBean.getResult());

        try {
            if (bNew) {
                bean.setCreatedEmp(MCUtil.getMemberID(request.getSession()));
                emcId = emcDAO.insertEmc(bean);
            } else {
                OnlineUser user = MCUtil.getOnlineUser(session);
                LogUtil.info("Username : " + user.getName() + " - Form : " + mapping.getPath() + " - EDIT");
                emcDAO.updateEmc(bean);
            }
            session.setAttribute("id", emcId);
            addDetail(emcId, formBean);
        } catch (Exception ex) {
            LogUtil.error("FAILED:Emc:add-" + ex.getMessage());
            ex.printStackTrace();
        }
        return true;
    }

    private EmcDetailBean detExisted(ArrayList arrDet, String equipment) {
        EmcDetailBean bean = null;
        for (int i = 0; i < arrDet.size(); i++) {
            bean = (EmcDetailBean) arrDet.get(i);
            if (bean.getEquipment().equals(equipment)) {
                return bean;
            }
        }
        return null;
    }

    private void addDetail(int emcId, EmcFormBean formBean) {
        if (emcId > 0) {
            EmcDAO emcDAO = new EmcDAO();
            ArrayList arrDet = null;
            try {
                arrDet = emcDAO.getEmcDetailsByEmc(emcId);
            } catch (Exception ex) {
                arrDet = new ArrayList();
            }

            int quantity = 0;
            String spec = "", unit = "";

            if (formBean.getEquipment() != null) {
                String[] equIds = formBean.getEquipment();
                EmcDetailBean emcDetailBean = null;
                for (int i = 0; i < equIds.length; i++) {
                    emcDetailBean = detExisted(arrDet, equIds[i]);
                    if (emcDetailBean == null) { // insert
                        try {
                            emcDetailBean = new EmcDetailBean();
                            emcDetailBean.setEquipment(equIds[i]);
                            emcDetailBean.setEmcId(emcId);
                            emcDetailBean.setUnit(formBean.getUnit()[i]);
                            emcDetailBean.setQuantity(NumberUtil.parseInt(formBean.getQuantity()[i], 0));
                            emcDetailBean.setSpec(formBean.getSpec()[i]);
                            emcDAO.insertEmcDetail(emcDetailBean);
                        } catch (Exception ex) {
                        }
                    } else { // update
                        boolean bUpdated = false;
                        unit = formBean.getUnit()[i];
                        if (!unit.equals(emcDetailBean.getUnit())) {
                            emcDetailBean.setUnit(unit);
                            bUpdated = true;
                        }

                        quantity = NumberUtil.parseInt(formBean.getQuantity()[i], 0);
                        if (quantity != emcDetailBean.getQuantity()) {
                            emcDetailBean.setQuantity(quantity);
                            bUpdated = true;
                        }

                        spec = formBean.getSpec()[i];
                        if (!spec.equals(emcDetailBean.getSpec())) {
                            emcDetailBean.setSpec(spec);
                            bUpdated = true;
                        }

                        if (bUpdated) {
                            try {
                                emcDAO.updateEmcDetail(emcDetailBean);
                            } catch (Exception ex) {
                            }
                        }
                    }
                }
            }
        }
    }
}
