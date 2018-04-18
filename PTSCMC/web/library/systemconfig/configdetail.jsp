<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>
<h3><bean:message key="param.warning"/></h3>
<form name="configForm">
    <table border="0" width="100%" cellspacing="0" cellpadding="0" >
        <tr><td><div align="center">
                    <table width="90%" border="0" cellspacing="0" cellpadding="0" style="border-width: 0px">
                        <tr>
                            <td height="22"><bean:message key="param.warning.label.deliveryNotice"/> TO</td>
                            <td height="22"><html:text property="dnTO" size="70" name="<%=Constants.CONFIG%>"/></td>
                        </tr>
                        <tr>
                            <td height="22"><bean:message key="param.warning.label.deliveryNotice"/> CC</td>
                            <td height="22"><html:text property="dnCC" size="70" name="<%=Constants.CONFIG%>"/></td>
                        </tr>
                        <tr>
                            <td height="22"><bean:message key="param.warning.label.invoice"/> TO</td>
                            <td height="22"><html:text property="invoiceTO" size="70" name="<%=Constants.CONFIG%>"/></td>
                        </tr>
                        <tr>
                            <td height="22"><bean:message key="param.warning.label.invoice"/> CC</td>
                            <td height="22"><html:text property="invoiceCC" size="70" name="<%=Constants.CONFIG%>"/></td>
                        </tr>
                        <tr>
                            <td height="22"><bean:message key="param.warning.label.mrir"/> TO</td>
                            <td height="22"><html:text property="mrirTO" size="70" name="<%=Constants.CONFIG%>"/></td>
                        </tr>
                        <tr>
                            <td height="22"><bean:message key="param.warning.label.mrir"/> CC</td>
                            <td height="22"><html:text property="mrirCC" size="70" name="<%=Constants.CONFIG%>"/></td>
                        </tr>
                        <tr>
                            <td height="22"><bean:message key="param.warning.label.msv"/> TO</td>
                            <td height="22"><html:text property="msvTO" size="70" name="<%=Constants.CONFIG%>"/></td>
                        </tr>
                        <tr>
                            <td height="22"><bean:message key="param.warning.label.msv"/> CC</td>
                            <td height="22"><html:text property="msvCC" size="70" name="<%=Constants.CONFIG%>"/></td>
                        </tr>
                        <tr>
                            <td height="22"><bean:message key="param.warning.label.repair"/> TO</td>
                            <td height="22"><html:text property="repairTO" size="70" name="<%=Constants.CONFIG%>"/></td>
                        </tr>
                        <tr>
                            <td height="22"><bean:message key="param.warning.label.repair"/> CC</td>
                            <td height="22"><html:text property="repairCC" size="70" name="<%=Constants.CONFIG%>"/></td>
                        </tr>
                        <tr>
                            <td height="22"><bean:message key="param.warning.label.audit"/> TO</td>
                            <td height="22"><html:text property="auditTO" size="70" name="<%=Constants.CONFIG%>"/></td>
                        </tr>
                        <tr>
                            <td height="22"><bean:message key="param.warning.label.audit"/> CC</td>
                            <td height="22"><html:text property="auditCC" size="70" name="<%=Constants.CONFIG%>"/></td>
                        </tr>
                        <tr>
                            <td height="22"><bean:message key="param.warning.label.matIn"/> TO</td>
                            <td height="22"><html:text property="matInTO" size="70" name="<%=Constants.CONFIG%>"/></td>
                        </tr>
                        <tr>
                            <td height="22"><bean:message key="param.warning.label.matIn"/> CC</td>
                            <td height="22"><html:text property="matInCC" size="70" name="<%=Constants.CONFIG%>"/></td>
                        </tr>
                        <tr>
                            <td height="22"><bean:message key="param.warning.label.matOut"/> TO</td>
                            <td height="22"><html:text property="matOutTO" size="70" name="<%=Constants.CONFIG%>"/></td>
                        </tr>
                        <tr>
                            <td height="22"><bean:message key="param.warning.label.matOut"/> CC</td>
                            <td height="22"><html:text property="matOutCC" size="70" name="<%=Constants.CONFIG%>"/></td>
                        </tr>
                    </table>
                    <p style="margin-top: 0; margin-bottom: 0" align="left"><html:image onclick="return saveConfig();" src="images/but_save.gif" style="cursor: hand;"/></p>
    </div></td></tr></table>
    <html:hidden property="id" name="<%=Constants.CONFIG%>"/>
</form>