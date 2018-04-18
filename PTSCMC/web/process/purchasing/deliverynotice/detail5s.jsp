<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>
<table  style="width:100%" class="its" id="dnDetailTable">
    <thead>
        <tr>            
            <th><bean:message key="message.dn.matName"/></th>
            <th><bean:message key="message.dn.unit"/></th>
            <th><bean:message key="message.dn.quantity"/></th>
            <th><bean:message key="message.dn.note"/></th>
            <th><bean:message key="message.dn.requestNumber"/></th>
        </tr>
    </thead>
    <tbody>
        <logic:present name="<%=Constants.DN_DETAIL_LIST%>">
            <logic:iterate id="detail" name="<%=Constants.DN_DETAIL_LIST%>">
                <tr>
                        <input type="hidden" name="delDetId" value="<bean:write name="detail" property="detId"/>"/>
                        <html:hidden name="detail" property="matId"/>                    
                    <td><div style="width:200px;"><span><bean:write name="detail" property="matName"/></span></div></td>
                    <td>
                        <div style="width:50px"><span><bean:write name="detail" property="unit"/></span></div>
                        <html:hidden name="detail" property="unit"/>
                        <html:hidden name="detail" property="reqDetailId"/>
                        <html:hidden name="detail" property="price"/>
                    </td>
                    <td><span><bean:write name="detail" property="quantity"/></span></td>
                    <td><span><bean:write name="detail" property="note"/></span></td>
                    <td><span><bean:write name="detail" property="requestNumber"/></span></td>
                </tr>
            </logic:iterate>
        </logic:present>
    </tbody>
</table>