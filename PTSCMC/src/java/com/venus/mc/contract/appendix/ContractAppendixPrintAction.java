package com.venus.mc.contract.appendix;

import com.venus.core.util.DateUtil;
import com.venus.core.util.FileUtil;
import com.venus.core.util.LogUtil;
import com.venus.core.util.NumberUtil;
import com.venus.core.util.OutputUtil;
import com.venus.core.util.StringUtil;
import com.venus.mc.bean.ContractBean;
import com.venus.mc.bean.ContractPrintBean;
import com.venus.mc.bean.VendorBean;
import com.venus.mc.contract.ContractDetailFormBean;
import com.venus.mc.core.BaseAction;
import com.venus.mc.core.ExcelExport;
import com.venus.mc.dao.ContractDAO;
import com.venus.mc.dao.RequestDAO;
import com.venus.mc.request.RequestDetailFormBean;
import com.venus.mc.util.MCUtil;
import fr.opensagres.xdocreport.core.XDocReportException;
import fr.opensagres.xdocreport.document.IXDocReport;
import fr.opensagres.xdocreport.document.registry.XDocReportRegistry;
import fr.opensagres.xdocreport.template.IContext;
import fr.opensagres.xdocreport.template.TemplateEngineKind;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ContractAppendixPrintAction
  extends BaseAction
{
  private ArrayList arrFiles = null;
  
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    this.arrFiles = new ArrayList();
    String type = "zip";
    String fileName = "Phu luc hop dong";
    String fileName1 = "Phu luc hop dong (Tieng Anh)";
    String no = "message.appendix.no";
    String no1 = "message.appendix.noEnglish";
    String userName = MCUtil.getMemberName(request.getSession());
    try
    {
      ContractDAO conDAO = new ContractDAO();
      int id = Integer.parseInt(request.getParameter("conId"));
      int kind = 0;
      try
      {
        kind = conDAO.getVendorKind(id);
      }
      catch (Exception ex)
      {
        ex.printStackTrace();
      }
      if (kind == VendorBean.KIND_NATIONAL)
      {
        print(request, response, fileName, type, id, userName);
        printContractAppendixMaterial(request, response, id, fileName, no);
      }
      else
      {
        print(request, response, fileName1, type, id, userName);
        printContractAppendixMaterial(request, response, id, fileName1, no1);
      }
    }
    catch (Exception localException1) {}
    if (type.equals("zip"))
    {
      String outputFileName = fileName + "-" + userName + ".zip";
      outputFileName = request.getSession().getServletContext().getRealPath("/templates/temp/" + outputFileName);
      FileUtil.zipFile(outputFileName, this.arrFiles);
      OutputUtil.sendZipFileToOutput(response, outputFileName, fileName + ".zip");
    }
    return null;
  }
  
  private void print(HttpServletRequest request, HttpServletResponse response, String fileName, String type, int id, String userName)
  {
    try
    {
      if (type.equals("docx")) {
        type = "";
      } else {
        type = ".docx";
      }
      String wordDir = request.getSession().getServletContext().getRealPath("/templates/temp/");
      this.arrFiles.add(wordDir + "/" + fileName + "-" + userName + type);
      String wordTemplate = request.getSession().getServletContext().getRealPath("/templates/" + fileName + ".docx");
      
      InputStream in = new FileInputStream(wordTemplate);
      IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Freemarker);
      
      IContext context = report.createContext();
      
      List<ContractPrintBean> docs = new ArrayList();
      
      ContractBean bean = null;
      ContractBean bean2 = null;
      ContractBean parentContract = null;
      ContractDAO contractDAO = new ContractDAO();
      try
      {
        bean = contractDAO.getContractAppendix(id);
        bean2 = contractDAO.getOrgNameEmpCreatedContract(id);
        parentContract = contractDAO.getContract(bean.getParentId());
      }
      catch (Exception ex)
      {
        ex.printStackTrace();
      }
      if (bean == null) {
        bean = new ContractBean();
      }
      int kind = 0;
      
      Date date = DateUtil.transformerStringEnDate(bean.getSignDate(), "/");
      Date date2 = DateUtil.transformerStringEnDate(bean.getEffectedDate(), "/");
      String year = DateUtil.formatDate(date, "yyyy");
      String placeDelivery = "";
      String orgNameUpper = bean2.getOrgName().toUpperCase();
      String orgName = bean2.getOrgName();
      String abbreviate = bean2.getAbbreviate();
      String contractNumber = bean.getContractNumber();
      String appendixNumber = "";
      try
      {
        appendixNumber = bean.getAppendixNumber().split(" ")[1];
      }
      catch (Exception ex)
      {
        appendixNumber = " " + bean.getAppendixNumber();
      }
      String packName = bean.getPackName();
      String packNameUpcase = "";
      if (bean.getPackName() == null) {
        packName = bean.getNote();
      }
      packNameUpcase = packName.toUpperCase();
      String vendorName = bean.getVendorName();
      String signDate = "ng�y " + DateUtil.formatDate(date, "dd") + " th�ng " + DateUtil.formatDate(date, "MM") + " n?m " + DateUtil.formatDate(date, "yyyy");
      
      String delivery2 = DateUtil.formatDate(date, "dd") + "/" + DateUtil.formatDate(date, "MM") + "/" + DateUtil.formatDate(date, "yyyy");
      
      String effectDate = DateUtil.formatDate(date2, "dd") + "/" + DateUtil.formatDate(date2, "MM") + "/" + DateUtil.formatDate(date2, "yyyy");
      String effectedDate = "ng�y " + DateUtil.formatDate(date2, "dd") + " th�ng " + DateUtil.formatDate(date2, "MM") + " n?m " + DateUtil.formatDate(date2, "yyyy");
      String vendorNameUpcase = bean.getVendorName().toUpperCase();
      String address = bean.getAddress();
      String phone = bean.getPhone();
      String fax = bean.getFax();
      String license = bean.getLicense();
      String field = bean.getField();
      String presenter = bean.getPresenter() + " - Ch?c v?: " + bean.getPosPresenter();
      String delivery = bean.getDelivery();
      String certificate = bean.getCertificate();
      String currency = bean.getCurrency();
      double total = bean.getTotal() + bean.getOtherFee() + bean.getTransport();
      double sumVAT = bean.getSumVAT();
      double totalNotVAT = bean.getTotalNotVAT();
      String textTotal = NumberUtil.textMoney(Double.valueOf(bean.getTotal() + bean.getOtherFee() + bean.getTransport()), bean.getCurrency());
      String projectNames = "";
      
      ArrayList arrDAO = null;
      ArrayList reqDetails = null;
      ContractDetailFormBean beanDetails = null;
      RequestDetailFormBean reqBean = null;
      RequestDAO requestDAO = new RequestDAO();
      String reqDetIds = "0";
      if (reqDetails == null) {
        reqDetails = new ArrayList();
      }
      try
      {
        arrDAO = contractDAO.getContractDetails(id);
      }
      catch (Exception localException1) {}
      if (arrDAO == null) {
        arrDAO = new ArrayList();
      }
      for (int i = 0; i < arrDAO.size(); i++)
      {
        beanDetails = (ContractDetailFormBean)arrDAO.get(i);
        reqDetIds = reqDetIds + "," + beanDetails.getReqDetailId();
      }
      try
      {
        reqDetails = requestDAO.getRequestDetailsByDetIds(reqDetIds);
      }
      catch (Exception localException2) {}
      for (int i = 0; i < arrDAO.size(); i++)
      {
        reqBean = (RequestDetailFormBean)reqDetails.get(i);
        projectNames = projectNames.replace(reqBean.getReqProject() + ", ", "");
        projectNames = projectNames + reqBean.getReqProject() + ", ";
      }
      if (!StringUtil.isBlankOrNull(projectNames)) {
        try
        {
          projectNames = projectNames.substring(0, projectNames.length() - 2);
        }
        catch (Exception localException3) {}
      }
      String otherFrees = "";
      docs.add(new ContractPrintBean(kind, fax, contractNumber, effectedDate, effectedDate, effectDate, total, total, total, total, currency, currency, delivery, delivery, presenter, phone, fileName, vendorName, vendorName, address, phone, fax, presenter, presenter, field, license, totalNotVAT, sumVAT, totalNotVAT, totalNotVAT, sumVAT, sumVAT, total, license, appendixNumber, id, signDate, phone, projectNames, packName, delivery, appendixNumber, certificate, delivery, appendixNumber, signDate, vendorName, field, license, contractNumber, vendorName, field, license, contractNumber, delivery, delivery2, appendixNumber, fax, sumVAT, total, contractNumber, packName, id, fax, phone, year, vendorNameUpcase, textTotal, contractNumber, effectDate, effectDate, orgNameUpper, orgName, abbreviate, orgName, effectDate, packNameUpcase, projectNames, otherFrees));
      
      context.put("ptscmc", docs);
      
      OutputStream out = new FileOutputStream(new File(wordDir + "/" + fileName + "-" + userName + type));
      
      report.process(context, out);
      try
      {
        if (!type.equals(".docx")) {
          OutputUtil.sendWordFileToOutput(response, wordDir + "/", fileName + "-" + userName);
        }
      }
      catch (Exception localException4) {}
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    catch (XDocReportException e)
    {
      e.printStackTrace();
    }
  }
  
  private void printContractAppendixMaterial(HttpServletRequest request, HttpServletResponse response, int conId, String fileName, String no)
  {
    int kindContract = 0;
    String userName = MCUtil.getMemberName(request.getSession());
    String wordDir = request.getSession().getServletContext().getRealPath("/templates/temp/");
    this.arrFiles.add(wordDir + "/" + fileName + "-" + userName + ".xls");
    
    ArrayList list = null;
    ArrayList parentList = null;
    ContractBean bean = null;
    ContractDAO contractDAO = new ContractDAO();
    try
    {
      bean = contractDAO.getContractAppendix(conId);
      kindContract = bean.getKind();
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
    }
    if (bean == null) {
      return;
    }
    String templateFileName = request.getSession().getServletContext().getRealPath("/templates/" + fileName + ".xls");
    try
    {
      if (kindContract == 8) {
        list = contractDAO.getContractAdjustmentPrint(bean.getConId());
      } else {
        list = contractDAO.getContractAppendixPrint(bean.getConId());
      }
      parentList = contractDAO.getContractDetailsForPrint(bean.getParentId());
    }
    catch (Exception localException1) {}
    if (list == null) {
      list = new ArrayList();
    }
    if (parentList == null) {
      parentList = new ArrayList();
    }
    ContractDetailFormBean contractDetail = null;
    ContractDetailFormBean parentContractDetail = null;
    ArrayList contractDetailList = new ArrayList();
    ArrayList parentContractDetailList = new ArrayList();
    String No = "";
    Double total = Double.valueOf(0.0D);
    for (int j = 0; j < parentList.size(); j++)
    {
      parentContractDetail = (ContractDetailFormBean)parentList.get(j);
      for (int i = 0; i < list.size(); i++)
      {
        contractDetail = (ContractDetailFormBean)list.get(i);
        if (kindContract == 8)
        {
          if (contractDetail.getMatIdTemp() == parentContractDetail.getMatId())
          {
            contractDetail.setStt(i + 1);
            if (No.compareTo(MCUtil.getBundleString(no) + " " + (j + 1)) == 0) {
              No = "";
            } else {
              No = MCUtil.getBundleString(no) + " " + (j + 1);
            }
            contractDetail.setNo(No);
            contractDetail.setTotal(contractDetail.getQuantity() * contractDetail.getPrice());
            total = Double.valueOf(total.doubleValue() + contractDetail.getQuantity() * contractDetail.getPrice());
            contractDetailList.add(contractDetail);
          }
        }
        else if (contractDetail.getMatId() == parentContractDetail.getMatId())
        {
          contractDetail.setStt(i + 1);
          if (No.compareTo(MCUtil.getBundleString(no) + " " + (j + 1)) == 0) {
            No = "";
          } else {
            No = MCUtil.getBundleString(no) + " " + (j + 1);
          }
          contractDetail.setNo(No);
          contractDetail.setTotal(contractDetail.getQuantity() * contractDetail.getPrice());
          total = Double.valueOf(total.doubleValue() + contractDetail.getQuantity() * contractDetail.getPrice());
          contractDetailList.add(contractDetail);
        }
      }
    }
    try
    {
      Map beans = new HashMap();
      String number = bean.getAppendixNumber();
      int ind = number.indexOf(" H?");
      if (ind > -1) {
        number = number.substring(0, ind);
      }
      total = Double.valueOf(total.doubleValue() + (bean.getTransport() + bean.getOtherFee()));
      beans.put("mcrp_vendorName", bean.getVendorName());
      beans.put("mcrp_appendixNumber", bean.getAppendixNumber());
      beans.put("mcrp_contractNumber", bean.getContractNumber());
      beans.put("mcrp_number", number.replaceFirst("PL", ""));
      beans.put("mcrp_currency", bean.getCurrency());
      beans.put("mcrp_total_not_vat", Double.valueOf(bean.getTotalNotVAT()));
      beans.put("mcrp_vat", Double.valueOf(bean.getSumVAT()));
      beans.put("mcrp_total", Double.valueOf(bean.getTotal()));
      beans.put("mcrp_total_text", NumberUtil.textMoney(Double.valueOf(bean.getTotal()), bean.getCurrency()));
      beans.put("mcrp_contract", bean.getContractNumber());
      beans.put("material", contractDetailList);
      beans.put("materialEnglish", parentContractDetailList);
      beans.put("mcrp_others", Double.valueOf(bean.getTransport() + bean.getOtherFee()));
      beans.put("mcrp_sum", total);
      beans.put("mcrp_delivery", bean.getDelivery());
      beans.put("mcrp_certificate", bean.getCertificate());
      int rowCount = 0;
      if (contractDetailList.size() > 0) {
        rowCount = contractDetailList.size() - 1;
      }
      beans.put("mcrp_n2", Integer.valueOf(rowCount + 2));
      beans.put("mcrp_n3", Integer.valueOf(rowCount + 3));
      beans.put("mcrp_n4", Integer.valueOf(rowCount + 4));
      ExcelExport exporter = new ExcelExport();
      exporter.setBeans(beans);
      String tempFile = wordDir + "/" + fileName + "-" + userName + "_temp.xls";
      FileOutputStream fOS = new FileOutputStream(new File(tempFile));
      long milis = System.currentTimeMillis();
      String exportFile = (String)this.arrFiles.get(this.arrFiles.size() - 1);
      exporter.export(request, response, templateFileName, wordDir + "/" + "PhuLucHopDongTemp.xls", fOS);
      
      correctExcelColumn(tempFile, exportFile, rowCount);
      milis = System.currentTimeMillis() - milis;
      System.out.println("PhuLucHopDongTemp.xls : time : " + (int)(milis / 1000L % 60L) + " phut " + (int)(milis / 1000L / 60L) + " giay");
    }
    catch (Exception ex)
    {
      LogUtil.error("FAILED:RequestReportPrint:print-" + ex.getMessage());
      ex.printStackTrace();
    }
  }
  
  private void correctExcelColumn(String templateFileName, String tempFileName, int rowSize)
    throws Exception
  {
    POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(templateFileName));
    HSSFWorkbook wb = new HSSFWorkbook(fs);
    
    FileOutputStream fileOut = new FileOutputStream(tempFileName);
    wb.write(fileOut);
    fileOut.close();
  }
}
