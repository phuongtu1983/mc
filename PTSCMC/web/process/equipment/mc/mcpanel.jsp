<%-- 
    Document   : mcpanel
    Created on : Oct 23, 2009, 3:31:56 PM
    Author     : kngo
--%>

<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.PermissionUtil"%>

<h3><bean:message key="message.mclist.title"/></h3>
<div id="errorMessageDiv"></div>
<html:form action="searchMc.do">
    <table>
        <tr>
            <td>
                <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_DELETE, PermissionUtil.PER_EQUIPMENT_MC)) {%>
                <html:image src="images/ico_xoa.gif" onclick="return delMcs();"/>
                <%}%>
                <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_ADD, PermissionUtil.PER_EQUIPMENT_MC)) {%>
                <html:image src="images/ico_them.gif"  onclick="return mcForm();"/>
                <%}%>
            </td>
            <td><div>
                    <html:select property="searchid">
                        <html:option key="message.all" value='0'/>
                        <html:option key="message.mc.mcNumber" value='1'/>
                    </html:select>
                    <html:text property="searchvalue" />
                    <html:submit onclick="return searchMc();"><bean:message key="message.search"/></html:submit>
                    <html:submit onclick="return searchAdvMcForm();"><bean:message key="message.detailSearch"/></html:submit>
            </div></td>
        </tr>
    </table>
</html:form>
<form name='mcsForm' id='mcsForm'><div id='mcList'></div></form>
<html:image src="images/but_print.gif" onclick="return printMcs();" />
