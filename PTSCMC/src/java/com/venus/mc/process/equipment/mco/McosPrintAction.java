/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.process.equipment.mco;

import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.ContractBean;
import com.venus.mc.bean.MrirBean;
import com.venus.mc.bean.VendorBean;
import com.venus.mc.core.BaseAction;
import com.venus.mc.core.ExcelExport;
import com.venus.mc.dao.ContractDAO;
import com.venus.mc.dao.MrirDAO;
import com.venus.mc.dao.RequestDAO;
import com.venus.mc.dao.VendorDAO;
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
public class McosPrintAction extends BaseAction {

    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public boolean doAction(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        int mrirId = NumberUtil.parseInt(request.getParameter("mrirId"), 0);
        ArrayList mrirList = null;
        MrirDAO mrirDAO = new MrirDAO();
        MrirBean mrirBean = null;

        ContractDAO conDAO = new ContractDAO();
        VendorDAO venDAO = new VendorDAO();
        RequestDAO reqDAO = new RequestDAO();

        String templateFileName = request.getSession().getServletContext().getRealPath("/templates/BM.02.02.KH_BBKiemTraTiepNhanVTTBTL.xls");
        Map beans = new HashMap();

        try {
            mrirBean = mrirDAO.getMrir(mrirId);

//            RequestBean reqBean = reqDAO.getRequest(mrirBean.getReqId());
//            beans.put("mcrp_reqNumber", reqBean.getRequestNumber());

            if (mrirBean.getConId() > 0) {
                ContractBean conBean = conDAO.getContractMrir(mrirBean.getConId());
                beans.put("mcrp_conNumber", conBean.getContractNumber());
                VendorBean venBean = venDAO.getVendor(conBean.getVenId());
                beans.put("mcrp_venName", venBean.getName());
            } else {
                beans.put("mcrp_conNumber", "");
                beans.put("mcrp_venName", "");
            }

//            if (reqBean.getProId() > 0) {
//                OrganizationDAO orgDAO = new OrganizationDAO();
//                OrganizationBean orgBean = orgDAO.getOrganization(reqBean.getProId());
//                beans.put("mcrp_proName", orgBean.getName());
//            } else {
//                beans.put("mcrp_proName", "");
//            }

            beans.put("mcrp_blNo", mrirBean.getBlNo());

            beans.put("mcrp_invoiceNo", mrirBean.getInvoiceNo());

            beans.put("mcrp_plNo", mrirBean.getPlNo());

            beans.put("mcrp_mrirNumber", mrirBean.getMrirNumber());

            beans.put("mcrp_createdDate", mrirBean.getCreatedDate());

            mrirList = mrirDAO.getMrirOsDs(mrirId);
            if (mrirList == null) {
                mrirList = new ArrayList();
            }

            beans.put("mrir", mrirList);
            ExcelExport exporter = new ExcelExport();
            exporter.setBeans(beans);
            long milis = System.currentTimeMillis();
            exporter.export(request, response, templateFileName);
            milis = System.currentTimeMillis() - milis;
            System.out.println("BM.02.02.KH_BBKiemTraTiepNhanVTTBTL.xls : " + "time : " + (int) ((milis / 1000) % 60) + " phut " + (int) ((milis / 1000) / 60) + " mili giay");
        } catch (Exception ex) {
        }
        return true;
    }

    @Override
    protected boolean isReturnStream() {
        return true;
    }
}
