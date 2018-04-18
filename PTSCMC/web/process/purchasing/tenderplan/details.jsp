<%@page import="com.venus.mc.util.PermissionUtil"%>
<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>
<table  style="width:100%" class="its" id="tenderPlanDetailTable">
    <thead>
        <tr>
            <th><bean:message key="message.del"/></th>
            <th><bean:message key="message.rowNum"/></th>
            <th><bean:message key="message.material.code"/></th>
            <th><bean:message key="message.request.material.name"/></th>
            <th><bean:message key="message.request.material.unit"/></th>
            <th><bean:message key="message.request.material.requestQuantity"/></th>
            <th><bean:message key="message.request.material.additionalQuantity"/></th>
            <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_LIST, PermissionUtil.PER_LIBRARY_MATERIAL_PRICE)) {%>
            <th><bean:message key="message.contract.material.price"/></th>
            <th><bean:message key="message.contract.material.total"/></th>
            <%}%>
            <th><bean:message key="message.request.material.provideDate"/></th>
            <th><bean:message key="message.tenderplan.request.number"/></th>
            <th><bean:message key="message.project"/></th>
        </tr>
    </thead>
    <tbody>
        <logic:iterate id="detail" name="<%=Constants.TENDERPLAN_DETAIL_LIST%>">
            <tr>
                <td width="30px">
                    <div align="center"><input type="checkbox" name="detId" value="${detail.detId}"/></div>
                    <input type="hidden" name="tenDetId" value="${detail.detId}"/>
                    <input type="hidden" name="reqDetId" value="${detail.reqDetailId}"/>
                    <input type="hidden" name="reqId" value="${detail.reqId}"/>
                    <html:hidden name="detail" property="matId"/>
                    <input type="hidden" id="maxMatQuantity${detail.reqDetailId}" value="${detail.remainQuantity}"/>
                </td>
                <td width="10px"><span><bean:write name="detail" property="note"/></span></td>
                <td width="50px"><span><bean:write name="detail" property="matCode"/></span></td>
                <td width="100px"><span><bean:write name="detail" property="matName"/></span></td>
                <td width="30px">
                    <span><bean:write name="detail" property="unit"/></span>
                    <input type="hidden" name="matUnit" value="${detail.unit}"/>
                </td>
                <td width="50px"><input type="text" class="lbl10" style="border-width:1px;text-align:right" size="5" disabled value="${detail.requestQuantity}" name="requestQuantity"/></td>
                <td width="50px"><input type="text" class="lbl10" style="border-width:1px;text-align:right" size="5" id="matQuantity${detail.reqDetailId}" name="matQuantity" value="${detail.quantity}" onkeypress="return numbersonly(this, event)" onblur="return checkTenderplanMatValidQuantity(${detail.reqDetailId});tryNumberFormat(this)" /></td>
                    <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_LIST, PermissionUtil.PER_LIBRARY_MATERIAL_PRICE)) {%>
                <td width="70px"><input type="text" class="lbl10" style="border-width:1px;text-align:right" readonly size="8" name="matPrice" value="${detail.price}"/></td>
                <td width="80px"><input type="text" class="lbl10" style="border-width:1px;text-align:right" readonly size="9" name="matTotal" value="${detail.total}"/></td>
                    <%} else {%>
        <input type="text" hidden="" name="matPrice" value="${detail.price}"/></td>
    <input type="text" hidden="" name="matTotal" value="${detail.total}"/></td>
    <%}%>
<td width="60px"><input type="textbox" class="lbl10" style="border-width:1px;text-align:right" size="9" name="matProvideDate" id="provideDate${detail.matId}" onclick="javascript:showCalendar('provideDate${detail.matId}')" value="${detail.provideDate}"/></td>
<td width="30px"><bean:write name="detail" property="requestNumber"/></td>
<td width="100px"><bean:write name="detail" property="projectName"/></td>
</tr>
</logic:iterate>
</tbody>
</table>