/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.process.store.edmv;

import com.venus.core.util.OutputUtil;
import com.venus.mc.bean.DmvBean;
import com.venus.mc.bean.DmvMaterialBean;
import com.venus.mc.bean.EdmvBean;
import com.venus.mc.bean.EdmvMaterialBean;
import com.venus.mc.bean.MivBean;
import com.venus.mc.bean.MivDetailBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.DmvDAO;
import com.venus.mc.dao.EdmvDAO;
import com.venus.mc.dao.MivDAO;
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
public class CreateEmivFromEdmvAction extends SpineAction {

    @Override
    protected boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        EdmvFormBean formBean = (EdmvFormBean) form;
        MivDAO mivDAO = new MivDAO();
        MivBean bean = null;
        int edmvId = formBean.getEdmvId();
        //kiem tra ma so da ton tai hay chua
        if (DBUtil.checkFieldExisted("emiv", "emiv_number", formBean.getEmsvNumber())) {
            ActionMessages errors = new ActionMessages();
            errors.add("numberExisted", new ActionMessage("errors.duplicate.number"));
            saveErrors(request, errors);
            return false;
        }
        EdmvDAO edmvDAO = new EdmvDAO();
        EdmvBean edmvBean = null;
        try {
            edmvBean = edmvDAO.getEdmv(edmvId);
        } catch (Exception ex) {
        }

        bean = new MivBean();
        bean.setMivNumber(formBean.getEmivNumber());
        bean.setCreator(MCUtil.getMemberID(session));
        //bean.setCreatedDate();
        //bean.setReceiver(formBean.getReceiver());
        bean.setRfmId(formBean.getErfmId());
        //bean.setDescription(formBean.getDescription());
        bean.setTotal(edmvBean.getTotal());
        bean.setMivKind(MivBean.KIND_PARTNER);
        bean.setType(edmvBean.getKind());
        bean.setOrgId(edmvBean.getOrgId());
        bean.setProId(edmvBean.getProId());
        bean.setReceiver(edmvBean.getReceiveEmpId());
        bean.setRequestOrg(edmvBean.getOrgId());
        bean.setStoId(edmvBean.getStoId());

        int emivId = 0;
        try {
            emivId = mivDAO.insertEMiv(bean);
        } catch (Exception ex) {
        }
        ArrayList arrMaterial = null;
        try {
            arrMaterial = edmvDAO.getMaterialsFromEdmv(edmvId);
        } catch (Exception ex) {
        }
        MivDetailBean matBean;
        for (int i = 0; i < arrMaterial.size(); i++) {
            EdmvMaterialBean dmvMatBean = (EdmvMaterialBean) arrMaterial.get(i);
            matBean = new MivDetailBean();
            matBean.setMivId(emivId);
            matBean.setMatId(dmvMatBean.getEmatId());
            matBean.setUnit(dmvMatBean.getUnit());
            matBean.setQuantity(dmvMatBean.getQuantity());
            matBean.setPrice(dmvMatBean.getPrice());
            matBean.setTotal(dmvMatBean.getTotal());
            try {
                mivDAO.insertEMivDetail(matBean);
            } catch (Exception ex) {
            }
        }
        try {
            edmvDAO.updateEdmv(edmvId, 0, 0, 0, emivId);
        } catch (Exception ex) {
        }
        OutputUtil.sendStringToOutput(response, "" + emivId);
        return true;
    }

    @Override
    protected boolean isReturnStream() {
        return true;
    }
}
