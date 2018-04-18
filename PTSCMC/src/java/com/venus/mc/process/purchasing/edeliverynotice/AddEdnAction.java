/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.process.purchasing.edeliverynotice;

import com.venus.core.auth.OnlineUser;
import com.venus.core.util.LogUtil;
import com.venus.mc.bean.EdnBean;
import com.venus.mc.bean.EdnDetailBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.EdnDAO;
import com.venus.mc.util.MCUtil;
import com.venus.mc.util.PermissionUtil;
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
 * @author mai vinh loc
 */
public class AddEdnAction extends SpineAction {

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
        //int kind = NumberUtil.parseInt(request.getParameter("kind"),0);
        EdnFormBean formBean = (EdnFormBean) form;
        EdnDAO dnDAO = new EdnDAO();
        EdnBean bean = null;
        boolean bNew = false;
        boolean isExist = false;
        try {
            bean = dnDAO.getDnByNumber(formBean.getDnNumber());
        } catch (Exception ex) {
        }
        int dnId = formBean.getDnId();
        if (dnId == 0) {
            bNew = true;
            if (bean != null) {
                isExist = true;
            }
        } else {
            bNew = false;
            if (bean != null && bean.getDnId() != dnId) {
                isExist = true;
            }
        }
        if (isExist) {
            ActionMessages errors = new ActionMessages();
            errors.add("dnExisted", new ActionMessage("errors.dn.existed"));
            saveErrors(request, errors);
            return false;
        }
        bean = new EdnBean();
        bean.setDnId(formBean.getDnId());
        bean.setDnNumber(formBean.getDnNumber());
        bean.setCreatedDate(formBean.getCreatedDate());
        bean.setExpectedDate(formBean.getExpectedDate());
        bean.setDeliveryPlace(formBean.getDeliveryPlace());
        bean.setDeliveryPresenter(formBean.getDeliveryPresenter());
        bean.setContractNumber(formBean.getContractNumber());
        bean.setCreatedOrg(formBean.getCreatedOrg());
        bean.setProId(formBean.getProId());
        try {
            if (bNew) {
                dnId = dnDAO.insertDn(bean);
            } else {                
                OnlineUser user = MCUtil.getOnlineUser(session);
                LogUtil.info("Username : " + user.getName() + " - Form : " + mapping.getPath() + " - EDIT");
                dnDAO.updateDn(bean);
            }
            session.setAttribute("id", dnId);
            if (dnId != 0) {
                ArrayList arrDet = null;
                try {
                    arrDet = dnDAO.getDnDetails(dnId);
                } catch (Exception ex) {
                    arrDet = new ArrayList();
                }

                if (formBean.getDelDetId() != null) {
                    int[] detIds = formBean.getDelDetId();
                    int[] matIds = formBean.getMatId();
                    EdnDetailBean detBean = new EdnDetailBean();
                    for (int i = 0; i < detIds.length; i++) {
                        detBean.setDnId(dnId);
                        detBean.setDetId(detIds[i]);
                        detBean.setMatId(matIds[i]);
                        detBean.setPrice(formBean.getPrice()[i]);
                        detBean.setQuantity(formBean.getQuantity()[i]);
                        detBean.setNote(formBean.getNote()[i]);
                        if (detIds[i] > 0) {//old
                            try {
                                if (detExisted(arrDet, detIds[i])) {
                                    dnDAO.updateDnDetail(detBean);
                                }
                            } catch (Exception ex) {
                            }
                        } else {//new
                            try {
                                dnDAO.insertDnDetail(detBean);
                            } catch (Exception ex) {
                            }
                        }
                    }
                }
            }
        } catch (Exception ex) {
            LogUtil.error("FAILED:Dn:add-" + ex.getMessage());
            ex.printStackTrace();
        }
        return true;
    }

    private boolean detExisted(ArrayList arrDet, int detId) {
        EdnDetailBean formBean = null;
        for (int i = 0; i < arrDet.size(); i++) {
            formBean = (EdnDetailBean) arrDet.get(i);
            if (formBean.getDetId() == detId) {
                arrDet.remove(formBean);
                //EdnDetailBean bean = new EdnDetailBean(formBean.getDetId());
                return true;
            }
        }
        return false;
    }

    @Override
    protected String getFunction() {
        return PermissionUtil.FUNC_EDIT + "";
    }

    @Override
    protected int getPermit() {
        return PermissionUtil.PER_PURCHASING_DELIVERYNOTICE_PROJECT;
    }
}
