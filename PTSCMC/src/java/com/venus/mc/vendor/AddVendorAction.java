/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.vendor;

import com.venus.core.auth.OnlineUser;
import com.venus.core.util.GenericValidator;
import com.venus.core.util.LogUtil;
import com.venus.core.util.MailUtil;
import com.venus.core.util.NumberUtil;
import com.venus.core.util.StringUtil;
import com.venus.mc.MCConfig;
import com.venus.mc.bean.EmployeeBean;
import com.venus.mc.bean.VendorBean;
import com.venus.mc.bean.VendorContactBean;
import com.venus.mc.bean.VendorEvalDetailBean;
import com.venus.mc.bean.VendorEvaluateBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.EmployeeDAO;
import com.venus.mc.dao.VendorDAO;
import com.venus.mc.util.MCUtil;
import com.venus.mc.util.PermissionUtil;
import com.venus.mc.vendor.material.VendorGroupMaterialFormBean;
import com.venus.mc.vendor.material.VendorMaterialFormBean;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

/**
 *
 * @author phuongtu
 */
public class AddVendorAction extends SpineAction {

    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    @Override
    public boolean doAction(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        int contract = NumberUtil.parseInt(request.getParameter("contract"), 0);
        VendorFormBean formBean = (VendorFormBean) form;
        VendorDAO vendorDAO = new VendorDAO();
        VendorBean bean = null;
        boolean bNew = false;
        boolean isExist = false;
        try {
            bean = vendorDAO.getVendorByName(formBean.getName());
        } catch (Exception ex) {
        }
        int venId = formBean.getVenId();
        if (venId == 0) {
            bNew = true;
            if (bean != null) {
                isExist = true;
            }
        } else {
            bNew = false;
            if (bean != null && bean.getVenId() != venId) {
                isExist = true;
            }
        }
        if (isExist) {
            ActionMessages errors = new ActionMessages();
            errors.add("vendorExisted", new ActionMessage("errors.vendor.existed"));
            saveErrors(request, errors);
            return false;
        }
        bean = new VendorBean();
        bean.setVenId(formBean.getVenId());
        bean.setName(StringUtil.encodeHTML(formBean.getName()));
        bean.setPresenter(formBean.getPresenter());
        bean.setAddress(formBean.getAddress());
        bean.setPhone(formBean.getPhone());
        bean.setFax(formBean.getFax());
        bean.setStatus(formBean.getStatus());
        bean.setEmail(formBean.getEmail());
        bean.setWeb(formBean.getWeb());
        bean.setField(formBean.getField());
        bean.setLicense(formBean.getLicense());
        bean.setCharterCapital(formBean.getCharterCapital());
        bean.setNote(formBean.getNote());
        bean.setComments(formBean.getComments());
        bean.setPospresenter(formBean.getPospresenter());
        bean.setPhonePresenter(formBean.getPhonePresenter());
        bean.setKind(formBean.getKind());
        bean.setOrgHandle(formBean.getOrgHandle());
        try {
            HttpSession session = request.getSession();
            if (contract == 0) {
                if (bNew) {
                    vendorDAO.insertVendor(bean);
                    sendMail(MCUtil.getMemberID(session), MCUtil.getOrganizationID(session), bean.getName());
                } else {
                    OnlineUser user = MCUtil.getOnlineUser(session);
                    LogUtil.info("Username : " + user.getName() + " - Form : " + mapping.getPath() + " - EDIT");
                    vendorDAO.updateVendor(bean);
                }
            } else {
                ArrayList arrList = null;
                ArrayList arrList1 = null;
                VendorContactBean contractBean = null;
                VendorEvaluateBean evalBean = null;
                VendorEvalDetailBean evalDetailBean = null;
                VendorGroupMaterialFormBean groupBean = null;
                VendorMaterialFormBean mBean = null;
                int id = vendorDAO.insertVendor(bean);
                vendorDAO.updateStatusVendor(bean.getVenId(), id);
                //VendorContact
                arrList = vendorDAO.getVendorContacts(bean.getVenId());
                for (int i = 0; i < arrList.size(); i++) {
                    contractBean = (VendorContactBean) arrList.get(i);
                    contractBean.setVenId(id);
                    vendorDAO.insertVendorContact(contractBean);
                }
                //VendorEval
                arrList = vendorDAO.getVendorEvals(bean.getVenId());
                for (int i = 0; i < arrList.size(); i++) {
                    evalBean = (VendorEvaluateBean) arrList.get(i);
                    evalBean.setVenId(id);                    
                    int evalId = vendorDAO.insertVendorEval(evalBean);
                    arrList1 = vendorDAO.getEvaluteDetail(evalBean.getEvalId());
                    for (int j = 0; j < arrList1.size(); j++) {
                        evalDetailBean = (VendorEvalDetailBean) arrList.get(i);
                        evalDetailBean.setEvalid(evalId);
                        vendorDAO.insertEvalDetail(evalDetailBean);
                    }
                }
                //VendorGroupMaterial
                arrList = vendorDAO.getVendorGroupMaterials(bean.getVenId());
                for (int i = 0; i < arrList.size(); i++) {
                    groupBean = (VendorGroupMaterialFormBean) arrList.get(i);
                    groupBean.setVenId(id);
                    vendorDAO.insertVendorGroupMaterial(groupBean.getVenId() + "", groupBean.getGroId() + "");
                }
                //VendorMaterial
                arrList = vendorDAO.getVendorMaterials(bean.getVenId());
                for (int i = 0; i < arrList.size(); i++) {
                    mBean = (VendorMaterialFormBean) arrList.get(i);
                    mBean.setVenId(id);
                    vendorDAO.insertVendorMaterial(mBean);
                }
                
            }
        } catch (Exception ex) {
            LogUtil.error("FAILED:Vender:add-" + ex.getMessage());
            ex.printStackTrace();
        }
        return true;
    }
    
