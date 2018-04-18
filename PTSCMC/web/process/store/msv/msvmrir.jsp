<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ page import="com.venus.mc.util.MCUtil"%>

<div id="msvFormTitle"><h3><bean:message key="message.msv.title"/></h3></div>
<div id="errorMessageDiv"></div>
<form name="msvForm">
    <table width="100%">
        <logic:equal name="<%=Constants.MSV_OBJ%>" value="0" property="msvId">
            <tr>
                <td class="lbl9"><bean:message key="message.msv.mrirnumber"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <html:select styleClass="lbl9"  property="mrirId" onchange="selectMrir4Msv(this, this.selectedIndex);" name="<%=Constants.MSV_OBJ%>">
                        <html:options styleClass="lbl9" collection="<%=Constants.MRIR_LIST%>" property="value" labelProperty="label"/>
                    </html:select>                    
                </td>
            </tr>
        </logic:equal>
        <tr>
            <td id="msvDetail"></td>
        </tr>
    </table>
    <logic:greaterThan name="<%=Constants.MSV_OBJ%>" value="0" property="msvId">
        <html:image  onclick="return printMsvForm();" src="images/but_print.gif"/>
    </logic:greaterThan>
    <html:image onclick="return saveMsv1();" src="images/but_save.gif"/><img src="images/blank.gif" width="2" height="17">
    <html:image property="Back" value="Back" src="images/but_back.gif" onclick="return loadMsvList();"/>  
</form>
