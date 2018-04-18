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

<div id="dmvFormTitle"><h3><bean:message key="message.dmv.title"/></h3></div>
<div id="dmvErrorMessageDiv"></div>
<form name="dmvForm">
    <table width="100%">   
        <logic:equal name="<%=Constants.DMV_OBJ%>" value="0" property="msvId">
            <tr>
                <td class="lbl9"><bean:message key="message.msv.mrirnumber"/></td>
                <td colspan="5"><html:select styleClass="lbl9"  property="mrirId" onchange="selectMrir4Dmv(this, this.selectedIndex);" name="<%=Constants.DMV_OBJ%>">
                        <html:options styleClass="lbl9" collection="<%=Constants.MRIR_LIST%>" property="value" labelProperty="label"/>
                    </html:select>                    
                </td>
            </tr>
        </logic:equal>
        <logic:greaterThan name="<%=Constants.DMV_OBJ%>" value="0" property="msvId">
            <tr>
                <td><bean:message key="message.msv.mrirnumber"/></td>
                <td colspan="5"><html:text readonly="true" size="20" property="mrirNumber" name="<%=Constants.DMV_OBJ%>"/></td>                
            </tr>
        </logic:greaterThan>
        <tr>
            <td><bean:message key="message.msv.number"/></td>
            <td><html:text size="40" property="msvNumber" name="<%=Constants.DMV_OBJ%>"/></td>
            <td><bean:message key="message.msv.createddate"/></td>
            <td><html:text property="createdDate" size="10" styleId="createdDate3" onclick="javascript: showCalendar('createdDate3')" name="<%=Constants.DMV_OBJ%>" /></td>
            <td><bean:message key="message.msv.creator"/></td>
            <td><html:text readonly="true" size="20" property="createdEmpName" name="<%=Constants.DMV_OBJ%>"/></td>
        </tr>
        <tr>
            <td><bean:message key="message.msv.deliverer"/></td>
            <td colspan="5"><html:text size="110" property="deliverer" name="<%=Constants.DMV_OBJ%>"/></td>                    
        </tr>
        <tr>
            <td><bean:message key="message.contract.number"/></td>
            <td><html:text readonly="true" size="20" property="conNumber" name="<%=Constants.DMV_OBJ%>"/></td>
            <td><bean:message key="message.u_vendor"/></td>
            <td colspan="3"><html:text readonly="true" size="40" property="vendorName" name="<%=Constants.DMV_OBJ%>"/></td>
        </tr>
        <tr>
            <td><bean:message key="message.mrv.org"/></td>
            <td><html:text readonly="true" size="20" property="orgName" name="<%=Constants.DMV_OBJ%>"/></td>
            <td><bean:message key="message.mrv.pro"/></td>
            <td><html:text readonly="true" size="20" property="proName" name="<%=Constants.DMV_OBJ%>"/></td>
            <td><bean:message key="message.dmv.receiver"/></td>
            <td><html:select styleId="dmvEmpListCbx" styleClass="lbl9"  property="receiveEmpId" name="<%=Constants.DMV_OBJ%>">            
                    <html:options styleClass="lbl9" collection="<%=Constants.EMP_LIST%>" property="value" labelProperty="label"/>
            </html:select></td>
        </tr>
        <tr>
            <td><bean:message key="message.dmv.dmvorder"/></td>
            <td colspan="5"><html:text size="110" property="dmvOrder" name="<%=Constants.DMV_OBJ%>"/></td>                    
        </tr> 
        <tr>
            <td><bean:message key="message.dmv.content"/></td>
            <td colspan="5"><html:textarea cols="80" rows="4" property="description" name="<%=Constants.DMV_OBJ%>"/></td>                    
        </tr> 
        <tr>
            <td><bean:message key="message.dmv.store"/></td>
            <td colspan="5"><html:text readonly="true" size="40" property="stoName" name="<%=Constants.DMV_OBJ%>"/></td>                    
        </tr> 
        <tr>
            <td><bean:message key="message.miv.type"/></td>
            <td>
                <html:select property="kind" name="<%=Constants.DMV_OBJ%>">
                    <html:options collection="<%=Constants.MSV_KIND_LIST%>" property="value" labelProperty="label"/>
                </html:select>
            </td>
        </tr>
        <tr>
            <td colspan="6">
                <fieldset>
                    <legend class="lbl10b"><bean:message key="message.mrir.materials"/></legend>
                    <!--
                <logic:present name="<%=Constants.MATERIAL_LIST%>"> 
                    <html:image src="images/ico_xoa.gif" onclick="return dmvRemoveMatFromTable();"/>
                    <html:image src="images/ico_them.gif" onclick="return dmvAddMatToTable();"/>
                    <select name="cbxMaterialList">
                        <logic:iterate id="mat_iter" name="<%=Constants.MATERIAL_LIST%>">                        
                            <option class="lbl9" value="${mat_iter.detId}">${mat_iter.matName}</option>
                        </logic:iterate>
                    </select>
                </logic:present>
                    -->
                    <div id="dmvMaterialList" style="width:700px;height:300px;overflow:auto"><%@include  file="/process/store/dmv/materiallist.jsp" %></div>
                </fieldset>
            </td>
        </tr>                  
    </table>
    <logic:greaterThan name="<%=Constants.DMV_OBJ%>" value="0" property="msvId">
        <html:image onclick="return printDmvForm();" src="images/but_print.gif"/>
    </logic:greaterThan>
                <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_EDIT, PermissionUtil.PER_STORE)) {%>
    <html:image onclick="return saveDmv();" src="images/but_save.gif"/><img src="images/blank.gif" width="2" height="17">
    <%}%>
    <html:image property="Back" value="Back" src="images/but_back.gif" onclick="return loadDmvList();"/>  
    <html:hidden property="type" name="<%=Constants.DMV_OBJ%>"/>
    <html:hidden property="stoId" name="<%=Constants.DMV_OBJ%>"/>
    <html:hidden property="orgId" name="<%=Constants.DMV_OBJ%>"/>
    <html:hidden property="proId" name="<%=Constants.DMV_OBJ%>"/>
    <html:hidden property="msvId" name="<%=Constants.DMV_OBJ%>"/>
</form>