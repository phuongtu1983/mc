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
<%@ page import="com.venus.mc.comevalmaterial.ComEvalMaterialDetailFormBean"%>
<%@ page import="com.venus.mc.bean.ComEvalMaterialBean" %>
<%@ page import="com.venus.mc.tenderplan.TenderPlanFormBean" %>
<div id="comEvalFormTitle"><h3><bean:message key="message.comevalmaterial.title1"/></h3></div>
<div id="errorMessageDiv"></div>
<form name="comEvalMaterialDetailForm">
    <table width="100%">
        <tr>
            <td>&nbsp;&nbsp;&nbsp;&nbsp;<bean:message key="message.comevalmaterial.vendorName"/></td>
            <td colspan="2"><html:text readonly="true" property="vendorName" size="100" name="<%=Constants.COM_EVAL_DETAIL%>"/></td>
            <td colspan="2"><bean:message key="message.comevalmaterial.rates"/></td>
            <td><html:text style="border-width:1px;text-align:right"  onblur="tryNumberFormat(this);caculateComEvalMaterialDetail2();"  property="rates" styleId="rates" size="10" name="<%=Constants.COM_EVAL_DETAIL%>"/></td>
        </tr>
        <tr>
            <td>&nbsp;&nbsp;&nbsp;&nbsp;<bean:message key="message.comevalmaterial.incoterm"/></td>
            <td colspan="2">
                <%
                    ComEvalMaterialBean formBean = (ComEvalMaterialBean) request.getAttribute(Constants.COM_EVAL_DETAIL);
                    if (formBean.getForm() == TenderPlanFormBean.FORM_PRIVATE) {
                %>
                <html:select disabled="true" property="incoterm" name="<%=Constants.COM_EVAL_DETAIL%>" >
                    <html:options collection="<%=Constants.TENDERPLAN_BIDOPENING_INCOTERM%>" property="incId" labelProperty="incName"/>
                </html:select>
                <%} else {%>
                <html:select property="incoterm" name="<%=Constants.COM_EVAL_DETAIL%>" >
                    <html:options collection="<%=Constants.TENDERPLAN_BIDOPENING_INCOTERM%>" property="incId" labelProperty="incName"/>
                </html:select>
                <%}%>
            </td>
            <td colspan="2"><bean:message key="message.comevalmaterial.ratesAfter"/></td>
            <td><html:text style="border-width:1px;text-align:right" readonly="true"  property="ratesAfter" styleId="ratesAfter" size="10" name="<%=Constants.COM_EVAL_DETAIL%>"/></td>
        </tr>
        <tr>
            <td>&nbsp;&nbsp;&nbsp;&nbsp;<bean:message key="message.comevalmaterial.currency"/></td>
            <td colspan="2">
                <html:select property="currency" name="<%=Constants.COM_EVAL_DETAIL%>" >
                    <html:options collection="<%=Constants.CURRENCY_LIST%>" property="value" labelProperty="label"/>
                </html:select>
            </td>
        </tr>
        <tr>
            <td colspan="6">
                <fieldset>
                    <legend><bean:message key="message.comevalmaterial.ncc"/></legend>
                    <p><div id="listComEvalMaterialDetailDataDiv"></div></p>
                </fieldset>
            </td>
        </tr>
        <tr>
            <td>&nbsp;&nbsp;&nbsp;&nbsp;<bean:message key="message.comevalmaterial.paymentMethod"/></td>
            <td colspan="2"><html:text property="paymentMethod" size="120" name="<%=Constants.COM_EVAL_DETAIL%>"/></td>
        </tr>
        <tr>
            <td>&nbsp;&nbsp;&nbsp;&nbsp;<bean:message key="message.comevalmaterial.guaranteeContract"/></td>
            <td colspan="2"><html:text property="guaranteeContract" size="120" name="<%=Constants.COM_EVAL_DETAIL%>"/></td>
        </tr>
    </table>    
    <html:image onclick="return saveComEvalMaterialDetail();" src="images/but_save.gif"/>    
    <html:hidden property="cemId" name="<%=Constants.COM_EVAL_DETAIL%>"/>
    <html:hidden property="tenId" name="<%=Constants.COM_EVAL_DETAIL%>"/>
    <html:hidden property="venId" name="<%=Constants.COM_EVAL_DETAIL%>"/>
</form>
<div id="comEvalGroupHideDiv" style="display:none"></div>
<div id="popupDialog2" dojoType="dijit.Dialog"></div>
