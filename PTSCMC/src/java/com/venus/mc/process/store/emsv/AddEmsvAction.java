/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.process.store.emsv;

import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.EmaterialStoreBean;
import com.venus.mc.bean.EmsvBean;
import com.venus.mc.bean.EmsvMaterialBean;

import com.venus.mc.bean.MsvMaterialBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.EmaterialStoreDAO;

import com.venus.mc.dao.EmsvDAO;
import com.venus.mc.database.DBUtil;
import com.venus.mc.util.MCUtil;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

/**
 *
 * @author thcao
 */
public class AddEmsvAction extends SpineAction {

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
        EmsvFormBean formBean = (EmsvFormBean) form;
        EmsvDAO emsvDAO = new EmsvDAO();
        int emsvId = NumberUtil.parseInt(MCUtil.getParameter("emsvId", request), 0);
        if (emsvId > 0) { //update   
            EmsvBean bean = new EmsvBean();
            bean.setEmsvNumber(formBean.getEmsvNumber());
            bean.setEmrirId(formBean.getEmrirId());
            bean.setCreatedEmpId(MCUtil.getMemberID(session));
            bean.setStoId(formBean.getStoId());
            bean.setDeliverer(formBean.getDeliverer());
            try {
                emsvDAO.updateEmsv(bean);
            } catch (Exception ex) {

            }
        } else { //add new
            //kiem tra ma so da ton tai hay chua
            if (DBUtil.checkFieldExisted("emsv", "emsv_number", formBean.getEmsvNumber())) {
                ActionMessages errors = new ActionMessages();
                errors.add("numberExisted", new ActionMessage("errors.duplicate.number"));
                saveErrors(request, errors);
                return false;
            }
            int[] ematId = formBean.getEmatId();
            String[] unit = formBean.getUnit();
            String[] quantity = formBean.getQuantity();
            String[] price = formBean.getPrice();
            int len = ematId.length;
            double sumAll = 0;
            ArrayList arrMaterial = new ArrayList();
            for (int i = 0; i < len; i++) {
                EmsvMaterialBean bean = new EmsvMaterialBean();
                bean.setEmatId(ematId[i]);
                bean.setUnit(unit[i]);
                double qty = NumberUtil.parseDouble(quantity[i].replaceAll(",", ""), 0);
                bean.setQuantity(qty);
                double pri = NumberUtil.parseDouble(price[i].replaceAll(",", ""), 0);
                bean.setPrice(pri);
                bean.setStoId(formBean.getStoId());
                double tot = pri * qty;
                bean.setTotal(tot);
                sumAll += tot;
                arrMaterial.add(bean);
            }
            EmsvBean bean = new EmsvBean();
            bean.setEmsvNumber(formBean.getEmsvNumber());
            bean.setEmrirId(formBean.getEmrirId());
            bean.setCreatedEmpId(MCUtil.getMemberID(session));
            bean.setStoId(formBean.getStoId());
            bean.setTotal(sumAll);
            bean.setDeliverer(formBean.getDeliverer());
            //bean.setTotalMoney(msvId)

            try {
                emsvId = emsvDAO.insertEmsv(bean);
            } catch (Exception ex) {
            }
            if (emsvId > 0) {
                int detId = 0;
                EmaterialStoreDAO msDAO = new EmaterialStoreDAO();
                EmaterialStoreBean msBean = null;
                EmsvMaterialBean beanMaterial = null;
                for (int i = 0; i < arrMaterial.size(); i++) {
                    beanMaterial = (EmsvMaterialBean) arrMaterial.get(i);
                    beanMaterial.setEmsvId(emsvId);
                    try {
                        detId = emsvDAO.insertEmsvMaterial(beanMaterial);
                    } catch (Exception ex) {
                    }
                    //cap nhat so luong ton kho   
                    try {
                        msBean = msDAO.getEmaterialStore(beanMaterial.getEmatId(), beanMaterial.getStoId());
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    if (msBean == null) {
                        try {
                            msDAO.insertEmaterialStore(beanMaterial.getEmatId(), beanMaterial.getStoId(), beanMaterial.getPrice(), beanMaterial.getQuantity());
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    } else {
                        try {
                            //tang so luong trong kho
                            msBean.setActualQuantity(msBean.getActualQuantity() + beanMaterial.getQuantity());
                            msBean.setAvailableQuantity(msBean.getAvailableQuantity() + beanMaterial.getQuantity());
                            msDAO.updateEmaterialStore(msBean);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                }
                if (formBean.getEmrirId() > 0) {
                    try {
                        emsvDAO.updateEmrirStatus(formBean.getEmrirId(), 1);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }

            }
        }
        return true;

    }
}
