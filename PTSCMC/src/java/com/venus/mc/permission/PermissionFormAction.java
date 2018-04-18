/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.permission;

import com.venus.core.util.LogUtil;
import com.venus.mc.bean.PermissionBean;
import com.venus.mc.bean.PermissionDetailBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.EmployeeDAO;
import com.venus.mc.dao.OrganizationDAO;
import com.venus.mc.dao.PermissionDAO;
import com.venus.mc.util.Constants;
import com.venus.mc.util.MCUtil;
import com.venus.mc.util.PermissionUtil;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author phuongtu
 */
public class PermissionFormAction extends SpineAction {

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

        PermissionFormBean formBean = null;
        String perId = request.getParameter("perId");
        PermissionDAO permissionDAO = new PermissionDAO();
        if (!GenericValidator.isBlankOrNull(perId)) {
            try {
                PermissionBean bean = permissionDAO.getPermission(Integer.parseInt(perId));
                if (bean != null) {
                    formBean = new PermissionFormBean(bean);
                }
            } catch (Exception ex) {
            }
        }
        if (formBean == null) {
            formBean = new PermissionFormBean();
        }
        request.setAttribute(Constants.PERMISSION, formBean);

        ArrayList arrDet = null;
        try {
            arrDet = permissionDAO.getPermissionDetails(formBean.getPerId());
            PermissionDetailBean detBean = null;
            for (int i = 0; i < arrDet.size(); i++) {
                detBean = (PermissionDetailBean) arrDet.get(i);
                if (detBean.getFunction().equals(PermissionUtil.FUNC_LIST)) {
                    formBean.setFuncList(detBean.getPermit().split(","));
                }
                if (detBean.getFunction().equals(PermissionUtil.FUNC_ADD)) {
                    formBean.setFuncAdd(detBean.getPermit().split(","));
                }
                if (detBean.getFunction().equals(PermissionUtil.FUNC_DELETE)) {
                    formBean.setFuncDelete(detBean.getPermit().split(","));
                }
                if (detBean.getFunction().equals(PermissionUtil.FUNC_EDIT)) {
                    formBean.setFuncEdit(detBean.getPermit().split(","));
                }
                if (detBean.getFunction().equals(PermissionUtil.FUNC_VIEW)) {
                    formBean.setFuncView(detBean.getPermit().split(","));
                }
            }
        } catch (Exception ex) {
        }

        ArrayList arrOrg = new ArrayList();
        try {
            OrganizationDAO orgDAO = new OrganizationDAO();
            arrOrg = orgDAO.getOrgByKind(0);
        } catch (Exception ex) {
        }
        if (arrOrg == null) {
            arrOrg = new ArrayList();
        }
        request.setAttribute(Constants.ORG_LIST, arrOrg);

        ArrayList arrEmp = new ArrayList();
        try {
            EmployeeDAO empDAO = new EmployeeDAO();
            arrEmp = empDAO.getEmployees();
        } catch (Exception ex) {
        }
        if (arrEmp == null) {
            arrEmp = new ArrayList();
        }
        request.setAttribute(Constants.EMPLOYEE_LIST, arrEmp);

        ArrayList arrPerOrg = null;
        ArrayList arrPerEmp = null;
        if (formBean.getPerId() != 0) {
            try {
                arrPerEmp = permissionDAO.getPermissionEmp(formBean.getPerId());
            } catch (Exception ex) {
                LogUtil.error("PermissionFormAction: " + ex.getMessage());
            }
            try {
                arrPerOrg = permissionDAO.getPermissionOrg(formBean.getPerId());
            } catch (Exception ex) {
                LogUtil.error("PermissionFormAction: " + ex.getMessage());
            }
        }

        if (arrPerOrg == null) {
            arrPerOrg = new ArrayList();
        }
        request.setAttribute(Constants.PERMISSION_ORG_LIST, arrPerOrg);

        if (arrPerEmp == null) {
            arrPerEmp = new ArrayList();
        }
        request.setAttribute(Constants.PERMISSION_EMP_LIST, arrPerEmp);

        ArrayList arrFun = new ArrayList();
        PermissionViewBean perBean;

