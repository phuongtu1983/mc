<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<div style="width:500px;height:100px;overflow:auto;" >
    <table width="100%">
        <tr><td>
                <fieldset>
                    <legend><font face='Tahoma' style='font-size: 9pt;' color='#000080'><img id='selectedMaterial_image' border='0' src='images/icon_minus.gif' width='9' height='9' onclick="expandDiv(this,'materialRequestSelectedDiv');">&nbsp;<bean:message key='message.intermemo.template.select'/></font></legend>
                    <div>
                        <form name="intermemoTemplateForm">
                            <select name="templateid">
                                <option value='1'><bean:message key='message.intermemo.template.no1'/></option>
                                <option value='2'><bean:message key='message.intermemo.template.no2'/></option>
                            </select>
                            <img onclick="return printIntermemo();" src="images/but_print.gif"/>
                        </form>
                    </div>
                </fieldset>
        </td></tr>
    </table>
</div>