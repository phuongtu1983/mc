<%-- 
    Document   : inventorypanel
    Created on : Oct 7, 2009, 10:33:13 PM
    Author     : kngo
--%>

<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<h3><bean:message key="message.inventorylist.title"/></h3>
<div id="errorMessageDiv"></div>
<html:form action="searchInventory.do" onsubmit="return searchInventory();">
    <table>
        <tr>
            <td>
                <img alt="add" src="images/ico_xoa.gif" onclick="return delInventorys();"/>
                <img alt="del" src="images/ico_them.gif" onclick="return inventoryForm();"/>
            </td>
            <td><div>
                <html:select property="searchid">
                    <html:option key="message.all" value='0'/>
                    <html:option key="message.inventory.storeName" value='1'/>
                </html:select>
                <html:text property="searchvalue" />
                <html:submit onclick="return searchInventory();"><bean:message key="message.search"/></html:submit>
        </div></td>
        </tr>
    </table>
</html:form>
<form name='inventorysForm' id='inventorysForm'><div id='inventoryList'></div></form>
