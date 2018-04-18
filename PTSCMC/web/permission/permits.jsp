<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ page import="com.venus.mc.bean.PermissionBean"%>
<table style="width:100%" id="permissionTable" class="its">
    <thead>
        <tr>
            <th width="20px"><bean:message key="message.rowNum"/></th>
            <th width="200px"><bean:message key="message.permission.func"/></th>
            <th width="50px"><bean:message key="message.permission.permit.list"/></th>
            <th width="50px"><bean:message key="message.permission.permit.add"/></th>
            <th width="50px"><bean:message key="message.permission.permit.delete"/></th>
            <th width="50px"><bean:message key="message.permission.permit.edit"/></th>
            <th width="50px"><bean:message key="message.permission.permit.view"/></th>
        </tr>
    </thead>
    <tbody>
        <logic:present name="<%=Constants.PERMISSION_FUNC_LIST%>">
            <logic:iterate id="detail" name="<%=Constants.PERMISSION_FUNC_LIST%>">
                <tr>
                <bean:define id="level" value="${detail.level}"/>
                <logic:equal name="level" value="0">
                    <td><div>
                        <strong>${detail.counter}</strong>
                    </div></td>
                    <td><strong><bean:write name="detail" property="name"/></strong></td>
                    <td><div align="center"><input type="checkbox" id="funcList${detail.sharedId}" onchange="return permissionLevel1Change(this,'funcList')" value="${detail.value}"/></div></td>
                    <td><div align="center"><input type="checkbox" id="funcAdd${detail.sharedId}" onchange="return permissionLevel1Change(this,'funcAdd')" value="${detail.value}"/></div></td>
                    <td><div align="center"><input type="checkbox" id="funcDelete${detail.sharedId}" onchange="return permissionLevel1Change(this,'funcDelete')" value="${detail.value}"/></div></td>
                    <td><div align="center"><input type="checkbox" id="funcEdit${detail.sharedId}" onchange="return permissionLevel1Change(this,'funcEdit')" value="${detail.value}"/></div></td>
                    <td><div align="center"><input type="checkbox" id="funcView${detail.sharedId}" onchange="return permissionLevel1Change(this,'funcView')" value="${detail.value}"/></div></td>
                </logic:equal>
                <logic:equal name="level" value="1">
                    <td><div>
                        <strong>${detail.counter}</strong>
                    </div></td>
                    <td><strong><bean:write name="detail" property="name"/></strong></td>
                    <td><div align="center"><html:multibox property="funcList" name="<%=Constants.PERMISSION%>">${detail.value}</html:multibox></div></td>
                    <td><div align="center"><html:multibox property="funcAdd" name="<%=Constants.PERMISSION%>">${detail.value}</html:multibox></div></td>
                    <td><div align="center"><html:multibox property="funcDelete" name="<%=Constants.PERMISSION%>">${detail.value}</html:multibox></div></td>
                    <td><div align="center"><html:multibox property="funcEdit" name="<%=Constants.PERMISSION%>">${detail.value}</html:multibox></div></td>
                    <td><div align="center"><html:multibox property="funcView" name="<%=Constants.PERMISSION%>">${detail.value}</html:multibox></div></td>
                </logic:equal>
                <logic:equal name="level" value="2">
                    <td><div>${detail.counter}</div></td>
                    <td>&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name="detail" property="name"/></td>
                    <td><div align="center"><html:multibox styleId="funcList${detail.sharedId}" property="funcList" name="<%=Constants.PERMISSION%>">${detail.value}</html:multibox></div></td>
                    <td><div align="center"><html:multibox styleId="funcAdd${detail.sharedId}" property="funcAdd" name="<%=Constants.PERMISSION%>">${detail.value}</html:multibox></div></td>
                    <td><div align="center"><html:multibox styleId="funcDelete${detail.sharedId}" property="funcDelete" name="<%=Constants.PERMISSION%>">${detail.value}</html:multibox></div></td>
                    <td><div align="center"><html:multibox styleId="funcEdit${detail.sharedId}" property="funcEdit" name="<%=Constants.PERMISSION%>">${detail.value}</html:multibox></div></td>
                    <td><div align="center"><html:multibox styleId="funcView${detail.sharedId}" property="funcView" name="<%=Constants.PERMISSION%>">${detail.value}</html:multibox></div></td>
                </logic:equal>
                </tr>
            </logic:iterate>
        </logic:present>
    </tbody>
</table>