/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.warning;

import com.venus.mc.bean.DnWarningBean;
import com.venus.mc.bean.DniWarningBean;
import com.venus.mc.bean.McWarningBean;
import com.venus.mc.bean.McoWarningBean;
import com.venus.mc.bean.RepairPlanWarningBean;
import com.venus.mc.bean.VerifiedPlanWarningBean;
import com.venus.mc.contract.InvoiceContractFormBean;
import com.venus.mc.util.MCUtil;
import java.util.ArrayList;

/**
 *
 * @author phuongtu
 */
public class WarningContentHandle {

    public static String getDnsDetailForWarning(String header, ArrayList details) throws Exception {

        String mailContent = "	<html><head><meta http-equiv=\"Content-Language\" content=\"en-us\"><meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"> ";
        mailContent += "	<title>" + header + "</title><style type=\"text/css\">A { COLOR: #000000; TEXT-DECORATION: none } ";
        mailContent += "	A:hover { COLOR: #000000; cursor: hand; } .cart_row {	border-bottom: 1px solid #CCCCCC; }	.cart_row_red {	border-bottom:1px solid #CCCCCC; border-right:1px solid #FF6565; border-left-style:none; border-left-width:medium; } ";
        mailContent += "	.cart_textbox { font-family: Arial; font-size: 9pt; border: 1px solid #FFFFFF; } </style> ";
        mailContent += "	<SCRIPT language=JavaScript> var enablepersist=\"off\" var collapseprevious=\"no\" if (document.getElementById){document.write('<style type=\"text/css\">') document.write('.switchcontent{ }') document.write('</style>')} if (document.getElementById){document.write('<style type=\"text/css\">') document.write('.switchcontent_a{display:none;}') document.write('</style>')} function getElementbyClass(classname){ccollect=new Array()var inc=0 var alltags=document.all? document.all : document.getElementsByTagName(\"*\")for (i=0; i<alltags.length; i++){if (alltags[i].className==classname) ccollect[inc++]=alltags[i]}} function contractcontent(omit){var inc=0 while (ccollect[inc]){ if (ccollect[inc].id!=omit) ccollect[inc].style.display=\"none\" inc++ }} function expandcontent(cid){ if (typeof ccollect!=\"undefined\"){ if (collapseprevious==\"yes\") contractcontent(cid) document.getElementById(cid).style.display=(document.getElementById(cid).style.display!=\"none\")? \"none\" : \"block\" } } function expandcontent_a(cid){ if (typeof ccollect!=\"undefined\"){ if (collapseprevious==\"yes\") contractcontent(cid) document.getElementById(cid).style.display=(document.getElementById(cid).style.display!=\"block\")? \"block\" : \"none\" } } function revivecontent(){ contractcontent(\"omitnothing\") selectedItem=getselectedItem() selectedComponents=selectedItem.split(\"|\") for (i=0; i<selectedComponents.length-1; i++) document.getElementById(selectedComponents[i]).style.display=\"block\" } function get_cookie(Name) { var search = Name + \"=\" var returnvalue = \"\"; if (document.cookie.length > 0) { offset = document.cookie.indexOf(search) if (offset != -1) { offset += search.length end = document.cookie.indexOf(\";\", offset); if (end == -1) end = document.cookie.length; returnvalue=unescape(document.cookie.substring(offset, end)) } }  return returnvalue; }  function getselectedItem(){ if (get_cookie(window.location.pathname) != \"\"){ selectedItem=get_cookie(window.location.pathname) return selectedItem } else return \"\" }  function saveswitchstate(){ var inc=0, selectedItem=\"\" while (ccollect[inc]){ if (ccollect[inc].style.display==\"block\") selectedItem+=ccollect[inc].id+\"|\" inc++ } document.cookie=window.location.pathname+\"=\"+selectedItem }  function do_onload(){ getElementbyClass(\"switchcontent\") if (enablepersist==\"on\" && typeof ccollect!=\"undefined\") revivecontent() } if (window.addEventListener) window.addEventListener(\"load\", do_onload, false) else if (window.attachEvent) window.attachEvent(\"onload\", do_onload) else if (document.getElementById) window.onload=do_onload if (enablepersist==\"on\" && document.getElementById) window.onunload=saveswitchstate </SCRIPT> ";
        mailContent += "	</head> ";
        mailContent += "	<body topmargin=\"0\" leftmargin=\"0\" bgcolor=\"#FFFFFF\" style=\"font-family: Tahoma\">	<div align=\"center\"> ";
        mailContent += "	<table border=\"0\" width=\"650\" cellspacing=\"0\" cellpadding=\"0\" height=\"78\" style=\"border-right-width: 0px; border-top-width: 0px\"> ";
        mailContent += "	<tr><td class=\"cart_b\" width=\"761\" height=\"31\" align=\"center\"><font color=\"#CCCCCC\" face=\"Wingdings\" style=\"font-size: 13pt\">? ? ? ? ? ? ? ? ? ? ? ? ? ? ? ? ? ? ? ?</font></td></tr> ";
        mailContent += "	</tr><tr><td class=\"cart_b\" width=\"761\" height=\"47\" style=\"border-left-width: 1px; border-right-width: 1px; border-top: 1px solid #C0C0C0; border-bottom: 1px solid #C0C0C0; font-family:Tahoma; margin-left:6; margin-top:0; margin-bottom:0; font-size:9pt\"> ";
        mailContent += "	</i></font></td></tr></table> ";

        DnWarningBean bean = null;
        if (details.size() > 0) {
            mailContent += "<table style=\"width: 100%;\" class=\"its\"><thead><tr>";
            mailContent += "<th>" + MCUtil.getBundleString("message.dn.matName") + "</th>";
            mailContent += "<th>" + MCUtil.getBundleString("message.dn.unit") + "</th>";
            mailContent += "<th>" + MCUtil.getBundleString("message.dn.quantity") + "</th>";
            mailContent += "<th>" + MCUtil.getBundleString("message.dn.requestNumber") + "</th>";
            mailContent += "<th>" + MCUtil.getBundleString("message.dn.dnNumber") + "</th>";
            mailContent += "<th>" + MCUtil.getBundleString("message.dn.expectedDate") + "</th>";
            mailContent += "</tr></thead><tbody>";
            for (int i = 0; i < details.size(); i++) {
                bean = (DnWarningBean) details.get(i);
                mailContent += "<tr>";
                mailContent += "<td><span>" + bean.getMatName() + "</span></td>";
                mailContent += "<td><span>" + bean.getMatUnit() + "</span></td>";
                mailContent += "<td><span>" + bean.getQuantity() + "</span></td>";
                mailContent += "<td><span>" + bean.getReqNumber() + "</span></td>";
                mailContent += "<td><span>" + bean.getDnNumber() + "</span></td>";
                mailContent += "<td><span>" + bean.getExpectedDate() + "</span></td>";
                mailContent += "</tr>";
            }
            mailContent += "</tbody></table>";
        }
        mailContent += "</div></body></html>";
        return mailContent;
    }

