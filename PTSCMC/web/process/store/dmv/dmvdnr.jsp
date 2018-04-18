<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ page import="com.venus.mc.util.MCUtil"%>

<div id="dmvFormTitle"><h3><bean:message key="message.dmv.title"/></h3></div>
<div id="dmvErrorMessageDiv"></div>
<form name="dmvForm">
    <table width="100%">
        <logic:equal name="<%=Constants.DMV_OBJ%>" value="0" property="dmvId">
            <tr>
                <td><bean:message key="message.dmv.deliverynotice"/></td>
                <td><html:select property="dnId" onchange="dmvDnChanged(this, this.selectedIndex);" name="<%=Constants.DMV_OBJ%>">
                        <html:options collection="<%=Constants.DELIVERY_NOTICE_LIST%>" property="value" labelProperty="label"/>
                    </html:select>                    
                </td>
                <td><bean:message key="message.dmv.request"/></td>
                <td id="dmvRequestListCbx"><html:select property="reqId" onchange="dmvReqChanged(this, this.selectedIndex);" name="<%=Constants.DMV_OBJ%>">
                        <html:options collection="<%=Constants.REQUEST_LIST%>" property="value" labelProperty="label"/>
                    </html:select>                    
                </td>
                
            </tr>
        </logic:equal>
        <tr>
            <td id="dmvDetail" colspan="4"></td>
        </tr>
    </table>
    <logic:greaterThan name="<%=Constants.DMV_OBJ%>" value="0" property="dmvId">
        <html:image onclick="return printDmvForm();" src="images/but_print.gif"/>
    </logic:greaterThan>
    <html:image onclick="return saveDmv();" src="images/but_save.gif"/><img src="images/blank.gif" width="2" height="17">
    <html:image property="Back" value="Back" src="images/but_back.gif" onclick="return loadDmvList();"/>  
</form>
