<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.MCUtil"%>

<table width="220" border="0" cellpadding="0" cellspacing="0">
    <tr> 
        <td id="navMenuContainer" background="images/im101.gif">                          
            <div style="width:98%;margin-left:0.2em;" dojoType="dijit.Menu" id="navMenu">                               
                <span><h5 style="margin-left:0.5em;margin-top:0.5em;margin-bottom:0.5em;"><bean:message key="message.menu2.text"/></h5></span>
                <div dojoType="dijit.PopupMenuItem" id="navMenuPopupItem1">
                    <span><bean:message key="message.menu20.text"/><%=MCUtil.getMenuSpaces(33, request)%></span>
                    <div dojoType="dijit.Menu" id="navMenuSub1">
                        <div dojoType="dijit.MenuItem" id="navMenuSub1_item1" iconClass="mcMenuIconList"
                             onClick="loadVendorList();"><bean:message key="message.menu.list"/></div>
                        <div dojoType="dijit.MenuItem" id="navMenuSub1_item2" iconClass="mcMenuIconNew"
                             onClick="vendorForm();"><bean:message key="message.menu.add"/></div>  
                        <div dojoType="dijit.MenuItem" id="navMenuSub1_item3"
                             onClick="loadCriterionList();"><bean:message key="message.menu200.text"/></div>
                    </div>
                </div>
                <div dojoType="dijit.PopupMenuItem" id="navMenuPopupItem2">
                    <span><bean:message key="message.menu21.text"/></span>
                    <div dojoType="dijit.Menu" id="navMenuSub2">
                        <div dojoType="dijit.MenuItem" id="navMenuSub2_item1" iconClass="mcMenuIconList"
                             onClick="loadMaterialList();"><bean:message key="message.menu.list"/></div>
                        <div dojoType="dijit.MenuItem" id="navMenuSub2_item2" iconClass="mcMenuIconNew"
                             onClick="addMaterial();"><bean:message key="message.menu.add"/></div>
                        <div dojoType="dijit.MenuItem" id="navMenuSub2_item3" 
                             onClick="loadUnitList();"><bean:message key="message.menu210.text"/></div>
                        <div dojoType="dijit.MenuItem" id="navMenuSub2_item4" 
                             onClick="loadUnitList();"><bean:message key="message.menu211.text"/></div>                        
                        <div dojoType="dijit.MenuItem" id="navMenuSub2_item5" 
                             onClick="loadOriginList();"><bean:message key="message.menu212.text"/></div>                        
                    </div>
                </div>                
                <div dojoType="dijit.MenuItem" 
                     onClick="loadStoreList();"><bean:message key="message.menu22.text"/></div>
                <div dojoType="dijit.MenuItem" 
                     onClick="loadProjectList();"><bean:message key="message.menu23.text"/></div>                
                <div dojoType="dijit.MenuItem" iconClass="dijitEditorIcon dijitEditorIconCopy"
                     onClick="callServer('testReport.do');">Test Report</div>
                <div dojoType="dijit.MenuSeparator"></div>
                
                <span><h5 style="margin-left:0.5em;margin-top:0.5em;margin-bottom:0.5em;"><bean:message key="message.menu3.text"/></h5></span>
                
                <div dojoType="dijit.PopupMenuItem" id="navMenuPopupItem3">
                    <span><bean:message key="message.menu31.text"/><%=MCUtil.getMenuSpaces(30, request)%></span>
                    <div dojoType="dijit.Menu" id="navMenuSub3">
                        <div dojoType="dijit.MenuItem" id="navMenuSub3_item1" iconClass="mcMenuIconList"
                             onClick="loadRequestList();"><bean:message key="message.menu.list"/></div>
                        <div dojoType="dijit.MenuItem" id="navMenuSub3_item2" iconClass="mcMenuIconNew"
                             onClick="requestForm();"><bean:message key="message.menu.add"/></div>    
                        <div dojoType="dijit.MenuItem" id="navMenuSub3_item3" 
                             onClick="intermemoForm();"><bean:message key="message.menu310.text"/></div>
                    </div>
                </div>
                <div dojoType="dijit.PopupMenuItem" id="navMenuPopupItem4">
                    <span><bean:message key="message.menu32.text"/></span>
                    <div dojoType="dijit.Menu" id="navMenuSub4">
                        <div dojoType="dijit.MenuItem" id="navMenuSub4_item1" iconClass="mcMenuIconList"
                             onClick="loadIntermemoList();"><bean:message key="message.menu.list"/></div>
                        <div dojoType="dijit.MenuItem" id="navMenuSub4_item2" iconClass="mcMenuIconNew"
                             onClick="intermemoForm();"><bean:message key="message.menu.add"/></div>                        
                    </div>
                </div>
                <div dojoType="dijit.PopupMenuItem" id="navMenuPopupItem5">
                    <span><bean:message key="message.menu33.text"/></span>
                    <div dojoType="dijit.Menu" id="navMenuSub5">
                        <div dojoType="dijit.MenuItem" id="navMenuSub5_item1" iconClass="mcMenuIconList"
                             onClick="loadTenderPlanList();"><bean:message key="message.menu.list"/></div>
                        <div dojoType="dijit.MenuItem" id="navMenuSub5_item2" 
                             onClick="tenderLetterForm();"><bean:message key="message.menu330.text"/></div>                        
                        <div dojoType="dijit.MenuItem" id="navMenuSub5_item3" 
                             onClick="techEvalForm();"><bean:message key="message.menu331.text"/></div>   
                        <div dojoType="dijit.MenuItem" id="navMenuSub5_item4" 
                             onClick="techClarificationFrm();"><bean:message key="message.menu332.text"/></div>
                    </div>
                </div>
                <div dojoType="dijit.PopupMenuItem" id="navMenuPopupItem6">
                    <span><bean:message key="message.menu34.text"/></span>
                    <div dojoType="dijit.Menu" id="navMenuSub6">
                        <div dojoType="dijit.MenuItem" id="navMenuSub6_item1" iconClass="mcMenuIconList"
                             onClick="loadContractList();"><bean:message key="message.menu.list"/></div>
                        <div dojoType="dijit.MenuItem" id="navMenuSub6_item2" 
                             onClick="loadContractPrincipleList();"><bean:message key="message.menu340.text"/></div>                        
                        <div dojoType="dijit.MenuItem" id="navMenuSub6_item3" 
                             onClick="loadContractFollowList();"><bean:message key="message.menu341.text"/></div>                                
                    </div>
                </div>
                <div dojoType="dijit.MenuItem" 
                     onClick="loadOrderList();"><bean:message key="message.menu35.text"/></div>
                <div dojoType="dijit.MenuItem" 
                     onClick="loadDeliveryRequestList();"><bean:message key="message.menu36.text"/></div>                                     
                <div dojoType="dijit.MenuItem" iconClass="dijitEditorIcon dijitEditorIconCopy"
                     onClick="callServer('testReport.do');">Test Report</div>
                <div dojoType="dijit.MenuSeparator"></div>
                
                <span><h5 style="margin-left:0.5em;margin-top:0.5em;margin-bottom:0.5em;"><bean:message key="message.menu4.text"/></h5></span>
                
                <div dojoType="dijit.MenuItem" iconClass="dijitEditorIcon dijitEditorIconCopy"
                     onClick="callServer('testReport.do');">Test Report</div>
                <div dojoType="dijit.MenuSeparator"></div>
                
                <span><h5 style="margin-left:0.5em;margin-top:0.5em;margin-bottom:0.5em;"><bean:message key="message.menu5.text"/></h5></span>
                
                <div dojoType="dijit.PopupMenuItem" id="navMenuPopupItem7">
                    <span><bean:message key="message.menu51.text"/><%=MCUtil.getMenuSpaces(40, request)%></span>
                    <div dojoType="dijit.Menu" id="navMenuSub7">
                        <div dojoType="dijit.MenuItem" id="navMenuSub7_item1" iconClass="mcMenuIconList"
                             onClick="alert('Not implement yet');"><bean:message key="message.menu.list"/></div>
                        <div dojoType="dijit.MenuItem" id="navMenuSub7_item2" iconClass="mcMenuIconNew"
                             onClick="alert('Not implement yet');"><bean:message key="message.menu.add"/></div>                        
                    </div>
                </div>
                <div dojoType="dijit.PopupMenuItem" id="navMenuPopupItem8">
                    <span><bean:message key="message.menu52.text"/></span>
                    <div dojoType="dijit.Menu" id="navMenuSub8">
                        <div dojoType="dijit.MenuItem" id="navMenuSub8_item1" iconClass="mcMenuIconList"
                             onClick="alert('Not implement yet');"><bean:message key="message.menu.list"/></div>
                        <div dojoType="dijit.MenuItem" id="navMenuSub8_item2" iconClass="mcMenuIconNew"
                             onClick="alert('Not implement yet');"><bean:message key="message.menu.add"/></div>                        
                    </div>
                </div>
                <div dojoType="dijit.PopupMenuItem" id="navMenuPopupItem9">
                    <span><bean:message key="message.menu53.text"/></span>
                    <div dojoType="dijit.Menu" id="navMenuSub9">
                        <div dojoType="dijit.MenuItem" id="navMenuSub9_item1" iconClass="mcMenuIconList"
                             onClick="alert('Not implement yet');"><bean:message key="message.menu.list"/></div>
                        <div dojoType="dijit.MenuItem" id="navMenuSub9_item2" iconClass="mcMenuIconNew"
                             onClick="alert('Not implement yet');"><bean:message key="message.menu.add"/></div>                        
                    </div>
                </div>
                <div dojoType="dijit.PopupMenuItem" id="navMenuPopupItem10">
                    <span><bean:message key="message.menu54.text"/></span>
                    <div dojoType="dijit.Menu" id="navMenuSub10">
                        <div dojoType="dijit.MenuItem" id="navMenuSub10_item1" iconClass="mcMenuIconList"
                             onClick="alert('Not implement yet');"><bean:message key="message.menu.list"/></div>
                        <div dojoType="dijit.MenuItem" id="navMenuSub10_item2" iconClass="mcMenuIconNew"
                             onClick="alert('Not implement yet');"><bean:message key="message.menu.add"/></div>                        
                    </div>
                </div>
                <div dojoType="dijit.MenuItem" 
                     onClick="alert('Not implement yet');"><bean:message key="message.menu55.text"/></div>
                <div dojoType="dijit.MenuItem" 
                     onClick="alert('Not implement yet');"><bean:message key="message.menu56.text"/></div>
                
            </div>            
        </td>
    </tr>
</table>
