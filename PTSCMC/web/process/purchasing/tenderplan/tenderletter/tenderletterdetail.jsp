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
<%@ page import="com.venus.mc.bean.TenderLetterBean"%>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<div id="errorMessageDiv"></div>
<form name="tenderLetterForm">
    <table width="100%">
        <tr>
            <td width="80px"><bean:message key="message.tenderletter.tenNumber"/></td>
            <td colspan="2"><html:text readonly="true" size="20" property="tenNumber" name="<%=Constants.TENDER_LETTER%>"/></td>
            <td><bean:message key="message.tenderletter.createdDate"/></td>
            <td colspan="2"><html:text readonly="true" property="createdDate" styleId="createdDate2" onclick="javascript: showCalendar('createdDate2')" size="20" name="<%=Constants.TENDER_LETTER%>"/></td>
            <%-- <td width="140px"><bean:message key="message.tenderletter.recievedEmp1"/></td>
                <td colspan="2">
                <html:select property="recievedEmp1" name="<%=Constants.TENDER_LETTER%>" >
                    <html:options style="border-width:1px;text-align:left" collection="<%=Constants.EMPLOYEE_LIST%>" property="value" labelProperty="label"/>
                </html:select>
            </td>--%>
        </tr>  
        <%-- <tr>
             <td><bean:message key="message.tenderletter.createdDate"/></td>
             <td colspan="2"><html:text readonly="true" property="createdDate" styleId="createdDate2" onclick="javascript: showCalendar('createdDate2')" size="20" name="<%=Constants.TENDER_LETTER%>"/></td>
              <td><bean:message key="message.tenderletter.recievedEmp2"/></td>
             <td colspan="2">
                 <html:select property="recievedEmp2" name="<%=Constants.TENDER_LETTER%>" >
                     <html:options style="border-width:1px;text-align:left" collection="<%=Constants.EMPLOYEE_LIST%>" property="value" labelProperty="label"/>
                 </html:select>
             </td>
         </tr>--%>

        <tr>
            <td colspan="6">
                <fieldset>
                    <legend><bean:message key="message.tenderletter.list"/></legend>
                    <logic:notEqual name="<%=Constants.TENDER_LETTER%>" property="letId" value="0">
                    <input value="<bean:message key="message.tenderletter.printall"/>" onclick="printTenderLetterPrint(<bean:write property="letId" name="<%=Constants.TENDER_LETTER%>" />);" type="button">
                    </logic:notEqual>
                    <p><div id="listTenderLetterVendorDataDiv"  style="width:700px;height:300px;overflow:auto"></div></p>
                </fieldset>
            </td>
        </tr>        
        <logic:notEqual name="<%=Constants.TENDER_LETTER%>" property="letId" value="0">
            <tr>
                <td colspan="6"><div id='divAttachFileSystem1' ><img src="img/indicator.gif"/></div></td>            
            </tr>
        </logic:notEqual>        
    </table>
    <%
            TenderLetterBean form = (TenderLetterBean) request.getAttribute(Constants.TENDER_LETTER);
            if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_EDIT, PermissionUtil.PER_PURCHASING_TENDERPLAN, form.getCreatedEmp())) {
    %>
    <logic:present name="<%=Constants.CAN_SAVE%>">
        <html:image onclick="return saveTenderLetter();" src="images/but_save.gif"/>
    </logic:present>
    <%}%>
    <html:hidden property="letId" name="<%=Constants.TENDER_LETTER%>"/>
    <html:hidden property="tenId" name="<%=Constants.TENDER_LETTER%>"/>
    <html:hidden property="venId" name="<%=Constants.TENDER_LETTER%>"/>
</form>
<div id="tenderLetterVendorHideDiv" style="display:none"></div>