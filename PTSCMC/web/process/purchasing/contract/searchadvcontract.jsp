<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>
<form name="searchContractForm">
    <table width="100%">
        <tr>
            <td><bean:message key="message.contract.number"/></td>
            <td colspan="3"><html:text size="20" property="contractNumber" name="<%=Constants.CONTRACT%>"/></td>
        </tr>
        <tr>
            <td><bean:message key="message.contract.effectedDate"/> <bean:message key="message.from"/></td>
            <td><html:text property="effectedFromDate" size="10" styleId="effectedFromDate" onclick="javascript: showCalendar('effectedFromDate')" name="<%=Constants.CONTRACT%>" /></td>
            <td><bean:message key="message.to"/></td>
            <td><html:text property="effectedToDate" size="10" styleId="effectedToDate" onclick="javascript: showCalendar('effectedToDate')" name="<%=Constants.CONTRACT%>" /></td>
        </tr>
        <tr>
            <td><bean:message key="message.payment.moveAccountingDate"/> <bean:message key="message.from"/></td>
            <td><html:text property="moveAccountingFromDate" size="10" styleId="moveAccountingFromDate" onclick="javascript: showCalendar('moveAccountingFromDate')" name="<%=Constants.CONTRACT%>" /></td>
            <td><bean:message key="message.to"/></td>
            <td><html:text property="moveAccountingToDate" size="10" styleId="moveAccountingToDate" onclick="javascript: showCalendar('moveAccountingToDate')" name="<%=Constants.CONTRACT%>" /></td>
        </tr>
        <tr>
            <td><bean:message key="message.appendix.deliveryDateAsContract"/> <bean:message key="message.from"/></td>
            <td><html:text property="deliveryDateAsContractFromDate" size="10" styleId="deliveryDateAsContractFromDate" onclick="javascript: showCalendar('deliveryDateAsContractFromDate')" name="<%=Constants.CONTRACT%>" /></td>
            <td><bean:message key="message.to"/></td>
            <td><html:text property="deliveryDateAsContractToDate" size="10" styleId="deliveryDateAsContractToDate" onclick="javascript: showCalendar('deliveryDateAsContractToDate')" name="<%=Constants.CONTRACT%>" /></td>
        </tr>
        <tr>
            <td><bean:message key="message.project"/></td>
            <td colspan="3">
                <html:select style="width: 170px;" property="proId" name="<%=Constants.CONTRACT%>">
                    <html:options collection="<%=Constants.PROJECT_LIST%>" property="proId" labelProperty="name"/>
                </html:select>
            </td>
        </tr>
        <tr id="contractFollowHandleEmpIdTr">
            <td><bean:message key="message.contract.responseEmp"/></td>
            <td colspan="3">
                <html:select style="width: 170px;" property="handleEmp" styleId="contractFollowHandleEmpCmb" name="<%=Constants.CONTRACT%>">
                    <html:options collection="<%=Constants.EMPLOYEE_LIST%>" property="empId" labelProperty="fullname"/>
                </html:select>
                <input type="button" onclick="return comboTextIdClick('searchContractForm','handleEmpName','handleEmp','contractFollowHandleEmpIdTr','contractFollowHandleEmpNameTr');" value='<>'/>
            </td>
        </tr>
        <tr style="display:none" id="contractFollowHandleEmpNameTr">
            <td><bean:message key="message.contract.responseEmp"/></td>
            <td colspan="3">
                <html:text size="29" property="handleEmpName" name="<%=Constants.CONTRACT%>"/>
                <input type="button" onclick="return comboTextNameClick('searchContractForm','getEmployeeToCombobox.do',null,'handleEmpName','handleEmp','contractFollowHandleEmpCmb','contractFollowHandleEmpIdTr','contractFollowHandleEmpNameTr');" value='<>'/>
            </td>
        </tr>
        <tr id="contractFollowFollowEmpIdTr">
            <td><bean:message key="message.contract.followEmp"/></td>
            <td colspan="3">
                <html:select style="width: 170px;" property="followEmp" name="<%=Constants.CONTRACT%>" styleId="contractFollowFollowEmpCmb">
                    <html:options collection="<%=Constants.EMPLOYEE_LIST%>" property="empId" labelProperty="fullname"/>
                </html:select>
                <input type="button" onclick="return comboTextIdClick('searchContractForm','followEmpName','followEmp','contractFollowFollowEmpIdTr','contractFollowFollowEmpNameTr');" value='<>'/>
            </td>
        </tr>
        <tr style="display:none" id="contractFollowFollowEmpNameTr">
            <td><bean:message key="message.contract.followEmp"/></td>
            <td colspan="3">
                <html:text size="29" property="followEmpName" name="<%=Constants.CONTRACT%>"/>
                <input type="button" onclick="return comboTextNameClick('searchContractForm','getEmployeeToCombobox.do',null,'followEmpName','followEmp','contractFollowFollowEmpCmb','contractFollowFollowEmpIdTr','contractFollowFollowEmpNameTr');" value='<>'/>
            </td>
        </tr>
        <tr id="contractFollowVendorIdTr">
            <td><bean:message key="message.contract.vendor"/></td>
            <td colspan="3">
                <html:select style="width: 300px;" styleId="contractFollowVendorIdCmb" property="venId" name="<%=Constants.CONTRACT%>">
                    <html:options collection="<%=Constants.VENDOR_LIST%>" property="venId" labelProperty="name"/>
                </html:select>
                <input type="button" onclick="return comboTextIdClick('searchContractForm','vendorName','venId','contractFollowVendorIdTr','contractFollowVendorNameTr');" value='<>'/>
            </td>
        </tr>
        <tr style="display:none" id="contractFollowVendorNameTr">
            <td><bean:message key="message.contract.vendor"/></td>
            <td colspan="3">
                <html:text size="55" property="vendorName" name="<%=Constants.CONTRACT%>"/>
                <input type="button" onclick="return comboTextNameClick('searchContractForm','getVendorToCombobox.do',null,'vendorName','venId','contractFollowVendorIdCmb','contractFollowVendorIdTr','contractFollowVendorNameTr');" value='<>'/>
            </td>
        </tr>
        <tr>
            <td><bean:message key="message.contract.status"/></td>
            <td colspan="3">
                <html:select property="paymentStatus" name="<%=Constants.CONTRACT%>">
                    <html:options collection="<%=Constants.STATUS_LIST%>" property="value" labelProperty="label"/>
                </html:select>
            </td>
        </tr>
    </table>
    <logic:equal name="<%=Constants.CONTRACT%>" property="isPrint" value="0">
        <html:image property="Submit" value="Submit" src="images/but_find.gif" style="cursor: hand;" onclick="return searchAdvContract();"/>
    </logic:equal>
    <logic:notEqual name="<%=Constants.CONTRACT%>" property="isPrint" value="0">
        <img onclick="return printContractProcessFollow();" src="images/but_print.gif"/>
    </logic:notEqual>
    <html:hidden property="kind" name="<%=Constants.CONTRACT%>"/>
</form>