<%@page import="com.venus.mc.util.PermissionUtil"%>
<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>
<table  style="width:100%" class="its" id="deliveryRequestDetailTable">
    <thead>
        <tr>
            <th><bean:message key="message.del"/></th>
            <!--<th><bean:message key="message.deliveryrequest.contract"/></th>-->
            <th><bean:message key="message.rowNum"/></th>
            <th><bean:message key="message.material.nameVn"/></th>
            <th><bean:message key="message.request"/></th>
            <th><bean:message key="message.contract.material.origin"/></th>
            <th><bean:message key="message.material.uniId"/></th>
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
            <logic:iterate id="detail" name="<%=Constants.CONTRACT_DETAIL_LIST%>">
                <tr>
                    <td width="30px">                    
                        <logic:equal value="0" name="detail" property="cb">
                            <div align="center"><input type="checkbox" name="detId" value="<bean:write name="detail" property="detId"/>"/></div>
                            </logic:equal>
                            <logic:greaterThan value="0" name="detail" property="cb">
                            <div align="center"><input type="checkbox" disabled name="detId" value="<bean:write name="detail" property="detId"/>"/></div>
                            </logic:greaterThan>                    
                        <input type="hidden" name="delDetId" value="<bean:write name="detail" property="detId"/>"/>
                        <html:hidden name="detail" property="matId"/>
                        <input type="hidden" name="conDetId" value="${detail.detId}"/>
                        <input type="hidden" name="reqDetId" value="${detail.reqDetailId}"/>
                        <input type="hidden" id="reqDetQuantity${detail.detId}" value="${detail.quantity}"/>
                    </td>
                    <td width="10px"><bean:write name="detail" property="stt"/></td>
                    <td><span><bean:write name="detail" property="matName"/></span></td>
                    <td><span><bean:write name="detail" property="requestNumber"/></span></td>
                    <td><span><bean:write name="detail" property="matOrigin"/></span></td>
                    <td>
                        <span><bean:write name="detail" property="unit"/></span>
                        <html:hidden name="detail" property="unit"/>
                    </td>
                    <td width="120px"><input type="textbox"  styleClass="lbl10" style="border-width:1px;text-align:right" size="15" name="quantity" id="detquantity${detail.detId}" onfocus="reformatNumberMoney(this)" value="${detail.quantity}"  onblur="tryNumberFormat(this);quantityDeliveryRequestDetailChanged(${detail.detId})" /></td>
                        <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_LIST, PermissionUtil.PER_LIBRARY_MATERIAL_PRICE)) {%>
                    <td width="120px"><input type="textbox"  styleClass="lbl10" style="border-width:1px;text-align:right" size="15" name="price" id="detprice${detail.detId}" value="${detail.price}"  onblur="tryNumberFormat(this);caculateDeliveryRequestDetail(${detail.detId})" /></td>
                    <td width="30px"><input type="textbox"  styleClass="lbl10" style="border-width:1px;text-align:right" size="3" name="vat" id="detvat${detail.detId}" value="${detail.vat}"  onblur="tryNumberFormat(this);caculateDeliveryRequestDetail(${detail.detId})" /></td>
                    <td width="120px"><input type="textbox" readonly size="15" readonly name="detTotal" id="detTotal${detail.detId}" value="${detail.total}"/></td>
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
<tbody>
</table>