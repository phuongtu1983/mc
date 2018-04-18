<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<%@ page import="com.venus.mc.bean.ContractBean"%>
<h3><bean:message key="message.deliveryrequestadd.title"/>/<bean:message key="message.list.s"/></h3>
<div id="errorMessageDiv"></div>
<html:form action="searchDeliveryRequest.do">
    <table>
        <tr>
            <td>
                <html:image src="images/blank.png" onclick="return searchDeliveryRequest();"/>
                <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_DELETE, PermissionUtil.PER_PURCHASING_DELIVERYREQUEST)) {%>
                <html:image src="images/ico_xoa.gif" onclick="return delDeliveryRequests();"/>
                <%}%>
                <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_ADD, PermissionUtil.PER_PURCHASING_DELIVERYREQUEST)) {%>
                <%----<img alt="add" src="images/ico_them.gif" onclick="return contractForm(<%=ContractBean.KIND_DELIVERY_REQUEST%>);"/> --%>
                <%}%>
            </td>
            <td><div>
                    <html:select property="searchid">
                        <html:option key="message.all" value='0'/>
                        <html:option key="message.deliveryrequest.number" value='1'/>
                        <html:option key="message.deliveryrequest.vendor" value='2'/>
                        <!--<html:option key="message.vendor.material.group" value='3'/>-->
                        <html:option key="message.request" value='4'/>
                        <html:option key="message.contract.responseEmp" value='6'/>
                        <html:option key="message.contract.followEmp" value='7'/>
                        <html:option key="message.contract.status.payments" value='8'/>
                        <html:option key="message.contract.process.status" value='9'/>
                        <html:option key="message.contract.deliveryDate" value='10'/>
                        <html:option key="message.contract.note" value='11'/>
                        <html:option key="message.material.nameVn" value='14'/>
                        <html:option key="message.contract.principle" value='15'/>
                    </html:select>
                    <html:text property="searchvalue" />
                    <html:submit onclick="return searchDeliveryRequest();"><bean:message key="message.search"/></html:submit>
                    <html:submit onclick="return searchAdvDeliveryRequestForm();"><bean:message key="message.detailSearch"/></html:submit>
                    <img onclick="return printSearchedContract(ContractBean.KIND_DELIVERY_REQUEST);" src="images/but_print.gif"/>
            </div></td>
        </tr>
    </table>
</html:form>
<form name='deliveryRequestsForm' id='deliveryRequestsForm'><div id='deliveryRequestList'></div></form>