<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib prefix="display" uri="/tags/displaytag" %>
<%@ page import="com.venus.mc.util.MCUtil"%>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.venus.mc.util.PermissionUtil"%>

<h3><bean:message key="message.detailcolorcode.title"/></h3>
<div id="errorMessageDiv"></div>
<html:form action="addColorCode.do">
    <table border="0" width="100%" cellspacing="0" cellpadding="0" >
        <tr><td><div align="center">
                    <table width="90%" border="0" cellspacing="0" cellpadding="0" style="border-width: 0px">
                        <tr>
                            <td>&nbsp;</td>
                        </tr>
                        <tr>
                            <td height="22"><bean:message key="message.colorcode.colorCode"/></font>&nbsp;</td>
                            <td height="22" colspan="5"><html:text property="colorCode" size="80" name="<%=Constants.COLORCODE%>"/></td>
                        </tr>
                        <tr>
                            <td height="22"><bean:message key="message.colorcode.timeApplication"/></font>&nbsp;</td>
                            <td height="22" colspan="5"><html:text property="timeApplication" size="80" name="<%=Constants.COLORCODE%>" /></td>
                        </tr>
                        <tr>
                            <td height="22"><bean:message key="message.colorcode.startDate"/></font>&nbsp;</td>
                            <td height="22" colspan="5"><html:text property="startDate" size="80" styleId="startDate" onclick="javascript: showCalendar('startDate')" name="<%=Constants.COLORCODE%>" /></td>
                        </tr>
                        <tr>
                            <td height="22"><bean:message key="message.colorcode.endDate"/></font>&nbsp;</td>
                            <td height="22" colspan="5"><html:text property="endDate" size="80" styleId="endDate" onclick="javascript: showCalendar('endDate')" name="<%=Constants.COLORCODE%>" /></td>
                        </tr>
                    </table>
                    <p style="margin-top: 0; margin-bottom: 0" align="left">
                        <%
            if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_EDIT, PermissionUtil.PER_EQUIPMENT_COLOR)) {
                        %>
                        <html:image onclick="return saveColorCode();" src="images/but_save.gif" style="cursor: hand;"/><img border="0" src="images/blank.gif" width="2" height="17">
                        <%}%>
                        <html:image property="Back" value="Back" src="images/but_back.gif" onclick="return loadColorCodeList();"/>
                </div></td></tr></table>
    <html:hidden property="ccId" name="<%=Constants.COLORCODE%>" />
</html:form>