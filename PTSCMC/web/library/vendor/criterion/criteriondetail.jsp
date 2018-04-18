<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<h3><bean:message key="message.criterionadd.title"/></h3>
<form name="venCriForm">
    <table style="width:100%">
        <tr>
            <td><bean:message key="message.vendor.criterion.name"/></td>
            <td><html:textarea rows="3" cols="80"  property="name" name="<%=Constants.EVAL_CRITERION%>" /></td>
        </tr>
        <tr>
            <td><bean:message key="message.vendor.criterion.reach"/></td>
            <td><html:textarea rows="3" cols="80"  property="reach" name="<%=Constants.EVAL_CRITERION%>" /></td>
        </tr>
        <tr>
            <td><bean:message key="message.vendor.criterion.notreach"/></td>
            <td><html:textarea rows="3" cols="80"  property="notReach" name="<%=Constants.EVAL_CRITERION%>" /></td>
        </tr>
        <tr>
            <td><bean:message key="message.note"/></td>
            <td><html:textarea rows="3" cols="80"  property="comments" name="<%=Constants.EVAL_CRITERION%>" /></td>
        </tr>
    </table>
    <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_EDIT, PermissionUtil.PER_LIBRARY_VENDOR_EVAL)) {%>
    <html:image onclick="return saveCriterion();" src="images/but_save.gif"/><img src="images/blank.gif" width="2" height="17">
    <%}%>
    <html:image property="Back" value="Back" src="images/but_back.gif" onclick="return loadCriterionList();"/>
    <html:hidden property="criId" name="<%=Constants.EVAL_CRITERION%>"/>
</form>