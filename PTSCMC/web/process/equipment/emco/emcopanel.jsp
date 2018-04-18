<%-- 
    Document   : eemcoopanel
    Created on : Nov 5, 2009, 9:30:08 PM
    Author     : kngo
--%>
<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.PermissionUtil"%>

<h3><bean:message key="message.emcolist.title"/></h3>
<div id="errorMessageDiv"></div>
<html:form action="searchEmco.do">
    <table>
        <tr>
            <td>
                <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_DELETE, PermissionUtil.PER_EQUIPMENT_EMCO)) {%>                
                <html:image src="images/ico_xoa.gif" onclick="return delEmcos();"/>
                <%}%>
                <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_ADD, PermissionUtil.PER_EQUIPMENT_EMCO)) {%>
                <html:image src="images/ico_them.gif"  onclick="return emcoForm();"/>
                <%}%>
            </td>
            <td><div>
                    <html:select property="searchid">
                        <html:option key="message.all" value='0'/>
                        <html:option key="message.emco.emcoNumber" value='1'/>
                    </html:select>
                    <html:text property="searchvalue" />
                    <html:submit onclick="return searchEmco();"><bean:message key="message.search"/></html:submit>
                    <html:submit onclick="return searchAdvEmcoForm();"><bean:message key="message.detailSearch"/></html:submit>
            </div></td>
        </tr>
    </table>
</html:form>
<form name='emcosForm' id='emcosForm'><div id='emcoList'></div></form>
<html:image src="images/but_print.gif" onclick="return printEmcs();" />