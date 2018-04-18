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
public class ListSpe2Action extends SpineAction {

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
        String spe1 = request.getParameter("spe1");
        if (GenericValidator.isBlankOrNull(spe1)) {
            spe1 = "0";
        }

        String a = request.getParameter("a");
        if (GenericValidator.isBlankOrNull(a)) {
            a = "0";
        }


        //Spe2
        SpeDAO spe2DAO = new SpeDAO();
        ArrayList spe2List = null;

        ArrayList arrSpe2 = new ArrayList();
        LabelValueBean value;
        value = new LabelValueBean();
        value.setLabel(MCUtil.getBundleString("message.spe.select"));
        value.setValue("???");
        arrSpe2.add(value);
        if (a.equals("0")) {
            try {
                spe2List = spe2DAO.getSpe2s(spe1);
            } catch (Exception ex) {
            }
            for (int i = 0; i < spe2List.size(); i++) {
                SpeBean spe = (SpeBean) spe2List.get(i);
                value = new LabelValueBean();
                value.setLabel(String.valueOf(spe.getSign()) + " - " + String.valueOf(spe.getNote()).replaceAll("null", ""));
                value.setValue(String.valueOf(spe.getSpe2Id()));
                arrSpe2.add(value);
            }
            request.setAttribute(Constants.SPE2_LIST, arrSpe2);
            this.actionForwardResult = "spe2";
        } else {
            try {
                spe2List = spe2DAO.getSpe2as(spe1);
            } catch (Exception ex) {
            }
            for (int i = 0; i < spe2List.size(); i++) {
                SpeBean spe = (SpeBean) spe2List.get(i);
                value = new LabelValueBean();
                value.setLabel(String.valueOf(spe.getSign()) + " - " + String.valueOf(spe.getNote()).replaceAll("null", ""));
                value.setValue(String.valueOf(spe.getSpe2Id()));
                arrSpe2.add(value);
            }
            request.setAttribute(Constants.SPE2_LIST, arrSpe2);
            this.actionForwardResult = "spe2a";
        }

        //spe bean
        SpeBean spebean = new SpeBean();
        request.setAttribute(Constants.SPE, spebean);

        return true;
    }
}
