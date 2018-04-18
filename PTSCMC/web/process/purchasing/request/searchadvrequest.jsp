<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.MCUtil"%>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.venus.mc.bean.OrganizationBean"%>
<%@ page import="com.venus.mc.request.RequestFormBean"%>
<form name="searchRequestForm">
   <table width="100%">
        <tr>
            <td><bean:message key="message.request.number"/></td>
            <td colspan="5"><html:text size="20" property="requestNumber" name="<%=Constants.REQUEST%>"/></td>
        </tr>
        <tr>
            <td><bean:message key="message.request.createdDate"/> <bean:message key="message.from"/></td>
            <td><input type="textbox" size="20" name="fromDate" id="fromDateRequest" onclick="javascript:showCalendar('fromDateRequest')"/></td>
            <td><bean:message key="message.to"/></td>
            <td><input type="textbox" size="20" name="toDate" id="toDateRequest" onclick="javascript:showCalendar('toDateRequest')"/></td>
        </tr>
        <tr>
            <td><bean:message key="message.request.createdEmp"/></td>
            <td>
                <html:select property="createdEmp" name="<%=Constants.REQUEST%>">
                    <html:options collection="<%=Constants.EMPLOYEE_LIST%>" property="empId" labelProperty="fullname"/>
                </html:select>
            </td>
            <td class="lbl10"><bean:message key="message.request.organization"/></td>
            <td>
                <html:select property="createdOrg" name="<%=Constants.REQUEST%>" >
                    <html:options collection="<%=Constants.ORG_LIST%>" property="orgId" labelProperty="name"/>
                </html:select>
            </td>
        </tr>
        <tr>
            <td><bean:message key="message.request.whichUse"/></td>
            <td colspan="3">
                <html:select property="whichUse" name="<%=Constants.REQUEST%>" onchange="return whichUseSearchAdvReqChanged(this);">
                    <html:options collection="<%=Constants.REQUEST_WHICHUSE_LIST%>" property="value" labelProperty="label"/>
                </html:select>
                <span name="whichuseSpan" id="whichuseSpan"></span>
            </td>
        </tr>
        <!--
        <tr>
            <td colspan="4">
                <fieldset>
                    <legend><bean:message key="message.request.approveSuggest"/></legend>
                    <p>
                        <table width="100%">
                            <tr>
                                <td><html:multibox property="approveSuggest" name="<%=Constants.REQUEST%>" value="1"></html:multibox> <bean:message key="message.request.approveSuggest1"/></td>
                                <td><html:multibox property="approveSuggest" name="<%=Constants.REQUEST%>" value="2"></html:multibox> <bean:message key="message.request.approveSuggest2"/></td>
                                <td><html:multibox property="approveSuggest" name="<%=Constants.REQUEST%>" value="4"></html:multibox> <bean:message key="message.request.approveSuggest3"/></td>
                                <td><html:multibox property="approveSuggest" name="<%=Constants.REQUEST%>" value="8"></html:multibox> <bean:message key="message.request.approveSuggest4"/></td>
                                <td><html:multibox property="approveSuggest" name="<%=Constants.REQUEST%>" value="16"></html:multibox> <bean:message key="message.request.approveSuggest5"/></td>
                            </tr>
                        </table></p>
                </fieldset>
            </td>
        </tr>                              
        <tr>
            <td colspan="4">
                <fieldset>
                    <legend><bean:message key="message.request.statusSuggest"/></legend>
                    <p>
                        <table width="100%">
                            <tr>
                                <td><html:radio property="statusSuggest" name="<%=Constants.REQUEST%>" value="1"><bean:message key="message.request.statusSuggest1"/></html:radio></td>
                                <td><html:radio property="statusSuggest" name="<%=Constants.REQUEST%>" value="2"><bean:message key="message.request.statusSuggest2"/></html:radio></td>
                                <td><html:radio property="statusSuggest" name="<%=Constants.REQUEST%>" value="3"><bean:message key="message.request.statusSuggest3"/></html:radio></td>
                            </tr>
                        </table>
                    </p>
                </fieldset>
            </td>
        </tr>
        -->
    </table>
    <html:image property="Submit" value="Submit" src="images/but_find.gif" style="cursor: hand;" onclick="return searchAdvRequest();"/>
</form>