    public static String getInvoicesForWarning(String header, ArrayList details) throws Exception {

        String mailContent = "	<html><head><meta http-equiv=\"Content-Language\" content=\"en-us\"><meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"> ";
        mailContent += "	<title>" + header + "</title><style type=\"text/css\">A { COLOR: #000000; TEXT-DECORATION: none } ";
        mailContent += "	A:hover { COLOR: #000000; cursor: hand; } .cart_row {	border-bottom: 1px solid #CCCCCC; }	.cart_row_red {	border-bottom:1px solid #CCCCCC; border-right:1px solid #FF6565; border-left-style:none; border-left-width:medium; } ";
        mailContent += "	.cart_textbox { font-family: Arial; font-size: 9pt; border: 1px solid #FFFFFF; } </style> ";
        mailContent += "	<SCRIPT language=JavaScript> var enablepersist=\"off\" var collapseprevious=\"no\" if (document.getElementById){document.write('<style type=\"text/css\">') document.write('.switchcontent{ }') document.write('</style>')} if (document.getElementById){document.write('<style type=\"text/css\">') document.write('.switchcontent_a{display:none;}') document.write('</style>')} function getElementbyClass(classname){ccollect=new Array()var inc=0 var alltags=document.all? document.all : document.getElementsByTagName(\"*\")for (i=0; i<alltags.length; i++){if (alltags[i].className==classname) ccollect[inc++]=alltags[i]}} function contractcontent(omit){var inc=0 while (ccollect[inc]){ if (ccollect[inc].id!=omit) ccollect[inc].style.display=\"none\" inc++ }} function expandcontent(cid){ if (typeof ccollect!=\"undefined\"){ if (collapseprevious==\"yes\") contractcontent(cid) document.getElementById(cid).style.display=(document.getElementById(cid).style.display!=\"none\")? \"none\" : \"block\" } } function expandcontent_a(cid){ if (typeof ccollect!=\"undefined\"){ if (collapseprevious==\"yes\") contractcontent(cid) document.getElementById(cid).style.display=(document.getElementById(cid).style.display!=\"block\")? \"block\" : \"none\" } } function revivecontent(){ contractcontent(\"omitnothing\") selectedItem=getselectedItem() selectedComponents=selectedItem.split(\"|\") for (i=0; i<selectedComponents.length-1; i++) document.getElementById(selectedComponents[i]).style.display=\"block\" } function get_cookie(Name) { var search = Name + \"=\" var returnvalue = \"\"; if (document.cookie.length > 0) { offset = document.cookie.indexOf(search) if (offset != -1) { offset += search.length end = document.cookie.indexOf(\";\", offset); if (end == -1) end = document.cookie.length; returnvalue=unescape(document.cookie.substring(offset, end)) } }  return returnvalue; }  function getselectedItem(){ if (get_cookie(window.location.pathname) != \"\"){ selectedItem=get_cookie(window.location.pathname) return selectedItem } else return \"\" }  function saveswitchstate(){ var inc=0, selectedItem=\"\" while (ccollect[inc]){ if (ccollect[inc].style.display==\"block\") selectedItem+=ccollect[inc].id+\"|\" inc++ } document.cookie=window.location.pathname+\"=\"+selectedItem }  function do_onload(){ getElementbyClass(\"switchcontent\") if (enablepersist==\"on\" && typeof ccollect!=\"undefined\") revivecontent() } if (window.addEventListener) window.addEventListener(\"load\", do_onload, false) else if (window.attachEvent) window.attachEvent(\"onload\", do_onload) else if (document.getElementById) window.onload=do_onload if (enablepersist==\"on\" && document.getElementById) window.onunload=saveswitchstate </SCRIPT> ";
        mailContent += "	</head> ";
        mailContent += "	<body topmargin=\"0\" leftmargin=\"0\" bgcolor=\"#FFFFFF\" style=\"font-family: Tahoma\">	<div align=\"center\"> ";
        mailContent += "	<table border=\"0\" width=\"650\" cellspacing=\"0\" cellpadding=\"0\" height=\"78\" style=\"border-right-width: 0px; border-top-width: 0px\"> ";
        mailContent += "	<tr><td class=\"cart_b\" width=\"761\" height=\"31\" align=\"center\"><font color=\"#CCCCCC\" face=\"Wingdings\" style=\"font-size: 13pt\">? ? ? ? ? ? ? ? ? ? ? ? ? ? ? ? ? ? ? ?</font></td></tr> ";
        mailContent += "	</tr><tr><td class=\"cart_b\" width=\"761\" height=\"47\" style=\"border-left-width: 1px; border-right-width: 1px; border-top: 1px solid #C0C0C0; border-bottom: 1px solid #C0C0C0; font-family:Tahoma; margin-left:6; margin-top:0; margin-bottom:0; font-size:9pt\"> ";
        mailContent += "	</i></font></td></tr></table> ";

        InvoiceContractFormBean bean = null;
        if (details.size() > 0) {
            mailContent += "<table style=\"width: 100%;\" class=\"its\"><thead><tr>";
            mailContent += "<th>" + MCUtil.getBundleString("message.contract.bill.number") + "</th>";
            mailContent += "<th>" + MCUtil.getBundleString("message.contract.number") + "</th>";
            mailContent += "<th>" + MCUtil.getBundleString("message.contract.bill.payment.date") + "</th>";
            mailContent += "<th>" + MCUtil.getBundleString("message.contract.bill.amount") + "</th>";
            mailContent += "</tr></thead><tbody>";
            for (int i = 0; i < details.size(); i++) {
                bean = (InvoiceContractFormBean) details.get(i);
                mailContent += "<tr>";
                mailContent += "<td><span>" + bean.getInvoice() + "</span></td>";
                mailContent += "<td><span>" + bean.getContractNumber() + "</span></td>";
                mailContent += "<td><span>" + bean.getPaymentDate() + "</span></td>";
                mailContent += "<td><span>" + bean.getAmount() + "</span></td>";
                mailContent += "</tr>";
            }
            mailContent += "</tbody></table>";
        }
        mailContent += "</div></body></html>";
        return mailContent;
    }

