<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>
<table width="100%">
    <tr>
        <td><bean:message key="message.msv.number"/></td>
        <td><html:text size="20" property="msvNumber" name="<%=Constants.MSV_OBJ%>"/></td>
        <td><bean:message key="message.msv.createddate"/></td>
        <td><html:text property="createdDate" size="10" styleId="createdDate1" onclick="javascript: showCalendar('createdDate1')" name="<%=Constants.MSV_OBJ%>" /></td>
        <td><bean:message key="message.msv.creator"/></td>
        <td><html:text readonly="true" size="20" property="empName" name="<%=Constants.MSV_OBJ%>"/></td>
    </tr>     
    <tr>
        <td><bean:message key="message.msv.deliverer"/></td>
        <td colspan="5"><html:text size="90" property="deliverer" name="<%=Constants.MSV_OBJ%>"/></td>                    
    </tr>       
    <tr>
        <td><bean:message key="message.msv.inputstore"/></td>
        <td colspan="5"><html:text size="40" property="stoName" name="<%=Constants.MSV_OBJ%>"/></td>                    
    </tr> 
    <tr>
        <td><bean:message key="message.msv.content"/></td>
        <td colspan="5"><html:textarea cols="80" rows="4" property="content" name="<%=Constants.MSV_OBJ%>"/></td>                    
    </tr> 
    <tr>
        <td id="msvMaterialList" colspan="6" style="width:700px;height:300px;overflow:auto">
            <%@include  file="/process/store/msv/materiallist.jsp" %>            
        </td>        
    </tr> 
</table>
 <html:hidden property="msvId" name="<%=Constants.MSV_OBJ%>"/>   
 <html:hidden property="stoId" name="<%=Constants.MSV_OBJ%>"/>   
 <html:hidden property="mrirId" name="<%=Constants.MSV_OBJ%>"/>   
 <html:hidden property="empId" name="<%=Constants.MSV_OBJ%>"/>   
