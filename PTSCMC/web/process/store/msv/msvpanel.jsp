<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.MCUtil"%>
<%@ page import="com.venus.mc.util.Constants"%>
<h3><bean:message key="message.msv.title1"/></h3>
<div id="errorMessageDiv"></div>
<html:form action="searchMsv.do">
    <table>
        <tr>
            <td>
                <html:image src="images/blank.png" onclick="return searchMsv();"/>
                <html:image src="images/ico_xoa.gif" onclick="return delMsvs();"/>
                <html:image src="images/ico_them.gif"  onclick="return msvForm();"/>
            </td>
            <td><div>
                    <html:select property="searchid">
                        <html:option key="message.all" value='0'/>
                        <html:option key="message.msv.number" value='1'/>
                        <html:option key="message.msv.contract" value='2'/>
                        <html:option key="message.msv.inputstore" value='3'/>
                        <html:option key="message.msv.mrirnumber" value='4'/>
                    </html:select>
                    <html:text property="searchvalue" style="cursor:hand;" />
                    <html:submit onclick="return searchMsv();"><bean:message key="message.search"/></html:submit>
                    <!--<html:submit onclick="return searchAdvMsvForm();"><bean:message key="message.detailSearch"/></html:submit>-->
                </div></td>
        </tr>
        <tr><td colspan="2">&nbsp;</td></tr>
        <tr><td colspan="2"><bean:message key="message.store"/>
                <html:select property="searchvalues" onchange="searchMsv();">
                    <html:options collection="<%=Constants.STORE_LIST%>" property="value" labelProperty="label"/>
                </html:select>
            </td></tr>
    </table>
</html:form>
<form name='msvListForm' id='msvListForm'><div id='msvList'></div></form>