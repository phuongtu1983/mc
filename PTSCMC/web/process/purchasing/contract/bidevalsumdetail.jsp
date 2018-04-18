<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<%
    int isPricePer = 0;
    if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_LIST, PermissionUtil.PER_LIBRARY_MATERIAL_PRICE)) {
        isPricePer = 1;
    }
%>
<table id="bidEvalSumDetailDetailTable">
    <logic:iterate id="detail" name="<%=Constants.MATERIAL_LIST%>">
        <tr>
            <td width="30px">
                <div align="center"><input type="checkbox" name="detId" value="0"/></div>
                <input type="hidden" name="conDetId" value="0"/>
                <html:hidden name="detail" property="matId"/>
                <input type="hidden" name="reqDetId" value="<bean:write name="detail" property="reqDetailId"/>"/>
            </td>
            <td></td>
            <td><span><bean:write name="detail" property="matName"/></span></td>
            <td><span><bean:write name="detail" property="requestNumber"/></span></td>
            <td><span><bean:write name="detail" property="matOrigin"/></span></td>
            <td>
                <span><bean:write name="detail" property="unit"/></span>
                <html:hidden name="detail" property="unit"/>
            </td>
            <td width="60px"><html:text styleClass="lbl10" style="border-width:1px;text-align:right" size="8" name="detail" property="quantity" styleId="detquantity${detail.matId}_${detail.reqDetailId}"  onblur="tryNumberFormat(this);caculateContractDetail('${detail.matId}_${detail.reqDetailId}')" /></td>
            <%if (isPricePer == 1) {%>
            <td width="40px"><html:text styleClass="lbl10" style="border-width:1px;text-align:right" size="8" name="detail" property="price" styleId="detprice${detail.matId}_${detail.reqDetailId}"  onblur="tryNumberFormat(this);caculateContractDetail('${detail.matId}_${detail.reqDetailId}')" /></td>
            <td width="40px"><html:text styleClass="lbl10" style="border-width:1px;text-align:right" size="8" name="detail" property="vat" styleId="detvat${detail.matId}_${detail.reqDetailId}"  onblur="caculateContractDetail('${detail.matId}_${detail.reqDetailId}')"/></td>
            <td width="40px"><input type="text" class="lbl10" style="border-width:1px;text-align:right"  size="10" name="detTotal" readonly id="detTotal<bean:write name="detail" property="matId"/>_<bean:write name="detail" property="reqDetailId"/>" value="<bean:write name="detail" property="total"/>"/></td>
                <%} else {%>
                <html:hidden property="price" name="detail"/>
                <html:hidden property="vat" name="detail"/>
                <input type="hidden" name="detTotal"/>
                <%}%>
            <td width="80px">
                <html:select property="matStatus" name="detail">
                    <html:options collection="<%=Constants.PRINCIPLE_MATERIAL_KIND_LIST%>" property="value" labelProperty="label"/>
                </html:select>
            </td>
        </tr>
    </logic:iterate>
</table>