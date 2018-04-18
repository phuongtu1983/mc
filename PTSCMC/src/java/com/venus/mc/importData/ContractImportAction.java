/*
 *
 * Created on April 13, 2007, 2:08 PM
 */
package com.venus.mc.importData;

import com.venus.core.util.DateUtil;
import com.venus.core.util.GenericValidator;
import com.venus.core.util.LogUtil;
import com.venus.mc.bean.ContractBean;
import com.venus.mc.bean.ContractDetailBean;
import com.venus.mc.bean.RequestBean;
import com.venus.mc.bean.RequestDetailBean;
import com.venus.mc.bean.TenderPlanBean;
import com.venus.mc.bean.TenderPlanDetailBean;
import com.venus.mc.bean.VendorBean;
import com.venus.mc.contract.ContractFormBean;
import com.venus.mc.core.BaseAction;
import com.venus.mc.dao.ContractDAO;
import com.venus.mc.dao.MaterialDAO;
import com.venus.mc.dao.RequestDAO;
import com.venus.mc.dao.TenderPlanDAO;
import com.venus.mc.dao.VendorDAO;
import com.venus.mc.request.RequestFormBean;
import com.venus.mc.tenderplan.TenderPlanFormBean;
import com.venus.mc.util.MCUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jxls.reader.ReaderBuilder;
import net.sf.jxls.reader.ReaderConfig;
import net.sf.jxls.reader.XLSDataReadException;
import net.sf.jxls.reader.XLSReadStatus;
import net.sf.jxls.reader.XLSReader;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author phuontu
 * @version
 */
public class ContractImportAction extends BaseAction {

    private int orgId = 4;//phong ke hoach
    private int empId = 1;//admin
    RequestBean reqBean = null;
//    ContractBean contract = null;
    TenderPlanBean tenBean = null;

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
        importProcess(request);
        return true;
    }

