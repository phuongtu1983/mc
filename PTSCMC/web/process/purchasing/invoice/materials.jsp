<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>
<table width="100%" id="orderSourceMaterialTable">
<logic:iterate id="detail" name="<%=Constants.MATERIAL_LIST%>">
<tr>
<td width="30px">
    <div align="center"><input type="checkbox" name="detId" value="0"/></div>
    <input type="hidden" name="conDetId" value="0"/>
    <input type="hidden" name="reqDetId" value="${detail.reqDetailId}"/>
    <html:hidden name="detail" property="matId"/>
</td>
<td><span><bean:write name="detail" property="matName"/></span></td>
<td><span><bean:write name="detail" property="requestNumber"/></span></td>
<td><span><bean:write name="detail" property="matOrigin"/></span></td>
<td>
    <span><bean:write name="detail" property="unit"/></span>
    <html:hidden name="detail" property="unit"/>
</td>
<td width="60px"><html:text styleClass="lbl10" style="border-width:1px;text-align:right" size="4" name="detail" property="quantity" styleId="detquantity${detail.matId}_${detail.reqDetailId}"  onblur="tryNumberFormat(this);caculateInvoiceDetail('${detail.matId}_${detail.reqDetailId}')" /></td>
<td width="40px"><input type="text" style="border-width:1px;text-align:right" size="10" name="price" id="detprice<bean:write name="detail" property="matId"/>_<bean:write name="detail" property="reqDetailId"/>" value="<bean:write name="detail" property="price"/>" readonly/></td>
<td width="30px"><input type="text" style="border-width:1px;text-align:right" size="3" name="vat" id="detvat<bean:write name="detail" property="matId"/>_<bean:write name="detail" property="reqDetailId"/>" value="<bean:write name="detail" property="vat"/>" readonly/></td>
<td width="40px"><input type="text" style="border-width:1px;text-align:right" size="10" name="detTotal" readonly id="detTotal<bean:write name="detail" property="matId"/>_<bean:write name="detail" property="reqDetailId"/>" value="<bean:write name="detail" property="total"/>" readonly/></td>
</tr>
</logic:iterate>
</table>