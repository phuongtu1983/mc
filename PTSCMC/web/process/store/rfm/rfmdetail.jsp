<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib prefix="display" uri="/tags/displaytag" %>
<%@ page import="com.venus.mc.util.MCUtil"%>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<%@ page import="com.venus.mc.bean.OrganizationBean"%>
<%@ page import="com.venus.mc.bean.RfmBean"%>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<div id="rfmFormTitle"><h3>
        <logic:equal value="0" name="<%=Constants.RFM%>" property="kind">
            <bean:message key="message.rfmadd.title"/>
        </logic:equal>
        <logic:equal value="1" name="<%=Constants.RFM%>" property="kind">
            <bean:message key="message.rfmadd1.title"/>
        </logic:equal>
    </h3></div>
<div id="errorMessageDiv"></div>
<form name="rfmForm">
    <table width="100%">
        <tr>
            <td><bean:message key="message.rfm.rfmNumber"/></td>
            <td id="rfmNumberTd"><html:text size="60" property="rfmNumber" name="<%=Constants.RFM%>"/></td>
            <td><bean:message key="message.rfm.createdDate"/></td>
            <td><html:text property="createdDate" readonly="true" size="10" styleId="createdDateRfm" onclick="javascript: showCalendar('createdDateRfm')" name="<%=Constants.RFM%>" /></td>
        </tr>
        <tr>
            <td><bean:message key="message.rfm.empName"/></td>
            <td><html:text size="30" readonly="true" property="empName" name="<%=Constants.RFM%>"/></td>    
            <td><bean:message key="message.dn.orgHandle"/></td>
            <td><html:select property="orgHandle" name="<%=Constants.RFM%>" >
                    <html:options collection="<%=Constants.ORGANIZATION_LIST%>" property="value" labelProperty="label"/>
                </html:select>
            </td>
        </tr>
        <tr>
            <td colspan="6">
                <fieldset>
                    <legend class="lbl10b" style="color:blue;"><bean:message key="message.rfm.receive"/></legend>
                    <p>
                    <table width="100%" border="0">
                        <tr>                            
                            <td class="lbl10" width="148px"><bean:message key="message.rfm.orgId"/></td>
                            <td>
                                <html:select property="orgId" name="<%=Constants.RFM%>" >
                                    <html:options collection="<%=Constants.ORG_LIST%>" property="value" labelProperty="label"/>
                                </html:select></td>
                        </tr>
                        <tr>
                            <td class="lbl10"><bean:message key="message.rfm.proId"/></td>
                            <td>
                                <html:select property="proId" name="<%=Constants.RFM%>" onchange="return rfmProChanged(this);">
                                    <html:options collection="<%=Constants.PROJECT_LIST%>" property="value" labelProperty="label"/>
                                </html:select></td>
                        </tr>
                    </table>
                    </p>
                </fieldset>
            </td>
        </tr>
        <tr>
            <td><bean:message key="message.rfm.deliveryDate"/></td>
            <td><html:text property="deliveryDate" readonly="true" size="10" styleId="deliveryDateRfm" onclick="javascript: showCalendar('deliveryDateRfm')" name="<%=Constants.RFM%>" /></td>
            <td><bean:message key="message.rfm.stoId"/></td>
            <td>
                <logic:equal value="0" name="<%=Constants.RFM%>" property="rfmId">
                    <html:select property="stoId" name="<%=Constants.RFM%>" onchange="return stoChanged(this);">
                        <html:options collection="<%=Constants.STORE_LIST%>" property="value" labelProperty="label"/>
                    </html:select>
                </logic:equal>
                <logic:greaterThan value="0" name="<%=Constants.RFM%>" property="rfmId">
                    <html:select disabled="true" property="stoId" name="<%=Constants.RFM%>" onchange="return stoChanged(this);">
                        <html:options collection="<%=Constants.STORE_LIST%>" property="value" labelProperty="label"/>
                    </html:select>
                    <html:hidden property="stoId" name="<%=Constants.RFM%>"/>
                </logic:greaterThan>
            </td>
        </tr>
        <tr>
            <td><bean:message key="message.rfm.deliveryPlace"/></td>
            <td colspan="3"><html:text size="100" property="deliveryPlace" name="<%=Constants.RFM%>"/></td>
        </tr>    
        <tr>
            <td><bean:message key="message.purpose"/></td>
            <td colspan="3"><html:text size="100" property="purpose" name="<%=Constants.RFM%>"/></td>
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
            <!--<tr>
                <td colspan="6" class="lbl10">
        <bean:message key="message.rfm.storeApprove"/>
        <html:select styleClass="lbl10" property="storeApprove" name="<%=Constants.RFM%>">
            <html:options styleClass="lbl10" collection="<%=Constants.STORE_APPROVE_LIST%>" property="value" labelProperty="label"/>
        </html:select>
    </td>
