/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.process.store.mrir.decorator;

import com.venus.core.util.GenericValidator;
import com.venus.core.util.LogUtil;
import com.venus.core.util.NumberUtil;
import com.venus.core.util.StringUtil;
import com.venus.mc.bean.MrirDetailBean;
import com.venus.mc.dao.MrirDAO;
import com.venus.mc.request.RequestFormBean;
import com.venus.mc.util.MCUtil;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.util.LabelValueBean;
import org.displaytag.decorator.DisplaytagColumnDecorator;
import org.displaytag.decorator.TotalTableDecorator;
import org.displaytag.exception.DecoratorException;
import org.displaytag.model.HeaderCell;

/**
 *
 * @author thcao
 */
public class MrirMaterialDecorator extends TotalTableDecorator {

    protected ArrayList arrMatKind = null;
    protected ArrayList arrMatKind1 = null;
    protected ArrayList arrMatKind2 = null;

    public MrirMaterialDecorator() {
        super();
        if (arrMatKind == null) {
            arrMatKind = getMatKindList(RequestFormBean.KIND_BOTH);
        }
        if (arrMatKind1 == null) {
            arrMatKind1 = getMatKindList(RequestFormBean.KIND_MAJOR);
        }
        if (arrMatKind2 == null) {
            arrMatKind2 = getMatKindList(RequestFormBean.KIND_SECONDARY);
        }
    }

    public String getDelId() {
        MrirDetailBean bean = (MrirDetailBean) getCurrentRowObject();
        String disabled = "";
        if (bean.getStatus() == 1) {
            disabled = "disabled";
        }
        return "<input type=\"checkbox\" name=\"chkDetId\" value=\"" + bean.getDetId() + "\"" + disabled +
                //" onclick=\"removeMrvRow(" + bean.getDetId()+ ")\"" +
                "><input type=\"hidden\" name=\"detId\" value=\"" + bean.getDetId() + "\"" + ">" +
                "<input type=\"hidden\" name=\"dndId\" value=\"" + bean.getDndId() + "\"" + ">";
    }

    public String getSystemNo() {
        MrirDetailBean bean = (MrirDetailBean) getCurrentRowObject();
        return "<input type=\"text\" id=\"systemno_" +
                bean.getDetId() +
                "\" size=\"6\" name=\"systemNo\" " +
                "value=\"" + bean.getSystemNo() + "\"" +
                ">";
    }

    public String getItemNo() {
        MrirDetailBean bean = (MrirDetailBean) getCurrentRowObject();
        return "<input type=\"text\" id=\"itemno_" +
                bean.getDetId() +
                "\" size=\"6\" name=\"itemNo\" " +
                "value=\"" + bean.getItemNo() + "\"" +
                ">";
    }

    public String getLocation() {
        MrirDetailBean bean = (MrirDetailBean) getCurrentRowObject();
        return "<input type=\"text\" id=\"location_" +
                bean.getDetId() +
                "\" style=\"text-align:left;\" size=\"20\" name=\"location\" " +
                "value=\"" + bean.getLocation() + "\"" +
                ">";
    }

    public String getComment() {
        MrirDetailBean bean = (MrirDetailBean) getCurrentRowObject();
        //return "<input type=\"button\" value=\"...\">&nbsp;" + bean.getComment();
//        return "<input type=\"hidden\" id=\"comment_" +
//                bean.getDetId() +
//                "\" name=\"comment\"" +
//                "value=\"" + bean.getComment() + "\"" +
//                ">";
        String comment = bean.getComment();
        if (GenericValidator.isBlankOrNull(comment)) {
            comment = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
        }
        return "<pre id=\"pre_" + bean.getDetId() + "\" style=\"font-family:Tahoma;\" onclick=\"editMrirComment('" + bean.getDetId() +
                "');\">" + comment + "</pre>" +
                "<textarea style=\"display:none;\" id=\"comment_" +
                bean.getDetId() +
                "\" name=\"comment\" cols=\"30\" rows=\"2\"" +
                ">" + bean.getComment() +
                "</textarea>";
    }

    public String getTimeUse() {
        MrirDetailBean bean = (MrirDetailBean) getCurrentRowObject();
        String id = "timeuse_" + bean.getDetId();
        return "<input type=\"text\" id=\"" +
                id +
                "\" style=\"text-align:right;\" size=\"11\" name=\"timeUse\" " +
                "value=\"" + bean.getTimeUse() + "\"" +
                " onclick=\"javascript: showCalendar('" + id + "')\">";
    }

