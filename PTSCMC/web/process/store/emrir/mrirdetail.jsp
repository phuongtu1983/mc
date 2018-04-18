<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib prefix="display" uri="/tags/displaytag" %>
<%@ page import="com.venus.mc.util.MCUtil"%>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.venus.mc.process.store.emrir.EmrirFormBean"%>

<div id="emrirFormTitle"><h3><bean:message key="message.emriradd.title"/></h3></div>
<div id="errorMessageDiv"></div>
<form name="emrirForm">
    <table width="100%">
        <tr>
            <logic:equal value="0" name="<%=Constants.EMRIR%>" property="emrirId">
                <td colspan="6">
                    <fieldset>                                        
                        <legend class="lbl10b"><bean:message key="message.mrir.dnnote"/></legend>                    
                        <html:select styleClass="lbl10" property="ednId" name="<%=Constants.EMRIR%>" onchange="return emrirDnChanged(this,this.selectedIndex);">
                            <html:options styleClass="lbl10" collection="<%=Constants.EMRIR_DELIVERY_NOTICE_LIST%>" property="value" labelProperty="label"/>
                        </html:select>
                    </fieldset>   
                </td>                          
            </logic:equal>
            <logic:notEqual value="0" name="<%=Constants.EMRIR%>" property="emrirId">
                <td><bean:message key="message.mrir.dnId"/></td>
                <td colspan="5"><html:select  styleClass="lbl10" property="ednId" name="<%=Constants.EMRIR%>">
                        <html:options styleClass="lbl10" collection="<%=Constants.EMRIR_DELIVERY_NOTICE_LIST%>" property="value" labelProperty="label"/>
                </html:select><input type="button" value="..." onclick="return printEdn(<bean:write property="ednId" name="<%=Constants.EMRIR%>"/>);"></td>               
            </logic:notEqual>  
        </tr> 
        <tr>
            <td class="lbl10"><bean:message key="message.mrir.mrirNumber"/></td>
            <td><html:text styleClass="lbl10" size="15" property="emrirNumber" name="<%=Constants.EMRIR%>"/></td>
            <td class="lbl10"><bean:message key="message.mrir.createdDate"/></td>
            <td><html:text styleClass="lbl10" property="createdDate" styleId="createdDateMrir" size="10" name="<%=Constants.EMRIR%>" disabled="true" /></td>
            <td class="lbl10">&nbsp;</td>
            <td>&nbsp;</td>            
        </tr>   
        <tr>
            <td class="lbl10"><bean:message key="message.project"/></td>
            <td><html:text styleClass="lbl10" size="15" property="proName" name="<%=Constants.EMRIR%>" disabled="true"/></td>
            <td class="lbl10"><bean:message key="message.mrir.orgid"/></td>
            <td><html:text styleClass="lbl10" size="15" property="orgName" name="<%=Constants.EMRIR%>" disabled="true"/></td>
            <td class="lbl10"><bean:message key="message.mrir.conId"/></td>
            <td><html:text styleClass="lbl10" size="15" property="conNumber" name="<%=Constants.EMRIR%>" disabled="true" /></td>            
        </tr>
        <tr>
            <td><bean:message key="message.mrir.note"/></td>
            <td colspan="5" class="lbl10"><html:textarea cols="80" rows="4" property="note" name="<%=Constants.EMRIR%>"/></td>
            
        </tr>                  
        <tr>            
            <td><bean:message key="message.mrir.packinglistno"/></td>
            <td colspan="3"><html:text styleClass="lbl10" size="60" property="packingListNo" name="<%=Constants.EMRIR%>"/></td>              
            <td class="lbl10"><bean:message key="message.mrir.invoiceNo"/></td>
            <td><html:text styleClass="lbl10" size="15" property="invoiceNo" name="<%=Constants.EMRIR%>" /></td>
        </tr>               
        <tr>
            <td colspan="6">
                <fieldset>                                        
                    <legend class="lbl10b"><bean:message key="message.mrir.materials"/></legend>
                    <html:image src="images/ico_xoa.gif" onclick="return emrirDelMaterial();"/>
                    <html:image src="images/ico_them.gif" onclick="return emrirAddMaterial();"/>                          
                    <span id="listMaterialByReqIdDiv">                                       
                        <logic:present name="<%=Constants.EMRIR_MATERIAL_LIST%>"> 
                            <select class="lbl10" name="cbxMaterialOfDn">
                                <logic:iterate id="mat_iter" name="<%=Constants.EMRIR_MATERIAL_LIST%>">                        
                                    <option class="lbl10" value="${mat_iter.value}">${mat_iter.label}</option>
                                </logic:iterate>
                            </select>                            
                        </logic:present>
                    </span>             
                    <div id="listEmrirDetailDataDiv" style="width:700px;height:300px;overflow:auto">                    
                        <%@include  file="/process/store/emrir/materiallistpro.jsp" %>
                    </div>
                </fieldset>
            </td>
        </tr>        
    </table>
    <logic:greaterThan name="<%=Constants.EMRIR%>" value="0" property="emrirId">
        <img onclick="return printEmrir();" src="images/but_print.gif"/>
    </logic:greaterThan>
    <html:image onclick="return saveEmrir();" src="images/but_save.gif"/><img src="images/blank.gif" width="2" height="17">
    
    <html:image property="Back" value="Back" src="images/but_back.gif" onclick="return loadEmrirList();"/>
    <html:hidden property="emrirId" name="<%=Constants.EMRIR%>"/>
    
</form>
<div id="emrirDetailHideDiv" style="display:none"></div>

