<%@ taglib prefix="display" uri="/tags/displaytag"%>
<%@ page import="com.venus.mc.bean.TechClarificationListBean"%>
<%@ page import="com.venus.mc.util.Constants"%>
<display:table style="width:100%" pagesize="15" requestURIcontext="false" name="<%=Constants.DISCIPLINE_LIST%>" id="discipline" class="its" >
    <display:setProperty name="paging.banner.items_name" value='Ph&#226;n h&#7879;'/>
    <display:setProperty name="paging.banner.item_name" value="Ph&#226;n h&#7879;"/>
    <display:column titleKey="message.del">
        <div align="center"><input type="checkbox" name="tclId" value="<%=((TechClarificationListBean) pageContext.getAttribute("discipline")).getTclId()%>"></div>
    </display:column>
    <display:column titleKey="message.techclarification.discipline" >
        <a href="#" name="tclId" onclick="addTechClarificationDetail(<%=((TechClarificationListBean) pageContext.getAttribute("discipline")).getTclId()%>)"><%=((TechClarificationListBean) pageContext.getAttribute("discipline")).getDiscipline()%></a>
    </display:column>
    <display:column titleKey="message.techclarification.category" >
        <div align="center"><%=((TechClarificationListBean) pageContext.getAttribute("discipline")).getCategory()%></div>
    </display:column>
    <display:column titleKey="message.techclarification.notes" >
        <div align="center"><%=((TechClarificationListBean) pageContext.getAttribute("discipline")).getNote()%></div>
    </display:column>
</display:table>