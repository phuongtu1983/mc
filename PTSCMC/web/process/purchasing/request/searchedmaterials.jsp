<%@ taglib prefix="display" uri="/tags/displaytag"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ page import="com.venus.mc.bean.MaterialBean"%>
<%@ page import="com.venus.mc.util.Constants"%>
<display:table style="width:100%;" pagesize="15" requestURI="javascript:searchMaterialRequest({})" name="<%=Constants.MATERIAL_LIST%>" id="material" class="its" >
    <display:setProperty name="paging.banner.items_name"><bean:message key='message.material'/></display:setProperty>
    <display:setProperty name="paging.banner.item_name"><bean:message key='message.material'/></display:setProperty>
    <display:column titleKey="message.choose">
        <div align="center">
            <input type="checkbox" name="matId" value="<%=((MaterialBean)pageContext.getAttribute("material")).getMatId()%>">
            <input type="hidden" name="matCodeHidden" value="<%=((MaterialBean)pageContext.getAttribute("material")).getCode()%>">
            <input type="hidden" name="matNameHidden" value="<%=((MaterialBean)pageContext.getAttribute("material")).getNameVn()%>">
        </div>
    </display:column>
    <display:column property="code" titleKey="message.material.code"/>
    <display:column property="nameVn" titleKey="message.material.nameVn"/>
    <display:column property="unitName" titleKey="message.material.unit"/>
    <display:column property="qc" titleKey="message.material.qc"/>
    <display:column property="contractNumber" titleKey="message.contract.number"/>
    <display:column property="deliveryTime" titleKey="message.material.deliveryTime"/>
</display:table>
