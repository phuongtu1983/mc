<%@page import="com.venus.mc.util.PermissionUtil"%>
<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>

<html:select  onchange="return spe5Changed(this);" property="spe5Id" name="<%=Constants.SPE%>">
    <html:options collection="<%=Constants.SPE5_LIST%>" property="value" labelProperty="label"/>
</html:select>
<%
    if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_EDIT, PermissionUtil.PER_PURCHASING_REQUEST_MATERIALNOTCODE)) {
%>
<html:image src="images/add.png" title="Th&#234;m m&#7899;i" onclick="return addSpe5();"/>
<html:image src="images/edit.png" title="Ch&#7881;nh s&#7917;a" onclick="return editSpe5();"/>
<html:image src="images/del.png" title="X&#243;a" onclick="return delSpe5();"/>
<%}%>