<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<h3><bean:message key="message.emsv.title1"/></h3>
<div id="errorMessageDiv"></div>
<html:form action="searchTenderPlan.do">
    <table>
        <tr>
            <td>
                <html:image src="images/blank.png" onclick="return searchTenderPlan();"/>
                <html:image src="images/ico_xoa.gif" onclick="return delMsvs();"/>
                <html:image src="images/ico_them.gif"  onclick="return msvMrirForm();"/>
            </td>
            <td><div>
                <html:select property="searchid">
                    <html:option key="message.all" value='0'/>
                    <html:option key="message.tenderplan.number" value='1'/>
                </html:select>
                <html:text property="searchvalue" />
                <html:submit onclick="return searchTenderPlan();"><bean:message key="message.search"/></html:submit>
                <html:submit onclick="return searchAdvTenderPlanForm();"><bean:message key="message.detailSearch"/></html:submit>
        </div></td>
        </tr>
    </table>
</html:form>
<form name='msvListForm' id='msvListForm'><div id='msvList'></div></form>