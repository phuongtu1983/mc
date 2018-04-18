package com.venus.mc.process.store.asset;

/**
 * @author Mai Vinh Loc
 */
import com.venus.core.auth.OnlineUser;
import com.venus.core.util.LogUtil;
import com.venus.mc.bean.AssetBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.AssetDAO;
import com.venus.mc.util.MCUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

public class AddAssetAction extends SpineAction {

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
        AssetFormBean formBean = (AssetFormBean) form;
        int assId = formBean.getAssId();
        boolean bNew = false;
        if (assId == 0) {
            bNew = true;
        } else {
            bNew = false;
        }

        AssetBean bean = new AssetBean();
        bean.setAssId(formBean.getAssId());
        bean.setAssetName(formBean.getAssetName());
        bean.setDecisionNumber(formBean.getDecisionNumber());
        bean.setManageCode(formBean.getManageCode());
        bean.setAssetName(formBean.getAssetName());
        bean.setRequestNumber(formBean.getRequestNumber());
        bean.setContractNumber(formBean.getContractNumber());
        bean.setTestNumber(formBean.getTestNumber());
        bean.setUnit(formBean.getUnit());
        bean.setUsedCode(formBean.getUsedCode());
        bean.setColorCode(formBean.getColorCode());
        bean.setSpecCerts(formBean.getSpecCerts());
        bean.setFuelLevel(formBean.getFuelLevel());
        bean.setStatus(formBean.getStatus());
        bean.setAppearedDate(formBean.getAppearedDate());
        bean.setUsedDate(formBean.getUsedDate());
        bean.setComment(formBean.getComment());

        AssetDAO assetDAO = new AssetDAO();

        try {
            boolean isExist = false;
            /*         isExist = assetDAO.checkDecisionNumber(formBean.getAssId(), formBean.getDecisionNumber());
            if (isExist) {
            ActionMessages errors = new ActionMessages();
            errors.add("decisionNumberExisted", new ActionMessage("errors.asset.existedDecisionNumber"));
            saveErrors(request, errors);
            return false;
            }
             */
            isExist = false;
            isExist = assetDAO.checkUsedCode(bean.getAssId(), bean.getUsedCode());
            if (isExist) {
                ActionMessages errors = new ActionMessages();
                errors.add("usedCodeExisted", new ActionMessage("errors.asset.existedUsedCode"));
                saveErrors(request, errors);
                return false;
            }
            if (bNew) {
                assetDAO.insertAsset(bean);
            } else {
                HttpSession session = request.getSession();
                OnlineUser user = MCUtil.getOnlineUser(session);
                LogUtil.info("Username : " + user.getName() + " - Form : " + mapping.getPath() + " - EDIT");
                assetDAO.updateAsset(bean);
            }
        } catch (Exception ex) {
            LogUtil.error("FAILED:Vender:add-" + ex.getMessage());
            ex.printStackTrace();
        }
        return true;
    }
}
