<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<div id="errorMessageBidOpeningDiv"></div>
<form name="bidOpeningForm">
    <table width="100%">
        <tr>
            <td width="160px"><bean:message key="message.tenderplan.bidopening.reportNumber"/></td>
            <td><html:text size="20" property="reportNumber" name="<%=Constants.TENDERPLAN_BIDOPENING%>"/></td>
            <td width="80px"><bean:message key="message.tenderplan.number"/></td>
            <td><html:text size="20" disabled="true" property="tenderNumber" name="<%=Constants.TENDERPLAN_BIDOPENING%>"/></td>
        </tr>  
        <tr>
            <td><bean:message key="message.tenderplan.bidopening.startDate"/></td>
            <td><html:text size="5" property="startTime" name="<%=Constants.TENDERPLAN_BIDOPENING%>"/><html:text property="startDate" size="10" styleId="startDate" onclick="javascript: showCalendar('startDate')" name="<%=Constants.TENDERPLAN_BIDOPENING%>"/></td>
            <td><bean:message key="message.tenderplan.bidopening.endDate"/></td>
            <td><html:text size="5" property="endTime" name="<%=Constants.TENDERPLAN_BIDOPENING%>"/><html:text property="endDate" size="10" styleId="endDate" onclick="javascript: showCalendar('endDate')" name="<%=Constants.TENDERPLAN_BIDOPENING%>"/></td>
        </tr>
        <tr>
            <td colspan="4">
                <fieldset>
                    <legend><bean:message key="message.tenderplan.bidopening.group"/></legend>
                    <logic:notEqual name="<%=Constants.TENDERPLAN_BIDOPENING%>" property="borId" value="0">
                        <html:image src="images/ico_xoa.gif" onclick="return delBidOpeningGroup();"/>
                    </logic:notEqual>
                    <html:image src="images/ico_them.gif" onclick="return addBidOpeningGroup();"/>
                    <select name="employeeList">
                        <logic:iterate id="emp" name="<%=Constants.EMPLOYEE_LIST%>">
                            <option value="<bean:write name="emp" property="empId"/>"><bean:write name="emp" property="fullname"/></option>
                        </logic:iterate>
                    </select>
                    <table class="its" style="width:100%"  id="bidOpeningEmployeeTbl">
                        <thead>
                            <tr>
                                <th><bean:message key="message.del"/></th>
                                <th><bean:message key="message.tenderplan.employee.name"/></th>
                                <th><bean:message key="message.tenderplan.employee.position"/></th>
                                <th><bean:message key="message.employee.note"/></th>
                            </tr>
                        </thead>
                        <tbody>
                            <logic:iterate id="employee" name="<%=Constants.TENDERPLAN_BIDOPENING_EMPLOYEE%>">
                                <tr>
                                    <td>
                                        <input type="checkbox" name="bidOpeningEmployeeChk" value="${employee.bogId}"/>
                                        <input type="hidden" name="bogId" value="${employee.bogId}"/>
                                    </td>
                                    <td><input type="textbox" size="37" name="bidOpeningEmployee" value="${employee.employee}"/></td>
                                    <td><input type="textbox" size="37" name="bidOpeningPosition" value="${employee.position}"/></td>
                                    <td><input type="textbox" size="37" name="bidOpeningNote" value="${employee.note}"/></td>
                                </tr>
                            </logic:iterate>
                        </tbody>
                    </table>
                </fieldset>
            </td>
        </tr>
        <tr>
            <td colspan="4">
                <fieldset>
                    <legend><bean:message key="message.tenderplan.bidopening.vendor"/></legend>
                    <table id="bidOpeningVendorTbl" class="its" style="width:100%" >
                        <thead>
                            <tr>
                                <th><bean:message key="message.vendor.name"/></th>
                                <th><bean:message key="message.tenderplan.bidopening.page"/></th>
                                <th><bean:message key="message.tenderplan.bidopening.foundation"/></th>
                                <th><bean:message key="message.tenderplan.bidopening.status"/></th>
                                <th><bean:message key="message.tenderplan.bidopening.quono"/></th>
                                <th><bean:message key="message.tenderplan.bidopening.quodate"/></th>
                                <th><bean:message key="message.tenderplan.bidopening.bidvalidity"/></th>
                                <th><bean:message key="message.tenderplan.bidopening.incoterm"/></th>
                            </tr>
                        </thead>
                        <tbody>
                            <logic:iterate id="vendor" name="<%=Constants.TENDERPLAN_BIDOPENING_VENDOR%>">
                                <tr>
                                    <td><bean:write name="vendor" property="venName"/></td>
                                    <td width="20px">
                                        <input type="text" size="5" name="vendorBiddedPage" value="<bean:write name="vendor" property="biddedPage"/>">
                                        <input type="hidden" name="vendorId" value="<bean:write name="vendor" property="venId"/>"/>
                                    </td>
                                    <td width="20px"><input type="text" size="10" name="vendorBiddedFoundation" value="<bean:write name="vendor" property="biddedFoundation"/>"></td>
                                    <td width="20px">
                                        <select name="vendorBiddedStatus">
                                            <logic:iterate id="status" name="<%=Constants.TENDERPLAN_BIDOPENING_BIDDEDSTATUS%>">
                                                <logic:equal name="vendor" property="biddedStatus" value="${status.value}"><option selected value="<bean:write name="status" property="value"/>"><bean:write name="status" property="label"/></option></logic:equal>
                                                <logic:notEqual name="vendor" property="biddedStatus" value="${status.value}"><option value="<bean:write name="status" property="value"/>"><bean:write name="status" property="label"/></option></logic:notEqual>
                                            </logic:iterate>
                                        </select>
                                    </td>
                                    <td width="20px"><input type="text" size="10" name="vendorQuoNo" value="<bean:write name="vendor" property="quoNo"/>"></td>
                                    <td width="20px"><input type="text" size="10" name="vendorQuoDate" id="vendorQuoDate${vendor.venId}" onclick="javascript: showCalendar('vendorQuoDate${vendor.venId}')" value="<bean:write name="vendor" property="quoDate"/>"></td>
                                    <td width="20px"><input type="text" size="10" name="vendorBidValidity" value="<bean:write name="vendor" property="bidValidity"/>"></td>
                                    <td width="20px">
                                        <select name="vendorIncoterm">
                                            <logic:iterate id="incoterm" name="<%=Constants.TENDERPLAN_BIDOPENING_INCOTERM%>">
                                                <logic:equal name="vendor" property="incoterm" value="${incoterm.incId}"><option selected value="<bean:write name="incoterm" property="incId"/>"><bean:write name="incoterm" property="incName"/></option></logic:equal>
                                                <logic:notEqual name="vendor" property="incoterm" value="${incoterm.incId}"><option value="<bean:write name="incoterm" property="incId"/>"><bean:write name="incoterm" property="incName"/></option></logic:notEqual>
                                            </logic:iterate>
                                        </select>
                                    </td>
                                </tr>
                            </logic:iterate>
                        </tbody>
                    </table>
                </fieldset>
            </td>
        </tr>
        <tr><td colspan="4"><bean:message key="message.note"/></td></tr>
        <tr><td colspan="4"><html:textarea cols="113" rows="4" property="comments" name="<%=Constants.TENDERPLAN_BIDOPENING%>"/></td></tr>
        <logic:notEqual name="<%=Constants.TENDERPLAN_BIDOPENING%>" property="borId" value="0">
            <tr>
                <td colspan="4"><div id='divAttachFileSystem3' ><img src="img/indicator.gif"/></div></td>            
            </tr>
        </logic:notEqual> 
    </table>
    <logic:notEqual name="<%=Constants.TENDERPLAN_BIDOPENING%>" property="borId" value="0">
        <img onclick="return printTenderPlanBidOpening();" src="images/but_print.gif"/>
    </logic:notEqual> 
    <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_EDIT, PermissionUtil.PER_PURCHASING_TENDERPLAN)) {%>
    <logic:present name="<%=Constants.CAN_SAVE%>">
        <html:image onclick="return saveTenderPlanBidOpening();" src="images/but_save.gif"/>
    </logic:present>
    <%}%>
    <html:hidden property="borId" name="<%=Constants.TENDERPLAN_BIDOPENING%>"/>
    <html:hidden property="tenId" name="<%=Constants.TENDERPLAN_BIDOPENING%>"/>
</form>
