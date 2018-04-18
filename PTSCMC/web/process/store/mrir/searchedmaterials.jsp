<%-- 
    Document   : searchedmaterials
    Created on : Oct 11, 2009, 7:57:31 AM
    Author     : kngo
--%>

<%@ taglib prefix="display" uri="/tags/displaytag"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ page import="com.venus.mc.bean.MrirDetailBean"%>
<%@ page import="com.venus.mc.util.Constants"%>
<display:table style="width:100%;" pagesize="8" requestURI="javascript:searchMrirMaterialRequest({})" name="<%=Constants.MATERIAL_LIST%>" id="material" class="its" >
    <display:setProperty name="paging.banner.items_name"><bean:message key='message.material'/></display:setProperty>
    <display:setProperty name="paging.banner.item_name"><bean:message key='message.material'/></display:setProperty>
    <display:column titleKey="message.choose">
        <div align="center">
            <input type="checkbox" name="matId" value="<%=((MrirDetailBean)pageContext.getAttribute("material")).getMatId()%>">
            <input type="hidden" name="matNameHidden" value="<%=((MrirDetailBean)pageContext.getAttribute("material")).getMatName()%>">
        </div>
    </display:column>
    <display:column property="matName" titleKey="message.material.nameVn"/>
</display:table>

