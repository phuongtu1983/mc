<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.MCUtil"%>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.venus.mc.bean.OrganizationBean"%>
<%@ page import="com.venus.mc.techclarification.TechClarificationFormBean"%>
<div id="techClarificationFormTitle"><h3><bean:message key="message.techclarification.title"/></h3></div>
<div id="errorMessageDiv"></div>
<form name="techClarificationForm">
    <table width="100%">
        <tr>
            <td><bean:message key="message.techclarification.tcNumber"/></td>
            <td colspan="2"><html:text size="20" property="tcNumber" name="<%=Constants.TECH_CLARIFICATION%>"/></td>
            <td width="50px"><bean:message key="message.techclarification.subfix"/></td>
            <td width="40px"><html:text property="subfix" size="2" maxlength="1" name="<%=Constants.TECH_CLARIFICATION%>"/></td>
        </tr>
        <tr>
            <td><bean:message key="message.techclarification.vendorName"/></td>
            <td colspan="5"><html:text size="90" property="vendorName" name="<%=Constants.TECH_CLARIFICATION%>"/></td>
        </tr>
        <tr>            
            <td><bean:message key="message.techclarification.createdDate"/></td>
            <td colspan="2"><html:text readonly="true" size="20" property="createdDate" styleId="createdDate" onclick="javascript: showCalendar('createdDate')" name="<%=Constants.TECH_CLARIFICATION%>" /></td>
        </tr>               
        <tr>
            <td colspan="6">
                <fieldset>
                    <legend><bean:message key="message.techclarification.disciplineDetail"/></legend>
                    <table style="width:100%">
                        <tr>
                            <td><bean:message key="message.techclarification.name"/></td>
                            <td colspan="2"><html:text size="90" property="discipline" name="<%=Constants.TECH_CLARIFICATION%>"/></td>
                        </tr>
                        <tr>
                            <td><bean:message key="message.techclarification.notes"/></td>
                            <td colspan="2"><html:text property="notes" size="90" name="<%=Constants.TECH_CLARIFICATION%>"/></td>
                        </tr>
                        <tr>
                            <td colspan="6">
                                <html:image src="images/ico_xoa.gif" onclick="return delTechClarificationDiscipline();"/>
                                <html:image src="images/but_save.gif" onclick="return saveTechClarification();"/>
                                <fieldset>
                                    <legend><bean:message key="message.techclarification.list"/></legend>
                                    <div id='disciplineList'></div>
                                </fieldset>
                            </td>
                        </tr>
                    </table>                    
                </fieldset>
            </td>
        </tr>        
    </table>
    <html:image onclick="return saveTechClarification();" src="images/but_save.gif"/><img src="images/blank.gif" width="2" height="17">
    <html:image property="Back" value="Back" src="images/but_back.gif" onclick="return addTechEvalVendor(document.forms['techClarificationForm'].terId.value,document.forms['techClarificationForm'].venId.value);"/>
    <html:hidden property="terId" name="<%=Constants.TECH_CLARIFICATION%>"/>
    <html:hidden property="tcId" name="<%=Constants.TECH_CLARIFICATION%>"/>
    <html:hidden property="venId" name="<%=Constants.TECH_CLARIFICATION%>"/>
</form>
<div id="techClarificationGroupHideDiv" style="display:none"></div>
<div id="popupDialog3" dojoType="dijit.Dialog"></div>