package com.venus.mc.techeval;

/**
 * @author Mai Vinh Loc
 */
import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.TenderPlanBean;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import com.venus.mc.util.Constants;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.TenderEvaluateVendorDAO;
import com.venus.mc.dao.TenderPlanDAO;
import java.util.ArrayList;

public class ListVendorAction extends SpineAction {

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
        TenderEvaluateVendorDAO tevDAO = new TenderEvaluateVendorDAO();
        TenderPlanDAO tenderDAO = new TenderPlanDAO();
        String tenId = request.getParameter("tenId");
        ArrayList tevList = null;
        try {
            TenderPlanBean tenderPlanBean = tenderDAO.checkForm(NumberUtil.parseInt(tenId, 0));
            if (NumberUtil.parseInt(tenderPlanBean.getForm(), 0) == 2) {
                //request.setAttribute(Constants.CAN_SAVE, "true");
                tevList = tevDAO.getTenderEvaluateVendorsForTechEval2(tenId);
            } else {
                tevList = tevDAO.getTenderEvaluateVendorsForTechEval(tenId);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        request.setAttribute(Constants.TEV_LIST, tevList);

        return true;
    }
}
