<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>
<table id="tenderPlanVendorTbl" class="its" style="width:100%">
    <thead>
        <tr>
            <th width="20px"><bean:message key="message.del"/></th>
            <th><bean:message key="message.rowNum"/></th>
            <th><bean:message key="message.vendor.name"/></th>
            <th><bean:message key="message.vendor.phone"/></th>
            <th><bean:message key="message.vendor.fax"/></th>
            <th><bean:message key="message.vendor.email"/></th>
            <th><bean:message key="message.vendor.address"/></th>
        </tr>
    </thead>
    <tbody>
        <logic:iterate id="vendor" name="<%=Constants.VENDOR_LIST%>">
            <tr>
                <td width="20px">
                    <input type="checkbox" name="tenderPlanVendorChk" value="<bean:write name="vendor" property="tevId"/>">
                    <input type="hidden" name="vendor" value="<bean:write name="vendor" property="venId"/>"/>
                </td>
                <td><bean:write name="vendor" property="note"/></td>
                <td><bean:write name="vendor" property="venName"/></td>
                <td><bean:write name="vendor" property="venPhone"/></td>
                <td><bean:write name="vendor" property="venFax"/></td>
                <td><bean:write name="vendor" property="venEmail"/></td>
                <td><bean:write name="vendor" property="venAddress"/></td>
            </tr>
        </logic:iterate>
    </tbody>
</table>