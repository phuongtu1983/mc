package com.venus.mc.request;

import com.venus.core.util.GenericValidator;
import com.venus.core.util.LogUtil;
import com.venus.core.util.StringUtil;
import com.venus.mc.bean.ContractBean;
import com.venus.mc.bean.SearchFormBean;
import com.venus.mc.core.ExcelExport;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.MaterialDAO;
import com.venus.mc.dao.OrganizationDAO;
import com.venus.mc.util.MCUtil;
import com.venus.mc.util.PermissionUtil;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class ExportMaterialInContractAction
  extends SpineAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    SearchFormBean formBean = (SearchFormBean)form;
    String strFieldvalue = formBean.getSearchvalue();
    ArrayList materialList = null;
    String kind = request.getParameter("kind");
    int fieldid = Integer.parseInt(request.getParameter("searchid"));
    String fieldvalue = request.getParameter("searchvalue");
    if (GenericValidator.isBlankOrNull(kind)) {
      kind = "5";
    }
    String templateFileName = request.getSession().getServletContext().getRealPath("/templates/danh_sach_VTTB_de_xuat.xls");
    try
    {
      MaterialDAO materialDAO = new MaterialDAO();
      materialDAO.setRequestEmp(MCUtil.getMemberID(request.getSession()));
      if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_LIST, PermissionUtil.PER_PURCHASING_REQUEST_MANAGER))
      {
        OrganizationDAO orgDAO = new OrganizationDAO();
        int orgId = MCUtil.getOrganizationID(request.getSession());
        String orgs = orgDAO.getnestedChildOfOrg(orgId + "");
        orgs = orgs + "," + orgId;
        materialDAO.setRequestOrg(orgs);
      }
      if (kind.equals("1"))
      {
        materialList = materialDAO.getMaterialInContractExpire(ContractBean.KIND_PRINCIPLE, fieldid, StringUtil.encodeHTML(fieldvalue));
      }
      else if (kind.equals("2"))
      {
        materialList = materialDAO.getMaterialInContract(ContractBean.KIND_CONTRACT + "", fieldid, StringUtil.encodeHTML(fieldvalue), true);
      }
      else if (kind.equals("3"))
      {
        materialList = materialDAO.getMaterialInContract(ContractBean.KIND_ORDER + "", fieldid, strFieldvalue, true);
      }
      else if (kind.equals("4"))
      {
        materialList = materialDAO.getMaterialInContract(ContractBean.KIND_PRINCIPLE + "", fieldid, strFieldvalue, true);
      }
      else if (kind.equals("8"))
      {
        materialList = materialDAO.getMaterialInAdjusmentContract(ContractBean.KIND_PRINCIPLE + "", fieldid, strFieldvalue, true);
      }
      else
      {
        templateFileName = request.getSession().getServletContext().getRealPath("/templates/danh_sach_VTTB_de_xuat_not_in_contract.xls");
        String reqDetailIds = "";
        materialList = materialDAO.getMaterialInContract(ContractBean.KIND_PRINCIPLE + "," + ContractBean.KIND_CONTRACT + "," + ContractBean.KIND_ORDER, fieldid, strFieldvalue, false);
        reqDetailIds = reqDetailIds + getReqDetailIds(materialList);
        materialList = materialDAO.getMaterialNotInContract(reqDetailIds, fieldid, strFieldvalue);
        MaterialInContractFormBean bean = null;
        String name = "";
        for (int i = 0; i < materialList.size(); i++)
        {
          bean = (MaterialInContractFormBean)materialList.get(i);
          name = bean.getAssignedEmployeeName();
          if (!GenericValidator.isBlankOrNull(name)) {
            bean.setAssignedEmployeeName(name.substring(name.lastIndexOf(" ")));
          }
        }
      }
      Map beans = new HashMap();
      beans.put("hopdong", materialList);
      ExcelExport exporter = new ExcelExport();
      exporter.setBeans(beans);
      long milis = System.currentTimeMillis();
      exporter.export(request, response, templateFileName, "Danh_Sach_VTTB_De_Xuat.xls");
      milis = System.currentTimeMillis() - milis;
      System.out.println("contract.xls : time : " + (int)(milis / 1000L % 60L) + " phut " + (int)(milis / 1000L / 60L) + " mili giay");
    }
    catch (Exception ex)
    {
      LogUtil.error("FAILED:ExportMaterialNotContract:search-" + ex.getMessage());
      ex.printStackTrace();
    }
    return true;
  }
  
  private String getReqDetailIds(ArrayList materialList)
  {
    MaterialInContractFormBean bean = null;
    String result = "0";
    for (int i = 0; i < materialList.size(); i++)
    {
      bean = (MaterialInContractFormBean)materialList.get(i);
      result = result + "," + bean.getReqDetailId();
    }
    return result;
  }
  
  protected boolean isReturnStream()
  {
    return true;
  }
}
