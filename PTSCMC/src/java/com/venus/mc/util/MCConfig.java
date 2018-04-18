package com.venus.mc.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
import javax.servlet.ServletContext;

public class MCConfig
{
  private static String PATH_TASK_UPLOAD = "task/upload/";
  private static String PATH_REPORT_ARCHIVES = "task/archives/";
  private static String PATH_QUOTE_UPLOAD = "process/quote/upload/";
  private static boolean showLastLogon = false;
  private static String repoArchiveFolder = "/";
  private static String uploadTaskFolder = "/";
  private static String uploadQuoteFolder = "/";
  private static String mailServer = "omailmc.ptsc.com.vn";
  private static String domainName = "ptsc.com.vn";
  private static String senderMail = "vnpsoftware@ptsc.com.vn";
  private static String birthdayMail = "vnpsoftware@ptsc.com.vn";
  private static int birthdayBefore = 7;
  
  public static void setShowLastLogon(boolean enable)
  {
    showLastLogon = enable;
  }
  
  public static boolean isShowLastLogon()
  {
    return showLastLogon;
  }
  
  public static String getUploadTaskFolder()
  {
    return uploadTaskFolder;
  }
  
  public static String getUploadQuoteFolder()
  {
    return uploadQuoteFolder;
  }
  
  public static String getTMReportArchiveFolder()
  {
    return repoArchiveFolder;
  }
  
  public static String getMailServer()
  {
    return mailServer;
  }
  
  public static void setMailServer(String server)
  {
    mailServer = server;
  }
  
  public static String getDomainName()
  {
    return domainName;
  }
  
  public static String getSenderMail()
  {
    return senderMail;
  }
  
  public static void setSenderMail(String mail)
  {
    senderMail = mail;
  }
  
  public static String getBirthdayMail()
  {
    return birthdayMail;
  }
  
  public static void setBirthdayMail(String mail)
  {
    birthdayMail = mail;
  }
  
  public static void setBirthdayBefore(int day)
  {
    birthdayBefore = day;
  }
  
  public static int getBirthdayBefore()
  {
    return birthdayBefore;
  }
  
  public static void loadProperties(ServletContext context)
  {
    repoArchiveFolder = context.getRealPath(PATH_REPORT_ARCHIVES);
    uploadTaskFolder = context.getRealPath(PATH_TASK_UPLOAD);
    uploadQuoteFolder = context.getRealPath(PATH_QUOTE_UPLOAD);
    String strFilename = context.getRealPath("/conf") + "/TMConfig.properties";
    strFilename.replace('\\', '/');
    File file = new File(strFilename);
    if (!file.exists()) {
      return;
    }
    try
    {
      Properties pro = new Properties();
      InputStream input = new FileInputStream(file);
      pro.load(input);
      input.close();
      setMailServer((String)pro.get("mail.smtp.host"));
      setSenderMail((String)pro.get("mail.from"));
      setBirthdayMail((String)pro.get("mail.birthday.announcement"));
      setBirthdayBefore(Integer.parseInt((String)pro.get("mail.birthday.beforeday")));
    }
    catch (Exception localException) {}
  }
}