    public String getQuantity() {
        MrirDetailBean bean = (MrirDetailBean) getCurrentRowObject();
        return "<input type=\"text\" id=\"quantity_" +
                bean.getDetId() +
                "\" style=\"text-align:right;\" size=\"6\" name=\"quantity\" " +
                "value=\"" + bean.getQuantity() + "\"" +
                //"onkeyup=\"calculateMsvRow(" +  bean.getDetId() +")\"" +
                //"readonly=\"true\"" + 
                ">";
    }

    public String getPrice() {
        MrirDetailBean bean = (MrirDetailBean) getCurrentRowObject();
        return "<input type=\"text\" id=\"price_" +
                bean.getDetId() +
                "\" style=\"text-align:right;\" size=\"8\" name=\"price\" value=\"" +
                NumberUtil.formatMoneyDefault(bean.getPrice()) + "\"" +
                "readonly=\"true\"" +
                ">";

    }

    public String getMatKind() {
        MrirDetailBean bean = (MrirDetailBean) getCurrentRowObject();
        StringBuffer buffer = new StringBuffer();
        ArrayList arrMaterialKind = null;
        int materialKind = bean.getMaterialKind();
        if (materialKind == RequestFormBean.KIND_MAJOR) {
            arrMaterialKind = arrMatKind1;
        } else if (materialKind == RequestFormBean.KIND_SECONDARY) {
            arrMaterialKind = arrMatKind2;
        } else {
            arrMaterialKind = arrMatKind;
        }
        if (arrMaterialKind != null) {
            buffer.append("<select name=\"matKind\" class=\"lbl10\">");
            for (int i = 0; i < arrMaterialKind.size(); i++) {
                LabelValueBean value = (LabelValueBean) arrMaterialKind.get(i);
                if (bean.getMatKind() == NumberUtil.parseInt(value.getValue(), 0)) {
                    buffer.append("<option value=\"").append(value.getValue()).append("\" selected=\"selected\" class=\"lbl10\">").append(value.getLabel()).append("</option>");
                } else {
                    buffer.append("<option value=\"").append(value.getValue()).append("\" class=\"lbl10\">").append(value.getLabel()).append("</option>");
                }
            }
            buffer.append("</select>");
        }
        return buffer.toString();

    }

//    public String getTotal() {
//        MrirDetailBean bean = (MrirDetailBean) getCurrentRowObject();
//        return "<input type=\"text\" id=\"total_" +
//                bean.getDetId() +
//                "\" style=\"text-align:right;\" size=\"8\" name=\"total\" value=\"" + 
//                NumberUtil.formatNumberDefault(bean.getTotal()) + "\">";
//    }
    public String getReqNumber() {
        MrirDetailBean bean = (MrirDetailBean) getCurrentRowObject();
        if (bean.getReqId() > 0) {
            return "<a href=\"#\" onclick=\"return printRequest(" + bean.getReqId() +
                    ");\">" + bean.getReqNumber() + "</a>";
        } else {
            return "";
        }

    }

    public String getMatName() {
        MrirDetailBean bean = (MrirDetailBean) getCurrentRowObject();
        StringBuffer str = new StringBuffer();

        str.append("<div onmouseout=\"UnTip()\" onmouseover=displayTip('mrir_tip_").append(bean.getDetId()).append("')>").append(StringUtil.makeShortName(bean.getMatName(), 5));
        str.append("<span id=\"mrir_tip_").append(bean.getDetId()).append("\" style=\"display:none;\">");
        str.append("[header]=").append(MCUtil.getBundleString("message.material.infor"));
        str.append("[content]=");
        str.append("<b>").append(MCUtil.getBundleString("message.material.nameVn")).append(":</b> ").append(bean.getMatName()).append("<br/>");
        str.append("<b>").append(MCUtil.getBundleString("message.material.code")).append(":</b> ").append(bean.getMatCode()).append("<br/>");
        str.append("<b>").append(MCUtil.getBundleString("message.mrir.reqId")).append(":</b> ").append(this.getReqNumber());
        if (GenericValidator.isBlankOrNull(bean.getProName())) {
            str.append("<br/>");
        } else {
            str.append(" - <b>").append(MCUtil.getBundleString("message.project")).append(":</b> ").append(bean.getProName()).append("<br/>");
        }
        str.append("</span></div>");
        str.append("<input type=\"hidden\" name=\"matId\" value=\"");
        str.append(bean.getMatId()).append("\">");
        str.append("<input type=\"hidden\" id=\"mat_name_" + bean.getDetId() + "\" name=\"matName\" value=\"").append(bean.getMatName()).append("\">");
        str.append("<input type=\"hidden\" name=\"reqDetailId\" value=\"");
        str.append(bean.getReqDetailId()).append("\">");
        str.append("<input type=\"hidden\" name=\"price\" value=\"");
        str.append(bean.getPrice()).append("\">");


        return str.toString();
//        return bean.getMatName() + "<input type=\"hidden\" name=\"matId\" value=\"" +
//                bean.getMatId() + "\">" +
//                "<input type=\"hidden\" name=\"reqDetailId\" value=\"" +
//                bean.getReqDetailId() + "\">" +
//                "<input type=\"hidden\" name=\"price\" value=\"" +
//                bean.getPrice() + "\">";
    }

//    public String getMatCode() {
//        MrirDetailBean bean = (MrirDetailBean) getCurrentRowObject();
//         return bean.getMatCode();
//    }
    public String getUnit() {
        MrirDetailBean bean = (MrirDetailBean) getCurrentRowObject();
        return bean.getUnit() + "<input type=\"hidden\" name=\"unit\" value=\"" +
                bean.getUnit() + "\">";
    }

