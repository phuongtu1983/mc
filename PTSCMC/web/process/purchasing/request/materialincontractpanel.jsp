<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<%@ page import="com.venus.mc.bean.ContractBean"%>
<div id="errorMessageDiv"></div>
<form name="materialInContractForm_search" id="materialInContractForm_search">
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
                    <input type="button" onclick="return searchMaterialInContract(2,'incontract',document.forms['materialInContractForm_search']);" value="<bean:message key="message.search"/>"/>
                    <img onclick="return exportMaterialInContract(2,document.forms['materialInContractForm_search']);" src="images/but_print.gif"/>
                </div></td>
        </tr>
    </table>
</form>
<form name='materialInContractForm' id='materialInContractForm'>
    <div id='incontract'></div>
    </br>
    <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_ADD, PermissionUtil.PER_PURCHASING_REQUEST_MATERIAL)) {%>
    <input type="button" onclick="return createAppendix(<%=ContractBean.KIND_CONTRACT%>,'<%=ContractBean.KIND_APPENDIX%>');" value="<bean:message key="message.appendix.create"/>"/>
    <input type="button" onclick="return createTenderPlan('contract');" value="<bean:message key="message.tenderplan.create"/>"/>
    <%}%>
    <input id="reqDetId_materialInContractForm" name="reqDetId_materialInContractForm" type="hidden">
    <input id="reqId_materialInContractForm" name="reqId_materialInContractForm" type="hidden">
    <input id="matId_materialInContractForm" name="matId_materialInContractForm" type="hidden">
    <input id="conId_materialInContractForm" name="conId_materialInContractForm" type="hidden">
    <input id="conDetId_materialInContractForm" name="conDetId_materialInContractForm" type="hidden">
    <input id="venId_materialInContractForm" name="venId_materialInContractForm" type="hidden">
</form>