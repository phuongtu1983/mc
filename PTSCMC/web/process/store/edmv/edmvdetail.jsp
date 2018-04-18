<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib prefix="display" uri="/tags/displaytag" %>
<%@ page import="com.venus.mc.util.MCUtil"%>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ page import="java.util.ArrayList"%>
<table width="100%">    
    <tr>
        <td><bean:message key="message.msv.number"/></td>
        <td><html:text size="20" property="edmvNumber" name="<%=Constants.EDMV_OBJ%>"/></td>
        <td><bean:message key="message.msv.createddate"/></td>
        <td><html:text property="createdDate" size="10" styleId="createdDate3" onclick="javascript: showCalendar('createdDate3')" name="<%=Constants.EDMV_OBJ%>" /></td>
        <td><bean:message key="message.msv.creator"/></td>
        <td><html:text readonly="true" size="20" property="createdEmpName" name="<%=Constants.EDMV_OBJ%>"/></td>
    </tr>
    <tr>
        <td><bean:message key="message.msv.deliverer"/></td>
        <td colspan="5"><html:text size="110" property="deliverer" name="<%=Constants.EDMV_OBJ%>"/></td>                    
    </tr>
    <tr>
        <td><bean:message key="message.contract.number"/></td>
        <td><html:text readonly="true" size="20" property="contract" name="<%=Constants.EDMV_OBJ%>"/></td>
        <td><bean:message key="message.u_vendor"/></td>
        <td colspan="3"><html:text size="40" property="vendor" name="<%=Constants.EDMV_OBJ%>"/></td>
    </tr>
    <tr>
        <td><bean:message key="message.mrv.org"/></td>
        <td><html:select styleId="edmvOrgListCbx" styleClass="lbl9"  property="orgId" onchange="edmvOrgChange(this, this.selectedIndex);" name="<%=Constants.EDMV_OBJ%>">            
                <html:options styleClass="lbl9" collection="<%=Constants.ORG_LIST%>" property="value" labelProperty="label"/>
            </html:select></td>
        <td><bean:message key="message.mrv.pro"/></td>
        <td><html:text size="20" readonly="true" property="proName" name="<%=Constants.EDMV_OBJ%>"/></td>
        <td><bean:message key="message.dmv.receiver"/></td>
        <td id="edmvEmpListCbx" ><html:select styleClass="lbl9"  property="receiveEmpId" name="<%=Constants.EDMV_OBJ%>">            
                <html:options styleClass="lbl9" collection="<%=Constants.EMP_LIST%>" property="value" labelProperty="label"/>
        </html:select></td>
    </tr>
    <tr>
        <td><bean:message key="message.dmv.dmvorder"/></td>
        <td colspan="5"><html:text size="110" property="dmvOrder" name="<%=Constants.EDMV_OBJ%>"/></td>                    
    </tr> 
    <tr>
        <td><bean:message key="message.dmv.content"/></td>
        <td colspan="5"><html:textarea cols="80" rows="4" property="description" name="<%=Constants.EDMV_OBJ%>"/></td>                    
    </tr> 
    <tr>
        <td><bean:message key="message.dmv.store"/></td>
        <td colspan="5"><html:text readonly="true" size="40" property="stoName" name="<%=Constants.EDMV_OBJ%>"/></td>                    
    </tr> 
    <tr>
        <td><bean:message key="message.miv.type"/></td>
        <td>
            <html:select property="kind" name="<%=Constants.EDMV_OBJ%>">
                <html:options collection="<%=Constants.DMV_KIND_LIST%>" property="value" labelProperty="label"/>
            </html:select>
        </td>
    </tr>
    <tr>
        <td colspan="6">
            <fieldset>
                <legend class="lbl10b"><bean:message key="message.mrir.materials"/></legend>               
                <div id="edmvMaterialList" style="width:700px;height:300px;overflow:auto"><%@include  file="/process/store/edmv/materiallist.jsp" %></div>
            </fieldset>
        </td>
    </tr>        
    <logic:greaterThan value="0" parameter="edmvId">
        <tr>
            <td><bean:message key="message.dmv.mrir"/></td>
            <td colspan="5">
                <logic:greaterThan value="0" property="emrirId" name="<%=Constants.EDMV_OBJ%>">
                    <html:text size="40" property="emrirNumber" readonly="true" name="<%=Constants.EDMV_OBJ%>"/>
                </logic:greaterThan>
                <logic:equal value="0" property="emrirId" name="<%=Constants.EDMV_OBJ%>">
                    <html:text size="40" property="emrirNumber" name="<%=Constants.EDMV_OBJ%>"/>
                </logic:equal>
            <input onclick="return edmvCreateMrir();" type="button" value="..."></td>
            
        </tr>
        <tr>
            <td><bean:message key="message.dmv.msv"/></td>
            <td colspan="5">
                <logic:greaterThan value="0" property="emsvId" name="<%=Constants.EDMV_OBJ%>">
                    <html:text size="40" readonly="true" property="emsvNumber" name="<%=Constants.EDMV_OBJ%>"/>
                </logic:greaterThan>
                <logic:equal value="0" property="emsvId" name="<%=Constants.EDMV_OBJ%>">
                    <html:text size="40" property="emsvNumber" name="<%=Constants.EDMV_OBJ%>"/>
                </logic:equal>
            <input onclick="return edmvCreateMsv();" type="button" value="..."></td>
        </tr>
        <tr>
            <td><bean:message key="message.dmv.rfm"/></td>
            <td colspan="5">
                <logic:greaterThan value="0" property="erfmId" name="<%=Constants.EDMV_OBJ%>">
                    <html:text size="40" readonly="true" property="erfmNumber" name="<%=Constants.EDMV_OBJ%>"/>
                </logic:greaterThan>
                <logic:equal value="0" property="erfmId" name="<%=Constants.EDMV_OBJ%>">
                    <html:text size="40" property="erfmNumber" name="<%=Constants.EDMV_OBJ%>"/>
                </logic:equal>
                <input onclick="return edmvCreateRfm();" type="button" value="..."></td>
        </tr>
        <tr>
            <td><bean:message key="message.dmv.miv"/></td>
            <td colspan="5">
                 <logic:greaterThan value="0" property="emivId" name="<%=Constants.EDMV_OBJ%>">
                    <html:text size="40" readonly="true" property="emivNumber" name="<%=Constants.EDMV_OBJ%>"/>
                </logic:greaterThan>
                <logic:equal value="0" property="emivId" name="<%=Constants.EDMV_OBJ%>">
                    <html:text size="40" property="emivNumber" name="<%=Constants.EDMV_OBJ%>"/>
                </logic:equal>
                <input onclick="return edmvCreateMiv();" type="button" value="..."></td>
        </tr>
    </logic:greaterThan>
</table>
<html:hidden property="stoId" name="<%=Constants.EDMV_OBJ%>"/>
<html:hidden property="orgId" name="<%=Constants.EDMV_OBJ%>"/>
<html:hidden property="proId" name="<%=Constants.EDMV_OBJ%>"/>
<html:hidden property="emrirId" name="<%=Constants.EDMV_OBJ%>"/>
<html:hidden property="emsvId" name="<%=Constants.EDMV_OBJ%>"/>
<html:hidden property="erfmId" name="<%=Constants.EDMV_OBJ%>"/>
<html:hidden property="emivId" name="<%=Constants.EDMV_OBJ%>"/>
<html:hidden property="edmvId" name="<%=Constants.EDMV_OBJ%>"/>