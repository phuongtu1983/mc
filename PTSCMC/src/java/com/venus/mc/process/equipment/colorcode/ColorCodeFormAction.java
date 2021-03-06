package com.venus.mc.process.equipment.colorcode;

/**
 * @author Mai Vinh Loc
 */
import com.venus.mc.bean.ColorCodeBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.ColorCodeDAO;
import com.venus.mc.util.Constants;
import com.venus.mc.util.PermissionUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class ColorCodeFormAction extends SpineAction {

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

        ColorCodeBean bean = new ColorCodeBean();
        String ccId = request.getParameter("ccId");
        Integer id = (Integer) session.getAttribute("id");
        if (id != null) {
            ccId = id + "";
        }
        session.removeAttribute("id");
        if (!GenericValidator.isBlankOrNull(ccId)) {
            ColorCodeDAO colorCodeDAO = new ColorCodeDAO();
            try {
                bean = colorCodeDAO.getColorCode(ccId);
            } catch (Exception ex) {
            }
        }
        request.setAttribute(Constants.COLORCODE, bean);
        return true;
    }

    @Override
    protected String getFunction() {
        return PermissionUtil.FUNC_VIEW + ";" + PermissionUtil.FUNC_EDIT;
    }

    @Override
    protected int getPermit() {
        return PermissionUtil.PER_EQUIPMENT_COLOR;
    }
}
