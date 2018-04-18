package com.venus.mc.process.equipment.colorcode;

/**
 * @author Mai Vinh Loc
 */
import com.venus.core.auth.OnlineUser;
import com.venus.core.util.LogUtil;
import com.venus.mc.bean.ColorCodeBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.ColorCodeDAO;
import com.venus.mc.util.MCUtil;
import com.venus.mc.util.PermissionUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

public class AddColorCodeAction extends SpineAction {

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
        ColorCodeFormBean formBean = (ColorCodeFormBean) form;
        int ccId = formBean.getCcId();
        boolean bNew = false;
        if (ccId == 0) {
            bNew = true;
        } else {
            bNew = false;
        }

        ColorCodeBean bean = new ColorCodeBean();
        bean.setCcId(formBean.getCcId());
        bean.setColorCode(formBean.getColorCode());
        bean.setTimeApplication(formBean.getTimeApplication());
        bean.setStartDate(formBean.getStartDate());
        bean.setEndDate(formBean.getEndDate());

        ColorCodeDAO colorCodeDAO = new ColorCodeDAO();

        try {
            boolean isExist = false;
            isExist = colorCodeDAO.checkColorCode(formBean.getCcId(), formBean.getColorCode());
            if (isExist) {
                ActionMessages errors = new ActionMessages();
                errors.add("existedColorCode", new ActionMessage("errors.colorcode.existedColorCode"));
                saveErrors(request, errors);
                return false;
            }
            if (bNew) {
                ccId = colorCodeDAO.insertColorCode(bean);
            } else {
                OnlineUser user = MCUtil.getOnlineUser(session);
                LogUtil.info("Username : " + user.getName() + " - Form : " + mapping.getPath() + " - EDIT");
                colorCodeDAO.updateColorCode(bean);
            }
            session.setAttribute("id", ccId);
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
        return PermissionUtil.PER_EQUIPMENT_COLOR;
    }
}
