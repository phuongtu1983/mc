<%@ taglib prefix="display" uri="/tags/displaytag"%>
<%@ page import="com.venus.mc.bean.EmsvBean"%>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<display:table style="width:100%" pagesize="15" requestURI="javascript:loadEmsvListSort({})" name="<%=Constants.EMSV_LIST%>" id="emsv" class="its" >
    <display:setProperty name="paging.banner.items_name" value="" />
    <display:setProperty name="paging.banner.item_name" value="" />
    <display:column titleKey="message.del">
        <input type="checkbox" name="emsvId" value="<%=((EmsvBean)pageContext.getAttribute("emsv")).getEmsvId()%>">
    </display:column>
    <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_VIEW, PermissionUtil.PER_STORE)) {%>
    <display:column titleKey="message.msv.number">
        <a href="#" onclick="emsvEmrirForm(<%=((EmsvBean)pageContext.getAttribute("emsv")).getEmsvId()%>)"><%=((EmsvBean)pageContext.getAttribute("emsv")).getEmsvNumber()%></a>
    </display:column>
    <%}else{%>
    <display:column property="number" titleKey="message.msv.number" sortable="true" headerClass="sortable"/>	
    <%}%>
    <display:column property="createdDate" titleKey="message.msv.createddate" />   
    <display:column property="deliverer" titleKey="message.msv.deliverer" />   
    <display:column property="contract" titleKey="message.msv.contract" />   
    <display:column property="stoName" titleKey="message.msv.inputstore" />       
</display:table>
