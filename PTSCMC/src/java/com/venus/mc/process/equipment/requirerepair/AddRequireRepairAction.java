/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.process.equipment.requirerepair;

import com.venus.core.auth.OnlineUser;
import com.venus.core.util.GenericValidator;
import com.venus.core.util.LogUtil;
import com.venus.mc.bean.RepairNotPlanBean;
import com.venus.mc.bean.RequireRepairBean;
import com.venus.mc.bean.RequireRepairDetailBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.RequireRepairDAO;
import com.venus.mc.util.MCUtil;
import com.venus.mc.util.PermissionUtil;
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
 * @author mai vinh loc
 */
public class AddRequireRepairAction extends SpineAction {

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
        RequireRepairFormBean formBean = (RequireRepairFormBean) form;
        RequireRepairDAO requireRepairDAO = new RequireRepairDAO();
        int rrId = formBean.getRrId();
        boolean bNew = false;
        if (rrId == 0) {
            bNew = true;
        } else {
            bNew = false;
        }
        RequireRepairBean bean = new RequireRepairBean();
        bean.setRrId(rrId);
        bean.setRrNumber(formBean.getRrNumber());
        bean.setCreatedEmp(formBean.getCreatedEmp());
        bean.setCreatedDate(formBean.getCreatedDate());
        bean.setRequireDate(formBean.getRequireDate());
        bean.setComment(formBean.getComment());
        bean.setRequireOrg(formBean.getRequireOrg());

        try {
            boolean isExist = false;
            isExist = requireRepairDAO.checkRdNumber(formBean.getRrId(), formBean.getRrNumber());
            if (isExist) {
                ActionMessages errors = new ActionMessages();
                errors.add("existedRrNumber", new ActionMessage("errors.requirerepair.existedRrNumber"));
                saveErrors(request, errors);
                return false;
            }
            isExist = false;

            if (bNew) {
                bean.setCreatedEmp(MCUtil.getMemberID(request.getSession()));
                rrId = requireRepairDAO.insertRequireRepair(bean);                
            } else {
                OnlineUser user = MCUtil.getOnlineUser(session);
                LogUtil.info("Username : " + user.getName() + " - Form : " + mapping.getPath() + " - EDIT");
                requireRepairDAO.updateRequireRepair(bean);
            }
            session.setAttribute("id", rrId);
            if (rrId != 0) {
                ArrayList arrDet = null;
                try {
                    arrDet = requireRepairDAO.getRequireRepairDetails(rrId);
                } catch (Exception ex) {
                    arrDet = new ArrayList();
                }

                if (formBean.getReqDetId() != null) {//old
                    String[] detIds = formBean.getReqDetId();
                    String[] equIds = formBean.getEquId();
                    RequireRepairDetailBean detBean = new RequireRepairDetailBean();
                    RepairNotPlanBean rnpBean = new RepairNotPlanBean();
                    double quantity = 0;
                    boolean canUpdate = false;
                    for (int i = 0; i < detIds.length; i++) {
                        canUpdate = false;
                        if (!detIds[i].equals("0")) {//old
                            try {
                                detBean = detExisted(arrDet, Integer.parseInt(detIds[i]));
                                if (detBean != null) {
                                    quantity = Double.parseDouble(formBean.getQuantity()[i]);
                                    if (quantity != detBean.getQuantity()) {
                                        detBean.setQuantity(quantity);
                                        detBean.setRrId(rrId);
                                        detBean.setEquId(Integer.parseInt(formBean.getEquId()[i]));
                                        canUpdate = true;
                                    }

                                    if (canUpdate) {
                                        requireRepairDAO.updateRequireRepairDetail(detBean);
                                    }
                                }
                            } catch (Exception ex) {
                            }
                        } else {//new
                            try {
                                if (GenericValidator.isBlankOrNull(formBean.getQuantity()[i])) {
                                    quantity = 0;
                                } else {
                                    quantity = Double.parseDouble(formBean.getQuantity()[i]);
                                }
                                
                                detBean.setRrId(rrId);
                                detBean.setEquId(Integer.parseInt(equIds[i]));
                                detBean.setQuantity(quantity);
                                rnpBean.setRrId(rrId);
                                rnpBean.setEquId(Integer.parseInt(equIds[i]));
                                rnpBean.setOrgUsed(MCUtil.getOrganizationID(session));
                                requireRepairDAO.insertRequireRepairDetail(detBean);
                                requireRepairDAO.insertRepairNotPlan(rnpBean);
                            } catch (Exception ex) {
                            }
                        }
                    }
                }
            }
        } catch (Exception ex) {
            LogUtil.error("FAILED:RequireRepair:add-" + ex.getMessage());
            ex.printStackTrace();
        }
        return true;
    }

    private RequireRepairDetailBean detExisted(ArrayList arrDet, int detId) {
        RequireRepairDetailBean formBean = null;
        for (int i = 0; i < arrDet.size(); i++) {
            formBean = (RequireRepairDetailBean) arrDet.get(i);
            if (formBean.getDetId() == detId) {
                arrDet.remove(formBean);
                RequireRepairDetailBean bean = new RequireRepairDetailBean(detId);
                bean.setQuantity(formBean.getQuantity());
                bean.setEquId(formBean.getEquId());
                bean.setUsedCode(formBean.getUsedCode());
                return bean;
            }
        }
        return null;
    }

    @Override
    protected String getFunction() {
        return PermissionUtil.FUNC_EDIT + "";
    }

    @Override
    protected int getPermit() {
        return PermissionUtil.PER_EQUIPMENT_REPAIRREQUEST;
    }
}
