<%@ taglib prefix="display" uri="/tags/displaytag"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ page import="com.venus.mc.bean.EdnDetailBean"%>
<%@ page import="com.venus.mc.util.Constants"%>
<display:table style="width:100%;" pagesize="15" requestURI="javascript:searchEdnMaterialRequest({})" name="<%=Constants.MATERIAL_LIST%>" id="material" class="its" >
    <display:setProperty name="paging.banner.items_name"><bean:message key='message.material'/></display:setProperty>
    <display:setProperty name="paging.banner.item_name"><bean:message key='message.material'/></display:setProperty>
    <display:column titleKey="message.choose">
        <div align="center">
            <input type="checkbox" name="matId" value="<%=((EdnDetailBean)pageContext.getAttribute("material")).getMatId()%>">
            <input type="hidden" name="matNameHidden" value="<%=((EdnDetailBean)pageContext.getAttribute("material")).getMatName()%>">
            <input type="hidden" name="matCodeHidden" value="<%=((EdnDetailBean)pageContext.getAttribute("material")).getUnit()%>">
        </div>
    </display:column>
    <display:column property="matName" titleKey="message.material.nameVn"/>
    <display:column property="unit" titleKey="message.dn.unit"/>
    <display:column property="quantity" titleKey="message.dn.quantity"/>
</display:table>
