<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib prefix="display" uri="/tags/displaytag" %>
<%@ page import="com.venus.mc.util.MCUtil"%>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ page import="java.util.ArrayList"%>
<div id="techClarificationFormTitle"><h3><bean:message key="message.techclarificationdetail.title"/></h3></div>
<div id="errorMessageDiv"></div>
<form name="techClarificationDetailForm">
    <table width="100%">
        <tr>
            <td colspan="6">
                <fieldset>
                    <legend class="lbl10b" ><bean:message key="message.techclarificationdetail.ttph"/></legend>
                    <p>
                    <table width="100%">
                        <tr>
                            <td><bean:message key="message.techclarificationdetail.discipline"/></td>
                            <td colspan="3"><html:text  property="discipline" size="10" name="<%=Constants.TECH_CLARIFICATION_DETAIL%>"/></td>
                            <td colspan="2"><bean:message key="message.techclarification.category"/>&nbsp;<html:text  property="category" size="10" name="<%=Constants.TECH_CLARIFICATION_DETAIL%>"/></td>
                        </tr>
                        <tr>
                            <td><bean:message key="message.techclarification.notes"/></td>
                            <td colspan="4"><html:text  property="note" size="90" name="<%=Constants.TECH_CLARIFICATION_DETAIL%>"/></td>
                        </tr>
                    </table>
                    </p>
                </fieldset>
            </td>
        </tr>
        <tr>
            <td colspan="6">
                <fieldset>
                    <legend class="lbl10b" ><bean:message key="message.techclarificationdetail.tthm"/></legend>
                    <p>
                    <table width="100%">
                        <tr>
                            <td><bean:message key="message.techclarificationdetail.subcategory"/></td>
                            <td colspan="4"><html:text  property="subcategory" size="90" name="<%=Constants.TECH_CLARIFICATION_DETAIL%>"/></td>
                        </tr>
                        <tr>
                            <td><bean:message key="message.techclarificationdetail.reference"/></td>
                            <td colspan="4"><html:text  property="reference" size="90" name="<%=Constants.TECH_CLARIFICATION_DETAIL%>"/></td>
                        </tr>
                        <tr>
                            <td><bean:message key="message.techclarificationdetail.clarification"/></td>
                            <td colspan="4"><html:text  property="clarification" size="90" name="<%=Constants.TECH_CLARIFICATION_DETAIL%>"/></td>
                        </tr>
                        <tr>
                            <td><bean:message key="message.techclarificationdetail.supplierReply"/></td>
                            <td colspan="4"><html:text  property="supplierReply" size="90" name="<%=Constants.TECH_CLARIFICATION_DETAIL%>"/></td>
                        </tr>
                        <tr>
                            <td><bean:message key="message.techclarificationdetail.status"/></td>
                            <td colspan="4">
                                <html:select property="status" name="<%=Constants.TECH_CLARIFICATION_DETAIL%>" >
                                    <html:options collection="<%=Constants.STATUS_LIST%>" property="value" labelProperty="label"/>
                                </html:select>
                            </td>
                        </tr>
                    </table>
                    </p>
                </fieldset>
            </td>
        </tr>
        <tr>
            <td colspan="6">
                <fieldset>
                    <legend><bean:message key="message.techclarificationdetail.list"/></legend>                    
                    <p><div id="listTechClarificationDetailDataDiv"></div></p>
                </fieldset>
            </td>
        </tr>             
    </table>    
    <html:image onclick="return saveTechClarificationDetail();" src="images/but_save.gif"/><img src="images/blank.gif" width="2" height="17">    
    <html:image property="Back" value="Back" src="images/but_back.gif" onclick="return techClarificationFrm();"/>
    <html:hidden property="detId" name="<%=Constants.TECH_CLARIFICATION_DETAIL%>"/>
    <html:hidden property="tclId" name="<%=Constants.TECH_CLARIFICATION_DETAIL%>"/>
</form>
<div id="techEvalGroupHideDiv" style="display:none"></div>    
