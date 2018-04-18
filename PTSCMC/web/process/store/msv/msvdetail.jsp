<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>

<div id="msvFormTitle"><h3><bean:message key="message.msv.title"/></h3></div>
<div id="errorMessageDiv"></div>
<form name="msvForm">
    <table width="100%">
        <logic:equal name="<%=Constants.MSV_OBJ%>" value="0" property="msvId">
            <tr>
                <td class="lbl9"><bean:message key="message.msv.mrirnumber"/></td>
                <td colspan="5"><html:select styleClass="lbl9"  property="mrirId" onchange="selectMrir4Msv(this, this.selectedIndex)" name="<%=Constants.MSV_OBJ%>">
                        <html:options styleClass="lbl9" collection="<%=Constants.MRIR_LIST%>" property="value" labelProperty="label"/>
                    </html:select></td>
            </tr>
        </logic:equal>
        <logic:greaterThan name="<%=Constants.MSV_OBJ%>" value="0" property="msvId">
            <tr>
                <td><bean:message key="message.msv.mrirnumber"/></td>
                <td colspan="5"><html:text readonly="true" size="40" property="mrirNumber" name="<%=Constants.MSV_OBJ%>"/>
                    <html:hidden property="mrirId" name="<%=Constants.MSV_OBJ%>"/></td>                
            </tr>
        </logic:greaterThan>
        <tr>
            <td><bean:message key="message.msv.number"/></td>
            <td><html:text size="40" property="msvNumber" name="<%=Constants.MSV_OBJ%>"/></td>
            <td><bean:message key="message.msv.createddate"/></td>
            <td><html:text property="createdDate" size="10" styleId="createdDate1" onclick="javascript: showCalendar('createdDate1')" name="<%=Constants.MSV_OBJ%>" /></td>
            <td><bean:message key="message.msv.creator"/></td>
            <td><html:text readonly="true" size="20" property="createdEmpName" name="<%=Constants.MSV_OBJ%>"/></td>
        </tr>     
        <tr>
            <td><bean:message key="message.msv.deliverer"/></td>
            <td colspan="5">
                <logic:equal name="<%=Constants.MSV_OBJ%>" property="msvId" value="0">
                <span name="whichUseSpan" id="whichUseSpan">
                    <text name="deliverer"></text>
                </span>
                </logic:equal>
                <logic:notEqual name="<%=Constants.MSV_OBJ%>" property="msvId" value="0">
                <html:text readonly="true" size="20" property="deliverer" name="<%=Constants.MSV_OBJ%>"/>
                </logic:notEqual>
            </td>                    
        </tr>       

        <tr>
            <td><bean:message key="message.msv.inputstore"/></td>
            <td colspan="5">
                <logic:present name="<%=Constants.MSV_STORE_LIST%>">
                    <html:select property="stoId" onclick="msvStoreChanged(this,this.selectedIndex);" name="<%=Constants.MSV_OBJ%>">
                        <html:options collection="<%=Constants.MSV_STORE_LIST%>" property="value" labelProperty="label"/>
                    </html:select>
                </logic:present>
                <logic:notPresent name="<%=Constants.MSV_STORE_LIST%>">
                    <html:text readonly="true" size="40" property="stoName" name="<%=Constants.MSV_OBJ%>"/>
                    <html:hidden property="stoId" name="<%=Constants.MSV_OBJ%>"/>
                </logic:notPresent>
            </td>                    
        </tr> 

        <tr>
            <td><bean:message key="message.msv.content"/></td>
            <td colspan="5"><html:textarea cols="80" rows="4" property="description" name="<%=Constants.MSV_OBJ%>"/></td>                    
        </tr> 
        <tr>
            <td><bean:message key="message.miv.type"/></td>
            <td>
                <html:select property="kind" name="<%=Constants.MSV_OBJ%>">
                    <html:options collection="<%=Constants.MSV_KIND_LIST%>" property="value" labelProperty="label"/>
                </html:select>
            </td>
        </tr>
        <tr>
            <td id="msvMaterialList" colspan="6" style="width:700px;height:300px;overflow:auto">
                <%@include  file="/process/store/msv/materiallist.jsp" %>            
            </td>        
        </tr> 
    </table>
    <logic:greaterThan name="<%=Constants.MSV_OBJ%>" value="0" property="msvId">
        <html:image  onclick="return printMsvForm();" src="images/but_print.gif"/>
    </logic:greaterThan>
    <html:image onclick="return saveMsv1();" src="images/but_save.gif"/><img src="images/blank.gif" width="2" height="17">
    <html:image property="Back" value="Back" src="images/but_back.gif" onclick="return loadMsvList();"/>      
    <html:hidden property="type" name="<%=Constants.MSV_OBJ%>"/>   
    <html:hidden property="createdEmpId" name="<%=Constants.MSV_OBJ%>"/>   
    <html:hidden property="msvId" name="<%=Constants.MSV_OBJ%>"/>
</form>