        perBean = new PermissionViewBean();
        perBean.setCounter("1");
        perBean.setLevel(1);
        perBean.setSharedId(1);
        perBean.setName(MCUtil.getBundleString("message.permission.func.system"));
        perBean.setValue(PermissionUtil.PER_SYSTEM );
        arrFun.add(perBean);

        perBean = new PermissionViewBean();
        perBean.setCounter("2");
        perBean.setLevel(0);
        perBean.setSharedId(2);
        perBean.setName(MCUtil.getBundleString("message.vendoradd.title"));
        perBean.setValue(PermissionUtil.PER_LIBRARY_VENDOR );
        arrFun.add(perBean);
        
        perBean = new PermissionViewBean();
        perBean.setCounter("2.1");
        perBean.setLevel(2);
        perBean.setSharedId(2);
        perBean.setName(MCUtil.getBundleString("message.permission.func.library.vendor"));
        perBean.setValue(PermissionUtil.PER_LIBRARY_VENDOR );
        arrFun.add(perBean);

        perBean = new PermissionViewBean();
        perBean.setCounter("2.2");
        perBean.setLevel(2);
        perBean.setSharedId(2);
        perBean.setName(MCUtil.getBundleString("message.permission.func.library.vendor.evaluate"));
        perBean.setValue(PermissionUtil.PER_LIBRARY_VENDOR_EVAL );
        arrFun.add(perBean);

        perBean = new PermissionViewBean();
        perBean.setCounter("3");
        perBean.setLevel(0);
        perBean.setSharedId(3);
        perBean.setName(MCUtil.getBundleString("message.u_material"));
        perBean.setValue(PermissionUtil.PER_LIBRARY_MATERIAL_COMPANY );
        arrFun.add(perBean);
        
        perBean = new PermissionViewBean();
        perBean.setCounter("3.1");
        perBean.setLevel(2);
        perBean.setSharedId(3);
        perBean.setName(MCUtil.getBundleString("message.permission.func.library.material.company"));
        perBean.setValue(PermissionUtil.PER_LIBRARY_MATERIAL_COMPANY );
        arrFun.add(perBean);

        perBean = new PermissionViewBean();
        perBean.setCounter("3.2");
        perBean.setLevel(2);
        perBean.setSharedId(3);
        perBean.setName(MCUtil.getBundleString("message.permission.func.library.material.project"));
        perBean.setValue(PermissionUtil.PER_LIBRARY_MATERIAL_OUT );
        arrFun.add(perBean);

        perBean = new PermissionViewBean();
        perBean.setCounter("3.3");
        perBean.setLevel(2);
        perBean.setSharedId(3);
        perBean.setName(MCUtil.getBundleString("message.permission.func.library.material.catalog"));
        perBean.setValue(PermissionUtil.PER_LIBRARY_MATERIAL_CATALOG );
        arrFun.add(perBean);

        perBean = new PermissionViewBean();
        perBean.setCounter("3.4");
        perBean.setLevel(2);
        perBean.setSharedId(3);
        perBean.setName(MCUtil.getBundleString("message.permission.func.library.material.unit"));
        perBean.setValue(PermissionUtil.PER_LIBRARY_MATERIAL_UNIT );
        arrFun.add(perBean);

        perBean = new PermissionViewBean();
        perBean.setCounter("3.5");
        perBean.setLevel(2);
        perBean.setSharedId(3);
        perBean.setName(MCUtil.getBundleString("message.permission.func.library.material.origin"));
        perBean.setValue(PermissionUtil.PER_LIBRARY_MATERIAL_ORIGIN );
        arrFun.add(perBean);

        perBean = new PermissionViewBean();
        perBean.setCounter("3.6");
        perBean.setLevel(2);
        perBean.setSharedId(3);
        perBean.setName(MCUtil.getBundleString("message.permission.func.library.material.price"));
        perBean.setValue(PermissionUtil.PER_LIBRARY_MATERIAL_PRICE );
        arrFun.add(perBean);
      
        perBean = new PermissionViewBean();
        perBean.setCounter("4");
        perBean.setLevel(1);
        perBean.setSharedId(4);
        perBean.setName(MCUtil.getBundleString("message.permission.func.library.store"));
        perBean.setValue(PermissionUtil.PER_LIBRARY_STORE );
        arrFun.add(perBean);

