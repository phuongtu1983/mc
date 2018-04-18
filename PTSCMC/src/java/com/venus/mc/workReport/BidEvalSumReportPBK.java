/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.workReport;

import com.venus.core.sax.RowSAXHandler;
import com.venus.core.util.DateUtil;
import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.BidEvalSumBean;
import com.venus.mc.bean.BidEvalSumDetailBean;
import com.venus.mc.bean.BidEvalSumGroupBean;
import com.venus.mc.bean.BidEvalSumVendorBean;
import com.venus.mc.bean.BidOpeningReportBean;
import com.venus.mc.bean.OrganizationBean;
import com.venus.mc.bean.RequestBean;
import com.venus.mc.bean.TenderLetterDetailBean;
import com.venus.mc.bean.TenderPlanBean;
import com.venus.mc.bean.VendorBean;
import com.venus.mc.core.HierSpineReportParser;
import com.venus.mc.dao.BidEvalSumDAO;
import com.venus.mc.dao.OrganizationDAO;
import com.venus.mc.dao.TenderLetterDetailDAO;
import com.venus.mc.dao.TenderPlanDAO;
import com.venus.mc.util.MCUtil;
import com.venus.mc.workReportBean.BidEvalSumReportBean;
import com.venus.mc.workReportBean.BidEvalSumReportMaterialBean;
import com.venus.mc.workReportBean.BidEvalSumReportVendorBean;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;

/**
 *
 * @author mai vinh loc
 */
public class BidEvalSumReportPBK extends HierSpineReportParser {

    private ArrayList arrDAO;
    private ArrayList arrDAOVendor1;
    private ArrayList arrDAOVendor2;
    private BidEvalSumReportBean reportBean;
    private String table1 = "table1";
    private String tableEmployee = "tableEmployee";
    private String tableVendor = "tableVendor";
    private String tableVendorAccepted = "tableVendorAccepted";
    private String tableOpenVendor = "tableOpenVendor";
    private String tableEmployeeEvaluate = "tableEmployeeEvaluate";
    private String tableRow1 = "tableRow1";
    private String tableRow2 = "tableRow2";

    public BidEvalSumReportPBK(String xmlTemplate, String wordTemplate, String resultFileName) {
        super(xmlTemplate, wordTemplate, resultFileName);
    }

