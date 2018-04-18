package com.venus.mc.process.store.equipment.repairplan;

/**
 * @author Mai Vinh Loc
 */
import com.venus.core.auth.OnlineUser;
import com.venus.core.util.GenericValidator;
import com.venus.core.util.LogUtil;
import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.RepairMaterialBean;
import com.venus.mc.bean.RepairPlanBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.RepairPlanDAO;
import com.venus.mc.util.MCUtil;
import com.venus.mc.util.PermissionUtil;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class AddRepairPlanAction extends SpineAction {

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
        RepairPlanFormBean formBean = (RepairPlanFormBean) form;
        int equId = NumberUtil.parseInt(request.getParameter("equId"), 0);
        int rpId = formBean.getRpId();
        boolean bNew = false;
        if (rpId == 0) {
            bNew = true;
        } else {
            bNew = false;
        }

        RepairPlanBean bean = new RepairPlanBean();
        bean.setRpId(formBean.getRpId());
        bean.setEquId(equId);
        bean.setAssId(formBean.getAssId());
        bean.setOrgId(formBean.getOrgId());
        bean.setRepairPart(formBean.getRepairPart());
        bean.setCost(formBean.getCost());
        bean.setRepairKind(formBean.getRepairKind());
        bean.setStatus(formBean.getStatus());
        bean.setComment(formBean.getComment());
        bean.setPerformKind(formBean.getPerformKind());
        bean.setPerformPart(formBean.getPerformPart());
        bean.setTimeTrue(formBean.getTimeTrue());
        bean.setTimeRepair(formBean.getTimeRepair());
        bean.setTimeUnit(formBean.getTimeUnit());

        RepairPlanDAO repairPlanDAO = new RepairPlanDAO();

        try {
            boolean isExist = false;
            /*         isExist = repairplanDAO.checkDecisionNumber(formBean.getEquId(), formBean.getDecisionNumber());
            if (isExist) {
            ActionMessages errors = new ActionMessages();
            errors.add("decisionNumberExisted", new ActionMessage("errors.repairplan.existedDecisionNumber"));
            saveErrors(request, errors);
            return false;
            }
             */

            if (bNew) {
                rpId = repairPlanDAO.insertRepairPlan(bean);
            } else {
                HttpSession session = request.getSession();
                OnlineUser user = MCUtil.getOnlineUser(session);
                LogUtil.info("Username : " + user.getName() + " - Form : " + mapping.getPath() + " - EDIT");
                repairPlanDAO.updateRepairPlan(bean);
            }
            if (rpId != 0) {
                ArrayList arrDet = null;
                try {
                    arrDet = repairPlanDAO.getRepairMaterials(rpId);
                } catch (Exception ex) {
                    arrDet = new ArrayList();
                }

                if (formBean.getReqDetId() != null) {//old
                    String[] detIds = formBean.getReqDetId();
                    String[] matIds = formBean.getMatId();
                    String[] units = formBean.getUnit();
                    RepairMaterialBean detBean = new RepairMaterialBean();
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
                                        detBean.setRpId(rpId);
                                        detBean.setMatId(Integer.parseInt(formBean.getMatId()[i]));
                                        detBean.setUnit(formBean.getUnit()[i]);
                                        canUpdate = true;
                                    }

                                    if (canUpdate) {
                                        repairPlanDAO.updateRepairMaterial(detBean);
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

                                detBean.setRpId(rpId);
                                detBean.setMatId(Integer.parseInt(matIds[i]));
                                detBean.setUnit(units[i]);
                                detBean.setQuantity(quantity);
                                repairPlanDAO.insertRepairMaterial(detBean);
                            } catch (Exception ex) {
                            }
                        }
                    }
                }
            }
        } catch (Exception ex) {
            LogUtil.error("FAILED:Vender:add-" + ex.getMessage());
            ex.printStackTrace();
        }
        return true;
    }

    private RepairMaterialBean detExisted(ArrayList arrDet, int detId) {
        RepairMaterialBean formBean = null;
        for (int i = 0; i < arrDet.size(); i++) {
            formBean = (RepairMaterialBean) arrDet.get(i);
            if (formBean.getDetId() == detId) {
                arrDet.remove(formBean);
                RepairMaterialBean bean = new RepairMaterialBean(detId);
                bean.setQuantity(formBean.getQuantity());
                bean.setMatId(formBean.getMatId());
                bean.setUnit(formBean.getUnit());
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
        return PermissionUtil.PER_EQUIPMENT;
    }
}