        perBean = new PermissionViewBean();
        perBean.setCounter("5");
        perBean.setLevel(1);
        perBean.setSharedId(5);
        perBean.setName(MCUtil.getBundleString("message.permission.func.library.project"));
        perBean.setValue(PermissionUtil.PER_LIBRARY_PROJECT );
        arrFun.add(perBean);
        
        perBean = new PermissionViewBean();
        perBean.setCounter("6");
        perBean.setLevel(1);
        perBean.setSharedId(6);
        perBean.setName(MCUtil.getBundleString("message.permission.func.library.certificate"));
        perBean.setValue(PermissionUtil.PER_LIBRARY_CERTIFICATE );
        arrFun.add(perBean);
        
        perBean = new PermissionViewBean();
        perBean.setCounter("7");
        perBean.setLevel(1);
        perBean.setSharedId(7);
        perBean.setName(MCUtil.getBundleString("message.permission.func.library.incoterm"));
        perBean.setValue(PermissionUtil.PER_LIBRARY_INCOTERM );
        arrFun.add(perBean);

        perBean = new PermissionViewBean();
        perBean.setCounter("8");
        perBean.setLevel(0);
        perBean.setSharedId(8);
        perBean.setName(MCUtil.getBundleString("message.requestadd.title"));
        perBean.setValue(PermissionUtil.PER_PURCHASING_REQUEST);
        arrFun.add(perBean);
        
        perBean = new PermissionViewBean();
        perBean.setCounter("8.1");
        perBean.setLevel(2);
        perBean.setSharedId(8);
        perBean.setName(MCUtil.getBundleString("message.permission.func.purchasing.request"));
        perBean.setValue(PermissionUtil.PER_PURCHASING_REQUEST);
        arrFun.add(perBean);

        perBean = new PermissionViewBean();
        perBean.setCounter("8.2");
        perBean.setLevel(2);
        perBean.setSharedId(8);
        perBean.setName(MCUtil.getBundleString("message.permission.func.purchasing.request.material"));
        perBean.setValue(PermissionUtil.PER_PURCHASING_REQUEST_MATERIAL);
        arrFun.add(perBean);

        perBean = new PermissionViewBean();
        perBean.setCounter("8.3");
        perBean.setLevel(2);
        perBean.setSharedId(8);
        perBean.setName(MCUtil.getBundleString("message.permission.func.purchasing.request.tracing"));
        perBean.setValue(PermissionUtil.PER_PURCHASING_REQUEST_TRACING);
        arrFun.add(perBean);

        perBean = new PermissionViewBean();
        perBean.setCounter("8.4");
        perBean.setLevel(2);
        perBean.setSharedId(8);
        perBean.setName(MCUtil.getBundleString("message.permission.func.purchasing.request.gather"));
        perBean.setValue(PermissionUtil.PER_PURCHASING_REQUEST_GATHER);
        arrFun.add(perBean);
        
        perBean = new PermissionViewBean();
        perBean.setCounter("8.5");
        perBean.setLevel(2);
        perBean.setSharedId(8);
        perBean.setName(MCUtil.getBundleString("message.permission.func.purchasing.request.view"));
        perBean.setValue(PermissionUtil.PER_PURCHASING_REQUEST_VIEW);
        arrFun.add(perBean);
        
        perBean = new PermissionViewBean();
        perBean.setCounter("8.6");
        perBean.setLevel(2);
        perBean.setSharedId(8);
        perBean.setName(MCUtil.getBundleString("message.permission.func.purchasing.request.materialnotcode"));
        perBean.setValue(PermissionUtil.PER_PURCHASING_REQUEST_MATERIALNOTCODE);
        arrFun.add(perBean);
        
        perBean = new PermissionViewBean();
        perBean.setCounter("8.7");
        perBean.setLevel(2);
        perBean.setSharedId(8);
        perBean.setName(MCUtil.getBundleString("message.permission.func.purchasing.request.check"));
        perBean.setValue(PermissionUtil.PER_PURCHASING_REQUEST_CHECK);
        arrFun.add(perBean);
        
        perBean = new PermissionViewBean();
        perBean.setCounter("8.8");
        perBean.setLevel(2);
        perBean.setSharedId(8);
        perBean.setName(MCUtil.getBundleString("message.permission.func.purchasing.request.store"));
        perBean.setValue(PermissionUtil.PER_PURCHASING_REQUEST_STORE);
        arrFun.add(perBean);
        
