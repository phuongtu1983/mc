<%@ taglib prefix="display" uri="/tags/displaytag"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ page import="com.venus.mc.request.MaterialInContractFormBean"%>
<%@ page import="com.venus.mc.util.Constants"%>
<display:table style="width:100%" pagesize="15" requestURI="javascript:searchMaterialTenderPlan({})" name="<%=Constants.MATERIAL_LIST%>" id="mat" class="its" >
    <display:setProperty name="paging.banner.items_name"><bean:message key='message.material'/></display:setProperty>
    <display:setProperty name="paging.banner.item_name"><bean:message key='message.material'/></display:setProperty>
    <display:column titleKey="message.empty">
        <input type="checkbox" name="matId" value="<%=((MaterialInContractFormBean) pageContext.getAttribute("mat")).getMatId()%>">
        <input type="hidden" name="matCodeHidden" value="<%=((MaterialInContractFormBean)pageContext.getAttribute("mat")).getMatCode()%>">
        <input type="hidden" name="matNameHidden" value="<%=((MaterialInContractFormBean)pageContext.getAttribute("mat")).getMatName()%>">
        <input type="hidden" name="reqId" value="<%=((MaterialInContractFormBean)pageContext.getAttribute("mat")).getReqId()%>">
        <input type="hidden" name="conDetId" value="<%=((MaterialInContractFormBean)pageContext.getAttribute("mat")).getConDetId()%>">
    </display:column>
    <display:column property="reqNumber" titleKey="message.request.number"/>
    <display:column property="matCode" titleKey="message.material.code"/>
    <display:column property="matName" titleKey="message.material.nameVn"/>
    <display:column property="unit" titleKey="message.material.uniId"/>
    <display:column property="quantity" titleKey="message.request.material.requestQuantity"/>
</display:table>
