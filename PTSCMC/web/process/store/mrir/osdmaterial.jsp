<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>
<!--div id="osDMaterialTitle"><h3><bean:message key="message.osddetail.title"/></h3></div-->
<div id="osDMaterialErrorMessageDiv"></div>
<form name="osDMaterialForm">
    <table>
        <tr>
            <td><bean:message key="message.osddetail.matId"/></td>
            <td colspan="3"><html:text styleClass="lbl10" size="80" readonly="true" name="<%=Constants.OSD_MATERIAL%>" property="matName" /></td>
        </tr>
        <tr>
            <td>Item No.</td>
            <td><html:text styleClass="lbl10" size="20" name="<%=Constants.OSD_MATERIAL%>" property="itemNo"/></td>
            <td>Discipline</td>
            <td><html:text styleClass="lbl10" size="20" name="<%=Constants.OSD_MATERIAL%>" property="discipline"/></td>
        </tr>
        <tr>
            <td><bean:message key="message.osddetail.quantity"/></td>
            <td><html:text styleClass="lbl10" size="20" name="<%=Constants.OSD_MATERIAL%>" property="quantity"/></td>
            <td><bean:message key="message.osddetail.unit"/></td>
            <td><html:text styleClass="lbl10" size="20" readonly="true" name="<%=Constants.OSD_MATERIAL%>" property="unit"/></td>
        </tr>
        <tr>
            <td class="lbl10"><bean:message key="message.osd.closedDate"/></td>
            <td><html:text readonly="true" property="closedDate" styleId="closedDate1" size="20" onclick="javascript: showCalendar('closedDate1')" name="<%=Constants.OSD_MATERIAL%>"/></td>
            <td class="lbl10"><bean:message key="message.osd.status"/></td>
            <td><html:select property="status" name="<%=Constants.OSD_MATERIAL%>">
                    <html:option value="0">Open</html:option>
                    <html:option value="1">Close</html:option>                
                </html:select>
            </td>            
        </tr>
        <tr>
            <td colspan="4">
                <fieldset>
                    <legend class="lbl10b" ><bean:message key="message.osddetail.nonConform"/></legend>
                    <p><table width="100%">
                            <tr>
                                <td class="lbl10"><html:multibox property="nonConform" name="<%=Constants.OSD_MATERIAL%>" value="1"></html:multibox> <bean:message key="message.osd.nonConform1"/></td>
                                <td class="lbl10"><html:multibox property="nonConform" name="<%=Constants.OSD_MATERIAL%>" value="2"></html:multibox> <bean:message key="message.osd.nonConform2"/></td>
                                <td class="lbl10"><html:multibox property="nonConform" name="<%=Constants.OSD_MATERIAL%>" value="4"></html:multibox> <bean:message key="message.osd.nonConform4"/></td>
                                <td class="lbl10"><html:multibox property="nonConform" name="<%=Constants.OSD_MATERIAL%>" value="8"></html:multibox> <bean:message key="message.osd.nonConform8"/></td>
                            </tr>
                        </table>
                    </p>
                </fieldset>
            </td>
        </tr>
        <tr><td colspan="4" class="lbl10"><bean:message key="message.osddetail.description"/></td></tr>
        <tr><td colspan="4"><html:textarea rows="3" cols="100" property="description" name="<%=Constants.OSD_MATERIAL%>"/></td></tr>
        <tr><td colspan="4"><bean:message key="message.osddetail.correctAct"/></td></tr>
        <tr><td colspan="4"><html:textarea rows="3" cols="100" property="correctAct" name="<%=Constants.OSD_MATERIAL%>"/></td></tr>
        <tr><td colspan="4"><bean:message key="message.osddetail.vendorexplain"/></td></tr>
        <tr><td colspan="4"><html:textarea rows="3" cols="100" property="vendorNote" name="<%=Constants.OSD_MATERIAL%>"/></td></tr>
        <tr><td colspan="4"><bean:message key="message.osddetail.closedout"/></td></tr>
        <tr><td colspan="4"><html:textarea rows="3" cols="100" property="closedOut" name="<%=Constants.OSD_MATERIAL%>"/></td></tr>        
        <tr><td colspan="4"><bean:message key="message.osddetail.remark"/></td></tr>
        <tr><td colspan="4"><html:textarea rows="3" cols="100" property="remark" name="<%=Constants.OSD_MATERIAL%>"/></td></tr>
    </table>
    <html:image onclick="return saveOsDMaterial();" src="images/but_save.gif"/><img src="images/blank.gif" width="2" height="17"><html:image property="Back" value="Back" src="images/but_back.gif" onclick="return hidePopupForm();"/>
    <html:hidden name="<%=Constants.OSD_MATERIAL%>" property="detId"/>
    <html:hidden name="<%=Constants.OSD_MATERIAL%>" property="osdId"/>
</form>