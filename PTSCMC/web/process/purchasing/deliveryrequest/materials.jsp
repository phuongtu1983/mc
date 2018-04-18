<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>
<table id="deliveryRequestMaterialTable">
    <logic:iterate id="detail" name="<%=Constants.MATERIAL_LIST%>">
        <tr>
            <td width="30px">
                <div align="center"><input type="checkbox" name="detId" value="0"/></div>
                <input type="hidden" name="delDetId" value="0"/>
                <html:hidden name="detail" property="matId"/>
                <!--<input type="hidden" name="conDetId" value="${detail.conDetailId}"/>-->
                <input type="hidden" name="conDetId" value="0"/>
                <input type="hidden" name="reqDetId" value="<bean:write name="detail" property="reqDetId"/>"/>
                <input type="hidden" id="reqDetQuantity${detail.conDetailId}" value="${detail.quantity}"/>
            </td>
            <!--<td><span><bean:write name="detail" property="contractNumber"/></span></td>-->
            <td></td>
            <td><span><bean:write name="detail" property="matName"/></span></td>
            <td><span><bean:write name="detail" property="requestNumber"/></span></td>
            <td><span><bean:write name="detail" property="origin"/></span></td>
            <td>
                <span><bean:write name="detail" property="unit"/></span>
                <html:hidden name="detail" property="unit"/>
            </td>
            <td width="120px"><input type="textbox"  styleClass="lbl10" style="border-width:1px;text-align:right" size="15" name="quantity" onfocus="reformatNumberMoney(this)" autofocus id="detquantity${detail.conDetailId}" value="${detail.quantity}"  onblur="tryNumberFormat(this);quantityDeliveryRequestDetailChanged(${detail.conDetailId})" /></td>
            <td width="120px"><input type="textbox" readonly styleClass="lbl10" style="border-width:1px;text-align:right" size="15" name="price" id="detprice${detail.conDetailId}" value="${detail.price}"  onblur="tryNumberFormat(this);caculateDeliveryRequestDetail(${detail.conDetailId})" /></td>
            <td width="30px"><input type="textbox" readonly styleClass="lbl10" style="border-width:1px;text-align:right" size="3" name="vat" id="detvat${detail.conDetailId}" value="${detail.vat}"  onblur="tryNumberFormat(this);caculateDeliveryRequestDetail(${detail.conDetailId})" /></td>
            <td width="120px"><input type="textbox" readonly size="15" readonly name="detTotal" id="detTotal${detail.conDetailId}" value="${detail.total}"/></td>
        </tr>
    </logic:iterate>
</table>