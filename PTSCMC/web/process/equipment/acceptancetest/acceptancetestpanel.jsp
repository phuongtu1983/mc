<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<h3><bean:message key="message.listacceptancetest.title"/></h3>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<div id="errorMessageDiv"></div>
<html:form action="searchAcceptanceTest.do">
    <table>
        <tr>
            <td>
                <html:image src="images/blank.png" onclick="return searchAcceptanceTest();"/>
                <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_DELETE, PermissionUtil.PER_EQUIPMENT_SURVEYREPORT)) {%>
                <html:image src="images/ico_xoa.gif" onclick="return delAcceptanceTests();"/>
                <%}%>
                <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_ADD, PermissionUtil.PER_EQUIPMENT_SURVEYREPORT)) {%>
                <html:image src="images/ico_them.gif"  onclick="return acceptancetestForm();"/>
                <%}%>
            </td>
            <td><div>
                <html:select property="searchid">
                    <html:option key="message.all" value='0'/>
                    <html:option key="message.acceptancetest.atNumber" value='1'/>
                    <html:option key="message.acceptancetest.testDate" value='2'/>
                    <html:option key="message.acceptancetest.usedOrg" value='3'/>
                </html:select>
                <html:text property="searchvalue" />
                <html:submit onclick="return searchAcceptanceTest();"><bean:message key="message.search"/></html:submit>
        </div></td>
        </tr>
    </table>
</html:form>
<form name='acceptancetestsForm' id='acceptancetestsForm'><div id='acceptancetestList'></div></form>