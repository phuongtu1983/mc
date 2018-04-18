<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.MCUtil"%>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.venus.mc.bean.OrganizationBean"%>
<%@ page import="com.venus.mc.process.purchasing.deliverynotice.DnFormBean"%>
<form name="searchDnForm">
   <table width="100%">
        <tr>
            <td><bean:message key="message.dn.dnNumber"/></td>
            <td><html:text size="40" property="dnNumber" name="<%=Constants.DN%>"/></td>
        </tr>
        <tr>
            <td class="lbl10"><bean:message key="message.dn.contractNumber"/></td>
            <td>
                <html:select styleClass="lbl10" property="whichUse" name="<%=Constants.DN%>" onchange="return whichUseChangedDn(this);">
                    <html:options styleClass="lbl10" collection="<%=Constants.DN_WHICHUSE_LIST%>" property="value" labelProperty="label"/>
                </html:select>
                <span name="whichUseSpan" id="whichUseSpan">
                    <select name="conId"></select>
                </span>
            </td>
            <td><bean:message key="message.rfm.orgName"/></td>
            <td><html:select property="createdOrg" name="<%=Constants.DN%>" >
                    <html:options collection="<%=Constants.ORG_LIST%>" property="value" labelProperty="label"/>
                </html:select>
            </td>
        </tr>
        <tr>
            <td><bean:message key="message.dn.expectedDate"/></td>
            <td><html:text property="expectedDate" readonly="true" size="10" styleId="expectedDateRequest" onclick="javascript: showCalendar('expectedDateRequest')" name="<%=Constants.DN%>" /></td>
            <td><bean:message key="message.dn.createdDate"/></td>
            <td><html:text property="createdDate" readonly="true" size="10" styleId="createdDateRequest" onclick="javascript: showCalendar('createdDateRequest')" name="<%=Constants.DN%>" /></td>
        </tr>
        <tr>
            <td><bean:message key="message.dn.deliveryPlace"/></td>
            <td colspan="3"><html:text size="100" property="deliveryPlace" name="<%=Constants.DN%>"/></td>
        </tr>
        <tr>
            <td><bean:message key="message.dn.deliveryPresenter"/></td>
            <td colspan="3"><html:text size="100" property="deliveryPresenter" name="<%=Constants.DN%>"/></td>
        </tr>        
    </table>
    <html:image property="Submit" value="Submit" src="images/but_find.gif" style="cursor: hand;" onclick="return searchAdvDn();"/>
</form>
