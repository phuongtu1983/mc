<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<table width="220" border="0" cellpadding="0" cellspacing="0">
    <tr> 
        <td background="images/im101.gif">
            <div id="accordion" style="margin-left:15;margin-bottom:7">
                <div id="menu1-header" class="toggler atStart"><img src="img/mnu200.gif" width="220" height="34"></div>
                <div id="menu1-content" style="border-top: medium none; border-bottom: medium none; overflow: hidden; padding-top: 0px; padding-bottom: 0px; height: 0px;" class="element atStart">
                    <ul>
                        <li>
                            <html:link styleClass="my3" href="#" onclick="window.location='vendorList.do';">
                                <strong><bean:message key="message.menu20.text"/></strong>
                            </html:link>
                        </li>
                        <li>
                            <html:link styleClass="my3" href="#" onclick="window.location='materialList.do';">
                                <strong><bean:message key="message.menu21.text"/></strong>
                            </html:link>
                            <ul>
                                <li><html:link styleClass="my3" href="#" onclick="window.location='unitList.do';">
                                    <strong><bean:message key="message.menu211.text"/></strong>
                                </html:link></li>
                                <li><html:link styleClass="my3" href="#" onclick="window.location='originList.do';">
                                    <strong><bean:message key="message.menu212.text"/></strong>
                                </html:link></li>
                            </ul>
                        </li>
                        <li><html:link styleClass="my3" href="#" onclick="window.location='storeList.do';">
                            <strong><bean:message key="message.menu22.text"/></strong>
                        </html:link></li>
                        <li><html:link styleClass="my3" href="#" onclick="window.location='projectList.do';">
                            <strong><bean:message key="message.menu23.text"/></strong>
                        </html:link></li>
                        <li><html:link styleClass="my3" href="#" onclick="window.location='criterionList.do';">
                            <strong><bean:message key="message.menu24.text"/></strong>
                        </html:link></li>                            
                    </ul>
                </div>
                <div id="menu2-header" class="toggler atStart"><img src="img/mnu201.gif" width="220" height="34"></div>
                <div id="menu2-content" style="border-top: medium none; border-bottom: medium none; overflow: hidden; padding-top: 0px; padding-bottom: 0px; height: 0px;" class="element atStart">
                    <html:link styleClass="my3" href="#" onclick="loadRequestList();">
                        <strong><bean:message key="message.menu31.text"/></strong>
                    </html:link><br/>
                    <html:link styleClass="my3" href="#" onclick="loadIntermemoList();">
                        <strong><bean:message key="message.menu34.text"/></strong>
                    </html:link><br/>
                    <html:link styleClass="my3" href="#" onclick="loadContractList();">
                        <strong><bean:message key="message.menu33.text"/></strong>
                    </html:link><br/>
                    <html:link styleClass="my3" href="#" onclick="loadContractPrincipleList();">
                        <strong><bean:message key="message.menu35.text"/></strong>
                    </html:link><br/>
                    <html:link styleClass="my3" href="#" onclick="loadOrderList();">
                        <strong><bean:message key="message.menu36.text"/></strong>
                    </html:link><br/>
                    <html:link styleClass="my3" href="#" onclick="loadMaterialListInContract();">
                        <strong><bean:message key="message.menu37.text"/></strong>
                    </html:link><br/>
                    <html:link styleClass="my3" href="#" onclick="loadContractFollowList();">
                        <strong><bean:message key="message.menu38.text"/></strong>
                    </html:link><br/>
                     <html:link styleClass="my3" href="#" onclick="loadTenderPlanList();">
                        <strong><bean:message key="message.menu32.text"/></strong>
                    </html:link><br/>                    
                    <html:link styleClass="my3" href="#" onclick="loadDeliveryRequestList();">
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
