<%@ page language="java" pageEncoding="UTF-8" %> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ page import="com.venus.core.util.DateUtil"%>
<%@ page import="com.venus.mc.util.MCUtil"%>

<%
            String ftype = MCUtil.getParameter("ftype", request);
            String pid = MCUtil.getParameter("pid", request);
            String comments = MCUtil.getParameter("comments", request);
%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>Flash HTML</title>
        
        <link href="js/dojotoolkit/dijit/themes/dijit.css" rel="stylesheet" />
        <link href="js/dojotoolkit/dijit/themes/tundra/form/Button.css" rel="stylesheet" />
        <link href="js/dojotoolkit/dijit/themes/tundra/ProgressBar.css" rel="stylesheet" />
        <link href="js/dojotoolkit/dojox/form/resources/FileUploader.css" rel="stylesheet" />
        <script type='text/javascript' src='js/mc_common.js'></script>
        <script src="js/dojotoolkit/dojo/dojo.js" djConfig="isDebug: false,parseOnLoad:true"></script>
        <script>
            dojo.require("dojox.form.FileUploader");
            dojo.require("dijit.form.Button");
            dojo.require("dijit.ProgressBar");
            
            dojo.addOnLoad(function() {                
                var props = {
                    isDebug:false,
                    force:"html",
                    hoverClass:"uploadHover",
                    activeClass:"uploadPress",
                    disabledClass:"uploadDisabled",
                    uploadUrl:"uploadFile.do",
                    htmlFieldName:"theFile"           
                };
                
                if (dojo.byId("btnF")) {
                    dojo.byId("fFiles").value = ""; 
                    var f = new dojox.form.FileUploader(dojo.mixin({
                        progressWidgetId:"progressBar",
                        showProgress:true,
                        fileListId:"fFiles",
                        tabIndex:5,
                        selectMultipleFiles:true,
                        deferredUploading:false
                    },props), "btnF");
                    
                    dojo.connect(dijit.byId("fSubmit"), "onClick", function(){
                        f.submit(dojo.byId("formF"));
                        //f.upload();
                    });
                    
                    dojo.connect(f, "onChange", function(dataArray){
                        // alert(dataArray);
                        console.log("onChange.data:", dataArray);
                    });
                    dojo.connect(f, "onComplete", function(dataArray){
                        //alert(dataArray);      
                        dojo.forEach(dataArray, function(d){                           
                            if (d.type=='error') {
                                var errorMessageDiv = dojo.byId('errorMessageDiv');
                                var strError = '<bean:message key="message.file.cannotupload"/> ' + d.file;
                                errorMessageDiv.innerHTML = strError;   
                                strError = null;
                                errorMessageDiv = null;
                            } else {
                                var errorMessageDiv = dojo.byId('errorMessageDiv');
                                var strError = 'Upload ' + d.name + ' successful.';
                                errorMessageDiv.className = 'successful';
                                errorMessageDiv.innerHTML = strError;   
                                strError = null;
                                errorMessageDiv = null;
                            }
                        });
                        hideUploadDialog();                        
                    });                   
                }                
            });
        </script>        
        <style>
            html, body{
                font-family:sans-serif;
                font-size:12px;
            }
            .uploadBtn{
                border:1px solid #333333;
                background:url(js/dojotoolkit/dijit/themes/soria/images/buttonEnabled.png) #d0d0d0 repeat-x scroll 0px top;
                font-size:12px;
                font-family:Arial;
                width:100px;
                height:30px;
                line-height:50px;
                vertical-align:middle; /* emulates a <button> */
                text-align:center;
            }
            .uploadHover{
                background-image:url(js/dojotoolkit/dijit/themes/soria/images/buttonHover.png);
                cursor:pointer;
                font-weight:bold;
                font-style:italic;
                font-family:serif;
            }
            .uploadPress{
                background-image:url(js/dojotoolkit/dijit/themes/soria/images/buttonActive.png);
            }
            .uploadDisabled{
                background-image:none;
                background-color:#666;
                color:#999;
                border:1px solid #999;
                font-family:serif;
                font-style:italic;
            }
            .progBar{
                width:294px;
                display:none;
            }
            .form{
                
                border:1px solid #ccc;
                margin:5px;
                padding:3px;
                position:relative;
            }
            .form, .thumbList{
                float:left;
            }
            .thumbList{
                width:300px;
                border:1px solid #ccc;
                min-height:100px;
                margin:5px;
                padding:3px;
            }
            #fFiles, #hFiles{
                width:200px;
                height:30px;
                overflow-x:hidden;
                overflow-y:auto;
                border:1px solid #ccc;
            }
            .form .field{
                width:197px;
            }
            .tbl{
                width:100%;
            }
            .tbl td{
                width:50%;
                vertical-align:top;
            }          
          
            .error {
                font-family: Tahoma;
                font-size:11pt;
                color:#FF0000;
            }
            .sucessful {
                font-family: Tahoma;
                font-size:11pt;
                color:#0000FF;
            }
        </style>
    </head>
    <body class="tundra">
        <form id="formF" class="form">
            <input type="hidden" name="ftype" value="<%=ftype%>"> 
            <input type="hidden" name="pid" value="<%=pid%>"/>
            <input type="hidden" name="comments" value="<%=comments%>"/>
            <table>
                <tr><td colspan="3"><div id="errorMessageDiv" class="error"></div></td></tr>
                 <tr>
                    <td colspan="3" align="center"><div indeterminate="false" id="progressBar" class="progBar" dojoType="dijit.ProgressBar"></div></td>
                </tr>
                <tr>
                    <td>File</td>
                    <td>                        
                        <div id="fFiles" class="field"></div>
                    </td>
                    <td>
                        <div tabIndex="5" id="btnF" class="uploadBtn btn">Browser...</div>
                    </td>
                </tr>
                <tr>
                    <td colspan="3" align="center"><button tabIndex="6" id="fSubmit" class="btn" dojoType="dijit.form.Button">Upload</button></td>
                </tr>               
            </table>    
        </form>           
    </body>    
</html>
