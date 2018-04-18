<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib prefix="display" uri="/tags/displaytag" %>
<%@ page import="com.venus.mc.util.MCUtil"%>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.venus.mc.process.store.emrir.EosDFormBean"%>

<div id="eosDFormTitle"><h3><bean:message key="message.eosdadd.title"/></h3></div>
<div id="eosDErrorMessageDiv"></div>
<form name="eosDForm">
    <table width="100%">
        <tr>
            <td class="lbl10"><bean:message key="message.eosd.eosdNumber"/></td>
            <td colspan="2"><html:text styleClass="lbl10" size="30" property="eosdNumber" name="<%=Constants.EOSD%>"/></td>
            <td class="lbl10"><bean:message key="message.eosd.createdDate"/></td>
            <td colspan="2"><html:text styleClass="lbl10" property="createdDate" styleId="createdDateEosD" size="30" name="<%=Constants.EOSD%>" disabled="true" /></td>
        </tr>  
        <tr>
            <td class="lbl10"><bean:message key="message.eosd.econNumber"/></td>
            <td colspan="2"><html:text styleClass="lbl10" size="30" property="econNumber" name="<%=Constants.EOSD%>" disabled="true"/></td>
        </tr>
        <tr>
            <td class="lbl10"><bean:message key="message.eosd.createdEmp"/></td>
            <td colspan="2">
                <html:select styleClass="lbl10" property="createdEmpId" name="<%=Constants.EOSD%>" disabled = "true" >
                    <html:options styleClass="lbl10" collection="<%=Constants.EOSD_EMPLOYEE_LIST%>" property="empId" labelProperty="fullname"/>
                </html:select>
            </td>
        </tr>  
        <tr>
            <td colspan="6">
                <fieldset>
                    <legend class="lbl10b" ><bean:message key="message.eosd.nonConform"/></legend>
                    <p>
                        <table width="100%">
                            <tr>
                                <td class="lbl10"><html:multibox property="nonConform" name="<%=Constants.EOSD%>" value="1"></html:multibox> <bean:message key="message.eosd.nonConform1"/></td>
                                <td class="lbl10"><html:multibox property="nonConform" name="<%=Constants.EOSD%>" value="2"></html:multibox> <bean:message key="message.eosd.nonConform2"/></td>
                                <td class="lbl10"><html:multibox property="nonConform" name="<%=Constants.EOSD%>" value="4"></html:multibox> <bean:message key="message.eosd.nonConform3"/></td>
                                <td class="lbl10"><html:multibox property="nonConform" name="<%=Constants.EOSD%>" value="8"></html:multibox> <bean:message key="message.eosd.nonConform4"/></td>
                            </tr>
                    </table></p>
                </fieldset>
            </td>
        </tr>                              
        <tr>
            <td colspan="6">
                <fieldset>
                    <legend class="lbl10b"><bean:message key="message.eosd.materials"/></legend>
                        <html:image src="images/ico_xoa.gif" onclick="return eosDDelMaterial();"/>
                        <html:image src="images/ico_them.gif" onclick="return eosDAddMaterial();"/>
                        <select name="cbxMaterialOfDn">
                            <logic:iterate id="iter_mat" name="<%=Constants.EOSD_MATERIAL_LIST%>">
                                <option value="<bean:write name="iter_mat" property="value"/>"><bean:write name="iter_mat" property="label"/></option>
                            </logic:iterate>
                        </select>                                            
                    <div id="eosDListDetailDataDiv" style="width:700px;height:300px;overflow:auto"><%@include  file="/process/store/emrir/materiallistosd.jsp" %></div>
                </fieldset>
            </td>
        </tr>        
        <tr><td colspan="6" class="lbl10"><bean:message key="message.eosd.description"/></td></tr>
        <tr><td colspan="6"><html:textarea rows="3" cols="100" property="description" name="<%=Constants.EOSD%>"/></td></tr>
        <tr>
            <td colspan="6">
                <fieldset>
                        <legend class="lbl10b"><bean:message key="message.eosd.correctAct"/></legend>
                        <p><table width="100%">
                            <tr>
                                <td class="lbl10"><bean:message key="message.eosd.actionBy"/></td>
                                <td>
                                    <html:select styleClass="lbl10" property="actionBy" name="<%=Constants.EOSD%>">
                                        <html:options styleClass="lbl10" collection="<%=Constants.EOSD_EMPLOYEE_LIST%>" property="empId" labelProperty="fullname"/>
                                    </html:select>
                                </td>
                                <td class="lbl10"><bean:message key="message.eosd.dueDate"/></td>
                                <td><html:text property="dueDate" styleId="dueDate" size="30" onclick="javascript: showCalendar('dueDate')" name="<%=Constants.EOSD%>"/></td>
                            </tr>
                            <tr>
                                <td colspan="6" class="lbl10"><bean:message key="message.eosd.correctAct"/>
                                <br/><html:textarea rows="3" cols="100" property="correctAct" name="<%=Constants.EOSD%>"/></td>
                            </tr>    
                        </table></p>
                </fieldset>
            </td>           
        </tr>
        <tr>
            <td colspan="2" class="lbl10"><bean:message key="message.eosd.closed"/></td>
            <td><html:multibox property="closed" name="<%=Constants.EOSD%>" value="1"></html:multibox></td>
            <td class="lbl10"><bean:message key="message.eosd.closedDate"/></td>
            <td colspan="2"><html:text property="closedDate" styleId="closedDate" size="30" onclick="javascript: showCalendar('closedDate')" name="<%=Constants.EOSD%>"/></td>
        </tr>
        <tr><td colspan="6" class="lbl10"><bean:message key="message.eosd.note"/></td></tr>
        <tr><td colspan="6"><html:textarea rows="3" cols="100" property="note" name="<%=Constants.EOSD%>"/></td></tr>
    </table>    
    <logic:greaterThan value="0" property="eosdId" name="<%=Constants.EOSD%>">
        <img onclick="return printEosD();" src="images/but_print.gif"/>
    </logic:greaterThan>
    <html:image onclick="return saveEosD();" src="images/but_save.gif"/><img src="images/blank.gif" width="2" height="17">
    
    <html:image property="Back" value="Back" src="images/but_back.gif" onclick="return loadEmrirList();"/>
    <html:hidden property="eosdId" name="<%=Constants.EOSD%>"/>
    <html:hidden property="emrirId" name="<%=Constants.EOSD%>"/>
    <html:hidden property="ednId" name="<%=Constants.EOSD%>"/>
</form>
<div id="eosDDetailHideDiv" style="display:none"></div>