    public static String getMrirForWarning(String header, ArrayList details) throws Exception {

        String mailContent = "	<html><head><meta http-equiv=\"Content-Language\" content=\"en-us\"><meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"> ";
        mailContent += "	<title>" + header + "</title><style type=\"text/css\">A { COLOR: #000000; TEXT-DECORATION: none } ";
        mailContent += "	A:hover { COLOR: #000000; cursor: hand; } .cart_row {	border-bottom: 1px solid #CCCCCC; }	.cart_row_red {	border-bottom:1px solid #CCCCCC; border-right:1px solid #FF6565; border-left-style:none; border-left-width:medium; } ";
        mailContent += "	.cart_textbox { font-family: Arial; font-size: 9pt; border: 1px solid #FFFFFF; } </style> ";
        mailContent += "	<SCRIPT language=JavaScript> var enablepersist=\"off\" var collapseprevious=\"no\" if (document.getElementById){document.write('<style type=\"text/css\">') document.write('.switchcontent{ }') document.write('</style>')} if (document.getElementById){document.write('<style type=\"text/css\">') document.write('.switchcontent_a{display:none;}') document.write('</style>')} function getElementbyClass(classname){ccollect=new Array()var inc=0 var alltags=document.all? document.all : document.getElementsByTagName(\"*\")for (i=0; i<alltags.length; i++){if (alltags[i].className==classname) ccollect[inc++]=alltags[i]}} function contractcontent(omit){var inc=0 while (ccollect[inc]){ if (ccollect[inc].id!=omit) ccollect[inc].style.display=\"none\" inc++ }} function expandcontent(cid){ if (typeof ccollect!=\"undefined\"){ if (collapseprevious==\"yes\") contractcontent(cid) document.getElementById(cid).style.display=(document.getElementById(cid).style.display!=\"none\")? \"none\" : \"block\" } } function expandcontent_a(cid){ if (typeof ccollect!=\"undefined\"){ if (collapseprevious==\"yes\") contractcontent(cid) document.getElementById(cid).style.display=(document.getElementById(cid).style.display!=\"block\")? \"block\" : \"none\" } } function revivecontent(){ contractcontent(\"omitnothing\") selectedItem=getselectedItem() selectedComponents=selectedItem.split(\"|\") for (i=0; i<selectedComponents.length-1; i++) document.getElementById(selectedComponents[i]).style.display=\"block\" } function get_cookie(Name) { var search = Name + \"=\" var returnvalue = \"\"; if (document.cookie.length > 0) { offset = document.cookie.indexOf(search) if (offset != -1) { offset += search.length end = document.cookie.indexOf(\";\", offset); if (end == -1) end = document.cookie.length; returnvalue=unescape(document.cookie.substring(offset, end)) } }  return returnvalue; }  function getselectedItem(){ if (get_cookie(window.location.pathname) != \"\"){ selectedItem=get_cookie(window.location.pathname) return selectedItem } else return \"\" }  function saveswitchstate(){ var inc=0, selectedItem=\"\" while (ccollect[inc]){ if (ccollect[inc].style.display==\"block\") selectedItem+=ccollect[inc].id+\"|\" inc++ } document.cookie=window.location.pathname+\"=\"+selectedItem }  function do_onload(){ getElementbyClass(\"switchcontent\") if (enablepersist==\"on\" && typeof ccollect!=\"undefined\") revivecontent() } if (window.addEventListener) window.addEventListener(\"load\", do_onload, false) else if (window.attachEvent) window.attachEvent(\"onload\", do_onload) else if (document.getElementById) window.onload=do_onload if (enablepersist==\"on\" && document.getElementById) window.onunload=saveswitchstate </SCRIPT> ";
        mailContent += "	</head> ";
        mailContent += "	<body topmargin=\"0\" leftmargin=\"0\" bgcolor=\"#FFFFFF\" style=\"font-family: Tahoma\">	<div align=\"center\"> ";
        mailContent += "	<table border=\"0\" width=\"650\" cellspacing=\"0\" cellpadding=\"0\" height=\"78\" style=\"border-right-width: 0px; border-top-width: 0px\"> ";
        mailContent += "	<tr><td class=\"cart_b\" width=\"761\" height=\"31\" align=\"center\"><font color=\"#CCCCCC\" face=\"Wingdings\" style=\"font-size: 13pt\">? ? ? ? ? ? ? ? ? ? ? ? ? ? ? ? ? ? ? ?</font></td></tr> ";
        mailContent += "	</tr><tr><td class=\"cart_b\" width=\"761\" height=\"47\" style=\"border-left-width: 1px; border-right-width: 1px; border-top: 1px solid #C0C0C0; border-bottom: 1px solid #C0C0C0; font-family:Tahoma; margin-left:6; margin-top:0; margin-bottom:0; font-size:9pt\"> ";
        mailContent += "	</i></font></td></tr></table> ";

        DniWarningBean bean = null;
        if (details.size() > 0) {
            mailContent += "<table style=\"width: 100%;\" class=\"its\"><thead><tr>";
            mailContent += "<th>" + MCUtil.getBundleString("message.dn.dnNumber") + "</th>";
            mailContent += "<th>" + MCUtil.getBundleString("message.dn.moveCreateMrir") + "</th>";
            mailContent += "</tr></thead><tbody>";
            for (int i = 0; i < details.size(); i++) {
                bean = (DniWarningBean) details.get(i);
                mailContent += "<tr>";
                mailContent += "<td><span>" + bean.getDnNumber() + "</span></td>";
                mailContent += "<td><span>" + bean.getMoveCreateMrir() + "</span></td>";
                mailContent += "</tr>";
            }
            mailContent += "</tbody></table>";
        }
        mailContent += "</div></body></html>";
        return mailContent;
    }

