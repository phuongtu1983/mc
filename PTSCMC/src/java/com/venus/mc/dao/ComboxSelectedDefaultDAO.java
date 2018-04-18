package com.venus.mc.dao;

import com.venus.mc.database.DBUtil;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ComboxSelectedDefaultDAO
  extends BasicDAO
{
  public int getEmployeeId(String kind, int orgId)
    throws Exception
  {
    ResultSet rs = null;
    String sql = "SELECT  MAX(employee_id) AS employee_id FROM combobox_selected_default WHERE kind='" + kind + "' AND organization_id=" + orgId;
    try
    {
      rs = DBUtil.executeQuery(sql);
      if (rs.next()) {
        return rs.getInt("employee_id");
      }
    }
    catch (SQLException sqle)
    {
      throw new Exception(sqle.getMessage());
    }
    catch (Exception ex)
    {
      throw new Exception(ex.getMessage());
    }
    finally
    {
      if (rs != null) {
        DBUtil.closeConnection(rs);
      }
    }
    return 0;
  }
}
