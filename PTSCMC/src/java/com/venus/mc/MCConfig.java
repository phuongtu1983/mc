/*
 * TMConfig.java
 *
 * Created on March 26, 2007, 4:51 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package com.venus.mc;

import com.venus.mc.util.MCUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
import javax.servlet.ServletContext;

/**
 *
 * @author Administrator
 */
public class MCConfig {

    private static String pathTaskUpload = "task/upload/";
    private static String pathReportArchive = "task/archives/";
    private static boolean showLastLogon = false;
    private static String repoArchiveFolder = "/";
    private static String uploadTaskFolder = "/";
    private static String mailServer = "mail.transimexsaigon.com";
    private static String domainName = "transimexsaigon.com";
    private static String senderMail = "tunp@transimexsaigon.com";
    private static String birthdayMail = "tmadmin@transimexsaigon.com";
    private static String companyName = "MC. LTD.";
    private static String adminEmail = "tunp@transimexsaigon.com";
    private static String authUserName = "tunp@transimexsaigon.com";
    private static String authPassword = "qwert12345";
    private static int birthdayBefore = 7;

    public static void setShowLastLogon(boolean enable) {
        showLastLogon = enable;
    }

    public static boolean isShowLastLogon() {
        return showLastLogon;
    }

    public static String getUploadTaskFolder() {
        return uploadTaskFolder;
    }

    public static String getTMReportArchiveFolder() {
        return repoArchiveFolder;
    }

    public static String getMailServer() {
        return mailServer;
    }

    public static void setMailServer(String server) {
        mailServer = server;
    }

    public static String getDomainName() {
        return domainName;
    }

    public static String getSenderMail() {
        return senderMail;
    }

    public static void setSenderMail(String mail) {
        senderMail = mail;
    }

    public static String getBirthdayMail() {
        return birthdayMail;
    }

    public static void setBirthdayMail(String mail) {
        birthdayMail = mail;
    }

    public static void setBirthdayBefore(int day) {
        birthdayBefore = day;
    }

    public static int getBirthdayBefore() {
        return birthdayBefore;
    }

    public static String getAdminEmail() {
        return adminEmail;
    }

    public static String getCompanyName() {
        return companyName;
    }

    public static void setAdminEmail(String mail) {
        adminEmail = mail;
    }

    public static void setCompanyName(String comName) {
        companyName = comName;
    }

    public static String getAuthUserName() {
        return authUserName;
    }

    public static void setAuthUserName(String authUserName) {
        MCConfig.authUserName = authUserName;
    }

    public static String getAuthPassword() {
        return authPassword;
    }

    public static void setAuthPassword(String authPassword) {
        MCConfig.authPassword = authPassword;
    }

    public static void loadProperties(ServletContext context) {

        repoArchiveFolder = context.getRealPath(pathReportArchive);
        uploadTaskFolder = context.getRealPath(pathTaskUpload);

        MCUtil.createFullFolder(repoArchiveFolder);
        MCUtil.createFullFolder(uploadTaskFolder);

        String strFilename = context.getRealPath("/conf") + "/TMConfig.properties";

        strFilename.replace('\\', '/');
        File file = new File(strFilename);
        if (!file.exists()) {
            return;
        } else {
            try {
                Properties pro = new Properties();
                InputStream input = new FileInputStream(file);
                pro.load(input);
                input.close();
                setMailServer((String) pro.get("mail.smtp.host"));
                setSenderMail((String) pro.get("mail.from"));
                setBirthdayMail((String) pro.get("mail.birthday.announcement"));
                setBirthdayBefore(Integer.parseInt((String) pro.get("mail.birthday.beforeday")));
                setCompanyName((String) pro.get("company.name"));
                setAdminEmail((String) pro.get("mail.admin"));
            } catch (Exception ex) {
            }
        }
    }
}
