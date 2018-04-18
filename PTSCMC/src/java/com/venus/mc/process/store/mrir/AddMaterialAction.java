package com.venus.mc.process.store.mrir;

/**
 * @author Mai vinh loc
 */
import com.venus.core.util.LogUtil;
import com.venus.core.util.NumberUtil;
import com.venus.core.util.OutputUtil;
import com.venus.core.util.StringUtil;
import com.venus.mc.bean.MaterialBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.MaterialDAO;
import com.venus.mc.dao.MrirDAO;
import com.venus.mc.material.MaterialFormBean;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class AddMaterialAction extends SpineAction {

    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    @Override
    public boolean doAction(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        int detId = NumberUtil.parseInt(request.getParameter("detId"), 0);
        MaterialFormBean formBean = (MaterialFormBean) form;
        MaterialBean bean = new MaterialBean();
        bean.setMatId(formBean.getMatId());
//        bean.setCode("");
        bean.setCode(null);
        bean.setUniId(formBean.getUniId());
        bean.setNameEn(StringUtil.encodeHTML(formBean.getNameEn()));
        bean.setNameVn(StringUtil.encodeHTML(formBean.getNameVn()));
//        if (bean.getNameEn().length() == 0) {
//            bean.setNameEn(bean.getNameVn());
//        }
//      
        bean.setNote(StringUtil.encodeHTML(formBean.getNote()));
        MaterialDAO materialDAO = new MaterialDAO();
        int matId = 0;
        try {

            matId = materialDAO.checkName(bean.getMatId(), bean.getNameVn(), bean.getNameEn());
            if (matId == 0) {
//                ActionMessages errors = new ActionMessages();
//                errors.add("matNameExisted", new ActionMessage("errors.material.existedName"));
//                saveErrors(request, errors);
//                return false;
                matId = materialDAO.insertMaterialOsD(bean);
            }

            if (matId > 0) {
                MrirDAO mrirDAO = new MrirDAO();
                try {
                    mrirDAO.updateOsDMatIdTemp(matId, detId);
                } catch (Exception ex) {
                    LogUtil.error("FAILED:TechEval:add-" + ex.getMessage());
                    ex.printStackTrace();
                }
            }
            OutputUtil.sendStringToOutput(response, "success:" + matId);
        } catch (Exception ex) {
            LogUtil.error("FAILED:RequestMaterial:add-" + ex.getMessage());
            ex.printStackTrace();
        }
        return true;
    }

    @Override
    protected boolean isReturnStream() {
        return true;
    }
}
