package com.venus.mc.core;

import com.venus.core.sax.DOMDocument;
import com.venus.core.sax.RowSAXHandler;
import com.venus.core.sax.SAXContentHandler;
import com.venus.core.sax.TableSAXHandler;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jdom.Element;

public class SpineReportParser
{
  public static final String CHECKBOX_TAG = "checkbox";
  protected String xmlTemplate;
  protected String wordTemplate;
  private String resultFileName;
  protected Element root;
  SAXContentHandler sax;
  DOMDocument xmlProperties;
  HttpServletRequest request;
  
  public SpineReportParser() {}
  
  public HttpServletRequest getRequest()
  {
    return this.request;
  }
  
  public void setRequest(HttpServletRequest request)
  {
    this.request = request;
  }
  
  public SpineReportParser(String xmlTemplate, String wordTemplate, String resultFileName)
  {
    this.wordTemplate = wordTemplate;
    this.xmlTemplate = xmlTemplate;
    this.resultFileName = resultFileName;
  }
  
  public void init(HttpServletRequest request)
    throws Exception
  {
    try
    {
      this.xmlProperties = new DOMDocument(this.xmlTemplate);
      this.sax = new TableSAXHandler(this.wordTemplate);
      this.root = this.xmlProperties.getRoot();
    }
    catch (Exception localException) {}
  }
  
  private void endParse(HttpServletResponse response)
    throws Exception
  {
    try
    {
      this.sax.parse(response, this.xmlProperties, this.resultFileName);
    }
    catch (Exception localException) {}
  }
  
  protected void parse(Object object)
    throws Exception
  {}
  
  protected void setArrTable(Hashtable arr)
  {
    this.sax.setArrTable(arr);
  }
  
  public void setText(String childName, String text)
  {
    try
    {
      Element el = this.root.getChild(childName);
      if (el != null) {
        el.setText(text);
      }
    }
    catch (Exception localException) {}
  }
  
  public void setCheck(String childName, boolean bCheck)
  {
    Element el = this.root.getChild(childName);
    if (el != null) {
      el.setText(Boolean.toString(bCheck));
    }
  }
  
  public String getTabText(String rowId, int i, String tab)
  {
    return "";
  }
  
  public void addNode(String childName)
  {
    this.root.getChildren().add(new Element(childName));
  }
  
  public void addNode(String childName, String text)
  {
    Element child = new Element(childName);
    child.setText(text);
    this.root.getChildren().add(child);
  }
  
  public void autoSetTag()
  {
    Hashtable map = this.sax.getArrTable();
    if (map == null) {
      return;
    }
    Enumeration enums = map.keys();
    RowSAXHandler row = null;
    String id = "";
    Element element = null;
    while (enums.hasMoreElements())
    {
      id = (String)enums.nextElement();
      element = this.root.getChild(id);
      if (element != null)
      {
        List<Element> childs = element.getChildren();
        Element c = null;
        ArrayList tabs = new ArrayList();
        row = (RowSAXHandler)map.get(id);
        row.setArrTab(tabs);
        for (int i = 0; i < childs.size(); i++)
        {
          c = (Element)childs.get(i);
          Element child = new Element(c.getName());
          this.root.getChildren().add(child);
          tabs.add(c.getName());
        }
        this.root.removeChild(id);
      }
    }
  }
  
  public void executeParse(HttpServletRequest request, HttpServletResponse response, Object object)
    throws Exception
  {
    try
    {
      init(request);
      parse(object);
      autoSetTag();
      endParse(response);
    }
    catch (Exception ex)
    {
      Logger.getLogger("SpinReportParser.executeParse").log(Level.SEVERE, null, ex);
    }
    finally
    {
      this.root = null;
      this.xmlProperties = null;
      this.sax = null;
    }
  }
  
  public void executeParse(HttpServletRequest request, HttpServletResponse response, Object object, OutputStream output)
    throws Exception
  {
    try
    {
      init(request);
      this.sax.setOutput(output);
      parse(object);
      autoSetTag();
      endParse(response);
    }
    catch (Exception localException) {}finally
    {
      this.root = null;
      this.xmlProperties = null;
      this.sax = null;
    }
  }
}
