/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.process.store.edmv;

import com.venus.core.util.OutputUtil;
import com.venus.mc.bean.EdmvBean;
import com.venus.mc.bean.EdmvMaterialBean;
import com.venus.mc.bean.EmsvBean;
import com.venus.mc.bean.EmsvMaterialBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.EdmvDAO;
import com.venus.mc.dao.EmsvDAO;
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
public class CreateEmsvFromEdmvAction extends SpineAction {

    @Override
    protected boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        EdmvFormBean formBean = (EdmvFormBean) form;
        EmsvBean bean = null;
        //kiem tra ma so da ton tai hay chua
        if (DBUtil.checkFieldExisted("emsv", "emsv_number", formBean.getEmsvNumber())) {
            ActionMessages errors = new ActionMessages();
            errors.add("numberExisted", new ActionMessage("errors.duplicate.number"));
            saveErrors(request, errors);
            return false;
        }
        int edmvId = formBean.getEdmvId();
        EdmvDAO edmvDAO = new EdmvDAO();
        EdmvBean edmvBean = null;
        try {
            edmvBean = edmvDAO.getEdmv(edmvId);
        } catch (Exception ex) {
        }

        bean = new EmsvBean();
        bean.setEmsvNumber(formBean.getEmsvNumber());
        bean.setEmrirId(edmvBean.getEmrirId());
        bean.setCreatedEmpId(MCUtil.getMemberID(session));
        bean.setStoId(edmvBean.getStoId());
        bean.setTotal(edmvBean.getTotal());
        bean.setDeliverer(edmvBean.getDeliverer());

        EmsvDAO emsvDAO = new EmsvDAO();
        int emsvId = 0;
        try {
            emsvId = emsvDAO.insertEmsv(bean);
        } catch (Exception ex) {
        }
        ArrayList arrMaterial = null;
        try {
            arrMaterial = edmvDAO.getMaterialsFromEdmv(edmvId);
        } catch (Exception ex) {
        }
        EmsvMaterialBean matBean;
        for (int i = 0; i < arrMaterial.size(); i++) {
            EdmvMaterialBean dmvMatBean = (EdmvMaterialBean) arrMaterial.get(i);
            matBean = new EmsvMaterialBean();
            matBean.setEmsvId(emsvId);
            matBean.setEmatId(dmvMatBean.getEmatId());
            matBean.setUnit(dmvMatBean.getUnit());
            matBean.setQuantity(dmvMatBean.getQuantity());
            matBean.setPrice(dmvMatBean.getPrice());
            matBean.setTotal(dmvMatBean.getTotal());
            try {
                emsvDAO.insertEmsvMaterial(matBean);
            } catch (Exception ex) {
            }
        }
        try {
            edmvDAO.updateEdmv(edmvId, 0, emsvId, 0, 0);
        } catch (Exception ex) {
        }
        OutputUtil.sendStringToOutput(response, "" + emsvId);
        return true;
    }

    @Override
    protected boolean isReturnStream() {
        return true;
    }
}
