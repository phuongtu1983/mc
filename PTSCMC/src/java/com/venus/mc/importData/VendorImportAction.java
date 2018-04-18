/*
 *
 * Created on April 13, 2007, 2:08 PM
 */
package com.venus.mc.importData;

import com.venus.core.util.GenericValidator;
import com.venus.core.util.LogUtil;
import com.venus.core.util.StringUtil;
import com.venus.mc.bean.GroupMaterialBean;
import com.venus.mc.bean.VendorBean;
import com.venus.mc.bean.VendorContactBean;
import com.venus.mc.core.BaseAction;
import com.venus.mc.dao.GroupMaterialDAO;
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
 * @author phuontu
 * @version
 */
public class VendorImportAction extends BaseAction {

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
        String fileName = "vendor_data";
        if (!GenericValidator.isBlankOrNull(request.getParameter("name"))) {
            fileName = request.getParameter("name");
        }
        fileName += ".xls";
        String xmlConfig = request.getSession().getServletContext().getRealPath("/data_imported/vendor_template.xml");
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
                    VendorDataBean data = null;
                    int venId = 0;
                    String name = "";
                    VendorBean venBean = null;
                    for (int i = 0; i < lst.size(); i++) {
                        data = (VendorDataBean) lst.get(i);
                        if (GenericValidator.isBlankOrNull(data.getName())) {
                            continue;
                        }
                        if (!name.equals(data.getName())) {//dong moi cua vendor => insert vendor
                            name = StringUtil.encodeHTML(data.getName());
                            venId = searchVendor(name);
                            venBean = new VendorBean();
                            venBean.setName(name);
                            venBean.setAddress(StringUtil.encodeHTML(data.getAddress()));
                            venBean.setPhone(StringUtil.encodeHTML(data.getPhone()));
                            venBean.setFax(StringUtil.encodeHTML(data.getFax()));
                            venBean.setEmail(StringUtil.encodeHTML(data.getPresidentEmail()));
                            venBean.setWeb(StringUtil.encodeHTML(data.getPresidentEmail()));
                            venBean.setPresenter(StringUtil.encodeHTML(data.getPresident()));
                            venBean.setStatus(1);
                            venBean.setField(StringUtil.encodeHTML(data.getLisence()));
                            venBean.setLicense(data.getTaxCode());
                            venBean.setCharterCapital("");
                            venBean.setNote(StringUtil.encodeHTML(data.getNote()));
                            venBean.setPospresenter(StringUtil.encodeHTML(data.getPresentativePosition()));
                            venBean.setKind(1);
                            venBean.setPhonePresenter(StringUtil.encodeHTML(data.getPresentativePhone()));
                            venBean.setEmailPresenter(StringUtil.encodeHTML(data.getPresentativeEmail()));
                            if (venId == 0) {
                                venId = insertVendor(venBean);
                            } else {
                                updateVendor(venBean);
                            }
//                            if (venId == 0) {
//                                insertVendor(venBean);
//                                venId = venBean.getVenId();
//                            } else {
//                                updateVendor(venBean);
//                            }
                            if (!GenericValidator.isBlankOrNull(data.getGroup())) {
                                int gmId = searchGroupMaterial(StringUtil.encodeHTML(data.getGroup()));
                                if (gmId == 0) {
                                    gmId = insertGroupMaterial(StringUtil.encodeHTML(data.getGroup()));
                                }
                                insertVendorGroupMaterial(venId + "", gmId + "");
                            }

                            if (!GenericValidator.isBlankOrNull(data.getPresentative())) {
                                int preId = searchPresentative(venId, StringUtil.encodeHTML(data.getPresentative()));
                                if (preId == 0) {
                                    VendorContactBean bean = new VendorContactBean();
                                    bean.setVenId(venId);
                                    bean.setBirthday("");
                                    bean.setEmail(StringUtil.encodeHTML(data.getPresentativeEmail()));
                                    bean.setHandPhone(StringUtil.encodeHTML(data.getPresentativePhone()));
                                    bean.setHomePhone("");
                                    bean.setName(StringUtil.encodeHTML(data.getPresentative()));
                                    bean.setOfficePhone("");
                                    bean.setPosition(StringUtil.encodeHTML(data.getPresentativePosition()));
                                    insertPresentative(bean);
                                }
                            }
                        }
                    }
                }
            }
        } catch (XLSDataReadException e) {
            LogUtil.error("FAILED:Import Vendor-" + e.getMessage());
        } catch (Exception ex) {
            LogUtil.error("FAILED:Import Vendor-" + ex.getMessage());
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

    private int searchPresentative(int venId, String name) {
        int pId = 0;
        VendorDAO venDAO = new VendorDAO();
        try {
            ArrayList arr = venDAO.searchSimpleVendorContact(venId, 1, name);
            for (int i = 0; i < arr.size(); i++) {
                VendorContactBean bean = (VendorContactBean) arr.get(i);
                pId = bean.getContId();
            }
        } catch (Exception ex) {
        }
        return pId;
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

    private void insertVendorGroupMaterial(String venId, String gmId) {
        try {
            VendorDAO vendorDAO = new VendorDAO();
            if (vendorDAO.getVendorGroupMaterial(venId, gmId) == 0) {
                vendorDAO.insertVendorGroupMaterial(venId, gmId);
            }
        } catch (Exception ex) {
        }
    }

    private void insertPresentative(VendorContactBean bean) {
        try {
            VendorDAO vendorDAO = new VendorDAO();
            vendorDAO.insertVendorContact(bean);
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

    private int insertVendor(VendorBean bean) {
        int venId = 0;
        VendorDAO venDAO = new VendorDAO();
        try {
            venId = venDAO.insertVendor(bean);
            bean.setVenId(venId);
        } catch (Exception ex) {
        }
        return venId;
    }

    private void updateVendor(VendorBean bean) {
        VendorDAO venDAO = new VendorDAO();
        try {
            venDAO.updateVendor(bean);
        } catch (Exception ex) {
        }
    }
}
