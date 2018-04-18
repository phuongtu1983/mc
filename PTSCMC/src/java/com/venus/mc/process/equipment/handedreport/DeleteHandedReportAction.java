package com.venus.mc.process.equipment.handedreport;

/**
 * @author thuhc
 */
import com.venus.core.auth.OnlineUser;
import com.venus.core.util.LogUtil;
import com.venus.core.util.NumberUtil;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.HandedReportDAO;
import com.venus.mc.upload.UploadFileUtil;
import com.venus.mc.util.MCUtil;
import com.venus.mc.util.PermissionUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class DeleteHandedReportAction extends SpineAction {

    private String result = "";

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
        String[] arrHrId = request.getParameterValues("hrId");
        OnlineUser user = MCUtil.getOnlineUser(session);
        LogUtil.info("Username : " + user.getName() + " - Form : " + mapping.getPath() + " - DELETE");
        int length = 0;
        HandedReportDAO hrDAO = new HandedReportDAO();
        if (arrHrId != null) {
            length = arrHrId.length;
        }
        for (int i = 0; i < length; i++) {
            int hrId = NumberUtil.parseInt(arrHrId[i], 0);
            if (hrId > 0) {
                try {
                    //delete files
                    UploadFileUtil.deleteFiles(UploadFileUtil.ATTACH_FILE_REQUIRETRANSFER, hrId);
                } catch (Exception ex) {
                    LogUtil.error(getClass(),ex.getMessage());
                }
                try {
                    //delete detail
                    hrDAO.deleteHandedReportDetails(hrId);
                    //delete main
                    hrDAO.deleteHandedReport(hrId);
                } catch (Exception ex) {
                    LogUtil.error(getClass(),ex.getMessage());
                }

            }

        }

        return true;
    }

    @Override
    protected String getErrorsString(
            HttpServletRequest request) {
        return result;
    }

    @Override
    protected String getFunction() {
        return PermissionUtil.FUNC_DELETE + "";
    }

    @Override
    protected int getPermit() {
        return PermissionUtil.PER_EQUIPMENT_REPAIRREQUEST;
    }
}
