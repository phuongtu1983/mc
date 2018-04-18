<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%--<h3><bean:message key="message.listverifiedplan.title"/></h3>--%>
<div id="errorMessageDiv"></div>
<html:form styleId="searchVerifiedPlan" action="searchVerifiedPlan.do">
    <table>            
        <tr>
            <td>
                <html:image src="images/blank.png" onclick="return searchVerifiedPlan();"/>
                <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_DELETE, PermissionUtil.PER_EQUIPMENT)) {%>
                <html:image src="images/ico_xoa.gif" onclick="return delVerifiedPlans();"/>
                <%}%>
                <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_ADD, PermissionUtil.PER_EQUIPMENT)) {%>
                <html:image src="images/ico_them.gif"  onclick="return addVerifiedPlan();"/>
                <%}%>
            </td>
            <td><div>
                    <html:select property="searchid">
                        <html:option key="message.all" value='0'/>
                        <html:option key="message.verifiedplan.content" value='1'/>
                        <html:option key="message.verifiedplan.cost" value='2'/>
                    </html:select>
                    <html:text property="searchvalue" />
                    <html:submit onclick="return searchVerifiedPlan();"><bean:message key="message.search"/></html:submit>
                    <html:submit onclick="return searchAdvVerifiedPlanForm();"><bean:message key="message.detailSearch"/></html:submit>
                </div></td>
        </tr>
    </table>
</html:form>
<form name='verifiedplansForm' id='verifiedplansForm'><div id='verifiedplanList'></div></form>