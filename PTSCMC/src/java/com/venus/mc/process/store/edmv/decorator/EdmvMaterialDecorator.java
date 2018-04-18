/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.process.store.edmv.decorator;

import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.EdmvMaterialBean;
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
public class EdmvMaterialDecorator extends TotalTableDecorator {

    public EdmvMaterialDecorator() {
        super();
    }

    public String getDelId() {
        EdmvMaterialBean bean = (EdmvMaterialBean) getCurrentRowObject();        
        return "<input type=\"checkbox\" name=\"detId\" value=\"" + bean.getDetId() + "\"" +
                //" onclick=\"removeMrvRow(" + bean.getDetId()+ ")\"" +
                ">";
    }

    public String getQuantity() {
        EdmvMaterialBean bean = (EdmvMaterialBean) getCurrentRowObject();
        return "<input type=\"text\" id=\"quantity_" + 
                bean.getDetId() +
                "\" style=\"text-align:right;\" size=\"8\" name=\"quantity\" " +
                "value=\"" + bean.getQuantity() + "\"" +
                //"onkeyup=\"calculateMsvRow(" +  bean.getDetId() +")\"" +
                //"readonly=\"true\"" + 
                ">";       
    }

    public String getPrice() {
        EdmvMaterialBean bean = (EdmvMaterialBean) getCurrentRowObject();
        return "<input type=\"text\" id=\"price_" +
                bean.getDetId() +
                "\" style=\"text-align:right;\" size=\"8\" name=\"price\" value=\"" +                 
                NumberUtil.formatMoneyDefault(bean.getPrice()) + "\""+
                //"readonly=\"true\"" + 
                ">";
        
    }

    public String getTotal() {
        EdmvMaterialBean bean = (EdmvMaterialBean) getCurrentRowObject();
        return "<input type=\"text\" id=\"total_" +
                bean.getDetId() +
                "\" style=\"text-align:right;\" size=\"8\" name=\"total\" value=\"" + 
                NumberUtil.formatMoneyDefault(bean.getTotal()) + "\">";
    }
    
    public String getMatName() {
        EdmvMaterialBean bean = (EdmvMaterialBean) getCurrentRowObject();
         return bean.getMatName() + "<input type=\"hidden\" name=\"ematId\" value=\"" + 
                bean.getEmatId() + "\">"
                 //"<input type=\"hidden\" name=\"reqDetailId\" value=\"" + bean.getReqDetailId() + "\">"
                ;
    }
    
    public String getMatCode() {
        EdmvMaterialBean bean = (EdmvMaterialBean) getCurrentRowObject();
         return bean.getMatCode();
    }
    
    public String getUnit() {
         EdmvMaterialBean bean = (EdmvMaterialBean) getCurrentRowObject();
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
        EdmvMaterialBean bean = (EdmvMaterialBean) getCurrentRowObject();
        return "edmv_row_" + bean.getDetId();
    }
}
