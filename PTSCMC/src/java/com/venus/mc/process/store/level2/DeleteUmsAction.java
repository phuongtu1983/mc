package com.venus.mc.process.store.level2;

import com.venus.core.auth.OnlineUser;
import com.venus.core.util.LogUtil;
import com.venus.core.util.NumberUtil;
import com.venus.core.util.OutputUtil;
import com.venus.mc.bean.UsedMaterialDiaryDetailBean;
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

public class DeleteUmsAction
  extends SpineAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    HttpSession session = request.getSession();
    String[] arrUmsId = request.getParameterValues("umsId");
    try
    {
      OnlineUser user = MCUtil.getOnlineUser(session);
      LogUtil.info("Username : " + user.getName() + " - Form : " + mapping.getPath() + " - DELETE");
      int length = 0;
      StoreDAO storeDAO = new StoreDAO();
      if (arrUmsId != null) {
        length = arrUmsId.length;
      }
      int umsId = 0;
      UsedMaterialDiaryDetailBean detail = null;
      for (int i = 0; i < length; i++) {
        if (!arrUmsId[i].equals("0"))
        {
          umsId = NumberUtil.parseInt(arrUmsId[i], 0);
          ArrayList arrDetail = storeDAO.getUsedMaterialStoreDetails(umsId);
          for (int j = 0; j < arrDetail.size(); j++)
          {
            detail = (UsedMaterialDiaryDetailBean)arrDetail.get(j);
            storeDAO.deleteUmsDetail(detail.getDetId());
          }
          storeDAO.deleteUms(umsId);
        }
      }
      OutputUtil.sendStringToOutput(response, "deleted");
    }
    catch (Exception ex)
    {
      LogUtil.error("FAILED:Ums:delete-" + ex.getMessage());
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
