/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.process.store.dmv;

import com.venus.core.util.OutputUtil;
import com.venus.mc.bean.DmvBean;
import com.venus.mc.bean.DmvMaterialBean;
import com.venus.mc.bean.RfmBean;
import com.venus.mc.bean.RfmDetailBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.DmvDAO;
import com.venus.mc.dao.RfmDAO;
import com.venus.mc.util.MCUtil;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

/**
 *
 * @author kngo
 */
public class CreateRfmFromDmvAction extends SpineAction {

    @Override
    protected boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        DmvFormBean formBean = (DmvFormBean) form;
        RfmDAO rfmDAO = new RfmDAO();
        RfmBean bean = null;
        int dmvId = formBean.getDmvId();
        boolean isExist = false;
        try {
            bean = rfmDAO.getRfmByNumber(0, formBean.getRfmNumber());
        } catch (Exception ex) {
        }
        if (bean != null) {
            isExist = true;
        }
        if (isExist) {
            ActionMessages errors = new ActionMessages();
            errors.add("mrirExisted", new ActionMessage("errors.mrir.existed"));
            saveErrors(request, errors);
            return false;
        }
        DmvDAO dmvDAO = new DmvDAO();
        DmvBean dmvBean = null;
        try {
            dmvBean = dmvDAO.getDmv(dmvId);
        } catch (Exception ex) {
        }

        bean = new RfmBean();
        bean.setCreator(MCUtil.getMemberID(session));
        bean.setRfmNumber(formBean.getRfmNumber());
        //bean.setCreatedDate(formBean.getCreatedDate());
        bean.setOrgId(dmvBean.getOrgId());
        bean.setRequestOrg(dmvBean.getOrgId());
        bean.setProId(dmvBean.getProId());
        bean.setKind(0);
        bean.setReqType(1);
        bean.setStoId(dmvBean.getStoId());
        bean.setReqId(dmvBean.getReqId());
        bean.setDeliveryPlace("");

        int rfmId = 0;
        try {
            rfmId = rfmDAO.insertRfm(bean);
        } catch (Exception ex) {
        }
        ArrayList arrMaterial = null;
        try {
            arrMaterial = dmvDAO.getMaterialsFromDmv(dmvId);
        } catch (Exception ex) {
        }
        RfmDetailBean detBean = null;
        for (int i = 0; i < arrMaterial.size(); i++) {
            DmvMaterialBean dmvMatBean = (DmvMaterialBean) arrMaterial.get(i);
            detBean = new RfmDetailBean();
            detBean.setRfmId(rfmId);
            detBean.setMatId(dmvMatBean.getMatId());
            detBean.setMsvId(formBean.getMsvId());
            detBean.setQuantity(dmvMatBean.getQuantity());
            detBean.setUnit(dmvMatBean.getUnit());
            detBean.setReqDetailId(dmvMatBean.getReqDetailId());
            detBean.setStoId(formBean.getStoId());
            detBean.setPrice(dmvMatBean.getPrice());
            detBean.setComment("");
            try {
                rfmDAO.insertRfmDetail(detBean, 0);
            } catch (Exception ex) {
            }
        }
        try {
            dmvDAO.updateDmv(dmvId, 0, 0, rfmId, 0);
        } catch (Exception ex) {
        }
        OutputUtil.sendStringToOutput(response, "" + rfmId);
        return true;
    }

    @Override
    protected boolean isReturnStream() {
        return true;
    }
}
