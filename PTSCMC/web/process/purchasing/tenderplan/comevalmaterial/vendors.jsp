<%@ taglib prefix="display" uri="/tags/displaytag"%>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<%@ page import="com.venus.mc.bean.TenderEvaluateVendorBean"%>
<%@ page import="com.venus.mc.util.Constants"%>
<display:table style="width:100%" pagesize="15" requestURIcontext="false" name="<%=Constants.TEV_LIST%>" id="vendor" class="its" >
    <display:setProperty name="paging.banner.items_name" value='NCC'/>
    <display:setProperty name="paging.banner.item_name" value="NCC"/>
    <display:column titleKey="message.comevalmaterial.stt">
        <div align="center"><%=((TenderEvaluateVendorBean)pageContext.getAttribute("vendor")).getStt()%></div>
    </display:column>
    <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_LIST, PermissionUtil.PER_LIBRARY_MATERIAL_PRICE)) {%>
    <display:column titleKey="message.comevalmaterial.vendorName" ><input type="hidden" name="cemId" value="${vendor.cemId}"/>
        <a href="#" name="cemId" onclick="addComEvalMaterialVendor(<%=((TenderEvaluateVendorBean)pageContext.getAttribute("vendor")).getCemId()%>,'<%=((TenderEvaluateVendorBean)pageContext.getAttribute("vendor")).getVenId()%>','<%=((TenderEvaluateVendorBean)pageContext.getAttribute("vendor")).getTerId()%>')"><%=((TenderEvaluateVendorBean)pageContext.getAttribute("vendor")).getVendorName()%></a>
    </display:column>
    <display:column titleKey="message.comevalmaterial.ratesAfter" >
        <div align="center"><%=((TenderEvaluateVendorBean)pageContext.getAttribute("vendor")).getRatesAfter()%></div>
    </display:column>  
    <%} else { %>
    <display:column titleKey="message.comevalmaterial.vendorName" ><input type="hidden" name="cemId" value="${vendor.cemId}"/>
        <div align="center"><%=((TenderEvaluateVendorBean)pageContext.getAttribute("vendor")).getVendorName()%></div>
    </display:column> 
        <%}%>

</display:table>

