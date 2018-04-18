<%@page import="com.venus.mc.util.PermissionUtil"%>
<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<h3><bean:message key="message.listincoterm.title"/></h3>
<div id="errorMessageDiv"></div>
<html:form action="searchIncoterm.do">
    <table>            
        <tr>
            <td>
                <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_DELETE, PermissionUtil.PER_LIBRARY_INCOTERM)) {%>
                <html:image src="images/ico_xoa.gif" onclick="return delIncoterms();"/>
                <%}%>
                <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_ADD, PermissionUtil.PER_LIBRARY_INCOTERM)) {%>
                <html:image src="images/ico_them.gif"  onclick="return incotermForm();"/>
                 <%}%>
            </td>
            <td><div>
                    <html:select property="searchid">
                        <html:option key="message.all" value='0'/>
                        <html:option key="message.incoterm.incName" value='1'/>
                        <html:option key="message.incoterm.comment" value='2'/>
                    </html:select>
                    <html:text property="searchvalue" size="60"/>
                    <html:submit onclick="return searchIncoterm();"><bean:message key="message.search"/></html:submit>
            </div></td>
        </tr>
    </table>
</html:form>
<form name='incotermsForm' id='incotermsForm'><div id='incotermList'></div></form>