/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.process.store.mrir;

import com.venus.core.util.LogUtil;
import com.venus.core.util.NumberUtil;
import com.venus.core.util.OutputUtil;
import com.venus.mc.bean.OsDDetailBean;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import com.venus.mc.util.Constants;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.DnDAO;
import com.venus.mc.dao.MrirDAO;
import java.util.ArrayList;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

/**
 *
 * @author kngo
 */
public class AddOsDMaterialAction extends SpineAction {

    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    @Override
    public boolean doAction(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        OsDMaterialFormBean formBean = (OsDMaterialFormBean) form;
        OsDDetailBean bean = new OsDDetailBean();
        bean.setDetId(formBean.getDetId()); // primary key
        bean.setItemNo(formBean.getItemNo());
        bean.setQuantity(formBean.getQuantity());
        bean.setOsdId(formBean.getOsdId());
        bean.setDiscipline(formBean.getDiscipline());        
        if (formBean.getNonConform() != null) {
            int length = formBean.getNonConform().length;
            String appConform = formBean.getNonConform()[0];
            for (int i = 1; i < length; i++) {
                appConform += "," + formBean.getNonConform()[i];
            }
            bean.setNonConform(appConform);
        }
        bean.setDescription(formBean.getDescription());
        bean.setStatus(formBean.getStatus());
        bean.setCorrectAct(formBean.getCorrectAct());
        bean.setVendorNote(formBean.getVendorNote());
        bean.setClosedOut(formBean.getClosedOut());
        bean.setClosedDate(formBean.getClosedDate());
        bean.setRemark(formBean.getRemark());
        MrirDAO mrirDAO = new MrirDAO();
        try {
            mrirDAO.updateOsDDetail(bean);
            OutputUtil.sendStringToOutput(response, formBean.getOsdId() + "");
        } catch (Exception ex) {
            LogUtil.error(this.getClass(),ex.getMessage());
            OutputUtil.sendStringToOutput(response,"");
        }
        return true;
    }

    @Override
    protected boolean isReturnStream() {
        return true;
    }
}
