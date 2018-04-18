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

<h3><bean:message key="message.detailspe.title"/></h3>
<form name="spePopupDetailForm">
    <html:hidden property="spe" value="0" name="<%=Constants.SPE%>" />
    <table border="0" width="100%" cellspacing="0" cellpadding="0" >
        <tr><td><div align="center">
                    <table width="100%" border="0" cellspacing="0" cellpadding="0" style="border-width: 0px">
                        <tr>
                            <td height="22"><bean:message key="message.material.sign"/></td>
                            <td height="22" colspan="5"><html:text property="sign" maxlength="3" size="5" name="<%=Constants.SPE%>" /></td>
                        </tr>
                        <tr>
                            <td height="22"><bean:message key="message.material.note"/></td>
                            <td height="22" colspan="5"><html:text property="note" size="80" name="<%=Constants.SPE%>" /></td>
                        </tr>
                    </table>
                    <%
            if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_EDIT, PermissionUtil.PER_PURCHASING_REQUEST_MATERIALNOTCODE)) {
                    %>
                    <html:image onclick="return addSpePopupForm();" src="images/but_save.gif" style="cursor: hand;"/><img border="0" src="images/blank.gif" width="2" height="17">
                    <%}%>
                    <html:image property="Back" value="Back" src="images/but_back.gif" onclick="return hideWindows();"/>
                </div></td></tr></table>
    <input type="hidden" name="kind" value =""/>
    <input type="hidden" name="speId" value =""/>
    <input type="hidden" name="speId0" value =""/>
</form>