</tr>
<tr>
    <td colspan="6"><html:textarea rows="3" cols="80" property="storeComment" name="<%=Constants.RFM%>"/></td>
</tr>
<tr>
    <td colspan="6" class="lbl10">
        <bean:message key="message.rfm.accountingApprove"/>
        <html:select styleClass="lbl10" property="accountingApprove" name="<%=Constants.RFM%>">
            <html:options styleClass="lbl10" collection="<%=Constants.ACCOUNTING_APPROVE_LIST%>" property="value" labelProperty="label"/>
        </html:select>
    </td>
</tr>
<tr>
    <td colspan="6"><html:textarea rows="3" cols="80" property="accountingComment" name="<%=Constants.RFM%>"/></td>
</tr>-->
        <tr>
            <td colspan="4">
                <fieldset>
                    <legend style="color:blue;"><bean:message key="message.rfm.materials"/></legend>
                    <div>
                        <html:image src="images/ico_xoa.gif" onclick="return delRfmDetails();"/>
                        <html:image src="images/ico_them.gif" onclick="return selectRfmMaterial('setSelectedRfmMaterial');"/>
                    </div>
                    <p><div id="listRfmMaterialDataDiv" style="width:700px;height:300px;overflow:auto"><%@include  file="/process/store/rfm/details.jsp" %></div></p>
                </fieldset>
            </td>
        </tr>        
    </table>
    <logic:greaterThan value="0" name="<%=Constants.RFM%>" property="rfmId">
        <logic:equal value="0" name="<%=Constants.RFM%>" property="kind">
            <img onclick="return printRfm();" src="images/but_print.gif"/>
        </logic:equal>
        <logic:equal value="1" name="<%=Constants.RFM%>" property="kind">
            <img onclick="return printRfm1();" src="images/but_print.gif"/>
        </logic:equal>
    </logic:greaterThan>
    <%
        RfmBean form = (RfmBean) request.getAttribute(Constants.RFM);
        if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_EDIT, PermissionUtil.PER_STOCK_RFM, form.getCreatedEmp())) {
    %>
    <logic:equal value="1" name="<%=Constants.RFM%>" property="canSave">
        <logic:equal value="0" name="<%=Constants.RFM%>" property="kind">
            <html:image onclick="return saveRfm();" src="images/but_save.gif"/><img src="images/blank.gif" width="2" height="17">
        </logic:equal>
        <logic:equal value="1" name="<%=Constants.RFM%>" property="kind">
            <html:image onclick="return saveRfm1();" src="images/but_save.gif"/><img src="images/blank.gif" width="2" height="17">
        </logic:equal>
    </logic:equal>
    <%}%>
    <logic:equal value="0" name="<%=Constants.RFM%>" property="kind">
        <html:image property="Back" value="Back" src="images/but_back.gif" onclick="return loadRfmList();"/>
    </logic:equal>
    <logic:equal value="1" name="<%=Constants.RFM%>" property="kind">
        <html:image property="Back" value="Back" src="images/but_back.gif" onclick="return loadRfmList1();"/>
    </logic:equal>
    <html:hidden property="rfmId" name="<%=Constants.RFM%>"/>    
    <html:hidden property="creator" name="<%=Constants.RFM%>"/>
    <html:hidden property="kind" name="<%=Constants.RFM%>"/>
</form>
<div id="rfmMaterialHideDiv" style="display:none"></div>