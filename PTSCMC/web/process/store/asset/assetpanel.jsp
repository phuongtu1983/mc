<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<h3><bean:message key="message.listasset.title"/></h3>
<div id="errorMessageDiv"></div>
<html:form styleId="searchAsset" action="searchAsset.do">
    <table>            
        <tr>
            <td>
                <html:image src="images/blank.png" onclick="return searchAsset();"/>
                <html:image src="images/ico_xoa.gif" onclick="return delAssets();"/>
                <html:image src="images/ico_them.gif"  onclick="return addAsset();"/>
            </td>
            <td><div>
                <html:select property="searchid">
                    <html:option key="message.all" value='0'/>
                    <html:option key="message.asset.decisionNumber1" value='1'/>
                    <html:option key="message.asset.assetName" value='2'/>
                    <html:option key="message.asset.requestNumber" value='3'/>
                    <html:option key="message.asset.contractNumber" value='4'/>
                </html:select>
                <html:text property="searchvalue" />
                <html:submit onclick="return searchAsset();"><bean:message key="message.search"/></html:submit>
                <html:submit onclick="return searchAdvAssetForm();"><bean:message key="message.detailSearch"/></html:submit>
        </div></td>
        </tr>
    </table>
</html:form>
<form name='assetsForm' id='assetsForm'><div id='assetList'></div></form>