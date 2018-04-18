<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ page import="com.venus.mc.bean.MivBean"%>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<%@ page import="com.venus.mc.process.store.miv.MivFormBean"%>
<div id="mivFormTitle">
    <h3>
        <bean:define id="com" value="1"/>
        <bean:define id="part" value="2"/>
        <logic:equal name="<%=Constants.MIV%>" property="mivKind" value="${com}">
            <bean:message key="message.mivdetail.title"/>
        </logic:equal>
        <logic:equal name="<%=Constants.MIV%>" property="mivKind" value="${part}">
            <bean:message key="message.mivpartnerdetail.title"/>
        </logic:equal>
        /
        <logic:greaterThan name="<%=Constants.MIV%>" value="0" property="mivId"><bean:message key="message.detail.s"/></logic:greaterThan>
        <logic:equal name="<%=Constants.MIV%>" value="0" property="mivId"><bean:message key="message.add.s"/></logic:equal>
        </h3>
    </div>
    <div id="errorMessageDiv"></div>
    <form name="mivForm">
        <table width="100%">
        <logic:equal name="<%=Constants.MIV%>" property="mivId" value="0">
            <tr>
                <td class="lbl10"><bean:message key="message.miv.project"/></td>
                <td>
                    <html:select property="proId"  name="<%=Constants.MIV%>" onchange="return mivProChanged(this);" >
                        <html:options collection="<%=Constants.PROJECT_LIST%>" property="value" labelProperty="label"/>
                    </html:select></td>
            </tr>
            <tr>                            
                <td class="lbl10" width="148px"><bean:message key="message.miv.used"/></td>
                <td>
                    <html:select property="orgId" onchange="return whichUseChangedMiv(this,null);" name="<%=Constants.MIV%>" >
                        <html:options collection="<%=Constants.ORG_LIST%>" property="value" labelProperty="label"/>
                    </html:select></td>
            </tr>        
        </logic:equal>
        <logic:notEqual name="<%=Constants.MIV%>" property="mivId" value="0">
            <html:hidden property="proId" name="<%=Constants.MIV%>"/>
            <html:hidden property="orgId" name="<%=Constants.MIV%>"/>
            <tr>
                <td class="lbl10"><bean:message key="message.miv.project"/></td>
                <td>
                    <html:select property="proId" disabled="true" name="<%=Constants.MIV%>" >
                        <html:options collection="<%=Constants.PROJECT_LIST%>" property="value" labelProperty="label"/>
                    </html:select></td>
            </tr>
            <tr>                            
                <td class="lbl10" width="148px"><bean:message key="message.miv.used"/></td>
                <td>
                    <html:select property="orgId" disabled="true" onchange="return whichUseChangedMiv(this,null);" name="<%=Constants.MIV%>" >
                        <html:options collection="<%=Constants.ORG_LIST%>" property="value" labelProperty="label"/>
                    </html:select></td>
            </tr>       
        </logic:notEqual>
        <tr>
            <td><bean:message key="message.miv.number"/></td>
            <td id="mivNumberTd"><html:text size="50" property="mivNumber" name="<%=Constants.MIV%>"/></td>
            <td><bean:message key="message.miv.createdDate"/></td>
            <td><html:text property="createdDate" size="10" styleId="createdDateMiv" onclick="javascript: showCalendar('createdDateMiv')" name="<%=Constants.MIV%>" /></td>
        </tr>
        <tr>
            <td><bean:message key="message.miv.receiver"/></td>
            <td id="mivReceiverTd">
                <html:select property="receiver" name="<%=Constants.MIV%>">
                    <html:options collection="<%=Constants.EMPLOYEE_LIST%>" property="value" labelProperty="label"/>
                </html:select>
            </td>
            <td><bean:message key="message.miv.creator"/></td>
            <td><html:text size="20" property="creatorName" name="<%=Constants.MIV%>" disabled="true"/></td>
        </tr>        
        <tr>
            <td><bean:message key="message.miv.rfm"/></td>
            <td colspan="3">
                <logic:equal name="<%=Constants.MIV%>" property="mivId" value="0">
                    <span name="whichUseSpan" id="whichUseSpan">
                        <select name="rfmId"></select>
                    </span>                    
                </logic:equal>
                <logic:notEqual name="<%=Constants.MIV%>" property="mivId" value="0">
                    <html:text size="40" property="rfmNumber" name="<%=Constants.MIV%>" disabled="true"/>
                    <html:hidden property="rfmId" name="<%=Constants.MIV%>"/>
                </logic:notEqual>
            </td>

        </tr>
        <tr id="trRfmInfo">
            <td><bean:message key="message.miv.whichUse"/></td>
            <td><html:text size="40" property="whichUseName" name="<%=Constants.MIV%>" readonly="true"/></td>
            <td><bean:message key="message.miv.store"/></td>
            <td>
                <html:text size="20" property="storeName" name="<%=Constants.MIV%>" disabled="true"/>
                <html:hidden property="stoId" name="<%=Constants.MIV%>"/>
            </td>
        </tr>
        <tr>
            <td><bean:message key="message.miv.description"/></td>
            <td colspan="3"><html:textarea rows="3" cols="80" property="description" name="<%=Constants.MIV%>"/></td>
        </tr>
        <tr>
            <td><bean:message key="message.contract.total"/></td>
            <td colspan="3"><html:text size="20" property="total" readonly="true" name="<%=Constants.MIV%>"/></td>
        </tr>
        <logic:equal name="<%=Constants.MIV%>" property="mivKind" value="${com}">
            <tr>
                <td><bean:message key="message.miv.type"/></td>
                <td>
                    <html:select property="type" name="<%=Constants.MIV%>">
                        <html:options collection="<%=Constants.MIV_TYPE_LIST%>" property="value" labelProperty="label"/>
                    </html:select>
                </td>
            </tr>
        </logic:equal>
        <tr>
            <td colspan="4">
                <fieldset>
                    <legend><bean:message key="message.miv.materials"/></legend>
                    <div>
                        <html:image src="images/ico_xoa.gif" onclick="return delMivDetails();"/>
                        <html:image src="images/ico_them.gif" onclick="return setSelectedMivMaterial();"/>
                        <span id="listMivMaterialDataSpan"><select name="material"></select></span>
                    </div>
                    <div id="listMivMaterialDataDiv" style="width:700px;height:300px;overflow:auto"><%@include  file="/process/store/miv/details.jsp" %></div>
                </fieldset>
            </td>
        </tr>
    </table>
    <logic:greaterThan name="<%=Constants.MIV%>" value="0" property="mivId">
        <img onclick="return printMiv();" src="images/but_print.gif"/>
    </logic:greaterThan>
    <%
        MivFormBean form = (MivFormBean) request.getAttribute(Constants.MIV);
        if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_EDIT, PermissionUtil.PER_STOCK_MIV, form.getCreator())) {
    %>
    <html:image onclick="return saveMiv();" src="images/but_save.gif"/><img src="images/blank.gif" width="2" height="17">
    <%}%>
    <logic:equal name="<%=Constants.MIV%>" property="mivKind" value="${com}">
        <img src="images/but_back.gif" onclick="return loadMivList(<%=MivBean.KIND_COMPANY%>);"/>
    </logic:equal>
    <logic:equal name="<%=Constants.MIV%>" property="mivKind" value="${part}">
        <img src="images/but_back.gif" onclick="return loadMivList(<%=MivBean.KIND_PARTNER%>);"/>
    </logic:equal>
    <html:hidden property="mivId" name="<%=Constants.MIV%>"/>
    <html:hidden property="mivKind" name="<%=Constants.MIV%>"/>
    <html:hidden property="creator" name="<%=Constants.MIV%>"/>
</form>
<div id="mivMaterialHideDiv" style="display:none"></div>