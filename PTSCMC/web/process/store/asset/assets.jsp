<%@ taglib prefix="display" uri="/tags/displaytag"%>
<%@ page import="com.venus.mc.bean.AssetBean"%>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<display:table style="width:100%" pagesize="15" requestURI="javascript:loadAssetList({})" name="<%=Constants.ASSET_LIST%>" id="asset" class="its" >
    <display:setProperty name="paging.banner.items_name"><bean:message key='message.asset'/></display:setProperty>
    <display:setProperty name="paging.banner.item_name"><bean:message key='message.asset'/></display:setProperty>
    <display:column titleKey="message.del">
        <div align="center"><input type="checkbox" name="assId" value="<%=((AssetBean)pageContext.getAttribute("asset")).getAssId()%>"></div>
    </display:column>
    <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_VIEW, PermissionUtil.PER_EQUIPMENT_ASSET)) {%>
    <display:column titleKey="message.asset.usedCode" sortable="true" headerClass="sortable">
        <a href="#" name="assId" onclick="addAsset(<%=((AssetBean)pageContext.getAttribute("asset")).getAssId()%>)"><%=((AssetBean)pageContext.getAttribute("asset")).getUsedCode()%></a>
    </display:column>
    <display:column titleKey="message.asset.assetName" sortable="true" headerClass="sortable">
        <a href="#" name="assId" onclick="addAsset(<%=((AssetBean)pageContext.getAttribute("asset")).getAssId()%>)"><%=((AssetBean)pageContext.getAttribute("asset")).getAssetName()%></a>
    </display:column> 
    <display:column titleKey="message.asset.decisionNumber1" sortable="true" headerClass="sortable">
        <a href="#" name="assId" onclick="addAsset(<%=((AssetBean)pageContext.getAttribute("asset")).getAssId()%>)"><%=((AssetBean)pageContext.getAttribute("asset")).getDecisionNumber()%></a>
    </display:column>     
    <display:column titleKey="message.asset.status0" sortable="true" headerClass="sortable">
        <a href="#" name="assId" onclick="addAsset(<%=((AssetBean)pageContext.getAttribute("asset")).getAssId()%>)"><%=((AssetBean)pageContext.getAttribute("asset")).getStatusName()%></a>
    </display:column> 
    <display:column titleKey="message.asset.usedDate" sortable="true" headerClass="sortable">
        <a href="#" name="assId" onclick="addAsset(<%=((AssetBean)pageContext.getAttribute("asset")).getAssId()%>)"><%=((AssetBean)pageContext.getAttribute("asset")).getUsedDate()%></a>
    </display:column>
    <%}else{%>
    <display:column property="usedCode" titleKey="message.asset.usedCode" sortable="true" headerClass="sortable"/>	
    <display:column property="assetName" titleKey="message.asset.assetName" sortable="true" headerClass="sortable"/>
    <display:column property="decisionNumber1" titleKey="message.asset.decisionNumber1" sortable="true" headerClass="sortable"/>
    <display:column property="status0" titleKey="message.asset.status0" sortable="true" headerClass="sortable"/>
    <display:column property="usedDate" titleKey="message.asset.usedDate" sortable="true" headerClass="sortable"/>
    <%}%>
</display:table>

