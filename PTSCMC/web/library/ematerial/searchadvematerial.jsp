<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<form id='searchEmaterialForm' name='searchEmaterialForm'>
    <table border="0" width="100%" cellspacing="0" cellpadding="0" >
        <tr><td><div align="center">
                    <table width="100%" border="0" cellspacing="0" cellpadding="0" style="border-width: 0px">
                        <tr>
                            <td>&nbsp;</td>
                        </tr>
                        <tr>
                            <td height="22"><bean:message key="message.ematerial.code"/></td>
                            <td height="22" colspan="5"><html:text property="code" size="80" name="<%=Constants.MATERIAL%>"/></td>
                        </tr>                                                
                        <tr>
                            <td height="22"><bean:message key="message.ematerial.nameVn"/></td>
                            <td height="22" colspan="5"><html:text styleId="nameVn" property="nameVn" size="80" name="<%=Constants.MATERIAL%>" /></td>
                        </tr>
                        <tr>
                            <td height="22"><bean:message key="message.ematerial.nameEn"/></td>
                            <td height="22" colspan="5"><html:text styleId="nameEn"  property="nameEn" size="80" name="<%=Constants.MATERIAL%>" /></td>
                        </tr>
                        <tr>
                            <td height="22"><bean:message key="message.ematerial.note"/></td>
                            <td height="22" colspan="5"><html:text  property="note" size="80" name="<%=Constants.MATERIAL%>" /></td>
                        </tr>
                        <tr>
                            <td height="22"><bean:message key="message.material.qc"/></td>
                            <td height="22" colspan="5"><html:text  property="qc" size="80" name="<%=Constants.MATERIAL%>" /></td>
                        </tr>
                        <tr>
                            <td height="22"><bean:message key="message.ematerial.uniId"/></td>
                            <td height="22" colspan="5">

                                <html:select  property="uniId" onchange="document.forms[0].uniIdEn.options.selectedIndex=this.options.selectedIndex" name="<%=Constants.MATERIAL%>">
                                    <html:options collection="<%=Constants.UNIT_LIST%>" property="value" labelProperty="label"/> </html:select>

                                </td>
                            </tr>
                            <tr>
                                <td height="22"><bean:message key="message.ematerial.uniIdEn"/></td>
                            <td height="22" colspan="5">

                                <html:select  property="uniIdEn" onchange="document.forms[0].uniId.options.selectedIndex=this.options.selectedIndex" name="<%=Constants.MATERIAL%>">
                                    <html:options collection="<%=Constants.UNIT_LISTEN%>" property="value" labelProperty="label"/> </html:select>

                                </td>
                            </tr>
                            <tr>
                                <td height="22"><bean:message key="message.ematerial.oriId"/></td>
                            <td height="22" colspan="5">

                                <html:select  property="oriId" onchange="document.forms[0].oriIdEn.options.selectedIndex=this.options.selectedIndex" name="<%=Constants.MATERIAL%>">
                                    <html:options collection="<%=Constants.ORIGIN_LIST%>" property="value" labelProperty="label"/> </html:select>

                                </td>
                            </tr>
                            <tr>
                                <td height="22"><bean:message key="message.ematerial.oriIdEn"/></td>
                            <td height="22" colspan="5">

                                <html:select  property="oriIdEn" onchange="document.forms[0].oriId.options.selectedIndex=this.options.selectedIndex" name="<%=Constants.MATERIAL%>">
                                    <html:options collection="<%=Constants.ORIGIN_LISTEN%>" property="value" labelProperty="label"/> </html:select>

                                </td>
                            </tr>
                            <tr>
                                <td height="22"><bean:message key="message.ematerial.kind"/></td>
                            <td height="22" colspan="5">

                                <html:select  property="kind" name="<%=Constants.MATERIAL%>">
                                    <html:options collection="<%=Constants.KIND_LIST%>" property="value" labelProperty="label"/> </html:select>

                                </td>
                            </tr>
                        </table>
                    </div></td></tr></table>
                <html:image property="Submit" value="Submit" src="images/but_find.gif" style="cursor: hand;" onclick="return searchAdvEmaterial();"/>
</form>