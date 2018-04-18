<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<%@ page import="com.venus.mc.contract.ContractFormBean"%>
<div id="adjustmentErrorMessageDiv"></div>
<form name="adjustmentForm">
    <table width="100%">
        <tr>
            <td><bean:message key="message.adjustment.number"/></td>
            <td><html:text size="30" property="contractNumber" name="<%=Constants.CONTRACT%>"/></td>
            <td><bean:message key="message.contract.status"/></td>
            <td>
                <html:select property="paymentStatus" name="<%=Constants.CONTRACT%>">
                    <html:options collection="<%=Constants.STATUS_LIST%>" property="value" labelProperty="label"/>
                </html:select>
            </td>
        </tr>
        <tr>
            <td><bean:message key="message.contract.number"/></td>
            <td>
                <html:text size="30" property="parentNumber" disabled="true" name="<%=Constants.CONTRACT%>"/>
                <html:hidden property="parentId" name="<%=Constants.CONTRACT%>"/>
            </td>
            <td><bean:message key="message.adjustment.contract.number"/></td>
            <td>
                <html:text size="20" property="appendixContractNumber" name="<%=Constants.CONTRACT%>"/>
            </td>
        </tr>
        <tr>
            <td><bean:message key="message.u_vendor"/></td>
            <td colspan="3" id="adjustmentVendorName"></td>
        </tr>
        <tr>
            <td><bean:message key="message.contract.signDate"/></td>
            <td><html:text property="signDate" size="10" styleId="asignDate1" onclick="javascript: showCalendar('asignDate1')" name="<%=Constants.CONTRACT%>" /></td>
            <td><bean:message key="message.contract.effectedDate"/></td>
            <td><html:text property="effectedDate" size="10" styleId="adjustmentEffectedDate1" onclick="javascript: showCalendar('adjustmentEffectedDate1')" onblur="updateOtherDateValue(this.value,document.forms['adjustmentForm'].aexpireDate,365)" name="<%=Constants.CONTRACT%>" /></td>
        </tr>
        <tr>
            <!--<td><bean:message key="message.contract.expireDate"/></td>
            <td><html:text property="expireDate" size="10" styleId="aexpireDate1" onclick="javascript: showCalendar('aexpireDate1')" name="<%=Constants.CONTRACT%>" /></td>-->
        </tr>
        <tr>
            <td><bean:message key="message.contract.responseEmp"/></td>
            <td colspan="3"><html:text size="20" property="employeeName" name="<%=Constants.CONTRACT%>" disabled="true"/></td>
        </tr>
        <tr>
            <td><bean:message key="message.contract.followEmp"/></td>
            <td colspan="3">
                <span id="adjustmentFollowEmpIdSpan">
                    <html:select property="followEmp" name="<%=Constants.CONTRACT%>" styleId="adjustmentFollowEmpIdCmb">
                        <html:options collection="<%=Constants.EMPLOYEE_LIST%>" property="empId" labelProperty="fullname"/>
                    </html:select>
                    <input type="button" onclick="return comboTextIdClick('adjustmentForm','followEmpName','followEmp','adjustmentFollowEmpIdSpan','adjustmentFollowEmpNameSpan');" value='<>'/>
                </span>
                <span  style="display:none" id="adjustmentFollowEmpNameSpan">
                    <input type="textbox" size="29" name="followEmpName"/>
                    <input type="button" onclick="return comboTextNameClick('adjustmentForm','getEmployeeToCombobox.do',null,'followEmpName','followEmp','adjustmentFollowEmpIdCmb','adjustmentFollowEmpIdSpan','adjustmentFollowEmpNameSpan');" value='<>'/>
                </span>
            </td>
        </tr>
        <%
            ContractFormBean form = (ContractFormBean) request.getAttribute(Constants.CONTRACT);
            if (form.getIsPermissionPrice() == 1) {
        %>
        <tr>
            <td><bean:message key="message.contract.transport"/></td>
            <td><html:text style="text-align: right" size="20" property="transport" name="<%=Constants.CONTRACT%>"  onblur="formatPositiveNumberMoney(this);caculateAdjustment()" onfocus="return reformatNumberMoney(this)"/></td>
            <td><bean:message key="message.contract.otherFee"/></td>
            <td><html:text style="text-align: right" size="20" property="otherFee" name="<%=Constants.CONTRACT%>"  onblur="formatPositiveNumberMoney(this);caculateAdjustment()" onfocus="return reformatNumberMoney(this)"/></td>
        </tr>
        <tr>
            <td><bean:message key="message.contract.totalNotVAT"/></td>
            <td><html:text size="20" style="text-align: right" property="totalNotVAT" readonly="true" name="<%=Constants.CONTRACT%>"/></td>
            <td><bean:message key="message.contract.sumVAT"/></td>
            <td><html:text size="20" style="text-align: right" property="sumVAT" readonly="true" name="<%=Constants.CONTRACT%>"/></td>
        </tr>
        <tr>
            <td><bean:message key="message.contract.total"/></td>
            <td><html:text size="20" style="text-align: right" property="total" readonly="true" name="<%=Constants.CONTRACT%>"/></td>
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
            <td><html:text size="20" styleId="adeliveryDate" onclick="javascript: showCalendar('adeliveryDate')" property="deliveryDate" name="<%=Constants.CONTRACT%>"/></td>
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
            <td>
                <html:select property="processStatus" name="<%=Constants.CONTRACT%>">
                    <html:options collection="<%=Constants.CONTRACT_PROCESS_STATUS_LIST%>" property="value" labelProperty="label"/>
                </html:select>
            </td>
            <td colspan="2"><html:text size="20" property="processStatusText" name="<%=Constants.CONTRACT%>"/></td>
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
                        if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_EDIT, PermissionUtil.PER_PURCHASING_CONTRACT, form.getOwnerId())) {
                    %>
                    <logic:equal name="<%=Constants.CONTRACT%>" property="isClosed" value="0">
                        <input type="button" onclick="return createDnAdjustment(8,document.forms['adjustmentForm'].conId.value);" value="<bean:message key="message.dn.add"/>"/>
                    </logic:equal>
                    <%}%>
                    <div>
                        <%
                            // ContractFormBean form = (ContractFormBean) request.getAttribute(Constants.CONTRACT);
                            if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_EDIT, PermissionUtil.PER_PURCHASING_CONTRACT, form.getOwnerId())) {
                        %>
                        <logic:equal name="<%=Constants.CONTRACT%>" property="paymentStatus" value="2">
                            <html:image src="images/ico_xoa.gif" onclick="return delAdjustmentDetails();"/>
                            <html:image src="images/ico_them.gif" onclick="return setSelectedAdjustmentMaterial();"/>
                            <span id="listAdjustmentMaterialDataSpan"><select name="material"></select></span>
                        </logic:equal>
                        <%}%>
                        <logic:notEqual name="<%=Constants.CONTRACT%>" property="paymentStatus" value="2">
                            <span id="listAdjustmentMaterialDataSpan" style="display:none"><select name="material"></select></span>
                        </logic:notEqual>
                    </div>
                    <div id="listAdjustmentMaterialDataDiv" style="width:700px;height:300px;overflow:auto"><%@include  file="/process/purchasing/contract/adjustment/details.jsp" %></div>
                </fieldset>
            </td>
        </tr>
        <logic:notEqual name="<%=Constants.CONTRACT%>" property="conId" value="0">
            <tr>
                <td colspan="4"><div id='divAttachFileSystem2' ><img src="img/indicator.gif"/></div></td>            
            </tr>
        </logic:notEqual> 
    </table>
    <logic:greaterThan name="<%=Constants.CONTRACT%>" value="0" property="conId">
        <img onclick="return printContractAdjustment();" src="images/but_print.gif"/>
        <%
            //    ContractFormBean form = (ContractFormBean) request.getAttribute(Constants.CONTRACT);
            if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_EDIT, PermissionUtil.PER_PURCHASING_CONTRACT, form.getOwnerId())) {
        %>
        <%--<input type="button" onclick="return printAdjustmentRequest();" value="<bean:message key="message.adjustment.createRequest"/>"/>--%>
        <%}%>
    </logic:greaterThan>
    <logic:present name="<%=Constants.CAN_EMAIL%>">
        <input value="<bean:message key="message.notify.request.notcode.email"/>" onclick="return emailForNotMaterialCodeAdjustment();" type="submit">
    </logic:present>
    <%
        //    ContractFormBean form = (ContractFormBean) request.getAttribute(Constants.CONTRACT);
        if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_EDIT, PermissionUtil.PER_PURCHASING_CONTRACT, form.getOwnerId())) {
    %>
    <html:image onclick="return saveAdjustment();" src="images/but_save.gif"/><img src="images/blank.gif" width="2" height="17">
    <%}%>
    <html:image property="Back" value="Back" src="images/but_back.gif" onclick="return loadAdjustmentList();"/>
    <html:hidden property="conId" name="<%=Constants.CONTRACT%>"/>
    <html:hidden property="kind" name="<%=Constants.CONTRACT%>"/>
    <input name="reqDetailIds" type="hidden">
</form>
<div id="adjustmentMaterialHideDiv" style="display:none"></div>
