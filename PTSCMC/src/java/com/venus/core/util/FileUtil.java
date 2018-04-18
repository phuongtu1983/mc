/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.core.util;

import java.io.*;
import java.nio.channels.*;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.jar.JarOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 *
 * @author Administrator
 */
public class FileUtil {

    final public static int MAX_BUFFER = 1024;

    public static void createFullFolder(String pathName) throws IOException {
        String str = pathName.replace('\\', '/');
        StringTokenizer token = new StringTokenizer(str, "/");
        String dir = "";
        while (token.hasMoreTokens()) {
            str = token.nextToken();
            if (str.indexOf(':') == 1) {
                dir = str;
            } else {
                dir = dir + "/" + str;
                try {
                    new File(dir).mkdir();
                } catch (Exception ex) {
                }
            }
        }
    }

    public static String getFileExt(String fileName) throws IOException {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    public static String getFileName(String fileName) throws IOException {
        String sFileName = fileName;
        String sName, sExt;
        sName = fileName.substring(0, fileName.lastIndexOf("."));
        sExt = fileName.substring(fileName.lastIndexOf("."));
        int i = 1;
        File file = new File(fileName);
        while (file.exists()) {
            sFileName = sName + "(" + i + ")" + sExt;
            file = new File(sFileName);
            i++;
        }
        return sFileName;
    }

    public static void copyFile(File in, File out)
            throws IOException {
        FileChannel inChannel = new FileInputStream(in).getChannel();
        FileChannel outChannel = new FileOutputStream(out).getChannel();
        try {
            inChannel.transferTo(0, inChannel.size(),
                    outChannel);
        } catch (IOException e) {
            throw e;
        } finally {
            if (inChannel != null) {
                inChannel.close();
            }
            if (outChannel != null) {
                outChannel.close();
            }
        }
    }

    public static void saveFile(InputStream in, String fileName)
            throws IOException {
        //write the file to the file specified
        OutputStream bos = new FileOutputStream(fileName);
        int bytesRead = 0;
        byte[] buffer = new byte[MAX_BUFFER];
        while ((bytesRead = in.read(buffer, 0, MAX_BUFFER)) != -1) {
            bos.write(buffer, 0, bytesRead);
        }
        bos.close();
        //close the stream
        in.close();
    }

    public static boolean deleteFile(String fileName) {
        File f1 = new File(fileName);
        return f1.delete();
    }

    public static File createFile(String fileName) {
        File f = null;
        try {
            f = new File(fileName);
            if (!f.exists()) {
                f.createNewFile();
            }
        } catch (Exception ex) {
            LogUtil.error("FAILED:FileUtil:createFile-" + ex.getMessage());
            ex.printStackTrace();
        }
        return f;
    }

    public static void zipFile(String zipFileName, ArrayList arrFileNames) {
        try {
            ZipOutputStream out = new JarOutputStream(new BufferedOutputStream(new FileOutputStream(zipFileName)));
            byte[] data = new byte[1000];
            BufferedInputStream in = null;
            String fileName = "";
            for (int i = 0; i < arrFileNames.size(); i++) {
                fileName = (String) arrFileNames.get(i);
                in = new BufferedInputStream(new FileInputStream(fileName));
                int count;
                out.putNextEntry(new ZipEntry(fileName));
                while ((count = in.read(data, 0, 1000)) != -1) {
                    out.write(data, 0, count);
                }
                in.close();
            }
            out.flush();
            out.close();
        } catch (Exception ex) {
            LogUtil.error("FAILED:FileUtil:zipFile-" + ex.getMessage());
            ex.printStackTrace();
        } finally {
            String fileName = "";
            for (int i = 0; i < arrFileNames.size(); i++) {
                fileName = (String) arrFileNames.get(i);
                File file = new File(fileName);
                file.delete();
            }
        }
    }
}