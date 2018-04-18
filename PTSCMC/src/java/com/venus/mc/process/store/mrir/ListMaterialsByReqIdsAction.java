package com.venus.mc.process.store.mrir;

import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.DeliveryNoticeDetailBean;
import com.venus.mc.bean.MrirDetailBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.DeliveryNoticeDAO;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class ListMaterialsByReqIdsAction
  extends SpineAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    int reqId = NumberUtil.parseInt(request.getParameter("reqId"), 0);
    int dnId = NumberUtil.parseInt(request.getParameter("dnId"), 0);
    try
    {
      if (reqId > 0)
      {
        ArrayList arrMat = new ArrayList();
        ArrayList arrMat2 = new ArrayList();
        
        DeliveryNoticeDAO dnDAO = new DeliveryNoticeDAO();
        arrMat = dnDAO.getDeliveryNoticeDetails(dnId, reqId);
        
        MrirDetailBean mrirDetailBean = null;
        DeliveryNoticeDetailBean dnBean = null;
        for (int i = 0; i < arrMat.size(); i++)
        {
          dnBean = (DeliveryNoticeDetailBean)arrMat.get(i);
          mrirDetailBean = new MrirDetailBean();
          mrirDetailBean.setMatId(dnBean.getMatId());
          mrirDetailBean.setMatName(dnBean.getMatName());
          mrirDetailBean.setUnit(dnBean.getUnit());
          mrirDetailBean.setQuantity(dnBean.getQuantity());
          arrMat2.add(mrirDetailBean);
        }
        request.setAttribute("mrirMaterialList", arrMat2);
      }
    }
    catch (Exception localException) {}
    this.actionForwardResult = "materials";
    return true;
  }
}
