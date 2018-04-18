/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.otherAction;

import com.venus.core.util.GenericValidator;
import com.venus.mc.bean.MaterialBean;
import com.venus.mc.bean.SpeBean;
import com.venus.mc.dao.MaterialDAO;
import com.venus.mc.dao.SpeDAO;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author phuongtu
 */
public class UpdateSpecMaterialAction extends org.apache.struts.action.Action {

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
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        try {
            MaterialDAO matDAO = new MaterialDAO();
            ArrayList materials = matDAO.getMaterials();
            MaterialBean bean = null;
            String code = "";
            for (int i = 0; i < materials.size(); i++) {
                try {
                    bean = (MaterialBean) materials.get(i);
                    code = bean.getCode();
                    if (!GenericValidator.isBlankOrNull(code)) {
                        code = code.replace(".", ";");
                        String[] spes = code.split(";");
                        //kiem tra spec1
//                        String specSign = StringUtil.encodeHTML(data.getSpec1());
                        String speText = "";
                        if (!GenericValidator.isBlankOrNull(spes[0])) {
                            int spec1 = searchSpec1(spes[0]);

                            bean.setSpe1Id(spec1);
                            speText += spec1 + ";";
                            //kiem tra spec2
                            if (!GenericValidator.isBlankOrNull(spes[1])) {
                                int spec2 = searchSpec2(spes[1], spec1);
                                bean.setSpe2Id(spec2);
                                speText += spec2 + ";";
                                //kiem tra spec3
                                if (!GenericValidator.isBlankOrNull(spes[2])) {
                                    int spec3 = searchSpec3(spes[2], spec2);
                                    bean.setSpe3Id(spec3);
                                    speText += spec3 + ";";
                                    //kiem tra spec4
                                    if (!GenericValidator.isBlankOrNull(spes[3])) {
                                        int spec4 = searchSpec4(spes[3], spec3);
                                        bean.setSpe4Id(spec4);
                                        speText += spec4 + ";";
                                        //kiem tra spec5
                                        if (!GenericValidator.isBlankOrNull(spes[4])) {
                                            int spec5 = searchSpec5(spes[4], spec4);
                                            bean.setSpe5Id(spec5);
                                            speText += spec5 + ";";
                                        } else {
                                            speText += "???;";
                                        }
                                    } else {
                                        speText += "???;";
                                    }
                                } else {
                                    speText += "???;???;";
                                }
                            } else {
                                speText += "???;???;???;";
                            }
                        } else {
                            speText += "???;???;???;???;";
                        }
                        speText += "???";
                        bean.setSpe(speText);
                        matDAO.updateMaterial(bean);
                    }
                } catch (Exception ex) {
                }
            }
        } catch (Exception ex) {
        }
        return null;
    }

    private int searchSpec1(String sign) {
        int specId = 0;
        SpeDAO dao = new SpeDAO();
        try {
            SpeBean bean = dao.getSpe1BySign(sign);
            if (bean != null) {
                specId = bean.getSpe1Id();
            }
        } catch (Exception ex) {
        }
        return specId;
    }

    private int searchSpec2(String sign, int spec1) {
        int specId = 0;
        SpeDAO dao = new SpeDAO();
        try {
            SpeBean bean = dao.getSpe2BySign(sign, spec1);
            if (bean != null) {
                specId = bean.getSpe2Id();
            }
        } catch (Exception ex) {
        }
        return specId;
    }

    private int searchSpec3(String sign, int spec2) {
        int specId = 0;
        SpeDAO dao = new SpeDAO();
        try {
            SpeBean bean = dao.getSpe3BySign(sign, spec2);
            if (bean != null) {
                specId = bean.getSpe3Id();
            }
        } catch (Exception ex) {
        }
        return specId;
    }

    private int searchSpec4(String sign, int spec3) {
        int specId = 0;
        SpeDAO dao = new SpeDAO();
        try {
            SpeBean bean = dao.getSpe4BySign(sign, spec3);
            if (bean != null) {
                specId = bean.getSpe4Id();
            }
        } catch (Exception ex) {
        }
        return specId;
    }

    private int searchSpec5(String sign, int spec4) {
        int specId = 0;
        SpeDAO dao = new SpeDAO();
        try {
            SpeBean bean = dao.getSpe5BySign(sign, spec4);
            if (bean != null) {
                specId = bean.getSpe5Id();
            }
        } catch (Exception ex) {
        }
        return specId;
    }
}
