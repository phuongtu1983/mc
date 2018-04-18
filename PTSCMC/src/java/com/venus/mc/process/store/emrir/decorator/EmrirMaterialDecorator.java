/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.process.store.emrir.decorator;

import com.venus.core.util.GenericValidator;
import com.venus.core.util.LogUtil;
import com.venus.core.util.NumberUtil;
import com.venus.core.util.StringUtil;
import com.venus.mc.bean.EmrirDetailBean;
import com.venus.mc.dao.MrirDAO;
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
public class EmrirMaterialDecorator extends TotalTableDecorator {

    protected ArrayList arrMatKind = null;

    public EmrirMaterialDecorator() {
        super();
        if (arrMatKind == null) {
            arrMatKind = getMatKindList();
        }

    }

    public String getDelId() {
        EmrirDetailBean bean = (EmrirDetailBean) getCurrentRowObject();
        String disabled = "";
        if (bean.getStatus() == 1) {
            disabled = "disabled";
        }
        return "<input type=\"checkbox\" name=\"chkDetId\" value=\"" + bean.getDetId() + "\"" + disabled +
                //" onclick=\"removeMrvRow(" + bean.getDetId()+ ")\"" +
                "><input type=\"hidden\" name=\"detId\" value=\"" + bean.getDetId() + "\"" + ">";
    }

    public String getSystemNo() {
        EmrirDetailBean bean = (EmrirDetailBean) getCurrentRowObject();
        return "<input type=\"text\" id=\"systemno_" +
                bean.getDetId() +
                "\" size=\"6\" name=\"systemNo\" " +
                "value=\"" + bean.getSystemNo() + "\"" +
                ">";
    }

    public String getItemNo() {
        EmrirDetailBean bean = (EmrirDetailBean) getCurrentRowObject();
        return "<input type=\"text\" id=\"itemno_" +
                bean.getDetId() +
                "\" size=\"6\" name=\"itemNo\" " +
                "value=\"" + bean.getItemNo() + "\"" +
                ">";
    }

    public String getLocation() {
        EmrirDetailBean bean = (EmrirDetailBean) getCurrentRowObject();
        return "<input type=\"text\" id=\"location_" +
                bean.getDetId() +
                "\" style=\"text-align:left;\" size=\"20\" name=\"location\" " +
                "value=\"" + bean.getLocation() + "\"" +
                ">";
    }

