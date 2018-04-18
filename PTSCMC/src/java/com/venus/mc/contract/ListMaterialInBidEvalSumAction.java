/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.contract;

import com.venus.mc.bean.ContractBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.ContractDAO;
import com.venus.mc.util.Constants;
import com.venus.mc.util.MCUtil;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.LabelValueBean;

/**
 *
 * @author phuongtu
 */
public class ListMaterialInBidEvalSumAction extends SpineAction {

    @Override
    public boolean doAction(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        ArrayList arrMaterial = null;
        try {
            ContractDAO contractDAO = new ContractDAO();
            //arrMaterial = contractDAO.getDetailFromBidEvalSumDetail(request.getParameter("tenId"), request.getParameter("venId"), request.getParameter("matIds"));
            // Them moi bao cao danh gia
                arrMaterial = contractDAO.getDetailFromBidEvalSumDetailNon(request.getParameter("tenId"), request.getParameter("venId"), request.getParameter("matIds"));
            //
        } catch (Exception ex) {
        }
        if (arrMaterial == null) {
            arrMaterial = new ArrayList();
        }
        request.setAttribute(Constants.MATERIAL_LIST, arrMaterial);

        LabelValueBean value = null;
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


        String kind = request.getParameter("kind");
        if (kind.equals(ContractBean.KIND_CONTRACT + "") || kind.equals(ContractBean.KIND_ORDER + "")) {
            this.actionForwardResult = "contract";
        } else if (kind.equals(ContractBean.KIND_PRINCIPLE + "")) {
            this.actionForwardResult = "principle";
        }
        return true;
    }
}
