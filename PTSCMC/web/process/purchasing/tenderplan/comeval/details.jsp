<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>
<table style="width:100%" class="its" id="comEvalDetailTable">
    <thead>
        <tr>
            <th><bean:message key="message.comeval.stt"/></th>
            <th><bean:message key="message.comeval.nameVn"/></th>
            <th><bean:message key="message.comeval.unit"/></th>
            <th><bean:message key="message.comeval.quantity"/></th>
            <th><bean:message key="message.comeval.price"/></th>
            <th><bean:message key="message.comevalmaterial.priceAfter"/></th>
            <th><bean:message key="message.comeval.total"/></th>
        </tr>
    </thead>
    <tbody>
        <logic:iterate id="detail" name="<%=Constants.COM_EVAL_DETAIL_LIST%>">
            <tr>
                <td><p align="center"><bean:write name="detail" property="stt"/></p><input type="hidden" name="comDetId" value="${detail.detId}"/></td>
                <td><p align="left"><bean:write name="detail" property="nameVn"/></p><input type="hidden" name="matId" value="${detail.matId}"/></td>
                <td><p align="center"><bean:write name="detail" property="unit"/></p><input type="hidden" name="unit" value="${detail.unit}"/></td>
                <td><p align="center"><bean:write name="detail" property="quantity"/></p><input type="hidden" id="detQuantity${detail.detId}" name="quantity" value="${detail.quantity}"/></td>
                <td width="80px"><p align="center"><html:text style="border-width:1px;text-align:right"  onblur="caculateComEvalDetail(${detail.detId});tryNumberFormat(this);"  styleId="detPrice${detail.detId}" size="10" name="detail" property="price" /></p></td>
                <td><p align="center"><html:text style="border-width:1px;text-align:right"  styleId="priceAfter${detail.detId}"  onblur="tryNumberFormat(this);caculateComEvalDetail(${detail.detId});"  size="10" name="detail" property="priceAfter" /></p></td>
                <td width="80px"><p align="center"><html:text style="border-width:1px;text-align:right"  onblur="tryNumberFormat(this);caculateComEvalDetail(${detail.detId});" styleId="detTotal${detail.detId}" size="10" name="detail" property="total" /></p></td>
            </tr>
        </logic:iterate>
    </tbody>
</table>