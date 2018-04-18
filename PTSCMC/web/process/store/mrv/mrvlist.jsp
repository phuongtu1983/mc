<%@ taglib prefix="display" uri="/tags/displaytag"%>
<%@ page import="com.venus.mc.bean.MsvBean"%>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<display:table style="width:100%" pagesize="15" requestURI="javascript:loadMrvListSort({})" name="<%=Constants.MRV_LIST%>" id="mrv" class="its" >
    <display:setProperty name="paging.banner.items_name" value="" />
    <display:setProperty name="paging.banner.item_name" value="" />
    <display:column titleKey="message.del">
        <input type="checkbox" name="msvId" value="<%=((MsvBean)pageContext.getAttribute("mrv")).getMsvId()%>">
    </display:column>
    <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_VIEW, PermissionUtil.PER_STOCK_MSV)) {%>
    <display:column titleKey="message.msv.number">
        <a href="#" onclick="mrvForm(<%=((MsvBean)pageContext.getAttribute("mrv")).getMsvId()%>)"><%=((MsvBean)pageContext.getAttribute("mrv")).getMsvNumber()%></a>
    </display:column>
    <%}else{%>
    <display:column property="msvNumber" titleKey="message.msv.number" sortable="true" headerClass="sortable"/>	
    <%}%>
    <display:column property="createdDate" titleKey="message.msv.createddate" />   
    <display:column property="deliveryEmpName" titleKey="message.msv.deliveryemp" />   
    <display:column property="orgName" titleKey="message.msv.deliveryorg" />  
    <display:column property="mivNumbers" titleKey="message.msv.mivnumber" />    
    <display:column property="stoName" titleKey="message.msv.inputstore" />       
</display:table>
