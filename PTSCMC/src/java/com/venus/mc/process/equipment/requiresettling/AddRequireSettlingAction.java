package com.venus.mc.process.equipment.requiresettling;

/**
 * @author Mai Vinh Loc
 */
import com.venus.core.auth.OnlineUser;
import com.venus.core.util.LogUtil;
import com.venus.mc.bean.RequireSettlingBean;
import com.venus.mc.bean.RequireSettlingDetailBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.RequireSettlingDAO;
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

public class AddRequireSettlingAction extends SpineAction {

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
        RequireSettlingFormBean formBean = (RequireSettlingFormBean) form;
        int rsId = formBean.getRsId();
        boolean bNew = false;
        if (rsId == 0) {
            bNew = true;
        } else {
            bNew = false;
        }

        RequireSettlingBean bean = new RequireSettlingBean();
        bean.setRsId(formBean.getRsId());
        bean.setRsNumber(formBean.getRsNumber());
        bean.setCreatedDate(formBean.getCreatedDate());
        bean.setRequireDate(formBean.getRequireDate());
        bean.setCreatedEmp(formBean.getCreatedEmp());
        bean.setSrId(formBean.getSrId());
        bean.setProId(formBean.getProId());
        bean.setRequireOrg(formBean.getRequireOrg());
        bean.setPerformOrg(formBean.getPerformOrg());
        bean.setStatus(formBean.getStatus());

        RequireSettlingDAO requireSettlingDAO = new RequireSettlingDAO();

        try {
            boolean isExist = false;
            isExist = requireSettlingDAO.checkRsNumber(formBean.getRsId(), formBean.getRsNumber());
            if (isExist) {
                ActionMessages errors = new ActionMessages();
                errors.add("existedRsNumber", new ActionMessage("errors.requiresettling.existedRsNumber"));
                saveErrors(request, errors);
                return false;
            }
            isExist = false;

            if (bNew) {
                bean.setCreatedEmp(MCUtil.getMemberID(request.getSession()));
                rsId = requireSettlingDAO.insertRequireSettling(bean);
            } else {
                OnlineUser user = MCUtil.getOnlineUser(session);
                LogUtil.info("Username : " + user.getName() + " - Form : " + mapping.getPath() + " - EDIT");
                requireSettlingDAO.updateRequireSettling(bean);
            }
            session.setAttribute("id", rsId);
            if (rsId != 0) {
                ArrayList arrDet = null;
                try {
                    arrDet = requireSettlingDAO.getRequireSettlingDetails(rsId);
                } catch (Exception ex) {
                    arrDet = new ArrayList();
                }

                if (formBean.getReqDetId() != null) {//old
                    RequireSettlingDetailBean detBean = new RequireSettlingDetailBean();
                    String[] detIds = formBean.getReqDetId();

                    for (int i = 0; i < detIds.length; i++) {
                        detBean.setRsId(rsId);
                        detBean.setWorkPlan(formBean.getWorkPlan()[i]);
                        detBean.setContentWork(formBean.getContentWork()[i]);
                        detBean.setLocation(formBean.getLocation()[i]);
                        detBean.setQuantity(formBean.getQuantity()[i]);
                        detBean.setStartTimePlan(formBean.getStartTimePlan()[i]);
                        detBean.setEndTimePlan(formBean.getEndTimePlan()[i]);
                        detBean.setStartTimeReality(formBean.getStartTimeReality()[i]);
                        detBean.setEndTimeReality(formBean.getEndTimeReality()[i]);
                        detBean.setExplanation(formBean.getExplanation()[i]);
                        detBean.setComment(formBean.getComment()[i]);
                        if (!detIds[i].equals("0")) {//old
                            try {
                                detBean = detExisted(arrDet, Integer.parseInt(detIds[i]));
                                if (detBean != null) {
                                    requireSettlingDAO.updateRequireSettlingDetail(detBean);
                                }
                            } catch (Exception ex) {
                            }
                        } else {//new
                            try {
                                requireSettlingDAO.insertRequireSettlingDetail(detBean);
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

    private RequireSettlingDetailBean detExisted(ArrayList arrDet, int detId) {
        RequireSettlingDetailBean formBean = null;
        for (int i = 0; i < arrDet.size(); i++) {
            formBean = (RequireSettlingDetailBean) arrDet.get(i);
            if (formBean.getDetId() == detId) {
                arrDet.remove(formBean);
                RequireSettlingDetailBean bean = new RequireSettlingDetailBean(detId);
                bean.setRsId(formBean.getRsId());
                bean.setWorkPlan(formBean.getWorkPlan());
                bean.setContentWork(formBean.getContentWork());
                bean.setLocation(formBean.getLocation());
                bean.setQuantity(formBean.getQuantity());
                bean.setStartTimePlan(formBean.getStartTimePlan());
                bean.setEndTimePlan(formBean.getEndTimePlan());
                bean.setStartTimeReality(formBean.getStartTimeReality());
                bean.setEndTimeReality(formBean.getEndTimeReality());
                bean.setExplanation(formBean.getExplanation());
                bean.setComment(formBean.getComment());
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
