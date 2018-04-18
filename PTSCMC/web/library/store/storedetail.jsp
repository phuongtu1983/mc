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
<html:form action="addStore.do">
    <table border="0" width="100%" cellspacing="0" cellpadding="0" >
        <tr><td><div align="center">
                    <table width="100%" border="0" cellspacing="0" cellpadding="0" style="border-width: 0px">
                        <tr>
                            <td>&nbsp;</td>
                        </tr>
                        <tr>
                            <td height="22"><bean:message key="message.store.proId"/></td>
                            <td height="22" colspan="5">
                                <html:select property="proId" name="<%=Constants.STORE%>">
                                    <html:options collection="<%=Constants.PROJECT_LIST%>" property="proId" labelProperty="name"/>
                                </html:select>
                            </td>
                        </tr>
                        <tr>
                            <td height="22"><bean:message key="message.store.l_name"/></td>
                            <td height="22" colspan="5"><html:text property="name" size="80" name="<%=Constants.STORE%>"/></td>
                        </tr>
                        <tr>
                            <td height="22"><bean:message key="message.store.physicalAdd"/></td>
                            <td height="22" colspan="5"><html:text property="physicalAdd" size="80" name="<%=Constants.STORE%>" /></td>
                        </tr>
                        <tr>
                            <td height="22"><bean:message key="message.store.kind"/></td>
                            <td height="22" colspan="5">
                                <html:select property="kind" name="<%=Constants.STORE%>">
                                    <html:options collection="<%=Constants.STORE_KIND_LIST%>" property="value" labelProperty="label"/>
                                </html:select>
                            </td>
                        </tr>  
                        <tr>
                            <td height="22"><bean:message key="message.store.comments"/></td>
                            <td height="22"><html:textarea property="comments" cols="80" rows="2" name="<%=Constants.STORE%>" /></td>
                        </tr>
                        <tr>                        
                            <td height="22"><bean:message key="message.store.empId"/></td>
                            <td height="22" colspan="5">
                                <html:select property="empId" name="<%=Constants.STORE%>">
                                    <html:options collection="<%=Constants.EMPLOYEE_LIST%>" property="empId" labelProperty="fullname"/>
                                </html:select>
                            </td>
                        </tr>
                        <tr>
                            <td height="22"><bean:message key="message.store.empTel"/></td>
                            <td height="22"><html:text property="empTel" size="20" name="<%=Constants.STORE%>" /></td>
                        </tr>  

                    </table>
                    <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_EDIT, PermissionUtil.PER_LIBRARY_STORE)) {%>
                    <html:image align="left" onclick="return saveStore();" src="images/but_save.gif" style="cursor: hand;"/><img border="0" src="images/blank.gif" width="2" height="17">
                    <%}%>
                    <html:image align="left" property="Back" value="Back" src="images/but_back.gif" onclick="return loadStoreList();"/>
                </div></td></tr></table> 
    <html:hidden property="stoId" name="<%=Constants.STORE%>" />
</html:form>