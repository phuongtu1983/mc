<%@ taglib prefix="display" uri="/tags/displaytag"%>
<%@ page import="com.venus.mc.bean.PermissionBean"%>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<display:table style="width:100%" pagesize="15" requestURI="javascript:loadPermissionList({})" name="<%=Constants.PERMISSION_LIST%>" id="permission" class="its" defaultsort="1">
    <display:setProperty name="paging.banner.items_name"><bean:message key='message.permission'/></display:setProperty>
    <display:setProperty name="paging.banner.item_name"><bean:message key='message.permission'/></display:setProperty>
    <display:column titleKey="message.del">
        <input type="checkbox" name="perId" value="<%=((PermissionBean)pageContext.getAttribute("permission")).getPerId()%>">
    </display:column>
    <display:column titleKey="message.permission.name" sortable="true" headerClass="sortable">
        <a href="#" onclick="permissionForm(<%=((PermissionBean)pageContext.getAttribute("permission")).getPerId()%>)"><%=((PermissionBean)pageContext.getAttribute("permission")).getName()%></a>
    </display:column>
</display:table>
