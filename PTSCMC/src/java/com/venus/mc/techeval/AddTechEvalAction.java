/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.techeval;

import com.venus.core.auth.OnlineUser;
import com.venus.core.util.LogUtil;
import com.venus.mc.bean.TechEvalBean;
import com.venus.mc.bean.TechEvalGroupBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.TechEvalDAO;
import com.venus.mc.dao.TechEvalVendorDAO;
import com.venus.mc.util.MCUtil;
import com.venus.mc.util.PermissionUtil;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author mai vinh loc
 */
public class AddTechEvalAction extends SpineAction {

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
        TechEvalFormBean formBean = (TechEvalFormBean) form;
        int tenId = formBean.getTenId();
        boolean bNew = false;

        TechEvalBean bean = new TechEvalBean();

        bean.setTeId(formBean.getTeId());
        bean.setTenId(formBean.getTenId());
        bean.setCreatedDate(formBean.getCreatedDate());

        TechEvalDAO techDAO = new TechEvalDAO();
        TechEvalVendorDAO techVendorDAO = new TechEvalVendorDAO();

        TechEvalBean bean1 = null;
        int forms = 0;
        try {
            bean1 = techDAO.getTechEval(tenId);
            if (bean1 == null) {
                bNew = true;
            } else {
                bNew = false;
            }

            if (bNew) {
                bean.setTeId(techDAO.insertTechEval(bean));
            } else {
                HttpSession session = request.getSession();
                OnlineUser user = MCUtil.getOnlineUser(session);
                LogUtil.info("Username : " + user.getName() + " - Form : " + mapping.getPath() + " - EDIT");
                techDAO.updateTechEval(bean);
            }
            forms = techDAO.getForm(tenId);
            techVendorDAO.insertTechEvalVendorForTechEval(bean.getTeId(), formBean.getTenId(), forms);
            addEvalGroup(bean.getTeId(), formBean);
        } catch (Exception ex) {
            LogUtil.error("FAILED:Vender:add-" + ex.getMessage());
            ex.printStackTrace();
        }
        return true;
    }

    private TechEvalGroupBean evalGroupExisted(ArrayList arrEvalGroup, int tegId) {
        TechEvalGroupBean bean = null;
        for (int i = 0; i < arrEvalGroup.size(); i++) {
            bean = (TechEvalGroupBean) arrEvalGroup.get(i);
            if (bean.getTegId() == tegId) {
                return bean;
            }
        }
        return null;
    }

    private void addEvalGroup(int teId, TechEvalFormBean formBean) {
        TechEvalDAO techDAO = new TechEvalDAO();
        ArrayList arrDet = null;
        if (teId != 0) {
            try {
                arrDet = techDAO.getTechEvalGroup(formBean.getTenId());
            } catch (Exception ex) {
                arrDet = new ArrayList();
            }
        }
        if (formBean.getEvalId() != null) {
            String[] detIds = formBean.getEvalId();
            TechEvalGroupBean detBean = null;
            boolean canUpdate = false;
            for (int i = 0; i < detIds.length; i++) {
                if (!detIds[i].equals("0")) {//old
                    try {
                        detBean = evalGroupExisted(arrDet, Integer.parseInt(detIds[i]));
                        if (detBean != null) {
                            if (!detBean.getEmployee().equals(formBean.getEvalEmployee()[i])) {
                                detBean.setEmployee(formBean.getEvalEmployee()[i]);
                                canUpdate = true;
                            }
                            if (!detBean.getPosition().equals(formBean.getEvalPosition()[i])) {
                                detBean.setPosition(formBean.getEvalPosition()[i]);
                                canUpdate = true;
                            }
                            if (!detBean.getNote().equals(formBean.getEvalNote()[i])) {
                                detBean.setNote(formBean.getEvalNote()[i]);
                                canUpdate = true;
                            }
                            if (canUpdate) {
                                techDAO.updateTechEvalGroup(detBean);
                            }
                        }
                    } catch (Exception ex) {
                    }
                } else {//new
                    try {
                        detBean = new TechEvalGroupBean(0, formBean.getEvalEmployee()[i], formBean.getEvalPosition()[i], formBean.getEvalNote()[i]);
                        detBean.setTeId(teId);
                        techDAO.insertTechEvalGroup(detBean);
                    } catch (Exception ex) {
                    }
                }
            }
        }
    }

    @Override
    protected String getFunction() {
        return PermissionUtil.FUNC_EDIT + "";
    }

    @Override
    protected int getPermit() {
        return PermissionUtil.PER_PURCHASING_TENDERPLAN;
    }
}
