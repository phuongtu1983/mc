<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="display" uri="/tags/displaytag"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.bean.IncotermBean"%>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<display:table style="width:100%" pagesize="15" requestURI="javascript:loadIncoterms({})" name="<%=Constants.INC_LIST%>" id="incoterm" class="its" >
    <display:setProperty name="paging.banner.items_name"><bean:message key='message.incoterm'/></display:setProperty>
    <display:setProperty name="paging.banner.item_name"><bean:message key='message.incoterm'/></display:setProperty>
    <display:column titleKey="message.del">
        <div align="center"><input type="checkbox" name="incId" value="<%=((IncotermBean) pageContext.getAttribute("incoterm")).getIncId()%>"></div>
        </display:column>
        <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_VIEW, PermissionUtil.PER_LIBRARY_INCOTERM)) {%>
        <display:column titleKey="message.incoterm.incName" sortable="true" headerClass="sortable">
        <a href="#" name="incId" onclick="addIncoterm(<%=((IncotermBean) pageContext.getAttribute("incoterm")).getIncId()%>)"><%=((IncotermBean) pageContext.getAttribute("incoterm")).getIncName()%></a>
    </display:column>
    <display:column titleKey="message.incoterm.comment" sortable="true" headerClass="sortable">
        <a href="#" name="incId" onclick="addIncoterm(<%=((IncotermBean) pageContext.getAttribute("incoterm")).getIncId()%>)"><%=((IncotermBean) pageContext.getAttribute("incoterm")).getComment()%></a>
    </display:column>
    <%}else{%>
    <display:column property="incName" titleKey="message.incoterm.incName" sortable="true" headerClass="sortable"/>
    <display:column property="comment" titleKey="message.incoterm.comment" sortable="true" headerClass="sortable"/>
    <%}%>
</display:table>