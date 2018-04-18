<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<html>
<head>
<title>Dojo example</title>
<link rel='stylesheet' href='js/dojotoolkit/dijit/themes/nihilo/nihilo.css' type='text/css'>
<script type="text/javascript" src="js/dojotoolkit/dojo/dojo.js" djConfig="parseOnLoad:true, isDebug: true"></script>
<script type='text/javascript' src='js/Ajax.js'></script>
<script type="text/javascript">
    dojo.require("dijit.form.Button");
    dojo.require("dijit.Dialog");
    function abc(data){
        alert(data);
        var control=dijit.byId("dialogOne");
        control.attr('content',data);
        control.show();
    }
</script>
<body class="nihilo">
<div id="dialogOne" dojoType="dijit.Dialog" title="My Dialog Title">
    abc
</div>

<p>When pressing this button the dialog will popup:</p>
<html:submit onclick="return callAjax('searchAdvVendorForm.do',null,null,abc);">Show me 1</html:submit>
</body>
</html>