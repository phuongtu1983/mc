/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.process.store.edmv;

import com.venus.core.util.OutputUtil;
import com.venus.mc.bean.EdmvBean;
import com.venus.mc.bean.EdmvMaterialBean;
import com.venus.mc.bean.RfmBean;
import com.venus.mc.bean.RfmDetailBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.EdmvDAO;
import com.venus.mc.dao.RfmDAO;
import com.venus.mc.database.DBUtil;
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
public class CreateErfmFromEdmvAction extends SpineAction {

    @Override
    protected boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        EdmvFormBean formBean = (EdmvFormBean) form;
        RfmDAO rfmDAO = new RfmDAO();
        RfmBean bean = null;
        int edmvId = formBean.getEdmvId();
        //kiem tra ma so da ton tai hay chua
        if (DBUtil.checkFieldExisted("erfm", "erfm_number", formBean.getErfmNumber())) {
            ActionMessages errors = new ActionMessages();
            errors.add("numberExisted", new ActionMessage("errors.duplicate.number"));
            saveErrors(request, errors);
            return false;
        }
        EdmvDAO edmvDAO = new EdmvDAO();
        EdmvBean dmvBean = null;
        try {
            dmvBean = edmvDAO.getEdmv(edmvId);
        } catch (Exception ex) {
        }

        bean = new RfmBean();
        bean.setCreator(MCUtil.getMemberID(session));
        bean.setRfmNumber(formBean.getErfmNumber());
        //bean.setCreatedDate(formBean.getCreatedDate());
        bean.setOrgId(dmvBean.getOrgId());
        bean.setRequestOrg(dmvBean.getOrgId());
        bean.setProId(dmvBean.getProId());
        bean.setKind(1);
        bean.setReqType(1);
        bean.setStoId(dmvBean.getStoId());

        int erfmId = 0;
        try {
            erfmId = rfmDAO.insertRfm(bean);
        } catch (Exception ex) {
        }
        ArrayList arrMaterial = null;
        try {
            arrMaterial = edmvDAO.getMaterialsFromEdmv(edmvId);
        } catch (Exception ex) {
        }
        RfmDetailBean detBean = null;
        for (int i = 0; i < arrMaterial.size(); i++) {
            EdmvMaterialBean dmvMatBean = (EdmvMaterialBean) arrMaterial.get(i);
            detBean = new RfmDetailBean();
            detBean.setRfmId(erfmId);
            detBean.setMatId(dmvMatBean.getEmatId());
            detBean.setMsvId(formBean.getEmsvId());
            detBean.setQuantity(dmvMatBean.getQuantity());
            detBean.setUnit(dmvMatBean.getUnit());
            detBean.setStoId(formBean.getStoId());
            detBean.setPrice(dmvMatBean.getPrice());
            try {
                rfmDAO.insertRfmDetail(detBean, 1);
            } catch (Exception ex) {
            }
        }
        try {
            edmvDAO.updateEdmv(edmvId, 0, 0, erfmId, 0);
        } catch (Exception ex) {
        }
        OutputUtil.sendStringToOutput(response, "" + erfmId);
        return true;
    }

    @Override
    protected boolean isReturnStream() {
        return true;
    }
}
