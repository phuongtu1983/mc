/*
 *
 * Created on April 13, 2007, 2:08 PM
 */
package com.venus.mc.contract.adjustment;

import com.venus.core.util.GenericValidator;
import com.venus.mc.bean.ContractBean;
import com.venus.mc.contract.ContractFormBean;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import com.venus.mc.util.Constants;

import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.ContractDAO;
import com.venus.mc.util.MCUtil;
import java.util.ArrayList;
import javax.servlet.http.HttpSession;
import org.apache.struts.util.LabelValueBean;

/**
 *
 * @author mai vinh loc
 * @version
 */
public class ListAdjustmentContractMaterialAction extends SpineAction {

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
        String conId = request.getParameter("conId");
        String matId = request.getParameter("matId");
        String reqDetailIds = request.getParameter("reqDetailIds");
        String orderMaterialSource = request.getParameter("orderMaterialSource");
        Integer stt = (Integer) session.getAttribute("stt");
        int parentId = 0;
        ArrayList materialList = null;
        if (!GenericValidator.isBlankOrNull(conId) && !GenericValidator.isBlankOrNull(matId)) {
            ContractDAO contractDAO = new ContractDAO();
            try {
                parentId = contractDAO.getContractId(conId);
                if (parentId > 0) {
                    conId = parentId + "";
                }
                materialList = contractDAO.getAdjustmentContractDetailsByMaterialIds(matId, conId, reqDetailIds, stt);
                stt++;
                session.removeAttribute("stt");
                session.setAttribute("stt", stt);
                String[] conIds = conId.split(",");
                int c = 0;
                if (conIds.length > 0) {
                    c = Integer.parseInt(conIds[0]);
                }
                ContractBean conBean = contractDAO.getContract(c);
                if (conBean == null) {
                    conBean = new ContractBean();
                }
                request.setAttribute(Constants.CONTRACT, conBean);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        if (materialList == null) {
            materialList = new ArrayList();
        }
        request.setAttribute(Constants.MATERIAL_LIST, materialList);

        request.setAttribute(Constants.ORDER_SOURCE, orderMaterialSource);

        LabelValueBean value;
        ArrayList arrMaterialStatus = new ArrayList();
        value = new LabelValueBean();
        value.setLabel(MCUtil.getBundleString("message.empty"));
        value.setValue(ContractFormBean.MATERIAL_STATUS_NORMAL + "");
        arrMaterialStatus.add(value);
        value = new LabelValueBean();
        value.setLabel(MCUtil.getBundleString("message.cancel"));
        value.setValue(ContractFormBean.MATERIAL_STATUS_CANCEL + "");
        arrMaterialStatus.add(value);
        request.setAttribute(Constants.PRINCIPLE_MATERIAL_KIND_LIST, arrMaterialStatus);

        ArrayList arrMaterialCancelStatus = new ArrayList();
        value = new LabelValueBean();
        value.setLabel(MCUtil.getBundleString("message.cancel"));
        value.setValue(ContractFormBean.MATERIAL_STATUS_CANCEL + "");
        arrMaterialCancelStatus.add(value);
        request.setAttribute(Constants.PRINCIPLE_MATERIAL_CANCEL_KIND_LIST, arrMaterialCancelStatus);

        return true;
    }
}
