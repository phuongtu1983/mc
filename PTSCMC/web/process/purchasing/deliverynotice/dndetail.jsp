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
<% DnBean form = (DnBean) request.getAttribute(Constants.DN);%>
<logic:equal value="1" name="<%=Constants.DN%>" property="kind">
    <div id="dnFormTitle"><h3>
            <bean:message key="message.dnadd.title"/>        
        </h3></div>
    <div id="errorMessageDiv"></div>
    <form name="dnForm">
        <table width="100%">
            <tr>
                <td><bean:message key="message.dn.dnNumber"/></td>
                <td><html:text size="40" property="dnNumber" name="<%=Constants.DN%>"/></td>
                <td><bean:message key="message.dn.orgHandle"/></td>
                <td><html:select property="orgHandle" name="<%=Constants.DN%>" >
                        <html:options collection="<%=Constants.ORGANIZATION_LIST%>" property="value" labelProperty="label"/>
                    </html:select>
                </td>
            </tr>
            <tr>
                <td class="lbl10"><bean:message key="message.dn.contractNumber"/></td>
                <td>
                    <html:select styleClass="lbl10" property="whichUse" name="<%=Constants.DN%>" onchange="return whichUseChangedDn(this,null);">
                        <html:options styleClass="lbl10" collection="<%=Constants.DN_WHICHUSE_LIST%>" property="value" labelProperty="label"/>
                    </html:select>
                    <span id="dnConIdSpan">
                        <span name="whichUseSpan" id="whichUseSpan"><select name="conId" style="width: 170px;"></select></span>
                        <input type="button" onclick="return comboTextIdClick('dnForm','dnConName','conId','dnConIdSpan','dnConNameSpan');" value='<>'/>
                    </span>
                    <span  style="display:none" id="dnConNameSpan">
                        <input type="textbox" size="29" name="dnConName"/>
                        <input type="button" onclick="return comboTextNameClick_Handle('dnForm',searchContractForDn,null,'dnConName','conId','whichUseSpan','dnConIdSpan','dnConNameSpan');" value='<>'/>
                    </span>
                </td>
                <td><bean:message key="message.rfm.orgName"/></td>
                <td><html:select property="createdOrg" name="<%=Constants.DN%>" >
                        <html:options collection="<%=Constants.ORG_LIST%>" property="value" labelProperty="label"/>
                    </html:select>
                </td>
            </tr>
            <tr>
                <td><bean:message key="message.dn.expectedDate"/></td>
                <td><html:text property="expectedDate" readonly="true" size="10" styleId="expectedDateDn" onclick="javascript: showCalendar('expectedDateDn')" name="<%=Constants.DN%>" /></td>
                <td><bean:message key="message.dn.createdDate"/></td>
                <td><html:text property="createdDate" readonly="true" size="10" styleId="createdDateDn" onclick="javascript: showCalendar('createdDateDn')" name="<%=Constants.DN%>" /></td>
            </tr>
            <tr>
                <td><bean:message key="message.dn.extensionDate"/></td>
                <td><html:text property="extensionDate" readonly="true" size="10" styleId="extensionDateDn" onclick="javascript: showCalendar('extensionDateDn')" name="<%=Constants.DN%>" /></td>
                <td><bean:message key="message.dn.actualDate"/></td>
                <td><html:text property="actualDate" readonly="true" size="10" styleId="actualDateDn" onclick="javascript: showCalendar('actualDateDn')" name="<%=Constants.DN%>" /></td>
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
                        <logic:equal value="0" name="<%=Constants.DN%>" property="canSave">
                            <div>
                                <logic:equal value="0" name="<%=Constants.DN%>" property="status">
                                    <html:image src="images/ico_xoa.gif" onclick="return delDnDetails();"/>
                                    <html:image src="images/ico_them.gif" onclick="return selectDnMaterial('setSelectedDnMaterial');"/>
                                </logic:equal>
                                <logic:equal value="2" name="<%=Constants.DN%>" property="status">
                                    <html:image src="images/ico_xoa.gif" onclick="return delDnDetails();"/>
                                    <html:image src="images/ico_them.gif" onclick="return selectDnMaterial('setSelectedDnMaterial');"/>
                                </logic:equal>
                            </div>
                        </logic:equal>
                        <p><div id="listDnMaterialDataDiv" style="width:700px;height:300px;overflow:auto"><%@include  file="/process/purchasing/deliverynotice/details.jsp" %></div></p>
                    </fieldset>
                </td>
            </tr>
        </table>
        <logic:greaterThan value="0" name="<%=Constants.DN%>" property="dnId">
            <img onclick="return printDn();" src="images/but_print.gif"/>
        </logic:greaterThan>
        <%
                    if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_EDIT, PermissionUtil.PER_PURCHASING_DELIVERYNOTICE, form.getCreatedEmp())) {
        %>
            <logic:equal value="0" name="<%=Constants.DN%>" property="status">
                <html:image onclick="return saveDn();" src="images/but_save.gif"/><img src="images/blank.gif" width="2" height="17">
            </logic:equal>
            <logic:equal value="2" name="<%=Constants.DN%>" property="status">
                <html:image onclick="return saveDn();" src="images/but_save.gif"/><img src="images/blank.gif" width="2" height="17">
            </logic:equal>
        <%}%>

        <logic:equal value="1" name="<%=Constants.DN%>" property="kind">
            <html:image property="Back" value="Back" src="images/but_back.gif" onclick="return loadDnList();"/>
        </logic:equal>
        <logic:equal value="5" name="<%=Constants.DN%>" property="kind">
            <html:image property="Back" value="Back" src="images/but_back.gif" onclick="return loadDnTestList();"/>
        </logic:equal>

        <html:hidden property="dnId" name="<%=Constants.DN%>"/>

        <html:hidden property="createdEmp" name="<%=Constants.DN%>"/>
        <html:hidden property="kind" name="<%=Constants.DN%>"/>
        <input type="hidden" name="conId_hid" value="<bean:write property="conId" name="<%=Constants.DN%>"/>"/>
    </form>
