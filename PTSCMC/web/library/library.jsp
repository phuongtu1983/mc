<%@ page language="java" pageEncoding="UTF-8" %> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ page import="com.venus.core.util.DateUtil"%>
<%@ page import="com.venus.mc.util.MCUtil"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
    <head>
        <title><bean:message key='welcome.title'/></title>
        <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'/>
        <meta content='no-cache' http-equiv='Pragma'/>
        <link rel="stylesheet" href="css/my.css" type="text/css" >
        <link rel="stylesheet" href="css/maven-base.css" type="text/css"/>
        <link rel="stylesheet" href="css/maven-theme.css" type="text/css"/>
        <link rel="stylesheet" href="css/screen.css" type="text/css"/>
        <link rel="stylesheet" href="css/site.css" type="text/css"/>
        <link rel="stylesheet" href="css/displaytag.css" type="text/css"/>        
        <link rel="stylesheet" href="css/ezcalendar.css" type="text/css"/>        
        <link href="js/dojotoolkit/dijit/themes/tundra/tundra.css" rel="stylesheet" />
        <link href="js/dojotoolkit/dijit/themes/dijit.css" rel="stylesheet" />
        <link href="js/dojotoolkit/dijit/themes/tundra/form/Button.css" rel="stylesheet" />
        <link href="js/dojotoolkit/dijit/themes/tundra/ProgressBar.css" rel="stylesheet" />
        <link href="js/dojotoolkit/dojox/form/resources/FileUploader.css" rel="stylesheet" />                
        <script type='text/javascript' src='js/mootools-1.11.js'></script>
        <script type='text/javascript' src='js/Ajax.js'></script>
        <script type='text/javascript' src='js/library.js'></script>
        <script type='text/javascript' src='js/mc_common.js'></script>
        <script type='text/javascript' src='js/ezcalendar.js'></script>
        <script type='text/javascript' src='js/NumberFormat.js'></script>
        <script src="js/dojotoolkit/dojo/dojo.js" djConfig="parseOnLoad: true"></script>      
        <script>
            dojo.require("dijit.dijit"); // optimize: load dijit layer
            dojo.require("dojo.parser");   
            dojo.require("dijit.Dialog");            
            dojo.require("dijit.layout.TabContainer");            
            dojo.require("dijit.form.Button");
            dojo.require("dijit.Menu");
            dojo.require("dijit.MenuItem");
            dojo.require("dijit.PopupMenuItem");
            dojo.require("dijit.MenuBar");
            dojo.require("dijit.MenuBarItem");
            dojo.require("dijit.PopupMenuBarItem");
            dojo.require("dijit.MenuSeparator");
            dojo.require("dijit.ColorPalette");
            
            sessionId='<%=session.getAttribute(Constants.SESSION_ID)%>';       
            
            function initAccordion() {
                //init menu
                var accordion = new Accordion('div.atStart', 'div.atStart', {
                    opacity: true,   
                    show: true,                    
                    alwaysHide: true,                    
                    onActive: function(toggler, element){
                        toggler.setStyle('color', '#ff3300');                        
                    },                    
                    onBackground: function(toggler, element){
                        toggler.setStyle('color', '#222');
                    }
                }, $('accordion'));        
                accordion.addSection('menu4-header', 'menu4-content');
                accordion.addSection('menu3-header', 'menu3-content');
                accordion.addSection('menu2-header', 'menu2-content');
                accordion.addSection('menu1-header', 'menu1-content');                
                accordion.display(3);   
            }
            
            function onMCStart() {
                
                //initAccordion();   
                
                initCalendar();
                if(window.name!=''){                    
                    eval(window.name);
                    window.name='';
                } else loadVendorList();
            }            
            
            window.onload=onMCStart;
        </script>       
        <style>
            .mcMenuIconNew {           
                background-image: url(images/ico_edit.gif);
                background-position: 0px;
                width: 16px;
                height: 16px;
            }
        </style>
    </head>
    <body leftmargin="0" topmargin="0" class="tundra">
        <center>
            <table width="990" height="14" border="0" align="center" cellpadding="0" cellspacing="0">
                <tr> 
                    <td width="14" background="img/lf.gif">&nbsp;</td>
                    <td width="962" bgcolor="#9AC8FA">
                        <div align="right"><font color="#0C478A">
                                <a href="#" onclick="return getAjaxData('aboutus.do',null);" class="my1">About us</a>
                                &nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
                                <a href="#" class="my1">Help</a>
                                &nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
                                <a href="passwordForm.do" class="my1">Change Password</a>
                                &nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
                                <a href="logout.do" class="my1">Logout</a>
                            </font></div>
                    </td>
                    <td width="14" background="img/rf.gif">&nbsp;</td>
                </tr>
            </table>
            <table width="990" height="111" border="0" align="center" cellpadding="0" cellspacing="0">
                <tr> 
                    <td width="14" background="img/lf.gif">&nbsp;</td>
                    <td width="962" background="img/mcbanner1.gif">
                        <p style="margin-top:55px; margin-bottom:0;margin-left:230px;"><font face="Tahoma" size="2"><strong><i>Welcome <font style="color: #FF0000"><%=MCUtil.getMemberFullName(session)%></font></i></strong></font>
                            <br/><font face="Tahoma" style="font-size: 11px"><b>Administrator</b></font>
                        <br/><font face="Tahoma" style="font-size: 11px"><b>Date: </b><%=DateUtil.today("dd/MM/yyyy")%></font></p>
                    </td>
                    <td width="14" background="img/rf.gif">&nbsp;</td>
                </tr>
            </table>
            <table width="990" border="0" align="center" cellpadding="0" cellspacing="0">
                <tr> 
                    <td width="14" background="img/lf.gif">&nbsp;</td>
                    <td width="220" background="img/im101.gif">                        
                        <%@include  file="/inc/left.inc.jsp" %>   
                    </td>
                    <td width="742">
                        <table width="100%" height="440" border="0" cellpadding="0" cellspacing="0">
                            <tr> 
                                <td background="img/im102.gif">
                                    <table width="100%" height="440" border="0" cellpadding="0" cellspacing="0">
                                        <tr> 
                                            <td>
                                                <div id='ajaxContent'><div align="center"><img src="img/indicator.gif"/></div></div>                                                
                                            </td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                        </table>
                    </td>
                    <td width="14" background="img/rf.gif">&nbsp;</td>
                </tr>
            </table>        
            <%@include  file="/inc/footer.inc.jsp" %>       
        </center>   
        <div id="popupDialog" dojoType="dijit.Dialog"></div>  
        <%@include  file="/inc/attachfiledlg.inc.jsp" %>        
    </body>
</html>
