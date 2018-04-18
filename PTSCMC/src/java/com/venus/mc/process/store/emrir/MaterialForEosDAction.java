/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.process.store.emrir;

import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.EdeliveryNoticeDetailBean;
import com.venus.mc.bean.EmrirBean;
import com.venus.mc.bean.EosDBean;
import com.venus.mc.bean.EosDDetailBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.DeliveryNoticeDAO;
import com.venus.mc.dao.EmrirDAO;
import com.venus.mc.util.Constants;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author kngo
 */
public class MaterialForEosDAction extends SpineAction {

    @Override
    public boolean doAction(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        int ematId = NumberUtil.parseInt(request.getParameter("ematId"), 0);
        int emrirId = NumberUtil.parseInt(request.getParameter("emrirId"), 0);

        try {
            EmrirDAO mrirDAO = new EmrirDAO();
            DeliveryNoticeDAO dnDAO = new DeliveryNoticeDAO();
            EosDBean osDBean = null;
            osDBean = mrirDAO.getEosDByEmrir(emrirId);

            EosDDetailBean osDDetailBean = new EosDDetailBean();
            if (osDBean != null) {
                osDDetailBean.setEosdId(osDBean.getEosdId());
            }

            osDDetailBean.setEmatId(ematId);

            if (emrirId > 0) {
                EmrirBean mrirBean = mrirDAO.getEmrir(emrirId);

                if (mrirBean != null) {
                    EdeliveryNoticeDetailBean dnDetailBean = dnDAO.getEdeliveryNoticeDetail(ematId);
                    if (dnDetailBean != null) {
                        osDDetailBean.setMatName(dnDetailBean.getMatName());
                        osDDetailBean.setUnit(dnDetailBean.getUnit());
                        osDDetailBean.setQuantity(dnDetailBean.getQuantity());
                    }
                }
            }
            request.setAttribute(Constants.EOSD_MATERIAL, osDDetailBean);
        } catch (Exception ex) {
        }

        return true;
    }
}
