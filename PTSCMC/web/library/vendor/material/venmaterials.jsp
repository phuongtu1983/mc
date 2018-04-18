<%@ taglib prefix="display" uri="/tags/displaytag"%>
<%@ page import="com.venus.mc.vendor.material.VendorMaterialFormBean"%>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<display:table style="width:100%" requestURI="javascript:loadVenMaterials({})" name="<%=Constants.VENDOR_MATERIAL_LIST%>" id="vendorMaterial" class="its" >
    <display:setProperty name="paging.banner.items_name"><bean:message key='message.vendor.material'/></display:setProperty>
    <display:setProperty name="paging.banner.item_name"><bean:message key='message.vendor.material'/></display:setProperty>
    <display:column titleKey="message.del">
        <input type="checkbox" name="venmaterialid" value="<%=((VendorMaterialFormBean)pageContext.getAttribute("vendorMaterial")).getVmId()%>">
    </display:column>
    <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_VIEW, PermissionUtil.PER_LIBRARY_VENDOR)) {%>
    <display:column titleKey="message.vendor.material.nameVn" sortable="true" headerClass="sortable">
        <a href="#" onclick="venMaterialForm(<%=((VendorMaterialFormBean)pageContext.getAttribute("vendorMaterial")).getVmId()%>)"><%=((VendorMaterialFormBean)pageContext.getAttribute("vendorMaterial")).getNameVn()%></a>
    </display:column>
    <%}else{%>
    <display:column property="nameVn" titleKey="message.vendor.material.nameVn" sortable="true" headerClass="sortable"/>	
    <%}%>
    <display:column property="nameEn" titleKey="message.vendor.material.nameEn" sortable="true"/>
    <display:column property="manufacturer" titleKey="message.vendor.material.manufacturer" sortable="true"/>
    <display:column property="groName" titleKey="message.vendor.material.group" sortable="true"/>
    <display:column property="note" titleKey="message.note"/>
</display:table>