<%@ taglib prefix="display" uri="/tags/displaytag"%>
<%@ page import="com.venus.mc.bean.VendorBean"%>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<display:table style="width:100%" pagesize="15" requestURI="javascript:loadVendorListSort({})" name="<%=Constants.VENDOR_LIST%>" id="vendor" class="its" >
    <display:setProperty name="paging.banner.items_name"><bean:message key='message.vendor'/></display:setProperty>
    <display:setProperty name="paging.banner.item_name"><bean:message key='message.vendor'/></display:setProperty>
    <display:column titleKey="message.del">
        <input type="checkbox" name="venId" value="<%=((VendorBean)pageContext.getAttribute("vendor")).getVenId()%>">
    </display:column>
    <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_VIEW, PermissionUtil.PER_LIBRARY_VENDOR)) {%>
    <display:column titleKey="message.vendor.name" sortable="true" headerClass="sortable">
        <a href="#" onclick="vendorForm(<%=((VendorBean)pageContext.getAttribute("vendor")).getVenId()%>)"><%=((VendorBean)pageContext.getAttribute("vendor")).getName()%></a>
    </display:column>
    <%}else{%>
    <display:column property="name" titleKey="message.vendor.name" sortable="true" headerClass="sortable"/>	
    <%}%>
    <display:column property="phone" titleKey="message.vendor.phone" sortable="true" headerClass="sortable"/>	
    <display:column property="fax" titleKey="message.vendor.fax" sortable="true" headerClass="sortable" />
    <display:column property="address" titleKey="message.vendor.address"/>
    <display:column property="email" titleKey="message.vendor.email"/>
    <display:column property="charterCapital" titleKey="message.vendor.charterCapital"/>
</display:table>