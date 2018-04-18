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
<%@ page import="com.venus.mc.request.RequestFormBean"%>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<%@ page import="com.venus.mc.bean.RequestBean"%>
<%@ page import="com.venus.core.util.GenericValidator"%>
<div id="requestFormTitle"><h3>
        <bean:message key="message.requestadd.title"/>/
        <logic:greaterThan name="<%=Constants.REQUEST%>" value="0" property="reqId">
            <bean:message key="message.detail.s"/>
        </logic:greaterThan>
        <logic:equal name="<%=Constants.REQUEST%>" value="0" property="reqId">
            <bean:message key="message.add.s"/>
        </logic:equal>
    </h3></div>
<div id="errorMessageDiv"></div>
<form name="requestForm">
    <table width="100%">
        <tr>
            <td class="lbl10"><bean:message key="message.request.number"/></td>
            <td colspan="2"><html:text styleClass="lbl10" size="20" property="requestNumber" name="<%=Constants.REQUEST%>"/></td>
            <td class="lbl10"><bean:message key="message.request.createdDate"/></td>
            <td colspan="2"><html:text styleClass="lbl10" property="createdDate" styleId="createdDateRequest" size="10" disabled="true" name="<%=Constants.REQUEST%>"/></td>
        </tr>  
        <tr>
            <td class="lbl10"><bean:message key="message.request.createdEmp"/></td>
            <td colspan="2"><html:text styleClass="lbl10" size="20" property="employeeName" name="<%=Constants.REQUEST%>" disabled="true"/></td>
            <td class="lbl10"><bean:message key="message.request.organization"/></td>
            <td colspan="2">
                <html:select property="createdOrg" name="<%=Constants.REQUEST%>" >
                    <html:options collection="<%=Constants.ORG_LIST%>" property="orgId" labelProperty="name"/>
                </html:select>
            </td>
        </tr>
        <%
            RequestFormBean form = (RequestFormBean) request.getAttribute(Constants.REQUEST);
            if (form.getStatus() == RequestBean.STATUS_HANDLE && form.getIsReceiveRequest() == 1) {%>
        <tr>
            <td class="lbl10"><bean:message key="message.request.assignedEmp2"/></td>
            <td colspan="5">
                <html:select property="assignedEmp" name="<%=Constants.REQUEST%>" >
                    <html:options collection="<%=Constants.EMPLOYEE_LIST%>" property="empId" labelProperty="fullname"/>
                </html:select>
            </td>
        </tr>
        <%} else {%>
        <tr>
            <td class="lbl10"><bean:message key="message.request.assignedEmp2"/></td>
            <td colspan="5">
                <html:select disabled="true" property="assignedEmp" name="<%=Constants.REQUEST%>" >
                    <html:options collection="<%=Constants.EMPLOYEE_LIST%>" property="empId" labelProperty="fullname"/>
                </html:select>
            </td>
        </tr>
        <%}%>
        <tr>
            <td colspan="6">
                <fieldset>
                    <legend class="lbl10b" ><bean:message key="message.request.approveSuggest"/></legend>
                    <p>
                    <table width="100%">
                        <tr>
                            <td class="lbl10"><html:multibox property="approveSuggest" name="<%=Constants.REQUEST%>" value="1"></html:multibox> <bean:message key="message.request.approveSuggest1"/></td>
                            <td class="lbl10"><html:multibox property="approveSuggest" name="<%=Constants.REQUEST%>" value="2"></html:multibox> <bean:message key="message.request.approveSuggest2"/></td>
                            <td class="lbl10"><html:multibox property="approveSuggest" name="<%=Constants.REQUEST%>" value="4"></html:multibox> <bean:message key="message.request.approveSuggest3"/></td>
                            <td class="lbl10"><html:multibox property="approveSuggest" name="<%=Constants.REQUEST%>" value="8"></html:multibox> <bean:message key="message.request.approveSuggest4"/></td>
                            <td class="lbl10"><html:multibox property="approveSuggest" name="<%=Constants.REQUEST%>" value="16"></html:multibox> <bean:message key="message.request.approveSuggest5"/></td>
                            </tr>
                        </table></p>
                    </fieldset>
                </td>
            </tr>                              
            <tr>
                <td colspan="6">
                    <fieldset>
                        <legend class="lbl10b"><bean:message key="message.request.statusSuggest"/></legend>
                    <p>
                    <table width="100%">
                        <tr>
                            <td class="lbl10"><html:radio property="statusSuggest" name="<%=Constants.REQUEST%>" value="1"><bean:message key="message.request.statusSuggest1"/></html:radio></td>
                            <td class="lbl10"><html:radio property="statusSuggest" name="<%=Constants.REQUEST%>" value="2"><bean:message key="message.request.statusSuggest2"/></html:radio></td>
                            <td class="lbl10"><html:radio property="statusSuggest" name="<%=Constants.REQUEST%>" value="3"><bean:message key="message.request.statusSuggest3"/></html:radio></td>
                            </tr>
                        </table>
                        </p>
                    </fieldset>
                </td>
            </tr>        
            <tr>
                <td colspan="6">
                    <fieldset>
                        <legend class="lbl10b"><bean:message key="message.request.materials"/></legend>
                    <%if ((form.getPermission() == RequestBean.STATUS_CREATE || form.getPermission() == RequestBean.STATUS_STORE) && form.getStatus() < RequestBean.STATUS_HANDLE) {%>
                    <div>
                        <html:image src="images/ico_xoa.gif" onclick="return delRequestMaterial();"/>
                        <img src="images/ico_them.gif" onclick="return selectMaterial('setSelectedRequestMaterial','<bean:message key="message.request"/>/<bean:message key="message.material.choose"/>');"/>
                        <img src="images/ico_themvt.gif" onclick="return newRequestMaterial('setSelectedRequestMaterial',null,'<bean:message key="message.request"/>/<bean:message key="message.material.add"/>');"/>
                        <%
                            if (form.getPermission() == RequestBean.STATUS_STORE && form.getStatus() == RequestBean.STATUS_STORE) {%>
                        <input type="button" onclick="return checkMaterial('<bean:message key="message.request.checkStock"/>', <bean:write property="reqId" name="<%=Constants.REQUEST%>"/>);" value="<bean:message key="message.request.checkStock"/>"/>
                        <%}%>
                    </div>
                    <%}%>
                    <p><div id="listRequestMaterialDataDiv"></div></p>
                </fieldset>
            </td>
        </tr>        
        <tr><td colspan="6" class="lbl10"><bean:message key="message.request.certificateRequire"/></td></tr>
        <tr><td colspan="6"><html:textarea rows="3" cols="80" property="certificateRequire" name="<%=Constants.REQUEST%>"/></td></tr>
        <tr><td colspan="6" class="lbl10"><bean:message key="message.request.description"/></td></tr>
        <tr><td colspan="6"><html:textarea rows="3" cols="80" property="description" name="<%=Constants.REQUEST%>"/></td></tr>
        <tr>
            <td class="lbl10"><bean:message key="message.request.whichUse"/></td>
            <td colspan="5" class="lbl10">
                <html:select styleClass="lbl10" property="whichUse" name="<%=Constants.REQUEST%>" onchange="return whichUseChanged(this);">
                    <html:options styleClass="lbl10" collection="<%=Constants.REQUEST_WHICHUSE_LIST%>" property="value" labelProperty="label"/>
                </html:select>
                <span name="whichuseSpan" id="whichuseSpan"></span>
            </td>
        </tr>
        <%//if (form.getPermission() == RequestBean.STATUS_STORE && form.getStatus()== RequestBean.STATUS_STORE) {%>
        <tr>
            <td colspan="6" class="lbl10">
                <bean:message key="message.request.store"/>
                <html:select styleClass="lbl10" property="storeApprove" name="<%=Constants.REQUEST%>">
                    <html:options styleClass="lbl10" collection="<%=Constants.REQUEST_APPROVE_LIST%>" property="value" labelProperty="label"/>
                </html:select>
                <html:select styleClass="lbl10" property="storeOrg" name="<%=Constants.REQUEST%>">
                    <html:options styleClass="lbl10" collection="<%=Constants.STORE_ORG_LIST%>" property="value" labelProperty="label"/>
                </html:select>
            </td>
        </tr>
        <tr>
            <td colspan="6"><html:textarea rows="3" cols="80" property="storeComment" name="<%=Constants.REQUEST%>"/></td>
        </tr>
        <%//} else {%>
        <!--<tr>
            <td colspan="6" class="lbl10">
        <bean:message key="message.request.store"/>
        <html:select disabled="true" styleClass="lbl10" property="storeApprove" name="<%=Constants.REQUEST%>">
            <html:options styleClass="lbl10" collection="<%=Constants.REQUEST_APPROVE_LIST%>" property="value" labelProperty="label"/>
        </html:select>
    </td>
