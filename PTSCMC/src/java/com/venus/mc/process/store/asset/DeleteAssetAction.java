package com.venus.mc.process.store.asset;

/**
 * @author Mai Vinh Loc
 */
import com.venus.core.auth.OnlineUser;
import com.venus.core.util.LogUtil;
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

public class DeleteAssetAction extends SpineAction {

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
        String[] arrAssetid = request.getParameterValues("assId");
        try {
            OnlineUser user = MCUtil.getOnlineUser(session);
            LogUtil.info("Username : " + user.getName() + " - Form : " + mapping.getPath() + " - DELETE");
            int length = 0;
            boolean isExist = false;
            boolean isExist1 = false;
            AssetDAO assetDAO = new AssetDAO();
            if (arrAssetid != null) {
                length = arrAssetid.length;
            }
            for (int i = 0; i < length; i++) {
                //       isExist = assetDAO.checkDeleted(arrAssetid[i]);
                //       if (!isExist) {
                assetDAO.deleteAsset(arrAssetid[i]);
                //       } else {
                //           isExist1 = true;
                //       }
            }
            if (isExist1) {
                ActionMessages errors = new ActionMessages();
                errors.add("deleteCode", new ActionMessage("errors.delete"));
                saveErrors(request, errors);
                return false;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return true;
    }
}
