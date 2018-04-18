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
import com.venus.mc.bean.MsvBean;
import com.venus.mc.bean.MsvMaterialBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.DeliveryNoticeDAO;
import com.venus.mc.dao.DmvDAO;
import com.venus.mc.dao.MrirDAO;
import com.venus.mc.dao.MsvDAO;
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
public class CreateMsvFromDmvAction extends SpineAction {

    @Override
    protected boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        DmvFormBean formBean = (DmvFormBean) form;        
        MsvBean bean = null;
        int dmvId = formBean.getDmvId();
//        boolean isExist = false;
//        try {
//            bean = mrirDAO.getMrirByNumber(msvNumber);
//        } catch (Exception ex) {
//        }
//        if (bean != null) {
//            isExist = true;
//        }
//        if (isExist) {
//            ActionMessages errors = new ActionMessages();
//            errors.add("mrirExisted", new ActionMessage("errors.mrir.existed"));
//            saveErrors(request, errors);
//            return false;
//        }
        DmvDAO dmvDAO = new DmvDAO();
        DmvBean dmvBean = null;
        try {
            dmvBean = dmvDAO.getDmv(dmvId);
        } catch (Exception ex) {
        }

        bean = new MsvBean();
        bean.setMsvNumber(formBean.getMsvNumber());
        bean.setMrirId(dmvBean.getMrirId());
        bean.setCreatedEmpId(MCUtil.getMemberID(session));
        bean.setStoId(dmvBean.getStoId());
        bean.setTotal(dmvBean.getTotal());
        bean.setDeliverer(dmvBean.getDeliverer());

        MsvDAO msvDAO = new MsvDAO();
        int msvId = 0;
        try {
            msvId = msvDAO.insertMsv(bean);
        } catch (Exception ex) {
        }
        ArrayList arrMaterial = null;
        try {
            arrMaterial = dmvDAO.getMaterialsFromDmv(dmvId);
        } catch (Exception ex) {
        }
        MsvMaterialBean matBean;
        for (int i = 0; i < arrMaterial.size(); i++) {
            DmvMaterialBean dmvMatBean = (DmvMaterialBean) arrMaterial.get(i);
            matBean = new MsvMaterialBean();
            matBean.setMsvId(msvId);
            matBean.setMatId(dmvMatBean.getMatId());
            matBean.setReqDetailId(dmvMatBean.getReqDetailId());
            matBean.setUnit(dmvMatBean.getUnit());
            matBean.setQuantity(dmvMatBean.getQuantity());
            matBean.setPrice(dmvMatBean.getPrice());
            matBean.setTotal(dmvMatBean.getTotal());
            try {
                msvDAO.insertMsvMaterial(matBean);
            } catch (Exception ex) {            }
        }
        try {
            dmvDAO.updateDmv(dmvId, 0, msvId, 0, 0);
        } catch (Exception ex) {
        }
        OutputUtil.sendStringToOutput(response, "" + msvId);
        return true;
    }

    @Override
    protected boolean isReturnStream() {
        return true;
    }
}
