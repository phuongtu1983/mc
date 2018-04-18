<%-- 
    Document   : mrirRequestVendor
    Created on : Sep 27, 2009, 8:16:48 PM
    Author     : kngo
--%>
<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib prefix="display" uri="/tags/displaytag" %>
<%@ page import="com.venus.mc.util.MCUtil"%>
<%@ page import="com.venus.mc.util.Constants"%>
<logic:equal value="0" name="<%=Constants.MRIR%>" property="dnId">
<table width="100%">
<tr>    
    <td class="lbl10"><bean:message key="message.mrir.venId"/></td>
    <td colspan="2"><html:text styleClass="lbl10" size="40" property="venName" name="<%=Constants.MRIR%>" disabled="true" /></td>
    <td class="lbl10"><bean:message key="message.mrir.reqId"/></td>
    <td colspan="2">
        <html:select styleClass="lbl10" property="reqId" name="<%=Constants.MRIR%>" onchange="return reqIdChangedMrir(this);" disabled="true">
            <html:options styleClass="lbl10" collection="<%=Constants.MRIR_REQUEST_LIST%>" property="reqId" labelProperty="requestNumber"/>
        </html:select>
    </td>
</tr>
</table>
</logic:equal>
<logic:notEqual value="0" name="<%=Constants.MRIR%>" property="dnId">
<table width="100%">
<tr>     
    <td class="lbl10"><bean:message key="message.mrir.venId"/></td>
    <td colspan="2"><html:text styleClass="lbl10" size="50" property="venName" name="<%=Constants.MRIR%>" disabled="true" /></td>
    <td class="lbl10"><bean:message key="message.mrir.reqId"/></td>
    <td colspan="2">
        <logic:equal value="0" name="<%=Constants.MRIR%>" property="mrirId">
            <html:select styleClass="lbl10" property="reqId" name="<%=Constants.MRIR%>" onchange="return reqIdChangedMrir(this);">
                <html:options styleClass="lbl10" collection="<%=Constants.MRIR_REQUEST_LIST%>" property="reqId" labelProperty="requestNumber"/>
            </html:select>
        </logic:equal>
        <logic:notEqual value="0" name="<%=Constants.MRIR%>" property="mrirId">
            <html:select styleClass="lbl10" property="reqId" name="<%=Constants.MRIR%>" disabled="true" onchange="return reqIdChangedMrir(this);">
                <html:options styleClass="lbl10" collection="<%=Constants.MRIR_REQUEST_LIST%>" property="reqId" labelProperty="requestNumber"/>
            </html:select>
        </logic:notEqual>
        
    </td>
</tr>
</table>    
</logic:notEqual>      
