<%@ taglib prefix="display" uri="/tags/displaytag"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ page import="com.venus.mc.bean.DnDetailBean"%>
<%@ page import="com.venus.mc.util.Constants"%>
<display:table style="width:100%;" pagesize="15" requestURI="javascript:searchDn2MaterialRequest({})" name="<%=Constants.MATERIAL_LIST%>" id="material" class="its" >
    <display:setProperty name="paging.banner.items_name"><bean:message key='message.material'/></display:setProperty>
    <display:setProperty name="paging.banner.item_name"><bean:message key='message.material'/></display:setProperty>
    <display:column titleKey="message.choose">
        <div align="center">
            <input type="checkbox" name="matId" value="<%=((DnDetailBean)pageContext.getAttribute("material")).getMatId()%>">
            <input type="hidden" name="matNameHidden" value="<%=((DnDetailBean)pageContext.getAttribute("material")).getMatName()%>">
            <input type="hidden" name="matCodeHidden" value="<%=((DnDetailBean)pageContext.getAttribute("material")).getMatCode()%>">
            <input type="hidden" name="matUnitHidden" value="<%=((DnDetailBean)pageContext.getAttribute("material")).getUnit()%>">
            <input type="hidden" name="matQuantityHidden" value="<%=((DnDetailBean)pageContext.getAttribute("material")).getQuantity()%>">
            <input type="hidden" name="matDetIdHidden" value="<%=((DnDetailBean)pageContext.getAttribute("material")).getDetId()%>">
            <input type="hidden" name="matReqDetailIdHidden" value="<%=((DnDetailBean)pageContext.getAttribute("material")).getReqDetailId()%>">
        </div>
    </display:column>
    <display:column property="matName" titleKey="message.material.nameVn"/>
    <display:column property="matCode" titleKey="message.material.code"/>
    <display:column property="unit" titleKey="message.dn.unit"/>
</display:table>
