/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.process.equipment.emco;

import com.venus.core.auth.OnlineUser;
import com.venus.core.util.LogUtil;
import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.EmcoBean;
import com.venus.mc.bean.EmcoDetailBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.EmcoDAO;
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
public class AddEmcoAction extends SpineAction {

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
        EmcoFormBean formBean = (EmcoFormBean) form;
        EmcoDAO emcoDAO = new EmcoDAO();
        EmcoBean bean = null;

        boolean bNew = false;
        boolean isExist = false;

        try {
            bean = emcoDAO.getEmcoByNumber(formBean.getEmcoNumber());
        } catch (Exception ex) {
        }

        int emcoId = formBean.getEmcoId();
        if (emcoId == 0) {
            bNew = true;
            if (bean != null) {
                isExist = true;
            }
        } else {
            bNew = false;
            if (bean != null && bean.getEmcoId() != emcoId) {
                isExist = true;
            }
        }

        if (isExist) {
            ActionMessages errors = new ActionMessages();
            errors.add("emcoExisted", new ActionMessage("errors.emco.existed"));
            saveErrors(request, errors);
            return false;
        }

        bean = new EmcoBean();
        bean.setEmcoId(formBean.getEmcoId());
        bean.setEmcoNumber(formBean.getEmcoNumber());
        bean.setDepartment(formBean.getDepartment());
        bean.setCarryOutDate(formBean.getCarryOutDate());
        bean.setCarryOutHour(formBean.getCarryOutHour());
        bean.setCarryOutMinute(formBean.getCarryOutMinute());
        bean.setExplanation(formBean.getExplanation());
        bean.setDescCarryOut(formBean.getDescCarryOut());
        bean.setDescNotCarryOut(formBean.getDescNotCarryOut());
        bean.setStatus(formBean.getStatus());
        bean.setResult(formBean.getResult());

        try {
            if (bNew) {
                bean.setCreatedEmp(MCUtil.getMemberID(request.getSession()));
                emcoId = emcoDAO.insertEmco(bean);
            } else {
                OnlineUser user = MCUtil.getOnlineUser(session);
                LogUtil.info("Username : " + user.getName() + " - Form : " + mapping.getPath() + " - EDIT");
                emcoDAO.updateEmco(bean);
            }
            session.setAttribute("id", emcoId);
            addDetail(emcoId, formBean);
        } catch (Exception ex) {
            LogUtil.error("FAILED:Emco:add-" + ex.getMessage());
            ex.printStackTrace();
        }
        return true;
    }

    private EmcoDetailBean detExisted(ArrayList arrDet, String equipment) {
        EmcoDetailBean bean = null;
        for (int i = 0; i < arrDet.size(); i++) {
            bean = (EmcoDetailBean) arrDet.get(i);
            if (bean.getEquipment().equals(equipment)) {
                return bean;
            }
        }
        return null;
    }

    private void addDetail(int emcoId, EmcoFormBean formBean) {
        if (emcoId > 0) {
            EmcoDAO emcoDAO = new EmcoDAO();
            ArrayList arrDet = null;
            try {
                arrDet = emcoDAO.getEmcoDetailsByEmco(emcoId);
            } catch (Exception ex) {
                arrDet = new ArrayList();
            }

            int quantity = 1;
            String spec = "";

            if (formBean.getEquipment() != null) {
                String[] equIds = formBean.getEquipment();
                EmcoDetailBean emcoDetailBean = null;
                for (int i = 0; i < equIds.length; i++) {
                    emcoDetailBean = detExisted(arrDet, equIds[i]);
                    if (emcoDetailBean == null) { // insert
                        try {
                            emcoDetailBean = new EmcoDetailBean();
                            emcoDetailBean.setEquipment(equIds[i]);
                            emcoDetailBean.setEmcoId(emcoId);
                            emcoDetailBean.setUnit(formBean.getUnit()[i]);
                            emcoDetailBean.setQuantity(NumberUtil.parseInt(formBean.getQuantity()[i], 0));
                            emcoDetailBean.setSpec(formBean.getSpec()[i]);
                            emcoDetailBean.setEmcDetailId(NumberUtil.parseInt(formBean.getEmcDetailId()[i], 0));
                            emcoDAO.insertEmcoDetail(emcoDetailBean);
                        } catch (Exception ex) {
                        }
                    } else { // update
                        boolean bUpdated = false;
                        quantity = NumberUtil.parseInt(formBean.getQuantity()[i], 0);
                        if (quantity != emcoDetailBean.getQuantity()) {
                            emcoDetailBean.setQuantity(quantity);
                            bUpdated = true;
                        }

                        spec = formBean.getSpec()[i];
                        if (!spec.equals(emcoDetailBean.getSpec())) {
                            emcoDetailBean.setSpec(spec);
                            bUpdated = true;
                        }

                        if (bUpdated) {
                            try {
                                emcoDAO.updateEmcoDetail(emcoDetailBean);
                            } catch (Exception ex) {
                            }
                        }
                    }
                }
            }
        }
    }
}
