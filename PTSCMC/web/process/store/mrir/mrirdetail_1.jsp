<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib prefix="display" uri="/tags/displaytag" %>
<%@ page import="com.venus.mc.util.MCUtil"%>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.venus.mc.process.store.mrir.MrirFormBean"%>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<div id="mrirFormTitle"><h3>
        <logic:equal value="0" name="<%=Constants.MRIR%>" property="kind">
            <bean:message key="message.mrirform.title0"/>
        </logic:equal>
        <logic:equal value="1" name="<%=Constants.MRIR%>" property="kind">
            <bean:message key="message.mrirform.title1"/>
        </logic:equal>
        <logic:equal value="2" name="<%=Constants.MRIR%>" property="kind">
            <bean:message key="message.mrirform.title2"/>
        </logic:equal>
    </h3></div>
<div id="mrirErrorMessageDiv"></div>
<form name="mrirForm">
    <table width="100%">
        <logic:equal value="1" name="<%=Constants.MRIR%>" property="kind">
            <logic:equal value="0" name="<%=Constants.MRIR%>" property="mrirId">
                <tr>
                    <td colspan="6">
                        <fieldset>                                        
                            <legend class="lbl10b"><bean:message key="message.mrir.dnnote1"/></legend>                    
                            <html:select styleClass="lbl10" property="proId" name="<%=Constants.MRIR%>" onchange="return mrirProChanged(this,this.selectedIndex);">
                                <html:options styleClass="lbl10" collection="<%=Constants.MRIR_PROJECT_LIST%>" property="value" labelProperty="label"/>
                            </html:select>
                        </fieldset>   
                    </td>                
                </tr>
            </logic:equal>
        </logic:equal>
        <logic:present name="<%=Constants.MRIR_DELIVERY_NOTICE_LIST%>"> 
            <tr>
                <logic:equal value="0" name="<%=Constants.MRIR%>" property="mrirId">
                    <td colspan="6">
                        <fieldset>
                            <legend class="lbl10b"><bean:message key="message.mrir.dnnote"/></legend>
                            <html:select styleClass="lbl10" property="dnId" name="<%=Constants.MRIR%>" onchange="return mrirDnChanged(this,this.selectedIndex);">
                                <html:options styleClass="lbl10" collection="<%=Constants.MRIR_DELIVERY_NOTICE_LIST%>" property="value" labelProperty="label"/>
                            </html:select>
                        </fieldset>
                    </td>
                </logic:equal>
                <logic:notEqual value="0" name="<%=Constants.MRIR%>" property="mrirId">
                    <td><bean:message key="message.mrir.dnId"/></td>
                    <td colspan="5"><html:select  styleClass="lbl10" property="dnId" name="<%=Constants.MRIR%>">
                            <html:options styleClass="lbl10" collection="<%=Constants.MRIR_DELIVERY_NOTICE_LIST%>" property="value" labelProperty="label"/>
                        </html:select><input type="button" value="..." onclick="return printDn(<bean:write property="dnId" name="<%=Constants.MRIR%>"/>);"></td>
                    </logic:notEqual>
            </tr>
        </logic:present>
        <logic:present name="<%=Constants.MRIR_REQUEST_LIST%>"> 
            <tr>
                <td colspan="6">
                    <fieldset>                                        
                        <legend class="lbl10b"><bean:message key="message.mrir.reqnote"/></legend>
                        <html:select styleClass="lbl10" property="reqId" name="<%=Constants.MRIR%>" onchange="return mrirReqChanged(this,this.selectedIndex);">
                            <html:options styleClass="lbl10" collection="<%=Constants.MRIR_REQUEST_LIST%>" property="value" labelProperty="label"/>
                        </html:select>            
                    </fieldset>   
                </td>                
            </tr> 
        </logic:present>
        <tr>
            <td class="lbl10"><bean:message key="message.mrir.mrirNumber"/></td>
            <td colspan="5"><html:text styleClass="lbl10" size="60" property="mrirNumber" name="<%=Constants.MRIR%>"/></td>
        </tr>
        <tr>
            <td class="lbl10"><bean:message key="message.mrir.createdDate"/></td>
            <td><html:text styleClass="lbl10" property="createdDate" styleId="createdDateMrir" size="10" name="<%=Constants.MRIR%>" disabled="true" /></td>
            <td class="lbl10"><bean:message key="message.mrir.conId"/></td>
            <td><html:text styleClass="lbl10" size="35" property="conNumber" name="<%=Constants.MRIR%>" disabled="true" />
                <logic:notEqual value="0" name="<%=Constants.MRIR%>" property="conId">
                    <input type="button" value="..." onclick="return printContractEx(<bean:write property="conId" name="<%=Constants.MRIR%>"/>);">
                </logic:notEqual></td>
        </tr>
        <tr>            
            <td><bean:message key="message.mrir.venId"/></td>
            <td colspan="5"><html:text styleClass="lbl10" size="60" property="venName" name="<%=Constants.MRIR%>" disabled="true" /></td>              
        </tr>
        <tr>
            <td><bean:message key="message.mrir.note"/></td>
            <td colspan="5" class="lbl10"><html:textarea cols="80" rows="4" property="note" name="<%=Constants.MRIR%>"/></td>
        </tr>
        <logic:equal value="0" name="<%=Constants.MRIR%>" property="kind">
            <tr>
                <td class="lbl10"><bean:message key="message.mrir.blNo"/></td>
                <td><html:text styleClass="lbl10" size="15" property="blNo" name="<%=Constants.MRIR%>" /></td>
                <td class="lbl10"><bean:message key="message.mrir.plNo"/></td>
                <td ><html:text styleClass="lbl10" size="15" property="plNo" name="<%=Constants.MRIR%>" /></td>
                <td class="lbl10"><bean:message key="message.mrir.invoiceNo"/></td>
                <td><html:text styleClass="lbl10" size="15" property="invoiceNo" name="<%=Constants.MRIR%>" /></td>
            </tr>       
        </logic:equal>
        <logic:equal value="1" name="<%=Constants.MRIR%>" property="kind">
            <tr>            
                <td><bean:message key="message.mrir.packinglistno"/></td>
                <td colspan="3"><html:text styleClass="lbl10" size="60" property="plNo" name="<%=Constants.MRIR%>"/></td>              
                <td class="lbl10"><bean:message key="message.mrir.invoiceNo"/></td>
                <td><html:text styleClass="lbl10" size="15" property="invoiceNo" name="<%=Constants.MRIR%>" /></td>
            </tr>
        </logic:equal>
        <tr>
            <td colspan="6">
                <fieldset>                                        
                    <legend class="lbl10b"><bean:message key="message.mrir.materials"/></legend>
                    <html:image src="images/ico_print.gif" onclick="return mrirPrintComments();"/>
                    <html:image src="images/ico_xoa.gif" onclick="return mrirDelMaterial();"/>
                    <html:image src="images/ico_them.gif" onclick="return mrirAddMaterial();"/>        
                    <html:image src="images/ico_themall.gif" onclick="return mrirAddAllMaterial();"/>    
                    <logic:notEqual value="2" name="<%=Constants.MRIR%>" property="kind">
                        <logic:present name="<%=Constants.REQUEST_MATERIAL_KIND_LIST%>">                            
                            <html:select styleClass="lbl10" property="materialKind" name="<%=Constants.MRIR%>" onchange="return mrirMaterialKindChanged();">
                                <html:options styleClass="lbl10" collection="<%=Constants.REQUEST_MATERIAL_KIND_LIST%>" property="value" labelProperty="label"/>
                            </html:select>   
                        </logic:present>
                    </logic:notEqual>
                    <span id="listMaterialByReqIdDiv">                                       
                        <logic:present name="<%=Constants.MRIR_MATERIAL_LIST%>">
                            <select class="lbl10" name="cbxMaterialOfReq">
                                <logic:iterate id="mat_iter" name="<%=Constants.MRIR_MATERIAL_LIST%>">                        
                                    <option class="lbl10" value="${mat_iter.value}">${mat_iter.label}</option>
                                </logic:iterate>
                            </select>                            
                        </logic:present>
                    </span>             
                    <div id="listMrirDetailDataDiv" style="width:700px;height:300px;overflow:auto">
                        <logic:equal value="1" name="<%=Constants.MRIR%>" property="kind">
                            <%@include  file="/process/store/mrir/materiallistpro.jsp" %>
                        </logic:equal>
                        <logic:notEqual value="1" name="<%=Constants.MRIR%>" property="kind">
                            <%@include  file="/process/store/mrir/materiallist.jsp" %>
                        </logic:notEqual>
                    </div>
                </fieldset>
            </td>
        </tr>        
    </table>
    <logic:greaterThan name="<%=Constants.MRIR%>" value="0" property="mrirId">
        <img onclick="return printMrir();" src="images/but_print.gif"/>
    </logic:greaterThan>
        <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_EDIT, PermissionUtil.PER_STOCK_MRIR)) {%>
    <html:image onclick="return saveMrir();" src="images/but_save.gif"/><img src="images/blank.gif" width="2" height="17">
    <%}%>
    <html:image property="Back" value="Back" src="images/but_back.gif" onclick="return loadMrirList();"/>
    <html:hidden property="mrirId" name="<%=Constants.MRIR%>"/>
    <html:hidden property="conId" name="<%=Constants.MRIR%>"/>
    <html:hidden property="kind" name="<%=Constants.MRIR%>"/>     

</form>
<div id="mrirDetailHideDiv" style="display:none"></div>

