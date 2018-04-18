package com.venus.mc.process.store.equipment;

/**
 * @author Mai Vinh Loc
 */
import com.venus.core.auth.OnlineUser;
import com.venus.core.util.LogUtil;
import com.venus.mc.bean.EquipmentBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.EquipmentDAO;
import com.venus.mc.util.MCUtil;
import com.venus.mc.util.PermissionUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

public class AddEquipmentAction extends SpineAction {

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
        EquipmentFormBean formBean = (EquipmentFormBean) form;
        int equId = formBean.getEquId();
        boolean bNew = false;
        if (equId == 0) {
            bNew = true;
        } else {
            bNew = false;
        }

        EquipmentBean bean = new EquipmentBean();
        bean.setEquId(formBean.getEquId());
        bean.setMivId(formBean.getMivId());
        bean.setMivNumber(formBean.getMivNumber());
        bean.setDecisionNumber(formBean.getDecisionNumber());
        bean.setTest(formBean.getTest());
        bean.setManageCode(formBean.getManageCode());
        bean.setEquipmentName(formBean.getEquipmentName());
        bean.setRequestNumber(formBean.getRequestNumber());
        bean.setContractNumber(formBean.getContractNumber());
        bean.setTestNumber(formBean.getTestNumber());
        bean.setUnit(formBean.getUnit());
        bean.setUsedCode(formBean.getUsedCode());
        bean.setColorCode(formBean.getColorCode());
        bean.setSpecCerts(formBean.getSpecCerts());
        bean.setFuelLevel(formBean.getFuelLevel());
        bean.setStatus(formBean.getStatus());
        bean.setAppearedDate(formBean.getAppearedDate());
        bean.setUsedDate(formBean.getUsedDate());
        bean.setComment(formBean.getComment());

        bean.setReqDetailId(formBean.getReqDetailId());
        bean.setMatId(formBean.getMatId());
        bean.setConId(formBean.getConId());

        EquipmentDAO equipmentDAO = new EquipmentDAO();

        try {
            boolean isExist = false;
            /*         isExist = equipmentDAO.checkDecisionNumber(formBean.getEquId(), formBean.getDecisionNumber());
            if (isExist) {
            ActionMessages errors = new ActionMessages();
            errors.add("decisionNumberExisted", new ActionMessage("errors.equipment.existedDecisionNumber"));
            saveErrors(request, errors);
            return false;
            }
             */
            isExist = false;
            isExist = equipmentDAO.checkUsedCode(bean.getEquId(), bean.getUsedCode());
            if (isExist) {
                ActionMessages errors = new ActionMessages();
                errors.add("usedCodeExisted", new ActionMessage("errors.equipment.existedUsedCode"));
                saveErrors(request, errors);
                return false;
            }
            if (bNew) {
                equipmentDAO.insertEquipment(bean);
            } else {
                HttpSession session = request.getSession();
                OnlineUser user = MCUtil.getOnlineUser(session);
                LogUtil.info("Username : " + user.getName() + " - Form : " + mapping.getPath() + " - EDIT");
                equipmentDAO.updateEquipment(bean);
            }
        } catch (Exception ex) {
            LogUtil.error("FAILED:Vender:add-" + ex.getMessage());
            ex.printStackTrace();
        }
        return true;
    }

    @Override
    protected String getFunction() {
        return PermissionUtil.FUNC_EDIT + "";
    }

    @Override
    protected int getPermit() {
        return PermissionUtil.PER_EQUIPMENT;
    }
}
