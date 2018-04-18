/*
 *
 * Copyright (C) 2002-2005 by MyVietnam.net
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or any later version.
 *
 * All copyright notices regarding mvnForum MUST remain intact
 * in the scripts and in the outputted HTML.
 * The "powered by" text/logo with a link back to
 * http://www.mvnForum.com and http://www.MyVietnam.net in the
 * footer of the pages MUST remain visible when the pages
 * are viewed on the internet or intranet.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 *
 *
 */

function clearDefault(el) {
  if (el.defaultValue==el.value) el.value = ""
}
function getKeyCode(evt) {
  if (typeof(evt)=='string') return evt.charCodeAt(0);
  return document.all? event.keyCode: (evt && evt.which)? evt.which: 0;
}

function gotoPage(s) {
    if (s != '') location.href = s;
    return 1;
}

function trimLeft(s) {
    var whitespaces = " \t\n\r";
    for(n = 0; n < s.length; n++) { if (whitespaces.indexOf(s.charAt(n)) == -1) return (n > 0) ? s.substring(n, s.length) : s; }
    return("");
}

function trimRight(s){
    var whitespaces = " \t\n\r";
    for(n = s.length - 1; n  > -1; n--) { if (whitespaces.indexOf(s.charAt(n)) == -1) return (n < (s.length - 1)) ? s.substring(0, n+1) : s; }
    return("");
}

function trim(s) {return ((s == null) ? "" : trimRight(trimLeft(s))); }

function isSelected(field, strBodyHeader) {
    for(i=0; i < field.length; i++) { 
        if (field[i].selected && (trim(field[i].value).length > 0)) {
            return true;
        }
    }
    alert("\"" + strBodyHeader + "\" is a required field. Please choose a selection.");
    field.focus();
    return false;
}

// the num should be character or string with length=1
function isDigit(num) {
    if (num.length > 1) {
        return false;
    }
    var string = "1234567890";
    if (string.indexOf(num) != -1) {
        return true;
    }
    return false;
}

function _IsNumeric(passedVal)
{
    var validChars1 = "0123456789.()-";    
    var c;
    if (passedVal == "") {
        return false;
    }

    for (i = 0; i < passedVal.length; i++) {
        c = passedVal.charAt(i);
        if (validChars1.indexOf(c) == -1) {
            return false;
        }
    }
    return true;
}

var emailPat=/^(.+)@(.+)$/;
var specialChars="\\(\\)><@,;:\\\\\\\"\\.\\[\\]";
var validChars="\[^" + specialChars + "\]";
var quotedString="(\"[^\"]*\")";
var ipDomainPat=/^(\d{1,3})\.(\d{1,3})\.(\d{1,3})\.(\d{1,3})$/;
var atom=validChars + '+';
var word="(" + atom + "|" + quotedString + ")";
var atomPat=new RegExp("^" + atom + "$");
var localPat=new RegExp("^" + word + "(\\." + word + ")*$");
var domainPat=new RegExp("^" + atom + "(\\." + atom +")*$");
var complexPat=/^(.+)<(.+)>$/;
var commentPat=/(.*)\((.*)\)(.*)$/;

function simpleEmailCheck (emailStr) {
    if(emailStr == "") return false;
    var matchArray=emailStr.match(emailPat);
    if (matchArray==null) return false;
    var user=matchArray[1];
    var domain=matchArray[2];
    for (i=0; i<user.length; i++) {
        if (user.charCodeAt(i)>127) return false;
    }
    for (i=0; i<domain.length; i++) {
        if (domain.charCodeAt(i)>127) return false;
    }
    if( !checkUser(user)) return false;
    var IPArray=domain.match(ipDomainPat);
    if (IPArray!=null) {
        for (var i=1;i<=4;i++) {
            if (IPArray[i]>255) return false;
        }
        return true;
    }
    var domArr=domain.split(".");
    var len=domArr.length;
    for (i=0;i<len;i++) {
        if( !checkDomain(domArr[i]) ) return false;
    }
    return true;
}
function checkDomain(domain) {
    var validDomain = true;
    var domainArray = domain.match(commentPat);
    if( domainArray == null ) {
        if( domain.match(atomPat)==-1) validDomain = false;
    } else {
        for(var i=1; i<domainArray.length; i++) {
            if( domainArray[i] != "" && !checkDomain(domainArray[i]) ) validDomain = false;
        }
    }
    return validDomain;
}
function checkUser(user){
    var validUser = true;
    var userArray = user.match(commentPat);
    if( userArray == null ){
        if (user.match(localPat)==null)  validUser = false;
    }
    else {
        for(var i=1; i<userArray.length; i++) {
            if( userArray[i] != "" && !checkUser(userArray[i]) ) validUser = false;
        }
    }
    return validUser;
}
function complexEmailCheck(emailStr){
    if(emailStr == "") return false;
    if(emailStr.indexOf("@")==-1) return false;
    var matchArray=emailStr.match(complexPat);
    if (matchArray==null) {
        if( simpleEmailCheck(emailStr) ) return true;
        else return false;
    }
    var phrase=matchArray[1];
    var routeAddr=matchArray[2];
    if( !simpleEmailCheck(routeAddr) ) return false;
    else return true;
}

