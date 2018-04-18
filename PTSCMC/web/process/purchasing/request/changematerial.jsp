<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>
<div style="width:500px;height:450px;overflow:auto;" >
    <table width="100%">
        <tr><td>
                <fieldset>
                    <legend><font face='Tahoma' style='font-size: 9pt;' color='#000080'><bean:message key='message.request.material.old'/></font></legend>                    
                    <div>
                        <input type="button" onclick="return saveMaterialChanged(<bean:write property="detId" name="<%=Constants.REQUEST_DETAIL%>"/>);" value="<bean:message key="message.save"/>"/>
                        <form name="materialChangeRequestSelectedForm">
                            <table class="its" style="width:100%">
                                <thead>
                                    <tr>
                                        <th width="100px"><bean:message key="message.material.code"/></th>
                                        <th><bean:message key="message.material.nameVn"/></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td><bean:write property="matCode" name="<%=Constants.REQUEST_DETAIL%>"/></td>
                                        <td><bean:write property="matName" name="<%=Constants.REQUEST_DETAIL%>"/></td>
                                    </tr>
                                </tbody>
                            </table>
                        </form>
                    </div>
                </fieldset>
            </td></tr>
        <tr><td>           
                <form name="selectMaterialForm">
                    <select name="searchid">
                        <option value='0'><bean:message key='message.all'/></option>
                        <option value='1'><bean:message key='message.material.code'/></option>
                        <option value='2'><bean:message key='message.material.nameVn'/></option>
                    </select>
                    <input type="textbox"  size="70" name="searchvalue"/>
                    <input type="button" onclick="return searchMaterialRequestForChange(<bean:write name="<%=Constants.REQUEST_DETAIL%>" property="detId"/>);" value="<bean:message key="message.search"/>"/>
                </form>                
            </td></tr>
    </table>
    <form name="materialChangeRequestForm">
        <html:hidden property="reqId" name="<%=Constants.REQUEST_DETAIL%>"/>
        <div id='materialChangeRequestList'></div>
    </form>
    <input type="hidden" id="callbackFunc"/>
</div>