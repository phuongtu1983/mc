package com.venus.mc.process.store.level2;

import com.venus.core.auth.OnlineUser;
import com.venus.core.util.LogUtil;
import com.venus.core.util.NumberUtil;
import com.venus.core.util.OutputUtil;
import com.venus.mc.bean.ReturnedMaterialDiaryDetailBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.StoreDAO;
import com.venus.mc.util.MCUtil;
import com.venus.mc.util.PermissionUtil;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class DeleteRmsAction
  extends SpineAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    HttpSession session = request.getSession();
    String[] arrRmsId = request.getParameterValues("rmsId");
    try
    {
      OnlineUser user = MCUtil.getOnlineUser(session);
      LogUtil.info("Username : " + user.getName() + " - Form : " + mapping.getPath() + " - DELETE");
      int length = 0;
      StoreDAO storeDAO = new StoreDAO();
      if (arrRmsId != null) {
        length = arrRmsId.length;
      }
      int rmsId = 0;
      ReturnedMaterialDiaryDetailBean detail = null;
      for (int i = 0; i < length; i++) {
        if (!arrRmsId[i].equals("0"))
        {
          rmsId = NumberUtil.parseInt(arrRmsId[i], 0);
          ArrayList arrDetail = storeDAO.getReturnedMaterialStoreDetails(rmsId);
          for (int j = 0; j < arrDetail.size(); j++)
          {
            detail = (ReturnedMaterialDiaryDetailBean)arrDetail.get(j);
            storeDAO.deleteRmsDetail(detail.getDetId());
          }
          storeDAO.deleteRms(rmsId);
        }
      }
      OutputUtil.sendStringToOutput(response, "deleted");
    }
    catch (Exception ex)
    {
      LogUtil.error("FAILED:Rms:delete-" + ex.getMessage());
      ex.printStackTrace();
    }
    return true;
  }
  
  protected boolean isReturnStream()
  {
    return true;
  }
  
  protected String getFunction()
  {
    return PermissionUtil.FUNC_DELETE + "";
  }
  
  protected int getPermit()
  {
    return PermissionUtil.PER_STOCK_STORE2;
  }
}
