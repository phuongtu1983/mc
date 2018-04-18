package com.venus.mc.process.store.mrv;

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

public class MrvMrirFormAction
  extends SpineAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    String mrvId = request.getParameter("mrvId");
    if (GenericValidator.isBlankOrNull(mrvId))
    {
      ArrayList arrMrir = null;
      try
      {
        MrirDAO mrirDAO = new MrirDAO();
        arrMrir = mrirDAO.getMrir4Mrv();
      }
      catch (Exception ex)
      {
        Logger.getLogger(MrvMrirFormAction.class.getName()).log(Level.SEVERE, null, ex);
      }
      if (arrMrir == null) {
        arrMrir = new ArrayList();
      }
      arrMrir.add(0, new LabelValueBean(MCUtil.getBundleString("message.msv.mrirsel"), "0"));
      MrvFormBean formBean = new MrvFormBean();
      formBean.setMrirId(0);
      formBean.setCreatedDate(DateUtil.today("dd/MM/yyyy"));
      formBean.setCreatedEmpName(MCUtil.getMemberFullName(request.getSession()));
      formBean.setCreatedEmpId(MCUtil.getMemberID(request.getSession()));
      
      request.setAttribute("mrvObj", formBean);
      request.setAttribute("mrirList", arrMrir);
    }
    return true;
  }
}
