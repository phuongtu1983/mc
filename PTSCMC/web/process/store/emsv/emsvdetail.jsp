<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>
<table width="100%">
    <tr>
        <td><bean:message key="message.msv.number"/></td>
        <td><html:text size="20" property="emsvNumber" name="<%=Constants.EMSV_OBJ%>"/></td>
        <td><bean:message key="message.msv.createddate"/></td>
        <td><html:text property="createdDate" size="10" styleId="createdDate1" onclick="javascript: showCalendar('createdDate1')" name="<%=Constants.EMSV_OBJ%>" /></td>
        <td><bean:message key="message.msv.creator"/></td>
        <td><html:text readonly="true" size="20" property="createdEmpName" name="<%=Constants.EMSV_OBJ%>"/></td>
    </tr>     
    <tr>
        <td><bean:message key="message.msv.deliverer"/></td>
        <td colspan="5"><html:text size="90" property="deliverer" name="<%=Constants.EMSV_OBJ%>"/></td>                    
    </tr>       
    <tr>
        <td><bean:message key="message.msv.inputstore"/></td>
        <td colspan="5">
            <logic:present name="<%=Constants.STORE_LIST%>">
                <html:select styleClass="lbl9"  property="stoId" name="<%=Constants.EMSV_OBJ%>">
                    <html:options styleClass="lbl9" collection="<%=Constants.STORE_LIST%>" property="value" labelProperty="label"/>
                </html:select>
            </logic:present>
            <logic:notPresent name="<%=Constants.STORE_LIST%>">
                <html:text size="40" property="stoName" name="<%=Constants.EMSV_OBJ%>"/>
                <html:hidden property="stoId" name="<%=Constants.EMSV_OBJ%>"/>    
            </logic:notPresent>
        </td>
    </tr>   
    <tr>
        <td id="emsvMaterialList" colspan="6" style="width:700px;height:300px;overflow:auto">
            <%@include  file="/process/store/emsv/materiallist.jsp" %>            
        </td>        
    </tr> 
</table>
<html:hidden property="emsvId" name="<%=Constants.EMSV_OBJ%>"/>   
<html:hidden property="createdEmpId" name="<%=Constants.EMSV_OBJ%>"/>   
