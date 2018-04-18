<%@ taglib prefix="display" uri="/tags/displaytag"%>
<%@ page import="com.venus.mc.bean.VendorContactBean"%>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<display:table style="width:100%" requestURI="javascript:loadVenContacts({})" name="<%=Constants.VENDOR_CONTACT_LIST%>" id="vendorContact" class="its" >
    <display:setProperty name="paging.banner.items_name"><bean:message key='message.vendor.contact'/></display:setProperty>
    <display:setProperty name="paging.banner.item_name"><bean:message key='message.vendor.contact'/></display:setProperty>
    <display:column titleKey="message.del">
        <input type="checkbox" name="vencontactid" value="<%=((VendorContactBean)pageContext.getAttribute("vendorContact")).getContId()%>">
    </display:column>
    <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_VIEW, PermissionUtil.PER_LIBRARY_VENDOR)) {%>
    <display:column titleKey="message.vendor.contact.name" sortable="true" headerClass="sortable">
        <a href="#" onclick="venContactForm(<%=((VendorContactBean)pageContext.getAttribute("vendorContact")).getContId()%>)"><%=((VendorContactBean)pageContext.getAttribute("vendorContact")).getName()%></a>
    </display:column>
    <%}else{%>
    <display:column property="name" titleKey="message.vendor.contact.name" sortable="true"/>
    <%}%>
    <display:column property="position" titleKey="message.vendor.contact.position" sortable="true"/>
    <display:column property="handPhone" titleKey="message.vendor.contact.handPhone"/>
    <display:column property="birthday" titleKey="message.vendor.contact.birthday"/>
    <display:column property="email" titleKey="message.vendor.contact.email"/>
</display:table>