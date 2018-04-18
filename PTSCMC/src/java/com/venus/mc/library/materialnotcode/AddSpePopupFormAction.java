package com.venus.mc.library.materialnotcode;

/**
 * @author Mai Vinh Loc
 */
import com.venus.mc.core.SpineAction;
import com.venus.mc.bean.SpeBean;
import com.venus.mc.dao.SpeDAO;
import com.venus.mc.util.Constants;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.LabelValueBean;
import com.venus.core.auth.OnlineUser;
import com.venus.core.util.LogUtil;
import com.venus.core.util.NumberUtil;
import com.venus.core.util.StringUtil;
import com.venus.mc.util.MCUtil;
import com.venus.mc.util.PermissionUtil;

public class AddSpePopupFormAction extends SpineAction {

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

        SpeBean bean = new SpeBean();

        session.setAttribute(Constants.SPE, bean);

        String spe0 = MCUtil.getParameter("spe", request);
        session.setAttribute("spe", spe0);


        String[] kind = request.getParameterValues("kind");
        String[] speId = request.getParameterValues("speId");
        String[] save = request.getParameterValues("save");
        String[] sign = request.getParameterValues("sign");
        String[] note = request.getParameterValues("note");
        String s1 = "0";
        String s2 = "0";
        String s3 = "0";
        String s4 = "0";
        String s5 = "0";
        String s6 = "0";
        String[] del = request.getParameterValues("del");
        String[] add = request.getParameterValues("add");
        String[] edit = request.getParameterValues("edit");
        String spes = "";
        String spe = "";
        

        SpeBean bean1 = new SpeBean();
        if (save != null) {
            if (save[0].equals("1")) {
                SpeDAO speDAO = new SpeDAO();

                try {

                    bean1.setSpe1Id(Integer.parseInt(speId[0]));
                    bean1.setSpe2Id(Integer.parseInt(speId[0]));
                    bean1.setSpe3Id(Integer.parseInt(speId[0]));
                    bean1.setSpe4Id(Integer.parseInt(speId[0]));
                    bean1.setSpe5Id(Integer.parseInt(speId[0]));
                    bean1.setSpe6Id(Integer.parseInt(speId[0]));
                    bean1.setSign(sign[0]);
                    bean1.setNote(note[0]);
                    bean1.setSpeId(NumberUtil.parseInt(speId[0], 0));
                    bean1.setSpe(kind[0]);
                    OnlineUser user = MCUtil.getOnlineUser(session);
                    LogUtil.info("Username : " + user.getName() + " - Form : " + mapping.getPath() + " - EDIT");
                    
                    speDAO.insertSpe(bean1);
                    
                } catch (Exception ex) {
                    LogUtil.error("FAILED:Vender:add-" + ex.getMessage());
                    ex.printStackTrace();
                }

            }
        }

        if (del != null) {
            if (del[0].equals("1")) {
                SpeDAO speDAO = new SpeDAO();
                if (session.getAttribute("spe") != null) {
                    spe = session.getAttribute("spe").toString();
                    session.removeAttribute("spe");
                }

                s1 = MCUtil.getParameter("spe1", request);
                s2 = MCUtil.getParameter("spe2", request);
                s3 = MCUtil.getParameter("spe3", request);
                s4 = MCUtil.getParameter("spe4", request);
                s5 = MCUtil.getParameter("spe5", request);
                s6 = MCUtil.getParameter("spe6", request);

                try {
                    if (del != null) {
                        if (spe.equals("1")) {
                            spes = s1;
                        }
                        if (spe.equals("2")) {
                            spes = s2;
                        }
                        if (spe.equals("3")) {
                            spes = s3;
                        }
                        if (spe.equals("4")) {
                            spes = s4;
                        }
                        if (spe.equals("5")) {
                            spes = s5;
                        }
                        if (spe.equals("6")) {
                            spes = s6;
                        }
                        bean1.setSpe(spe);
                        speDAO.deleteSpe(spe, spes);
                    } else {
                        OnlineUser user = MCUtil.getOnlineUser(session);
                        LogUtil.info("Username : " + user.getName() + " - Form : " + mapping.getPath() + " - EDIT");
                        //speDAO.updateSpe(bean1);
                    }
                } catch (Exception ex) {
                    LogUtil.error("FAILED:Vender:add-" + ex.getMessage());
                    ex.printStackTrace();
                }

            }
        }


        //Spe1
        SpeDAO spe1DAO = new SpeDAO();
        ArrayList spe1List = null;
        try {
            spe1List = spe1DAO.getSpe1s();
        } catch (Exception ex) {
        }
        ArrayList arrSpe1 = new ArrayList();
        LabelValueBean value;
        value = new LabelValueBean();
        value.setLabel(MCUtil.getBundleString("message.spe.select"));
        value.setValue("0");
        arrSpe1.add(value);
        for (int i = 0; i < spe1List.size(); i++) {
            SpeBean spe1 = (SpeBean) spe1List.get(i);
            value = new LabelValueBean();
            value.setLabel(String.valueOf(spe1.getSign()) + " - " + String.valueOf(StringUtil.decodeString(spe1.getNote())));
            value.setValue(String.valueOf(spe1.getSpe1Id()));
            arrSpe1.add(value);
        }
        request.setAttribute(Constants.SPE1_LIST, arrSpe1);


        return true;
    }

    @Override
    protected String getFunction() {
        return PermissionUtil.FUNC_VIEW + ";" + PermissionUtil.FUNC_EDIT;
    }

    @Override
    protected int getPermit() {
        return PermissionUtil.PER_LIBRARY_MATERIAL_CATALOG;
    }
}
