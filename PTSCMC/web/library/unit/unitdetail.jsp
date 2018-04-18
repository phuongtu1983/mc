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
<%
            String uniId = MCUtil.getParameter("uniId", request);
%>

<h3><bean:message key="message.detailunit.title"/></h3>
<div id="errorMessageDiv"></div>
<html:form action="addUnit.do">
    <table border="0" width="100%" cellspacing="0" cellpadding="0" >
    <tr><td><div align="center">
        <table width="90%" border="0" cellspacing="0" cellpadding="0" style="border-width: 0px">
        <tr>
            <td>&nbsp;</td>
        </tr>
        <tr>
        <td height="35"><bean:message key="message.unit.unitEn"/></font>&nbsp;</td>
            <td height="35" colspan="5"><html:text property="unitEn" size="70" name="<%=Constants.UNIT%>"/></td>
        </tr>
        <tr>
        <td height="35"><bean:message key="message.unit.unitVn"/></font>&nbsp;</td>
            <td height="35" colspan="5"><html:text property="unitVn" size="70" name="<%=Constants.UNIT%>" /></td>
        </tr>                          
        </table>
        <p style="margin-top: 0; margin-bottom: 0" align="left">
        <%
            if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_EDIT, PermissionUtil.PER_LIBRARY_MATERIAL_UNIT)) {
        %>
        <html:image onclick="return saveUnit();" src="images/but_save.gif" style="cursor: hand;"/><img border="0" src="images/blank.gif" width="2" height="17">
        <%}%>
        <html:image property="Back" value="Back" src="images/but_back.gif" onclick="return loadUnitList();"/>
    </div></td></tr></table>
    <html:hidden property="uniId" name="<%=Constants.UNIT%>" />
</html:form>