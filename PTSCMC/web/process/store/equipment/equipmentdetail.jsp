<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.MCUtil"%>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ page import="com.venus.mc.bean.EquipmentBean"%>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<%
            String equId = MCUtil.getParameter("equId", request);
            EquipmentBean form = (EquipmentBean) request.getAttribute(Constants.EQUIPMENT);
%>
<logic:equal value="0" name="<%=Constants.EQUIPMENT%>" property="equId">
    <h3><bean:message key="message.detailequipment.title"/></h3>
    <h3>
        <logic:equal value="2" name="<%=Constants.EQUIPMENT%>" property="kind">
            <bean:message key="message.detailequipment.title"/>
        </logic:equal>
        <logic:equal value="3" name="<%=Constants.EQUIPMENT%>" property="kind">
            <bean:message key="message.detailasset.title"/>
        </logic:equal>
    </h3>
</logic:equal>
<div id="errorMessageDiv"></div>
<form action="addEquipment.do" name="addEquipment">
    <table border="0" width="100%" cellspacing="0" cellpadding="0" >
        <tr><td><div align="center">
                    <table width="100%" border="0" cellspacing="0" cellpadding="0" style="border-width: 0px">
                        <tr>
                            <td>&nbsp;</td>
                        </tr>
                        <tr>
                            <td height="22"><bean:message key="message.equipment.mivId"/></td>
                            <td height="22"><html:text property="mivNumber" style="background-color:#F4F4F4" disabled="true"  size="40" name="<%=Constants.EQUIPMENT%>"/></td>
                            <td height="22"><bean:message key="message.equipment.manageCode"/></td>
                            <td height="22"><html:text property="manageCode" style="background-color:#F4F4F4" disabled="true"  size="40" name="<%=Constants.EQUIPMENT%>"/></td>
                        </tr>                        
                        <tr>
                            <logic:equal value="2" name="<%=Constants.EQUIPMENT%>" property="kind">
                                <td height="22"><bean:message key="message.equipment.equipmentName"/></td>
                            </logic:equal>
                            <logic:equal value="3" name="<%=Constants.EQUIPMENT%>" property="kind">
                                <td height="22"><bean:message key="message.asset.assetName"/></td>
                            </logic:equal>
                            <td height="22" colspan="3"><html:textarea   property="equipmentName" style="background-color:#F4F4F4"   disabled="true"   rows="3" cols="93" name="<%=Constants.EQUIPMENT%>"/></td>
                        </tr>
                        <tr>
                            <td height="22"><bean:message key="message.equipment.requestNumber"/></td>
                            <td height="22"><html:text property="requestNumber" style="background-color:#F4F4F4" disabled="true"  size="40" name="<%=Constants.EQUIPMENT%>"/></td>
                            <td height="22"><bean:message key="message.equipment.contractNumber"/></td>
                            <td height="22"><html:text property="contractNumber" style="background-color:#F4F4F4" disabled="true"  size="30" name="<%=Constants.EQUIPMENT%>"/>
                                <%
                                            if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_EDIT, PermissionUtil.PER_EQUIPMENT, form.getCreatedEmp())) {
                                %>
                                <a class="my3" href="uploadafileForm.do?ftype=20&pid=<%=equId%>&comments=contract&session=<%=session.getAttribute(Constants.SESSION_ID)%>" target="new_dialog" onclick="showUploadDialog(20,1,'1');">
                                    <bean:message key="message.file.add1"/>
                                </a>
                                <%}%>
                                <a class="my3"  target="_blank" href="getFile.do?ftype=20&pid=<%=equId%>&comments=contract&session=<%=session.getAttribute(Constants.SESSION_ID)%>" target="new_dialog" >
                                    <bean:message key="message.file.read1"/>
                                </a></td>
                        </tr>
                        <logic:equal value="1" name="<%=Constants.EQUIPMENT%>" property="kind">
                            <tr>
                                <td height="22"><bean:message key="message.equipment.testNumber"/></td>
                                <td height="22"><html:text property="testNumber" style="background-color:#F4F4F4" disabled="true" size="30" name="<%=Constants.EQUIPMENT%>"/>
                                    <%
                                                if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_EDIT, PermissionUtil.PER_EQUIPMENT, form.getCreatedEmp())) {
                                    %>
                                    <a class="my3" href="uploadafileForm.do?ftype=20&pid=<%=equId%>&comments=testNumber&session=<%=session.getAttribute(Constants.SESSION_ID)%>" target="new_dialog" onclick="showUploadDialog(20,1,'1');">
                                        <bean:message key="message.file.add1"/>
                                    </a>
                                    <%}%>
                                    <a class="my3" target="_blank" href="getFile.do?ftype=20&pid=<%=equId%>&comments=testNumber&session=<%=session.getAttribute(Constants.SESSION_ID)%>" target="new_dialog" >
                                        <bean:message key="message.file.read1"/>
                                    </a>
                                </td>
                                <td height="22"><bean:message key="message.equipment.unit"/></td>
                                <td height="22"><html:text property="unit" style="background-color:#F4F4F4" disabled="true"  size="40" name="<%=Constants.EQUIPMENT%>"/></td>
                            </tr>
                        </logic:equal>
                        <logic:equal value="2" name="<%=Constants.EQUIPMENT%>" property="kind">
                            <tr>
                                <td height="22"><bean:message key="message.asset.decisionNumber"/></td>
                                <td height="22"><html:text property="decisionNumber" size="40" name="<%=Constants.EQUIPMENT%>"/></td>
                                <td height="22"><bean:message key="message.asset.testNumber"/></td>
                                <td height="22"><html:text property="test" size="30" name="<%=Constants.EQUIPMENT%>"/>
                                    <%
                                                if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_EDIT, PermissionUtil.PER_EQUIPMENT, form.getCreatedEmp())) {
                                    %>
                                    <a class="my3" href="uploadafileForm.do?ftype=20&pid=<%=equId%>&comments=testNumber&session=<%=session.getAttribute(Constants.SESSION_ID)%>" target="new_dialog" onclick="showUploadDialog(20,1,'1');">
                                        <bean:message key="message.file.add1"/>
                                    </a>
                                    <%}%>
                                    <a class="my3" target="_blank" href="getFile.do?ftype=20&pid=<%=equId%>&comments=testNumber&session=<%=session.getAttribute(Constants.SESSION_ID)%>" target="new_dialog" >
                                        <bean:message key="message.file.read1"/>
                                    </a>
                                </td>
                            </tr>
                            <tr>
                                <td height="22"><bean:message key="message.equipment.unit"/></td>
                                <td height="22"><html:text property="unit" style="background-color:#F4F4F4" disabled="true"  size="40" name="<%=Constants.EQUIPMENT%>"/></td>
                            </tr>
                        </logic:equal>
                        <tr>
                            <td height="22"><bean:message key="message.equipment.usedCode"/></td>
                            <td height="22"><html:text property="usedCode" size="40" name="<%=Constants.EQUIPMENT%>"/></td>
                            <td height="22"><bean:message key="message.equipment.colorCode"/></td>
                            <td height="22"><html:text property="colorCode" style="background-color:#F4F4F4"  disabled="true" size="40" name="<%=Constants.EQUIPMENT%>"/></td>
                        </tr>
                        <tr>
                            <td height="22"><bean:message key="message.equipment.specCerts"/></td>
                            <td height="22" colspan="3"><html:textarea property="specCerts"  cols="80" rows="10" name="<%=Constants.EQUIPMENT%>"/>
                                <%
                                            if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_EDIT, PermissionUtil.PER_EQUIPMENT, form.getCreatedEmp())) {
                                %>
                                <a class="my3" href="uploadafileForm.do?ftype=20&pid=<%=equId%>&comments=specCerts&session=<%=session.getAttribute(Constants.SESSION_ID)%>" target="new_dialog" onclick="showUploadDialog(20,1,'1');">
                                    <bean:message key="message.file.add1"/>
                                </a>
                                <%}%>
                                <a class="my3" target="_blank" href="getFile.do?ftype=20&pid=<%=equId%>&comments=specCerts&session=<%=session.getAttribute(Constants.SESSION_ID)%>" target="new_dialog" >
                                    <bean:message key="message.file.read1"/>
                                </a>
                            </td>
                        </tr>
                        <tr>
                            <td height="22"><bean:message key="message.equipment.fuelLevel"/></td>
                            <td height="22" colspan="3"><html:textarea property="fuelLevel"  cols="90" rows="10" name="<%=Constants.EQUIPMENT%>"/></td>
                        </tr>
                        <tr>
                            <td height="22"><bean:message key="message.equipment.status"/></td>
                            <td height="22" colspan="3">
                                <html:select  property="status" name="<%=Constants.EQUIPMENT%>">
                                    <html:options collection="<%=Constants.STATUS_LIST%>" property="value" labelProperty="label"/> </html:select>
                                </td>
                            </tr>
                            <tr>
                                <td><bean:message key="message.equipment.appearedDate"/></td>
                            <td><html:text property="appearedDate" readonly="true" size="10" styleId="appearedDate" onclick="javascript: showCalendar('appearedDate')" name="<%=Constants.EQUIPMENT%>" /></td>
                            <td><bean:message key="message.equipment.usedDate"/></td>
                            <td><html:text property="usedDate" readonly="true" size="10" styleId="usedDate" onclick="javascript: showCalendar('usedDate')" name="<%=Constants.EQUIPMENT%>" /></td>
                        </tr>
                        <tr>
                            <td height="22"><bean:message key="message.equipment.comment"/></td>
                            <td height="22" colspan="3"><html:textarea property="comment"  cols="90" rows="10" name="<%=Constants.EQUIPMENT%>"/></td>
                        </tr>
                        <tr>
                            <td colspan="6">
                                <fieldset>
                                    <legend><bean:message key="message.equipment.repairplan"/></legend>
                                    <input type="button" onclick="return addRepairPlan();" value="<bean:message key="message.repairplan"/>"/>
                                    <logic:greaterThan value="0" property="equId" name="<%=Constants.EQUIPMENT%>">
                                        <img onclick="return printEquipment();" src="images/ico_print.gif"/>
                                    </logic:greaterThan>                                    
                                    <div id='repairplanList'></div>                                    
                                </fieldset>
                            </td>
                            <logic:greaterThan name="<%=Constants.EQUIPMENT%>" value="0" property="equId">
                            <tr><td colspan="6">&nbsp;</td></tr>
                            <tr>
                                <td colspan="6"><div id='divAttachFileSystem' ><img src="img/indicator.gif"/></div></td>
                            </tr>
                            </logic:greaterThan>
                        </tr>
                        <tr>
                            <td colspan="6">
                                <fieldset>
                                    <legend><bean:message key="message.equipment.verifiedplan"/></legend>
                                    <input type="button" onclick="return addVerifiedPlan();" value="<bean:message key="message.verifiedplan"/>"/>
                                    <div id='verifiedplanList'></div>
                                </fieldset>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="6">
                                <fieldset>
                                    <legend><bean:message key="message.equipment.copy"/></legend>
                                    <input type="textbox" name="copyNumber" maxlength="255" size="112" />
                                    <input type="button" onclick="return copyRepairPlan();" value="<bean:message key="message.copy"/>"/>
                                </fieldset>
                            </td>
                        </tr>
                    </table>
                    <p style="margin-top: 0; margin-bottom: 0" align="left">
                        <%
                                    if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_EDIT, PermissionUtil.PER_EQUIPMENT, form.getCreatedEmp())) {
                        %>
                        <html:image onclick="return saveEquipment();" src="images/but_save.gif" style="cursor: hand;"/><img border="0" src="images/blank.gif" width="2" height="17">
                        <%}%>
                        <logic:equal value="2" name="<%=Constants.EQUIPMENT%>" property="kind">
                            <html:image property="Back" value="Back" src="images/but_back.gif" onclick="return loadEquipmentList();"/>
                        </logic:equal>
                        <logic:equal value="3" name="<%=Constants.EQUIPMENT%>" property="kind">
                            <html:image property="Back" value="Back" src="images/but_back.gif" onclick="return loadEquipmentList2();"/>
                        </logic:equal>
                </div></td></tr></table>
                <html:hidden property="equId" name="<%=Constants.EQUIPMENT%>" />
                <html:hidden property="mivId" name="<%=Constants.EQUIPMENT%>" />
                <html:hidden property="matId" name="<%=Constants.EQUIPMENT%>" />
                <html:hidden property="conId" name="<%=Constants.EQUIPMENT%>" />
                <html:hidden property="reqDetailId" name="<%=Constants.EQUIPMENT%>" />
                <html:hidden property="kind" name="<%=Constants.EQUIPMENT%>" />
    <input type="hidden" name="timeTrue"/>
</form>