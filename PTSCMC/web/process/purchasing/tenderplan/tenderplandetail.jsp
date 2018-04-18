<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<%@ page import="com.venus.mc.tenderplan.TenderPlanFormBean"%>
<div id="tenderPlanFormTitle"><h3><bean:message key="message.tenderplanadd.title"/>/
        <logic:greaterThan name="<%=Constants.TENDERPLAN%>" value="0" property="tenId"><bean:message key="message.detail.s"/></logic:greaterThan>
        <logic:equal name="<%=Constants.TENDERPLAN%>" value="0" property="tenId"><bean:message key="message.add.s"/></logic:equal>
    </h3></div>
<strong><div id="errorMessageDiv"></div></strong>
<form name="tenderPlanForm">
    <table width="100%">
        <tr>
            <td><bean:message key="message.tenderplan.number"/></td>
            <td><html:text size="30" property="tenderNumber" name="<%=Constants.TENDERPLAN%>"/></td>
            <td><bean:message key="message.tenderplan.createdDate"/></td>
            <td><html:text property="createdDate" size="10" styleId="createdDateTenderPlan" onclick="javascript: showCalendar('createdDateTenderPlan')" name="<%=Constants.TENDERPLAN%>"/></td>
        </tr>  
        <tr>
            <td><bean:message key="message.tenderplan.packName"/></td>
            <td><html:text size="30" property="packName" name="<%=Constants.TENDERPLAN%>"/></td>
            <td><bean:message key="message.tenderplan.evalKind"/></td>
            <td>
                <html:select property="evalKind" name="<%=Constants.TENDERPLAN%>">
                    <html:options collection="<%=Constants.TENDERPLAN_EVALKIND%>" property="value" labelProperty="label"/>
                </html:select>
            </td>
        </tr>
        <tr id="tenderPlanHandleempIdTr">
            <td><bean:message key="message.tenderplan.handleEmployee"/></td>
            <td colspan="3">
                <html:select style="width: 170px;" property="handleEmp" styleId="tenderPlanHandleEmpCmb" name="<%=Constants.TENDERPLAN%>" >
                    <html:options collection="<%=Constants.EMPLOYEE_LIST%>" property="empId" labelProperty="fullname"/>
                </html:select>
                <input type="button" onclick="return comboTextIdClick('tenderPlanForm','handleEmpName','handleEmp','tenderPlanHandleempIdTr','tenderPlanHandleempNameTr');" value='<>'/>
            </td>
        </tr>
        <tr style="display:none" id="tenderPlanHandleempNameTr">
            <td><bean:message key="message.tenderplan.handleEmployee"/></td>
            <td colspan="3">
                <input type="textbox" size="30" name="handleEmpName"/>
                <input type="button" onclick="return comboTextNameClick('tenderPlanForm','getEmployeeToCombobox.do',null,'handleEmpName','handleEmp','tenderPlanHandleEmpCmb','tenderPlanHandleempIdTr','tenderPlanHandleempNameTr');" value='<>'/>
            </td>
        </tr>
        <tr>
            <td colspan="4"><bean:message key="message.tenderplan.foundation"/><br/><html:textarea cols="100" rows="4" property="foundation" name="<%=Constants.TENDERPLAN%>"/></td>
        </tr>
        <tr>
            <td colspan="4"><bean:message key="message.tenderplan.supplyScope"/><br/><html:textarea cols="100" rows="4" property="supplyScope" name="<%=Constants.TENDERPLAN%>"/></td>
        </tr>
        <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_LIST, PermissionUtil.PER_LIBRARY_MATERIAL_PRICE)) {%>
        <tr>
            <td><bean:message key="message.tenderplan.financial"/></td>
            <td colspan="3"><html:text size="50" property="financial" name="<%=Constants.TENDERPLAN%>"/></td>
        </tr>
        <tr>
            <td><bean:message key="message.tenderplan.financial.preTax"/></td>
            <td colspan="3"><html:text size="50" readonly="true" property="financialPreTax" name="<%=Constants.TENDERPLAN%>"/></td>
        </tr>
        <%} else {%>
        <html:hidden property="financial" name="<%=Constants.TENDERPLAN%>"/>
        <html:hidden property="financialPreTax" name="<%=Constants.TENDERPLAN%>"/>
        <%}%>
        <tr>
            <td><bean:message key="message.tenderplan.form"/></td>
            <td colspan="3">
                <html:select property="form" name="<%=Constants.TENDERPLAN%>">
                    <html:options collection="<%=Constants.TENDERPLAN_FORM%>" property="value" labelProperty="label"/>
                </html:select>
            </td>
        </tr>
        <tr>
            <td><bean:message key="message.tenderplan.offer"/></td>
            <td colspan="3">
                <html:select property="offerType" name="<%=Constants.TENDERPLAN%>">
                    <html:options collection="<%=Constants.TENDERPLAN_OFFER%>" property="value" labelProperty="label"/>
                </html:select>
            </td>
        </tr>
        <!--<tr>
            <td><bean:message key="message.tenderplan.certificate"/></td>
            <td colspan="3">
        <html:select property="cerId" name="<%=Constants.TENDERPLAN%>">
            <html:options collection="<%=Constants.MATERIAL_CERTIFICATE_LIST%>" property="cerId" labelProperty="cerName"/>
        </html:select>
    </td>
