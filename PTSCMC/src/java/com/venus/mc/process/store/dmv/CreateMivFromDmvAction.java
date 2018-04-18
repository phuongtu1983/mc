/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.process.store.dmv;

import com.venus.core.util.OutputUtil;
import com.venus.mc.bean.DmvBean;
import com.venus.mc.bean.DmvMaterialBean;
import com.venus.mc.bean.MivBean;
import com.venus.mc.bean.MivDetailBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.DmvDAO;
import com.venus.mc.dao.MivDAO;
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
public class CreateMivFromDmvAction extends SpineAction {

    @Override
    protected boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        DmvFormBean formBean = (DmvFormBean) form;        
        MivDAO mivDAO = new MivDAO();
        MivBean bean = null;
        int dmvId = formBean.getDmvId();
        boolean isExist = false;
        try {
            bean = mivDAO.getMivByNumber(formBean.getMivNumber());
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

        bean = new MivBean();        
        bean.setMivNumber(formBean.getMivNumber());
        bean.setCreator(MCUtil.getMemberID(session));
        //bean.setCreatedDate();
        //bean.setReceiver(formBean.getReceiver());
        bean.setRfmId(formBean.getRfmId());
        //bean.setDescription(formBean.getDescription());
        bean.setTotal(dmvBean.getTotal());        
        bean.setMivKind(MivBean.KIND_COMPANY);
        bean.setType(dmvBean.getKind());
        bean.setOrgId(dmvBean.getOrgId());
        bean.setProId(dmvBean.getProId());
        bean.setRequestOrg(dmvBean.getOrgId());
        bean.setStoId(dmvBean.getStoId());
        bean.setReceiver(dmvBean.getReceiveEmpId());

        int mivId = 0;
        try {
            mivId = mivDAO.insertMiv(bean);
        } catch (Exception ex) {
        }
        ArrayList arrMaterial = null;
        try {
            arrMaterial = dmvDAO.getMaterialsFromDmv(dmvId);
        } catch (Exception ex) {
        }
        MivDetailBean matBean;
        for (int i = 0; i < arrMaterial.size(); i++) {
            DmvMaterialBean dmvMatBean = (DmvMaterialBean) arrMaterial.get(i);
            matBean = new MivDetailBean();
            matBean.setMivId(mivId);
            matBean.setMatId(dmvMatBean.getMatId());
            matBean.setReqDetailId(dmvMatBean.getReqDetailId());
            matBean.setUnit(dmvMatBean.getUnit());
            matBean.setQuantity(dmvMatBean.getQuantity());
            matBean.setPrice(dmvMatBean.getPrice());
            matBean.setTotal(dmvMatBean.getTotal());
            try {
                mivDAO.insertMivDetail(matBean);
            } catch (Exception ex) {            }
        }
        try {
            dmvDAO.updateDmv(dmvId, 0, 0, 0, mivId);
        } catch (Exception ex) {
        }
        OutputUtil.sendStringToOutput(response, "" + mivId);
        return true;
    }

    @Override
    protected boolean isReturnStream() {
        return true;
    }
}
