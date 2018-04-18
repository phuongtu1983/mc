package com.venus.core.sax;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Hashtable;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.XMLReader;

public class TableSAXHandler
  extends SAXContentHandler
{
  private Hashtable arrTable;
  private ArrayList rowAppeared = new ArrayList();
  private int count = -1;
  
  public TableSAXHandler()
    throws Exception
  {}
  
  public void setArrTable(Hashtable arr)
  {
    this.arrTable = arr;
  }
  
  public Hashtable getArrTable()
  {
    return this.arrTable;
  }
  
  public TableSAXHandler(String xmlInputFile)
    throws Exception
  {
    try
    {
      SAXParserFactory spFactory = SAXParserFactory.newInstance();
      spFactory.setValidating(false);
      SAXParser saxParser = spFactory.newSAXParser();
      this.xmlReader = saxParser.getXMLReader();
      this.xmlReader.setContentHandler(this);
      this.xmlReader.setErrorHandler(new SAXErrorHandler());
      this.xmlInputFile = xmlInputFile;
    }
    catch (Exception ex)
    {
      throw ex;
    }
  }
  
  public void startElement(String namespaceURI, String localName, String qName, Attributes attr)
  {
    if ((qName.equals("w:tr")) && 
      (isMySelf()) && 
      (attr.getLength() > 0))
    {
      int index = attr.getIndex("w:markId");
      if (index > -1)
      {
        RowSAXHandler row = (RowSAXHandler)this.arrTable.remove(attr.getValue(index));
        this.rowAppeared.add(row);
        this.count += 1;
        this.elementContent = row.getContent();
        this.isDirectWrite = false;
      }
    }
    super.startElement(namespaceURI, localName, qName, attr);
  }
  
  public void characters(char[] ch, int start, int length)
  {
    try
    {
      if (isMySelf())
      {
        if ((!this.isDirectWrite) && (this.count > -1)) {
          this.elementContent.append(normalize(new String(ch, start, length)));
        } else {
          super.characters(ch, start, length);
        }
      }
      else {
        super.characters(ch, start, length);
      }
    }
    catch (Exception localException) {}
  }
  
  public void endElement(String namespaceURI, String localName, String qName)
  {
    if (qName.equals("w:tr"))
    {
      boolean isDuplicated = false;
      if ((isMySelf()) && 
        (!this.isDirectWrite) && (this.count > -1))
      {
        RowSAXHandler row = (RowSAXHandler)this.rowAppeared.get(this.count);
        super.endElement(namespaceURI, localName, qName);
        row.duplicateRow();
        isDuplicated = true;
        try
        {
          this.output.write(replaceContent(row.getContent().toString()).getBytes("UTF-8"));
          this.isDirectWrite = true;
          this.elementContent = new StringBuffer();
          this.count -= 1;
          row.setReport(null);
          this.rowAppeared.remove(row);
        }
        catch (Exception ex)
        {
          ex.getMessage();
        }
      }
      if (!isDuplicated) {
        super.endElement(namespaceURI, localName, qName);
      }
    }
    else
    {
      super.endElement(namespaceURI, localName, qName);
    }
  }
  
  private boolean isMySelf()
  {
    return getClass().toString().endsWith("com.venus.core.sax.TableSAXHandler");
  }
}
