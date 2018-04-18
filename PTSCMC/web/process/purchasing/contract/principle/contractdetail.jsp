<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<%@ page import="com.venus.mc.contract.ContractFormBean"%>
<logic:equal name="<%=Constants.CONTRACT%>" value="0" property="conId">
    <h3><bean:message key="message.contractprincipledetail.title"/>/
        <bean:message key="message.add.s"/>
    </h3>
</logic:equal>
<div id="errorMessageDiv"></div>
<form name="contractForm">
    <table width="100%">
        <tr>
            <td width="140px"><bean:message key="message.contract.number"/></td>
            <td><html:text size="20" property="contractNumber" name="<%=Constants.CONTRACT%>"/></td>
            <td><bean:message key="message.contract.status"/></td>
            <td>
                <html:select property="paymentStatus" name="<%=Constants.CONTRACT%>">
                    <html:options collection="<%=Constants.STATUS_LIST%>" property="value" labelProperty="label"/>
                </html:select>
                <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_EDIT, PermissionUtil.PER_PURCHASING_CONTRACT_UPDATE_STATUS)) {%>
                <input value="<bean:message key="message.permission.func.purchasing.contract.updateStatus"/>" onclick="return updateContractStatus('contractForm');" type="button">
                <%}%>
            </td>
        </tr>
        <tr>
            <logic:present name="<%=Constants.TENDERPLAN_LIST%>">
                <td><bean:message key="message.contract.tender.number"/></td>
                <td>
                    <html:select style="width: 170px;" property="tenId" name="<%=Constants.CONTRACT%>" onchange="return contractTenderPlanChanged(this)">
                        <html:options collection="<%=Constants.TENDERPLAN_LIST%>" property="tenId" labelProperty="tenderNumber"/>
                    </html:select>
                </td>
                <td><bean:message key="message.contract.vendor"/></td>
                <td>
                    <div id="contractTenderPlanDiv">
                        <select name="venId" style="width: 170px;"></select>
                    </div>
                </td>
            </logic:present>
            <logic:notPresent name="<%=Constants.TENDERPLAN_LIST%>">
                <td><bean:message key="message.contract.tender.number"/></td>
                <td colspan="3"><html:text size="20" property="tenderNumber" disabled="true" name="<%=Constants.CONTRACT%>"/></td>
            </tr>
            <tr>
                <td><bean:message key="message.contract.vendor"/></td>
                <td colspan="3" ><html:text size="106" property="vendorName" disabled="true" name="<%=Constants.CONTRACT%>"/></td>
            </logic:notPresent>
        </tr>
        <tr>
            <td><bean:message key="message.contract.signDate"/></td>
            <td><html:text property="signDate" size="10" styleId="signDate" onclick="javascript: showCalendar('signDate')" name="<%=Constants.CONTRACT%>" /></td>
            <td><bean:message key="message.contract.material.deliveryDate"/></td>
            <td><html:text size="20" styleId="deliveryDate" onclick="javascript: showCalendar('deliveryDate')" property="deliveryDate" name="<%=Constants.CONTRACT%>"/></td>
        </tr>
        <tr>
            <td><bean:message key="message.contract.effectedDate"/></td>
            <td><html:text property="effectedDate" size="10" styleId="effectedDate" onclick="javascript: showCalendar('effectedDate')" onblur="updateOtherDateValue(this.value,document.forms['contractForm'].expireDate,365)" name="<%=Constants.CONTRACT%>" /></td>
            <td><bean:message key="message.contract.expireDate"/></td>
            <td><html:text property="expireDate" size="10" styleId="expireDate" onclick="javascript: showCalendar('expireDate')" name="<%=Constants.CONTRACT%>" />
                <logic:present name="<%=Constants.CAN_EXPIREDATE%>">
                    <input value="<bean:message key="message.contract.SaveExpireDate"/>" onclick="return saveContractExpireDate();" type="button">     
                </logic:present>
            </td>
        </tr>
        <tr>
            <td><bean:message key="message.contract.responseEmp"/></td>
            <td colspan="3"><html:text size="30" property="employeeName" name="<%=Constants.CONTRACT%>" disabled="true"/></td>
        </tr>
        <tr>
            <td><bean:message key="message.contract.followEmp"/></td>
            <td colspan="3">
                <span id="contractFollowEmpIdSpan">
                    <html:select style="width: 170px;" property="followEmp" name="<%=Constants.CONTRACT%>" styleId="contractFollowEmpIdCmb">
                        <html:options collection="<%=Constants.EMPLOYEE_LIST%>" property="empId" labelProperty="fullname"/>
                    </html:select>
                    <input type="button" onclick="return comboTextIdClick('contractForm','followEmpName','followEmp','contractFollowEmpIdSpan','contractFollowEmpNameSpan');" value='<>'/>
                </span>
                <span  style="display:none" id="contractFollowEmpNameSpan">
                    <input type="textbox" size="29" name="followEmpName"/>
                    <input type="button" onclick="return comboTextNameClick('contractForm','getEmployeeToCombobox.do',null,'followEmpName','followEmp','contractFollowEmpIdCmb','contractFollowEmpIdSpan','contractFollowEmpNameSpan');" value='<>'/>
                </span>
            </td>
        </tr>
        <%
            ContractFormBean formBean = (ContractFormBean) request.getAttribute(Constants.CONTRACT);
            if (formBean.getIsPermissionPrice() == 1) {
        %>
        <tr>
            <td><bean:message key="message.contract.currency"/></td>
            <td colspan="3">
                <html:select property="currency" name="<%=Constants.CONTRACT%>">
                    <html:options collection="<%=Constants.CURRENCY_LIST%>" property="value" labelProperty="label"/>
                </html:select>
            </td>
        </tr>
        <tr>
            <td><bean:message key="message.contract.transport"/></td>
            <td><html:text size="20" property="transport" name="<%=Constants.CONTRACT%>"  onblur="formatPositiveNumberMoney(this);caculateContract()" /></td>
            <td><bean:message key="message.contract.otherFee"/></td>
            <td><html:text size="20" property="otherFee" name="<%=Constants.CONTRACT%>"  onblur="formatPositiveNumberMoney(this);caculateContract()" /></td>
        </tr>
        <%} else {%>
        <html:hidden property="currency" name="<%=Constants.CONTRACT%>"/>
        <html:hidden property="transport" name="<%=Constants.CONTRACT%>"/>
        <html:hidden property="otherFee" name="<%=Constants.CONTRACT%>"/>
        <%}%>
        <!--<tr>
        <td><bean:message key="message.contract.totalNotVAT"/></td>
            <td><html:text size="20" property="totalNotVAT" readonly="true" name="<%=Constants.CONTRACT%>"/></td>
            <td><bean:message key="message.contract.sumVAT"/></td>
            <td><html:text size="20" property="sumVAT" readonly="true" name="<%=Constants.CONTRACT%>"/></td>
        </tr>
        <tr>
            <td><bean:message key="message.contract.total"/></td>
            <td><html:text size="20" property="total" readonly="true" name="<%=Constants.CONTRACT%>"/></td>
            <td><bean:message key="message.contract.currency"/></td>
            <td><html:text size="20" property="currency" name="<%=Constants.CONTRACT%>"/></td>
        </tr>-->
        <!--<tr>
            <td><bean:message key="message.contract.payment"/></td>
            <td><html:text size="20" property="payment" name="<%=Constants.CONTRACT%>"/></td>
        </tr>-->
        <tr>
            <td><bean:message key="message.contract.paymentMode"/></td>
            <td>
                <html:select property="paymentMode" name="<%=Constants.CONTRACT%>">
                    <html:options collection="<%=Constants.PAYMENT_MODE_LIST%>" property="value" labelProperty="label"/>
                </html:select>
            </td>
            <td><bean:message key="message.contract.delivery"/></td>
            <td><html:text size="20" property="delivery" name="<%=Constants.CONTRACT%>"/></td>
        </tr>
        <!--<tr>
            <td><bean:message key="message.contract.process.status"/></td>
            <td>
        <html:select property="processStatus" name="<%=Constants.CONTRACT%>">
            <html:options collection="<%=Constants.CONTRACT_PROCESS_STATUS_LIST%>" property="value" labelProperty="label"/>
        </html:select>
    </td>
    <td colspan="2"><html:text size="20" property="processStatusText" name="<%=Constants.CONTRACT%>"/></td>
