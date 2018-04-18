<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.bean.ContractBean"%>
<h3><bean:message key="message.rfm.store.title1"/></h3>
<html:form action="searchRfmMaterialInStore1.do">
    <table>
        <tr>           
            <td><div>
                    <html:select property="searchid">
                        <html:option key="message.all" value='0'/>
                        <html:option key="message.material.code" value='1'/>
                        <html:option key="message.material.nameVn" value='2'/>                       
                        <html:option key="message.rfm.storeName" value='4'/>
                    </html:select>
                    <html:text property="searchvalue" />                    
                    <html:submit onclick="return searchRfmMaterialInStore1();"><bean:message key="message.search"/></html:submit>
                </div></td>
        </tr>
    </table>

</html:form>
<form name='materialInStoreForm' id='materialInStoreForm'>
    <div id='materialInStoreList'></div>
    <input type="button" onclick="return createRfm1();" value="<bean:message key="message.rfm.store.create1"/>"/>
    <input type="hidden" name="kind" value="1">
</form>
