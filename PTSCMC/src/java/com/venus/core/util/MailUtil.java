/*
 * MailUtil.java
 *
 * Created on March 24, 2007, 10:24 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.venus.core.util;

import com.venus.core.filter.DisableHtmlTagFilter;
import com.venus.mc.util.MailHandle;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Properties;
import javax.mail.internet.*;
import javax.mail.*;
import java.io.File;
import javax.activation.*;
import java.util.Date;
import javax.mail.internet.InternetAddress;
/**
 *
 * @author Administrator
 */
public class MailUtil {
    
    public static class Authenticator extends javax.mail.Authenticator {
        private PasswordAuthentication authentication;
        
        public Authenticator(String username, String password) {
            authentication = new PasswordAuthentication(username,
                password);
        }
        
        @Override
        protected PasswordAuthentication getPasswordAuthentication() {
            return authentication;
        }
    }
    
    /** Creates a new instance of MailUtil */
    public MailUtil() {
    }
    
    public static void checkGoodEmail(String input)
    throws Exception {
        if(input == null)
            throw new Exception("Sorry, null string is not a good email.");
        int atIndex = input.indexOf('@');
        int dotIndex = input.lastIndexOf('.');
        if(atIndex == -1 || dotIndex == -1 || atIndex >= dotIndex)
            throw new Exception("Error: '" + DisableHtmlTagFilter.filter(input) + "' is not a valid email value. Please try again.");
        int length = input.length();
        char c = '\0';
        for(int i = 0; i < length; i++) {
            c = input.charAt(i);
            if((c < 'a' || c > 'z') && (c < 'A' || c > 'Z') && (c < '0' || c > '9') && (c != '_' && c != '-' && c != '.' && c != '@' || i == 0))
                throw new Exception(input + " is not a valid email. Reason: character '" + c + "' is not accepted in an email.");
        }
        
        try {
            new InternetAddress(input);
        } catch(Exception ex) {
            throw new Exception("Assertion: dont want to occur in Util.checkGoodEmail");
        }
    }
    
    public static Address newEmailAddress(String email)
    throws Exception {
        Address address = null;
        if(email == null)
            throw new Exception("Sorry, null string is not a good email.");
        int atIndex = email.indexOf('@');
        int dotIndex = email.lastIndexOf('.');
        if(atIndex == -1 || dotIndex == -1 || atIndex >= dotIndex)
            throw new Exception("Error: '" + DisableHtmlTagFilter.filter(email) + "' is not a valid email value. Please try again.");
        int length = email.length();
        char c = '\0';
        for(int i = 0; i < length; i++) {
            c = email.charAt(i);
            if((c < 'a' || c > 'z') && (c < 'A' || c > 'Z') && (c < '0' || c > '9') && (c != '_' && c != '-' && c != '.' && c != '@' || i == 0))
                throw new Exception(email + " is not a valid email. Reason: character '" + c + "' is not accepted in an email.");
        }
        
        try {
            address = new InternetAddress(email);
        } catch(Exception ex) {
            throw new Exception("Assertion: dont want to occur in Util.checkGoodEmail");
        }
        return address;
    }
    
    private static final int getResponseCode(String s) {
        if(s == null || s.length() < 3) {
            return -1;
        } else {
            return Integer.valueOf(s.substring(0, 3)).intValue();
        }
    }
    
