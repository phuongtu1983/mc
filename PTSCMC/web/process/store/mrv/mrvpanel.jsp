<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<h3><bean:message key="message.msv.title3"/></h3>
<div id="errorMessageDiv"></div>
<html:form action="searchMrv.do">
    <table>
        <tr>
            <td>
                <html:image src="images/blank.png" onclick="return searchMrv();"/>
                <html:image src="images/ico_xoa.gif" onclick="return delMrvs();" />
                <html:image src="images/ico_them.gif"  onclick="return mrvForm();"/>
            </td>
            <td><div>
                    <html:select property="searchid">
                        <html:option key="message.all" value='0'/>
                        <html:option key="message.msv.number" value='1'/>
                        <html:option key="message.msv.mivnumber" value='2'/>
                        <html:option key="message.msv.inputstore" value='3'/>
                    </html:select>
                    <html:text property="searchvalue" style="cursor:hand;" />
                    <html:submit onclick="return searchMrv();"><bean:message key="message.search"/></html:submit>
                    <!--<html:submit onclick="return searchAdvMrvForm();"><bean:message key="message.detailSearch"/></html:submit>-->
            </div></td>
        </tr>
    </table>
</html:form>
<form name='mrvListForm' id='mrvListForm'><div id='mrvList'></div></form>