package com.venus.mc.workReport;

import com.venus.core.sax.RowSAXHandler;
import com.venus.core.util.DateUtil;
import com.venus.core.util.GenericValidator;
import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.RequestBean;
import com.venus.mc.bean.RequestOrganizationBean;
import com.venus.mc.core.SpineReportParser;
import com.venus.mc.dao.RequestDAO;
import com.venus.mc.request.RequestDetailFormBean;
import com.venus.mc.util.MCUtil;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.StringTokenizer;

public class RequestReport
  extends SpineReportParser
{
  private ArrayList arrRequestMaterial;
  private String requestMaterialRow = "requestMaterialRow";
  
  public RequestReport(String xmlTemplate, String wordTemplate, String resultFileName)
  {
    super(xmlTemplate, wordTemplate, resultFileName);
  }
  
  protected void parse(Object object)
    throws Exception
  {
    Integer reqIdObject = (Integer)object;
    int reqId = reqIdObject.intValue();
    RequestBean bean = null;
    RequestDAO requestDAO = new RequestDAO();
    try
    {
      bean = requestDAO.getRequest(reqId);
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
    }
    if (bean == null) {
      return;
    }
    Date date = DateUtil.transformerStringEnDate(bean.getCreatedDate(), "/");
    SimpleDateFormat sdf = null;
    setText("mcrp_number", bean.getRequestNumber());
    sdf = new SimpleDateFormat("dd");
    setText("mcrp_day", sdf.format(date));
    sdf = new SimpleDateFormat("MM");
    setText("mcrp_month", sdf.format(date));
    sdf = new SimpleDateFormat("yyyy");
    setText("mcrp_year", sdf.format(date));
    
    setText("mcrp_org", bean.getOrganizationName());
    setText("mcrp_director", MCUtil.getBundleString("message.director"));
    setText("mcrp_certificate", bean.getCertificateRequire());
    setText("mcrp_whichuse", bean.getWhichUseName());
    setText("mcrp_description", bean.getDescription());
    setText("mcrp_n1", "1");
    setText("mcrp_material_sum", "V?t t?/CCDC theo danh s�ch ?�nh k�m.");
    setText("mcrp_material_unit", "M?c");
    sdf = new SimpleDateFormat("dd/MM/yyyy");
    setText("mcrp_content", sdf.format(date));
    
    String suggest = bean.getApproveSuggest();
    if (!GenericValidator.isBlankOrNull(suggest))
    {
      StringTokenizer token = new StringTokenizer(suggest, ",");
      while (token.hasMoreTokens())
      {
        int v = NumberUtil.parseInt(token.nextToken(), 0);
        switch (v)
        {
        case 1: 
          setCheck("checkbox1", true);
          break;
        case 2: 
          setCheck("checkbox2", true);
          break;
        case 4: 
          setCheck("checkbox3", true);
          break;
        case 8: 
          setCheck("checkbox4", true);
          break;
        case 16: 
          setCheck("checkbox5", true);
        }
      }
    }
    int status = bean.getStatusSuggest();
    switch (status)
    {
    case 1: 
      setCheck("checkbox6", true);
      break;
    case 2: 
      setCheck("checkbox7", true);
      break;
    case 3: 
      setCheck("checkbox8", true);
    }
    setCheckOrg(bean.getOrgHandle(), RequestBean.RQ_XL);
    setCheckOrg(bean.getOrgRefer(), RequestBean.RQ_TK);
    setCheckOrg(bean.getOrgPaid(), RequestBean.RQ_TT);
    
    Hashtable map = new Hashtable();
    RowSAXHandler row = null;
    row = new RowSAXHandler(this.requestMaterialRow, this);
    try
    {
      this.arrRequestMaterial = requestDAO.getRequestDetails(reqId);
    }
    catch (Exception localException1) {}
    if (this.arrRequestMaterial == null) {
      this.arrRequestMaterial = new ArrayList();
    }
    row.setRowCount(this.arrRequestMaterial.size());
    map.put(this.requestMaterialRow, row);
    if (this.arrRequestMaterial.size() > 0)
    {
      RequestDetailFormBean reqBean = (RequestDetailFormBean)this.arrRequestMaterial.get(0);
      setText("mcrp_delivery_date", reqBean.getProvideDate());
    }
    setText("mcrp_material_add_quantity", this.arrRequestMaterial.size() + "");
    setArrTable(map);
  }
  
  private String getRequestMaterialText(int i, String tab)
  {
    String text = "";
    RequestDetailFormBean bean = null;
    if (i < this.arrRequestMaterial.size())
    {
      bean = (RequestDetailFormBean)this.arrRequestMaterial.get(i);
      if (tab.equals("mcrp_n2")) {
        text = i + 1 + "";
      } else if (tab.equals("mcrp_material"))
      {
        if (bean.getIsCancel() == 0) {
          text = bean.getMatName();
        } else {
          text = "";
        }
      }
      else if (tab.equals("mcrp_material_cancel"))
      {
        if (bean.getIsCancel() == 1) {
          text = bean.getMatName();
        } else {
          text = "";
        }
      }
      else if (tab.equals("mcrp_unit")) {
        text = bean.getUnit();
      } else if (tab.equals("mcrp_prequan")) {
        text = NumberUtil.formatMoneyDefault(bean.getPresentQuantity(), "VND");
      } else if (tab.equals("mcrp_addquan")) {
        text = NumberUtil.formatMoneyDefault(bean.getRequestQuantity(), "VND");
      } else if (tab.equals("mcrp_providedate")) {
        text = bean.getProvideDate();
      } else if (tab.equals("mcrp_code")) {
        text = bean.getMatCode();
      }
    }
    return text;
  }
  
  public String getTabText(String rowId, int i, String tab)
  {
    if (rowId.equals(this.requestMaterialRow)) {
      return getRequestMaterialText(i, tab);
    }
    return "";
  }
  
  private void setCheckOrg(String orgs, String kind)
  {
    if (!GenericValidator.isBlankOrNull(orgs))
    {
      StringTokenizer token = new StringTokenizer(orgs, ",");
      RequestDAO reqDAO = new RequestDAO();
      ArrayList arrOrg = null;
      try
      {
        arrOrg = reqDAO.getOrgReqs(kind);
      }
      catch (Exception localException) {}
      if (arrOrg == null) {
        arrOrg = new ArrayList();
      }
      RequestOrganizationBean bean = null;
      while (token.hasMoreTokens())
      {
        int v = NumberUtil.parseInt(token.nextToken(), 0);
        for (int j = 0; j < arrOrg.size(); j++)
        {
          bean = (RequestOrganizationBean)arrOrg.get(j);
          if (v == bean.getOrgId()) {
            setCheck(bean.getMapChar(), true);
          }
        }
      }
    }
  }
}
