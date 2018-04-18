<%@page import="com.venus.mc.util.PermissionUtil"%>
<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.MCUtil"%>
<%@ page import="com.venus.mc.util.Constants"%>
<h3><bean:message key="message.mrirlist.title"/></h3>
<div id="errorMessageDiv"></div>
<html:form action="searchMrir.do">
    <table>
        <tr>
            <td>
                <html:image src="images/blank.png" onclick="return searchMrir();"/>
                <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_DELETE, PermissionUtil.PER_STOCK_MRIR)) {%>
                <html:image src="images/ico_xoa.gif" onclick="return delMrirs();"/>
                <%}%>
                <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_ADD, PermissionUtil.PER_STOCK_MRIR)) {%>
                <html:image src="images/ico_them.gif"  onclick="return mrirForm();"/>
                <!--<html:image src="images/ico_themmrir.gif"  onclick="return mrirForm(null,1);"/>-->
                <%}%>
            </td>
            <td><div>
                <html:select property="searchid">
                    <html:option key="message.all" value='0'/>
                    <html:option key="message.mrir.mrirNumber" value='1'/>
                    <html:option key="message.mrir.dnId" value='2'/>
                    <html:option key="message.mrir.reqId" value='3'/>
                </html:select>
                <html:text property="searchvalue" />                
                <html:submit onclick="return searchMrir();"><bean:message key="message.search"/></html:submit>
                <html:submit onclick="return searchAdvMrirForm();"><bean:message key="message.detailSearch"/></html:submit>
        </div></td>
        </tr>
        <tr><td colspan="2">&nbsp;</td></tr>
        <tr><td colspan="2"><bean:message key="message.project"/>
                <html:select property="searchvalues" onchange="searchMrir();">
                    <html:options collection="<%=Constants.MRIR_PROJECT_LIST%>" property="value" labelProperty="label"/>
                </html:select>
            </td></tr>
    </table>
</html:form>
<form name='mrirsForm' id='mrirsForm'><div id='mrirList'></div></form>