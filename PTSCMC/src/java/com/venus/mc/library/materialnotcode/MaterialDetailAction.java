/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.library.materialnotcode;

import com.venus.core.util.OutputUtil;
import com.venus.mc.bean.MaterialBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.MaterialDAO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author phuongtu
 */
public class MaterialDetailAction extends SpineAction {

    @Override
    public boolean doAction(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        String matId = request.getParameter("matId");
        try {
            MaterialDAO materialDAO = new MaterialDAO();
            MaterialBean bean = materialDAO.getMaterial(matId);
            String json = "{\"matCode\":\"" + bean.getCode() + "\"";
            json += ",\"matName\":\"" + bean.getNameVn() + "\"";
            json += ",\"matUnit\":\"" + bean.getUnitName() + "\"";
            json += "}";
            OutputUtil.sendStringToOutput(response, json);
        } catch (Exception ex) {
        }
        return true;
    }

    @Override
    protected boolean isReturnStream() {
        return true;
    }
}
