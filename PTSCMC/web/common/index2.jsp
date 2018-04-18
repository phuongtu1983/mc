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
        <style>
            .mcMenuIconNew {           
                background-image: url(images/ico_edit.gif);
                background-position: 0px;
                width: 16px;
                height: 16px;
            }
            .mcMenuIconList {           
                background-image: url(images/ico_list.gif);
                background-position: 0px;
                width: 16px;
                height: 16px;
            }
            #popupDialog_underlay { 
                background-color:green; 
            }               
            .lbl9 {                
                font-size:9pt;
                font-family:Tahoma;     
                color:#000080;
            }
            .lbl9r {                
                font-size:9pt;
                font-family:Tahoma;     
                color:#FF0000;
            }
            .lbl10b {                
                font-size:10pt;
                font-family:Tahoma;     
                font-weight:bold;
                color:#000080;
            }
            .lbl10 {                
                font-size:10pt;
                font-family:Tahoma;     
                color:#000080;
            }
            .lbl8 {                
                font-size:8pt;
                font-family:Tahoma;                
                color:#000080;
            }
            .cart_textbox {
                font-family: Arial; 
                font-size: 9pt; 
                border: 1px solid #FFFFFF;
            }
            .cart_row{
                font-family: Tahoma; 
                font-size: 7pt; 
                text-align: center; 

                border-left: 0px solid #FFFFFF; 
                border-right: 0px solid #CCCCCC; 
                border-bottom: 1px solid #808080; 
                border-top: 1px solid #FFFFFF;
            }
        </style>
        <script type='text/javascript' src='js/Ajax.js'></script>
        <script type='text/javascript' src='js/library.js'></script>
        <script type='text/javascript' src='js/purchasing.js'></script>
        <script type='text/javascript' src='js/store.js'></script>
        <script type='text/javascript' src='js/mc_common.js'></script>
        <script type='text/javascript' src='js/ezcalendar.js'></script>
        <script type='text/javascript' src='js/NumberFormat.js'></script> 
        <script type='text/javascript' src='js/date.js'></script>
        <script type='text/javascript' src='js/md5.js'></script>
        <script src="js/dojotoolkit/dojo/dojo.js" djConfig="parseOnLoad: true"></script>      
        
        <script>
            dojo.require("dojo.io.iframe"); 
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
            dojo.require("dijit.form.ComboBox");
            
            sessionId='<%=session.getAttribute(Constants.SESSION_ID)%>';       
            function onMCStart() {                
                initCalendar();
                if(window.name!='') {                    
                    eval(window.name);
                    window.name='';
                } else {
                    loadVendorList();
                }            
            }      
            window.onload=onMCStart;
        </script>               
    </head>
    <body leftmargin="0" topmargin="0" class="tundra">
        <script type="text/javascript" src="js/wz_tooltip.js"></script>
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
            <table width="990" height="126" border="0" align="center" cellpadding="0" cellspacing="0">
                <tr> 
                    <td width="14" background="img/lf.gif">&nbsp;</td>
                    <td width="962" background="img/mcbanner1.gif">
                        <p style="margin-top:55px; margin-bottom:0;margin-left:230px;"><font face="Tahoma" size="2"><strong><i>Welcome <font style="color: #FF0000"><%=MCUtil.getMemberFullName(session)%></font></i></strong></font>
                            <br/><font face="Tahoma" style="font-size: 11px"><b><%=MCUtil.getOrganizationName(session)%></b></font>
                        <br/><font face="Tahoma" style="font-size: 11px"><b>Date: </b><%=DateUtil.today("dd/MM/yyyy")%></font></p>
                    </td>
                    <td width="14" background="img/rf.gif">&nbsp;</td>
                </tr>
                <tr> 
                    <td width="14" background="img/lf.gif">&nbsp;</td>
                    <td width="962"><div style="background-color:#F7F7FF">&nbsp;</div></td>
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
                                        <tr valign="top"><td><div id='ajaxContent'><div align="center"><img src="img/indicator.gif"/></div></div></td></tr>
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
