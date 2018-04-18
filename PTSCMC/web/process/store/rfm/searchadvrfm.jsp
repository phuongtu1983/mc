<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.MCUtil"%>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.venus.mc.bean.OrganizationBean"%>
<%@ page import="com.venus.mc.process.store.rfm.RfmFormBean"%>
<form name="searchRfmForm">
   <table width="100%">
        <tr>
            <td><bean:message key="message.rfm.rfmNumber"/></td>
            <td><html:text size="30" property="rfmNumber" name="<%=Constants.RFM%>"/></td>
            <td><bean:message key="message.rfm.createdDate"/></td>
            <td><html:text property="createdDate" readonly="true" size="10" styleId="createdDateRequest" onclick="javascript: showCalendar('createdDateRequest')" name="<%=Constants.RFM%>" /></td>
        </tr>
        <tr>
            <td><bean:message key="message.rfm.empName"/></td>
            <td><html:text size="30" readonly="true" property="empName" name="<%=Constants.RFM%>"/></td>
            <td><bean:message key="message.rfm.orgName"/></td>
            <td><html:text size="30" readonly="true" property="orgName" name="<%=Constants.RFM%>"/></td>
        </tr>
        <tr>
            <td colspan="6">
                <fieldset>
                    <legend class="lbl10b" style="color:blue;"><bean:message key="message.rfm.receive"/></legend>
                    <p>
                    <table width="100%" border="0">
                        <tr>
                            <td class="lbl10" width="148px"><html:radio property="receiveId" name="<%=Constants.RFM%>" value="1"><bean:message key="message.rfm.orgId"/></html:radio></td>
                            <html:select property="orgId" name="<%=Constants.RFM%>" >
                                <html:options collection="<%=Constants.ORG_LIST%>" property="value" labelProperty="label"/>
                            </html:select>
                        </tr>
                        <tr>
                            <td class="lbl10"><html:radio property="receiveId" name="<%=Constants.RFM%>" value="2"><bean:message key="message.rfm.proId"/></html:radio></td>
                            <html:select property="proId" name="<%=Constants.RFM%>" >
                                <html:options collection="<%=Constants.PROJECT_LIST%>" property="value" labelProperty="label"/>
                            </html:select>
                        </tr>
                    </table>
                    </p>
                </fieldset>
            </td>
        </tr>
        <tr>
            <td><bean:message key="message.rfm.deliveryDate"/></td>
            <td><html:text property="deliveryDate" readonly="true" size="10" styleId="deliveryDateRequest" onclick="javascript: showCalendar('deliveryDateRequest')" name="<%=Constants.RFM%>" /></td>
            <td><bean:message key="message.rfm.stoId"/></td>
            <td><html:select property="stoId" name="<%=Constants.RFM%>" onchange="return stoChanged(this);">
                    <html:options collection="<%=Constants.STORE_LIST%>" property="value" labelProperty="label"/>
                </html:select></td>
        </tr>
        <tr>
            <td><bean:message key="message.rfm.deliveryPlace"/></td>
            <td colspan="3"><html:text size="100" property="deliveryPlace" name="<%=Constants.RFM%>"/></td>
        </tr>
        <tr>
            <td><bean:message key="message.rfm.requestNumber"/></td>
            <td><span id="listRequestDataSpan"><select name="request"></select></span></td>
        </tr>
        <tr>
            <td colspan="6">
                <fieldset>
                    <legend class="lbl10b" style="color:blue;"><bean:message key="message.rfm.request"/></legend>
                    <p>
                        <table width="100%">
                            <tr>
                                <td class="lbl10"><html:radio property="reqType" name="<%=Constants.RFM%>" value="1"><bean:message key="message.rfm.reqType1"/></html:radio></td>
                                <td class="lbl10"><html:radio property="reqType" name="<%=Constants.RFM%>" value="2"><bean:message key="message.rfm.reqType2"/></html:radio></td>
                                <td class="lbl10"><html:radio property="reqType" name="<%=Constants.RFM%>" value="3"><bean:message key="message.rfm.reqType3"/></html:radio></td>
                            </tr>
                        </table>
                    </p>
                </fieldset>
            </td>
        </tr>
    </table>
    <html:image property="Submit" value="Submit" src="images/but_find.gif" style="cursor: hand;" onclick="return searchAdvRfm();"/>
    <html:hidden property="kind" name="<%=Constants.RFM%>"/>
</form>