    public static String getMsvForWarning(String header, ArrayList details) throws Exception {

        String mailContent = "	<html><head><meta http-equiv=\"Content-Language\" content=\"en-us\"><meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"> ";
        mailContent += "	<title>" + header + "</title><style type=\"text/css\">A { COLOR: #000000; TEXT-DECORATION: none } ";
        mailContent += "	A:hover { COLOR: #000000; cursor: hand; } .cart_row {	border-bottom: 1px solid #CCCCCC; }	.cart_row_red {	border-bottom:1px solid #CCCCCC; border-right:1px solid #FF6565; border-left-style:none; border-left-width:medium; } ";
        mailContent += "	.cart_textbox { font-family: Arial; font-size: 9pt; border: 1px solid #FFFFFF; } </style> ";
        mailContent += "	<SCRIPT language=JavaScript> var enablepersist=\"off\" var collapseprevious=\"no\" if (document.getElementById){document.write('<style type=\"text/css\">') document.write('.switchcontent{ }') document.write('</style>')} if (document.getElementById){document.write('<style type=\"text/css\">') document.write('.switchcontent_a{display:none;}') document.write('</style>')} function getElementbyClass(classname){ccollect=new Array()var inc=0 var alltags=document.all? document.all : document.getElementsByTagName(\"*\")for (i=0; i<alltags.length; i++){if (alltags[i].className==classname) ccollect[inc++]=alltags[i]}} function contractcontent(omit){var inc=0 while (ccollect[inc]){ if (ccollect[inc].id!=omit) ccollect[inc].style.display=\"none\" inc++ }} function expandcontent(cid){ if (typeof ccollect!=\"undefined\"){ if (collapseprevious==\"yes\") contractcontent(cid) document.getElementById(cid).style.display=(document.getElementById(cid).style.display!=\"none\")? \"none\" : \"block\" } } function expandcontent_a(cid){ if (typeof ccollect!=\"undefined\"){ if (collapseprevious==\"yes\") contractcontent(cid) document.getElementById(cid).style.display=(document.getElementById(cid).style.display!=\"block\")? \"block\" : \"none\" } } function revivecontent(){ contractcontent(\"omitnothing\") selectedItem=getselectedItem() selectedComponents=selectedItem.split(\"|\") for (i=0; i<selectedComponents.length-1; i++) document.getElementById(selectedComponents[i]).style.display=\"block\" } function get_cookie(Name) { var search = Name + \"=\" var returnvalue = \"\"; if (document.cookie.length > 0) { offset = document.cookie.indexOf(search) if (offset != -1) { offset += search.length end = document.cookie.indexOf(\";\", offset); if (end == -1) end = document.cookie.length; returnvalue=unescape(document.cookie.substring(offset, end)) } }  return returnvalue; }  function getselectedItem(){ if (get_cookie(window.location.pathname) != \"\"){ selectedItem=get_cookie(window.location.pathname) return selectedItem } else return \"\" }  function saveswitchstate(){ var inc=0, selectedItem=\"\" while (ccollect[inc]){ if (ccollect[inc].style.display==\"block\") selectedItem+=ccollect[inc].id+\"|\" inc++ } document.cookie=window.location.pathname+\"=\"+selectedItem }  function do_onload(){ getElementbyClass(\"switchcontent\") if (enablepersist==\"on\" && typeof ccollect!=\"undefined\") revivecontent() } if (window.addEventListener) window.addEventListener(\"load\", do_onload, false) else if (window.attachEvent) window.attachEvent(\"onload\", do_onload) else if (document.getElementById) window.onload=do_onload if (enablepersist==\"on\" && document.getElementById) window.onunload=saveswitchstate </SCRIPT> ";
        mailContent += "	</head> ";
        mailContent += "	<body topmargin=\"0\" leftmargin=\"0\" bgcolor=\"#FFFFFF\" style=\"font-family: Tahoma\">	<div align=\"center\"> ";
        mailContent += "	<table border=\"0\" width=\"650\" cellspacing=\"0\" cellpadding=\"0\" height=\"78\" style=\"border-right-width: 0px; border-top-width: 0px\"> ";
        mailContent += "	<tr><td class=\"cart_b\" width=\"761\" height=\"31\" align=\"center\"><font color=\"#CCCCCC\" face=\"Wingdings\" style=\"font-size: 13pt\">? ? ? ? ? ? ? ? ? ? ? ? ? ? ? ? ? ? ? ?</font></td></tr> ";
        mailContent += "	</tr><tr><td class=\"cart_b\" width=\"761\" height=\"47\" style=\"border-left-width: 1px; border-right-width: 1px; border-top: 1px solid #C0C0C0; border-bottom: 1px solid #C0C0C0; font-family:Tahoma; margin-left:6; margin-top:0; margin-bottom:0; font-size:9pt\"> ";
        mailContent += "	</i></font></td></tr></table> ";

        DniWarningBean bean = null;
        if (details.size() > 0) {
            mailContent += "<table style=\"width: 100%;\" class=\"its\"><thead><tr>";
            mailContent += "<th>" + MCUtil.getBundleString("message.dn.dnNumber") + "</th>";
            mailContent += "<th>" + MCUtil.getBundleString("message.dn.receiveMsv") + "</th>";
            mailContent += "</tr></thead><tbody>";
            for (int i = 0; i < details.size(); i++) {
                bean = (DniWarningBean) details.get(i);
                mailContent += "<tr>";
                mailContent += "<td><span>" + bean.getDnNumber() + "</span></td>";
                mailContent += "<td><span>" + bean.getReceiveMsv() + "</span></td>";
                mailContent += "</tr>";
            }
            mailContent += "</tbody></table>";
        }
        mailContent += "</div></body></html>";
        return mailContent;
    }

