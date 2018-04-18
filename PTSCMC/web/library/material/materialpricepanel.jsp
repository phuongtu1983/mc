<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<h3><bean:message key="message.listmaterialprice.title"/></h3>
<div id="errorMessageDiv"></div>
<html:form action="searchMaterialPrice.do">
    <table>
        <tr>
            <td><div>
                    <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_DELETE, PermissionUtil.PER_LIBRARY_MATERIAL_PRICE)) {%>
                    <html:image src="images/ico_xoa.gif" onclick="return delPrices();"/>
                    <%}%>
                    <html:select property="searchid">
                        <html:option key="message.all" value='0'/>
                        <html:option key="message.material.code" value='4'/>
                        <html:option key="message.material.nameVn" value='1'/>
                        <html:option key="message.menu20.text" value='2'/>
                        <html:option key="message.menu34.text" value='3'/>
                    </html:select>
                    <input type="text" name="searchvalue" size="39"/>
                    <bean:message key="message.from"/>
                    <input type="textbox" size="10" name="fromDate" id="fromDateMaterialPriceFromDate" onclick="javascript:showCalendar('fromDateRequest')"/>
                    <bean:message key="message.to"/>
                    <input type="textbox" size="10" name="toDate" id="toDateMaterialPriceToDate" onclick="javascript:showCalendar('toDateRequest')"/>
                    <html:submit onclick="return searchMaterialPrice();"><bean:message key="message.search"/></html:submit>
                        <img onclick="return exportMaterialPrice();" src="images/but_print.gif"/>
                    </div></td>
            </tr>
        </table>
</html:form>
<form name='materialPricesForm' id='materialPricesForm'><div id='materialPriceList'></div></form>