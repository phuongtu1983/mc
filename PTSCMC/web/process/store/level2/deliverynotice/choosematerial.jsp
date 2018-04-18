<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<div style="width:500px;height:450px;overflow:auto;" >
    <table width="100%">
        <tr><td>
                <fieldset>
                    <legend><font face='Tahoma' style='font-size: 9pt;' color='#000080'><img id='selectedMaterial_image' border='0' src='images/icon_minus.gif' width='9' height='9' onclick="expandDiv(this,'materialDn2RequestSelectedDiv');">&nbsp;<bean:message key='message.request.material.selected'/></font></legend>
                    <div id='materialDn2RequestSelectedDiv'>
                        <input type="button" onclick="return delDn2MaterialRequest();" value="<bean:message key="message.del"/>"/>&nbsp;<input type="button" onclick="return chooseDn2MaterialSelected();" value="<bean:message key="message.ok"/>"/>
                        <form name="materialDn2RequestSelectedForm">
                            <table id="materialRequestSelectedTbl" class="its" style="width:100%">
                                <thead>
                                    <tr>
                                        <th><bean:message key="message.choose"/></th>
                                        <th><bean:message key="message.material.nameVn"/></th>
                                        <th><bean:message key="message.material.code"/></th>
                                        <th><bean:message key="message.dn2.unit"/></th>
                                    </tr>
                                </thead>
                                <tbody></tbody>
                            </table>
                        </form>
                    </div>
                </fieldset>
        </td></tr>
        <tr><td>           
                <form name="selectDn2MaterialForm">
                    <select name="searchid">
                        <option value='0'><bean:message key='message.all'/></option>
                        <option value='1'><bean:message key='message.material.code'/></option>
                        <option value='2'><bean:message key='message.material.nameVn'/></option>
                    </select>
                    <input type="textbox"  size="70" name="searchvalue"/>
                    <input type="button" onclick="return searchDn2MaterialRequest();" value="<bean:message key="message.search"/>"/>&nbsp;<input type="button" onclick="return setDn2MaterialSelected();" value="<bean:message key="message.choose"/>"/>
                </form>                
        </td></tr>
    </table>
    <form name="materialDn2RequestForm">
        <div id='materialDn2RequestList'></div>
    </form>
    <input type="hidden" id="callbackFunc"/>
</div>