</tr>
<tr>
    <td colspan="6"><html:textarea disabled="true" rows="3" cols="80" property="storeComment" name="<%=Constants.REQUEST%>"/></td>
</tr>-->
        <%//}%>
        <%//if (form.getPermission() == RequestBean.STATUS_CHECK && form.getStatus()== RequestBean.STATUS_CHECK) {%>      
        <tr>
            <td colspan="6" class="lbl10">
                <bean:message key="message.request.check"/>
                <html:select styleClass="lbl10" property="checkApprove" name="<%=Constants.REQUEST%>">
                    <html:options styleClass="lbl10" collection="<%=Constants.REQUEST_APPROVE_LIST%>" property="value" labelProperty="label"/>
                </html:select>
            </td>
        </tr>
        <tr>
            <td colspan="6"><html:textarea rows="3" cols="80" property="checkComment" name="<%=Constants.REQUEST%>"/></td>
        </tr>
        <%//} else {%>
        <!--<tr>
            <td colspan="6" class="lbl10">
        <bean:message key="message.request.check"/>
        <html:select disabled="true" styleClass="lbl10" property="checkApprove" name="<%=Constants.REQUEST%>">
            <html:options styleClass="lbl10" collection="<%=Constants.REQUEST_APPROVE_LIST%>" property="value" labelProperty="label"/>
        </html:select>
    </td>
