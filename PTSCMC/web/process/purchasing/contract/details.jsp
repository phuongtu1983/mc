<%@page import="com.venus.mc.util.PermissionUtil"%>
<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>
<table style="width:100%" id="contractDetailTable" class="its">
    <thead>
        <tr>
            <th width="20px"></th>
            <th><bean:message key="message.rowNum"/></th>
            <th><bean:message key="message.contract.material.name"/></th>
            <th><bean:message key="message.request"/></th>
            <th><bean:message key="message.contract.material.origin"/></th>
            <th><bean:message key="message.contract.material.unit"/></th>
            <th><bean:message key="message.contract.material.quantity"/></th>
            <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_LIST, PermissionUtil.PER_LIBRARY_MATERIAL_PRICE)) {%>
            <th><bean:message key="message.contract.material.price"/></th>
            <th><bean:message key="message.contract.material.VAT"/></th>
            <th><bean:message key="message.contract.material.total"/></th>
            <%}%>
            <th><bean:message key="message.status"/></th>
        </tr>
    </thead>
    <tbody>
        <logic:present name="<%=Constants.CONTRACT_DETAIL_LIST%>">
            <logic:iterate id="detail" indexId="counter" name="<%=Constants.CONTRACT_DETAIL_LIST%>">
                <bean:define id="mod2" value="${counter%2}"/>
                <logic:equal name="mod2" value="1"><tr class="odd"></logic:equal>
                <logic:equal name="mod2" value="0"><tr class="even"></logic:equal>
                    <td width="30px">
                        <logic:equal value="0" name="detail" property="cb">
                            <div align="center"><input type="checkbox" name="detId" value="<bean:write name="detail" property="detId"/>"/></div>
                            </logic:equal>
                            <logic:greaterThan value="0" name="detail" property="cb">
                            <div align="center"><input type="checkbox" disabled name="detId" value="<bean:write name="detail" property="detId"/>"/></div>
                            </logic:greaterThan>
                        <input type="hidden" name="conDetId" value="<bean:write name="detail" property="detId"/>"/>
                        <html:hidden name="detail" property="matId"/>
                        <input type="hidden" name="reqDetId" value="<bean:write name="detail" property="reqDetailId"/>"/>
                    </td>
                    <td width="10px"><bean:write name="detail" property="stt"/></td>
                    <td><span><bean:write name="detail" property="matName"/></span></td>
                    <td><span><bean:write name="detail" property="requestNumber"/></span></td>
                    <td><span><bean:write name="detail" property="matOrigin"/></span></td>
                    <td>
                        <span><bean:write name="detail" property="unit"/></span>
                        <html:hidden name="detail" property="unit"/>
                    </td>
                    <td width="30px"><html:text styleClass="lbl10" style="border-width:1px;text-align:right" size="4" name="detail" property="quantity" styleId="detquantity${detail.matId}_${detail.reqDetailId}"/></td>
                    <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_LIST, PermissionUtil.PER_LIBRARY_MATERIAL_PRICE)) {%>
                    <td width="40px"><html:text styleClass="lbl10" style="border-width:1px;text-align:right" size="10" name="detail" property="price" styleId="detprice${detail.matId}_${detail.reqDetailId}" onblur="tryNumberFormat(this);caculateContractDetail('${detail.matId}_${detail.reqDetailId}')"/></td>
                    <td width="30px"><html:text styleClass="lbl10" style="border-width:1px;text-align:right" size="3" name="detail" property="vat" styleId="detvat${detail.matId}_${detail.reqDetailId}" onblur="caculateContractDetail('${detail.matId}_${detail.reqDetailId}')"/></td>
                    <td width="40px"><input type="text" class="lbl10" style="border-width:1px;text-align:right" size="10" name="detTotal" readonly id="detTotal<bean:write name="detail" property="matId"/>_<bean:write name="detail" property="reqDetailId"/>" value="<bean:write name="detail" property="total"/>"/></td>

                    <%} else {%>
                    <html:hidden property="price" name="detail"/>
                    <html:hidden property="vat" name="detail"/>
                    <input type="text" hidden="" name="detTotal" readonly id="detTotal" />
                    <%}%>
            <td width="80px">
                <logic:equal name="detail" property="matStatus" value="1">
                    <html:select property="matStatus" name="detail">
                        <html:options collection="<%=Constants.PRINCIPLE_MATERIAL_KIND_LIST%>" property="value" labelProperty="label"/>
                    </html:select>
                </logic:equal>
                <logic:equal name="detail" property="matStatus" value="2">
                    <html:select property="matStatus" name="detail">
                        <html:options collection="<%=Constants.PRINCIPLE_MATERIAL_CANCEL_KIND_LIST%>" property="value" labelProperty="label"/>
                    </html:select>
                </logic:equal>
            </td>
        </tr>
    </logic:iterate>
</logic:present>
</tbody>
</table>
