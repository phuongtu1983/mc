/*
 *
 * Created on April 13, 2007, 2:08 PM
 */
package com.venus.mc.importData;

import com.venus.core.util.GenericValidator;
import com.venus.core.util.LogUtil;
import com.venus.core.util.StringUtil;
import com.venus.mc.bean.ContractBean;
import com.venus.mc.bean.ContractDetailBean;
import com.venus.mc.bean.EmaterialBean;
import com.venus.mc.bean.GroupMaterialBean;
import com.venus.mc.bean.MaterialBean;
import com.venus.mc.bean.MaterialPriceBean;
import com.venus.mc.bean.RequestBean;
import com.venus.mc.bean.RequestDetailBean;
import com.venus.mc.bean.SpeBean;
import com.venus.mc.bean.TenderPlanBean;
import com.venus.mc.bean.TenderPlanDetailBean;
import com.venus.mc.bean.UnitBean;
import com.venus.mc.bean.VendorBean;
import com.venus.mc.core.BaseAction;
import com.venus.mc.dao.ContractDAO;
import com.venus.mc.dao.GroupMaterialDAO;
import com.venus.mc.dao.MaterialDAO;
import com.venus.mc.dao.RequestDAO;
import com.venus.mc.dao.SpeDAO;
import com.venus.mc.dao.TenderPlanDAO;
import com.venus.mc.dao.UnitDAO;
import com.venus.mc.dao.VendorDAO;
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
 * @author phuongtu
 * @version
 */