</tr>
<tr>
    <td colspan="6"><html:textarea disabled="true" rows="3" cols="80" property="checkComment" name="<%=Constants.REQUEST%>"/></td>
</tr>-->
        <%//}%>
        <%//if (form.getPermission() == RequestBean.STATUS_PLANDEP && form.getStatus() == RequestBean.STATUS_PLANDEP) {%>
        <tr>
            <td colspan="6" class="lbl10">
                <bean:write property="purchaseOrgName" name="<%=Constants.REQUEST%>"/>
                <html:select styleClass="lbl10" property="plandepApprove" name="<%=Constants.REQUEST%>">
                    <html:options styleClass="lbl10" collection="<%=Constants.REQUEST_APPROVE_LIST%>" property="value" labelProperty="label"/>
                </html:select>
            </td>
        </tr>
        <tr>
            <td colspan="6"><html:textarea rows="3" cols="80" property="plandepComment" name="<%=Constants.REQUEST%>"/></td>
        </tr>
        <%//} else {%>
        <!--<tr>
            <td colspan="6" class="lbl10">
        <%if (!GenericValidator.isBlankOrNull(form.getPurchaseOrg())) {%>
        <bean:write property="purchaseOrgName" name="<%=Constants.REQUEST%>"/>
        <%} else {%>
        <bean:message key="message.request.plan"/>
        <%}%>
        <html:select disabled="true" styleClass="lbl10" property="plandepApprove" name="<%=Constants.REQUEST%>">
            <html:options styleClass="lbl10" collection="<%=Constants.REQUEST_APPROVE_LIST%>" property="value" labelProperty="label"/>
        </html:select>
    </td>
</tr>
<tr>
    <td colspan="6"><html:textarea disabled="true" rows="3" cols="80" property="plandepComment" name="<%=Constants.REQUEST%>"/></td>