    public String getOriginal() {
        MrirDetailBean bean = (MrirDetailBean) getCurrentRowObject();
        return "<input type=\"text\" size=\"10\" name=\"original\" value=\"" +
                bean.getOriginal() + "\">";
    }

    public String getWarranty() {
        MrirDetailBean bean = (MrirDetailBean) getCurrentRowObject();

        return "<input type=\"text\" size=\"10\" name=\"warranty\" value=\"" +
                bean.getWarranty() + "\">";
    }

    public String getInspection() {
        MrirDetailBean bean = (MrirDetailBean) getCurrentRowObject();

        return "<input type=\"text\" size=\"10\" name=\"inspection\" value=\"" +
                bean.getInspection() + "\">";
    }

    public String getInsurance() {
        MrirDetailBean bean = (MrirDetailBean) getCurrentRowObject();

        return "<input type=\"text\" size=\"10\" name=\"insurance\" value=\"" +
                bean.getInsurance() + "\">";
    }

    public String getQuality() {
        MrirDetailBean bean = (MrirDetailBean) getCurrentRowObject();

        return "<input type=\"text\" size=\"10\" name=\"quality\" value=\"" +
                bean.getQuality() + "\">";
    }

    public String getApprovalType() {
        MrirDetailBean bean = (MrirDetailBean) getCurrentRowObject();

        return "<input type=\"text\" size=\"6\" name=\"approvalType\" value=\"" +
                bean.getApprovalType() + "\">";
    }

    public String getComplCert() {
        MrirDetailBean bean = (MrirDetailBean) getCurrentRowObject();

        return "<input type=\"text\" size=\"6\" name=\"complCert\" value=\"" +
                bean.getComplCert() + "\">";
    }

    public String getManufacture() {
        MrirDetailBean bean = (MrirDetailBean) getCurrentRowObject();

        return "<input type=\"text\" size=\"6\" name=\"manufacture\" value=\"" +
                bean.getManufacture() + "\">";
    }

    public String getHeatSerial() {
        MrirDetailBean bean = (MrirDetailBean) getCurrentRowObject();

        return "<input type=\"text\" size=\"6\" name=\"heatSerial\" value=\"" +
                bean.getHeatSerial() + "\">";
    }

    public String getMaterialGrade() {
        MrirDetailBean bean = (MrirDetailBean) getCurrentRowObject();

        return "<input type=\"text\" size=\"6\" name=\"materialGrade\" value=\"" +
                bean.getMaterialGrade() + "\">";
    }

    public String getMaterialType() {
        MrirDetailBean bean = (MrirDetailBean) getCurrentRowObject();

        return "<input type=\"text\" size=\"6\" name=\"materialType\" value=\"" +
                bean.getMaterialType() + "\">";
    }

    public String getRating() {
        MrirDetailBean bean = (MrirDetailBean) getCurrentRowObject();

        return "<input type=\"text\" size=\"6\" name=\"rating\" value=\"" +
                bean.getRating() + "\">";
    }

    public String getSize1() {
        MrirDetailBean bean = (MrirDetailBean) getCurrentRowObject();

        return "<input type=\"text\" size=\"6\" name=\"size1\" value=\"" +
                bean.getSize1() + "\">";
    }

    public String getSize2() {
        MrirDetailBean bean = (MrirDetailBean) getCurrentRowObject();

        return "<input type=\"text\" size=\"6\" name=\"size2\" value=\"" +
                bean.getSize2() + "\">";
    }

