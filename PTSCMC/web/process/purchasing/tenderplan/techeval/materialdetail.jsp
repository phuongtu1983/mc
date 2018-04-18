<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib prefix="display" uri="/tags/displaytag" %>
<%@ page import="com.venus.mc.util.Constants"%>
<%@page import="com.venus.core.util.NumberUtil"%>
<%
            int detId = (int) NumberUtil.parseInt(request.getAttribute("detId").toString(), 0);
            int detIdTp = (int) NumberUtil.parseInt(request.getAttribute("detIdTp").toString(), 0);
            int tenId = (int) NumberUtil.parseInt(request.getAttribute("tenId").toString(), 0);
%>
<h3><bean:message key="message.detailmaterial.title"/></h3>
<div id="errorMaterialMessageDiv"></div>
<form name="addMaterialTechEval">
    <table border="0" width="100%" cellspacing="0" cellpadding="0" >
        <tr><td><div align="center">
                    <table width="100%" border="0" cellspacing="0" cellpadding="0" style="border-width: 0px">
                        <tr>
                            <td height="22"><bean:message key="message.material.nameVn"/></td>
                            <td height="22" colspan="5"><html:textarea rows="7" cols="80"  styleId="nameVn" property="nameVn" name="<%=Constants.MATERIAL%>" /></td>
                        </tr>
                        <tr>
                            <td height="22"><bean:message key="message.material.nameEn"/></td>
                            <td height="22" colspan="5"><html:textarea rows="7" cols="80"  styleId="nameEn"  property="nameEn" name="<%=Constants.MATERIAL%>" /></td>
                        </tr>
                        <tr>
                            <td height="22"><bean:message key="message.material.note"/></td>
                            <td height="22" colspan="5"><html:textarea rows="3" cols="80"   property="note" name="<%=Constants.MATERIAL%>" /></td>
                        </tr>
                        <tr>
                            <td height="22"><bean:message key="message.material.uniId"/></td>
                            <td height="22" colspan="5">
                                <html:select  property="uniId" onchange="document.forms['addMaterialTechEval'].uniIdEn.options.selectedIndex=this.options.selectedIndex" name="<%=Constants.MATERIAL%>">
                                <html:options collection="<%=Constants.UNIT_LIST%>" property="value" labelProperty="label"/> </html:select>
                            </td>
                        </tr>
                        <tr>
                            <td height="22"><bean:message key="message.material.uniIdEn"/></td>
                            <td height="22" colspan="5">
                                <html:select  property="uniIdEn" onchange="document.forms['addMaterialTechEval'].uniId.options.selectedIndex=this.options.selectedIndex" name="<%=Constants.MATERIAL%>">
                                <html:options collection="<%=Constants.UNIT_LISTEN%>" property="value" labelProperty="label"/> </html:select>
                            </td>
                        </tr>
                    </table>
                    <p style="margin-top: 0; margin-bottom: 0" align="left">
                    <html:image onclick="return saveMaterialTechEval(document.forms['addMaterialTechEval'].detId.value,document.forms['addMaterialTechEval'].tenId.value);" src="images/but_save.gif" style="cursor: hand;"/><img border="0" src="images/blank.gif" width="2" height="17">
    </div></td></tr></table>
    <input type="hidden" name="detId" value ="<%=detId%>"/>
    <input type="hidden" name="detIdTp" value ="<%=detIdTp%>"/>
    <input type="hidden" name="tenId" value ="<%=tenId%>"/>
</form>
<input type="hidden" id="callbackFunc"/>