<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.bean.ContractBean"%>
<%@ page import="com.venus.mc.util.Constants"%>

<h3><bean:message key="message.rfm.store.title0"/></h3>
<table width="100%">
    <tr><td>
            <fieldset>
                <legend><font face='Tahoma' style='font-size: 9pt;' color='#000080'><img id='selectedMaterial_image' border='0' src='images/icon_minus.gif' width='9' height='9' onclick="expandDiv(this,'materialRfmRequestSelectedDiv');">&nbsp;<bean:message key='message.request.material.selected'/></font></legend>
                <div id='materialRfmRequestSelectedDiv'>
                    <input type="button" onclick="return delRfmMaterialInstore();" value="<bean:message key="message.del"/>"/>&nbsp;<input type="button" onclick="return createRfm0();" value="<bean:message key="message.rfm.store.create0"/>"/>
                    <input type="hidden" name="kind" value="0">
                    <form name="materialRfmRequestSelectedForm">
                        <table id="materialRequestSelectedTbl" class="its" style="width:100%">
                            <thead>
                                <tr>
                                    <th><bean:message key="message.choose"/></th>
                                    <th><bean:message key="message.material.code"/></th>
                                    <th><bean:message key="message.material.nameVn"/></th>
                                    <th><bean:message key="message.material.uniId"/></th>
                                    <th><bean:message key="message.rfm.store.reserveQuantity"/></th>
                                    <th><bean:message key="message.rfm.store.availableQuantity"/></th>
                                    <th><bean:message key="message.rfm.store.actualQuantity"/></th>
                                    <th><bean:message key="message.rfm.requestNumber"/></th>
                                    <th><bean:message key="message.rfm.storeName"/></th>
                                </tr>
                            </thead>
                            <tbody></tbody>
                        </table>
                    </form>
                </div>
            </fieldset>
        </td></tr>
</table>
<html:form action="searchRfmMaterialInStore.do">
    <table>        
        <tr>           
            <td><div>
                    <html:select property="searchid" style="width: 170px;">
                        <html:option key="message.all" value='0'/>
                        <html:option key="message.material.code" value='1'/>
                        <html:option key="message.material.nameVn" value='2'/>                     
                    </html:select>
                    <html:text property="searchvalue" />
                    <html:submit onclick="return searchRfmMaterialInStore();"><bean:message key="message.search"/></html:submit>
                    <html:button property="" onclick="return setRfmMaterialInStoreSelected();"><bean:message key="message.choose"/></html:button>
                    <img onclick="return printMaterialInStore();" src="images/but_print.gif"/>
                </div>
                <div>
                    <html:select property="extraSearchId" style="width: 170px;">
                        <html:option key="message.rfm.requestNumber" value='3'/>                        
                    </html:select>
                    <html:text property="extraSearchValue" />
                </div>
            </td>
        </tr>
        <tr><td>&nbsp;</td></tr>
        <tr><td><bean:message key="message.store"/>
                <html:select property="searchvalues" onchange="searchRfmMaterialInStore();">
                    <html:options collection="<%=Constants.STORE_LIST%>" property="value" labelProperty="label"/>
                </html:select>
            </td></tr>
    </table>

</html:form>
<form name='materialInStoreForm' id='materialInStoreForm'>
    <div id='materialInStoreList'></div>
    <input type="hidden" name="kind" value="0">
</form>
