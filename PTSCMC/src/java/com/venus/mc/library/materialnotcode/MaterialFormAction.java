package com.venus.mc.library.materialnotcode;

/**
 * @author Mai Vinh Loc
 */
import com.venus.core.util.NumberUtil;
import com.venus.core.util.StringUtil;
import com.venus.mc.bean.EmaterialBean;
import com.venus.mc.bean.MaterialBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.MaterialDAO;
import com.venus.mc.dao.SpeDAO;
import com.venus.mc.bean.SpeBean;
import com.venus.mc.dao.UnitDAO;
import com.venus.mc.bean.UnitBean;
import com.venus.mc.dao.OriginDAO;
import com.venus.mc.bean.OriginBean;
import com.venus.mc.dao.GroupMaterialDAO;
import com.venus.mc.bean.GroupMaterialBean;
import com.venus.mc.bean.RequestBean;
import com.venus.mc.util.Constants;
import com.venus.mc.util.MCUtil;
import com.venus.mc.util.PermissionUtil;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.LabelValueBean;

public class MaterialFormAction extends SpineAction {

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
        int detId = NumberUtil.parseInt(request.getParameter("detId"), 0);
        int tenId = NumberUtil.parseInt(request.getParameter("tenId"), 0);
        int reqId = NumberUtil.parseInt(request.getParameter("reqId"), 0);
        MaterialBean bean = new MaterialBean();
        String matId = request.getParameter("matId");
        bean.setCode("???.???.???.???.???");
        if (!GenericValidator.isBlankOrNull(matId)) {
            MaterialDAO materialDAO = new MaterialDAO();
            try {
                bean = materialDAO.getMaterialNotCode(matId);
            } catch (Exception ex) {
            }
        }
        request.setAttribute(Constants.MATERIAL, bean);
        RequestBean reqBean = new RequestBean(reqId);
        request.setAttribute(Constants.REQUEST, reqBean);
        request.setAttribute("detId", detId);
        request.setAttribute("tenId", tenId);

        //spe bean
        SpeBean spebean = new SpeBean();
        request.setAttribute(Constants.SPE, spebean);

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


        //Unit EN
        UnitDAO unitDAO = new UnitDAO();
        ArrayList unitList = null;
        try {
            unitList = unitDAO.getUnits();
        } catch (Exception ex) {
        }
        ArrayList arrUnit = new ArrayList();
        //LabelValueBean value;
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

        //GroId
        GroupMaterialDAO groDAO = new GroupMaterialDAO();
        ArrayList groList = null;
        try {
            groList = groDAO.getGroupMaterials();
        } catch (Exception ex) {
        }
        ArrayList arrGro = new ArrayList();
        //LabelValueBean value;
        value = new LabelValueBean();
        value.setLabel(MCUtil.getBundleString("message.groupmaterial.select"));
        value.setValue("0");
        arrGro.add(value);
        for (int i = 0; i < groList.size(); i++) {
            GroupMaterialBean gro = (GroupMaterialBean) groList.get(i);
            value = new LabelValueBean();
            if (String.valueOf(StringUtil.decodeString(gro.getName())).length() > 90) {
                value.setLabel(String.valueOf(StringUtil.decodeString(gro.getName())).substring(0, 90) + "...");
            } else {
                value.setLabel(String.valueOf(StringUtil.decodeString(gro.getName())));
            }
            value.setValue(String.valueOf(gro.getGroId()));
            arrGro.add(value);
        }
        request.setAttribute(Constants.GROUP_MATERIAL_LIST, arrGro);

        //Kind
        ArrayList arrKind = new ArrayList();
        value = new LabelValueBean();
        value.setLabel(MCUtil.getBundleString("message.kind.select"));
        value.setValue("0");
        arrKind.add(value);
        value = new LabelValueBean();
        value.setLabel(MCUtil.getBundleString("message.kind.CCDC"));
        value.setValue(EmaterialBean.KIND_CCDC + "");
        arrKind.add(value);
        value = new LabelValueBean();
        value.setLabel(MCUtil.getBundleString("message.kind.VTTB"));
        value.setValue(EmaterialBean.KIND_VTTB + "");
        arrKind.add(value);
        value = new LabelValueBean();
        value.setLabel(MCUtil.getBundleString("message.kind.TSCĐ"));
        value.setValue(EmaterialBean.KIND_TSCD + "");
        arrKind.add(value);
        request.setAttribute(Constants.KIND_LIST, arrKind);

        //Type
        ArrayList arrType = new ArrayList();
        value = new LabelValueBean();
        value.setLabel(MCUtil.getBundleString("message.type.select"));
        value.setValue("0");
        arrType.add(value);
        value = new LabelValueBean();
        value.setLabel(MCUtil.getBundleString("message.type.STRUCTURE"));
        value.setValue(EmaterialBean.TYPE1 + "");
        arrType.add(value);
        value = new LabelValueBean();
        value.setLabel(MCUtil.getBundleString("message.type.PIPING"));
        value.setValue(EmaterialBean.TYPE2 + "");
        arrType.add(value);
        value = new LabelValueBean();
        value.setLabel(MCUtil.getBundleString("message.type.MECHANICAL"));
        value.setValue(EmaterialBean.TYPE3 + "");
        arrType.add(value);
        value = new LabelValueBean();
        value.setLabel(MCUtil.getBundleString("message.type.ELECTRICAL&INSTRUMENT"));
        value.setValue(EmaterialBean.TYPE4 + "");
        arrType.add(value);
        request.setAttribute(Constants.TYPE_LIST, arrType);
        return true;
    }

    @Override
    protected String getFunction() {
        return PermissionUtil.FUNC_VIEW + ";" + PermissionUtil.FUNC_EDIT;
    }

    @Override
    protected int getPermit() {
        return PermissionUtil.PER_LIBRARY_MATERIAL_COMPANY;
    }
}