    @Override
    protected void parse(Object object) throws Exception {
        Integer besIdObject = (Integer) object;
        int besId = besIdObject.intValue();
        double total1 = 0;
        double total2 = 0;
        BidEvalSumBean bean = null;
        TenderPlanBean tender = null;
        BidOpeningReportBean bcr = null;
        BidEvalSumDAO besDAO = new BidEvalSumDAO();
        TenderPlanDAO tenDAO = new TenderPlanDAO();
        try {
            bean = besDAO.getBidEvalSumForReport(besId);
            tender = besDAO.getTenderPlanReport(besId);
            bcr = tenDAO.getBidOpeningReport(tender.getTenId());
            total1 = besDAO.getVendorsBeforeForReport(besId);
            total2 = besDAO.getVendorsAfterForReport(besId);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (bean == null) {
            return;
        }

        Date date = DateUtil.transformerStringEnDate(bean.getCreatedDate(), "/");
        Date date2 = DateUtil.transformerStringEnDate(tender.getBidSendingDate(), "/");
        Date date3 = DateUtil.transformerStringEnDate(tender.getBidDeadline(), "/");
        Date date4 = DateUtil.transformerStringEnDate(bcr.getStartDate(), "/");
        Date date5 = DateUtil.transformerStringEnDate(tender.getCreatedDate(), "/");
        Date date6 = DateUtil.transformerStringEnDate(tender.getTenderLetterDate(), "/");

        setText("mcrp_number", bean.getBesNumber());
        setText("mcrp_year", DateUtil.formatDate(date, "yyyy"));
        setText("mcrp_header_date", MCUtil.getBundleString("message.day") + " " + DateUtil.formatDate(date, "dd") + " " + MCUtil.getBundleString("message.month") + " " + DateUtil.formatDate(date, "MM") + " " + MCUtil.getBundleString("message.year") + " " + DateUtil.formatDate(date, "yyyy"));
        setText("mcrp_packName", tender.getPackName());
        setText("mcrp_tender_number", tender.getTenderNumber());
        setText("mcrp_cv_date", "" + DateUtil.formatDate(date, "dd") + "/" + DateUtil.formatDate(date, "MM") + "/" + DateUtil.formatDate(date, "yyyy"));
        setText("mcrp_tender_date", "" + DateUtil.formatDate(date, "dd") + "/" + DateUtil.formatDate(date, "MM") + "/" + DateUtil.formatDate(date, "yyyy"));
        setText("mcrp_cv_from", "");
        setText("mcrp_cv_to", "");
        setText("mcrp_mcg", "");
        setText("mcrp_mcg_date", "");
        setText("mcrp_tender_date", DateUtil.formatDate(date5, "dd") + "/" + DateUtil.formatDate(date5, "MM") + "/" + DateUtil.formatDate(date5, "yyyy"));
        setText("mcrp_cv_date", DateUtil.formatDate(date6, "dd") + "/" + DateUtil.formatDate(date6, "MM") + "/" + DateUtil.formatDate(date6, "yyyy"));
        setText("mcrp_hinhthuc", tender.getForm().toLowerCase());
        setText("mcrp_tpdate_from", tender.getBidSendingTime() + " " + DateUtil.formatDate(date2, "dd") + "/" + DateUtil.formatDate(date2, "MM") + "/" + DateUtil.formatDate(date2, "yyyy"));
        setText("mcrp_tpdate_to", tender.getBidDeadlineTime() + " " + DateUtil.formatDate(date3, "dd") + "/" + DateUtil.formatDate(date3, "MM") + "/" + DateUtil.formatDate(date3, "yyyy"));
        setText("mcrp_ven_count", total1 + "");

        setText("mcrp_vendor_sent_date", tender.getBidDeadlineTime() + " " + MCUtil.getBundleString("message.day")
                + " " + DateUtil.formatDate(date3, "dd") + "/" + DateUtil.formatDate(date3, "MM") + "/" + DateUtil.formatDate(date3, "yyyy"));
        setText("mcrp_vendor_sent_count", total2 + "");
        setText("mcrp_open_date", bcr.getStartTime() + " " + MCUtil.getBundleString("message.day") + " "
                + DateUtil.formatDate(date4, "dd") + "/" + DateUtil.formatDate(date4, "MM") + "/" + DateUtil.formatDate(date4, "yyyy"));
        setText("mcrp_open_count", total2 + "");
        setText("mcrp_doc_count", "");
        BidOpeningReportBean opening = getTenderPlanLeterOpening(bean.getTenId());
        setText("mcrp_mcg", opening.getReportNumber());
        setText("mcrp_mcg_date", opening.getStartDate());
        setText("mcrp_letter", getTenderPlanLeterSuffix(bean.getTenId()));
        OrganizationBean pO = null;
        try {
            int orgId = MCUtil.getOrganizationID(this.getRequest().getSession());
            OrganizationDAO orgDAO = new OrganizationDAO();
            pO = orgDAO.getParentOrganization(orgId);
            if (pO == null) {
                pO = orgDAO.getOrganization(orgId);
            }
        } catch (Exception ex) {
        }
        if (pO == null) {
            pO = new OrganizationBean();
        }
        setText("mcrp_org", pO.getName().toUpperCase());
        TenderPlanDAO tenderDAO = new TenderPlanDAO();
        String whichuse = "";
        try {
            ArrayList arrReq = tenderDAO.getRequestsOfTenderPlan(tender.getTenId());
            RequestBean reqBean = null;
            for (int i = 0; i < arrReq.size(); i++) {
                reqBean = (RequestBean) arrReq.get(i);
                whichuse += ", " + reqBean.getWhichUseName();
            }
            if (!whichuse.equals("")) {
                whichuse = whichuse.substring(2);
            }
        } catch (Exception ex) {
        }
        //setText("mcrp_pro", whichuse);
        setText("mcrp_pro", "");
        reportBean = new BidEvalSumReportBean(this);
        try {
            initBean(besId, tender.getEvalKind(), tender.getTenId());
        } catch (Exception ex) {
        }
        Hashtable map = new Hashtable();
        ArrayList arrTable = new ArrayList();
        arrTable.add(reportBean);
        map.put(table1, arrTable);
        this.setArrTable(map);

        RowSAXHandler row = null;
        row = new RowSAXHandler("", this);
        try {
            arrDAO = besDAO.getBidEvalSumGroupReport(besId);
        } catch (Exception ex) {
        }
        if (arrDAO == null) {
            arrDAO = new ArrayList();
        }
        row.setRowCount(arrDAO.size());
        map.put(tableEmployee, row);

        row = new RowSAXHandler("", this);
        try {
            arrDAOVendor1 = besDAO.getTenderEvaluateVendorReport(besId);
        } catch (Exception ex) {
        }
        if (arrDAOVendor1 == null) {
            arrDAOVendor1 = new ArrayList();
        }
        row.setRowCount(arrDAOVendor1.size());
        map.put(tableVendor, row);

        row = new RowSAXHandler("", this);
        try {
            arrDAOVendor2 = besDAO.getTenderEvaluateVendorBiddedReport(besId);
        } catch (Exception ex) {
        }
        if (arrDAOVendor2 == null) {
            arrDAOVendor2 = new ArrayList();
        }
        row.setRowCount(arrDAOVendor2.size());
        map.put(tableVendorAccepted, row);

        row = new RowSAXHandler("", this);
        row.setRowCount(arrDAO.size());
        map.put(tableEmployeeEvaluate, row);

        row = new RowSAXHandler("", this);
        row.setRowCount(arrDAOVendor2.size());
        map.put(tableOpenVendor, row);
    }

    private String getBidEvalGroupText(int i, String tab) {
        String text = "";
        BidEvalSumGroupBean bean = null;
        if (i < arrDAO.size() && arrDAO.size() > 0) {
            bean = (BidEvalSumGroupBean) arrDAO.get(i);
            if (tab.equals("mcrp_group_no")) {
                text = (i + 1) + "";
            } else if (tab.equals("mcrp_group_name")) {
                text = bean.getEmployee();
            } else if (tab.equals("mcrp_group_org")) {
                text = bean.getPosition();
            } else if (tab.equals("mcrp_group_position")) {
                text = bean.getNote() + "";
            }
        }
        return text;
    }

    private String getBidEvalGroup5Text(int i, String tab) {
        String text = "";
        BidEvalSumGroupBean bean = null;
        if (i < arrDAO.size() && arrDAO.size() > 0) {
            bean = (BidEvalSumGroupBean) arrDAO.get(i);
            if (tab.equals("mcrp_group")) {
                text = bean.getEmployee();
            }
        }
        return text;
    }

    private String getTenderEvaluateVendorReport(int i, String tab) {
        String text = "";
        BidEvalSumVendorBean bean = null;
        if (i < arrDAOVendor1.size() && arrDAOVendor1.size() > 0) {
            bean = (BidEvalSumVendorBean) arrDAOVendor1.get(i);
            if (tab.equals("mcrp_vendor_no")) {
                text = (i + 1) + "";
            } else if (tab.equals("mcrp_vendor_name")) {
                text = bean.getVendorName();
            }
        }
        return text;
    }

    private String getTenderEvaluateVendorBiddedReport(int i, String tab) {
        String text = "";
        BidEvalSumVendorBean bean = null;
        if (i < arrDAOVendor2.size() && arrDAOVendor2.size() > 0) {
            bean = (BidEvalSumVendorBean) arrDAOVendor2.get(i);
            if (tab.equals("mcrp_vendor_sent_no") || tab.equals("mcrp_open_count_no")) {
                text = (i + 1) + "";
            } else if (tab.equals("mcrp_vendor_sent_name") || tab.equals("mcrp_open_count_name")) {
                text = bean.getVendorName();
            }
        }
        return text;
    }

    private void initBean(int besId, int kind, int tenId) {
        try {
            BidEvalSumDAO besDAO = new BidEvalSumDAO();
            ArrayList arrVendor = besDAO.getBidEvalSumVendor(besId, kind, tenId);
            BidEvalSumReportVendorBean level1 = null;
            BidEvalSumReportVendorBean bean1 = null;
            for (int i = 0; i < arrVendor.size(); i++) {
                bean1 = (BidEvalSumReportVendorBean) arrVendor.get(i);
                level1 = new BidEvalSumReportVendorBean(bean1);
                level1.setN1("" + (i + 1));
                reportBean.addBeanVendor(level1);
                try {
                    ArrayList arr = besDAO.getBidEvalSumDetailsReport(besId, level1.getBesvId(), level1.getCurrency());
                    for (int j = 0; j < arr.size(); j++) {
                        BidEvalSumDetailBean beanLevel2 = (BidEvalSumDetailBean) arr.get(j);
                        BidEvalSumReportMaterialBean level2 = new BidEvalSumReportMaterialBean();
                        level1.addMaterial(level2);
                        level2.setAmount(Double.parseDouble(beanLevel2.getTotal().replaceAll(",", "")));
                        level2.setMaterial(beanLevel2.getMatName());
                        level2.setNo(j + 1 + "");
                        level2.setPrice(Double.parseDouble(beanLevel2.getPrice().replaceAll(",", "")));
                        level2.setQuantity(beanLevel2.getQuantity());
                        level2.setUnit(beanLevel2.getUnit());
                        level2.setRequest(beanLevel2.getRequest());
                    }
                } catch (Exception ex) {
                    System.out.println("ex=" + ex);
                }
            }
        } catch (Exception ex) {
            System.out.println("ex=" + ex);
        }
    }

    @Override
    public String getTabText(String rowName, int rowId, String tab) {
        if (rowName.equals(tableRow1)) {
            if (reportBean.getArrBeanVendor() != null) {
                ArrayList arr = reportBean.getArrBeanVendor();
                BidEvalSumReportVendorBean vendorBean = null;
                int amount = 0;
                for (int i = 0; i < arr.size(); i++) {
                    amount += 1;
                    vendorBean = (BidEvalSumReportVendorBean) arr.get(i);
                    if (rowId == amount) {
                        break;
                    }
                    if (vendorBean != null && vendorBean.arrMaterial != null) {
                        amount += vendorBean.arrMaterial.size();
                    }
                }
                if (vendorBean != null) {
//                    if (tab.equals("mcrp_conc_no")) {
//                        return vendorBean.getN1();
//                    } else if (tab.equals("mcrp_conc_vendor")) {
//                        return vendorBean.getVendor();
//                    } else if (tab.equals("mcrp_conc_deliveryDate")) {
//                        return vendorBean.getDeliveryDate();
//                    } else if (tab.equals("mcrp_conc_certificate")) {
//                        return vendorBean.getCertificate();
//                    } else if (tab.equals("mcrp_con_not_vat")) {
//                        return vendorBean.getAmount3();
//                    } else if (tab.equals("mcrp_con_vat")) {
//                        return vendorBean.getAmount4();
//                    } else if (tab.equals("mcrp_conc_total")) {
//                        return vendorBean.getAmount5();
//                    } else if (tab.equals("mcrp_conc_cur")) {
//                        return vendorBean.getCurrency();
//                    }
                    if (tab.equals("mcrp_conc_no")) {
                        return vendorBean.getN1();
                    } else if (tab.equals("mcrp_conc_vendor")) {
                        return vendorBean.getVendor();
                    } else if (tab.equals("mcrp_req")) {
                        return vendorBean.getReqNumber();
                    } else if (tab.equals("mcrp_conc_cur")) {
                        return vendorBean.getCurrency();
                    } else if (tab.equals("mcrp_con_not_vat_no")) {
//                        return (vendorBean.getArrMaterial().size() + 1) + "";//mcrp_conc_in_no
                        return "I";//mcrp_conc_in_no
                    } else if (tab.equals("mcrp_con_not_vat")) {
                        return NumberUtil.formatMoneyDefault(Double.parseDouble(vendorBean.getAmount3()));
                    } else if (tab.equals("mcrp_con_vat_no")) {
//                        return (vendorBean.getArrMaterial().size() + 2) + "";
                        return "II";
                    } else if (tab.equals("mcrp_con_vat")) {
                        return NumberUtil.formatMoneyDefault(Double.parseDouble(vendorBean.getAmount4()));
                    } else if (tab.equals("mcrp_conc_in_no")) {
//                        return (vendorBean.getArrMaterial().size() + 3) + "";
                        return "III";
                    } else if (tab.equals("mcrp_conc_total_no")) {
//                        return (vendorBean.getArrMaterial().size() + 4) + "";
                        return "IV";
                    } else if (tab.equals("mcrp_conc_total")) {
                        return NumberUtil.formatMoneyDefault(Double.parseDouble(vendorBean.getAmount5()));
                    } else if (tab.equals("mcrp_conc_deliveryDate")) {
                        return vendorBean.getDeliveryDate();
                    } else if (tab.equals("mcrp_conc_certificate")) {
                        return vendorBean.getCertificate();
                    } else if (tab.equals("mcrp_conc_total_text")) {
                        return NumberUtil.textMoney(Double.parseDouble(vendorBean.getAmount5()), vendorBean.getCurrency());
                    } else if (tab.equals("mcrp_con_not_vat_no_text")) {
                        if (vendorBean.getVendorKind() == VendorBean.KIND_NATIONAL) {
                            return "Tổng giá trị - chưa bao gồm thuế GTGT";
                        } else {
                            return "Total amount";
                        }
                    } else if (tab.equals("mcrp_con_vat_no_text")) {
                        if (vendorBean.getVendorKind() == VendorBean.KIND_NATIONAL) {
                            return "Thuế GTGT 10%";
                        } else {
                            return "Certificate of Origin from Chamber of Commerce";
                        }
                    } else if (tab.equals("mcrp_conc_in_no_text")) {
                        if (vendorBean.getVendorKind() == VendorBean.KIND_NATIONAL) {
                            return "Chi phí vận chuyển đến kho của CTCP DVCKHH";
                        } else {
                            return "Freight, Insurance, Document and other charges (" + vendorBean.getCurrency() + ")";
                        }
                    } else if (tab.equals("mcrp_conc_total_no_text")) {
                        if (vendorBean.getVendorKind() == VendorBean.KIND_NATIONAL) {
                            return "Tổng giá trị: đã bao gồm chi phí vận chuyển đến kho Cty và thuế GTGT 10%";
                        } else {
                            return "Total amount on CIF Vung Tau city, Viet Nam (I + II + III)";
                        }
                    }
                }
            }
        } else if (rowName.equals(tableRow2)) {
            if (reportBean.getArrBeanVendor() != null) {
                ArrayList arr = reportBean.getArrBeanVendor();
                BidEvalSumReportVendorBean vendorBean = null;
                int amount = 0;
                BidEvalSumReportMaterialBean material = null;
                for (int i = 0; i < arr.size(); i++) {
                    amount += 1;
                    vendorBean = (BidEvalSumReportVendorBean) arr.get(i);
                    if (vendorBean.getArrMaterial() != null) {
//                        if (rowId > amount && rowId <= amount + vendorBean.getArrMaterial().size()) {
//                            material = (BidEvalSumReportMaterialBean) vendorBean.getArrMaterial().get(i);
//                            break;
//                        }
//                        amount += vendorBean.getArrMaterial().size();
                        int j = 0;
                        for (; j < vendorBean.getArrMaterial().size(); j++) {
                            amount++;
                            if (rowId == amount) {
                                material = (BidEvalSumReportMaterialBean) vendorBean.getArrMaterial().get(j);
                                break;
                            }
                        }
                        if (j < vendorBean.getArrMaterial().size()) {
                            break;
                        }
                    }
                }
                if (material != null) {
//                    if (tab.equals("mcrp_conc_mat_no")) {
//                        return material.getNo();
//                    } else if (tab.equals("mcrp_conc_mat_name")) {
//                        return material.getMaterial();
//                    } else if (tab.equals("mcrp_conc_mat_unit")) {
//                        return material.getUnit();
//                    } else if (tab.equals("mcrp_conc_mat_quan")) {
//                        return material.getQuantity() + "";
//                    } else if (tab.equals("mcrp_conc_mat_price")) {
//                        return material.getPrice() + "";
//                    } else if (tab.equals("mcrp_conc_mat_amount")) {
//                        return material.getAmount() + "";
//                    }
                    if (tab.equals("mcrp_conc_mat_no")) {
                        return material.getNo();
                    } else if (tab.equals("mcrp_conc_mat_name")) {
                        return material.getMaterial();
                    } else if (tab.equals("mcrp_conc_mat_unit")) {
                        return material.getUnit();
                    } else if (tab.equals("mcrp_conc_mat_quan")) {
                        return NumberUtil.formatMoneyDefault(material.getQuantity(), "VND");
                    } else if (tab.equals("mcrp_conc_mat_price")) {
                        return NumberUtil.formatMoneyDefault(material.getPrice());
                    } else if (tab.equals("mcrp_conc_mat_amount")) {
                        return NumberUtil.formatMoneyDefault(material.getAmount());
                    } else if (tab.equals("mcrp_conc_mat_request")) {
                        return material.getRequest();
                    }
                }
            }
        } else if (rowName.equals("employeeRow")) {
            return getBidEvalGroupText(rowId, tab);
        } else if (rowName.equals("vendorRow")) {
            return getTenderEvaluateVendorReport(rowId, tab);
        } else if (rowName.equals("vendorAcceptedRow")) {
            return getTenderEvaluateVendorBiddedReport(rowId, tab);
        } else if (rowName.equals("employeeEvaluateRow")) {
            return getBidEvalGroup5Text(rowId, tab);
        } else if (rowName.equals("openVendorRow")) {
            return getTenderEvaluateVendorBiddedReport(rowId, tab);
        }
        return "";
    }

    @Override
    public int getNumberOfRowToDuplicate(int id, String name) {
        int result = 0;
        if (name.equals(tableRow1)) {
            if (reportBean.getArrBeanVendor() != null) {
                result = reportBean.getArrBeanVendor().size();
            }
        } else if (name.equals(tableRow2)) {
            ArrayList arr = reportBean.getArrBeanVendor();
            BidEvalSumReportVendorBean vendorBean = null;
            int amount = 0;
            for (int i = 0; i < arr.size(); i++) {
                amount += 1;
                vendorBean = (BidEvalSumReportVendorBean) arr.get(i);
                if (id == amount + 1) {
                    if (vendorBean.getArrMaterial() != null) {
                        result = vendorBean.getArrMaterial().size();
                        break;
                    }
                }
                if (vendorBean.getArrMaterial() != null) {
                    amount += vendorBean.getArrMaterial().size();
                }
            }
        }
        return result;
    }

    private String getTenderPlanLeterSuffix(int tenId) {
        String text = "";
        try {
            TenderLetterDetailDAO dao = new TenderLetterDetailDAO();
            ArrayList details = dao.getTenderLetterDetailsByTenderPlan(tenId);
            TenderLetterDetailBean detail = null;
            for (int i = 0; i < details.size(); i++) {
                detail = (TenderLetterDetailBean) details.get(i);
                text += "," + detail.getSubfix();
            }
            if (!text.equals("")) {
                text = text.substring(1);
            }
        } catch (Exception ex) {
        }
        return text;
    }

    private BidOpeningReportBean getTenderPlanLeterOpening(int tenId) {
        BidOpeningReportBean bean = null;
        try {
            TenderPlanDAO dao = new TenderPlanDAO();
            bean = dao.getBidOpeningReport(tenId);
        } catch (Exception ex) {
        }
        if (bean == null) {
            bean = new BidOpeningReportBean();
        }
        return bean;
    }
}
