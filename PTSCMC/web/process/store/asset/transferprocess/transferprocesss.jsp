<%@ taglib prefix="display" uri="/tags/displaytag"%>
<%@ page import="com.venus.mc.bean.TransferProcessBean"%>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<display:table style="width:100%" pagesize="15" requestURI="javascript:loadAssetTransferProcessList({})" name="<%=Constants.TRANSFERPROCESS_LIST%>" id="transferprocess" class="its" >
    <display:setProperty name="paging.banner.items_name"><bean:message key='message.transferprocess'/></display:setProperty>
    <display:setProperty name="paging.banner.item_name"><bean:message key='message.transferprocess'/></display:setProperty>
    <display:column titleKey="message.del">
        <div align="center"><input type="checkbox" name="tpId" value="<%=((TransferProcessBean)pageContext.getAttribute("transferprocess")).getTpId()%>"></div>
    </display:column>
    <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_VIEW, PermissionUtil.PER_STORE)) {%>
    <display:column titleKey="message.transferprocess.receiveOrg" sortable="true" headerClass="sortable">
        <a href="#" name="tpId" onclick="addAssetTransferProcess(<%=((TransferProcessBean)pageContext.getAttribute("transferprocess")).getTpId()%>)"><%=((TransferProcessBean)pageContext.getAttribute("transferprocess")).getOrgName()%></a>
    </display:column>
    <display:column titleKey="message.transferprocess.receiveDate" sortable="true" headerClass="sortable">
        <a href="#" name="tpId" onclick="addAssetTransferProcess(<%=((TransferProcessBean)pageContext.getAttribute("transferprocess")).getTpId()%>)"><%=((TransferProcessBean)pageContext.getAttribute("transferprocess")).getReceiveDate()%></a>
    </display:column> 
    <display:column titleKey="message.transferprocess.returnDate" sortable="true" headerClass="sortable">
        <a href="#" name="tpId" onclick="addAssetTransferProcess(<%=((TransferProcessBean)pageContext.getAttribute("transferprocess")).getTpId()%>)"><%=((TransferProcessBean)pageContext.getAttribute("transferprocess")).getReturnDate()%></a>
    </display:column>     
    <display:column titleKey="message.transferprocess.comment" sortable="true" headerClass="sortable">
        <a href="#" name="tpId" onclick="addAssetTransferProcess(<%=((TransferProcessBean)pageContext.getAttribute("transferprocess")).getTpId()%>)"><%=((TransferProcessBean)pageContext.getAttribute("transferprocess")).getComment()%></a>
    </display:column> 
    <%}else{%>
    <display:column property="receiveOrg" titleKey="message.transferprocess.receiveOrg" sortable="true" headerClass="sortable"/>
    <display:column property="receiveDate" titleKey="message.transferprocess.receiveDate" sortable="true" headerClass="sortable"/>
    <display:column property="returnDate" titleKey="message.transferprocess.returnDate" sortable="true" headerClass="sortable"/>
    <display:column property="comment" titleKey="message.transferprocess.comment" sortable="true" headerClass="sortable"/>
    <%}%>
</display:table>

