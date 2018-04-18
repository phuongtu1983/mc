/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.process.equipment.acceptancetest;

import com.venus.core.auth.OnlineUser;
import com.venus.core.util.GenericValidator;
import com.venus.core.util.LogUtil;
import com.venus.mc.bean.AcceptanceTestBean;
import com.venus.mc.bean.AcceptanceTestDetailBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.AcceptanceTestDAO;
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
public class AddAcceptanceTestAction extends SpineAction {

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
        AcceptanceTestFormBean formBean = (AcceptanceTestFormBean) form;
        AcceptanceTestDAO acceptanceTestDAO = new AcceptanceTestDAO();
        int atId = formBean.getAtId();
        boolean bNew = false;
        if (atId == 0) {
            bNew = true;
        } else {
            bNew = false;
        }
        AcceptanceTestBean bean = new AcceptanceTestBean();
        bean.setAtId(atId);
        bean.setCreatedDate(formBean.getCreatedDate());
        bean.setTestDate(formBean.getTestDate());
        bean.setCreatedEmp(formBean.getCreatedEmp());
        bean.setAtNumber(formBean.getAtNumber());
        bean.setCreatedTime(formBean.getCreatedTime());
        bean.setCreatedLocation(formBean.getCreatedLocation());
        bean.setManagerEmp(formBean.getManagerEmp());
        bean.setManagerEquipmentEmp(formBean.getManagerEquipmentEmp());
        bean.setManagerQAEmp(formBean.getManagerQAEmp());
        bean.setResultAfterRepair(formBean.getResultAfterRepair());
        bean.setResult(formBean.getResult());
        bean.setComment(formBean.getComment());
        bean.setSrId(formBean.getSrId());

        try {
            boolean isExist = false;
            isExist = acceptanceTestDAO.checkAtNumber(formBean.getAtId(), formBean.getAtNumber());
            if (isExist) {
                ActionMessages errors = new ActionMessages();
                errors.add("existedAtNumber", new ActionMessage("errors.acceptancetest.existedAtNumber"));
                saveErrors(request, errors);
                return false;
            }
            isExist = false;

            if (bNew) {
                bean.setCreatedEmp(MCUtil.getMemberID(request.getSession()));
                atId = acceptanceTestDAO.insertAcceptanceTest(bean);
            } else {
                OnlineUser user = MCUtil.getOnlineUser(session);
                LogUtil.info("Username : " + user.getName() + " - Form : " + mapping.getPath() + " - EDIT");
                acceptanceTestDAO.updateAcceptanceTest(bean);
            }
            session.setAttribute("id", atId);
            if (atId != 0) {
                ArrayList arrDet = null;
                try {
                    arrDet = acceptanceTestDAO.getAcceptanceTestDetails(atId);
                } catch (Exception ex) {
                    arrDet = new ArrayList();
                }

                if (formBean.getReqDetId() != null) {//old
                    String[] detIds = formBean.getReqDetId();
                    String[] equIds = formBean.getEquId();
                    AcceptanceTestDetailBean detBean = new AcceptanceTestDetailBean();
                    double quantity = 0;
                    boolean canUpdate = false;
                    for (int i = 0; i < detIds.length; i++) {
                        canUpdate = false;
                        if (!detIds[i].equals("0")) {//old
                            try {
                                detBean = detExisted(arrDet, Integer.parseInt(detIds[i]));
                                if (detBean != null) {
                                    quantity = Double.parseDouble(formBean.getQuantity()[i]);
                                    if (quantity != detBean.getQuantity()) {
                                        detBean.setQuantity(quantity);
                                        detBean.setAtId(atId);
                                        detBean.setEquId(Integer.parseInt(formBean.getEquId()[i]));
                                        canUpdate = true;
                                    }

                                    if (canUpdate) {
                                        acceptanceTestDAO.updateAcceptanceTestDetail(detBean);
                                    }
                                }
                            } catch (Exception ex) {
                            }
                        } else {//new
                            try {
                                if (GenericValidator.isBlankOrNull(formBean.getQuantity()[i])) {
                                    quantity = 0;
                                } else {
                                    quantity = Double.parseDouble(formBean.getQuantity()[i]);
                                }

                                detBean.setAtId(atId);
                                detBean.setQuantity(quantity);
                                detBean.setEquId(Integer.parseInt(equIds[i]));
                                acceptanceTestDAO.insertAcceptanceTestDetail(detBean);
                            } catch (Exception ex) {
                            }
                        }
                    }
                }
            }
        } catch (Exception ex) {
            LogUtil.error("FAILED:AcceptanceTest:add-" + ex.getMessage());
            ex.printStackTrace();
        }
        return true;
    }

    private AcceptanceTestDetailBean detExisted(ArrayList arrDet, int detId) {
        AcceptanceTestDetailBean formBean = null;
        for (int i = 0; i < arrDet.size(); i++) {
            formBean = (AcceptanceTestDetailBean) arrDet.get(i);
            if (formBean.getDetId() == detId) {
                arrDet.remove(formBean);
                AcceptanceTestDetailBean bean = new AcceptanceTestDetailBean(detId);
                bean.setQuantity(formBean.getQuantity());
                bean.setEquId(formBean.getEquId());
                bean.setUsedCode(formBean.getUsedCode());
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
        return PermissionUtil.PER_EQUIPMENT_SURVEYREPORT;
    }
}
