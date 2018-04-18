<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<%@ page import="com.venus.mc.bean.ContractBean"%>
<div id="errorMessageDiv"></div>
<form name="materialNotInContractForm_search" id="materialNotInContractForm_search">
    <table>
        <tr>
            <td><div>                   
                    <select name="searchid">
                        <option value='0'><bean:message key='message.all'/></option>
                        <option value='1'><bean:message key='message.material.code'/></option>
                        <option value='2'><bean:message key='message.material.nameVn'/></option>
                        <option value='3'><bean:message key='message.request'/></option>
                        <option value='5'><bean:message key='message.project'/></option>
                        <option value='7'><bean:message key='message.vendor.material.group'/></option>
                    </select>
                    <input type="textbox" name="searchvalue"/>
                    <input type="button" onclick="return searchMaterialInContract(5,'notincontract',document.forms['materialNotInContractForm_search']);" value="<bean:message key="message.search"/>"/>
                    <img onclick="return exportMaterialInContract(5,document.forms['materialNotInContractForm_search']);" src="images/but_print.gif"/>
            </div></td>
        </tr>
    </table>
</form>
<form name='materialNotInContractForm' id='materialNotInContractForm'>
    <div id='notincontract'></div>
    </br>
    <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_ADD, PermissionUtil.PER_PURCHASING_REQUEST_MATERIAL)) {%>
    <input type="button" onclick="return createOrder('notincontract','<%=ContractBean.KIND_ORDER%>');" value="<bean:message key="message.retail"/>"/>
    <input type="button" onclick="return createTenderPlan('notincontract');" value="<bean:message key="message.tenderplan.create"/>"/>
    <%}%>
    <input id="reqDetId_materialNotInContractForm" name="reqDetId_materialNotInContractForm" type="hidden">
    <input id="reqId_materialNotInContractForm" name="reqId_materialNotInContractForm" type="hidden">
    <input id="matId_materialNotInContractForm" name="matId_materialNotInContractForm" type="hidden">
</form>