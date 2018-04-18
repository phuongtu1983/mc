<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>
<table id="tenderPlanVendorDetailTbl">
    <logic:iterate id="vendor" name="<%=Constants.VENDOR_LIST%>">
        <tr>
            <td>
                <input type="checkbox" name="tenderPlanVendorChk" value="0">
                <input type="hidden" name="vendor" value="<bean:write name="vendor" property="venId"/>"/>
            </td>
            <td><bean:write name="vendor" property="note"/></td>
            <td><bean:write name="vendor" property="name"/></td>
            <td><bean:write name="vendor" property="phone"/></td>
            <td><bean:write name="vendor" property="fax"/></td>
            <td><bean:write name="vendor" property="email"/></td>
            <td><bean:write name="vendor" property="address"/></td>
        </tr>
    </logic:iterate>
</table>