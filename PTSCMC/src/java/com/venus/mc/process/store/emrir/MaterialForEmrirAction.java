/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.process.store.emrir;

import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.EdeliveryNoticeDetailBean;
import com.venus.mc.bean.EmrirDetailBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.DeliveryNoticeDAO;
import com.venus.mc.util.Constants;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author kngo
 */
public class MaterialForEmrirAction extends SpineAction {

    @Override
    public boolean doAction(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        int emrirId = NumberUtil.parseInt(request.getParameter("emrirId"), 0);
        int ednId = NumberUtil.parseInt(request.getParameter("ednId"), 0);
        int ematId = NumberUtil.parseInt(request.getParameter("ematId"), 0);

        try {
            EmrirDetailBean mrirDetailBean = new EmrirDetailBean();
            DeliveryNoticeDAO dnDAO = new DeliveryNoticeDAO();

            if (emrirId > 0) {
                mrirDetailBean.setEmrirId(emrirId);
                mrirDetailBean.setEmatId(ematId);

                EdeliveryNoticeDetailBean dnDetailBean = dnDAO.getEdeliveryNoticeDetail(ematId);
                if (dnDetailBean != null) {
                    mrirDetailBean.setMatName(dnDetailBean.getMatName());
                    mrirDetailBean.setUnit(dnDetailBean.getUnit());
                    mrirDetailBean.setQuantity(dnDetailBean.getQuantity());
                    mrirDetailBean.setPrice(dnDetailBean.getPrice());
                }
            } else {
                if (ednId > 0) {
                    mrirDetailBean.setEmatId(ematId);

                    EdeliveryNoticeDetailBean dnDetailBean = dnDAO.getEdeliveryNoticeDetail(ematId);
                    if (dnDetailBean != null) {
                        mrirDetailBean.setMatName(dnDetailBean.getMatName());
                        mrirDetailBean.setUnit(dnDetailBean.getUnit());
                        mrirDetailBean.setQuantity(dnDetailBean.getQuantity());
                        mrirDetailBean.setPrice(dnDetailBean.getPrice());
                    }
                }
            }
            request.setAttribute(Constants.EMRIR_MATERIAL, mrirDetailBean);
        } catch (Exception ex) {
        }
        return true;
    }
}
