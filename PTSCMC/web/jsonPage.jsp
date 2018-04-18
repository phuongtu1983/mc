<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ page import="com.venus.mc.util.Constants"%>
<html>
<head>
<script type='text/javascript' src='js/Ajax.js'></script>
<script type="text/javascript">
    function redirect(){
        var message=document.getElementById('message').value;
        if(message=='') window.location='loginPage.do';
        else{
            var obj=eval('('+message+')');
            var func=obj.func+"(";
            if(obj.params!=null) func+= obj.params;
            func+=")";
            window.name=func;
            window.location=obj.parentPage;
        }
    }
    window.onload=redirect;
</script>
</head>
<body><html:hidden property="message" styleId="message" name="<%=Constants.JSON_FORM%>"/></body>
</html>