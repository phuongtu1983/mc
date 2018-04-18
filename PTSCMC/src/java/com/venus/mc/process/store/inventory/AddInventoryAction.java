/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.process.store.inventory;

import com.venus.core.auth.OnlineUser;
import com.venus.core.util.LogUtil;
import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.InventoryBean;
import com.venus.mc.bean.InventoryDetailBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.InventoryDAO;
import com.venus.mc.util.MCUtil;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

/**
 *
 * @author kngo
 */
public class AddInventoryAction extends SpineAction {

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

        InventoryFormBean formBean = (InventoryFormBean) form;
        InventoryDAO inventoryDAO = new InventoryDAO();
        InventoryBean bean = null;

        boolean bNew = false;
        boolean isExist = false;

        try {
            bean = inventoryDAO.getInventoryByStoId(formBean.getStoId());
        } catch (Exception ex) {
        }

        int invId = formBean.getInvId();
        if (invId == 0) {
            bNew = true;
            if (bean != null) {
                isExist = true;
            }
        } else {
            bNew = false;
            if (bean != null && bean.getInvId() != invId) {
                isExist = true;
            }
        }

        if (isExist) {
            ActionMessages errors = new ActionMessages();
            errors.add("inventoryExisted", new ActionMessage("errors.inventory.existed"));
            saveErrors(request, errors);
            return false;
        }

        bean = new InventoryBean();
        bean.setInvId(formBean.getInvId());
        bean.setStoId(formBean.getStoId());
        bean.setInvDate(formBean.getInvDate());
        bean.setCreatedEmp(formBean.getCreatedEmp());
        bean.setComment(formBean.getComment());

        try {
            if (bNew) {
                invId = inventoryDAO.insertInventory(bean);
            } else {
                HttpSession session = request.getSession();
                OnlineUser user = MCUtil.getOnlineUser(session);
                LogUtil.info("Username : " + user.getName() + " - Form : " + mapping.getPath() + " - EDIT");
                inventoryDAO.updateInventory(bean);
            }

            addDetail(invId, formBean);
        } catch (Exception ex) {
            LogUtil.error("FAILED:Inventory:add-" + ex.getMessage());
            ex.printStackTrace();
        }
        return true;
    }

    private InventoryDetailBean detExisted(ArrayList arrDet, int matId) {
        InventoryDetailBean bean = null;
        for (int i = 0; i < arrDet.size(); i++) {
            bean = (InventoryDetailBean) arrDet.get(i);
            if (bean.getMatId() == matId) {
                return bean;
            }
        }
        return null;
    }

    private void addDetail(int invId, InventoryFormBean formBean) {
        if (invId > 0) {
            InventoryDAO inventoryDAO = new InventoryDAO();
            ArrayList arrDet = null;
            
            try {
                arrDet = inventoryDAO.getInventoryDetailsByInvId(invId);
            } catch (Exception ex) {
                arrDet = new ArrayList();
            }

            double quantityActual = 0;
            double quantityDocument = 0;
            String commentDetail = "";

            if (formBean.getMaterial() != null) {
                String[] matIds = formBean.getMaterial();
                InventoryDetailBean matBean = null;
                int currMatId = 0;
                for (int i = 0; i < matIds.length; i++) {
                    currMatId = NumberUtil.parseInt(matIds[i], 0);
                    matBean = detExisted(arrDet, currMatId);
                    try {
                        InventoryBean invBean = inventoryDAO.getInventory(invId);

                        if (matBean == null) { // insert
                            matBean = new InventoryDetailBean();
                            matBean.setInvId(invId);
                            matBean.setMatId(currMatId);
                            matBean.setQuantityActual(NumberUtil.parseDouble(formBean.getQuantityActual()[i], 0));

                            InventoryDetailBean invDetailBean = inventoryDAO.getMaterialsByStoId(invBean.getStoId(), currMatId);
                            matBean.setQuantityDocument(invDetailBean.getQuantityDocument());
                            matBean.setCommentDetail(formBean.getCommentDetail()[i]);

                            inventoryDAO.insertInventoryDetail(matBean);
                        } else { // update
                            boolean bUpdated = false;
                            quantityActual = NumberUtil.parseDouble(formBean.getQuantityActual()[i], 0);
                            if (quantityActual != matBean.getQuantityActual()) {
                                matBean.setQuantityActual(quantityActual);
                                bUpdated = true;
                            }

//                        quantityDocument = NumberUtil.parseInt(formBean.getQuantityDocument()[i], 0);
//                        if (quantityDocument != matBean.getQuantityDocument()) {
//                            matBean.setQuantityDocument(quantityDocument);
//                            bUpdated = true;
//                        }

                            commentDetail = formBean.getCommentDetail()[i];
                            if (!commentDetail.equals(matBean.getCommentDetail())) {
                                matBean.setCommentDetail(commentDetail);
                                bUpdated = true;
                            }

                            if (quantityActual != quantityDocument) {
                                matBean.setIsChanged(1);
                                bUpdated = true;
                            }

                            if (bUpdated) {
                                inventoryDAO.updateInventoryDetail(matBean);
                                inventoryDAO.updateMaterialStoreRequest(invBean.getStoId(), currMatId, quantityActual);
                            }
                        }
                    } catch (Exception ex) {
                    }
                }
            }
        }
    }
}
