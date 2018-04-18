/*
 *
 * Created on April 13, 2007, 2:08 PM
 */
package com.venus.mc.importData;

import com.venus.core.util.DateUtil;
import com.venus.core.util.GenericValidator;
import com.venus.mc.bean.ContractBean;
import com.venus.mc.bean.ContractDetailBean;
import com.venus.mc.bean.DnBean;
import com.venus.mc.bean.DnDetailBean;
import com.venus.mc.bean.MaterialStoreRequestBean;
import com.venus.mc.bean.MivBean;
import com.venus.mc.bean.MivDetailBean;
import com.venus.mc.bean.MrirBean;
import com.venus.mc.bean.MrirDetailBean;
import com.venus.mc.bean.MsvBean;
import com.venus.mc.bean.MsvMaterialBean;
import com.venus.mc.bean.RequestBean;
import com.venus.mc.bean.RequestDetailBean;
import com.venus.mc.bean.RfmBean;
import com.venus.mc.bean.RfmDetailBean;
import com.venus.mc.contract.ContractFormBean;
import com.venus.mc.core.BaseAction;
import com.venus.mc.dao.ContractDAO;
import com.venus.mc.dao.DnDAO;
import com.venus.mc.dao.MaterialDAO;
import com.venus.mc.dao.MaterialStoreRequestDAO;
import com.venus.mc.dao.MivDAO;
import com.venus.mc.dao.MrirDAO;
import com.venus.mc.dao.MsvDAO;
import com.venus.mc.dao.RequestDAO;
import com.venus.mc.dao.RfmDAO;
import com.venus.mc.dao.StoreDAO;
import com.venus.mc.request.RequestFormBean;
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
public class PurchaseImportAction extends BaseAction {