        perBean = new PermissionViewBean();
        perBean.setCounter("8.9");
        perBean.setLevel(2);
        perBean.setSharedId(8);
        perBean.setName(MCUtil.getBundleString("message.permission.func.purchasing.request.plandep"));
        perBean.setValue(PermissionUtil.PER_PURCHASING_REQUEST_PLANDEP);
        arrFun.add(perBean);
        
        perBean = new PermissionViewBean();
        perBean.setCounter("8.10");
        perBean.setLevel(2);
        perBean.setSharedId(8);
        perBean.setName(MCUtil.getBundleString("message.permission.func.purchasing.request.manager"));
        perBean.setValue(PermissionUtil.PER_PURCHASING_REQUEST_MANAGER);
        arrFun.add(perBean);
        
         perBean = new PermissionViewBean();
        perBean.setCounter("8.11");
        perBean.setLevel(2);
        perBean.setSharedId(8);
        perBean.setName(MCUtil.getBundleString("message.permission.func.purchasing.request.shortcut"));
        perBean.setValue(PermissionUtil.PER_PURCHASING_REQUEST_SHORTCUT);
        arrFun.add(perBean);
        
        perBean = new PermissionViewBean();
        perBean.setCounter("9");
        perBean.setLevel(1);
        perBean.setSharedId(9);
        perBean.setName(MCUtil.getBundleString("message.permission.func.purchasing.intermemo"));
        perBean.setValue(PermissionUtil.PER_PURCHASING_INTERMEMO);
        arrFun.add(perBean);

        perBean = new PermissionViewBean();
        perBean.setCounter("10");
        perBean.setLevel(1);
        perBean.setSharedId(10);
        perBean.setName(MCUtil.getBundleString("message.permission.func.purchasing.tenderplan"));
        perBean.setValue(PermissionUtil.PER_PURCHASING_TENDERPLAN);
        arrFun.add(perBean);

        perBean = new PermissionViewBean();
        perBean.setCounter("11");
        perBean.setLevel(0);
        perBean.setSharedId(11);
        perBean.setName(MCUtil.getBundleString("message.contractdetail.title"));
        perBean.setValue(PermissionUtil.PER_PURCHASING_CONTRACT);
        arrFun.add(perBean);
        
        perBean = new PermissionViewBean();
        perBean.setCounter("11.1");
        perBean.setLevel(2);
        perBean.setSharedId(11);
        perBean.setName(MCUtil.getBundleString("message.permission.func.purchasing.contract"));
        perBean.setValue(PermissionUtil.PER_PURCHASING_CONTRACT);
        arrFun.add(perBean);

        perBean = new PermissionViewBean();
        perBean.setCounter("11.2");
        perBean.setLevel(2);
        perBean.setSharedId(11);
        perBean.setName(MCUtil.getBundleString("message.permission.func.purchasing.principle"));
        perBean.setValue(PermissionUtil.PER_PURCHASING_PRINCIPLE);
        arrFun.add(perBean);

        perBean = new PermissionViewBean();
        perBean.setCounter("11.3");
        perBean.setLevel(2);
        perBean.setSharedId(11);
        perBean.setName(MCUtil.getBundleString("message.permission.func.purchasing.contractFollow"));
        perBean.setValue(PermissionUtil.PER_PURCHASING_CONTRACT_FOLLOW);
        arrFun.add(perBean);

        perBean = new PermissionViewBean();
        perBean.setCounter("11.4");
        perBean.setLevel(2);
        perBean.setSharedId(11);
        perBean.setName(MCUtil.getBundleString("message.permission.func.purchasing.contractExecute"));
        perBean.setValue(PermissionUtil.PER_PURCHASING_CONTRACT_EXECUTE);
        arrFun.add(perBean);

        perBean = new PermissionViewBean();
        perBean.setCounter("12");
        perBean.setLevel(1);
        perBean.setSharedId(12);
        perBean.setName(MCUtil.getBundleString("message.permission.func.purchasing.order"));
        perBean.setValue(PermissionUtil.PER_PURCHASING_ORDER);
        arrFun.add(perBean);