    public static String getRepairPlanForWarning(String header, ArrayList details) throws Exception {

        String mailContent = "	<html><head><meta http-equiv=\"Content-Language\" content=\"en-us\"><meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"> ";
        mailContent += "	<title>" + header + "</title><style type=\"text/css\">A { COLOR: #000000; TEXT-DECORATION: none } ";
        mailContent += "	A:hover { COLOR: #000000; cursor: hand; } .cart_row {	border-bottom: 1px solid #CCCCCC; }	.cart_row_red {	border-bottom:1px solid #CCCCCC; border-right:1px solid #FF6565; border-left-style:none; border-left-width:medium; } ";
        mailContent += "	.cart_textbox { font-family: Arial; font-size: 9pt; border: 1px solid #FFFFFF; } </style> ";
        mailContent += "	<SCRIPT language=JavaScript> var enablepersist=\"off\" var collapseprevious=\"no\" if (document.getElementById){document.write('<style type=\"text/css\">') document.write('.switchcontent{ }') document.write('</style>')} if (document.getElementById){document.write('<style type=\"text/css\">') document.write('.switchcontent_a{display:none;}') document.write('</style>')} function getElementbyClass(classname){ccollect=new Array()var inc=0 var alltags=document.all? document.all : document.getElementsByTagName(\"*\")for (i=0; i<alltags.length; i++){if (alltags[i].className==classname) ccollect[inc++]=alltags[i]}} function contractcontent(omit){var inc=0 while (ccollect[inc]){ if (ccollect[inc].id!=omit) ccollect[inc].style.display=\"none\" inc++ }} function expandcontent(cid){ if (typeof ccollect!=\"undefined\"){ if (collapseprevious==\"yes\") contractcontent(cid) document.getElementById(cid).style.display=(document.getElementById(cid).style.display!=\"none\")? \"none\" : \"block\" } } function expandcontent_a(cid){ if (typeof ccollect!=\"undefined\"){ if (collapseprevious==\"yes\") contractcontent(cid) document.getElementById(cid).style.display=(document.getElementById(cid).style.display!=\"block\")? \"block\" : \"none\" } } function revivecontent(){ contractcontent(\"omitnothing\") selectedItem=getselectedItem() selectedComponents=selectedItem.split(\"|\") for (i=0; i<selectedComponents.length-1; i++) document.getElementById(selectedComponents[i]).style.display=\"block\" } function get_cookie(Name) { var search = Name + \"=\" var returnvalue = \"\"; if (document.cookie.length > 0) { offset = document.cookie.indexOf(search) if (offset != -1) { offset += search.length end = document.cookie.indexOf(\";\", offset); if (end == -1) end = document.cookie.length; returnvalue=unescape(document.cookie.substring(offset, end)) } }  return returnvalue; }  function getselectedItem(){ if (get_cookie(window.location.pathname) != \"\"){ selectedItem=get_cookie(window.location.pathname) return selectedItem } else return \"\" }  function saveswitchstate(){ var inc=0, selectedItem=\"\" while (ccollect[inc]){ if (ccollect[inc].style.display==\"block\") selectedItem+=ccollect[inc].id+\"|\" inc++ } document.cookie=window.location.pathname+\"=\"+selectedItem }  function do_onload(){ getElementbyClass(\"switchcontent\") if (enablepersist==\"on\" && typeof ccollect!=\"undefined\") revivecontent() } if (window.addEventListener) window.addEventListener(\"load\", do_onload, false) else if (window.attachEvent) window.attachEvent(\"onload\", do_onload) else if (document.getElementById) window.onload=do_onload if (enablepersist==\"on\" && document.getElementById) window.onunload=saveswitchstate </SCRIPT> ";
        mailContent += "	</head> ";
        mailContent += "	<body topmargin=\"0\" leftmargin=\"0\" bgcolor=\"#FFFFFF\" style=\"font-family: Tahoma\">	<div align=\"center\"> ";
        mailContent += "	<table border=\"0\" width=\"650\" cellspacing=\"0\" cellpadding=\"0\" height=\"78\" style=\"border-right-width: 0px; border-top-width: 0px\"> ";
        mailContent += "	<tr><td class=\"cart_b\" width=\"761\" height=\"31\" align=\"center\"><font color=\"#CCCCCC\" face=\"Wingdings\" style=\"font-size: 13pt\">? ? ? ? ? ? ? ? ? ? ? ? ? ? ? ? ? ? ? ?</font></td></tr> ";
        mailContent += "	</tr><tr><td class=\"cart_b\" width=\"761\" height=\"47\" style=\"border-left-width: 1px; border-right-width: 1px; border-top: 1px solid #C0C0C0; border-bottom: 1px solid #C0C0C0; font-family:Tahoma; margin-left:6; margin-top:0; margin-bottom:0; font-size:9pt\"> ";
        mailContent += "	</i></font></td></tr></table> ";

        RepairPlanWarningBean bean = null;
        if (details.size() > 0) {
            mailContent += "<table style=\"width: 100%;\" class=\"its\"><thead><tr>";
            mailContent += "<th>" + MCUtil.getBundleString("message.asset.manageCode") + "</th>";
            mailContent += "<th>" + MCUtil.getBundleString("message.equipment.equipmentName") + "</th>";
            mailContent += "<th>" + MCUtil.getBundleString("message.repairplan.timeTrue") + "</th>";
            mailContent += "</tr></thead><tbody>";
            for (int i = 0; i < details.size(); i++) {
                bean = (RepairPlanWarningBean) details.get(i);
                mailContent += "<tr>";
                mailContent += "<td><span>" + bean.getManageCode() + "</span></td>";
                mailContent += "<td><span>" + bean.getMatName() + "</span></td>";
                mailContent += "<td><span>" + bean.getTimeTrue() + "</span></td>";
                mailContent += "</tr>";
            }
            mailContent += "</tbody></table>";
        }
        mailContent += "</div></body></html>";
        return mailContent;
    }

