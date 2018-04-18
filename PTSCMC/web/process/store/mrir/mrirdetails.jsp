<%-- 
    Document   : mrirdetails
    Created on : Sep 24, 2009, 8:41:54 PM
    Author     : kngo
--%>

<%@ taglib prefix="display" uri="/tags/displaytag"%>
<%@ page import="com.venus.mc.bean.MrirDetailBean"%>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<display:table style="width:100%" pagesize="15" requestURI="javascript:loadMrirDetailList({})" name="<%=Constants.MRIR_DETAIL_LIST%>" id="mrir_detail" class="its"  >
    <display:setProperty name="paging.banner.items_name"><bean:message key='message.l_mrir_detail'/></display:setProperty>
    <display:setProperty name="paging.banner.item_name"><bean:message key='message.l_mrir_detail'/></display:setProperty>
    <display:column titleKey="message.del">
        <input type="checkbox" name="detId" value="<%=((MrirDetailBean) pageContext.getAttribute("mrir_detail")).getDetId()%>">
    </display:column>
    <display:column property="matId" titleKey="message.mrirdetail.matId" sortable="true" headerClass="sortable"/>
    <display:column property="unit" titleKey="message.mrirdetail.unit" sortable="true" headerClass="sortable"/>
    <display:column property="quantity" titleKey="message.mrirdetail.quantity" sortable="true" headerClass="sortable"/>
    <display:column property="manufacture" titleKey="message.mrirdetail.manufacture" sortable="true" headerClass="sortable"/>
    <display:column property="heatSerial" titleKey="message.mrirdetail.heatSerial" sortable="true" headerClass="sortable"/>
    <display:column property="inspection" titleKey="message.mrirdetail.inspection" sortable="true" headerClass="sortable"/>
    <display:column property="original" titleKey="message.mrirdetail.original" sortable="true" headerClass="sortable"/>
    <display:column property="quality" titleKey="message.mrirdetail.quality" sortable="true" headerClass="sortable"/>
    <display:column property="warranty" titleKey="message.mrirdetail.warranty" sortable="true" headerClass="sortable"/>
    <display:column property="insurance" titleKey="message.mrirdetail.insurance" sortable="true" headerClass="sortable"/>
    <display:column property="approvalType" titleKey="message.mrirdetail.approvalType" sortable="true" headerClass="sortable"/>
    <display:column property="complCert" titleKey="message.mrirdetail.complCert" sortable="true" headerClass="sortable"/>
</display:table>