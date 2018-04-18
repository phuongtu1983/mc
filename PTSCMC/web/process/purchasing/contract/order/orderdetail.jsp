<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<%@ page import="com.venus.mc.contract.ContractFormBean"%>
<logic:equal name="<%=Constants.CONTRACT%>" value="0" property="conId">
    <h3><bean:message key="message.orderdetail.title"/>/    
        <bean:message key="message.add.s"/>
    </h3>
</logic:equal>
<div id="errorMessageDiv"></div>
<form name="contractForm">
    <table width="100%">
        <tr>
            <td width="140px"><bean:message key="message.order.number"/></td>
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
            <td><bean:message key="message.order.source"/></td>
            <td colspan="3">
                <logic:equal name="<%=Constants.CONTRACT%>" property="conId" value="0">
                    <html:select property="orderMaterialSource" name="<%=Constants.CONTRACT%>" onchange="return orderMaterialSourceChanged(this)">
                        <html:options collection="<%=Constants.ORDER_SOURCE%>" property="value" labelProperty="label"/>
                    </html:select>
                </logic:equal>
                <logic:notEqual name="<%=Constants.CONTRACT%>" property="conId" value="0">
                    <html:select property="orderMaterialSource" name="<%=Constants.CONTRACT%>" disabled="true">
                        <html:options collection="<%=Constants.ORDER_SOURCE%>" property="value" labelProperty="label"/>
                    </html:select>
                </logic:notEqual>
            </td>
        </tr>
        <logic:notEqual name="<%=Constants.CONTRACT%>" property="conId" value="0">
            <logic:present name="<%=Constants.ORDER_CONTRACT%>">
                <tr>
                    <td><bean:message key="message.order.number"/></td>
                    <td colspan="3">
                        <input type="text" value="<bean:write property="contractNumber" name="<%=Constants.ORDER_CONTRACT%>"/>" size="20" disabled></input>
                    </td>
                </tr>
                <tr>
                    <td><bean:message key="message.contract.vendor"/></td>
                    <td colspan="3">
                        <html:text size="50" property="vendorName" disabled="true" name="<%=Constants.CONTRACT%>"/>
                        <input type="hidden" name="venId" value="<bean:write name="<%=Constants.CONTRACT%>" property="venId"/>"/>
                    </td>
                </tr>
            </logic:present>
            <logic:notPresent name="<%=Constants.ORDER_CONTRACT%>">
                <logic:equal name="<%=Constants.CONTRACT%>" property="tenId" value="0">
                    <tr id="orderSourceTr">
                        <td><bean:message key="message.u_vendor"/></td>
                        <td colspan="3">
                            <span style="display:none" id="orderSourceVendorIdSpan">
                                <select style="width: 170px;" name="venId" id="orderSourceVendorIdCmd">
                                    <logic:iterate id="vendor" name="<%=Constants.VENDOR_LIST%>">
                                        <logic:equal name="<%=Constants.CONTRACT%>" property="venId" value="${vendor.venId}">
                                            <option value="${vendor.venId}" selected="selected">${vendor.name}</option>
                                        </logic:equal>
                                        <logic:notEqual name="<%=Constants.CONTRACT%>" property="venId" value="${vendor.venId}">
                                            <option value="${vendor.venId}">${vendor.name}</option>
                                        </logic:notEqual>
                                    </logic:iterate>
                                </select>
                                <input type="button" onclick="return comboTextIdClick('contractForm', 'venName', 'venId', 'orderSourceVendorIdSpan', 'orderSourceVendorNameSpan');" value='<>'/>
                            </span>
                            <span id="orderSourceVendorNameSpan">
                                <input type="textbox" size="29" name="venName" value='<bean:write name="<%=Constants.CONTRACT%>" property="vendorName"/>'/>
                                <input type="button" onclick="return comboTextNameClick('contractForm', 'getVendorToCombobox.do', null, 'venName', 'venId', 'orderSourceVendorIdCmd', 'orderSourceVendorIdSpan', 'orderSourceVendorNameSpan');" value='<>'/>
                            </span>
                        </td>
                    </tr>
                </logic:equal>
                <logic:notEqual name="<%=Constants.CONTRACT%>" property="tenId" value="0">
                    <tr>
                        <td><bean:message key="message.contract.tender.number"/></td>
                        <td colspan="3"><html:text size="20" property="tenderNumber" disabled="true" name="<%=Constants.CONTRACT%>"/></td>                        
                    </tr>
                    <tr>
                        <td><bean:message key="message.contract.vendor"/></td>
                        <td colspan="3"><html:text size="90" property="vendorName" disabled="true" name="<%=Constants.CONTRACT%>"/></td>
                    </tr>
                </logic:notEqual>
            </logic:notPresent>
        </logic:notEqual>
        <logic:equal name="<%=Constants.CONTRACT%>" property="conId" value="0">
            <tr id="orderSourceTr"></tr>
            <logic:present name="<%=Constants.ORDER_CONTRACT%>">
                <tr>
                    <td><bean:message key="message.order.number"/></td>
                    <td>
                        <input type="text" value="<bean:write property="contractNumber" name="<%=Constants.ORDER_CONTRACT%>"/>" size="20" disabled></input>
                        <logic:equal name="<%=Constants.CONTRACT%>" property="conId" value="0">
                            <input name="parentId" value='<bean:write property="conId" name="<%=Constants.ORDER_CONTRACT%>"/>' type="hidden">
                        </logic:equal>
                    </td>
                    <td colspan="2"><div id="contractTenderPlanDiv"></div></td>
                </tr>
            </logic:present>
        </logic:equal>
        <tr>
            <td><bean:message key="message.contract.signDate"/></td>
            <td><html:text property="signDate" size="10" styleId="signDate" onclick="javascript: showCalendar('signDate')" name="<%=Constants.CONTRACT%>" /></td>
            <td><bean:message key="message.contract.material.deliveryDate"/></td>
            <td><html:text size="20" styleId="deliveryDate" onclick="javascript: showCalendar('deliveryDate')" property="deliveryDate" name="<%=Constants.CONTRACT%>"/></td>
        </tr>
        <tr>
            <td><bean:message key="message.contract.effectedDate"/></td>
            <td><html:text property="effectedDate" size="10" styleId="effectedDate" onclick="javascript: showCalendar('effectedDate')" onblur="updateOtherDateValue(this.value,document.forms['contractForm'].expireDate,365/2)" name="<%=Constants.CONTRACT%>" /></td>
            <%
                ContractFormBean formBean = (ContractFormBean) request.getAttribute(Constants.CONTRACT);
                if (formBean.getIsNotResell() == 1 && formBean.getTenId() != 0) {
            %>
            <td><bean:message key="message.contract.expireDate"/></td>
            <td><html:text property="expireDate" size="10" styleId="expireDate" onclick="javascript: showCalendar('expireDate')" name="<%=Constants.CONTRACT%>" /></td>
            <%}%>            
        </tr>
        <tr>
            <td><bean:message key="message.contract.responseEmp"/></td>
            <td colspan="3"><html:text size="30" property="employeeName" name="<%=Constants.CONTRACT%>" disabled="true"/></td>
        </tr>
        <tr>
            <td><bean:message key="message.contract.followEmp"/></td>
            <td colspan="3">
                <span id="orderFollowEmpIdSpan">
                    <html:select style="width: 170px;" property="followEmp" name="<%=Constants.CONTRACT%>" styleId="orderFollowEmpCmb">
                        <html:options collection="<%=Constants.EMPLOYEE_LIST%>" property="empId" labelProperty="fullname"/>
                    </html:select>
                    <input type="button" onclick="return comboTextIdClick('contractForm', 'followEmpName', 'followEmp', 'orderFollowEmpIdSpan', 'orderFollowEmpNameSpan');" value='<>'/>
                </span>
                <span  style="display:none" id="orderFollowEmpNameSpan">
                    <input type="textbox" size="29" name="followEmpName"/>
                    <input type="button" onclick="return comboTextNameClick('contractForm', 'getEmployeeToCombobox.do', null, 'followEmpName', 'followEmp', 'orderFollowEmpCmb', 'orderFollowEmpIdSpan', 'orderFollowEmpNameSpan');" value='<>'/>
                </span>
            </td>
        </tr>
        <%

            if (formBean.getIsPermissionPrice() == 1) {%>
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
            <!--<td><bean:message key="message.contract.payment"/></td>
            <td><html:text size="20" property="payment" name="<%=Constants.CONTRACT%>"/></td>-->
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
        <!--<tr>
        <td><bean:message key="message.contract.deliveryDate"/></td>
            <td colspan="3"><html:text size="50" property="deliveryDate" name="<%=Constants.CONTRACT%>"/></td>
        </tr>
        <tr>
            <td><bean:message key="message.contract.deliveryPlace"/></td>
            <td colspan="3"><html:text size="50" property="deliveryPlace" name="<%=Constants.CONTRACT%>"/></td>
        </tr>-->
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

        <tr>
            <td colspan="4">
                <fieldset>
                    <legend><bean:message key="message.contract.materials"/></legend>
                    <%

                        if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_EDIT, PermissionUtil.PER_PURCHASING_ORDER, formBean.getOwnerId())) {
                    %>
                    <input type="button" onclick="return createDn(2, document.forms['contractForm'].conId.value);" value="<bean:message key="message.dn.add"/>"/>
                    <%}%>
                    <div>
                        <%
                            //ContractFormBean form = (ContractFormBean) request.getAttribute(Constants.CONTRACT);
                            if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_EDIT, PermissionUtil.PER_PURCHASING_ORDER, formBean.getOwnerId())) {
                        %>
                        <logic:equal name="<%=Constants.CONTRACT%>" property="paymentStatus" value="2">
                            <html:image src="images/ico_xoa.gif" onclick="return delContractDetails();"/>
                            <!--<html:image src="images/ico_them.gif" onclick="return setSelectedOrderMaterial();"/>-->
                            <span id="listTenderPlanMaterialDataSpan"><select name="material"></select></span>
                            </logic:equal>
                            <%}%>
                            <logic:notEqual name="<%=Constants.CONTRACT%>" property="paymentStatus" value="2">
                            <span id="listTenderPlanMaterialDataSpan" style="display:none"><select name="material"></select></span>
                            </logic:notEqual>
                        <!--<logic:equal name="<%=Constants.CONTRACT%>" property="conId" value="0">
                        <span id="listTenderPlanMaterialDataSpan"><select name="material"></select></span>
                        </logic:equal>
                        <logic:notEqual name="<%=Constants.CONTRACT%>" property="conId" value="0">
                            <logic:present name="<%=Constants.ORDER_MATERIAL%>">
                                <select name="material">
                                <logic:iterate id="mat" name="<%=Constants.ORDER_MATERIAL%>">
                                    <option value="${mat.matId}">${mat.nameVn}</option>
                                </logic:iterate>
                            </select>
                            </logic:present>
                        </logic:notEqual>-->
                    </div>
                    <div id="listContractMaterialDataDiv" style="width:700px;height:300px;overflow:auto"><%@include  file="/process/purchasing/contract/order/details.jsp" %></div>
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
        <img onclick="return printOrder();" src="images/but_print.gif"/>
        <%
            //ContractFormBean form = (ContractFormBean) request.getAttribute(Constants.CONTRACT);
            if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_EDIT, PermissionUtil.PER_PURCHASING_ORDER, formBean.getOwnerId())) {
        %>
        <logic:equal name="<%=Constants.CONTRACT%>" property="orderMaterialSource" value="contract">
            <input type="button" onclick="return printOrderRequest('order');" value="<bean:message key="message.order.orderCreate"/>"/>
        </logic:equal>
        <logic:equal name="<%=Constants.CONTRACT%>" property="orderMaterialSource" value="other">
            <input type="button" onclick="return printOrderRequest('other');" value="<bean:message key="message.order.otherCreate"/>"/>
        </logic:equal>
        <%}%>
    </logic:greaterThan>
    <%
        //ContractFormBean form = (ContractFormBean) request.getAttribute(Constants.CONTRACT);
        if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_EDIT, PermissionUtil.PER_PURCHASING_ORDER, formBean.getOwnerId())) {
    %>
    <!--<html:image onclick="return saveContract();" src="images/but_save.gif"/><img src="images/blank.gif" width="2" height="17">-->
    <logic:present name="<%=Constants.CAN_EMAIL%>">
        <input value="<bean:message key="message.notify.request.notcode.email"/>" onclick="return emailForNotMaterialCodeContract();" type="submit">
    </logic:present>
    <logic:present name="<%=Constants.CAN_SAVE%>">
        <%if (formBean.getPaymentStatus() == ContractFormBean.STATUS_APPROVED) {%>
        <html:image onclick="return saveContractCancelMaterial();" src="images/but_save.gif"/><img src="images/blank.gif" width="2" height="17">   
        <%} else {%>
        <html:image onclick="return saveContract();" src="images/but_save.gif"/><img src="images/blank.gif" width="2" height="17">        
        <%}%>
    </logic:present>    
    <%}%>
    <html:image property="Back" value="Back" src="images/but_back.gif" onclick="return loadOrderList();"/>
    <html:hidden property="conId" name="<%=Constants.CONTRACT%>"/>
    <logic:notEqual name="<%=Constants.CONTRACT%>" property="conId" value="0">
        <html:hidden property="tenId" name="<%=Constants.CONTRACT%>"/>
        <html:hidden property="venId" name="<%=Constants.CONTRACT%>"/>
        <html:hidden property="parentId" name="<%=Constants.CONTRACT%>"/>
    </logic:notEqual>
    <html:hidden property="kind" name="<%=Constants.CONTRACT%>"/>
</form>
<div id="contractMaterialHideDiv" style="display:none"></div>