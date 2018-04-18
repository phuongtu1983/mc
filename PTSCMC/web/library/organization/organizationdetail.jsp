<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.MCUtil"%>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<%
    String orgId = MCUtil.getParameter("orgId", request);
%>
<h3><bean:message key="message.detailorganization.title"/></h3>
<div id="errorMessageDiv"></div>
<html:form action="addOrganization.do">
    <table border="0" width="100%" cellspacing="0" cellpadding="0" >
        <tr><td><div align="center">
                    <table width="90%" border="0" cellspacing="0" cellpadding="0" style="border-width: 0px">
                        <tr>
                            <td>&nbsp;</td>
                        </tr>
                        <tr>
                            <td height="22"><bean:message key="message.organization.l_name"/></td>
                            <td height="22" colspan="5"><html:text property="name" size="80" name="<%=Constants.ORGANIZATION%>"/></td>
                        </tr>
                        <tr>
                            <td height="22"><bean:message key="message.organization.abbreviate"/></td>
                            <td height="22" colspan="5"><html:text property="abbreviate" size="80" name="<%=Constants.ORGANIZATION%>" /></td>
                        </tr>
                        <tr>
                            <td height="22"><bean:message key="message.organization.parentId"/></td>
                            <td height="22" colspan="5">
                                <html:select property="parentId" name="<%=Constants.ORGANIZATION%>">
                                    <html:options collection="<%=Constants.ORGANIZATION_PARENT%>" property="orgId" labelProperty="name"/>
                                </html:select>
                            </td>
                        </tr>  
                        <tr>
                            <td height="22"><bean:message key="message.organization.status"/></td>
                            <td height="22">
                                <html:select property="status" name="<%=Constants.ORGANIZATION%>">
                                    <html:options collection="<%=Constants.ORGANIZATION_STATUS_LIST%>" property="value" labelProperty="label"/>
                                </html:select>
                            </td>
                        </tr>
                        <tr>
                            <td height="22"><bean:message key="message.organization.kind"/></td>
                            <td height="22">
                                <html:select property="kind" name="<%=Constants.ORGANIZATION%>">
                                    <html:options collection="<%=Constants.ORGANIZATION_KIND_LIST%>" property="value" labelProperty="label"/>
                                </html:select>
                            </td>
                        </tr>  
                    </table>
                    <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_EDIT, PermissionUtil.PER_SYSTEM)) {%>
                    <p style="margin-top: 0; margin-bottom: 0" align="left"><html:image onclick="return saveOrganization();" src="images/but_save.gif" style="cursor: hand;"/><img border="0" src="images/blank.gif" width="2" height="17">
                    <%}%>
                    <html:image property="Back" value="Back" src="images/but_back.gif" onclick="return loadOrganizationList();"/></p>
                </div></td></tr></table> 
    <html:hidden property="orgId" name="<%=Constants.ORGANIZATION%>" />
</html:form>