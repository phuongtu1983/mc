/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.process.store.miv;

import com.venus.core.util.GenericValidator;
import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.MivBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.StoreDAO;
import com.venus.mc.util.Constants;
import com.venus.mc.util.MCUtil;
import com.venus.mc.util.PermissionUtil;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.LabelValueBean;

/**
 *
 * @author phuongtu
 */
public class MivPanelAction extends SpineAction {

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
        int kind = 0;
        if (!GenericValidator.isBlankOrNull(request.getParameter("kind"))) {
            kind = NumberUtil.parseInt(request.getParameter("kind"), 0);
        }
        if (kind == 0) {
            kind = MivBean.KIND_COMPANY;
        }
        LabelValueBean label;
        label = new LabelValueBean();
        label.setLabel("");
        label.setValue(kind + "");
        request.setAttribute(Constants.MIV_KIND, label);
        
        StoreDAO storeDAO = new StoreDAO();
        try {
            ArrayList arrStore = storeDAO.getStoresLevel1();     
            if (arrStore!=null) {
                label = new LabelValueBean();
                label.setValue("all");
                label.setLabel(MCUtil.getBundleString("message.all"));
                arrStore.add(0,label);
            }
            request.setAttribute(Constants.STORE_LIST, arrStore);
        } catch (Exception ex) {
            //Logger.getLogger(MsvPanelAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return true;
    }

    @Override
    protected String getXmlMessage() {
        return "mivPanel";
    }
    
    @Override
    protected String getFunction() {
        return PermissionUtil.FUNC_LIST + "";
    }

    @Override
    protected int getPermit() {
        return PermissionUtil.PER_STOCK_MIV;
    }
}
