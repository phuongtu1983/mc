<%@page import="com.venus.mc.util.PermissionUtil"%>
<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.MCUtil"%>
<%@ page import="com.venus.mc.util.Constants"%>
<%
    String cerId = MCUtil.getParameter("cerId", request);
%>
<h3><bean:message key="message.detailcertificate.title"/></h3>
<div id="errorMessageDiv"></div>
<html:form action="addCertificate.do">
    <table border="0" width="100%" cellspacing="0" cellpadding="0" >
        <tr><td><div align="center">
                    <table width="100%" border="0" cellspacing="0" cellpadding="0" style="border-width: 0px">
                        <tr>
                            <td>&nbsp;</td>
                        </tr>
                        <tr>
                            <td height="22"><bean:message key="message.certificate.cerName"/></td>
                            <td height="22" colspan="5"><html:textarea property="cerName" cols="100" rows="5" name="<%=Constants.CER%>"/></td>
                        </tr>
                    </table>
                    <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_EDIT, PermissionUtil.PER_LIBRARY_CERTIFICATE)) {%>
                    <html:image align="left" onclick="return saveCertificate();" src="images/but_save.gif" style="cursor: hand;"/><img border="0" src="images/blank.gif" width="2" height="17">
                    <%}%>
                    <html:image align="left" property="Back" value="Back" src="images/but_back.gif" onclick="return loadCertificateList();"/>
                </div></td></tr></table> 
    <html:hidden property="cerId" name="<%=Constants.CER%>" />
</html:form>