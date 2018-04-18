<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<%@ page import="com.venus.mc.bean.ContractBean"%>
<div id="errorMessageDiv"></div>
<form name="materialInContractPrincipleForm_search" id="materialInContractPrincipleForm_search">
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
                    <input type="button" onclick="return searchMaterialInContract(4,'incontractprinciple',document.forms['materialInContractPrincipleForm_search']);" value="<bean:message key="message.search"/>"/>
                    <img onclick="return exportMaterialInContract(4,document.forms['materialInContractPrincipleForm_search']);" src="images/but_print.gif"/>
            </div></td>
        </tr>
    </table>
</form>
<form name='materialInContractPrincipleForm' id='materialInContractPrincipleForm'>
    <div id='incontractprinciple'></div>
    </br>
    <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_ADD, PermissionUtil.PER_PURCHASING_REQUEST_MATERIAL)) {%>
    <input type="button" onclick="return createDeliveryRequest(<%=ContractBean.KIND_DELIVERY_REQUEST%>,'materialInContractPrincipleForm');" value="<bean:message key="message.deliveryrequest.create"/>"/>
    <input type="button" onclick="return createTenderPlan('principle');" value="<bean:message key="message.tenderplan.create"/>"/>
    <%}%>
    <input id="reqDetId_materialInContractPrincipleForm" name="reqDetId_materialInContractPrincipleForm" type="hidden">
    <input id="reqId_materialInContractPrincipleForm" name="reqId_materialInContractPrincipleForm" type="hidden">
    <input id="matId_materialInContractPrincipleForm" name="matId_materialInContractPrincipleForm" type="hidden">
    <input id="conId_materialInContractPrincipleForm" name="conId_materialInContractPrincipleForm" type="hidden">
    <input id="conDetId_materialInContractPrincipleForm" name="conDetId_materialInContractPrincipleForm" type="hidden">
    <input id="venId_materialInContractPrincipleForm" name="venId_materialInContractPrincipleForm" type="hidden">
</form>