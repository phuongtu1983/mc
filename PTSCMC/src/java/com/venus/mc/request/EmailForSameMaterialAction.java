package com.venus.mc.request;

import com.venus.core.util.GenericValidator;
import com.venus.core.util.LogUtil;
import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.EmployeeBean;
import com.venus.mc.bean.MaterialBean;
import com.venus.mc.bean.RequestBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.EmployeeDAO;
import com.venus.mc.dao.MaterialDAO;
import com.venus.mc.dao.RequestDAO;
import com.venus.mc.util.MCConfig;
import com.venus.mc.util.MCUtil;
import com.venus.mc.util.MailHandle;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class EmailForSameMaterialAction
  extends SpineAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    String reqId = request.getParameter("reqId");
    String matId = request.getParameter("matId");
    String code = request.getParameter("code");
    if ((!GenericValidator.isBlankOrNull(reqId)) && (!GenericValidator.isBlankOrNull(matId)) && (!GenericValidator.isBlankOrNull(code))) {
      try
      {
        notifyForSameMaterialCode(NumberUtil.parseInt(reqId, 0), MCUtil.getMemberID(request.getSession()), NumberUtil.parseInt(matId, 0), code);
      }
      catch (Exception localException) {}
    }
    return true;
  }
  
  private void notifyForSameMaterialCode(int reqId, int empId, int matId, String code)
  {
    try
    {
      RequestDAO dao = new RequestDAO();
      RequestBean reqBean = dao.getRequest(reqId);
      if (reqBean != null)
      {
        EmployeeDAO empDAO = new EmployeeDAO();
        EmployeeBean emp = empDAO.getEmployee(reqBean.getCreatedEmp());
        String mail_to = emp.getEmail();
        String mail_cc = empDAO.getEmailOfEmployees(empId + "");
        MaterialDAO matDAO = new MaterialDAO();
        MaterialBean matBean = matDAO.getMaterial(matId + "");
        MaterialBean oldMat = matDAO.getMaterialByCode(code);
        if (oldMat == null) {
          oldMat = new MaterialBean();
        }
        String content = "";
        content = content + "<html><head><meta http-equiv='Content-Language' content='en-us'><meta http-equiv='Content-Type' content='text/html; charset=UTF-8'></head><body>";
        content = content + "<table align='center' width='70%'><tr><td class='cart_b' width='761' height='31' align='center'><font color='#CCCCCC' face='Wingdings' style='font-size: 13pt'>? ? ? ? ? ? ? ? ? ? ? ? ? ? ? ? ? ? ? ?</font></td></tr><tr><td class='cart_b' style='border-left-width: 1px; border-right-width: 1px; border-top: 1px solid #C0C0C0; border-bottom-width: 1px ;margin-left:6; margin-top:0; margin-bottom:0; font-size:9pt'>&nbsp;</td></tr><tr><td align='center'><span style='font-size: 17pt;color:#0000FF'><b>" + MCUtil.getBundleString("message.notify.request.samecode.title") + " " + reqBean.getRequestNumber() + "</b></span></td></tr><tr><td>";
        
        content = content + "<span style='color: rgb(0, 32, 96);'>Kính gởi " + emp.getFullname() + ",</span></p>" + "<p class='yiv1749408891MsoNormal'><span style='color: rgb(0, 32, 96);'> &nbsp;</span></p>" + "<p class='yiv1749408891MsoNormal'><span style='color: rgb(0, 32, 96);'>Hiện tại," + " Ban dự án " + reqBean.getWhichUseName() + "/" + reqBean.getOrganizationName() + " có Phiếu đề xuất số " + reqBean.getRequestNumber() + " ngày " + reqBean.getCreatedDate() + " về việc mua VT/CCDC để phục vụ thi công " + reqBean.getWhichUseName() + "." + " Nhóm Quản lý Thư viện đề xuất thay vật tư " + matBean.getNameVn() + " thành vật tư " + oldMat.getNameVn() + "(Mã VT : " + oldMat.getCode() + ").</span></p><p class='yiv1749408891MsoNormal'>" + "</p><p class='yiv1749408891MsoNormal'><span style='color: rgb(0, 32, 96);'> &nbsp;</span></p><p class='yiv1749408891MsoNormal'>" + "<span style='color: rgb(0, 32, 96);'>Trân trọng kính chào.</span></p><p class='yiv1749408891MsoNormal'>" + "<span style='color: rgb(0, 32, 96);'>Phần mềm QL Cung ứng VTTB</span>";
        
        content = content + "</td></tr><tr><td><div style='border-left-width: 1px; border-right-width: 1px; border-top: 1px solid #C0C0C0; border-bottom-width: 1px' align='center'><font face=\"Trebuchet MS\" size=\"1\"><BR>If your browser does not support UNICODE, please open attachment or contact Webmaster for more infomation, thank you! <BR>" + MCUtil.getBundleString("message.notify.senderMail") + "</font></div></td></tr></table></body></html>";
        
        String mail_subject = MCUtil.getBundleString("message.notify.request.samecode.title") + " " + reqBean.getRequestNumber();
        MailHandle.sendMail(MCUtil.getBundleString("message.notify.senderMail"), mail_to, mail_cc, mail_subject, content, "", "", MCConfig.getMailServer());
      }
    }
    catch (Exception ex)
    {
      LogUtil.error("FAILED:Request:sendSameCodeEmail-" + ex.getMessage());
    }
  }
}
