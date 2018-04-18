<%@ taglib prefix="display" uri="/tags/displaytag"%>
<%@ page import="com.venus.mc.request.MaterialInContractFormBean"%>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<display:table style="width:100%" pagesize="10" requestURI="javascript:loadMaterialInAdjustmentContracts({})" name="<%=Constants.MATERIAL_CONTRACT_LIST%>" id="mat" class="its" >
    <display:setProperty name="paging.banner.items_name"><bean:message key='message.material'/></display:setProperty>
    <display:setProperty name="paging.banner.item_name"><bean:message key='message.material'/></display:setProperty>
    <display:column titleKey="message.empty">
        <input type="checkbox" name="matId" value="<%=((MaterialInContractFormBean) pageContext.getAttribute("mat")).getMatId()%>" id="reqDetId_materialInAdjustmentContractForm_<%=((MaterialInContractFormBean) pageContext.getAttribute("mat")).getReqDetailId()%>_<%=((MaterialInContractFormBean) pageContext.getAttribute("mat")).getConId()%>" onclick="return materialInContract_CheckOnClick('materialInAdjustmentContractForm','<%=((MaterialInContractFormBean) pageContext.getAttribute("mat")).getReqDetailId()%>_<%=((MaterialInContractFormBean) pageContext.getAttribute("mat")).getConId()%>',<%=((MaterialInContractFormBean) pageContext.getAttribute("mat")).getMatId()%>,<%=((MaterialInContractFormBean)pageContext.getAttribute("mat")).getReqId()%>,<%=((MaterialInContractFormBean) pageContext.getAttribute("mat")).getConId()%>,<%=((MaterialInContractFormBean) pageContext.getAttribute("mat")).getVenId()%>,<%=((MaterialInContractFormBean) pageContext.getAttribute("mat")).getConDetId()%>)"
        <input type="hidden" name="conId" value="<%=((MaterialInContractFormBean) pageContext.getAttribute("mat")).getConId()%>">
        <input type="hidden" name="conDetId" value="<%=((MaterialInContractFormBean) pageContext.getAttribute("mat")).getConDetId()%>">
        <input type="hidden" name="venId" value="<%=((MaterialInContractFormBean) pageContext.getAttribute("mat")).getVenId()%>">
        <input type="hidden" name="reqId" value="<%=((MaterialInContractFormBean) pageContext.getAttribute("mat")).getReqId()%>">
        <input type="hidden" name="reqDetailId" value="<%=((MaterialInContractFormBean) pageContext.getAttribute("mat")).getReqDetailId()%>">
    </display:column>
    <display:column style="" titleKey="message.contract.number" sortable="true" headerClass="sortable">
        <a href="#" onclick="return printPrinciple(<%=((MaterialInContractFormBean) pageContext.getAttribute("mat")).getConId()%>);"><%=((MaterialInContractFormBean) pageContext.getAttribute("mat")).getConNumber()%></a>
    </display:column>
    <display:column property="venName" titleKey="message.u_vendor" sortable="true"/>
    <display:column property="reqNumber" titleKey="message.request.number" sortable="true"/>
    <display:column property="project" titleKey="message.request.whichUse" sortable="true"/>
    <display:column property="materialGroup" titleKey="message.vendor.material.group" sortable="true"/>
    <display:column property="matCode" titleKey="message.material.code"/>
    <display:column property="matName" titleKey="message.material.nameVn"/>
    <display:column property="unit" titleKey="message.material.uniId" sortable="true"/>
    <display:column property="quantityString" titleKey="message.request.material.additionalQuantity"/>
    <display:column property="priceText" titleKey="message.contract.material.price"/>
    <display:column property="provideDate" titleKey="message.request.material.provideDate" sortable="true"/>
    <display:column property="responseEmployeeName" titleKey="message.contract.responseEmp.abbrevation"/>
    <display:column titleKey="message.empty">
        <input type="button" onclick="return cancelRequestRemainQuantity(<%=((MaterialInContractFormBean) pageContext.getAttribute("mat")).getReqDetailId()%>,loadMaterialInAdjustmentContracts)" value='<bean:message key="message.cancel"/>'/>
    </display:column>
</display:table>