    public static String getVerifiedPlanForWarning(String header, ArrayList details) throws Exception {

        String mailContent = "	<html><head><meta http-equiv=\"Content-Language\" content=\"en-us\"><meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"> ";
        mailContent += "	<title>" + header + "</title><style type=\"text/css\">A { COLOR: #000000; TEXT-DECORATION: none } ";
        mailContent += "	A:hover { COLOR: #000000; cursor: hand; } .cart_row {	border-bottom: 1px solid #CCCCCC; }	.cart_row_red {	border-bottom:1px solid #CCCCCC; border-right:1px solid #FF6565; border-left-style:none; border-left-width:medium; } ";
        mailContent += "	.cart_textbox { font-family: Arial; font-size: 9pt; border: 1px solid #FFFFFF; } </style> ";
        mailContent += "	<SCRIPT language=JavaScript> var enablepersist=\"off\" var collapseprevious=\"no\" if (document.getElementById){document.write('<style type=\"text/css\">') document.write('.switchcontent{ }') document.write('</style>')} if (document.getElementById){document.write('<style type=\"text/css\">') document.write('.switchcontent_a{display:none;}') document.write('</style>')} function getElementbyClass(classname){ccollect=new Array()var inc=0 var alltags=document.all? document.all : document.getElementsByTagName(\"*\")for (i=0; i<alltags.length; i++){if (alltags[i].className==classname) ccollect[inc++]=alltags[i]}} function contractcontent(omit){var inc=0 while (ccollect[inc]){ if (ccollect[inc].id!=omit) ccollect[inc].style.display=\"none\" inc++ }} function expandcontent(cid){ if (typeof ccollect!=\"undefined\"){ if (collapseprevious==\"yes\") contractcontent(cid) document.getElementById(cid).style.display=(document.getElementById(cid).style.display!=\"none\")? \"none\" : \"block\" } } function expandcontent_a(cid){ if (typeof ccollect!=\"undefined\"){ if (collapseprevious==\"yes\") contractcontent(cid) document.getElementById(cid).style.display=(document.getElementById(cid).style.display!=\"block\")? \"block\" : \"none\" } } function revivecontent(){ contractcontent(\"omitnothing\") selectedItem=getselectedItem() selectedComponents=selectedItem.split(\"|\") for (i=0; i<selectedComponents.length-1; i++) document.getElementById(selectedComponents[i]).style.display=\"block\" } function get_cookie(Name) { var search = Name + \"=\" var returnvalue = \"\"; if (document.cookie.length > 0) { offset = document.cookie.indexOf(search) if (offset != -1) { offset += search.length end = document.cookie.indexOf(\";\", offset); if (end == -1) end = document.cookie.length; returnvalue=unescape(document.cookie.substring(offset, end)) } }  return returnvalue; }  function getselectedItem(){ if (get_cookie(window.location.pathname) != \"\"){ selectedItem=get_cookie(window.location.pathname) return selectedItem } else return \"\" }  function saveswitchstate(){ var inc=0, selectedItem=\"\" while (ccollect[inc]){ if (ccollect[inc].style.display==\"block\") selectedItem+=ccollect[inc].id+\"|\" inc++ } document.cookie=window.location.pathname+\"=\"+selectedItem }  function do_onload(){ getElementbyClass(\"switchcontent\") if (enablepersist==\"on\" && typeof ccollect!=\"undefined\") revivecontent() } if (window.addEventListener) window.addEventListener(\"load\", do_onload, false) else if (window.attachEvent) window.attachEvent(\"onload\", do_onload) else if (document.getElementById) window.onload=do_onload if (enablepersist==\"on\" && document.getElementById) window.onunload=saveswitchstate </SCRIPT> ";
        mailContent += "	</head> ";
        mailContent += "	<body topmargin=\"0\" leftmargin=\"0\" bgcolor=\"#FFFFFF\" style=\"font-family: Tahoma\">	<div align=\"center\"> ";
        mailContent += "	<table border=\"0\" width=\"650\" cellspacing=\"0\" cellpadding=\"0\" height=\"78\" style=\"border-right-width: 0px; border-top-width: 0px\"> ";
        mailContent += "	<tr><td class=\"cart_b\" width=\"761\" height=\"31\" align=\"center\"><font color=\"#CCCCCC\" face=\"Wingdings\" style=\"font-size: 13pt\">? ? ? ? ? ? ? ? ? ? ? ? ? ? ? ? ? ? ? ?</font></td></tr> ";
        mailContent += "	</tr><tr><td class=\"cart_b\" width=\"761\" height=\"47\" style=\"border-left-width: 1px; border-right-width: 1px; border-top: 1px solid #C0C0C0; border-bottom: 1px solid #C0C0C0; font-family:Tahoma; margin-left:6; margin-top:0; margin-bottom:0; font-size:9pt\"> ";
        mailContent += "	</i></font></td></tr></table> ";

        VerifiedPlanWarningBean bean = null;
        if (details.size() > 0) {
            mailContent += "<table style=\"width: 100%;\" class=\"its\"><thead><tr>";
            mailContent += "<th>" + MCUtil.getBundleString("message.asset.manageCode") + "</th>";
            mailContent += "<th>" + MCUtil.getBundleString("message.equipment.equipmentName") + "</th>";
            mailContent += "<th>" + MCUtil.getBundleString("message.requireverified.timeEstimate") + "</th>";
            mailContent += "</tr></thead><tbody>";
            for (int i = 0; i < details.size(); i++) {
                bean = (VerifiedPlanWarningBean) details.get(i);
                mailContent += "<tr>";
                mailContent += "<td><span>" + bean.getManageCode() + "</span></td>";
                mailContent += "<td><span>" + bean.getMatName() + "</span></td>";
                mailContent += "<td><span>" + bean.getTimeTrue() + "</span></td>";
                mailContent += "</tr>";
            }
            mailContent += "</tbody></table>";
        }
        mailContent += "</div></body></html>";
        return mailContent;
    }

