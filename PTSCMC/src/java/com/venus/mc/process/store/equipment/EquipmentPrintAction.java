/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.process.store.equipment;

import com.venus.core.util.LogUtil;
import com.venus.mc.bean.RepairMaterialBean;
import com.venus.mc.core.BaseAction;
import com.venus.mc.core.ExcelExport;
import com.venus.mc.dao.EquipmentDAO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author mai vinh loc
 */
public class EquipmentPrintAction extends BaseAction {

    private String templateFile = "DanhmucVTTB.xls";

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
        String rpId = request.getParameter("rpId");
        if (rpId.length() > 0) {
            ArrayList arrRepairMaterial = null;
            Map beans = new HashMap();
            try {
                EquipmentDAO equipmentDAO = new EquipmentDAO();
                arrRepairMaterial = equipmentDAO.getRepairMaterial(rpId);
            } catch (Exception ex) {
                LogUtil.error(this.getClass(), ex.getMessage());
            }
            if (arrRepairMaterial == null) {
                arrRepairMaterial = new ArrayList();
            }
            for (int i = 0; i < arrRepairMaterial.size(); i++) {
                RepairMaterialBean matBean = (RepairMaterialBean) arrRepairMaterial.get(i);
                matBean.setDetId((i + 1));
            }
            beans.put("pc", arrRepairMaterial);
            String templateFileName = request.getSession().getServletContext().getRealPath("/templates/" + templateFile);
            ExcelExport exporter = new ExcelExport();
            exporter.setBeans(beans);
            try {
                long milis = System.currentTimeMillis();
                exporter.export(request, response, templateFileName, templateFile);
                milis = System.currentTimeMillis() - milis;
                System.out.println("DanhmucVTTB.xls : " + "time : " + (int) ((milis / 1000) % 60) + " phut " + (int) ((milis / 1000) / 60) + " mili giay");
            } catch (Exception ex) {
                LogUtil.error(this.getClass(), ex.getMessage());
            }
        }
        return true;
    }

    @Override
    protected boolean isReturnStream() {
        return true;
    }
}
