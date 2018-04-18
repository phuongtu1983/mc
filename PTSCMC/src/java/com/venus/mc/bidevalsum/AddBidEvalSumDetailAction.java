/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.bidevalsum;

import com.venus.core.auth.OnlineUser;
import com.venus.core.util.LogUtil;
import com.venus.mc.bean.BidEvalSumVendorBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.BidEvalSumVendorDAO;
import com.venus.mc.util.MCUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author mai vinh loc
 */
  public class AddBidEvalSumDetailAction extends SpineAction {

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
        BidEvalSumDetailFormBean formBean = (BidEvalSumDetailFormBean) form;
        //       int detId = formBean.getDetId();
        //int terId = formBean.getTerId();
        boolean bNew = false;
        //      if (detId==0) {
        //         bNew = true;
        //     } else {
        //         bNew = false;
        //     }

        BidEvalSumVendorDAO bidVendorDAO = new BidEvalSumVendorDAO();
        BidEvalSumVendorBean beanVendor;


        try {
            if (bNew) {
                //        bidDAO.insertBidEvalSumDetail(bean);
            } else {
                HttpSession session = request.getSession();
                OnlineUser user = MCUtil.getOnlineUser(session);
                LogUtil.info("Username : " + user.getName() + " - Form : " + mapping.getPath() + " - EDIT");

                beanVendor = new BidEvalSumVendorBean();
      
                beanVendor.setBesvId(formBean.getBesvId());
                beanVendor.setDeliveryTime(formBean.getDeliveryTime());
                beanVendor.setPaymentMode(formBean.getPaymentMode());
                
                
           //     bidDAO.updateBidEvalSumDetail(bean);
                bidVendorDAO.updateBidEvalSumVendor(beanVendor);
            }

        } catch (Exception ex) {
            LogUtil.error("FAILED:Vender:add-" + ex.getMessage());
            ex.printStackTrace();
        }
        
        return true;
    }
}
