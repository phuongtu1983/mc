/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.process.store.emrir;

import com.venus.core.util.LogUtil;
import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.EmrirBean;
import com.venus.mc.core.BaseAction;
import com.venus.mc.core.ExcelExport;
import com.venus.mc.dao.EmrirDAO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author kngo
 */
public class EmrirPrintAction extends BaseAction {

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
        int emrirId = NumberUtil.parseInt(request.getParameter("emrirId"), 0);
        if (emrirId > 0) {
            ArrayList emrirList = null;
            EmrirDAO emrirDAO = new EmrirDAO();
            EmrirBean emrirBean = null;

            String templateFileName = request.getSession().getServletContext().getRealPath("/templates/BM.02.02.KH_BBKiemTraTiepNhanVTTBTL_HHDonViNgoai.xls");
            Map beans = new HashMap();
            try {
                emrirBean = emrirDAO.getEmrir(emrirId);
            } catch (Exception ex) {
                LogUtil.error(EmrirPrintAction.class.getName() + ": " + ex.getMessage());
            }
            beans.put("mcrp_conNumber", emrirBean.getConNumber());
            beans.put("mcrp_proName", emrirBean.getProName());
            beans.put("mcrp_mrirNumber", emrirBean.getEmrirNumber());
            beans.put("mcrp_createdDate", emrirBean.getCreatedDate());
            beans.put("mcrp_packingListNo", emrirBean.getPackingListNo());
            beans.put("mcrp_invoiceNo", emrirBean.getInvoiceNo());
            try {
                emrirList = emrirDAO.getEmrirEosDs(emrirId);
            } catch (Exception ex) {
                LogUtil.error(EmrirPrintAction.class.getName() + ": " + ex.getMessage());
            }
            if (emrirList == null) {
                emrirList = new ArrayList();
            }
            beans.put("mrir", emrirList);
            ExcelExport exporter = new ExcelExport();
            exporter.setBeans(beans);
            try {
                long milis = System.currentTimeMillis();
                exporter.export(request, response, templateFileName,"BM.02.02.KH_BBKiemTraTiepNhanVTTBTL_HHDonViNgoai.xls");
                milis = System.currentTimeMillis() - milis;
                System.out.println("BM.02.02.KH_BBKiemTraTiepNhanVTTBTL_HHDonViNgoai.xls : " + "time : " + (int) ((milis / 1000) % 60) + " phut " + (int) ((milis / 1000) / 60) + " mili giay");
            } catch (Exception ex) {
                LogUtil.error(EmrirPrintAction.class.getName() + ": " + ex.getMessage());
            }
            return true;
        }
        return false;
    }

    @Override
    protected boolean isReturnStream() {
        return true;
    }
}
