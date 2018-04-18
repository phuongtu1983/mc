<%@ taglib prefix="display" uri="/tags/displaytag"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ page import="com.venus.mc.bean.RfmDetailBean"%>
<%@ page import="com.venus.mc.util.Constants"%>
<display:table style="width:100%;" pagesize="15" requestURI="javascript:searchRfmMaterialRequest({})" name="<%=Constants.MATERIAL_LIST%>" id="material" class="its" >
    <display:setProperty name="paging.banner.items_name"><bean:message key='message.material'/></display:setProperty>
    <display:setProperty name="paging.banner.item_name"><bean:message key='message.material'/></display:setProperty>
    <display:column titleKey="message.choose">
        <div align="center">
            <input type="checkbox" name="msrId" value="<%=((RfmDetailBean)pageContext.getAttribute("material")).getMsrId()%>">
            <input type="hidden" name="matNameHidden" value="<%=((RfmDetailBean)pageContext.getAttribute("material")).getMatName()%>">
            <input type="hidden" name="matCodeHidden" value="<%=((RfmDetailBean)pageContext.getAttribute("material")).getRequestNumber()%>">
        </div>
    </display:column>
    <display:column property="matName" titleKey="message.material.nameVn"/>
    <display:column property="requestNumber" titleKey="message.rfm.requestNumber"/>
</display:table>
