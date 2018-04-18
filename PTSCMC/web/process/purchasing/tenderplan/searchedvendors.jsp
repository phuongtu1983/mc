<%@ taglib prefix="display" uri="/tags/displaytag"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ page import="com.venus.mc.bean.VendorBean"%>
<%@ page import="com.venus.mc.util.Constants"%>
<display:table style="width:100%" pagesize="15" requestURI="javascript:searchVendorTenderPlanSort({})" name="<%=Constants.VENDOR_LIST%>" id="vendor" class="its" >
    <display:setProperty name="paging.banner.items_name"><bean:message key='message.u_vendor'/></display:setProperty>
    <display:setProperty name="paging.banner.item_name"><bean:message key='message.u_vendor'/></display:setProperty>
    <display:column titleKey="message.empty">
        <input type="checkbox" name="venId" value="<%=((VendorBean) pageContext.getAttribute("vendor")).getVenId()%>">
        <input type="hidden" name="vendorNameHidden" value="<%=((VendorBean)pageContext.getAttribute("vendor")).getName()%>">
        <input type="hidden" name="vendorCharterCapitalHidden" value="<%=((VendorBean)pageContext.getAttribute("vendor")).getCharterCapital()%>">
        <input type="hidden" name="vendorPresenterHidden" value="<%=((VendorBean)pageContext.getAttribute("vendor")).getPresenter()%>">
    </display:column>
    <display:column property="name" titleKey="message.vendor.l_name"/>
    <display:column property="charterCapital" titleKey="message.vendor.charterCapital"/>
    <display:column property="presenter" titleKey="message.vendor.presenter"/>
    <display:column property="note" titleKey="message.vendor.groupmaterial.material"/>
</display:table>