    public String getSize3() {
        MrirDetailBean bean = (MrirDetailBean) getCurrentRowObject();

        return "<input type=\"text\" size=\"6\" name=\"size3\" value=\"" +
                bean.getSize3() + "\">";
    }

    public String getTraceNo() {
        MrirDetailBean bean = (MrirDetailBean) getCurrentRowObject();

        return "<input type=\"text\" size=\"6\" name=\"traceNo\" value=\"" +
                bean.getTraceNo() + "\">";
    }

    public String getCertNo() {
        MrirDetailBean bean = (MrirDetailBean) getCurrentRowObject();

        return "<input type=\"text\" size=\"6\" name=\"certNo\" value=\"" +
                bean.getCertNo() + "\">";
    }

    public String getColourCode() {
        MrirDetailBean bean = (MrirDetailBean) getCurrentRowObject();

        return "<input type=\"text\" size=\"6\" name=\"colourCode\" value=\"" +
                bean.getColourCode() + "\">";
    }

    protected String createTotalRow(boolean grandTotal) {
        StringBuffer buffer = new StringBuffer(1000);
        buffer.append("\n<tr class=\"total\">"); //$NON-NLS-1$

        List headerCells = tableModel.getHeaderCellList();
        int col = 0;
        for (int i = 0; i < headerCells.size(); i++) {
            HeaderCell cell = (HeaderCell) headerCells.get(i);
            if (cell.isTotaled()) {
                col = i;
                break;
            }
        }
        if (col > 1) {
            buffer.append("<td"); //$NON-NLS-1$            
            buffer.append(" style=\"text-align:center\" colspan=\""); //$NON-NLS-1$
            buffer.append(col);
            buffer.append("\""); //$NON-NLS-1$            
            buffer.append(">"); //$NON-NLS-1$          
            buffer.append(MCUtil.getBundleString("message.SUM"));
            buffer.append("</td>"); //$NON-NLS-1$
        } else {
            col = 0;
        }

        for (int i = col; i < headerCells.size(); i++) {
            HeaderCell cell = (HeaderCell) headerCells.get(i);
            String cssClass = ObjectUtils.toString(cell.getHtmlAttributes().get("class"));

            buffer.append("<td"); //$NON-NLS-1$
            if (StringUtils.isNotEmpty(cssClass)) {
                buffer.append(" class=\""); //$NON-NLS-1$
                buffer.append(cssClass);
                buffer.append("\""); //$NON-NLS-1$
            }
            buffer.append(">"); //$NON-NLS-1$           
            if (cell.isTotaled()) {

                String totalPropertyName = cell.getBeanPropertyName();
                Object total = grandTotal ? grandTotals.get(totalPropertyName) : subTotals.get(totalPropertyName);

                DisplaytagColumnDecorator[] decorators = cell.getColumnDecorators();
                for (int j = 0; j < decorators.length; j++) {
                    try {
                        total = decorators[j].decorate(total, this.getPageContext(), tableModel.getMedia());
                    } catch (DecoratorException e) {
                        log.warn(e.getMessage(), e);
                    // ignore, use undecorated value for totals
                    }
                }
                //buffer.append(total);
                buffer.append("<input type=\"text\" readonly=\"true\" style=\"text-align:right;\" size=\"8\" name=\"total\" value=\"");
                buffer.append(NumberUtil.formatMoneyDefault(Double.parseDouble(total.toString())));
                buffer.append("\">");
            } else if (groupPropertyName != null && groupPropertyName.equals(cell.getBeanPropertyName())) {
                buffer.append(grandTotal ? totalLabel : MessageFormat.format(subtotalLabel, new Object[]{previousValues.get(groupPropertyName)}));
            }
            buffer.append("</td>"); //$NON-NLS-1$

        }

        buffer.append("</tr>"); //$NON-NLS-1$      
        // reset subtotal
        this.subTotals.clear();

        return buffer.toString();
    }

    @Override
    public String addRowId() {
        MrirDetailBean bean = (MrirDetailBean) getCurrentRowObject();
        return "mrir_row_" + bean.getDetId();
    }

    protected ArrayList getMatKindList(int kind) {
        MrirDAO mrirDAO = new MrirDAO();
        ArrayList arrKind = null;
        try {
            arrKind = mrirDAO.getMatKindList(kind);
        } catch (Exception ex) {
            LogUtil.error("MrirMaterialDecorator:" + ex.getMessage());
        }
        return arrKind;
    }
}
