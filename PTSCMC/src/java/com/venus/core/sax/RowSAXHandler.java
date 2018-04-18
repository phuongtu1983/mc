package com.venus.core.sax;

import com.venus.mc.core.SpineReportParser;
import java.util.ArrayList;

public class RowSAXHandler
  extends SAXContentHandler
{
  private String id;
  private int rowCount;
  private StringBuffer content = new StringBuffer();
  private ArrayList arrTab;
  private SpineReportParser report;
  
  public RowSAXHandler()
    throws Exception
  {}
  
  public RowSAXHandler(String id, SpineReportParser report)
    throws Exception
  {
    this.id = id;
    this.report = report;
  }
  
  public String getId()
  {
    return this.id;
  }
  
  public void setId(String id)
  {
    this.id = id;
  }
  
  public int getRowCount()
  {
    return this.rowCount;
  }
  
  public void setRowCount(int rowCount)
  {
    this.rowCount = rowCount;
  }
  
  public StringBuffer getContent()
  {
    return this.content;
  }
  
  public void setArrTab(ArrayList arrTab)
  {
    this.arrTab = arrTab;
  }
  
  public void setReport(SpineReportParser report)
  {
    this.report = report;
  }
  
  public SpineReportParser getReport()
  {
    return this.report;
  }
  
  public void duplicateRow()
  {
    int index = 0;
    String tab = "";
    String tempStr = this.content.toString();
    String temp = "";
    StringBuffer tempBuffer = new StringBuffer();
    if (this.content.toString().contains("matRow")) {
      tempBuffer.append(this.content.toString().toString().split("</w:t></w:r></w:p></w:tc></w:tr>")[0] + "</w:t></w:r></w:p></w:tc></w:tr>");
    } else {
      tempBuffer.append(this.content);
    }
    boolean ischanged = false;
    for (int j = 0; j < this.arrTab.size(); j++)
    {
      tab = (String)this.arrTab.get(j);
      this.report.setText(tab, normalize(this.report.getTabText(this.id, 0, tab)));
    }
    for (int i = 0; i < this.rowCount - 1; i++)
    {
      temp = tempStr;
      for (int j = 0; j < this.arrTab.size(); j++)
      {
        tab = (String)this.arrTab.get(j);
        index = this.content.indexOf("{" + tab + "}");
        if (index > -1)
        {
          temp = temp.replace("{" + tab + "}", "{" + tab + i + "}");
          ischanged = true;
          this.report.addNode(tab + i, normalize(this.report.getTabText(this.id, i + 1, tab)));
        }
      }
      tempBuffer.append(temp);
    }
    if (ischanged == true)
    {
      this.content = new StringBuffer();
      this.content.append(tempBuffer);
    }
  }
}
