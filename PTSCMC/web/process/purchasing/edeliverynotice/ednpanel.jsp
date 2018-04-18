<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<%@ page import="com.venus.mc.util.MCUtil"%>
<%@ page import="com.venus.mc.util.Constants"%>
<%
        //    String kind = MCUtil.getParameter("kind", request);
                String kind = "1";
%>
<h3>
    <bean:message key="message.ednlist.title"/>
</h3>
<div id="errorMessageDiv"></div>
<html:form action="searchEdn.do">
    <table>
        <tr>
            <td>                
                <html:image src="images/blank.png" onclick="return searchEdn();"/>
                <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_DELETE, PermissionUtil.PER_PURCHASING_DELIVERYNOTICE_PROJECT)) {%>
                <html:image src="images/ico_xoa.gif" onclick="return delEdns();"/>
                <%}%>
                <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_ADD, PermissionUtil.PER_PURCHASING_DELIVERYNOTICE_PROJECT)) {%>
                <html:image src="images/ico_them.gif"  onclick="return ednForm();"/>
                <%}%>
            </td>
            <td><div>
                    <html:select property="searchid">
                        <html:option key="message.all" value='0'/>
                        <html:option key="message.dn.dnNumber" value='1'/>
                        <html:option key="message.dn.deliveryPlace" value='2'/>
                    </html:select>
                    <html:text property="searchvalue" />                    
                    <html:submit onclick="return searchEdn();"><bean:message key="message.search"/></html:submit>
                    <html:submit onclick="return searchAdvEdnForm();"><bean:message key="message.detailSearch"/></html:submit>
            </div></td>
        </tr>
    </table>
    
</html:form>
<form name='ednsForm' id='ednsForm'><div id='ednList'></div><input type="hidden" name="kind" value="<%=kind%>"/></form>