    public static String getMcoForWarning(String header, ArrayList details) throws Exception {

        String mailContent = "	<html><head><meta http-equiv=\"Content-Language\" content=\"en-us\"><meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"> ";
        mailContent += "	<title>" + header + "</title><style type=\"text/css\">A { COLOR: #000000; TEXT-DECORATION: none } ";
        mailContent += "	A:hover { COLOR: #000000; cursor: hand; } .cart_row {	border-bottom: 1px solid #CCCCCC; }	.cart_row_red {	border-bottom:1px solid #CCCCCC; border-right:1px solid #FF6565; border-left-style:none; border-left-width:medium; } ";
        mailContent += "	.cart_textbox { font-family: Arial; font-size: 9pt; border: 1px solid #FFFFFF; } </style> ";
        mailContent += "	<SCRIPT language=JavaScript> var enablepersist=\"off\" var collapseprevious=\"no\" if (document.getElementById){document.write('<style type=\"text/css\">') document.write('.switchcontent{ }') document.write('</style>')} if (document.getElementById){document.write('<style type=\"text/css\">') document.write('.switchcontent_a{display:none;}') document.write('</style>')} function getElementbyClass(classname){ccollect=new Array()var inc=0 var alltags=document.all? document.all : document.getElementsByTagName(\"*\")for (i=0; i<alltags.length; i++){if (alltags[i].className==classname) ccollect[inc++]=alltags[i]}} function contractcontent(omit){var inc=0 while (ccollect[inc]){ if (ccollect[inc].id!=omit) ccollect[inc].style.display=\"none\" inc++ }} function expandcontent(cid){ if (typeof ccollect!=\"undefined\"){ if (collapseprevious==\"yes\") contractcontent(cid) document.getElementById(cid).style.display=(document.getElementById(cid).style.display!=\"none\")? \"none\" : \"block\" } } function expandcontent_a(cid){ if (typeof ccollect!=\"undefined\"){ if (collapseprevious==\"yes\") contractcontent(cid) document.getElementById(cid).style.display=(document.getElementById(cid).style.display!=\"block\")? \"block\" : \"none\" } } function revivecontent(){ contractcontent(\"omitnothing\") selectedItem=getselectedItem() selectedComponents=selectedItem.split(\"|\") for (i=0; i<selectedComponents.length-1; i++) document.getElementById(selectedComponents[i]).style.display=\"block\" } function get_cookie(Name) { var search = Name + \"=\" var returnvalue = \"\"; if (document.cookie.length > 0) { offset = document.cookie.indexOf(search) if (offset != -1) { offset += search.length end = document.cookie.indexOf(\";\", offset); if (end == -1) end = document.cookie.length; returnvalue=unescape(document.cookie.substring(offset, end)) } }  return returnvalue; }  function getselectedItem(){ if (get_cookie(window.location.pathname) != \"\"){ selectedItem=get_cookie(window.location.pathname) return selectedItem } else return \"\" }  function saveswitchstate(){ var inc=0, selectedItem=\"\" while (ccollect[inc]){ if (ccollect[inc].style.display==\"block\") selectedItem+=ccollect[inc].id+\"|\" inc++ } document.cookie=window.location.pathname+\"=\"+selectedItem }  function do_onload(){ getElementbyClass(\"switchcontent\") if (enablepersist==\"on\" && typeof ccollect!=\"undefined\") revivecontent() } if (window.addEventListener) window.addEventListener(\"load\", do_onload, false) else if (window.attachEvent) window.attachEvent(\"onload\", do_onload) else if (document.getElementById) window.onload=do_onload if (enablepersist==\"on\" && document.getElementById) window.onunload=saveswitchstate </SCRIPT> ";
        mailContent += "	</head> ";
        mailContent += "	<body topmargin=\"0\" leftmargin=\"0\" bgcolor=\"#FFFFFF\" style=\"font-family: Tahoma\">	<div align=\"center\"> ";
        mailContent += "	<table border=\"0\" width=\"650\" cellspacing=\"0\" cellpadding=\"0\" height=\"78\" style=\"border-right-width: 0px; border-top-width: 0px\"> ";
        mailContent += "	<tr><td class=\"cart_b\" width=\"761\" height=\"31\" align=\"center\"><font color=\"#CCCCCC\" face=\"Wingdings\" style=\"font-size: 13pt\">? ? ? ? ? ? ? ? ? ? ? ? ? ? ? ? ? ? ? ?</font></td></tr> ";
        mailContent += "	</tr><tr><td class=\"cart_b\" width=\"761\" height=\"47\" style=\"border-left-width: 1px; border-right-width: 1px; border-top: 1px solid #C0C0C0; border-bottom: 1px solid #C0C0C0; font-family:Tahoma; margin-left:6; margin-top:0; margin-bottom:0; font-size:9pt\"> ";
        mailContent += "	</i></font></td></tr></table> ";

        McoWarningBean bean = null;
        if (details.size() > 0) {
            mailContent += "<table style=\"width: 100%;\" class=\"its\"><thead><tr>";
            mailContent += "<th>" + MCUtil.getBundleString("message.asset.manageCode") + "</th>";
            mailContent += "<th>" + MCUtil.getBundleString("message.equipment.equipmentName") + "</th>";
            mailContent += "<th>" + MCUtil.getBundleString("message.mcodetail.quantity") + "</th>";
            mailContent += "<th>" + MCUtil.getBundleString("message.mco.carryOnDatePlan") + "</th>";
            mailContent += "<th>" + MCUtil.getBundleString("message.mco.mcoNumber") + "</th>";
            mailContent += "</tr></thead><tbody>";
            for (int i = 0; i < details.size(); i++) {
                bean = (McoWarningBean) details.get(i);
                mailContent += "<tr>";
                mailContent += "<td><span>" + bean.getManageCode() + "</span></td>";
                mailContent += "<td><span>" + bean.getMatName() + "</span></td>";
                mailContent += "<td><span>" + bean.getQuantity() + "</span></td>";
                mailContent += "<td><span>" + bean.getOnDatePlan() + "</span></td>";
                mailContent += "<td><span>" + bean.getMcoNumber() + "</span></td>";
                mailContent += "</tr>";
            }
            mailContent += "</tbody></table>";
        }
        mailContent += "</div></body></html>";
        return mailContent;
    }

