/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.process.store.emrir.decorator;

import com.venus.core.util.NumberUtil;
import com.venus.core.util.StringUtil;
import com.venus.mc.bean.EosDDetailBean;
import com.venus.mc.util.MCUtil;
import java.text.MessageFormat;
import java.util.List;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.displaytag.decorator.DisplaytagColumnDecorator;
import org.displaytag.decorator.TotalTableDecorator;
import org.displaytag.exception.DecoratorException;
import org.displaytag.model.HeaderCell;

/**
 *
 * @author thcao
 */
public class EosDMaterialDecorator extends TotalTableDecorator {

    public EosDMaterialDecorator() {
        super();
    }

    public String getDelId() {
        EosDDetailBean bean = (EosDDetailBean) getCurrentRowObject();
        return "<input type=\"checkbox\" name=\"chkDetId\" value=\"" + bean.getDetId() + "\"" +
                //" onclick=\"removeMrvRow(" + bean.getDetId()+ ")\"" +
                "><input type=\"hidden\" name=\"detId\" value=\"" + bean.getDetId() + "\"" + ">";
    }

    public String getQuantity() {
        EosDDetailBean bean = (EosDDetailBean) getCurrentRowObject();
        return "<input type=\"text\" id=\"quantity_" +
                bean.getDetId() +
                "\" style=\"text-align:right;\" size=\"8\" name=\"quantity\" " +
                "value=\"" + bean.getQuantity() + "\"" +
                //"onkeyup=\"calculateMsvRow(" +  bean.getDetId() +")\"" +
                //"readonly=\"true\"" + 
                ">";
    }

//    public String getTotal() {
//        EosDDetailBean bean = (EosDDetailBean) getCurrentRowObject();
//        return "<input type=\"text\" id=\"total_" +
//                bean.getDetId() +
//                "\" style=\"text-align:right;\" size=\"8\" name=\"total\" value=\"" + 
//                NumberUtil.formatMoneyDefault(bean.getTotal()) + "\">";
//    }
//    public String getReqNumber() {
//        EosDDetailBean bean = (EosDDetailBean) getCurrentRowObject();
//        if (bean.getReqId() > 0) {
//            return "<a href=\"#\" onclick=\"return printRequest(" + bean.getReqId() +
//                    ");\">" + bean.getReqNumber() + "</a>";
//        } else {
//            return "";
//        }
//
//    }

    public String getMatName() {
        EosDDetailBean bean = (EosDDetailBean) getCurrentRowObject();
        StringBuffer str = new StringBuffer();

        str.append("<div onmouseout=\"UnTip()\" onmouseover=\"displayTip('mrir_tip_").append(bean.getDetId()).append("')\">").append(StringUtil.makeShortName(bean.getMatName(), 10));
        str.append("<span id=\"mrir_tip_").append(bean.getDetId()).append("\" style=\"display:none;\">");
        str.append("[header]=").append(MCUtil.getBundleString("message.material.infor"));
        str.append("[content]=");
        str.append("<b>").append(MCUtil.getBundleString("message.material.nameVn")).append(":</b> ").append(bean.getMatName()).append("<br/>");
        str.append("<b>").append(MCUtil.getBundleString("message.material.code")).append(":</b> ").append(bean.getMatCode()).append("<br/>");
        str.append("</span></div>");
        str.append("<input type=\"hidden\" name=\"ematId\" value=\"");
        str.append(bean.getEmatId()).append("\">");        
        return str.toString();
//        return bean.getMatName() + "<input type=\"hidden\" name=\"matId\" value=\"" +
//                bean.getMatId() + "\">" +
//                "<input type=\"hidden\" name=\"reqDetailId\" value=\"" +
//                bean.getReqDetailId() + "\">" +
//                "<input type=\"hidden\" name=\"price\" value=\"" +
//                bean.getPrice() + "\">";
    }

//    public String getMatCode() {
//        EosDDetailBean bean = (EosDDetailBean) getCurrentRowObject();
//         return bean.getMatCode();
//    }
    public String getUnit() {
        EosDDetailBean bean = (EosDDetailBean) getCurrentRowObject();
        return bean.getUnit() + "<input type=\"hidden\" name=\"unit\" value=\"" +
                bean.getUnit() + "\">";
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
                buffer.append(NumberUtil.formatNumberDefault(Double.parseDouble(total.toString())));
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
        EosDDetailBean bean = (EosDDetailBean) getCurrentRowObject();
        return "eosd_row_" + bean.getDetId();
    }
}
