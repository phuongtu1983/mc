<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.MCUtil"%>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.venus.mc.bean.OrganizationBean"%>
<%@ page import="com.venus.mc.bean.TechEvalBean"%>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<%@ page import ="com.venus.core.util.*"%>
<div id="errorMessageDiv"></div>
<form name="techEvalForm">
    <table style="width:100%">
        <logic:equal value="1" property="form" name="<%=Constants.TECH_EVAL%>">
            <tr>
                <td width="80px"><bean:message key="message.techeval.borNumber"/></td>
                <td width="100px"><html:text size="20" property="borNumber" readonly="true" name="<%=Constants.TECH_EVAL%>"/></td>            
            </tr>
        </logic:equal>
        <tr>            
            <td width="60px"><bean:message key="message.techeval.createdDate"/></td>
            <td width="60px"><html:text readonly="true" size="20" property="createdDate" styleId="createdDate1" onclick="javascript: showCalendar('createdDate1')" name="<%=Constants.TECH_EVAL%>" /></td>
            <td width="30px"><bean:message key="message.techeval.tenNumber"/></td>
            <td width="10px"><html:text property="tenNumber" size="20" readonly="true" name="<%=Constants.TECH_EVAL%>"/></td>
        </tr>               
        <tr>
            <td colspan="6">
                <fieldset>
                    <legend><bean:message key="message.techeval.emp"/></legend>
                    <table>
                        <tr>
                            <td>
                                <html:image src="images/ico_xoa.gif" onclick="return delTechEvalGroup();"/>
                                <html:image src="images/ico_them.gif" onclick="return addTechEvalGroup();"/>
                                <span id="techEvalEmployeeListIdSpan">
                                    <select name="employeeList" id="techEvalEmployeeListCmb">
                                        <logic:iterate id="emp" name="<%=Constants.EMPLOYEE_LIST%>">
                                            <option value='<bean:write name="emp" property="empId"/>'><bean:write name="emp" property="fullname"/></option>
                                        </logic:iterate>
                                    </select>
                                    <input type="button" onclick="return comboTextIdClick('techEvalForm','employeeListName','employeeList','techEvalEmployeeListIdSpan','techEvalEmployeeListNameSpan');" value='<>'/>
                                </span>
                                <span  style="display:none" id="techEvalEmployeeListNameSpan">
                                    <input type="textbox" size="29" name="employeeListName"/>
                                    <input type="button" onclick="return comboTextNameClick('techEvalForm','getEmployeeToCombobox.do',null,'employeeListName','employeeList','techEvalEmployeeListCmb','techEvalEmployeeListIdSpan','techEvalEmployeeListNameSpan');" value='<>'/>
                                </span>
                            </td>
                        </tr>
                    </table>
                    <div id="techEvalEmployeeList"></div>
                </fieldset>
            </td>
        </tr>
        <tr>
            <td colspan="6">
                <fieldset>
                    <legend><bean:message key="message.techeval.tech"/></legend>
                    <div id='vendorListTE'></div>
                </fieldset>
            </td>
        </tr>
        <logic:notEqual name="<%=Constants.TECH_EVAL%>" property="teId" value="0">
            <tr>
                <td colspan="6"><div id='divAttachFileSystem4' ><img src="img/indicator.gif"/></div></td>            
            </tr>
        </logic:notEqual>
    </table>
    <logic:notEqual name="<%=Constants.TECH_EVAL%>" property="teId" value="0">
        <img onclick="return printTechEval();" src="images/but_print.gif"/>
    </logic:notEqual>
    <logic:present name="<%=Constants.CAN_SAVE%>">
        <%
            TechEvalBean form = (TechEvalBean) request.getAttribute(Constants.TECH_EVAL);
            if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_EDIT, PermissionUtil.PER_PURCHASING_TENDERPLAN, form.getCreatedEmp())) {
        %>
        <html:image onclick="return saveTechEval();" src="images/but_save.gif"/>
        <input type="button" onclick="return rollbackMaterial(<bean:write property="tenId" name="<%=Constants.TECH_EVAL%>"/>);" value="<bean:message key="message.material.rollback"/>"/>
        <%}%>
    </logic:present>    
    <html:hidden property="teId" name="<%=Constants.TECH_EVAL%>"/>
    <html:hidden property="tenId" name="<%=Constants.TECH_EVAL%>"/>
    <input type="hidden" value="0" id="terId"/>
    <input type="hidden" value="0" id="venId"/>
    <input type="hidden" value="0" id="detId"/>
</form>
<div id="techEvalGroupHideDiv" style="display:none"></div>