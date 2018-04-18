<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ page import="com.venus.mc.util.MCUtil"%>

<div id="mrvFormTitle"><h3><bean:message key="message.mrv.title"/></h3></div>
<div class="lbl9r" id="mrvErrorMessageDiv"></div>
<form name="mrvForm">
    <table width="100%">
        <logic:equal name="<%=Constants.MRV_OBJ%>" value="0" property="mrvId">
            <tr>
                <td class="lbl9"><bean:message key="message.msv.mrirnumber"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <html:select styleClass="lbl9"  property="mrirId" onchange="selectMrir4Mrv(this, this.selectedIndex);" name="<%=Constants.MRV_OBJ%>">
                        <html:options styleClass="lbl9" collection="<%=Constants.MRIR_LIST%>" property="value" labelProperty="label"/>
                    </html:select>                    
                </td>
            </tr>
        </logic:equal>
        <tr>
            <td id="mrvDetail"></td>
        </tr>
    </table>
    <logic:greaterThan name="<%=Constants.MRV_OBJ%>" value="0" property="mrvId">
        <html:image  onclick="return printMrvForm();" src="images/but_print.gif"/>
    </logic:greaterThan>
    <html:image onclick="return saveMrv();" src="images/but_save.gif"/><img src="images/blank.gif" width="2" height="17">
    <html:image property="Back" value="Back" src="images/but_back.gif" onclick="return loadMrvList();"/>  
</form>