function writeEmail(name, domain, subject, body, linkname, classstyle) {
    if (subject == "") subject = null;
    if (body == "") body = null;
    if (linkname == "") linkname = null;
    if (classstyle == "") classstyle = "";
    if (subject == null && body == null) {
      document.write("<a href=\"mailto:" + name + "@" + domain + "\" class=\"" + classstyle + "\">");
    } else if (subject != null && body == null) {
      document.write("<a href=\"mailto:" + name + "@" + domain + "?subject=" + subject + "\" class=\"" + classstyle + "\">");
    } else if (subject == null && body != null) {
      document.write("<a href=\"mailto:" + name + "@" + domain + "?body=" + body + "\" class=\"" + classstyle + "\">");
    } else {
      document.write("<a href=\"mailto:" + name + "@" + domain + "?subject=" + subject + "&body=" + body + "\" class=\"" + classstyle + "\">");
    }
    if (linkname == null) {
      document.write(name + "@" + domain);
    } else {
      document.write(linkname);
    }
    document.write("</a>")
}

function writeDomain(emailstring) {
    var index;
    var result;
    if (!simpleEmailCheck(emailstring)) return emailstring;
    index = emailstring.indexOf("@");
    result= emailstring.substring(index+1, emailstring.length);
    return result;
    //alert(result);
}

function writeEmailTitle(emailstring) {
    if (!simpleEmailCheck(emailstring)) return emailstring;
    var index;
    var result;
    index = emailstring.indexOf("@");
    result= emailstring.substring(0, index);
    //alert(result);
    return result;
}
///////////////////////////////////////////////////////
function popUp(URL)
{
  day = new Date();
  id = day.getTime();
  eval("page" + id + " = window.open(URL, '" + id + "', 'toolbar=0,scrollbars=0,location=0,statusbar=0,menubar=0,resizable=0,width=280,height=415');");
}

function popUpPost(URL)
{
  day = new Date();
  id = day.getTime();
  eval("page" + id + " = window.open(URL, '" + id + "', 'toolbar=0,scrollbars=1,location=0,statusbar=0,menubar=0,resizable=0,width=280,height=415');");
}
  
function openWin(dir,name,w,h)
{
  var left = (screen.availWidth/2) - (w/2);
  var top = (screen.availHeight/2) - (h/2);
  colorWin = window.open(dir, name, 'scrollbars=1, toolbar=0, statusbar=0, width='+w+', height='+h+', left='+left+', top='+top);
  colorWin.focus()
}  

function dodel(gb,gburl){
  if(confirm(gb)){
    window.location = gburl
  }
}

var ie45,ns6,ns4,dom;
if (navigator.appName=="Microsoft Internet Explorer") {
  ie45=parseInt(navigator.appVersion)>=4;
} else if (navigator.appName=="Netscape") {
  ns6=parseInt(navigator.appVersion)>=5;
  ns4=parseInt(navigator.appVersion)<5;
}
dom=ie45 || ns6;

function showhide(id) {
  el = document.all ? document.all[id] : 
    dom ? document.getElementById(id) : 
    document.layers[id];
  els = dom ? el.style : el;
  if (dom){
    if (els.display == "none")
      els.display = "";
    else els.display = "none";
  } else if (ns4){
    if (els.display == "show")
      els.display = "hide";
    else els.display = "show";
  }
}
function detectBrowser(){
    var browserName = 'IE';
    if (window.XMLHttpRequest) { // Non-IE browsers
      browserName = 'NON-IE';
    } else if (window.ActiveXObject) { // IE
      browserName = 'IE';
    }
    return browserName;
}

function isValidTime(timeStr) {
    // Checks if time is in HH:MM format.

    var timePat = /^(\d{1,2}):(\d{1,2})?$/;
    var matchArray = timeStr.match(timePat);
    if (matchArray == null) {
        //alert("Time is not in a valid format.");
        return false;
    }
    hour = matchArray[1];
    minute = matchArray[2];

    if (hour < 0  || hour > 23) {
        //alert("Hour must be between 0 and 23");
        return false;
    }

    if (minute<0 || minute > 59) {
        //alert ("Minute must be between 0 and 59.");
        return false;
    }

    return true;
}