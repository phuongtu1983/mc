package com.venus.mc.process.store.equipment.verifiedplan;

/**
 * @author Mai Vinh Loc
 */
import com.venus.core.util.NumberUtil;
import com.venus.core.util.StringUtil;
import com.venus.mc.bean.OrganizationBean;
import com.venus.mc.bean.VerifiedPlanBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.OrganizationDAO;
import com.venus.mc.dao.VerifiedPlanDAO;
import com.venus.mc.util.Constants;
import com.venus.mc.util.MCUtil;
import com.venus.mc.util.PermissionUtil;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.LabelValueBean;

public class VerifiedPlanFormAction extends SpineAction {

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

        VerifiedPlanBean bean = new VerifiedPlanBean();
        int vpId = NumberUtil.parseInt(request.getParameter("vpId"), 0);
        int equId = NumberUtil.parseInt(request.getParameter("equId"), 0);
        if (vpId > 0) {
            VerifiedPlanDAO materialDAO = new VerifiedPlanDAO();
            try {
                bean = materialDAO.getVerifiedPlan(vpId, equId);
            } catch (Exception ex) {
            }
        }
        request.setAttribute(Constants.VERIFIEDPLAN, bean);

        //STATUS
        ArrayList arrStatus = new ArrayList();
        LabelValueBean value;
        value = new LabelValueBean();
        value.setLabel(MCUtil.getBundleString("message.status.select"));
        value.setValue("0");
        arrStatus.add(value);
        value = new LabelValueBean();
        value.setLabel(MCUtil.getBundleString("message.verifiedplan.status1"));
        value.setValue(VerifiedPlanBean.S1 + "");
        arrStatus.add(value);
        value = new LabelValueBean();
        value.setLabel(MCUtil.getBundleString("message.verifiedplan.status2"));
        value.setValue(VerifiedPlanBean.S2 + "");
        arrStatus.add(value);
        value = new LabelValueBean();
        value.setLabel(MCUtil.getBundleString("message.verifiedplan.status3"));
        value.setValue(VerifiedPlanBean.S3 + "");
        arrStatus.add(value);
        request.setAttribute(Constants.VERIFIED_STATUS_LIST, arrStatus);

        //PERFORM_KIND
        ArrayList arrPK = new ArrayList();
        //LabelValueBean value;
        value = new LabelValueBean();
        value.setLabel(MCUtil.getBundleString("message.kind.select"));
        value.setValue("0");
        arrPK.add(value);
        value = new LabelValueBean();
        value.setLabel(MCUtil.getBundleString("message.verifiedplan.pk1"));
        value.setValue(VerifiedPlanBean.S1 + "");
        arrPK.add(value);
        value = new LabelValueBean();
        value.setLabel(MCUtil.getBundleString("message.verifiedplan.pk2"));
        value.setValue(VerifiedPlanBean.S2 + "");
        arrPK.add(value);
        request.setAttribute(Constants.VERIFIED_PERFORM_KIND_LIST, arrPK);

        //ORG
        ArrayList orgList = null;
        try {
            OrganizationDAO orgDAO = new OrganizationDAO();
            orgList = orgDAO.getOrgByKind(0);
        } catch (Exception ex) {
        }
        ArrayList arrOrg = new ArrayList();
        //LabelValueBean value;
        value = new LabelValueBean();
        value.setLabel(MCUtil.getBundleString("message.organization.select"));
        value.setValue("0");
        arrOrg.add(value);
        for (int i = 0; i < orgList.size(); i++) {
            OrganizationBean org = (OrganizationBean) orgList.get(i);
            value = new LabelValueBean();
            value.setLabel(String.valueOf(StringUtil.decodeString(org.getName())));
            value.setValue(String.valueOf(org.getOrgId()));
            arrOrg.add(value);
        }
        request.setAttribute(Constants.VERIFIED_ORG_LIST, arrOrg);

        return true;
    }

    @Override
    protected String getFunction() {
        return PermissionUtil.FUNC_VIEW + ";" + PermissionUtil.FUNC_EDIT;
    }

    @Override
    protected int getPermit() {
        return PermissionUtil.PER_EQUIPMENT;
    }
}
