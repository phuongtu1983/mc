
package com.venus.mc.process.purchasing.edeliverynotice;

import com.venus.core.util.LogUtil;
import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.SearchFormBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.EdnDAO;
import com.venus.mc.util.Constants;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.venus.core.util.StringUtil;
import java.util.ArrayList;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author mai vinh loc
 * @version
 */
public class SearchSimpleEdnAction extends SpineAction {

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
        SearchFormBean formBean = (SearchFormBean) form;
        int fieldid = formBean.getSearchid();
  //      int kind = NumberUtil.parseInt(request.getParameter("kind"),0);
        String strFieldvalue = formBean.getSearchvalue();
        EdnDAO dnDAO = new EdnDAO();
        ArrayList dnList = null;
        try {
            dnList = dnDAO.searchSimpleDn(fieldid, StringUtil.encodeHTML(strFieldvalue));
        } catch (Exception ex) {
            LogUtil.error("FAILED:Dn:searchSimple-" + ex.getMessage());
            ex.printStackTrace();
        }
        request.setAttribute(Constants.DN_LIST, dnList);
        return true;
    }
}
