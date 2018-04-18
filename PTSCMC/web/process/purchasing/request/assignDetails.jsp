<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<table width="100%" class="its" style="width:100%;" id="requestDetailTable">
    <thead>
        <tr>
            <th><bean:message key="message.del"/></th>
            <th><bean:message key="message.rowNum"/></th>
            <th><bean:message key="message.request.material.code"/></th>
            <th><bean:message key="message.request.material.name"/></th>
            <th><bean:message key="message.request.material.unit"/></th>
            <th><bean:message key="message.request.material.presentQuantity"/></th>
            <th style="display:none"><bean:message key="message.request.material.additionalQuantity"/></th>
            <th>
                <bean:message key="message.request.material.additionalQuantity"/>
                <html:image src="images/arrow2.gif" onclick="return duplicateObject('requestForm','requestQuantity');"/>
            </th>
            <th>
                <bean:message key="message.request.material.provideDate"/>
                <html:image src="images/arrow2.gif" onclick="return duplicateObject('requestForm','deliverDate');"/>
            </th>
            <th>
                <bean:message key="message.request.CTCV.handler"/>
                <html:image src="images/arrow2.gif" onclick="return duplicateObject('requestForm','empId');"/>
            </th>
            <!--<th><bean:message key="message.request.materialKind"/></th>-->
        </tr>
    </thead>
    <tbody>
        <logic:iterate id="detail" name="<%=Constants.REQUEST_DETAIL_LIST%>">
            <tr>
                <td width="30px">
                    <div align="center"><input type="checkbox" name="detId" value="<bean:write name="detail" property="detId"/>"/></div>
                    <input type="hidden" name="reqDetId" value="<bean:write name="detail" property="detId"/>"/>
                    <html:hidden name="detail" property="matId"/>
                </td>
                <td width="10px"><bean:write name="detail" property="no"/></td>
                <td width="50px" class="lbl10"><span id="code_${detail.matId}"><bean:write name="detail" property="matCode"/></span></td>
                <td width="230px" class="lbl10"><span>
                        <logic:equal name="detail" value="0" property="canChangeMaterial">
                            <logic:notEqual name="detail" value="0" property="isAssigned">
                                <logic:equal name="detail" value="0" property="isCancel">
                                    <a id="name_${detail.matId}" href="#" onclick="changeMaterial(<bean:write name="detail" property="detId"/>,'<bean:message key="message.request"/>/<bean:message key="message.material.edit"/>');"><bean:write name="detail" property="matName"/></a>
                                </logic:equal>
                                <logic:notEqual name="detail" value="0" property="isCancel">
                                    <a id="name_${detail.matId}" href="#" onclick="changeMaterial(<bean:write name="detail" property="detId"/>,'<bean:message key="message.request"/>/<bean:message key="message.material.edit"/>');"><font color="red"><bean:write name="detail" property="matName"/></font></a>
                                    </logic:notEqual>
                                </logic:notEqual>
                                <logic:equal name="detail" value="0" property="isAssigned">
                                    <logic:equal name="detail" value="0" property="isCancel">
                                        <bean:write name="detail" property="matName"/>
                                    </logic:equal>
                                    <logic:notEqual name="detail" value="0" property="isCancel">
                                    <font color="red"><bean:write name="detail" property="matName"/></font>
                                </logic:notEqual>
                            </logic:equal>
                        </logic:equal>
                        <logic:notEqual name="detail" value="0" property="canChangeMaterial">
                            <a id="name_${detail.matId}" href="#" onclick="changeMaterial(<bean:write name="detail" property="detId"/>,'<bean:message key="message.request"/>/<bean:message key="message.material.edit"/>')"><bean:write name="detail" property="matName"/></a>
                        </logic:notEqual>
                    </span></td>
                <td width="50px" class="lbl10">
                    <html:text readonly="true" styleClass="lbl10" style="border-width:1px;text-align:right" size="4" name="detail" property="unit"/>
                </td>
                <td width="60px" class="lbl10" style="text-align:right;">
                    <!--<span><bean:write name="detail" property="presentQuantity"/></span>
                    <html:hidden name="detail" property="presentQuantity"/>-->
                    <html:text styleClass="lbl10" style="border-width:1px;text-align:right" size="4" name="detail" property="presentQuantity"  onblur="return tryNumberFormat(this)" />
                </td>
                <td style="display:none"><html:text styleClass="lbl10" style="border-width:1px;text-align:right" size="4" name="detail" property="additionalQuantity"  onblur="return tryNumberFormat(this)" /></td>
                <td width="50px"><html:text styleClass="lbl10" style="border-width:1px;text-align:right" size="4" name="detail" property="requestQuantity"  onblur="return tryNumberFormat(this)" /></td>
                <td width="80px"><input class="lbl10" type="textbox" style="border-width:1px;text-align:right" size="9" name="deliverDate" id="deliverDate${detail.matId}" onclick="javascript:showCalendar('deliverDate${detail.matId}')" value="${detail.provideDate}"/></td>
                <!--<td>
                <html:select property="materialKind" name="detail">
                    <html:options collection="<%=Constants.REQUEST_MATERIAL_KIND_LIST%>" property="value" labelProperty="label"/>
                </html:select>
            </td>-->
                <td>
                    <logic:equal name="detail" value="0" property="isOtherDepartment">
                        <html:select property="empId" name="detail" style="width:150px">
                            <html:options collection="<%=Constants.EMPLOYEE_LIST%>" property="empId" labelProperty="fullname"/>
                        </html:select>
                    </logic:equal>
                    <logic:notEqual name="detail" value="0" property="isOtherDepartment">
                        <select name="empId" style="width:150px">
                            <option value="${detail.empId}">${detail.employee}</option>
                        </select>
                    </logic:notEqual>
                </td>
            </tr>
        </logic:iterate>
    <tbody>
</table>
