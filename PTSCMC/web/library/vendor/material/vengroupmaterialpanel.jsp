<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<form>
    <table>            
        <tr>
            <td>
                <h2><bean:write name="<%=Constants.VENDOR%>" property="name"/></h2>
            </td>
        </tr>
        <tr valign="middle">
            <td>
                <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_DELETE, PermissionUtil.PER_LIBRARY_VENDOR)) {%>
                <html:image src="images/ico_xoa.gif" onclick="return delVenGroupMaterials();"/>
                <%}%>
                <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_ADD, PermissionUtil.PER_LIBRARY_VENDOR)) {%>
                <logic:present name="<%=Constants.GROUP_MATERIAL_LIST%>">
                    <html:image src="images/ico_them.gif"  onclick="return saveVenGroupMaterial();"/>
                    <html:select property="venId" style="width: 600px;" styleId="groupMaterialSel" name="<%=Constants.VENDOR%>">
                        <html:options collection="<%=Constants.GROUP_MATERIAL_LIST%>" property="groId" labelProperty="name"/>
                    </html:select>
                </logic:present>
                <%}%>
            </td>
        </tr>
    </table>
    <html:hidden property="venId" styleId="venIdGroupMaterial" name="<%=Constants.VENDOR%>"/>
</form>
<form name='venGroupMaterialsForm' id='venGroupMaterialsForm'><div id='venGroupMaterialList'></div></form>