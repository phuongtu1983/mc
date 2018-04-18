<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<h3><bean:message key="message.listcontractFollow.title"/></h3>
<div id="errorMessageDiv"></div>
<html:form action="searchContractFollow.do">
    <table>
        <tr>
            <td>
                <html:image src="images/blank.png" onclick="return searchContractFollow();"/>
                <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_DELETE, PermissionUtil.PER_PURCHASING_CONTRACT_FOLLOW)) {%>
                <html:image src="images/ico_xoa.gif" onclick="return delContractFollows();"/>
                <%}%>
                <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_ADD, PermissionUtil.PER_PURCHASING_CONTRACT_FOLLOW)) {%>
                <html:image src="images/ico_them.gif"  onclick="return addContractFollow();"/>
                <%}%>
            </td>
            <td><div align="right">
                    <html:select property="searchid">
                        <html:option key="message.all" value='0'/>
                        <html:option key="message.contractFollow.folNumber" value='1'/>
                        <html:option key="message.contractFollow.conId" value='2'/>
                        <html:option key="message.contractFollow.createdDate" value='3'/>
                    </html:select>
                    <html:text property="searchvalue" />
                    <html:submit onclick="return searchContractFollow();"><bean:message key="message.search"/></html:submit>
                    <html:submit onclick="return searchAdvContractFollowForm();"><bean:message key="message.detailSearch"/></html:submit>
            </div></td>
        </tr>
    </table>
</html:form>
<form name='contractFollowForm' id='contractFollowsForm'><div id='contractFollowList'></div></form>