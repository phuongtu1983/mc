<%@page import="com.venus.mc.util.PermissionUtil"%>
<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>
<table style="width:100%" id="invoiceDetailTable" class="its">
    <thead>
        <tr>
            <th width="20px"></th>
            <th><bean:message key="message.contract.material.name"/></th>
            <th><bean:message key="message.request"/></th>
            <th><bean:message key="message.contract.material.origin"/></th>
            <th><bean:message key="message.contract.material.unit"/></th>
            <th><bean:message key="message.contract.material.quantity"/></th>
            <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_LIST, PermissionUtil.PER_LIBRARY_MATERIAL_PRICE)) {%>
            <th><bean:message key="message.contract.material.price"/></th>
            <th><bean:message key="message.contract.material.VAT"/></th>
            <th><bean:message key="message.contract.material.total"/></th>
            <%}%>
        </tr>
    </thead>
    <tbody>
        <logic:present name="<%=Constants.CONTRACT_DETAIL_LIST%>">
            <logic:iterate id="detail" indexId="counter" name="<%=Constants.CONTRACT_DETAIL_LIST%>">
                <bean:define id="mod2" value="${counter%2}"/>
                <logic:equal name="mod2" value="1"><tr class="odd"></logic:equal>
                <logic:equal name="mod2" value="0"><tr class="even"></logic:equal>
                    <td width="30px">
                        <div align="center"><input type="checkbox" name="detId" value="<bean:write name="detail" property="detId"/>"/></div>
                        <input type="hidden" name="conDetId" value="<bean:write name="detail" property="detId"/>"/>
                        <html:hidden name="detail" property="matId"/>
                        <input type="hidden" name="reqDetId" value="<bean:write name="detail" property="reqDetailId"/>"/>
                    </td>
                    <td><span><bean:write name="detail" property="matName"/></span></td>
                    <td><span><bean:write name="detail" property="requestNumber"/></span></td>
                    <td><span><bean:write name="detail" property="matOrigin"/></span></td>
                    <td>
                        <span><bean:write name="detail" property="unit"/></span>
                        <html:hidden name="detail" property="unit"/>
                    </td>
                    <td width="30px"><html:text styleClass="lbl10" style="border-width:1px;text-align:right" size="4" name="detail" property="quantity" styleId="detquantity${detail.matId}_${detail.reqDetailId}"  onblur="caculateInvoiceDetail('${detail.matId}_${detail.reqDetailId}')"/></td>
                    <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_LIST, PermissionUtil.PER_LIBRARY_MATERIAL_PRICE)) {%>
                    <td width="40px"><html:text size="10" style="border-width:1px;text-align:right" name="detail" property="price" styleId="detprice${detail.matId}_${detail.reqDetailId}" readonly="true"/></td>
                    <td width="30px"><html:text size="3" style="border-width:1px;text-align:right" name="detail" property="vat" styleId="detvat${detail.matId}_${detail.reqDetailId}" readonly="true"/></td>
                    <td width="40px"><input type="text" style="border-width:1px;text-align:right" size="10" name="detTotal" readonly id="detTotal<bean:write name="detail" property="matId"/>_<bean:write name="detail" property="reqDetailId"/>" value="<bean:write name="detail" property="total"/>"/></td>
                    <%} else {%>
                    <html:hidden property="price" name="detail"/>
                    <html:hidden property="vat" name="detail"/>
                    <input type="text" hidden="" name="detTotal" readonly id="detTotal" />
                    <%}%>
                </tr>
            </logic:iterate>
        </logic:present>
    </tbody>
</table>