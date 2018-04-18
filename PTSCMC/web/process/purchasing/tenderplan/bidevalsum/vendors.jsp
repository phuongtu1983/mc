<%@ taglib prefix="display" uri="/tags/displaytag"%>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.venus.mc.bean.TenderEvaluateVendorBean"%>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<display:table style="width:100%" pagesize="15" requestURIcontext="false" name="<%=Constants.TEV_LIST%>" id="vendor" class="its" >
    <display:setProperty name="paging.banner.items_name" value='NCC'/>
    <display:setProperty name="paging.banner.item_name" value="NCC"/>
    <display:column >
        <logic:equal value="0" name="vendor" property="cb">
            <input type="radio" name="besvId" value="<%=((TenderEvaluateVendorBean)pageContext.getAttribute("vendor")).getBesvId()%>">
        </logic:equal>
        <logic:greaterThan value="0" name="vendor" property="cb">
            <input type="radio" disabled name="besvId" value="<%=((TenderEvaluateVendorBean)pageContext.getAttribute("vendor")).getBesvId()%>">
        </logic:greaterThan>
        <input type="hidden" id="besvTenId_${vendor.besvId}" value="${vendor.tenId}"/>
        <input type="hidden" id="besvVenId_${vendor.besvId}" value="${vendor.venId}"/>
    </display:column>
    <display:column titleKey="message.bidevalsum.stt">
        <div align="center"><%=((TenderEvaluateVendorBean)pageContext.getAttribute("vendor")).getStt()%></div>
    </display:column>
    <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_LIST, PermissionUtil.PER_LIBRARY_MATERIAL_PRICE)) {%>
    <display:column titleKey="message.bidevalsum.vendor" ><input type="hidden" name="besvId" value="${vendor.besvId}"/>
        <a href="#" name="a_besvId" onclick="addBidEvalSumVendor(<%=((TenderEvaluateVendorBean)pageContext.getAttribute("vendor")).getBesvId()%>,'<%=((TenderEvaluateVendorBean)pageContext.getAttribute("vendor")).getTenId()%>')"><%=((TenderEvaluateVendorBean)pageContext.getAttribute("vendor")).getVendorName()%></a>
    </display:column>
    <%} else { %>
    <display:column titleKey="message.bidevalsum.vendor" ><input type="hidden" name="besvId" value="${vendor.besvId}"/>
        <div align="center"><%=((TenderEvaluateVendorBean)pageContext.getAttribute("vendor")).getVendorName()%></div>
    </display:column>
    <%}%>
    <display:column titleKey="message.bidevalsum.result" >
        <div align="center"><%=((TenderEvaluateVendorBean)pageContext.getAttribute("vendor")).getResult()%></div>
    </display:column>
    <display:column titleKey="message.bidevalsum.deliveryTime" >
        <div align="center"><%=((TenderEvaluateVendorBean)pageContext.getAttribute("vendor")).getDeliveryTime()%></div>
    </display:column>
    <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_LIST, PermissionUtil.PER_LIBRARY_MATERIAL_PRICE)) {%>
    <display:column titleKey="message.bidevalsum.sumVAT" >
        <div align="center"><%=((TenderEvaluateVendorBean)pageContext.getAttribute("vendor")).getSum()%></div>
    </display:column>
    <display:column titleKey="message.bidevalsum.currency" >
        <div align="center"><%=((TenderEvaluateVendorBean)pageContext.getAttribute("vendor")).getCurrency()%></div>
    </display:column>
    <%}%>
</display:table>

