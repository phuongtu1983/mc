package com.venus.mc.library.materialnotcode;

/**
 * @author Mai Vinh Loc
 */
import com.venus.core.auth.OnlineUser;
import com.venus.core.util.LogUtil;
import com.venus.mc.bean.SpeBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.SpeDAO;
import com.venus.mc.util.MCUtil;
import com.venus.mc.util.PermissionUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class AddSpeAction extends SpineAction {

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

        SpeFormBean formBean = (SpeFormBean) form;
        String spe = session.getAttribute("spe").toString();
        boolean bNew = false;
        if (spe.equals("0")) {
            bNew = true;
        } else {
            bNew = false;
        }

        SpeBean bean = new SpeBean();

        bean.setSpe1Id(formBean.getSpe1Id());
        bean.setSpe2Id(formBean.getSpe2Id());
        bean.setSpe3Id(formBean.getSpe3Id());
        bean.setSpe4Id(formBean.getSpe4Id());
        bean.setSpe5Id(formBean.getSpe5Id());
        bean.setSpe6Id(formBean.getSpe6Id());
        bean.setSign(formBean.getSign());
        bean.setNote(formBean.getNote());
        bean.setSpe(formBean.getSpe());

        SpeDAO speDAO = new SpeDAO();

        try {
            if (bNew) {
                //           speDAO.insertSpe(bean);
            } else {
                OnlineUser user = MCUtil.getOnlineUser(session);
                LogUtil.info("Username : " + user.getName() + " - Form : " + mapping.getPath() + " - EDIT");
                //speDAO.updateSpe(bean);
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
        return PermissionUtil.PER_LIBRARY_MATERIAL_CATALOG;
    }
}
