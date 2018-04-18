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
            String ematId = MCUtil.getParameter("ematId", request);
%>

<h3><bean:message key="message.detailematerial.title"/></h3>
<div id="errorMessageDiv"></div>
<form action="addEmaterial.do" name="addEmaterial">
    <table border="0" width="100%" cellspacing="0" cellpadding="0" >
        <tr><td><div align="center">
                    <table width="100%" border="0" cellspacing="0" cellpadding="0" style="border-width: 0px">
                        <tr>
                            <td>&nbsp;</td>
                        </tr>
                        <tr>
                            <td height="22"><bean:message key="message.ematerial.code"/></td>
                            <td height="22" colspan="5"><html:text property="code" size="80" name="<%=Constants.MATERIAL%>"/></td>
                        </tr>                        
                        <tr>
                            <td height="22"><bean:message key="message.ematerial.nameVn"/></td>
                            <td height="22" colspan="5"><html:textarea rows="3" cols="80"  property="nameVn" name="<%=Constants.MATERIAL%>" /></td>
                        </tr>
                        <tr>
                            <td height="22"><bean:message key="message.ematerial.nameEn"/></td>
                            <td height="22" colspan="5"><html:textarea rows="3" cols="80"  property="nameEn" name="<%=Constants.MATERIAL%>" /></td>
                        </tr>
                        <tr>
                            <td height="22"><bean:message key="message.ematerial.note"/></td>
                            <td height="22" colspan="5"><html:textarea rows="3" cols="80"  property="note" name="<%=Constants.MATERIAL%>" /></td>
                        </tr>
                        <tr>
                            <td height="22"><bean:message key="message.material.qc"/></td>
                            <td height="22" colspan="5"><html:textarea rows="3" cols="80"  property="qc" name="<%=Constants.MATERIAL%>" /></td>
                        </tr>
                        <tr>
                            <td height="22"><bean:message key="message.ematerial.uniId"/></td>
                            <td height="22" colspan="5">
                                <html:select  property="uniId" onchange="document.forms[0].uniIdEn.options.selectedIndex=this.options.selectedIndex" name="<%=Constants.MATERIAL%>">
                                    <html:options collection="<%=Constants.UNIT_LIST%>" property="value" labelProperty="label"/> </html:select>
                                </td>
                            </tr>
                            <tr>
                                <td height="22"><bean:message key="message.ematerial.uniIdEn"/></td>
                            <td height="22" colspan="5">
                                <html:select  property="uniIdEn" onchange="document.forms[0].uniId.options.selectedIndex=this.options.selectedIndex" name="<%=Constants.MATERIAL%>">
                                    <html:options collection="<%=Constants.UNIT_LISTEN%>" property="value" labelProperty="label"/> </html:select>
                                </td>
                            </tr>
                            <tr>
                                <td height="22"><bean:message key="message.ematerial.oriId"/></td>
                            <td height="22" colspan="5">
                                <html:select  property="oriId" onchange="document.forms[0].oriIdEn.options.selectedIndex=this.options.selectedIndex" name="<%=Constants.MATERIAL%>">
                                    <html:options collection="<%=Constants.ORIGIN_LIST%>" property="value" labelProperty="label"/> </html:select>
                                </td>
                            </tr>
                            <tr>
                                <td height="22"><bean:message key="message.ematerial.oriIdEn"/></td>
                            <td height="22" colspan="5">
                                <html:select  property="oriIdEn" onchange="document.forms[0].oriId.options.selectedIndex=this.options.selectedIndex" name="<%=Constants.MATERIAL%>">
                                    <html:options collection="<%=Constants.ORIGIN_LISTEN%>" property="value" labelProperty="label"/> </html:select>
                                </td>
                            </tr>
                            <tr>
                                <td height="22"><bean:message key="message.ematerial.kind"/></td>
                            <td height="22" colspan="5">

                                <html:select  property="kind" name="<%=Constants.MATERIAL%>">
                                    <html:options collection="<%=Constants.KIND_LIST%>" property="value" labelProperty="label"/> </html:select>
                                </td>
                            </tr>                            
                        </table>
                        <p style="margin-top: 0; margin-bottom: 0" align="left">
                        <%
                if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_EDIT, PermissionUtil.PER_LIBRARY_MATERIAL_OUT)) {
                        %>
                        <html:image onclick="return saveEmaterial();" src="images/but_save.gif" style="cursor: hand;"/><img border="0" src="images/blank.gif" width="2" height="17">
                        <%}%>
                        <html:image property="Back" value="Back" src="images/but_back.gif" onclick="return loadEmaterialList();"/>
                </div></td></tr></table>
                <html:hidden property="ematId" name="<%=Constants.MATERIAL%>" />
</form>