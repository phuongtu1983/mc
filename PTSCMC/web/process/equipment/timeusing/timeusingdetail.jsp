<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ page import="com.venus.mc.process.equipment.timeusing.TimeUsingFormBean"%>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<div id="timeUsingFormTitle"><h3><bean:message key="message.timeusing.title"/></h3></div>
<div id="tuErrorMessageDiv"></div>
<form name="timeUsingForm">
    <table width="100%" style="width:100%">
        <tr>
            <td class="lbl10"><bean:message key="message.timeusing.updatedate"/></td>
            <td colspan="0"><html:text size="20" property="updateDate" name="<%=Constants.TIMEUSING%>"/></td>
            <td class="lbl10"><bean:message key="message.timeusing.createdemp"/></td>
            <td colspan="0"><html:text property="createdEmpName" readonly="true" size="20" name="<%=Constants.TIMEUSING%>"/></td>
        </tr>
        <tr>            
            <td colspan="4"><%@include  file="details.jsp" %></td>
        </tr>
    </table>
    <%
            TimeUsingFormBean form = (TimeUsingFormBean) request.getAttribute(Constants.TIMEUSING);
            if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_EDIT, PermissionUtil.PER_EQUIPMENT_REPAIRREQUEST, form.getCreatedEmpId())) {
    %>
    <html:image onclick="return saveTimeUsing();" src="images/but_save.gif"/><img src="images/blank.gif" width="2" height="17">
    <%}%>
    <html:image property="Back" value="Back" src="images/but_back.gif" onclick="return loadTimeUsingList();"/>    
</form>
<div id="timeUsingMaterialHideDiv" style="display:none"></div>