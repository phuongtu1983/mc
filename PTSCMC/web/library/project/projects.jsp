<%@ taglib prefix="display" uri="/tags/displaytag"%>
<%@ page import="com.venus.mc.bean.ProjectBean"%>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<display:table style="width:100%" pagesize="15" requestURI="javascript:loadProjects({})" name="<%=Constants.PROJECT_LIST%>" id="project" class="its" >
    <display:setProperty name="paging.banner.items_name" value='D&#7921; &#193;n'/>
    <display:setProperty name="paging.banner.item_name" value="D&#7921; &#193;n"/>
    <display:column titleKey="message.del">
        <div align="center"><input type="checkbox" name="proId" value="<%=((ProjectBean)pageContext.getAttribute("project")).getProId()%>"></div>
    </display:column>
    <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_VIEW, PermissionUtil.PER_LIBRARY_PROJECT)) {%>
    <display:column titleKey="message.project.name" sortable="true" headerClass="sortable">
        <a href="#" name="proId" onclick="projectForm(<%=((ProjectBean)pageContext.getAttribute("project")).getProId()%>)"><%=((ProjectBean)pageContext.getAttribute("project")).getName()%></a>
    </display:column>
    <%}else{%>
    <display:column property="name" titleKey="message.project.name" sortable="true" headerClass="sortable"/>	
    <%}%>
    <display:column property="startDate" titleKey="message.project.startDate" sortable="true" headerClass="sortable"/>	
    <display:column property="endDate" titleKey="message.project.endDate" sortable="true" headerClass="sortable"/>
    <display:column property="investorsName" titleKey="message.project.investors_name" sortable="true" headerClass="sortable"/>    
    <display:column property="comments" titleKey="message.project.comments" sortable="true" headerClass="sortable"/>    
    <display:column property="statusDetail" titleKey="message.project.status" sortable="true" headerClass="sortable"/>
</display:table>
