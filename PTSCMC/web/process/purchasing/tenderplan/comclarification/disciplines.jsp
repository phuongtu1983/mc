<%@ taglib prefix="display" uri="/tags/displaytag"%>
<%@ page import="com.venus.mc.bean.ComClarificationListBean"%>
<%@ page import="com.venus.mc.util.Constants"%>
<display:table style="width:100%" pagesize="15" requestURIcontext="false" name="<%=Constants.DISCIPLINE_LIST%>" id="discipline" class="its" >
    <display:setProperty name="paging.banner.items_name" value='h&#7841;ng m&#7909;c'/>
    <display:setProperty name="paging.banner.item_name" value="h&#7841;ng m&#7909;c"/>
    <display:column titleKey="message.del">
        <div align="center"><input type="checkbox" name="cclId" value="<%=((ComClarificationListBean)pageContext.getAttribute("discipline")).getCclId()%>"></div>
        </display:column>
        <display:column titleKey="message.comclarification.reference" >
        <a href="#" name="cclId" onclick="addComClarificationDetail(<%=((ComClarificationListBean)pageContext.getAttribute("discipline")).getCclId()%>)"><%=((ComClarificationListBean)pageContext.getAttribute("discipline")).getReference()%></a>
    </display:column>
    <display:column titleKey="message.comclarification.clarification" >
        <div align="center"><%=((ComClarificationListBean)pageContext.getAttribute("discipline")).getClarification()%></div>
    </display:column>
    <display:column titleKey="message.comclarification.supplierReply" >
        <div align="center"><%=((ComClarificationListBean)pageContext.getAttribute("discipline")).getSupplierReply()%></div>
    </display:column>
    <display:column titleKey="message.comclarification.status" >
        <div align="center"><%=((ComClarificationListBean)pageContext.getAttribute("discipline")).getStatus()%></div>
    </display:column>
</display:table>

