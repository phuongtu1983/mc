package com.venus.mc.contract.adjustment;

import com.venus.core.util.DateUtil;
import com.venus.core.util.GenericValidator;
import com.venus.core.util.LogUtil;
import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.ContractBean;
import com.venus.mc.contract.ContractDetailFormBean;
import com.venus.mc.core.BaseAction;
import com.venus.mc.core.ExcelExport;
import com.venus.mc.dao.ContractDAO;
import com.venus.mc.dao.OrganizationDAO;
import com.venus.mc.dao.ReportDAO;
import com.venus.mc.util.MCUtil;
import com.venus.mc.util.PermissionUtil;
import com.venus.mc.workReport.ContractAdjustmentRequestReport;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author mai vinh loc
 * @version
 */
public class ContractAdjustmentRequestPrintAction extends BaseAction {

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
        boolean permission = false;
        String conId = request.getParameter("conId");
        ContractBean bean = null;
        ContractDAO contractDAO = new ContractDAO();
        try {
            bean = contractDAO.getContractAdjustment(conId);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (bean == null) {
            //return;
        }



        String templateFileName = request.getSession().getServletContext().getRealPath("/templates/Tai lieu dinh kem PLSD.xls");
        try {
            Map beans = new HashMap();
            ArrayList list = null;
            ContractDAO dao = new ContractDAO();
            double total = 0;
            double total1 = 0;
            double vat = 0;
            double vat1 = 0;
            try {
                list = dao.getContractDetailsNote(bean.getConId());
                ContractDetailFormBean contractDetail = null;
                contractDetail = new ContractDetailFormBean();
                for (int i = 0; i < list.size(); i++) {
                    contractDetail = (ContractDetailFormBean) list.get(i);
                    total += contractDetail.getTotal();
                    total1 += contractDetail.getTotal1();
                    vat += contractDetail.getTotalNotVat() * 0.1;
                    vat1 += contractDetail.getTotalNotVat1() * 0.1;
                }
                bean.setTotalVAT(total);
                bean.setTotalVAT1(total1);
                bean.setSumVAT(vat);
                bean.setSumVAT1(vat1);
                bean.setSumVAT0(vat1 - vat);
                bean.setMoney(total1 - total);
            } catch (Exception ex) {
            }
            if (list == null) {
                list = new ArrayList();
            }
            Date date = DateUtil.transformerStringEnDate(DateUtil.today("dd/MM/yyyy"), "/");

            beans.put("mcrp_name1", bean.getVendorName1());
            beans.put("mcrp_license1", bean.getLicense1());
            beans.put("mcrp_field1", bean.getField1());
            beans.put("mcrp_deliveryTime1", bean.getDelivery1());
            beans.put("mcrp_name2", bean.getVendorName2());
            beans.put("mcrp_license2", bean.getLicense2());
            beans.put("mcrp_field2", bean.getField2());
            beans.put("mcrp_deliveryTime2", bean.getDelivery2());
            beans.put("mcrp_number1", bean.getContractNumber1());
            beans.put("mcrp_number2", bean.getContractNumber2());
            beans.put("mcrp_currency", bean.getCurrency());
            beans.put("vattu", list);


            if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_LIST, PermissionUtil.PER_LIBRARY_MATERIAL_PRICE)) {
                OrganizationDAO orgDAO = new OrganizationDAO();
                int orgId = MCUtil.getOrganizationID(request.getSession());
                String orgs = orgDAO.getnestedChildOfOrg(orgId + "");
                orgs += "," + orgDAO.getnestedParentOfOrg(orgId + "");
                orgs += "," + orgId;
                contractDAO.setRequestOrg(orgs);
                contractDAO.setRequestEmp(MCUtil.getMemberID(request.getSession()));
                if (contractDAO.isPermissionOnContractInfo(bean.getConId()) > 0) {
                    permission = true;
                    beans.put("mcrp_money", NumberUtil.formatMoneyDefault(bean.getMoney(), bean.getCurrency()));
                    beans.put("mcrp_sumVat0", NumberUtil.formatMoneyDefault(bean.getSumVAT0(), bean.getCurrency()));
                    beans.put("mcrp_sumVat", NumberUtil.formatMoneyDefault(bean.getSumVAT(), bean.getCurrency()));
                    beans.put("mcrp_sumVat1", NumberUtil.formatMoneyDefault(bean.getSumVAT1(), bean.getCurrency()));
                    beans.put("mcrp_totalVat", NumberUtil.formatMoneyDefault(bean.getTotalVAT(), bean.getCurrency()));
                    beans.put("mcrp_totalVat1", NumberUtil.formatMoneyDefault(bean.getTotalVAT1(), bean.getCurrency()));
                    beans.put("mcrp_moneyText", NumberUtil.textMoney(bean.getMoney(), bean.getCurrency()));
                } else {
                    beans.put("mcrp_money", "xxx");
                    beans.put("mcrp_sumVat0", "xxx");
                    beans.put("mcrp_sumVat", "xxx");
                    beans.put("mcrp_sumVat1", "xxx");
                    beans.put("mcrp_totalVat", "xxx");
                    beans.put("mcrp_totalVat1", "xxx");
                    beans.put("mcrp_moneyText", "xxx");
                }
            } else {
                beans.put("mcrp_money", "xxx");
                beans.put("mcrp_sumVat0", "xxx");
                beans.put("mcrp_sumVat", "xxx");
                beans.put("mcrp_sumVat1", "xxx");
                beans.put("mcrp_totalVat", "xxx");
                beans.put("mcrp_totalVat1", "xxx");
                beans.put("mcrp_moneyText", "xxx");
            }

            ExcelExport exporter = new ExcelExport();
            exporter.setBeans(beans);
            long milis = System.currentTimeMillis();
            exporter.export(request, response, templateFileName, "Tai lieu dinh kem PLSD.xls");
            milis = System.currentTimeMillis() - milis;
            System.out.println("Tai lieu dinh kem PLSD.xls : " + "time : " + (int) ((milis / 1000) % 60) + " phut " + (int) ((milis / 1000) / 60) + " mili giay");
        } catch (Exception ex) {
            LogUtil.error("FAILED:MCProjectStoreReportPrint:print-" + ex.getMessage());
            ex.printStackTrace();
        }

        return true;
    }

    @Override
    protected boolean isReturnStream() {
        return true;
    }
}
