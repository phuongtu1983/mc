<%@ taglib prefix="display" uri="/tags/displaytag"%>
<%@ page import="com.venus.mc.bean.MrirBean"%>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<display:table style="width:100%" pagesize="15" requestURI="javascript:loadMrirList1({})" name="<%=Constants.MRIR_LIST%>" id="mrir" class="its" decorator="com.venus.mc.process.store.mrir.decorator.MrirDecorator" >
    <display:setProperty name="paging.banner.items_name"><bean:message key='message.l_mrir'/></display:setProperty>
    <display:setProperty name="paging.banner.item_name"><bean:message key='message.l_mrir'/></display:setProperty>
    <display:column titleKey="message.del">
        <input type="checkbox" name="mrirId" value="<%=((MrirBean) pageContext.getAttribute("mrir")).getMrirId()%>">
    </display:column>
    <display:column property="createdDate" titleKey="message.mrir.createdDate" sortable="true" headerClass="sortable"/>
    <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_VIEW, PermissionUtil.PER_STORE)) {%>
    <display:column titleKey="message.mrir.mrirNumber" sortable="true" headerClass="sortable">
        <a href="#" onclick="mrirForm(<%=((MrirBean)pageContext.getAttribute("mrir")).getMrirId()%>)"><%=((MrirBean)pageContext.getAttribute("mrir")).getMrirNumber()%></a>
    </display:column>
    <%}else{%>
    <display:column property="mrirNumber" titleKey="message.mrir.mrirNumber" sortable="true" headerClass="sortable"/>	
    <%}%>
    <display:column property="dnNumber" titleKey="message.mrir.dnId" sortable="true" headerClass="sortable"/>
    <display:column property="invoiceNo" titleKey="message.mrir.invoiceNo" sortable="true" headerClass="sortable"/>   
    <%--<display:column property="mrirKind" titleKey="message.project" sortable="true" headerClass="sortable"/>    --%>  
    <display:column property="proName" titleKey="message.project" sortable="true" headerClass="sortable"/>
</display:table>
