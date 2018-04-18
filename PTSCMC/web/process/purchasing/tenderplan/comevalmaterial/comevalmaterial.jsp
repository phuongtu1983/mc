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
<%@ page import="com.venus.mc.comevalmaterial.ComEvalMaterialFormBean"%>
<%@ page import="com.venus.mc.bean.ComEvalBean"%>
<div id="errorMessageDiv"></div>
<form name="comEvalMaterialForm">
    <table  style="width:100%">
        <logic:equal value="1" property="form" name="<%=Constants.COM_EVAL%>">
            <tr>
                <td width="80px"><bean:message key="message.comevalmaterial.borNumber"/></td>
                <td width="100px"><html:text size="20" property="borNumber"  readonly="true" name="<%=Constants.COM_EVAL%>"/></td>            
            </tr>
        </logic:equal>
        <tr>
            <td width="60px"><bean:message key="message.comevalmaterial.createdDate"/></td>
            <td width="60px"><html:text readonly="true" size="20" property="createdDate" styleId="createdDate3" onclick="javascript: showCalendar('createdDate3')" name="<%=Constants.COM_EVAL%>" /></td>
            <td width="30px"><bean:message key="message.comevalmaterial.tenNumber"/></td>
            <td width="10px"><html:text property="tenNumber" size="20" readonly="true" name="<%=Constants.COM_EVAL%>"/></td>
        </tr>
        <tr>
            <td colspan="6">
                <fieldset>
                    <legend><bean:message key="message.comevalmaterial.emp"/></legend>
                    <table>
                        <tr>
                            <td>
                                <html:image src="images/ico_xoa.gif" onclick="return delComEvalMaterialGroup();"/>
                                <html:image src="images/ico_them.gif" onclick="return addComEvalMaterialGroup();"/>
                                <span id="comEvalMatEmployeeListIdSpan">
                                    <select name="employeeList" id="comEvalMatEmployeeListCmb">
                                        <logic:iterate id="emp" name="<%=Constants.EMPLOYEE_LIST%>">
                                            <option value="<bean:write name="emp" property="empId"/>"><bean:write name="emp" property="fullname"/></option>
                                        </logic:iterate>
                                    </select>
                                    <input type="button" onclick="return comboTextIdClick('comEvalMaterialForm','employeeListName','employeeList','comEvaMatlEmployeeListIdSpan','comEvalMatEmployeeListNameSpan');" value='<>'/>
                                </span>
                                <span  style="display:none" id="comEvalMatEmployeeListNameSpan">
                                    <input type="textbox" size="29" name="employeeListName"/>
                                    <input type="button" onclick="return comboTextNameClick('comEvalMaterialForm','getEmployeeToCombobox.do',null,'employeeListName','employeeList','comEvalMatEmployeeListCmb','comEvalMatEmployeeListIdSpan','comEvalMatEmployeeListNameSpan');" value='<>'/>
                                </span>
                            </td>
                        </tr>
                    </table>
                    <div id="comEvalMaterialEmployeeList"></div>
                </fieldset>
            </td>
        </tr>
        <tr>
            <td colspan="6">
                <fieldset>
                    <legend><bean:message key="message.comevalmaterial.tech"/></legend>
                    <div id='materialList'></div>
                </fieldset>
            </td>
        </tr>
        <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_LIST, PermissionUtil.PER_LIBRARY_MATERIAL_PRICE)) {%>
        <logic:notEqual name="<%=Constants.COM_EVAL%>" property="ceId" value="0">
            <tr>
                <td colspan="6"><div id='divAttachFileSystem5' ><img src="img/indicator.gif"/></div></td>            
            </tr>
        </logic:notEqual>
        <%}%>
    </table>
    <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_LIST, PermissionUtil.PER_LIBRARY_MATERIAL_PRICE)) {%>
    <logic:notEqual name="<%=Constants.COM_EVAL%>" property="ceId" value="0">
        <img onclick="return printComEvalMaterial();" src="images/but_print.gif"/>
    </logic:notEqual>
    <%}%>
    <%
        ComEvalBean form = (ComEvalBean) request.getAttribute(Constants.COM_EVAL);
        if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_EDIT, PermissionUtil.PER_PURCHASING_TENDERPLAN, form.getCreatedEmp())) {
    %>
    <logic:present name="<%=Constants.CAN_SAVE%>">
        <html:image onclick="return saveComEvalMaterial();" src="images/but_save.gif"/>
    </logic:present>
    <%}%>
    <html:hidden property="ceId" name="<%=Constants.COM_EVAL%>"/>
    <html:hidden property="tenId" name="<%=Constants.COM_EVAL%>"/>
    <html:hidden property="cemId" name="<%=Constants.COM_EVAL%>"/>
</form>
<div id="comEvalMaterialGroupHideDiv" style="display:none"></div>