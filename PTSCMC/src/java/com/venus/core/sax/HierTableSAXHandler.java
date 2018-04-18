package com.venus.core.sax;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Hashtable;
import org.xml.sax.Attributes;

public class HierTableSAXHandler
  extends TableSAXHandler
{
  private ArrayList arrTag = new ArrayList();
  private HierTableBean curTableTag;
  private HierRowBean curRowTag;
  private Hashtable arrTable;
  private final String insertedMark = "mcrp_hierarchic";
  
  public HierTableSAXHandler()
    throws Exception
  {}
  
  public HierTableSAXHandler(String xmlInputFile)
    throws Exception
  {
    super(xmlInputFile);
  }
  
  public void setArrTable(Hashtable arr)
  {
    this.arrTable = arr;
  }
  
  public Hashtable getArrTable()
  {
    return this.arrTable;
  }
  
  public void startElement(String namespaceURI, String localName, String qName, Attributes attr)
  {
    try
    {
      if (qName.equals("w:tbl"))
      {
        if (attr.getLength() > 0)
        {
          int index = attr.getIndex("w:markId");
          if ((index > -1) && 
            (isMySelf()))
          {
            if (this.curTableTag != null)
            {
              this.arrTag.add(this.curTableTag);
              this.curTableTag = new HierTableBean();
              this.curRowTag.addTable(this.curTableTag);
              this.curTableTag.setParentRow(this.curRowTag);
              this.curRowTag = null;
            }
            else
            {
              this.curTableTag = new HierTableBean();
            }
            this.isDirectWrite = false;
            this.elementContent = new StringBuffer();
            this.curTableTag.setContent(this.elementContent);
            this.curTableTag.setTableName(attr.getValue(index));
          }
        }
      }
      else if ((qName.equals("w:tr")) && 
        (isMySelf()) && 
        (attr.getLength() > 0))
      {
        int index = attr.getIndex("w:markId");
        if (index > -1)
        {
          this.curRowTag = new HierRowBean();
          this.curRowTag.setParentTable(this.curTableTag);
          this.curRowTag.setRowName(attr.getValue(index));
          this.curRowTag.setRowId(-1);
          this.curRowTag.setNeedDuplicate(true);
          this.curTableTag.addRow(this.curRowTag);
          this.isDirectWrite = false;
          this.elementContent = new StringBuffer();
          this.curRowTag.setContent(this.elementContent);
        }
      }
    }
    catch (Exception localException) {}
    super.startElement(namespaceURI, localName, qName, attr);
  }
  
  public void endElement(String namespaceURI, String localName, String qName)
  {
    boolean writed = false;
    try
    {
      if (qName.equals("w:tbl"))
      {
        if (isMySelf()) {
          if (this.arrTag.size() > 0)
          {
            this.elementContent = this.curTableTag.getContent();
            super.endElement(namespaceURI, localName, qName);
            writed = true;
            this.curRowTag = this.curTableTag.getParentRow();
            if (this.curTableTag.getTableName() == null) {
              this.curRowTag.setContent(this.curTableTag.convertToString());
            } else {
              this.curRowTag.setContent(new StringBuffer("{mcrp_hierarchic" + this.curTableTag.getTableName() + "}"));
            }
            this.curTableTag = ((HierTableBean)this.arrTag.get(this.arrTag.size() - 1));
            this.arrTag.remove(this.curTableTag);
            this.elementContent = this.curRowTag.getContent();
          }
          else
          {
            if (!this.arrTable.isEmpty()) {
              try
              {
                if (this.curTableTag.getTableName() != null)
                {
                  Object obj = this.arrTable.remove(this.curTableTag.getTableName());
                  if (obj != null) {
                    if ((obj instanceof RowSAXHandler)) {
                      this.curTableTag.generateStructureFromSaxHandler(obj);
                    } else {
                      this.curTableTag.generateStructure(obj);
                    }
                  }
                }
              }
              catch (Exception localException) {}
            }
            this.output.write(replaceContent(this.curTableTag.convertToString().toString()).getBytes("UTF-8"));
            this.curTableTag = null;
            this.isDirectWrite = true;
          }
        }
      }
      else if ((qName.equals("w:tr")) && 
        (isMySelf()))
      {
        this.elementContent = this.curRowTag.getContent();
        super.endElement(namespaceURI, localName, qName);
        writed = true;
        if (this.curRowTag.getRowName() == null) {
          this.curTableTag.setContent(this.curRowTag.convertToString());
        } else {
          this.curTableTag.setContent(new StringBuffer("{mcrp_hierarchic" + this.curRowTag.getRowName() + "}"));
        }
        this.curRowTag = null;
        this.elementContent = this.curTableTag.getContent();
      }
    }
    catch (Exception localException1) {}
    if (!writed) {
      super.endElement(namespaceURI, localName, qName);
    }
  }
  
  public void characters(char[] ch, int start, int length)
  {
    try
    {
      if (isMySelf())
      {
        if (!this.isDirectWrite) {
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
  
  private boolean isMySelf()
  {
    return getClass().toString().endsWith("com.venus.core.sax.HierTableSAXHandler");
  }
}
