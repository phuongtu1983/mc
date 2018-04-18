<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib prefix="display" uri="/tags/displaytag" %>
<%@ page import="com.venus.mc.util.MCUtil"%>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<%
            String matId = MCUtil.getParameter("matId", request);
%>

<h3><bean:message key="message.detailspe.title"/></h3>
<form name ="speForm" action="addSpeNotCode.do">
    <html:hidden property="spe" value="0" name="<%=Constants.SPE%>" />
    <table border="0" width="100%" cellspacing="0" cellpadding="0" >
        <tr><td><div align="center">
                    <table width="100%" border="0" cellspacing="0" cellpadding="0" style="border-width: 0px">
                        <tr>
                            <td>&nbsp;</td>
                        </tr>
                        <tr>
                            <td height="22"><bean:message key="message.material.spe1Id"/></td>
                            <td height="22" colspan="5">

                                <span name="spe1Span" id="spe1Span"></span>
                                <%
                                     if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_EDIT, PermissionUtil.PER_PURCHASING_REQUEST_MATERIALNOTCODE)) {
                                %>
                                <html:image src="images/add.png" title="Th&#234;m m&#7899;i" onclick="return addSpe1NotCode();"/>
                                <html:image src="images/edit.png" title="Ch&#7881;nh s&#7917;a" onclick="return editSpe1NotCode();"/>
                                <html:image src="images/del.png" title="X&#243;a" onclick="return delSpe1NotCode();"/>
                                <%}%>
                            </td>
                        </tr>
                        <tr>
                            <td height="22"><bean:message key="message.material.spe2Id"/></td>
                            <td height="22" colspan="5">

                                <span name="spe2Span" id="spe2Span"></span>

                            </td>
                        </tr>
                        <tr>
                            <td height="22"><bean:message key="message.material.spe3Id"/></td>
                            <td height="22" colspan="5">

                                <span name="spe3Span" id="spe3Span"></span>

                            </td>
                        </tr>
                        <tr>
                            <td height="22"><bean:message key="message.material.spe4Id"/></td>
                            <td height="22" colspan="5">

                                <span name="spe4Span" id="spe4Span"></span>

                            </td>
                        </tr>
                        <tr>
                            <td height="22"><bean:message key="message.material.spe5Id"/></td>
                            <td height="22" colspan="5">

                                <span name="spe5Span" id="spe5Span"></span>

                            </td>
                        </tr>
                        
                        <tr><td></td>
                            <th align="center" ><bean:message key="message.material.spe1"/></th>
                        </tr>

                        <tr>
                            <td height="22"><bean:message key="message.material.sign"/></td>
                            <td height="22" colspan="5"><html:text  disabled="true" property="sign" maxlength="3" size="5" name="<%=Constants.SPE%>" /></td>
                        </tr>
                        <tr>
                            <td height="22"><bean:message key="message.material.note"/></td>
                            <td height="22" colspan="5"><html:text  disabled="true" property="note" size="80" name="<%=Constants.SPE%>" /></td>
                        </tr>


                    </table>
                    <%
            if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_EDIT, PermissionUtil.PER_PURCHASING_REQUEST_MATERIALNOTCODE)) {
                    %>
                    <html:image onclick="return addSpe0NotCode();" src="images/but_save.gif" style="cursor: hand;"/><img border="0" src="images/blank.gif" width="2" height="17">
                    <%}%>
                    <html:image property="Back" value="Back" src="images/but_back.gif" onclick="return loadMaterialListNotCode();"/>
                </div></td></tr></table>
    <input type="hidden" name="speId" value =""/>
</form>
