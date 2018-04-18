<%@ taglib prefix="display" uri="/tags/displaytag"%>
<%@ page import="com.venus.mc.bean.OriginBean"%>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<display:table style="width:100%" pagesize="15" requestURI="javascript:loadOrigins({})" name="<%=Constants.ORIGIN_LIST%>" id="origin" class="its" >
    <display:setProperty name="paging.banner.items_name" value='xu&#7845;t x&#7913;'/>
    <display:setProperty name="paging.banner.item_name" value="xu&#7845;t x&#7913;"/>
    <display:column titleKey="message.del">
        <div align="center"><input type="checkbox" name="oriId" value="<%=((OriginBean)pageContext.getAttribute("origin")).getOriId()%>"></div>
    </display:column>
    <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_VIEW, PermissionUtil.PER_LIBRARY_MATERIAL_ORIGIN)) {%>
    <display:column titleKey="message.origin.nameEn" sortable="true" headerClass="sortable">
        <a href="#" name="oriId" onclick="addOrigin(<%=((OriginBean)pageContext.getAttribute("origin")).getOriId()%>)"><%=((OriginBean)pageContext.getAttribute("origin")).getNameEn()%></a>
    </display:column>
    <display:column titleKey="message.origin.nameVn" sortable="true" headerClass="sortable">
        <a href="#" name="oriId" onclick="addOrigin(<%=((OriginBean)pageContext.getAttribute("origin")).getOriId()%>)"><%=((OriginBean)pageContext.getAttribute("origin")).getNameVn()%></a>
    </display:column>
    <display:column titleKey="message.origin.note" sortable="true" headerClass="sortable">
        <a href="#" name="oriId" onclick="addOrigin(<%=((OriginBean)pageContext.getAttribute("origin")).getOriId()%>)"><%=((OriginBean)pageContext.getAttribute("origin")).getNote()%></a>
    </display:column>
    <%}else{%>
    <display:column property="nameEn" titleKey="message.origin.nameEn" sortable="true" headerClass="sortable"/>	
    <display:column property="nameVn" titleKey="message.origin.nameVn" sortable="true" headerClass="sortable"/>	
    <display:column property="note" titleKey="message.origin.note" sortable="true" headerClass="sortable"/>	
    <%}%>
</display:table>

