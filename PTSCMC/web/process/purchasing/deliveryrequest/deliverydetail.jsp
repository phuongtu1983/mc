<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<%@ page import="com.venus.mc.contract.ContractFormBean"%>
<div id="deliveryFormTitle">
    <logic:equal name="<%=Constants.CONTRACT%>" value="0" property="conId">
        <h3><bean:message key="message.deliveryrequestadd.title"/>/
            <logic:greaterThan name="<%=Constants.CONTRACT%>" value="0" property="conId"><bean:message key="message.detail.s"/></logic:greaterThan>
            <bean:message key="message.add.s"/>
        </h3>
    </logic:equal>
</div>
<div id="errorMessageDiv"></div>
<form name="contractForm">
    <table width="100%">
        <tr>
            <td width="140px"><bean:message key="message.contract.number"/></td>
            <td><html:text size="30" property="contractNumber" name="<%=Constants.CONTRACT%>"/></td>
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
        <!--
        <tr>
        <logic:present name="<%=Constants.TENDERPLAN_LIST%>">
            <td><bean:message key="message.contract.tender.number"/></td>
            <td>
            <html:select property="tenId" name="<%=Constants.CONTRACT%>" onchange="return contractTenderPlanChanged(this)">
                <html:options collection="<%=Constants.TENDERPLAN_LIST%>" property="tenId" labelProperty="tenderNumber"/>
            </html:select>
        </td>
        <td><bean:message key="message.contract.vendor"/></td>
        <td>
            <div id="contractTenderPlanDiv">
                <select name="venId"></select>
            </div>
        </td>
        </logic:present>
        <logic:notPresent name="<%=Constants.TENDERPLAN_LIST%>">
            <td><bean:message key="message.contract.tender.number"/></td>
            <td><html:text size="30" property="tenderNumber" disabled="true" name="<%=Constants.CONTRACT%>"/></td>
        </tr>
        <tr>
            <td><bean:message key="message.contract.vendor"/></td>
            <td colspan="3"><html:text size="50" property="vendorName" disabled="true" name="<%=Constants.CONTRACT%>"/></td>
        <td><bean:message key="message.deliveryrequest.contract"/></td>
                <td><span name="deliveryRequestContractSpan" id="deliveryRequestContractSpan"><select name="conId"></select></span></td>
        </logic:notPresent>
    </tr>
        -->
        <logic:equal name="<%=Constants.CONTRACT%>" property="venId" value="0">
            <tr>
                <td><bean:message key="message.deliveryrequest.vendor"/></td>
                <td>
                    <html:select style="width: 170px;" property="venId" name="<%=Constants.CONTRACT%>" onchange="return deliveryRequestVendorChanged(this);">
                        <html:options collection="<%=Constants.VENDOR_LIST%>" property="venId" labelProperty="name"/>
                    </html:select>
                </td>
                <td><bean:message key="message.deliveryrequest.contract"/></td>
                <td><span name="deliveryRequestContractSpan" id="deliveryRequestContractSpan"><select name="conId"></select></span></td>
            </tr>
        </logic:equal>
        <logic:notEqual name="<%=Constants.CONTRACT%>" property="venId" value="0">
            <tr>
                <td><bean:message key="message.deliveryrequest.vendor"/></td>
                <td colspan="3"><html:text size="50" property="vendorName" disabled="true" name="<%=Constants.CONTRACT%>"/></td>
            </tr>
            <html:hidden property="venId" name="<%=Constants.CONTRACT%>"/>
            <td><bean:message key="message.deliveryrequest.contract"/></td>
            <td colspan="3"><html:text size="50" property="deliveryNumber" disabled="true" name="<%=Constants.CONTRACT%>"/></td>
        </logic:notEqual>
        <tr>
            <td><bean:message key="message.contract.signDate"/></td>
            <td colspan="3"><html:text property="signDate" size="10" styleId="signDate" onclick="javascript: showCalendar('signDate')" name="<%=Constants.CONTRACT%>" /></td>
        </tr>
        <tr>
            <td><bean:message key="message.contract.effectedDate"/></td>
            <td colspan="3"><html:text property="effectedDate" size="10" styleId="effectedDate" onclick="javascript: showCalendar('effectedDate')" onblur="updateOtherDateValue(this.value,document.forms['contractForm'].expireDate,365)" name="<%=Constants.CONTRACT%>" /></td>
        </tr>
        <tr>
            <td><bean:message key="message.contract.responseEmp"/></td>
            <td colspan="3"><html:text size="30" property="employeeName" name="<%=Constants.CONTRACT%>" disabled="true"/></td>
        </tr>
        <tr>
            <td><bean:message key="message.contract.followEmp"/></td>
            <td colspan="3">
                <span id="deliveryFollowEmpIdSpan">
                    <html:select style="width: 170px;" property="followEmp" name="<%=Constants.CONTRACT%>" styleId="deliveryFollowEmpCmb">
                        <html:options collection="<%=Constants.EMPLOYEE_LIST%>" property="empId" labelProperty="fullname"/>
                    </html:select>
                    <input type="button" onclick="return comboTextIdClick('contractForm','followEmpName','followEmp','deliveryFollowEmpIdSpan','deliveryFollowEmpNameSpan');" value='<>'/>
                </span>
                <span  style="display:none" id="deliveryFollowEmpNameSpan">
                    <input type="textbox" size="29" name="followEmpName"/>
                    <input type="button" onclick="return comboTextNameClick('contractForm','getEmployeeToCombobox.do',null,'followEmpName','followEmp','deliveryFollowEmpCmb','deliveryFollowEmpIdSpan','deliveryFollowEmpNameSpan');" value='<>'/>
                </span>
            </td>
        </tr>
        <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_LIST, PermissionUtil.PER_LIBRARY_MATERIAL_PRICE)) {%>
        <tr>
            <td><bean:message key="message.contract.transport"/></td>
            <td><html:text size="20" property="transport" name="<%=Constants.CONTRACT%>"  onblur="formatPositiveNumberMoney(this);caculateContract()" /></td>
            <td><bean:message key="message.contract.otherFee"/></td>
            <td><html:text size="20" property="otherFee" name="<%=Constants.CONTRACT%>"  onblur="formatPositiveNumberMoney(this);caculateContract()" /></td>
        </tr>
        <tr>
            <td><bean:message key="message.contract.totalNotVAT"/></td>
            <td><html:text size="20" property="totalNotVAT" readonly="true" name="<%=Constants.CONTRACT%>"/></td>
            <td><bean:message key="message.contract.sumVAT"/></td>
            <td><html:text size="20" property="sumVAT" readonly="true" name="<%=Constants.CONTRACT%>"/></td>
        </tr>
        <tr>
            <td><bean:message key="message.contract.total"/></td>
            <td><html:text size="20" property="total" readonly="true" name="<%=Constants.CONTRACT%>"/></td>
            <td><bean:message key="message.contract.currency"/></td>
            <td>
                <html:select property="currency" name="<%=Constants.CONTRACT%>">
                    <html:options collection="<%=Constants.CURRENCY_LIST%>" property="value" labelProperty="label"/>
                </html:select>
            </td>
        </tr>
        <%} else {%>
        <html:hidden property="transport" name="<%=Constants.CONTRACT%>"/>
        <html:hidden property="otherFee" name="<%=Constants.CONTRACT%>"/>
        <html:hidden property="totalNotVAT" name="<%=Constants.CONTRACT%>"/>
        <html:hidden property="sumVAT" name="<%=Constants.CONTRACT%>"/>
        <html:hidden property="total" name="<%=Constants.CONTRACT%>"/>
        <html:hidden property="currency" name="<%=Constants.CONTRACT%>"/>        
        <%}%>
        <tr>
            <td><bean:message key="message.contract.material.deliveryDate"/></td>
            <td><html:text size="20" styleId="deliveryDate" onclick="javascript: showCalendar('deliveryDate')" property="deliveryDate" name="<%=Constants.CONTRACT%>"/></td>
            <td><bean:message key="message.contract.payment"/></td>
            <td><html:text size="20" property="payment" name="<%=Constants.CONTRACT%>"/></td>
        </tr>
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
        <tr>
            <td><bean:message key="message.contract.process.status"/></td>
            <td colspan="3">
                <html:select property="processStatus" name="<%=Constants.CONTRACT%>">
                    <html:options collection="<%=Constants.CONTRACT_PROCESS_STATUS_LIST%>" property="value" labelProperty="label"/>
                </html:select>
            </td>
        </tr>
        <tr>
            <td><bean:message key="message.contract.status.payment1"/></td>
            <td colspan="3"><html:textarea rows="3" cols="80" property="processStatusText" name="<%=Constants.CONTRACT%>"/></td>
        </tr>
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
        <logic:greaterThan name="<%=Constants.CONTRACT%>" value="0" property="conId">
            <tr>
                <td colspan="4">
                    <fieldset>
                        <legend><bean:message key="message.contract.cost"/></legend>
                        <div><%@include  file="/process/purchasing/contract/costs.jsp" %></div>
                    </fieldset>
                </td>
            </tr>
            <tr>
                <td colspan="4">
                    <fieldset>
                        <legend><bean:message key="message.contract.bill"/></legend>
                        <div><%@include  file="/process/purchasing/contract/bills.jsp" %></div>
                    </fieldset>
                </td>
            </tr>
        </logic:greaterThan>
        <tr>
            <td colspan="4">
                <fieldset>
                    <legend><bean:message key="message.deliveryrequest.materials"/></legend> 
                    <%
                        ContractFormBean form1 = (ContractFormBean) request.getAttribute(Constants.CONTRACT);
                        if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_EDIT, PermissionUtil.PER_PURCHASING_DELIVERYREQUEST, form1.getOwnerId())) {
                    %>
                    <input type="button" onclick="return createDn4(4,document.forms['contractForm'].conId.value);" value="<bean:message key="message.dn.add"/>"/>
                    <div>
                        <logic:equal name="<%=Constants.CONTRACT%>" property="paymentStatus" value="2">
                            <html:image src="images/ico_xoa.gif" onclick="return delDeliveryRequestDetails();"/>
                            <html:image src="images/ico_them.gif" onclick="return setSelectedDeliveryMaterial();"/>
                            <span id="listContractMaterialDataSpan"><select name="material"></select></span>
                        </logic:equal>
                        <logic:notEqual name="<%=Constants.CONTRACT%>" property="paymentStatus" value="2">
                            <span id="listContractMaterialDataSpan" style="display:none"><select name="material"></select></span>
                        </logic:notEqual>
                    </div>
                    <%}%>
                    <p><div id="listDeliveryRequestMaterialDataDiv" style="width:700px;height:300px;overflow:auto"><%@include  file="/process/purchasing/deliveryrequest/details.jsp" %></div></p>
                </fieldset>
            </td>
            <logic:notEqual name="<%=Constants.CONTRACT%>" property="conId" value="0">
            <tr>
                <td colspan="4"><div id='divAttachFileSystem' ><img src="img/indicator.gif"/></div></td>            
            </tr>
        </logic:notEqual> 
        </tr>        
    </table>
    <logic:greaterThan value="0" name="<%=Constants.CONTRACT%>" property="conId">
        <img onclick="return printDeliveryRequest();" src="images/but_print.gif"/>
    </logic:greaterThan>
        <logic:greaterThan value="0" name="<%=Constants.CONTRACT%>" property="conId">
        <input type="button" onclick="return printDXKDeliveryRequest();" value="<bean:message key="message.deliveryrequest.dxk"/>"/>
    </logic:greaterThan>
    <%
        //ContractFormBean form = (ContractFormBean) request.getAttribute(Constants.CONTRACT);
        if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_EDIT, PermissionUtil.PER_PURCHASING_DELIVERYREQUEST, form1.getOwnerId())) {
    %>
    <logic:present name="<%=Constants.CAN_SAVE%>">
        <%if (form1.getPaymentStatus() == ContractFormBean.STATUS_APPROVED) {%>
        <html:image onclick="return saveContractCancelMaterial();" src="images/but_save.gif"/><img src="images/blank.gif" width="2" height="17">   
        <%} else {%>
        <html:image onclick="return saveContract();" src="images/but_save.gif"/><img src="images/blank.gif" width="2" height="17">        
        <%}%>
    </logic:present>
    <%}%>
    
    <html:image property="Back" value="Back" src="images/but_back.gif" onclick="return loadDeliveryRequestList();"/>
    <html:hidden property="conId" name="<%=Constants.CONTRACT%>"/>
    <html:hidden property="kind" name="<%=Constants.CONTRACT%>"/>
    <html:hidden property="expireDate" name="<%=Constants.CONTRACT%>" value="null"/>
</form>
<div id="deliveryMaterialHideDiv" style="display:none"></div>