        perBean = new PermissionViewBean();
        perBean.setCounter("13");
        perBean.setLevel(1);
        perBean.setSharedId(13);
        perBean.setName(MCUtil.getBundleString("message.permission.func.purchasing.deliveryrequest"));
        perBean.setValue(PermissionUtil.PER_PURCHASING_DELIVERYREQUEST);
        arrFun.add(perBean);

        perBean = new PermissionViewBean();
        perBean.setCounter("14");
        perBean.setLevel(0);
        perBean.setSharedId(14);
        perBean.setName(MCUtil.getBundleString("message.u_deliverynotice"));
        perBean.setValue(PermissionUtil.PER_PURCHASING_DELIVERYNOTICE);
        arrFun.add(perBean);
        
        perBean = new PermissionViewBean();
        perBean.setCounter("14.1");
        perBean.setLevel(2);
        perBean.setSharedId(14);
        perBean.setName(MCUtil.getBundleString("message.permission.func.purchasing.deliverynotice"));
        perBean.setValue(PermissionUtil.PER_PURCHASING_DELIVERYNOTICE);
        arrFun.add(perBean);

        perBean = new PermissionViewBean();
        perBean.setCounter("14.2");
        perBean.setLevel(2);
        perBean.setSharedId(14);
        perBean.setName(MCUtil.getBundleString("message.permission.func.purchasing.deliverynotice.project"));
        perBean.setValue(PermissionUtil.PER_PURCHASING_DELIVERYNOTICE_PROJECT);
        arrFun.add(perBean);
        
        perBean = new PermissionViewBean();
        perBean.setCounter("14.3");
        perBean.setLevel(2);
        perBean.setSharedId(14);
        perBean.setName(MCUtil.getBundleString("message.permission.func.purchasing.deliverynotice.org"));
        perBean.setValue(PermissionUtil.PER_PURCHASING_DELIVERYNOTICE_ORG);
        arrFun.add(perBean);

        perBean = new PermissionViewBean();
        perBean.setCounter("15");
        perBean.setLevel(1);
        perBean.setSharedId(15);
        perBean.setName(MCUtil.getBundleString("message.permission.func.purchasing.invoice"));
        perBean.setValue(PermissionUtil.PER_PURCHASING_INVOICE);
        arrFun.add(perBean);

        perBean = new PermissionViewBean();
        perBean.setCounter("16");
        perBean.setLevel(1);
        perBean.setSharedId(16);
        perBean.setName(MCUtil.getBundleString("message.permission.func.purchasing.payment"));
        perBean.setValue(PermissionUtil.PER_PURCHASING_PAYMENT);
        arrFun.add(perBean);

        perBean = new PermissionViewBean();
        perBean.setCounter("17");
        perBean.setLevel(0);
        perBean.setSharedId(17);
        perBean.setName(MCUtil.getBundleString("message.mrir.u_title"));
        perBean.setValue(PermissionUtil.PER_STOCK_MRIR);
        arrFun.add(perBean);
        
        perBean = new PermissionViewBean();
        perBean.setCounter("17.1");
        perBean.setLevel(2);
        perBean.setSharedId(17);
        perBean.setName(MCUtil.getBundleString("message.permission.func.stock.mrir"));
        perBean.setValue(PermissionUtil.PER_STOCK_MRIR);
        arrFun.add(perBean);

        perBean = new PermissionViewBean();
        perBean.setCounter("17.2");
        perBean.setLevel(2);
        perBean.setSharedId(17);
        perBean.setName(MCUtil.getBundleString("message.permission.func.stock.mrir.project"));
        perBean.setValue(PermissionUtil.PER_STOCK_MRIR_PROJECT);
        arrFun.add(perBean);

        perBean = new PermissionViewBean();
        perBean.setCounter("17.3");
        perBean.setLevel(2);
        perBean.setSharedId(17);
        perBean.setName(MCUtil.getBundleString("message.permission.func.stock.mrir.store"));
        perBean.setValue(PermissionUtil.PER_STOCK_MRIR_STORE);
        arrFun.add(perBean);

        perBean = new PermissionViewBean();
        perBean.setCounter("18");
        perBean.setLevel(1);
        perBean.setSharedId(18);
        perBean.setName(MCUtil.getBundleString("message.permission.func.stock.msv"));
        perBean.setValue(PermissionUtil.PER_STOCK_MSV);
        arrFun.add(perBean);

