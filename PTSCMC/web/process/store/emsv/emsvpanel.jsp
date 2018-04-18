<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<h3><bean:message key="message.emsv.title1"/></h3>
<div id="errorMessageDiv"></div>
<html:form action="searchEmsv.do" onsubmit="return searchEmsv();">
    <table>
        <tr>
            <td>
                <img alt="add" src="images/ico_xoa.gif" onclick="return delEmsvs();"/>
                <img alt="del" src="images/ico_them.gif" onclick="return emsvEmrirForm();"/>
            </td>
            <td><div>
                    <html:select property="searchid">
                        <html:option key="message.all" value='0'/>
                        <html:option key="message.msv.number" value='1'/>
                        <html:option key="message.msv.contract" value='2'/>
                        <html:option key="message.msv.inputstore" value='3'/>
                    </html:select>
                    <html:text property="searchvalue" />
                    <html:submit onclick="return searchEmsv();"><bean:message key="message.search"/></html:submit>
                    <!--<html:submit onclick="return searchAdvEmsvForm();"><bean:message key="message.detailSearch"/></html:submit>-->
            </div></td>
        </tr>
    </table>
</html:form>
<form name='emsvListForm' id='emsvListForm'><div id='emsvList'></div></form>