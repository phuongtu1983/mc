/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.permission;

import com.venus.core.auth.OnlineUser;
import com.venus.core.util.LogUtil;
import com.venus.mc.bean.PermissionBean;
import com.venus.mc.bean.PermissionDetailBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.PermissionDAO;
import com.venus.mc.util.MCUtil;
import com.venus.mc.util.PermissionUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

/**
 *
 * @author phuongtu
 */
public class AddPermissionAction extends SpineAction {

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
        PermissionFormBean formBean = (PermissionFormBean) form;
        PermissionDAO permissionDAO = new PermissionDAO();
        PermissionBean bean = null;
        boolean bNew = false;
        boolean isExist = false;
        try {
            bean = permissionDAO.getPermissionByNumber(formBean.getName());
        } catch (Exception ex) {
        }
        int perId = formBean.getPerId();
        if (perId == 0) {
            bNew = true;
            if (bean != null) {
                isExist = true;
            }
        } else {
            bNew = false;
            if (bean != null && bean.getPerId() != perId) {
                isExist = true;
            }
        }
        if (isExist) {
            ActionMessages errors = new ActionMessages();
            errors.add("permissionExisted", new ActionMessage("errors.permission.existed"));
            saveErrors(request, errors);
            return false;
        }
        bean = new PermissionBean();
        bean.setPerId(perId);
        bean.setName(formBean.getName());

        String orgIds = "";
        if (formBean.getPermissionOrgId() != null) {
            int length = formBean.getPermissionOrgId().length;
            orgIds = formBean.getPermissionOrgId()[0];
            for (int i = 1; i < length; i++) {
                orgIds += "," + formBean.getPermissionOrgId()[i];
            }
        }
        bean.setOrganizations(orgIds);

        String empIds = "";
        if (formBean.getPermissionEmpId() != null) {
            int length = formBean.getPermissionEmpId().length;
            empIds = formBean.getPermissionEmpId()[0];
            for (int i = 1; i < length; i++) {
                empIds += "," + formBean.getPermissionEmpId()[i];
            }
        }
        bean.setEmployees(empIds);

        try {
            if (bNew) {
                perId = permissionDAO.insertPermission(bean);
            } else {
                HttpSession session = request.getSession();
                OnlineUser user = MCUtil.getOnlineUser(session);
                LogUtil.info("Username : " + user.getName() + " - Form : " + mapping.getPath() + " - EDIT");
                permissionDAO.updatePermission(bean);
            }
            String funcLists = "";
            if (formBean.getFuncList() != null) {
                int length = formBean.getFuncList().length;
                funcLists = formBean.getFuncList()[0];
                for (int i = 1; i < length; i++) {
                    funcLists += "," + formBean.getFuncList()[i];
                }
            }

            String funcAdds = "";
            if (formBean.getFuncAdd() != null) {
                int length = formBean.getFuncAdd().length;
                funcAdds = formBean.getFuncAdd()[0];
                for (int i = 1; i < length; i++) {
                    funcAdds += "," + formBean.getFuncAdd()[i];
                }
            }

            String funcDeletes = "";
            if (formBean.getFuncDelete() != null) {
                int length = formBean.getFuncDelete().length;
                funcDeletes = formBean.getFuncDelete()[0];
                for (int i = 1; i < length; i++) {
                    funcDeletes += "," + formBean.getFuncDelete()[i];
                }
            }

            String funcEdits = "";
            if (formBean.getFuncEdit() != null) {
                int length = formBean.getFuncEdit().length;
                funcEdits = formBean.getFuncEdit()[0];
                for (int i = 1; i < length; i++) {
                    funcEdits += "," + formBean.getFuncEdit()[i];
                }
            }
            
            String funcViews = "";
            if (formBean.getFuncView() != null) {
                int length = formBean.getFuncView().length;
                funcViews = formBean.getFuncView()[0];
                for (int i = 1; i < length; i++) {
                    funcViews += "," + formBean.getFuncView()[i];
                }
            }
            
            if (bNew) {
                PermissionDetailBean detailBean = null;
                detailBean = new PermissionDetailBean(PermissionUtil.FUNC_LIST, funcLists);
                detailBean.setPerId(perId);
                permissionDAO.insertPermissionDetail(detailBean);
                detailBean = new PermissionDetailBean(PermissionUtil.FUNC_ADD, funcAdds);
                detailBean.setPerId(perId);
                permissionDAO.insertPermissionDetail(detailBean);
                detailBean = new PermissionDetailBean(PermissionUtil.FUNC_DELETE, funcDeletes);
                detailBean.setPerId(perId);
                permissionDAO.insertPermissionDetail(detailBean);
                detailBean = new PermissionDetailBean(PermissionUtil.FUNC_EDIT, funcEdits);
                detailBean.setPerId(perId);
                permissionDAO.insertPermissionDetail(detailBean);
                detailBean = new PermissionDetailBean(PermissionUtil.FUNC_VIEW, funcViews);
                detailBean.setPerId(perId);
                permissionDAO.insertPermissionDetail(detailBean);
            } else {
                PermissionDetailBean detailBean = null;
                detailBean = new PermissionDetailBean(PermissionUtil.FUNC_LIST, funcLists);
                detailBean.setPerId(perId);
                permissionDAO.updatePermissionDetail(detailBean);
                detailBean = new PermissionDetailBean(PermissionUtil.FUNC_ADD, funcAdds);
                detailBean.setPerId(perId);
                permissionDAO.updatePermissionDetail(detailBean);
                detailBean = new PermissionDetailBean(PermissionUtil.FUNC_DELETE, funcDeletes);
                detailBean.setPerId(perId);
                permissionDAO.updatePermissionDetail(detailBean);
                detailBean = new PermissionDetailBean(PermissionUtil.FUNC_EDIT, funcEdits);
                detailBean.setPerId(perId);
                permissionDAO.updatePermissionDetail(detailBean);
                detailBean = new PermissionDetailBean(PermissionUtil.FUNC_VIEW, funcViews);
                detailBean.setPerId(perId);
                permissionDAO.updatePermissionDetail(detailBean);
            }
        } catch (Exception ex) {
            LogUtil.error("FAILED:Request:add-" + ex.getMessage());
            ex.printStackTrace();
        }
        return true;
    }
}
