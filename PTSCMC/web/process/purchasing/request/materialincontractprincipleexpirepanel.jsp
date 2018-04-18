<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<%@ page import="com.venus.mc.bean.ContractBean"%>
<div id="errorMessageDiv"></div>
<form name="materialInContractPrincipleExpireForm_search" id="materialInContractPrincipleExpireForm_search">
    <table>
        <tr>
            <td><div>                   
                    <select name="searchid">
                        <option value='0'><bean:message key='message.all'/></option>
                        <option value='1'><bean:message key='message.material.code'/></option>
                        <option value='2'><bean:message key='message.material.nameVn'/></option>
                        <option value='3'><bean:message key='message.request'/></option>
                        <option value='4'><bean:message key='message.contract.number'/></option>
                        <option value='5'><bean:message key='message.project'/></option>
                        <option value='6'><bean:message key='message.contract.vendor'/></option>
                        <option value='7'><bean:message key='message.vendor.material.group'/></option>
                    </select>
                    <input type="textbox" name="searchvalue"/>
                    <input type="button" onclick="return searchMaterialInContract(1,'incontractprincipleexpire',document.forms['materialInContractPrincipleExpireForm_search']);" value="<bean:message key="message.search"/>"/>
            </div></td>
        </tr>
    </table>
</form>
<form name='materialInContractPrincipleExpireForm' id='materialInContractPrincipleExpireForm'>
    <div id='incontractprincipleexpire'></div>
    </br>
    <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_ADD, PermissionUtil.PER_PURCHASING_REQUEST_MATERIAL)) {%>
    <input type="button" onclick="return createDeliveryRequest(<%=ContractBean.KIND_DELIVERY_REQUEST%>,'materialInContractPrincipleExpireForm');" value="<bean:message key="message.deliveryrequest.create"/>"/>
    <input type="button" onclick="return createTenderPlan('principleexpire');" value="<bean:message key="message.tenderplan.create"/>"/>
    <%}%>
    <input id="reqDetId_materialInContractPrincipleExpireForm" name="reqDetId_materialInContractPrincipleExpireForm" type="hidden">
    <input id="reqId_materialInContractPrincipleExpireForm" name="reqId_materialInContractPrincipleExpireForm" type="hidden">
    <input id="matId_materialInContractPrincipleExpireForm" name="matId_materialInContractPrincipleExpireForm" type="hidden">
    <input id="conId_materialInContractPrincipleExpireForm" name="conId_materialInContractPrincipleExpireForm" type="hidden">
    <input id="conDetId_materialInContractPrincipleExpireForm" name="conDetId_materialInContractPrincipleExpireForm" type="hidden">
    <input id="venId_materialInContractPrincipleExpireForm" name="venId_materialInContractPrincipleExpireForm" type="hidden">
</form>