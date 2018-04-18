<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<%@ page import="com.venus.mc.bean.ContractBean"%>
<div id="errorMessageDiv"></div>
<form name="materialInAdjustmentContractForm_search" id="materialInAdjustmentContractForm_search">
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
                    <input type="button" onclick="return searchMaterialInContract(8,'inadjustmentcontract',document.forms['materialInAdjustmentContractForm_search']);" value="<bean:message key="message.search"/>"/>
                    <img onclick="return exportMaterialInContract(8,document.forms['materialInAdjustmentContractForm_search']);" src="images/but_print.gif"/>
            </div></td>
        </tr>
    </table>
</form>
<form name='materialInAdjustmentContractForm' id='materialInAdjustmentContractForm'>
    <div id='inadjustmentcontract'></div>
    </br>
    <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_ADD, PermissionUtil.PER_PURCHASING_REQUEST_MATERIAL)) {%>
    <input type="button" onclick="return createDeliveryRequest(<%=ContractBean.KIND_DELIVERY_REQUEST%>,'materialInAdjustmentContractForm');" value="<bean:message key="message.deliveryrequest.create"/>"/>
    <input type="button" onclick="return createTenderPlan('adjustmentcontract');" value="<bean:message key="message.tenderplan.create"/>"/>
    <%}%>
    <input id="reqDetId_materialInAdjustmentContractForm" name="reqDetId_materialInAdjustmentContractForm" type="hidden">
    <input id="reqId_materialInAdjustmentContractForm" name="reqId_materialInAdjustmentContractForm" type="hidden">
    <input id="matId_materialInAdjustmentContractForm" name="matId_materialInAdjustmentContractForm" type="hidden">
    <input id="conId_materialInAdjustmentContractForm" name="conId_materialInAdjustmentContractForm" type="hidden">
    <input id="conDetId_materialInAdjustmentContractForm" name="conDetId_materialInAdjustmentContractForm" type="hidden">
    <input id="venId_materialInAdjustmentContractForm" name="venId_materialInAdjustmentContractForm" type="hidden">
</form>