    public static String getMcForWarning(String header, ArrayList details) throws Exception {

        String mailContent = "	<html><head><meta http-equiv=\"Content-Language\" content=\"en-us\"><meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"> ";
        mailContent += "	<title>" + header + "</title><style type=\"text/css\">A { COLOR: #000000; TEXT-DECORATION: none } ";
        mailContent += "	A:hover { COLOR: #000000; cursor: hand; } .cart_row {	border-bottom: 1px solid #CCCCCC; }	.cart_row_red {	border-bottom:1px solid #CCCCCC; border-right:1px solid #FF6565; border-left-style:none; border-left-width:medium; } ";
        mailContent += "	.cart_textbox { font-family: Arial; font-size: 9pt; border: 1px solid #FFFFFF; } </style> ";
        mailContent += "	<SCRIPT language=JavaScript> var enablepersist=\"off\" var collapseprevious=\"no\" if (document.getElementById){document.write('<style type=\"text/css\">') document.write('.switchcontent{ }') document.write('</style>')} if (document.getElementById){document.write('<style type=\"text/css\">') document.write('.switchcontent_a{display:none;}') document.write('</style>')} function getElementbyClass(classname){ccollect=new Array()var inc=0 var alltags=document.all? document.all : document.getElementsByTagName(\"*\")for (i=0; i<alltags.length; i++){if (alltags[i].className==classname) ccollect[inc++]=alltags[i]}} function contractcontent(omit){var inc=0 while (ccollect[inc]){ if (ccollect[inc].id!=omit) ccollect[inc].style.display=\"none\" inc++ }} function expandcontent(cid){ if (typeof ccollect!=\"undefined\"){ if (collapseprevious==\"yes\") contractcontent(cid) document.getElementById(cid).style.display=(document.getElementById(cid).style.display!=\"none\")? \"none\" : \"block\" } } function expandcontent_a(cid){ if (typeof ccollect!=\"undefined\"){ if (collapseprevious==\"yes\") contractcontent(cid) document.getElementById(cid).style.display=(document.getElementById(cid).style.display!=\"block\")? \"block\" : \"none\" } } function revivecontent(){ contractcontent(\"omitnothing\") selectedItem=getselectedItem() selectedComponents=selectedItem.split(\"|\") for (i=0; i<selectedComponents.length-1; i++) document.getElementById(selectedComponents[i]).style.display=\"block\" } function get_cookie(Name) { var search = Name + \"=\" var returnvalue = \"\"; if (document.cookie.length > 0) { offset = document.cookie.indexOf(search) if (offset != -1) { offset += search.length end = document.cookie.indexOf(\";\", offset); if (end == -1) end = document.cookie.length; returnvalue=unescape(document.cookie.substring(offset, end)) } }  return returnvalue; }  function getselectedItem(){ if (get_cookie(window.location.pathname) != \"\"){ selectedItem=get_cookie(window.location.pathname) return selectedItem } else return \"\" }  function saveswitchstate(){ var inc=0, selectedItem=\"\" while (ccollect[inc]){ if (ccollect[inc].style.display==\"block\") selectedItem+=ccollect[inc].id+\"|\" inc++ } document.cookie=window.location.pathname+\"=\"+selectedItem }  function do_onload(){ getElementbyClass(\"switchcontent\") if (enablepersist==\"on\" && typeof ccollect!=\"undefined\") revivecontent() } if (window.addEventListener) window.addEventListener(\"load\", do_onload, false) else if (window.attachEvent) window.attachEvent(\"onload\", do_onload) else if (document.getElementById) window.onload=do_onload if (enablepersist==\"on\" && document.getElementById) window.onunload=saveswitchstate </SCRIPT> ";
        mailContent += "	</head> ";
        mailContent += "	<body topmargin=\"0\" leftmargin=\"0\" bgcolor=\"#FFFFFF\" style=\"font-family: Tahoma\">	<div align=\"center\"> ";
        mailContent += "	<table border=\"0\" width=\"650\" cellspacing=\"0\" cellpadding=\"0\" height=\"78\" style=\"border-right-width: 0px; border-top-width: 0px\"> ";
        mailContent += "	<tr><td class=\"cart_b\" width=\"761\" height=\"31\" align=\"center\"><font color=\"#CCCCCC\" face=\"Wingdings\" style=\"font-size: 13pt\">? ? ? ? ? ? ? ? ? ? ? ? ? ? ? ? ? ? ? ?</font></td></tr> ";
        mailContent += "	</tr><tr><td class=\"cart_b\" width=\"761\" height=\"47\" style=\"border-left-width: 1px; border-right-width: 1px; border-top: 1px solid #C0C0C0; border-bottom: 1px solid #C0C0C0; font-family:Tahoma; margin-left:6; margin-top:0; margin-bottom:0; font-size:9pt\"> ";
        mailContent += "	</i></font></td></tr></table> ";

        McWarningBean bean = null;
        if (details.size() > 0) {
            mailContent += "<table style=\"width: 100%;\" class=\"its\"><thead><tr>";
            mailContent += "<th>" + MCUtil.getBundleString("message.asset.manageCode") + "</th>";
            mailContent += "<th>" + MCUtil.getBundleString("message.equipment.equipmentName") + "</th>";
            mailContent += "<th>" + MCUtil.getBundleString("message.mcdetail.quantity") + "</th>";
            mailContent += "<th>" + MCUtil.getBundleString("message.emc.emcNumber") + "</th>";
            mailContent += "<th>" + MCUtil.getBundleString("message.emc.carryOutDatePlan") + "</th>";

            mailContent += "</tr></thead><tbody>";
            for (int i = 0; i < details.size(); i++) {
                bean = (McWarningBean) details.get(i);
                mailContent += "<tr>";
                mailContent += "<td><span>" + bean.getManageCode() + "</span></td>";
                mailContent += "<td><span>" + bean.getMatName() + "</span></td>";
                mailContent += "<td><span>" + bean.getQuantity() + "</span></td>";
                mailContent += "<td><span>" + bean.getOutDatePlan() + "</span></td>";
                mailContent += "<td><span>" + bean.getMcNumber() + "</span></td>";
                mailContent += "</tr>";
            }
            mailContent += "</tbody></table>";
        }
        mailContent += "</div></body></html>";
        return mailContent;
    }
}
