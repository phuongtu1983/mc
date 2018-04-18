<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.MCUtil"%>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<%
    String stoId = MCUtil.getParameter("stoId", request);
%>
<h3><bean:message key="message.detailstore.title"/></h3>
<div id="errorMessageDiv"></div>
<html:form action="addProjectStore.do">
    <table border="0" width="81%" cellspacing="0" cellpadding="0">
        <tr><td><div align="center">
                    <table width="100%" border="0" cellspacing="0" cellpadding="0" style="border-width: 0px">
                        <tr>
                            <td height="22"><bean:message key="message.store.l_name"/></td>
                            <td height="22" colspan="5"><html:text property="name" size="80" name="<%=Constants.PROJECT_STORE%>"/></td>
                        </tr>
                        <tr>
                            <td height="22"><bean:message key="message.store.physicalAdd"/></td>
                            <td height="22" colspan="5"><html:text property="physicalAdd" size="80" name="<%=Constants.PROJECT_STORE%>"/></td>
                        </tr>
                        <tr>
                            <td height="22"><bean:message key="message.store.kind"/></td>
                            <td height="22" colspan="5">
                                <html:select property="kind" name="<%=Constants.PROJECT_STORE%>">
                                    <html:options collection="<%=Constants.STORE_KIND_LIST%>" property="value" labelProperty="label"/>
                                </html:select>
                            </td>
                        </tr>  
                        <tr>
                            <td height="22"><bean:message key="message.store.comments"/></td>
                            <td height="22"><html:text property="comments" size="20" name="<%=Constants.PROJECT_STORE%>"/></td>
                        </tr>  
                    </table>
                    <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_EDIT, PermissionUtil.PER_LIBRARY_PROJECT)) {%>
                    <html:image onclick="return saveProjectStore();" src="images/but_save.gif" style="cursor: hand;"/><img border="0" src="images/blank.gif" width="2" height="17"><html:image property="Back" value="Back" src="images/but_back.gif" onclick="return loadProjectStoreList();"/>
                    <%}%>
                </div></td></tr></table> 
    <html:hidden property="proId" name="<%=Constants.PROJECT_STORE%>"/>
    <html:hidden property="stoId" name="<%=Constants.PROJECT_STORE%>"/>
</html:form>