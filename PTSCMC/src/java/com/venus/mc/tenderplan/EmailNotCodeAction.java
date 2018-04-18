package com.venus.mc.tenderplan;

import com.venus.core.util.GenericValidator;
import com.venus.core.util.LogUtil;
import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.BidEvalSumBean;
import com.venus.mc.bean.MaterialBean;
import com.venus.mc.bean.TenderPlanBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.BidEvalSumDAO;
import com.venus.mc.dao.EmployeeDAO;
import com.venus.mc.dao.MaterialDAO;
import com.venus.mc.dao.PermissionDAO;
import com.venus.mc.dao.TenderPlanDAO;
import com.venus.mc.dao.TenderPlanDetailDAO;
import com.venus.mc.util.MCConfig;
import com.venus.mc.util.MCUtil;
import com.venus.mc.util.MailHandle;
import com.venus.mc.util.PermissionUtil;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class EmailNotCodeAction
  extends SpineAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    String tenId = request.getParameter("tenId");
    if (!GenericValidator.isBlankOrNull(tenId)) {
      try
      {
        TenderPlanDetailDAO techDAO = new TenderPlanDetailDAO();
        BidEvalSumDAO bidDAO = new BidEvalSumDAO();
        
        String matIds = bidDAO.getMaterialBidEvalSumForEmailNon(NumberUtil.parseInt(tenId, 0), techDAO.getEvalKindTenderPlan(tenId));
        if (!matIds.equals("0"))
        {
          notifyForNotMaterialCode(matIds, NumberUtil.parseInt(tenId, 0), MCUtil.getMemberID(request.getSession()));
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
  
  private void notifyForNotMaterialCode(String matIds, int tenId, int empId)
  {
    try
    {
      BidEvalSumDAO bidDAO = new BidEvalSumDAO();
      TenderPlanDAO tenDAO = new TenderPlanDAO();
      BidEvalSumBean besBean = bidDAO.getBidEvalSum(tenId);
      TenderPlanBean tenBean = tenDAO.getTenderPlan(tenId);
      MaterialDAO matDAO = new MaterialDAO();
      ArrayList matList = matDAO.getMaterials(matIds);
      if (besBean != null)
      {
        String content = "";
        content = content + "<html><head><meta http-equiv='Content-Language' content='en-us'><meta http-equiv='Content-Type' content='text/html; charset=UTF-8'></head><body>";
        content = content + "<table align='center' width='70%'><tr><td class='cart_b' width='761' height='31' align='center'><font color='#CCCCCC' face='Wingdings' style='font-size: 13pt'>? ? ? ? ? ? ? ? ? ? ? ? ? ? ? ? ? ? ? ?</font></td></tr><tr><td class='cart_b' style='border-left-width: 1px; border-right-width: 1px; border-top: 1px solid #C0C0C0; border-bottom-width: 1px ;margin-left:6; margin-top:0; margin-bottom:0; font-size:9pt'>&nbsp;</td></tr><tr><td align='center'><span style='font-size: 17pt;color:#0000FF'><b>" + MCUtil.getBundleString("message.notify.bidevalsum.notcode.title") + " " + besBean.getBesNumber() + "</b></span></td></tr><tr><td>";
        
        content = content + "<span style='color: rgb(0, 32, 96);'>Kính gởi Nhóm Quản lý Thư viện,</span></p><p class='yiv1749408891MsoNormal'><span style='color: rgb(0, 32, 96);'> &nbsp;</span></p><p class='yiv1749408891MsoNormal'><span style='color: rgb(0, 32, 96);'>Hiện tại,";
        
        content = content + " báo cáo đánh giá chào hàng số " + besBean.getBesNumber() + " ngày " + besBean.getCreatedDate() + " của kế hoạch gọi chào hàng " + tenBean.getTenderNumber() + " có một số VTTB chưa có mã như sau : ";
        
        content = content + "<ul>";
        MaterialBean material = null;
        for (int i = 0; i < matList.size(); i++)
        {
          material = (MaterialBean)matList.get(i);
          content = content + "<li><span style=\"color: rgb(0, 32, 96);\">" + material.getNameVn() + "</span></li>";
        }
        content = content + "</ul>";
        
        content = content + " Kính đề nghị Nhóm Quản lý Thư viện bổ sung mã vào thư viện.</span></p><p class='yiv1749408891MsoNormal'></p><p class='yiv1749408891MsoNormal'><span style='color: rgb(0, 32, 96);'> &nbsp;</span></p><p class='yiv1749408891MsoNormal'><span style='color: rgb(0, 32, 96);'>Trân trọng kính chào.</span></p><p class='yiv1749408891MsoNormal'><span style='color: rgb(0, 32, 96);'>Phần mềm QL Cung ứng VTTB</span>";
        
        content = content + "</td></tr><tr><td><div style='border-left-width: 1px; border-right-width: 1px; border-top: 1px solid #C0C0C0; border-bottom-width: 1px' align='center'><font face=\"Trebuchet MS\" size=\"1\"><BR>If your browser does not support UNICODE, please open attachment or contact Webmaster for more infomation, thank you! <BR>" + MCUtil.getBundleString("message.notify.senderMail") + "</font></div></td></tr></table></body></html>";
        
        String employee = "";
        PermissionDAO perDAO = new PermissionDAO();
        employee = perDAO.getEmployeesHasPermission(PermissionUtil.PER_PURCHASING_REQUEST_MATERIALNOTCODE + "", PermissionUtil.FUNC_LIST + "");
        EmployeeDAO empDAO = new EmployeeDAO();
        String mail_to = empDAO.getEmailOfEmployees(employee);
        String mail_cc = empDAO.getEmailOfEmployees(empId + "");
        
        String mail_subject = MCUtil.getBundleString("message.notify.bidevalsum.notcode.title") + " " + besBean.getBesNumber();
        MailHandle.sendMail(MCUtil.getBundleString("message.notify.senderMail"), mail_to, mail_cc, mail_subject, content, "", "", MCConfig.getMailServer());
      }
    }
    catch (Exception ex)
    {
      LogUtil.error("FAILED:Request:sendNotCodeEmail-" + ex.getMessage());
    }
  }
}
