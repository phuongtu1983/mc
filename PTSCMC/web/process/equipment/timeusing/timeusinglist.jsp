<%@ taglib prefix="display" uri="/tags/displaytag"%>
<%@ page import="com.venus.mc.bean.TimeUsingBean"%>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<display:table style="width:100%" pagesize="15" requestURI="javascript:loadTimeUsingListSort({})" name="<%=Constants.TIMEUSING_LIST%>" id="tu" class="its" >
    <display:setProperty name="paging.banner.items_name" value=""/>
    <display:setProperty name="paging.banner.item_name" value="" />
    <display:column titleKey="message.del">
        <input type="checkbox" name="tuIds" value="<%=((TimeUsingBean) pageContext.getAttribute("tu")).getUpdateDate()%>">
    </display:column>    
    <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_VIEW, PermissionUtil.PER_EQUIPMENT_REPAIRREQUEST)) {%>
    <display:column titleKey="message.timeusing.updatedate">    
    <a href="#" onclick="timeUsingForm('<%=((TimeUsingBean) pageContext.getAttribute("tu")).getUpdateDate()%>')"><%=((TimeUsingBean) pageContext.getAttribute("tu")).getUpdateDate()%></a>
     </display:column>
     <%}else{%>
    <display:column property="updateDate" titleKey="message.timeusing.updatedate" sortable="true" headerClass="sortable"/>	
    <%}%>
    <display:column property="createdEmpName" titleKey="message.timeusing.createdemp"/>
</display:table>
