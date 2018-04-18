package com.venus.mc.unit;

/**
 * @author Mai Vinh Loc
 */
import com.venus.core.auth.OnlineUser;
import com.venus.core.util.LogUtil;
import com.venus.mc.bean.UnitBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.UnitDAO;
import com.venus.mc.util.MCUtil;
import com.venus.mc.util.PermissionUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

public class AddUnitAction extends SpineAction {

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
        UnitFormBean formBean = (UnitFormBean) form;
        int uniId = formBean.getUniId();
        boolean bNew = false;
        if (uniId == 0) {
            bNew = true;
        } else {
            bNew = false;
        }

        UnitBean bean = new UnitBean();
        bean.setUniId(formBean.getUniId());
        bean.setUnitEn(formBean.getUnitEn());
        bean.setUnitVn(formBean.getUnitVn());

        UnitDAO unitDAO = new UnitDAO();

        try {
            boolean isExist = false;
            isExist = unitDAO.checkNameVn(formBean.getUniId(), formBean.getUnitVn());
            if (isExist) {
                ActionMessages errors = new ActionMessages();
                errors.add("unitVnExisted", new ActionMessage("errors.unit.existedUnitVn"));
                saveErrors(request, errors);
                return false;
            }
            isExist = false;
            isExist = unitDAO.checkNameEn(formBean.getUniId(), formBean.getUnitEn());
            if (isExist) {
                ActionMessages errors = new ActionMessages();
                errors.add("unitEnExisted", new ActionMessage("errors.unit.existedUnitEn"));
                saveErrors(request, errors);
                return false;
            }
            if (bNew) {
                unitDAO.insertUnit(bean);
            } else {
                HttpSession session = request.getSession();
                OnlineUser user = MCUtil.getOnlineUser(session);
                LogUtil.info("Username : " + user.getName() + " - Form : " + mapping.getPath() + " - EDIT");
                unitDAO.updateUnit(bean);
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
        return PermissionUtil.PER_LIBRARY_MATERIAL_UNIT;
    }
}
