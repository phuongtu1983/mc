<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<div style="width:500px;height:350px;overflow:auto;" align="center">
    <form name="mrirMaterialForm">
        <table width="100%" align="center">
            <tr><td>Name</td></tr>
            <tr><td><html:text disabled="true" property="matName" name="mrirMaterial" size="90"/></td></tr>
            <tr><td>Comment</td></tr>
            <tr><td><html:textarea property="comment" name="mrirMaterial" cols="75" rows="18" />
                    <html:hidden property="detId" name="mrirMaterial" />
            </td></tr>
            <tr><td><div align="center"><html:image onclick="return saveMrirMaterial();" src="images/but_save.gif"/><img src="images/blank.gif" width="2" height="17"><html:image property="Back" value="Back" src="images/but_back.gif" onclick="return hidePopupForm();"/></div></td></tr>
        </table>        
    </form>           
</div>