<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="display" uri="/tags/displaytag"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.bean.WebBean"%>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<display:table style="width:100%" pagesize="15" requestURI="javascript:loadWebs({})" name="<%=Constants.WEB_LIST%>" id="web" class="its">
    <display:setProperty name="paging.banner.items_name" value='Website'/>
    <display:setProperty name="paging.banner.item_name" value="Website"/>
    <display:column titleKey="message.del">
        <div align="center"><input type="checkbox" name="webId" value="<%=((WebBean) pageContext.getAttribute("web")).getWebId()%>"></div>
    </display:column>
    <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_VIEW, PermissionUtil.PER_LIBRARY_VENDOR)) {%>
    <display:column titleKey="message.web.address" sortable="true" headerClass="sortable">
        <a href="#" name="webId" onclick="addWeb(<%=((WebBean) pageContext.getAttribute("web")).getWebId()%>)"><%=((WebBean) pageContext.getAttribute("web")).getAddress()%></a>
    </display:column>
    <%}else{%>
    <display:column property="address" titleKey="message.web.address" sortable="true" headerClass="sortable"/>	
    <%}%>
    <display:column property="content" titleKey="message.web.content" />
    <display:column property="comment" titleKey="message.web.comment" />
    <display:column property="evaluation" titleKey="message.web.evaluation" />
</display:table>