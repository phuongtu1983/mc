<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.MCUtil"%>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.venus.mc.bean.OrganizationBean"%>
<%@ page import="com.venus.mc.comeval.ComEvalDetailFormBean"%>
<%@ page import="com.venus.mc.bean.ComEvalVendorBean" %>
<%@ page import="com.venus.mc.tenderplan.TenderPlanFormBean" %>
<div id="comEvalFormTitle"><h3><bean:message key="message.comeval.title1"/></h3></div>
<div id="errorMessageDiv"></div>
<form name="comEvalDetailForm">
    <table width="100%">
        <tr>
            <td>&nbsp;&nbsp;&nbsp;&nbsp;<bean:message key="message.comeval.vendor"/></td>
            <td colspan="2"><html:text readonly="true" property="vendorName" size="70" name="<%=Constants.COM_EVAL_DETAIL%>"/></td>
            <td colspan="2"><bean:message key="message.comeval.rand"/></td>
            <td><html:text property="rand" readonly="true" size="15"   style="border-width:1px;text-align:right" name="<%=Constants.COM_EVAL_DETAIL%>"/></td>
        </tr>
        <tr>
            <td>&nbsp;&nbsp;&nbsp;&nbsp;<bean:message key="message.comeval.incoterm"/></td>
            <td colspan="2">

                <%
                    ComEvalVendorBean formBean = (ComEvalVendorBean) request.getAttribute(Constants.COM_EVAL_DETAIL);
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
            <td colspan="2"><bean:message key="message.comevalmaterial.rates"/></td>
            <td><html:text style="border-width:1px;text-align:right"  onblur="tryNumberFormat(this);caculateComEvalDetail2();"  property="rates" styleId="rates" size="15" name="<%=Constants.COM_EVAL_DETAIL%>"/></td>
        </tr>
        <tr>
            <td>&nbsp;&nbsp;&nbsp;&nbsp;<bean:message key="message.comevalmaterial.currency"/></td>
            <td colspan="2">
                <html:select property="currency" name="<%=Constants.COM_EVAL_DETAIL%>" >
                    <html:options collection="<%=Constants.CURRENCY_LIST%>" property="value" labelProperty="label"/>
                </html:select>
            </td>
            <td colspan="2"><bean:message key="message.comevalmaterial.ratesAfter"/></td>
            <td><html:text style="border-width:1px;text-align:right" readonly="true"  property="ratesAfter" styleId="ratesAfter" size="15" name="<%=Constants.COM_EVAL_DETAIL%>"/></td>
        </tr>
        <tr>
            <td>&nbsp;&nbsp;&nbsp;&nbsp;<bean:message key="message.comevalmaterial.paymentMethod"/></td>
            <td colspan="6"><html:text property="paymentMethod" size="120" name="<%=Constants.COM_EVAL_DETAIL%>"/></td>
        </tr>
        <tr>
            <td colspan="6">
                <fieldset>
                    <legend><bean:message key="message.comeval.danhmuc"/></legend>
                    <p><div id="listComEvalDetailDataDiv"></div></p>
                </fieldset>
            </td>
        </tr>
        <tr>
            <td colspan="6">
                <fieldset>
                    <legend><bean:message key="message.comeval.chiphi"/></legend>
                    <p>
                    <table width="100%">
                        <tr>
                            <td><bean:message key="message.comeval.customTax"/></td>
                            <td colspan="4"><html:text  property="customTax" size="90"  onblur="tryNumberFormat(this);caculateComEvalDetail2();"  style="border-width:1px;text-align:right" name="<%=Constants.COM_EVAL_DETAIL%>"/></td>
                        </tr>
                        <tr>
                            <td><bean:message key="message.comeval.costTransport"/></td>
                            <td colspan="4"><html:text  property="costTransport" size="90"  onblur="tryNumberFormat(this);caculateComEvalDetail2();"  style="border-width:1px;text-align:right" name="<%=Constants.COM_EVAL_DETAIL%>"/></td>
                        </tr>
                        <tr>
                            <td><bean:message key="message.comeval.otherTax"/></td>
                            <td colspan="4"><html:text  property="otherTax" size="90"  onblur="tryNumberFormat(this);caculateComEvalDetail2();"  style="border-width:1px;text-align:right" name="<%=Constants.COM_EVAL_DETAIL%>"/></td>
                        </tr>
                        <tr>
                            <td><bean:message key="message.comeval.priceCertificate"/></td>
                            <td colspan="4"><html:text  property="priceCertificate" size="90"  onblur="tryNumberFormat(this);caculateComEvalDetail2();"  style="border-width:1px;text-align:right" name="<%=Constants.COM_EVAL_DETAIL%>"/></td>
                        </tr>
                        <tr>
                            <td><bean:message key="message.comeval.priceToPort"/></td>
                            <td colspan="4"><html:text  property="priceToPort" size="90"  onblur="tryNumberFormat(this);caculateComEvalDetail2();"  style="border-width:1px;text-align:right" name="<%=Constants.COM_EVAL_DETAIL%>"/></td>
                        </tr>
                        <tr>
                            <td><bean:message key="message.comeval.otherCost"/></td>
                            <td colspan="4"><html:text  property="otherCost" size="90"  onblur="tryNumberFormat(this);caculateComEvalDetail2();"  style="border-width:1px;text-align:right" name="<%=Constants.COM_EVAL_DETAIL%>"/></td>
                        </tr>
                    </table>
                    </p>
                </fieldset>                        
            </td>
        </tr>
        <tr>
            <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
            <td colspan="1"></td>
            <td colspan="3"><bean:message key="message.comeval.sum"/></td>
            <td colspan="1"><html:text  property="sum" size="15"   style="border-width:1px;text-align:right" name="<%=Constants.COM_EVAL_DETAIL%>"/></td>
        </tr>
    </table>    
    <html:image style="cursor:hand;" onclick="return saveComEvalDetail();" src="images/but_save.gif"/>    
    <html:hidden property="ceId" name="<%=Constants.COM_EVAL_DETAIL%>"/>
    <html:hidden property="cevId" name="<%=Constants.COM_EVAL_DETAIL%>"/>
    <html:hidden property="tenId" name="<%=Constants.COM_EVAL_DETAIL%>"/>
    <html:hidden property="venId" name="<%=Constants.COM_EVAL_DETAIL%>"/>
</form>
<div id="comEvalGroupHideDiv" style="display:none"></div>
<div id="popupDialog2" dojoType="dijit.Dialog"></div>
