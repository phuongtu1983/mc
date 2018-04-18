/*
 *
 * Created on April 13, 2007, 2:08 PM
 */
package com.venus.mc.importData;

import com.venus.core.util.GenericValidator;
import com.venus.mc.bean.MaterialBean;
import com.venus.mc.bean.OrganizationBean;
import com.venus.mc.bean.StoreBean;
import com.venus.mc.bean.UnitBean;
import com.venus.mc.core.BaseAction;
import com.venus.mc.dao.MaterialDAO;
import com.venus.mc.dao.MaterialStoreRequestDAO;
import com.venus.mc.dao.OrganizationDAO;
import com.venus.mc.dao.StoreDAO;
import com.venus.mc.dao.UnitDAO;
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
public class StockImportAction extends BaseAction {

    private int proId;

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
        String xlsFile = "";
        if (!GenericValidator.isBlankOrNull(request.getParameter("xlsFile"))) {
            xlsFile = request.getParameter("xlsFile");
        }
        if (GenericValidator.isBlankOrNull(xlsFile)) {
            return;
        }
        String xmlConfig = request.getSession().getServletContext().getRealPath("/data_imported/stock_template.xml");
        String dataXLS = "";
        String[] arrFile = xlsFile.split(",");
        for (int j = 0; j < arrFile.length; j++) {
            xlsFile = arrFile[j];
            dataXLS = request.getSession().getServletContext().getRealPath("/data_imported/" + xlsFile + ".xls");
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
                        StockDataBean data = null;
                        String matName = "";
                        proId = 0;
                        for (int i = 0; i < lst.size(); i++) {
                            data = (StockDataBean) lst.get(i);
                            if (!matName.equals(data.getName())) {//dong moi cua mat => insert mat
                                matName = data.getName();
                                int matId = searchMaterial(matName);
                                if (matId == 0) {
                                    MaterialBean mat = new MaterialBean();
                                    mat.setNameVn(matName);
                                    mat.setCode(data.getCode());

                                    String unit = data.getUnit();
                                    int unitId = searchUnit(unit);
                                    if (unitId == 0) {
                                        unitId = insertUnit(unit);
                                    }
                                    mat.setUniId(unitId);
                                    insertMaterial(mat);
                                    matId = mat.getMatId();
                                }
                                if (proId == 0) {
                                    String proName = data.getProject();
                                    if (!proName.equals("Kho công ty")) {
                                        proId = searchProject(proName);
                                        if (proId == 0 && !proName.equals("Kho công ty")) {
                                            proId = insertProject(proName);
                                        } else {
                                            int stoId = searchStore(proId, proName);
                                            if (stoId == 0) {
                                                proId = insertStore(proId, proName);
                                            } else {
                                                proId = stoId;
                                            }
                                        }
                                    } else {
                                        proId = 1;
                                    }
                                }
                                insertMaterialStoreRequest(proId, matId, data.getQuantity());
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

    }

    private void insertMaterial(MaterialBean bean) {
        try {
            MaterialDAO matDAO = new MaterialDAO();
            int matId = matDAO.insertMaterial(bean);
            bean.setMatId(matId);
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

    private int searchStore(int orgId, String name) {
        StoreDAO stoDAO = new StoreDAO();
        int stoId = 0;
        try {
            StoreBean bean = null;
            if (orgId != 0) {
                bean = stoDAO.getStore(orgId, "Kho " + name);
            } else {
                bean = stoDAO.getStore(name);
            }
            if (bean != null) {
                stoId = bean.getStoId();
            }
        } catch (Exception ex) {

        }
        return stoId;
    }

    private int searchProject(String name) {
        OrganizationDAO orgDAO = new OrganizationDAO();
        try {
            OrganizationBean bean = orgDAO.getOrganizationByName(name);
            if (bean != null) {
                return bean.getOrgId();
            }
        } catch (Exception ex) {

        }
        return 0;
    }

    private int insertProject(String proName) {
        OrganizationDAO orgDAO = new OrganizationDAO();
        try {
            OrganizationBean bean = new OrganizationBean();
            bean.setName(proName);
            bean.setAbbreviate("");
            bean.setParentId(0);
            bean.setStatus(1);
            bean.setKind(OrganizationBean.KIND_PROJECT);
            proId = orgDAO.insertOrganization(bean);

            //insert new store
            StoreDAO stoDAO = new StoreDAO();
            StoreBean stoBean = new StoreBean();
            stoBean.setProId(proId);
            stoBean.setName("Kho " + proName);
            return stoDAO.insertStore(stoBean);
        } catch (Exception ex) {

        }
        return 0;
    }

    private int insertStore(int orgId, String name) {
        int stoId = 0;
        StoreDAO stoDAO = new StoreDAO();
        try {
            StoreBean stoBean = new StoreBean();
            stoBean.setProId(orgId);
            stoBean.setName("Kho " + name);
            stoBean.setKind(1);
            stoId = stoDAO.insertStore(stoBean);
        } catch (Exception ex) {

        }
        return stoId;
    }

    private void insertMaterialStoreRequest(int stoId, int matId, double quantity) {
        try {
            MaterialStoreRequestDAO msrDAO = new MaterialStoreRequestDAO();
            msrDAO.insertMaterialStoreRequest(matId, stoId, 0, quantity, 0, 0);
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
}
