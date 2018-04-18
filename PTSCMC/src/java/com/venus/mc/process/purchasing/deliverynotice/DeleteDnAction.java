/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.process.purchasing.deliverynotice;

import com.venus.core.auth.OnlineUser;
import com.venus.core.util.LogUtil;
import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.DnDetailBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.DnDAO;
import com.venus.mc.util.MCUtil;
import com.venus.mc.util.PermissionUtil;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author mai vinh loc
 */
public class DeleteDnAction extends SpineAction {

    private String result = "";

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
        HttpSession session = request.getSession();
        String[] arrDnId = request.getParameterValues("dnId");
        int kind = NumberUtil.parseInt(request.getParameter("kind"), 0);
        try {
            OnlineUser user = MCUtil.getOnlineUser(session);
            LogUtil.info("Username : " + user.getName() + " - Form : " + mapping.getPath() + " - DELETE");
            int length = 0;
            DnDAO dnDAO = new DnDAO();
            DnDetailBean detail = null;
            Object a;
            ArrayList arrDnDetail = null;
            if (arrDnId != null) {
                length = arrDnId.length;
            }
            String messages = "";
            for (int i = 0; i < length; i++) {
                if (dnDAO.checkDeleteDnMrir(arrDnId[i]).length() > 0) {
                    messages += MCUtil.getBundleString("message.dn.dnNumber") + " : " + dnDAO.checkDeleteDnMrir(arrDnId[i]).split(";")[0] + " --> " + MCUtil.getBundleString("message.mrir.title") + " : " + dnDAO.checkDeleteDnMrir(arrDnId[i]).split(";")[1] + " <br> ";
                } else if (dnDAO.checkDeleteDnInvoice(arrDnId[i]).length() > 0) {
                    messages += MCUtil.getBundleString("message.dn.dnNumber") + " : " + dnDAO.checkDeleteDnInvoice(arrDnId[i]).split(";")[0] + " --> " + MCUtil.getBundleString("message.invoice") + " : " + dnDAO.checkDeleteDnInvoice(arrDnId[i]).split(";")[1] + " <br> ";
                } else {
                    arrDnDetail = new ArrayList();
                    arrDnDetail = dnDAO.getDnDetails(NumberUtil.parseInt(arrDnId[i], 0));
                    if (arrDnDetail.size() > 0) {
                        for (int j = 0; j < arrDnDetail.size(); j++) {
                            detail = new DnDetailBean();
                            a = arrDnDetail.get(j);
                            BeanUtils.copyProperties(detail, a);
                            
                            if (kind == 2){
                                dnDAO.updateUsedMaterialImportQtDnDelete(String.valueOf(detail.getDetId()), String.valueOf(detail.getConDetId()));
                            }else{
                                dnDAO.updateContractDetailQtDnDelete(String.valueOf(detail.getDetId()), String.valueOf(detail.getReqDetailId()));
                            }
                        }
                        dnDAO.deleteDnDetails(arrDnId[i]);
                    }
                    dnDAO.deleteDn(arrDnId[i]);
                }
            }
            if (!messages.equals("")) {
                result = messages;
                return false;
            }
        } catch (Exception ex) {
            LogUtil.error("FAILED:DeliveryRequest:delete-" + ex.getMessage());
            ex.printStackTrace();
        }
        return true;
    }

    @Override
    protected String getErrorsString(HttpServletRequest request) {
        return result;
    }

    @Override
    protected String getFunction() {
        return PermissionUtil.FUNC_DELETE + "";
    }
    /*
    @Override
    protected int getPermit() {
    return PermissionUtil.PER_PURCHASING;
    }
     */
}
