package com.venus.mc.origin;

/**
 * @author Mai Vinh Loc
 */
import com.venus.core.auth.OnlineUser;
import com.venus.core.util.LogUtil;
import com.venus.mc.bean.OriginBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.OriginDAO;
import com.venus.mc.util.MCUtil;
import com.venus.mc.util.PermissionUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

public class AddOriginAction extends SpineAction {

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
        OriginFormBean formBean = (OriginFormBean) form;
        int oriId = formBean.getOriId();
        boolean bNew = false;
        if (oriId == 0) {
            bNew = true;
        } else {
            bNew = false;
        }

        OriginBean bean = new OriginBean();
        bean.setOriId(formBean.getOriId());
        bean.setNameEn(formBean.getNameEn());
        bean.setNameVn(formBean.getNameVn());
        bean.setNote(formBean.getNote());

        OriginDAO originDAO = new OriginDAO();

        try {
            boolean isExist = false;
            isExist = originDAO.checkNameVn(formBean.getOriId(), formBean.getNameVn());
            if (isExist) {
                ActionMessages errors = new ActionMessages();
                errors.add("oriVnExisted", new ActionMessage("errors.origin.existedOriVn"));
                saveErrors(request, errors);
                return false;
            }
            isExist = false;
            isExist = originDAO.checkNameEn(formBean.getOriId(), formBean.getNameEn());
            if (isExist) {
                ActionMessages errors = new ActionMessages();
                errors.add("oriEnExisted", new ActionMessage("errors.origin.existedOriEn"));
                saveErrors(request, errors);
                return false;
            }
            if (bNew) {
                originDAO.insertOrigin(bean);
            } else {
                HttpSession session = request.getSession();
                OnlineUser user = MCUtil.getOnlineUser(session);
                LogUtil.info("Username : " + user.getName() + " - Form : " + mapping.getPath() + " - EDIT");
                originDAO.updateOrigin(bean);
            }
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
        return PermissionUtil.PER_LIBRARY_MATERIAL_ORIGIN;
    }
}
