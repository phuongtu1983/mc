<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<logic:equal name="<%=Constants.MIV_KIND%>" property="value" value="1">
    <h3><bean:message key="message.mivlist.title"/>/<bean:message key="message.list.s"/></h3>
</logic:equal>
<logic:equal name="<%=Constants.MIV_KIND%>" property="value" value="2">
    <h3><bean:message key="message.mivpartnerlist.title"/></h3>
</logic:equal>
<div id="errorMessageDiv"></div>
<html:form action="searchMiv.do">
    <table>
        <tr>
            <td>
                <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_DELETE, PermissionUtil.PER_STOCK_MIV)) {%>
                <html:image src="images/ico_xoa.gif" onclick="return delMivs();"/>
                <%}%>
                <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_ADD, PermissionUtil.PER_STOCK_MIV)) {%>
                <html:image src="images/ico_them.gif"  onclick="return mivForm();"/>
                <%}%>
            </td>
            <td><div>
                    <html:select property="searchid">
                        <html:option key="message.all" value='0'/>
                        <html:option key="message.miv.number" value='1'/>
                        <html:option key="message.rfm.rfmNumber1" value='2'/>
                    </html:select>
                    <html:text property="searchvalue" />
                    <html:submit onclick="return searchMiv();"><bean:message key="message.search"/></html:submit>
                    <!--<html:submit onclick="return searchAdvMivForm();"><bean:message key="message.detailSearch"/></html:submit>-->
            </div></td>
        </tr>
        <tr><td colspan="2">&nbsp;</td></tr>
        <tr><td colspan="2"><bean:message key="message.store"/>
                <html:select property="searchvalues" onchange="searchMiv();">
                    <html:options collection="<%=Constants.STORE_LIST%>" property="value" labelProperty="label"/>
                </html:select>
            </td></tr>
    </table>
</html:form>
<html:hidden styleId="mivKind" name="<%=Constants.MIV_KIND%>" property="value"/>
<form name='mivsForm' id='mivsForm'><div id='mivList'></div></form>