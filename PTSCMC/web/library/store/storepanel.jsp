<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<h3><bean:message key="message.liststore.title"/></h3>
<div id="errorMessageDiv"></div>
<html:form action="searchStore.do">
    <table>            
        <tr>
            <td>
                <html:image src="images/blank.png" onclick="return searchStore();"/>
           </td>
            <td><div>
                    <html:select property="searchid">
                        <html:option key="message.all" value='0'/>
                        <html:option key="message.store.l_name" value='1'/>
                    </html:select>
                    <html:text property="searchvalue" size="60"/>
                    <html:submit onclick="return searchStore();"><bean:message key="message.search"/></html:submit>
                    <html:submit onclick="return searchAdvStoreForm();"><bean:message key="message.detailSearch"/></html:submit>
            </div></td>
        </tr>
    </table>
</html:form>
<form name='storesForm' id='storesForm'><div id='storeList'></div></form>