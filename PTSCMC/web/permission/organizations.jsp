<%@ taglib prefix="display" uri="/tags/displaytag"%>
<%@ page import="com.venus.mc.bean.OrganizationBean"%>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<table id="permissionOrgTbl" class="its"  style="width:100%">
    <thead>
        <tr>
            <th width="20px"><bean:message key="message.del"/></th>
            <th><bean:message key="message.organization.name"/></th>
        </tr>
    </thead>
    <tbody>
        <logic:iterate id="org" name="<%=Constants.PERMISSION_ORG_LIST%>">
            <tr>
                <td>
                    <input type="checkbox" name="permissionOrgChk" value="<%=((OrganizationBean)pageContext.getAttribute("org")).getOrgId()%>">
                    <input type="hidden" name="permissionOrgId" value="<bean:write name="org" property="orgId"/>"/>
                </td>
                <td><span name="permissionOrg"><bean:write name="org" property="name"/></span></td>
            </tr>
        </logic:iterate>
    </tbody>
</table>