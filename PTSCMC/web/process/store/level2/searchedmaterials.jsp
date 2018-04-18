<%@ taglib prefix="display" uri="/tags/displaytag"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ page import="com.venus.mc.process.store.level2.UsedMaterialSearchedFormBean"%>
<%@ page import="com.venus.mc.util.Constants"%>
<display:table style="width:100%;" pagesize="15" requestURI="javascript:searchMaterialUms({})" name="<%=Constants.MATERIAL_LIST%>" id="material" class="its" >
    <display:setProperty name="paging.banner.items_name"><bean:message key='message.material'/></display:setProperty>
    <display:setProperty name="paging.banner.item_name"><bean:message key='message.material'/></display:setProperty>
    <display:column titleKey="message.choose">
        <div align="center">
            <input type="checkbox" name="ms2rId" value="<%=((UsedMaterialSearchedFormBean)pageContext.getAttribute("material")).getMs2rId()%>">
            <input type="hidden" name="matCodeHidden" value="<%=((UsedMaterialSearchedFormBean)pageContext.getAttribute("material")).getMatCode()%>">
            <input type="hidden" name="matNameHidden" value="<%=((UsedMaterialSearchedFormBean)pageContext.getAttribute("material")).getMatName()%>">
            <input type="hidden" name="quantityHidden" value="<%=((UsedMaterialSearchedFormBean)pageContext.getAttribute("material")).getCurrentQuantity()%>">
            <input type="hidden" name="requestNumber" value="<%=((UsedMaterialSearchedFormBean)pageContext.getAttribute("material")).getRequestNumber()%>">
            <input type="hidden" name="mivNumber" value="<%=((UsedMaterialSearchedFormBean)pageContext.getAttribute("material")).getMivNumber()%>">
        </div>
    </display:column>
    <display:column property="matCode" titleKey="message.material.code"/>
    <display:column property="matName" titleKey="message.material.nameVn"/>
    <display:column property="currentQuantity" titleKey="message.umsdetail.currentQuantity"/>
    <display:column property="requestNumber" titleKey="message.umsdetail.request"/>
    <display:column property="mivNumber" titleKey="message.mivNumber"/>
</display:table>
