package com.venus.mc.process.store.asset;

/**
 * @author Mai Vinh Loc
 */
import com.venus.mc.bean.AssetBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.AssetDAO;
import com.venus.mc.dao.UnitDAO;
import com.venus.mc.bean.UnitBean;
import com.venus.mc.dao.OriginDAO;
import com.venus.mc.bean.OriginBean;
import com.venus.mc.util.Constants;
import com.venus.mc.util.MCUtil;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.LabelValueBean;

public class AssetFormAction extends SpineAction {

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

        AssetBean bean = new AssetBean();
        String assId = request.getParameter("assId");
        if (!GenericValidator.isBlankOrNull(assId)) {
            AssetDAO materialDAO = new AssetDAO();
            try {
                bean = materialDAO.getAsset(assId);
            } catch (Exception ex) {
            }
        }
        request.setAttribute(Constants.ASSET, bean);

        //STATUS
        ArrayList arrStatus = new ArrayList();
        LabelValueBean value;
        value = new LabelValueBean();
        value.setLabel(MCUtil.getBundleString("message.asset.status1"));
        value.setValue(bean.S1 + "");
        arrStatus.add(value);
        value = new LabelValueBean();
        value.setLabel(MCUtil.getBundleString("message.asset.status2"));
        value.setValue(bean.S2 + "");
        arrStatus.add(value);
        value = new LabelValueBean();
        value.setLabel(MCUtil.getBundleString("message.asset.status3"));
        value.setValue(bean.S3 + "");
        arrStatus.add(value);
        value = new LabelValueBean();
        value.setLabel(MCUtil.getBundleString("message.asset.status4"));
        value.setValue(bean.S4 + "");
        arrStatus.add(value);
        request.setAttribute(Constants.STATUS_LIST, arrStatus);

        return true;
    }
}
