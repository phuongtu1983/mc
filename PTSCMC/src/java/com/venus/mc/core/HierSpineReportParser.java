package com.venus.mc.core;

import com.venus.core.sax.DOMDocument;
import com.venus.core.sax.HierTableSAXHandler;
import javax.servlet.http.HttpServletRequest;

public class HierSpineReportParser
  extends SpineReportParser
{
  private int rowId = 1;
  
  public HierSpineReportParser() {}
  
  public int getRowId()
  {
    return this.rowId;
  }
  
  public void increaseRowId()
  {
    this.rowId += 1;
  }
  
  public void descreaseRowId()
  {
    this.rowId -= 1;
  }
  
  public HierSpineReportParser(String xmlTemplate, String wordTemplate, String resultFileName)
  {
    super(xmlTemplate, wordTemplate, resultFileName);
  }
  
  public void init(HttpServletRequest request)
    throws Exception
  {
    try
    {
      this.xmlProperties = new DOMDocument(this.xmlTemplate);
      this.sax = new HierTableSAXHandler(this.wordTemplate);
      this.root = this.xmlProperties.getRoot();
    }
    catch (Exception localException) {}
  }
  
  public int getNumberOfRowToDuplicate(int id, String name)
  {
    return 0;
  }
}
