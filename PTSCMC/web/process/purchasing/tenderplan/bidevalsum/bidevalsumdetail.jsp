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
<%@ page import ="com.venus.core.util.*"%>
<%
            int evalKind = (int) NumberUtil.parseInt(request.getAttribute(Constants.TENDERPLAN_EVALKIND).toString(), 0);
%>
<div id="bidEvalSumFormTitle"><h3><bean:message key="message.bidevalsum.title1"/></h3></div>
<div id="errorMessageDiv"></div>
<form name="bidEvalSumDetailForm">
    <table width="100%">
        <tr>
            <td width="120px"><bean:message key="message.bidevalsum.vendor"/></td>
            <td width="70px"><html:text readonly="true" property="vendorName" size="70" name="<%=Constants.BID_EVAL_SUM_DETAIL%>"/></td>
        </tr>
        <tr>            
            <td width="120px"><bean:message key="message.bidevalsum.deliveryTime"/></td>
            <td><html:text property="deliveryTime" size="70" name="<%=Constants.BID_EVAL_SUM_DETAIL%>"/></td>
        </tr>
        <tr>
            <td width="120px"><bean:message key="message.bidevalsum.paymentMode"/></td>
            <td>
                <html:select property="paymentMode" name="<%=Constants.BID_EVAL_SUM_DETAIL%>">
                    <html:options collection="<%=Constants.PAYMENT_MODE_LIST%>" property="value" labelProperty="label"/>
                </html:select>
            </td>
        </tr>
        <tr>
            <td colspan="6">
                <fieldset>
                    <legend><bean:message key="message.bidevalsum.vttb"/></legend>
                    <p><div id="listBidEvalSumDetailDataDiv"></div></p>
                </fieldset>
            </td>
        </tr>
        <tr>
            <td colspan="6">
                <fieldset>
                    <legend><bean:message key="message.bidevalsum.chiphi"/></legend>
                    <p>
                    <table width="100%" >
                        <tr>
                            <td width="130px"><bean:message key="message.bidevalsum.costTransport"/></td>
                            <td width="130px"><html:text style="text-align:right" readonly="true" property="costTransport"   size="20" name="<%=Constants.BID_EVAL_SUM_DETAIL%>"/></td>
                        </tr>
                        <tr>
                            <td width="130px"><bean:message key="message.bidevalsum.otherTax"/></td>
                            <td width="130px"><html:text style="text-align:right" readonly="true" property="otherTax"  size="20" name="<%=Constants.BID_EVAL_SUM_DETAIL%>"/></td>
                        </tr>
                        <tr>
                            <td width="130px"><bean:message key="message.bidevalsum.otherCost"/></td>
                            <td width="130px"><html:text style="text-align:right" readonly="true" property="otherCost"  size="20" name="<%=Constants.BID_EVAL_SUM_DETAIL%>"/></td>
                        </tr>
                        <% if (evalKind==0){ %>
                            <tr>
                                <td width="130px"><bean:message key="message.bidevalsum.sum"/></td>
                                <td width="130px"><html:text  style="text-align:right" readonly="true" property="sum"  size="20" name="<%=Constants.BID_EVAL_SUM_DETAIL%>"/></td>
                            </tr>
                        <% } %>
                    </table>
                    </p>
                </fieldset>
            </td>
        </tr>
    </table>
    <html:image style="cursor:hand;" onclick="return saveBidEvalSumDetail();" src="images/but_save.gif"/>
    <html:hidden property="besvId" name="<%=Constants.BID_EVAL_SUM_DETAIL%>"/>
    <html:hidden property="venId" name="<%=Constants.BID_EVAL_SUM_DETAIL%>"/>
    <html:hidden property="tenId" name="<%=Constants.BID_EVAL_SUM_DETAIL%>"/>
</form>
<div id="bidEvalSumGroupHideDiv" style="display:none"></div>
<div id="popupDialog2" dojoType="dijit.Dialog"></div>