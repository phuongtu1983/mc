<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.MCUtil"%>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ page import="java.util.ArrayList"%>
<form name="searchDn2Form">
    <table width="100%">
        <tr>
            <td><bean:message key="message.dn2.dn2Number"/></td>
            <td><html:text size="40" property="dnNumber" name="<%=Constants.DN%>"/></td>
            <td><bean:message key="message.rfm.orgName"/></td>
            <td><html:select property="createdOrg" name="<%=Constants.DN%>" >
                    <html:options collection="<%=Constants.ORG_LIST%>" property="value" labelProperty="label"/>
                </html:select>
            </td>
        </tr>
        <tr>
            <td><bean:message key="message.dn2.proId"/></td>
            <td><html:select property="proId" name="<%=Constants.DN%>" >
                    <html:options collection="<%=Constants.PRO_LIST%>" property="value" labelProperty="label"/>
                </html:select>
            </td>
            <td><bean:message key="message.dn2.createdDate"/></td>
            <td><html:text property="createdDate" readonly="true" size="10" styleId="createdDateDn2" onclick="javascript: showCalendar('createdDateDn2')" name="<%=Constants.DN%>" /></td>
        </tr>        
    </table>
    <logic:equal parameter="kind" value="2">
        <html:image property="Submit" value="Submit" src="images/but_find.gif" style="cursor: hand;" onclick="return searchAdvDn2();"/>
    </logic:equal>
    <logic:equal parameter="kind" value="4">
        <html:image property="Submit" value="Submit" src="images/but_find.gif" style="cursor: hand;" onclick="return searchAdvDn4();"/>
    </logic:equal>
</form>
