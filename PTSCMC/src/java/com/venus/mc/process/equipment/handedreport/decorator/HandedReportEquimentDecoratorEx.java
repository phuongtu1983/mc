/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.process.equipment.handedreport.decorator;

import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.EquipmentBean;
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
public class HandedReportEquimentDecoratorEx extends TotalTableDecorator {

    public HandedReportEquimentDecoratorEx() {
        super();
    }

    public String getDelId() {
        EquipmentBean bean = (EquipmentBean) getCurrentRowObject();
        String disabled = "";
//        if (bean.getStatus() == 1) {
//            disabled = "disabled";
//        }        
        int idx = getListIndex() + 1;
        return "<input type=\"checkbox\" name=\"chkDetId\" value=\"hr_" + idx + "\"" + disabled +
                //" onclick=\"removeMrvRow(" + bean.getDetId()+ ")\"" +
                "><input type=\"hidden\" name=\"detId\" value=\"hr_" + idx + "\"" + ">";
    }

    public String getEquipmentName() {
        EquipmentBean bean = (EquipmentBean) getCurrentRowObject();
        return bean.getEquipmentName() + "<input type=\"hidden\" name=\"equId\" value=\"" + bean.getEquId() + "\"" + ">" +
                "<input type=\"hidden\" name=\"equipmentName\" value=\"" + bean.getEquipmentName() + "\"" + ">";
    }

    public String getUseCode() {
        EquipmentBean bean = (EquipmentBean) getCurrentRowObject();
        return bean.getUsedCode() + "<input type=\"hidden\" name=\"useCode\" value=\"" + bean.getUsedCode() + "\"" + ">";
    }

    public String getUnit() {
        EquipmentBean bean = (EquipmentBean) getCurrentRowObject();
        return bean.getUnit();
    }

    public String getQuantity() {
        EquipmentBean bean = (EquipmentBean) getCurrentRowObject();
        return "<input type=\"text\" size=\"10\" name=\"quantity\" value=\"\"" + ">";
    }
    
    public String getComment() {
        EquipmentBean bean = (EquipmentBean) getCurrentRowObject();
        return "<input type=\"text\" size=\"30\" name=\"comment\" value=\"\"" + ">";
    }
    
    public String getSpecifications() {
        EquipmentBean bean = (EquipmentBean) getCurrentRowObject();
        return "<input type=\"text\" size=\"20\" name=\"specifications\" value=\"\"" + ">";
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
        return "hr_detail_row_" + (getListIndex() + 1);
    }
}
