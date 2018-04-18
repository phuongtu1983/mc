<%@ taglib prefix="display" uri="/tags/displaytag"%>
<%@ page import="com.venus.mc.vendor.material.VendorGroupMaterialFormBean"%>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<display:table style="width:100%" requestURI="javascript:loadVenGroupMaterials({})" name="<%=Constants.VENDOR_GROUP_MATERIAL_LIST%>" id="vendorGroupMaterial" class="its" >
    <display:setProperty name="paging.banner.items_name"><bean:message key='message.vendor.groupMaterial'/></display:setProperty>
    <display:setProperty name="paging.banner.item_name"><bean:message key='message.vendor.groupMaterial'/></display:setProperty>
    <display:column titleKey="message.del">
        <input type="checkbox" name="vengroupmaterialid" value="<%=((VendorGroupMaterialFormBean)pageContext.getAttribute("vendorGroupMaterial")).getVgmId()%>">
    </display:column>
    <display:column property="groupName" titleKey="message.vendor.groupMaterial.name" sortable="true"/>
    <display:column property="groupNote" titleKey="message.note"/>
</display:table>