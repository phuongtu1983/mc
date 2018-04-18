<%-- 
    Document   : groupmaterials
    Created on : Jul 10, 2010, 2:15:58 PM
    Author     : kngo
--%>

<%@ taglib prefix="display" uri="/tags/displaytag"%>
<%@ page import="com.venus.mc.bean.GroupMaterialBean"%>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<display:table style="width:100%" pagesize="15" requestURI="javascript:loadGroupMaterials({})" name="<%=Constants.GROUPMATERIAL_LIST%>" id="groupMaterial" class="its" >
    <display:setProperty name="paging.banner.items_name" value='nhóm'/>
    <display:setProperty name="paging.banner.item_name" value="nhóm"/>
    <display:column titleKey="message.del">
        <div align="center"><input type="checkbox" name="groId" value="<%=((GroupMaterialBean)pageContext.getAttribute("groupMaterial")).getGroId()%>"></div>
    </display:column>
    <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_VIEW, PermissionUtil.PER_LIBRARY_VENDOR)) {%>
        <display:column titleKey="message.groupmaterial.name" sortable="true" headerClass="sortable">
        <a href="#" name="groId" onclick="addGroupMaterial(<%=((GroupMaterialBean)pageContext.getAttribute("groupMaterial")).getGroId()%>)"><%=((GroupMaterialBean)pageContext.getAttribute("groupMaterial")).getName()%></a>
    </display:column>
    <display:column titleKey="message.groupmaterial.note" sortable="true" headerClass="sortable">
        <a href="#" name="groId" onclick="addGroupMaterial(<%=((GroupMaterialBean)pageContext.getAttribute("groupMaterial")).getGroId()%>)"><%=((GroupMaterialBean)pageContext.getAttribute("groupMaterial")).getNote()%></a>
    </display:column>
    <%}else{%>
    <display:column property="name" titleKey="message.groupmaterial.name" sortable="true" headerClass="sortable"/>	
    <display:column property="note" titleKey="message.groupmaterial.note" sortable="true" headerClass="sortable"/>	
    <%}%>
</display:table>