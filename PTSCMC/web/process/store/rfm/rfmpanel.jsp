<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<%@ page import="com.venus.mc.util.MCUtil"%>
<%@ page import="com.venus.mc.util.Constants"%>
<%
            String kind = MCUtil.getParameter("kind", request);
%>
<h3><logic:equal value="0" name="kind">
        <bean:message key="message.rfmlist.title"/>
    </logic:equal>
    <logic:equal value="1" name="kind">
        <bean:message key="message.rfmlist1.title"/>
    </logic:equal></h3>
<div id="errorMessageDiv"></div>
<html:form action="searchRfm.do">
    <table>
        <tr>
            <td>
                <logic:equal value="0" name="kind">
                    <html:image src="images/blank.png" onclick="return searchRfm();"/>
                </logic:equal>
                <logic:equal value="1" name="kind">
                    <html:image src="images/blank.png" onclick="return searchRfm1();"/>
                </logic:equal>
                <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_DELETE, PermissionUtil.PER_STOCK_RFM)) {%>
                <html:image src="images/ico_xoa.gif" onclick="return delRfms();"/>
                <%}%>
                <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_ADD, PermissionUtil.PER_STOCK_RFM)) {%>
                <html:image src="images/ico_them.gif"  onclick="return rfmForm();"/>
                <%}%>
            </td>
            <td><div>
                    <html:select property="searchid">
                        <html:option key="message.all" value='0'/>
                        <html:option key="message.rfm.rfmNumber" value='1'/>
                        <html:option key="message.rfm.deliveryPlace" value='2'/>
                    </html:select>
                    <html:text property="searchvalue" />
                    <logic:equal value="0" name="kind">
                        <html:submit onclick="return searchRfm();"><bean:message key="message.search"/></html:submit>
                        <html:submit onclick="return searchAdvRfmForm();"><bean:message key="message.detailSearch"/></html:submit>
                    </logic:equal>
                    <logic:equal value="1" name="kind">
                        <html:submit onclick="return searchRfm1();"><bean:message key="message.search"/></html:submit>
                        <html:submit onclick="return searchAdvRfmForm1();"><bean:message key="message.detailSearch"/></html:submit>
                    </logic:equal>

                </div></td>
        </tr>
        <tr><td><bean:message key="message.project"/>
                <logic:equal value="0" name="kind">
                    <html:select property="searchvalues" onchange="searchRfm();">
                        <html:options collection="<%=Constants.STORE_LIST%>" property="value" labelProperty="label"/>
                    </html:select>
                </logic:equal>
                <logic:equal value="1" name="kind">
                    <html:select property="searchvalues" onchange="searchRfm1();">
                        <html:options collection="<%=Constants.STORE_LIST%>" property="value" labelProperty="label"/>
                    </html:select>
                </logic:equal>
            </td></tr>
    </table>

</html:form>
<form name='rfmsForm' id='rfmsForm'><div id='rfmList'></div><input type="hidden" name="kind" value="<%=kind%>"/></form>