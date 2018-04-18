/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.process.equipment.mc;

import com.venus.core.auth.OnlineUser;
import com.venus.core.util.LogUtil;
import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.McBean;
import com.venus.mc.bean.McDetailBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.McDAO;
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
public class AddMcAction extends SpineAction {

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
        McFormBean formBean = (McFormBean) form;
        McDAO mcDAO = new McDAO();
        McBean bean = null;

        boolean bNew = false;
        boolean isExist = false;

        try {
            bean = mcDAO.getMcByNumber(formBean.getMcNumber());
        } catch (Exception ex) {
        }

        int mcId = formBean.getMcId();
        if (mcId == 0) {
            bNew = true;
            if (bean != null) {
                isExist = true;
            }
        } else {
            bNew = false;
            if (bean != null && bean.getMcId() != mcId) {
                isExist = true;
            }
        }

        if (isExist) {
            ActionMessages errors = new ActionMessages();
            errors.add("mcExisted", new ActionMessage("errors.mc.existed"));
            saveErrors(request, errors);
            return false;
        }

        bean = new McBean();
        bean.setMcId(formBean.getMcId());
        bean.setMcNumber(formBean.getMcNumber());
        bean.setOrgId(formBean.getOrgId());
        bean.setKind(formBean.getKind());
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
                mcId = mcDAO.insertMc(bean);
            } else {
                OnlineUser user = MCUtil.getOnlineUser(session);
                LogUtil.info("Username : " + user.getName() + " - Form : " + mapping.getPath() + " - EDIT");
                mcDAO.updateMc(bean);
            }
            session.setAttribute("id", mcId);
            addDetail(mcId, formBean);
        } catch (Exception ex) {
            LogUtil.error("FAILED:Mc:add-" + ex.getMessage());
            ex.printStackTrace();
        }
        return true;
    }

    private McDetailBean detExisted(ArrayList arrDet, int equId) {
        McDetailBean bean = null;
        for (int i = 0; i < arrDet.size(); i++) {
            bean = (McDetailBean) arrDet.get(i);
            if (bean.getEquId() == equId) {
                return bean;
            }
        }
        return null;
    }

    private void addDetail(int mcId, McFormBean formBean) {
        if (mcId > 0) {
            McDAO mcDAO = new McDAO();
            ArrayList arrDet = null;
            try {
                arrDet = mcDAO.getMcDetailsByMc(mcId);
            } catch (Exception ex) {
                arrDet = new ArrayList();
            }

            int quantity = 1;
            String spec = "";

            if (formBean.getEquipment() != null) {
                String[] equIds = formBean.getEquipment();
                McDetailBean mcDetailBean = null;
                int currEquId = 0;
                for (int i = 0; i < equIds.length; i++) {
                    currEquId = NumberUtil.parseInt(equIds[i], 0);
                    mcDetailBean = detExisted(arrDet, currEquId);
                    if (mcDetailBean == null) { // insert
                        try {
                            mcDetailBean = new McDetailBean();
                            mcDetailBean.setEquId(currEquId);
                            mcDetailBean.setMcId(mcId);
                            mcDetailBean.setUnit(formBean.getUnit()[i]);
                            mcDetailBean.setQuantity(NumberUtil.parseDouble(formBean.getQuantity()[i], 0));
                            mcDetailBean.setSpec(formBean.getSpec()[i]);
                            mcDetailBean.setMcoDetailId(NumberUtil.parseInt(formBean.getMcoDetailId()[i], 0));
                            mcDAO.insertMcDetail(mcDetailBean);
                        } catch (Exception ex) {
                        }
                    } else { // update
                        boolean bUpdated = false;
                        spec = formBean.getSpec()[i];
                        if (!spec.equals(mcDetailBean.getSpec())) {
                            mcDetailBean.setSpec(spec);
                            bUpdated = true;
                        }

                        if (bUpdated) {
                            try {
                                mcDAO.updateMcDetail(mcDetailBean);
                            } catch (Exception ex) {
                            }
                        }
                    }
                }
            }
        }
    }
}
