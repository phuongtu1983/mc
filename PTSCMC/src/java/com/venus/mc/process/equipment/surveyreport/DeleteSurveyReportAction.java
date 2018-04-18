package com.venus.mc.process.equipment.surveyreport;

/**
 * @author Mai Vinh Loc
 */
import com.venus.core.auth.OnlineUser;
import com.venus.core.util.LogUtil;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.SurveyReportDAO;
import com.venus.mc.util.MCUtil;
import com.venus.mc.util.PermissionUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

public class DeleteSurveyReportAction extends SpineAction {

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
        String[] arrSrId = request.getParameterValues("srId");
        try {
            OnlineUser user = MCUtil.getOnlineUser(session);
            LogUtil.info("Username : " + user.getName() + " - Form : " + mapping.getPath() + " - DELETE");
            int length = 0;
            boolean isExist = false;
            boolean isExist1 = false;
            SurveyReportDAO surveyReportDAO = new SurveyReportDAO();
            if (arrSrId != null) {
                length = arrSrId.length;
            }
            for (int i = 0; i < length; i++) {
                //isExist = surveyReportDAO.checkDeleted(arrRdId[i]);
                if (!isExist) {
                    surveyReportDAO.deleteSurveyReportDetails(arrSrId[i]);
                    surveyReportDAO.deleteSurveyReport(arrSrId[i]);
                } else {
                    isExist1 = true;
                }
            }
            if (isExist1) {
                ActionMessages errors = new ActionMessages();
                errors.add("deleteRrNumber", new ActionMessage("errors.surveyreport.deleteRrNumber"));
                saveErrors(request, errors);
                return false;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return true;
    }

    @Override
    protected String getErrorsString(HttpServletRequest request) {
        return result;
    }

    @Override
    protected String getFunction() {
        return PermissionUtil.FUNC_DELETE + "";
    }

    @Override
    protected int getPermit() {
        return PermissionUtil.PER_EQUIPMENT_SURVEYREPORT;
    }
}