</logic:equal>

<logic:equal value="5" name="<%=Constants.DN%>" property="kind">
    <div id="dnFormTitle"><h3>
            <bean:message key="message.dntestadd.title"/>
        </h3></div>
    <div id="errorMessageDiv"></div>
    <form name="dnForm">
        <table width="100%">
            <tr>
                <td><bean:message key="message.dn.dnNumber"/></td>
                <td><html:text size="40" readonly="true" property="dnNumber" style="background-color:#F4F4F4" name="<%=Constants.DN%>"/></td>
            </tr>
            <tr>
                <td class="lbl10"><bean:message key="message.dn.contractNumber"/></td>
                <td>
                    <html:select styleClass="lbl10" disabled="true" style="background-color:#F4F4F4" property="whichUse" name="<%=Constants.DN%>" onchange="return whichUseChangedDn(this);">
                        <html:options styleClass="lbl10" collection="<%=Constants.DN_WHICHUSE_LIST%>" property="value" labelProperty="label"/>
                    </html:select>
                    <html:text size="22" readonly="true" property="contractNumber" style="background-color:#F4F4F4" name="<%=Constants.DN%>"/>
                </td>
                <td><bean:message key="message.rfm.orgName"/></td>
                <td><html:select property="createdOrg" style="background-color:#F4F4F4" disabled="true" name="<%=Constants.DN%>" >
                        <html:options collection="<%=Constants.ORG_LIST%>" property="value" labelProperty="label"/>
                    </html:select>
                </td>
            </tr>
            <tr>
                <td><bean:message key="message.dn.expectedDate"/></td>
                <td><html:text property="expectedDate" style="background-color:#F4F4F4" readonly="true" size="10" styleId="expectedDateDn"  name="<%=Constants.DN%>" /></td>
                <td><bean:message key="message.dn.createdDate"/></td>
                <td><html:text property="createdDate" style="background-color:#F4F4F4" readonly="true" size="10" styleId="createdDateDn"  name="<%=Constants.DN%>" /></td>
            </tr>
            <tr>
                <td><bean:message key="message.dn.extensionDate"/></td>
                <td><html:text property="extensionDate" style="background-color:#F4F4F4" readonly="true" size="10" styleId="extensionDateDn"  name="<%=Constants.DN%>" /></td>
            </tr>
            <tr>
                <td><bean:message key="message.dn.deliveryPlace"/></td>
                <td colspan="3"><html:text size="100" property="deliveryPlace" style="background-color:#F4F4F4" readonly="true" name="<%=Constants.DN%>"/></td>
            </tr>
            <tr>
                <td><bean:message key="message.dn.deliveryPresenter"/></td>
                <td colspan="3"><html:text size="100" property="deliveryPresenter" style="background-color:#F4F4F4" readonly="true" name="<%=Constants.DN%>"/></td>
            </tr>
            <tr>
                <td><bean:message key="message.dn.status"/></td>
                <td><html:select property="status" name="<%=Constants.DN%>" >
                        <html:options collection="<%=Constants.DN_STATUS_LIST%>" property="value" labelProperty="label"/>
                    </html:select>
                </td>
            </tr>
            <tr>
                <td colspan="4">
                    <fieldset>
                        <legend style="color:blue;"><bean:message key="message.dn.materials"/></legend>                        
                        <p><div id="listDnMaterialDataDiv5" style="width:700px;height:300px;overflow:auto"><%@include  file="/process/purchasing/deliverynotice/detail5s.jsp" %></div></p>
                    </fieldset>
                </td>
            </tr>
        </table>
        <logic:greaterThan value="0" name="<%=Constants.DN%>" property="dnId">
            <img onclick="return printDn();" src="images/but_print.gif"/>
        </logic:greaterThan>
        <%
                    if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_EDIT, PermissionUtil.PER_PURCHASING_DELIVERYNOTICE, form.getCreatedEmp())) {
        %>
        <logic:equal value="0" name="<%=Constants.DN%>" property="status">
            <html:image onclick="return saveDn();" src="images/but_save.gif"/><img src="images/blank.gif" width="2" height="17">
        </logic:equal>
        <logic:equal value="2" name="<%=Constants.DN%>" property="status">
            <html:image onclick="return saveDn();" src="images/but_save.gif"/><img src="images/blank.gif" width="2" height="17">
        </logic:equal>
        <%}%>

        <logic:equal value="1" name="<%=Constants.DN%>" property="kind">
            <html:image property="Back" value="Back" src="images/but_back.gif" onclick="return loadDnList();"/>
        </logic:equal>
        <logic:equal value="5" name="<%=Constants.DN%>" property="kind">
            <html:image property="Back" value="Back" src="images/but_back.gif" onclick="return loadDnTestList();"/>
        </logic:equal>

        <html:hidden property="createdOrg" name="<%=Constants.DN%>"/>
        <html:hidden property="conId" name="<%=Constants.DN%>"/>
        <html:hidden property="dnId" name="<%=Constants.DN%>"/>
        <html:hidden property="drId" name="<%=Constants.DN%>"/>
        <html:hidden property="createdEmp" name="<%=Constants.DN%>"/>
        <html:hidden property="kind" name="<%=Constants.DN%>"/>
        <input type="hidden" name="conId_hid" value="<bean:write property="conId" name="<%=Constants.DN%>"/>"/>
    </form>
</logic:equal>
<div id="dnMaterialHideDiv" style="display:none"></div>