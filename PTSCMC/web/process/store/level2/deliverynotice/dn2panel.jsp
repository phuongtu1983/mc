<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<%@ page import="com.venus.mc.util.MCUtil"%>
<%@ page import="com.venus.mc.util.Constants"%>
<%
    String kind = MCUtil.getParameter("kind", request);
%>
<h3>
    <logic:equal parameter="kind" value="2">
        <bean:message key="message.dn2list.title"/>
    </logic:equal>
    <logic:equal parameter="kind" value="3">
        <bean:message key="message.dn3list.title"/>
    </logic:equal>
    <logic:equal parameter="kind" value="4">
        <bean:message key="message.dn4list.title"/>
    </logic:equal>    
</h3>
<div id="errorMessageDiv"></div>
<html:form action="searchDn2.do">
    <table>
        <tr>
            <td>                
                <html:image src="images/blank.png" onclick="return searchDn2();"/>
                <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_DELETE, PermissionUtil.PER_STOCK_YCKT_STORE2)) {%>
                <html:image src="images/ico_xoa.gif" onclick="return delDn2s();"/>
                <%}%>
                <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_ADD, PermissionUtil.PER_STOCK_YCKT_STORE2)) {%>
                <html:image src="images/ico_them.gif"  onclick="return dn2Form();"/>
                <%}%>
            </td>
            <td><div>
                    <html:select property="searchid">
                        <html:option key="message.all" value='0'/>
                        <html:option key="message.dn2.dn2Number" value='1'/>
                        <html:option key="message.dn2.proId" value='2'/>
                    </html:select>
                    <html:text property="searchvalue" />
                    <logic:equal parameter="kind" value="2">
                        <html:submit onclick="return searchDn2();"><bean:message key="message.search"/></html:submit>
                        <html:submit onclick="return searchAdvDn2Form();"><bean:message key="message.detailSearch"/></html:submit>
                    </logic:equal>
                    <logic:equal parameter="kind" value="4">
                        <html:submit onclick="return searchDn4();"><bean:message key="message.search"/></html:submit>
                        <html:submit onclick="return searchAdvDn4Form();"><bean:message key="message.detailSearch"/></html:submit>
                    </logic:equal>
                </div></td>
        </tr>
    </table>

</html:form>
<form name='dn2sForm' id='dn2sForm'><div id='dn2List'></div><input type="hidden" name="kind" value="<%=kind%>"/></form>