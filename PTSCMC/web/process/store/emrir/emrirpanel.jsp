<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<h3><bean:message key="message.emrirlist.title"/></h3>
<div id="errorMessageDiv"></div>
<html:form action="searchEmrir.do" onsubmit="searchEmrir();">
    <table>
        <tr>
            <td>
                <img alt="add" src="images/ico_xoa.gif" onclick="return delEmrirs();"/>
                <img alt="del" src="images/ico_them.gif" onclick="return emrirForm();"/>
            </td>
            <td><div>
                <html:select property="searchid">
                    <html:option key="message.all" value='0'/>
                    <html:option key="message.emrir.emrirNumber" value='1'/>
                    <html:option key="message.emrir.ednId" value='2'/>
                </html:select>
                <html:text property="searchvalue" />
                <html:submit onclick="return searchEmrir();"><bean:message key="message.search"/></html:submit>
        </div></td>
        </tr>
    </table>
</html:form>
<form name='emrirsForm' id='emrirsForm'><div id='emrirList'></div></form>