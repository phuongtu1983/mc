<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%--<h3><bean:message key="message.listrepairnotplan.title"/></h3>--%>
<div id="errorMessageDiv"></div>
<html:form styleId="searchRepairNotPlan" action="searchRepairNotPlan.do">
    <table>            
        <tr>
            <td>
                <html:image src="images/blank.png" onclick="return searchRepairNotPlan();"/>
                <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_DELETE, PermissionUtil.PER_EQUIPMENT)) {%>
                <%--<html:image src="images/ico_xoa.gif" onclick="return delRepairNotPlans();"/>--%>
                <%}%>
                <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_ADD, PermissionUtil.PER_EQUIPMENT)) {%>
                <%--<html:image src="images/ico_them.gif"  onclick="return addRepairNotPlan();"/>--%>
                <%}%>
            </td>
            <td><div>
                    <html:select property="searchid">
                        <html:option key="message.all" value='0'/>
                        <html:option key="message.repairnotplan.content" value='1'/>
                        <html:option key="message.repairnotplan.cost" value='2'/>
                    </html:select>
                    <html:text property="searchvalue" />
                    <html:submit onclick="return searchRepairNotPlan();"><bean:message key="message.search"/></html:submit>
                    <html:submit onclick="return searchAdvRepairNotPlanForm();"><bean:message key="message.detailSearch"/></html:submit>
                </div></td>
        </tr>
    </table>
</html:form>
<form name='repairnotplansForm' id='repairnotplansForm'><div id='repairnotplanList'></div></form>