</tr>-->
        <tr>
            <td colspan="4">
                <fieldset>
                    <legend><bean:message key="message.tenderplan.certificate"/></legend>
                    <logic:present name="<%=Constants.CAN_SAVE%>">
                        <table>
                            <tr>
                                <td>
                                    <html:image src="images/ico_xoa.gif" onclick="return delTenderPlanCertificate();"/>
                                    <html:image src="images/ico_them.gif" onclick="return addTenderPlanCertificate();"/>
                                    <select name="certificateList">
                                        <logic:iterate id="certificate" name="<%=Constants.MATERIAL_CERTIFICATE_LIST%>">
                                            <option value="<bean:write name="certificate" property="cerId"/>"><bean:write name="certificate" property="cerName"/></option>
                                        </logic:iterate>
                                    </select>
                                </td>
                            </tr>
                        </table>
                    </logic:present>
                    <div id="tenderPlanCertificateList"></div>
                </fieldset>
            </td>
        </tr>
        <tr>
            <td><bean:message key="message.tenderplan.deliveryTime"/></td>
            <td colspan="3"><html:text size="50" property="deliveryTime" name="<%=Constants.TENDERPLAN%>"/></td>
        </tr>
        <!--<tr>
            <td colspan="4">
                <fieldset>
                    <legend><bean:message key="message.tenderplan.evalStandard"/></legend>
                    <div><bean:message key="message.tenderplan.techEval"/></div>
        <html:textarea cols="100" rows="4" property="techEvalStandard" name="<%=Constants.TENDERPLAN%>"/>
        <br/>
        <div><bean:message key="message.tenderplan.comEval"/></div>
        <html:textarea cols="100" rows="4" property="comEvalStandard" name="<%=Constants.TENDERPLAN%>"/>
    </fieldset>
