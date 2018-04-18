package com.venus.mc.contractFollow;

/**
 * @author Mai Vinh Loc
 */
import com.venus.core.auth.OnlineUser;
import com.venus.core.util.LogUtil;
import com.venus.mc.bean.ContractFollowBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.ContractFollowDAO;
import com.venus.mc.util.MCUtil;
import com.venus.mc.util.PermissionUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

public class AddContractFollowAction extends SpineAction {

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
        ContractFollowFormBean formBean = (ContractFollowFormBean) form;
        int folId = formBean.getFolId();
        boolean bNew = false;
        if (folId == 0) {
            bNew = true;
        } else {
            bNew = false;
        }

        ContractFollowBean bean = new ContractFollowBean();
        bean.setFolId(formBean.getFolId());
        bean.setConId(formBean.getConId());
        bean.setProId(formBean.getProId());
        bean.setOrgId(formBean.getOrgId());
        bean.setFolNumber(formBean.getFolNumber());
        bean.setCreatedDate(formBean.getCreatedDate());
        bean.setServiceType(formBean.getServiceType());
        bean.setCreatedTime(formBean.getCreatedTime());
        bean.setStartTime(formBean.getStartTime());
        bean.setEndTime(formBean.getEndTime());
        bean.setServiceAbility(formBean.getServiceAbility());
        bean.setServiceLevel(formBean.getServiceLevel());
        bean.setServiceEquipment(formBean.getServiceEquipment());
        bean.setServiceProgress(formBean.getServiceProgress());
        bean.setServiceSafety(formBean.getServiceSafety());
        bean.setServiceOther(formBean.getServiceOther());
        bean.setServiceCooperate(formBean.getServiceCooperate());
        bean.setGoodAbility(formBean.getGoodAbility());
        bean.setGoodProgress(formBean.getGoodProgress());
        bean.setGoodCertificate(formBean.getGoodCertificate());
        bean.setGoodQuality(formBean.getGoodQuality());
        bean.setGoodOther(formBean.getGoodOther());
        bean.setGoodCooperate(formBean.getGoodCooperate());
        bean.setComments(formBean.getComments());

        ContractFollowDAO conDAO = new ContractFollowDAO();

        try {
            boolean isExist = false;
            isExist = conDAO.checkExisted(formBean.getFolId(), formBean.getFolNumber());
            if (isExist) {
                ActionMessages errors = new ActionMessages();
                errors.add("folNumberExisted", new ActionMessage("errors.contractFollow.existedFolNumber"));
                saveErrors(request, errors);
                return false;
            }
            if (bNew) {
                folId = conDAO.insertContractFollow(bean);
            } else {
                OnlineUser user = MCUtil.getOnlineUser(session);
                LogUtil.info("Username : " + user.getName() + " - Form : " + mapping.getPath() + " - EDIT");
                conDAO.updateContractFollow(bean);
            }
            session.setAttribute("id", folId);
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
        return PermissionUtil.PER_PURCHASING_CONTRACT_FOLLOW;
    }
}
