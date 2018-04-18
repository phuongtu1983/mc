<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="display" uri="/tags/displaytag"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.bean.OrganizationBean"%>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<display:table style="width:100%" pagesize="15" requestURI="javascript:loadOrganizations({})" name="<%=Constants.ORGANIZATION_LIST%>" id="organization" class="its" >
    <display:setProperty name="paging.banner.items_name" value='ph&#242;ng ban'/>
    <display:setProperty name="paging.banner.item_name" value="ph&#242;ng ban"/>
    <display:column titleKey="message.del">
        <div align="center"><input type="checkbox" name="orgId" value="<%=((OrganizationBean)pageContext.getAttribute("organization")).getOrgId()%>"></div>
    </display:column>
    <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_VIEW, PermissionUtil.PER_SYSTEM)) {%>
    <display:column titleKey="message.organization.name" sortable="true" headerClass="sortable">
        <a href="#" name="orgId" onclick="addOrganization(<%=((OrganizationBean)pageContext.getAttribute("organization")).getOrgId()%>)"><%=((OrganizationBean)pageContext.getAttribute("organization")).getName()%></a>
    </display:column>
    <%}else{%>
    <display:column property="name" titleKey="message.organization.name" sortable="true" headerClass="sortable"/>	
    <%}%>
    <display:column property="abbreviate" titleKey="message.organization.abbreviate" /> 
    <display:column property="statusDetail" titleKey="message.organization.status"/>
    <display:column property="kindDetail" titleKey="message.organization.kind" />    
</display:table>
