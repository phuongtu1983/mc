<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%--<h3><bean:message key="message.listtransferprocess.title"/></h3>--%>
<div id="errorMessageDiv"></div>
<html:form styleId="searchAssetTransferProcess" action="searchAssetTransferProcess.do">
    <table>            
        <tr>
            <td>
                <html:image src="images/blank.png" onclick="return searchAssetTransferProcess();"/>
                <html:image src="images/ico_xoa.gif" onclick="return delAssetTransferProcesss();"/>
                <html:image src="images/ico_them.gif"  onclick="return addAssetTransferProcess();"/>
            </td>
            <td><div>
                <html:select property="searchid">
                    <html:option key="message.all" value='0'/>
                    <html:option key="message.transferprocess.comment" value='1'/>
                </html:select>
                <html:text property="searchvalue" />
                <html:submit onclick="return searchAssetTransferProcess();"><bean:message key="message.search"/></html:submit>
                <html:submit onclick="return searchAdvAssetTransferProcessForm();"><bean:message key="message.detailSearch"/></html:submit>
        </div></td>
        </tr>
    </table>
</html:form>
<form name='transferprocesssForm' id='transferprocesssForm'><div id='transferprocessList'></div></form>