<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<form id='searchMaterialForm' name='searchMaterialForm'>
    <table border="0" width="100%" cellspacing="0" cellpadding="0" >
        <tr><td><div align="center">
                    <table width="100%" border="0" cellspacing="0" cellpadding="0" style="border-width: 0px">
                        <tr>
                            <td>&nbsp;</td>
                        </tr>
                        <tr>
                            <td height="22"><bean:message key="message.material.code"/></td>
                            <td height="22" colspan="5"><html:text property="code" size="80" name="<%=Constants.MATERIAL%>"/></td>
                        </tr>                                                
                        <tr>
                            <td height="22"><bean:message key="message.material.nameVn"/></td>
                            <td height="22" colspan="5"><html:textarea rows="7" cols="80" styleId="nameVn" property="nameVn" name="<%=Constants.MATERIAL%>" /></td>
                        </tr>
                        <tr>
                            <td height="22"><bean:message key="message.material.nameEn"/></td>
                            <td height="22" colspan="5"><html:textarea rows="7" cols="80" styleId="nameEn"  property="nameEn" name="<%=Constants.MATERIAL%>" /></td>
                        </tr>
                        <tr>
                            <td height="22"><bean:message key="message.material.note"/></td>
                            <td height="22" colspan="5"><html:textarea rows="7" cols="80"  property="note" name="<%=Constants.MATERIAL%>" /></td>
                        </tr>
                        <tr>
                            <td height="22"><bean:message key="message.material.qc"/></td>
                             <td colspan="3"><html:textarea rows="3" cols="80"  property="qc" name="<%=Constants.MATERIAL%>" /></td>
                        </tr>
                        <tr>
                            <td height="22"><bean:message key="message.material.uniId"/></td>
                            <td height="22" colspan="5">

                                <html:select  property="uniId" onchange="document.forms[0].uniIdEn.options.selectedIndex=this.options.selectedIndex" name="<%=Constants.MATERIAL%>">
                                    <html:options collection="<%=Constants.UNIT_LIST%>" property="value" labelProperty="label"/> </html:select>

                                </td>
                            </tr>
                            <tr>
                                <td height="22"><bean:message key="message.material.uniIdEn"/></td>
                            <td height="22" colspan="5">

                                <html:select  property="uniIdEn" onchange="document.forms[0].uniId.options.selectedIndex=this.options.selectedIndex" name="<%=Constants.MATERIAL%>">
                                    <html:options collection="<%=Constants.UNIT_LISTEN%>" property="value" labelProperty="label"/> </html:select>

                                </td>
                            </tr>
                            <tr>
                                <td height="22"><bean:message key="message.material.oriId"/></td>
                            <td height="22" colspan="5">

                                <html:select  property="oriId" onchange="document.forms[0].oriIdEn.options.selectedIndex=this.options.selectedIndex" name="<%=Constants.MATERIAL%>">
                                    <html:options collection="<%=Constants.ORIGIN_LIST%>" property="value" labelProperty="label"/> </html:select>

                                </td>
                            </tr>
                            <tr>
                                <td height="22"><bean:message key="message.material.oriIdEn"/></td>
                            <td height="22" colspan="5">

                                <html:select  property="oriIdEn" onchange="document.forms[0].oriId.options.selectedIndex=this.options.selectedIndex" name="<%=Constants.MATERIAL%>">
                                    <html:options collection="<%=Constants.ORIGIN_LISTEN%>" property="value" labelProperty="label"/> </html:select>

                                </td>
                            </tr>
                            <tr>
                                <td height="22"><bean:message key="message.material.type"/></td>
                            <td height="22" colspan="5">
                                <html:select  property="type" name="<%=Constants.MATERIAL%>">
                                    <html:options collection="<%=Constants.TYPE_LIST%>" property="value" labelProperty="label"/> </html:select>
                                </td>
                            </tr>
                            <tr>
                                <td height="22"><bean:message key="message.material.kind"/></td>
                            <td height="22" colspan="5">

                                <html:select  property="kind" name="<%=Constants.MATERIAL%>">
                                    <html:options collection="<%=Constants.KIND_LIST%>" property="value" labelProperty="label"/> </html:select>

                                </td>
                            </tr>
                        </table>
                    </div></td></tr></table>
                <html:image property="Submit" value="Submit" src="images/but_find.gif" style="cursor: hand;" onclick="return searchAdvMaterial();"/>
</form>