        perBean = new PermissionViewBean();
        perBean.setCounter("19");
        perBean.setLevel(0);
        perBean.setSharedId(19);
        perBean.setName(MCUtil.getBundleString("message.u_rfm"));
        perBean.setValue(PermissionUtil.PER_STOCK_RFM);
        arrFun.add(perBean);
        
        perBean = new PermissionViewBean();
        perBean.setCounter("19.1");
        perBean.setLevel(2);
        perBean.setSharedId(19);
        perBean.setName(MCUtil.getBundleString("message.permission.func.stock.rfm"));
        perBean.setValue(PermissionUtil.PER_STOCK_RFM);
        arrFun.add(perBean);

        perBean = new PermissionViewBean();
        perBean.setCounter("19.2");
        perBean.setLevel(2);
        perBean.setSharedId(19);
        perBean.setName(MCUtil.getBundleString("message.permission.func.stock.rfm.material"));
        perBean.setValue(PermissionUtil.PER_STOCK_RFM_MATERIAL);
        arrFun.add(perBean);
        
        perBean = new PermissionViewBean();
        perBean.setCounter("19.3");
        perBean.setLevel(2);
        perBean.setSharedId(19);
        perBean.setName(MCUtil.getBundleString("message.permission.func.stock.org"));
        perBean.setValue(PermissionUtil.PER_STOCK_ORG);
        arrFun.add(perBean);

        perBean = new PermissionViewBean();
        perBean.setCounter("20");
        perBean.setLevel(1);
        perBean.setSharedId(20);
        perBean.setName(MCUtil.getBundleString("message.permission.func.stock.miv"));
        perBean.setValue(PermissionUtil.PER_STOCK_MIV);
        arrFun.add(perBean);

        perBean = new PermissionViewBean();
        perBean.setCounter("21");
        perBean.setLevel(0);
        perBean.setSharedId(21);
        perBean.setName(MCUtil.getBundleString("message.menu441.u_text"));
        perBean.setValue(PermissionUtil.PER_STOCK_REPORT_REQUEST);
        arrFun.add(perBean);
        
        perBean = new PermissionViewBean();
        perBean.setCounter("21.1");
        perBean.setLevel(2);
        perBean.setSharedId(21);
        perBean.setName(MCUtil.getBundleString("message.permission.func.stock.report.request"));
        perBean.setValue(PermissionUtil.PER_STOCK_REPORT_REQUEST);
        arrFun.add(perBean);

        perBean = new PermissionViewBean();
        perBean.setCounter("21.2");
        perBean.setLevel(2);
        perBean.setSharedId(21);
        perBean.setName(MCUtil.getBundleString("message.permission.func.stock.report.store"));
        perBean.setValue(PermissionUtil.PER_STOCK_REPORT_STORE);
        arrFun.add(perBean);

        perBean = new PermissionViewBean();
        perBean.setCounter("21.3");
        perBean.setLevel(2);
        perBean.setSharedId(21);
        perBean.setName(MCUtil.getBundleString("message.permission.func.stock.report.store.project"));
        perBean.setValue(PermissionUtil.PER_STOCK_REPORT_STORE_PROJECT);
        arrFun.add(perBean);

        perBean = new PermissionViewBean();
        perBean.setCounter("21.4");
        perBean.setLevel(2);
        perBean.setSharedId(21);
        perBean.setName(MCUtil.getBundleString("message.permission.func.stock.report.balance"));
        perBean.setValue(PermissionUtil.PER_STOCK_REPORT_BALANCE);
        arrFun.add(perBean);
        
        perBean = new PermissionViewBean();
        perBean.setCounter("21.5");
        perBean.setLevel(2);
        perBean.setSharedId(21);
        perBean.setName(MCUtil.getBundleString("message.permission.func.stock.report.store.level2"));
        perBean.setValue(PermissionUtil.PER_STOCK_REPORT_STORE_LEVEL2);
        arrFun.add(perBean);

        perBean = new PermissionViewBean();
        perBean.setCounter("22");
        perBean.setLevel(1);
        perBean.setSharedId(22);
        perBean.setName(MCUtil.getBundleString("message.permission.func.stock.inventory"));
        perBean.setValue(PermissionUtil.PER_STOCK_INVENTORY);
        arrFun.add(perBean);

