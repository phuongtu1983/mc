<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>
<table style="width:100%" width="100%" class="its" id="intermemoDetailTable">
    <thead>
        <tr>
            <th><bean:message key="message.del"/></th>
            <th><bean:message key="message.request.material.name"/></th>
            <th><bean:message key="message.request.material.unit"/></th>
            <th><bean:message key="message.request.material.presentQuantity"/></th>
            <th><bean:message key="message.request.material.additionalQuantity"/></th>
            <th><bean:message key="message.request.material.requestQuantity"/></th>
            <th><bean:message key="message.request.materialKind"/></th>
            <th><bean:message key="message.note"/></th>
        </tr>
    </thead>
    <tbody>
        <logic:iterate id="detail" name="<%=Constants.REQUEST_DETAIL_LIST%>">
            <tr>
                <td width="30px">
                    <div align="center"><input type="checkbox" name="detId" value='<bean:write name="detail" property="detId"/>'/></div>
                    <input type="hidden" name="reqDetId" value='<bean:write name="detail" property="detId"/>'/>
                    <html:hidden name="detail" property="matId"/>
                </td>
                <td><span><bean:write name="detail" property="matName"/></span></td>
                <td>
                    <span><bean:write name="detail" property="unit"/></span>
                    <html:hidden name="detail" property="unit"/>
                </td>
                <td width="60px">
                    <span><bean:write name="detail" property="presentQuantity"/></span>
                    <html:hidden name="detail" property="presentQuantity"/>
                </td>
                <td width="80px"><html:text styleClass="lbl10" style="border-width:1px;text-align:right" size="10" name="detail" property="additionalQuantity"  onblur="return tryNumberFormat(this)" /></td>
                <td width="80px"><html:text styleClass="lbl10" style="border-width:1px;text-align:right" size="10" name="detail" property="requestQuantity"  onblur="return tryNumberFormat(this)" /></td>
                <td>
                    <html:select property="materialKind" name="detail">
                        <html:options collection="<%=Constants.REQUEST_MATERIAL_KIND_LIST%>" property="value" labelProperty="label"/>
                    </html:select>
                </td>
                <td width="120px"><input name="note" class="lbl10" style="border-width:1px;text-align:right" size="40" value='<bean:write name="detail" property="intermemoNote"/>' type="text"></td>
            </tr>
        </logic:iterate>
    </tbody>
</table>