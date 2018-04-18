<%@page import="com.venus.mc.util.PermissionUtil"%>
<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>
<table id="tenderPlanMaterialTable"  style="width:100%">
    <logic:iterate id="material" name="<%=Constants.MATERIAL_LIST%>">
        <tr>
            <td>
                <div align="center"><input type="checkbox" name="detId" value="0"/></div>
                <input type="hidden" name="tenDetId" value="0"/>
                <input type="hidden" name="reqDetId" value="${material.reqDetailId}"/>
                <input type="hidden" name="reqId" value="${material.reqId}"/>
                <html:hidden name="material" property="matId"/>
                <input type="hidden" id="maxMatQuantity${material.reqDetailId}" value="${material.remainQuantity}"/>
            </td>
            <td></td>
            <td><span><bean:write name="material" property="matCode"/></span></td>
            <td width="100px"><span><bean:write name="material" property="matName"/></span></td>
            <td>
                <span><bean:write name="material" property="unit"/></span>
                <input type="hidden" name="matUnit" value="${material.unit}"/>
            </td>
            <td width="50px"><input type="text" class="lbl10" style="border-width:1px;text-align:right" size="5" disabled value="${material.requestQuantity}"/></td>
            <td width="50px"><input type="text" class="lbl10" style="border-width:1px;text-align:right" size="5" id="matQuantity${material.reqDetailId}" name="matQuantity" onkeypress="return numbersonly(this, event)" value="${material.remainQuantity}" onblur="return checkTenderplanMatValidQuantity(${material.reqDetailId});tryNumberFormat(this)" /></td>
            <td width="70px"><input type="text" class="lbl10" style="border-width:1px;text-align:right" readonly size="8" name="matPrice" value="${material.price}"/></td>
            <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_LIST, PermissionUtil.PER_LIBRARY_MATERIAL_PRICE)) {%>
            <td width="80px"><input type="text" class="lbl10" style="border-width:1px;text-align:right" readonly size="9" name="matTotal" value="${material.total}"/></td>
            <td width="80px"><input type="textbox" class="lbl10" style="border-width:1px;text-align:right" size="9" name="matProvideDate" id="provideDate${material.matId}_${material.reqDetailId}" value="${material.provideDate}"/></td>
            <%} else { %>
            <input type="text" hidden="" name="matTotal" value="${material.total}"/></td>
            <input type="text" hidden="" name="matProvideDate" value="${material.total}"/></td>
            <%}%>
            <td><bean:write name="material" property="requestNumber"/></td>
            <td width="40px"><bean:write name="material" property="projectName"/></td>
        </tr>
    </logic:iterate>
</table>