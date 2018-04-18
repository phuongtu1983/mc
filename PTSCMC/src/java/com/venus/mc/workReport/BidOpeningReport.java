/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.workReport;

import com.venus.core.sax.RowSAXHandler;
import com.venus.core.util.DateUtil;
import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.BidOpeningGroupBean;
import com.venus.mc.bean.BidOpeningReportBean;
import com.venus.mc.bean.TenderLetterBean;
import com.venus.mc.bean.TenderLetterDetailBean;
import com.venus.mc.bean.TenderPlanBean;
import com.venus.mc.bean.VendorBean;
import com.venus.mc.core.SpineReportParser;
import com.venus.mc.dao.TenderLetterDAO;
import com.venus.mc.dao.TenderLetterDetailDAO;
import com.venus.mc.dao.TenderPlanDAO;
import com.venus.mc.dao.VendorDAO;
import com.venus.mc.tenderplan.BidOpeningReportFormBean;
import com.venus.mc.tenderplan.TenderEvaluateVendorFormBean;
import com.venus.mc.util.MCUtil;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;

/**
 *
 * @author kngo
 */
public class BidOpeningReport extends SpineReportParser {

    private ArrayList arrBidOpeningGroup;
    private ArrayList arrVendor, arrVendorBidded, arrVendorNoneBidded;

    public BidOpeningReport(String xmlTemplate, String wordTemplate, String resultFileName) {
        super(xmlTemplate, wordTemplate, resultFileName);
    }

