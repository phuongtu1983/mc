/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.process.store.dmv;

import com.venus.core.util.LogUtil;
import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.MsvBean;
import com.venus.mc.bean.MsvMaterialBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.MsvDAO;
import com.venus.mc.dao.StoreDAO;
import com.venus.mc.dao.UsedMaterialImportDAO;
import com.venus.mc.process.store.msv.MsvFormBean;
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
 * @author thcao
 */
public class AddDmvAction extends SpineAction {

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
        MsvFormBean formBean = (MsvFormBean) form;
        MsvDAO msvDAO = new MsvDAO();
        MsvBean bean = null;
        boolean bNew = false;
        boolean isExist = false;
        try {
            bean = msvDAO.getMsvByNumber(formBean.getMsvNumber());
        } catch (Exception ex) {
            LogUtil.error(AddDmvAction.class + ": " + ex.getMessage());
        }

        int msvId = formBean.getMsvId();
        if (msvId == 0) {
            bNew = true;
            if (bean != null) {
                isExist = true;
            }
        } else {
            bNew = false;
            if (bean != null && bean.getMsvId() != msvId) {
                isExist = true;
            }
        }

        if (isExist) {
            ActionMessages errors = new ActionMessages();
            errors.add("numberExisted", new ActionMessage("errors.duplicate.number"));
            saveErrors(request, errors);
            return false;
        }

        bean = new MsvBean();
        bean.setMsvId(msvId);
        bean.setMsvNumber(formBean.getMsvNumber());
        bean.setCreatedEmpId(MCUtil.getMemberID(session));
        bean.setStoId(formBean.getStoId());
        bean.setDescription(formBean.getDescription());
        bean.setMrirId(formBean.getMrirId());
        bean.setDeliverer(formBean.getDeliverer());
        bean.setOrgId(formBean.getOrgId());
        bean.setProId(formBean.getProId());
        bean.setReceiveEmpId(formBean.getReceiveEmpId());
        bean.setDmvOrder(formBean.getDmvOrder());
        bean.setKind(formBean.getKind());
        bean.setType(MsvFormBean.TYPE_DMV);

        if (bNew) { //insert new
            int[] matId = formBean.getMatId();
            String[] unit = formBean.getUnit();
            String[] quantity = formBean.getQuantity();
            String[] price = formBean.getPrice();
            int[] reqDetailId = formBean.getReqDetailId();
            if (matId == null) {
                this.strErrors = "Errors";
                return false;
            }
            int len = matId.length;
            double sumAll = 0;
            StoreDAO storeDAO = new StoreDAO();
            int sto2Id = 0;
            String whichUseName = formBean.getOrgName();
            if (formBean.getProId() > 0) {
                whichUseName = formBean.getProName() + "-" + formBean.getOrgName();
            }
            try {
                sto2Id = storeDAO.insertStoreLevel2(whichUseName, formBean.getProId(), formBean.getOrgId());
            } catch (Exception ex) {
                LogUtil.error(AddDmvAction.class + ": " + ex.getMessage());
            }
            ArrayList arrMaterial = new ArrayList();
            for (int i = 0; i < len; i++) {
                MsvMaterialBean matBean = new MsvMaterialBean();
                matBean.setMatId(matId[i]);
                matBean.setStoId(sto2Id);
                matBean.setUnit(unit[i]);
                matBean.setReqDetailId(reqDetailId[i]);
                double qty = NumberUtil.parseDouble(quantity[i].replaceAll(",", ""), 0);
                matBean.setQuantity(qty);
                double pri = NumberUtil.parseDouble(price[i].replaceAll(",", ""), 0);
                matBean.setPrice(pri);
                double tot = pri * qty;
                matBean.setTotal(tot);
                sumAll += tot;
                arrMaterial.add(matBean);
            }
            bean.setTotal(sumAll);
            try {
                msvId = msvDAO.insertMsv(bean);
            } catch (Exception ex) {
                LogUtil.error(AddDmvAction.class + ": " + ex.getMessage());
            }
            if (msvId > 0) {
                int detId = 0;
                UsedMaterialImportDAO umiDAO = new UsedMaterialImportDAO();
                for (int i = 0; i < arrMaterial.size(); i++) {
                    MsvMaterialBean beanMaterial = (MsvMaterialBean) arrMaterial.get(i);
                    beanMaterial.setMsvId(msvId);
                    try {
                        detId = msvDAO.insertMsvMaterial(beanMaterial);
                    } catch (Exception ex) {
                        LogUtil.error(AddDmvAction.class + ": " + ex.getMessage());
                    }
                    try {
                        //phuongtu
                        double temp = Double.parseDouble(beanMaterial.getQuantity() + "");
                        umiDAO.insertUsedMaterialImport(msvId, beanMaterial.getMatId(), beanMaterial.getStoId(), temp, beanMaterial.getReqDetailId());
                    } catch (Exception ex) {
                        LogUtil.error(AddDmvAction.class + ": " + ex.getMessage());
                    }
                }
            }


        } else { //update
            try {
                msvDAO.updateMsv(bean);
            } catch (Exception ex) {
                LogUtil.error(AddDmvAction.class + ": " + ex.getMessage());
            }
        }
        return true;

    }
}
