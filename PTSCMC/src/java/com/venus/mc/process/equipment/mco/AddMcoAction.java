/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.process.equipment.mco;

import com.venus.core.auth.OnlineUser;
import com.venus.core.util.LogUtil;
import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.McoBean;
import com.venus.mc.bean.McoDetailBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.McoDAO;
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
public class AddMcoAction extends SpineAction {

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
        McoFormBean formBean = (McoFormBean) form;
        McoDAO mcoDAO = new McoDAO();
        McoBean bean = null;

        boolean bNew = false;
        boolean isExist = false;

        try {
            bean = mcoDAO.getMcoByNumber(formBean.getMcoNumber());
        } catch (Exception ex) {
        }

        int mcoId = formBean.getMcoId();
        if (mcoId == 0) {
            bNew = true;
            if (bean != null) {
                isExist = true;
            }
        } else {
            bNew = false;
            if (bean != null && bean.getMcoId() != mcoId) {
                isExist = true;
            }
        }

        if (isExist) {
            ActionMessages errors = new ActionMessages();
            errors.add("mcoExisted", new ActionMessage("errors.mco.existed"));
            saveErrors(request, errors);
            return false;
        }

        bean = new McoBean();
        bean.setMcoId(formBean.getMcoId());
        bean.setMcoNumber(formBean.getMcoNumber());
        bean.setOrgId(formBean.getOrgId());
        bean.setKind(formBean.getKind());
        bean.setCarryOnDatePlan(formBean.getCarryOnDatePlan());
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
                mcoId = mcoDAO.insertMco(bean);
            } else {
                OnlineUser user = MCUtil.getOnlineUser(session);
                LogUtil.info("Username : " + user.getName() + " - Form : " + mapping.getPath() + " - EDIT");
                mcoDAO.updateMco(bean);
            }
            session.setAttribute("id", mcoId);
            addDetail(mcoId, formBean);
        } catch (Exception ex) {
            LogUtil.error("FAILED:Mco:add-" + ex.getMessage());
            ex.printStackTrace();
        }
        return true;
    }

    private McoDetailBean detExisted(ArrayList arrDet, int equId) {
        McoDetailBean bean = null;
        for (int i = 0; i < arrDet.size(); i++) {
            bean = (McoDetailBean) arrDet.get(i);
            if (bean.getEquId() == equId) {
                return bean;
            }
        }
        return null;
    }

    private void addDetail(int mcoId, McoFormBean formBean) {
        if (mcoId > 0) {
            McoDAO mcoDAO = new McoDAO();
            ArrayList arrDet = null;
            try {
                arrDet = mcoDAO.getMcoDetailsByMco(mcoId);
            } catch (Exception ex) {
                arrDet = new ArrayList();
            }

            double quantity = 0;
            String spec = "";

            if (formBean.getEquipment() != null) {
                String[] equIds = formBean.getEquipment();
                McoDetailBean mcoDetailBean = null;
                int currEquId = 0;
                for (int i = 0; i < equIds.length; i++) {
                    currEquId = NumberUtil.parseInt(equIds[i], 0);
                    mcoDetailBean = detExisted(arrDet, currEquId);
                    if (mcoDetailBean == null) { // insert
                        try {
                            mcoDetailBean = new McoDetailBean();
                            mcoDetailBean.setEquId(currEquId);
                            mcoDetailBean.setMcoId(mcoId);
                            mcoDetailBean.setUnit(formBean.getUnit()[i]);
                            mcoDetailBean.setQuantity(NumberUtil.parseDouble(formBean.getQuantity()[i], 0));
                            mcoDetailBean.setSpec(formBean.getSpec()[i]);
                            mcoDAO.insertMcoDetail(mcoDetailBean);
                        } catch (Exception ex) {
                        }
                    } else { // update
                        boolean bUpdated = false;
//                        quantity = NumberUtil.parseInt(formBean.getQuantity()[i], 0);
//                        if (quantity != mcoDetailBean.getQuantity()) {
//                            mcoDetailBean.setQuantity(quantity);
//                            bUpdated = true;
//                        }

                        spec = formBean.getSpec()[i];
                        if (!spec.equals(mcoDetailBean.getSpec())) {
                            mcoDetailBean.setSpec(spec);
                            bUpdated = true;
                        }

                        if (bUpdated) {
                            try {
                                mcoDAO.updateMcoDetail(mcoDetailBean);
                            } catch (Exception ex) {
                            }
                        }
                    }
                }
            }
        }
    }
}
