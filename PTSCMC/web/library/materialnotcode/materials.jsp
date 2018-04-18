<%@ taglib prefix="display" uri="/tags/displaytag"%>
<%@ page import="com.venus.mc.bean.MaterialBean"%>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<display:table style="width:100%" pagesize="15" requestURI="javascript:loadMaterialsNotCode({})" name="<%=Constants.MATERIAL_LIST%>" id="material" class="its" >
    <display:setProperty name="paging.banner.items_name" value='VTTB'/>
    <display:setProperty name="paging.banner.item_name" value="VTTB"/>
    <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_VIEW, PermissionUtil.PER_PURCHASING_REQUEST_MATERIALNOTCODE)) {%>
    <display:column titleKey="message.material.nameVn" sortable="true" headerClass="sortable">
        <a href="#" name="matId" onclick="addMaterialNotCode(<%=((MaterialBean)pageContext.getAttribute("material")).getMatId()%>,<%=((MaterialBean)pageContext.getAttribute("material")).getReqId()%>)"><%=((MaterialBean)pageContext.getAttribute("material")).getNameVn()%></a>
    </display:column>
        <display:column titleKey="message.request.thamchieu" sortable="true" headerClass="sortable">
        <a href="#" name="matId" onclick="addMaterialNotCode(<%=((MaterialBean)pageContext.getAttribute("material")).getMatId()%>,<%=((MaterialBean)pageContext.getAttribute("material")).getReqId()%>)"><%=((MaterialBean)pageContext.getAttribute("material")).getRequestNumber()%></a>
    </display:column>
    <display:column titleKey="message.request.createdEmp" sortable="true" headerClass="sortable">
        <a href="#" name="matId" onclick="addMaterialNotCode(<%=((MaterialBean)pageContext.getAttribute("material")).getMatId()%>,<%=((MaterialBean)pageContext.getAttribute("material")).getReqId()%>)"><%=((MaterialBean)pageContext.getAttribute("material")).getEmpName()%></a>
    </display:column> 
    <display:column titleKey="message.request.organization" sortable="true" headerClass="sortable">
        <a href="#" name="matId" onclick="addMaterialNotCode(<%=((MaterialBean)pageContext.getAttribute("material")).getMatId()%>,<%=((MaterialBean)pageContext.getAttribute("material")).getReqId()%>)"><%=((MaterialBean)pageContext.getAttribute("material")).getOrgName()%></a>
    </display:column>
    <%}else{%>
    <display:column property="nameVn" titleKey="message.material.nameVn" sortable="true" headerClass="sortable"/>
    <display:column property="requestNumber" titleKey="message.request.number" sortable="true" headerClass="sortable"/>	
    <display:column property="empName" titleKey="message.request.createdEmp" sortable="true" headerClass="sortable" />
    <display:column property="orgName" titleKey="message.request.organization"/>
    <%}%>

</display:table>

