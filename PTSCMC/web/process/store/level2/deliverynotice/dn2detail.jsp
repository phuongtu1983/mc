<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib prefix="display" uri="/tags/displaytag" %>
<%@ page import="com.venus.mc.util.MCUtil"%>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.venus.mc.bean.OrganizationBean"%>
<%@ page import="com.venus.mc.bean.DnBean"%>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<div id="dn2FormTitle"><h3>
        <logic:equal value="2" property="kind" name="<%=Constants.DN%>">
            <bean:message key="message.dn2add.title"/>
        </logic:equal>
        <logic:equal value="3" property="kind" name="<%=Constants.DN%>">
            <bean:message key="message.dn3add.title"/>
        </logic:equal>
        <logic:equal value="4" property="kind" name="<%=Constants.DN%>">
            <bean:message key="message.dn4add.title"/>
        </logic:equal>
    </h3></div>
<div id="errorMessageDiv"></div>
<form name="dn2Form">
    <table width="100%">
        <tr>
            <td><bean:message key="message.dn2.dn2Number"/></td>
            <td><html:text size="40" property="dnNumber" readonly="true" name="<%=Constants.DN%>"/></td>
            <td><bean:message key="message.rfm.orgName"/></td>
            <td><html:select disabled="true" property="createdOrg" name="<%=Constants.DN%>" >
                    <html:options collection="<%=Constants.ORG_LIST%>" property="value" labelProperty="label"/>
                </html:select>
            </td>
        </tr>
        <tr>            
            <td><bean:message key="message.dn2.proId"/></td>
            <%--<td><html:select onchange="return setSelectedDn2MaterialProject();" property="proId" name="<%=Constants.DN%>" > --%>
            <td><html:select property="proId" name="<%=Constants.DN%>" >
                    <html:options collection="<%=Constants.PRO_LIST%>" property="value" labelProperty="label"/>
                </html:select>
            </td>
            <td><bean:message key="message.dn2.createdDate"/></td>
            <td><html:text property="createdDate" readonly="true" size="10" styleId="createdDateDn2" onclick="javascript: showCalendar('createdDateDn2')" name="<%=Constants.DN%>" /></td>
        </tr>              
        <tr>
            <td colspan="4">
                <fieldset>
                    <legend style="color:blue;"><bean:message key="message.dn2.materials"/></legend>
                    
                    <logic:equal value="2" property="kind" name="<%=Constants.DN%>">
                        <div>
                            <html:image src="images/ico_xoa.gif" onclick="return delDn2Details();"/>
                            <html:image src="images/ico_them.gif" onclick="return selectDn2Material('setSelectedDn2Material');"/>
                        </div>
                    </logic:equal>
                    
                    <p><div id="listDn2MaterialDataDiv" style="width:700px;height:300px;overflow:auto"><%@include  file="/process/store/level2/deliverynotice/details.jsp" %></div></p>
                </fieldset>
            </td>
        </tr>        
    </table>
    <logic:greaterThan value="0" name="<%=Constants.DN%>" property="dnId">
        <img onclick="return printDn2();" src="images/but_print.gif"/>
    </logic:greaterThan>
    <%
            DnBean form = (DnBean) request.getAttribute(Constants.DN);
            if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_EDIT, PermissionUtil.PER_STOCK_YCKT_STORE2, form.getCreatedEmp())) {
    %>
    <logic:equal value="2" property="kind" name="<%=Constants.DN%>">
        <html:image onclick="return saveDn2();" src="images/but_save.gif"/><img src="images/blank.gif" width="2" height="17">
    </logic:equal>
    <logic:equal value="4" property="kind" name="<%=Constants.DN%>">
        <html:image onclick="return saveDn4();" src="images/but_save.gif"/><img src="images/blank.gif" width="2" height="17">
    </logic:equal>
    <%}%>
    <logic:equal value="2" property="kind" name="<%=Constants.DN%>">
        <html:image property="Back" value="Back" src="images/but_back.gif" onclick="return loadDn2List();"/>
    </logic:equal>
    <logic:equal value="4" property="kind" name="<%=Constants.DN%>">
        <html:image property="Back" value="Back" src="images/but_back.gif" onclick="return loadDn4List();"/>
    </logic:equal>
    <html:hidden property="dnId" name="<%=Constants.DN%>"/>
    <html:hidden property="drId" name="<%=Constants.DN%>"/>
    <html:hidden property="createdOrg" name="<%=Constants.DN%>"/>
    <html:hidden property="createdEmp" name="<%=Constants.DN%>"/>
    <html:hidden property="kind" name="<%=Constants.DN%>"/>
</form>
<div id="dn2MaterialProjectHideDiv" style="display:none"></div>
<div id="dn2MaterialHideDiv" style="display:none"></div>