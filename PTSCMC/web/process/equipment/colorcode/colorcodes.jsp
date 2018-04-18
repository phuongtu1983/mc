<%@ taglib prefix="display" uri="/tags/displaytag"%>
<%@ page import="com.venus.mc.bean.ColorCodeBean"%>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<display:table style="width:100%" pagesize="15" requestURI="javascript:loadColorCodeListSort({})" name="<%=Constants.COLORCODE_LIST%>" id="colorCode" class="its" >
    <display:setProperty name="paging.banner.items_name"><bean:message key='message.colorcode.colorCode'/></display:setProperty>
    <display:setProperty name="paging.banner.item_name"><bean:message key='message.colorcode.colorCode'/></display:setProperty>
    <display:column titleKey="message.del">
        <div align="center"><input type="checkbox" name="ccId" value="<%=((ColorCodeBean)pageContext.getAttribute("colorCode")).getCcId()%>"></div>
        </display:column>
        <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_VIEW, PermissionUtil.PER_EQUIPMENT_COLOR)) {%>
        <display:column titleKey="message.colorcode.colorCode" sortable="true" headerClass="sortable">
        <a href="#" name="ccId" onclick="addColorCode(<%=((ColorCodeBean)pageContext.getAttribute("colorCode")).getCcId()%>)"><%=((ColorCodeBean)pageContext.getAttribute("colorCode")).getColorCode()%></a>
    </display:column>
    <display:column titleKey="message.colorcode.timeApplication" sortable="true" headerClass="sortable">
        <a href="#" name="ccId" onclick="addColorCode(<%=((ColorCodeBean)pageContext.getAttribute("colorCode")).getCcId()%>)"><%=((ColorCodeBean)pageContext.getAttribute("colorCode")).getTimeApplication()%></a>
    </display:column>
    <display:column titleKey="message.colorcode.startDate" sortable="true" headerClass="sortable">
        <a href="#" name="ccId" onclick="addColorCode(<%=((ColorCodeBean)pageContext.getAttribute("colorCode")).getCcId()%>)"><%=((ColorCodeBean)pageContext.getAttribute("colorCode")).getStartDate()%></a>
    </display:column>
    <display:column titleKey="message.colorcode.endDate" sortable="true" headerClass="sortable">
        <a href="#" name="ccId" onclick="addColorCode(<%=((ColorCodeBean)pageContext.getAttribute("colorCode")).getCcId()%>)"><%=((ColorCodeBean)pageContext.getAttribute("colorCode")).getEndDate()%></a>
    </display:column>
    <%}else{%>
    <display:column property="colorCode" titleKey="message.colorcode.colorCode" sortable="true" headerClass="sortable"/>	
    <display:column property="timeApplication" titleKey="message.colorcode.timeApplication" sortable="true" headerClass="sortable"/>
    <display:column property="startDate" titleKey="message.colorcode.startDate" sortable="true" headerClass="sortable"/>
    <display:column property="endDate" titleKey="message.colorcode.endDate" sortable="true" headerClass="sortable"/>
    <%}%>
</display:table>

