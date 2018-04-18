<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="display" uri="/tags/displaytag"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.bean.PositionBean"%>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<display:table pagesize="15" requestURI="javascript:loadPositions({})" name="<%=Constants.POSITION_LIST%>" id="position" class="its" >
    <display:setProperty name="paging.banner.items_name" value='ch&#7913;c v&#7909;'/>
    <display:setProperty name="paging.banner.item_name" value="ch&#7913;c v&#7909;"/>
    <display:column style="width:40px" titleKey="message.del">
        <div align="center"><input type="checkbox" name="posId" value="<%=((PositionBean)pageContext.getAttribute("position")).getPosId()%>"></div>
    </display:column>
    <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_VIEW, PermissionUtil.PER_SYSTEM)) {%>
    <display:column titleKey="message.position.name" sortable="true" headerClass="sortable">
        <a href="#" name="posId" onclick="addPosition(<%=((PositionBean)pageContext.getAttribute("position")).getPosId()%>)"><%=((PositionBean)pageContext.getAttribute("position")).getName()%></a>
    </display:column>
    <%}else{%>
    <display:column property="name" titleKey="message.position.name" sortable="true" headerClass="sortable"/>	
    <%}%>
</display:table>
