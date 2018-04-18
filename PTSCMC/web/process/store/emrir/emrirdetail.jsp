<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib prefix="display" uri="/tags/displaytag" %>
<%@ page import="com.venus.mc.util.MCUtil"%>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.venus.mc.process.store.emrir.EmrirFormBean"%>

<div id="emrirFormTitle"><h3><bean:message key="message.emriradd.title"/></h3></div>
<div id="emrirErrorMessageDiv"></div>
<form name="emrirForm">
    <table width="100%">
        <tr>
            <td class="lbl10"><bean:message key="message.emrir.emrirNumber"/></td>
            <td colspan="2"><html:text styleClass="lbl10" size="30" property="emrirNumber" name="<%=Constants.EMRIR%>"/></td>
            <td class="lbl10"><bean:message key="message.emrir.createdDate"/></td>
            <td colspan="2"><html:text styleClass="lbl10" property="createdDate" styleId="createdDateEmrir" size="30" name="<%=Constants.EMRIR%>" disabled="true" /></td>
        </tr>
        <tr>
            <td class="lbl10"><bean:message key="message.emrir.ednId"/></td>
            <td colspan="2">
                <logic:equal value="0" name="<%=Constants.EMRIR%>" property="emrirId">
                    <html:select styleClass="lbl10" property="ednId" name="<%=Constants.EMRIR%>" onchange="return ednIdChangedEmrir(this);">
                        <html:options styleClass="lbl10" collection="<%=Constants.EMRIR_DELIVERY_NOTICE_LIST%>" property="ednId" labelProperty="ednNumber"/>
                    </html:select>
                </logic:equal>
                <logic:notEqual value="0" name="<%=Constants.EMRIR%>" property="emrirId">
                    <html:select styleClass="lbl10" property="ednId" name="<%=Constants.EMRIR%>" disabled="true" onchange="return ednIdChangedEmrir(this);">
                        <html:options styleClass="lbl10" collection="<%=Constants.EMRIR_DELIVERY_NOTICE_LIST%>" property="ednId" labelProperty="ednNumber"/>
                    </html:select>
                </logic:notEqual>
            </td>
            <td id="econNumberDiv" colspan="4" class="lbl10"></td>
        </tr>
        <tr>
            <td colspan="6" class="lbl10"><bean:message key="message.emrir.note"/>
            <br/><html:textarea cols="100" rows="4" property="note" name="<%=Constants.EMRIR%>"/></td>
        </tr>
        <tr>
            <td class="lbl10"><bean:message key="message.emrir.blNo"/></td>
            <td colspan="2"><html:text styleClass="lbl10" size="20" property="blNo" name="<%=Constants.EMRIR%>" /></td>
            <td class="lbl10"><bean:message key="message.emrir.invoiceNo"/></td>
            <td colspan="2"><html:text styleClass="lbl10" size="20" property="invoiceNo" name="<%=Constants.EMRIR%>" /></td>
        </tr>
        <tr>
            <td class="lbl10"><bean:message key="message.emrir.plNo"/></td>
            <td colspan="2"><html:text styleClass="lbl10" size="20" property="plNo" name="<%=Constants.EMRIR%>" /></td>
        </tr>
        <tr>
            <td colspan="6">
                <fieldset>
                    <legend class="lbl10b"><bean:message key="message.emrir.materials"/></legend>
                    <html:image src="images/ico_xoa.gif" onclick="return delEmrirDetails();"/>
                    <html:image src="images/ico_them.gif" onclick="return addEmrirDetail();"/>
                    <span id="listMaterialByDnIdDiv"></span>
                    <p><div id="listEmrirDetailDataDiv"></div></p>
                </fieldset>
            </td>
        </tr>        
    </table>
    <logic:greaterThan name="<%=Constants.EMRIR%>" value="0" property="emrirId">
        <img onclick="return printEmrir();" src="images/but_print.gif"/>
    </logic:greaterThan>
    <html:image onclick="return saveEmrir();" src="images/but_save.gif"/><img src="images/blank.gif" width="2" height="17">
    
    <html:image property="Back" value="Back" src="images/but_back.gif" onclick="return loadEmrirList();"/>
    <html:hidden property="emrirId" name="<%=Constants.EMRIR%>"/>
</form>
<div id="emrirDetailHideDiv" style="display:none"></div>