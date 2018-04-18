<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<h3><bean:message key="message.invoice.title"/>/<bean:message key="message.list.s"/></h3>
<div id="errorMessageDiv"></div>
<html:form action="searchInvoice.do" onsubmit="return searchInvoice();">
    <table>
        <tr>
            <td>
                <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_ADD, PermissionUtil.PER_PURCHASING_INVOICE)) {%>
                <img alt="add" src="images/ico_them.gif" onclick="return invoiceForm();"/>
                <%}%>
            </td>
            <td><div>
                    <html:select property="searchid">
                        <html:option key="message.all" value='0'/>
                        <html:option key="message.contract.bill.number" value='1'/>
                        <html:option key="message.contract.number" value='2'/>
                        <html:option key="message.u_vendor" value='3'/>
                        <html:option key="message.contract.followEmp" value='4'/>
                        <html:option key="message.contract.bill.status" value='5'/>
                    </html:select>
                    <html:text property="searchvalue" />
                    <input type="button" onclick="return searchInvoice();" value="<bean:message key="message.search"/>"/>
                    <input type="button" onclick="return searchAdvInvoiceForm();" value="<bean:message key="message.detailSearch"/>"/>
            </div></td>
        </tr>
    </table>
</html:form>
<form name='invoicesForm' id='invoicesForm'><div id='invoiceList'></div></form>