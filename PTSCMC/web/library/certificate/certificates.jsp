<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="display" uri="/tags/displaytag"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.bean.CertificateBean"%>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<display:table style="width:100%" pagesize="15" requestURI="javascript:loadCertificates({})" name="<%=Constants.CER_LIST%>" id="certificate" class="its" >
    <display:setProperty name="paging.banner.items_name"><bean:message key='message.certificate'/></display:setProperty>
    <display:setProperty name="paging.banner.item_name"><bean:message key='message.certificate'/></display:setProperty>
    <display:column titleKey="message.del">
        <div align="center"><input type="checkbox" name="cerId" value="<%=((CertificateBean) pageContext.getAttribute("certificate")).getCerId()%>"></div>
    </display:column>
    <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_VIEW, PermissionUtil.PER_LIBRARY_CERTIFICATE)) {%>
    <display:column titleKey="message.certificate.cerName" sortable="true" headerClass="sortable">
        <a href="#" name="cerId" onclick="addCertificate(<%=((CertificateBean) pageContext.getAttribute("certificate")).getCerId()%>)"><%=((CertificateBean) pageContext.getAttribute("certificate")).getCerName()%></a>
    </display:column>
    <%}else{%>
    <display:column property="cerName" titleKey="message.certificate.cerName" sortable="true" headerClass="sortable"/>	
    <%}%>
</display:table>