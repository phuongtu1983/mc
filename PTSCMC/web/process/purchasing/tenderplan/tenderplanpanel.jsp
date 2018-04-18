<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<h3><bean:message key="message.tenderplanadd.title"/>/<bean:message key="message.list.s"/></h3>
<div id="errorMessageDiv"></div>
<html:form action="searchTenderPlan.do">
    <table>
        <tr>
            <td>
                <html:image src="images/blank.png" onclick="return searchTenderPlan();"/>
                <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_DELETE, PermissionUtil.PER_PURCHASING_TENDERPLAN)) {%>
                <html:image src="images/ico_xoa.gif" onclick="return delTenderPlans();"/>
                <%}%>
                <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_ADD, PermissionUtil.PER_PURCHASING_TENDERPLAN)) {%>
                <html:image src="images/ico_them.gif"  onclick="return tenderPlanForm();"/>
                <%}%>
            </td>
            <td><div>
                    <html:select property="searchid">
                        <html:option key="message.all" value='0'/>
                        <html:option key="message.tenderplan.number" value='1'/>
                        <html:option key="message.tenderplan.handleEmployee" value='2'/>
                        <html:option key="message.tenderplan.packName" value='3'/>
                        <html:option key="message.tenderplan.plan.bidDeadline" value='4'/>
                        <html:option key="message.tenderplan.status" value='5'/>
                        <html:option key="message.l_request" value='6'/>
                    </html:select>
                    <html:text property="searchvalue" />
                    <html:submit onclick="return searchTenderPlan();"><bean:message key="message.search"/></html:submit>
                    <html:submit onclick="return searchAdvTenderPlanForm();"><bean:message key="message.detailSearch"/></html:submit>
                    <img onclick="return exportTenderPlan();" src="images/but_print.gif"/>
            </div></td>
        </tr>
    </table>
</html:form>
<form name='tenderplansForm' id='tenderplansForm'><div id='tenderPlanList'></div></form>