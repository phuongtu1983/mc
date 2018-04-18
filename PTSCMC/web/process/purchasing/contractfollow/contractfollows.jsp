<%@ taglib prefix="display" uri="/tags/displaytag"%>
<%@ page import="com.venus.mc.bean.ContractFollowBean"%>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<%@ page import="com.venus.mc.util.Constants"%>
<display:table style="width:100%" pagesize="15" requestURI="javascript:loadContractFollowListSort({})" name="<%=Constants.CONTRACT_FOLLOW_LIST%>" id="contractFollow" class="its" >
    <display:setProperty name="paging.banner.items_name" value='Phi&#7871;u theo d&#245;i H&#272;'/>
    <display:setProperty name="paging.banner.item_name" value="Phi&#7871;u theo d&#245;i H&#272;"/>
    <display:column titleKey="message.del">
        <div align="center"><input type="checkbox" name="folId" value="<%=((ContractFollowBean)pageContext.getAttribute("contractFollow")).getFolId()%>"></div>
    </display:column>
    <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_VIEW, PermissionUtil.PER_PURCHASING_CONTRACT_FOLLOW)) {%>
    <display:column titleKey="message.contractFollow.folNumber" sortable="true" headerClass="sortable">
        <a href="#" name="folId" onclick="addContractFollow(<%=((ContractFollowBean)pageContext.getAttribute("contractFollow")).getFolId()%>)"><%=((ContractFollowBean)pageContext.getAttribute("contractFollow")).getFolNumber()%></a>
    </display:column>
    <display:column titleKey="message.contractFollow.conId" sortable="true" headerClass="sortable">
        <a href="#" name="folId" onclick="addContractFollow(<%=((ContractFollowBean)pageContext.getAttribute("contractFollow")).getFolId()%>)"><%=((ContractFollowBean)pageContext.getAttribute("contractFollow")).getConNumber()%></a>
    </display:column>
    <display:column titleKey="message.contractFollow.createdDate" sortable="true" headerClass="sortable">
        <a href="#" name="folId" onclick="addContractFollow(<%=((ContractFollowBean)pageContext.getAttribute("contractFollow")).getFolId()%>)"><%=((ContractFollowBean)pageContext.getAttribute("contractFollow")).getCreatedDate()%></a>
    </display:column>
    <%}else{%>
    <display:column property="folNumber" titleKey="message.contractFollow.folNumber" sortable="true" headerClass="sortable"/>
    <display:column property="conId" titleKey="message.contractFollow.conId" sortable="true" headerClass="sortable"/>
    <display:column property="createdDate" titleKey="message.contractFollow.createdDate" sortable="true" headerClass="sortable"/>
    <%}%>
</display:table>