    public static final boolean validEmailAddress(String email, String mailServer) {
        Socket socket = new Socket();
        try {
            StringBuffer stringbuffer = new StringBuffer(128);
            String ret;
            InetSocketAddress inetsocketaddress = new InetSocketAddress(mailServer, 25);
            socket.connect(inetsocketaddress, 10000);
            socket.setSoTimeout(10000);
            BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter printwriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())));
            ret = bufferedreader.readLine();
            if (getResponseCode(ret)!=220) {
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
            if (getResponseCode(ret)!=250) {
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
            if (getResponseCode(ret)!=250) {
                printwriter.print("QUIT\r\n");
                printwriter.flush();
                socket.close();
                return false;
            }
            printwriter.print("QUIT\r\n");
            printwriter.flush();
            socket.close();
            return true;
        } catch (Exception ex) {
        } finally {
            try {
                socket.close();
                socket = null;
            } catch (Exception e) {
            }
        }
        return false;
    }
    public static boolean sendMail(
        String mail_from,
        String mail_to,
        String mail_cc,
        String mail_subject,
        String mail_content,
        String path_prefix,
        String mail_att,
        String mail_server) throws Exception {
        
        if (StringUtil.isBlankOrNull(mail_to) ||
            StringUtil.isBlankOrNull(mail_server)) {
            return false;
        }
        
        Properties props = new Properties();
        props.setProperty("mail.host",mail_server);
        props.put("mail.mime.charset", "UTF-8");
        
        Session mailsession = Session.getInstance(props, null);
        MimeMessage message = new MimeMessage(mailsession);
        Address fromAddress = new InternetAddress(mail_from);
        
        BodyPart messageBodyPart = new MimeBodyPart();
        Multipart multipart = new MimeMultipart();
        
        String[] arrEmailCC = StringUtil.toTokensArray(mail_cc,";");
        String[] arrEmailTO = StringUtil.toTokensArray(mail_to,";");
        String[] arrFileAtt = StringUtil.toTokensArray(mail_att,";");
        
        String query_string = "";
        boolean result = true;
        
        try {
            messageBodyPart.setContent(mail_content, "text/html; charset=UTF-8");
            multipart.addBodyPart(messageBodyPart);
            message.addHeaderLine("charset=UTF-8");
            message.setFrom(fromAddress);
            message.setSubject(mail_subject,"UTF-8");
            message.setContent(multipart);
            if (!StringUtil.isBlankOrNull(mail_att)) {
                File mail_f=new File("");
                for (int i=0; i<arrFileAtt.length; i++){
                    //arrFileAtt[i] = path_prefix + arrFileAtt[i];
                    mail_f = new File(arrFileAtt[i]);
                    if ((arrFileAtt[i] !=null) &&  (!arrFileAtt[i].equals("")) && mail_f.exists()){
                        messageBodyPart = new MimeBodyPart();
                        DataSource source = new FileDataSource(arrFileAtt[i]);
                        messageBodyPart.setDataHandler(new DataHandler(source));
                        messageBodyPart.setFileName(StringUtil.replace(arrFileAtt[i],path_prefix,""));
                        multipart.addBodyPart(messageBodyPart);
                    }
                }
            }            
            for (int i = 0; i<arrEmailTO.length; i++){
                if (validEmailAddress(arrEmailTO[i],mail_server)) {
                    Address toAddress = new InternetAddress(arrEmailTO[i]);
                    message.addRecipient(Message.RecipientType.TO, toAddress);
                }
            }
            if (!StringUtil.isBlankOrNull(mail_cc)) {
                for (int i = 0; i<arrEmailCC.length; i++){
                    if (!arrEmailCC[i].equals("none") ){
                        if (validEmailAddress(arrEmailCC[i],mail_server)) {
                            Address ccAddress = new InternetAddress(arrEmailCC[i]);
                            message.addRecipient(Message.RecipientType.CC, ccAddress);
                        }
                    }
                }
            }
            if (message.getRecipients(Message.RecipientType.TO)==null ||
                message.getRecipients(Message.RecipientType.TO).length==0) {
                Address toAddress = new InternetAddress(MailHandle.EMAIL_DEFAULT);
                message.addRecipient(Message.RecipientType.TO,toAddress);
            }
            message.setSentDate(new Date());
            message.saveChanges();
            Transport.send(message);
        } catch (Exception e) {
            LogUtil.error(e.getMessage());
            result = false;
        }
        return result;
    }
    
    public static boolean sendMailAuth(
        String mail_from,
        String mail_to,
        String mail_cc,
        String mail_subject,
        String mail_content,
        String path_prefix,
        String mail_att,
        String mail_server,
        String mail_acc,
        String mail_pass) throws Exception {
        
        if (StringUtil.isBlankOrNull(mail_to) ||
            StringUtil.isBlankOrNull(mail_server)) {
            return false;
        }
        
        Properties props = new Properties();
        props.setProperty("mail.host",mail_server);
        props.put("mail.mime.charset", "UTF-8");
        
        Authenticator auth = new Authenticator(mail_acc,mail_pass);
        Session mailsession = Session.getInstance(props, auth);
        MimeMessage message = new MimeMessage(mailsession);
        Address fromAddress = new InternetAddress(mail_from);
        
        BodyPart messageBodyPart = new MimeBodyPart();
        Multipart multipart = new MimeMultipart();
        
        String[] arrEmailCC = StringUtil.toTokensArray(mail_cc,";");
        String[] arrEmailTO = StringUtil.toTokensArray(mail_to,";");
        String[] arrFileAtt = StringUtil.toTokensArray(mail_att,";");
        
        String query_string = "";
        boolean result = true;
        
        try {
            messageBodyPart.setContent(mail_content, "text/html; charset=UTF-8");
            multipart.addBodyPart(messageBodyPart);
            message.addHeaderLine("charset=UTF-8");
            message.setFrom(fromAddress);
            message.setSubject(mail_subject,"UTF-8");
            message.setContent(multipart);
            if (!StringUtil.isBlankOrNull(mail_att)) {
                File mail_f=new File("");
                for (int i=0; i<arrFileAtt.length; i++){
                    //arrFileAtt[i] = path_prefix + arrFileAtt[i];
                    mail_f = new File(arrFileAtt[i]);
                    if ((arrFileAtt[i] !=null) &&  (!arrFileAtt[i].equals("")) && mail_f.exists()){
                        messageBodyPart = new MimeBodyPart();
                        DataSource source = new FileDataSource(arrFileAtt[i]);
                        messageBodyPart.setDataHandler(new DataHandler(source));
                        messageBodyPart.setFileName(StringUtil.replace(arrFileAtt[i],path_prefix,""));
                        multipart.addBodyPart(messageBodyPart);
                    }
                }
            }
            for (int i = 0; i<arrEmailTO.length; i++){
                Address toAddress = new InternetAddress(arrEmailTO[i]);
                message.addRecipient(Message.RecipientType.TO, toAddress);
            }
            if (!StringUtil.isBlankOrNull(mail_cc)) {
                for (int i = 0; i<arrEmailCC.length; i++){
                    if (!arrEmailCC[i].equals("none") ){
                        Address ccAddress = new InternetAddress(arrEmailCC[i]);
                        message.addRecipient(Message.RecipientType.CC, ccAddress);
                    }
                }
            }
            message.setSentDate(new Date());
            message.saveChanges();
            Transport.send(message);
        } catch (Exception e) {
            LogUtil.error(e.getMessage());
            result = false;
        }
        return result;
    }
}
