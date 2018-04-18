<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ page import="org.apache.struts.action.ActionErrors" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><bean:message key="welcome.title"/></title>
        <style type="text/css">
            /* pushes the page to the full capacity of the viewing area */
            html {height:100%;}
            body {height:100%; margin:0; padding:0;}
            /* prepares the background image to full capacity of the viewing area */
            #bg {position:fixed; top:0; left:0; width:100%; height:100%;}
            /* places the content ontop of the background image */
            #content {position:relative; z-index:1;}
        </style>
        <!--[if IE 6]>
        <style type="text/css">
        /* some css fixes for IE browsers */
        html {overflow-y:hidden;}
        body {overflow-y:auto;}
        #bg {position:absolute; z-index:-1;}
        #content {position:static;}
        </style>
        <![endif]-->
    </head>
    <body>
        <div id="bg"><img src="images/uc.jpeg" width="100%" height="100%" alt=""></div>
    </body>
</html>
