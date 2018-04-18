<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<h3><bean:message key="message.payment.title"/>/<bean:message key="message.list.s"/></h3>
<div id="errorMessageDiv"></div>
<html:form action="searchPayment.do">
    <table>
        <tr>
            <td>
                <html:image src="images/blank.png" onclick="return searchPayment();"/>
                <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_ADD, PermissionUtil.PER_PURCHASING_PAYMENT)) {%>
                <html:image src="images/ico_them.gif"  onclick="return paymentForm();"/>
                <%}%>
            </td>
            <td>
                <div>
                    <html:select property="searchid">
                        <html:option key="message.all" value='0'/>
                        <html:option key="message.payment.number" value='1'/>
                        <html:option key="message.contract.number" value='2'/>
                        <html:option key="message.payment.vendor" value='3'/>
                        <html:option key="message.payment.handleEmp" value='4'/>
                        <html:option key="message.payment.location" value='5'/>
                        <html:option key="message.payment.status" value='6'/>
                    </html:select>
                    <html:text property="searchvalue" />
                    <html:submit onclick="return searchPayment();"><bean:message key="message.search"/></html:submit>
                </div>
            </td>
            <td><img onclick="return paymentReportPrintForm();" src="images/but_print.gif"/></td>    
        </tr>
    </table>
</html:form>
<form name='paymentsForm' id='paymentsForm'><div id='paymentList'></div></form>