<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>
<table id="techEvalGroupTbl" class="its" style="width:100%" >
    <thead>
        <tr>
            <th width="20px"><bean:message key="message.del"/></th>
            <th><bean:message key="message.tenderplan.employee.name"/></th>
            <th><bean:message key="message.tenderplan.employee.position"/></th>
            <th><bean:message key="message.note"/></th>
        </tr>
    </thead>
    <tbody>
        <logic:iterate id="employee" name="<%=Constants.EMPLOYEE_LIST%>">
            <tr>
                <td>
                    <input type="checkbox" name="techEvalEmployeeChk" value='<bean:write name="employee" property="tegId"/>'/>
                    <input type="hidden" name="evalId" value='<bean:write name="employee" property="tegId"/>'/>
                </td>
                <td><input type="textbox" size="37" name="evalEmployee" value="${employee.employee}"/></td>
                <td><input type="textbox" size="37" name="evalPosition" value="${employee.position}"/></td>
                <td><input type="textbox" size="37" name="evalNote" value="${employee.note}"/></td>
            </tr>
        </logic:iterate>
    </tbody>
</table>