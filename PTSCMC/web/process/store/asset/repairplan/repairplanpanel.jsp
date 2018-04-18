<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%--<h3><bean:message key="message.listrepairplan.title"/></h3>--%>
<div id="errorMessageDiv"></div>
<html:form styleId="searchAssetRepairPlan" action="searchAssetRepairPlan.do">
    <table>            
        <tr>
            <td>
                <html:image src="images/blank.png" onclick="return searchAssetRepairPlan();"/>
                <html:image src="images/ico_xoa.gif" onclick="return delAssetRepairPlans();"/>
                <html:image src="images/ico_them.gif"  onclick="return addAssetRepairPlan();"/>
            </td>
            <td><div>
                <html:select property="searchid">
                    <html:option key="message.all" value='0'/>
                    <html:option key="message.repairplan.repairPart" value='1'/>
                    <html:option key="message.repairplan.cost" value='2'/>
                </html:select>
                <html:text property="searchvalue" />
                <html:submit onclick="return searchAssetRepairPlan();"><bean:message key="message.search"/></html:submit>
                <html:submit onclick="return searchAdvAssetRepairPlanForm();"><bean:message key="message.detailSearch"/></html:submit>
        </div></td>
        </tr>
    </table>
</html:form>
<form name='repairplansForm' id='repairplansForm'><div id='repairplanList'></div></form>