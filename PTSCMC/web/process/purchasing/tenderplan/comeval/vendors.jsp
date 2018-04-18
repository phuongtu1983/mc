<%@page import="com.venus.mc.util.PermissionUtil"%>
<%@ taglib prefix="display" uri="/tags/displaytag"%>
<%@ page import="com.venus.mc.bean.TenderEvaluateVendorBean"%>
<%@ page import="com.venus.mc.util.Constants"%>
<display:table style="width:100%" pagesize="15" requestURIcontext="false" name="<%=Constants.TEV_LIST%>" id="vendor" class="its" >
    <display:setProperty name="paging.banner.items_name" value='NCC'/>
    <display:setProperty name="paging.banner.item_name" value="NCC"/>
    <display:column titleKey="message.comeval.stt">
        <div align="center"><%=((TenderEvaluateVendorBean)pageContext.getAttribute("vendor")).getStt()%></div>
    </display:column>
    <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_LIST, PermissionUtil.PER_LIBRARY_MATERIAL_PRICE)) {%>
    <display:column titleKey="message.comeval.vendor" ><input type="hidden" name="cevId" value="${vendor.cevId}"/>
        <a href="#" name="cevId" onclick="addComEvalVendor(<%=((TenderEvaluateVendorBean)pageContext.getAttribute("vendor")).getCevId()%>,'<%=((TenderEvaluateVendorBean)pageContext.getAttribute("vendor")).getVenId()%>')"><%=((TenderEvaluateVendorBean)pageContext.getAttribute("vendor")).getVendorName()%></a>
    </display:column>    
    <display:column titleKey="message.comeval.sum" >
        <div align="center"><%=((TenderEvaluateVendorBean)pageContext.getAttribute("vendor")).getSum()%></div>
    </display:column>
    <%} else { %>
    <display:column titleKey="message.comeval.vendor" ><input type="hidden" name="cevId" value="${vendor.cevId}"/>
        <div align="center"><%=((TenderEvaluateVendorBean)pageContext.getAttribute("vendor")).getVendorName()%></div>
    </display:column>   
        <%}%>
    <display:column titleKey="message.comeval.rand" >
        <div align="center"><%=((TenderEvaluateVendorBean)pageContext.getAttribute("vendor")).getRand()%></div>
    </display:column>
</display:table>

