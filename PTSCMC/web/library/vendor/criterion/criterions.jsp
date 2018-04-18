<%@ taglib prefix="display" uri="/tags/displaytag"%>
<%@ page import="com.venus.mc.bean.EvaluateCriterionBean"%>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<display:table style="width:100%" pagesize="15" requestURIcontext="false" name="<%=Constants.EVAL_CRITERION%>" id="criterion" class="its">
    <display:column titleKey="message.del">
        <input type="checkbox" name="criterionId" value="<%=((EvaluateCriterionBean)pageContext.getAttribute("criterion")).getCriId()%>">
    </display:column>
    <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_VIEW, PermissionUtil.PER_LIBRARY_VENDOR)) {%>
    <display:column titleKey="message.vendor.criterion.name">
        <a href="#" onclick="criterionForm(<%=((EvaluateCriterionBean)pageContext.getAttribute("criterion")).getCriId()%>)"><%=((EvaluateCriterionBean)pageContext.getAttribute("criterion")).getName()%></a>
    </display:column>
    <%}else{%>
    <display:column property="name" titleKey="message.vendor.criterion.name" sortable="true" headerClass="sortable"/>	
    <%}%>
    <display:column property="reach" titleKey="message.vendor.criterion.reach" />
    <display:column property="notReach" titleKey="message.vendor.criterion.notreach" />
    <display:column property="comments" titleKey="message.note"/>
</display:table>