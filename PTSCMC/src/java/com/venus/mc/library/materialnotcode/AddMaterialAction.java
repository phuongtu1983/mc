package com.venus.mc.library.materialnotcode;

/**
 * @author Mai Vinh Loc
 */
import com.venus.core.auth.OnlineUser;
import com.venus.core.util.GenericValidator;
import com.venus.core.util.LogUtil;
import com.venus.core.util.StringUtil;
import com.venus.mc.bean.MaterialBean;
import com.venus.mc.bean.RequestBean;
import com.venus.mc.material.MaterialFormBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.EmployeeDAO;
import com.venus.mc.dao.MaterialDAO;
import com.venus.mc.dao.RequestDAO;
import com.venus.mc.util.MCUtil;
import com.venus.mc.util.MailHandle;
import com.venus.mc.util.PermissionUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

public class AddMaterialAction extends SpineAction {

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
        MaterialFormBean formBean = (MaterialFormBean) form;
        int matId = formBean.getMatId();
        MaterialDAO materialDAO = new MaterialDAO();
        boolean bNew = false;
        if (matId == 0) {
            bNew = true;
        } else {
            bNew = false;
//            try {
//                MaterialBean oldb = materialDAO.getMaterialByCode(formBean.getCode());
//                if (oldb == null) {
//                    bNew = true;
//                }
//            } catch (Exception ex) {
//            }
        }

        MaterialBean bean = new MaterialBean();
        bean.setMatId(formBean.getMatId());
        bean.setOriId(formBean.getOriId());
        bean.setUniId(formBean.getUniId());
        bean.setCode(formBean.getCode());
        bean.setSpe1Id(formBean.getSpe1Id());
        bean.setSpe2Id(formBean.getSpe2Id());
        bean.setSpe3Id(formBean.getSpe3Id());
        bean.setSpe4Id(formBean.getSpe4Id());
        bean.setSpe5Id(formBean.getSpe5Id());
        bean.setSpe6Id(formBean.getSpe6Id());
        bean.setSpe7(formBean.getSpe7());
        bean.setNameEn(StringUtil.encodeHTML(formBean.getNameEn()));
        bean.setNameVn(StringUtil.encodeHTML(formBean.getNameVn()));
        // if (bean.getNameEn().length() == 0) {
        //      bean.setNameEn(bean.getNameVn());
        //  }

        bean.setType(formBean.getType());
        bean.setKind(formBean.getKind());
        bean.setNote(StringUtil.encodeHTML(formBean.getNote()));
        bean.setGroId(formBean.getGroId());
        bean.setQc(StringUtil.encodeHTML(formBean.getQc()));
        bean.setSpe(formBean.getSpe());
        bean.setDeliveryTime(StringUtil.encodeHTML(formBean.getDeliveryTime()));

