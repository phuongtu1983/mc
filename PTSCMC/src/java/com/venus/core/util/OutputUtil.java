/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.core.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author phuongtu
 */
public class OutputUtil {

    public static void sendStringToOutput(HttpServletResponse resp, String content) {
        try {
            System.out.println("Send response: " + content);
            resp.setCharacterEncoding("UTF-8");
            resp.setContentType("text/html; charset=UTF-8");
            resp.getOutputStream().write(content.getBytes("UTF-8"));
        } catch (IOException ex) {
            Logger.getLogger(OutputUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void sendWordFileToOutput(HttpServletResponse resp, String patch, String fileName) throws Exception {
        ServletOutputStream stream = null;
        BufferedInputStream buf = null;
        try {
            stream = resp.getOutputStream();
            File doc = new File(patch + fileName);
            resp.setContentType("application/msword");
            resp.addHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
            resp.setContentLength((int) doc.length());
            FileInputStream input = new FileInputStream(doc);
            buf = new BufferedInputStream(input);
            int readBytes = 0;
            while ((readBytes = buf.read()) != -1) {
                stream.write(readBytes);
            }
        } catch (IOException ioe) {
        } finally {
            if (stream != null) {
                stream.close();
                File file = new File(patch + fileName);
                file.delete();
            }
            if (buf != null) {
                buf.close();
            }
        }
    }

    public static void sendZipFileToOutput(HttpServletResponse resp, String fileName) throws Exception {
        FileInputStream fis = new FileInputStream(fileName);
        BufferedInputStream stream = new BufferedInputStream(fis);
        try {
            resp.setContentType("application/zip");
            resp.setHeader("Content-disposition", "attachment; filename=\"" + fileName + "\"");
            resp.setHeader("Cache-Control", "max-age=1000");
            OutputStream output = resp.getOutputStream();
            byte[] buffer = new byte[4028];
            int n = 0;
            while (-1 != (n = stream.read(buffer))) {
                output.write(buffer, 0, n);
            }
        } finally {
            if (stream != null) {
                stream.close();
                File file = new File(fileName);
                file.delete();
            }
        }
    }
}
