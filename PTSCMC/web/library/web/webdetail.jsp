<%@page import="com.venus.mc.util.PermissionUtil"%>
<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.MCUtil"%>
<%@ page import="com.venus.mc.util.Constants"%>
<%
    String webId = MCUtil.getParameter("webId", request);
%>
<h3><bean:message key="message.detailweb.title"/></h3>
<div id="errorMessageDiv"></div>
<html:form action="addWeb.do">
    <table border="0" width="100%" cellspacing="0" cellpadding="0" >
        <tr><td><div align="center">
                    <table width="100%" border="0" cellspacing="0" cellpadding="0" style="border-width: 0px">
                        <tr>
                            <td>&nbsp;</td>
                        </tr>
                        <tr>
                            <td height="22"><bean:message key="message.web.address"/></td>
                            <td height="22" colspan="5"><html:text property="address" size="80" name="<%=Constants.WEB%>"/></td>
                        </tr>
                        <tr>
                            <td height="22"><bean:message key="message.web.content"/></td>
                            <td height="22"><html:textarea property="content" cols="80" rows="2" name="<%=Constants.WEB%>" /></td>
                        </tr>
                        <tr>
                            <td height="22"><bean:message key="message.web.comment"/></td>
                            <td height="22"><html:textarea property="comment" cols="80" rows="2" name="<%=Constants.WEB%>" /></td>
                        </tr>
                        <tr>
                            <td height="22"><bean:message key="message.web.evaluation"/></td>
                            <td height="22"><html:textarea property="evaluation" cols="80" rows="2" name="<%=Constants.WEB%>" /></td>
                        </tr>
                    </table>
                    <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_EDIT, PermissionUtil.PER_LIBRARY_STORE)) {%>
                    <html:image align="left" onclick="return saveWeb();" src="images/but_save.gif" style="cursor: hand;"/><img border="0" src="images/blank.gif" width="2" height="17">
                    <%}%>
                    <html:image align="left" property="Back" value="Back" src="images/but_back.gif" onclick="return loadWebList();"/>
                </div></td></tr></table> 
    <html:hidden property="webId" name="<%=Constants.WEB%>" />
</html:form>