    private int IMPORTER = 1;//admin
    private int orgHandle = 1;
//    private int stoId = 0;
//    private int orgId = 0;

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
//        String xmlConfig = request.getSession().getServletContext().getRealPath("/data_imported/purchase_template.xml");
//        String dataXLS = request.getSession().getServletContext().getRealPath("/data_imported/purchase_data.xls");
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
//                    PurchaseDataBean data = null;
//                    RequestBean req = null;
//                    RequestDetailBean reqDet = null;
//                    String reqNumber = "";
//                    int dnId = 0;
//                    double quantity = 0;
//                    for (int i = 0; i < lst.size(); i++) {
//                        data = (PurchaseDataBean) lst.get(i);
//                        if (GenericValidator.isBlankOrNull(data.getRequestNumber())) {
//                            continue;
//                        }
//                        if (!GenericValidator.isBlankOrNull(data.getMivNumber())
//                                && (GenericValidator.isBlankOrNull(data.getMsvNumber())
//                                || GenericValidator.isBlankOrNull(data.getMrirNumber()))) {
//                            continue;
//                        }
//                        if (!GenericValidator.isBlankOrNull(data.getMsvNumber())
//                                && GenericValidator.isBlankOrNull(data.getMrirNumber())) {
//                            continue;
//                        }
//                        if (data.getXKCT() > 0) {
//                            data.setReceiveOrg(10);
//                            quantity = data.getXKCT();
//                        } else if (data.getXCK() > 0) {
//                            data.setReceiveOrg(12);
//                            quantity = data.getXCK();
//                        } else if (data.getXDTD() > 0) {
//                            data.setReceiveOrg(11);
//                            quantity = data.getXDTD();
//                        } else if (data.getXDVTH() > 0) {
//                            data.setReceiveOrg(13);
//                            quantity = data.getXDVTH();
//                        } else if (data.getXK() > 0) {
//                            quantity = data.getXK();
////                            data.setReceiveOrg(11);
//                        }
//                        String code = data.getMaterial();
//                        if (code.equals("002.001.010.001")) {
//                            int a = 1;
//                        }
//                        int matId = searchMaterialByCode(code);
//                        if (matId == 0) {
//                            continue;
//                        }
//                        ContractBean con = null;
//                        reqNumber = data.getRequestNumber();
//                        req = searchRequest(reqNumber);
//                        if (req == null) {
//                            req = new RequestBean();
//                            //req.setCreatedOrg();
//                            req.setKind(RequestBean.REQUEST);
//                            req.setRequestNumber(data.getRequestNumber());
//                            req.setCreatedDate(DateUtil.today("dd/MM/yyyy"));
//                            req.setApproveSuggest("1");
//                            req.setStatusSuggest(1);
//                            req.setWhichUse(RequestFormBean.WHICHUSE_PROJECT);
//                            req.setDescription("");
//                            req.setCheckComment("");
//                            req.setStoreComment("");
//                            req.setBomComment("");
//                            req.setPlandepComment("");
//                            req.setOrgHandle(orgHandle + "");
//                            req.setOrgRefer("");
//                            req.setOrgPaid("");
//                            req.setIntermemoSubject("");
//                            req.setCreatedEmp(IMPORTER);
//                            req.setCheckApprove(1);
//                            req.setStoreApprove(1);
//                            req.setBomApprove(1);
//                            req.setBomAgreeDate(DateUtil.today("dd/MM/yyyy"));
//                            req.setPlandepApprove(1);
//                            req.setAssignedEmp(IMPORTER);
//                            req.setApproveEmp(IMPORTER);
//                            req.setProId(data.getProject());
//
//                            insertReq(req);
//                        }
//
//                        int reqDetId = searchRequestDetail(data.getMaterial(), req.getReqId(), data.getContractQuantity());
//                        if (reqDetId == 0) {
//                            //insert chi tiet cua req
//                            reqDet = new RequestDetailBean();
//                            reqDet.setReqId(req.getReqId());
//                            reqDet.setMatId(matId);
//                            reqDet.setUnit(data.getUnit());
//                            reqDet.setAdditionalQuantity(data.getContractQuantity());
//                            //reqDet.setStep(MCUtil.getBundleString(""));
//                            //reqDet.setStepId();
//                            reqDet.setRequestQuantity(reqDet.getAdditionalQuantity());
//                            insertReqDetail(reqDet);
//                            reqDetId = reqDet.getDetId();
//                        }
//
//
//                        String conNumber = "";
//                        if (!GenericValidator.isBlankOrNull(data.getContractNumber())) {
//                            conNumber = data.getContractNumber();
//                        } else {
//                            conNumber = "auto-contract-" + data.getRequestNumber();
//                        }
//                        con = searchContract(conNumber);
//                        if (con == null) {//insert contract
//                            con = new ContractBean();
//                            con.setResponseEmp(IMPORTER);
//                            con.setKind(ContractBean.KIND_CONTRACT);
//                            con.setContractNumber(conNumber);
//                            insertContract(con);
//
//                            //insert delivery_notice auto
//                            dnId = insertDeliveryNotice(data, con.getConId(), MCUtil.getOrganizationID(request.getSession()));
//                        } else {
//                            dnId = getDn(con.getConId());
//                        }
//
//                        int conDetId = searchContractDetail(con.getConId(), code, reqDetId);
//                        int dnd = 0;
//                        if (conDetId == 0) {
//                            //insert dn_detail
//                            dnd = insertDnDetail(data, reqDet, dnId);
//
//                            //insert contract detail
//                            ContractDetailBean conDet = new ContractDetailBean();
//                            conDet.setPrice(data.getPrice());
////                            conDet.setTotal(data.getTotal());
////                            conDet.setVat(data.getVAT());
//                            conDet.setConId(con.getConId());
//                            conDet.setMatId(matId);
//                            conDet.setQuantity(data.getContractQuantity());
//                            conDet.setReqDetailId(reqDetId);
//                            conDet.setStatus(ContractFormBean.MATERIAL_STATUS_NORMAL);
//                            insertContractDetail(conDet);
//                        } else {
//                            dnd = getDnDetail(dnId, reqDetId);
//                        }
//
//
//                        //insert mrir
//                        String mrir = data.getMrirNumber();
//                        if (!GenericValidator.isBlankOrNull(mrir)) {
//                            int mrirId = searchMrir(mrir);
//                            if (mrirId == 0) {
//                                MrirBean mrirBean = new MrirBean();
//                                mrirBean.setDnId(dnId);
//                                mrirBean.setMrirNumber(mrir);
//                                mrirBean.setConId(con.getConId());
//                                mrirBean.setConNumber(con.getContractNumber());
//                                if (!GenericValidator.isBlankOrNull(data.getMsvNumber())) {
//                                    mrirBean.setKind(1);
//                                } else {
//                                    mrirBean.setKind(0);
//                                }
//                                mrirBean.setStatus(1);
//                                insertMrir(mrirBean);
//                                mrirId = mrirBean.getMrirId();
//                            }
//
//                            int mrDetId = getMrirDetail(mrirId, reqDetId, dnId);
//                            if (mrDetId == 0) {
//                                //insert mrir_detail
//                                insertMrirDetail(data, reqDet, mrirId, dnd);
//                            }
//
//                            if (quantity == 0) {
//                                continue;
//                            }
//
//                            //insert msv
//                            String msv = data.getMsvNumber();
//                            if (GenericValidator.isBlankOrNull(msv)) {
//                                msv = "auto-msv-" + data.getMrirNumber();
//                            }
//                            int msvId = searchMsv(msv);
//                            if (msvId == 0) {
//                                MsvBean msvBean = new MsvBean();
//                                msvBean.setMrirId(mrirId);
//                                msvBean.setMsvNumber(data.getMsvNumber());
//                                msvBean.setMrirId(mrirId);
//                                msvBean.setStoId(data.getStore());
//                                msvBean.setCreatedEmpId(IMPORTER);
//                                msvBean.setCreatedDate(data.getMsvDate());
//                                insertMsv(msvBean);
//                                msvId = msvBean.getMsvId();
//                            }
//
//                            int msvDetId = getMsvDetail(msvId, reqDetId);
//                            if (msvDetId == 0) {
//                                //insert msv_detail
//                                insertMsvDetail(data, reqDet, msvId);
//                            }
//
//                            //insert rfm
//                            String rfm = data.getRfmNumber();
//                            if (GenericValidator.isBlankOrNull(rfm)) {
//                                rfm = "auto-rfm-" + data.getMrirNumber();
//                            }
//                            int rfmId = searchRfm(rfm);
//                            if (!GenericValidator.isBlankOrNull(rfm)) {
//                                if (rfmId == 0) {
//                                    RfmBean rfmBean = new RfmBean();
//                                    rfmBean.setRfmNumber(data.getRfmNumber());
//                                    rfmBean.setCreatedEmp(IMPORTER);
//                                    rfmBean.setOrgId(data.getProject());
//                                    rfmBean.setStoId(data.getStore());
//                                    rfmBean.setProId(data.getProject());
//                                    rfmBean.setRequestOrg(data.getOrg());
//                                    rfmBean.setStoreApprove(1);
//                                    rfmBean.setAccountingApprove(1);
//                                    rfmBean.setStatusReserveQuantity(1);
//                                    insertRfm(rfmBean);
//                                    rfmId = rfmBean.getRfmId();
//                                }
//                            }
//
//                            int rfmDetId = getRfmDetail(rfmId, reqDetId);
//                            if (rfmDetId == 0) {
//                                //insert rmf_detail
//                                rfmDetId = insertRfmDetail(data, reqDet, rfmId, msvId);
//                            }
//
//                            //insert miv
//                            String miv = data.getMivNumber();
//                            int mivId = 0;
//                            if (!GenericValidator.isBlankOrNull(miv)) {
//                                if (GenericValidator.isBlankOrNull(miv)) {
//                                    miv = "auto-miv-" + data.getMrirNumber();
//                                }
//                            }
//                            mivId = searchMiv(miv);
//                            if (mivId == 0) {
//                                MivBean mivBean = new MivBean();
//                                mivBean.setMivNumber(data.getMivNumber());
//                                mivBean.setRfmId(rfmId);
//                                mivBean.setOrgId(data.getProject());
//                                mivBean.setStoId(data.getStore());
//                                mivBean.setProId(data.getProject());
//                                mivBean.setRequestOrg(data.getOrg());
//                                mivBean.setMivKind(1);
//                                insertMiv(mivBean);
//                                mivId = mivBean.getMivId();
//                            }
//
//                            //insert msv_detail
//                            insertMivDetail(data, reqDet, mivId, rfmDetId);
////                            if (data.getBalance() > 0) {
////                                insertMaterialStoreRequest(data.getStore(), matId, data.getBalance());
////                            }
//                        }
//                    }
//                }
//            }
//        } catch (XLSDataReadException e) {
//            e.printStackTrace();
//        } catch (Exception ex) {
//            ex.printStackTrace();
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
        String fileName = "";
        if (!GenericValidator.isBlankOrNull(request.getParameter("name"))) {
            String names = request.getParameter("name");
            String[] ns = names.split(",");
            for (int i = 0; i < ns.length; i++) {
                fileName = ns[i] + ".xls";
                runImport(request, fileName);
            }
        }
    }

    private void runImport(HttpServletRequest request, String fileName) {
        String xmlConfig = request.getSession().getServletContext().getRealPath("/data_imported/purchase_template.xml");
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
                    PurchaseDataBean data = null;
                    RequestBean req = null;
                    RequestDetailBean reqDet = null;
                    String reqNumber = "";
                    int dnId = 0;
                    double quantity = 0;
                    for (int i = 0; i < lst.size(); i++) {
                        data = (PurchaseDataBean) lst.get(i);
                        quantity = 0;
                        dnId = 0;
                        if (GenericValidator.isBlankOrNull(data.getMaterial())) {
                            continue;
                        }
                        if (GenericValidator.isBlankOrNull(data.getRequestNumber())) {
                            if (GenericValidator.isBlankOrNull(data.getContractNumber())) {
                                continue;
                            } else {
                                data.setRequestNumber("auto-" + data.getContractNumber());
                            }
                        }
                        String conNumber = "";
                        if (!GenericValidator.isBlankOrNull(data.getContractNumber())) {
                            conNumber = data.getContractNumber();
                        } else {
                            conNumber = "auto-contract-" + data.getRequestNumber();
                        }
                        if (data.getXKCT1() > 0) {
                            quantity = data.getXKCT1();
                        } else if (data.getXKCT2() > 0) {
                            quantity = data.getXKCT2();
                        } else if (data.getXCK() > 0) {
                            quantity = data.getXCK();
                        } else if (data.getXDTD() > 0) {
                            quantity = data.getXDTD();
                        } else if (data.getXDVTH() > 0) {
                            quantity = data.getXDVTH();
                        } else if (data.getXTBTH() > 0) {
                            quantity = data.getXTBTH();
                        } else if (data.getXK() > 0) {
                            quantity = data.getXK();
                        }
                        String code = data.getMaterial();
                        int matId = searchMaterialByCode(code);
                        if (matId == 0) {
                            continue;
                        }
                        ContractBean con = null;
                        reqNumber = data.getRequestNumber();
                        req = searchRequest(reqNumber);
                        if (req == null) {
                            req = new RequestBean();
                            //req.setCreatedOrg();
                            req.setKind(RequestBean.REQUEST);
                            req.setRequestNumber(data.getRequestNumber());
                            req.setCreatedDate(DateUtil.today("dd/MM/yyyy"));
                            req.setApproveSuggest("1");
                            req.setStatusSuggest(1);
                            req.setWhichUse(RequestFormBean.WHICHUSE_PROJECT);
                            req.setDescription("");
                            req.setCheckComment("");
                            req.setStoreComment("");
                            req.setBomComment("");
                            req.setPlandepComment("");
                            req.setOrgHandle(orgHandle + "");
                            req.setOrgRefer("");
                            req.setOrgPaid("");
                            req.setIntermemoSubject("");
                            req.setCreatedEmp(IMPORTER);
                            req.setCheckApprove(1);
                            req.setStoreApprove(1);
                            req.setBomApprove(1);
                            req.setBomAgreeDate(DateUtil.today("dd/MM/yyyy"));
                            req.setPlandepApprove(1);
                            req.setAssignedEmp(IMPORTER);
                            req.setApproveEmp(IMPORTER);
                            req.setProId(data.getProject());

                            insertReq(req);
                        }

                        reqDet = searchRequestDetail(data.getMaterial(), req.getReqId(), data.getContractQuantity(), data.getMrirNumber());
                        if (reqDet == null) {
                            //insert chi tiet cua req
                            reqDet = new RequestDetailBean();
                            reqDet.setReqId(req.getReqId());
                            reqDet.setMatId(matId);
                            reqDet.setUnit(data.getUnit());
                            reqDet.setAdditionalQuantity(data.getContractQuantity());
                            //reqDet.setStep(MCUtil.getBundleString(""));
                            //reqDet.setStepId();
                            reqDet.setRequestQuantity(reqDet.getAdditionalQuantity());
                            insertReqDetail(reqDet);
                        }

                        con = searchContract(conNumber);
                        if (con == null) {//insert contract
                            con = new ContractBean();
                            con.setResponseEmp(IMPORTER);
                            con.setKind(ContractBean.KIND_CONTRACT);
                            con.setContractNumber(conNumber);
                            con.setCreatedEmp(IMPORTER);
                            insertContract(con);

                            //insert delivery_notice auto
                            dnId = insertDeliveryNotice(data, con.getConId(), MCUtil.getOrganizationID(request.getSession()));
                        } else {
                            dnId = getDn(con.getConId());
                            if (dnId == 0) {
                                dnId = insertDeliveryNotice(data, con.getConId(), MCUtil.getOrganizationID(request.getSession()));
                            }
                        }

                        int conDetId = searchContractDetail(con.getConId(), code, reqDet.getDetId());
                        int dnd = 0;
                        if (conDetId == 0) {
                            //insert dn_detail
                            dnd = searchDnDetail(dnId, reqDet.getDetId());
                            if (dnd == 0) {
                                dnd = insertDnDetail(data, reqDet, dnId);
                            }

                            //insert contract detail
                            ContractDetailBean conDet = new ContractDetailBean();
                            conDet.setPrice(data.getPrice());
//                            conDet.setTotal(data.getTotal());
//                            conDet.setVat(data.getVAT());
                            conDet.setConId(con.getConId());
                            conDet.setMatId(matId);
                            conDet.setQuantity(data.getContractQuantity());
                            conDet.setReqDetailId(reqDet.getDetId());
                            conDet.setStatus(ContractFormBean.MATERIAL_STATUS_NORMAL);
                            insertContractDetail(conDet);
                        } else {
                            dnd = getDnDetail(dnId, reqDet.getDetId());
                        }

                        //insert mrir
                        String mrir = data.getMrirNumber();
                        if (!GenericValidator.isBlankOrNull(mrir)) {
                            int mrirId = searchMrir(mrir);
                            if (mrirId == 0) {
                                MrirBean mrirBean = new MrirBean();
                                mrirBean.setDnId(dnId);
                                mrirBean.setMrirNumber(mrir);
                                mrirBean.setConId(con.getConId());
                                mrirBean.setConNumber(con.getContractNumber());
                                if (!GenericValidator.isBlankOrNull(data.getMsvNumber())) {
                                    mrirBean.setKind(1);
                                } else {
                                    mrirBean.setKind(0);
                                }
                                mrirBean.setStatus(1);
                                insertMrir(mrirBean);
                                mrirId = mrirBean.getMrirId();
                            }

                            int mrDetId = getMrirDetail(mrirId, reqDet.getDetId(), dnd);
                            if (mrDetId == 0) {
                                //insert mrir_detail
                                mrDetId = insertMrirDetail(data, reqDet, mrirId, dnd);
                            }

                            String msv = data.getMsvNumber();
                            if (GenericValidator.isBlankOrNull(msv) && data.getMsvQuantity() == 0) {
                                continue;
                            }

                            //insert msv
                            if (GenericValidator.isBlankOrNull(msv)) {
                                msv = "auto-msv-" + data.getMrirNumber();
                                data.setMsvNumber(msv);
                            }
                            MsvBean msvBean = searchMsv(msv);
                            int msvId = 0;
                            if (msvBean == null) {
                                msvBean = new MsvBean();
                                msvBean.setMrirId(mrirId);
                                msvBean.setMsvNumber(msv);
                                msvBean.setStoId(data.getStore());
                                msvBean.setCreatedEmpId(IMPORTER);
                                if (!GenericValidator.isBlankOrNull(data.getMsvDate())) {
//                                    msvBean.setCreatedDate(DateUtil.formatDate(DateUtil.convertStringToDate(data.getMsvDate(), "dd/MM/yyyy"), "yyyy-MM-dd"));
                                    msvBean.setCreatedDate(data.getMsvDate());
                                }
                                insertMsv(msvBean);
                                msvId = msvBean.getMsvId();
                            } else {
                                msvId = msvBean.getMsvId();
                            }

                            int msvDetId = getMsvDetail(msvId, reqDet.getDetId());
                            if (msvDetId == 0) {
                                //insert msv_detail
                                insertMsvDetail(data, reqDet, msvId, mrDetId);
                            }

                            if (quantity == 0) {
                                continue;
                            }

                            //insert rfm
                            String rfm = data.getRfmNumber();
                            if (GenericValidator.isBlankOrNull(rfm)) {
                                rfm = "auto-rfm-" + data.getMrirNumber();
                                data.setRfmNumber(rfm);
                            }
                            int rfmId = searchRfm(rfm);
                            if (!GenericValidator.isBlankOrNull(rfm)) {
                                RfmBean rfmBean = new RfmBean();
                                rfmBean.setRfmNumber(rfm);
                                rfmBean.setCreatedEmp(IMPORTER);
                                rfmBean.setStoId(data.getStore());
                                rfmBean.setProId(data.getProject());
                                rfmBean.setRequestOrg(data.getOrg());
                                rfmBean.setOrgId(rfmBean.getRequestOrg());
                                rfmBean.setStoreApprove(1);
                                rfmBean.setAccountingApprove(1);
                                rfmBean.setStatusReserveQuantity(1);
                                if (rfmId == 0) {
                                    insertRfm(rfmBean);
                                    rfmId = rfmBean.getRfmId();
                                } else {
                                    updateRfm(rfmId, rfmBean);
                                }
                            }

                            int rfmDetId = getRfmDetail(rfmId, reqDet.getDetId());
                            if (rfmDetId == 0) {
                                //insert rmf_detail
                                rfmDetId = insertRfmDetail(data, reqDet, rfmId, msvId);
                            } else {
                                if (getMivDetail(data.getMivNumber(), reqDet.getDetId(), rfmDetId) == 0) {
//                                    updateRfmDetail(data, reqDet, rfmId, msvId);
                                }
                            }

                            //insert miv
                            String miv = data.getMivNumber();
                            MivBean mivBean = null;
                            if (GenericValidator.isBlankOrNull(miv)) {
                                miv = "auto-miv-" + data.getRfmNumber();
                                data.setMivNumber(miv);
                            }
                            mivBean = searchMiv(miv);
                            if (mivBean == null) {
                                mivBean = new MivBean();
                                mivBean.setMivNumber(miv);
                                mivBean.setRfmId(rfmId);
                                mivBean.setOrgId(data.getOrg());
                                mivBean.setStoId(data.getStore());
                                mivBean.setCreator(IMPORTER);
//                                mivBean.setProId(data.getProject());
//                                mivBean.setRequestOrg(data.getOrg());
                                mivBean.setMivKind(1);
                                if (!GenericValidator.isBlankOrNull(data.getMivDate())) {
//                                    mivBean.setCreatedDate(DateUtil.formatDate(DateUtil.convertStringToDate(data.getMivDate(), "dd/MM/yyyy"), "yyyy-MM-dd"));
                                    mivBean.setCreatedDate(data.getMivDate());
                                }
                                insertMiv(mivBean);
                            }

                            //insert msv_detail
                            if (getMivDetail(mivBean.getMivId(), reqDet.getDetId(), rfmDetId) == 0) {
                                insertMivDetail(data, reqDet, mivBean.getMivId(), rfmDetId);
                            } else {
//                                updateMivDetail(data, reqDet, mivBean.getMivId(), rfmDetId);
                            }
                        }
                    }
                }
            }
        } catch (XLSDataReadException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
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

    private RequestBean searchRequest(String name) {
        RequestDAO reqDAO = new RequestDAO();
        RequestBean bean = null;
        try {
            bean = reqDAO.getRequestByNumber(name);
        } catch (Exception ex) {
        }
        return bean;
    }

    private void insertReq(RequestBean bean) {
        try {
            RequestDAO reqDAO = new RequestDAO();
            int reqId = reqDAO.insertRequest(bean);
            bean.setReqId(reqId);
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

    private ContractBean searchContract(String name) {
        ContractBean bean = null;
        try {
            ContractDAO conDAO = new ContractDAO();
            bean = conDAO.getContractByNumber(name, ContractBean.KIND_CONTRACT);
        } catch (Exception ex) {
        }
        return bean;
    }

    private void insertContract(ContractBean bean) {
        ContractDAO conDAO = new ContractDAO();
        try {
            int conId = conDAO.insertContract(bean);
            bean.setConId(conId);
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

    private int searchMrir(String name) {
        int mrirId = 0;
        MrirDAO mrirDAO = new MrirDAO();
        try {
            MrirBean bean = mrirDAO.getMrirByNumber(name);
            if (bean != null) {
                mrirId = bean.getMrirId();
            }
        } catch (Exception ex) {
        }
        return mrirId;
    }

    private void insertMrir(MrirBean bean) {
        MrirDAO mrirDAO = new MrirDAO();
        try {
            int mrirId = mrirDAO.insertMrir(bean);
            bean.setMrirId(mrirId);
        } catch (Exception ex) {
        }
    }

    private int insertMrirDetail(PurchaseDataBean data, RequestDetailBean reqDet, int mrirId, int dnd) {
        int detId = 0;
        try {
            MrirDAO mrirDAO = new MrirDAO();
            MrirDetailBean bean = new MrirDetailBean();
            bean.setMrirId(mrirId);
            bean.setMatId(reqDet.getMatId());
            bean.setUnit(reqDet.getUnit());
            bean.setQuantity(reqDet.getAdditionalQuantity());
            bean.setPrice(data.getPrice());
            bean.setReqDetailId(reqDet.getDetId());
            bean.setHeatSerial(data.getHeatNo());
            bean.setCertNo(data.getMrirCerts());
            bean.setLocation(data.getLocation());
            bean.setDndId(dnd);
            bean.setNote(data.getNo());
            detId = mrirDAO.insertMrirDetail(bean);
        } catch (Exception ex) {
        }
        return detId;
    }

    private MsvBean searchMsv(String name) {
        MsvDAO msvDAO = new MsvDAO();
        try {
            MsvBean bean = msvDAO.getMsvByNumber(name);
            return bean;
        } catch (Exception ex) {
        }
        return null;
    }

    private void insertMsv(MsvBean bean) {
        MsvDAO msvDAO = new MsvDAO();
        try {
            int msvId = msvDAO.insertMsv(bean);
            bean.setMsvId(msvId);
        } catch (Exception ex) {
        }
    }

    private void insertMsvDetail(PurchaseDataBean data, RequestDetailBean reqDet, int msvId, int mrirDetId) {
        MsvDAO msvDAO = new MsvDAO();
        try {
            MsvMaterialBean bean = new MsvMaterialBean();
            bean.setMsvId(msvId);
            bean.setMatId(reqDet.getMatId());
            bean.setUnit(reqDet.getUnit());
            bean.setQuantity(data.getMsvQuantity());
            bean.setPrice(data.getPrice());
//            bean.setTotal(data.getTotal());
            bean.setReqDetailId(reqDet.getDetId());
            bean.setMrirDetId(mrirDetId);
            bean.setNote(data.getNo());
            msvDAO.insertMsvMaterial(bean);

            double temp = Double.parseDouble(bean.getQuantity() + "");
            updateMSR(bean.getMatId(), data.getStore(), bean.getPrice(), temp, bean.getReqDetailId(), msvId);
        } catch (Exception ex) {
        }
    }

    private int insertDeliveryNotice(PurchaseDataBean data, int conId, int orgId) {
        DnDAO dnDAO = new DnDAO();
        int dnId = 0;
        try {
            DnBean bean = new DnBean();
            bean.setConId(conId);
            bean.setDnNumber("auto-dn-" + data.getContractNumber());
//            bean.setActualDate(data.getActualDate());
            bean.setCreatedEmp(IMPORTER);
            bean.setCreatedOrg(orgId);
            bean.setCreatedDate(DateUtil.yesterday("dd/MM/yyyy"));
            bean.setExpectedDate(bean.getCreatedDate());
            dnId = dnDAO.insertDn(bean);
            bean.setDnId(dnId);
        } catch (Exception ex) {
        }
        return dnId;
    }

    private int searchDnDetail(int dnId, int reqDetId) {
        int result = 0;
        try {
            DnDAO dnDAO = new DnDAO();
            result = dnDAO.getDnDetailByRequestDetail(dnId, reqDetId);
        } catch (Exception ex) {
        }
        return result;
    }

    private int insertDnDetail(PurchaseDataBean data, RequestDetailBean reqDet, int dnId) {
        DnDAO dnDAO = new DnDAO();
        int dnd = 0;
        try {
            DnDetailBean bean = new DnDetailBean();
            bean.setDnId(dnId);
            bean.setMatId(reqDet.getMatId());
            bean.setUnit(reqDet.getUnit());
            bean.setQuantity(data.getContractQuantity());
            bean.setReqDetailId(reqDet.getDetId());

//            bean.setMoveCreateMrir(data.getMoveCreateMrir());
//            bean.setReceiveMrir(data.getReceiveMrirDate());
//            bean.setMoveCreateMsv(data.getMoveCreateMsv());
//            bean.setReceiveMsv(data.getReceiveMsvDate());
            dnd = dnDAO.insertDnDetail(bean);
            if (!GenericValidator.isBlankOrNull(data.getMrirNumber())) {
                dnDAO.updateDnDetailStatus(dnd, 1);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return dnd;
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

    private int searchRfm(String name) {
        int rfmId = 0;
        RfmDAO rfmDAO = new RfmDAO();
        try {
            RfmBean bean = rfmDAO.getRfmByNumber(0, name);
            if (bean != null) {
                rfmId = bean.getRfmId();
            }
        } catch (Exception ex) {
        }
        return rfmId;
    }

    private void insertRfm(RfmBean bean) {
        RfmDAO rfmDAO = new RfmDAO();
        try {
            int rfmId = rfmDAO.insertRfm(bean);
            bean.setRfmId(rfmId);
        } catch (Exception ex) {
        }
    }

    private void updateRfm(int rfmId, RfmBean newBean) {
        RfmDAO rfmDAO = new RfmDAO();
        try {
            RfmBean bean = rfmDAO.getRfm(rfmId, 0, 0);
            bean.setOrgId(newBean.getRequestOrg());
            rfmDAO.updateRfm(bean);
        } catch (Exception ex) {
        }
    }

    private int insertRfmDetail(PurchaseDataBean data, RequestDetailBean reqDet, int rfmId, int msvId) {
        RfmDAO rfmDAO = new RfmDAO();
        int rfmDetId = 0;
        try {
            RfmDetailBean bean = new RfmDetailBean();
            bean.setRfmId(rfmId);
            bean.setMatId(reqDet.getMatId());
            bean.setMsvId(msvId);
            double quantity = 0;
            if (data.getXKCT1() > 0) {
                quantity = data.getXKCT1();
            } else if (data.getXKCT2() > 0) {
                quantity = data.getXKCT2();
            } else if (data.getXCK() > 0) {
                quantity = data.getXCK();
            } else if (data.getXDTD() > 0) {
                quantity = data.getXDTD();
            } else if (data.getXDVTH() > 0) {
                quantity = data.getXDVTH();
            } else if (data.getXTBTH() > 0) {
                quantity = data.getXTBTH();
            } else if (data.getXK() > 0) {
                quantity = data.getXK();
            }
            bean.setQuantity(quantity);
            bean.setUnit(reqDet.getUnit());
            bean.setPrice(data.getPrice());
            bean.setReqDetailId(reqDet.getDetId());
            bean.setMsvNumber(data.getMsvNumber());
            bean.setStoId(data.getStore());
            bean.setNote(data.getNo());
            rfmDetId = rfmDAO.insertRfmDetail(bean, 0);
            rfmDAO.insertStore(bean, 0);
        } catch (Exception ex) {
        }
        return rfmDetId;
    }

    private MivBean searchMiv(String name) {
        MivDAO mivDAO = new MivDAO();
        try {
            MivBean bean = mivDAO.getMivByNumber(name);
            return bean;
        } catch (Exception ex) {
        }
        return null;
    }

    private void insertMiv(MivBean bean) {
        MivDAO mivDAO = new MivDAO();
        try {
            int mivId = mivDAO.insertMiv(bean);
            bean.setMivId(mivId);
        } catch (Exception ex) {
        }
    }

    private void insertMivDetail(PurchaseDataBean data, RequestDetailBean reqDet, int mivId, int rfmDetId) {
        MivDAO mivDAO = new MivDAO();
        StoreDAO storeDAO = new StoreDAO();
        MaterialStoreRequestDAO msrDAO = new MaterialStoreRequestDAO();
        try {
            MivDetailBean bean = new MivDetailBean();
            bean.setMivId(mivId);
            bean.setMatId(reqDet.getMatId());
            double quantity = 0;
            if (data.getXKCT1() > 0) {
                quantity = data.getXKCT1();
            } else if (data.getXKCT2() > 0) {
                quantity = data.getXKCT2();
            } else if (data.getXCK() > 0) {
                quantity = data.getXCK();
            } else if (data.getXDTD() > 0) {
                quantity = data.getXDTD();
            } else if (data.getXDVTH() > 0) {
                quantity = data.getXDVTH();
            } else if (data.getXTBTH() > 0) {
                quantity = data.getXTBTH();
            } else if (data.getXK() > 0) {
                quantity = data.getXK();
            }
            bean.setQuantity(quantity);
            bean.setUnit(reqDet.getUnit());
            bean.setPrice(data.getPrice());
            bean.setReqDetailId(reqDet.getDetId());
            bean.setStoId(data.getStore());
            bean.setRfmDetId(rfmDetId);
            bean.setPreQuantity(data.getFirst());
            bean.setNote(data.getNo());
            storeDAO.insertStoreLevel2Detail(mivId, data.getStore2(), bean.getMatId(), bean.getQuantity(), bean.getReqDetailId(), data.getStore());
            mivDAO.insertMivDetailForImport(bean);
            msrDAO.updateMaterialStoreRequest(bean);
        } catch (Exception ex) {
        }
    }

    private void updateMivDetail(PurchaseDataBean data, RequestDetailBean reqDet, int mivId, int rfmDetId) {
        MivDAO mivDAO = new MivDAO();
        StoreDAO storeDAO = new StoreDAO();
        MaterialStoreRequestDAO msrDAO = new MaterialStoreRequestDAO();
        try {
            MivDetailBean bean = new MivDetailBean();
            bean.setMivId(mivId);
            bean.setMatId(reqDet.getMatId());
            double quantity = 0;
            if (data.getXKCT1() > 0) {
                quantity = data.getXKCT1();
            } else if (data.getXKCT2() > 0) {
                quantity = data.getXKCT2();
            } else if (data.getXCK() > 0) {
                quantity = data.getXCK();
            } else if (data.getXDTD() > 0) {
                quantity = data.getXDTD();
            } else if (data.getXDVTH() > 0) {
                quantity = data.getXDVTH();
            } else if (data.getXTBTH() > 0) {
                quantity = data.getXTBTH();
            } else if (data.getXK() > 0) {
                quantity = data.getXK();
            }
            bean.setQuantity(quantity);
            bean.setUnit(reqDet.getUnit());
            bean.setPrice(data.getPrice());
            bean.setReqDetailId(reqDet.getDetId());
            bean.setStoId(data.getStore());
            bean.setRfmDetId(rfmDetId);
            bean.setPreQuantity(data.getFirst());
//            storeDAO.insertStoreLevel2Detail(mivId, data.getStore2(), bean.getMatId(), bean.getQuantity(), bean.getReqDetailId(), data.getStore());
            mivDAO.updateMivDetailForImport(bean);
            msrDAO.updateMaterialStoreRequest(bean);
        } catch (Exception ex) {
        }
    }

    //cap nhat so luong ton kho   
    private void updateMSR(int matId, int stoId, double price, double quantity, int reqDetailId, int msvId) {
        MaterialStoreRequestDAO msrDAO = new MaterialStoreRequestDAO();
        MaterialStoreRequestBean msrBean = null;
        try {
            msrBean = msrDAO.getMaterialStoreRequest(matId, stoId, reqDetailId, msvId);
        } catch (Exception ex) {
        }
        if (msrBean == null) {
            try {
                msrDAO.insertMaterialStoreRequest(matId, stoId, price, quantity, reqDetailId, msvId);
            } catch (Exception ex) {
            }
        } else {
            //tang so luong trong kho
            msrBean.setActualQuantity(msrBean.getActualQuantity() + quantity);
            msrBean.setAvailableQuantity(msrBean.getAvailableQuantity() + quantity);
            try {
                msrDAO.updateMaterialStoreRequest(msrBean);
            } catch (Exception ex) {
            }
        }
    }

    private RequestDetailBean searchRequestDetail(String materialCode, int reqId, double quantity, String mrirNumber) {
        RequestDetailBean bean = null;
        try {
            RequestDAO reqDAO = new RequestDAO();
            bean = reqDAO.getRequestDetailByMaterial(reqId, materialCode, quantity, mrirNumber);
        } catch (Exception ex) {
        }
        return bean;
    }

    private int searchContractDetail(int conId, String materialCode, int reqDetId) {
        int result = 0;
        try {
            ContractDAO conDAO = new ContractDAO();
            result = conDAO.getContractDetailByMaterialCode(conId, materialCode, reqDetId);
        } catch (Exception ex) {
        }
        return result;
    }

    private int getDnDetail(int dnId, int reqDetId) {
        int result = 0;
        try {
            DnDAO dnDAO = new DnDAO();
            result = dnDAO.getDnDetailByRequestDetail(dnId, reqDetId);
        } catch (Exception ex) {
        }
        return result;
    }

    private int getDn(int conId) {
        int result = 0;
        try {
            DnDAO dnDAO = new DnDAO();
            result = dnDAO.getDnByContract(conId);
        } catch (Exception ex) {
        }
        return result;
    }

    private int getMrirDetail(int mrirId, int reqDetId, int dndId) {
        int result = 0;
        try {
            MrirDAO mrirDAO = new MrirDAO();
            result = mrirDAO.getMrirDetail(mrirId, reqDetId, dndId);
        } catch (Exception ex) {
        }
        return result;
    }

    private int getMsvDetail(int msvId, int reqDetId) {
        int result = 0;
        try {
            MsvDAO msvDAO = new MsvDAO();
            result = msvDAO.getMsvDetailByRequestDetail(msvId, reqDetId);
        } catch (Exception ex) {
        }
        return result;
    }

    private int getRfmDetail(int rfmId, int reqDetId) {
        int result = 0;
        try {
            RfmDAO rfmDAO = new RfmDAO();
            result = rfmDAO.getRfmDetail(rfmId, reqDetId);
        } catch (Exception ex) {
        }
        return result;
    }

    private int updateRfmDetail(PurchaseDataBean data, RequestDetailBean reqDet, int rfmId, int msvId) {
        RfmDAO rfmDAO = new RfmDAO();
        int rfmDetId = 0;
        try {
            RfmDetailBean bean = new RfmDetailBean();
            bean.setRfmId(rfmId);
            bean.setMatId(reqDet.getMatId());
            bean.setMsvId(msvId);
            double quantity = 0;
            if (data.getXKCT1() > 0) {
                quantity = data.getXKCT1();
            } else if (data.getXKCT2() > 0) {
                quantity = data.getXKCT2();
            } else if (data.getXCK() > 0) {
                quantity = data.getXCK();
            } else if (data.getXDTD() > 0) {
                quantity = data.getXDTD();
            } else if (data.getXDVTH() > 0) {
                quantity = data.getXDVTH();
            } else if (data.getXTBTH() > 0) {
                quantity = data.getXTBTH();
            } else if (data.getXK() > 0) {
                quantity = data.getXK();
            }
            bean.setQuantity(quantity);
            bean.setUnit(reqDet.getUnit());
            bean.setPrice(data.getPrice());
            bean.setReqDetailId(reqDet.getDetId());
            bean.setMsvNumber(data.getMsvNumber());
            bean.setStoId(data.getStore());
            rfmDAO.insertStore(bean, 0);
        } catch (Exception ex) {
        }
        return rfmDetId;
    }

    private int getMivDetail(int mivId, int reqDetId, int rfmDetId) {
        int result = 0;
        try {
            MivDAO mivDAO = new MivDAO();
            MivDetailBean bean = mivDAO.getMivDetail(mivId, reqDetId, rfmDetId);
            if (bean != null) {
                result = bean.getDetId();
            }
        } catch (Exception ex) {
        }
        return result;
    }

    private int getMivDetail(String mivNumber, int reqDetId, int rfmDetId) {
        int result = 0;
        try {
            MivDAO mivDAO = new MivDAO();
            MivDetailBean bean = mivDAO.getMivDetail(mivNumber, reqDetId, rfmDetId);
            if (bean != null) {
                result = bean.getDetId();
            }
        } catch (Exception ex) {
        }
        return result;
    }

    private DnBean searchDn(String name) {
        DnDAO dnDAO = new DnDAO();
        try {
            DnBean bean = dnDAO.getDnByNumber(name);
            return bean;
        } catch (Exception ex) {
        }
        return null;
    }
}
