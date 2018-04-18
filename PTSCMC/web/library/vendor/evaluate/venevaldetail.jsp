<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<form name="venEvalForm">
    <table style="width:100%">
        <tr>
            <td><bean:message key="message.vendor.evaluate.number"/></td>
            <td colspan="3"><html:text property="evalNumber" size="30" name="<%=Constants.VENDOR_EVAL%>" /></td>
        </tr>
        <tr>
            <td><bean:message key="message.vendor.l_name"/></td>
            <td colspan="3"><html:text property="name" size="80" disabled="true"  name="<%=Constants.VENDOR%>"/></td>
        </tr>
        <tr>
            <td><bean:message key="message.vendor.phone"/></td>
            <td><html:text property="phone" size="20" disabled="true" name="<%=Constants.VENDOR%>" /></td>
            <td><bean:message key="message.vendor.fax"/></td>
            <td><html:text property="fax" size="20" disabled="true" name="<%=Constants.VENDOR%>" /></td>
        </tr>
        <tr>
            <td><bean:message key="message.vendor.presenter"/></td>
            <td colspan="3"><html:text property="presenter" size="80" disabled="true" name="<%=Constants.VENDOR%>" /></td>
        </tr>
        <tr>
            <td><bean:message key="message.vendor.evaluate.fromDate"/></td>
            <td><html:text property="fromDate" size="10" styleId="fromDate" onclick="javascript: showCalendar('fromDate')" name="<%=Constants.VENDOR_EVAL%>" /></td>
            <td><bean:message key="message.vendor.evaluate.toDate"/></td>
            <td><html:text property="toDate" size="10" styleId="toDate" onclick="javascript: showCalendar('toDate')" name="<%=Constants.VENDOR_EVAL%>" /></td>
        </tr>
        <tr>
            <td><bean:message key="message.vendor.evaluate.organization"/></td>
            <td>
                <html:select property="orgId" name="<%=Constants.VENDOR_EVAL%>">
                    <html:options collection="<%=Constants.ORG_LIST%>" property="orgId" labelProperty="name"/>
                </html:select>
            </td>
            <td><bean:message key="message.vendor.evaluate.employee"/></td>
            <td>
                <html:select property="empId" name="<%=Constants.VENDOR_EVAL%>">
                    <html:options collection="<%=Constants.MEMBER_LIST%>" property="empId" labelProperty="fullname"/>
                </html:select>
            </td>
        </tr>
        <tr>
            <td><bean:message key="message.vendor.evaluate.result"/></td>
            <td>
                <html:select property="lastResult" name="<%=Constants.VENDOR_EVAL%>">
                    <html:option key="message.vendor.evaluate.notreach" value='0'/>
                    <html:option key="message.vendor.evaluate.reach" value='1'/>
                </html:select>
            </td>
            <td><bean:message key="message.vendor.evaluate.date"/></td>
            <td><html:text property="createdDate" styleId="createdDateVenEval" size="10" disabled="true" name="<%=Constants.VENDOR_EVAL%>"/></td>
        </tr>
        <tr>
            <td colspan="4"><div id='venEvalDetailList'></div></td>
        </tr>
    </table>
    <logic:greaterThan value="0" name="<%=Constants.VENDOR_EVAL%>" property="evalId">
        <image onclick="return printVendorEval();" src="images/but_print.gif"/>
    </logic:greaterThan>
    <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_EDIT, PermissionUtil.PER_LIBRARY_VENDOR_EVAL)) {%>
    <html:image onclick="return saveVenEval();" src="images/but_save.gif"/><img src="images/blank.gif" width="2" height="17">
    <%}%>
    <html:image property="Back" value="Back" src="images/but_back.gif" onclick="return loadVendorEvalList();"/>
    <html:hidden property="evalId" name="<%=Constants.VENDOR_EVAL%>"/>
    <html:hidden property="venId" styleId="venIdEval" name="<%=Constants.VENDOR%>"/>
    <input type="hidden" id="fromDateHidden" value='<bean:write property="fromDate" name="<%=Constants.VENDOR_EVAL%>" />'/>
    <input type="hidden" id="toDateHidden" value='<bean:write property="fromDate" name="<%=Constants.VENDOR_EVAL%>" />'/>
</form>