    private void sendMail(int createdId, int orgId, String vendorName) {
        try {
            String mailContent = "	<html><head><meta http-equiv=\"Content-Language\" content=\"en-us\"><meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"> ";
            mailContent += "	<title>" + MCUtil.getBundleString("message.vendor.sentMailHeader") + "</title><style type=\"text/css\">A { COLOR: #000000; TEXT-DECORATION: none } ";
            mailContent += "	A:hover { COLOR: #000000; cursor: hand; } .cart_row {	border-bottom: 1px solid #CCCCCC; }	.cart_row_red {	border-bottom:1px solid #CCCCCC; border-right:1px solid #FF6565; border-left-style:none; border-left-width:medium; } ";
            mailContent += "	.cart_textbox { font-family: Arial; font-size: 9pt; border: 1px solid #FFFFFF; } </style> ";
            mailContent += "	<SCRIPT language=JavaScript> var enablepersist=\"off\" var collapseprevious=\"no\" if (document.getElementById){document.write('<style type=\"text/css\">') document.write('.switchcontent{ }') document.write('</style>')} if (document.getElementById){document.write('<style type=\"text/css\">') document.write('.switchcontent_a{display:none;}') document.write('</style>')} function getElementbyClass(classname){ccollect=new Array()var inc=0 var alltags=document.all? document.all : document.getElementsByTagName(\"*\")for (i=0; i<alltags.length; i++){if (alltags[i].className==classname) ccollect[inc++]=alltags[i]}} function contractcontent(omit){var inc=0 while (ccollect[inc]){ if (ccollect[inc].id!=omit) ccollect[inc].style.display=\"none\" inc++ }} function expandcontent(cid){ if (typeof ccollect!=\"undefined\"){ if (collapseprevious==\"yes\") contractcontent(cid) document.getElementById(cid).style.display=(document.getElementById(cid).style.display!=\"none\")? \"none\" : \"block\" } } function expandcontent_a(cid){ if (typeof ccollect!=\"undefined\"){ if (collapseprevious==\"yes\") contractcontent(cid) document.getElementById(cid).style.display=(document.getElementById(cid).style.display!=\"block\")? \"block\" : \"none\" } } function revivecontent(){ contractcontent(\"omitnothing\") selectedItem=getselectedItem() selectedComponents=selectedItem.split(\"|\") for (i=0; i<selectedComponents.length-1; i++) document.getElementById(selectedComponents[i]).style.display=\"block\" } function get_cookie(Name) { var search = Name + \"=\" var returnvalue = \"\"; if (document.cookie.length > 0) { offset = document.cookie.indexOf(search) if (offset != -1) { offset += search.length end = document.cookie.indexOf(\";\", offset); if (end == -1) end = document.cookie.length; returnvalue=unescape(document.cookie.substring(offset, end)) } }  return returnvalue; }  function getselectedItem(){ if (get_cookie(window.location.pathname) != \"\"){ selectedItem=get_cookie(window.location.pathname) return selectedItem } else return \"\" }  function saveswitchstate(){ var inc=0, selectedItem=\"\" while (ccollect[inc]){ if (ccollect[inc].style.display==\"block\") selectedItem+=ccollect[inc].id+\"|\" inc++ } document.cookie=window.location.pathname+\"=\"+selectedItem }  function do_onload(){ getElementbyClass(\"switchcontent\") if (enablepersist==\"on\" && typeof ccollect!=\"undefined\") revivecontent() } if (window.addEventListener) window.addEventListener(\"load\", do_onload, false) else if (window.attachEvent) window.attachEvent(\"onload\", do_onload) else if (document.getElementById) window.onload=do_onload if (enablepersist==\"on\" && document.getElementById) window.onunload=saveswitchstate </SCRIPT> ";
            mailContent += "	</head> ";
            mailContent += "	<body topmargin=\"0\" leftmargin=\"0\" bgcolor=\"#FFFFFF\" style=\"font-family: Tahoma\">	<div align=\"center\"> ";
            mailContent += "	<table border=\"0\" width=\"650\" cellspacing=\"0\" cellpadding=\"0\" height=\"78\" style=\"border-right-width: 0px; border-top-width: 0px\"> ";
            mailContent += "	<tr><td class=\"cart_b\" width=\"761\" height=\"31\" align=\"center\"><font color=\"#CCCCCC\" face=\"Wingdings\" style=\"font-size: 13pt\">? ? ? ? ? ? ? ? ? ? ? ? ? ? ? ? ? ? ? ?</font></td></tr> ";
            mailContent += "	</tr><tr><td class=\"cart_b\" width=\"761\" height=\"47\" style=\"border-left-width: 1px; border-right-width: 1px; border-top: 1px solid #C0C0C0; border-bottom: 1px solid #C0C0C0; font-family:Tahoma; margin-left:6; margin-top:0; margin-bottom:0; font-size:9pt\"> ";
            mailContent += "	</i></font></td></tr></table> ";
            mailContent += MCUtil.getBundleString("message.u_vendor") + MCUtil.getBundleString("message.vendor.created") + " : " + vendorName;
            mailContent += "</div></body></html>";
            
            String mailTo = "";
            String mailCC = "";
            try {
                EmployeeDAO empDAO = new EmployeeDAO();
                mailTo = empDAO.getEmailOfEmployees(createdId + "");
                ArrayList emps = empDAO.getEmployeeOfOrg(orgId);
                if (emps != null) {
                    EmployeeBean empBean = null;
                    for (int i = 0; i < emps.size(); i++) {
                        empBean = (EmployeeBean) emps.get(i);
                        mailCC += empBean.getEmail();
                        if (i < emps.size() - 1) {
                            mailCC += ",";
                        }
                    }
                }
                if (!GenericValidator.isBlankOrNull(mailTo)) {
                    MailUtil.sendMailAuth(MCConfig.getSenderMail(),
                            mailTo,
                            mailCC,
                            MCUtil.getBundleString("message.vendor.sentMailHeader"),
                            mailContent,
                            null,
                            null,
                            MCConfig.getMailServer(),
                            MCConfig.getAuthUserName(),
                            MCConfig.getAuthPassword());
                }
            } catch (Exception ex) {
            }
        } catch (Exception ex) {
        }
    }
    
    @Override
    protected String getFunction() {
        return PermissionUtil.FUNC_EDIT + "";
    }
    
    @Override
    protected int getPermit() {
        return PermissionUtil.PER_LIBRARY_VENDOR;
    }
}
