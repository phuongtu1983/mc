<%@page import="com.venus.mc.util.PermissionUtil"%>
<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>
<table style="width:100%"  class="its" id="priceComparisonDetailTable">
    <thead>
        <tr>
            <th><bean:message key="message.pricecomparison.stt"/></th>
            <th><bean:message key="message.pricecomparison.matName"/></th>
            <th><bean:message key="message.pricecomparison.unit"/></th>
            <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_LIST, PermissionUtil.PER_LIBRARY_MATERIAL_PRICE)) {%>
            <th><bean:message key="message.pricecomparison.latestPrice"/></th>
            <th><bean:message key="message.comevalmaterial.currency"/></th>
            <%}%>
            <th><bean:message key="message.pricecomparison.contractNumber"/></th>
            <th><bean:message key="message.pricecomparison.effectedDate"/></th>
            <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_LIST, PermissionUtil.PER_LIBRARY_MATERIAL_PRICE)) {%>
            <th><bean:message key="message.pricecomparison.internetPrice"/></th>
            <th><bean:message key="message.pricecomparison.proposedPrice"/></th>
            <th><bean:message key="message.comevalmaterial.currency"/></th>
            <%}%>
        </tr>
    </thead>
    <tbody>
        <logic:iterate id="detail" name="<%=Constants.PRICECOMPARISON_LIST%>">
            <tr>
                <td width="30px">
                    <input type="hidden" name="regDetId" value='<bean:write name="detail" property="detId"/>'/>
                    <html:hidden name="detail" property="matId"/>
                    <html:hidden name="detail" property="detId"/>
                    <span><div align="center"><bean:write name="detail" property="stt"/></div></span></td>
                <td width="260px">
                    <span><bean:write name="detail" property="matName"/></span>
                </td>
                <td width="80px"><span><div align="center"><bean:write name="detail" property="unit"/></div></span></td>
                <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_LIST, PermissionUtil.PER_LIBRARY_MATERIAL_PRICE)) {%>
                <td width="80px"><html:text readonly="true" style="border-width:1px;text-align:right" size="11" name="detail" property="latestPrice" /></td>
                <td width="80px"><html:text readonly="true" style="border-width:1px;text-align:right" size="3" name="detail" property="currency1" /></td>
                <%} else {%>
                <html:hidden property="latestPrice" name="detail"/>
                <%}%>
                <td width="80px"><html:text readonly="true" style="border-width:1px;text-align:right" size="11" name="detail" property="contractNumber" /></td>
                <td width="80px"><html:text readonly="true" style="border-width:1px;text-align:right" size="11" name="detail" property="effectedDate" /></td>
                <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_LIST, PermissionUtil.PER_LIBRARY_MATERIAL_PRICE)) {%>
                <td width="80px"><html:text style="border-width:1px;text-align:right" size="11" name="detail" property="internetPrice" /></td>
                <td width="80px"><html:text readonly="true" style="border-width:1px;text-align:right" size="11" name="detail" property="proposedPrice" /></td>
                <td width="80px"><html:text readonly="true" style="border-width:1px;text-align:right" size="3" name="detail" property="currency2" /></td>
                <%} else {%>
                <html:hidden property="internetPrice" name="detail"/>
                <html:hidden property="proposedPrice" name="detail"/>
                <%}%>
            </tr>
        </logic:iterate>
    </tbody>
</table>