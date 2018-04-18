/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.process.store.dmv;

import com.venus.core.auth.OnlineUser;
import com.venus.core.util.GenericValidator;
import com.venus.core.util.LogUtil;
import com.venus.core.util.NumberUtil;
import com.venus.core.util.OutputUtil;
import com.venus.mc.bean.DeliveryNoticeBean;
import com.venus.mc.bean.DeliveryNoticeDetailBean;
import com.venus.mc.bean.DmvBean;
import com.venus.mc.bean.DmvMaterialBean;
import com.venus.mc.bean.MrirBean;
import com.venus.mc.bean.MrirDetailBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.DeliveryNoticeDAO;
import com.venus.mc.dao.DmvDAO;
import com.venus.mc.dao.MrirDAO;
import com.venus.mc.process.store.mrir.MrirFormBean;
import com.venus.mc.util.Constants;
import com.venus.mc.util.MCUtil;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

/**
 *
 * @author kngo
 */
public class CreateMrirFromDmvAction extends SpineAction {

    @Override
    protected boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        DmvFormBean formBean = (DmvFormBean) form;
        MrirDAO mrirDAO = new MrirDAO();
        MrirBean bean = null;
        String mrirNumber = formBean.getMrirNumber();
        int dmvId = formBean.getDmvId();
        // boolean bNew = false;
        boolean isExist = false;
        try {
            bean = mrirDAO.getMrirByNumber(mrirNumber, formBean.getMrirId());
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

        bean = new MrirBean();
        bean.setConId(dmvBean.getConId());
        bean.setDnId(dmvBean.getDnId());        
        bean.setMrirNumber(mrirNumber);
        bean.setStatus(1);
        //bean.setMrirId(formBean.getMrirId());
        //bean.setCreatedDate(formBean.getCreatedDate());
        //bean.setDnId(formBean.getDnId());
        int mrirId = 0;
        try {
            mrirId = mrirDAO.insertMrir(bean);
        } catch (Exception ex) {
        }
        ArrayList arrMaterial = null;
        try {
            arrMaterial = dmvDAO.getMaterialsFromDmv(dmvId);
        } catch (Exception ex) {
        }
        MrirDetailBean matBean;
        for (int i = 0; i < arrMaterial.size(); i++) {
            DmvMaterialBean dmvMatBean = (DmvMaterialBean) arrMaterial.get(i);
            matBean = new MrirDetailBean();
            matBean.setMatId(dmvMatBean.getMatId());
            matBean.setMrirId(mrirId);
            matBean.setQuantity(dmvMatBean.getQuantity());
            matBean.setPrice(dmvMatBean.getPrice());
            matBean.setUnit(dmvMatBean.getUnit());
            matBean.setReqDetailId(dmvMatBean.getReqDetailId());
            try {
                mrirDAO.insertMrirDetail(matBean);
            } catch (Exception ex) {
            }
        }
        try {
            dmvDAO.updateDmv(dmvId, mrirId,0,0,0);
        } catch (Exception ex) {
        }      
        OutputUtil.sendStringToOutput(response, "" + mrirId);
        return true;
    }

    @Override
    protected boolean isReturnStream() {
        return true;
    }
    
}
