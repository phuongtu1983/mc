package com.venus.mc.library.ematerial;

/**
 * @author Mai Vinh Loc
 */
import com.venus.core.auth.OnlineUser;
import com.venus.core.util.LogUtil;
import com.venus.core.util.StringUtil;
import com.venus.mc.bean.EmaterialBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.EmaterialDAO;
import com.venus.mc.util.MCUtil;
import com.venus.mc.util.PermissionUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

public class AddEmaterialAction extends SpineAction {

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
        EmaterialFormBean formBean = (EmaterialFormBean) form;
        int ematId = formBean.getEmatId();
        boolean bNew = false;
        if (ematId == 0) {
            bNew = true;
        } else {
            bNew = false;
        }

        EmaterialBean bean = new EmaterialBean();
        bean.setEmatId(formBean.getEmatId());
        bean.setOriId(formBean.getOriId());
        bean.setUniId(formBean.getUniId());
        bean.setCode(formBean.getCode());
        bean.setNameEn(StringUtil.encodeHTML(formBean.getNameEn()));
        bean.setNameVn(StringUtil.encodeHTML(formBean.getNameVn()));
        if (bean.getNameEn().length() == 0) {
            bean.setNameEn(bean.getNameVn());
        }
        
        bean.setKind(formBean.getKind());
        bean.setNote(formBean.getNote());
        bean.setQc(formBean.getQc());
        EmaterialDAO ematerialDAO = new EmaterialDAO();

        try {
            boolean isExist = false;
            isExist = ematerialDAO.checkCode(formBean.getEmatId(), formBean.getCode());
            if (isExist) {
                ActionMessages errors = new ActionMessages();
                errors.add("matCodeExisted", new ActionMessage("errors.ematerial.existedCode"));
                saveErrors(request, errors);
                return false;
            }
            isExist = false;
            isExist = ematerialDAO.checkName(bean.getEmatId(), bean.getNameVn(), bean.getNameEn());
            if (isExist) {
                ActionMessages errors = new ActionMessages();
                errors.add("matNameExisted", new ActionMessage("errors.ematerial.existedName"));
                saveErrors(request, errors);
                return false;
            }
            if (bNew) {
                ematerialDAO.insertEmaterial(bean);
            } else {
                HttpSession session = request.getSession();
                OnlineUser user = MCUtil.getOnlineUser(session);
                LogUtil.info("Username : " + user.getName() + " - Form : " + mapping.getPath() + " - EDIT");
                ematerialDAO.updateEmaterial(bean);
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
        return PermissionUtil.PER_LIBRARY_MATERIAL_OUT;
    }
}
