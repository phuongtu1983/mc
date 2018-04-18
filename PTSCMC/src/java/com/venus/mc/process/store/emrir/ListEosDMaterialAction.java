/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.venus.mc.process.store.emrir;
import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.EmrirBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.DeliveryNoticeDAO;
import com.venus.mc.dao.EmrirDAO;
import com.venus.mc.util.Constants;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author kngo
 */
public class ListEosDMaterialAction extends SpineAction {

    @Override
    public boolean doAction(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        int eosdId = NumberUtil.parseInt(request.getParameter("eosdId"), 0);
        int emrirId = NumberUtil.parseInt(request.getParameter("emrirId"), 0);

        try {
            ArrayList arrEosD = null;
            EmrirDAO mrirDAO = new EmrirDAO();
            EmrirBean mrirBean = mrirDAO.getEmrir(emrirId);

            if (eosdId > 0) {
                arrEosD = mrirDAO.getEosDDetailsByEosD(eosdId);
            } else { // New
                if (mrirBean.getEdnId() > 0) {
                    DeliveryNoticeDAO dnDAO = new DeliveryNoticeDAO();
                    arrEosD = dnDAO.getEdeliveryNoticeDetails(mrirBean.getEdnId());
                }
            }

            if (arrEosD == null) {
                arrEosD = new ArrayList();
            }

            request.setAttribute(Constants.EOSD_MATERIAL_LIST, arrEosD);
        } catch (Exception ex) {
        }

        return true;
    }
}
