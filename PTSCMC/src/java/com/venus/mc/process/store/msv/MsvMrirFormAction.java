package com.venus.mc.process.store.msv;

import com.venus.core.util.DateUtil;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.MrirDAO;
import com.venus.mc.util.MCUtil;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.LabelValueBean;

public class MsvMrirFormAction
  extends SpineAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    String msvId = request.getParameter("msvId");
    if (GenericValidator.isBlankOrNull(msvId))
    {
      ArrayList arrMrir = null;
      try
      {
        MrirDAO mrirDAO = new MrirDAO();
        arrMrir = mrirDAO.getMrir4Msv(0);
      }
      catch (Exception ex)
      {
        Logger.getLogger(MsvFormAction.class.getName()).log(Level.SEVERE, null, ex);
      }
      if (arrMrir == null) {
        arrMrir = new ArrayList();
      }
      arrMrir.add(0, new LabelValueBean(MCUtil.getBundleString("message.msv.mrirsel"), "0"));
      MsvFormBean formBean = new MsvFormBean();
      formBean.setMrirId(0);
      formBean.setCreatedDate(DateUtil.today("dd/MM/yyyy"));
      formBean.setCreatedEmpName(MCUtil.getMemberFullName(request.getSession()));
      formBean.setCreatedEmpId(MCUtil.getMemberID(request.getSession()));
      
      request.setAttribute("msvObj", formBean);
      request.setAttribute("mrirList", arrMrir);
    }
    return true;
  }
}
