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
import com.venus.mc.bean.MaterialBean;
import com.venus.mc.bean.RequestBean;
import com.venus.mc.bean.RequestDetailBean;
import com.venus.mc.bean.TenderPlanBean;
import com.venus.mc.bean.TenderPlanDetailBean;
import com.venus.mc.bean.UnitBean;
import com.venus.mc.bean.VendorBean;
import com.venus.mc.core.BaseAction;
import com.venus.mc.dao.ContractDAO;
import com.venus.mc.dao.MaterialDAO;
import com.venus.mc.dao.RequestDAO;
import com.venus.mc.dao.TenderPlanDAO;
import com.venus.mc.dao.UnitDAO;
import com.venus.mc.dao.VendorDAO;
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
public class RequestImportAction extends BaseAction {

    private int orgId = 4;//phong ke hoach
    private int empId = 213;//thi
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

    private void importProcess(HttpServletRequest request) {
        String fileName = "hopdong_data";
        if (!GenericValidator.isBlankOrNull(request.getParameter("name"))) {
            fileName = request.getParameter("name");
        }
        fileName += ".xls";
        String xmlConfig = request.getSession().getServletContext().getRealPath("/data_imported/request_template.xml");
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
                    RequestDataBean data = null;
                    String requestNumber = "";
                    int reqId = 0;
                    for (int i = 0; i < lst.size(); i++) {
                        data = (RequestDataBean) lst.get(i);
                        if (GenericValidator.isBlankOrNull(data.getRequestNumber())) {
                            continue;
                        }
                        requestNumber = data.getRequestNumber();
                        reqId = searchRequest(requestNumber);
                        reqBean = new RequestBean();
                        reqBean.setOrgId(data.getProject());
                        reqBean.setCreatedOrg(data.getOrganization());
                        reqBean.setKind(RequestBean.REQUEST);
                        reqBean.setRequestNumber(requestNumber);
                        reqBean.setCreatedDate(DateUtil.today("dd/MM/yyyy"));
                        reqBean.setApproveSuggest("1");//mua moi
                        reqBean.setStatusSuggest(1);//binh thuong
                        reqBean.setCertificateRequire("");
                        reqBean.setWhichUse(RequestFormBean.WHICHUSE_PROJECT);
                        reqBean.setDescription("");
                        reqBean.setCheckComment("");
                        reqBean.setStoreComment("");
                        reqBean.setBomComment("");
                        reqBean.setPlandepComment("");
                        reqBean.setOrgHandle(orgId + "");
                        reqBean.setIntermemoSubject("");
                        reqBean.setCreatedEmp(data.getEmployee());
                        reqBean.setCheckApprove(1);
                        reqBean.setStoreApprove(1);
                        reqBean.setBomApprove(1);
                        reqBean.setPlandepApprove(1);
                        reqBean.setAssignedEmp(empId);
                        reqBean.setApproveEmp(reqBean.getCreatedEmp());
                        reqBean.setBomAgreeDate(reqBean.getCreatedDate());
                        reqBean.setStatus(RequestBean.STATUS_HANDLE);
//                        reqBean.setPurchaseOrg(orgId+"");
                        if (reqId == 0) {
                            insertRequest(reqBean);
                            reqId = reqBean.getReqId();
                        } else {
                            updateRequest(reqBean);
                        }

                        String code = data.getMatCode();
                        int matId = searchMaterialByCode(code);
                        if (matId == 0) {
                            continue;
                        }

//                        int reqDetId = searchRequestDetail(code, reqId, data.getContractNumber());
                        int reqDetId = searchRequestDetail(reqId, matId);

                        if (reqDetId == 0) {
                            RequestDetailBean reqDet = new RequestDetailBean();
                            reqDet.setReqId(reqId);
                            reqDet.setMatId(matId);
                            reqDet.setUnit(data.getUnit());
//                            reqDet.setPresentQuantity();
                            reqDet.setAdditionalQuantity(data.getQuantity());
//                            reqDet.setProvideDate();
//                            reqDet.setCheckAppro();
//                            reqDet.setPlandepAppro();
//                            reqDet.setIntermemoNote();
                            reqDet.setStepId(RequestBean.STEP_REQ);
                            reqDet.setStep(MCUtil.getBundleString("message.request"));
//                            reqDet.setTenId(tenId);
//                            reqDet.setConId(data.getContract());
                            reqDet.setRequestQuantity(reqDet.getAdditionalQuantity());
                            reqDet.setEmpId(data.getEmployee());
//                            reqDet.setRemainQuantity(0);
                            insertReqDetail(reqDet);
                        }
                    }
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

    private int searchContract(String name, int kind) {
        ContractDAO conDAO = new ContractDAO();
        int conId = 0;
        try {
            ContractBean bean = conDAO.getContractByNumber(name, kind);
            if (bean != null) {
                conId = bean.getConId();
            }
        } catch (Exception ex) {
        }
        return conId;
    }

    private void insertRequest(RequestBean bean) {
        try {
            RequestDAO reqDAO = new RequestDAO();
            int reqId = reqDAO.insertRequest(bean);
            bean.setReqId(reqId);
        } catch (Exception ex) {
        }
    }

    private void updateRequest(RequestBean bean) {
        try {
            RequestDAO reqDAO = new RequestDAO();
            reqDAO.updateRequest(bean);
            reqDAO.updateRequestCreatedEmp(bean);
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

    private int searchMaterial(String name) {
        int matId = 0;
        MaterialDAO matDAO = new MaterialDAO();
        try {
            MaterialBean bean = matDAO.getMaterialByName(name);
            if (bean != null) {
                matId = bean.getMatId();
            }
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

    private int searchRequestDetail(String materialCode, int reqId, int conId) {
        int result = 0;
        try {
            RequestDAO reqDAO = new RequestDAO();
            result = reqDAO.getRequestDetailByMaterial(reqId, materialCode, conId);
        } catch (Exception ex) {
        }
        return result;
    }

    private int searchRequestDetail(int reqId, int matId) {
        int result = 0;
        try {
            RequestDAO reqDAO = new RequestDAO();
            result = reqDAO.getRequestDetail(reqId, matId);
        } catch (Exception ex) {
        }
        return result;
    }
}
