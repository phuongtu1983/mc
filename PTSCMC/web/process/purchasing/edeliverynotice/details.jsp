<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>
<table  style="width:100%" class="its" id="ednDetailTable">
    <thead>
        <tr>
            <th><bean:message key="message.del"/></th>            
            <th><bean:message key="message.dn.matName"/></th>
            <th><bean:message key="message.dn.unit"/></th>
            <th><bean:message key="message.dn.quantity"/></th>
            <th><bean:message key="message.dn.note"/></th>
        </tr>
    </thead>
    <tbody>
        <logic:present name="<%=Constants.DN_DETAIL_LIST%>">
            <logic:iterate id="detail" name="<%=Constants.DN_DETAIL_LIST%>">
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
                        <html:hidden name="detail" property="price"/>
                    </td>
                    <td width="7px"><input type="textbox" styleClass="lbl10" style="border-width:1px;text-align:right" size="7" name="quantity" id="detquantity${detail.detId}" value="${detail.quantity}"  onblur="dnCheckQuantity(${detail.detId});" /></td>
                    <td width="20px"><input type="textbox" size="20" name="note" value="${detail.note}"/></td>
                </tr>
            </logic:iterate>
        </logic:present>
    </tbody>
</table>