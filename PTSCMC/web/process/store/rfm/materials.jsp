<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>
<table id="rfmMaterialTable">
    <logic:iterate id="detail" name="<%=Constants.MATERIAL_LIST%>">
        <tr>
            <td width="30px">
                <div align="center"><input type="checkbox" name="detId" value="0"/></div>
                <input type="hidden" name="delDetId" value="0"/>
                <html:hidden name="detail" property="matId"/>
            </td>
            <td><span><bean:write name="detail" property="matName"/></span></td>
            <td>
                <span><bean:write name="detail" property="unit"/></span>
                <html:hidden name="detail" property="unit"/>
                <html:hidden name="detail" property="price"/>
                <html:hidden name="detail" property="msvId"/>
                <html:hidden name="detail" property="availableQuantity" styleId="detqt${detail.detId}${detail.reqDetailId}"/>
                <html:hidden name="detail" property="qtTemp" styleId="detqt1${detail.detId}${detail.reqDetailId}"/>
            </td>
            <td width="7px"><input type="textbox" styleClass="lbl10" style="border-width:1px;text-align:right" size="7" name="quantity" id="detquantity${detail.detId}${detail.reqDetailId}" value="${detail.quantity}"   onblur="rfmCheckQuantity('${detail.detId}${detail.reqDetailId}');"/></td>
            <td><span><bean:write name="detail" property="msvNumber"/></span><html:hidden name="detail" property="msvNumber"/><html:hidden name="detail" property="reqDetailId"/></td>
            <logic:equal value="0" name="detail" property="kind">
                <td><span><bean:write name="detail" property="requestNumber"/></span></td>
            </logic:equal>
            <td width="120px"><input type="textbox" size="20" name="comment" id="detTotal${detail.detId}" value="${detail.comment}"/></td>
        </tr>
    </logic:iterate>
</table>