package com.venus.mc.process.store.equipment.verifiedplan;

/**
 * @author Mai Vinh Loc
 */
import com.venus.core.util.LogUtil;
import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.SearchFormBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.VerifiedPlanDAO;
import com.venus.mc.util.Constants;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import com.venus.core.util.StringUtil;
public class SearchSimpleVerifiedPlanAction extends SpineAction {

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
        SearchFormBean verifiedplanForm = (SearchFormBean) form;
        int equId = NumberUtil.parseInt(request.getParameter("equId"), 0);
        int fieldid = verifiedplanForm.getSearchid();
        String strFieldvalue = verifiedplanForm.getSearchvalue();
        ArrayList verifiedplanList = null;
        VerifiedPlanDAO verifiedplanDAO = new VerifiedPlanDAO();
        try {
            verifiedplanList = verifiedplanDAO.searchSimpleVerifiedPlan(fieldid, StringUtil.encodeHTML(strFieldvalue), equId);
        } catch (Exception ex) {
            LogUtil.error("FAILED: VerifiedPlanBean:searchSimpleVerifiedPlan-" + ex.getMessage());
            ex.printStackTrace();
        }
        request.setAttribute(Constants.VERIFIEDPLAN_LIST, verifiedplanList);
        return true;
    }
}
