<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="display" uri="/tags/displaytag"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.bean.StoreBean"%>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<display:table pagesize="15" requestURIcontext="false" requestURI="listStore.do" name="<%=Constants.PROJECT_STORE_LIST%>" id="store" class="its" >
    <display:setProperty name="paging.banner.items_name" value='kho'/>
    <display:setProperty name="paging.banner.item_name" value="kho"/>
    <display:column titleKey="message.del">
        <div align="center"><input type="checkbox" name="stoId" value="<%=((StoreBean) pageContext.getAttribute("store")).getStoId()%>"></div>
    </display:column>
    <display:column property="proName" titleKey="message.store.proId" />
    <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_VIEW, PermissionUtil.PER_LIBRARY_PROJECT)) {%>
    <display:column titleKey="message.store.name" sortable="true" headerClass="sortable">
        <a href="#" name="stoId" onclick="addProjectStore(<%=((StoreBean) pageContext.getAttribute("store")).getStoId()%>)"><%=((StoreBean) pageContext.getAttribute("store")).getName()%></a>
    </display:column>
    <%}else{%>
    <display:column property="name" titleKey="message.store.name" sortable="true" headerClass="sortable"/>	
    <%}%>
    <display:column property="physicalAdd" titleKey="message.store.physicalAdd" />	
    <display:column property="kindDetail" titleKey="message.store.kind" />
</display:table>
    