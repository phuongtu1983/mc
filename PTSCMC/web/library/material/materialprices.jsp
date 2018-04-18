<%@ taglib prefix="display" uri="/tags/displaytag"%>
<%@ page import="com.venus.mc.material.MaterialPriceFormBean"%>
<%@ page import="com.venus.mc.util.Constants"%>
<display:table style="width:100%" pagesize="15" requestURI="javascript:loadMaterialPricesSort({})" name="<%=Constants.MATERIAL_LIST%>" id="material" class="its" defaultsort="1">
    <display:setProperty name="paging.banner.items_name" value='VTTB'/>
    <display:setProperty name="paging.banner.item_name" value="VTTB"/>
    <display:column titleKey="message.del">
        <div align="center"><input type="checkbox" name="autoId" value="<%=((MaterialPriceFormBean)pageContext.getAttribute("material")).getAutoId()%>"></div>
    </display:column>
    <display:column property="code" titleKey="message.material.code"/>
    <display:column property="matName" titleKey="message.request.material.name"/>
    <display:column property="unit" titleKey="message.menu211.text"/>
    <display:column titleKey="message.contract.material.price">
        <div align="center"><input type="text" style="border-width: 1px; text-align: right;" disabled name="materialPrice" value="<%=((MaterialPriceFormBean)pageContext.getAttribute("material")).getPrice()%>"/></div>        
    </display:column>
    <display:column property="currency" titleKey="message.contract.currency"/>
    <display:column property="contractNumber" titleKey="message.contract.number"/>
    <display:column property="vendorName" titleKey="message.u_vendor"/>
    <display:column property="effectedDate" titleKey="message.contract.effectedDate"/>
    <display:column property="expireDate" titleKey="message.contract.expireDate"/>
</display:table>

