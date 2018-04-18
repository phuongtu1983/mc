package com.venus.mc.process.store.mrir;

import com.venus.core.auth.OnlineUser;
import com.venus.core.util.LogUtil;
import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.MrirBean;
import com.venus.mc.bean.MrirDetailBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.DnDAO;
import com.venus.mc.dao.MrirDAO;
import com.venus.mc.database.DBUtil;
import com.venus.mc.util.MCUtil;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

public class DeleteMrirAction
  extends SpineAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    HttpSession session = request.getSession();
    String[] arrMrirId = request.getParameterValues("mrirId");
    OnlineUser user = MCUtil.getOnlineUser(session);
    LogUtil.info("Username : " + user.getName() + " - Form : " + mapping.getPath() + " - DELETE");
    int length = 0;
    MrirDAO mrirDAO = new MrirDAO();
    MrirBean mrirBean = null;
    int mrirId = 0;int dnId = 0;int osdId = 0;
    if (arrMrirId != null) {
      length = arrMrirId.length;
    }
    DnDAO dnDAO = new DnDAO();
    for (int i = 0; i < length; i++)
    {
      mrirId = NumberUtil.parseInt(arrMrirId[i], 0);
      if (mrirId > 0)
      {
        if (DBUtil.checkFieldExisted("msv", "mrir_id", mrirId))
        {
          ActionMessages errors = new ActionMessages();
          errors.add("delExisted", new ActionMessage("errors.mrir.delExisted"));
          saveErrors(request, errors);
          return false;
        }
        try
        {
          mrirBean = mrirDAO.getMrir(mrirId);
        }
        catch (Exception ex)
        {
          LogUtil.error(getClass(), ex.getMessage());
        }
        dnId = mrirBean.getDnId();
        ArrayList arrMat = null;
        try
        {
          arrMat = mrirDAO.getMrirDetailsByMrir(mrirId);
        }
        catch (Exception ex)
        {
          LogUtil.error(getClass(), ex.getMessage());
        }
        try
        {
          mrirDAO.deleteMrirDetailByMrirId(mrirId);
          osdId = mrirDAO.getOsDId(mrirId);
          mrirDAO.deleteOsDDetails(osdId);
          mrirDAO.deleteOsD(osdId);
        }
        catch (Exception ex)
        {
          LogUtil.error(getClass(), ex.getMessage());
        }
        try
        {
          mrirDAO.deleteMrir(mrirId);
        }
        catch (Exception ex)
        {
          LogUtil.error(getClass(), ex.getMessage());
        }
        if (arrMat != null) {
          for (int j = 0; j < arrMat.size(); j++)
          {
            MrirDetailBean detailBean = (MrirDetailBean)arrMat.get(j);
            try
            {
              dnDAO.updateDnDetailStatus(detailBean.getDndId(), 0);
            }
            catch (Exception ex)
            {
              LogUtil.error(getClass(), ex.getMessage());
            }
          }
        }
      }
    }
    return true;
  }
}
