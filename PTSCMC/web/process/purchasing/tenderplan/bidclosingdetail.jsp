<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<div id="errorMessageBidClosingDiv"></div>
<form name="bidClosingForm">
    <table width="100%">
        <tr>
            <td width="160px"><bean:message key="message.tenderplan.bidclosing.reportNumber"/></td>
            <td><html:text size="20" property="reportNumber" name="<%=Constants.TENDERPLAN_BIDCLOSING%>"/></td>
            <td width="80px"><bean:message key="message.tenderplan.number"/></td>
            <td><html:text size="20" disabled="true" property="tenderNumber" name="<%=Constants.TENDERPLAN_BIDCLOSING%>"/></td>
        </tr>  
        <tr>
            <td><bean:message key="message.tenderplan.bidclosing.closingDate"/></td>
            <td><html:text size="5" property="closingTime" name="<%=Constants.TENDERPLAN_BIDCLOSING%>"/><html:text property="closingDate" size="10" styleId="closingDate" onclick="javascript: showCalendar('closingDate')" name="<%=Constants.TENDERPLAN_BIDCLOSING%>"/></td>
            <td><bean:message key="message.tenderplan.bidclosing.endClosingDate"/></td>
            <td><html:text size="5" property="endClosingTime" name="<%=Constants.TENDERPLAN_BIDCLOSING%>"/><html:text property="endClosingDate" size="10" styleId="endClosingDate" onclick="javascript: showCalendar('endClosingDate')" name="<%=Constants.TENDERPLAN_BIDCLOSING%>"/></td>
        </tr>
        <tr>
            <td colspan="4">
                <fieldset>
                    <legend><bean:message key="message.tenderplan.bidclosing.group"/></legend>
                    <logic:notEqual name="<%=Constants.TENDERPLAN_BIDCLOSING%>" property="bcrId" value="0">
                        <html:image src="images/ico_xoa.gif" onclick="return delBidClosingGroup();"/>
                    </logic:notEqual>
                    <html:image src="images/ico_them.gif" onclick="return addBidClosingGroup();"/>
                    <select name="employeeList" onchange="document.forms['bidClosingForm'].employeeList2.options.selectedIndex=this.options.selectedIndex">
                        <logic:iterate id="emp" name="<%=Constants.EMPLOYEE_LIST%>">
                            <option value="<bean:write name="emp" property="empId"/>"><bean:write name="emp" property="fullname"/></option>
                        </logic:iterate>
                    </select>
                    <!--<select name="employeeList2" onchange="document.forms['bidClosingForm'].employeeList.options.selectedIndex=this.options.selectedIndex">
                        <logic:iterate id="org" name="<%=Constants.EMPLOYEE_LIST%>">
                            <option value="<bean:write name="org" property="empId"/>"><bean:write name="org" property="orgName"/></option>
                        </logic:iterate>
                    </select>-->
                    <table class="its" style="width:100%"  id="bidClosingEmployeeTbl">
                        <thead>
                            <tr>
                                <th><bean:message key="message.del"/></th>
                                <th><bean:message key="message.tenderplan.employee.name"/></th>
                                <th><bean:message key="message.tenderplan.employee.position"/></th>
                                <th><bean:message key="message.employee.note"/></th>
                            </tr>
                        </thead>
                        <tbody>
                            <logic:iterate id="employee" name="<%=Constants.TENDERPLAN_BIDCLOSING_EMPLOYEE%>">
                                <tr>
                                    <td>
                                        <input type="checkbox" name="bidClosingEmployeeChk" value="${employee.bcgId}"/>
                                        <input type="hidden" name="bcgId" value="${employee.bcgId}"/>
                                    </td>
                                    <td><input type="textbox" size="37" name="bidClosingEmployee" value="${employee.employee}"/></td>
                                    <td><input type="textbox" size="37" name="bidClosingPosition" value="${employee.position}"/></td>
                                    <td><input type="textbox" size="37" name="bidClosingNote" value="${employee.note}"/></td>
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
                    <legend><bean:message key="message.tenderplan.bidclosing.vendor"/></legend>
                    <table id="bidClosingVendorTbl" class="its" style="width:100%" >
                        <thead>
                            <tr>
                                
                                <th><bean:message key="message.vendor.name"/></th>
                                <th><bean:message key="message.vendor.phone"/></th>
                                <th><bean:message key="message.vendor.fax"/></th>
                                <th><bean:message key="message.vendor.email"/></th>
                                <th><bean:message key="message.vendor.address"/></th>
                            </tr>
                        </thead>
                        <tbody>
                            <logic:iterate id="vendor" name="<%=Constants.TENDERPLAN_BIDCLOSING_VENDOR%>">
                                <tr>                                    
                                        
                                        <input type="hidden" name="vendorId" value="<bean:write name="vendor" property="venId"/>"/>
                                    
                                    <td><bean:write name="vendor" property="venName"/></td>
                                    <td><bean:write name="vendor" property="venPhone"/></td>
                                    <td><bean:write name="vendor" property="venFax"/></td>
                                    <td><bean:write name="vendor" property="venEmail"/></td>
                                    <td><bean:write name="vendor" property="venAddress"/></td>
                                </tr>
                            </logic:iterate>
                        </tbody>
                    </table>
                </fieldset>
            </td>
        </tr>
        <tr>
            <td colspan="4"><bean:message key="message.note"/></td>
        </tr>
        <tr>
            <td colspan="4"><html:textarea cols="113" rows="4" property="comments" name="<%=Constants.TENDERPLAN_BIDCLOSING%>"/></td>
        </tr>
        <logic:notEqual name="<%=Constants.TENDERPLAN_BIDCLOSING%>" property="bcrId" value="0">
            <tr>
                <td colspan="4"><div id='divAttachFileSystem2' ><img src="img/indicator.gif"/></div></td>            
            </tr>
        </logic:notEqual> 
    </table>
    <logic:notEqual name="<%=Constants.TENDERPLAN_BIDCLOSING%>" property="bcrId" value="0">
        <img onclick="return printTenderPlanBidClosing();" src="images/but_print.gif"/>
    </logic:notEqual> 
    <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_EDIT, PermissionUtil.PER_PURCHASING_TENDERPLAN)) {%>
    <logic:present name="<%=Constants.CAN_SAVE%>">
        <html:image onclick="return saveTenderPlanBidClosing();" src="images/but_save.gif"/>
    </logic:present>
    <%}%>
    <html:hidden property="bcrId" name="<%=Constants.TENDERPLAN_BIDCLOSING%>"/>
    <html:hidden property="tenId" name="<%=Constants.TENDERPLAN_BIDCLOSING%>"/>
</form>