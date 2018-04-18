package com.venus.mc.material;

import com.venus.core.util.NumberUtil;
import com.venus.core.util.StringUtil;
import com.venus.mc.bean.EmaterialBean;
import com.venus.mc.bean.GroupMaterialBean;
import com.venus.mc.bean.MaterialBean;
import com.venus.mc.bean.OriginBean;
import com.venus.mc.bean.SpeBean;
import com.venus.mc.bean.UnitBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.GroupMaterialDAO;
import com.venus.mc.dao.MaterialDAO;
import com.venus.mc.dao.OriginDAO;
import com.venus.mc.dao.SpeDAO;
import com.venus.mc.dao.UnitDAO;
import com.venus.mc.util.MCUtil;
import com.venus.mc.util.PermissionUtil;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.LabelValueBean;

public class MaterialFormAction
  extends SpineAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    int detId = NumberUtil.parseInt(request.getParameter("detId"), 0);
    int detIdTp = NumberUtil.parseInt(request.getParameter("detIdTp"), 0);
    int tenId = NumberUtil.parseInt(request.getParameter("tenId"), 0);
    int osdId = NumberUtil.parseInt(request.getParameter("osdId"), 0);
    int mrirId = NumberUtil.parseInt(request.getParameter("mrirId"), 0);
    String detIds = request.getParameter("detIds");
    String matIds = request.getParameter("matIds");
    String reqIds = request.getParameter("reqIds");
    MaterialBean bean = new MaterialBean();
    String matId = request.getParameter("matId");
    if (!GenericValidator.isBlankOrNull(matId))
    {
      MaterialDAO materialDAO = new MaterialDAO();
      try
      {
        bean = materialDAO.getMaterial(matId);
      }
      catch (Exception localException) {}
    }
    if (bean == null) {
      bean = new MaterialBean();
    }
    request.setAttribute("material", bean);
    request.setAttribute("detId", Integer.valueOf(detId));
    request.setAttribute("detIdTp", Integer.valueOf(detIdTp));
    request.setAttribute("tenId", Integer.valueOf(tenId));
    request.setAttribute("detIds", detIds);
    request.setAttribute("matIds", matIds);
    request.setAttribute("reqIds", reqIds);
    request.setAttribute("osdId", Integer.valueOf(osdId));
    request.setAttribute("mrirId", Integer.valueOf(mrirId));
    
    SpeBean spebean = new SpeBean();
    request.setAttribute("speForm", spebean);
    
    SpeDAO spe1DAO = new SpeDAO();
    ArrayList spe1List = null;
    try
    {
      spe1List = spe1DAO.getSpe1s();
    }
    catch (Exception localException1) {}
    ArrayList arrSpe1 = new ArrayList();
    
    LabelValueBean value = new LabelValueBean();
    value.setLabel(MCUtil.getBundleString("message.spe.select"));
    value.setValue("0");
    arrSpe1.add(value);
    for (int i = 0; i < spe1List.size(); i++)
    {
      SpeBean spe1 = (SpeBean)spe1List.get(i);
      value = new LabelValueBean();
      value.setLabel(String.valueOf(spe1.getSign()) + " - " + String.valueOf(StringUtil.decodeString(spe1.getNote())));
      value.setValue(String.valueOf(spe1.getSpe1Id()));
      arrSpe1.add(value);
    }
    request.setAttribute("spe1List", arrSpe1);
    
    UnitDAO unitDAO = new UnitDAO();
    ArrayList unitList = null;
    try
    {
      unitList = unitDAO.getUnits();
    }
    catch (Exception localException2) {}
    ArrayList arrUnit = new ArrayList();
    
    value = new LabelValueBean();
    value.setLabel(MCUtil.getBundleString("message.unit.select"));
    value.setValue("0");
    arrUnit.add(value);
    for (int i = 0; i < unitList.size(); i++)
    {
      UnitBean unit = (UnitBean)unitList.get(i);
      value = new LabelValueBean();
      value.setLabel(String.valueOf(StringUtil.decodeString(unit.getUnitEn())));
      value.setValue(String.valueOf(unit.getUniId()));
      arrUnit.add(value);
    }
    request.setAttribute("unitListEN", arrUnit);
    
    arrUnit = new ArrayList();
    value = new LabelValueBean();
    value.setLabel(MCUtil.getBundleString("message.unit.select"));
    value.setValue("0");
    arrUnit.add(value);
    for (int i = 0; i < unitList.size(); i++)
    {
      UnitBean unit = (UnitBean)unitList.get(i);
      value = new LabelValueBean();
      value.setLabel(String.valueOf(StringUtil.decodeString(unit.getUnitVn())));
      value.setValue(String.valueOf(unit.getUniId()));
      arrUnit.add(value);
    }
    request.setAttribute("unitList", arrUnit);
    
    OriginDAO originDAO = new OriginDAO();
    ArrayList originList = null;
    try
    {
      originList = originDAO.getOrigins();
    }
    catch (Exception localException3) {}
    ArrayList arrOrigin = new ArrayList();
    
    value = new LabelValueBean();
    value.setLabel(MCUtil.getBundleString("message.origin.select"));
    value.setValue("0");
    arrOrigin.add(value);
    for (int i = 0; i < originList.size(); i++)
    {
      OriginBean origin = (OriginBean)originList.get(i);
      value = new LabelValueBean();
      value.setLabel(String.valueOf(StringUtil.decodeString(origin.getNameEn())));
      value.setValue(String.valueOf(origin.getOriId()));
      arrOrigin.add(value);
    }
    request.setAttribute("originListEN", arrOrigin);
    
    arrOrigin = new ArrayList();
    value = new LabelValueBean();
    value.setLabel(MCUtil.getBundleString("message.origin.select"));
    value.setValue("0");
    arrOrigin.add(value);
    for (int i = 0; i < originList.size(); i++)
    {
      OriginBean origin = (OriginBean)originList.get(i);
      value = new LabelValueBean();
      value.setLabel(String.valueOf(StringUtil.decodeString(origin.getNameVn())));
      value.setValue(String.valueOf(origin.getOriId()));
      arrOrigin.add(value);
    }
    request.setAttribute("originList", arrOrigin);
    
    GroupMaterialDAO groDAO = new GroupMaterialDAO();
    ArrayList groList = null;
    try
    {
      groList = groDAO.getGroupMaterials();
    }
    catch (Exception localException4) {}
    ArrayList arrGro = new ArrayList();
    
    value = new LabelValueBean();
    value.setLabel(MCUtil.getBundleString("message.groupmaterial.select"));
    value.setValue("0");
    arrGro.add(value);
    for (int i = 0; i < groList.size(); i++)
    {
      GroupMaterialBean gro = (GroupMaterialBean)groList.get(i);
      value = new LabelValueBean();
      if (String.valueOf(StringUtil.decodeString(gro.getName())).length() > 90) {
        value.setLabel(String.valueOf(StringUtil.decodeString(gro.getName())).substring(0, 90) + "...");
      } else {
        value.setLabel(String.valueOf(StringUtil.decodeString(gro.getName())));
      }
      value.setValue(String.valueOf(gro.getGroId()));
      arrGro.add(value);
    }
    request.setAttribute("groupMaterialList", arrGro);
    
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
    value.setLabel(MCUtil.getBundleString("message.kind.TSC?"));
    value.setValue(EmaterialBean.KIND_TSCD + "");
    arrKind.add(value);
    request.setAttribute("kindList", arrKind);
    
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
    request.setAttribute("typeList", arrType);
    return true;
  }
  
  protected String getFunction()
  {
    return PermissionUtil.FUNC_VIEW + ";" + PermissionUtil.FUNC_EDIT;
  }
  
  protected int getPermit()
  {
    return PermissionUtil.PER_LIBRARY_MATERIAL_COMPANY;
  }
}
