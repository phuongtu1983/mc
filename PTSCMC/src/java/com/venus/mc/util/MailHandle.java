package com.venus.mc.util;

import com.venus.core.exception.BadInputException;
import com.venus.core.filter.DisableHtmlTagFilter;
import com.venus.core.util.LogUtil;
import com.venus.core.util.StringUtil;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Date;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message.RecipientType;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class MailHandle
{
  public static final String EMAIL_DEFAULT = "tmadmin@ptsc.com.vn";
  
  public static class Authenticator
    extends Authenticator
  {
    private PasswordAuthentication authentication;
    
    public Authenticator(String username, String password)
    {
      this.authentication = new PasswordAuthentication(username, password);
    }
    
    protected PasswordAuthentication getPasswordAuthentication()
    {
      return this.authentication;
    }
  }
  
  public static void checkGoodEmail(String input)
    throws BadInputException
  {
    if (input == null) {
      throw new BadInputException("Sorry, null string is not a good email.");
    }
    int atIndex = input.indexOf('@');
    int dotIndex = input.lastIndexOf('.');
    if ((atIndex == -1) || (dotIndex == -1) || (atIndex >= dotIndex)) {
      throw new BadInputException("Error: '" + DisableHtmlTagFilter.filter(input) + "' is not a valid email value. Please try again.");
    }
    int length = input.length();
    char c = '\000';
    for (int i = 0; i < length; i++)
    {
      c = input.charAt(i);
      if (((c < 'a') || (c > 'z')) && ((c < 'A') || (c > 'Z')) && ((c < '0') || (c > '9')) && (((c != '_') && (c != '-') && (c != '.') && (c != '@')) || (i == 0))) {
        throw new BadInputException(input + " is not a valid email. Reason: character '" + c + "' is not accepted in an email.");
      }
    }
    try
    {
      new InternetAddress(input);
    }
    catch (Exception ex)
    {
      throw new BadInputException("Assertion: dont want to occur in Util.checkGoodEmail");
    }
  }
  
  public static Address newEmailAddress(String email)
    throws BadInputException
  {
    Address address = null;
    if (email == null) {
      throw new BadInputException("Sorry, null string is not a good email.");
    }
    int atIndex = email.indexOf('@');
    int dotIndex = email.lastIndexOf('.');
    if ((atIndex == -1) || (dotIndex == -1) || (atIndex >= dotIndex)) {
      throw new BadInputException("Error: '" + DisableHtmlTagFilter.filter(email) + "' is not a valid email value. Please try again.");
    }
    int length = email.length();
    char c = '\000';
    for (int i = 0; i < length; i++)
    {
      c = email.charAt(i);
      if (((c < 'a') || (c > 'z')) && ((c < 'A') || (c > 'Z')) && ((c < '0') || (c > '9')) && (((c != '_') && (c != '-') && (c != '.') && (c != '@')) || (i == 0))) {
        throw new BadInputException(email + " is not a valid email. Reason: character '" + c + "' is not accepted in an email.");
      }
    }
    try
    {
      address = new InternetAddress(email);
    }
    catch (Exception ex)
    {
      throw new BadInputException("Assertion: dont want to occur in Util.checkGoodEmail");
    }
    return address;
  }
  
  private static int getResponseCode(String s)
  {
    if ((s == null) || (s.length() < 3)) {
      return -1;
    }
    return Integer.valueOf(s.substring(0, 3)).intValue();
  }
  
  public static boolean validEmailAddress(String email, String mailServer)
  {
    Socket socket = new Socket();
    try
    {
      StringBuilder stringbuffer = new StringBuilder(128);
      
      InetSocketAddress inetsocketaddress = new InetSocketAddress(mailServer, 25);
      socket.connect(inetsocketaddress, 10000);
      socket.setSoTimeout(10000);
      BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      PrintWriter printwriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())));
      String ret = bufferedreader.readLine();
      boolean bool;
      if (getResponseCode(ret) != 220)
      {
        printwriter.print("QUIT\r\n");
        printwriter.flush();
        socket.close();
        return false;
      }
      stringbuffer.setLength(0);
      stringbuffer.append("HELO ");
      stringbuffer.append(socket.getLocalAddress().getHostName());
      stringbuffer.append("\r\n");
      printwriter.print(stringbuffer.toString());
      printwriter.flush();
      ret = bufferedreader.readLine();
      if (getResponseCode(ret) != 250)
      {
        printwriter.print("QUIT\r\n");
        printwriter.flush();
        socket.close();
        return false;
      }
      stringbuffer.setLength(0);
      stringbuffer.append("MAIL FROM: <");
      stringbuffer.append(email);
      stringbuffer.append(">\r\n");
      printwriter.print(stringbuffer.toString());
      printwriter.flush();
      ret = bufferedreader.readLine();
      if (getResponseCode(ret) != 250)
      {
        printwriter.print("QUIT\r\n");
        printwriter.flush();
        socket.close();
        return false;
      }
      printwriter.print("QUIT\r\n");
      printwriter.flush();
      socket.close();
      return true;
    }
    catch (Exception localException4) {}finally
    {
      try
      {
        socket.close();
        socket = null;
      }
      catch (Exception localException6) {}
    }
    return false;
  }
  
  public static boolean sendMail(String mail_from, String mail_to, String mail_cc, String mail_subject, String mail_content, String path_prefix, String mail_att, String mail_server)
    throws Exception
  {
    if ((StringUtil.isBlankOrNull(mail_to)) || (StringUtil.isBlankOrNull(mail_server))) {
      return false;
    }
    Properties props = new Properties();
    props.setProperty("mail.host", mail_server);
    props.put("mail.mime.charset", "UTF-8");
    
    Session mailsession = Session.getInstance(props, null);
    MimeMessage message = new MimeMessage(mailsession);
    Address fromAddress = new InternetAddress(mail_from);
    
    BodyPart messageBodyPart = new MimeBodyPart();
    Multipart multipart = new MimeMultipart();
    
    String[] arrEmailCC = mail_cc.split(",");
    String[] arrEmailTO = mail_to.split(",");
    String[] arrFileAtt = mail_att.split(";");
    
    String query_string = "";
    boolean result = true;
    try
    {
      messageBodyPart.setContent(mail_content, "text/html; charset=UTF-8");
      multipart.addBodyPart(messageBodyPart);
      message.addHeaderLine("charset=UTF-8");
      message.setFrom(fromAddress);
      message.setSubject(mail_subject, "UTF-8");
      message.setContent(multipart);
      if (!StringUtil.isBlankOrNull(mail_att))
      {
        File mail_f = new File("");
        for (int i = 0; i < arrFileAtt.length; i++)
        {
          mail_f = new File(arrFileAtt[i]);
          if ((arrFileAtt[i] != null) && (!arrFileAtt[i].equals("")) && (mail_f.exists()))
          {
            messageBodyPart = new MimeBodyPart();
            DataSource source = new FileDataSource(arrFileAtt[i]);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(StringUtil.replace(arrFileAtt[i], path_prefix, ""));
            multipart.addBodyPart(messageBodyPart);
          }
        }
      }
      for (int i = 0; i < arrEmailTO.length; i++) {
        if (validEmailAddress(arrEmailTO[i], mail_server))
        {
          Address toAddress = new InternetAddress(arrEmailTO[i]);
          message.addRecipient(Message.RecipientType.TO, toAddress);
        }
      }
      if (!StringUtil.isBlankOrNull(mail_cc)) {
        for (int i = 0; i < arrEmailCC.length; i++) {
          if ((!arrEmailCC[i].equals("none")) && 
            (validEmailAddress(arrEmailCC[i], mail_server)))
          {
            Address ccAddress = new InternetAddress(arrEmailCC[i]);
            message.addRecipient(Message.RecipientType.CC, ccAddress);
          }
        }
      }
      if ((message.getRecipients(Message.RecipientType.TO) == null) || (message.getRecipients(Message.RecipientType.TO).length == 0))
      {
        Address toAddress = new InternetAddress("tmadmin@ptsc.com.vn");
        message.addRecipient(Message.RecipientType.TO, toAddress);
      }
      message.setSentDate(new Date());
      message.saveChanges();
      Transport.send(message);
      LogUtil.info("Send mail subject : " + mail_subject + "; date : " + message.getSentDate());
    }
    catch (Exception e)
    {
      LogUtil.error(e.getMessage());
      result = false;
    }
    return result;
  }
  
  public static boolean sendMailAuth(String mail_from, String mail_to, String mail_cc, String mail_subject, String mail_content, String path_prefix, String mail_att, String mail_server, String mail_acc, String mail_pass)
    throws Exception
  {
    if ((StringUtil.isBlankOrNull(mail_to)) || (StringUtil.isBlankOrNull(mail_server))) {
      return false;
    }
    Properties props = new Properties();
    props.setProperty("mail.host", mail_server);
    props.put("mail.mime.charset", "UTF-8");
    
    Authenticator auth = new Authenticator(mail_acc, mail_pass);
    Session mailsession = Session.getInstance(props, auth);
    MimeMessage message = new MimeMessage(mailsession);
    Address fromAddress = new InternetAddress(mail_from);
    
    BodyPart messageBodyPart = new MimeBodyPart();
    Multipart multipart = new MimeMultipart();
    
    String[] arrEmailCC = mail_cc.split(",");
    String[] arrEmailTO = mail_to.split(",");
    String[] arrFileAtt = mail_att.split(";");
    
    String query_string = "";
    boolean result = true;
    try
    {
      messageBodyPart.setContent(mail_content, "text/html; charset=UTF-8");
      multipart.addBodyPart(messageBodyPart);
      message.addHeaderLine("charset=UTF-8");
      message.setFrom(fromAddress);
      message.setSubject(mail_subject, "UTF-8");
      message.setContent(multipart);
      if (!StringUtil.isBlankOrNull(mail_att))
      {
        File mail_f = new File("");
        for (int i = 0; i < arrFileAtt.length; i++)
        {
          mail_f = new File(arrFileAtt[i]);
          if ((arrFileAtt[i] != null) && (!arrFileAtt[i].equals("")) && (mail_f.exists()))
          {
            messageBodyPart = new MimeBodyPart();
            DataSource source = new FileDataSource(arrFileAtt[i]);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(StringUtil.replace(arrFileAtt[i], path_prefix, ""));
            multipart.addBodyPart(messageBodyPart);
          }
        }
      }
      for (int i = 0; i < arrEmailTO.length; i++)
      {
        Address toAddress = new InternetAddress(arrEmailTO[i]);
        message.addRecipient(Message.RecipientType.TO, toAddress);
      }
      if (!StringUtil.isBlankOrNull(mail_cc)) {
        for (int i = 0; i < arrEmailCC.length; i++) {
          if (!arrEmailCC[i].equals("none"))
          {
            Address ccAddress = new InternetAddress(arrEmailCC[i]);
            message.addRecipient(Message.RecipientType.CC, ccAddress);
          }
        }
      }
      message.setSentDate(new Date());
      message.saveChanges();
      Transport.send(message);
    }
    catch (Exception e)
    {
      LogUtil.error(e.getMessage());
      result = false;
    }
    return result;
  }
  
  public static boolean sendMail(String mail_from, String mail_to, String mail_subject, String mail_content)
    throws Exception
  {
    return sendMail(mail_from, mail_to, "", mail_subject, mail_content, "", "", MCConfig.getMailServer());
  }
}
