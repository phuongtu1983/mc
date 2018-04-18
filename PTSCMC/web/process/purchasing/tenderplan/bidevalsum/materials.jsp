<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ page import ="com.venus.core.util.*"%>

<table style="width:100%" class="its" id="bidEvalSumMaterialList">
    <thead>
        <tr>
            <th><html:image src="images/arrow2.gif" onclick="return checkAllBidEvalSumDetail('bidEvalSumForm','detId');"/></th>
            <th><bean:message key="message.techeval.stt"/></th>
            <th><bean:message key="message.techeval.matId"/></th>
            <th><bean:message key="message.techeval.unit"/></th>
            <th><bean:message key="message.techeval.spec"/></th>
            <th><bean:message key="message.techeval.unit"/></th>
            <%--<th><bean:message key="message.confirm"/></th> --%>        
        </tr>
    </thead>
    <tbody>
        <logic:iterate id="detail" name="<%=Constants.MATERIAL_LIST%>">
            <tr>
                <td>
                    <logic:equal value="0" name="detail" property="cb">
                        <input type="checkbox" name="detId" value="${detail.detId}">
                    </logic:equal>
                    <logic:greaterThan value="0" name="detail" property="cb">
                        <input type="checkbox" disabled name="detId" value="${detail.detId}">
                    </logic:greaterThan>
                </td>
                <td><p align="center"><bean:write name="detail" property="stt"/></p>
                    <input type="hidden" name="detIdTp" value="${detail.detIdTp}"/>
                    <input type="hidden" id="bidEvalSumTempName${detail.detId}" value="${detail.nameTemp}"/>

                </td>
                <td width="180px"><p align="left"><bean:write name="detail" property="nameVn"/></p></td>
                <td width="80px"><p align="left"><bean:write name="detail" property="unit"/></p></td>
                <td width="80px"><p align="center"><html:textarea style="border-width:1px;text-align:left"  cols="40" rows="3" name="detail" property="spec" /></p></td>
                <td width="80px"><p align="left"><bean:write name="detail" property="unitTemp"/></p></td>              
                <%--<td width="80px"><html:select name="detail" property="confirm" style="border-width:1px;text-align:left" >
                        <html:options collection="<%=Constants.EVAL_TBE_LIST%>" property="value" labelProperty="label"/> </html:select></td>--%>
                <logic:equal value="0" name="detail" property="cb">
                    <td>
                        <img src="images/ico_them.gif" onclick="return selectMaterialTechEval('setSelectedMaterialTechEval','',${detail.detId},${detail.detId});"/>
                        <img src="images/ico_themvt.gif" onclick="return newMaterialTechEval('setSelectedMaterialTechEval',null,'<bean:message key="message.techeval"/>/<bean:message key="message.material.add"/>',${detail.detId},document.forms['bidEvalSumForm'].tenId.value,${detail.detId},${detail.detIdTp});"/>
                    </td>
                </logic:equal>
            </tr>
        </logic:iterate>
    </tbody>
</table>