/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.comevalmaterial;

import com.venus.core.auth.OnlineUser;
import com.venus.core.util.LogUtil;
import com.venus.mc.bean.ComEvalBean;
import com.venus.mc.bean.ComEvalGroupBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.ComEvalDAO;
import com.venus.mc.dao.ComEvalMaterialDAO;
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
public class AddComEvalMaterialAction extends SpineAction {

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
        ComEvalMaterialFormBean formBean = (ComEvalMaterialFormBean) form;
        int ceId = formBean.getCeId();
        int cemId = formBean.getCemId()[0];
        boolean bNew = false;
        if (ceId == 0) {
            bNew = true;
        } else {
            bNew = false;
        }

        ComEvalBean bean = new ComEvalBean();
        bean.setCeId(formBean.getCeId());
        bean.setTenId(formBean.getTenId());
        bean.setCcId(formBean.getCcId());
        bean.setCreatedDate(formBean.getCreatedDate());
        bean.setKind("1");//Tron goi

        ComEvalDAO techDAO = new ComEvalDAO();
        ComEvalMaterialDAO techMaterialDAO = new ComEvalMaterialDAO();

        try {
            if (bNew) {
                bean.setCeId(techDAO.insertComEval(bean));
            } else {
                HttpSession session = request.getSession();
                OnlineUser user = MCUtil.getOnlineUser(session);
                LogUtil.info("Username : " + user.getName() + " - Form : " + mapping.getPath() + " - EDIT");
                techDAO.updateComEval(bean);
            }

            addEvalGroup(bean.getCeId(), formBean);
            for (int i = 0; i < formBean.getCemId().length; i++) {
                if (formBean.getCemId()[i] == 0) {
                    techMaterialDAO.insertComEvalMaterialForComEvalMaterial(bean.getCeId(), formBean.getTenId());
                }
            }
        } catch (Exception ex) {
            LogUtil.error("FAILED:Vender:add-" + ex.getMessage());
            ex.printStackTrace();
        }
        return true;
    }

    private ComEvalGroupBean evalGroupExisted(ArrayList arrEvalGroup, int cegId) {
        ComEvalGroupBean bean = null;
        for (int i = 0; i < arrEvalGroup.size(); i++) {
            bean = (ComEvalGroupBean) arrEvalGroup.get(i);
            if (bean.getCegId() == cegId) {
                return bean;
            }
        }
        return null;
    }

    private void addEvalGroup(int ceId, ComEvalMaterialFormBean formBean) {
        ComEvalDAO techDAO = new ComEvalDAO();
        ArrayList arrDet = null;
        if (ceId != 0) {
            try {
                arrDet = techDAO.getComEvalGroup(formBean.getTenId());
            } catch (Exception ex) {
                arrDet = new ArrayList();
            }
        }
        if (formBean.getEvalId() != null) {
            String[] detIds = formBean.getEvalId();
            ComEvalGroupBean detBean = null;
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
                                techDAO.updateComEvalGroup(detBean);
                            }
                        }
                    } catch (Exception ex) {
                    }
                } else {//new
                    try {
                        detBean = new ComEvalGroupBean(0, formBean.getEvalEmployee()[i], formBean.getEvalPosition()[i], formBean.getEvalNote()[i]);
                        detBean.setCeId(ceId);
                        techDAO.insertComEvalGroup(detBean);
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
