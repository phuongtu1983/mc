/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.auth;

/**
 *
 * @author Admin
 */
import com.venus.core.sercurity.Encoder;
import com.venus.core.util.StringUtil;
import com.venus.mc.bean.EmployeeBean;
import com.venus.mc.dao.EmployeeDAO;
import com.venus.mc.util.MCUtil;
import com.venus.mc.util.MailHandle;
import java.lang.reflect.Array;
import java.util.Random;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

public class ResetPasswordAction extends Action {
    /* forward name="success" path="" */

    private final static String SUCCESS = "success";

    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        ResetPasswordForm formBean = (ResetPasswordForm) form;
        ActionMessages errors = new ActionMessages();
        EmployeeBean member = null;
        EmployeeDAO memberDAO = new EmployeeDAO();
        try {
            member = (EmployeeBean) memberDAO.isExistMember(formBean.getUsername(), formBean.getEmail());
        } catch (Exception ex) {
            throw ex;
        }

        if (member != null) {
            String newPass = makePassword();
            try {
                memberDAO.changePassword(formBean.getUsername(), member.getPassword(), Encoder.getMD5_Base64(newPass));
                sendMail(member.getOrgName(), member.getFullname(), formBean.getUsername(), formBean.getEmail(), newPass);
            } catch (Exception ex) {
                throw ex;
            }
        } else {
            errors.add("userLogin", new ActionMessage("errors.resetPassword.Fail"));
            saveErrors(request, errors);
            return mapping.getInputForward();
        }


        return mapping.findForward(SUCCESS);

    }

    private String makePassword() {
        String pass = "";
        String[] chars = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0",
            "a", "A", "b", "B", "c", "C", "d", "D", "e", "E",
            "f", "F", "g", "G", "h", "H", "i", "I", "j", "J",
            "k", "K", "l", "L", "m", "M", "n", "N", "o", "O",
            "p", "P", "q", "Q", "r", "R", "s", "S", "t", "T",
            "u", "U", "v", "V", "w", "W", "x", "X", "y", "Y",
            "z", "Z"};

        int count = Array.getLength(chars) - 1;
        int length = 10;
        Random ran = new Random();

        //srand((double)microtime()*1000000);

        for (int i = 0; i < length; i++) {
            pass += chars[ran.nextInt(count)];
        }
        return (pass);
    }

    private void sendMail(String department, String name, String userName, String mail_to, String newPassword) throws Exception {
        String content = "";
        content += "<html><head><meta http-equiv='Content-Language' content='en-us'><meta http-equiv='Content-Type' content='text/html; charset=UTF-8'></head><body>";
        content += "<table align='center' width='70%'><tr><td class='cart_b' width='761' height='31' align='center'>"
                + "<font color='#CCCCCC' face='Wingdings' style='font-size: 13pt'>? ? ? ? ? ? ? ? ? ? ? ? ? ? ? ? ? ? ? ?</font></td></tr>"
                + "<tr><td class='cart_b' style='border-left-width: 1px; border-right-width: 1px; border-top: 1px solid #C0C0C0; border-bottom-width: 1px ;margin-left:6; margin-top:0; margin-bottom:0; font-size:9pt'>&nbsp;</td></tr>"
                + "<tr><td align='center'>"
                + "<span style='font-size: 17pt;color:#0000FF'><b>"
                + MCUtil.getBundleString("message.resetpassword.email.title")
                + "</b></span></td></tr><tr><td>";
        if (!StringUtil.isBlankOrNull(department)) {
            content += "<span style='font-size: 12pt'><b>Department : </b></span><span style='font-size: 12pt'>" + department + "</span><br>&nbsp;";
        }
        content += "<span style='font-size: 12pt'><b>Name : </b>" + name + "</span><br>&nbsp;"
                + "<span style='font-size: 12pt'><b>Username : </b>" + userName + "</span><br>&nbsp;"
                + "<span style='font-size: 12pt'><b>NewPassword : </b>" + newPassword + "</span><br>&nbsp;"
                + "</td></tr><tr><td><div style='border-left-width: 1px; border-right-width: 1px; border-top: 1px solid #C0C0C0; border-bottom-width: 1px' align='center'><font face=\"Trebuchet MS\" size=\"1\"><BR>If your browser does not support UNICODE, please open attachment or contact Webmaster for more infomation, thank you! <BR>"
                + MCUtil.getBundleString("message.notify.senderMail") + "</font></div></td></tr></table>";

        content += "</body></html>";
        String mail_subject = "PTSC-MC, Reset Password for " + name;
        MailHandle.sendMail(MCUtil.getBundleString("message.notify.senderMail"), mail_to, mail_subject, content);
    }
}
