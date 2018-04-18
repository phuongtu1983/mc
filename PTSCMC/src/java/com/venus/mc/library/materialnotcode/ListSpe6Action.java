package com.venus.mc.library.materialnotcode;

import com.venus.core.util.GenericValidator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import com.venus.mc.util.Constants;

import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.SpeDAO;
import com.venus.mc.bean.SpeBean;
import com.venus.mc.util.MCUtil;
import java.util.ArrayList;
import org.apache.struts.util.LabelValueBean;

/**
 *
 * @author Mai Vinh Loc
 * @version
 */
public class ListSpe6Action extends SpineAction {

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
        String spe5 = request.getParameter("spe5");
        if (GenericValidator.isBlankOrNull(spe5)) {
            return true;
        }

        String a = request.getParameter("a");
        if (GenericValidator.isBlankOrNull(a)) {
            a="0";
        }
       
        //Spe6
        SpeDAO spe6DAO = new SpeDAO();
        ArrayList spe6List=null;
        
        ArrayList arrSpe6 = new ArrayList();
        LabelValueBean value;
        value = new LabelValueBean();
        value.setLabel(MCUtil.getBundleString("message.spe.select"));
        value.setValue("???");
        arrSpe6.add(value);
        if (a.equals("0")) {
            try {
                spe6List =spe6DAO.getSpe6s(spe5);
            }   catch (Exception ex) {

            }
            for (int i=0;i<spe6List.size();i++) {
            SpeBean spe = (SpeBean)spe6List.get(i);
            value = new LabelValueBean();
            value.setLabel(String.valueOf(spe.getSign()) + " - " + String.valueOf(spe.getNote()).replaceAll("null", ""));
            value.setValue(String.valueOf(spe.getSpe6Id()));
            arrSpe6.add(value);
        }
        request.setAttribute(Constants.SPE6_LIST,arrSpe6);
        this.actionForwardResult = "spe6";
        }
        else {
            try {
                spe6List =spe6DAO.getSpe6s(spe5);
            }   catch (Exception ex) {

            }
            for (int i=0;i<spe6List.size();i++) {
            SpeBean spe = (SpeBean)spe6List.get(i);
            value = new LabelValueBean();
            value.setLabel(String.valueOf(spe.getNote()).replaceAll("null", ""));
            value.setValue(String.valueOf(spe.getSign()));
            arrSpe6.add(value);
        }
            request.setAttribute(Constants.SPE6_LIST,arrSpe6);
            this.actionForwardResult = "spe6a";
        }
        //spe bean
        SpeBean spebean = new SpeBean();
        request.setAttribute(Constants.SPE, spebean);
        return true;
    }
}
