<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>

<div id="mrvFormTitle"><h3><bean:message key="message.mrv.title"/></h3></div>
<div class="lbl9r" id="mrvErrorMessageDiv"></div>
<form name="mrvForm">
    <table width="100%">
        <logic:equal name="<%=Constants.MRV_OBJ%>" value="0" property="msvId">
            <tr>
                <td class="lbl9"><bean:message key="message.msv.mrirnumber"/></td>
                <td colspan="5"><html:select styleClass="lbl9"  property="mrirId" onchange="selectMrir4Mrv(this, this.selectedIndex);" name="<%=Constants.MRV_OBJ%>">
                        <html:options styleClass="lbl9" collection="<%=Constants.MRIR_LIST%>" property="value" labelProperty="label"/>
                    </html:select>                    
                </td>
            </tr>
        </logic:equal>
        <logic:greaterThan name="<%=Constants.MRV_OBJ%>" value="0" property="msvId">
            <tr>
                <td><bean:message key="message.msv.mrirnumber"/></td>
                <td colspan="5"><html:text readonly="true" size="20" property="mrirNumber" name="<%=Constants.MRV_OBJ%>"/></td>                
            </tr>
        </logic:greaterThan>
        <tr>
            <td><bean:message key="message.msv.number"/></td>
            <td><html:text size="40" property="msvNumber" name="<%=Constants.MRV_OBJ%>"/></td>
            <td><bean:message key="message.msv.createddate"/></td>
            <td><html:text property="createdDate" size="10" styleId="createdDate1" onclick="javascript: showCalendar('createdDate1')" name="<%=Constants.MRV_OBJ%>" /></td>
            <td><bean:message key="message.msv.creator"/></td>
            <td><html:text readonly="true" size="20" property="createdEmpName" name="<%=Constants.MRV_OBJ%>"/></td>
        </tr>     
        <tr>
            <td><bean:message key="message.mrv.org"/></td>
            <td><html:text readonly="true" size="20" property="orgName" name="<%=Constants.MRV_OBJ%>"/></td>                    
            <td><bean:message key="message.mrv.pro"/></td>
            <td><html:text readonly="true" size="20" property="proName" name="<%=Constants.MRV_OBJ%>"/></td>                    
            <td><bean:message key="message.mrv.emp"/></td>
            <td id="mrvEmpListCbx"><html:select styleClass="lbl9"  property="deliveryEmpId" name="<%=Constants.MRV_OBJ%>">            
                    <html:options styleClass="lbl9" collection="<%=Constants.EMP_LIST%>" property="value" labelProperty="label"/>
                </html:select>
            </td>                    
        </tr>       
        <tr>
            <td><bean:message key="message.mrv.miv"/></td>
            <td colspan="3"><html:text size="40" property="mivNumbers" name="<%=Constants.MRV_OBJ%>"/></td>
            <td><bean:message key="message.msv.inputstore"/></td>
            <td><html:text size="20" property="stoName" name="<%=Constants.MRV_OBJ%>"/></td>                    
        </tr> 
        <tr>
            <td><bean:message key="message.mrv.content"/></td>
            <td colspan="5"><html:textarea cols="80" rows="4" property="description" name="<%=Constants.MRV_OBJ%>"/></td>                    
        </tr> 
        <tr>
            <td id="mrvMaterialList" colspan="6" style="width:700px;height:300px;overflow:auto">
                <%@include  file="/process/store/mrv/materiallist.jsp" %>            
            </td>        
        </tr> 
    </table>
    <logic:greaterThan name="<%=Constants.MRV_OBJ%>" value="0" property="msvId">
        <html:image  onclick="return printMrvForm();" src="images/but_print.gif"/>
    </logic:greaterThan>
    <html:image onclick="return saveMrv();" src="images/but_save.gif"/><img src="images/blank.gif" width="2" height="17">
    <html:image property="Back" value="Back" src="images/but_back.gif" onclick="return loadMrvList();"/>
    <html:hidden property="msvId" name="<%=Constants.MRV_OBJ%>"/>   
    <html:hidden property="type" name="<%=Constants.MRV_OBJ%>"/>   
    <html:hidden property="stoId" name="<%=Constants.MRV_OBJ%>"/>   
    <html:hidden property="orgId" name="<%=Constants.MRV_OBJ%>"/>
    <html:hidden property="proId" name="<%=Constants.MRV_OBJ%>"/>
    <html:hidden property="createdEmpId" name="<%=Constants.MRV_OBJ%>"/>   
</form>