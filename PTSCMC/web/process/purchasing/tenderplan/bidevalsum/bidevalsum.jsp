<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.MCUtil"%>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<%@ page import="com.venus.mc.bean.OrganizationBean"%>
<%@ page import="com.venus.mc.bean.BidEvalSumBean"%>
<%@ page import="com.venus.mc.bean.ContractBean"%>
<div id="errorMessageDiv"></div>
<form name="bidEvalSumForm">
    <table width="100%">
        <tr>
            <td width="40px"><bean:message key="message.bidevalsum.besNumber"/></td>
            <td width="100px"><html:text size="20" property="besNumber" name="<%=Constants.BID_EVAL_SUM%>"/></td>
            <td width="30px"><bean:message key="message.bidevalsum.tenNumber"/></td>
            <td width="10px"><html:text property="tenNumber" size="10" readonly="true" name="<%=Constants.BID_EVAL_SUM%>"/></td>
        </tr>
        <tr>
            <td><bean:message key="message.bidevalsum.createdDate"/></td>
            <td colspan="2"><html:text readonly="true" size="20" property="createdDate" styleId="createdDate6" onclick="javascript: showCalendar('createdDate6')" name="<%=Constants.BID_EVAL_SUM%>" /></td>
        </tr>
        <tr>
            <td colspan="6">
                <fieldset>
                    <legend><bean:message key="message.bidevalsum.emp"/></legend>
                    <logic:present name="<%=Constants.CAN_SAVE%>">
                        <table>
                            <tr>
                                <td>
                                    <html:image src="images/ico_xoa.gif" onclick="return delBidEvalSumGroup();"/>
                                    <html:image src="images/ico_them.gif" onclick="return addBidEvalSumGroup();"/>
                                    <span id="bidEvalSumEmployeeListIdSpan">
                                        <select name="employeeList" id="bidEvalSumEmployeeListCmb">
                                            <logic:iterate id="emp" name="<%=Constants.EMPLOYEE_LIST%>">
                                                <option value="<bean:write name="emp" property="empId"/>"><bean:write name="emp" property="fullname"/></option>
                                            </logic:iterate>
                                        </select>
                                        <input type="button" onclick="return comboTextIdClick('bidEvalSumForm','employeeListName','employeeList','bidEvalSumEmployeeListIdSpan','bidEvalSumEmployeeListNameSpan');" value='<>'/>
                                    </span>
                                    <span  style="display:none" id="bidEvalSumEmployeeListNameSpan">
                                        <input type="textbox" size="29" name="employeeListName"/>
                                        <input type="button" onclick="return comboTextNameClick('bidEvalSumForm','getEmployeeToCombobox.do',null,'employeeListName','employeeList','bidEvalSumEmployeeListCmb','bidEvalSumEmployeeListIdSpan','bidEvalSumEmployeeListNameSpan');" value='<>'/>
                                    </span>
                                </td>
                            </tr>
                        </table>
                    </logic:present>
                    <div id="bidEvalSumEmployeeList"></div>
                </fieldset>
            </td>
        </tr>
        <tr>
            <td colspan="6">
                <fieldset>
                    <legend><bean:message key="message.bidevalsum.tech"/></legend>
                    <logic:greaterThan name="<%=Constants.BID_EVAL_SUM%>" value="0" property="besId">
                        <div>
                            <input type="button" onclick="return tenderPlanCreateContract('<%=ContractBean.KIND_CONTRACT%>');" value="<bean:message key="message.tenderplan.createContract"/>"/>
                            <input type="button" onclick="return tenderPlanCreateContract('<%=ContractBean.KIND_PRINCIPLE%>');" value="<bean:message key="message.tenderplan.createPrinciple"/>"/>
                            <input type="button" onclick="return tenderPlanCreateContract('<%=ContractBean.KIND_ORDER%>');" value="<bean:message key="message.order.create"/>"/>
                        </div>
                    </logic:greaterThan>
                    <div id='vendor2List' style="width:700px;height:300px;overflow:auto"></div>
                </fieldset>
            </td>
        </tr>
        <tr>
            <td colspan="4">
                <fieldset>
                    <legend><bean:message key="message.techeval.danhmuchh"/></legend>
                    <p><div id="bidEvalSumMaterialList" style="width:700px;height:300px;overflow:auto"></div></p>
                </fieldset>
            </td>
        </tr>
        <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_LIST, PermissionUtil.PER_LIBRARY_MATERIAL_PRICE)) {%>
        <logic:notEqual name="<%=Constants.BID_EVAL_SUM%>" property="besId" value="0">
            <tr>
                <td colspan="6"><div id='divAttachFileSystem6' ><img src="img/indicator.gif"/></div></td>            
            </tr>
        </logic:notEqual>
        <%}%>
    </table>
    <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_LIST, PermissionUtil.PER_LIBRARY_MATERIAL_PRICE)) {%>
    <logic:greaterThan value="0" name="<%=Constants.BID_EVAL_SUM%>" property="besId">
        <img onclick="return printBidEvalSum();" src="images/but_print.gif"/>
    </logic:greaterThan>
    <%}%>
    <%
        BidEvalSumBean form = (BidEvalSumBean) request.getAttribute(Constants.BID_EVAL_SUM);
        if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_EDIT, PermissionUtil.PER_PURCHASING_TENDERPLAN, form.getCreatedEmp())) {
    %>
    <logic:present name="<%=Constants.CAN_SAVE%>">
        <html:image onclick="return saveBidEvalSum();" src="images/but_save.gif"/>
    </logic:present>
    <logic:present name="<%=Constants.CAN_EMAIL%>">
        <input value="<bean:message key="message.notify.request.notcode.email"/>" onclick="return emailForNotMaterialCodeBidEvalSum();" type="submit">
    </logic:present>
    <%}%>
    <html:hidden property="besId" name="<%=Constants.BID_EVAL_SUM%>"/>
    <html:hidden property="tenId" name="<%=Constants.BID_EVAL_SUM%>"/>  
</form>
<div id="bidEvalSumGroupHideDiv" style="display:none"></div>