<%@ taglib prefix="display" uri="/tags/displaytag"%>
<%@ page import="com.venus.mc.bean.VendorEvaluateBean"%>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<display:table style="width:100%" requestURI="javascript:loadVenEvals({})" name="<%=Constants.VENDOR_EVAL_LIST%>" id="vendorEval" class="its" >
    <display:setProperty name="paging.banner.items_name"><bean:message key='message.vendor.evaluate'/></display:setProperty>
    <display:setProperty name="paging.banner.item_name"><bean:message key='message.vendor.evaluate'/></display:setProperty>
    <display:column titleKey="message.del">
        <input type="checkbox" name="venevalid" value="<%=((VendorEvaluateBean)pageContext.getAttribute("vendorEval")).getEvalId()%>">
    </display:column>
    <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_VIEW, PermissionUtil.PER_LIBRARY_VENDOR_EVAL)) {%>
    <display:column titleKey="message.vendor.evaluate.number" sortable="true" headerClass="sortable">
        <a href="#" onclick="venEvalForm(<%=((VendorEvaluateBean)pageContext.getAttribute("vendorEval")).getEvalId()%>)"><%=((VendorEvaluateBean)pageContext.getAttribute("vendorEval")).getEvalNumber()%></a>
    </display:column>
    <%}else{%>
    <display:column property="evalNumber" titleKey="message.vendor.evaluate.number" sortable="true" headerClass="sortable"/>	
    <%}%>
    <display:column property="fromDate" titleKey="message.vendor.evaluate.fromDate"  sortable="true"/>
    <display:column property="toDate" titleKey="message.vendor.evaluate.toDate"  sortable="true"/>
    <display:column property="resultString" titleKey="message.vendor.evaluate.lastResult" sortable="true"/>
    <display:column property="orgName" titleKey="message.vendor.evaluate.organization" sortable="true"/>
</display:table>