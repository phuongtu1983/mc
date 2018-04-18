/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.common;

import com.venus.core.util.NumberUtil;
import com.venus.core.util.OutputUtil;
import com.venus.core.util.StringUtil;
import com.venus.mc.bean.MaterialBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.EmployeeDAO;
import com.venus.mc.dao.MaterialDAO;
import com.venus.mc.dao.OrganizationDAO;
import com.venus.mc.util.Constants;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author thcao
 */
public class GetMaterialJSON extends org.apache.struts.action.Action {

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
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        MaterialDAO matDAO = new MaterialDAO();
        ArrayList arrMat = null;
        try {
            arrMat = matDAO.getMaterials();
        } catch (Exception ex) {
            Logger.getLogger(GetMaterialJSON.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (arrMat != null && arrMat.size() > 0) {
            StringBuffer buff = new StringBuffer();
            
            buff.append("{");
            buff.append(" identifier:\"id\",");
            buff.append(" label: \"name\",");
            buff.append(" items: [");
            
            for (int i = 0; i < arrMat.size(); i++) {
                MaterialBean bean = (MaterialBean) arrMat.get(i);
                String matName = StringUtil.encodeString(bean.getNameVn());
                buff.append("{name:\"").append(matName).append("\",label:\"").append(matName).append("\",id:\"").append(bean.getMatId()).append("\"}");
                if (i != arrMat.size() - 1) {
                    buff.append(",");
                }                
            }
            buff.append(" ]");
            buff.append("}");
            OutputUtil.sendStringToOutput(response,buff.toString());            
        }
        return null;
    }
}
