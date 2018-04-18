<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>
<div style="width:500px;height:450px;overflow:auto;" >
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
                <td><bean:message key="message.project"/></td>
                <td colspan="3">
                    <html:select property="proId" name="<%=Constants.CONTRACT%>">
                        <html:options collection="<%=Constants.PROJECT_LIST%>" property="proId" labelProperty="name"/>
                    </html:select>
                </td>
            </tr>
            <tr>
                <td><bean:message key="message.contract.responseEmp"/></td>
                <td>
                    <html:select property="handleEmp" name="<%=Constants.CONTRACT%>">
                        <html:options collection="<%=Constants.EMPLOYEE_LIST%>" property="empId" labelProperty="fullname"/>
                    </html:select>
                </td>
                <td><bean:message key="message.contract.followEmp"/></td>
                <td>
                    <html:select property="followEmp" name="<%=Constants.CONTRACT%>">
                        <html:options collection="<%=Constants.EMPLOYEE_LIST%>" property="empId" labelProperty="fullname"/>
                    </html:select>
                </td>
            </tr>
            <tr>
                <td><bean:message key="message.contract.vendor"/></td>
                <td colspan="3">
                    <html:select property="venId" name="<%=Constants.CONTRACT%>">
                        <html:options collection="<%=Constants.VENDOR_LIST%>" property="venId" labelProperty="name"/>
                    </html:select>
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
    </form>
    <html:image property="Submit" value="Submit" src="images/but_find.gif" style="cursor: hand;" onclick="return searchInvoiceContract();"/>
    <input type="hidden" id="callbackFunc"/>
    <input type="hidden" id="invoiceContractData"/>
    <input type="hidden" id="invoiceContractSearchUrl"/>
</div>