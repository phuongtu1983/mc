package com.venus.core.sax;

public class SAXColumn
{
  private int gridSpan = 1;
  private String data;
  
  public void setGridSpan(int gridSpan)
  {
    this.gridSpan = gridSpan;
  }
  
  public void setData(String data)
  {
    this.data = data;
  }
  
  public StringBuffer newCol()
  {
    StringBuffer strCol = new StringBuffer();
    strCol.append("<w:tc>");
    strCol.append("<w:tcPr>");
    strCol.append("<w:tcW w:type=\"dxa\"/>");
    if (this.gridSpan > 1)
    {
      strCol.append("<w:gridSpan w:val=\"");
      strCol.append(this.gridSpan);
      strCol.append("\"/>");
    }
    strCol.append("</w:tcPr>");
    strCol.append("<w:p>");
    strCol.append("<w:r>");
    strCol.append("<w:t>");
    strCol.append(this.data);
    strCol.append("</w:t>");
    strCol.append("</w:r>");
    strCol.append("</w:p>");
    strCol.append("</w:tc>");
    return strCol;
  }
}
