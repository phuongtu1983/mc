<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<h3><bean:message key="message.listsurveyreport.title"/></h3>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<div id="errorMessageDiv"></div>
<html:form action="searchSurveyReport.do">
    <table>
        <tr>
            <td>
                <html:image src="images/blank.png" onclick="return searchSurveyReport();"/>
                <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_DELETE, PermissionUtil.PER_EQUIPMENT)) {%>
                <html:image src="images/ico_xoa.gif" onclick="return delSurveyReports();"/>
                <%}%>
                <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_ADD, PermissionUtil.PER_EQUIPMENT)) {%>
                <html:image src="images/ico_them.gif"  onclick="return surveyreportForm();"/>
                <%}%>
            </td>
            <td><div>
                <html:select property="searchid">
                    <html:option key="message.all" value='0'/>
                    <html:option key="message.surveyreport.srNumber" value='1'/>
                    <html:option key="message.surveyreport.surveyDate" value='2'/>
                    <html:option key="message.surveyreport.usedOrg" value='3'/>
                </html:select>
                <html:text property="searchvalue" />
                <html:submit onclick="return searchSurveyReport();"><bean:message key="message.search"/></html:submit>
        </div></td>
        </tr>
    </table>
</html:form>
<form name='surveyreportsForm' id='surveyreportsForm'><div id='surveyreportList'></div></form>