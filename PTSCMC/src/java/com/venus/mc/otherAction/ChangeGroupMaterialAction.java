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
public class ChangeGroupMaterialAction extends org.apache.struts.action.Action {

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
                        if (!GenericValidator.isBlankOrNull(spes[0])) {
                            SpeDAO specDAO = new SpeDAO();
                            SpeBean spe1 = specDAO.getSpe1BySign(spes[0]);
                            if (spe1 == null) {
                                continue;
                            }
                            if (!GenericValidator.isBlankOrNull(spes[1])) {
                                SpeBean spe2 = specDAO.getSpe2BySign(spes[1], spe1.getSpe1Id());
                                if (spe2 == null) {
                                    continue;
                                } else {
                                    bean.setGroId(spe2.getSpe2Id());
                                    matDAO.updateMaterial(bean);
                                }
                            }
                        }
                    }
                } catch (Exception ex) {
                }
            }
        } catch (Exception ex) {
        }
        return null;
    }
}
