package com.venus.mc.dao;

import com.venus.mc.database.DBUtil;
import java.sql.SQLException;

public class UsedMaterialImportDAO
  extends BasicDAO
{
  public int insertUsedMaterialImport(int msvId, int matId, int stoId, double quantity, int reqDetailId)
    throws Exception
  {
    if ((stoId == 0) || (matId == 0) || (quantity == 0.0D)) {
      return 0;
    }
    int result = 0;
    try
    {
      String sql = "";
      sql = "insert into used_material_import(sto_id, mat_id, msv_id, import_quantity, req_detail_id) Values (" + stoId + "," + matId + "," + msvId + "," + quantity + "," + reqDetailId + ")";
      
      result = DBUtil.executeInsert(sql);
    }
    catch (SQLException sqle)
    {
      throw new Exception(sqle.getMessage());
    }
    catch (Exception ex)
    {
      throw new Exception(ex.getMessage());
    }
    return result;
  }
}
