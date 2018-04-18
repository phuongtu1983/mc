<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title><bean:message key="welcome.title"/></title>
<meta http-equiv="Content-Language" content="en-us">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript">
redirTime = 2;
redirURL = "/PTSCMC/loginPage.do"; 
function redirTimer() { self.setTimeout("self.location.href = redirURL;",redirTime); }
</script>
</head>
<BODY onLoad="redirTimer()">
    <table border="0" width="100%" cellspacing="0" cellpadding="0" background="images/home.jpg" height="600"></table>
    
</body>
</html>
