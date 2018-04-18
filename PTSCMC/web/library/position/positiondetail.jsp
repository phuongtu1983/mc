<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.MCUtil"%>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<%
    String posId = MCUtil.getParameter("posId", request);
%>
<h3><bean:message key="message.detailposition.title"/></h3>
<div id="errorMessageDiv"></div>
<html:form action="addPosition.do">
    <table border="0" width="100%" cellspacing="0" cellpadding="0" >
        <tr><td><div align="center">
                    <table width="90%" border="0" cellspacing="0" cellpadding="0" style="border-width: 0px">
                        <tr>
                            <td>&nbsp;</td>
                        </tr>
                        <tr>
                            <td height="22"><bean:message key="message.position.l_name"/></td>
                            <td height="22" colspan="5"><html:text property="name" size="80" name="<%=Constants.POSITION%>"/></td>
                        </tr>
                    </table>
                    <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_EDIT, PermissionUtil.PER_SYSTEM)) {%>
                    <p style="margin-top: 0; margin-bottom: 0" align="left"><html:image onclick="return savePosition();" src="images/but_save.gif" style="cursor: hand;"/><img border="0" src="images/blank.gif" width="2" height="17"><html:image property="Back" value="Back" src="images/but_back.gif" onclick="return loadPositionList();"/></p>
                    <%}%>
                </div></td></tr></table> 
    <html:hidden property="posId" name="<%=Constants.POSITION%>" />
</html:form>