//    private void importProcess(HttpServletRequest request) {
//        String fileName = "hopdong_data";
//        if (!GenericValidator.isBlankOrNull(request.getParameter("name"))) {
//            fileName = request.getParameter("name");
//        }
//        fileName += ".xls";
//        String xmlConfig = request.getSession().getServletContext().getRealPath("/data_imported/hopdong_template.xml");
//        String dataXLS = request.getSession().getServletContext().getRealPath("/data_imported/" + fileName);
//        InputStream inputXML = null;
//        InputStream inputXLS = null;
//        try {
//            File fileConfig = new File(xmlConfig);
//            inputXML = new FileInputStream(fileConfig);
//            XLSReader mainReader = ReaderBuilder.buildFromXML(inputXML);
//            File fileData = new File(dataXLS);
//            inputXLS = new FileInputStream(fileData);
//
//            List lst = new ArrayList();
//            Map beans = new HashMap();
//            beans.put("datas", lst);
//            ReaderConfig.getInstance().setSkipErrors(true);
//            XLSReadStatus readStatus = mainReader.read(inputXLS, beans);
//            if (readStatus.isStatusOK()) {
//                if (lst != null) {
//                    ContractDataBean data = null;
//                    String contractNumber = "";
//                    int kind = 0;
//                    int reqId = 0;
//                    int tenId = 0;
//                    int conId = 0;
//                    int venId = 0;
//                    String auto = "auto";
//                    for (int i = 0; i < lst.size(); i++) {
//                        data = (ContractDataBean) lst.get(i);
//                        if (GenericValidator.isBlankOrNull(data.getContractNumber())) {
////                            data.setContractNumber(count++ + "");
//                            continue;
//                        }
//                        if (data.getKind().equals("HDNT")) {
//                            kind = ContractBean.KIND_PRINCIPLE;
//                        } else if (data.getKind().equals("HD")) {
//                            kind = ContractBean.KIND_CONTRACT;
//                        } else if (data.getKind().equals("DDH")) {
//                            kind = ContractBean.KIND_ORDER;
//                        }
////                        if (!contractNumber.equals(data.getContractNumber())) {//dong moi cua contract => insert contract
//                        contractNumber = data.getContractNumber();
//                        conId = searchContract(contractNumber, kind);
//                        if (conId == 0) {
////                                name = "auto-" + count++;
//                            reqId = searchRequest(auto + "-request-" + contractNumber);
//                            if (reqId == 0) {
////                                    if (reqId > 0) {
////                                        auto += reqId + 1;
////                                    } else {
////                                        auto += "1";
////                                    }
//                                reqBean = new RequestBean();
//                                reqBean.setOrgId(orgId);
//                                reqBean.setCreatedOrg(orgId);
//                                reqBean.setKind(RequestBean.REQUEST);
//                                reqBean.setRequestNumber(auto + "-request-" + contractNumber);
//                                reqBean.setWhichUse(RequestFormBean.WHICHUSE_PROJECT);
//                                reqBean.setOrgHandle(orgId + "");
//                                reqBean.setCreatedEmp(empId);
//                                reqBean.setCheckApprove(1);
//                                reqBean.setStoreApprove(1);
//                                reqBean.setBomApprove(1);
//                                reqBean.setPlandepApprove(1);
//                                reqBean.setAssignedEmp(empId);
//                                reqBean.setApproveEmp(empId);
//                                reqBean.setBomAgreeDate(DateUtil.today("dd/MM/yyyy"));
//                                insertRequest(reqBean);
//                                reqId = reqBean.getReqId();
//
//                                tenId = searchTenderPlan(auto + "-tender-" + contractNumber);
//                                if (tenId == 0) {
//                                    tenBean = new TenderPlanBean();
//                                    tenBean.setCreatedEmp(empId);
//                                    tenBean.setTenderNumber(auto + "-tender-" + contractNumber);
//                                    tenBean.setCreatedDate(DateUtil.today("dd/MM/yyyy"));
//                                    tenBean.setPackName(tenBean.getTenderNumber());
//                                    tenBean.setForm(TenderPlanFormBean.FORM_FAX + "");
//                                    tenBean.setOfferType(TenderPlanFormBean.OFFER_IN);
//                                    tenBean.setEvalKind(TenderPlanFormBean.EVAL_KIND_ALL);
//                                    tenBean.setHandleEmp(empId);
//                                    insertTenderPlan(tenBean);
//                                    tenId = tenBean.getTenId();
//                                }
//                            }
//                            try {
//                                venId = Integer.parseInt(data.getVendor());
//                            } catch (Exception ex) {
//                                venId = searchVendor(data.getVendor());
//                                if (venId == 0) {
//                                    venId = insertVendor(data.getVendor());
//                                }
//                            }
//
//                            ContractBean contract = new ContractBean();
//                            contract.setTenId(tenId);
//                            contract.setVenId(venId);
//                            contract.setResponseEmp(empId);
//                            contract.setContractNumber(contractNumber);
//                            contract.setCreatedDate(DateUtil.today("dd/MM/yyyy"));
//                            contract.setSignDate(correctDate(data.getContractDate()));
//                            contract.setEffectedDate(contract.getSignDate());
//                            contract.setExpireDate(correctDate(data.getExpireDate()));
//                            contract.setCurrency(data.getCurrency());
//                            contract.setDelivery(correctDate(data.getDeliveryDate()));
//                            contract.setKind(kind);
//                            contract.setCreatedEmp(empId);
//                            insertContract(contract);
//                            conId = contract.getConId();
//                        } else {
//                            reqId = searchRequest(auto + "-request-" + contractNumber);
//                            tenId = searchTenderPlan(auto + "-tender-" + contractNumber);
//                        }
//                        //insert chi tiet cua req
////                        String matName = data.getMaterial();
////                        int matId = searchMaterial(matName);
////                        if (matId == 0) {
////                            MaterialBean bean = new MaterialBean();
////                            bean.setNameVn(matName);
////                            bean.setKind(1);
////                            bean.setGroId(1);
////                            bean.setCode(data.getCode());
////                            //kiem tra unit
////                            String unit = data.getUnit();
////                            int unitId = searchUnit(unit);
////                            if (unitId == 0) {
////                                unitId = insertUnit(unit);
////                            }
////                            bean.setUniId(unitId);
////                            matId = insertMaterial(bean);
////                        }
//
//                        String code = data.getCode();
//                        int matId = searchMaterialByCode(code);
//                        if (matId == 0) {
//                            continue;
//                        }
//
//                        RequestDetailBean reqDet = new RequestDetailBean();
//                        reqDet.setReqId(reqId);
//                        reqDet.setMatId(matId);
//                        reqDet.setUnit(data.getUnit());
//                        reqDet.setAdditionalQuantity(1);
//                        reqDet.setStep(MCUtil.getBundleString("message.contract"));
//                        reqDet.setStepId(RequestBean.STEP_CONTRACT);
//                        reqDet.setTenId(tenId);
//                        reqDet.setConId(conId);
//                        reqDet.setRequestQuantity(1);
//                        reqDet.setRemainQuantity(0);
//                        insertReqDetail(reqDet);
//
//                        TenderPlanDetailBean tenDet = new TenderPlanDetailBean();
//                        tenDet.setTenId(tenId);
//                        tenDet.setMatId(matId);
//                        tenDet.setUnit(data.getUnit());
//                        tenDet.setQuantity(1);
//                        tenDet.setReqDetailId(reqDet.getDetId());
//                        insertTenDetail(tenDet);
//
//                        ContractDetailBean conDet = new ContractDetailBean();
//                        conDet.setConId(conId);
//                        conDet.setMatId(matId);
//                        conDet.setUnit(data.getUnit());
//                        conDet.setQuantity(1);
//                        conDet.setPrice(data.getPrice());
//                        conDet.setTotal(data.getPrice());
//                        conDet.setCurrency(data.getCurrency());
//                        conDet.setVat(data.getVat());
//                        conDet.setNote(data.getNote());
//                        conDet.setReqDetailId(reqDet.getDetId());
//                        conDet.setStatus(ContractFormBean.MATERIAL_STATUS_NORMAL);
//                        insertContractDetail(conDet);
//                    }
//                }
//            }
//        } catch (XLSDataReadException e) {
//            LogUtil.error("FAILED:Import Contract-" + e.getMessage());
//        } catch (Exception ex) {
//            LogUtil.error("FAILED:Import Contract-" + ex.getMessage());
//        } finally {
//            if (inputXLS != null) {
//                try {
//                    inputXLS.close();
//                } catch (Exception ex) {
//                }
//            }
//            if (inputXML != null) {
//                try {
//                    inputXML.close();
//                } catch (Exception ex) {
//                }
//            }
//        }
//    }
//    private void importProcess(HttpServletRequest request) {
//        String fileName = "hopdong_data";
//        if (!GenericValidator.isBlankOrNull(request.getParameter("name"))) {
//            fileName = request.getParameter("name");
//        }
//        fileName += ".xls";
//        String xmlConfig = request.getSession().getServletContext().getRealPath("/data_imported/hopdong_template.xml");
//        String dataXLS = request.getSession().getServletContext().getRealPath("/data_imported/" + fileName);
//        InputStream inputXML = null;
//        InputStream inputXLS = null;
//        try {
//            File fileConfig = new File(xmlConfig);
//            inputXML = new FileInputStream(fileConfig);
//            XLSReader mainReader = ReaderBuilder.buildFromXML(inputXML);
//            File fileData = new File(dataXLS);
//            inputXLS = new FileInputStream(fileData);
//
//            List lst = new ArrayList();
//            Map beans = new HashMap();
//            beans.put("datas", lst);
//            ReaderConfig.getInstance().setSkipErrors(true);
//            XLSReadStatus readStatus = mainReader.read(inputXLS, beans);
//            if (readStatus.isStatusOK()) {
//                if (lst != null) {
//                    ContractDataBean data = null;
//                    String contractNumber = "";
//                    int kind = 0;
//                    int reqId = 0;
//                    int tenId = 0;
//                    int conId = 0;
//                    int venId = 0;
//                    String auto = "auto";
//                    for (int i = 0; i < lst.size(); i++) {
//                        data = (ContractDataBean) lst.get(i);
//                        if (GenericValidator.isBlankOrNull(data.getContractNumber())) {
////                            data.setContractNumber(count++ + "");
//                            continue;
//                        }
//                        if (data.getKind().equals("HDNT")) {
//                            kind = ContractBean.KIND_PRINCIPLE;
//                        } else if (data.getKind().equals("HD")) {
//                            kind = ContractBean.KIND_CONTRACT;
//                        } else if (data.getKind().equals("DDH")) {
//                            kind = ContractBean.KIND_ORDER;
//                        } else if (data.getKind().equals("DNGH")) {
//                            kind = ContractBean.KIND_DELIVERY_REQUEST;
//                        }
////                        if (!contractNumber.equals(data.getContractNumber())) {//dong moi cua contract => insert contract
//                        contractNumber = data.getContractNumber();
//                        reqId = searchRequest(auto + "-request-" + contractNumber);
//                        if (reqId == 0) {
//                            reqBean = new RequestBean();
//                            reqBean.setOrgId(orgId);
//                            reqBean.setCreatedOrg(orgId);
//                            reqBean.setKind(RequestBean.REQUEST);
//                            reqBean.setRequestNumber(auto + "-request-" + contractNumber);
//                            reqBean.setWhichUse(RequestFormBean.WHICHUSE_PROJECT);
//                            reqBean.setOrgHandle(orgId + "");
//                            reqBean.setCreatedEmp(empId);
//                            reqBean.setCheckApprove(1);
//                            reqBean.setStoreApprove(1);
//                            reqBean.setBomApprove(1);
//                            reqBean.setPlandepApprove(1);
//                            reqBean.setAssignedEmp(empId);
//                            reqBean.setApproveEmp(empId);
//                            reqBean.setBomAgreeDate(DateUtil.today("dd/MM/yyyy"));
//                            insertRequest(reqBean);
//                            reqId = reqBean.getReqId();
//
//                            tenId = searchTenderPlan(auto + "-tender-" + contractNumber);
//                            if (tenId == 0) {
//                                tenBean = new TenderPlanBean();
//                                tenBean.setCreatedEmp(empId);
//                                tenBean.setTenderNumber(auto + "-tender-" + contractNumber);
//                                tenBean.setCreatedDate(DateUtil.today("dd/MM/yyyy"));
//                                tenBean.setPackName(tenBean.getTenderNumber());
//                                tenBean.setForm(TenderPlanFormBean.FORM_FAX + "");
//                                tenBean.setOfferType(TenderPlanFormBean.OFFER_IN);
//                                tenBean.setEvalKind(TenderPlanFormBean.EVAL_KIND_ALL);
//                                tenBean.setHandleEmp(empId);
//                                insertTenderPlan(tenBean);
//                                tenId = tenBean.getTenId();
//                            }
//                        }
//                        try {
//                            venId = Integer.parseInt(data.getVendor());
//                        } catch (Exception ex) {
//                            venId = searchVendor(data.getVendor());
//                            if (venId == 0) {
//                                venId = insertVendor(data.getVendor());
//                            }
//                        }
//                        conId = searchContract(contractNumber, kind);
//                        ContractBean contract = new ContractBean();
//                        contract.setConId(conId);
//                        contract.setTenId(tenId);
//                        contract.setVenId(venId);
//                        contract.setResponseEmp(data.getEmpId());
//                        contract.setContractNumber(contractNumber);
//                        contract.setCreatedDate(data.getContractDate());
//                        contract.setSignDate(correctDate(data.getContractDate()));
//                        contract.setEffectedDate(data.getContractDate());
//                        contract.setExpireDate(correctDate(data.getExpireDate()));
//                        contract.setCurrency(data.getCurrency());
//                        contract.setDelivery(data.getDeliveryDate());
//                        contract.setCertificate(data.getCertificate());
//                        contract.setKind(kind);
//                        contract.setNote(data.getNote());
//                        contract.setCreatedEmp(data.getEmpId());
//                        if (conId == 0) {
//                            insertContract(contract);
//                            conId = contract.getConId();
//                        } else {
//                            updateContract(contract);
//                        }
//                        //insert chi tiet cua req
//                        String code = data.getCode();
//                        int matId = searchMaterialByCode(code);
//                        if (matId == 0) {
//                            continue;
//                        }
//
//                        RequestDetailBean reqDet = new RequestDetailBean();
//                        reqDet.setReqId(reqId);
//                        reqDet.setMatId(matId);
//                        reqDet.setUnit(data.getUnit());
//                        reqDet.setAdditionalQuantity(1);
//                        reqDet.setStep(MCUtil.getBundleString("message.contract"));
//                        reqDet.setStepId(RequestBean.STEP_CONTRACT);
//                        reqDet.setTenId(tenId);
//                        reqDet.setConId(conId);
//                        if (data.getQuantity() == 0) {
//                            reqDet.setRequestQuantity(1);
//                        } else {
//                            reqDet.setRequestQuantity(data.getQuantity());
//                        }
//                        reqDet.setRemainQuantity(0);
//                        insertReqDetail(reqDet);
//
//                        TenderPlanDetailBean tenDet = new TenderPlanDetailBean();
//                        tenDet.setTenId(tenId);
//                        tenDet.setMatId(matId);
//                        tenDet.setUnit(data.getUnit());
//                        tenDet.setQuantity(reqDet.getRequestQuantity());
//                        tenDet.setReqDetailId(reqDet.getDetId());
//                        insertTenDetail(tenDet);
//
//                        ContractDetailBean conDet = new ContractDetailBean();
//                        conDet.setConId(conId);
//                        conDet.setMatId(matId);
//                        conDet.setUnit(data.getUnit());
//                        conDet.setQuantity(reqDet.getRequestQuantity());
//                        conDet.setPrice(data.getPrice());
//                        conDet.setTotal(data.getPrice());
//                        conDet.setCurrency(data.getCurrency());
//                        conDet.setVat(data.getVat());
//                        conDet.setNote(data.getNote());
//                        conDet.setReqDetailId(reqDet.getDetId());
//                        conDet.setStatus(ContractFormBean.MATERIAL_STATUS_NORMAL);
//                        insertContractDetail(conDet);
//                    }
//                }
//            }
//        } catch (XLSDataReadException e) {
//            LogUtil.error("FAILED:Import Contract-" + e.getMessage());
//        } catch (Exception ex) {
//            LogUtil.error("FAILED:Import Contract-" + ex.getMessage());
//        } finally {
//            if (inputXLS != null) {
//                try {
//                    inputXLS.close();
//                } catch (Exception ex) {
//                }
//            }
//            if (inputXML != null) {
//                try {
//                    inputXML.close();
//                } catch (Exception ex) {
//                }
//            }
//        }
//    }
    private void importProcess(HttpServletRequest request) {
        String fileName = "hopdong_data";
        if (!GenericValidator.isBlankOrNull(request.getParameter("name"))) {
            fileName = request.getParameter("name");
        }
        fileName += ".xls";
        String xmlConfig = request.getSession().getServletContext().getRealPath("/data_imported/hopdong_template.xml");
        String dataXLS = request.getSession().getServletContext().getRealPath("/data_imported/" + fileName);
        InputStream inputXML = null;
        InputStream inputXLS = null;
        try {
            File fileConfig = new File(xmlConfig);
            inputXML = new FileInputStream(fileConfig);
            XLSReader mainReader = ReaderBuilder.buildFromXML(inputXML);
            File fileData = new File(dataXLS);
            inputXLS = new FileInputStream(fileData);

            List lst = new ArrayList();
            Map beans = new HashMap();
            beans.put("datas", lst);
            ReaderConfig.getInstance().setSkipErrors(true);
            XLSReadStatus readStatus = mainReader.read(inputXLS, beans);
            if (readStatus.isStatusOK()) {
                if (lst != null) {
                    ContractDataBean data = null;
                    String contractNumber = "";
                    int kind = 0;
                    int reqId = 0;
                    int tenId = 0;
                    int conId = 0;
                    int venId = 0;
                    String auto = "auto";
                    ArrayList conIdArr = new ArrayList();
                    for (int i = 0; i < lst.size(); i++) {
                        data = (ContractDataBean) lst.get(i);
                        if (GenericValidator.isBlankOrNull(data.getContractNumber())) {
//                            data.setContractNumber(count++ + "");
                            continue;
                        }
                        String code = data.getCode();
                        int matId = searchMaterialByCode(code);
                        if (matId == 0) {
                            continue;
                        }
                        if (data.getKind().equals("HDNT")) {
                            kind = ContractBean.KIND_PRINCIPLE;
                        } else if (data.getKind().equals("HD")) {
                            kind = ContractBean.KIND_CONTRACT;
                        } else if (data.getKind().equals("DDH")) {
                            kind = ContractBean.KIND_ORDER;
                        } else if (data.getKind().equals("DNGH")) {
                            kind = ContractBean.KIND_DELIVERY_REQUEST;
                        } else if (data.getKind().equals("PL")) {
                            kind = ContractBean.KIND_APPENDIX;
                        }
//                        if (!contractNumber.equals(data.getContractNumber())) {//dong moi cua contract => insert contract
                        contractNumber = data.getContractNumber();
                        ContractBean contract = searchContract(contractNumber, kind);
                        conId = contract.getConId();
                        if (conId != 0) {
                            reqId = searchRequest(contractNumber, matId);
                            if (reqId == 0) {
                                reqId = searchRequest(auto + "-request-" + contractNumber);
                            }
                        } else {
                            reqId = searchRequest(auto + "-request-" + contractNumber);
                        }

                        tenId = searchTenderPlan(auto + "-tender-" + contractNumber);
                        if (reqId == 0) {
                            reqBean = new RequestBean();
                            reqBean.setOrgId(orgId);
                            reqBean.setCreatedOrg(orgId);
                            reqBean.setKind(RequestBean.REQUEST);
                            reqBean.setRequestNumber(auto + "-request-" + contractNumber);
                            reqBean.setWhichUse(RequestFormBean.WHICHUSE_PROJECT);
                            reqBean.setOrgHandle(orgId + "");
                            reqBean.setCreatedEmp(empId);
                            reqBean.setCheckApprove(1);
                            reqBean.setStoreApprove(1);
                            reqBean.setBomApprove(1);
                            reqBean.setPlandepApprove(1);
                            reqBean.setAssignedEmp(empId);
                            reqBean.setApproveEmp(empId);
                            reqBean.setBomAgreeDate(DateUtil.today("dd/MM/yyyy"));
                            insertRequest(reqBean);
                            reqId = reqBean.getReqId();

                            if (tenId == 0) {
                                tenBean = new TenderPlanBean();
                                tenBean.setCreatedEmp(empId);
                                tenBean.setTenderNumber(auto + "-tender-" + contractNumber);
                                tenBean.setCreatedDate(DateUtil.today("dd/MM/yyyy"));
                                tenBean.setPackName(tenBean.getTenderNumber());
                                tenBean.setForm(TenderPlanFormBean.FORM_FAX + "");
                                tenBean.setOfferType(TenderPlanFormBean.OFFER_IN);
                                tenBean.setEvalKind(TenderPlanFormBean.EVAL_KIND_ALL);
                                tenBean.setHandleEmp(empId);
                                insertTenderPlan(tenBean);
                                tenId = tenBean.getTenId();
                            }
                        }
                        try {
                            venId = Integer.parseInt(data.getVendor());
                        } catch (Exception ex) {
                            venId = searchVendor(data.getVendor());
                            if (venId == 0) {
                                venId = insertVendor(data.getVendor());
                            }
                        }

                        contract.setConId(conId);
                        contract.setTenId(tenId);
                        contract.setVenId(venId);
                        contract.setResponseEmp(data.getEmpId());
                        contract.setFollowEmp(contract.getResponseEmp());
                        contract.setContractNumber(contractNumber);
                        contract.setCreatedDate(data.getContractDate());
                        contract.setSignDate(correctDate(data.getContractDate()));
                        contract.setEffectedDate(data.getContractDate());
                        contract.setExpireDate(correctDate(data.getExpireDate()));
                        contract.setCurrency(data.getCurrency());
                        contract.setDelivery(data.getDeliveryDate());
                        contract.setCertificate(data.getCertificate());
                        contract.setKind(kind);
                        contract.setNote(data.getNote());
                        contract.setCreatedEmp(data.getEmpId());
                        if (contractNumber.equals("1234-2011/PTSCMC-KH/MHH-V")) {
                            int a = 1;
                        }
                        if (isConIdExisted(conIdArr, conId) == 0) {
                            contract.setTotal(0);
                            contract.setTotalNotVAT(0);
                            contract.setSumVAT(0);
                        }
                        contract.setTotalNotVAT(contract.getTotalNotVAT() + data.getQuantity() * data.getPrice());
                        double vat = data.getVat() / 100;
                        vat = vat * data.getPrice() * data.getQuantity();
                        contract.setSumVAT(contract.getSumVAT() + vat);
                        contract.setTotal(contract.getTotal() + vat + data.getQuantity() * data.getPrice());
                        if (contract.getKind() == ContractBean.KIND_APPENDIX) {
                            String pNumber = data.getParentNumber();
                            ContractBean pBean = searchContract(pNumber, 0);
                            if (pBean != null) {
                                contract.setParentId(pBean.getConId());
                            }
                        }
                        if (conId == 0) {
                            insertContract(contract);
                            conId = contract.getConId();
                        } else {
                            updateContract(contract);
                        }
                        this.insertContractToArray(conIdArr, conId);
                        //ins ert chi tiet cua req

                        int reqDetId = searchRequestDetail(matId, reqId, data.getQuantity());
                        RequestDetailBean reqDet = new RequestDetailBean();
                        if (data.getQuantity() == 0) {
                            reqDet.setRequestQuantity(1);
                        } else {
                            reqDet.setRequestQuantity(data.getQuantity());
                        }
                        if (reqDetId == 0) {
                            reqDet.setReqId(reqId);
                            reqDet.setMatId(matId);
                            reqDet.setUnit(data.getUnit());
                            reqDet.setAdditionalQuantity(1);
                            reqDet.setStep(MCUtil.getBundleString("message.contract"));
                            reqDet.setStepId(RequestBean.STEP_CONTRACT);
                            reqDet.setTenId(tenId);
                            reqDet.setConId(conId);
                            reqDet.setRemainQuantity(0);
                            insertReqDetail(reqDet);
                        } else {
                            reqDet.setDetId(reqDetId);
                        }

                        TenderPlanDetailBean tenDet = new TenderPlanDetailBean();
                        tenDet.setTenId(tenId);
                        tenDet.setMatId(matId);
                        tenDet.setUnit(data.getUnit());
                        tenDet.setQuantity(reqDet.getRequestQuantity());
                        tenDet.setReqDetailId(reqDet.getDetId());
                        int tenDetId = searchTenDetail(tenId, reqDet.getDetId());
                        if (tenDetId == 0) {
                            insertTenDetail(tenDet);
                        } else {
                            tenDet.setDetId(tenDetId);
//                            updateTenDetail(tenDet);
                        }

                        int conDetId = searchContractDetail(matId, conId, reqDet.getRequestQuantity());
                        ContractDetailBean conDet = new ContractDetailBean();
                        conDet.setConId(conId);
                        conDet.setMatId(matId);
                        conDet.setUnit(data.getUnit());
                        conDet.setQuantity(reqDet.getRequestQuantity());
                        conDet.setPrice(data.getPrice());
                        conDet.setVat(data.getVat());
                        conDet.setTotal(data.getPrice() * data.getQuantity() * (data.getVat() + 100) / 100);
                        conDet.setCurrency(data.getCurrency());
                        conDet.setVat(data.getVat());
                        conDet.setNote(data.getNote());
                        conDet.setReqDetailId(reqDet.getDetId());
                        conDet.setStatus(ContractFormBean.MATERIAL_STATUS_NORMAL);
                        conDet.setStatus(ContractFormBean.MATERIAL_STATUS_NORMAL);
//                        conDet.setStatus(data.getContractStatus());

                        if (conDetId == 0) {
                            insertContractDetail(conDet);
                        } else {
                            conDet.setDetId(conDetId);
                            updateContractDetail(conDet);
                        }

                    }
                    insertContractPrice(lst);
                }
            }
        } catch (XLSDataReadException e) {
            LogUtil.error("FAILED:Import Contract-" + e.getMessage());
        } catch (Exception ex) {
            LogUtil.error("FAILED:Import Contract-" + ex.getMessage());
        } finally {
            if (inputXLS != null) {
                try {
                    inputXLS.close();
                } catch (Exception ex) {
                }
            }
            if (inputXML != null) {
                try {
                    inputXML.close();
                } catch (Exception ex) {
                }
            }
        }
    }

    private int searchRequest(String name) {
        int reqId = 0;
        RequestDAO reqDAO = new RequestDAO();
        try {
            RequestBean bean = reqDAO.getRequestByNumber(name);
            reqId = bean.getReqId();
        } catch (Exception ex) {
        }
        return reqId;
    }

    private int searchTenderPlan(String name) {
        int tenId = 0;
        TenderPlanDAO tenDAO = new TenderPlanDAO();
        try {
            TenderPlanBean bean = tenDAO.getTenderPlanByNumber(name);
            if (bean != null) {
                tenId = bean.getTenId();
            }
        } catch (Exception ex) {
        }
        return tenId;
    }

    private ContractBean searchContract(String name, int kind) {
        ContractDAO conDAO = new ContractDAO();
        ContractBean bean = null;
        try {
            bean = conDAO.getContractByNumber(name, kind);
            if (bean == null) {
                bean = new ContractBean();
            }
        } catch (Exception ex) {
        }
        return bean;
    }

    private void insertRequest(RequestBean bean) {
        try {
            RequestDAO reqDAO = new RequestDAO();
            int reqId = reqDAO.insertRequest(bean);
            bean.setReqId(reqId);
        } catch (Exception ex) {
        }
    }

    private void insertTenderPlan(TenderPlanBean bean) {
        try {
            TenderPlanDAO tenDAO = new TenderPlanDAO();
            int tenId = tenDAO.insertTenderPlan(bean);
            bean.setTenId(tenId);
        } catch (Exception ex) {
        }
    }

    private void insertReqDetail(RequestDetailBean bean) {
        try {
            RequestDAO reqDAO = new RequestDAO();
            int reqDetId = reqDAO.insertRequestDetail(bean);
            bean.setDetId(reqDetId);
        } catch (Exception ex) {
        }
    }

    private void insertTenDetail(TenderPlanDetailBean bean) {
        try {
            TenderPlanDAO tenDAO = new TenderPlanDAO();
            tenDAO.insertTenderPlanDetail(bean);
        } catch (Exception ex) {
        }
    }

    private int searchTenDetail(int tenId, int reqDetId) {
        int tenDetId = 0;
        TenderPlanDAO tenDAO = new TenderPlanDAO();
        try {
            TenderPlanDetailBean bean = tenDAO.getTenderPlanDetail(tenId, reqDetId);
            if (bean != null) {
                tenDetId = bean.getDetId();
            }
        } catch (Exception ex) {
        }
        return tenDetId;
    }

    private void updateTenDetail(TenderPlanDetailBean bean) {
        try {
            TenderPlanDAO tenDAO = new TenderPlanDAO();
            tenDAO.updateTenderPlanDetail(bean);
        } catch (Exception ex) {
        }
    }

    private int searchVendor(String name) {
        int venId = 0;
        VendorDAO venDAO = new VendorDAO();
        try {
            VendorBean bean = venDAO.getVendorByName(name);
            if (bean != null) {
                venId = bean.getVenId();
            }
        } catch (Exception ex) {
        }
        return venId;
    }

    private int insertVendor(String venName) {
        int venId = 0;
        VendorDAO venDAO = new VendorDAO();
        try {
            VendorBean bean = new VendorBean();
            bean.setName(venName);
            bean.setStatus(1);
            bean.setKind(VendorBean.KIND_NATIONAL);
            venId = venDAO.insertVendor(bean);
        } catch (Exception ex) {
        }
        return venId;
    }

    private void insertContract(ContractBean bean) {
        ContractDAO conDAO = new ContractDAO();
        try {
            int conId = conDAO.insertContract(bean);
            bean.setConId(conId);
        } catch (Exception ex) {
            LogUtil.error("FAILED:Import Contract- insert Contract - " + ex.getMessage());
        }
    }

    private void insertContractDetail(ContractDetailBean bean) {
        ContractDAO conDAO = new ContractDAO();
        try {
            int detId = conDAO.insertContractDetail(ContractBean.KIND_CONTRACT, bean);
            bean.setDetId(detId);
        } catch (Exception ex) {
        }
    }

    private String correctDate(String date) {
        String result = "";
        try {
            if (GenericValidator.isBlankOrNull(date)) {
                return "";
            }
            String[] part = date.split("/");
            if (part[0].length() == 1) {
                result += "0";
            }
            result += part[0] + "/";
            if (part[1].length() == 1) {
                result += "0";
            }
            result += part[1] + "/";
            if (part[2].length() == 2) {
                result += "20";
            }
            result += part[2];
        } catch (Exception ex) {
        }
        return result;
    }

    private int searchMaterialByCode(String code) {
        int matId = 0;
        MaterialDAO matDAO = new MaterialDAO();
        try {
            matId = matDAO.getMaterialIdByCode(code);
        } catch (Exception ex) {
        }
        return matId;
    }

    private void updateContract(ContractBean bean) {
        ContractDAO conDAO = new ContractDAO();
        try {
            conDAO.updateContract(bean);
            conDAO.updateContractCreatedEmp(bean);
        } catch (Exception ex) {
            LogUtil.error("FAILED:Import Contract- update Contract - " + ex.getMessage());
        }
    }

    private int searchRequest(String contractName, int matId) {
        int reqId = 0;
        RequestDAO reqDAO = new RequestDAO();
        try {
            RequestBean bean = reqDAO.getRequestByContractAndMaterial(contractName, matId);
            reqId = bean.getReqId();
        } catch (Exception ex) {
        }
        return reqId;
    }

    private int searchRequestDetail(int matId, int reqId, double quantity) {
        int result = 0;
        try {
            RequestDAO reqDAO = new RequestDAO();
            result = reqDAO.getRequestDetailByMaterial(reqId, matId, quantity);
        } catch (Exception ex) {
        }
        return result;
    }

    private int searchContractDetail(int matId, int conId, double quantity) {
        int result = 0;
        try {
            ContractDAO conDAO = new ContractDAO();
            result = conDAO.getContractDetailByMaterial(conId, matId, quantity);
        } catch (Exception ex) {
        }
        return result;
    }

    private void updateContractDetail(ContractDetailBean bean) {
        ContractDAO conDAO = new ContractDAO();
        try {
            conDAO.updateContractDetail(bean);
        } catch (Exception ex) {
        }
    }

    private void insertContractPrice(List lst) {
        ContractDataBean data = null;
        String contractNumber = "";
        int conId = 0;
        for (int i = 0; i < lst.size(); i++) {
            data = (ContractDataBean) lst.get(i);
            if (GenericValidator.isBlankOrNull(data.getContractNumber())) {
                continue;
            }
            contractNumber = data.getContractNumber();
            ContractBean contract = searchContract(contractNumber, 0);
            conId = contract.getConId();
//            String code = data.getCode();
//            int matId = searchMaterialByCode(code);
//            if (conId != 0 && matId != 0) {
            if (conId != 0) {
                insertMaterialPrice(conId);
            }
        }
    }

    private void insertMaterialPrice(int conId) {
        ContractDAO conDAO = new ContractDAO();
        try {
//            if (conDAO.getContractPrice(conId, matId) == 0) {
            conDAO.saveContractMaterialPrice(conId);
//            }
        } catch (Exception ex) {
        }
    }

    private void insertContractToArray(ArrayList conArr, int conId) {
        if (isConIdExisted(conArr, conId) == 0) {
            conArr.add(conId + "");
        }
    }

    private int isConIdExisted(ArrayList conArr, int conId) {
        for (int i = 0; i < conArr.size(); i++) {
            String c = conArr.get(i) + "";
            if (c.equals(conId + "")) {
                return 1;
            }
        }
        return 0;
    }
}