    public String getComment() {
        EmrirDetailBean bean = (EmrirDetailBean) getCurrentRowObject();
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

    public String getQuantity() {
        EmrirDetailBean bean = (EmrirDetailBean) getCurrentRowObject();
        return "<input type=\"text\" id=\"quantity_" +
                bean.getDetId() +
                "\" style=\"text-align:right;\" size=\"8\" name=\"quantity\" " +
                "value=\"" + bean.getQuantity() + "\"" +
                //"onkeyup=\"calculateMsvRow(" +  bean.getDetId() +")\"" +
                //"readonly=\"true\"" + 
                ">";
    }

    public String getPrice() {
        EmrirDetailBean bean = (EmrirDetailBean) getCurrentRowObject();
        return "<input type=\"text\" id=\"price_" +
                bean.getDetId() +
                "\" style=\"text-align:right;\" size=\"8\" name=\"price\" value=\"" +
                NumberUtil.formatMoneyDefault(bean.getPrice()) + "\"" +
                "readonly=\"true\"" +
                ">";

    }

    public String getMatKind() {
        EmrirDetailBean bean = (EmrirDetailBean) getCurrentRowObject();
        StringBuffer buffer = new StringBuffer();
        if (arrMatKind != null) {
            buffer.append("<select name=\"matKind\" class=\"lbl10\">");
            for (int i = 0; i < arrMatKind.size(); i++) {
                LabelValueBean value = (LabelValueBean) arrMatKind.get(i);
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

    /*
    public String getReqNumber() {
    EmrirDetailBean bean = (EmrirDetailBean) getCurrentRowObject();
    if (bean.getReqId() > 0) {
    return "<a href=\"#\" onclick=\"return printRequest(" + bean.getReqId() +
    ");\">" + bean.getReqNumber() + "</a>";
    } else {
    return "";
    }
    }
     */
    public String getMatName() {
        EmrirDetailBean bean = (EmrirDetailBean) getCurrentRowObject();

        StringBuffer str = new StringBuffer();
        str.append("<div onmouseout=\"UnTip()\" onmouseover=displayTip('mrir_tip_").append(bean.getDetId()).append("')>").append(StringUtil.makeShortName(bean.getMatName(), 5));
        str.append("<span id=\"mrir_tip_").append(bean.getDetId()).append("\" style=\"display:none;\">");
        str.append("[header]=").append(MCUtil.getBundleString("message.material.infor"));
        str.append("[content]=");
        str.append("<b>").append(MCUtil.getBundleString("message.material.nameVn")).append(":</b> ").append(bean.getMatName()).append("<br/>");
        str.append("<b>").append(MCUtil.getBundleString("message.material.code")).append(":</b> ").append(bean.getMatCode()).append("<br/>");

        str.append("</span></div>");
        str.append("<input type=\"hidden\" name=\"ematId\" value=\"");
        str.append(bean.getEmatId()).append("\">");
        str.append("<input type=\"hidden\" id=\"mat_name_" + bean.getDetId() + "\" name=\"matName\" value=\"").append(bean.getMatName()).append("\">");
        str.append("<input type=\"hidden\" name=\"price\" value=\"");
        str.append(bean.getPrice()).append("\">");
        return str.toString();

    /*        
    return bean.getMatName() + "<input type=\"hidden\" name=\"matId\" value=\"" +
    bean.getEmatId() + "\">" +                
    "<input type=\"hidden\" name=\"price\" value=\"" +
    bean.getPrice() + "\">";
     */
    }

//    public String getMatCode() {
//        EmrirDetailBean bean = (EmrirDetailBean) getCurrentRowObject();
//         return bean.getMatCode();
//    }
    public String getUnit() {
        EmrirDetailBean bean = (EmrirDetailBean) getCurrentRowObject();
        return bean.getUnit() + "<input type=\"hidden\" name=\"unit\" value=\"" +
                bean.getUnit() + "\">";
    }

    public String getHeatSerial() {
        EmrirDetailBean bean = (EmrirDetailBean) getCurrentRowObject();

        return "<input type=\"text\" size=4 name=\"heatSerial\" value=\"" +
                bean.getHeatSerial() + "\">";
    }

    public String getMaterialGrade() {
        EmrirDetailBean bean = (EmrirDetailBean) getCurrentRowObject();

        return "<input type=\"text\" size=4 name=\"materialGrade\" value=\"" +
                bean.getMaterialGrade() + "\">";
    }

    public String getMaterialType() {
        EmrirDetailBean bean = (EmrirDetailBean) getCurrentRowObject();

        return "<input type=\"text\" size=4 name=\"materialType\" value=\"" +
                bean.getMaterialType() + "\">";
    }

    public String getRating() {
        EmrirDetailBean bean = (EmrirDetailBean) getCurrentRowObject();

        return "<input type=\"text\" size=4 name=\"rating\" value=\"" +
                bean.getRating() + "\">";
    }

    public String getSize1() {
        EmrirDetailBean bean = (EmrirDetailBean) getCurrentRowObject();

        return "<input type=\"text\" size=4 name=\"size1\" value=\"" +
                bean.getSize1() + "\">";
    }

    public String getSize2() {
        EmrirDetailBean bean = (EmrirDetailBean) getCurrentRowObject();

        return "<input type=\"text\" size=4 name=\"size2\" value=\"" +
                bean.getSize2() + "\">";
    }

    public String getSize3() {
        EmrirDetailBean bean = (EmrirDetailBean) getCurrentRowObject();

        return "<input type=\"text\" size=4 name=\"size3\" value=\"" +
                bean.getSize3() + "\">";
    }

    public String getTraceNo() {
        EmrirDetailBean bean = (EmrirDetailBean) getCurrentRowObject();

        return "<input type=\"text\" size=4 name=\"traceNo\" value=\"" +
                bean.getTraceNo() + "\">";
    }

    public String getCertNo() {
        EmrirDetailBean bean = (EmrirDetailBean) getCurrentRowObject();

        return "<input type=\"text\" size=4 name=\"certNo\" value=\"" +
                bean.getCertNo() + "\">";
    }

    public String getColourCode() {
        EmrirDetailBean bean = (EmrirDetailBean) getCurrentRowObject();

        return "<input type=\"text\" size=4 name=\"colourCode\" value=\"" +
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
        EmrirDetailBean bean = (EmrirDetailBean) getCurrentRowObject();
        return "emrir_row_" + bean.getDetId();
    }

    protected ArrayList getMatKindList() {
        MrirDAO mrirDAO = new MrirDAO();
        ArrayList arrKind = null;
        try {
            arrKind = mrirDAO.getMatKindList(0);
        } catch (Exception ex) {
            LogUtil.error("EmrirMaterialDecorator:" + ex.getMessage());
        }
        return arrKind;
    }
}
