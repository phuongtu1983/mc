<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib prefix="display" uri="/tags/displaytag" %>
<%@ page import="com.venus.mc.util.MCUtil"%>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import ="com.venus.core.util.*"%>
<div id="techEvalFormTitle"><h3><bean:message key="message.techeval.title1"/></h3></div>
<div id="errorMessageDiv"></div>
<%
            int kind = (int) NumberUtil.parseInt(session.getAttribute("kind").toString(), 0);
%>
<form name="techEvalDetailForm">
    <table width="100%">
        <tr>            
            <td>&nbsp;&nbsp;&nbsp;&nbsp;<bean:message key="message.techeval.vendor"/>&nbsp;<html:text readonly="true" property="vendorName" size="70" name="<%=Constants.TECH_EVAL_DETAIL%>"/></td>
            <% if (kind == 0) {%>
            <td colspan="2"><bean:message key="message.techeval.ketluan"/>&nbsp;
                <html:select property="result" name="<%=Constants.TECH_EVAL_DETAIL%>" >
                    <html:options collection="<%=Constants.RESULT_LIST%>" style="border-width:1px;text-align:left" property="value" labelProperty="label"/>
                </html:select>
                <% }%>
            </td>
        </tr>
        <tr>
            <td colspan="4">
                <fieldset>
                    <legend><bean:message key="message.techeval.danhmuchh"/></legend>
                    <p><div id="listTechEvalDetailDataDiv"></div></p>
                </fieldset>
            </td>
        </tr>
        <% //if (kind == 0) {%>
        <tr>
            <td colspan="4">
                <fieldset>
                    <legend><bean:message key="message.techeval.dkk"/></legend>
                    <p><bean:message key="message.techeval.hoso"/>&nbsp;<html:textarea style="border-width:1px;text-align:left" cols="140" rows="4" name="<%=Constants.TECH_EVAL_DETAIL%>" property="certificateAttach" /></p>
                </fieldset>
            </td>
        </tr>
        <%// }%>
    </table>
    <html:image property="Back" value="Back" src="images/but_bienban.gif" onclick="return techClarificationFrm();"/>&nbsp;
    <html:image onclick="return saveTechEvalDetail();" src="images/but_save.gif"/>
    <html:hidden property="terId" name="<%=Constants.TECH_EVAL_DETAIL%>"/>
    <html:hidden property="tenId" name="<%=Constants.TECH_EVAL_DETAIL%>"/>
    <html:hidden property="venId" name="<%=Constants.TECH_EVAL_DETAIL%>"/>
    <html:hidden property="reqDetailId" name="<%=Constants.TECH_EVAL_DETAIL%>"/>
</form>
<div id="techEvalGroupHideDiv" style="display:none"></div>
<div id="popupDialog2" dojoType="dijit.Dialog"></div>