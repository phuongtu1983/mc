package com.venus.mc.process.store.equipment.transferprocess;

/**
 * @author Mai Vinh Loc
 */
import com.venus.core.auth.OnlineUser;
import com.venus.core.util.LogUtil;
import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.TransferProcessBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.TransferProcessDAO;
import com.venus.mc.util.MCUtil;
import com.venus.mc.util.PermissionUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class AddTransferProcessAction extends SpineAction {

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
        TransferProcessFormBean formBean = (TransferProcessFormBean) form;
        int equId = NumberUtil.parseInt(request.getParameter("equId"),0);
        int tpId = formBean.getTpId();
        boolean bNew = false;
        if (tpId == 0) {
            bNew = true;
        } else {
            bNew = false;
        }

        TransferProcessBean bean = new TransferProcessBean();
        bean.setTpId(formBean.getTpId());
        bean.setEquId(equId);
        bean.setAssId(formBean.getAssId());
        bean.setReceiveOrg(formBean.getReceiveOrg());
        bean.setReceiveEmp(formBean.getReceiveEmp());
        bean.setProject(formBean.getProject());
        bean.setComment(formBean.getComment());
        bean.setReceiveDate(formBean.getReceiveDate());
        bean.setReturnDate(formBean.getReturnDate());

        TransferProcessDAO transferprocessDAO = new TransferProcessDAO();

        try {
            boolean isExist = false;
            /*         isExist = transferprocessDAO.checkDecisionNumber(formBean.getEquId(), formBean.getDecisionNumber());
            if (isExist) {
            ActionMessages errors = new ActionMessages();
            errors.add("decisionNumberExisted", new ActionMessage("errors.transferprocess.existedDecisionNumber"));
            saveErrors(request, errors);
            return false;
            }
             */
            
            if (bNew) {
                transferprocessDAO.insertTransferProcess(bean);
            } else {
                HttpSession session = request.getSession();
                OnlineUser user = MCUtil.getOnlineUser(session);
                LogUtil.info("Username : " + user.getName() + " - Form : " + mapping.getPath() + " - EDIT");
                transferprocessDAO.updateTransferProcess(bean);
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
        return PermissionUtil.PER_EQUIPMENT;
    }
}
