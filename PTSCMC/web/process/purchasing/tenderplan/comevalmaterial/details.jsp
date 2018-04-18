<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>
<table style="width:100%" class="its" id="comEvalDetailTable">
    <thead>
        <tr>
            <th><bean:message key="message.comevalmaterial.stt"/></th>
            <th><bean:message key="message.comevalmaterial.materialName"/></th>
            <th><bean:message key="message.comevalmaterial.unit"/></th>
            <th><bean:message key="message.comevalmaterial.quantity"/></th>
            <th><bean:message key="message.comevalmaterial.price"/></th>
            <th><bean:message key="message.comevalmaterial.priceCustom"/></th>
            <th><bean:message key="message.comevalmaterial.priceTransport"/></th>
            <th><bean:message key="message.comevalmaterial.priceTax"/></th>
            <th><bean:message key="message.comevalmaterial.priceCertificate"/></th>
            <th><bean:message key="message.comevalmaterial.priceToPort"/></th>
            <th><bean:message key="message.comevalmaterial.priceOtherCost"/></th>
            <th><bean:message key="message.comevalmaterial.pricePtscmc"/></th>
            <th><bean:message key="message.comevalmaterial.priceAfter"/></th>
            <th><bean:message key="message.comevalmaterial.total"/></th>
            <th><bean:message key="message.status"/></th>
        </tr>
    </thead>
    <tbody>
        <logic:iterate id="detail" name="<%=Constants.COM_EVAL_DETAIL_LIST%>">
            <logic:equal value="1" name="detail" property="evalTbe" >
                <tr>
                    <td><p align="center"><bean:write name="detail" property="stt"/></p><input type="hidden" name="comDetId" value="${detail.detId}"/><input type="hidden" name="detIdTp" value="${detail.detIdTp}"/></td>
                    <td style="width:12px"><p align="left"><bean:write name="detail" property="nameVn"/></p><input type="hidden" name="matId" value="${detail.matId}"/></td>
                    <td><p align="center"><bean:write name="detail" property="unit"/></p><input type="hidden" name="unit" value="${detail.unit}"/></td>
                    <td><p align="right"><bean:write name="detail" property="quantity"/></p><input type="hidden" id="9${detail.detId}" name="quantity" value="${detail.quantity}"/></td>
                    <td><p align="center"><html:text style="border-width:1px;text-align:right" styleId="1${detail.detId}" onblur="tryNumberFormat(this);caculateComEvalMaterialDetail(${detail.detId});"  size="10" name="detail" property="price" /></p></td>
                    <td><p align="center"><html:text style="border-width:1px;text-align:right"  styleId="2${detail.detId}"  onblur="tryNumberFormat(this);caculateComEvalMaterialDetail(${detail.detId});"  size="10" name="detail" property="priceCustom" /></p></td>
                    <td><p align="center"><html:text style="border-width:1px;text-align:right"  styleId="3${detail.detId}"  onblur="tryNumberFormat(this);caculateComEvalMaterialDetail(${detail.detId});"  size="13" name="detail" property="priceTransport" /></p></td>
                    <td><p align="center"><html:text style="border-width:1px;text-align:right"  styleId="4${detail.detId}"  onblur="tryNumberFormat(this);caculateComEvalMaterialDetail(${detail.detId});"  size="10" name="detail" property="priceTax" /></p></td>
                    <td><p align="center"><html:text style="border-width:1px;text-align:right"  styleId="11${detail.detId}"  onblur="tryNumberFormat(this);caculateComEvalMaterialDetail(${detail.detId});"  size="10" name="detail" property="priceCertificate" /></p></td>
                    <td><p align="center"><html:text style="border-width:1px;text-align:right"  styleId="12${detail.detId}"  onblur="tryNumberFormat(this);caculateComEvalMaterialDetail(${detail.detId});"  size="10" name="detail" property="priceToPort" /></p></td>
                    <td><p align="center"><html:text style="border-width:1px;text-align:right"  styleId="5${detail.detId}"  onblur="tryNumberFormat(this);caculateComEvalMaterialDetail(${detail.detId});"  size="10" name="detail" property="priceOtherCost" /></p></td>
                    <td><p align="center"><html:text style="border-width:1px;text-align:right"  styleId="6${detail.detId}"  onblur="tryNumberFormat(this);caculateComEvalMaterialDetail(${detail.detId});"  size="10" name="detail" property="pricePtscmc" /></p></td>
                    <td><p align="center"><html:text style="border-width:1px;text-align:right"  styleId="8${detail.detId}"  onblur="caculateComEvalMaterialDetail(${detail.detId});" size="10" name="detail" property="priceAfter" /></p></td>
                    <td><p align="center"><html:text style="border-width:1px;text-align:right"  styleId="7${detail.detId}"  size="10" name="detail" property="total" /></p></td>
                    <td><p align="right"><bean:write name="detail" property="status"/></p><input type="hidden" name="status" value="${detail.status}"/></td>
                </tr>
            </logic:equal>
            <logic:equal value="2" name="detail" property="evalTbe" >
                <tr>
                    <td><p align="center"><bean:write name="detail" property="stt"/></p><input type="hidden" name="comDetId" value="${detail.detId}"/><input type="hidden" name="detIdTp" value="${detail.detIdTp}"/></td>
                    <td style="width:12px"><p align="left"><bean:write name="detail" property="nameVn"/></p><input type="hidden" name="matId" value="${detail.matId}"/></td>
                    <td><p align="center"><bean:write name="detail" property="unit"/></p><input type="hidden" name="unit" value="${detail.unit}"/></td>
                    <td><p align="right"><bean:write name="detail" property="quantity"/></p><input type="hidden" id="9${detail.detId}" name="quantity" value="${detail.quantity}"/></td>
                    <td><p align="right"><bean:write name="detail" property="price"/></p><input type="hidden" name="price" value="${detail.price}"/></td>
                    <td><p align="right"><bean:write name="detail" property="priceCustom"/></p><input type="hidden" name="priceCustom" value="${detail.priceCustom}"/></td>
                    <td><p align="right"><bean:write name="detail" property="priceTransport"/></p><input type="hidden" name="priceTransport" value="${detail.priceTransport}"/></td>
                    <td><p align="right"><bean:write name="detail" property="priceTax"/></p><input type="hidden" name="priceTax" value="${detail.priceTax}"/></td>
                    <td><p align="right"><bean:write name="detail" property="priceCertificate"/></p><input type="hidden" name="priceCertificate" value="${detail.priceCertificate}"/></td>
                    <td><p align="right"><bean:write name="detail" property="priceToPort"/></p><input type="hidden" name="priceToPort" value="${detail.priceToPort}"/></td>
                    <td><p align="right"><bean:write name="detail" property="priceOtherCost"/></p><input type="hidden" name="priceOtherCost" value="${detail.priceOtherCost}"/></td>
                    <td><p align="right"><bean:write name="detail" property="pricePtscmc"/></p><input type="hidden" name="pricePtscmc" value="${detail.pricePtscmc}"/></td>
                    <td><p align="right"><bean:write name="detail" property="priceAfter"/></p><input type="hidden" name="priceAfter" value="${detail.priceAfter}"/></td>
                    <td><p align="right"><bean:write name="detail" property="total"/></p><input type="hidden" name="total" value="${detail.total}"/></td>
                    <td><p align="right"><bean:write name="detail" property="status"/></p><input type="hidden" name="status" value="${detail.status}"/></td>
                </tr>
            </logic:equal>
            <logic:equal value="3" name="detail" property="evalTbe" >
                <tr>
                    <td><p align="center"><bean:write name="detail" property="stt"/></p><input type="hidden" name="comDetId" value="${detail.detId}"/><input type="hidden" name="detIdTp" value="${detail.detIdTp}"/></td>
                    <td style="width:12px"><p align="left"><bean:write name="detail" property="nameVn"/></p><input type="hidden" name="matId" value="${detail.matId}"/></td>
                    <td><p align="center"><bean:write name="detail" property="unit"/></p><input type="hidden" name="unit" value="${detail.unit}"/></td>
                    <td><p align="right"><bean:write name="detail" property="quantity"/></p><input type="hidden" id="9${detail.detId}" name="quantity" value="${detail.quantity}"/></td>
                    <td><p align="right"><bean:write name="detail" property="price"/></p><input type="hidden" name="price" value="${detail.price}"/></td>
                    <td><p align="right"><bean:write name="detail" property="priceCustom"/></p><input type="hidden" name="priceCustom" value="${detail.priceCustom}"/></td>
                    <td><p align="right"><bean:write name="detail" property="priceTransport"/></p><input type="hidden" name="priceTransport" value="${detail.priceTransport}"/></td>
                    <td><p align="right"><bean:write name="detail" property="priceTax"/></p><input type="hidden" name="priceTax" value="${detail.priceTax}"/></td>
                    <td><p align="right"><bean:write name="detail" property="priceCertificate"/></p><input type="hidden" name="priceCertificate" value="${detail.priceCertificate}"/></td>
                    <td><p align="right"><bean:write name="detail" property="priceToPort"/></p><input type="hidden" name="priceToPort" value="${detail.priceToPort}"/></td>
                    <td><p align="right"><bean:write name="detail" property="priceOtherCost"/></p><input type="hidden" name="priceOtherCost" value="${detail.priceOtherCost}"/></td>
                    <td><p align="right"><bean:write name="detail" property="pricePtscmc"/></p><input type="hidden" name="pricePtscmc" value="${detail.pricePtscmc}"/></td>
                    <td><p align="right"><bean:write name="detail" property="priceAfter"/></p><input type="hidden" name="priceAfter" value="${detail.priceAfter}"/></td>
                    <td><p align="right"><bean:write name="detail" property="total"/></p><input type="hidden" name="total" value="${detail.total}"/></td>
                    <td><p align="right"><bean:write name="detail" property="status"/></p><input type="hidden" name="status" value="${detail.status}"/></td>
                </tr>
            </logic:equal>
        </logic:iterate>
    </tbody>
</table>