<%@ taglib prefix="display" uri="/tags/displaytag"%>
<%@ page import="com.venus.mc.bean.MaterialBean"%>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<display:table style="width:100%" pagesize="15" requestURI="javascript:loadMaterials({})" name="<%=Constants.MATERIAL_LIST%>" id="material" class="its" >
    <display:setProperty name="paging.banner.items_name" value='VTTB'/>
    <display:setProperty name="paging.banner.item_name" value="VTTB"/>
    <display:column titleKey="message.del">
        <div align="center"><input type="checkbox" name="matId" value="<%=((MaterialBean)pageContext.getAttribute("material")).getMatId()%>"></div>
    </display:column>
    <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_VIEW, PermissionUtil.PER_LIBRARY_MATERIAL_COMPANY)) {%>
    <display:column titleKey="message.material.code" sortable="true" headerClass="sortable">
        <a href="#" name="matId" onclick="addMaterial(<%=((MaterialBean)pageContext.getAttribute("material")).getMatId()%>)"><%=((MaterialBean)pageContext.getAttribute("material")).getCode()%></a>
    </display:column>
    <display:column titleKey="message.material.nameVn" sortable="true" headerClass="sortable">
        <a href="#" name="matId" onclick="addMaterial(<%=((MaterialBean)pageContext.getAttribute("material")).getMatId()%>)"><%=((MaterialBean)pageContext.getAttribute("material")).getNameVn()%></a>
    </display:column> 
    <display:column titleKey="message.material.nameEn" sortable="true" headerClass="sortable">
        <a href="#" name="matId" onclick="addMaterial(<%=((MaterialBean)pageContext.getAttribute("material")).getMatId()%>)"><%=((MaterialBean)pageContext.getAttribute("material")).getNameEn()%></a>
    </display:column> 
    <display:column titleKey="message.material.qc" sortable="true" headerClass="sortable">
        <a href="#" name="matId" onclick="addMaterial(<%=((MaterialBean)pageContext.getAttribute("material")).getMatId()%>)"><%=((MaterialBean)pageContext.getAttribute("material")).getQc()%></a>
    </display:column>
    <%}else{%>
    <display:column property="code" titleKey="message.material.code" sortable="true" headerClass="sortable"/>
    <display:column property="nameVn" titleKey="message.material.nameVn" sortable="true" headerClass="sortable"/>	
    <display:column property="nameEn" titleKey="message.material.nameEn" sortable="true" headerClass="sortable" />
    <display:column property="kindName" titleKey="message.material.kind"/>
    <display:column property="qc" titleKey="message.material.qc"/>
    <%}%>

</display:table>

