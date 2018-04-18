<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ page import="com.venus.mc.util.MCUtil"%>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<div id="vendorFormTitle"><h3><bean:message key="message.vendoradd.title"/>/<bean:message key="message.add.s"/></h3></div>
<div id="vendorFormError"></div>
<form name='vendorForm'>
    <table style="width:100%">
        <tr>
            <td><div align="right"><bean:message key="message.vendor.l_name"/></div></td>
            <td colspan="3"><html:text property="name" size="100" name="<%=Constants.VENDOR%>"/></td>
        </tr>
        <tr>
            <td><div align="right"><bean:message key="message.vendor.address"/></div></td>
            <td colspan="3"><html:text property="address" size="100" name="<%=Constants.VENDOR%>"/></td>
        </tr>  
        <tr>
            <td><div align="right"><bean:message key="message.vendor.phone"/></div></td>
            <td><html:text property="phone" size="33" name="<%=Constants.VENDOR%>" /></td>
            <td><div align="right"><bean:message key="message.vendor.fax"/></div></td>
            <td><html:text property="fax" size="32" name="<%=Constants.VENDOR%>" /></td>            
        </tr>  
        <tr>
            <td><div align="right"><bean:message key="message.vendor.presenter"/></div></td>
            <td ><html:text property="presenter" size="33" name="<%=Constants.VENDOR%>" /></td>
            <td><div align="right"><bean:message key="message.vendor.pospresenter"/></div></td>
            <td ><html:text property="pospresenter" size="32" name="<%=Constants.VENDOR%>" /></td>
        </tr>
        <tr>
            <td><div align="right"><bean:message key="message.vendor.phonePresenter"/></div></td>
            <td colspan="3"><html:text property="phonePresenter" size="100" name="<%=Constants.VENDOR%>"/></td>
        </tr>
        <tr>
            <td><div align="right"><bean:message key="message.vendor.email"/></div></td>
            <td ><html:text property="email" size="33"  name="<%=Constants.VENDOR%>"/></td>
            <td><div align="right"><bean:message key="message.vendor.web"/></div></td>
            <td ><html:text property="web" size="32" name="<%=Constants.VENDOR%>" /></td>
        </tr>        
        <tr>
            <td><div align="right"><bean:message key="message.vendor.field"/></div></td>
            <td colspan="3"><html:text property="field" size="100" name="<%=Constants.VENDOR%>"/></td>
        </tr>
        <tr>
            <td><div align="right"><bean:message key="message.vendor.license"/></div></td>
            <td colspan="3"><html:text property="license" size="100" name="<%=Constants.VENDOR%>" /></td>
        </tr>
        <tr>
            <td><div align="right"><bean:message key="message.vendor.charterCapital"/></div></td>
            <td colspan="3"><html:text property="charterCapital" size="100" name="<%=Constants.VENDOR%>" /></td>
        </tr>
        <tr>
            <td><div align="right"><bean:message key="message.vendor.status"/></div></td>
            <td colspan="3">
                <html:select property="status" name="<%=Constants.VENDOR%>">
                    <html:options collection="<%=Constants.VENDOR_STATUS_LIST%>" property="value" labelProperty="label"/>
                </html:select>
            </td>
        </tr>
        <tr>
            <td><div align="right"><bean:message key="message.vendor.kind"/></div></td>
            <td colspan="3">
                <html:select property="kind" name="<%=Constants.VENDOR%>">
                    <html:options collection="<%=Constants.VENDOR_KIND_LIST%>" property="value" labelProperty="label"/>
                </html:select>
            </td>
        </tr>
        <tr>
            <td><div align="right"><bean:message key="message.dn.orgHandle"/></td>
            <td><html:select property="orgHandle" name="<%=Constants.VENDOR%>" >
                    <html:options collection="<%=Constants.ORGANIZATION_LIST%>" property="value" labelProperty="label"/>
                </html:select>
            </td>
        </tr>
        <tr>
            <td><div align="right"><bean:message key="message.note"/></div></td>
            <td colspan="3"><html:textarea property="comments" cols="80" rows="3" name="<%=Constants.VENDOR%>" /></td>
        </tr>
        <logic:greaterThan name="<%=Constants.VENDOR%>" value="0" property="venId">
            <tr>
                <td colspan="4"><div id='divVendorAttachFileSystem' ><img src="img/indicator.gif"/></div></td>
            </tr>
        </logic:greaterThan>
    </table>
    <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_EDIT, PermissionUtil.PER_LIBRARY_VENDOR)) {%>
    <html:image onclick="return saveVendor();" src="images/but_save.gif"/><img src="images/blank.gif" width="2" height="17">
    <%}%>
    <html:image property="Back" value="Back" src="images/but_back.gif" onclick="return loadVendorList();"/>
    <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_EDIT, PermissionUtil.PER_LIBRARY_VENDOR)) {%>
    <logic:greaterThan value="0" name="<%=Constants.VENDOR%>" property="venId">
        <div align="right"><input value="Lưu thay đổi điều chỉnh HĐ" title="Lưu Thay đổi điều chỉnh HĐ" onclick="return saveVendorContract();" type="button" /></div>
        </logic:greaterThan>
        <%}%>
        <html:hidden property="venId" name="<%=Constants.VENDOR%>"/>
</form>