        perBean = new PermissionViewBean();
        perBean.setCounter("23");
        perBean.setLevel(0);
        perBean.setSharedId(23);
        perBean.setName(MCUtil.getBundleString("message.menu432.u_text"));
        perBean.setValue(PermissionUtil.PER_STOCK_PROJECT);
        arrFun.add(perBean);
        
        perBean = new PermissionViewBean();
        perBean.setCounter("23.1");
        perBean.setLevel(2);
        perBean.setSharedId(23);
        perBean.setName(MCUtil.getBundleString("message.permission.func.stock.project"));
        perBean.setValue(PermissionUtil.PER_STOCK_PROJECT);
        arrFun.add(perBean);

        perBean = new PermissionViewBean();
        perBean.setCounter("23.2");
        perBean.setLevel(2);
        perBean.setSharedId(23);
        perBean.setName(MCUtil.getBundleString("message.permission.func.stock.project.mrir"));
        perBean.setValue(PermissionUtil.PER_STOCK_PROJECT_MRIR);
        arrFun.add(perBean);

        perBean = new PermissionViewBean();
        perBean.setCounter("23.3");
        perBean.setLevel(2);
        perBean.setSharedId(23);
        perBean.setName(MCUtil.getBundleString("message.permission.func.stock.project.msv"));
        perBean.setValue(PermissionUtil.PER_STOCK_PROJECT_MSV);
        arrFun.add(perBean);

        perBean = new PermissionViewBean();
        perBean.setCounter("23.5");
        perBean.setLevel(2);
        perBean.setSharedId(23);
        perBean.setName(MCUtil.getBundleString("message.permission.func.stock.project.statistics"));
        perBean.setValue(PermissionUtil.PER_STOCK_PROJECT_STATISTICS);
        arrFun.add(perBean);

        perBean = new PermissionViewBean();
        perBean.setCounter("23.6");
        perBean.setLevel(2);
        perBean.setSharedId(23);
        perBean.setName(MCUtil.getBundleString("message.permission.func.stock.project.rfm"));
        perBean.setValue(PermissionUtil.PER_STOCK_PROJECT_RFM);
        arrFun.add(perBean);

        perBean = new PermissionViewBean();
        perBean.setCounter("23.7");
        perBean.setLevel(2);
        perBean.setSharedId(23);
        perBean.setName(MCUtil.getBundleString("message.permission.func.stock.project.miv"));
        perBean.setValue(PermissionUtil.PER_STOCK_PROJECT_MIV);
        arrFun.add(perBean);

        perBean = new PermissionViewBean();
        perBean.setCounter("24");
        perBean.setLevel(1);
        perBean.setSharedId(24);
        perBean.setName(MCUtil.getBundleString("message.permission.func.stock.store2"));
        perBean.setValue(PermissionUtil.PER_STOCK_STORE2);
        arrFun.add(perBean);

        perBean = new PermissionViewBean();
        perBean.setCounter("25");
        perBean.setLevel(0);
        perBean.setSharedId(25);
        perBean.setName(MCUtil.getBundleString("message.menu480.u_text"));
        perBean.setValue(PermissionUtil.PER_STOCK_YCKT_STORE2);
        arrFun.add(perBean);
        
        perBean = new PermissionViewBean();
        perBean.setCounter("25.1");
        perBean.setLevel(2);
        perBean.setSharedId(25);
        perBean.setName(MCUtil.getBundleString("message.permission.func.stock.YCKT.store2"));
        perBean.setValue(PermissionUtil.PER_STOCK_YCKT_STORE2);
        arrFun.add(perBean);

        perBean = new PermissionViewBean();
        perBean.setCounter("25.2");
        perBean.setLevel(2);
        perBean.setSharedId(25);
        perBean.setName(MCUtil.getBundleString("message.permission.func.stock.YCKT.redundant"));
        perBean.setValue(PermissionUtil.PER_STOCK_YCKT_REDUNDANT);
        arrFun.add(perBean);

