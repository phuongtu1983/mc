<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<h3><bean:message key="message.listmaterialprice.title"/></h3>
<div id="errorMessageDiv"></div>
<form name="materialPriceForm">
    <table>
        <tr>
            <td><div>
                <select name="searchid">
                    <option value='0'><bean:message key="message.all"/></option>
                    <option value='1'><bean:message key="message.menu20.text"/></option>
                    <option value='2'><bean:message key="message.menu34.text"/></option>
                    <option value='3'><bean:message key="message.contract.cost.project"/></option>
                </select>
                <input type="text" name="searchvalue" size="60"/>
                <html:submit onclick="return searchMaterialPriceNotCode();"><bean:message key="message.search"/></html:submit>
        </div></td>
        </tr>
    </table>
</form>
<form name='materialPricesForm' id='materialPricesForm'><div id='materialPriceList'></div></form>