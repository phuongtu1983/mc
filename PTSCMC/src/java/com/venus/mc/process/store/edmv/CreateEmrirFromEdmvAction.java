/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.process.store.edmv;

import com.venus.core.util.OutputUtil;
import com.venus.mc.bean.EdmvBean;
import com.venus.mc.bean.EdmvMaterialBean;
import com.venus.mc.bean.EmrirBean;
import com.venus.mc.bean.EmrirDetailBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.EdmvDAO;
import com.venus.mc.dao.EmrirDAO;
import com.venus.mc.database.DBUtil;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

/**
 *
 * @author kngo
 */
public class CreateEmrirFromEdmvAction extends SpineAction {

    @Override
    protected boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        EdmvFormBean formBean = (EdmvFormBean) form;
        EmrirDAO emrirDAO = new EmrirDAO();
        EmrirBean bean = null;        
        int edmvId = formBean.getEdmvId();
        // boolean bNew = false;
        //kiem tra ma so da ton tai hay chua
        if (DBUtil.checkFieldExisted("emrir", "emrir_number", formBean.getEmrirNumber())) {
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

        bean = new EmrirBean();                
        bean.setEdnId(edmvBean.getEdnId());
        bean.setEmrirNumber(formBean.getEmrirNumber());        
        bean.setStatus(1);
        int emrirId = 0;
        try {
            emrirId =  emrirDAO.insertEmrir(bean);;
        } catch (Exception ex) {
        }
        ArrayList arrMaterial = null;
        try {
            arrMaterial = edmvDAO.getMaterialsFromEdmv(edmvId);
        } catch (Exception ex) {
        }
        EmrirDetailBean matBean;
        for (int i = 0; i < arrMaterial.size(); i++) {
            EdmvMaterialBean dmvMatBean = (EdmvMaterialBean) arrMaterial.get(i);
            matBean = new EmrirDetailBean();
            matBean.setEmatId(dmvMatBean.getEmatId());
            matBean.setEmrirId(emrirId);
            matBean.setQuantity(dmvMatBean.getQuantity());
            matBean.setPrice(dmvMatBean.getPrice());
            matBean.setUnit(dmvMatBean.getUnit());            
            try {
                emrirDAO.insertEmrirDetail(matBean);
            } catch (Exception ex) {
            }
        }
        try {
            edmvDAO.updateEdmv(edmvId, emrirId,0,0,0);
        } catch (Exception ex) {
        }      
        OutputUtil.sendStringToOutput(response, "" + emrirId);
        return true;
    }

    @Override
    protected boolean isReturnStream() {
        return true;
    }
    
}