        try {
            boolean isExist = false;
            isExist = materialDAO.checkCode(formBean.getMatId(), formBean.getCode());
            if (isExist) {
                ActionMessages errors = new ActionMessages();
                errors.add("matCodeExisted", new ActionMessage("errors.material.existedCode"));
                saveErrors(request, errors);
                return false;
            }
            int m = 0;
            m = materialDAO.checkName(bean.getMatId(), bean.getNameVn(), bean.getNameEn());
            if (m > 0) {
                ActionMessages errors = new ActionMessages();
                errors.add("matNameExisted", new ActionMessage("errors.material.existedName"));
                saveErrors(request, errors);
                return false;
            }
            if (bNew) {
                materialDAO.insertMaterial(bean);

            } else {
                HttpSession session = request.getSession();
                OnlineUser user = MCUtil.getOnlineUser(session);
                LogUtil.info("Username : " + user.getName() + " - Form : " + mapping.getPath() + " - EDIT");
                materialDAO.updateMaterial(bean);
                if (formBean.getReqId() > 0) {
                    RequestDAO requestDAO = new RequestDAO();
                    String str = requestDAO.getMaterialNullCode(formBean.getReqId());
                    requestDAO.updateUnitRequestDetail(formBean.getUniId(), formBean.getReqId(), formBean.getMatId());
                    if (GenericValidator.isBlankOrNull(str)) {
                        notifyForNotCompleteMaterialCode(formBean.getReqId());
                    }
                }
            }
        } catch (Exception ex) {
            LogUtil.error("FAILED:Vender:add-" + ex.getMessage());
            ex.printStackTrace();
        }
        return true;
    }

    private void notifyForNotCompleteMaterialCode(int reqId) {
        try {
            RequestDAO dao = new RequestDAO();
            RequestBean reqBean = dao.getRequest(reqId);
            if (reqBean != null) {
                String content = "";
                content += "<html><head><meta http-equiv='Content-Language' content='en-us'><meta http-equiv='Content-Type' content='text/html; charset=UTF-8'></head><body>";
                content += "<table align='center' width='70%'><tr><td class='cart_b' width='761' height='31' align='center'>"
                        + "<font color='#CCCCCC' face='Wingdings' style='font-size: 13pt'>? ? ? ? ? ? ? ? ? ? ? ? ? ? ? ? ? ? ? ?</font></td></tr>"
                        + "<tr><td class='cart_b' style='border-left-width: 1px; border-right-width: 1px; border-top: 1px solid #C0C0C0; border-bottom-width: 1px ;margin-left:6; margin-top:0; margin-bottom:0; font-size:9pt'>&nbsp;</td></tr>"
                        + "<tr><td align='center'>"
                        + "<span style='font-size: 17pt;color:#0000FF'><b>"
                        + MCUtil.getBundleString("message.notify.request.completecode.title") + " " + reqBean.getRequestNumber()
                        + "</b></span></td></tr><tr><td>";
                content += "Kính gửi " + reqBean.getOrganizationName() + "/" + "BDA <span style='border-bottom: 2px dotted rgb(54, 99, 136); cursor: pointer;' class='yshortcuts' id='lw_1312562954_1'>"
                        + reqBean.getWhichUseName() + "</span>,</span></p><p class='yiv487906320MsoNormal'><span style='color: rgb(0, 32, 96);'>"
                        + "Hiện tại Phiếu đề xuất </span><span style='color: rgb(0, 32, 96);'>số " + reqBean.getRequestNumber() + " ngày " + reqBean.getCreatedDate()
                        + " về việc mua VT/CCDC để phục vụ thi công " + reqBean.getWhichUseName() + "</span><span style='color: rgb(0, 32, 96);'>"
                        + "đã được Nhóm Quản lý Thư viện bổ sung mã đầy đủ, Kính đề nghị " + reqBean.getOrganizationName() + "/BDA "
                        + reqBean.getWhichUseName() + " vào phần mềm để xử lý tiếp theo.</span></p><p class='yiv487906320MsoNormal'>"
                        + "</p><p class='yiv487906320MsoNormal'><span style='color: rgb(0, 32, 96);'> &nbsp;</span></p><p class='yiv487906320MsoNormal'>"
                        + "<span style='color: rgb(0, 32, 96);'>Trân trọng kính chào.</span></p><p class='yiv487906320MsoNormal'><span style='color: rgb(0, 32, 96);'>Phần mềm QL Cung ứng VTTB</span>";

                content += "</td></tr><tr><td><div style='border-left-width: 1px; border-right-width: 1px; border-top: 1px solid #C0C0C0; border-bottom-width: 1px' align='center'><font face=\"Trebuchet MS\" size=\"1\"><BR>If your browser does not support UNICODE, please open attachment or contact Webmaster for more infomation, thank you! <BR>"
                        + MCUtil.getBundleString("message.notify.senderMail") + "</font></div></td></tr></table></body></html>";
                EmployeeDAO empDAO = new EmployeeDAO();
                String mail_to = empDAO.getEmailOfEmployees(reqBean.getCreatedEmp() + "");
                String mail_subject = MCUtil.getBundleString("message.notify.request.completecode.title") + " " + reqBean.getRequestNumber();
                MailHandle.sendMail(MCUtil.getBundleString("message.notify.senderMail"), mail_to, mail_subject, content);
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
        return PermissionUtil.PER_LIBRARY_MATERIAL_COMPANY;
    }
}
