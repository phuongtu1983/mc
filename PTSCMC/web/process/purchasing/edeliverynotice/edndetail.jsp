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
<%@ page import="com.venus.mc.bean.EdnBean"%>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<div id="dnFormTitle"><h3>
        <bean:message key="message.ednadd.title"/>
</h3></div>
<div id="errorMessageDiv"></div>
<form name="ednForm">
    <table width="100%">
        <tr>
            <td><bean:message key="message.dn.dnNumber"/></td>
            <td><html:text size="40" property="dnNumber" name="<%=Constants.DN%>"/></td>
            <td><bean:message key="message.store.proId"/></td>
            <td><html:select property="proId" name="<%=Constants.DN%>" >
                    <html:options collection="<%=Constants.PRO_LIST%>" property="value" labelProperty="label"/>
                </html:select>
            </td>
        </tr>
        <tr>
            <td class="lbl10"><bean:message key="message.dn.contractNumber"/></td>
            <td><html:text size="40" property="contractNumber" name="<%=Constants.DN%>"/></td>
            <td><bean:message key="message.rfm.orgName"/></td>
            <td><html:select property="createdOrg" name="<%=Constants.DN%>" >
                    <html:options collection="<%=Constants.ORG_LIST%>" property="value" labelProperty="label"/>
                </html:select>
            </td>
        </tr>
        <tr>
            <td><bean:message key="message.dn.expectedDate"/></td>
            <td><html:text property="expectedDate" readonly="true" size="10" styleId="expectedDateDN" onclick="javascript: showCalendar('expectedDateDN')" name="<%=Constants.DN%>" /></td>
            <td><bean:message key="message.dn.createdDate"/></td>
            <td><html:text property="createdDate" readonly="true" size="10" styleId="createdDateDN" onclick="javascript: showCalendar('createdDateDN')" name="<%=Constants.DN%>" /></td>
        </tr>        
        <tr>
            <td><bean:message key="message.dn.deliveryPlace"/></td>
            <td colspan="3"><html:text size="100" property="deliveryPlace" name="<%=Constants.DN%>"/></td>
        </tr>
        <tr>
            <td><bean:message key="message.dn.deliveryPresenter"/></td>
            <td colspan="3"><html:text size="100" property="deliveryPresenter" name="<%=Constants.DN%>"/></td>
        </tr>              
        <tr>
            <td colspan="4">
                <fieldset>
                    <legend style="color:blue;"><bean:message key="message.dn.materials"/></legend>
                    <div>
                        <html:image src="images/ico_xoa.gif" onclick="return delEdnDetails();"/>
                        <html:image src="images/ico_them.gif" onclick="return selectEdnMaterial('setSelectedEdnMaterial');"/>
                    </div>
                    <p><div id="listEdnMaterialDataDiv" style="width:700px;height:300px;overflow:auto"><%@include  file="/process/purchasing/edeliverynotice/details.jsp" %></div></p>
                </fieldset>
            </td>
        </tr>        
    </table>
    <logic:greaterThan value="0" name="<%=Constants.DN%>" property="dnId">
        <img onclick="return printEdn();" src="images/but_print.gif"/>
    </logic:greaterThan>
    <%
            EdnBean form = (EdnBean) request.getAttribute(Constants.DN);
            if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_EDIT, PermissionUtil.PER_PURCHASING_DELIVERYNOTICE_PROJECT, form.getCreatedEmp())) {
    %>
    <html:image onclick="return saveEdn();" src="images/but_save.gif"/><img src="images/blank.gif" width="2" height="17">
    <%}%>
    <html:image property="Back" value="Back" src="images/but_back.gif" onclick="return loadEdnList();"/>
    <html:hidden property="dnId" name="<%=Constants.DN%>"/>
    <html:hidden property="createdEmp" name="<%=Constants.DN%>"/>    
</form>
<div id="ednMaterialHideDiv" style="display:none"></div>