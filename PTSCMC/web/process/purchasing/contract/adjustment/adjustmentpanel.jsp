<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.bean.ContractBean"%>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<form>
    <table>
        <tr>
            <td>
                <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_DELETE, PermissionUtil.PER_PURCHASING_CONTRACT)) {%>
                <img alt="delete" src="images/ico_xoa.gif" onclick="return delAdjustments(<%=ContractBean.KIND_ADJUSTMENT%>);"/>
                <%}%>
                <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_ADD, PermissionUtil.PER_PURCHASING_CONTRACT)) {%>
                <img alt="add" src="images/ico_them.gif" onclick="return adjustmentForm(<%=ContractBean.KIND_ADJUSTMENT%>);"/>
                <%}%>
            </td>
        </tr>
    </table>
</form>
<form name='adjustmentsForm' id='adjustmentsForm'><div id='adjustmentList'></div></form>