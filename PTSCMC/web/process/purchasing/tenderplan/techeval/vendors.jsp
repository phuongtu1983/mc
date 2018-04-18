<%@ taglib prefix="display" uri="/tags/displaytag"%>
<%@ page import="com.venus.mc.bean.TenderEvaluateVendorBean"%>
<%@ page import="com.venus.mc.util.Constants"%>
<display:table style="width:100%" pagesize="15" requestURIcontext="false" name="<%=Constants.TEV_LIST%>" id="vendor" class="its" >
    <display:setProperty name="paging.banner.items_name" value='NCC'/>
    <display:setProperty name="paging.banner.item_name" value="NCC"/>
    <display:setProperty name="basic.msg.empty_list_row" value=""/>
    <display:setProperty name="paging.banner.onepage" value=""/>
    <display:setProperty name="paging.banner.no_items_found" value=""/>
    <display:column titleKey="message.techeval.stt">
        <div align="center"><%=((TenderEvaluateVendorBean) pageContext.getAttribute("vendor")).getStt()%></div>
    </display:column>
    <display:column titleKey="message.techeval.vendor" >
        <a href="#" name="terId" onclick="addTechEvalVendor(<%=((TenderEvaluateVendorBean) pageContext.getAttribute("vendor")).getTerId()%>,'<%=((TenderEvaluateVendorBean) pageContext.getAttribute("vendor")).getVenId()%>')"><%=((TenderEvaluateVendorBean) pageContext.getAttribute("vendor")).getVendorName()%></a>
    </display:column>
    <display:column titleKey="message.techeval.evalTbe" >
        <div align="center"><input type="text" disabled name="result"  value="<%=((TenderEvaluateVendorBean) pageContext.getAttribute("vendor")).getResult()%>">
    </display:column>    
</display:table>