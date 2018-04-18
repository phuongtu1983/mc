package com.venus.mc.process.equipment.reportdamage;

/**
 * @author Mai Vinh Loc
 */
import com.venus.core.auth.OnlineUser;
import com.venus.core.util.LogUtil;
import com.venus.mc.bean.ReportDamageBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.ReportDamageDAO;
import com.venus.mc.util.MCUtil;
import com.venus.mc.util.PermissionUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

public class AddReportDamageAction extends SpineAction {

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
        ReportDamageFormBean formBean = (ReportDamageFormBean) form;
        int rdId = formBean.getRdId();
        boolean bNew = false;
        if (rdId == 0) {
            bNew = true;
        } else {
            bNew = false;
        }

        ReportDamageBean bean = new ReportDamageBean();
        bean.setRdId(formBean.getRdId());
        bean.setRdNumber(formBean.getRdNumber());
        bean.setCreatedDate(formBean.getCreatedDate());
        bean.setEquId(formBean.getEquId());
        bean.setCreatedTime(formBean.getCreatedTime());
        bean.setCreatedLocation(formBean.getCreatedLocation());
        bean.setManagerEmp(formBean.getManagerEmp());
        bean.setUsedEmp(formBean.getUsedEmp());
        bean.setManagerEquipmentEmp(formBean.getManagerEquipmentEmp());
        bean.setStatus(formBean.getStatus());
        bean.setComment(formBean.getComment());

        ReportDamageDAO reportDamageDAO = new ReportDamageDAO();

        try {
            boolean isExist = false;
            isExist = reportDamageDAO.checkRdNumber(formBean.getRdId(), formBean.getRdNumber());
            if (isExist) {
                ActionMessages errors = new ActionMessages();
                errors.add("existedRdNumber", new ActionMessage("errors.reportdamage.existedRdNumber"));
                saveErrors(request, errors);
                return false;
            }
            isExist = false;

            if (bNew) {
                rdId = reportDamageDAO.insertReportDamage(bean);
            } else {
                OnlineUser user = MCUtil.getOnlineUser(session);
                LogUtil.info("Username : " + user.getName() + " - Form : " + mapping.getPath() + " - EDIT");
                reportDamageDAO.updateReportDamage(bean);
            }
            session.setAttribute("id", rdId);
        } catch (Exception ex) {
            LogUtil.error("FAILED:Vender:add-" + ex.getMessage());
            ex.printStackTrace();
        }
        return true;
    }

    @Override
    protected String getFunction() {
        return PermissionUtil.FUNC_EDIT + "";
    }

    @Override
    protected int getPermit() {
        return PermissionUtil.PER_EQUIPMENT_FACTREPORT;
    }
}
