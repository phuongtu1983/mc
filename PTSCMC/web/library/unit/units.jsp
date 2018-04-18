<%@ taglib prefix="display" uri="/tags/displaytag"%>
<%@ page import="com.venus.mc.bean.UnitBean"%>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<display:table style="width:100%" pagesize="15" requestURI="javascript:loadUnits({})" name="<%=Constants.UNIT_LIST%>" id="unit" class="its" >
    <display:setProperty name="paging.banner.items_name" value='&#273;&#417;n v&#7883; t&#237;nh'/>
    <display:setProperty name="paging.banner.item_name" value="&#273;&#417;n v&#7883; t&#237;nh"/>
    <display:column titleKey="message.del">
        <div align="center"><input type="checkbox" name="uniId" value="<%=((UnitBean)pageContext.getAttribute("unit")).getUniId()%>"></div>
    </display:column>
    <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_VIEW, PermissionUtil.PER_LIBRARY_MATERIAL_UNIT)) {%>
    <display:column titleKey="message.unit.unitVn" sortable="true" headerClass="sortable">
        <a href="#" name="uniId" onclick="addUnit(<%=((UnitBean)pageContext.getAttribute("unit")).getUniId()%>)"><%=((UnitBean)pageContext.getAttribute("unit")).getUnitVn()%></a>
    </display:column>
    <display:column titleKey="message.unit.unitEn" sortable="true" headerClass="sortable">
        <a href="#" name="uniId" onclick="addUnit(<%=((UnitBean)pageContext.getAttribute("unit")).getUniId()%>)"><%=((UnitBean)pageContext.getAttribute("unit")).getUnitEn()%></a>
    </display:column>
    <%}else{%>
    <display:column property="unitVn" titleKey="message.unit.unitVn" sortable="true" headerClass="sortable"/>	
    <display:column property="unitEn" titleKey="message.unit.unitEn" sortable="true" headerClass="sortable"/>	
    <%}%>
</display:table>

