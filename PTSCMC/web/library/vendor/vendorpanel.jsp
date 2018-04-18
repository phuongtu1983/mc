<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<h3><bean:message key="message.vendoradd.title"/>/<bean:message key="message.list.s"/></h3>
<div id="errorMessageDiv"></div>
<html:form action="searchVenContact.do">
    <table>
        <tr>
            <td>
                <html:image src="images/blank.png" onclick="return searchVendor();"/>
                <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_DELETE, PermissionUtil.PER_LIBRARY_VENDOR)) {%>
                <html:image src="images/ico_xoa.gif" onclick="return delVendors();"/>
                <%}%>
                <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_ADD, PermissionUtil.PER_LIBRARY_VENDOR)) {%>
                <html:image src="images/ico_them.gif"  onclick="return vendorForm();"/>
                <%}%>
            </td>
            <td><div>
                    <html:select property="searchid">
                        <html:option key="message.all" value='0'/>
                        <html:option key="message.vendor.l_name" value='1'/>
                        <html:option key="message.vendor.phone" value='2'/>
                        <html:option key="message.vendor.address" value='3'/>
                        <html:option key="message.vendor.charterCapital" value='4'/>
                        <html:option key="message.dn.orgHandle" value='5'/>
                        <html:option key="message.vendor.field" value='6'/>
                        <html:option key="message.vendor.groupMaterial.name" value='7'/>
                        <html:option key="message.material" value='8'/>                        
                    </html:select>
                    <html:text property="searchvalue" size="40"/>
                    <html:submit onclick="return searchVendor();"><bean:message key="message.search"/></html:submit>
                    <html:submit onclick="return searchAdvVendorForm();"><bean:message key="message.detailSearch"/></html:submit>
                    <img onclick="return exportVendor();" src="images/but_print.gif"/>
            </div></td>
        </tr>
    </table>
</html:form>
<form name='vendorsForm' id='vendorsForm'><div id='vendorList'><div align="center"><img src="img/indicator.gif"/></div></div></form>
