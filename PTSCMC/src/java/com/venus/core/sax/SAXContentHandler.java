package com.venus.core.sax;

import com.venus.core.util.DateUtil;
import com.venus.core.util.GenericValidator;
import com.venus.core.util.NumberUtil;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.jdom.Element;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

public class SAXContentHandler
  extends DefaultHandler
{
  protected XMLReader xmlReader = null;
  protected boolean canonical = true;
  protected String xmlInputFile;
  protected OutputStream output = null;
  protected StringBuffer elementContent;
  protected DOMDocument template = null;
  protected boolean isDirectWrite = true;
  
  public SAXContentHandler()
    throws Exception
  {}
  
  public void setArrTable(Hashtable arr) {}
  
  public Hashtable getArrTable()
  {
    return null;
  }
  
  public void setOutput(OutputStream output)
  {
    this.output = output;
  }
  
  public void parse(HttpServletResponse resp, DOMDocument doc, String outputFileName)
    throws Exception
  {
    if (GenericValidator.isBlankOrNull(this.xmlInputFile)) {
      return;
    }
    try
    {
      InputSource source = new InputSource(this.xmlInputFile);
      if (this.output == null)
      {
        this.output = resp.getOutputStream();
        resp.setContentType("application/msword");
        resp.setHeader("Content-disposition", "attachment; filename=\"" + outputFileName + "\"");
        resp.setHeader("Cache-Control", "max-age=1000");
      }
      this.template = doc;
      this.xmlReader.parse(source);
    }
    catch (Exception ex)
    {
      throw ex;
    }
  }
  
  protected String normalize(String s)
  {
    StringBuffer str = new StringBuffer();
    
    int len = s != null ? s.length() : 0;
    for (int i = 0; i < len; i++)
    {
      char ch = s.charAt(i);
      switch (ch)
      {
      case '<': 
        str.append("&lt;");
        break;
      case '>': 
        str.append("&gt;");
        break;
      case '&': 
        str.append("&amp;");
        break;
      case '"': 
        str.append("&quot;");
        break;
      case '\'': 
        str.append("&apos;");
        break;
      case '\n': 
      case '\r': 
        if (this.canonical)
        {
          str.append("&#");
          str.append(Integer.toString(ch));
          str.append(';');
        }
        break;
      }
      str.append(ch);
    }
    return str.toString();
  }
  
  public void write(String str)
  {
    try
    {
      if (this.isDirectWrite == true) {
        this.output.write(str.getBytes("UTF-8"));
      } else {
        this.elementContent.append(str);
      }
    }
    catch (Exception ex)
    {
      ex.getMessage();
    }
  }
  
  public void write(char[] ch, int start, int length)
  {
    String str = new String(ch, start, length);
    write(str);
  }
  
  public void startDocument()
  {
    try
    {
      write("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>");
      write("<?mso-application progid=\"Word.Document\"?>");
    }
    catch (Exception localException) {}
  }
  
  public void endDocument()
  {
    try
    {
      this.output.flush();
      this.output.close();
    }
    catch (Exception localException)
    {
      localException = 
      
        localException;
    }
    finally {}
  }
  
  private String findAttValueFromTemplateByAtt(String qName, String attName)
  {
    if (this.template == null) {
      return null;
    }
    if (this.template.root == null) {
      return null;
    }
    List<Element> childs = this.template.root.getChildren();
    for (int i = 0; i < childs.size(); i++)
    {
      Element e = (Element)childs.get(i);
      if (e.getName().equals(qName))
      {
        String value = e.getAttributeValue(attName);
        if (!GenericValidator.isBlankOrNull(value)) {
          return value;
        }
      }
    }
    return null;
  }
  
  private Element findElementFromTemplateByAtt(String qName, String attName, String attValue)
  {
    if (this.template == null) {
      return null;
    }
    if (this.template.root == null) {
      return null;
    }
    Iterator itr = this.template.root.getChildren(qName).iterator();
    while (itr.hasNext())
    {
      Element e = (Element)itr.next();
      
      String value = e.getAttributeValue(attName);
      if ((!GenericValidator.isBlankOrNull(value)) && (attValue.equals(attValue))) {
        return e;
      }
    }
    return null;
  }
  
  private boolean processSym(String namespaceURI, String localName, String qName, Attributes attr)
  {
    if (qName.equals("w:sym"))
    {
      String id = attr.getValue("w:id");
      boolean b = false;
      if (!GenericValidator.isBlankOrNull(id))
      {
        Element e = this.template.root.getChild(id);
        if (e != null)
        {
          String value = e.getText();
          if (!GenericValidator.isBlankOrNull(value)) {
            b = NumberUtil.parseBool(value, false);
          }
        }
      }
      write("<");
      write(qName);
      for (int i = 0; i < attr.getLength(); i++)
      {
        String attName = attr.getQName(i);
        String attValue;
        String attValue;
        if ((attName.equals("w:char")) && (b)) {
          attValue = "F0FE";
        } else {
          attValue = attr.getValue(attName);
        }
        write(" ");
        write(attName);
        write("=\"");
        write(normalize(attValue));
        write("\"");
      }
      write(">");
      return true;
    }
    return false;
  }
  
  public void startElement(String namespaceURI, String localName, String qName, Attributes attr)
  {
    try
    {
      if (!processSym(namespaceURI, localName, qName, attr))
      {
        write("<");
        write(qName);
        for (int i = 0; i < attr.getLength(); i++)
        {
          String attName = attr.getQName(i);
          String attValue = attr.getValue(attName);
          write(" ");
          write(attName);
          write("=\"");
          write(normalize(attValue));
          write("\"");
        }
        write(">");
      }
    }
    catch (Exception localException) {}
  }
  
  public void endElement(String namespaceURI, String localName, String qName)
  {
    try
    {
      write("</");
      write(qName);
      write(">");
    }
    catch (Exception localException) {}
  }
  
  public void characters(char[] ch, int start, int length)
  {
    try
    {
      String str = new String(ch, start, length);
      str = replaceContent(str);
      write(normalize(str));
    }
    catch (Exception localException) {}
  }
  
  protected String replaceContent(String str)
  {
    try
    {
      int istart = 0;
      int iend = 0;
      String contentId = "";
      istart = str.indexOf("{", istart);
      while (istart >= 0)
      {
        istart++;
        iend = str.indexOf("}", istart);
        contentId = str.substring(istart, iend);
        Element child = this.template.root.getChild(contentId);
        if (child != null) {
          str = str.replace("{" + contentId + "}", child.getText());
        }
        istart = str.indexOf("{", istart);
      }
      return str;
    }
    catch (Exception ex)
    {
      System.out.println(DateUtil.today("dd/MMM/yyyy hh:mm:ss") + " replaceContent : " + " string : " + str + " eror : " + ex.toString());
    }
    return "";
  }
}
