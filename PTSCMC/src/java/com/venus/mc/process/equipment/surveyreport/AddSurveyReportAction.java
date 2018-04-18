/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.process.equipment.surveyreport;

import com.venus.core.auth.OnlineUser;
import com.venus.core.util.GenericValidator;
import com.venus.core.util.LogUtil;
import com.venus.mc.bean.SurveyReportBean;
import com.venus.mc.bean.SurveyReportDetailBean;
import com.venus.mc.bean.SurveyReportMaterialBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.SurveyReportDAO;
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
public class AddSurveyReportAction extends SpineAction {

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
        SurveyReportFormBean formBean = (SurveyReportFormBean) form;
        SurveyReportDAO surveyReportDAO = new SurveyReportDAO();
        int srId = formBean.getSrId();
        boolean bNew = false;
        if (srId == 0) {
            bNew = true;
        } else {
            bNew = false;
        }
        SurveyReportBean bean = new SurveyReportBean();
        bean.setSrId(srId);
        bean.setSrNumber(formBean.getSrNumber());
        bean.setCreatedEmp(formBean.getCreatedEmp());
        bean.setCreatedDate(formBean.getCreatedDate());
        bean.setSurveyDate(formBean.getSurveyDate());
        bean.setCreatedTime(formBean.getCreatedTime());
        bean.setCreatedLocation(formBean.getCreatedLocation());
        bean.setManagerEmp(formBean.getManagerEmp());
        bean.setManagerEquipmentEmp(formBean.getManagerEquipmentEmp());
        bean.setReasonToRepair(formBean.getReasonToRepair());
        bean.setRequest(formBean.getRequest());
        bean.setComment(formBean.getComment());

        try {
            boolean isExist = false;
            isExist = surveyReportDAO.checkSrNumber(formBean.getSrId(), formBean.getSrNumber());
            if (isExist) {
                ActionMessages errors = new ActionMessages();
                errors.add("existedSrNumber", new ActionMessage("errors.surveyreport.existedSrNumber"));
                saveErrors(request, errors);
                return false;
            }
            isExist = false;

            if (bNew) {
                bean.setCreatedEmp(MCUtil.getMemberID(request.getSession()));
                srId = surveyReportDAO.insertSurveyReport(bean);
            } else {                
                OnlineUser user = MCUtil.getOnlineUser(session);
                LogUtil.info("Username : " + user.getName() + " - Form : " + mapping.getPath() + " - EDIT");
                surveyReportDAO.updateSurveyReport(bean);
            }
            session.setAttribute("id", srId);
            if (srId != 0) {
                ArrayList arrDet = null;
                ArrayList arrMat = null;
                try {
                    arrDet = surveyReportDAO.getSurveyReportDetails(srId);
                    arrMat = surveyReportDAO.getSurveyReportMaterials(srId);
                } catch (Exception ex) {
                    arrDet = new ArrayList();
                    arrMat = new ArrayList();
                }
                //Detail
                if (formBean.getReqDetId() != null) {//old
                    String[] detIds = formBean.getReqDetId();
                    String[] equIds = formBean.getEquId();
                    SurveyReportDetailBean detBean = new SurveyReportDetailBean();
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
                                        detBean.setSrId(srId);
                                        detBean.setEquId(Integer.parseInt(formBean.getEquId()[i]));
                                        canUpdate = true;
                                    }

                                    if (canUpdate) {
                                        surveyReportDAO.updateSurveyReportDetail(detBean);
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

                                detBean.setSrId(srId);
                                detBean.setQuantity(quantity);
                                detBean.setEquId(Integer.parseInt(equIds[i]));
                                surveyReportDAO.insertSurveyReportDetail(detBean);
                            } catch (Exception ex) {
                            }
                        }
                    }
                }
                //Material
                if (formBean.getReqMatId() != null) {//old
                    String[] detIds = formBean.getReqMatId();
                    String[] matIds = formBean.getMatId();
                    SurveyReportMaterialBean detBean = new SurveyReportMaterialBean();
                    double quantity = 0;
                    boolean canUpdate = false;
                    for (int i = 0; i < detIds.length; i++) {
                        canUpdate = false;
                        if (!detIds[i].equals("0")) {//old
                            try {
                                detBean = detMaterialExisted(arrMat, Integer.parseInt(detIds[i]));
                                if (detBean != null) {
                                    quantity = Double.parseDouble(formBean.getQt()[i]);
                                    if (quantity != detBean.getQt()) {
                                        detBean.setQt(quantity);
                                        detBean.setSrId(srId);
                                        detBean.setMatId(Integer.parseInt(formBean.getMatId()[i]));
                                        canUpdate = true;
                                    }

                                    if (canUpdate) {
                                        surveyReportDAO.updateSurveyReportMaterial(detBean);
                                    }
                                }
                            } catch (Exception ex) {
                            }
                        } else {//new
                            try {
                                if (GenericValidator.isBlankOrNull(formBean.getQt()[i])) {
                                    quantity = 0;
                                } else {
                                    quantity = Double.parseDouble(formBean.getQt()[i]);
                                }
                                
                                detBean.setSrId(srId);
                                detBean.setQt(quantity);
                                detBean.setMatId(Integer.parseInt(matIds[i]));
                                surveyReportDAO.insertSurveyReportMaterial(detBean);
                            } catch (Exception ex) {
                            }
                        }
                    }
                }
            }
        } catch (Exception ex) {
            LogUtil.error("FAILED:SurveyReport:add-" + ex.getMessage());
            ex.printStackTrace();
        }
        return true;
    }

    private SurveyReportDetailBean detExisted(ArrayList arrDet, int detId) {
        SurveyReportDetailBean formBean = null;
        for (int i = 0; i < arrDet.size(); i++) {
            formBean = (SurveyReportDetailBean) arrDet.get(i);
            if (formBean.getDetId() == detId) {
                arrDet.remove(formBean);
                SurveyReportDetailBean bean = new SurveyReportDetailBean(detId);
                bean.setQuantity(formBean.getQuantity());
                bean.setEquId(formBean.getEquId());
                bean.setUsedCode(formBean.getUsedCode());
                return bean;
            }
        }
        return null;
    }

    private SurveyReportMaterialBean detMaterialExisted(ArrayList arrDet, int detId) {
        SurveyReportMaterialBean formBean = null;
        for (int i = 0; i < arrDet.size(); i++) {
            formBean = (SurveyReportMaterialBean) arrDet.get(i);
            if (formBean.getDetId() == detId) {
                arrDet.remove(formBean);
                SurveyReportMaterialBean bean = new SurveyReportMaterialBean(detId);
                bean.setQt(formBean.getQt());
                bean.setMatId(formBean.getMatId());
                bean.setCode(formBean.getCode());
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