</tr>-->
        <%//}%>
        <%//if (form.getPermission() == RequestBean.STATUS_MANAGER && form.getStatus()== RequestBean.STATUS_MANAGER) {%>
        <tr>
            <td colspan="6" class="lbl10">
                <bean:message key="message.request.bom"/>
                <html:select styleClass="lbl10" property="bomApprove" name="<%=Constants.REQUEST%>">
                    <html:options styleClass="lbl10" collection="<%=Constants.REQUEST_APPROVE_LIST%>" property="value" labelProperty="label"/>
                </html:select>
            </td>
        </tr>        
        <tr>
            <td colspan="6"><html:textarea rows="3" cols="80" property="bomComment" name="<%=Constants.REQUEST%>"/></td>
        </tr>
        <tr><td colspan="6" class="lbl10"><bean:message key="message.request.CTCV"/></td></tr>                       
        <tr>
            <td colspan="6">
                <fieldset>
                    <legend class="lbl10b"><bean:message key="message.request.CTCV.handler"/></legend>
                    <%int index = 0, count = 0, col = 6;%>
                    <table width="100%">
                        <tr>
                            <%
                                ArrayList orgList = (ArrayList) request.getAttribute(Constants.REQ_ORG_XL);
                                for (int i = 0; i < orgList.size(); i++) {
                                    if (orgList.get(i) instanceof OrganizationBean) {
                                        OrganizationBean org = (OrganizationBean) orgList.get(i);
                            %>
                            <%if (index == 0 && count != 0) {%>
                        </tr>
                        <tr>
                            <%}%>
                            <%if (org.getOrgId() == 0) {%>
                            <td class="lbl10" width="17%"></td>
                            <%} else {%>
                            <td class="lbl10" width="17%"><html:multibox property="orgHandle" name="<%=Constants.REQUEST%>"><%=org.getOrgId()%></html:multibox><%=org.getAbbreviate()%></td>
                            <%}
                                        count++;
                                        if (index == col - 1) {
                                            index = 0;
                                        } else {
                                            index++;
                                        }
                                    }
                                }
                            %>
                        </tr>
                    </table>
                </fieldset>
            </td>
        </tr>
        <tr><td colspan="6">&nbsp;</td></tr>
        <tr>
            <td colspan="6">
                <fieldset>
                    <legend class="lbl10b"><bean:message key="message.request.CTCV.prefer"/></legend>
                    <%index = 0;
                        count = 0;
                        col = 6;%>
                    <table width="100%">
                        <tr>
                            <%
                                orgList = (ArrayList) request.getAttribute(Constants.REQ_ORG_TK);
                                for (int i = 0; i < orgList.size(); i++) {
                                    if (orgList.get(i) instanceof OrganizationBean) {
                                        OrganizationBean org = (OrganizationBean) orgList.get(i);
                            %>
                            <%if (index == 0 && count != 0) {%>
                        </tr>
                        <tr>
                            <%}%>
                            <%if (org.getOrgId() == 0) {%>
                            <td class="lbl10" width="17%"></td>
                            <%} else {%>
                            <td class="lbl10" width="17%"><html:multibox property="orgRefer" name="<%=Constants.REQUEST%>"><%=org.getOrgId()%></html:multibox><%=org.getAbbreviate()%></td>
                            <%}
                                        count++;
                                        if (index == col - 1) {
                                            index = 0;
                                        } else {
                                            index++;
                                        }
                                    }
                                }
                            %>
                        </tr>
                    </table>
                </fieldset>
            </td>
        </tr>
        <tr><td colspan="6">&nbsp;</td></tr>
        <tr>
            <td colspan="6">
                <fieldset>
                    <legend class="lbl10b"><bean:message key="message.request.CTCV.paid"/></legend>
                    <%index = 0;
                        count = 0;
                        col = 6;%>
                    <table width="100%">
                        <tr>
                            <%
                                orgList = (ArrayList) request.getAttribute(Constants.REQ_ORG_TT);
                                for (int i = 0; i < orgList.size(); i++) {
                                    if (orgList.get(i) instanceof OrganizationBean) {
                                        OrganizationBean org = (OrganizationBean) orgList.get(i);
                            %>
                            <%if (index == 0 && count != 0) {%>
                        </tr>
                        <tr>
                            <%}%>
                            <%if (org.getOrgId() == 0) {%>
                            <td class="lbl10" width="17%"></td>
                            <%} else {%>
                            <td class="lbl10" width="17%"><html:multibox property="orgPaid" name="<%=Constants.REQUEST%>"><%=org.getOrgId()%></html:multibox><%=org.getAbbreviate()%></td>
                            <%}
                                        count++;
                                        if (index == col - 1) {
                                            index = 0;
                                        } else {
                                            index++;
                                        }
                                    }
                                }
                            %>
                        </tr>
                    </table>
                </fieldset>
            </td>            
        </tr>
        <%//} else {%>
        <!--<tr>
            <td colspan="6" class="lbl10">
        <bean:message key="message.request.bom"/>
        <html:select disabled="true" styleClass="lbl10" property="bomApprove" name="<%=Constants.REQUEST%>">
            <html:options styleClass="lbl10" collection="<%=Constants.REQUEST_APPROVE_LIST%>" property="value" labelProperty="label"/>
        </html:select>
    </td>
</tr>        
<tr>
    <td colspan="6"><html:textarea disabled="true" rows="3" cols="80" property="bomComment" name="<%=Constants.REQUEST%>"/></td>
</tr>-->
        <%//}%>
        <logic:greaterThan name="<%=Constants.REQUEST%>" value="0" property="reqId">
            <tr><td colspan="6">&nbsp;</td></tr>
            <tr>
                <td colspan="6"><div id='divAttachFileSystem' ><img src="img/indicator.gif"/></div></td>            
            </tr>
        </logic:greaterThan>
    </table>
    <html:image onclick="return saveRequestCopy();" src="images/but_save.gif"/>
    <img src="images/blank.gif" width="2" height="17">
    <html:image property="Back" value="Back" src="images/but_back.gif" onclick="return loadRequestList();"/>
    <html:hidden property="reqId" name="<%=Constants.REQUEST%>"/>
    <html:hidden property="isAssignEmp" name="<%=Constants.REQUEST%>"/>
</form>
<div id="requestMaterialHideDiv" style="display:none"></div>