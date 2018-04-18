<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib prefix="display" uri="/tags/displaytag" %>
<%@ page import="com.venus.mc.util.MCUtil"%>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.venus.mc.process.store.mrir.OsDFormBean"%>

<div id="osDFormTitle"><h3><bean:message key="message.osdadd.title"/></h3></div>
<div id="osDErrorMessageDiv"></div>
<form name="osDForm">
    <table width="100%">
        <tr>
            <td class="lbl10"><bean:message key="message.osd.osdNumber"/></td>
            <td><html:text styleClass="lbl10" size="30" property="osdNumber" name="<%=Constants.OSD%>"/></td>
            <td class="lbl10"><bean:message key="message.osd.createdDate"/></td>
            <td><html:text styleClass="lbl10" property="createdDate" styleId="createdDateOsD" size="30" name="<%=Constants.OSD%>" readonly="true" /></td>
        </tr>  
        <tr>
            <td class="lbl10"><bean:message key="message.osd.conNumber"/></td>
            <td><html:text styleClass="lbl10" size="30" property="conNumber" name="<%=Constants.OSD%>" readonly="true"/></td>
            <td class="lbl10"><bean:message key="message.osd.dnNumber"/></td>
            <td><html:text styleClass="lbl10" size="30" property="dnNumber" name="<%=Constants.OSD%>" readonly="true"/></td>            
        </tr>
        <tr>
            <td class="lbl10"><bean:message key="message.osd.venName"/></td>
            <td><html:text styleClass="lbl10" size="30" property="venName" name="<%=Constants.OSD%>" readonly="true"/></td>
            <td class="lbl10"><bean:message key="message.osd.createdEmp"/></td>
            <td><html:text styleClass="lbl10" size="30" property="createdEmpName" name="<%=Constants.OSD%>" readonly="true"/></td>
        </tr>  
        <tr>
            <td colspan="4">
                <fieldset>
                    <legend class="lbl10b" ><bean:message key="message.osd.nonConform"/></legend>
                    <p>
                    <table width="100%">
                        <tr>
                            <td class="lbl10"><html:multibox property="nonConform" name="<%=Constants.OSD%>" value="1"></html:multibox> <bean:message key="message.osd.nonConform1"/></td>
                            <td class="lbl10"><html:multibox property="nonConform" name="<%=Constants.OSD%>" value="2"></html:multibox> <bean:message key="message.osd.nonConform2"/></td>
                            <td class="lbl10"><html:multibox property="nonConform" name="<%=Constants.OSD%>" value="4"></html:multibox> <bean:message key="message.osd.nonConform4"/></td>
                            <td class="lbl10"><html:multibox property="nonConform" name="<%=Constants.OSD%>" value="8"></html:multibox> <bean:message key="message.osd.nonConform8"/></td>
                            </tr>
                        </table></p>
                    </fieldset>
                </td>
            </tr>
        <logic:present name="<%=Constants.MRIR_REQUEST_LIST%>"> 
            <tr>
                <td colspan="4">
                    <fieldset>                                        
                        <legend class="lbl10b"><bean:message key="message.mrir.reqnote"/></legend>                        
                        <select name="reqId" onchange="return osDReqChanged(this,this.selectedIndex);">
                            <logic:iterate id="iter_req" name="<%=Constants.MRIR_REQUEST_LIST%>">
                                <option value="<bean:write name="iter_req" property="value"/>"><bean:write name="iter_req" property="label"/></option>
                            </logic:iterate>
                        </select>        
                    </fieldset>   
                </td>                
            </tr> 
        </logic:present>
        <tr>
            <td colspan="4">
                <fieldset>
                    <legend class="lbl10b"><bean:message key="message.osd.materials"/></legend>
                    <html:image src="images/ico_print.gif" onclick="return printOsDMatList();"/>
                    <html:image src="images/ico_xoa.gif" onclick="return osDDelMaterial();"/>
                    <html:image src="images/ico_them.gif" onclick="return osDAddMaterial();"/>
                    <html:image src="images/ico_them_mrir.gif" onclick="return osDCreateMrir();"/>
                    <span id="osDListMaterialByReqDiv"> 
                        <select name="cbxMaterialOfReq">
                            <logic:iterate id="iter_mat" name="<%=Constants.OSD_MATERIAL_LIST%>">
                                <option value="<bean:write name="iter_mat" property="value"/>"><bean:write name="iter_mat" property="label"/></option>
                            </logic:iterate>
                        </select>
                    </span>
                    <div id="osDListDetailDataDiv" style="width:700px;height:300px;overflow:auto"><%@include  file="/process/store/mrir/materiallistosd.jsp" %></div>
                </fieldset>
            </td>
        </tr>        
        <tr><td colspan="4" class="lbl10"><bean:message key="message.osd.description"/></td></tr>
        <tr><td colspan="4"><html:textarea rows="3" cols="100" property="description" name="<%=Constants.OSD%>"/></td></tr>
        <tr>
            <td colspan="4">
                <fieldset>
                    <legend class="lbl10b"><bean:message key="message.osd.correctAct"/></legend>
                    <p><table width="100%">
                        <tr>
                            <td class="lbl10"><bean:message key="message.osd.actionBy"/></td>
                            <td>
                                <html:select styleClass="lbl10" property="actionBy" name="<%=Constants.OSD%>">
                                    <html:options styleClass="lbl10" collection="<%=Constants.OSD_EMPLOYEE_LIST%>" property="empId" labelProperty="fullname"/>
                                </html:select>
                            </td>
                            <td class="lbl10"><bean:message key="message.osd.dueDate"/></td>
                            <td><html:text property="dueDate" styleId="dueDate" size="30" onclick="javascript: showCalendar('dueDate')" name="<%=Constants.OSD%>"/></td>
                        </tr>
                        <tr>
                            <td colspan="6" class="lbl10"><bean:message key="message.osd.correctAct"/>
                                <br/><html:textarea rows="3" cols="100" property="correctAct" name="<%=Constants.OSD%>"/></td>
                        </tr>    
                    </table></p>
                </fieldset>
            </td>           
        </tr>
        <tr>
            <td class="lbl10"><bean:message key="message.osd.closed"/></td>
            <td><html:multibox property="closed" name="<%=Constants.OSD%>" value="1"></html:multibox></td>
            <td class="lbl10"><bean:message key="message.osd.closedDate"/></td>
            <td><html:text property="closedDate" styleId="closedDate" size="30" onclick="javascript: showCalendar('closedDate')" name="<%=Constants.OSD%>"/></td>
        </tr>
        <tr><td colspan="4" class="lbl10"><bean:message key="message.osd.note"/></td></tr>
        <tr><td colspan="4"><html:textarea rows="3" cols="100" property="note" name="<%=Constants.OSD%>"/></td></tr>
    </table>
    <logic:greaterThan value="0" property="osdId" name="<%=Constants.OSD%>">
        <img onclick="return printOsD();" src="images/but_print.gif"/>
    </logic:greaterThan>
    <logic:present name="<%=Constants.CAN_EMAIL%>">
        <input value="<bean:message key="message.notify.request.notcode.email"/>" onclick="return emailForNotMaterialCodeOsD();" type="submit">
    </logic:present>
    <html:image onclick="return saveOsD();" src="images/but_save.gif"/><img src="images/blank.gif" width="2" height="17"><html:image property="Back" value="Back" src="images/but_back.gif" onclick="return loadMrirList();"/>
    <html:hidden property="osdId" name="<%=Constants.OSD%>"/>
    <html:hidden property="mrirId" name="<%=Constants.OSD%>"/>
    <html:hidden property="dnId" name="<%=Constants.OSD%>"/>
</form>
<div id="osDDetailHideDiv" style="display:none"></div>
