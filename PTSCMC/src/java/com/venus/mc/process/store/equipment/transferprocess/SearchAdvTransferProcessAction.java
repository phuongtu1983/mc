package com.venus.mc.process.store.equipment.transferprocess;

/**
 * @author Mai Vinh Loc
 */
import com.venus.core.util.LogUtil;
import com.venus.mc.bean.TransferProcessBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.TransferProcessDAO;
import com.venus.mc.util.Constants;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import java.util.ArrayList;

public class SearchAdvTransferProcessAction extends SpineAction {

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
        SearchAdvTransferProcessFormBean formBean = (SearchAdvTransferProcessFormBean) form;
        TransferProcessBean bean = new TransferProcessBean();

        bean.setTpId(formBean.getTpId());
        bean.setEquId(formBean.getEquId());
        bean.setAssId(formBean.getAssId());
        bean.setReceiveOrg(formBean.getReceiveOrg());
        bean.setReceiveEmp(formBean.getReceiveEmp());
        bean.setProject(formBean.getProject());
        bean.setComment(formBean.getComment());
        bean.setReceiveDate(formBean.getReceiveDate());
        bean.setReturnDate(formBean.getReturnDate());

        ArrayList transferprocessList = null;
        TransferProcessDAO transferprocessDAO = new TransferProcessDAO();
        try {
            transferprocessList = transferprocessDAO.searchAdvTransferProcess(bean);
        } catch (Exception ex) {
            LogUtil.error("FAILED: searchAdvTransferProcess-" + ex.getMessage());
            ex.printStackTrace();
        }
        request.setAttribute(Constants.TRANSFERPROCESS_LIST, transferprocessList);
        return true;
    }
}
