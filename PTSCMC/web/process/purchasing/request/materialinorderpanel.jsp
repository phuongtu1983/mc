<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<%@ page import="com.venus.mc.bean.ContractBean"%>
<div id="errorMessageDiv"></div>
<form name="materialInOrderForm_search" id="materialInOrderForm_search">
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
                    <input type="button" onclick="return searchMaterialInContract(3,'inorder',document.forms['materialInOrderForm_search']);" value="<bean:message key="message.search"/>"/>
                    <img onclick="return exportMaterialInContract(3,document.forms['materialInOrderForm_search']);" src="images/but_print.gif"/>
            </div></td>
        </tr>
    </table>
</form>
<form name='materialInOrderForm' id='materialInOrderForm'>
    <div id='inorder'></div>
    </br>
    <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_ADD, PermissionUtil.PER_PURCHASING_REQUEST_MATERIAL)) {%>
    <input type="button" onclick="return createOrder('order','<%=ContractBean.KIND_ORDER%>');" value="<bean:message key="message.order.create"/>"/>
    <input type="button" onclick="return createTenderPlan('order');" value="<bean:message key="message.tenderplan.create"/>"/>
    <%}%>
    <input id="reqDetId_materialInOrderForm" name="reqDetId_materialInOrderForm" type="hidden">
    <input id="reqId_materialInOrderForm" name="reqId_materialInOrderForm" type="hidden">
    <input id="matId_materialInOrderForm" name="matId_materialInOrderForm" type="hidden">
    <input id="conId_materialInOrderForm" name="conId_materialInOrderForm" type="hidden">
    <input id="conDetId_materialInOrderForm" name="conDetId_materialInOrderForm" type="hidden">
    <input id="venId_materialInOrderForm" name="venId_materialInOrderForm" type="hidden">
</form>