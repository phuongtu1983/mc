<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<h3><bean:message key="message.msv.title2"/></h3>
<div id="errorMessageDiv"></div>
<html:form action="searchEdmv.do">
    <table>
        <tr>
            <td>
                <html:image src="images/blank.png" onclick="return searchEdmv();" />
                <html:image src="images/ico_xoa.gif" onclick="return delEdmvs();" />
                <html:image src="images/ico_them.gif"  onclick="return edmvEdnForm();"/>
            </td>
            <td><div>
                    <html:select property="searchid">
                        <html:option key="message.all" value='0'/>
                        <html:option key="message.msv.number" value='1'/>
                        <html:option key="message.msv.commandorder" value='2'/>
                        <html:option key="message.msv.inputstore" value='3'/>
                    </html:select>
                    <html:text property="searchvalue" style="cursor:hand;" />
                    <html:submit onclick="return searchEdmv();"><bean:message key="message.search"/></html:submit>
                    <!--<html:submit onclick="return searchAdvEdmvForm();"><bean:message key="message.detailSearch"/></html:submit>-->
            </div></td>
        </tr>
    </table>
</html:form>
<form name='edmvListForm' id='edmvListForm'><div id='edmvList'></div></form>