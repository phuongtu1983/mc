<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<%@ page import="com.venus.mc.util.MCUtil"%>
<%@ page import="com.venus.mc.util.Constants"%>
<%
            String kind = MCUtil.getParameter("kind", request);
%>
<h3>
    <% if (Integer.parseInt(kind) < 5) {%>
    <bean:message key="message.dnlist.title"/>
    <% } else {%>
    <bean:message key="message.testlist.title"/>
    <% }%>
</h3>
<div id="errorMessageDiv"></div>
<html:form action="searchDn.do">
    <table>
        <tr>
            <td><% if (Integer.parseInt(kind) < 5) {%>
                <html:image src="images/blank.png" onclick="return searchDn();"/>
                <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_DELETE, PermissionUtil.PER_PURCHASING_DELIVERYNOTICE)) {%>
                <html:image src="images/ico_xoa.gif" onclick="return delDns();"/>
                <%}%>
                <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_ADD, PermissionUtil.PER_PURCHASING_DELIVERYNOTICE)) {%>
                <html:image src="images/ico_them.gif"  onclick="return dnForm();"/>
                <%}%>
                <% }%>                
            </td>
            <td><div><html:select property="searchvalues" onchange="searchDn();">
                        <html:options collection="<%=Constants.PROJECT_LIST%>" property="value" labelProperty="label"/>
                    </html:select>
                    <html:select property="searchid">
                        <html:option key="message.all" value='0'/>
                        <html:option key="message.dn.dnNumber" value='1'/>
                        <html:option key="message.dn.deliveryPlace" value='2'/>
                        <html:option key="message.dn.contractNumber" value='3'/>
                        <html:option key="message.u_vendor" value='4'/>
                        <html:option key="message.payment.invoice" value='5'/>
                        <html:option key="message.contract.followEmp" value='6'/>
                        <html:option key="message.dn.statusInvoice" value='7'/>
                    </html:select>
                    <html:text property="searchvalue" />                    
                    <html:submit onclick="return searchDn();"><bean:message key="message.search"/></html:submit>
                    <% if (Integer.parseInt(kind) < 5) {%>
                    <html:submit onclick="return searchAdvDnForm();"><bean:message key="message.detailSearch"/></html:submit>
                    <% }%>
                </div></td>
        </tr>        
    </table>

</html:form>
<form name='dnsForm' id='dnsForm'><div id='dnList'></div><input type="hidden" id ="kind" name="kind" value="<%=kind%>"/></form>