    @Override
    protected void parse(Object object) throws Exception {
        Integer borIdObject = (Integer) object;
        int borId = borIdObject.intValue();
        BidOpeningReportBean bean = null;
        TenderPlanDAO bcrDAO = new TenderPlanDAO();
        try {
            bean = bcrDAO.getBidOpeningReport2(borId);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (bean == null) {
            return;
        }

        TenderPlanBean tenderBean = null;
        try {
            tenderBean = bcrDAO.getTenderPlan(bean.getTenId());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (tenderBean == null) {
            return;
        }

        setText("mcrp_number", bean.getReportNumber());
        Date date = DateUtil.transformerStringEnDate(DateUtil.today("dd/MM/yyyy"), "/");
        SimpleDateFormat sdf = null;
        sdf = new SimpleDateFormat("dd");
        setText("mcrp_day", sdf.format(date));
        sdf = new SimpleDateFormat("MM");
        setText("mcrp_month", sdf.format(date));
        sdf = new SimpleDateFormat("yyyy");
        setText("mcrp_year", sdf.format(date));

        setText("mcrp_packName", tenderBean.getPackName());
//        setText("mcrp_pro", tenderBean.getProName());
        setText("mcrp_pro", "");
        setText("mcrp_cv_date", getTenderPlanLeterNumber(bean.getTenId()));
        setText("mcrp_tenderPlanNumber", tenderBean.getTenderNumber());
        setText("mcrp_tenderPlanDate", tenderBean.getCreatedDate());
        setText("mcrp_tenderLeterNumber", getTenderPlanLeterSuffix(bean.getTenId()));
        date = DateUtil.transformerStringEnDate(bean.getStartDate(), "/");
        setText("mcrp_open_time", bean.getStartTime() + " " + MCUtil.getBundleString("message.day") + " " + DateUtil.formatDate(date, "dd")
                + " " + MCUtil.getBundleString("message.month") + " " + DateUtil.formatDate(date, "MM")
                + " " + MCUtil.getBundleString("message.year") + " " + DateUtil.formatDate(date, "yyyy"));

        date = DateUtil.transformerStringEnDate(bean.getEndDate(), "/");
        setText("mcrp_end_time", bean.getEndTime() + " " + MCUtil.getBundleString("message.day") + " " + DateUtil.formatDate(date, "dd")
                + " " + MCUtil.getBundleString("message.month") + " " + DateUtil.formatDate(date, "MM")
                + " " + MCUtil.getBundleString("message.year") + " " + DateUtil.formatDate(date, "yyyy"));

        date = DateUtil.transformerStringEnDate(tenderBean.getBidDeadline(), "/");
        if (date == null) {
            setText("mcrp_deadline", tenderBean.getBidDeadlineTime() + " " + MCUtil.getBundleString("message.day") + " ... "
                    + MCUtil.getBundleString("message.month") + " ... " + MCUtil.getBundleString("message.year") + " ... ");
        } else {
            setText("mcrp_deadline", tenderBean.getBidDeadlineTime() + " "
                    + MCUtil.getBundleString("message.day") + " " + DateUtil.formatDate(date, "dd")
                    + " " + MCUtil.getBundleString("message.month") + " " + DateUtil.formatDate(date, "MM")
                    + " " + MCUtil.getBundleString("message.year") + " " + DateUtil.formatDate(date, "yyyy"));
        }

        Hashtable map = new Hashtable();
        RowSAXHandler row = null;
        row = new RowSAXHandler("bidOpeningGroupRow", this);
        try {
            arrBidOpeningGroup = bcrDAO.getBidOpeningGroup(borId);
        } catch (Exception ex) {
        }
        if (arrBidOpeningGroup == null) {
            arrBidOpeningGroup = new ArrayList();
        }
        row.setRowCount(arrBidOpeningGroup.size());
        map.put("bidOpeningGroupRow", row);

        row = new RowSAXHandler("vendorRow", this);
        try {
            VendorDAO vendorDAO = new VendorDAO();
            arrVendor = vendorDAO.getVendorsForTenderLetter(tenderBean.getTenId() + "");
//            arrVendor = bcrDAO.getTenderPlanVendorDetailBidded(tenderBean.getTenId());
        } catch (Exception ex) {
        }
        if (arrVendor == null) {
            arrVendor = new ArrayList();
        }
        row.setRowCount(arrVendor.size());
        map.put("vendorRow", row);

        row = new RowSAXHandler("sentVendorRow", this);
        try {
            arrVendorBidded = bcrDAO.getTenderPlanVendorDetailBidded(bean.getTenId());
        } catch (Exception ex) {
        }
        if (arrVendorBidded == null) {
            arrVendorBidded = new ArrayList();
        }
        row.setRowCount(arrVendorBidded.size());
        map.put("sentVendorRow", row);

        row = new RowSAXHandler("evaluatedVendorRow", this);
//        try {
//            arrVendorNoneBidded = bcrDAO.getTenderPlanVendorNoneBiddiedDetail(bean.getTenId());
//        } catch (Exception ex) {
//        }
//        if (arrVendorNoneBidded == null) {
//            arrVendorNoneBidded = new ArrayList();
//        }
        row.setRowCount(arrVendorBidded.size());
        map.put("evaluatedVendorRow", row);

        row = new RowSAXHandler("evaluatedRow", this);
        row.setRowCount(arrVendorBidded.size());
        map.put("evaluatedRow", row);

        int vendorCounts = arrVendorBidded.size();
        setText("mcrp_ven_count", Integer.toString(vendorCounts));
        setText("mcrp_ven_count_text", NumberUtil.textMoney(Double.parseDouble(vendorCounts + ""), ""));

        row = new RowSAXHandler("groupRow", this);
        row.setRowCount(arrBidOpeningGroup.size());
        map.put("groupRow", row);

        this.setArrTable(map);
    }

    private String getBidOpeningGroupText(int i, String tab) {
        String text = "";
        BidOpeningGroupBean bean = null;
        if (i < arrBidOpeningGroup.size()) {
            bean = (BidOpeningGroupBean) arrBidOpeningGroup.get(i);
            if (tab.equals("mcrp_n1")) {
                text = (i + 1) + "";
            } else if (tab.equals("mcrp_employee")) {
                text = bean.getEmployee();
            } else if (tab.equals("mcrp_org")) {
                text = bean.getPosition();
            } else if (tab.equals("mcrp_position")) {
                text = bean.getNote();
            }
        }
        return text;
    }

    private String getBidOpeningGroupSignText(int i, String tab) {
        String text = "";
        BidOpeningGroupBean bean = null;
        if (i < arrBidOpeningGroup.size()) {
            bean = (BidOpeningGroupBean) arrBidOpeningGroup.get(i);
            if (tab.equals("mcrp_n6")) {
                text = (i + 1) + "";
            } else if (tab.equals("mcrp_employee")) {
                text = bean.getEmployee();
            }
        }
        return text;
    }

    private String getVendorText(int i, String tab) {
        String text = "";
        if (i < arrVendor.size()) {
            VendorBean bean = (VendorBean) arrVendor.get(i);
            if (tab.equals("mcrp_n2")) {
                text = (i + 1) + "";
            } else if (tab.equals("mcrp_vendor2")) {
                text = bean.getName();
            }
        }
        return text;
    }

    private String getVendorBiddedText(int i, String tab) {
        String text = "";
        if (i < arrVendorBidded.size()) {
            TenderEvaluateVendorFormBean bean = (TenderEvaluateVendorFormBean) arrVendorBidded.get(i);
            if (tab.equals("mcrp_n3")) {
                text = (i + 1) + "";
            } else if (tab.equals("mcrp_vendor3")) {
                text = bean.getVenName();
            }
        }
        return text;
    }

    private String getVendorNoneBiddedText(int i, String tab) {
        String text = "";
//        if (i < arrVendorNoneBidded.size()) {
//            TenderEvaluateVendorFormBean bean = (TenderEvaluateVendorFormBean) arrVendorNoneBidded.get(i);
//            if (tab.equals("mcrp_n4")) {
//                text = (i + 1) + "";
//            } else if (tab.equals("mcrp_vendor")) {
//                if (bean == null) {
//                    text = "";
//                } else {
//                    text = bean.getVenName();
//                }
//            }
//        }
        if (i < arrVendorBidded.size()) {
            TenderEvaluateVendorFormBean bean = (TenderEvaluateVendorFormBean) arrVendorBidded.get(i);
            if (tab.equals("mcrp_n4")) {
                text = (i + 1) + "";
            } else if (tab.equals("mcrp_vendor4")) {
                if (bean == null) {
                    text = "";
                } else {
                    text = bean.getVenName();
                }
            }
        }
        return text;
    }

    private String getVendorBiddedResultText(int i, String tab) {
        String text = "";
        if (i < arrVendorBidded.size()) {
            TenderEvaluateVendorFormBean bean = (TenderEvaluateVendorFormBean) arrVendorBidded.get(i);
            if (tab.equals("mcrp_n5")) {
                text = (i + 1) + "";
            } else if (tab.equals("mcrp_vendor5")) {
                text = bean.getVenName();
            } else if (tab.equals("mcrp_doc_count")) {
                text = bean.getBiddedPage();
            } else if (tab.equals("mcrp_base")) {
                text = bean.getBiddedFoundation();
            } else if (tab.equals("mcrp_status")) {
                if (bean.getBiddedStatus() == BidOpeningReportFormBean.NOTSEALED) {
                    text = MCUtil.getBundleString("message.tenderplan.bidopening.biddedStatus.notsealed");
                } else if (bean.getBiddedStatus() == BidOpeningReportFormBean.SEALED) {
                    text = MCUtil.getBundleString("message.tenderplan.bidopening.biddedStatus.sealed");
                }
            }
        }
        return text;
    }

    @Override
    public String getTabText(String rowId, int i, String tab) {
        if (rowId.equals("bidOpeningGroupRow")) {
            return getBidOpeningGroupText(i, tab);
        } else if (rowId.equals("vendorRow")) {
            return getVendorText(i, tab);
        } else if (rowId.equals("sentVendorRow")) {
            return getVendorBiddedText(i, tab);
        } else if (rowId.equals("evaluatedVendorRow")) {
            return getVendorNoneBiddedText(i, tab);
        } else if (rowId.equals("evaluatedRow")) {
            return getVendorBiddedResultText(i, tab);
        } else if (rowId.equals("groupRow")) {
            return getBidOpeningGroupSignText(i, tab);
        }
        return "";
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

    private String getTenderPlanLeterNumber(int tenId) {
        String text = "";
        try {
            TenderLetterDAO dao = new TenderLetterDAO();
            TenderLetterBean bean = dao.getTenderLetter(tenId);
            text = bean.getCreatedDate();
        } catch (Exception ex) {
        }
        return text;
    }
}
