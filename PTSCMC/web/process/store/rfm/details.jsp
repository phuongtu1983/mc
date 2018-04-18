<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>
<table  style="width:100%" class="its" id="rfmDetailTable">
    <thead>
        <tr>
            <th><bean:message key="message.del"/></th>            
            <th><bean:message key="message.rfm.matName"/></th>
            <th><bean:message key="message.rfm.unit"/></th>
            <th><bean:message key="message.rfm.quantity"/></th>
            <th><bean:message key="message.rfm.msvNumber"/></th>
            <logic:equal value="0" parameter="kind">
                <th><bean:message key="message.rfm.requestNumber"/></th> 
            </logic:equal>
            <th><bean:message key="message.rfm.comment"/></th>
        </tr>
    </thead>
    <tbody>
        <logic:present name="<%=Constants.RFM_DETAIL_LIST%>">
            <logic:iterate id="detail" name="<%=Constants.RFM_DETAIL_LIST%>">
                <tr>
                    <td width="30px">
                        <div align="center"><input type="checkbox" name="detId" value="<bean:write name="detail" property="detId"/>"/></div>
                        <input type="hidden" name="delDetId" value="<bean:write name="detail" property="detId"/>"/>
                        <html:hidden name="detail" property="matId"/>                        
                    </td>
                    <td><span><bean:write name="detail" property="matName"/></span></td>
                    <td>
                        <span><bean:write name="detail" property="unit"/></span>
                        <html:hidden name="detail" property="unit"/>
                    </td>
                    <td width="7px"><input type="textbox" styleClass="lbl10" style="border-width:1px;text-align:right" size="7" name="quantity" id="detquantity${detail.detId}${detail.reqDetailId}" value="${detail.quantity}"  onblur="rfmCheckQuantity('${detail.detId}${detail.reqDetailId}');" /></td>
                    <td>
                        <span><bean:write name="detail" property="msvNumber"/></span>
                        <html:hidden name="detail" property="msvNumber"/>
                        <html:hidden name="detail" property="msvId"/>
                        <html:hidden name="detail" property="reqDetailId"/>
                        <html:hidden name="detail" property="price"/>
                        <html:hidden name="detail" property="availableQuantity" styleId="detqt${detail.detId}${detail.reqDetailId}"/>
                        <html:hidden name="detail" property="qtTemp" styleId="detqt1${detail.detId}${detail.reqDetailId}"/>
                    </td>
                    <logic:equal value="0" name="detail" property="kind">
                        <td><span><bean:write name="detail" property="requestNumber"/></span></td>
                    </logic:equal>
                    <td width="20px"><input type="textbox" size="20" name="comment" value="${detail.comment}"/></td>
                </tr>
            </logic:iterate>
        </logic:present>
    </tbody>
</table>