public class MaterialImportAction extends BaseAction {

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
//        String xmlConfig = request.getSession().getServletContext().getRealPath("/data_imported/vattu_template.xml");
//        String dataXLS = request.getSession().getServletContext().getRealPath("/data_imported/vattu_data.xls");
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
//                    MaterialDataBean data = null;
//                    int conId = 0;
//                    int kind = 0;
//                    String code = "";
//                    for (int i = 0; i < lst.size(); i++) {
//                        data = (MaterialDataBean) lst.get(i);
//                        if (GenericValidator.isBlankOrNull(data.getCode())) {
//                            data.setCode("auto-" + count++);
//                        }
//                        if (!code.equals(data.getCode())) {//dong moi cua material => insert material
//                            code = data.getCode();
//                            if (GenericValidator.isBlankOrNull(data.getKind())) {
//                                data.setKind("vttb");
//                            }
//                            if (data.getKind().equals("vttb")) {
//                                kind = 1;
//                            } else if (data.getKind().equals("ccdc")) {
//                                kind = 2;
//                            } else if (data.getKind().equals("tscdhh")) {
//                                kind = 3;
//                            }
//                            int matId = searchMaterialByCode(code);
//                            if (matId == 0) {
//                                MaterialBean bean = new MaterialBean();
//                                if (!GenericValidator.isBlankOrNull(data.getName())) {
//                                    bean.setNameVn(data.getName());
//                                } else {
//                                    bean.setNameVn(code);
//                                }
//                                bean.setNameEn(data.getNameEn());
//                                bean.setKind(kind);
//                                bean.setCode(code);
//                                //kiem tra unit
//                                String unit = data.getUnit();
//                                if (GenericValidator.isBlankOrNull(unit)) {
//                                    unit = "Other";
//                                }
//                                int unitId = searchUnit(unit);
//                                if (unitId == 0) {
//                                    unitId = insertUnit(unit);
//                                }
//                                bean.setUniId(unitId);
//
//                                //kiem tra group
//                                String group = data.getGroup();
//                                if (GenericValidator.isBlankOrNull(group)) {
//                                    group = "Other";
//                                }
//                                int gmId = searchGroupMaterial(group);
//                                if (gmId == 0) {
//                                    gmId = insertGroupMaterial(data.getGroup());
//                                }
//                                bean.setGroId(gmId);
//
//                                matId = insertMaterial(bean);
//
////                                if (!GenericValidator.isBlankOrNull(data.getContract())) {
////                                    ContractBean conBean = searchContract(data.getContract(), 0);
////                                    if (conBean == null) {
////                                        int venId = searchVendor(data.getVendor());
////                                        if (venId == 0) {
////                                            venId = insertVendor(data.getVendor());
////                                        }
////
////                                        conBean = new ContractBean();
////                                        conBean.setVenId(venId);
////                                        conBean.setContractNumber(data.getContract());
////                                        conBean.setCreatedDate(DateUtil.today("dd/MM/yyyy"));
////                                        conBean.setSignDate(DateUtil.today("dd/MM/yyyy"));
////                                        conBean.setEffectedDate(data.getEffectDate());
////                                        conBean.setExpireDate(data.getExpireDate());
////                                        conBean.setCurrency(data.getCurrency());
////                                        conBean.setKind(ContractBean.KIND_CONTRACT);
////                                        conBean.setCreatedEmp(empId);
////                                        insertContract(conBean);
////                                        conId = conBean.getConId();
////                                    }
////
////                                    MaterialPriceBean mpBean = new MaterialPriceBean();
////                                    mpBean.setConId(conId);
////                                    mpBean.setContractNumber(data.getContract());
////                                    mpBean.setVendorName(data.getVendor());
////                                    mpBean.setEffectedDate(data.getEffectDate());
////                                    mpBean.setExpireDate(data.getExpireDate());
////                                    mpBean.setMatId(matId);
////                                    mpBean.setPrice(data.getPrice());
////                                    insertContractPrice(mpBean);
////                                }
//                            }
//                            if (!GenericValidator.isBlankOrNull(data.getContract())) {
//                                ContractBean conBean = searchContract(data.getContract(), 0);
//                                if (conBean == null) {
//                                    int venId = searchVendor(data.getVendor());
//                                    if (venId == 0) {
//                                        venId = insertVendor(data.getVendor());
//                                    }
//
//                                    conBean = new ContractBean();
//                                    conBean.setVenId(venId);
//                                    conBean.setContractNumber(data.getContract());
//                                    conBean.setCreatedDate(DateUtil.today("dd/MM/yyyy"));
//                                    conBean.setSignDate(DateUtil.today("dd/MM/yyyy"));
//                                    conBean.setEffectedDate(correctDate(data.getEffectDate()));
//                                    conBean.setExpireDate(correctDate(data.getExpireDate()));
//                                    conBean.setCurrency(data.getCurrency());
//                                    conBean.setKind(ContractBean.KIND_CONTRACT);
//                                    conBean.setCreatedEmp(empId);
//                                    insertContract(conBean);
//                                    conId = conBean.getConId();
//                                }
//
//                                MaterialPriceBean mpBean = new MaterialPriceBean();
//                                mpBean.setConId(conId);
//                                mpBean.setContractNumber(data.getContract());
//                                mpBean.setVendorName(data.getVendor());
//                                mpBean.setEffectedDate(conBean.getEffectedDate());
//                                mpBean.setExpireDate(conBean.getExpireDate());
//                                mpBean.setMatId(matId);
//                                mpBean.setPrice(data.getPrice());
//                                insertContractPrice(mpBean);
//                            }
//                        }
//                    }
//                }
//            }
//        } catch (XLSDataReadException e) {
//            LogUtil.error("FAILED:Import Material-" + e.getMessage());
//        } catch (Exception ex) {
//            LogUtil.error("FAILED:Import Material-" + ex.getMessage());
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
        String fileName = "vattu_data";
        if (!GenericValidator.isBlankOrNull(request.getParameter("name"))) {
            fileName = request.getParameter("name");
        }
        fileName += ".xls";
        String xmlConfig = request.getSession().getServletContext().getRealPath("/data_imported/vattu_template.xml");
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
                    MaterialDataBean data = null;
                    int kind = 0;
                    String code = "";
                    for (int i = 0; i < lst.size(); i++) {
                        data = (MaterialDataBean) lst.get(i);
                        if (GenericValidator.isBlankOrNull(data.getCode())) {
//                            data.setCode("auto-" + count++);
                            continue;
                        }
                        if (!code.equals(data.getCode().trim())) {//dong moi cua material => insert material
                            code = data.getCode().trim();
                            if (GenericValidator.isBlankOrNull(data.getLevel1())) {
                                data.setLevel1("003");//VTTB
                            }
                            if (data.getLevel1().equals("001")) {
                                if (GenericValidator.isBlankOrNull(data.getLevel4())) {
                                    kind = EmaterialBean.KIND_MAYMOC;
                                } else {
                                    kind = EmaterialBean.KIND_PHUTUNG;
                                }
                            } else if (data.getLevel1().equals("002")) {
                                kind = EmaterialBean.KIND_CCDC;
                            } else if (data.getLevel1().equals("003")) {
                                kind = EmaterialBean.KIND_VTTB;
                            } else if (data.getLevel1().equals("004")) {
                                kind = EmaterialBean.KIND_BAOHO;
//                            }else if (data.getLevel1().equals("004")) {
//                                kind=EmaterialBean.KIND_MAYMOC;
                            }
                            MaterialBean bean = new MaterialBean();
                            String name = "";
                            int matId = searchMaterialByCode(code);
                            name = StringUtil.encodeHTML(data.getNameVn());
                            if (!GenericValidator.isBlankOrNull(name)) {
                                bean.setNameVn(name);
                            } else {
                                bean.setNameVn(code);
                            }
                            bean.setMatId(matId);
                            name = StringUtil.encodeHTML(data.getNameEn());
                            bean.setNameEn(name);
                            bean.setKind(kind);
                            bean.setCode(code);
                            bean.setNote("");
                            bean.setQc("");
                            //kiem tra unit
                            String unit = StringUtil.encodeHTML(data.getUnit());
                            if (GenericValidator.isBlankOrNull(unit)) {
                                unit = "Other";
                            }
                            int unitId = searchUnit(unit);
                            if (unitId == 0) {
                                unitId = insertUnit(unit);
                            }
                            bean.setUniId(unitId);

                            //kiem tra group
                            String group = StringUtil.encodeHTML(data.getLevel2Name());
                            if (GenericValidator.isBlankOrNull(group)) {
                                group = "Other";
                            }
                            int gmId = searchGroupMaterial(group);
                            if (gmId == 0) {
                                gmId = insertGroupMaterial(group);
                            }
                            bean.setGroId(gmId);
                            String speText = "";
                            //kiem tra spec1
                            String specSign = StringUtil.encodeHTML(data.getSpec1());
                            if (!GenericValidator.isBlankOrNull(specSign)) {
                                int spec1 = searchSpec1(specSign);
                                SpeBean spe = new SpeBean();
                                spe.setSpe1Id(spec1);
                                spe.setSpe("1");
                                spe.setSign(data.getSpec1());
                                spe.setNote(data.getSpec1Text());
                                if (spec1 == 0) {
                                    spec1 = insertSpec(spe);
                                } else {
                                    updateSpec(spe);
                                }
                                bean.setSpe1Id(spec1);
                                speText += spec1 + ";";
                                //kiem tra spec2
                                specSign = StringUtil.encodeHTML(data.getSpec2());
                                if (!GenericValidator.isBlankOrNull(specSign)) {
                                    int spec2 = searchSpec2(specSign, spec1);
                                    spe = new SpeBean();
                                    spe.setSpe2Id(spec2);
                                    spe.setSpe("2");
                                    spe.setSign(data.getSpec2());
                                    spe.setSpe1Id(spec1);
                                    spe.setNote(data.getSpec2Text());
                                    if (spec2 == 0) {
                                        spec2 = insertSpec(spe);
                                    } else {
                                        updateSpec(spe);
                                    }
                                    bean.setSpe2Id(spec2);
                                    speText += spec2 + ";";
                                    //kiem tra spec3
                                    specSign = StringUtil.encodeHTML(data.getSpec3());
                                    if (!GenericValidator.isBlankOrNull(specSign)) {
                                        int spec3 = searchSpec3(specSign, spec2);
                                        spe = new SpeBean();
                                        spe.setSpe3Id(spec3);
                                        spe.setSpe("3");
                                        spe.setSign(data.getSpec3());
                                        spe.setSpe2Id(spec2);
                                        spe.setNote(data.getSpec3Text());
                                        if (spec3 == 0) {
                                            spec3 = insertSpec(spe);
                                        } else {
                                            updateSpec(spe);
                                        }
                                        bean.setSpe3Id(spec3);
                                        speText += spec3 + ";";
                                        //kiem tra spec4
                                        specSign = StringUtil.encodeHTML(data.getSpec4());
                                        if (!GenericValidator.isBlankOrNull(specSign)) {
                                            int spec4 = searchSpec4(specSign, spec3);
                                            spe = new SpeBean();
                                            spe.setSpe4Id(spec4);
                                            spe.setSpe("4");
                                            spe.setSign(data.getSpec4());
                                            spe.setSpe3Id(spec3);
                                            spe.setNote(data.getSpec4Text());
                                            if (spec4 == 0) {
                                                spec4 = insertSpec(spe);
                                            } else {
                                                updateSpec(spe);
                                            }
                                            bean.setSpe4Id(spec4);
                                            speText += spec4 + ";";

                                            //kiem tra spec5
                                            specSign = StringUtil.encodeHTML(data.getSpec5());
                                            if (!GenericValidator.isBlankOrNull(specSign)) {
                                                int spec5 = searchSpec5(specSign, spec4);
                                                spe = new SpeBean();
                                                spe.setSpe5Id(spec5);
                                                spe.setSpe("5");
                                                spe.setSign(data.getSpec5());
                                                spe.setSpe4Id(spec4);
                                                spe.setNote(data.getSpec5Text());
                                                if (spec5 == 0) {
                                                    spec5 = insertSpec(spe);
                                                } else {
                                                    updateSpec(spe);
                                                }
                                                bean.setSpe5Id(spec5);
                                                speText += spec5 + ";";
                                            } else {
                                                speText += "???;";
                                            }
                                        } else {
                                            speText += "???;";
                                        }
                                    } else {
                                        speText += "???;???;";
                                    }
                                } else {
                                    speText += "???;???;???;";
                                }
                            } else {
                                speText += "???;???;???;???;";
                            }
                            speText += "???";
                            bean.setSpe(speText);
                            if (matId == 0) {
                                matId = insertMaterial(bean);
                            } else {
                                updateMaterial(bean);
                            }
//                            if (!GenericValidator.isBlankOrNull(data.getContract())) {
//                                ContractBean conBean = searchContract(data.getContract(), 0);
//                                if (conBean == null) {
//                                    int venId = searchVendor(data.getVendor());
//                                    if (venId == 0) {
//                                        venId = insertVendor(data.getVendor());
//                                    }
//
//                                    conBean = new ContractBean();
//                                    conBean.setVenId(venId);
//                                    conBean.setContractNumber(data.getContract());
//                                    conBean.setCreatedDate(DateUtil.today("dd/MM/yyyy"));
//                                    conBean.setSignDate(DateUtil.today("dd/MM/yyyy"));
//                                    conBean.setEffectedDate(correctDate(data.getEffectDate()));
//                                    conBean.setExpireDate(correctDate(data.getExpireDate()));
//                                    conBean.setCurrency(data.getCurrency());
//                                    conBean.setKind(ContractBean.KIND_CONTRACT);
//                                    conBean.setCreatedEmp(empId);
//                                    insertContract(conBean);
//                                    conId = conBean.getConId();
//                                }
//
//                                MaterialPriceBean mpBean = new MaterialPriceBean();
//                                mpBean.setConId(conId);
//                                mpBean.setContractNumber(data.getContract());
//                                mpBean.setVendorName(data.getVendor());
//                                mpBean.setEffectedDate(conBean.getEffectedDate());
//                                mpBean.setExpireDate(conBean.getExpireDate());
//                                mpBean.setMatId(matId);
//                                mpBean.setPrice(data.getPrice());
//                                insertContractPrice(mpBean);
//                            }
                        }
                    }
                }
            }
        } catch (XLSDataReadException e) {
            LogUtil.error("FAILED:Import Material-" + e.getMessage());
        } catch (Exception ex) {
            LogUtil.error("FAILED:Import Material-" + ex.getMessage());
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
            if (bean != null) {
                reqId = bean.getReqId();
            }
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

    private int searchMaterialByCode(String code) {
        int matId = 0;
        MaterialDAO matDAO = new MaterialDAO();
        try {
            matId = matDAO.getMaterialIdByCode(code);
        } catch (Exception ex) {
        }
        return matId;
    }

    private int insertMaterial(MaterialBean bean) {
        int matId = 0;
        try {
            MaterialDAO matDAO = new MaterialDAO();
            matId = matDAO.insertMaterial(bean);
        } catch (Exception ex) {
        }
        return matId;
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
        }
    }

    private void insertContractPrice(MaterialPriceBean bean) {
        ContractDAO conDAO = new ContractDAO();
        try {
            conDAO.saveContractMaterialPrice(bean);
//            bean.set(conId);
        } catch (Exception ex) {
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

    private int searchUnit(String name) {
        int unitId = 0;
        UnitDAO unitDAO = new UnitDAO();
        try {
            UnitBean bean = unitDAO.getUnitByName(name);
            if (bean != null) {
                unitId = bean.getUniId();
            }
        } catch (Exception ex) {
        }
        return unitId;
    }

    private int insertUnit(String name) {
        int unitId = 0;
        UnitDAO unitDAO = new UnitDAO();
        try {
            UnitBean bean = new UnitBean();
            bean.setUnitVn(name);
            unitId = unitDAO.insertUnit(bean);
        } catch (Exception ex) {
        }
        return unitId;
    }

    private int searchGroupMaterial(String name) {
        int gmId = 0;
        GroupMaterialDAO gmDAO = new GroupMaterialDAO();
        try {
            GroupMaterialBean bean = gmDAO.getGroupMaterialByName(name);
            if (bean != null) {
                gmId = bean.getGroId();
            }
        } catch (Exception ex) {
        }
        return gmId;
    }

    private int insertGroupMaterial(String name) {
        int gmId = 0;
        try {
            GroupMaterialDAO gmDAO = new GroupMaterialDAO();
            GroupMaterialBean bean = new GroupMaterialBean();
            bean.setName(name);
            gmId = gmDAO.insertGroupMaterial(bean);
        } catch (Exception ex) {
        }
        return gmId;
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

    private void updateMaterial(MaterialBean bean) {
        try {
            MaterialDAO matDAO = new MaterialDAO();
            matDAO.updateMaterial(bean);
        } catch (Exception ex) {
        }
    }

    private int searchSpec1(String sign) {
        int specId = 0;
        SpeDAO dao = new SpeDAO();
        try {
            SpeBean bean = dao.getSpe1BySign(sign);
            if (bean != null) {
                specId = bean.getSpe1Id();
            }
        } catch (Exception ex) {
        }
        return specId;
    }

    private int searchSpec2(String sign, int spec1) {
        int specId = 0;
        SpeDAO dao = new SpeDAO();
        try {
            SpeBean bean = dao.getSpe2BySign(sign, spec1);
            if (bean != null) {
                specId = bean.getSpe2Id();
            }
        } catch (Exception ex) {
        }
        return specId;
    }

    private int searchSpec3(String sign, int spec2) {
        int specId = 0;
        SpeDAO dao = new SpeDAO();
        try {
            SpeBean bean = dao.getSpe3BySign(sign, spec2);
            if (bean != null) {
                specId = bean.getSpe3Id();
            }
        } catch (Exception ex) {
        }
        return specId;
    }

    private int searchSpec4(String sign, int spec3) {
        int specId = 0;
        SpeDAO dao = new SpeDAO();
        try {
            SpeBean bean = dao.getSpe4BySign(sign, spec3);
            if (bean != null) {
                specId = bean.getSpe4Id();
            }
        } catch (Exception ex) {
        }
        return specId;
    }

    private int searchSpec5(String sign, int spec4) {
        int specId = 0;
        SpeDAO dao = new SpeDAO();
        try {
            SpeBean bean = dao.getSpe5BySign(sign, spec4);
            if (bean != null) {
                specId = bean.getSpe5Id();
            }
        } catch (Exception ex) {
        }
        return specId;
    }

    private int insertSpec(SpeBean spe) {
        int speId = 0;
        try {
            SpeDAO dao = new SpeDAO();
            speId = dao.insertSpe(spe);
        } catch (Exception ex) {
        }
        return speId;
    }

    private void updateSpec(SpeBean spe) {
        try {
            SpeDAO dao = new SpeDAO();
            dao.updateSpe(spe.getSpe(), spe);
        } catch (Exception ex) {
        }
    }
}
