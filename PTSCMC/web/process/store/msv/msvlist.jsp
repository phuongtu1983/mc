<%@ taglib prefix="display" uri="/tags/displaytag"%>
<%@ page import="com.venus.mc.bean.MsvBean"%>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<display:table style="width:100%" pagesize="15" requestURI="javascript:loadMsvListSort({})" name="<%=Constants.MSV_LIST%>" id="msv" class="its" >
    <display:setProperty name="paging.banner.items_name" value="" />
    <display:setProperty name="paging.banner.item_name" value="" />
    <display:column titleKey="message.del">
        <input type="checkbox" name="msvId" value="<%=((MsvBean)pageContext.getAttribute("msv")).getMsvId()%>">
    </display:column>

    <display:column titleKey="message.msv.number">
        <a href="#" onclick="msvForm(<%=((MsvBean)pageContext.getAttribute("msv")).getMsvId()%>)"><%=((MsvBean)pageContext.getAttribute("msv")).getMsvNumber()%></a>
    </display:column>
    <display:column property="createdDate" titleKey="message.msv.createddate" />   
    <display:column property="conNumber" titleKey="message.msv.contract" />   
    <display:column property="mrirNumber" titleKey="message.msv.mrirnumber" />
    <display:column property="deliverer" titleKey="message.msv.deliverer" />  
    <display:column property="stoName" titleKey="message.msv.inputstore" />   
    
</display:table>
