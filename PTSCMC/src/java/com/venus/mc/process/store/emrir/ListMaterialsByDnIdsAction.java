/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.process.store.emrir;

import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.EdeliveryNoticeDetailBean;
import com.venus.mc.bean.EmrirDetailBean;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import com.venus.mc.util.Constants;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.DeliveryNoticeDAO;
import java.util.ArrayList;

/**
 *
 * @author kngo
 */
public class ListMaterialsByDnIdsAction extends SpineAction {

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
        int ednId = NumberUtil.parseInt(request.getParameter("ednId"), 0);

        try {
            ArrayList arrMat2 = new ArrayList();

            if (ednId > 0) {
                ArrayList arrMat = new ArrayList();
                DeliveryNoticeDAO dnDAO = new DeliveryNoticeDAO();
                arrMat = dnDAO.getEdeliveryNoticeDetails(ednId);

                EmrirDetailBean mrirDetailBean = null;
                EdeliveryNoticeDetailBean dnBean = null;

                for (int i = 0; i < arrMat.size(); i++) {
                    dnBean = (EdeliveryNoticeDetailBean) arrMat.get(i);
                    mrirDetailBean = new EmrirDetailBean();
                    mrirDetailBean.setEmatId(dnBean.getEmatId());
                    mrirDetailBean.setMatName(dnBean.getMatName());
                    mrirDetailBean.setUnit(dnBean.getUnit());
                    mrirDetailBean.setQuantity(dnBean.getQuantity());
                    arrMat2.add(mrirDetailBean);
                }
            }
            request.setAttribute(Constants.EMRIR_MATERIAL_LIST, arrMat2);
        } catch (Exception ex) {
        }

        this.actionForwardResult = "materials";
        return true;
    }
}
