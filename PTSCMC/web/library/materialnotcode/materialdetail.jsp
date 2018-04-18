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
<%
            String matId = MCUtil.getParameter("matId", request);
%>

<h3><bean:message key="message.detailmaterial.title"/></h3>
<div id="errorMaterialMessageDiv"></div>
<form action="addMaterialNotCode.do" name="addMaterial">
    <table border="0" width="100%" cellspacing="0" cellpadding="0" >
        <tr><td><div align="center">
                    <table width="100%" border="0" cellspacing="0" cellpadding="0" style="border-width: 0px">
                        <tr>
                            <td>&nbsp;</td>
                        </tr>
                        <tr>
                            <td class="lbl10"><bean:message key="message.request.number"/></td>
                            <td colspan="4"><html:text styleClass="lbl10" size="30" property="requestNumber" name="<%=Constants.MATERIAL%>" disabled="true"/></td>
                        </tr>  
                        <tr>
                            <td class="lbl10"><bean:message key="message.request.createdEmp"/></td>
                            <td colspan="4"><html:text styleClass="lbl10" size="30" property="empName" name="<%=Constants.MATERIAL%>" disabled="true"/>
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <bean:message key="message.request.organization"/>
                            <html:text styleClass="lbl10" size="30" property="orgName" name="<%=Constants.MATERIAL%>" disabled="true"/></td>
                        </tr>
                        <tr>
                            <td height="22"><bean:message key="message.material.code"/></td>
                            <td height="22" colspan="5"><html:text property="code" size="80" name="<%=Constants.MATERIAL%>"/></td>
                        </tr>
                        <tr>
                            <td height="22"><bean:message key="message.material.spe1Id"/></td>
                            <td height="22" colspan="5">

                                <span name="spe1Span" id="spe1Span"></span>
                                <%
                                    if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_EDIT, PermissionUtil.PER_PURCHASING_REQUEST_MATERIALNOTCODE)) {
                                %>
                                <html:image src="images/add.png" title="Th&#234;m m&#7899;i" onclick="return spePopupDetail(1,document.forms['addMaterial'].spe1Id.value,0);"/>
                                <%}%>
                            </td>
                        </tr>
                        <tr>
                            <td height="22"><bean:message key="message.material.spe2Id"/></td>
                            <td height="22" colspan="5">

                                <span name="spe2Span" id="spe2Span"></span>
                                <%
                                    if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_EDIT, PermissionUtil.PER_PURCHASING_REQUEST_MATERIALNOTCODE)) {
                                %>
                                <html:image src="images/add.png" title="Th&#234;m m&#7899;i" onclick="return spePopupDetail(2,document.forms['addMaterial'].spe1Id.value,document.forms['addMaterial'].spe2Id.value);"/>
                                <%}%>
                            </td>
                        </tr>
                        <tr>
                            <td height="22"><bean:message key="message.material.spe3Id"/></td>
                            <td height="22" colspan="5">

                                <span name="spe3Span" id="spe3Span"></span>
                                <%
                                    if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_EDIT, PermissionUtil.PER_PURCHASING_REQUEST_MATERIALNOTCODE)) {
                                %>
                                <html:image src="images/add.png" title="Th&#234;m m&#7899;i" onclick="return spePopupDetail(3,document.forms['addMaterial'].spe2Id.value,document.forms['addMaterial'].spe3Id.value);"/>
                                <%}%>
                            </td>
                        </tr>
                        <tr>
                            <td height="22"><bean:message key="message.material.spe4Id"/></td>
                            <td height="22" colspan="5">

                                <span name="spe4Span" id="spe4Span"></span>
                                <%
                                    if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_EDIT, PermissionUtil.PER_PURCHASING_REQUEST_MATERIALNOTCODE)) {
                                %>
                                <html:image src="images/add.png" title="Th&#234;m m&#7899;i" onclick="return spePopupDetail(4,document.forms['addMaterial'].spe3Id.value,document.forms['addMaterial'].spe4Id.value);"/>
                                <%}%>
                            </td>
                        </tr>
                        <tr>
                            <td height="22"><bean:message key="message.material.spe5Id"/></td>
                            <td height="22" colspan="5">

                                <span name="spe5Span" id="spe5Span"></span>
                                <%
                                    if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_EDIT, PermissionUtil.PER_PURCHASING_REQUEST_MATERIALNOTCODE)) {
                                %>
                                <html:image src="images/add.png" title="Th&#234;m m&#7899;i" onclick="return spePopupDetail(5,document.forms['addMaterial'].spe4Id.value,document.forms['addMaterial'].spe5Id.value);"/>
                                <%}%>
                            </td>
                        </tr>

                        <tr>
                            <td height="22"><bean:message key="message.material.nameVn"/></td>
                            <td colspan="3"><html:textarea rows="5" cols="80"  property="nameVn" name="<%=Constants.MATERIAL%>" /></td>
                        </tr>
                        <tr>
                            <td height="22"><bean:message key="message.material.nameEn"/></td>
                            <td colspan="3"><html:textarea rows="5" cols="80"  property="nameEn" name="<%=Constants.MATERIAL%>" /></td>
                        </tr>
                        <tr>
                            <td height="22"><bean:message key="message.material.note"/></td>
                            <td colspan="3"><html:textarea rows="3" cols="80"  property="note" name="<%=Constants.MATERIAL%>" /></td>
                        </tr>
                        <tr>
                            <td height="22"><bean:message key="message.material.qc"/></td>
                            <td colspan="3"><html:textarea rows="3" cols="80"  property="qc" name="<%=Constants.MATERIAL%>" /></td>
                        </tr>
                        <tr>
                            <td height="22"><bean:message key="message.material.deliveryTime"/></td>
                            <td colspan="3"><html:textarea rows="3" cols="80"  property="deliveryTime" name="<%=Constants.MATERIAL%>" /></td>
                        </tr>
                        <tr>
                            <td height="22"><bean:message key="message.material.uniId"/></td>
                            <td height="22" colspan="5">

                                <html:select  property="uniId" onchange="document.forms['addMaterial'].uniIdEn.options.selectedIndex=this.options.selectedIndex" name="<%=Constants.MATERIAL%>">
                                    <html:options collection="<%=Constants.UNIT_LIST%>" property="value" labelProperty="label"/> </html:select>

                                </td>
                            </tr>
                            <tr>
                                <td height="22"><bean:message key="message.material.uniIdEn"/></td>
                            <td height="22" colspan="5">

                                <html:select  property="uniIdEn" onchange="document.forms['addMaterial'].uniId.options.selectedIndex=this.options.selectedIndex" name="<%=Constants.MATERIAL%>">
                                    <html:options collection="<%=Constants.UNIT_LISTEN%>" property="value" labelProperty="label"/> </html:select>

                                </td>
                            </tr>
                            <tr>
                                <td height="22"><bean:message key="message.material.oriId"/></td>
                            <td height="22" colspan="5">

                                <html:select  property="oriId" onchange="document.forms['addMaterial'].oriIdEn.options.selectedIndex=this.options.selectedIndex" name="<%=Constants.MATERIAL%>">
                                    <html:options collection="<%=Constants.ORIGIN_LIST%>" property="value" labelProperty="label"/> </html:select>

                                </td>
                            </tr>
                            <tr>
                                <td height="22"><bean:message key="message.material.oriIdEn"/></td>
                            <td height="22" colspan="5">

                                <html:select  property="oriIdEn" onchange="document.forms['addMaterial'].oriId.options.selectedIndex=this.options.selectedIndex" name="<%=Constants.MATERIAL%>">
                                    <html:options collection="<%=Constants.ORIGIN_LISTEN%>" property="value" labelProperty="label"/> </html:select>

                                </td>
                            </tr> 
                        </table>
                        <p style="margin-top: 0; margin-bottom: 0" align="left">
                        <%
                                    if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_EDIT, PermissionUtil.PER_PURCHASING_REQUEST_MATERIALNOTCODE)) {
                        %>
                        <html:image onclick="return saveMaterialNotCode();" src="images/but_save.gif" style="cursor: hand;"/><img border="0" src="images/blank.gif" width="2" height="17">
                        <%}%>
                        <html:image property="Back" value="Back" src="images/but_back.gif" onclick="return loadMaterialListNotCode();"/>
                        <input type="button" onclick="return emailForSameMaterial();" value="<bean:message key="message.request.material.notcode.mail"/>"/>
                </div></td></tr></table>
                <html:hidden property="matId" name="<%=Constants.MATERIAL%>" />
                <html:hidden property="spe" name="<%=Constants.MATERIAL%>" />
                <html:hidden property="groId" name="<%=Constants.MATERIAL%>" />
                <html:hidden property="reqId" name="<%=Constants.REQUEST%>" />
</form>