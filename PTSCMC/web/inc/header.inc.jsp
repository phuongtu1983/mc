<%@ page import="com.venus.mc.util.MCUtil"%>
<%@ page import="com.venus.core.util.DateUtil"%>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ page import="com.venus.core.auth.OnlineUser"%>
<%
    OnlineUser user = (OnlineUser) session.getAttribute(Constants.EMPLOYEE_OBJ);
%>
<div><img alt="fdgd" src="images/mm_ptsc_banner2.gif"></div>
<div id="banner"><script type="text/javascript">
    AC_FL_RunContent('wmode', 'transparent', 'codebase','http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=7,0,19,0','width','964','height','245','src','images/flash_banner02_vn','quality','high', 'pluginspage','http://www.macromedia.com/go/getflashplayer','movie','images/flash_banner02_vn' ); //end AC code				
    </script><embed wmode="transparent" src="images/flash_banner02_vn.swf" quality="high" pluginspage="http://www.macromedia.com/go/getflashplayer" type="application/x-shockwave-flash" height="245" width="964"> </div>
<div>
    <div class="xleft"><%=DateUtil.today("dd-MMM-yyyy")%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <%=MCUtil.displayMemberInfo(user, false)%></div>
    <div class="xright"><a href="docs/help.doc" onclick="popWin = window.open('docs/help.doc','myWin');return false"><bean:message key="message.help"/></a> |
        <a href="memberLogoff.do"><bean:message key="message.exit"/></a></div>
    <div class="clear"><hr></div></div>