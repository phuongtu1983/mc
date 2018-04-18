<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib prefix="display" uri="/tags/displaytag" %>
<%@ page import="com.venus.mc.util.MCUtil"%>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.venus.mc.bean.OrganizationBean"%>
<%@ page import="com.venus.mc.bean.ContractFollowBean"%>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<%
            String folId = MCUtil.getParameter("folId", request);
%>

<h3><bean:message key="message.detailcontractFollow.title"/></h3>
<div id="errorMessageDiv"></div>
<form action="addContractFollow.do" name="contractFollowForm">
    <table border="0" style="width:100%" cellspacing="0" cellpadding="0" >
        <tr><td><div align="center">
                    <table width="100%" border="0" cellspacing="5" cellpadding="0" style="border-width: 0px">
                        <tr>
                            <td height="22"><p align="right"><bean:message key="message.contractFollow.folNumber"/>&nbsp;</p></td>
                            <td colspan="2"><html:text size="14" property="folNumber" name="<%=Constants.CONTRACT_FOLLOW%>"/></td>
                            <td><p align="right"><bean:message key="message.contractFollow.createdDate"/>&nbsp;</p></td>
                            <td colspan="2"><html:text property="createdDate" styleId="createdDateContractFollow" onclick="javascript: showCalendar('createdDateContractFollow')" size="14" name="<%=Constants.CONTRACT_FOLLOW%>"/></td>
                        </tr>
                        <tr>
                            <td height="22"><p align="right"><bean:message key="message.contractFollow.conId"/>&nbsp;</p></td>
                            <td height="22" colspan="2">
                                <html:select property="conId" onchange="return contractUseChanged(this);" name="<%=Constants.CONTRACT_FOLLOW%>">
                                <html:options collection="<%=Constants.CONTRACT_LIST%>" property="value" labelProperty="label"/> </html:select>
                            </td>
                            <td><p align="right"><bean:message key="message.contractFollow.createdTime"/>&nbsp;</p></td>
                            <td colspan="2"><html:text size="14" readonly="true" property="createdTime" styleId="createdTime" name="<%=Constants.CONTRACT_FOLLOW%>"/></td>
                        </tr>
                        <tr>
                            <td height="22"><p align="right"><bean:message key="message.contractFollow.startTime"/>&nbsp;</p></td>
                            <td colspan="2"><html:text size="14" readonly="true" property="startTime" styleId="startTime" name="<%=Constants.CONTRACT_FOLLOW%>" /></td>
                            <td><p align="right"><bean:message key="message.contractFollow.endTime"/>&nbsp;</p></td>
                            <td colspan="2"><html:text size="14" readonly="true" property="endTime" styleId="endTime" name="<%=Constants.CONTRACT_FOLLOW%>"/></td>
                        </tr>
                        <tr>
                            <td height="22"><p align="right"><bean:message key="message.contractFollow.proId"/>&nbsp;</p></td>
                            <td height="22" colspan="4">
                                <html:select property="proId" name="<%=Constants.CONTRACT_FOLLOW%>">
                                <html:options collection="<%=Constants.PROJECT_LIST%>" property="value" labelProperty="label"/> </html:select>
                            </td>
                        </tr>
                        <tr>
                            <td height="22"><p align="right"><bean:message key="message.contractFollow.serviceType"/>&nbsp;</p></td>
                            <td height="22" colspan="2">
                                <html:select onchange="return serviceTypes();" property="serviceType" name="<%=Constants.CONTRACT_FOLLOW%>">
                                <html:options collection="<%=Constants.SERVICE_LIST%>" property="value" labelProperty="label"/> </html:select>
                            </td>
                            <td height="22"><p align="right"><bean:message key="message.contractFollow.orgId"/>&nbsp;</p></td>
                            <td height="32" colspan="2">
                                <html:select property="orgId" name="<%=Constants.CONTRACT_FOLLOW%>">
                                <html:options collection="<%=Constants.ORG_LIST%>" property="value" labelProperty="label"/> </html:select>
                            </td>
                        </tr>
                        <tr>
                            <td height="22"><p style="margin-top:60; margin-bottom:20; color:blue;"><bean:message key="message.contractFollow.Nd"/></td>
                        </tr>
                        <logic:equal value="1" property="serviceType" name="<%=Constants.CONTRACT_FOLLOW%>">
                            <tr>
                                <td colspan="6">
                                    <fieldset>
                                        <legend style="color:teal"><bean:message key="message.contractFollow.DV"/></legend>
                                        
                                        <table style="width:100%"  class="its" width="100%" border="0">
                                            <tr>
                                                <td colspan="6">
                                                    <table  style="width:100%"  border="0">
                                                        <thead>
                                                            <tr>
                                                                <th colspan="2" height="22"><div align="center"><bean:message key="message.contractFollow.STT"/></div></th>
                                                                <th><div align="center"><bean:message key="message.contractFollow.TCDG"/></div></th>
                                                                <th><div align="center"><bean:message key="message.contractFollow.Tot"/></div></th>
                                                                <th><div align="center"><bean:message key="message.contractFollow.Kha"/></div></th>
                                                                <th><div align="center"><bean:message key="message.contractFollow.TB"/></div></th>
                                                                <th><div align="center"><bean:message key="message.contractFollow.Kem"/></div></th>
                                                            </tr>
                                                        </thead>
                                                        <tr>
                                                            <td height="22"><div align="center">1</div></td>
                                                            <td colspan="2">&nbsp;<bean:message key="message.contractFollow.serviceAbility"/></td>
                                                            <td><div align="center"><html:radio property="serviceAbility" name="<%=Constants.CONTRACT_FOLLOW%>" value="1"></div></html:radio></td>
                                                            <td><div align="center"><html:radio property="serviceAbility" name="<%=Constants.CONTRACT_FOLLOW%>" value="2"></div></html:radio></td>
                                                            <td><div align="center"><html:radio property="serviceAbility" name="<%=Constants.CONTRACT_FOLLOW%>" value="3"></div></html:radio></td>
                                                            <td><div align="center"><html:radio property="serviceAbility" name="<%=Constants.CONTRACT_FOLLOW%>" value="4"></div></html:radio></td>
                                                        </tr>
                                                        <tr>
                                                            <td height="22"><div align="center">2</div></td>
                                                            <td colspan="2">&nbsp;<bean:message key="message.contractFollow.serviceLevel"/></td>
                                                            <td><div align="center"><html:radio property="serviceLevel" name="<%=Constants.CONTRACT_FOLLOW%>" value="1"></div></html:radio></td>
                                                            <td><div align="center"><html:radio property="serviceLevel" name="<%=Constants.CONTRACT_FOLLOW%>" value="2"></div></html:radio></td>
                                                            <td><div align="center"><html:radio property="serviceLevel" name="<%=Constants.CONTRACT_FOLLOW%>" value="3"></div></html:radio></td>
                                                            <td><div align="center"><html:radio property="serviceLevel" name="<%=Constants.CONTRACT_FOLLOW%>" value="4"></div></html:radio></td>
                                                        </tr>
                                                        <tr>
                                                            <td height="22"><div align="center">3</div></td>
                                                            <td colspan="2">&nbsp;<bean:message key="message.contractFollow.serviceEquipment"/></td>
                                                            <td><div align="center"><html:radio property="serviceEquipment" name="<%=Constants.CONTRACT_FOLLOW%>" value="1"></div></html:radio></td>
                                                            <td><div align="center"><html:radio property="serviceEquipment" name="<%=Constants.CONTRACT_FOLLOW%>" value="2"></div></html:radio></td>
                                                            <td><div align="center"><html:radio property="serviceEquipment" name="<%=Constants.CONTRACT_FOLLOW%>" value="3"></div></html:radio></td>
                                                            <td><div align="center"><html:radio property="serviceEquipment" name="<%=Constants.CONTRACT_FOLLOW%>" value="4"></div></html:radio></td>
                                                        </tr>
                                                        <tr>
                                                            <td height="22"><div align="center">4</div></td>
                                                            <td colspan="2">&nbsp;<bean:message key="message.contractFollow.serviceProgress"/></td>
                                                            <td><div align="center"><html:radio property="serviceProgress" name="<%=Constants.CONTRACT_FOLLOW%>" value="1"></div></html:radio></td>
                                                            <td><div align="center"><html:radio property="serviceProgress" name="<%=Constants.CONTRACT_FOLLOW%>" value="2"></div></html:radio></td>
                                                            <td><div align="center"><html:radio property="serviceProgress" name="<%=Constants.CONTRACT_FOLLOW%>" value="3"></div></html:radio></td>
                                                            <td><div align="center"><html:radio property="serviceProgress" name="<%=Constants.CONTRACT_FOLLOW%>" value="4"></div></html:radio></td>
                                                        </tr>
                                                        <tr>
                                                            <td height="22"><div align="center">5</div></td>
                                                            <td colspan="2">&nbsp;<bean:message key="message.contractFollow.serviceSafety"/></td>
                                                            <td><div align="center"><html:radio property="serviceSafety" name="<%=Constants.CONTRACT_FOLLOW%>" value="1"></div></html:radio></td>
                                                            <td><div align="center"><html:radio property="serviceSafety" name="<%=Constants.CONTRACT_FOLLOW%>" value="2"></div></html:radio></td>
                                                            <td><div align="center"><html:radio property="serviceSafety" name="<%=Constants.CONTRACT_FOLLOW%>" value="3"></div></html:radio></td>
                                                            <td><div align="center"><html:radio property="serviceSafety" name="<%=Constants.CONTRACT_FOLLOW%>" value="4"></div></html:radio></td>
                                                        </tr>
                                                        
                                                    </table>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td height="22"><p align="right"><bean:message key="message.contractFollow.serviceOther"/>&nbsp;</p></td>
                                                <td height="22">
                                                <html:textarea rows="4" cols="80" property="serviceOther" name="<%=Constants.CONTRACT_FOLLOW%>"/></td>
                                            </tr>
                                            <tr>
                                                <th height="20" colspan="5"><p align="left"><bean:message key="message.contractFollow.serviceCooperate"/>&nbsp;</p></th>
                                            </tr>
                                            <tr>
                                                <td></td>
                                                <td><div align="center"><bean:message key="message.contractFollow.yes"/><html:radio property="serviceCooperate" name="<%=Constants.CONTRACT_FOLLOW%>" value="1"></html:radio>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                        <bean:message key="message.contractFollow.No"/><html:radio property="serviceCooperate" name="<%=Constants.CONTRACT_FOLLOW%>" value="2"></html:radio>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                <bean:message key="message.contractFollow.Continuos"/><html:radio property="serviceCooperate" name="<%=Constants.CONTRACT_FOLLOW%>" value="3"></div></html:radio></td>
                                            </tr>
                                        </table>
                                    </fieldset>
                                </td>
                            </tr>
                        </logic:equal>
                        <logic:equal value="2" property="serviceType" name="<%=Constants.CONTRACT_FOLLOW%>">
                            <tr>
                                <td colspan="6">
                                    <fieldset>
                                        <legend style="color:teal"><bean:message key="message.contractFollow.HH"/></legend>
                                        
                                        <table style="width:100%"  class="its" width="100%" border="0">
                                            <tr>
                                                <td colspan="6">
                                                    <table style="width:100%"  border="0">
                                                        <thead>
                                                            <tr>
                                                                <th colspan="2" height="22"><div align="center"><bean:message key="message.contractFollow.STT"/></div></th>
                                                                <th><div align="center"><bean:message key="message.contractFollow.TCDG"/></div></th>
                                                                <th><div align="center"><bean:message key="message.contractFollow.Tot"/></div></th>
                                                                <th><div align="center"><bean:message key="message.contractFollow.Kha"/></div></th>
                                                                <th><div align="center"><bean:message key="message.contractFollow.TB"/></div></th>
                                                                <th><div align="center"><bean:message key="message.contractFollow.Kem"/></div></th>
                                                            </tr>
                                                        </thead>
                                                        <tr>
                                                            <td height="22"><div align="center">1</div></td>
                                                            <td colspan="2">&nbsp;<bean:message key="message.contractFollow.goodAbility"/></td>
                                                            <td><div align="center"><html:radio property="goodAbility" name="<%=Constants.CONTRACT_FOLLOW%>" value="1"></div></html:radio></td>
                                                            <td><div align="center"><html:radio property="goodAbility" name="<%=Constants.CONTRACT_FOLLOW%>" value="2"></div></html:radio></td>
                                                            <td><div align="center"><html:radio property="goodAbility" name="<%=Constants.CONTRACT_FOLLOW%>" value="3"></div></html:radio></td>
                                                            <td><div align="center"><html:radio property="goodAbility" name="<%=Constants.CONTRACT_FOLLOW%>" value="4"></div></html:radio></td>
                                                        </tr>
                                                        <tr>
                                                            <td height="22"><div align="center">2</div></td>
                                                            <td colspan="2">&nbsp;<bean:message key="message.contractFollow.goodProgress"/></td>
                                                            <td><div align="center"><html:radio property="goodProgress" name="<%=Constants.CONTRACT_FOLLOW%>" value="1"></div></html:radio></td>
                                                            <td><div align="center"><html:radio property="goodProgress" name="<%=Constants.CONTRACT_FOLLOW%>" value="2"></div></html:radio></td>
                                                            <td><div align="center"><html:radio property="goodProgress" name="<%=Constants.CONTRACT_FOLLOW%>" value="3"></div></html:radio></td>
                                                            <td><div align="center"><html:radio property="goodProgress" name="<%=Constants.CONTRACT_FOLLOW%>" value="4"></div></html:radio></td>
                                                        </tr>
                                                        <tr>
                                                            <td height="22"><div align="center">3</div></td>
                                                            <td colspan="2">&nbsp;<bean:message key="message.contractFollow.goodCertificate"/></td>
                                                            <td><div align="center"><html:radio property="goodCertificate" name="<%=Constants.CONTRACT_FOLLOW%>" value="1"></div></html:radio></td>
                                                            <td><div align="center"><html:radio property="goodCertificate" name="<%=Constants.CONTRACT_FOLLOW%>" value="2"></div></html:radio></td>
                                                            <td><div align="center"><html:radio property="goodCertificate" name="<%=Constants.CONTRACT_FOLLOW%>" value="3"></div></html:radio></td>
                                                            <td><div align="center"><html:radio property="goodCertificate" name="<%=Constants.CONTRACT_FOLLOW%>" value="4"></div></html:radio></td>
                                                        </tr>
                                                        <tr>
                                                            <td height="22"><div align="center">4</div></td>
                                                            <td colspan="2">&nbsp;<bean:message key="message.contractFollow.goodQuality"/></td>
                                                            <td><div align="center"><html:radio property="goodQuality" name="<%=Constants.CONTRACT_FOLLOW%>" value="1"></div></html:radio></td>
                                                            <td><div align="center"><html:radio property="goodQuality" name="<%=Constants.CONTRACT_FOLLOW%>" value="2"></div></html:radio></td>
                                                            <td><div align="center"><html:radio property="goodQuality" name="<%=Constants.CONTRACT_FOLLOW%>" value="3"></div></html:radio></td>
                                                            <td><div align="center"><html:radio property="goodQuality" name="<%=Constants.CONTRACT_FOLLOW%>" value="4"></div></html:radio></td>
                                                        </tr>
                                                    </table>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td width="100" height="22"><p align="right"><bean:message key="message.contractFollow.goodOther"/>&nbsp;</p></td>
                                                <td height="22">
                                                <html:textarea rows="4" cols="80" property="serviceOther" name="<%=Constants.CONTRACT_FOLLOW%>"/></td>
                                            </tr>
                                            <tr>
                                                <th height="20" colspan="5"><p align="left"><bean:message key="message.contractFollow.goodCooperate"/>&nbsp;</p></th>
                                            </tr>
                                            <tr>
                                                <td></td>
                                                <td><div align="center">
                                                        <bean:message key="message.contractFollow.yes"/><html:radio property="goodCooperate" name="<%=Constants.CONTRACT_FOLLOW%>" value="1"></html:radio>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                        <bean:message key="message.contractFollow.No"/><html:radio property="goodCooperate" name="<%=Constants.CONTRACT_FOLLOW%>" value="2"></html:radio>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                <bean:message key="message.contractFollow.Continuos"/><html:radio property="goodCooperate" name="<%=Constants.CONTRACT_FOLLOW%>" value="3"></div></html:radio></td>
                                            </tr>
                                        </table>
                                    </fieldset>
                                </td>
                            </tr>
                        </logic:equal>
                        <tr>
                            <td height="22"><p align="right"><bean:message key="message.contractFollow.comments"/>&nbsp;</p></td>
                            <td height="22" colspan="5">
                            <html:textarea rows="4" cols="80" property="comments" name="<%=Constants.CONTRACT_FOLLOW%>"/></td>
                        </tr>
                    </table>
                    <p style="margin-top: 0; margin-bottom: 0" align="left">
                        <logic:notEqual name="<%=Constants.CONTRACT_FOLLOW%>" property="folId" value="0">
                            <img onclick="return printContractFollow();" src="images/but_print.gif"/>
                        </logic:notEqual>
                        <%
            ContractFollowBean form = (ContractFollowBean) request.getAttribute(Constants.CONTRACT_FOLLOW);
            if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_EDIT, PermissionUtil.PER_PURCHASING_CONTRACT_FOLLOW, form.getCreatedEmp())) {
                        %>
                        <html:image onclick="return saveContractFollow();" src="images/but_save.gif" style="cursor: hand;"/><img border="0" src="images/blank.gif" width="2" height="17">
                        <%}%>
                        <html:image property="Back" value="Back" src="images/but_back.gif" onclick="return loadContractFollowList();"/>
                    </p>
    </div></td></tr></table>
    <html:hidden property="folId" name="<%=Constants.CONTRACT_FOLLOW%>" />
</form>