<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.MCUtil"%>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.venus.mc.bean.OrganizationBean"%>
<%@ page import="com.venus.mc.comclarification.ComClarificationFormBean"%>
<div id="comClarificationFormTitle"><h3><bean:message key="message.comclarification.title"/></h3></div>
<div id="errorMessageDiv"></div>
<form name="comClarificationForm">
    <table width="100%">
        <tr>
            <td width="130px"><bean:message key="message.comclarification.ccNumber"/></td>
            <td colspan="2"><html:text size="20" property="ccNumber" name="<%=Constants.COM_CLARIFICATION%>"/></td>
            <td width="50px"><bean:message key="message.comclarification.subfix"/></td>
            <td width="40px"><html:text property="subfix" size="2" maxlength="1" name="<%=Constants.COM_CLARIFICATION%>"/></td>
        </tr>
        <tr>
            <td><bean:message key="message.comclarification.vendorName"/></td>
            <td colspan="5">
                <html:select property="vendorName" name="<%=Constants.COM_CLARIFICATION%>" >
                    <html:options collection="<%=Constants.VENDOR_LIST%>" property="value" labelProperty="label"/>
                </html:select>
            </td>
        </tr>
        <tr>            
            <td><bean:message key="message.comclarification.createdDate"/></td>
            <td colspan="2"><html:text readonly="true" size="20" property="createdDate" styleId="createdDate4" onclick="javascript: showCalendar('createdDate4')" name="<%=Constants.COM_CLARIFICATION%>" /></td>
        </tr>               
        <tr>
            <td colspan="6">
                <fieldset>
                    <legend><bean:message key="message.comclarification.title1"/></legend>
                    <table style="width:100%">
                        <tr>
                            <td><bean:message key="message.comclarification.reference"/></td>
                            <td colspan="2"><html:text size="90" property="reference" name="<%=Constants.COM_CLARIFICATION%>"/></td>
                        </tr>
                        <tr>
                            <td><bean:message key="message.comclarification.clarification"/></td>
                            <td colspan="2"><html:text property="clarification" size="90" name="<%=Constants.COM_CLARIFICATION%>"/></td>
                        </tr>
                        <tr>
                            <td><bean:message key="message.comclarification.supplierReply"/></td>
                            <td colspan="2"><html:text size="90" property="supplierReply" name="<%=Constants.COM_CLARIFICATION%>"/></td>
                        </tr>
                        <tr>
                            <td><bean:message key="message.comclarification.status"/></td>
                            <td colspan="2">
                                <html:select property="status" name="<%=Constants.COM_CLARIFICATION%>" >
                                    <html:options collection="<%=Constants.STATUS_LIST%>" property="value" labelProperty="label"/>
                                </html:select>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="6">
                                <html:image src="images/ico_xoa.gif" onclick="return delComClarificationDiscipline();"/>
                                <html:image src="images/but_save.gif" onclick="return saveComClarification();"/>
                                <fieldset>
                                    <legend><bean:message key="message.comclarification.list"/></legend>
                                    <div id='comClarificationList'></div>
                                </fieldset>
                            </td>
                        </tr>
                    </table>                    
                </fieldset>
            </td>
        </tr>        
    </table>
    <html:image onclick="return saveComClarification();" src="images/but_save.gif"/><img src="images/blank.gif" width="2" height="17"><html:image property="Back" value="Back" src="images/but_back.gif" onclick="return hideWindows();"/>
    <html:hidden property="tenId" name="<%=Constants.COM_CLARIFICATION%>"/>
    <html:hidden property="ccId" name="<%=Constants.COM_CLARIFICATION%>"/>
</form>
<div id="comClarificationGroupHideDiv" style="display:none"></div>
<div id="popupDialog3" dojoType="dijit.Dialog"></div>