        perBean = new PermissionViewBean();
        perBean.setCounter("25.3");
        perBean.setLevel(2);
        perBean.setSharedId(25);
        perBean.setName(MCUtil.getBundleString("message.permission.func.stock.YCKT.project"));
        perBean.setValue(PermissionUtil.PER_STOCK_YCKT_PROJECT);
        arrFun.add(perBean);

        perBean = new PermissionViewBean();
        perBean.setCounter("26");
        perBean.setLevel(1);
        perBean.setSharedId(26);
        perBean.setName(MCUtil.getBundleString("message.permission.func.equipment.color"));
        perBean.setValue(PermissionUtil.PER_EQUIPMENT_COLOR);
        arrFun.add(perBean);

        perBean = new PermissionViewBean();
        perBean.setCounter("27");
        perBean.setLevel(1);
        perBean.setSharedId(27);
        perBean.setName(MCUtil.getBundleString("message.permission.func.equipment"));
        perBean.setValue(PermissionUtil.PER_EQUIPMENT);
        arrFun.add(perBean);
        
        perBean = new PermissionViewBean();
        perBean.setCounter("28");
        perBean.setLevel(1);
        perBean.setSharedId(28);
        perBean.setName(MCUtil.getBundleString("message.permission.func.equipment.asset"));
        perBean.setValue(PermissionUtil.PER_EQUIPMENT_ASSET);
        arrFun.add(perBean);

        perBean = new PermissionViewBean();
        perBean.setCounter("29");
        perBean.setLevel(0);
        perBean.setSharedId(29);
        perBean.setName(MCUtil.getBundleString("message.menu58.u_text"));
        perBean.setValue(PermissionUtil.PER_EQUIPMENT_ASSET);
        arrFun.add(perBean);
        
        perBean = new PermissionViewBean();
        perBean.setCounter("29.1");
        perBean.setLevel(2);
        perBean.setSharedId(29);
        perBean.setName(MCUtil.getBundleString("message.permission.func.equipment.mc"));
        perBean.setValue(PermissionUtil.PER_EQUIPMENT_MC);
        arrFun.add(perBean);

        perBean = new PermissionViewBean();
        perBean.setCounter("29.2");
        perBean.setLevel(2);
        perBean.setSharedId(29);
        perBean.setName(MCUtil.getBundleString("message.permission.func.equipment.mco"));
        perBean.setValue(PermissionUtil.PER_EQUIPMENT_MCO);
        arrFun.add(perBean);

        perBean = new PermissionViewBean();
        perBean.setCounter("29.3");
        perBean.setLevel(2);
        perBean.setSharedId(29);
        perBean.setName(MCUtil.getBundleString("message.permission.func.equipment.emc"));
        perBean.setValue(PermissionUtil.PER_EQUIPMENT_EMC);
        arrFun.add(perBean);

        perBean = new PermissionViewBean();
        perBean.setCounter("29.4");
        perBean.setLevel(2);
        perBean.setSharedId(29);
        perBean.setName(MCUtil.getBundleString("message.permission.func.equipment.emco"));
        perBean.setValue(PermissionUtil.PER_EQUIPMENT_EMCO);
        arrFun.add(perBean);

        perBean = new PermissionViewBean();
        perBean.setCounter("30");
        perBean.setLevel(1);
        perBean.setSharedId(30);
        perBean.setName(MCUtil.getBundleString("message.permission.func.equipment.factReport"));
        perBean.setValue(PermissionUtil.PER_EQUIPMENT_FACTREPORT);
        arrFun.add(perBean);

        perBean = new PermissionViewBean();
        perBean.setCounter("31");
        perBean.setLevel(1);
        perBean.setSharedId(31);
        perBean.setName(MCUtil.getBundleString("message.permission.func.equipment.repairRequest"));
        perBean.setValue(PermissionUtil.PER_EQUIPMENT_REPAIRREQUEST);
        arrFun.add(perBean);

        perBean = new PermissionViewBean();
        perBean.setCounter("32");
        perBean.setLevel(1);
        perBean.setSharedId(32);
        perBean.setName(MCUtil.getBundleString("message.permission.func.equipment.surveyReport"));
        perBean.setValue(PermissionUtil.PER_EQUIPMENT_SURVEYREPORT);
        arrFun.add(perBean);
        
        
                
        request.setAttribute(Constants.PERMISSION_FUNC_LIST, arrFun);

        return true;
    }
}
