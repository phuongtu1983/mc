/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.pricecomparison;

import com.venus.core.auth.OnlineUser;
import com.venus.core.util.LogUtil;
import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.PriceComparisonBean;
import com.venus.mc.bean.PriceComparisonDetailBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.PriceComparisonDAO;
import com.venus.mc.dao.PriceComparisonDetailDAO;
import com.venus.mc.util.MCUtil;
import com.venus.mc.util.PermissionUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author mai vinh loc
 */
public class AddPriceComparisonAction extends SpineAction {

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
        PriceComparisonFormBean formBean = (PriceComparisonFormBean) form;
        int pcId = formBean.getPcId();
        boolean bNew = false;
        if (pcId == 0) {
            bNew = true;
        } else {
            bNew = false;
        }

        PriceComparisonBean bean = new PriceComparisonBean();
        bean.setPcId(formBean.getPcId());
        bean.setTenId(formBean.getTenId());
        bean.setCreatedDate(formBean.getCreatedDate());
        bean.setCreatedEmp(MCUtil.getMemberID(request.getSession()));
        bean.setSubject(formBean.getSubject());

        PriceComparisonDetailBean beanDetail = new PriceComparisonDetailBean();

        PriceComparisonDAO compareDAO = new PriceComparisonDAO();
        PriceComparisonDetailDAO tenderDetailDAO = new PriceComparisonDetailDAO();

        String[] detIds = formBean.getRegDetId();

        try {
            if (bNew) {
                formBean.setPcId(compareDAO.insertPriceComparison(bean));
                if (formBean.getPcId() > 0) {
                    for (int i = 0; i < detIds.length; i++) {
                        beanDetail.setPcId(formBean.getPcId());
                        beanDetail.setMatId(formBean.getMatId()[i]);
                        beanDetail.setLatestPrice((formBean.getLatestPrice()[i]).replaceAll(",", ""));
                        beanDetail.setInternetPrice((formBean.getInternetPrice()[i]).replaceAll(",", ""));
                        beanDetail.setProposedPrice((formBean.getProposedPrice()[i]).replaceAll(",", ""));
                        beanDetail.setCurrency1(formBean.getCurrency1()[i]);
                        beanDetail.setCurrency2(formBean.getCurrency2()[i]);
                        beanDetail.setEffectedDate(formBean.getEffectedDate()[i]);
                        beanDetail.setContractNumber(formBean.getContractNumber()[i]);
                        tenderDetailDAO.insertPriceComparisonDetail(beanDetail);
                    }
                }
            } else {
                HttpSession session = request.getSession();
                OnlineUser user = MCUtil.getOnlineUser(session);
                LogUtil.info("Username : " + user.getName() + " - Form : " + mapping.getPath() + " - EDIT");
                compareDAO.updatePriceComparison(bean);
                for (int i = 0; i < detIds.length; i++) {
                    beanDetail.setDetId(formBean.getRegDetId()[i]);
                    beanDetail.setPcId(formBean.getPcId());
                    beanDetail.setMatId(formBean.getMatId()[i]);
                    beanDetail.setLatestPrice((formBean.getLatestPrice()[i]).replaceAll(",", ""));
                    beanDetail.setInternetPrice((formBean.getInternetPrice()[i]).replaceAll(",", ""));
                    beanDetail.setProposedPrice((formBean.getProposedPrice()[i]).replaceAll(",", ""));
                    beanDetail.setCurrency1(formBean.getCurrency1()[i]);
                    beanDetail.setCurrency2(formBean.getCurrency2()[i]);
                    beanDetail.setEffectedDate(formBean.getEffectedDate()[i]);
                    beanDetail.setContractNumber(formBean.getContractNumber()[i]);
                    tenderDetailDAO.updatePriceComparisonDetail(beanDetail);
                }
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
        return PermissionUtil.PER_PURCHASING_TENDERPLAN;
    }
}
