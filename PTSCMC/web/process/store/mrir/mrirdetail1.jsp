<%-- 
    Document   : emrirdetail
    Created on : Oct 16, 2009, 3:21:51 PM
    Author     : kngo
--%>

<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib prefix="display" uri="/tags/displaytag" %>
<%@ page import="com.venus.mc.util.MCUtil"%>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.venus.mc.process.store.mrir.MrirFormBean"%>

<div id="mrirFormTitle"><h3><bean:message key="message.mriradd.title"/></h3></div>
<div id="errorMessageDiv"></div>
<form name="mrirForm">
    <table width="100%">
        <tr>
            <td class="lbl10"><bean:message key="message.mrir.mrirNumber"/></td>
            <td colspan="2"><html:text styleClass="lbl10" size="30" property="mrirNumber" name="<%=Constants.MRIR%>"/></td>
            <td class="lbl10"><bean:message key="message.mrir.createdDate"/></td>
            <td colspan="2"><html:text styleClass="lbl10" property="createdDate" styleId="createdDateMrir" size="30" name="<%=Constants.MRIR%>" disabled="true" /></td>
        </tr>
        <tr>
            <td class="lbl10"><bean:message key="message.mrir.dnId"/></td>
            <td colspan="2">
                <logic:equal value="0" name="<%=Constants.MRIR%>" property="mrirId">
                    <html:select styleClass="lbl10" property="dnId" name="<%=Constants.MRIR%>" onchange="return dnIdChangedMrir(this);">
                        <html:options styleClass="lbl10" collection="<%=Constants.MRIR_DELIVERY_NOTICE_LIST%>" property="dnId" labelProperty="dnNumber"/>
                    </html:select>
                </logic:equal>
                <logic:notEqual value="0" name="<%=Constants.MRIR%>" property="mrirId">
                    <html:select styleClass="lbl10" property="dnId" name="<%=Constants.MRIR%>" disabled="true" onchange="return dnIdChangedMrir(this);">
                        <html:options styleClass="lbl10" collection="<%=Constants.MRIR_DELIVERY_NOTICE_LIST%>" property="dnId" labelProperty="dnNumber"/>
                    </html:select>                    
                </logic:notEqual>
            </td>
        </tr> 
        <tr>
            <td id="mrirRequestVendorDiv" colspan="6" class="lbl10"> 
                <table width="100%">
                    <tr>
                        <td class="lbl10"><bean:message key="message.mrir.venId"/></td>
                        <td colspan="2"><html:text styleClass="lbl10" size="50" property="venName" name="<%=Constants.MRIR%>" disabled="true" /></td>
                        <td class="lbl10"><bean:message key="message.mrir.reqId"/></td>
                        <td colspan="2">
                            <html:select styleClass="lbl10" property="reqId" name="<%=Constants.MRIR%>" onchange="return reqIdChangedMrir(this);">
                                <html:options styleClass="lbl10" collection="<%=Constants.MRIR_REQUEST_LIST%>" property="reqId" labelProperty="requestNumber"/>
                            </html:select>
                        </td>  
                    </tr>
                </table>
            </td>
        </tr>
        <tr>
            <td colspan="6" class="lbl10"><bean:message key="message.mrir.note"/>
            <br/><html:textarea cols="100" rows="4" property="note" name="<%=Constants.MRIR%>"/></td>
        </tr>
        <tr>
            <td class="lbl10"><bean:message key="message.mrir.blNo"/></td>
            <td colspan="2"><html:text styleClass="lbl10" size="20" property="blNo" name="<%=Constants.MRIR%>" /></td>
            <td class="lbl10"><bean:message key="message.mrir.invoiceNo"/></td>
            <td colspan="2"><html:text styleClass="lbl10" size="20" property="invoiceNo" name="<%=Constants.MRIR%>" /></td>
        </tr>
        <tr>
            <td class="lbl10"><bean:message key="message.mrir.plNo"/></td>
            <td colspan="2"><html:text styleClass="lbl10" size="20" property="plNo" name="<%=Constants.MRIR%>" /></td>
        </tr>
        <tr>
            <td colspan="6">
                <fieldset>
                    <legend class="lbl10b"><bean:message key="message.mrir.materials"/></legend>
                    <html:image src="images/ico_xoa.gif" onclick="return delMrirDetails();"/>
                    <html:image src="images/ico_them.gif" onclick="return addMrirDetail();"/>
                    <span id="listMaterialByReqIdDiv"></span>
                    <p><div id="listMrirDetailDataDiv"></div></p>
                </fieldset>
            </td>
        </tr>        
    </table>
    <logic:greaterThan name="<%=Constants.MRIR%>" value="0" property="mrirId">
        <img onclick="return printMrir();" src="images/but_print.gif"/>
    </logic:greaterThan>
    <html:image onclick="return saveMrir();" src="images/but_save.gif"/><img src="images/blank.gif" width="2" height="17"><html:image property="Back" value="Back" src="images/but_back.gif" onclick="return loadMrirList();"/>
    <html:hidden property="mrirId" name="<%=Constants.MRIR%>"/>
</form>
<div id="mrirDetailHideDiv" style="display:none"></div>