</tr>
        <tr>
            <td><bean:message key="message.contract.status.payment1"/></td>
            <td colspan="3"><html:textarea rows="3" cols="80" property="processStatusText" name="<%=Constants.CONTRACT%>"/></td>
        </tr>-->
        <tr>
            <td><bean:message key="message.contract.soft"/></td>
            <td colspan="3"><html:textarea rows="1" cols="80" property="softDocument" name="<%=Constants.CONTRACT%>"/></td>
        </tr>
        <tr>
            <td><bean:message key="message.order.certificate"/></td>
            <td colspan="3"><html:textarea rows="3" cols="80" property="certificate" name="<%=Constants.CONTRACT%>"/></td>
        </tr>
        <tr>
            <td><bean:message key="message.order.note"/></td>
            <td colspan="3"><html:textarea rows="3" cols="80" property="note" name="<%=Constants.CONTRACT%>"/></td>
        </tr>
        <tr>
            <td colspan="4">
                <fieldset>
                    <legend><bean:message key="message.contract.materials"/></legend>
                    <%
                        if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_EDIT, PermissionUtil.PER_PURCHASING_ORDER, formBean.getOwnerId())) {
                    %>
                    <input type="button" onclick="return createDn(5,document.forms['contractForm'].conId.value);" value="<bean:message key="message.dn.add"/>"/>
                    <% }%>
                    <div>
                        <logic:equal name="<%=Constants.CONTRACT%>" property="paymentStatus" value="2">
                            <html:image src="images/ico_xoa.gif" onclick="return delContractDetails();"/>
                            <html:image src="images/ico_them.gif" onclick="return setSelectedContractMaterial();"/>
                            <span id="listTenderPlanMaterialDataSpan"><select style="width: 500px;" name="material"></select></span>
                        </logic:equal>
                        <logic:notEqual name="<%=Constants.CONTRACT%>" property="paymentStatus" value="2">
                            <span id="listTenderPlanMaterialDataSpan" style="display:none"><select style="width: 500px;" name="material"></select></span>
                        </logic:notEqual>
                    </div>
                    <div id="listContractMaterialDataDiv" style="width:700px;height:300px;overflow:auto"><%@include  file="/process/purchasing/contract/principle/details.jsp" %></div>
                </fieldset>
            </td>
        </tr>
        <logic:notEqual name="<%=Constants.CONTRACT%>" property="conId" value="0">
            <tr>
                <td colspan="4"><div id='divAttachFileSystem' ><img src="img/indicator.gif"/></div></td>            
            </tr>
        </logic:notEqual> 
    </table>
    <logic:greaterThan name="<%=Constants.CONTRACT%>" value="0" property="conId">
        <img onclick="return printPrinciple();" src="images/but_print.gif"/>
    </logic:greaterThan>
    <%
        if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_EDIT, PermissionUtil.PER_PURCHASING_PRINCIPLE, formBean.getOwnerId())) {
    %>
    <logic:present name="<%=Constants.CAN_SAVE%>">
        <%if (formBean.getPaymentStatus() == ContractFormBean.STATUS_APPROVED) {%>
        <html:image onclick="return saveContractCancelMaterial();" src="images/but_save.gif"/><img src="images/blank.gif" width="2" height="17">   
        <%} else {%>
        <html:image onclick="return saveContract();" src="images/but_save.gif"/><img src="images/blank.gif" width="2" height="17">        
        <%}%>
    </logic:present>
    <%}%>
    <html:image property="Back" value="Back" src="images/but_back.gif" onclick="return loadContractPrincipleList();"/>
    <html:hidden property="conId" name="<%=Constants.CONTRACT%>"/>
    <html:hidden property="kind" name="<%=Constants.CONTRACT%>"/>
    <logic:notPresent name="<%=Constants.TENDERPLAN_LIST%>">
        <html:hidden property="tenId" name="<%=Constants.CONTRACT%>"/>
        <html:hidden property="venId" name="<%=Constants.CONTRACT%>"/>
    </logic:notPresent>
</form>
<div id="contractMaterialHideDiv" style="display:none"></div>