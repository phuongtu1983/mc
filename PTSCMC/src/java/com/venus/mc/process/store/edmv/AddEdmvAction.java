/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.process.store.edmv;

import com.venus.mc.process.store.dmv.*;
import com.venus.mc.process.store.mrv.*;
import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.DmvBean;
import com.venus.mc.bean.DmvMaterialBean;
import com.venus.mc.bean.EdmvBean;
import com.venus.mc.bean.EdmvMaterialBean;
import com.venus.mc.bean.MaterialStoreRequestBean;
import com.venus.mc.bean.MrvBean;
import com.venus.mc.bean.MrvMaterialBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.DmvDAO;
import com.venus.mc.dao.EdmvDAO;
import com.venus.mc.dao.EmrirDAO;
import com.venus.mc.dao.MaterialStoreRequestDAO;
import com.venus.mc.dao.MrirDAO;
import com.venus.mc.dao.MrvDAO;
import com.venus.mc.dao.UsedMaterialImportDAO;
import com.venus.mc.database.DBUtil;
import com.venus.mc.util.MCUtil;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

/**
 *
 * @author thcao
 */
public class AddEdmvAction extends SpineAction {

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
        HttpSession session = request.getSession();
        EdmvFormBean formBean = (EdmvFormBean) form;
        EdmvDAO edmvDAO = new EdmvDAO();
        int edmvId = NumberUtil.parseInt(MCUtil.getParameter("edmvId", request), 0);
        if (edmvId > 0) { //update   
            EdmvBean bean = new EdmvBean();
            bean.setEdmvId(edmvId);
            bean.setEdmvNumber(formBean.getEdmvNumber());
            bean.setCreatedEmpId(MCUtil.getMemberID(session));
            bean.setStoId(formBean.getStoId());
            bean.setDescription(formBean.getDescription());
            bean.setEdnId(formBean.getEdnId());
            bean.setDeliverer(formBean.getDeliverer());

            bean.setOrgId(formBean.getOrgId());
            bean.setProId(formBean.getProId());
            bean.setReceiveEmpId(formBean.getReceiveEmpId());
            bean.setDmvOrder(formBean.getDmvOrder());
            bean.setKind(formBean.getKind());
            try {
                edmvDAO.updateEdmv(bean);
            } catch (Exception ex) {

            }
        } else { //add new
            //kiem tra ma so da ton tai hay chua
            if (DBUtil.checkFieldExisted("edmv", "edmv_number", formBean.getEdmvNumber())) {
                ActionMessages errors = new ActionMessages();
                errors.add("numberExisted", new ActionMessage("errors.duplicate.number"));
                saveErrors(request, errors);
                return false;
            }
            int[] ematId = formBean.getEmatId();
            String[] unit = formBean.getUnit();
            String[] quantity = formBean.getQuantity();
            String[] price = formBean.getPrice();

            if (ematId == null) {
                this.strErrors = "Errors";
                return false;
            }
            int len = ematId.length;
            double sumAll = 0;
            ArrayList arrMaterial = new ArrayList();
            for (int i = 0; i < len; i++) {
                EdmvMaterialBean bean = new EdmvMaterialBean();
                bean.setEmatId(ematId[i]);
                bean.setStoId(formBean.getStoId());
                bean.setUnit(unit[i]);
                double qty = NumberUtil.parseDouble(quantity[i].replaceAll(",", ""), 0);
                bean.setQuantity(qty);
                double pri = NumberUtil.parseDouble(price[i].replaceAll(",", ""), 0);
                bean.setPrice(pri);
                double tot = pri * qty;
                bean.setTotal(tot);
                sumAll += tot;
                arrMaterial.add(bean);
            }
            EdmvBean bean = new EdmvBean();
            bean.setEdmvNumber(formBean.getEdmvNumber());
            bean.setCreatedEmpId(MCUtil.getMemberID(session));
            bean.setStoId(formBean.getStoId());
            bean.setDescription(formBean.getDescription());
            bean.setEdnId(formBean.getEdnId());
            bean.setDeliverer(formBean.getDeliverer());
            bean.setTotal(sumAll);
            bean.setOrgId(formBean.getOrgId());
            bean.setProId(formBean.getProId());
            bean.setReceiveEmpId(formBean.getReceiveEmpId());
            bean.setDmvOrder(formBean.getDmvOrder());
            bean.setKind(formBean.getKind());
            try {
                edmvId = edmvDAO.insertEdmv(bean);
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            if (edmvId > 0) {
                int detId = 0;
                for (int i = 0; i < arrMaterial.size(); i++) {
                    EdmvMaterialBean beanMaterial = (EdmvMaterialBean) arrMaterial.get(i);
                    beanMaterial.setEdmvId(edmvId);
                    try {
                        detId = edmvDAO.insertEdmvMaterial(beanMaterial);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
            EmrirDAO emrirDAO = new EmrirDAO();
            try {
                emrirDAO.updateEdeliveryNotice(formBean.getEdnId(), 1);
            } catch (Exception ex) {
            }
        }
        return true;

    }
}
