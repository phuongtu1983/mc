<%-- 
    Document   : osddetails
    Created on : Sep 25, 2009, 12:10:04 AM
    Author     : kngo
--%>

<%@ taglib prefix="display" uri="/tags/displaytag"%>
<%@ page import="com.venus.mc.bean.OsDDetailBean"%>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<display:table style="width:100%" pagesize="15" requestURI="javascript:loadOsDDetailList({})" name="<%=Constants.OSD_DETAIL_LIST%>" id="os_d_detail" class="its"  >
    <display:setProperty name="paging.banner.items_name"><bean:message key='message.l_osd_detail'/></display:setProperty>
    <display:setProperty name="paging.banner.item_name"><bean:message key='message.l_osd_detail'/></display:setProperty>
    <display:column titleKey="message.del">
        <input type="checkbox" name="detId" value="<%=((OsDDetailBean) pageContext.getAttribute("os_d_detail")).getDetId()%>">
    </display:column>
    <display:column property="matId" titleKey="message.osddetail.matId" sortable="true" headerClass="sortable"/>
    <display:column property="quantity" titleKey="message.osddetail.quantity" sortable="true" headerClass="sortable"/>
</display:table>
