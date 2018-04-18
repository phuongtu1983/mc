<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<table width="220" border="0" cellpadding="0" cellspacing="0">
    <tr> 
        <td background="images/im101.gif"m>
            <div id="accordion" style="margin-left:15;margin-bottom:7">
                <div id="menu1-header" class="toggler atStart"><img src="img/mnu200.gif" width="220" height="34"></div>
                <div id="menu1-content" style="border-top: medium none; border-bottom: medium none; padding-top: 0px; padding-bottom: 0px; height: 0px;" class="element atStart">
                    <ul>
                        <li><html:link styleClass="my3" href="#" onclick="loadVendorList();">
                            <strong><bean:message key="message.menu20.text"/></strong>
                        </html:link></li>
                        <li><html:link styleClass="my3" href="#" onclick="loadMaterialList();">
                            <strong><bean:message key="message.menu21.text"/></strong>
                            </html:link>
                            <ul id="expanded">
                                <li class="none">
                                    <html:link styleClass="my3" href="#" onclick="loadUnitList();">
                                        <strong><bean:message key="message.menu211.text"/></strong>
                                    </html:link>
                                </li>
                                <li class="none">
                                    <html:link styleClass="my3" href="#" onclick="loadOriginList();">
                                        <strong><bean:message key="message.menu212.text"/></strong>
                                    </html:link>
                                </li>
                            </ul>
                        </li>
                        <li><html:link styleClass="my3" href="#" onclick="loadStoreList();">
                            <strong><bean:message key="message.menu22.text"/></strong>
                        </html:link></li>
                        <li><html:link styleClass="my3" href="#" onclick="loadProjectList();">
                            <strong><bean:message key="message.menu23.text"/></strong>
                        </html:link></li>
                        <li><html:link styleClass="my3" href="#" onclick="loadCriterionList();">
                            <strong><bean:message key="message.menu24.text"/></strong>
                        </html:link></li>                    
                        <li><html:link styleClass="my3" href="#" onclick="callServer('testReport.do');">
                            <strong>test report</strong>
                        </html:link></li>      
                    </ul>
                </div>
                <div id="menu2-header" class="toggler atStart"><img src="img/mnu201.gif" width="220" height="34"></div>
                <div id="menu2-content" style="border-top: medium none; border-bottom: medium none; overflow: hidden; padding-top: 0px; padding-bottom: 0px; height: 0px;" class="element atStart">
                    <html:link styleClass="my3" href="#" onclick="window.location='requestList.do';">
                        <strong><bean:message key="message.menu31.text"/></strong>
                    </html:link><br/>
                    <html:link styleClass="my3" href="#" onclick="window.location='intermemoList.do';">
                        <strong><bean:message key="message.menu34.text"/></strong>
                    </html:link><br/>
                    <html:link styleClass="my3" href="#" onclick="window.location='contractList.do';">
                        <strong><bean:message key="message.menu33.text"/></strong>
                    </html:link><br/>
                    <html:link styleClass="my3" href="#" onclick="window.location='contractPrincipleList.do';">
                        <strong><bean:message key="message.menu35.text"/></strong>
                    </html:link><br/>
                    <html:link styleClass="my3" href="#" onclick="window.location='orderList.do';">
                        <strong><bean:message key="message.menu36.text"/></strong>
                    </html:link><br/>
                    <html:link styleClass="my3" href="#" onclick="window.location='materialInContractPanel.do';">
                        <strong><bean:message key="message.menu37.text"/></strong>
                    </html:link><br/>
                    <html:link styleClass="my3" href="#" onclick="window.location='contractFollowList.do';">
                        <strong><bean:message key="message.menu38.text"/></strong>
                    </html:link><br/>
                    <html:link styleClass="my3" href="#" onclick="window.location='tenderPlanList.do';">
                        <strong><bean:message key="message.menu32.text"/></strong>
                    </html:link><br/>                    
                    <html:link styleClass="my3" href="#" onclick="window.location='deliveryRequestList.do';">
                        <strong><bean:message key="message.menu61.text"/></strong>
                    </html:link><br/>
                </div>
                <div id="menu3-header" class="toggler atStart"><img src="img/mnu202.gif" width="220" height="34"></div>
                <div id="menu3-content" style="border-top: medium none; border-bottom: medium none; overflow: hidden; padding-top: 0px; padding-bottom: 0px; height: 0px;" class="element atStart">
                    <br/>
                </div>
                <div id="menu4-header" class="toggler atStart"><img src="img/mnu203.gif" width="220" height="34"></div>
                <div id="menu4-content" style="border-top: medium none; border-bottom: medium none; overflow: hidden; padding-top: 0px; padding-bottom: 0px; height: 0px;" class="element atStart">
                    <ul>
                        <li><strong><bean:message key="message.menu51.text"/></strong></li>
                        <li><strong><bean:message key="message.menu52.text"/></strong></li>
                        <li><strong><bean:message key="message.menu53.text"/></strong></li>
                        <li><strong><bean:message key="message.menu54.text"/></strong></li>
                        <li><strong><bean:message key="message.menu55.text"/></strong></li>
                        <li><strong><bean:message key="message.menu56.text"/></strong></li>
                    </ul>
                </div>
            </div>
        </td>
    </tr>
</table>
