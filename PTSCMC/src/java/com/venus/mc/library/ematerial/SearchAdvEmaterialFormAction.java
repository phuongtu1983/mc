package com.venus.mc.library.ematerial;

/**
 * @author Mai Vinh Loc
 */
import com.venus.core.util.StringUtil;
import com.venus.mc.bean.EmaterialBean;
import com.venus.mc.bean.OriginBean;
import com.venus.mc.bean.UnitBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.OriginDAO;
import com.venus.mc.dao.UnitDAO;
import com.venus.mc.util.Constants;
import com.venus.mc.util.MCUtil;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.LabelValueBean;

public class SearchAdvEmaterialFormAction extends SpineAction {

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

        EmaterialBean bean = new EmaterialBean();
        request.setAttribute(Constants.MATERIAL, bean);
        //Unit EN
        UnitDAO unitDAO = new UnitDAO();
        ArrayList unitList = null;
        try {
            unitList = unitDAO.getUnits();
        } catch (Exception ex) {
        }
        ArrayList arrUnit = new ArrayList();
        LabelValueBean value;
        value = new LabelValueBean();
        value.setLabel(MCUtil.getBundleString("message.unit.select"));
        value.setValue("0");
        arrUnit.add(value);
        for (int i = 0; i < unitList.size(); i++) {
            UnitBean unit = (UnitBean) unitList.get(i);
            value = new LabelValueBean();
            value.setLabel(String.valueOf(StringUtil.decodeString(unit.getUnitEn())));
            value.setValue(String.valueOf(unit.getUniId()));
            arrUnit.add(value);
        }
        request.setAttribute(Constants.UNIT_LISTEN, arrUnit);
        //Unit VN
        //LabelValueBean value;
        arrUnit = new ArrayList();
        value = new LabelValueBean();
        value.setLabel(MCUtil.getBundleString("message.unit.select"));
        value.setValue("0");
        arrUnit.add(value);
        for (int i = 0; i < unitList.size(); i++) {
            UnitBean unit = (UnitBean) unitList.get(i);
            value = new LabelValueBean();
            value.setLabel(String.valueOf(StringUtil.decodeString(unit.getUnitVn())));
            value.setValue(String.valueOf(unit.getUniId()));
            arrUnit.add(value);
        }
        request.setAttribute(Constants.UNIT_LIST, arrUnit);

        //Origin EN
        OriginDAO originDAO = new OriginDAO();
        ArrayList originList = null;
        try {
            originList = originDAO.getOrigins();
        } catch (Exception ex) {
        }
        ArrayList arrOrigin = new ArrayList();
        //LabelValueBean value;
        value = new LabelValueBean();
        value.setLabel(MCUtil.getBundleString("message.origin.select"));
        value.setValue("0");
        arrOrigin.add(value);
        for (int i = 0; i < originList.size(); i++) {
            OriginBean origin = (OriginBean) originList.get(i);
            value = new LabelValueBean();
            value.setLabel(String.valueOf(StringUtil.decodeString(origin.getNameEn())));
            value.setValue(String.valueOf(origin.getOriId()));
            arrOrigin.add(value);
        }
        request.setAttribute(Constants.ORIGIN_LISTEN, arrOrigin);
        //Origin VN
        //LabelValueBean value;
        arrOrigin = new ArrayList();
        value = new LabelValueBean();
        value.setLabel(MCUtil.getBundleString("message.origin.select"));
        value.setValue("0");
        arrOrigin.add(value);
        for (int i = 0; i < originList.size(); i++) {
            OriginBean origin = (OriginBean) originList.get(i);
            value = new LabelValueBean();
            value.setLabel(String.valueOf(StringUtil.decodeString(origin.getNameVn())));
            value.setValue(String.valueOf(origin.getOriId()));
            arrOrigin.add(value);
        }
        request.setAttribute(Constants.ORIGIN_LIST, arrOrigin);

        //Kind
        ArrayList arrKind = new ArrayList();
        value = new LabelValueBean();
        value.setLabel(MCUtil.getBundleString("message.kind.select"));
        value.setValue("0");
        arrKind.add(value);
        value = new LabelValueBean();
        value.setLabel(MCUtil.getBundleString("message.kind.TTBDC"));
        value.setValue(EmaterialBean.KIND_CCDC + "");
        arrKind.add(value);
        value = new LabelValueBean();
        value.setLabel(MCUtil.getBundleString("message.kind.VTTB"));
        value.setValue(EmaterialBean.KIND_VTTB + "");
        arrKind.add(value);
        request.setAttribute(Constants.KIND_LIST, arrKind);

        return true;
    }
}
