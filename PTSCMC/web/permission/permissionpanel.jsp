<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<h3><bean:message key="message.permissionlist.title"/></h3>
<div id="errorMessageDiv"></div>
<form>
    <table>
        <tr>
            <td>
                <html:image src="images/ico_xoa.gif" onclick="return delPermissions();"/>
                <html:image src="images/ico_them.gif"  onclick="return permissionForm();"/>
            </td>
        </tr>
    </table>
</form>
<form name='permissionsForm' id='permissionsForm'><div id='permissionList'><div align="center"><img src="img/indicator.gif"/></div></div></form>