</td>
</tr>-->
        <tr>
            <td colspan="4">
                <fieldset>
                    <legend><bean:message key="message.tenderplan.plan"/></legend>
                    <table class="its" style="width:100%" >
                        <thead>
                            <tr>
                                <th><bean:message key="message.rowNum"/></th>
                                <th><bean:message key="message.tenderplan.plan.work"/></th>
                                <th><bean:message key="message.tenderplan.plan.time"/></th>
                                <th><bean:message key="message.tenderplan.plan.date"/></th>
                            </tr>
                        </thead>
                        <tr>
                            <td width="50px" align="center"><div align="center">1</div></td>
                            <td width="350px"><bean:message key="message.tenderplan.plan.bidSendingDate"/></td>
                            <td><html:text property="bidSendingTime" size="20" name="<%=Constants.TENDERPLAN%>"/></td>
                            <td><html:text property="bidSendingDate" size="60" styleId="bidSendingDate" onclick="javascript: showCalendar('bidSendingDate')" name="<%=Constants.TENDERPLAN%>"/></td>
                        </tr>
                        <tr>
                            <td><div align="center">2</div></td>
                            <td><bean:message key="message.tenderplan.plan.bidDeadline"/></td>
                            <td><html:text property="bidDeadlineTime" size="20" name="<%=Constants.TENDERPLAN%>"/></td>
                            <td><html:text property="bidDeadline" size="60" styleId="bidDeadline" onclick="javascript: showCalendar('bidDeadline')" name="<%=Constants.TENDERPLAN%>"/></td>
                        </tr>
                        <tr>
                            <td><div align="center">3</div></td>
                            <td><bean:message key="message.tenderplan.plan.bidOpeningDate"/></td>
                            <td><html:text property="bidOpeningTime" size="20" name="<%=Constants.TENDERPLAN%>"/></td>
                            <td><html:text property="bidOpeningDate" size="60" styleId="bidOpeningDate" onclick="javascript: showCalendar('bidOpeningDate')" name="<%=Constants.TENDERPLAN%>"/></td>
                        </tr>
                        <tr>
                            <td><div align="center">4</div></td>
                            <td><bean:message key="message.tenderplan.plan.evaluatedDate"/></td>
                            <td><html:text property="evaluatedTime" size="20" name="<%=Constants.TENDERPLAN%>"/></td>
                            <td><html:text property="evaluatedDate" size="60" styleId="evaluatedDate" onclick="javascript: showCalendar('evaluatedDate')" name="<%=Constants.TENDERPLAN%>"/></td>
                        </tr>
                        <tr>
                            <td><div align="center">5</div></td>
                            <td><bean:message key="message.tenderplan.plan.approvedDate"/></td>
                            <td><html:text property="approvedTime" size="20" name="<%=Constants.TENDERPLAN%>"/></td>
                            <td><html:text property="approvedDate" size="60" styleId="approvedDate" onclick="javascript: showCalendar('approvedDate')" name="<%=Constants.TENDERPLAN%>"/></td>
                        </tr>
                        <tr>
                            <td><div align="center">6</div></td>
                            <td><bean:message key="message.tenderplan.plan.contractDate"/></td>
                            <td><html:text property="contractTime" size="20" name="<%=Constants.TENDERPLAN%>"/></td>
                            <td><html:text property="contractDate" size="60" styleId="contractDate" onclick="javascript: showCalendar('contractDate')" name="<%=Constants.TENDERPLAN%>"/></td>
                        </tr>
                        <tr>
                            <td><div align="center">7</div></td>
                            <td><bean:message key="message.tenderplan.plan.contractExecDate"/></td>
                            <td><html:text property="contractExecTime" size="20" name="<%=Constants.TENDERPLAN%>"/></td>
                            <td><html:text property="contractExecDate" size="60" styleId="contractExecDate" onclick="javascript: showCalendar('contractExecDate')" name="<%=Constants.TENDERPLAN%>"/></td>
                        </tr>
                    </table>
                </fieldset>
            </td>
        </tr>
        <tr>
            <td colspan="4">
                <fieldset>
                    <legend><bean:message key="message.tenderplan.materialList"/></legend>
                    <logic:present name="<%=Constants.CAN_SAVE%>">
                        <div>                        
                            <html:image src="images/ico_xoa.gif" onclick="return deleteTenderPlanMaterial();"/>
                            <img src="images/ico_them.gif" onclick="return materialToTenderPlan('<bean:message key="message.tenderplan"/>/<bean:message key="message.material.choose"/>');"/>
                            <img onclick="return printTenderPlanMaterial();" src="images/but_print.gif"/>
                            <select name="cbxMaterialSource">
                                <option class="lbl9" value="1"><bean:message key="message.material.incontractprincipleexpire"/></option>
                                <option class="lbl9" value="2"><bean:message key="message.material.incontract"/></option>
                                <option class="lbl9" value="3"><bean:message key="message.material.order"/></option>
                                <option class="lbl9" value="4"><bean:message key="message.material.incontractprinciple"/></option>
                                <option class="lbl9" value="5"><bean:message key="message.material.notincontract"/></option>
                            </select>
                        </div>
                    </logic:present>
                    <p><div id="listTenderPlanMaterialDataDiv"></div></p>
                </fieldset>
            </td>
        </tr>
        <tr>
            <td colspan="4">
                <fieldset>
                    <legend><bean:message key="message.tenderplan.evalGroup"/></legend>
                    <table>
                        <tr>
                            <td>
                                <logic:present name="<%=Constants.CAN_SAVE%>">
                                    <html:image src="images/ico_xoa.gif" onclick="return delTenderPlanEvalGroup();"/>
                                    <html:image src="images/ico_them.gif" onclick="return addTenderPlanEvalGroup();"/>
                                </logic:present>
                                <logic:iterate id="emp" name="<%=Constants.EMPLOYEE_LIST%>">
                                    <input type="hidden" id="emp_<bean:write name="emp" property="empId"/>" value="<bean:write name="emp" property="posName"/> <bean:write name="emp" property="orgName"/>"/>
                                </logic:iterate>
                                <span id="tenderPlanEmployeeListIdSpan">
                                    <select name="employeeList" id="tenderPlanEmployeeListCmb">
                                        <logic:iterate id="emp" name="<%=Constants.EMPLOYEE_LIST%>">
                                            <option value="<bean:write name="emp" property="empId"/>"><bean:write name="emp" property="fullname"/></option>
                                        </logic:iterate>
                                    </select>
                                    <input type="button" onclick="return comboTextIdClick('tenderPlanForm','employeeListName','employeeList','tenderPlanEmployeeListIdSpan','tenderPlanEmployeeListNameSpan');" value='<>'/>
                                </span>
                                <span  style="display:none" id="tenderPlanEmployeeListNameSpan">
                                    <input type="textbox" size="29" name="employeeListName"/>
                                    <input type="button" onclick="return comboTextNameClick('tenderPlanForm','getEmployeeToCombobox.do',null,'employeeListName','employeeList','tenderPlanEmployeeListCmb','tenderPlanEmployeeListIdSpan','tenderPlanEmployeeListNameSpan');" value='<>'/>
                                </span>
                            </td>
                        </tr>
                    </table>
                    <div id="tenderPlanEmployeeList"></div>
                </fieldset>
            </td>
        </tr>        
        <tr>
            <td colspan="4">
                <fieldset>
                    <legend><bean:message key="message.tenderplan.vendor"/></legend>
                    <logic:present name="<%=Constants.CAN_SAVE%>">
                        <table>
                            <tr>
                                <td>
                                    <html:image src="images/ico_xoa.gif" onclick="return delTenderPlanVendor();"/>
                                    <img src="images/ico_them.gif" onclick="return vendorToTenderPlan('<bean:message key="message.tenderplan"/>/<bean:message key="message.choose"/> <bean:message key="message.vendor"/>');"/>
                                </td>
                            </tr>
                        </table>
                    </logic:present>
                    <div id="tenderPlanVendorList"></div>
                </fieldset>
            </td>
        </tr>
        <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_LIST, PermissionUtil.PER_LIBRARY_MATERIAL_PRICE)) {%>
        <!-- file attachment -->
        <logic:greaterThan name="<%=Constants.TENDERPLAN%>" value="0" property="tenId">
            <tr>
                <td colspan="4"><div id='divAttachFileSystem' ><img src="img/indicator.gif"/></div></td>            
            </tr>
        </logic:greaterThan>
        <%}%>
    </table>
    <logic:greaterThan name="<%=Constants.TENDERPLAN%>" value="0" property="tenId">
        <img onclick="return printTenderPlan();" src="images/but_print.gif"/>
    </logic:greaterThan>
    <%
        TenderPlanFormBean form = (TenderPlanFormBean) request.getAttribute(Constants.TENDERPLAN);
        if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_EDIT, PermissionUtil.PER_PURCHASING_TENDERPLAN, form.getCreatedEmp())) {
    %>
    <logic:present name="<%=Constants.CAN_SAVE%>">
        <html:image onclick="return saveTenderPlan();" src="images/but_save.gif"/><img src="images/blank.gif" width="2" height="17">
    </logic:present>
    <%}%>
    <html:image property="Back" value="Back" src="images/but_back.gif" onclick="return loadTenderPlanList();"/>
    <html:hidden property="tenId" name="<%=Constants.TENDERPLAN%>"/>
</form>
<div id="tenderPlanVendorHideDiv" style="display:none"></div>
<div id="tenderPlanMaterialHideDiv" style="display:none"></div>