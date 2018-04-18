<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.MCUtil"%>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<%
    String empId = MCUtil.getParameter("empId", request);
%>
<h3><bean:message key="message.detailemployee.title"/></h3>
<html:form action="addEmployee.do">
    <table border="0" width="100%" cellspacing="0" cellpadding="0" >
        <tr><td><div align="center">
                    <table width="90%" border="0" cellspacing="0" cellpadding="0" style="border-width: 0px">
                        <tr>
                            <td>&nbsp;</td>
                        </tr>
                        <tr>
                            <td height="22"><bean:message key="message.employee.orgName"/></td>
                            <td height="22" colspan="5">
                                <html:select property="orgId" name="<%=Constants.EMPLOYEE%>">
                                    <html:options collection="<%=Constants.ORGANIZATION_LIST%>" property="orgId" labelProperty="name"/>
                                </html:select>
                            </td>
                        </tr>
                        <tr>
                            <td height="22"><bean:message key="message.employee.posName"/></td>
                            <td height="22" colspan="5">
                                <html:select property="posId" name="<%=Constants.EMPLOYEE%>">
                                    <html:options collection="<%=Constants.POSITION_LIST%>" property="posId" labelProperty="name"/>
                                </html:select>
                            </td>
                        </tr>
                        <tr>
                            <td height="22"><bean:message key="message.employee.code"/></td>
                            <td height="22" colspan="5"><html:text property="code" size="80" name="<%=Constants.EMPLOYEE%>"/></td>
                        </tr>
                        <tr>
                            <td height="22"><bean:message key="message.employee.fullname"/></td>
                            <td height="22" colspan="5"><html:text property="fullname" size="80" name="<%=Constants.EMPLOYEE%>"/></td>
                        </tr>
                        <tr>
                            <td height="22"><bean:message key="message.employee.nickname"/></td>
                            <td height="22" colspan="5"><html:text property="nickname" size="80" name="<%=Constants.EMPLOYEE%>"/></td>
                        </tr>
                        <logic:equal name="<%=Constants.EMPLOYEE%>" value="0" property="empId">
                            <tr>
                                <td height="22"><bean:message key="message.employee.password"/></td>
                                <td height="22" colspan="5"><html:text property="password" size="80" name="<%=Constants.EMPLOYEE%>"/></td>
                            </tr>
                        </logic:equal>
                        <tr>
                            <td height="22"><bean:message key="message.employee.email"/></td>
                            <td height="22" colspan="5"><html:text property="email" size="80" name="<%=Constants.EMPLOYEE%>"/></td>
                        </tr>
                        <tr>
                            <td height="22"><bean:message key="message.employee.officePhone"/></td>
                            <td height="22" colspan="5"><html:text property="officePhone" size="80" name="<%=Constants.EMPLOYEE%>" /></td>
                        </tr>
                        <tr>
                            <td height="22"><bean:message key="message.employee.handPhone"/></td>
                            <td height="22" colspan="5"><html:text property="handPhone" size="80" name="<%=Constants.EMPLOYEE%>" /></td>
                        </tr>
                        <tr>
                            <td height="22"><bean:message key="message.employee.homePhone"/></td>
                            <td height="22" colspan="5"><html:text property="homePhone" size="80" name="<%=Constants.EMPLOYEE%>" /></td>
                        </tr>
                        <tr>
                            <td height="22"><bean:message key="message.employee.status"/></td>
                            <td height="22">
                                <html:select property="status" name="<%=Constants.EMPLOYEE%>">
                                    <html:options collection="<%=Constants.EMPLOYEE_STATUS_LIST%>" property="value" labelProperty="label"/>
                                </html:select>
                            </td>
                        </tr>
                        <tr>
                            <td height="22"><bean:message key="message.employee.createdDate"/></td>
                            <td height="22"><html:text property="createdDate" size="20" name="<%=Constants.EMPLOYEE%>" /></td>
                        </tr>  
                        <tr>
                            <td height="22"><bean:message key="message.employee.modifiedDate"/></td>
                            <td height="22"><html:text property="modifiedDate" size="20" name="<%=Constants.EMPLOYEE%>" /></td>
                        </tr>  
                        <tr>
                            <td height="22"><bean:message key="message.employee.lastLogonDate"/></td>
                            <td height="22"><html:text property="lastLogonDate" size="20" name="<%=Constants.EMPLOYEE%>" /></td>
                        </tr>  
                        <tr>
                            <td height="22"><bean:message key="message.employee.firstIp"/></td>
                            <td height="22"><html:text property="firstIp" size="20" name="<%=Constants.EMPLOYEE%>" /></td>
                        </tr>  
                        <tr>
                            <td height="22"><bean:message key="message.employee.lastIp"/></td>
                            <td height="22"><html:text property="lastIp" size="20" name="<%=Constants.EMPLOYEE%>" /></td>
                        </tr>  
                    </table>
                    <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_EDIT, PermissionUtil.PER_SYSTEM)) {%>
                    <p style="margin-top: 0; margin-bottom: 0" align="left"><html:image onclick="return saveEmployee();" src="images/but_save.gif" style="cursor: hand;"/><img border="0" src="images/blank.gif" width="2" height="17"><html:image property="Back" value="Back" src="images/but_back.gif" onclick="return loadEmployeeList();"/></p>
                        <%}%>

                </div></td></tr></table> 
    <html:hidden property="empId" name="<%=Constants.EMPLOYEE%>" />
</html:form>