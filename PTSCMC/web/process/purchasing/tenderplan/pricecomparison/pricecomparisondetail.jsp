<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib prefix="display" uri="/tags/displaytag" %>
<%@ page import="com.venus.mc.util.MCUtil"%>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.venus.mc.bean.OrganizationBean"%>
<%@ page import="com.venus.mc.bean.PriceComparisonBean"%>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<div id="errorMessageDiv"></div>
<form name="priceComparisonForm">
    <table width="100%">
        <tr>
            <td width="80px"><bean:message key="message.pricecomparison.subject"/></td>
            <td colspan="2"><html:text size="20" property="subject" name="<%=Constants.PRICECOMPARISON%>"/></td>        
            <td><bean:message key="message.pricecomparison.createdDate"/></td>
            <td colspan="2"><html:text readonly="true" property="createdDate" size="20" name="<%=Constants.PRICECOMPARISON%>"/></td>
        </tr>

        <tr>
            <td colspan="6">
                <fieldset>
                    <legend><bean:message key="message.pricecomparison.list"/></legend>
                    <p><div id="listPriceComparisonVendorDataDiv" style="width:700px;height:300px;overflow:auto"></div></p>
                </fieldset>
            </td>
        </tr>        

    </table>
    <%
            PriceComparisonBean form = (PriceComparisonBean) request.getAttribute(Constants.PRICECOMPARISON);
            if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_EDIT, PermissionUtil.PER_PURCHASING_TENDERPLAN, form.getCreatedEmp())) {
    %>
    <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_LIST, PermissionUtil.PER_LIBRARY_MATERIAL_PRICE)) {%>
    <logic:greaterThan value="0" property="pcId" name="<%=Constants.PRICECOMPARISON%>">
        <img onclick="return printPriceComparison();" src="images/ico_print.gif"/>
    </logic:greaterThan>
    <%}%>
    <html:image onclick="return savePriceComparison();" src="images/but_save.gif"/>
    <%}%>
    <html:hidden property="pcId" name="<%=Constants.PRICECOMPARISON%>"/>
    <html:hidden property="tenId" name="<%=Constants.PRICECOMPARISON%>"/>
</form>
<div id="priceComparisonVendorHideDiv" style="display:none"></div>