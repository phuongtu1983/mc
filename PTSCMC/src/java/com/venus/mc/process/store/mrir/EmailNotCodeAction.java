/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.process.store.mrir;

import com.venus.core.util.GenericValidator;
import com.venus.core.util.LogUtil;
import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.ContractBean;
import com.venus.mc.bean.MaterialBean;
import com.venus.mc.bean.MrirBean;
import com.venus.mc.bean.OsDBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.ContractDAO;
import com.venus.mc.dao.EmployeeDAO;
import com.venus.mc.dao.MaterialDAO;
import com.venus.mc.dao.MrirDAO;
import com.venus.mc.dao.PermissionDAO;
import com.venus.mc.util.MCConfig;
import com.venus.mc.util.MCUtil;
import com.venus.mc.util.MailHandle;
import com.venus.mc.util.PermissionUtil;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author maivinhloc
 */
public class EmailNotCodeAction extends SpineAction {

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
    public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    String osdId = request.getParameter("osdId");
    if (!GenericValidator.isBlankOrNull(osdId)) {
      try
      {
        MrirDAO mrirDAO = new MrirDAO();
        String matIds = mrirDAO.getMaterialForEmail(NumberUtil.parseInt(osdId, 0));
        if (!matIds.equals("0"))
        {
          notifyForNotMaterialCode(matIds, NumberUtil.parseInt(osdId, 0), MCUtil.getMemberID(request.getSession()));
          MaterialDAO matDAO = new MaterialDAO();
          ArrayList mats = matDAO.getMaterials(matIds);
          MaterialBean material = null;
          for (int i = 0; i < mats.size(); i++)
          {
            material = (MaterialBean)mats.get(i);
            material.setCode("");
            try
            {
              matDAO.updateMaterialCode(material);
            }
            catch (Exception localException) {}
          }
        }
      }
      catch (Exception localException1) {}
    }
    return true;
  }

    private void notifyForNotMaterialCode(String matIds, int osdId, int empId) {
        try {
            MrirDAO mrirDAO = new MrirDAO();
            OsDBean osdBean = mrirDAO.getOsD(osdId);
            MaterialDAO matDAO = new MaterialDAO();
            ArrayList matList = matDAO.getMaterials(matIds);
            if (osdBean != null) {
                String content = "";
                content += "<html><head><meta http-equiv='Content-Language' content='en-us'><meta http-equiv='Content-Type' content='text/html; charset=UTF-8'></head><body>";
                content += "<table align='center' width='70%'><tr><td class='cart_b' width='761' height='31' align='center'>"
                        + "<font color='#CCCCCC' face='Wingdings' style='font-size: 13pt'>? ? ? ? ? ? ? ? ? ? ? ? ? ? ? ? ? ? ? ?</font></td></tr>"
                        + "<tr><td class='cart_b' style='border-left-width: 1px; border-right-width: 1px; border-top: 1px solid #C0C0C0; border-bottom-width: 1px ;margin-left:6; margin-top:0; margin-bottom:0; font-size:9pt'>&nbsp;</td></tr>"
                        + "<tr><td align='center'>"
                        + "<span style='font-size: 17pt;color:#0000FF'><b>"
                        + MCUtil.getBundleString("message.notify.osd.notcode.title") + " " + osdBean.getOsdNumber()
                        + "</b></span></td></tr><tr><td>";
                content += "<span style='color: rgb(0, 32, 96);'>Kính gởi Nhóm Quản lý Thư viện,</span></p>"
                        + "<p class='yiv1749408891MsoNormal'><span style='color: rgb(0, 32, 96);'> &nbsp;</span></p>"
                        + "<p class='yiv1749408891MsoNormal'><span style='color: rgb(0, 32, 96);'>Hiện tại,";

                content += " báo cáo số " + osdBean.getOsdNumber() + " ngày " + osdBean.getCreatedDate()
                        + " có một số VTTB chưa có mã như sau : ";
                content += "<ul>";
                MaterialBean material = null;
                for (int i = 0; i < matList.size(); i++) {
                    material = (MaterialBean) matList.get(i);
                    content += "<li><span style=\"color: rgb(0, 32, 96);\">" + material.getNameVn() + "</span></li>";
                }
                content += "</ul>";

                content += " Kính đề nghị Nhóm Quản lý Thư viện bổ sung mã vào thư viện.</span></p><p class='yiv1749408891MsoNormal'>"
                        + "</p><p class='yiv1749408891MsoNormal'><span style='color: rgb(0, 32, 96);'> &nbsp;</span></p><p class='yiv1749408891MsoNormal'>"
                        + "<span style='color: rgb(0, 32, 96);'>Trân trọng kính chào.</span></p><p class='yiv1749408891MsoNormal'>"
                        + "<span style='color: rgb(0, 32, 96);'>Phần mềm QL Cung ứng VTTB</span>";
                content += "</td></tr><tr><td><div style='border-left-width: 1px; border-right-width: 1px; border-top: 1px solid #C0C0C0; border-bottom-width: 1px' align='center'><font face=\"Trebuchet MS\" size=\"1\"><BR>If your browser does not support UNICODE, please open attachment or contact Webmaster for more infomation, thank you! <BR>"
                        + MCUtil.getBundleString("message.notify.senderMail") + "</font></div></td></tr></table></body></html>";
                String employee = "";
        PermissionDAO perDAO = new PermissionDAO();
        employee = perDAO.getEmployeesHasPermission(PermissionUtil.PER_PURCHASING_REQUEST_MATERIALNOTCODE + "", PermissionUtil.FUNC_LIST + "");
        EmployeeDAO empDAO = new EmployeeDAO();
        String mail_to = empDAO.getEmailOfEmployees(employee);
        String mail_cc = empDAO.getEmailOfEmployees(empId + "");
        
        String mail_subject = MCUtil.getBundleString("message.notify.osd.notcode.title") + " " + osdBean.getOsdNumber();
        MailHandle.sendMail(MCUtil.getBundleString("message.notify.senderMail"), mail_to, mail_cc, mail_subject, content, "", "", MCConfig.getMailServer());
      }
        } catch (Exception ex) {
            LogUtil.error("FAILED:Request:sendNotCodeEmail-" + ex.getMessage());
        }
    }
}
