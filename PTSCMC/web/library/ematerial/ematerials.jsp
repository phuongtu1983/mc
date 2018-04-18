<%@ taglib prefix="display" uri="/tags/displaytag"%>
<%@ page import="com.venus.mc.bean.EmaterialBean"%>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<display:table style="width:100%" pagesize="15" requestURI="javascript:loadEmaterials({})" name="<%=Constants.MATERIAL_LIST%>" id="ematerial" class="its" >
    <display:setProperty name="paging.banner.items_name" value='VTTB'/>
    <display:setProperty name="paging.banner.item_name" value="VTTB"/>
    <display:column titleKey="message.del">
        <div align="center"><input type="checkbox" name="ematId" value="<%=((EmaterialBean)pageContext.getAttribute("ematerial")).getEmatId()%>"></div>
    </display:column>
    <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_VIEW, PermissionUtil.PER_LIBRARY_MATERIAL_OUT)) {%>
    <display:column titleKey="message.ematerial.code" sortable="true" headerClass="sortable">
        <a href="#" name="ematId" onclick="addEmaterial(<%=((EmaterialBean)pageContext.getAttribute("ematerial")).getEmatId()%>)"><%=((EmaterialBean)pageContext.getAttribute("ematerial")).getCode()%></a>
    </display:column>
    <display:column titleKey="message.ematerial.nameVn" sortable="true" headerClass="sortable">
        <a href="#" name="ematId" onclick="addEmaterial(<%=((EmaterialBean)pageContext.getAttribute("ematerial")).getEmatId()%>)"><%=((EmaterialBean)pageContext.getAttribute("ematerial")).getNameVn()%></a>
    </display:column> 
    <display:column titleKey="message.ematerial.nameEn" sortable="true" headerClass="sortable">
        <a href="#" name="ematId" onclick="addEmaterial(<%=((EmaterialBean)pageContext.getAttribute("ematerial")).getEmatId()%>)"><%=((EmaterialBean)pageContext.getAttribute("ematerial")).getNameEn()%></a>
    </display:column> 
    <display:column titleKey="message.ematerial.kind" sortable="true" headerClass="sortable">
        <a href="#" name="ematId" onclick="addEmaterial(<%=((EmaterialBean)pageContext.getAttribute("ematerial")).getEmatId()%>)"><%=((EmaterialBean)pageContext.getAttribute("ematerial")).getKindName()%></a>
    </display:column> 
    <%}else{%>
    <display:column property="code" titleKey="message.ematerial.code" sortable="true" headerClass="sortable"/>	
    <display:column property="nameVn" titleKey="message.ematerial.nameVn" sortable="true" headerClass="sortable"/>	
    <display:column property="nameEn" titleKey="message.ematerial.nameEn" sortable="true" headerClass="sortable"/>	
    <display:column property="kindName" titleKey="message.ematerial.kind" sortable="true" headerClass="sortable"/>	
    <%}%>
</display:table>

