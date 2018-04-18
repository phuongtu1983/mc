<%@page import="com.venus.mc.util.PermissionUtil"%>
<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>

<html:select  onchange="return spe4Changed(this);" property="spe4Id" name="<%=Constants.SPE%>">
    <html:options collection="<%=Constants.SPE4_LIST%>" property="value" labelProperty="label"/>
</html:select>
<%
    if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_EDIT, PermissionUtil.PER_LIBRARY_MATERIAL_COMPANY)) {
%>
<html:image src="images/add.png" title="Th&#234;m m&#7899;i" onclick="return addSpe4();"/>
<html:image src="images/edit.png" title="Ch&#7881;nh s&#7917;a" onclick="return editSpe4();"/>
<html:image src="images/del.png" title="X&#243;a" onclick="return delSpe4();"/>
<%}%>