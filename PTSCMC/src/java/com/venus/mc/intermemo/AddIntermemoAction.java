/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.intermemo;

import com.venus.core.auth.OnlineUser;
import com.venus.core.util.GenericValidator;
import com.venus.core.util.LogUtil;
import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.RequestBean;
import com.venus.mc.bean.RequestDetailBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.RequestDAO;
import com.venus.mc.request.RequestDetailFormBean;
import com.venus.mc.request.RequestFormBean;
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
 * @author phuongtu
 */
public class AddIntermemoAction extends SpineAction {

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
        IntermemoFormBean formBean = (IntermemoFormBean) form;
        RequestDAO requestDAO = new RequestDAO();
        RequestBean bean = null;
        boolean bNew = false;
        boolean isExist = false;
        try {
            bean = requestDAO.getRequestByNumber(formBean.getRequestNumber());
        } catch (Exception ex) {
        }
        int reqId = formBean.getReqId();
        if (reqId == 0) {
            bNew = true;
            if (bean != null) {
                isExist = true;
            }
        } else {
            bNew = false;
            if (bean != null && bean.getReqId() != reqId) {
                isExist = true;
            }
        }
        if (isExist) {
            ActionMessages errors = new ActionMessages();
            errors.add("requestExisted", new ActionMessage("errors.request.existed"));
            saveErrors(request, errors);
            return false;
        }
        bean = new RequestBean();
        bean.setReqId(reqId);
        bean.setRequestNumber(formBean.getRequestNumber());
        bean.setCreatedEmp(formBean.getCreatedEmp());
        bean.setStatusSuggest(formBean.getStatusSuggest());
        bean.setIntermemoSubject(formBean.getSubject());
        bean.setKind(RequestBean.INTERMEMO);
        bean.setCreatedOrg(formBean.getCreatedOrg());
        bean.setOrgId(formBean.getProId());
        bean.setApproveEmp(formBean.getApproveEmp());
        bean.setWhichUse(RequestFormBean.WHICHUSE_PROJECT);
        bean.setAssignedEmp(formBean.getAssignedEmp());
        try {
            if (bNew) {
                bean.setCreatedEmp(MCUtil.getMemberID(request.getSession()));
                reqId = requestDAO.insertRequest(bean);
            } else {                
                OnlineUser user = MCUtil.getOnlineUser(session);
                LogUtil.info("Username : " + user.getName() + " - Form : " + mapping.getPath() + " - EDIT");
                requestDAO.updateRequest(bean);
            }
            session.setAttribute("id", reqId);
            if (reqId != 0) {
                ArrayList arrDet = null;
                try {
                    arrDet = requestDAO.getRequestDetails(reqId);
                } catch (Exception ex) {
                    arrDet = new ArrayList();
                }

                if (formBean.getReqDetId() != null) {//old
                    String[] detIds = formBean.getReqDetId();
                    String[] matIds = formBean.getMatId();
                    RequestDetailBean detBean = null;
                    double additionQuantity = 0;
                    double requestQuantity = 0;
                    boolean canUpdate = false;
                    for (int i = 0; i < detIds.length; i++) {
                        canUpdate = false;
                        if (!detIds[i].equals("0")) {//old
                            try {
                                detBean = detExisted(arrDet, Integer.parseInt(detIds[i]));
                                if (detBean != null) {
                                    additionQuantity = Double.parseDouble(formBean.getAdditionalQuantity()[i]);
                                    if (additionQuantity != detBean.getAdditionalQuantity()) {
                                        detBean.setAdditionalQuantity(additionQuantity);
                                        canUpdate = true;
                                    }
                                    if (detBean.getMaterialKind() != NumberUtil.parseInt(formBean.getMaterialKind()[i], 0)) {
                                        detBean.setMaterialKind(NumberUtil.parseInt(formBean.getMaterialKind()[i], 0));
                                        canUpdate = true;
                                    }
                                    requestQuantity = Double.parseDouble(formBean.getRequestQuantity()[i]);
                                    if (requestQuantity != detBean.getRequestQuantity()) {
                                        detBean.setRequestQuantity(requestQuantity);
                                        canUpdate = true;
                                    }
                                    if (!detBean.getIntermemoNote().equals(formBean.getNote()[i])) {
                                        detBean.setIntermemoNote(formBean.getNote()[i]);
                                        canUpdate = true;
                                    }
                                    if (canUpdate) {
                                        requestDAO.updateIntermemoDetail(detBean);
                                    }
                                }
                            } catch (Exception ex) {
                            }
                        } else {//new
                            try {
                                if (GenericValidator.isBlankOrNull(formBean.getAdditionalQuantity()[i])) {
                                    additionQuantity = 0;
                                } else {
                                    additionQuantity = Double.parseDouble(formBean.getAdditionalQuantity()[i]);
                                }
                                if (GenericValidator.isBlankOrNull(formBean.getRequestQuantity()[i])) {
                                    requestQuantity = 0;
                                } else {
                                    requestQuantity = Double.parseDouble(formBean.getRequestQuantity()[i]);
                                }
                                detBean = new RequestDetailBean(0, formBean.getUnit()[i], Double.parseDouble(formBean.getPresentQuantity()[i]), additionQuantity, requestQuantity, "", 0, 0, 0, 0, formBean.getNote()[i]);
                                detBean.setReqId(reqId);
                                detBean.setMaterialKind(NumberUtil.parseInt(formBean.getMaterialKind()[i], 0));
                                detBean.setMatId(Integer.parseInt(matIds[i]));
                                detBean.setStepId(RequestBean.STEP_REQ);
                                detBean.setStep(MCUtil.getBundleString("message.request"));
                                requestDAO.insertRequestDetail(detBean);
                            } catch (Exception ex) {
                            }
                        }
                    }
                }
            }
        } catch (Exception ex) {
            LogUtil.error("FAILED:Intermemo:add-" + ex.getMessage());
            ex.printStackTrace();
        }
        return true;
    }

    private RequestDetailBean detExisted(ArrayList arrDet, int detId) {
        RequestDetailFormBean formBean = null;
        for (int i = 0; i < arrDet.size(); i++) {
            formBean = (RequestDetailFormBean) arrDet.get(i);
            if (formBean.getDetId() == detId) {
                arrDet.remove(formBean);
                RequestDetailBean bean = new RequestDetailBean(detId);
                bean.setAdditionalQuantity(formBean.getAdditionalQuantity());
                bean.setIntermemoNote(formBean.getIntermemoNote());
                bean.setMaterialKind(formBean.getMaterialKind());
                return bean;
            }
        }
        return null;
    }

    @Override
    protected String getFunction() {
        return PermissionUtil.FUNC_EDIT + "";
    }

    @Override
    protected int getPermit() {
        return PermissionUtil.PER_PURCHASING_INTERMEMO;
    }
}
